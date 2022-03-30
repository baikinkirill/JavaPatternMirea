package com.example.exc_11;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
public class SimpleRestController {
    ArrayList<Worker> workers = new ArrayList<Worker>();
    ArrayList<Manufacturer> manufactures = new ArrayList<Manufacturer>();

    @GetMapping("/home")
    public String example() {
        return new HtmlPage().get("Баикин Кирилл", "ИКБО-10-20", 2);
    }

    @PostMapping("/manufacture")
    public ResponseEntity<Map<String, String>> createManufacturer(String name, String address) {
        Map<String, String> response = new HashMap<String, String>();
        if (name == null || address == null) {
            response.put("error", "Some env's are missing.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        manufactures.add(new Manufacturer(name, address));
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

        workers.add(new Worker(firstName, lastName, middleName));
        response.put("response", "ok");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/worker")
    public ResponseEntity<Map> getWorkers() {
        Map<String, ArrayList> response = new HashMap<String, ArrayList>();
        response.put("response", workers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/manufacture")
    public ResponseEntity<Map> getManufacture() {
        Map<String, ArrayList> response = new HashMap<String, ArrayList>();
        response.put("response", manufactures);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/worker")
    public ResponseEntity<Map> deleteWorker(String lastName) {
        Map<String, String> response = new HashMap<String, String>();

        if (lastName == null) {
            response.put("error", "lastName is missing");
            return new ResponseEntity<Map>(response, HttpStatus.BAD_REQUEST);
        }

        int index = -1;
        for(int i =0;i<workers.size();i++){
            if(workers.get(i).getLastName().equals(lastName)){
                index=i;
                break;
            }
        }
        if(index>-1){
            workers.remove(index);
            response.put("response","ok");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("error", "worker not found");
            return new ResponseEntity<Map>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/manufacture")
    public ResponseEntity<Map> deleteManufacture(String name) {
        Map<String, String> response = new HashMap<String, String>();

        if (name == null) {
            response.put("error", "name is missing");
            return new ResponseEntity<Map>(response, HttpStatus.BAD_REQUEST);
        }

        int index = -1;
        for(int i =0;i<manufactures.size();i++){
            if(manufactures.get(i).getName().equals(name)){
                index=i;
                break;
            }
        }
        if(index>-1){
            manufactures.remove(index);
            response.put("response","ok");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("error", "manufacture not found");
            return new ResponseEntity<Map>(response, HttpStatus.BAD_REQUEST);
        }
    }
}


