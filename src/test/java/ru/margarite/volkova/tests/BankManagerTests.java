package ru.margarite.volkova.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.BaseTest;
import ru.margarite.volkova.pages.BankManagerPage;

public class BankManagerTests extends BaseTest {

    @DisplayName("Проверка элементов на странице \"Менеджера\"")
    @Test
    public void checkVerifyBankPage() {
        new BankManagerPage(driver)
                .verifyPage();
    }
}
