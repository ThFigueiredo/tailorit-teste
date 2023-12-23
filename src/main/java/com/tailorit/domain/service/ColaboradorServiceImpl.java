package com.tailorit.domain.service;

import com.tailorit.core.config.PasswordValidationService;
import com.tailorit.domain.exception.InvalidPasswordFoundException;
import com.tailorit.domain.exception.NotFoundException;
import com.tailorit.core.config.CustomPasswordEncoder;
import com.tailorit.domain.model.Colaborador;
import com.tailorit.domain.repository.CargoRepository;
import com.tailorit.domain.repository.ColaboradorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final CargoRepository cargoRepository;
    private final PasswordValidationService passwordValidationService;


    public ColaboradorServiceImpl(ColaboradorRepository colaboradorRepository, CargoRepository cargoRepository, PasswordValidationService passwordValidationService) {
        this.colaboradorRepository = colaboradorRepository;
        this.cargoRepository = cargoRepository;
        this.passwordValidationService = passwordValidationService;
    }

    @Override
    public Colaborador create(Colaborador colaborador) {
        if (!passwordValidationService.isPasswordValid(colaborador.getSenha())) {
            throw new InvalidPasswordFoundException("A senha não atende aos critérios de validação.");
        }

        colaborador.setSenha(customPasswordEncoder.encode(colaborador.getSenha()));

        if (colaborador.getCargo() != null && colaborador.getCargo().getId() != null) {
            colaborador.setCargo(cargoRepository.findById(colaborador.getCargo().getId())
                    .orElseThrow(() -> new NotFoundException("Cargo não encontrado.")));
        }

        return colaboradorRepository.save(colaborador);
    }


    @Override
    public Colaborador findById(Long idColaborador) {
        return colaboradorRepository.findById(idColaborador)
                .orElseThrow(() -> new NotFoundException("Colaborador não encontrado."));
    }

    @Override
    public List<Colaborador> findAllPageable(Pageable pageable) {
        return colaboradorRepository.findAll();
    }

    @Override
    public List<Colaborador> findAllHierarquia() {
        return colaboradorRepository.findAllOrderedHierarchical();

    }
    @Override
    public Colaborador update(Long idColaborador, Colaborador colaborador) {
        Colaborador colaboradorToUpdate = findById(idColaborador);
        BeanUtils.copyProperties(colaborador, colaboradorToUpdate);
        return colaboradorRepository.save(colaboradorToUpdate);
    }

    @Override
    public void delete(Long idColaborador) {
        Colaborador colaborador = findById(idColaborador);
        colaboradorRepository.delete(colaborador);
    }

}
