package com.br.apipedido.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apipedido.dto.FormaPagamentoDTO;
import com.br.apipedido.model.FormaPagamento;
import com.br.apipedido.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {

    @Autowired
    FormaPagamentoRepository repository;

    // Método para receber e salvar um FormaPagamento novo (se houver um
    // FormaPagamento com o
    // mesmo nome, cancela a operação)
    public String cadastro(FormaPagamento formaPagamento) {
        List<FormaPagamento> formaPagamentos = repository.findAll();

        try {
            for (FormaPagamento f : formaPagamentos) {
                if (formaPagamento.getTipo().equalsIgnoreCase(f.getTipo())) {
                    return "Já existe uma forma de pagamento com o mesmo tipo!!";
                }
            }
            repository.save(formaPagamento);
            return "Forma de pagamento cadastrado com sucesso!!";
        } catch (Exception e) {
            return "Erro ao cadastrar a forma de pagamento" + e.getMessage();
        }

    }

    // Método para listar todos as formas de pagamento
    public List<FormaPagamentoDTO> listaFormaPagamentos() {
        try {
            List<FormaPagamento> formaPagamentos = repository.findAll();
            return formaPagamentos.stream().map(x -> new FormaPagamentoDTO(x)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Erro ao criar lista: " + e.getMessage());
            return null;
        }

    }

    // Método para buscar apenas uma forma de pagamento
    public FormaPagamentoDTO buscaFormaPagamento(int id) {
        try {
            FormaPagamento formaPagamento = repository.findById(id).get();
            FormaPagamentoDTO dto = new FormaPagamentoDTO(formaPagamento);
            return dto;
        } catch (Exception e) {
            System.out.println("Erro ao buscar a forma de pagamento: " + e.getMessage());
            return null;
        }

    }

    // Método para excluir uma forma de pagamento
    public String excluirFormaPagamento(int id) {
        try {
            repository.deleteById(id);
            return "Forma de Pagamento excluido com sucesso";
        } catch (Exception e) {
            return "Erro ao excluir Forma de Pagamento: " + e.getMessage();
        }

    }
}
