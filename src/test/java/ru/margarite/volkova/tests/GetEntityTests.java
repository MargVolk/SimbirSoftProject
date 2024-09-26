package ru.margarite.volkova.tests;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.dto.Entity;

import static ru.margarite.volkova.steps.ApiSteps.*;

public class GetEntityTests extends BaseTest {
    String id;

    @Test
    @DisplayName("Получение сущности по id")
    public void getEntity(Entity entity) {
        id = create(reqSpec, entity);
        entity.getAddition().setId(id);
        entity.setId(id);

        Assertions.assertEquals(entity, get(reqSpec, id));
    }

    @Test
    @DisplayName("Получение несуществующей сущности")
    public void getNoExistEntity() {
        Assertions.assertEquals("no rows in result set", errorGet(reqSpec, "1000"));
    }

    @AfterEach
    public void deleteEntity() {
        if (id != null) {
            delete(reqSpec, id);
        }
    }
}
