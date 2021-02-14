package com.example.menuservice.entity.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class MenuCreationDTO {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
