package com.example.menuservice.mapper;

import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.dto.MenuItemCreationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuItemCreationMapper {
    @Mapping(target = "id", ignore = true)
    MenuItem fromDTO(MenuItemCreationDTO menuItemCreationDTO);
}
