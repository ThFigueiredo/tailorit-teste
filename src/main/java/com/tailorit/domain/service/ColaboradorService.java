package com.tailorit.domain.service;



import com.tailorit.domain.model.Colaborador;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ColaboradorService {

    Colaborador create(Colaborador colaborador);

    Colaborador findById(Long idColaborador);

    List<Colaborador> findAllPageable(Pageable pageable);

     List<Colaborador> findAllHierarquia();

    Colaborador update(Long idColaborador, Colaborador colaborador);

    void delete(Long idColaborador);

}
