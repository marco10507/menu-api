package com.example.menuservice.repository;

import com.example.menuservice.entity.MenuSectionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuSectionItemsRepository extends JpaRepository<MenuSectionItem, Long> {
}
