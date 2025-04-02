package com.guidev.transacao_simplificada.infrastructure.repository;

import com.guidev.transacao_simplificada.infrastructure.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
