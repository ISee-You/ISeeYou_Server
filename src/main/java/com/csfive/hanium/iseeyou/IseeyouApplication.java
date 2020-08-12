package com.csfive.hanium.iseeyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class IseeyouApplication {

    public static void main(String[] args) {
        SpringApplication.run(IseeyouApplication.class, args);
    }

}
