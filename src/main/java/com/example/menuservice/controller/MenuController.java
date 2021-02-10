package com.example.menuservice.controller;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.exception.ResourceNotFoundException;
import com.example.menuservice.service.MenusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MenuController {
    private final MenusService menusService;

    public MenuController(MenusService menusService) {
        this.menusService = menusService;
    }

    @GetMapping(path = "/menus")
    public List<Menu> getMenus() {
        return menusService.findAll();
    }

    @GetMapping(path = "/menus/{id}")
    public Menu getMenu(@PathVariable Long id) {
        Optional<Menu> menu = menusService.findById(id);

        if (menu.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Menu id %s does not exist.", id));
        }

        return menu.get();
    }

    @PostMapping(path = "/menu")
    public ResponseEntity<Menu> postMenu(@RequestBody Menu menu) {
        menu.setSections(new ArrayList<>());

        menu = menusService.createMenu(menu);

        return ResponseEntity.status(HttpStatus.CREATED).body(menu);
    }

    @PostMapping(path = "/menu/{menuId}/section")
    public ResponseEntity<MenuSection> postSection(@RequestBody MenuSection menuSection, @PathVariable Long menuId) {
        menuSection.setItems(new ArrayList<>());

        menuSection = menusService.createSection(menuId, menuSection);

        return ResponseEntity.status(HttpStatus.CREATED).body(menuSection);
    }

    @PostMapping(path = "/menu/{menuId}/section/{sectionId}/item")
    public ResponseEntity<MenuItem> postItem(@PathVariable Long sectionId, @PathVariable Long menuId, @RequestBody MenuItem menuItem) {
        menuItem = menusService.createItem(menuId, sectionId, menuItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }
}
