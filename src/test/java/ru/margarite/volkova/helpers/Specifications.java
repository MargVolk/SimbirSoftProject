package ru.margarite.volkova.helpers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {
    static ConfigProvider props = ConfigProvider.getInstance();

    public static RequestSpecification requestSpecification() {
        AllureRestAssured allureRestAssured = new AllureRestAssured()
                .setRequestAttachmentName("Запрос")
                .setResponseAttachmentName("Ответ");

        return new RequestSpecBuilder()
                .addFilter(allureRestAssured)
                .setContentType(ContentType.JSON)
                .setBaseUri(props.getValue("base_url"))
                .setPort(Integer.parseInt(props.getValue("base_port")))
                .build();
    }
}
