import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static lv.acodemy.utils.ConfigurationProperties.getConfiguration;

public class SauceDemoTest {

    ChromeDriver driver = new ChromeDriver();

    private final String APP_URL = "https://www.saucedemo.com/v1/";

    WebDriverWait wait = new WebDriverWait(driver,
            ofSeconds(getConfiguration().getLong("wait.time")));


    @Test
    public void sauceDemoValid () {

        driver.get(getConfiguration().getString("sauce.url"));
        driver.manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));

        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("performance_glitch_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();




        System.out.println();


    }

    @AfterMethod
    public void tearDown1() {
        driver.close();
        driver.quit();
    }


    @Test
    public void sauceDemoInvalid () {
        driver.get(getConfiguration().getString("sauce.url"));
        driver.manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));

        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("user1_not_exist");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("qwerty123");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        wait.until(ExpectedConditions.textToBePresentInElement(errorMessage, "Epic sadface: Username and password do not match any user in this service"));
        Assertions.assertThat(errorMessage.getText()).isEqualTo("Epic sadface: Username and password do not match any user in this service");

        System.out.println();
    }

    @AfterMethod
    public void tearDown2() {
        driver.close();
        driver.quit();
    }



}
