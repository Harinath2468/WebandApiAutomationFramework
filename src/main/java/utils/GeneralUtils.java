package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GeneralUtils {

    public static String getProperty(String fileName, String attribute) throws IOException {
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

}
