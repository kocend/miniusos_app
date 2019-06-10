package com.miniusos_app.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncode {

    BCryptPasswordEncoder passwordEncoder;

    public PasswordEncode(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    public static void main(String[] args) {
        PasswordEncode pe = new PasswordEncode();
    }
}