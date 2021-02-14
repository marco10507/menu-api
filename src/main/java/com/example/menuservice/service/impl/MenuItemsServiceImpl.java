package com.example.menuservice.service.impl;

import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.entity.MenuSectionItem;
import com.example.menuservice.entity.dto.MenuItemCreationDTO;
import com.example.menuservice.exception.ResourceNotFoundException;
import com.example.menuservice.mapper.MenuItemCreationMapper;
import com.example.menuservice.repository.MenuItemsRepository;
import com.example.menuservice.repository.MenuSectionItemsRepository;
import com.example.menuservice.repository.MenuSectionsRepository;
import com.example.menuservice.service.MenuItemsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuItemsServiceImpl implements MenuItemsService {
    private final MenuItemCreationMapper menuItemCreationMapper;
    private final MenuSectionsRepository menuSectionsRepository;
    private final MenuItemsRepository menuItemsRepository;
    private final MenuSectionItemsRepository menuSectionItemsRepository;

    public MenuItemsServiceImpl(
            MenuItemCreationMapper menuItemCreationMapper,
            MenuSectionsRepository menuSectionsRepository,
            MenuItemsRepository menuItemsRepository,
            MenuSectionItemsRepository menuSectionItemsRepository
    ) {
        this.menuItemCreationMapper = menuItemCreationMapper;
        this.menuSectionsRepository = menuSectionsRepository;
        this.menuItemsRepository = menuItemsRepository;
        this.menuSectionItemsRepository = menuSectionItemsRepository;
    }

    public MenuItem save(Long menuId, Long sectionId, MenuItemCreationDTO menuItemCreationDTO) {
        MenuItem menuItem = menuItemCreationMapper.fromDTO(menuItemCreationDTO);

        menuItemsRepository.save(menuItem);

        Optional<MenuSection> section = menuSectionsRepository.findByIdAndMenuId(sectionId, menuId);

        if (section.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Section with id %s and menu id %s does not exist.", sectionId, menuId));
        }

        MenuSectionItem menuSectionItem = MenuSectionItem.builder()
                .menuSection(section.get())
                .menuItem(menuItem)
                .build();

        menuSectionItemsRepository.save(menuSectionItem);

        return menuItem;
    }
}
