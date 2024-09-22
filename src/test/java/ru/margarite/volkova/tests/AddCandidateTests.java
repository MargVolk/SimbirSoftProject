package ru.margarite.volkova.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.BaseTest;
import ru.margarite.volkova.pages.BankManagerPage;

import static ru.margarite.volkova.helpers.DataUtils.*;

public class AddCandidateTests extends BaseTest {

    @DisplayName("Проверка элементов на странице \"Добавление клиента\"")
    @Test
    public void checkVerifyAddCandidatePage() {
        new BankManagerPage(driver)
                .getMenu()
                .clickAddCustomerButton()
                .verifyPage();
    }

    @DisplayName("Обязательность поля First Name")
    @Test
    public void checkRequiredFirstNameInput() {
        new BankManagerPage(driver)
                .getMenu()
                .clickAddCustomerButton()
                .isRequiredFirstName();
    }

    @DisplayName("Обязательность поля Last Name")
    @Test
    public void checkRequiredLastNameInput() {
        new BankManagerPage(driver)
                .getMenu()
                .clickAddCustomerButton()
                .isRequiredLastName();
    }

    @DisplayName("Обязательность поля Post Code")
    @Test
    public void checkRequiredPostCodeInput() {
        new BankManagerPage(driver)
                .getMenu()
                .clickAddCustomerButton()
                .isRequiredPostCodeName();
    }

    @DisplayName("Добавление клиента")
    @Test
    public void addCustomer() {
        String postCode = generatePostCode();
        String firstName = generateFirstName(postCode);

        new BankManagerPage(driver)
                .getMenu()
                .clickAddCustomerButton()
                .addCustomer(firstName, getLastNameFromList(), postCode)
                .getMenu().clickShowCustomersButton()
                .deleteCustomer(firstName);
    }

    @DisplayName("Дубликат клиента")
    @Test
    public void addDuplicateCustomer() {
        String postCode = generatePostCode();
        String firstName = generateFirstName(postCode);

        new BankManagerPage(driver)
                .getMenu()
                .clickAddCustomerButton()
                .addCustomer(firstName, "Jones", postCode)
                .fillAddCustomerForm(firstName, "Jones", postCode)
                .clickSubmitButtonAndAcceptAlert("Please check the details. Customer may be duplicate.")
                .getMenu().clickShowCustomersButton()
                .deleteCustomer(firstName);
    }
}
