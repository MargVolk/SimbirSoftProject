package ru.margarite.volkova;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import ru.margarite.volkova.data.Student;
import ru.margarite.volkova.extension.AllureScreenshotExtension;
import ru.margarite.volkova.page.RegistrationPage;
import ru.margarite.volkova.utils.browserUtils.DriverFactory;

import java.util.List;

import static ru.margarite.volkova.utils.browserUtils.Browser.CHROME;

@ExtendWith(AllureScreenshotExtension.class)
public class RegistrationTests {
    Student student;
    WebDriver driver;

    @BeforeEach
    @DisplayName("Установка браузера")
    public void browser() {
        driver = DriverFactory.createDriver(CHROME);
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registration(){
        student = new Student("Margarita", "Volkova", "test@mail.ru", "Female",
                "8888888888", "07.07.2020", "English", List.of("Sports", "Music"),
                "studentPhoto.png", "address", "Haryana", "Karnal");

        new RegistrationPage(driver)
                .checkPageLoaded()
                .registration(student)
                .checkOpenThanksModal()
                .checkTitle()
                .checkValues(student);
    }
}
