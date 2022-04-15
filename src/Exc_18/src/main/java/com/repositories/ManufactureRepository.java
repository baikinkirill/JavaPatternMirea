package com.repositories;

import com.models.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
    List<Manufacture> findManufactureById(long id);
}
