package com.example.exc_11;

import com.example.exc_11.dao.DAO;
import com.example.exc_11.dao.ManufactureDAO;
import com.example.exc_11.dao.WorkerDAO;
import com.example.exc_11.models.Manufacture;
import com.example.exc_11.models.Worker;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class SimpleRestController {
    ArrayList<Worker> workers = new ArrayList<Worker>();
    ArrayList<Manufacture> manufactures = new ArrayList<Manufacture>();
    static SessionFactory factory = null;

    public SessionFactory getFactory() {
        if (factory == null)
            factory = new Configuration().configure().buildSessionFactory();
        return factory;
    }

    @PostMapping("/manufacture")
    public ResponseEntity<Map<String, String>> createManufacture(String name, String address) {
        Map<String, String> response = new HashMap<String, String>();
        if (name == null || address == null) {
            response.put("error", "Some env's are missing.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());

        Manufacture m = new Manufacture(name, address);
        m.setId(manufactureDAO.getNextId());

        manufactureDAO.create(m);
        response.put("response", "ok");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/worker")
    public ResponseEntity<Map<String, String>> createWorker(String firstName, String lastName, String middleName) {
        Map<String, String> response = new HashMap<String, String>();
        if (firstName == null || lastName == null || middleName == null) {
            response.put("error", "Some env's are missing.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());

        Worker m = new Worker(firstName, lastName, middleName);
        m.setId(workerDAO.getNextId());

        workerDAO.create(m);
        response.put("response", "ok");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/worker")
    public ResponseEntity<Map> getWorkers() {
        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());

        Map<String, List> response = new HashMap<String, List>();
        response.put("response", workerDAO.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/manufacture")
    public ResponseEntity<Map> getManufacture() {
        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());

        Map<String, List> response = new HashMap<String, List>();
        response.put("response", manufactureDAO.findAll());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/worker")
    public ResponseEntity<Map> deleteWorker(String lastName, String firstName, String middleName) {
        Map<String, String> response = new HashMap<String, String>();

        if (lastName == null || firstName == null || middleName == null) {
            response.put("error", "lastName is missing");
            return new ResponseEntity<Map>(response, HttpStatus.BAD_REQUEST);
        }

        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());

        workerDAO.delete(new Worker(firstName, lastName, middleName));
        response.put("response", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/manufacture")
    public ResponseEntity<Map> deleteManufacture(String name, String address) {
        Map<String, String> response = new HashMap<String, String>();

        if (name == null || address == null) {
            response.put("error", "name is missing");
            return new ResponseEntity<Map>(response, HttpStatus.BAD_REQUEST);
        }

        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());
        manufactureDAO.delete(new Manufacture(name, address));
        response.put("response", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


