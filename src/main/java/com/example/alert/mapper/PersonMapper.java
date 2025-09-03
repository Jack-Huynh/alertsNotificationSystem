package com.example.alert.mapper;

import com.example.alert.model.Person;
import com.example.alert.model.DTO.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    
    // Tự động map tất cả fields giống tên
    PersonDTO toDTO(Person person);
    
    // Map ngược lại từ DTO về Entity
    Person toEntity(PersonDTO personDTO);
    
    // Custom mapping cho child alert (chỉ cần một số fields)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "medication", ignore = true)
    @Mapping(target = "allergies", ignore = true)
    PersonDTO toChildDTO(Person person);
    
    // Custom mapping cho person info (cần đầy đủ thông tin)
    @Mapping(target = "familyMembers", ignore = true) // Sẽ set riêng
    PersonDTO toPersonInfoDTO(Person person);
}
