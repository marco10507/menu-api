package com.example.menuservice.service;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.dto.MenuCreationDTO;
import com.example.menuservice.exception.ResourceNotFoundException;
import com.example.menuservice.repository.MenusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MenusServiceTest {
    @Autowired
    private MenusService menusService;

    @MockBean
    private MenusRepository mockMenusRepository;

    @Test
    public void findAllTest() {
        List<Menu> mockMenus = new ArrayList<>();

        when(mockMenusRepository.findAll()).thenReturn(mockMenus);

        List<Menu> actualMenus = menusService.findAll();
        List<Menu> expectedMenus = mockMenus;

        assertEquals(expectedMenus, actualMenus);
    }

    @Test
    public void findByIdTest() {
        Optional<Menu> mockMenu = Optional.of(new Menu());

        when(mockMenusRepository.findById(100L)).thenReturn(mockMenu);

        Menu actualMenu = menusService.findById(100L);
        Menu expectedMenu = mockMenu.get();

        assertEquals(expectedMenu, actualMenu);
    }

    @Test
    public void findByIdWhenMenuNotFoundTest() {
        Optional<Menu> mockMenu = Optional.empty();

        when(mockMenusRepository.findById(100L)).thenReturn(mockMenu);

        assertThrows(ResourceNotFoundException.class, () -> menusService.findById(100L));
    }

    @Test
    public void createMenuTest() {
        Menu mockMenu = new Menu();
        MenuCreationDTO mockMenuDTO = new MenuCreationDTO();

        when(mockMenusRepository.save(mockMenu)).thenReturn(mockMenu);

        Menu actualMenu = menusService.save(mockMenuDTO);
        Menu expectedMenu = mockMenu;

        assertEquals(expectedMenu, actualMenu);
    }
}
