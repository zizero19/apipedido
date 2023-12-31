package com.br.apipedido.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.apipedido.dto.ProdutoDTO;
import com.br.apipedido.model.Produto;
import com.br.apipedido.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    // Método para receber e salvar um produto novo (se houver um produto com o
    // mesmo nome, cancela a operação)
    public String cadastro(Produto produto) {
        List<Produto> produtos = repository.findAll();

        try {
            for (Produto p : produtos) {
                if (produto.getNome().equalsIgnoreCase(p.getNome())) {
                    return "Já existe um produto com o mesmo nome!!";
                }
            }
            repository.save(produto);
            return "Produto cadastrado com sucesso!!";
        } catch (Exception e) {
            return "Erro ao cadastrar o produto" + e.getMessage();
        }

    }

    // Método para listar todos produtos
    public List<ProdutoDTO> listaProdutos() {
        try {
            List<Produto> produtos = repository.findAll();
            return produtos.stream().map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Erro ao criar lista: " + e.getMessage());
            return null;
        }

    }

    // Método para buscar apenas um produto
    public ProdutoDTO buscaProduto(int id) {
        try {
            Produto produto = repository.findById(id).get();
            ProdutoDTO dto = new ProdutoDTO(produto);
            return dto;
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
            return null;
        }

    }

    // Método para excluir um produto
    public String excluirProduto(int id) {
        try {
            repository.deleteById(id);
            return "Produto excluido com sucesso";
        } catch (Exception e) {
            return "Erro ao excluir produto: " + e.getMessage();
        }

    }

}
