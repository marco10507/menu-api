package com.example.menuservice.service;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.MenuSection;
import com.example.menuservice.entity.dto.MenuSectionCreationDTO;
import com.example.menuservice.exception.ResourceNotFoundException;
import com.example.menuservice.mapper.MenuSectionCreationMapper;
import com.example.menuservice.repository.MenuSectionsRepository;
import com.example.menuservice.repository.MenusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@SpringBootTest
public class MenuSectionsServiceTest {
    @Autowired
    private MenuSectionsService menuSectionsService;

    @MockBean
    private MenuSectionCreationMapper mockMenuSectionCreationMapper;
    @MockBean
    private MenusRepository mockMenusRepository;
    @MockBean
    private MenuSectionsRepository mockMenuSectionsRepository;

    @Test
    public void createSectionTest() {
        Optional<Menu> mockMenu = Optional.of(new Menu());
        MenuSectionCreationDTO mockSectionCreationDTO = new MenuSectionCreationDTO();
        MenuSection mockMenuSection = new MenuSection();

        when(mockMenusRepository.findById(100L)).thenReturn(mockMenu);
        when(mockMenuSectionCreationMapper.fromDTO(mockSectionCreationDTO)).thenReturn(mockMenuSection);

        MenuSection actualMenuSection = menuSectionsService.save(100L, mockSectionCreationDTO);
        MenuSection expectedMenuSection = mockMenuSection;

        assertEquals(expectedMenuSection, actualMenuSection);
        assertEquals(actualMenuSection.getMenu(), mockMenu.get());

        verify(mockMenuSectionsRepository, times(1)).save(mockMenuSection);
    }

    @Test
    public void createSectionWhenMenuNotFoundTest() {
        Optional<Menu> mockMenu = Optional.empty();

        when(mockMenusRepository.findById(anyLong())).thenReturn(mockMenu);

        MenuSectionCreationDTO mockMenuSectionCreationDTO = new MenuSectionCreationDTO();

        assertThrows(
                ResourceNotFoundException.class,
                () -> menuSectionsService.save(100L, mockMenuSectionCreationDTO)
        );
    }
}
