package ru.margarite.volkova.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.dto.Entity;

import java.util.List;

import static ru.margarite.volkova.steps.ApiSteps.*;

public class PatchEntityTests extends BaseTest {
    private String id;

    @Test
    @DisplayName("Изменение сущности")
    public void patchEntity(Entity entity) {
        Entity newEntity = Entity.builder()
                .addition(entity.getAddition())
                .importantNumbers(List.of(4, 5, 6))
                .title("Тест")
                .verified(true)
                .build();

        id = create(reqSpec, entity);
        patch(reqSpec, id, newEntity);
        newEntity.setId(id);
        Assertions.assertEquals(newEntity, get(reqSpec, id));
    }

    @Test
    @DisplayName("Изменение не существующей сущности")
    public void patchNoExistEntity(Entity entity) {
        Entity newEntity = Entity.builder()
                .addition(entity.getAddition())
                .importantNumbers(List.of(4, 5, 6))
                .title("Тест")
                .verified(true)
                .build();

        Assertions.assertEquals("unable to check for existence of such id [no rows in result set]",
                errorPatch(reqSpec, "100", newEntity));
    }

    @AfterEach
    public void deleteEntity() {
        if (id != null) {
            delete(reqSpec, id);
        }
    }
}
