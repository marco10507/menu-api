package com.example.menuservice.service;

import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.dto.MenuItemCreationDTO;

public interface MenuItemsService {
    MenuItem save(Long menuId, Long sectionId, MenuItemCreationDTO menuItemCreationDTO);
}
