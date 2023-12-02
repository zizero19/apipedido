package com.br.apipedido.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apipedido.dto.EnderecoDTO;
import com.br.apipedido.model.Endereco;
import com.br.apipedido.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    // Método para receber e salvar um endereço novo
    public String cadastro(Endereco endereco) {
        try {
            repository.save(endereco);
            return "endereco cadastrado com sucesso!!";
        } catch (Exception e) {
            return "Erro ao cadastrar o endereco" + e.getMessage();
        }

    }

    // Método para listar todos endereços
    public List<EnderecoDTO> listaEnderecos() {
        try {
            List<Endereco> enderecos = repository.findAll();
            return enderecos.stream().map(x -> new EnderecoDTO(x)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Erro ao criar lista: " + e.getMessage());
            return null;
        }

    }

    // Método para buscar apenas um endereço
    public EnderecoDTO buscaEndereco(int id) {
        try {
            Endereco endereco = repository.findById(id).get();
            EnderecoDTO dto = new EnderecoDTO(endereco);
            return dto;
        } catch (Exception e) {
            System.out.println("Erro ao buscar endereco: " + e.getMessage());
            return null;
        }

    }

    // Método para excluir um endereço
    public String excluirEndereco(int id) {
        try {
            repository.deleteById(id);
            return "endereco excluido com sucesso";
        } catch (Exception e) {
            return "Erro ao excluir endereco: " + e.getMessage();
        }

    }

}
