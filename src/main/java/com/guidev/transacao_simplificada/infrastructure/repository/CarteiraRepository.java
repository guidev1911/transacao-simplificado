package com.guidev.transacao_simplificada.infrastructure.repository;

import com.guidev.transacao_simplificada.infrastructure.entities.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    
}
