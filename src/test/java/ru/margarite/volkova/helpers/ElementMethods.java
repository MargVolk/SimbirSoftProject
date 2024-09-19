package ru.margarite.volkova.helpers;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Pattern;

public class ElementMethods {


    public static void clickButton(WebElement button) {
        Assertions.assertTrue(button.isEnabled(), "Кнопка задизейблена");
        button.click();
    }

    public static void doubleClickButton(WebElement button) {
        clickButton(button);
        button.click();
    }

    public static void setValue(String text, WebElement input) {
        input.sendKeys(text);
        Assertions.assertEquals(text, input.getAttribute("value"), "Поле не содержит введенного значения");
    }

    public static void isNotExist(List<WebElement> elements) {
        Assertions.assertTrue(elements.isEmpty(),
                "Элемент существует");
    }

    public static void acceptAlert(String alertText, WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        Assertions.assertTrue(Pattern.matches("^" + alertText, alertText), "Текст в алерте не совпадает");
        alert.accept();
    }

    public static void isRequired(WebElement element) {
        Assertions.assertEquals("true", element.getAttribute("required"), "Элемент не обязательный");
    }
}
