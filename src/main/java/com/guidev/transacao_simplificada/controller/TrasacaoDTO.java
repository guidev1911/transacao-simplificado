package com.guidev.transacao_simplificada.controller;

import java.math.BigDecimal;

public record TrasacaoDTO(BigDecimal value, Long payer, Long payee){

}
