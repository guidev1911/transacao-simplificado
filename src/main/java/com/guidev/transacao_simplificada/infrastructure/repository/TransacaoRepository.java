package com.guidev.transacao_simplificada.infrastructure.repository;

import com.guidev.transacao_simplificada.infrastructure.entities.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacoes, Long> {
    
}
