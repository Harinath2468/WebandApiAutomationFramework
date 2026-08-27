package sampletests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.GeneralUtils;

import java.io.IOException;
import java.time.Duration;

public class Testrandom {
    WebDriver driver;

    @BeforeClass
    public void launchBrowser() throws IOException {
        WebDriverManager.edgedriver().setup();
        driver =  new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(GeneralUtils.getConfigFile("config", "urlamazon"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.switchTo().newWindow(WindowType.TAB);
        driver.switchTo().newWindow(WindowType.WINDOW);
        ((JavascriptExecutor) driver).executeScript("window.open();");
        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.CONTROL + "t");
    }

    @Test
    public void amazonTest() throws IOException, InterruptedException {
        driver.get("https://www.amazon.in/");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(GeneralUtils.getLocator("accountSignin")))));
        Thread.sleep(3000);
        GeneralUtils.hoverAndClick(driver,By.id(GeneralUtils.getLocator("accountSignin")));
        Thread.sleep(3000);
    }

    @Test
    public void login() {
        try {
            driver.get(GeneralUtils.getConfigFile("config", "url"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            WebElement body = driver.findElement(By.tagName("body"));
            body.sendKeys(Keys.CONTROL + "t");

            driver.findElement(By.name(GeneralUtils.getLocator( "username"))).sendKeys(GeneralUtils
                    .getConfigFile("config", "username"));
            driver.findElement(By.name(GeneralUtils.getLocator("password"))).sendKeys(GeneralUtils
                    .getConfigFile("config", "password"));


            driver.findElement(By.xpath(GeneralUtils.getLocator("login"))).click();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test(dependsOnMethods = "login")
    public void aadUser() throws IOException, InterruptedException {
        driver.findElement(By.xpath(GeneralUtils.getLocator("timesheets"))).click();
        GeneralUtils.waitForElementTobeVisible(driver,"adminMenu.button").click();
        driver.findElement(By.xpath(GeneralUtils.getLocator("adminMenu.button"))).click();
        driver.findElement(By.xpath(GeneralUtils.getLocator("addUser.button"))).click();
        driver.findElement(By.xpath(GeneralUtils.getLocator( "userRole"))).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ess = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(GeneralUtils.getLocator("ESS"))));
        ess.click();
        WebElement employeeName = driver.findElement(By.xpath(GeneralUtils.getLocator( "employeeName.input")));
                employeeName.sendKeys("john");
        wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//*[contains(normalize-space(),'john  ')]")));
        GeneralUtils.hoverAndClick(driver,By.xpath("//*[contains(normalize-space(),'john doe')]"));


    }

    @Test
    public void searchAmazon() throws IOException, InterruptedException {

        WebElement ele = driver.findElement(By.xpath(GeneralUtils.getLocator("searchbox")));
        ele.sendKeys("iphone");
        ele.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath(GeneralUtils.getLocator("ratings")))));

        GeneralUtils.hoverAndClick(driver,By.xpath(GeneralUtils.getLocator("ratings")));
Thread.sleep(3000);
        WebElement ratings = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GeneralUtils.getLocator("customerReview"))));
        ratings.click();
    }

    @AfterClass
    public void teardown() throws InterruptedException {
        driver.quit();
    }
}
