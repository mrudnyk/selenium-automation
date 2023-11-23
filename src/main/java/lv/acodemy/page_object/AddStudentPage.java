package lv.acodemy.page_object;

import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class AddStudentPage {

    ChromeDriver driver = LocalDriverManager.getInstance();

    private final By nameField = By.id("name");
    private final By emailField = By.id("email");
    private final By submitButton = By.xpath("//span[text()='Submit']//parent::button");

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


}
