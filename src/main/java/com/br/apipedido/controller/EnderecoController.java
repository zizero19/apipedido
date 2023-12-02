package com.br.apipedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipedido.dto.EnderecoDTO;
import com.br.apipedido.model.Endereco;
import com.br.apipedido.service.EnderecoService;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping("/cadastro")
    public String cadastroEndereco(Endereco endereco) {
        return service.cadastro(endereco);
    }

    @GetMapping("/lista")
    public List<EnderecoDTO> listaEnderecos() {
        return service.listaEnderecos();
    }

    @GetMapping("/busca/{id}")
    public EnderecoDTO buscaEndereco(@PathVariable("id") int id) {
        return service.buscaEndereco(id);
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirEndereco(@PathVariable("id") int id) {
        return service.excluirEndereco(id);
    }

}
