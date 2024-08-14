package ru.margarite.volkova;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.margarite.volkova.utils.dateUtils.DateParse;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class ElementMethods {

    private static WebElement getSelectValueWebElement(WebDriver driver, String value) {
        return driver.findElement(By.xpath("//div[contains(@id, 'react-select') and text()='" + value + "']"));
    }

    public static void clickButton(WebElement button) {
        button.click();
    }

    public static void inputText(String text, WebElement input) {
        input.sendKeys(text);
        Assertions.assertEquals(text, input.getAttribute("value"), "Поле не содержит введенного значения");
    }

    public static void selectWithSearch(String value, WebDriver driver, WebElement select) {
        select.click();
        select.sendKeys(value.substring(0, 2));
        getSelectValueWebElement(driver, value).click();
        Assertions.assertEquals(value, select.findElement(By.xpath("../../preceding-sibling::div"))
                .getText(), "Поле не содержит выбранное значение");
    }

    public static void select(String value, WebDriver driver, WebElement select) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", select);
        new Actions(driver)
                .click(select.findElement(By.xpath("./ancestor::div[contains(@class, 'control')]//*[local-name() = 'svg']"))).perform();
        getSelectValueWebElement(driver, value).click();
        Assertions.assertEquals(value, select.findElement(By.xpath("./ancestor::div[contains(@class, 'control')]//div[contains(@class, 'Value')]"))
                .getText(), "Поле не содержит выбранное значение");
    }

    public static void radio(String value, WebDriver driver, List<WebElement> radios) {
        for (WebElement radio : radios) {
            if (value.equals(radio.getAttribute("value"))) {
                new Actions(driver).moveToElement(radio).click().perform();
                Assertions.assertEquals("true", radio.getAttribute("checked"),
                        "Элемент не был выбран");
            }
        }
    }

    public static void checkText(String text, WebElement element) {
        Assertions.assertEquals(text, element.getText());
    }

    public static WebElement waitVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void setDate(String date, WebDriver driver, WebElement datepickerInput) {
        DateParse dateParse = new DateParse(date);

        datepickerInput.click();

        new Select(driver.findElement(By.cssSelector(".react-datepicker__year-select")))
                .selectByValue(dateParse.getYear());
        new Select(driver.findElement(By.cssSelector(".react-datepicker__month-select")))
                .selectByValue(dateParse.getMonth());
        driver.findElement(By.xpath("//div[contains(@aria-label, '" +
                        dateParse.getLongNameMonth() + " " + dateParse.getDay() + "')]"))
                .click();

        Assertions.assertEquals(dateParse.getDateWithFormat("dd MMM yyyy")
                , datepickerInput.getAttribute("value"), "Проставленная дата не соответствует ожидаемой");
    }

    public static void checkbox(List<String> values, WebDriver driver) {
        for (String value : values) {
            WebElement checkbox = driver.findElement(By.xpath("//label[text()='" + value + "']" +
                    "/preceding-sibling::input[@type='checkbox']"));
            new Actions(driver).moveToElement(checkbox).click().perform();
            Assertions.assertEquals("true", checkbox.getAttribute("checked"),
                    "В чекбоксе не отображается выбор");
        }
    }

    public static void uploadFile(File file, WebElement uploadInput) {
        uploadInput.sendKeys(file.getAbsolutePath());
        String[] actual = uploadInput.getAttribute("value").split("\\\\");
        Assertions.assertEquals(file.getName(), actual[actual.length - 1], "Файл не был загружен");
    }
}
