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
    private String nomeCliente;
    private double valorTotal;
    private LocalDate data;
    private List<Produto> produtos;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.nomeCliente = pedido.getNomeCliente();
        this.valorTotal = pedido.getValorTotal();
        this.data = pedido.getData();
        this.produtos = pedido.getProdutos();
    }
}
