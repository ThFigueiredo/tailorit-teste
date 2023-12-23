package com.tailorit.domain.service;


import com.tailorit.domain.model.Cargo;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Predicate;


public interface CargoService {

    Cargo save(Cargo entity);
    Cargo findById(Long id);
    Cargo update(Long id, Cargo entity);
    List<Cargo> findAllPageable(Pageable pageable);

}
