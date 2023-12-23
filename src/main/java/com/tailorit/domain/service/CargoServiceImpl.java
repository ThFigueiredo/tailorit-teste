package com.tailorit.domain.service;

import com.tailorit.core.mapper.CargoMapper;
import com.tailorit.domain.exception.NotFoundException;
import com.tailorit.domain.model.Cargo;
import com.tailorit.domain.repository.CargoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;

    public CargoServiceImpl(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @Override
    public Cargo save(Cargo entity) {
        return cargoRepository.save(entity);
    }

    @Override
    public Cargo findById(Long id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cargo não encontrado."));
    }

    @Override
    @Transactional
    public Cargo update(Long id, Cargo entity) {
        Cargo cargo = findById(id);
        CargoMapper.INSTANCE.mapUpdateEntityToCargo(entity, cargo);
        return cargoRepository.save(cargo);
    }

    @Override
    public List<Cargo> findAllPageable(Pageable pageable) {
        return cargoRepository.findAll();
    }

}
