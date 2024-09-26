package ru.margarite.volkova.tests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.margarite.volkova.extensions.EntityData;

import static ru.margarite.volkova.helpers.Specifications.requestSpecification;

@ExtendWith(EntityData.class)
public class BaseTest {
    protected final static RequestSpecification reqSpec = requestSpecification();

    @BeforeAll
    public static void config() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
