package com.example.menuservice.service;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.entity.MenuSectionItem;
import com.example.menuservice.repository.MenuItemsRepository;
import com.example.menuservice.repository.MenuSectionItemsRepository;
import com.example.menuservice.repository.MenuSectionsRepository;
import com.example.menuservice.repository.MenusRepository;
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

    public List<Menu> findAll() {
        return menusRepository.findAll();
    }

    public Optional<Menu> findById(Long id) {
        return menusRepository.findById(id);
    }

    public Menu createMenu(Menu menu) {
        return menusRepository.save(menu);
    }

    public MenuSection createSection(Long menuId, MenuSection menuSection) {
        Optional<Menu> menu = menusRepository.findById(menuId);

        if (menu.isEmpty()) {
            throw new RuntimeException(String.format("Menu id %s does not exist.", menuId));
        }

        menuSection.setMenu(menu.get());

        menuSectionsRepository.save(menuSection);

        return menuSection;
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
