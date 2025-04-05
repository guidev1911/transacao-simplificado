package com.guidev.transacao_simplificada.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "autorizacao", url = "https://util.devi.tools/api/v2/authorize")
public interface AutorizacaoClient {

    @GetMapping
    AutorizacaoDTO validarAutorizacao();

}