package com.br.apipedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipedido.dto.FormaPagamentoDTO;
import com.br.apipedido.model.FormaPagamento;
import com.br.apipedido.service.FormaPagamentoService;

@RestController
@RequestMapping(value = "/formaPagamento")
public class FormaPagamentoController {

    @Autowired
    FormaPagamentoService service;

    @PostMapping("/cadastro")
    public String cadastroFormaPagamento(FormaPagamento formaPagamento) {
        return service.cadastro(formaPagamento);
    }

    @GetMapping("/lista")
    public List<FormaPagamentoDTO> listaFormaPagamento() {
        return service.listaFormaPagamentos();
    }

    @GetMapping("/busca/{id}")
    public FormaPagamentoDTO buscaProduto(@PathVariable("id") int id) {
        return service.buscaFormaPagamento(id);
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirFormaPagamento(@PathVariable("id") int id) {
        return service.excluirFormaPagamento(id);
    }
}
