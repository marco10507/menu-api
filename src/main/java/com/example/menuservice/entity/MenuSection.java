package com.example.menuservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class MenuSection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;

    @ManyToOne
    @JsonBackReference
    private Menu menu;

    @OneToMany(mappedBy = "menuSection")
    @JsonManagedReference
    private List<MenuSectionItem> items;

    public MenuSection() {
    }

    public MenuSection(String name, String description, Menu menu, List<MenuSectionItem> items) {
        this.name = name;
        this.description = description;
        this.menu = menu;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<MenuSectionItem> getItems() {
        return items;
    }

    public void setItems(List<MenuSectionItem> items) {
        this.items = items;
    }
}