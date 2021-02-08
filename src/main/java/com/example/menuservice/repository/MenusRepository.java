package com.example.menuservice.repository;

import com.example.menuservice.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenusRepository extends JpaRepository<Menu, Long> {
}
