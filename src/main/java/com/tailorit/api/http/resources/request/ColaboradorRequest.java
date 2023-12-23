package com.tailorit.api.http.resources.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorRequest {

    @Schema(description = "O nome do colaborador")
    @NotBlank(message = "O nome não pode ser vazio ou nulo")
    @Size(min = 3, message = "O nome não pode ser menor que 3 caracteres")
    private String nome;

    @NotBlank(message = "A senha não pode ser vazia ou nula")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    private String senha;

}
