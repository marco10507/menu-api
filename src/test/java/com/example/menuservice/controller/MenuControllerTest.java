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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MenuControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MenusService mockMenusService;
    @MockBean
    private MenuSectionsService mockMenusSectionService;
    @MockBean
    private MenuItemsService mockMenuItemsService;

    @Test
    public void getMenusTest() throws Exception {
        Menu mockMenu1 = new Menu(1L, "menu 1", "best menu", new ArrayList<>());

        Menu mockMenu2 = new Menu(2L, "menu 2", "best menu", new ArrayList<>());

        List<Menu> mockMenus = Arrays.asList(
                mockMenu1,
                mockMenu2
        );

        when(mockMenusService.findAll()).thenReturn(mockMenus);

        String expectedJson = mapper.writeValueAsString(mockMenus);

        mockMvc.perform(get("/menus"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void getMenuTest() throws Exception {
        Menu mockMenu = new Menu(1L, "menu 1", "best menu", new ArrayList<>());

        when(mockMenusService.findById(1L)).thenReturn(mockMenu);

        String expectedJson = mapper.writeValueAsString(mockMenu);

        mockMvc.perform(get("/menus/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void postMenuTest() throws Exception {
        Menu mockMenu = Menu.builder()
                .name("Lunch menu")
                .description("best meals on earth")
                .build();

        when(mockMenusService.save(any(MenuCreationDTO.class))).thenReturn(mockMenu);

        String json = mapper.writeValueAsString(mockMenu);

        mockMvc.perform(post("/menu")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(json));
    }

    @Test
    public void postSectionTest() throws Exception {
        MenuSection mockMenuSection = MenuSection.builder()
                .name("Desserts")
                .description("Best desserts on earth")
                .build();

        when(mockMenusSectionService.save(anyLong(), any(MenuSectionCreationDTO.class))).thenReturn(mockMenuSection);

        String json = mapper.writeValueAsString(mockMenuSection);

        mockMvc.perform(post("/menu/100/section")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(json));
    }

    @Test
    public void postItemTest() throws Exception {
        MenuItem mockMenuItem = MenuItem.builder()
                .price(10.5)
                .currency("eur")
                .name("haring")
                .description("best fish on earth")
                .pictureLink("www.mypictures.com/haring.png")
                .build();

        when(mockMenuItemsService.save(anyLong(), anyLong(), any(MenuItemCreationDTO.class)))
                .thenReturn(mockMenuItem);

        String json = mapper.writeValueAsString(mockMenuItem);

        mockMvc.perform(post("/menu/10/section/1/item")
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(json));
    }
}