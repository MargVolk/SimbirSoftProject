package ru.margarite.volkova.tests;

import org.junit.jupiter.api.*;
import ru.margarite.volkova.dto.Entity;

import java.util.List;
import java.util.Map;

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
    public void deleteEntities() {
        List<String> ids = getAll(reqSpec, Map.of()).getEntity().stream().map(Entity::getId).toList();
        ids.forEach(id -> delete(reqSpec, id));
    }
}
