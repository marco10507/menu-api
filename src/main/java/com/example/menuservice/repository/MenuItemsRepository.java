package com.example.menuservice.repository;

import com.example.menuservice.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemsRepository extends JpaRepository<MenuItem, Long> {
}
