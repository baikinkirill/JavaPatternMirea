package com.controllers;

import com.models.Worker;
import com.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/workers")
public class WorkerController {
    @Qualifier("workerService")
    WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Worker>> getAllWorkers() {
        final List<Worker> usrs = workerService.readAll();
        System.out.println(usrs);
        return usrs != null ? new ResponseEntity<>(usrs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> addUser(String firstName, String middleName, String lastname) {
        Worker w = new Worker();
        w.setFirstName(firstName);
        w.setLastName(lastname);
        w.setMiddleName(middleName);
        workerService.saveWorker(w);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable(name = "id") long id) {
        final boolean deleted = workerService.deleteWorker(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
