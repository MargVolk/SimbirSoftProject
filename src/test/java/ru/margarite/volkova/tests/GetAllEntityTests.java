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
    String id;

    @Test
    @DisplayName("Получение всех сущностей")
    public void getAllEntity(Entity entity) {
        id = create(reqSpec, entity);
        entity.getAddition().setId(id);
        entity.setId(id);

        Assertions.assertEquals(new GetAllResponse(List.of(entity)), getAll(reqSpec, Map.of()));
    }

    @AfterEach
    public void deleteEntity() {
        if (id != null) {
            delete(reqSpec, id);
        }
    }
}
