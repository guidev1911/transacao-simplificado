package com.guidev.transacao_simplificada.services;

import com.guidev.transacao_simplificada.infrastructure.entities.Carteira;
import com.guidev.transacao_simplificada.infrastructure.repository.CarteiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraRepository repository;

    public void Salvar(Carteira carteira){
        repository.save(carteira);
    }
}
