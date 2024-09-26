package ru.margarite.volkova.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.dto.Entity;

import static ru.margarite.volkova.steps.ApiSteps.create;
import static ru.margarite.volkova.steps.ApiSteps.delete;

public class CreateEntityTest extends BaseTest {
    String id;

    @Test
    @DisplayName("Создание сущности")
    public void createEntity(Entity entity) {
        id = create(reqSpec, entity);
    }

    @AfterEach
    public void deleteEntity() {
        if (id != null) {
            delete(reqSpec, id);
        }
    }
}
