package com.example.menuservice.controller;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.entity.dto.MenuCreationDTO;
import com.example.menuservice.entity.dto.MenuItemCreationDTO;
import com.example.menuservice.entity.dto.MenuSectionCreationDTO;
import com.example.menuservice.service.MenuItemsService;
import com.example.menuservice.service.MenuSectionsService;
import com.example.menuservice.service.MenusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MenuController {
    private final MenusService menusService;
    private final MenuSectionsService menuSectionsService;
    private final MenuItemsService menuItemsService;

    public MenuController(
            MenusService menusService,
            MenuSectionsService menuSectionsService,
            MenuItemsService menuItemsService
    ) {
        this.menusService = menusService;
        this.menuSectionsService = menuSectionsService;
        this.menuItemsService = menuItemsService;
    }

    @GetMapping(path = "/menus")
    public ResponseEntity<List<Menu>> getMenus() {
        List<Menu> menus = menusService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(menus);
    }

    @GetMapping(path = "/menus/{id}")
    public ResponseEntity<Menu> getMenu(@PathVariable Long id) {
        Menu menu = menusService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(menu);
    }

    @PostMapping(path = "/menu")
    public ResponseEntity<Menu> postMenu(@Valid @RequestBody MenuCreationDTO menuCreationDTO) {
        Menu menu = menusService.save(menuCreationDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(menu);
    }

    @PostMapping(path = "/menu/{menuId}/section")
    public ResponseEntity<MenuSection> postSection(
            @Valid @RequestBody MenuSectionCreationDTO menuSectionCreationDTO,
            @PathVariable Long menuId
    ) {
        MenuSection menuSection = menuSectionsService.save(menuId, menuSectionCreationDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(menuSection);
    }

    @PostMapping(path = "/menu/{menuId}/section/{sectionId}/item")
    public ResponseEntity<MenuItem> postItem(@PathVariable Long sectionId, @PathVariable Long menuId, @Valid @RequestBody MenuItemCreationDTO menuItemCreationDTO) {
        MenuItem menuItem = menuItemsService.save(menuId, sectionId, menuItemCreationDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }
}
