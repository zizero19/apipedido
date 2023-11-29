package com.br.apipedido.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.br.apipedido.dto.PedidoDTO;
import com.br.apipedido.enums.StatusEmail;
import com.br.apipedido.model.Email;
import com.br.apipedido.model.Pedido;
import com.br.apipedido.model.Produto;
import com.br.apipedido.repository.EmailRepository;
import com.br.apipedido.repository.PedidoRepository;
import com.br.apipedido.repository.ProdutoRepository;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    EmailRepository emailRepository;

    // Método para criar um novo pedido
    public String novoPedido(Pedido pedido) {
        Email email = new Email();
        double valorTotalPedido = 0.0;
        try {
            for (Produto produto : pedido.getProdutos()) {
                valorTotalPedido += produto.getValor();
            }
            pedido.setValorTotal(valorTotalPedido);
            pedido.setData(LocalDate.now());
            pedidoRepository.save(pedido);

            email.setOwnerRef("Lanchonete Rei Dos Lanches");
            email.setEmailFrom("cicero.ferro.78@gmail.com");
            email.setEmailTo("andreihaendel@gmail.com");
            email.setSubject("Pedido N° " + pedido.getId());

            StringBuilder textoEmail = new StringBuilder();
            textoEmail.append("PEDIDO N° ").append(pedido.getId()).append("\n Lista de produtos: \n");

            for (Produto produto : pedido.getProdutos()) {
                textoEmail.append(produto.getNome() + " | " + produto.getValor());
                textoEmail.append("\n");
            }
            textoEmail.append("\n Valor Total: " + pedido.getValorTotal());

            email.setText(textoEmail.toString());
            email.setSendDateEmail(LocalDateTime.now());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
            return "Pedido e email salvo com sucesso";
        } catch (Exception e) {
            email.setStatusEmail(StatusEmail.ERROR);
            return "Erro ao cadastrar o pedido e email: " + e.getMessage();
        } finally {
            emailRepository.save(email);
        }
    }

    // Método para trazer uma lista de pedidos
    public List<PedidoDTO> listaPedido() {
        try {
            List<Pedido> pedidos = pedidoRepository.findAll();
            return pedidos.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Erro ao criar lista: " + e.getMessage());
            return null;
        }
    }

    // Método para buscar um pedido
    public PedidoDTO buscaPedido(int id) {
        try {
            Pedido pedidoBusca = pedidoRepository.findById(id).get();
            PedidoDTO dto = new PedidoDTO(pedidoBusca);
            return dto;
        } catch (Exception e) {
            System.out.println("Erro ao buscar pedido: " + e.getMessage());
            return null;
        }
    }

    // Método para excluir pedido
    public String excluirPedido(int id) {
        try {
            pedidoRepository.deleteById(id);
            return "Pedido excluido com sucesso!!";
        } catch (Exception e) {
            return "Erro ao excluir usuario: " + e.getMessage();
        }

    }

}
