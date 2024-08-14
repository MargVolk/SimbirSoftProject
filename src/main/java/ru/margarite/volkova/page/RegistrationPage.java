package ru.margarite.volkova.page;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.margarite.volkova.AbstractPage;
import ru.margarite.volkova.data.Student;

import java.time.Duration;
import java.util.List;

import static ru.margarite.volkova.ElementMethods.*;

public class RegistrationPage extends AbstractPage<RegistrationPage> {
    WebDriver driver;


    @FindBy(xpath = "//div[contains(@class, 'practice-form')]/h5")
    private WebElement titleForm;
    @FindBy(id = "firstName")
    private WebElement nameInput;
    @FindBy(css = "#lastName")
    private WebElement lastNameInput;
    @FindBy(css = "#userEmail")
    private WebElement emailInput;
    @FindAll(@FindBy(name = "gender"))
    private List<WebElement> genderRadios;
    @FindBy(xpath = "//input[@id='userNumber']")
    private WebElement phoneInput;
    @FindBy(xpath = "//input[@id='dateOfBirthInput']")
    private WebElement birthdayInput;
    @FindBy(xpath = "//input[@id='subjectsInput']")
    private WebElement subjectsSelect;
    @FindBy(id = "uploadPicture")
    private WebElement uploadPictureButton;
    @FindBy(id = "currentAddress")
    private WebElement addressInput;
    @FindBy(xpath = "//input[@id='react-select-3-input']")
    private WebElement stateSelect;
    @FindBy(id = "react-select-4-input")
    private WebElement citySelect;
    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement thanksModal;


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Регистрация студента {student}")
    public RegistrationPage registration(Student student) {
        inputText(student.getFirstName(), nameInput);
        inputText(student.getLastName(), lastNameInput);
        inputText(student.getEmail(), emailInput);
        radio(student.getGender(), driver, genderRadios);
        inputText(student.getPhone(), phoneInput);
        setDate(student.getBithdayDate(), driver, birthdayInput);
        selectWithSearch(student.getSubjects(), driver, subjectsSelect);
        checkbox(student.getHobbies(), driver);
        uploadFile(student.getPicture(), uploadPictureButton);
        inputText(student.getAddress(), addressInput);
        select(student.getState(), driver, stateSelect);
        select(student.getCity(), driver, citySelect);
        clickButton(submitButton);
        return this;
    }

    @Step("Модальное окно открылось")
    public ThanksModal checkOpenThanksModal() {
        boolean exist = waitVisible(driver, thanksModal) != null;
        Assertions.assertTrue(exist);
        return new ThanksModal(driver);
    }

    @Override
    @Step("Проверка, что форма регистрации прогружена")
    public RegistrationPage checkPageLoaded() {
        waitVisible(driver, titleForm);
        return this;
    }
}
