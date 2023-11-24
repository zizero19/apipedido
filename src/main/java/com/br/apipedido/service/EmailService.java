package com.br.apipedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apipedido.repository.EmailRepository;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;
}
