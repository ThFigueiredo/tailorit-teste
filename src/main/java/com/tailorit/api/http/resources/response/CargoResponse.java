package com.tailorit.api.http.resources.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CargoResponse {

    @Schema(description = "Id do cargo")
    private Long id;

    @Schema(description = "Nome do cargo")
    private String nome;

    @Schema(description = "Número do cargo")
    private int numeroCargo;

}
