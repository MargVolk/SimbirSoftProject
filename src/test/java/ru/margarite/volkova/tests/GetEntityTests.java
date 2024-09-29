package ru.margarite.volkova.tests;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.dto.Entity;

import java.util.List;
import java.util.Map;

import static ru.margarite.volkova.steps.ApiSteps.*;

public class GetEntityTests extends BaseTest {
    private String id;

    @Test
    @DisplayName("Получение сущности по id")
    public void getEntity(Entity entity) {
        id = create(reqSpec, entity);

        Assertions.assertEquals(entity, get(reqSpec, id));
    }

    @Test
    @DisplayName("Получение несуществующей сущности")
    public void getNoExistEntity() {
        Assertions.assertEquals("no rows in result set", errorGet(reqSpec, "1000"));
    }

    @AfterEach
    public void deleteEntities() {
        List<String> ids = getAll(reqSpec, Map.of()).getEntity().stream().map(Entity::getId).toList();
        ids.forEach(id -> delete(reqSpec, id));
    }
}
