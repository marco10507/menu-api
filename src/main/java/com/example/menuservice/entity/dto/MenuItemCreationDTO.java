package com.example.menuservice.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MenuItemCreationDTO {
    @NotNull
    private Double price;
    @NotBlank
    private String currency;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String pictureLink;
}