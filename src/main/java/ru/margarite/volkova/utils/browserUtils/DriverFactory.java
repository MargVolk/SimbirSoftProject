package ru.margarite.volkova.utils.browserUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver(Browser browserType) {
        WebDriver webDriver;

        switch (browserType) {
            case EDGE:
                webDriver = new EdgeDriver();
                break;
            case CHROME:
                webDriver = new ChromeDriver();
                break;
            case FIREFOX:
                webDriver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Неизвестный тип браузера");
        }
        webDriver.manage().window().maximize();
        return webDriver;
    }

}