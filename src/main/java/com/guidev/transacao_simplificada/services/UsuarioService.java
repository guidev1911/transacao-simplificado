package com.guidev.transacao_simplificada.services;
import com.guidev.transacao_simplificada.infrastructure.entities.Usuario;
import com.guidev.transacao_simplificada.infrastructure.exceptions.UserNotFound;
import com.guidev.transacao_simplificada.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario buscarUsuario(Long id){
        return repository.findById(id).
                orElseThrow(() -> new UserNotFound("usuário não encontrado"));
    }
}
