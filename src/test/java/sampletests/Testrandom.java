package sampletests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.GeneralUtils;

import java.io.IOException;
import java.time.Duration;

public class Testrandom {
    WebDriver driver;

    @BeforeClass
    public void launchBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void login() {
        try {
            driver.get(GeneralUtils.getConfigFile("config", "url"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.findElement(By.name(GeneralUtils.getLocator( "username"))).sendKeys(GeneralUtils
                    .getConfigFile("config", "username"));
            driver.findElement(By.name(GeneralUtils.getLocator("password"))).sendKeys(GeneralUtils
                    .getConfigFile("config", "password"));
            driver.findElement(By.xpath(GeneralUtils.getLocator("login"))).click();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void aadUser() throws IOException {
        driver.findElement(By.xpath(GeneralUtils.getLocator("timesheets"))).click();
//        GeneralUtils.waitForElementTobeVisible(driver,"adminMenu.button").click();
        driver.findElement(By.xpath(GeneralUtils.getLocator("adminMenu.button"))).click();
        driver.findElement(By.xpath(GeneralUtils.getLocator("addUser.button"))).click();
        driver.findElement(By.xpath(GeneralUtils.getLocator( "userRole"))).click();
        GeneralUtils.hoverAndClick(driver,GeneralUtils.getLocator("ESS"));

    }

//    @AfterClass
//    public void teardown() throws InterruptedException {
//        driver.quit();
//    }
}
