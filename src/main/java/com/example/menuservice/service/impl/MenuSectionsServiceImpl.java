package com.example.menuservice.service.impl;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.entity.dto.MenuSectionCreationDTO;
import com.example.menuservice.exception.ResourceNotFoundException;
import com.example.menuservice.mapper.MenuSectionCreationMapper;
import com.example.menuservice.repository.MenuSectionsRepository;
import com.example.menuservice.repository.MenusRepository;
import com.example.menuservice.service.MenuSectionsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuSectionsServiceImpl implements MenuSectionsService {
    private final MenuSectionCreationMapper menuSectionCreationMapper;
    private final MenusRepository menusRepository;
    private final MenuSectionsRepository menuSectionsRepository;

    public MenuSectionsServiceImpl(
            MenuSectionCreationMapper menuSectionCreationMapper,
            MenusRepository menusRepository,
            MenuSectionsRepository menuSectionsRepository
    ) {
        this.menuSectionCreationMapper = menuSectionCreationMapper;
        this.menusRepository = menusRepository;
        this.menuSectionsRepository = menuSectionsRepository;
    }

    @Override
    public MenuSection save(Long menuId, MenuSectionCreationDTO menuSectionCreationDTO) {
        Optional<Menu> menu = menusRepository.findById(menuId);

        if (menu.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Menu id %s does not exist.", menuId));
        }

        MenuSection menuSection = menuSectionCreationMapper.fromDTO(menuSectionCreationDTO);
        menuSection.setMenu(menu.get());

        menuSectionsRepository.save(menuSection);

        return menuSection;
    }
}
