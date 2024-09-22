package ru.margarite.volkova.helpers;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

    public static void shouldHaveText(WebElement element, String expectedText) {
        isVisible(element);
        Assertions.assertEquals(expectedText, element.getText(), "Элемент не содержит " + expectedText + " текст");
    }

    public static void isClickable(WebElement button) {
        Assertions.assertTrue(button.isEnabled() && button.isDisplayed(), "Элемент не кликабельный");
    }

    public static void isVisible(WebElement element) {
        Assertions.assertTrue(element.isDisplayed(), "Элемент не видим");
    }

    public static void isNotVisible(WebElement element) {
        Assertions.assertFalse(element.isDisplayed(), "Элемент не видим");
    }

    public static void placeholderShouldNaveText(WebElement element, String expectedText) {
        Assertions.assertEquals(expectedText, element.getAttribute("placeholder"),
                "Плейсхолдер не содержит " + expectedText + " текст");
    }

    public static void labelShouldNaveText(WebElement element, String expectedText) {
        Assertions.assertEquals(expectedText, element.findElement(
                        By.xpath("./parent::div[@class='form-group']/label")).getText(),
                "Лейбл элемента не содержит " + expectedText + " текст");
    }

}
