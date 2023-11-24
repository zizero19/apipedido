package com.br.apipedido.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apipedido.dto.PedidoDTO;
import com.br.apipedido.model.Pedido;
import com.br.apipedido.repository.PedidoRepository;
import com.br.apipedido.repository.ProdutoRepository;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public String novoPedido(Pedido pedido) {
        try {
            pedido.setData(LocalDate.now());
            pedidoRepository.save(pedido);
            return "Pedido salvo com sucesso";
        } catch (Exception e) {
            return "Erro ao cadastrar o pedido: " + e.getMessage();
        }
    }

    public List<PedidoDTO> listaPedido() {
        try {
            List<Pedido> pedidos = pedidoRepository.findAll();
            return pedidos.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Erro ao criar lista: " + e.getMessage());
            return null;
        }
    }
}
