package com.example.menuservice.service.impl;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.dto.MenuCreationDTO;
import com.example.menuservice.exception.ResourceNotFoundException;
import com.example.menuservice.mapper.MenuCreationMapper;
import com.example.menuservice.repository.MenusRepository;
import com.example.menuservice.service.MenusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenusServiceImpl implements MenusService {
    private final MenuCreationMapper menuCreationMapper;
    private final MenusRepository menusRepository;

    public MenusServiceImpl(MenuCreationMapper menuCreationMapper, MenusRepository menusRepository) {
        this.menuCreationMapper = menuCreationMapper;
        this.menusRepository = menusRepository;
    }

    @Override
    public List<Menu> findAll() {
        return menusRepository.findAll();
    }

    @Override
    public Menu findById(Long id) {
        Optional<Menu> menu = menusRepository.findById(id);

        if (menu.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Menu id %s does not exist.", id));
        }

        return menu.get();
    }

    @Override
    public Menu save(MenuCreationDTO menuCreationDTO) {
        Menu menu = menuCreationMapper.fromDTO(menuCreationDTO);

        return menusRepository.save(menu);
    }
}
