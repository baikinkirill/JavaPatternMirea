package com.example.exc_11;

import com.example.exc_11.dao.DAO;
import com.example.exc_11.dao.ManufactureDAO;
import com.example.exc_11.dao.WorkerDAO;
import com.example.exc_11.models.Manufacture;
import com.example.exc_11.models.Worker;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Exc11Application {
    public static void main(String[] args) {
        SpringApplication.run(Exc11Application.class, args);
    }
}

