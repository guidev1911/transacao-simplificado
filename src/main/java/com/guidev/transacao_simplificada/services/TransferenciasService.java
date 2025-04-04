package com.guidev.transacao_simplificada.services;

import com.guidev.transacao_simplificada.controller.TrasacaoDTO;
import com.guidev.transacao_simplificada.infrastructure.entities.TipoUsuario;
import com.guidev.transacao_simplificada.infrastructure.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferenciasService {

    private final UsuarioService usuarioService;
    public void transferirValores(TrasacaoDTO trasacaoDTO){
        Usuario pagador = usuarioService.buscarUsuario(trasacaoDTO.payer());
        Usuario recebedor = usuarioService.buscarUsuario(trasacaoDTO.payee());

        validaPagadorLojista(pagador);

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

}
