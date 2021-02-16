package com.example.menuservice.repository;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.entity.MenuSection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
class MenuSectionsRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private MenuSectionsRepository menuSectionsRepository;

    @Test
    void findByIdAndMenuIdTest() {
        Menu menu = Menu.builder()
                .name("menu 1")
                .description("menu description")
                .build();

        testEntityManager.persist(menu);

        MenuSection menuSection = MenuSection.builder()
                .name("menu section")
                .description("menu description")
                .menu(menu)
                .build();

        testEntityManager.persist(menuSection);

        Optional<MenuSection> foundMenuSection = menuSectionsRepository.findByIdAndMenuId(menuSection.getId(), menu.getId());

        assertThat(foundMenuSection).isEqualTo(Optional.of(menuSection));
    }
}