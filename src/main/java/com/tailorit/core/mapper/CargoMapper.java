package com.tailorit.core.mapper;

import com.tailorit.api.http.resources.request.CargoRequest;
import com.tailorit.api.http.resources.response.CargoResponse;
import com.tailorit.domain.model.Cargo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CargoMapper {

    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "numeroCargo", target = "numeroCargo")
    CargoResponse cargoToCargoResponse(Cargo cargo);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "numeroCargo", target = "numeroCargo")
    Cargo cargoRequestToCargo(CargoRequest cargoRequest);

    @Mapping(target = "id", ignore = true)
    void mapUpdateEntityToCargo(Cargo entity, @MappingTarget Cargo cargo);
}
