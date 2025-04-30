package com.Hotel;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class A {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("testing");
        System.out.println(encodedPassword);
    }
}
