package com.example.menuservice.repository;

import com.example.menuservice.entity.MenuSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuSectionsRepository extends JpaRepository<MenuSection, Long> {
    Optional<MenuSection> findByIdAndMenuId(Long sectionId, Long menuId);
}
