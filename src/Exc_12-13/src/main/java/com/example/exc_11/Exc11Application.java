package com.example.exc_11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
public class Exc11Application {

    public static void main(String[] args) {
        SpringApplication.run(Exc11Application.class, args);
    }


//    mvn clean compile assembly:single
//    java -jar .\Exc_11-0.0.1-SNAPSHOT-jar-with-dependencies.jar input=input.txt output=out.txt
}

