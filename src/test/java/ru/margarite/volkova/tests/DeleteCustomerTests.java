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

    @DisplayName("Удаление клиента с длиной имени равной среднему арифметическому")
    @Test
    public void deleteCustomerWithCondition() {
        fistNameCustomers = Arrays.asList("Ah", "Aba", "A");

        new BankManagerPage(driver)
                .clickAddCustomerButton()
                .addCustomer(fistNameCustomers.get(0), getRandomLastName(), generatePostCode())
                .addCustomer(fistNameCustomers.get(1), getRandomLastName(), generatePostCode())
                .addCustomer(fistNameCustomers.get(2), getRandomLastName(), generatePostCode())
                .getMenu().clickShowCustomersButton()
                .deleteCustomer(averageLengthName(fistNameCustomers));
    }

    @DisplayName("Удаление нескольких клиентов подходящих под условие")
    @Test
    public void deleteCustomersWithSameLength() {
        fistNameCustomers = Arrays.asList("Albus", "Neville", "Voldemort", "Margota");

        new BankManagerPage(driver)
                .clickAddCustomerButton()
                .addCustomer(fistNameCustomers.get(0), getRandomLastName(), generatePostCode())
                .addCustomer(fistNameCustomers.get(1), getRandomLastName(), generatePostCode())
                .addCustomer(fistNameCustomers.get(2), getRandomLastName(), generatePostCode())
                .addCustomer(fistNameCustomers.get(3), getRandomLastName(), generatePostCode())
                .getMenu().clickShowCustomersButton()
                .deleteCustomer(averageLengthName(fistNameCustomers));
    }

    @DisplayName("Удаление клиента с наиболее близкой длиной имени к среднему арифметическому")
    @Test
    public void deleteCustomerWithLengthCloserToAverage() {
        fistNameCustomers = Arrays.asList("Kirill", "Margo", "Ellisa"); //

        new BankManagerPage(driver)
                .clickAddCustomerButton()
                .addCustomer(fistNameCustomers.get(0), getRandomLastName(), generatePostCode())
                .addCustomer(fistNameCustomers.get(1), getRandomLastName(), generatePostCode())
                .addCustomer(fistNameCustomers.get(2), getRandomLastName(), generatePostCode())
                .getMenu().clickShowCustomersButton()
                .deleteCustomer(averageLengthName(fistNameCustomers));
    }
}
