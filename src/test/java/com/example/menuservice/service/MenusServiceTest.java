package com.example.menuservice.service;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.entity.MenuSectionItem;
import com.example.menuservice.exception.ResourceNotFoundException;
import com.example.menuservice.repository.MenuItemsRepository;
import com.example.menuservice.repository.MenuSectionItemsRepository;
import com.example.menuservice.repository.MenuSectionsRepository;
import com.example.menuservice.repository.MenusRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
    @MockBean
    private MenuSectionsRepository mockMenuSectionsRepository;
    @MockBean
    private MenuSectionItemsRepository mockMenuSectionItemsRepository;
    @MockBean
    private MenuItemsRepository mockMenuItemsRepository;

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

        Optional<Menu> actualMenu = menusService.findById(100L);
        Optional<Menu> expectedMenu = mockMenu;

        assertEquals(expectedMenu, actualMenu);
    }

    @Test
    public void createSectionTest() {
        Optional<Menu> mockMenu = Optional.of(new Menu());

        when(mockMenusRepository.findById(100L)).thenReturn(mockMenu);

        MenuSection menuSection = new MenuSection();

        MenuSection actualMenuSection = menusService.createSection(100L, menuSection);
        MenuSection expectedMenuSection = menuSection;

        assertEquals(expectedMenuSection, actualMenuSection);
        assertEquals(actualMenuSection.getMenu(), mockMenu.get());

        verify(mockMenuSectionsRepository, times(1)).save(menuSection);
    }

    @Test
    public void createSectionWhenMenuNotFoundTest() throws ResourceNotFoundException {
        Optional<Menu> mockMenu = Optional.empty();

        when(mockMenusRepository.findById(anyLong())).thenReturn(mockMenu);

        MenuSection menuSection = new MenuSection();

        assertThrows(ResourceNotFoundException.class, () -> menusService.createSection(100L, menuSection));
    }

    @Test
    public void createItemTest() {
        Optional<MenuSection> mockSection = Optional.of(new MenuSection());

        when(mockMenuSectionsRepository.findByIdAndMenuId(10L, 10L)).thenReturn(mockSection);

        MenuItem menuItem = new MenuItem();

        MenuItem actualMenuItem = menusService.createItem(10L, 10L, menuItem);
        MenuItem expectedMenuItem = menuItem;

        assertEquals(expectedMenuItem, actualMenuItem);

        verify(mockMenuItemsRepository, times(1)).save(menuItem);

        ArgumentCaptor<MenuSectionItem> menuSectionItemCaptor = ArgumentCaptor.forClass(MenuSectionItem.class);

        verify(mockMenuSectionItemsRepository, times(1)).save(menuSectionItemCaptor.capture());

        MenuSection expectedMenuSection = menuSectionItemCaptor.getValue().getMenuSection();
        MenuSection actualMenuSection = mockSection.get();

        assertEquals(actualMenuSection, expectedMenuSection);

        MenuItem actualItem = menuSectionItemCaptor.getValue().getMenuItem();

        assertEquals(expectedMenuItem, actualItem);
    }

    @Test
    public void createItemWhenSectionNotFoundTest() {
        Optional<MenuSection> mockSection = Optional.empty();

        when(mockMenuSectionsRepository.findByIdAndMenuId(anyLong(), anyLong())).thenReturn(mockSection);

        MenuItem menuItem = new MenuItem();

        assertThrows(ResourceNotFoundException.class, () -> menusService.createItem(10L, 10L, menuItem));
    }
}
