package tests;

import database.UserData;
import drivers.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
        //SHOPPING CARD TESTCASE1 :
        driver.get("https://demowebshop.tricentis.com/");
        driver.findElement(By.linkText("Log in")).click();
        WebElement email=driver.findElement(By.id("Email"));
        email.sendKeys("mehmet_yilmaz8@gmail.com");
        WebElement pass=driver.findElement(By.id("Password"));
        pass.sendKeys("mehmet_yilmaz8");
        driver.findElement(By.id("RememberMe")).click();
        driver.findElement(By.cssSelector(".button-1[type='submit'][value='Log in']")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        WebElement computers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//ul[@class='top-menu']//li/a[@href='/computers']")));
        actions.moveToElement(computers).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Notebooks")));
        WebElement notebook = driver.findElement(By.linkText("Notebooks"));
        notebook.click();

        WebElement addToCardButton = driver.findElement(By.cssSelector("[type='button'][class='button-2 product-box-add-to-cart-button']"));
        addToCardButton.click();

        WebElement EmptyshoppingCard = driver.findElement(By.xpath("//span[text()='Shopping cart']"));
        EmptyshoppingCard.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("removefromcart")));
        //update shopping card

        WebElement removeFromCard= driver.findElement(By.name("removefromcart"));
        removeFromCard.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("updatecart")));
        driver.findElement(By.name("updatecart")).click();

        String expected = "Your Shopping Cart is empty!";
        WebElement actual = driver.findElement(By.xpath("//div[@class='order-summary-content']"));
        System.out.println("actual.getText() = " + actual.getText());

        Assert.assertTrue( actual.getText().equals(expected),"Siparis order is not empty");



        //NEGATIVE COUPON CARD TESTCASE2

        driver.navigate().to("https://demowebshop.tricentis.com/");


        WebElement computersp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//ul[@class='top-menu']//li/a[@href='/computers']")));
        actions.moveToElement(computersp).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Notebooks")));
        WebElement notebook2 = driver.findElement(By.linkText("Notebooks"));
        notebook2.click();


        WebElement addToCardButtonn = driver.findElement(By.cssSelector("[type='button'][class='button-2 product-box-add-to-cart-button']"));
        addToCardButtonn.click();


        //wait.until(ExpectedConditions.textToBe(By.cssSelector("p[class*='content']"),"The product has been added to your"));

        WebElement addedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class*='content']")));

        System.out.println("Added message= " + addedMessage.getText());
        String actualMessage = "The product has been added to your shopping cart";
        Assert.assertTrue(addedMessage.getText().equals(actualMessage),"Product added has been failed");
        driver.findElement(By.linkText("Shopping cart")).click();
        wait.until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/cart"));

        WebElement coupon = driver.findElement(By.name("discountcouponcode"));
        coupon.sendKeys("");
        driver.findElement(By.name("applydiscountcouponcode")).click();

        WebElement couponMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='message']")));
        System.out.println("Coupon Message= " + couponMessage.getText());
        Assert.assertTrue( couponMessage.getText().contains("couldn't"),"Coupon has been successfully added");





        //NEGATIVE GIFTCARD TEST3

        driver.navigate().to("https://demowebshop.tricentis.com/");
        driver.navigate().refresh();

        WebElement computersp2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//ul[@class='top-menu']//li/a[@href='/computers']")));
        actions.moveToElement(computersp2).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Notebooks")));
        driver.findElement(By.linkText("Notebooks")).click();



        WebElement addToCardButton2 = driver.findElement(By.cssSelector("[type='button'][class='button-2 product-box-add-to-cart-button']"));
        js.executeScript("arguments[0].scrollIntoView(true);", addToCardButton2);
        js.executeScript("arguments[0].click();", addToCardButton2);

         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class*='content']")));
        driver.findElement(By.linkText("Shopping cart")).click();
        wait.until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/cart"));
        driver.findElement(By.xpath("//div//ul[@class='top-menu']//li/a[@href='/gift-cards']")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='button-2 product-box-add-to-cart-button'])[1]")));
        driver.findElement(By.xpath("(//*[@class='button-2 product-box-add-to-cart-button'])[1]")).click();

        WebElement addtocardd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='button-1 add-to-cart-button'][data-productid='1']")));

        js.executeScript("arguments[0].scrollIntoView(true);", addtocardd);
        js.executeScript("arguments[0].click();", addtocardd);
        //addtocard.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class='content'])[1]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class='content'])[2]")));

        System.out.println(wait.until(ExpectedConditions.textToBe(By.xpath("(//p[@class='content'])[1]"), "Enter valid recipient name")));
        System.out.println(wait.until(ExpectedConditions.textToBe(By.xpath("(//p[@class='content'])[2]"), "Enter valid recipient email")));


        WebElement cardMessage = driver.findElement(By.xpath("(//p[@class='content'])[1]"));
        System.out.println("cardMessage = " + cardMessage.getText());
        WebElement cardMessage2 = driver.findElement(By.xpath("(//p[@class='content'])[2]"));
        System.out.println("cardMessage2 = " + cardMessage2.getText());

        Assert.assertTrue( cardMessage.getText().contains("name"),"Gift Card added successfully");
        Assert.assertTrue( cardMessage2.getText().contains("email"),"Gift Card added successfully");


        driver.findElement(By.linkText("Shopping cart")).click();

        WebElement giftBox = driver.findElement(By.name("giftcardcouponcode"));
        giftBox.sendKeys("");
        driver.findElement(By.name("applygiftcardcouponcode")).click();
        String giftMessage = "The coupon code you entered couldn't be applied to your order";
        WebElement giftboxMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='message']")));
        System.out.println("Gift Card is  not Added Message= " + giftboxMessage.getText());
        Assert.assertTrue( giftboxMessage.getText().contains("couldn't"),"Coupon has been successfully added");




        //PAYMENT POSITIVE TESTCASE4

        driver.navigate().to("https://demowebshop.tricentis.com/");



        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//ul[@class='top-menu']//li/a[@href='/computers']")));
        new Actions(driver).moveToElement(computers).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Notebooks")));
         driver.findElement(By.linkText("Notebooks")).click();



        WebElement addToCardButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='button'][class='button-2 product-box-add-to-cart-button']")));
        js.executeScript("arguments[0].scrollIntoView(true);", addToCardButton1);
        js.executeScript("arguments[0].click();", addToCardButton1);


        wait.until(ExpectedConditions.textToBe(By.cssSelector("p[class*='content']"), "The product has been added to your shopping cart"));

        Assert.assertTrue(addedMessage.getText().equals(actualMessage),"Product added has been failed");
        driver.findElement(By.linkText("Shopping cart")).click();
        wait.until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/cart"));

        driver.findElement(By.name("termsofservice")).click();


        driver.findElement(By.xpath("//button[@id='checkout']")).click();


        //billing_address_id
        WebElement newA = driver.findElement(By.name("billing_address_id"));
        Select sel = new Select(newA);
        sel.selectByVisibleText("New Address");

        //BILLING INFO
        driver.findElement(By.id("BillingNewAddress_Company")).sendKeys("Techno Study");

        WebElement counties = driver.findElement(By.xpath("//select[@data-val-number='The field Country must be a number.']"));
        Select sel1 = new Select(counties);
        sel1.selectByVisibleText("United States");
        WebElement AFA = driver.findElement(By.xpath("//select[@data-val-number='The field State / province must be a number.']"));
        Select sel2 = new Select(AFA);
        sel2.selectByVisibleText("California");


        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Istanbul");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Istanbul/Turkey");
        driver.findElement(By.id("BillingNewAddress_Address2")).sendKeys("Umraniye/Istanbul/Turkey");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("001232");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("03124564323");
        driver.findElement(By.id("BillingNewAddress_FaxNumber")).sendKeys("123456");
        driver.findElement(By.xpath("(//input[@title='Continue'])[1]")).click();


        driver.findElement(By.xpath("(//input[@title='Continue'])[2]")).click();
        driver.findElement(By.xpath("//input[@onclick='ShippingMethod.save()']")).click();
        driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']")).click();
        driver.findElement(By.xpath("//input[@onclick='PaymentInfo.save()']")).click();
        driver.findElement(By.xpath("//input[@onclick='ConfirmOrder.save()']")).click();


        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='title']"), "Your order has been successfully processed!"));

        String successMessage = "Your order has been successfully processed!";
        Assert.assertTrue( successMessage.equals("Your order has been successfully processed!"),"FAILED");

        driver.findElement(By.linkText("Click here for order details.")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));

        //PAYMENT NEGATIVE TESTCASE 5

        driver.navigate().to("https://demowebshop.tricentis.com/");
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//ul[@class='top-menu']//li/a[@href='/computers']")));
        actions.moveToElement(computers).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Notebooks")));
        driver.findElement(By.linkText("Notebooks")).click();


        WebElement addToCardButton3 = driver.findElement(By.cssSelector("[type='button'][class='button-2 product-box-add-to-cart-button']"));

        js.executeScript("arguments[0].scrollIntoView(true);", addToCardButton3);
        js.executeScript("arguments[0].click();", addToCardButton3);


        Assert.assertTrue( addedMessage.getText().equals(actualMessage),"Product added has been failed");
        driver.findElement(By.linkText("Shopping cart")).click();
        wait.until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/cart"));

        driver.findElement(By.name("termsofservice")).click();


        driver.findElement(By.xpath("//button[@id='checkout']")).click();



        //billing_address_id
        WebElement newAD = driver.findElement(By.name("billing_address_id"));
         new Select(newAD).selectByValue("3220970");

        driver.findElement(By.cssSelector("[onclick='Billing.save()']")).click();

        driver.findElement(By.name("PickUpInStore")).click();
        driver.findElement(By.cssSelector("[onclick='Shipping.save()']")).click();



        driver.findElement(By.id("paymentmethod_1")).click();
        driver.findElement(By.cssSelector("[onclick='PaymentMethod.save()']")).click();

        String text1 =
                driver.findElement(By.cssSelector("tr>td> :nth-child(1)")).getText();

        Assert.assertTrue(text1.contains("Check or money"));

        driver.findElement(By.xpath("(//a[@onclick='Checkout.back(); return false;'])[4]")).click();
        driver.findElement(By.id("paymentmethod_2")).click();
        driver.findElement(By.cssSelector("[onclick='PaymentMethod.save()']")).click();
        driver.findElement(By.xpath("(//a[@onclick='Checkout.back(); return false;'])[4]")).click();

        driver.findElement(By.id("paymentmethod_3")).click();
        driver.findElement(By.cssSelector("[onclick='PaymentMethod.save()']")).click();

        driver.findElement(By.id("PurchaseOrderNumber")).sendKeys("");
        driver.findElement(By.cssSelector("[onclick='PaymentInfo.save()']")).click();


        String confirm = driver.findElement(By.xpath("(//span[@class='number'])[6]")).getText();
        System.out.println("confirm = " + confirm);

        Assert.assertTrue(confirm.equals("6"),"it couldn't go to the next page");
    }

   @AfterClass
   public void driverQuit() {
       driver.quit();
   }
}
