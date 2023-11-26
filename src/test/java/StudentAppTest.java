import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import lv.acodemy.page_object.AddStudentPage;
import lv.acodemy.page_object.MainPage;
import lv.acodemy.page_object.Notification;
import lv.acodemy.utils.LocalDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static lv.acodemy.utils.ConfigurationProperties.getConfiguration;

@Slf4j
public class StudentAppTest {

    ChromeDriver driver;
    Faker fakeData;
    WebDriverWait wait;
    MainPage mainPage;
    AddStudentPage addStudentPage;
    Notification notifications;

    @BeforeMethod
    public void beforeTest() {
        driver = LocalDriverManager.getInstance();
        wait = new WebDriverWait(driver, ofSeconds(getConfiguration().getLong("wait.time")));
        fakeData = new Faker();
        mainPage = new MainPage();
        addStudentPage = new AddStudentPage();
        notifications = new Notification(wait);
    }

    @Test
    public void createStudentTest () {
        LocalDriverManager.getInstance().manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
        //logger.info ("Will open now " + getConfiguration().getString("app.url"));
        LocalDriverManager.getInstance().get(getConfiguration().getString("app.url"));
        mainPage.openAddStudentForm();

        addStudentPage.setNameField(fakeData.name().fullName());
        addStudentPage.setEmailField(fakeData.internet().emailAddress());
        addStudentPage.setGender("Female");
        addStudentPage.clickSubmitButton();

        Assertions.assertThat(notifications.getNotificationSuccessMessage()).isEqualTo("Student successfully added");

        System.out.println();

    }

    @AfterMethod
    public void tearDown1() {
        LocalDriverManager.closeDriver();
    }

    @Test
    public void checkEmptyFieldsError() {
        LocalDriverManager.getInstance().manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
        //logger.info ("Will open now " + getConfiguration().getString("app.url"));
        LocalDriverManager.getInstance().get(getConfiguration().getString("app.url"));
        mainPage.openAddStudentForm();

        addStudentPage.clickSubmitButton();

        Assertions.assertThat(addStudentPage.getErrorMessageNameText()).isEqualTo("Please enter student name");
        Assertions.assertThat(addStudentPage.getErrorMessageEmailText()).isEqualTo("Please enter student email");
        Assertions.assertThat(addStudentPage.getErrorMessageGenderText()).isEqualTo("Please select a gender");
    }

    @AfterMethod
    public void tearDown2() {
        LocalDriverManager.closeDriver();
    }

}
