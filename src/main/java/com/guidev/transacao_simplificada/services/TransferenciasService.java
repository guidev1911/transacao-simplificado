package com.guidev.transacao_simplificada.services;

import com.guidev.transacao_simplificada.controller.TrasacaoDTO;
import com.guidev.transacao_simplificada.infrastructure.entities.TipoUsuario;
import com.guidev.transacao_simplificada.infrastructure.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferenciasService {

    private final UsuarioService usuarioService;
    private final AutorizacaoService autorizacaoService;

    @Transactional
    public void transferirValores(TrasacaoDTO trasacaoDTO){
        Usuario pagador = usuarioService.buscarUsuario(trasacaoDTO.payer());
        Usuario recebedor = usuarioService.buscarUsuario(trasacaoDTO.payee());

        validaPagadorLojista(pagador);
        validarSaldoUsuario(pagador, trasacaoDTO.value());
        validarTransferencia();

        pagador.getCarteira().setSaldo(pagador.getCarteira().getSaldo().subtract(trasacaoDTO.value()));

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

}
