package sampletests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utils.GeneralUtils;

import java.io.IOException;
import java.time.Duration;

public class Testrandom {
    WebDriver driver;

    @Test
    public void launchBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void login() {
        try {
            driver.get(GeneralUtils.getProperty("config", "url"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.findElement(By.name(GeneralUtils.getProperty("locators", "username"))).sendKeys(GeneralUtils
                    .getProperty("config", "username"));
            driver.findElement(By.name(GeneralUtils.getProperty("locators", "password"))).sendKeys(GeneralUtils
                    .getProperty("config", "password"));
            driver.findElement(By.xpath(GeneralUtils.getProperty("locators", "login"))).click();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void teardown() throws InterruptedException {
        driver.quit();
    }
}
