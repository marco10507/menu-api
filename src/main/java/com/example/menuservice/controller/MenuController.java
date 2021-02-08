package com.example.menuservice.controller;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.repository.MenusRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    private final MenusRepository menusRepository;

    public MenuController(MenusRepository menusRepository) {
        this.menusRepository = menusRepository;
    }

    @GetMapping(path = "/menus")
    public List<Menu> getMenus() {
        return menusRepository.findAll();
    }

    @GetMapping(path = "/menus/{id}")
    public Menu getMenu(@PathVariable Long id) {
        return menusRepository.findById(id).orElse(null);
    }
}
