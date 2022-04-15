package com.services;

import com.models.Worker;
import com.repositories.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class WorkerService {
    private WorkerRepository workerRepository;

    @Autowired
    WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Transactional
    public Worker read(long id) {
        log.info("Read worker by id = {}", id);
        return workerRepository.findById(id).get();
    }

    @Transactional
    public List<Worker> readAll() {
        log.info("Read all workers");
        return workerRepository.findAll();
    }

    @Transactional
    public void saveWorker(Worker worker) {
        log.info("Save worker {}", worker);
        workerRepository.save(worker);
    }

    @Transactional
    public boolean update(Worker worker, long id) {
        log.info("Update worker {} by id = {}", worker, id);
        worker.setId(id);
        workerRepository.save(worker);
        return true;
    }

    @Transactional
    public boolean deleteWorker(long id) {
        log.info("Delete worker by id = {}", id);
        workerRepository.deleteById(id);
        return true;
    }


}

