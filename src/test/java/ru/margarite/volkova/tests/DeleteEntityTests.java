package ru.margarite.volkova.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.dto.Entity;

import static ru.margarite.volkova.steps.ApiSteps.*;

public class DeleteEntityTests extends BaseTest {

    @Test
    @DisplayName("Удаление сущности")
    public void deleteEntity(Entity entity) {
        String id = create(reqSpec, entity);
        delete(reqSpec, id);
    }

    @Test
    @DisplayName("Удаление несуществующей сущности")
    public void deleteNoExistEntity() {
        Assertions.assertEquals("no rows found for this id", errorDelete(reqSpec, "100"));
    }
}
