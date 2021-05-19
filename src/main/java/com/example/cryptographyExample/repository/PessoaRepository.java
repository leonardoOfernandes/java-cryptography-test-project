package com.example.cryptographyExample.repository;

import com.example.cryptographyExample.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,String> {
}
