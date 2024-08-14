package ru.margarite.volkova.extension;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

public class AllureScreenshotExtension implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        Object instance = extensionContext.getRequiredTestInstance();
        Field field = instance.getClass().getDeclaredField("driver");
        field.setAccessible(true);
        WebDriver driver = ((WebDriver) field.get(instance));

        if (extensionContext.getExecutionException().isPresent()) {
            takeScreenshot(driver);
        }
        driver.quit();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
