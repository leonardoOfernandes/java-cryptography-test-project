package com.example.cryptographyExample.controller;

import com.example.cryptographyExample.Utils.Constants;
import com.example.cryptographyExample.resources.crypto.CryptoBody;
import com.example.cryptographyExample.resources.crypto.CryptoHelper;
import com.example.cryptographyExample.service.CryptograpyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/cryptography")
public class CryptographyController {

    @Autowired
    private CryptograpyService cryptoService;

    @Autowired
    private CryptoHelper crypto;

    @RequestMapping(value = "/encrypt", method = RequestMethod.GET)
    public ResponseEntity encrypt(@RequestBody Map<String, Object> body){

        String key = crypto.genKey(body.toString().length());
        CryptoBody encrypt = cryptoService.encryptBody(body,key);

        return ResponseEntity.ok()
                .header(Constants.KEY, key)
                .body(encrypt.getBody());
    }


    @RequestMapping(value = "/decrypt", method = RequestMethod.GET)
    public ResponseEntity decrypt(@RequestBody Map<String, Object> body,
                                  @RequestHeader(name = "key") String key){

        CryptoBody decrypt = cryptoService.decryptBody(body,key);

        return ResponseEntity.ok()
                .header(Constants.KEY, decrypt.getKey())
                .body(decrypt.getBody());
    }
}
