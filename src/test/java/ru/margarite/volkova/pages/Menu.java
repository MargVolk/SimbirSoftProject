package ru.margarite.volkova.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static ru.margarite.volkova.helpers.ElementMethods.clickButton;

public class Menu {

    private final WebDriver driver;

    @FindBy(xpath = "//button[contains(text(), 'Add Customer')]")
    WebElement addCustomerButton;

    @FindBy(xpath = "//button[contains(text(), 'Open Account')]")
    WebElement openAccountButton;

    @FindBy(xpath = "//button[contains(text(), 'Customers')]")
    WebElement showCustomersButton;

    protected Menu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Нажать на кнопку \"Add Customer\" в меню")
    public AddCustomerForm clickAddCustomerButton() {
        clickButton(addCustomerButton);
        return new AddCustomerForm(driver, this);
    }

    @Step("Нажать на кнопку \"Customers\" в меню")
    public CustomersTable clickShowCustomersButton() {
        clickButton(showCustomersButton);
        return new CustomersTable(driver, this);
    }
}
