package com.tailorit.api.controller;

import com.tailorit.api.http.resources.request.CargoRequest;
import com.tailorit.api.http.resources.response.CargoResponse;
import com.tailorit.core.mapper.CargoMapper;
import com.tailorit.domain.model.Cargo;
import com.tailorit.domain.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/cargo")
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags = {"Cargo"}, description = "Criar Cargo")
    public ResponseEntity<CargoResponse> createCargo(@RequestBody CargoRequest cargoRequest) {
        Cargo cargo = CargoMapper.INSTANCE.cargoRequestToCargo(cargoRequest);
        Cargo cargoSaved = cargoService.save(cargo);
        CargoResponse cargoResponse = CargoMapper.INSTANCE.cargoToCargoResponse(cargoSaved);
        return ResponseEntity.ok(cargoResponse);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = {"Cargo"}, description = "Buscar por id Cargo")
    public ResponseEntity<CargoResponse> findById(@PathVariable(name = "id") Long id) {
        Cargo cargo = cargoService.findById(id);
        CargoResponse cargoResponse = CargoMapper.INSTANCE.cargoToCargoResponse(cargo);
        return ResponseEntity.ok(cargoResponse);
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = {"Cargo"}, description = "Atualizar Cargo")
    public ResponseEntity<CargoResponse> update(@PathVariable(name = "id") Long id, @RequestBody CargoRequest cargoRequest) {
        Cargo cargo = CargoMapper.INSTANCE.cargoRequestToCargo(cargoRequest);
        Cargo update = cargoService.update(id, cargo);
        CargoResponse cargoResponse = CargoMapper.INSTANCE.cargoToCargoResponse(update);
        return ResponseEntity.ok(cargoResponse);
    }

    @GetMapping(value = "findAllPageable")
    @ResponseBody
    @Operation(tags = {"Cargo"}, description = "Buscar todos os cargos com paginação", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operação realizada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CargoResponse.class))))
    })
    public ResponseEntity<List<CargoResponse>> findAllPageable(@QuerydslPredicate(root = Cargo.class)
                                                         @ParameterObject Pageable pageable) {
        List<Cargo> cargos = cargoService.findAllPageable(pageable);
        List<CargoResponse> cargoResponses = cargos.stream()
                .map(CargoMapper.INSTANCE::cargoToCargoResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cargoResponses);
    }


}

