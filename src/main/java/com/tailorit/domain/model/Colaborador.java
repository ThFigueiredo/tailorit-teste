package com.tailorit.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@Table(name = "T_COLABORADOR")
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COLABORADOR")
    private Long id;

    @Column(name = "NM_COLABORADOR", nullable = false, length = 50)
    private String nome;

    @Column(name = "SH_COLABORADOR", nullable = false)
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "NM_CARGO", nullable = false)
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "ID_SUPERIOR")
    private Colaborador superior;


}
