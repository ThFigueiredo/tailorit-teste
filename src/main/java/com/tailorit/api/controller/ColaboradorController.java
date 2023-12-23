package com.tailorit.api.controller;

import com.tailorit.api.http.resources.request.ColaboradorRequest;
import com.tailorit.api.http.resources.response.ColaboradorResponse;
import com.tailorit.core.mapper.ColaboradorMapper;
import com.tailorit.domain.model.Colaborador;
import com.tailorit.domain.service.ColaboradorService;
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
@RequestMapping("/api/colaborador")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags = {"Colaborador"}, description = "Criar Colaborador")
    public ResponseEntity<ColaboradorResponse> createColaborador(@RequestBody ColaboradorRequest colaboradorRequest) {
        Colaborador colaborador = ColaboradorMapper.INSTANCE.colaboradorRequestToColaborador(colaboradorRequest);
        Colaborador colaboradorSaved = colaboradorService.create(colaborador);
        ColaboradorResponse colaboradorResponse = ColaboradorMapper.INSTANCE.colaboradorToColaboradorResponse(colaboradorSaved);
        return ResponseEntity.ok(colaboradorResponse);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = {"Colaborador"}, description = "Buscar por id Colaborador")
    public ResponseEntity<ColaboradorResponse> findById(@PathVariable(name = "id") Long id) {
        Colaborador colaborador = colaboradorService.findById(id);
        ColaboradorResponse colaboradorResponse = ColaboradorMapper.INSTANCE.colaboradorToColaboradorResponse(colaborador);
        return ResponseEntity.ok(colaboradorResponse);
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = {"Colaborador"}, description = "Atualizar Colaborador")
    public ResponseEntity<ColaboradorResponse> update(@PathVariable(name = "id") Long id, @RequestBody ColaboradorRequest colaboradorRequest) {
        Colaborador colaborador = ColaboradorMapper.INSTANCE.colaboradorRequestToColaborador(colaboradorRequest);
        Colaborador update = colaboradorService.update(id, colaborador);
        ColaboradorResponse colaboradorResponse = ColaboradorMapper.INSTANCE.colaboradorToColaboradorResponse(update);
        return ResponseEntity.ok(colaboradorResponse);
    }

    @GetMapping(value = "findAllPageable")
    @ResponseBody
    @Operation(tags = {"Colaborador"}, description = "Buscar todos os colaboradors com paginação", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operação realizada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ColaboradorResponse.class))))
    })
    public ResponseEntity<List<ColaboradorResponse>> findAllPageable(@QuerydslPredicate(root = Colaborador.class)
                                                               @ParameterObject Pageable pageable) {
        List<Colaborador> colaboradors = colaboradorService.findAllPageable(pageable);
        List<ColaboradorResponse> colaboradorResponses = colaboradors.stream()
                .map(ColaboradorMapper.INSTANCE::colaboradorToColaboradorResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(colaboradorResponses);
    }

    @GetMapping(value = "findAllHierarquia")
    @ResponseBody
    @Operation(tags = {"Colaborador"}, description = "Buscar todos os colaboradores de forma hierárquica", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operação realizada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ColaboradorResponse.class))))
    })
    public ResponseEntity<List<ColaboradorResponse>> findAllHierarquia() {
        List<Colaborador> colaboradoresHierarquicos = colaboradorService.findAllHierarquia();

        List<ColaboradorResponse> colaboradorResponses = colaboradoresHierarquicos.stream()
                .map(ColaboradorMapper.INSTANCE::colaboradorToColaboradorResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(colaboradorResponses);
    }



}

