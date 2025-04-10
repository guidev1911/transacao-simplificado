package com.guidev.transacao_simplificada.services;

import com.guidev.transacao_simplificada.controller.TransacaoDTO;
import com.guidev.transacao_simplificada.infrastructure.entities.Carteira;
import com.guidev.transacao_simplificada.infrastructure.entities.TipoUsuario;
import com.guidev.transacao_simplificada.infrastructure.entities.Transacoes;
import com.guidev.transacao_simplificada.infrastructure.entities.Usuario;
import com.guidev.transacao_simplificada.infrastructure.exceptions.BadRequestException;
import com.guidev.transacao_simplificada.infrastructure.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final UsuarioService usuarioService;
    private final AutorizacaoService autorizacaoService;
    private final CarteiraService carteiraService;
    private final TransacaoRepository transacaoRepository;
    private final NotificacaoService notificacaoService;

    @Transactional
    public void transferirValores(TransacaoDTO trasacaoDTO){
        Usuario pagador = usuarioService.buscarUsuario(trasacaoDTO.payer());
        Usuario recebedor = usuarioService.buscarUsuario(trasacaoDTO.payee());

        validaPagadorLojista(pagador);
        validarSaldoUsuario(pagador, trasacaoDTO.value());
        validarTransferencia();

        pagador.getCarteira().setSaldo(pagador.getCarteira().getSaldo().subtract(trasacaoDTO.value()));
        atualizarSaldoCarteira(pagador.getCarteira());
        recebedor.getCarteira().setSaldo(pagador.getCarteira().getSaldo().add(trasacaoDTO.value()));
        atualizarSaldoCarteira(recebedor.getCarteira());

        Transacoes transacoes = Transacoes.builder()
                .valor(trasacaoDTO.value())
                .pagador(pagador)
                .recebedor(recebedor)
                .build();

        transacaoRepository.save(transacoes);
        enviarNotificacao();
    }

    private void validaPagadorLojista(Usuario usuario){
        try{
            if(usuario.getTipoUsuario().equals(TipoUsuario.LOJISTA)){
                throw new IllegalArgumentException("Transação não autorizada para esse tipo de usuário");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private void validarSaldoUsuario(Usuario usuario, BigDecimal valor){
        try{
            if (usuario.getCarteira().getSaldo().compareTo(valor) < 0){
                throw new IllegalArgumentException("Transação não autorizada, saldo insuficiente");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private void validarTransferencia(){
        try{
            if (!autorizacaoService.validarTransferencia()){
                throw new IllegalArgumentException("Transação não autorizada pela API");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private void atualizarSaldoCarteira(Carteira carteira){
        carteiraService.salvar(carteira);
    }

    private void enviarNotificacao(){
        try{
            notificacaoService.enviarNotificacao();
        }catch (HttpClientErrorException e){
            throw new BadRequestException("Erro ao enviar notificação");
        }
    }

}
