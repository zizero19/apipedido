package com.br.apipedido.dto;

import java.time.LocalDate;
import java.util.List;

import com.br.apipedido.model.Pedido;
import com.br.apipedido.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private int id;
    private double valorTotal;
    private LocalDate data;
    private List<Produto> produtos;
    private int qtd;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.valorTotal = pedido.getValorTotal();
        this.data = pedido.getData();
        this.produtos = pedido.getProdutos();
        this.qtd = pedido.getQtd();
    }
}
