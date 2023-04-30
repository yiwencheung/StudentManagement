package com.ysir.custom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class A {

    public static void main(String[] args) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode("admin123");
            System.out.println(password);
    }
}
