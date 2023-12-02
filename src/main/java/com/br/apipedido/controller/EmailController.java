package com.br.apipedido.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipedido.dto.EmailDTO;
import com.br.apipedido.model.Email;
import com.br.apipedido.service.EmailService;

import jakarta.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    EmailService service;

    @PostMapping("/enviar-email")
    public ResponseEntity<Email> enviarEmail(@RequestBody @Valid EmailDTO emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email); // Conversão de DTO para Model
        service.sendEmail(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }
}
