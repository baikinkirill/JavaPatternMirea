package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean(name = "voldemort")
    public Voldemort getVoldemort() {
        return new Voldemort();
    }

    @Bean(name = "ronweesly")
    public RonWeesly getRon() {
        return new RonWeesly();
    }

    @Bean(name = "harry")
    public HarryPotter getHarry() {
        return new HarryPotter();
    }
}
