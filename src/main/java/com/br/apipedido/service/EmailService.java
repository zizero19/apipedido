package com.br.apipedido.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.br.apipedido.enums.StatusEmail;
import com.br.apipedido.model.Email;
import com.br.apipedido.model.Pedido;
import com.br.apipedido.model.Produto;
import com.br.apipedido.repository.EmailRepository;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public Email buildEmail(Pedido pedido) {
        Email email = new Email();
        StringBuilder textoEmail = new StringBuilder();
        textoEmail.append("PEDIDO N° " + pedido.getId() + " para " + pedido.getUsuario().getNome());
        textoEmail.append("\n DADOS DO CLIENTE" +
                "\n Nome: " + pedido.getUsuario().getNome() +
                "\n Email: " + pedido.getUsuario().getEmail() +
                "\n CPF: " + pedido.getUsuario().getCpf() + "\n ");

        textoEmail.append("\n ENDEREÇO DO CLIENTE" +
                "\n Rua: " + pedido.getEndereco().getRua() +
                "\n Numero: " + pedido.getEndereco().getNumero() +
                "\n Bairro: " + pedido.getEndereco().getBairro() +
                "\n Cidade: " + pedido.getEndereco().getCidade() + "\n ");

        textoEmail.append("\n LISTA DE PRODUTOS: \n");
        for (Produto produto : pedido.getProdutos()) {
            textoEmail.append(produto.getNome() + " | " + produto.getValor());
            textoEmail.append("\n");
        }
        textoEmail.append("\n Forma de Pagamento: " + pedido.getFormaPagamento().getTipo());
        textoEmail.append("\n Quantidade: " + pedido.getQtd());
        textoEmail.append("\n Valor Total: " + pedido.getValorTotal());

        email.setEmailFrom("cicero.ferro.78@gmail.com");
        email.setEmailTo(pedido.getUsuario().getEmail());
        email.setSubject("PEDIDO N° " + pedido.getId());
        email.setText(textoEmail.toString());

        return email;
    }

    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(email);
        }
    }
}
