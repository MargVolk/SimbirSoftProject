package ru.margarite.volkova.tests;

import org.junit.jupiter.api.*;
import ru.margarite.volkova.BaseTest;
import ru.margarite.volkova.pages.AddCustomerForm;
import ru.margarite.volkova.pages.BankManagerPage;
import ru.margarite.volkova.pages.CustomersTable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static ru.margarite.volkova.helpers.DataUtils.generateCustomerData;

public class SortCustomersTests extends BaseTest {
    List<Map<String, String>> randomData;
    CustomersTable customersTable;

    @DisplayName("Создание клиентов")
    @BeforeEach
    public void createCustomersForSortTests() {
        randomData = generateCustomerData(5);
        AddCustomerForm addCustomerForm = new BankManagerPage(driver)
                .getMenu()
                .clickAddCustomerButton();
        for (Map<String, String> customer : randomData) {
            addCustomerForm.addCustomer(customer.get("firstName"), customer.get("lastName"), customer.get("postCode"));
        }
    }

    @DisplayName("Сортировка по возрастанию First Name")
    @Test
    public void sortFirstNameInAsc() {
        customersTable = new BankManagerPage(driver)
                .getMenu()
                .clickShowCustomersButton();
        List<String> actualNames = customersTable
                .doubleClickFirstName()
                .parseDataColumn(driver, "First Name");

        Assertions.assertLinesMatch(actualNames.stream().sorted(String::compareToIgnoreCase).toList(), actualNames,
                "Данные неправильно отсортированы. Ожидаемое: " +
                        actualNames.stream().sorted(String::compareToIgnoreCase).toList() +
                        " Актуальное: " + actualNames);
    }

    @DisplayName("Сортировка по убыванию First Name")
    @Test
    public void sortFirstNameInDesc() {
        customersTable = new BankManagerPage(driver)
                .getMenu()
                .clickShowCustomersButton();

        List<String> actualNames = customersTable
                .clickFirstName()
                .parseDataColumn(driver, "First Name");

        Assertions.assertLinesMatch(actualNames.stream().sorted(Collections.reverseOrder(String::compareToIgnoreCase))
                .toList(), actualNames, "Данные неправильно отсортированы. Ожидаемое: " +
                actualNames.stream().sorted(Collections.reverseOrder(String::compareToIgnoreCase)).toList() +
                " Актуальное: " + actualNames);
    }

    @DisplayName("Удаление клиентов")
    @AfterEach
    public void deleteCustomers() {
        for (Map<String, String> customer : randomData) {
            customersTable.deleteCustomer(customer.get("firstName"));
        }
    }
}
