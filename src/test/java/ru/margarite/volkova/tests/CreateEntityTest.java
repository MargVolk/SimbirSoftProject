package ru.margarite.volkova.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.dto.Entity;

import static ru.margarite.volkova.steps.ApiSteps.*;

public class CreateEntityTest extends BaseTest {
    private String id;

    @Test
    @DisplayName("Создание сущности")
    public void createEntity(Entity entity) {
        id = create(reqSpec, entity);
    }

    @Test
    @DisplayName("Обязательность поля title")
    public void requiredTitle(Entity entity) {
        entity.setTitle(null);
        Assertions.assertEquals("title field is required", errorCreate(reqSpec, entity));
    }

    @AfterEach
    public void deleteEntity() {
        if (id != null) {
            delete(reqSpec, id);
        }
    }
}
