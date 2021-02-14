package com.example.menuservice.service;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.dto.MenuCreationDTO;

import java.util.List;

public interface MenusService {
    List<Menu> findAll();

    Menu findById(Long id);

    Menu save(MenuCreationDTO menu);
}