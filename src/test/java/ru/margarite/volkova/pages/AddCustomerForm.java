package ru.margarite.volkova.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static ru.margarite.volkova.helpers.DataUtils.generateFirstName;
import static ru.margarite.volkova.helpers.ElementMethods.*;

public class AddCustomerForm extends BasePage {
    private final WebDriver driver;
    private final Menu menu;

    @FindBy(xpath = "//input[@ng-model='fName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@ng-model='lName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@ng-model='postCd']")
    private WebElement postCodeInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement addCustomerSubmitButton;

    public AddCustomerForm(WebDriver driver, Menu menu) {
        super(driver);
        this.driver = driver;
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    public AddCustomerForm addCustomer(String lastName, String postCode) {
        return addCustomer(generateFirstName(postCode), lastName, postCode);
    }

    public AddCustomerForm addCustomer(String firstName, String lastName, String postCode) {
        return fillAddCustomerForm(firstName, lastName, postCode)
                .clickSubmitButtonAndAcceptAlert("Customer added successfully");
    }

    @Step("Добавление кандидата с параметрами: {firstName}, {lastName}, {postCode}")
    public AddCustomerForm fillAddCustomerForm(String firstName, String lastName, String postCode) {
        setValue(firstName, firstNameInput);
        setValue(lastName, lastNameInput);
        setValue(postCode, postCodeInput);
        return this;
    }

    @Step("Нажать кнопку \"Add Customer\" и закрыть алерт с текстом {textAlert}")
    public AddCustomerForm clickSubmitButtonAndAcceptAlert(String textAlert) {
        clickButton(addCustomerSubmitButton);
        acceptAlert(textAlert, driver);
        return this;
    }

    public Menu getMenu() {
        return menu;
    }

    @Step("Проверка обязательности поля First Name")
    public void isRequiredFirstName() {
        isRequired(firstNameInput);
    }

    @Step("Проверка обязательности поля Last Name")
    public void isRequiredLastName() {
        isRequired(lastNameInput);
    }

    @Step("Проверка обязательности поля Post Code")
    public void isRequiredPostCodeName() {
        isRequired(postCodeInput);
    }

    @Step("Проверка элементов на странице")
    public AddCustomerForm verifyPage() {
        menu.verifyComponent();

        isClickable(firstNameInput);
        placeholderShouldNaveText(firstNameInput, "First Name");
        labelShouldNaveText(firstNameInput, "First Name :");

        isClickable(lastNameInput);
        placeholderShouldNaveText(lastNameInput, "Last Name");
        labelShouldNaveText(lastNameInput, "Last Name :");

        isClickable(postCodeInput);
        placeholderShouldNaveText(postCodeInput, "Post Code");
        labelShouldNaveText(postCodeInput, "Post Code :");

        isClickable(addCustomerSubmitButton);
        shouldHaveText(addCustomerSubmitButton, "Add Customer");
        return this;
    }
}
