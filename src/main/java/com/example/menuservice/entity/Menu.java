package com.example.menuservice.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy="menu")
    private List<MenuSection> sections;

    public Menu() {
    }

    public Menu(String name, String description, List<MenuSection> sections) {
        this.name = name;
        this.description = description;
        this.sections = sections;
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

    public List<MenuSection> getSections() {
        return sections;
    }

    public void setSections(List<MenuSection> sections) {
        this.sections = sections;
    }
}
