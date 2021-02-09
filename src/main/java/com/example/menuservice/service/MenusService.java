package com.example.menuservice.service;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.entity.MenuSectionItem;
import com.example.menuservice.repository.MenuItemsRepository;
import com.example.menuservice.repository.MenuSectionItemsRepository;
import com.example.menuservice.repository.MenuSectionsRepository;
import com.example.menuservice.repository.MenusRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MenusService {
    private final MenusRepository menusRepository;
    private final MenuSectionsRepository menuSectionsRepository;
    private final MenuSectionItemsRepository menuSectionItemsRepository;
    private final MenuItemsRepository menuItemsRepository;

    public MenusService(MenusRepository menusRepository, MenuSectionsRepository menuSectionsRepository, MenuSectionItemsRepository menuSectionItemsRepository, MenuItemsRepository menuItemsRepository) {
        this.menusRepository = menusRepository;
        this.menuSectionsRepository = menuSectionsRepository;
        this.menuSectionItemsRepository = menuSectionItemsRepository;
        this.menuItemsRepository = menuItemsRepository;
    }

    public MappingJacksonValue findAll() {
        List<Menu> menus = menusRepository.findAll();

        SimpleFilterProvider filters = new SimpleFilterProvider();
        filters.setFailOnUnknownId(false);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(menus);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    public MappingJacksonValue findById(Long id) {
        Optional<Menu> menu = menusRepository.findById(id);

        if (menu.isEmpty()) {
            throw new RuntimeException(String.format("Menu id %s does not exist.", id));
        }

        SimpleFilterProvider filters = new SimpleFilterProvider();
        filters.setFailOnUnknownId(false);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(menu.get());
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    public MappingJacksonValue createMenu(Menu menu) {
        Menu newMenu = menusRepository.save(menu);

        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("sections");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("menuFilter", propertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(newMenu);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    public MappingJacksonValue createSection(Long menuId, MenuSection menuSection) {
        Optional<Menu> menu = menusRepository.findById(menuId);

        if (menu.isEmpty()) {
            throw new RuntimeException(String.format("Menu id %s does not exist.", menuId));
        }

        menuSection.setMenu(menu.get());

        menuSectionsRepository.save(menuSection);

        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("items");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("menuSectionFilter", propertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(menuSection);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    public MenuItem createItem(Long menuId, Long sectionId, MenuItem menuItem) {
        menuItemsRepository.save(menuItem);

        Optional<MenuSection> section = menuSectionsRepository.findByIdAndMenuId(sectionId, menuId);

        if (section.isEmpty()) {
            throw new RuntimeException(String.format("Section with id %s and menu id %s does not exist.", sectionId, menuId));
        }

        MenuSectionItem menuSectionItem = new MenuSectionItem(section.get(), menuItem);

        menuSectionItemsRepository.save(menuSectionItem);

        return menuItem;
    }
}
