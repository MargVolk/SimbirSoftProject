package ru.margarite.volkova.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static ru.margarite.volkova.helpers.ElementMethods.*;

public class Header {

    @FindBy(xpath = "//div[@class ='box mainhdr']/button[contains(@class, 'home')]")
    private WebElement homeButton;

    @FindBy(xpath = "//div[@class ='box mainhdr']/strong[@class = 'mainHeading']")
    private WebElement titleHeader;

    @FindBy(xpath = "//div[@class ='box mainhdr']/button[contains(@class, 'logout')]")
    private WebElement logoutButton;

    public Header(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Проверка элементов у компонента")
    public Header verifyComponent() {
        isClickable(homeButton);
        isNotVisible(logoutButton);

        shouldHaveText(titleHeader, "XYZ Bank");
        shouldHaveText(homeButton, "Home");
        return this;
    }
}
