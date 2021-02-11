package com.example.menuservice.controller;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.entity.MenuSection;
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
import java.util.Optional;

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

    @Test
    public void getMenusTest() throws Exception {
        Menu mockMenu1 = new Menu("menu 1", "best menu", new ArrayList<>());
        mockMenu1.setId(1L);

        Menu mockMenu2 = new Menu("menu 2", "best menu", new ArrayList<>());
        mockMenu2.setId(2L);

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
        Menu mockMenu = new Menu("menu 1", "best menu", new ArrayList<>());
        mockMenu.setId(100L);

        when(mockMenusService.findById(100L)).thenReturn(Optional.of(mockMenu));

        String expectedJson = mapper.writeValueAsString(mockMenu);

        mockMvc.perform(get("/menus/100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void getMenuWhenMenuNotFoundTest() throws Exception {
        when(mockMenusService.findById(100L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/menus/100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void postMenuTest() throws Exception {
        Menu mockMenu = new Menu(
                "menu 1",
                "best menu",
                null
        );

        when(mockMenusService.createMenu(any(Menu.class))).thenReturn(mockMenu);

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
        MenuSection mockSection = new MenuSection(
                "section",
                "my section",
                null,
                null
        );

        when(mockMenusService.createSection(anyLong(), any(MenuSection.class))).thenReturn(mockSection);

        String json = mapper.writeValueAsString(mockSection);

        mockMvc.perform(post("/menu/100/section")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(json));
    }

    @Test
    public void postItemTest() throws Exception {
        MenuItem mockMenuItem = new MenuItem(
                10.5,
                "eur",
                "Haring",
                "best fish on earth",
                "www.mypictures.com/haring.png"
        );

        when(mockMenusService.createItem(anyLong(), anyLong(), any(MenuItem.class))).thenReturn(mockMenuItem);

        String json = mapper.writeValueAsString(mockMenuItem);

        mockMvc.perform(post("/menu/10/section/1/item")
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(json));
    }
}