package com.zst.week5.q2.conditional.autowired;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class NonWebContextApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(NonWebContextApp.class).web(WebApplicationType.NONE).run(args);
    }
}
