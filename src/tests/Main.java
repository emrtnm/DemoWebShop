package tests;

import drivers.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Main extends ChromeDriver {
    @BeforeClass
    public void init() {
        driver.get("https://demowebshop.tricentis.com/");
    }

    @Test
    public void US1CreateUserAccount() {

    }

    @Test
    public void US2CreateNegativeUserAccount() {

    }

    @Test
    public void US3Logout() {

    }

    @Test
    public void US4Login() {

    }

    @Test
    public void US5NegativeLogin() {

    }

    @Test
    public void US6Ordering() {

    }

    @Test
    public void US7SurveyResponse() {

    }

    @Test
    public void US8DownloadHistory() {

    }

    @Test
    public void US9UseCouponAndGiftCart() {

    }

    @AfterClass
    public void driverQuit() {
        driver.quit();
    }
}
