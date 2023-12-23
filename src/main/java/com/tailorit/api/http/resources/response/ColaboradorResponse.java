package com.tailorit.api.http.resources.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorResponse {

    @Schema(description = "Id do colaborador")
    private Long id;

    @Schema(description = "Nome do colaborador")
    @Size(min = 3, message = "O nome não pode ser menor que 3 caracteres")
    private String nome;

}
