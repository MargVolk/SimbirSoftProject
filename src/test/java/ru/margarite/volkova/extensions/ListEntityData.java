package ru.margarite.volkova.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.margarite.volkova.dto.Addition;
import ru.margarite.volkova.dto.Entity;

import java.util.List;

public class ListEntityData implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == List.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Addition addition = new Addition("Дополнительная информация", 1);
        Addition additionTwo = new Addition("Дополнительная информация", 2);
        Entity entity = Entity.builder()
                .addition(addition)
                .importantNumbers(List.of(1, 2, 3))
                .title("Данные 1")
                .verified(true)
                .build();

        Entity entityTwo = Entity.builder()
                .addition(additionTwo)
                .importantNumbers(List.of(4, 5, 6))
                .title("Данные 2")
                .verified(false)
                .build();

        return List.of(entity, entityTwo);
    }
}
