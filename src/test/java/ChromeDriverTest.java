import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ChromeDriverTest {

    ChromeDriver driver = new ChromeDriver();


    @Test
    public void chromeDriverTest() {
        driver.get("https://google.lv");
        WebElement acceptButton = driver.findElement(By.xpath("//div[text()='Принять все']//parent::button"));
        acceptButton.click();
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Riga Acodemy Testing");
        searchField.sendKeys(Keys.ENTER);
        driver.close();
        driver.quit();
    }

    @AfterMethod
    public void tearDown () {
        driver.close();
        driver.quit();
    }

}
