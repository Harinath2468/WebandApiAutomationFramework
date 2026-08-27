package sampletests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Playwrighttest {

    Playwright pw;
    Browser browser;

    @Test
    public void launchBrowser(){

         pw = Playwright.create();
         BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500);
         browser = pw.chromium().launch(options);
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://freelance-learn-automation.vercel.app/login");

        page.getByText("New user? Signup").click();
        Assert.assertEquals(page.locator("xpath=//form[@class='signup-form']//h2") .innerText(), "Sign Up");



    }
}
