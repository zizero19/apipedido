package com.br.apipedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apipedido.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
