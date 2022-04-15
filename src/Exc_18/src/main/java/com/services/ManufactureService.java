package com.services;

import com.models.Manufacture;
import com.repositories.ManufactureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class ManufactureService {
    private ManufactureRepository manufactureRepository;

    @Autowired
    ManufactureService(ManufactureRepository manufactureRepository) {
        this.manufactureRepository = manufactureRepository;
    }

    @Transactional
    public Manufacture read(long id) {
        log.info("Read manufacture by id = {}", id);
        return manufactureRepository.findById(id).get();
    }

    @Transactional
    public List<Manufacture> readAll() {
        log.info("Read all manufactures");
        return manufactureRepository.findAll();
    }

    @Transactional
    public void saveManufacture(Manufacture manufacture) {
        log.info("Save manufacture {}", manufacture);
        manufactureRepository.save(manufacture);
    }

    @Transactional
    public boolean update(Manufacture manufacture, long id) {
        log.info("Update manufacture {} by id = {}", manufacture, id);
        manufacture.setId(id);
        manufactureRepository.save(manufacture);
        return true;
    }

    @Transactional
    public boolean deleteManufacture(long id) {
        log.info("Delete manufacture by id = {}", id);
        manufactureRepository.deleteById(id);
        return true;
    }


}
