package com.example.menuservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class MenuSectionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JsonBackReference
    private MenuSection menuSection;

    @ManyToOne
    private MenuItem menuItem;

    public MenuSectionItem() {
    }

    public MenuSectionItem(MenuSection menuSection, MenuItem menuItem) {
        this.menuSection = menuSection;
        this.menuItem = menuItem;
    }

    public Long getId() {
        return id;
    }

    public MenuSection getMenuSection() {
        return menuSection;
    }

    public void setMenuSection(MenuSection menuSection) {
        this.menuSection = menuSection;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}