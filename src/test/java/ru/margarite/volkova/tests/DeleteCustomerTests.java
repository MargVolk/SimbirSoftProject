package ru.margarite.volkova.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.margarite.volkova.BaseTest;
import ru.margarite.volkova.pages.BankManagerPage;

import java.util.Arrays;
import java.util.List;

import static ru.margarite.volkova.helpers.DataUtils.*;

public class DeleteCustomerTests extends BaseTest {
    List<String> fistNameCustomers;

    @DisplayName("Проверка элементов на странице \"Клиенты\"")
    @Test
    public void checkVerifyCustomers() {
        new BankManagerPage(driver)
                .getMenu()
                .clickShowCustomersButton()
                .verifyPage();
    }

    @DisplayName("Удаление клиента с длиной имени равной среднему арифметическому")
    @Test
    public void deleteCustomerWithCondition() {
        fistNameCustomers = Arrays.asList("Ah", "Aba", "A");

        new BankManagerPage(driver)
                .getMenu()
                .clickAddCustomerButton()
                .addCustomer(fistNameCustomers.get(0), getLastNameFromList(), generatePostCode())
                .addCustomer(fistNameCustomers.get(1), getLastNameFromList(), generatePostCode())
                .addCustomer(fistNameCustomers.get(2), getLastNameFromList(), generatePostCode())
                .getMenu().clickShowCustomersButton()
                .deleteCustomer(averageLengthName(fistNameCustomers));
    }

    @DisplayName("Удаление нескольких клиентов подходящих под условие")
    @Test
    public void deleteCustomersWithSameLength() {
        fistNameCustomers = Arrays.asList("Albus", "Neville", "Voldemort", "Margota");

        new BankManagerPage(driver)
                .getMenu()
                .clickAddCustomerButton()
                .addCustomer(fistNameCustomers.get(0), getLastNameFromList(), generatePostCode())
                .addCustomer(fistNameCustomers.get(1), getLastNameFromList(), generatePostCode())
                .addCustomer(fistNameCustomers.get(2), getLastNameFromList(), generatePostCode())
                .addCustomer(fistNameCustomers.get(3), getLastNameFromList(), generatePostCode())
                .getMenu().clickShowCustomersButton()
                .deleteCustomer(averageLengthName(fistNameCustomers));
    }

    @DisplayName("Удаление клиента с наиболее близкой длиной имени к среднему арифметическому")
    @Test
    public void deleteCustomerWithLengthCloserToAverage() {
        fistNameCustomers = Arrays.asList("Kirill", "Margo", "Ellisa"); //

        new BankManagerPage(driver)
                .getMenu()
                .clickAddCustomerButton()
                .addCustomer(fistNameCustomers.get(0), getLastNameFromList(), generatePostCode())
                .addCustomer(fistNameCustomers.get(1), getLastNameFromList(), generatePostCode())
                .addCustomer(fistNameCustomers.get(2), getLastNameFromList(), generatePostCode())
                .getMenu().clickShowCustomersButton()
                .deleteCustomer(averageLengthName(fistNameCustomers));
    }
}
