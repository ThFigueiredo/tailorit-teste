package com.tailorit.api.http.resources.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CargoRequest {

    @Schema(description = "O nome do cargo")
    @NotBlank(message = "O nome não pode ser vazio ou nulo")
    private String nome;

    @Schema(description = "Número do cargo")
    private int numeroCargo;

}
