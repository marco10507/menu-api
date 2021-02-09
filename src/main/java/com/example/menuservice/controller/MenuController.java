package com.example.menuservice.controller;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.service.MenusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {
    private final MenusService menusService;

    public MenuController(MenusService menusService) {
        this.menusService = menusService;
    }

    @GetMapping(path = "/menus")
    public MappingJacksonValue getMenus() {
        return menusService.findAll();
    }

    @GetMapping(path = "/menus/{id}")
    public MappingJacksonValue getMenu(@PathVariable Long id) {
        return menusService.findById(id);
    }

    @PostMapping(path = "/menu")
    public ResponseEntity<MappingJacksonValue> postMenu(@RequestBody Menu menu) {
        MappingJacksonValue mappingJacksonValue = menusService.createMenu(menu);

        return ResponseEntity.status(HttpStatus.CREATED).body(mappingJacksonValue);
    }

    @PostMapping(path = "/menu/{menuId}/section")
    public ResponseEntity<MappingJacksonValue> postSection(@RequestBody MenuSection menuSection, @PathVariable Long menuId) {
        MappingJacksonValue mappingJacksonValue = menusService.createSection(menuId, menuSection);

        return ResponseEntity.status(HttpStatus.CREATED).body(mappingJacksonValue);
    }

    @PostMapping(path = "/menu/{menuId}/section/{sectionId}/item")
    public ResponseEntity<MenuItem> postItem(@PathVariable Long sectionId, @PathVariable Long menuId, @RequestBody MenuItem menuItem) {
        menuItem = menusService.createItem(menuId, sectionId, menuItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }
}
