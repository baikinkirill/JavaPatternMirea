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
import org.springframework.web.bind.annotation.*;

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
        if (factory == null) factory = new Configuration().configure().buildSessionFactory();
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
    public ResponseEntity<Map<String, String>> createWorker(String firstName, String lastName, String middleName, Long manufactureId) {
        Map<String, String> response = new HashMap<String, String>();
        if (firstName == null || lastName == null || middleName == null || manufactureId == null) {
            response.put("error", "Some env's are missing.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());

        Worker m = new Worker(firstName, lastName, middleName);
        m.setId(workerDAO.getNextId());
        m.setManufacture((long) ((ManufactureDAO) manufactureDAO).findById(manufactureId).getId());

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
    public ResponseEntity<Map> deleteWorker(Long id) {
        Map<String, String> response = new HashMap<String, String>();

        if (id == null) {
            response.put("error", "id is missing");
            return new ResponseEntity<Map>(response, HttpStatus.BAD_REQUEST);
        }

        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());

        workerDAO.delete(id);
        response.put("response", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/manufacture")
    public ResponseEntity<Map> deleteManufacture(Long id) {
        Map<String, String> response = new HashMap<String, String>();

        if (id == null) {
            response.put("error", "id is missing");
            return new ResponseEntity<Map>(response, HttpStatus.BAD_REQUEST);
        }

        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());
        manufactureDAO.delete(id);
        response.put("response", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/worker/{workerId}/manufacture")
    public ResponseEntity<Map> getManufacture(@PathVariable("workerId") Long workerId) {
        Map<String, Manufacture> response = new HashMap<String, Manufacture>();
        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());
        response.put("result", ((ManufactureDAO) manufactureDAO).findById(((WorkerDAO) workerDAO).getManufacture(workerId)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/worker/filter/{column}")
    public ResponseEntity<Map> filterWorker(@PathVariable("column") String column) {
        Map<String, Object[]> response = new HashMap<>();
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());
        response.put("response", workerDAO.filter(column).toArray());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/worker/filter/{column}/{pattern}")
    public ResponseEntity<Map> filterWorkerWithPattern(@PathVariable("column") String column,@PathVariable("pattern") String pattern) {
        Map<String, Object[]> response = new HashMap<>();
        DAO<Worker, String> workerDAO = new WorkerDAO(getFactory());
        response.put("response", workerDAO.filter(column, pattern).toArray());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/manufacture/filter/{column}")
    public ResponseEntity<Map> filterManufacture(@PathVariable("column") String column) {
        Map<String, Object[]> response = new HashMap<>();
        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        response.put("response", manufactureDAO.filter(column).toArray());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/manufacture/filter/{column}/{pattern}")
    public ResponseEntity<Map> filterManufactureWithPattern(@PathVariable("column") String column,@PathVariable("pattern") String pattern) {
        Map<String, Object[]> response = new HashMap<>();
        DAO<Manufacture, String> manufactureDAO = new ManufactureDAO(getFactory());
        System.out.println(column);
        response.put("response", manufactureDAO.filter(column, pattern).toArray());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


