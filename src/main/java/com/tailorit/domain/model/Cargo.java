package com.tailorit.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "T_CARGO")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARGO")
    private Long id;

    @Column(name = "NOME_CARGO", nullable = false)
    private String nome;

    @Column(name = "NUMERO_CARGO", nullable = false)
    private int numeroCargo;

}