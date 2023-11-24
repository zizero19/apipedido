package com.br.apipedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipedido.dto.PedidoDTO;
import com.br.apipedido.model.Pedido;
import com.br.apipedido.service.PedidoService;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

    @Autowired
    PedidoService service;

    @PostMapping("/cadastro")
    public String cadastroPedido(Pedido pedido) {
        return service.novoPedido(pedido);
    }

    @GetMapping("/lista")
    public List<PedidoDTO> listPedidos() {
        return service.listaPedido();
    }

    @GetMapping("/busca/{id}")
    public PedidoDTO buscarPedido(@PathVariable("id") int id) {
        return service.buscaPedido(id);
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirPedido(@PathVariable("id") int id) {
        return service.excluirPedido(id);
    }
}
