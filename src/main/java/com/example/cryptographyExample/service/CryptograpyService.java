package com.example.cryptographyExample.service;

import com.example.cryptographyExample.domain.Pessoa;
import com.example.cryptographyExample.repository.PessoaRepository;
import com.example.cryptographyExample.resources.crypto.CryptoBody;
import com.example.cryptographyExample.resources.crypto.CryptoHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Slf4j
@Service
public class CryptograpyService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CryptoHelper crypto;

    public CryptoBody encryptBody(Map<String,Object> pessoaBody, String key){

        repository.save(mapper.convertValue(pessoaBody, Pessoa.class));
        log.info(pessoaBody.toString());
        CryptoBody encryptMap = crypto.encrypt(pessoaBody, key);
        log.info(encryptMap.getBody().toString());

        return encryptMap;
    }


    public CryptoBody decryptBody(Map<String,Object> pessoaBody, String key){

        //repository.save(mapper.convertValue(pessoaBody, Pessoa.class));
        log.info(pessoaBody.toString());
        CryptoBody decryptMap = crypto.decrypt(pessoaBody, key);
        log.info(decryptMap.getBody().toString());

        return decryptMap;
    }





}
