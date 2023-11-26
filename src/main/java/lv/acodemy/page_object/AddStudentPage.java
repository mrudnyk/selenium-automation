package lv.acodemy.page_object;

import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;


public class AddStudentPage {

    ChromeDriver driver = LocalDriverManager.getInstance();
    WebDriverWait waiter;

    public AddStudentPage(ChromeDriver driver, WebDriverWait waiter) {
        this.driver = driver;
        this.waiter = waiter;
    }

    private final By nameField = By.id("name");
    private final By emailField = By.id("email");
    private final By submitButton = By.xpath("//span[text()='Submit']//parent::button");
    private final By errorMessageName = By.id("name_help");
    private final By errorMessageEmail = By.id("email_help");
    private final By errorMessageGender = By.id("gender_help");

    public void setNameField (String input) {
        driver.findElement(nameField).sendKeys();
    }

    public void setEmailField (String input) {
        driver.findElement(emailField).sendKeys();
    }

    public void setGender (String genderValue) {
        driver.findElement(By.id("gender")).click();
        driver.findElement(
                By.xpath(String.format("//div[@class='rc-virtual-list-holder-inner']//div[text()='%s']", genderValue.toUpperCase()))).click();
    }

    public void clickSubmitButton () {
        driver.findElement(submitButton).click();
    }

    public WebElement getErrorMessageNameLocator() {
        return driver.findElement(errorMessageName);
    }

    public String getErrorMessageNameText() {
        waiter.until(textToBePresentInElement(getErrorMessageNameLocator(), "Please enter student name"));
        return getErrorMessageNameLocator().getText();
    }

    public WebElement getErrorMessageEmailLocator() {
        return driver.findElement(errorMessageEmail);
    }

    public String getErrorMessageEmailText() {
        waiter.until(textToBePresentInElement(getErrorMessageEmailLocator(), "Please enter student email"));
        return getErrorMessageEmailLocator().getText();
    }

    public WebElement getErrorMessageGenderLocator() {
        return driver.findElement(errorMessageGender);
    }

    public String getErrorMessageGenderText() {
        waiter.until(textToBePresentInElement(getErrorMessageGenderLocator(), "Please select a gender"));
        return getErrorMessageGenderLocator().getText();
    }

    public void getErrorTextGender() {
        driver.findElement(By.id("gender_help"));
    }


}
