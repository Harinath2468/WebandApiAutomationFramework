package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class GeneralUtils {

    public static String getConfigFile(String fileName, String attribute) throws IOException {
        Properties prop = new Properties();
        FileInputStream config = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config.properties");
        FileInputStream locators = new FileInputStream(System.getProperty("user.dir")+"/src/test/locators.properties");
        if (fileName.equals("config")){
            prop.load(config);
        }
        else if (fileName.equals("locators")) {
            prop.load(locators);
        }
        return  prop.getProperty(attribute);
    }
    public static String getLocator( String attribute) throws IOException {
        Properties prop = new Properties();
        FileInputStream locators = new FileInputStream(System.getProperty("user.dir")+"/src/test/locators.properties");
        prop.load(locators);
        return  prop.getProperty(attribute);
    }

    public static WebElement waitForElementTobeVisible(WebDriver driver, String attribute) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getLocator(attribute))));
        return option;
    }

    public static void hoverAndClick(WebDriver driver, By locator) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(locator)).click().build().perform();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
