package tests;

import database.UserData;
import drivers.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Main extends ChromeDriver {
    @Test(priority = 1)
    public void US1CreateUserAccount() {
        driver.get("https://demowebshop.tricentis.com/");

        WebElement register=driver.findElement(By.xpath("//a[text()='Register']"));
        register.click();
        WebElement gender=driver.findElement(By.id("gender-male"));
        gender.click();

        WebElement firstName=driver.findElement(By.id("FirstName"));
        firstName.sendKeys(UserData.firstname);

        WebElement lastName=driver.findElement(By.id("LastName"));
        lastName.sendKeys(UserData.lastname);

        WebElement email=driver.findElement(By.id("Email"));
        email.sendKeys(UserData.email);

        WebElement password=driver.findElement(By.id("Password"));
        password.sendKeys(UserData.password);

        WebElement confirmPass=driver.findElement(By.id("ConfirmPassword"));
        confirmPass.sendKeys(UserData.password);

        WebElement registerButton=driver.findElement(By.id("register-button"));
        registerButton.click();

        WebElement control=driver.findElement(By.xpath("//div[@class='result']"));
        Assert.assertTrue(control.getText().contains("Your registration completed"),"your registration is not complete");

        WebElement logoutBtn = driver.findElement(By.className("ico-logout"));
        logoutBtn.click();

    }

    @Test(priority = 2)
    public void US2CreateNegativeUserAccount() {
        driver.get("https://demowebshop.tricentis.com/");

        WebElement register=driver.findElement(By.xpath("//a[text()='Register']"));
        register.click();
        WebElement gender=driver.findElement(By.id("gender-male"));
        gender.click();

        WebElement firstName=driver.findElement(By.id("FirstName"));
        firstName.sendKeys(UserData.firstname);

        WebElement lastName=driver.findElement(By.id("LastName"));
        lastName.sendKeys(UserData.lastname);

        WebElement email=driver.findElement(By.id("Email"));
        email.sendKeys(UserData.email);

        WebElement password=driver.findElement(By.id("Password"));
        password.sendKeys(UserData.password);

        WebElement confirmPass=driver.findElement(By.id("ConfirmPassword"));
        confirmPass.sendKeys(UserData.password);

        WebElement registerButton=driver.findElement(By.id("register-button"));
        registerButton.click();

        WebElement control=driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li"));
        Assert.assertTrue(control.getText().contains("The specified email already exists"),"The specified email does not already exist");
    }

    @Test(priority = 10)
    public void US3Logout() {

    }

    @Test(priority = 6)
    public void US4Login() {
        driver.get("https://demowebshop.tricentis.com/");

        WebElement loginLink = driver.findElement(By.xpath("//a[text()='Log in']"));
        loginLink.click();

        WebElement email    = driver.findElement(By.id("Email"));
        WebElement password = driver.findElement(By.id("Password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("input[type='submit'][value='Log in']"));

        email.sendKeys(UserData.email);
        password.sendKeys(UserData.password);
        loginBtn.click();

        WebElement accountInfoField = driver.findElement(By.cssSelector(".header-links a[href='/customer/info']"));

        Assert.assertTrue(accountInfoField.getText().equals(UserData.email));
    }

    @Test(priority = 3)
    public void US5NegativeLogin() {
        driver.get("https://demowebshop.tricentis.com/");

        WebElement loginLink = driver.findElement(By.xpath("//a[text()='Log in']"));
        loginLink.click();

        WebElement email    = driver.findElement(By.id("Email"));
        WebElement password = driver.findElement(By.id("Password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("input[type='submit'][value='Log in']"));

        email.sendKeys("");
        password.sendKeys("");
        loginBtn.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".message-error > div > span")));
        WebElement responseMsg = driver.findElement(By.cssSelector(".message-error > div > span"));

        Assert.assertTrue(responseMsg.getText().contains("unsuccessful"));
    }

    @Test(priority = 4)
    public void US5NegativeLogin2() {
        driver.get("https://demowebshop.tricentis.com/");

        WebElement loginLink = driver.findElement(By.xpath("//a[text()='Log in']"));
        loginLink.click();

        WebElement email    = driver.findElement(By.id("Email"));
        WebElement password = driver.findElement(By.id("Password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("input[type='submit'][value='Log in']"));

        email.sendKeys(UserData.email);
        password.sendKeys("incorrectpassword");
        loginBtn.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".message-error > div > span")));
        WebElement responseMsg = driver.findElement(By.cssSelector(".message-error > div > span"));

        Assert.assertTrue(responseMsg.getText().contains("unsuccessful"));
    }

    @Test(priority = 5)
    public void US5NegativeLogin3() {
        driver.get("https://demowebshop.tricentis.com/");

        WebElement loginLink = driver.findElement(By.xpath("//a[text()='Log in']"));
        loginLink.click();

        WebElement email    = driver.findElement(By.id("Email"));
        WebElement password = driver.findElement(By.id("Password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("input[type='submit'][value='Log in']"));

        email.sendKeys("incorrectmail@gmail.com");
        password.sendKeys(UserData.password);
        loginBtn.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".message-error > div > span")));
        WebElement responseMsg = driver.findElement(By.cssSelector(".message-error > div > span"));

        Assert.assertTrue(responseMsg.getText().contains("unsuccessful"));
    }

    @Test(priority = 7)
    public void US6Ordering() {

    }

    @Test(priority = 11)
    public void US7SurveyResponse() {

    }

    @Test(priority = 8)
    public void US8DownloadHistory() {

    }

    @Test(priority = 9)
    public void US9UseCouponAndGiftCart() {

    }

    @AfterClass
    public void driverQuit() {
        driver.quit();
    }
}
