package com.example.menuservice.mapper;

import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.entity.dto.MenuSectionCreationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuSectionCreationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "menu", ignore = true)
    MenuSection fromDTO (MenuSectionCreationDTO menuSectionCreationDTO);
}