package com.example.alert.mapper;

import com.example.alert.model.FireStation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FireStationMapper {
    
    FireStationMapper INSTANCE = Mappers.getMapper(FireStationMapper.class);
    
    // Map FireStation entity for responses (exclude internal ID)
    @Mapping(target = "id", ignore = true)
    FireStation toResponseEntity(FireStation fireStation);
}
