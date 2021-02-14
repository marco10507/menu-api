package com.example.menuservice.service;

import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.entity.MenuSectionItem;
import com.example.menuservice.entity.dto.MenuItemCreationDTO;
import com.example.menuservice.exception.ResourceNotFoundException;
import com.example.menuservice.mapper.MenuItemCreationMapper;
import com.example.menuservice.repository.MenuItemsRepository;
import com.example.menuservice.repository.MenuSectionItemsRepository;
import com.example.menuservice.repository.MenuSectionsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MenuItemsServiceTest {
    @Autowired
    private MenuItemsService menuItemsService;

    @MockBean
    private MenuItemCreationMapper mockMenuItemCreationMapper;
    @MockBean
    private MenuSectionItemsRepository mockMenuSectionItemsRepository;
    @MockBean
    private MenuSectionsRepository mockMenuSectionsRepository;
    @MockBean
    private MenuItemsRepository mockMenuItemsRepository;

    @Test
    public void createItemTest() {
        MenuItem mockItem = new MenuItem();
        MenuItemCreationDTO mockItemCreationDTO = new MenuItemCreationDTO();
        MenuSection mockMenuSection = new MenuSection();
        Optional<MenuSection> mockOptionalSection = Optional.of(mockMenuSection);

        when(mockMenuItemCreationMapper.fromDTO(mockItemCreationDTO)).thenReturn(mockItem);
        when(mockMenuSectionsRepository.findByIdAndMenuId(10L, 10L)).thenReturn(mockOptionalSection);

        MenuItem actualItem = menuItemsService.save(10L, 10L, mockItemCreationDTO);
        MenuItem expectedItem = mockItem;

        assertEquals(expectedItem, actualItem);

        verify(mockMenuItemsRepository, times(1)).save(mockItem);

        ArgumentCaptor<MenuSectionItem> menuSectionItemCaptor = ArgumentCaptor.forClass(MenuSectionItem.class);

        verify(mockMenuSectionItemsRepository, times(1)).save(menuSectionItemCaptor.capture());

        assertEquals(mockMenuSection, menuSectionItemCaptor.getValue().getMenuSection());
        assertEquals(mockItem, menuSectionItemCaptor.getValue().getMenuItem());
    }

    @Test
    public void createItemWhenSectionNotFoundTest() {
        Optional<MenuSection> mockSection = Optional.empty();

        when(mockMenuSectionsRepository.findByIdAndMenuId(anyLong(), anyLong())).thenReturn(mockSection);

        MenuItemCreationDTO menuItemCreationDTO = new MenuItemCreationDTO();

        assertThrows(
                ResourceNotFoundException.class,
                () -> menuItemsService.save(10L, 10L, menuItemCreationDTO)
        );
    }
}
