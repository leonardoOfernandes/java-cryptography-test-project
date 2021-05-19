package com.example.cryptographyExample.resources.crypto;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class CryptoHelper {

    private char[] key;
    private int[] sbox;
    private static final int SBOX_LENGTH = 256;
    private static final int TAM_MIN_KEY = 5;


    private static String IV = "AAAAAAAAAAAAAAAA";


    public CryptoBody encrypt(Map<String, Object> content, String key) {
        Map<String, Object> encryptMap = new HashMap<>();
        content.keySet().forEach(keyMap -> encryptMap.put(keyMap,encrypt(String.valueOf(content.get(keyMap)), key)));
        return CryptoBody.builder()
                .body(encryptMap)
                .key(key)
                .build();

    }

    public CryptoBody decrypt(Map<String, Object> content, String key) {
        Map<String, Object> decryptMap = new HashMap<>();
        content.keySet().forEach(keyMap -> decryptMap.put(keyMap,decrypt(String.valueOf(content.get(keyMap)), key)));
        return CryptoBody.builder()
                .body(decryptMap)
                .key(key)
                .build();
    }

    public String encrypt(String content, String key) {
        if (content.length() != key.length())
            log.info("O tamanho da content e da key devem ser iguais.");
        int[] im = charArrayToInt(content.toCharArray());
        int[] ik = charArrayToInt(key.toCharArray());
        int[] data = new int[content.length()];

        for (int i = 0; i < content.length(); i++) {
            data[i] = im[i] + ik[i];
        }

        return new String(intArrayToChar(data));
    }

    public String decrypt(String content, String key) {
        if (content.length() != key.length())
            log.info("O tamanho da content e da key devem ser iguais.");
        int[] im = charArrayToInt(content.toCharArray());
        int[] ik = charArrayToInt(key.toCharArray());
        int[] data = new int[content.length()];

        for (int i = 0; i < content.length(); i++) {
            data[i] = im[i] - ik[i];
        }

        return new String(intArrayToChar(data));
    }

    public String genKey(int tamanho) {
        Random randomico = new Random();
        char[] key = new char[tamanho];
        for (int i = 0; i < tamanho; i++) {
            key[i] = (char) randomico.nextInt(132);
            if ((int) key[i] < 97) key[i] = (char) (key[i] + 72);
            if ((int) key[i] > 122) key[i] = (char) (key[i] - 72);
        }

        return new String(key);
    }

    private int chartoInt(char c) {
        return (int) c;
    }

    private char intToChar(int i) {
        return (char) i;
    }

    private int[] charArrayToInt(char[] cc) {
        int[] ii = new int[cc.length];
        for (int i = 0; i < cc.length; i++) {
            ii[i] = chartoInt(cc[i]);
        }
        return ii;
    }

    private char[] intArrayToChar(int[] ii) {
        char[] cc = new char[ii.length];
        for (int i = 0; i < ii.length; i++) {
            cc[i] = intToChar(ii[i]);
        }
        return cc;
    }
}