package ru.margarite.volkova.page;

import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.margarite.volkova.utils.dateUtils.DateParse;
import ru.margarite.volkova.data.Student;

import java.util.List;

import static ru.margarite.volkova.ElementMethods.*;

public class ThanksModal {
    WebDriver driver;

    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement titleModal;
    @FindAll(@FindBy(xpath = "//tr/td[2]"))
    private List<WebElement> values;


    public ThanksModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @Step("Заголовок модального окна - \"Thanks for submitting the form\"")
    public ThanksModal checkTitle() {
        checkText("Thanks for submitting the form", titleModal);
        return this;
    }

    @Step("Проверка значений в модальном окне")
    public void checkValues(Student student) {
        checkText(student.getFirstName() + " " + student.getLastName(), values.get(0));
        checkText(student.getEmail(), values.get(1));
        checkText(student.getGender(), values.get(2));
        checkText(student.getPhone(), values.get(3));
        checkText(new DateParse(student.getBithdayDate()).getDateWithFormat("dd MMMM,yyyy"), values.get(4));
        checkText(student.getSubjects(), values.get(5));
        checkText(StringUtils.join(student.getHobbies(), ", "), values.get(6));
        checkText(student.getPicture().getName(), values.get(7));
        checkText(student.getAddress(), values.get(8));
        checkText(student.getState() + " " + student.getCity(), values.get(9));
    }
}
