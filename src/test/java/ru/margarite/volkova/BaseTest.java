package ru.margarite.volkova;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @DisplayName("Установка браузера chrome")
    @BeforeEach
    public void setDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
    }

    @DisplayName("Закрытие браузера")
    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
