package com.br.apipedido.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apipedido.dto.UsuarioDTO;
import com.br.apipedido.model.Usuario;
import com.br.apipedido.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    // Método para cadastrar usuário (se houver outro usuario com o mesmo cpf
    // a operação é cancelada)
    public String cadastro(Usuario usuario) {
        List<Usuario> usuarios = repository.findAll();

        try {
            for (Usuario u : usuarios) {
                if (usuario.getCpf().equalsIgnoreCase(u.getCpf())) {
                    return "Já há um usuario cadastrado com este CPF!!";
                }
            }

            repository.save(usuario);
            return "Usuario cadastrado com sucesso!!";
        } catch (Exception e) {
            return "Erro ao cadastrar o usuario" + e.getMessage();
        }
    }

    // Método para listar todos os usuarios
    public List<UsuarioDTO> listaUsuarios() {
        try {
            List<Usuario> usuarios = repository.findAll();
            return usuarios.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Erro ao criar lista: " + e.getMessage());
            return null;
        }
    }

    // Método para buscar apenas um usuario
    public UsuarioDTO buscaUsuario(int id) {
        try {
            Usuario usuario = repository.findById(id).get();
            UsuarioDTO dto = new UsuarioDTO(usuario);
            return dto;
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
            return null;
        }

    }

    // Método para excluir um usuario
    public String excluirUsuario(int id) {
        try {
            repository.deleteById(id);
            return "Usuario excluido com sucesso";
        } catch (Exception e) {
            return "Erro ao excluir usuario: " + e.getMessage();
        }

    }

}
