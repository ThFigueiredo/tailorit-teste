package com.tailorit.domain.repository;

import com.tailorit.domain.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    @Query("SELECT c FROM Colaborador c ORDER BY c.cargo.numeroCargo, c.superior.id, c.id")
    List<Colaborador> findAllOrderedHierarchical();
}