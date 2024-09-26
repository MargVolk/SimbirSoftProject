package ru.margarite.volkova.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.dto.Entity;

import java.util.List;

import static ru.margarite.volkova.steps.ApiSteps.*;

public class PatchEntityTests extends BaseTest {
    String id;

    @Test
    @DisplayName("Изменение сущности")
    public void patchEntity(Entity entity) {
        Entity newEntity = new Entity(entity.getAddition(), List.of(4, 5, 6), "Тест", true);

        id = create(reqSpec, entity);
        patch(reqSpec, id, newEntity);
    }

    @AfterEach
    public void deleteEntity() {
        if (id != null) {
            delete(reqSpec, id);
        }
    }
}
