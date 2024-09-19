package ru.margarite.volkova.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.BaseTest;
import ru.margarite.volkova.pages.BankManagerPage;

import static ru.margarite.volkova.helpers.DataUtils.*;

public class AddCandidateTests extends BaseTest {

    @DisplayName("Обязательность поля First Name")
    @Test
    public void checkRequiredFirstNameInput() {
        new BankManagerPage(driver)
                .clickAddCustomerButton()
                .isRequiredFirstName();
    }

    @DisplayName("Обязательность поля Last Name")
    @Test
    public void checkRequiredLastNameInput() {
        new BankManagerPage(driver)
                .clickAddCustomerButton()
                .isRequiredLastName();
    }

    @DisplayName("Обязательность поля Post Code")
    @Test
    public void checkRequiredPostCodeInput() {
        new BankManagerPage(driver)
                .clickAddCustomerButton()
                .isRequiredPostCodeName();
    }

    @DisplayName("Добавление клиента")
    @Test
    public void addCustomer() {
        String postCode = generatePostCode();

        new BankManagerPage(driver)
                .clickAddCustomerButton()
                .addCustomer(getRandomLastName(), postCode)
                .getMenu().clickShowCustomersButton()
                .deleteCustomer(generateFirstName(postCode));
    }

    @DisplayName("Дубликат клиента")
    @Test
    public void addDuplicateCustomer() {
        String postCode = generatePostCode();

        new BankManagerPage(driver)
                .clickAddCustomerButton()
                .addCustomer("Jones", postCode)
                .fillAddCustomerForm(generateFirstName(postCode), "Jones", postCode)
                .clickSubmitButtonAndAcceptAlert("Please check the details. Customer may be duplicate.")
                .getMenu().clickShowCustomersButton()
                .deleteCustomer(generateFirstName(postCode));
    }
}
