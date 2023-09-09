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

public class LoginPageTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sujat\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    @Test(priority = 1)
    public void testLoginWithEmptyInputs(){
        WebElement loginEl = driver.findElement(By.className("login-button"));
        loginEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errMsgEl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));

        Assert.assertEquals(errMsgEl.getText(), "Invalid user ID", "Error text with empty input fields does not match");
    }

    @Test(priority = 2)
    public void testLoginWithEmptyUserId(){
        WebElement pinEl = driver.findElement(By.id("pinInput"));
        pinEl.sendKeys("231225");

        WebElement loginEl = driver.findElement(By.className("login-button"));
        loginEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errMsgEl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));

        Assert.assertEquals(errMsgEl.getText(), "Invalid user ID", "Error text with empty user ID field do not match");
    }

    @Test(priority = 3)
    public void testLoginWithEmptyPin(){
        WebElement userIdEl = driver.findElement(By.id("userIdInput"));
        userIdEl.sendKeys("142420");

        WebElement loginEl = driver.findElement(By.className("login-button"));
        loginEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errMsgEl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));

        Assert.assertEquals(errMsgEl.getText(), "Invalid PIN", "Error text with empty PIN input field do not match");
    }

    @Test(priority = 4)
    public void testLoginWithInvalidCreds(){
        WebElement userIdEl = driver.findElement(By.id("userIdInput"));
        userIdEl.sendKeys("142420");

        WebElement pinEl = driver.findElement(By.id("pinInput"));
        pinEl.sendKeys("123456");

        WebElement loginEl = driver.findElement(By.className("login-button"));
        loginEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errMsgEl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-text")));

        Assert.assertEquals(errMsgEl.getText(), "User ID and PIN didn't match", "Error text with invalid PIN do not match");
    }

    @Test(priority = 5)
    public void testLoginWithValidCreds(){
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


}

