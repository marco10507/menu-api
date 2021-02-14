package com.example.menuservice.mapper;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.dto.MenuCreationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuCreationMapper {
    MenuCreationDTO toDTO(Menu menu);

    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "id", ignore = true)
    Menu fromDTO(MenuCreationDTO menuCreationDTO);
}