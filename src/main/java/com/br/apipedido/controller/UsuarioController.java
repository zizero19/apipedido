package com.br.apipedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipedido.dto.UsuarioDTO;
import com.br.apipedido.model.Usuario;
import com.br.apipedido.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping("/cadastro")
    public String cadastroUsuario(Usuario usuario) {
        return service.cadastro(usuario);
    }

    @GetMapping("/lista")
    public List<UsuarioDTO> listaProdutos() {
        return service.listaUsuarios();
    }

    @GetMapping("/busca/{id}")
    public UsuarioDTO buscaUsuario(@PathVariable("id") int id) {
        return service.buscaUsuario(id);
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable("id") int id) {
        return service.excluirUsuario(id);
    }
}
