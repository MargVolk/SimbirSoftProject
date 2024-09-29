package ru.margarite.volkova.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.margarite.volkova.dto.Entity;
import ru.margarite.volkova.dto.GetAllResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static ru.margarite.volkova.helpers.Endopoints.*;

public class ApiSteps {

    @Step("Отправка запроса getAll")
    public static GetAllResponse getAll(RequestSpecification reqSpec, Map<String, String> params) {
        return given()
                .spec(reqSpec)
                .queryParams(params)
                .when()
                .get(GET_ALL)
                .then()
                .statusCode(SC_OK)
                .extract()
                .as(GetAllResponse.class);
    }

    @Step("Отправка запроса get")
    public static Entity get(RequestSpecification reqSpec, String pathVariable) {
        return get(reqSpec, pathVariable, SC_OK)
                .extract()
                .as(Entity.class);
    }

    @Step("Отправка невалидного запроса get")
    public static String errorGet(RequestSpecification reqSpec, String pathVariable) {
        return get(reqSpec, pathVariable, SC_INTERNAL_SERVER_ERROR)
                .extract()
                .jsonPath()
                .getString("error");
    }

    private static ValidatableResponse get(RequestSpecification reqSpec, String pathVariable, int statusCode) {
        return given()
                .spec(reqSpec)
                .when()
                .get(GET, pathVariable)
                .then()
                .statusCode(statusCode);
    }

    @Step("Отправка запроса create")
    public static String create(RequestSpecification reqSpec, Entity body) {
        String id = create(reqSpec, body, SC_OK)
                .extract()
                .jsonPath()
                .getString(".");

        body.setId(id);
        body.getAddition().setId(id);
        return id;
    }

    @Step("Отправка невалидного запроса create")
    public static String errorCreate(RequestSpecification reqSpec, Entity body) {
        return create(reqSpec, body, SC_INTERNAL_SERVER_ERROR)
                .extract()
                .jsonPath()
                .getString("error");
    }

    private static ValidatableResponse create(RequestSpecification reqSpec, Entity body, int statusCode) {
        return given()
                .spec(reqSpec)
                .body(body)
                .when()
                .post(CREATE)
                .then()
                .statusCode(statusCode);
    }

    @Step("Отправка запроса delete ")
    public static void delete(RequestSpecification reqSpec, String pathVariable) {
        delete(reqSpec, pathVariable, SC_NO_CONTENT);
    }

    @Step("Отправка невалидного запроса delete")
    public static String errorDelete(RequestSpecification reqSpec, String pathVariable) {
        return delete(reqSpec, pathVariable, SC_INTERNAL_SERVER_ERROR)
                .extract()
                .jsonPath()
                .getString("error");
    }

    private static ValidatableResponse delete(RequestSpecification reqSpec, String pathVariable, int statusCode) {
        return given()
                .spec(reqSpec)
                .when()
                .delete(DELETE, pathVariable)
                .then()
                .statusCode(statusCode);
    }

    @Step("Отправка запроса patch")
    public static void patch(RequestSpecification reqSpec, String pathVariable, Entity body) {
        patch(reqSpec, pathVariable, body, SC_NO_CONTENT);
    }

    @Step("Отправка невалидного запроса patch")
    public static String errorPatch(RequestSpecification reqSpec, String pathVariable, Entity body) {
        return patch(reqSpec, pathVariable, body, SC_BAD_REQUEST)
                .extract()
                .jsonPath()
                .getString("error");
    }

    private static ValidatableResponse patch(RequestSpecification reqSpec, String pathVariable, Entity body, int statusCode) {
        return given()
                .spec(reqSpec)
                .body(body)
                .when()
                .patch(PATCH, pathVariable)
                .then()
                .statusCode(statusCode);
    }

}
