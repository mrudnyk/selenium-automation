import com.github.javafaker.Faker;
import lv.acodemy.page_object.AddStudentPage;
import lv.acodemy.page_object.MainPage;
import lv.acodemy.page_object.Notification;
import lv.acodemy.utils.LocalDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static lv.acodemy.utils.ConfigurationProperties.getConfiguration;

public class StudentAppTest {


    WebDriverWait wait = new WebDriverWait(LocalDriverManager.getInstance(),
            ofSeconds(getConfiguration().getLong("wait.time")));


    Faker fakeData = new Faker();

    private final String APP_URL = "http://acodemy-app-springboot-env.eba-pagku2yg.eu-north-1.elasticbeanstalk.com/";
    MainPage mainPage = new MainPage();

    AddStudentPage addStudentPage = new AddStudentPage();

    Notification notification = new Notification(LocalDriverManager.getInstance(), wait);

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


        Assertions.assertThat(notification.getNotificationSuccessMessage()).isEqualTo("Student successfully added");

        System.out.println();

    }

    @AfterMethod
    public void tearDown() {
        LocalDriverManager.closeDriver();
    }

}
