package ru.margarite.volkova.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.margarite.volkova.elements.Table;

import java.util.List;

import static ru.margarite.volkova.helpers.ElementMethods.*;

public class CustomersTable extends BasePage implements Table {
    private final WebDriver driver;
    private final Menu menu;

    @FindBy(xpath = "//a[contains(text(),'First Name')]")
    private WebElement firstNameHeader;

    @FindBy(xpath = "//a[contains(text(),'Last Name')]")
    private WebElement lastNameHeader;

    @FindBy(xpath = "//a[contains(text(),'Post Code')]")
    private WebElement postCodeHeader;

    @FindBy(xpath = "//input[@ng-model='searchCustomer']")
    private WebElement searchInput;

    @FindBy(xpath = "//td[contains(text(),'Account Number')]")
    private WebElement accountHeader;

    @FindBy(xpath = "//td[contains(text(),'Delete Customer')]")
    private WebElement deleteHeader;

    public CustomersTable(WebDriver driver, Menu menu) {
        super(driver);
        this.driver = driver;
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    //Так как клиентов с одинаковым именем может быть несколько, будем удалять всех.
    private List<WebElement> getRow(String firstName) {
        return driver.findElements(By.xpath("//tr/td[1][text()='" + firstName + "']/parent::tr"));
    }

    @Step("Удаление клиента с именем {firstName}")
    public void deleteCustomer(String firstName) {
        for (WebElement row : getRow(firstName)) {
            clickButton(row.findElement(By.xpath(".//button[contains(text(), 'Delete')]")));
        }
        isNotExist(getRow(firstName));
    }

    @Step("Нажать на столбец First Name в таблице")
    public CustomersTable clickFirstName() {
        clickButton(firstNameHeader);
        return this;
    }

    @Step("Двойное нажатие на столбец First Name в таблице")
    public CustomersTable doubleClickFirstName() {
        doubleClickButton(firstNameHeader);
        return this;
    }

    public Menu getMenu() {
        return menu;
    }

    @Step("Проверка элементов на странице")
    public CustomersTable verifyPage() {
        menu.verifyComponent();

        isClickable(searchInput);
        placeholderShouldNaveText(searchInput, "Search Customer");
        isClickable(firstNameHeader);
        isClickable(lastNameHeader);
        isClickable(postCodeHeader);
        isVisible(accountHeader);
        isVisible(deleteHeader);
        return this;
    }
}
