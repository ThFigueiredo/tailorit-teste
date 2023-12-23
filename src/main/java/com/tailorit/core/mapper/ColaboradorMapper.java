package com.tailorit.core.mapper;


import com.tailorit.api.http.resources.request.ColaboradorRequest;
import com.tailorit.api.http.resources.response.ColaboradorResponse;
import com.tailorit.domain.model.Colaborador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColaboradorMapper {

    ColaboradorMapper INSTANCE = Mappers.getMapper(ColaboradorMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    ColaboradorResponse colaboradorToColaboradorResponse(Colaborador colaborador);

    @Mapping(source = "nome", target = "nome")
    Colaborador colaboradorRequestToColaborador(ColaboradorRequest colaboradorRequest);

    @Mapping(target = "id", ignore = true)
    void mapUpdateEntityToColaborador(Colaborador entity, @MappingTarget Colaborador colaborador);

}