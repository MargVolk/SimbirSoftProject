package ru.margarite.volkova.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.margarite.volkova.dto.Addition;
import ru.margarite.volkova.dto.Entity;

import java.util.List;

public class EntityData implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == Entity.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Addition addition = new Addition("Дополнительная информация", 1);
        Entity entity = new Entity(addition, List.of(1, 2, 3), "Тест", true);

        return entity;
    }
}
