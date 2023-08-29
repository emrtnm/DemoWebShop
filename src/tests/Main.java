package tests;

import drivers.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

        WebElement register=driver.findElement(By.xpath("//a[text()='Register']"));
        register.click();
        WebElement gender=driver.findElement(By.id("gender-male"));
        gender.click();

        WebElement firstName=driver.findElement(By.id("FirstName"));
        firstName.sendKeys("ali2");

        WebElement lastName=driver.findElement(By.id("LastName"));
        lastName.sendKeys("veli");
        WebElement email=driver.findElement(By.id("Email"));
        email.sendKeys("ali3veli@gmail.com");

        WebElement password=driver.findElement(By.id("Password"));
        password.sendKeys("aliveli123");

        WebElement confirmPass=driver.findElement(By.id("ConfirmPassword"));
        confirmPass.sendKeys("aliveli123");

        WebElement registerButton=driver.findElement(By.id("register-button"));
        registerButton.click();

        WebElement control=driver.findElement(By.xpath("//div[@class='result']"));
        Assert.assertTrue(control.getText().contains("Your registration completed"),"your registration is not complete");

    }

    @Test
    public void US2CreateNegativeUserAccount() {
        driver.get("https://demowebshop.tricentis.com/");

        WebElement register=driver.findElement(By.xpath("//a[text()='Register']"));
        register.click();
        WebElement gender=driver.findElement(By.id("gender-male"));
        gender.click();

        WebElement firstName=driver.findElement(By.id("FirstName"));
        firstName.sendKeys("ali2");

        WebElement lastName=driver.findElement(By.id("LastName"));
        lastName.sendKeys("veli");
        WebElement email=driver.findElement(By.id("Email"));
        email.sendKeys("ali3veli@gmail.com");

        WebElement password=driver.findElement(By.id("Password"));
        password.sendKeys("aliveli123");

        WebElement confirmPass=driver.findElement(By.id("ConfirmPassword"));
        confirmPass.sendKeys("aliveli123");

        WebElement registerButton=driver.findElement(By.id("register-button"));
        registerButton.click();

        WebElement control=driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li"));
        Assert.assertTrue(control.getText().contains("The specified email already exists"),"The specified email does not already exist");
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
