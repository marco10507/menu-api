package com.example.menuservice.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Double price;
    @NotNull
    private String currency;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String pictureLink;

    public MenuItem() {
    }

    public MenuItem(Double price, String currency, String name, String description, String pictureLink) {
        this.price = price;
        this.currency = currency;
        this.name = name;
        this.description = description;
        this.pictureLink = pictureLink;
    }

    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
}
