package com.example.menuservice.service;

import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.entity.dto.MenuSectionCreationDTO;

public interface MenuSectionsService {
    MenuSection save(Long menuId, MenuSectionCreationDTO menuSectionCreationDTO);
}