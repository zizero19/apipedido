package com.br.apipedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipedido.dto.ProdutoDTO;
import com.br.apipedido.model.Produto;
import com.br.apipedido.service.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @PostMapping("/cadastro")
    public String cadastroProduto(Produto produto) {
        return service.cadastro(produto);
    }

    @GetMapping("/lista")
    public List<ProdutoDTO> listaProdutos() {
        return service.listaProdutos();
    }

    @GetMapping("/busca/{id}")
    public ProdutoDTO buscaProduto(@PathVariable("id") int id) {
        return service.buscaProduto(id);
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable("id") int id) {
        return service.excluirProduto(id);
    }
}
