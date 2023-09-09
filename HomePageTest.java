import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sujat\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");

        WebElement userIdEl = driver.findElement(By.id("userIdInput"));
        userIdEl.sendKeys("142420");

        WebElement pinEl = driver.findElement(By.id("pinInput"));
        pinEl.sendKeys("231225");

        WebElement loginEl = driver.findElement(By.className("login-button"));
        loginEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://qaebank.ccbp.tech/"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://qaebank.ccbp.tech/", "URL do not match");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    @Test(priority = 1)
    public void testHomepageHeading(){
        WebElement headingEl = driver.findElement(By.className("heading"));

        Assert.assertEquals(headingEl.getText(), "Your Flexibility, Our Excellence", "Heading Text does not match");
    }

    @Test(priority = 2)
    public void testLogoutFunctionality(){
        WebElement logOutEl = driver.findElement(By.className("logout-button"));
        logOutEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://qaebank.ccbp.tech/ebank/login"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://qaebank.ccbp.tech/ebank/login", "URLs do not match");

    }
}