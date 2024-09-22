package ru.margarite.volkova.elements;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Table {
    default List<String> parseDataColumn(WebDriver driver, String columnName) {
        int indexColumn = driver.findElements(By.xpath("//thead/tr/td")).stream()
                .map(WebElement::getText)
                .toList()
                .indexOf(columnName);

        return driver.findElements(By.xpath("//tbody/tr/td[" + indexColumn + 1 + "]")).stream()
                .map(WebElement::getText).toList();
    }

    default List<HashMap<String, String>> parseDataRows(List<String> headers, List<WebElement> rows) {
        List<HashMap<String, String>> dataRows = new ArrayList<>();
        for (WebElement row : rows) {
            List<String> dataRow = row.findElements(By.xpath("./td")).stream()
                    .map(WebElement::getText)
                    .toList();

            if (headers.size() != dataRow.size()) Assertions.fail("Ошибка во время парсинга данных таблицы: " +
                    "количество колонок не соответствует количеству данных - " + dataRow);

            dataRows.add(IntStream.range(0, headers.size())
                    .boxed()
                    .collect(Collectors.toMap(headers::get, dataRow::get, (e1, e2) -> e1, HashMap::new)));
        }

        return dataRows;
    }
}
