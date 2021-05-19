package com.example.cryptographyExample.resources.crypto;

import lombok.Builder;

import java.io.Serializable;
import java.util.Map;

@Builder
public class CryptoBody implements Serializable {

    private String key;
    private Map<String,Object> body;

    public CryptoBody(String key, Map<String, Object> body) {
        this.key = key;
        this.body = body;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
