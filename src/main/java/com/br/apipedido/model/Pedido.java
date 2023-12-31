package com.br.apipedido.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double valorTotal;
    private LocalDate data;
    private int qtd;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "pedido_produto", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private FormaPagamento formaPagamento;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Endereco endereco;
}
