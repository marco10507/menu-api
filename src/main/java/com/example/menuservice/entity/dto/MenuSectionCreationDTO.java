package com.example.menuservice.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MenuSectionCreationDTO {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
