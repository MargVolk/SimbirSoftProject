package ru.margarite.volkova.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    protected Header header;

    public BasePage(WebDriver driver) {
        header = new Header(driver);
    }
}
