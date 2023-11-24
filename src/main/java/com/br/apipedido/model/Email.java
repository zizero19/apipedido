package com.br.apipedido.model;

import java.time.LocalDateTime;

import com.br.apipedido.enums.StatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TBM_EMAIL")
public class Email {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emailId; // Id do email
    private String ownerRef; // Referencia do usuario (Id do usuario que recebera o email)
    private String emailFrom; // Usuario que esta enviando o email
    private String emailTo; // Usuario que vai receber o email
    private String subject; // Assunto do email
    @Column(columnDefinition = "TEXT")
    private String text; // Corpo do email
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
}
