package com.br.apipedido.dto;

import com.br.apipedido.model.FormaPagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoDTO {

    private int id;
    private String tipo;

    public FormaPagamentoDTO(FormaPagamento formaPagamento) {
        this.id = formaPagamento.getId();
        this.tipo = formaPagamento.getTipo();
    }
}
