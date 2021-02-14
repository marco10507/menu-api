package com.example.menuservice.mapper;

import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.dto.MenuItemCreationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MenuItemCreationMapper {
    public abstract MenuItem fromDTO(MenuItemCreationDTO menuItemCreationDTO);
}
