package com.example.menuservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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