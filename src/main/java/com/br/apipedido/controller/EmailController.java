package com.br.apipedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipedido.service.EmailService;

@RestController
public class EmailController {

    @Autowired
    EmailService service;
}
