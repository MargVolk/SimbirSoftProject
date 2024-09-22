package ru.margarite.volkova.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BankManagerPage extends BasePage {
    private final Menu menu;

    public BankManagerPage(WebDriver driver) {
        super(driver);
        menu = new Menu(driver);
    }

    public Menu getMenu() {
        return menu;
    }

    @Step("Проверка элементов на странице")
    public BankManagerPage verifyPage() {
        header.verifyComponent();
        menu.verifyComponent();
        return this;
    }
}
