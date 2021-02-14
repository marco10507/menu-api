package com.example.menuservice.mapper;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.dto.MenuCreationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class MenuCreationMapper {
    public abstract MenuCreationDTO toDTO(Menu menu);

    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Menu fromDTO(MenuCreationDTO menuCreationDTO);
}
