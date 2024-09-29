package ru.margarite.volkova.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.dto.Entity;
import ru.margarite.volkova.dto.GetAllResponse;

import java.util.List;
import java.util.Map;

import static ru.margarite.volkova.steps.ApiSteps.*;

public class GetAllEntityTests extends BaseTest {
    private List<String> id;

    @Test
    @DisplayName("Получение всех сущностей")
    public void getAllEntity(List<Entity> entities) {
        id = entities.stream().map(entity -> create(reqSpec, entity)).toList();

        Assertions.assertEquals(new GetAllResponse(entities), getAll(reqSpec, Map.of()));
    }

    @AfterEach
    public void deleteEntity() {
        if (id != null) {
            id.forEach(id -> delete(reqSpec, id));
        }
    }
}
