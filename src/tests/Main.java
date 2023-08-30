package tests;

import database.UserData;
import drivers.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

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
        driver.get("https://demowebshop.tricentis.com/");

        WebElement logoutBtn = driver.findElement(By.className("ico-logout"));
        logoutBtn.click();

        List<WebElement> loginBtn = driver.findElements(By.className("ico-login"));

        Assert.assertTrue(loginBtn.size() > 0, "Logout failed");
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
        driver.navigate().to("https://demowebshop.tricentis.com/");

        // 0- The user logs in

        driver.findElement(By.xpath("//*[text()='Log in']")).click();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("alicabbar@gmail.com");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("alicabbar1234");
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
        MyFunc.Wait(2);

        // 1- The user selects "14.1-inch Laptop" product from the product list on the homepage
        // by clicking its image or its link under the image.

        // clicking its image.
        WebElement productSelection01 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[1]/div[4]/div[3]/div/div/div[3]/div[3]/div/div[1]/a/img")));
        productSelection01.click();
        MyFunc.Wait(2);

        WebElement productMessage01 = driver.findElement(By.xpath("//h1[@itemprop='name']"));
        Assert.assertTrue("The product is not '14.1-inch Laptop'",productMessage01.getText().contains("14.1-inch Laptop"));

        // clicking its link under the image.
        driver.navigate().back();
        MyFunc.Wait(2);

        WebElement productSelection02 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='14.1-inch Laptop']")));
        productSelection02.click();
        MyFunc.Wait(2);

        WebElement productMessage02 = driver.findElement(By.xpath("//h1[@itemprop='name']"));
        Assert.assertTrue("The product is not '14.1-inch Laptop'",productMessage02.getText().contains("14.1-inch Laptop"));

        // 2- The user adds the product to the shopping cart by clicking the "Add To Cart" button
        // and confirm the selected product is added into the shopping cart.

        WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"add-to-cart-button-31\"]")));
        addToCartButton.click();
        MyFunc.Wait(2);

        WebElement addToCartMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='The product has been added to your ']")));
        Assert.assertTrue("Product has not been added to shopping cart", addToCartMessage.isDisplayed());

        // 3- The user goes to the shopping cart and selects the appropriate option by clicking
        // "Country" dropbox  "State/province" dropbox and "Zip/postal code" textbox for cargo information.

        driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();
        MyFunc.Wait(2);

        // positive scenario, avaliable address is used for the dropboxes and textbox.
        driver.findElement(By.xpath("//input[@name='estimateshipping']")).click();

        WebElement firstShippingMessage01 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='shipping-option-item']/strong)[1]")));
        Assert.assertTrue("Shipping message is not seen", firstShippingMessage01.getText().contains("Ground"));
        MyFunc.Wait(2);

        // negative scenario, when the country, state and zip/postal code boxes are changed.
        WebElement countryDropbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='CountryId']")));
        new Select(countryDropbox).selectByVisibleText("United States");
        MyFunc.Wait(2);

        WebElement stateDropbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='StateProvinceId']")));
        new Select(stateDropbox).selectByVisibleText("Arizona");
        MyFunc.Wait(2);

        new Robot().keyPress(KeyEvent.VK_TAB);
        new Robot().keyRelease(KeyEvent.VK_TAB);
        MyFunc.Wait(2);
        new Robot().keyPress(KeyEvent.VK_DELETE);
        new Robot().keyRelease(KeyEvent.VK_DELETE);
        MyFunc.Wait(2);

        WebElement zipPostalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ZipPostalCode']")));
        zipPostalCode.sendKeys("85004-1558");
        MyFunc.Wait(2);

        // 4- The user clicks estimate shipping button to see the estimated delivery time of product

        driver.findElement(By.xpath("//input[@name='estimateshipping']")).click();

        WebElement firstShippingMessage02 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='shipping-option-item']/strong)[1]")));
        Assert.assertTrue("Shipping message is not seen", firstShippingMessage02.getText().contains("Ground"));
        MyFunc.Wait(2);

        // 5- The user marks the "agree" confirmation box to accept conditions.

        // negative scenario, the user clicks checkout button without marking the "agree" confirmation box.
        WebElement checkout = driver.findElement(By.xpath("//button[@id='checkout']"));
        checkout.click();
        MyFunc.Wait(2);

        WebElement termsOfServiceMessage = driver.findElement(By.xpath("//button[@role='button']"));
        termsOfServiceMessage.click();
        MyFunc.Wait(2);

        // positive scenario, "agree" confirmation box is marked.
        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
        MyFunc.Wait(2);

        // 6- The user starts payment by clicking the "Checkout" button.

        checkout.click();
        MyFunc.Wait(2);

        // 7- The user selects the avaliable invoice address or enters a new address
        // and clicks the continue button.

        // avaliable invoice address
        WebElement billingAddressDropbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='billing-address-select']")));
        new Select(billingAddressDropbox).selectByIndex(0);
        MyFunc.Wait(2);

        WebElement billingSaveContinueButton01 = driver.findElement(By.xpath("//input[@onclick='Billing.save()']"));
        billingSaveContinueButton01.click();
        MyFunc.Wait(2);

        WebElement shippingBackButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='shipping-buttons-container']/p/a")));
        shippingBackButton.click();
        MyFunc.Wait(2);

        // enters a new address

        // negative scenario, all necessary fields are empty.
        new Select(billingAddressDropbox).selectByIndex(1);
        MyFunc.Wait(2);

        WebElement billingSaveContinueButton02 = driver.findElement(By.xpath("//input[@onclick='Billing.save()']"));
        billingSaveContinueButton02.click();
        MyFunc.Wait(2);

        WebElement oneOfNecessaryAddressElements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Country is required.']")));
        Assert.assertTrue("Required field is entered !",oneOfNecessaryAddressElements.isDisplayed());

        // positive scenario, all necessary fields are filled up.
        WebElement billingNewAddressCountryDropbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='BillingNewAddress_CountryId']")));
        new Select(billingNewAddressCountryDropbox).selectByIndex(1);// United States
        MyFunc.Wait(2);

        WebElement billingNewAddressStateDropbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='BillingNewAddress_StateProvinceId']")));
        new Select(billingNewAddressStateDropbox).selectByIndex(6);// Arizona
        MyFunc.Wait(2);

        //103 E PALM LN PHOENIX AZ 85004-1558 USA
        WebElement billingNewAddressCity = driver.findElement(By.xpath("//input[@id='BillingNewAddress_City']"));
        billingNewAddressCity.sendKeys("Phoenix");
        MyFunc.Wait(2);

        WebElement billingNewAddress1 = driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address1']"));
        billingNewAddress1.sendKeys("103 E PALM LN");
        MyFunc.Wait(2);

        WebElement billingNewAddressZip = driver.findElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"));
        billingNewAddressZip.sendKeys("85004-1558");
        MyFunc.Wait(2);

        WebElement billingNewAddressPhoneNumber = driver.findElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"));
        billingNewAddressPhoneNumber.sendKeys("(520)-520-1234");
        MyFunc.Wait(5);

        WebElement billingSaveContinueButton03 = driver.findElement(By.xpath("//input[@onclick='Billing.save()']"));
        billingSaveContinueButton03.click();
        MyFunc.Wait(2);

        // 8- When the user selects the "In-Store Pickup" option,
        // verifies that the shipping address is lost.
        // Then the user clicks the continue button.

        // negative scenario, In-Store Pickup" option is not selected
        WebElement shippingSaveContinueButton02 = driver.findElement(By.xpath("//input[@onclick='Shipping.save()']"));
        shippingSaveContinueButton02.click();
        MyFunc.Wait(2);

        driver.findElement(By.xpath("//input[@id='shippingoption_0']//following::label")).click();
        MyFunc.Wait(2);

        driver.findElement(By.xpath("//input[@id='shippingoption_1']//following::label")).click();
        MyFunc.Wait(2);

        driver.findElement(By.xpath("//input[@id='shippingoption_2']//following::label")).click();
        MyFunc.Wait(2);

        WebElement shippingMethodSaveContinueButton = driver.findElement(By.xpath("//input[@onclick='ShippingMethod.save()']"));
        shippingMethodSaveContinueButton.click();
        MyFunc.Wait(2);

        WebElement paymentMethodBackButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='payment-method-buttons-container']/p/a")));
        paymentMethodBackButton.click();
        MyFunc.Wait(2);

        WebElement shippingMethodBackButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='shipping-method-buttons-container']/p/a")));
        shippingMethodBackButton.click();
        MyFunc.Wait(2);

        //positive scenario
        driver.findElement(By.xpath("//input[@id='PickUpInStore']")).click();
        MyFunc.Wait(2);
        WebElement shippingAddressSelectMessage = driver.findElement(By.xpath("//label[text()='Select a shipping address from your address book or enter a new address.']"));
        Assert.assertFalse("Address message is still displayed", shippingAddressSelectMessage.isDisplayed());

        WebElement shippingSaveContinueButton01 = driver.findElement(By.xpath("//input[@onclick='Shipping.save()']"));
        shippingSaveContinueButton01.click();
        MyFunc.Wait(2);

        // 9- The user chooses the payment method (Cash on,Check/MoneyOrder,Credit Card, Purchase Order )
        // and clicks the continue button.
        // If the user has selected the Cash on or Check/MoneyOrder payment method
        // verifies the payment information message of selected payment method
        // or enters necessary payment  information into the textboxes

        // 9 (a)- If the user has selected the Cash on or Check/MoneyOrder
        // he/she verifies the payment method by checking message occuring in the "Payment Information" section
        // and if it is true clicks the continue button.

        // cash on delivery
        WebElement cashOnDelivery = driver.findElement(By.xpath("//input[@id='paymentmethod_0']"));
        cashOnDelivery.click();
        MyFunc.Wait(2);

        WebElement cashOnDeliveryContinueButton = driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']"));
        cashOnDeliveryContinueButton.click();
        MyFunc.Wait(2);

        WebElement cashOnDeliveryMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='checkout-payment-info-load']//p")));
        Assert.assertTrue("Message has not been shown !", cashOnDeliveryMessage.isDisplayed());

        WebElement cashOnDeliveryBackButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='payment-info-buttons-container']/p/a")));
        cashOnDeliveryBackButton.click();
        MyFunc.Wait(2);

        // 9 (b)- If the user has selected the Cash on or Check/MoneyOrder
        // he/she verifies the payment method  by checking message occuring in the "Payment Information" section
        // and if it is true clicks the continue button.

        // check / money order
        WebElement checkMoneyOrder = driver.findElement(By.xpath("//input[@id='paymentmethod_1']"));
        checkMoneyOrder.click();
        MyFunc.Wait(2);

        WebElement checkMoneyOrderContinueButton = driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']"));
        checkMoneyOrderContinueButton.click();
        MyFunc.Wait(2);

        WebElement checkMoneyOrderMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Tricentis GmbH']")));
        Assert.assertTrue("Check / Money order has not been completed !", checkMoneyOrderMessage.isDisplayed());

        WebElement checkMoneyOrderBackButton = driver.findElement(By.xpath("//div[@id='payment-info-buttons-container']/p/a"));
        checkMoneyOrderBackButton.click();
        MyFunc.Wait(2);

        // 9 (c) If the user has selected the Credit Card or Purchase Order in the "Payment Method",
        // he/she enters the payment details in the "Payment Information" section
        // and if it is true clicks the continue button.

        // credit card
        // negative scenario 01, credit card fields are empty
        WebElement creditCard = driver.findElement(By.xpath("//input[@id='paymentmethod_2']"));
        creditCard.click();
        MyFunc.Wait(2);

        WebElement creditCardContinueButton01 = driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']"));
        creditCardContinueButton01.click();
        MyFunc.Wait(2);

        WebElement creditCardInfoContinueButton01 = driver.findElement(By.xpath("//input[@onclick='PaymentInfo.save()']"));
        creditCardInfoContinueButton01.click();
        MyFunc.Wait(2);

        WebElement creditCardInfoMessage01 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Wrong card number']")));
        Assert.assertTrue("Right card Number !", creditCardInfoMessage01.isDisplayed());
        MyFunc.Wait(2);

        // negative scenario 02, credit card fields are filled up except cardholder name
        WebElement creditCardHolderName01 = driver.findElement(By.xpath("//input[@id='CardholderName']"));
        creditCardHolderName01.sendKeys("Ali Cabbar");
        MyFunc.Wait(2);

        creditCardInfoContinueButton01.click();
        MyFunc.Wait(2);

        WebElement creditCardInfoMessageCardHolderName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='checkout-payment-info-load']/div/div/div[2]/div/ul/li[1]")));
        Assert.assertTrue("Card holder name has been entered !", creditCardInfoMessageCardHolderName.isDisplayed());
        MyFunc.Wait(2);

        // negative scenario 03, credit card fields are filled up except credit card number
        WebElement creditCardNumber01 = driver.findElement(By.xpath("//input[@id='CardNumber']"));
        creditCardNumber01.sendKeys("5555 5555 5555 5555");
        MyFunc.Wait(2);

        creditCardInfoContinueButton01.click();
        MyFunc.Wait(2);

        WebElement creditCardInfoMessageCardNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='checkout-payment-info-load']/div/div/div[2]/div/ul/li[1]")));
        Assert.assertTrue("Right card number !", creditCardInfoMessageCardNumber.isDisplayed());
        MyFunc.Wait(2);

        // negative scenario 04, credit card fields are filled up except credit card code
        WebElement creditCardCode01 = driver.findElement(By.xpath("//input[@id='CardCode']"));
        creditCardCode01.sendKeys("555");
        MyFunc.Wait(2);

        creditCardInfoContinueButton01.click();
        MyFunc.Wait(2);

        WebElement creditCardInfoMessageCardCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='checkout-payment-info-load']/div/div/div[2]/div/ul/li")));
        Assert.assertTrue("Right card code !", creditCardInfoMessageCardCode.isDisplayed());
        MyFunc.Wait(2);

        WebElement creditCardBackButton = driver.findElement(By.xpath("//div[@id='payment-info-buttons-container']/p/a"));
        creditCardBackButton.click();
        MyFunc.Wait(2);

        // positive scenario, all credit card fields are filled up
        WebElement creditCardContinueButton02 = driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']"));
        creditCardContinueButton02.click();
        MyFunc.Wait(2);

        WebElement creditCardTypeDropBox = driver.findElement(By.xpath("//select[@id='CreditCardType']"));

        new Select(creditCardTypeDropBox).selectByIndex(1);
        MyFunc.Wait(2);
        new Select(creditCardTypeDropBox).selectByIndex(2);
        MyFunc.Wait(2);
        new Select(creditCardTypeDropBox).selectByIndex(3);
        MyFunc.Wait(2);
        new Select(creditCardTypeDropBox).selectByIndex(0);
        MyFunc.Wait(2);

        WebElement creditCardHolderName02 = driver.findElement(By.xpath("//input[@id='CardholderName']"));
        creditCardHolderName02.sendKeys("Ali Cabbar");
        MyFunc.Wait(2);

        WebElement creditCardNumber02 = driver.findElement(By.xpath("//input[@id='CardNumber']"));
        creditCardNumber02.sendKeys("5555 5555 5555 5555");
        MyFunc.Wait(2);

        WebElement creditCardExpirationDateMonthDropBox = driver.findElement(By.xpath("//select[@id='ExpireMonth']"));
        new Select(creditCardExpirationDateMonthDropBox).selectByIndex(1);
        MyFunc.Wait(2);

        WebElement creditCardExpirationDateYearDropBox = driver.findElement(By.xpath("//select[@id='ExpireYear']"));
        new Select(creditCardExpirationDateYearDropBox).selectByIndex(1);
        MyFunc.Wait(2);

        WebElement creditCardCode02 = driver.findElement(By.xpath("//input[@id='CardCode']"));
        creditCardCode02.sendKeys("555");
        MyFunc.Wait(2);

        WebElement creditCardInfoContinueButton02 = driver.findElement(By.xpath("//input[@onclick='PaymentInfo.save()']"));
        creditCardInfoContinueButton02.click();
        MyFunc.Wait(2);

        WebElement creditCardInfoMessage02 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Wrong card number']")));
        Assert.assertTrue("Right card Number !", creditCardInfoMessage02.isDisplayed());
        MyFunc.Wait(2);

        WebElement creditCardBackButton02 = driver.findElement(By.xpath("//div[@id='payment-info-buttons-container']/p/a"));
        creditCardBackButton02.click();
        MyFunc.Wait(2);

        // 9 (d) If the user has selected the Credit Card or Purchase Order in the "Payment Method",
        // he/she enters the payment details in the "Payment Information" section
        // and if it is true clicks the continue button.

        // purchase order

        // negative scenario, PO box is empty
        WebElement purchaseOrder = driver.findElement(By.xpath("//input[@id='paymentmethod_3']"));
        purchaseOrder.click();
        MyFunc.Wait(2);

        WebElement purchaseOrderContinueButton = driver.findElement(By.xpath("//input[@onclick='PaymentMethod.save()']"));
        purchaseOrderContinueButton.click();
        MyFunc.Wait(2);

        WebElement purchaseOrderInfoContinueButton01 = driver.findElement(By.xpath("//input[@onclick='PaymentInfo.save()']"));
        purchaseOrderInfoContinueButton01.click();
        MyFunc.Wait(2);

        WebElement purchaseOrderInfoMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='checkout-confirm-order-load']/div/div[2]/div/div/ul[1]/li[10]")));
        Assert.assertTrue("Wrong payment method !", purchaseOrderInfoMessage.isDisplayed());
        MyFunc.Wait(2);

        WebElement purchaseOrderBackButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='confirm-order-buttons-container']/p/a")));
        purchaseOrderBackButton.click();
        MyFunc.Wait(2);

        // positive scenario, PO box is filled up
        WebElement purchaseOrderPOBox = driver.findElement(By.xpath("//input[@id='PurchaseOrderNumber']"));
        purchaseOrderPOBox.sendKeys("123456");
        MyFunc.Wait(2);

        WebElement purchaseOrderInfoContinueButton02 = driver.findElement(By.xpath("//input[@onclick='PaymentInfo.save()']"));
        purchaseOrderInfoContinueButton02.click();
        MyFunc.Wait(2);

        // 10- The user verifies that the total price of the product is the same as the sub-total.

        List<WebElement> prices = driver.findElements(By.xpath("//*[@class='product-subtotal']"));

        double totalPrice = 0;
        for (WebElement p : prices) {
            System.out.println(p.getText());
            totalPrice = totalPrice + Double.parseDouble(p.getText().replaceAll("[^0-9,.]", ""));
        }
        System.out.println("totalPrice = " + totalPrice);

        WebElement itemTotalelement = driver.findElement(By.xpath("//*[@class='product-price']"));
        Double itemTotal = Double.parseDouble(itemTotalelement.getText().replaceAll("[^0-9,.]", ""));
        System.out.println("itemTotal = " + itemTotal);

        Assert.assertTrue("Prices are not the same", totalPrice == itemTotal);

        // 11- The user confirms the order after checking the order document's
        // Billing Address, Shipping Method, Payment Method, Product sections.

        WebElement orderBillingAddresConfirm = driver.findElement(By.xpath("//strong[text()='Billing Address']"));
        Assert.assertTrue("Billing Address is not seen !",orderBillingAddresConfirm.isDisplayed());
        MyFunc.Wait(2);

        WebElement orderShippingMethodConfirm = driver.findElement(By.xpath("//strong[text()='Shipping Method']"));
        Assert.assertTrue("Shipping Method is not seen !",orderShippingMethodConfirm.isDisplayed());
        MyFunc.Wait(2);

        WebElement orderPaymentMethodConfirm = driver.findElement(By.xpath("//strong[text()='Payment Method']"));
        Assert.assertTrue("Shipping Method is not seen !",orderPaymentMethodConfirm.isDisplayed());
        MyFunc.Wait(2);

        WebElement orderProductConfirm = driver.findElement(By.xpath("//div[@id=\"checkout-confirm-order-load\"]//td[2]/a"));
        Assert.assertTrue("Shipping Method is not seen !",orderProductConfirm.isDisplayed());
        MyFunc.Wait(2);

        WebElement confirmOrderSaveButton = driver.findElement(By.xpath("//input[@onclick='ConfirmOrder.save()']"));
        confirmOrderSaveButton.click();
        MyFunc.Wait(2);

        // 12- The user confirms that the order is successfully completed
        // by seeing the "Your Order Has Been SuccessFully Processed!"message.

        WebElement orderConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()='Your order has been successfully processed!']")));
        Assert.assertTrue("", orderConfirmation.isDisplayed());
        MyFunc.Wait(2);

        WebElement setLocationButton = driver.findElement(By.xpath("//div[@class='buttons']/input"));
        setLocationButton.click();
        MyFunc.Wait(2);


    }

    @Test(priority = 11)
    public void US7SurveyResponse() {
        driver.get("https://demowebshop.tricentis.com/");

        WebElement logoutBtn = driver.findElement(By.className("ico-logout"));
        logoutBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'Excellent')]")));
        WebElement resultsExButton = driver.findElement(By.xpath("//label[contains(text(), 'Excellent')]"));
        resultsExButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='vote-poll-1']")));
        WebElement Votebutton = driver.findElement(By.xpath("//input[@id='vote-poll-1']"));
        Votebutton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ico-login' ]")));
        WebElement login = driver.findElement(By.xpath("//a[@class='ico-login' ]"));
        login.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Email']")));
        WebElement email = driver.findElement(By.xpath("//*[@id='Email']"));
        email.sendKeys(UserData.email);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Password']")));
        WebElement password = driver.findElement(By.xpath("//*[@id='Password']"));
        password.sendKeys(UserData.password);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 login-button']")));
        WebElement login1 = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        login1.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'Excellent')]")));
        WebElement excellentRadio = driver.findElement(By.xpath("//label[contains(text(), 'Excellent')]"));
        excellentRadio.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("poll-display-text")));
        WebElement response = driver.findElement(By.className("poll-display-text"));

        Assert.assertTrue(response.getText().contains("Do you like"), "Test failed");
    }

    @Test(priority = 8)
    public void US8DownloadHistory() {
        driver.get("https://demowebshop.tricentis.com/");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='account'])[1]")));
        WebElement account = driver.findElement(By.xpath("(//*[@class='account'])[1]"));
        account.click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(), 'Orders')])[1]")));
        WebElement order1 = driver.findElement(By.xpath("(//a[contains(text(), 'Orders')])[1]"));
        order1.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Details']")));
        WebElement details1 = driver.findElement(By.xpath("//input[@value='Details']"));
        details1.click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'PDF')]")));
        WebElement pdfInvoice = driver.findElement(By.xpath("//a[contains(text(), 'PDF')]"));
        pdfInvoice.click();
    }

    @Test(priority = 9)
    public void US9UseCouponAndGiftCart() {
        driver.get("https://demowebshop.tricentis.com/");

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

        WebElement removeFromCard= driver.findElement(By.name("removefromcart"));
        removeFromCard.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("updatecart")));
        driver.findElement(By.name("updatecart")).click();

        String expected = "Your Shopping Cart is empty!";
        WebElement actual = driver.findElement(By.xpath("//div[@class='order-summary-content']"));

        Assert.assertEquals(expected, actual.getText(), "Siparis order is not empty");

        driver.navigate().to("https://demowebshop.tricentis.com/");

        WebElement computersp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//ul[@class='top-menu']//li/a[@href='/computers']")));
        actions.moveToElement(computersp).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Notebooks")));
        WebElement notebook2 = driver.findElement(By.linkText("Notebooks"));
        notebook2.click();

        WebElement addToCardButtonn = driver.findElement(By.cssSelector("[type='button'][class='button-2 product-box-add-to-cart-button']"));
        addToCardButtonn.click();

        WebElement addedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class*='content']")));

        String actualMessage = "The product has been added to your shopping cart";
        Assert.assertEquals(actualMessage, addedMessage.getText(), "Product added has been failed");

        driver.findElement(By.linkText("Shopping cart")).click();
        wait.until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/cart"));

        WebElement coupon = driver.findElement(By.name("discountcouponcode"));
        coupon.sendKeys("");
        driver.findElement(By.name("applydiscountcouponcode")).click();

        WebElement couponMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='message']")));
        Assert.assertTrue( couponMessage.getText().contains("couldn't"),"Coupon has been successfully added");

        driver.navigate().to("https://demowebshop.tricentis.com/");

        computers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//ul[@class='top-menu']//li/a[@href='/computers']")));
        new Actions(driver).moveToElement(computers).build().perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Notebooks")));
        driver.findElement(By.linkText("Notebooks")).click();

        WebElement addToCardButton2 = driver.findElement(By.cssSelector("[type='button'][class='button-2 product-box-add-to-cart-button']"));
        js.executeScript("arguments[0].scrollIntoView(true);", addToCardButton2);
        js.executeScript("arguments[0].click();", addToCardButton2);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class*='content']")));
        driver.findElement(By.linkText("Shopping cart")).click();

        wait.until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/cart"));
        driver.findElement(By.xpath("//div//ul[@class='top-menu']//li/a[@href='/gift-cards']")).click();

        driver.findElement(By.xpath("(//input[@value='Add to cart'])[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='add-to-cart-button-1']")));
        WebElement addtocard = driver.findElement(By.xpath("//input[@id='add-to-cart-button-1']"));
        addtocard.click();

        wait.until(ExpectedConditions.textToBe(By.xpath("(//p[@class='content'])[1]"), "Enter valid recipient name"));
        wait.until(ExpectedConditions.textToBe(By.xpath("(//p[@class='content'])[2]"), "Enter valid recipient email"));

        WebElement cardMessage  = driver.findElement(By.xpath("(//p[@class='content'])[1]"));
        WebElement cardMessage2 = driver.findElement(By.xpath("(//p[@class='content'])[2]"));

        Assert.assertTrue( cardMessage.getText().contains("name"),"Gift Card added successfully");
        Assert.assertTrue( cardMessage2.getText().contains("email"),"Gift Card added successfully");

        driver.findElement(By.linkText("Shopping cart")).click();

        WebElement giftBox = driver.findElement(By.name("giftcardcouponcode"));
        giftBox.sendKeys("");

        driver.findElement(By.name("applygiftcardcouponcode")).click();
        String giftMessage = "The coupon code you entered couldn't be applied to your order";

        WebElement giftboxMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='message']")));
        Assert.assertTrue( giftboxMessage.getText().contains("couldn't"),"Coupon has been successfully added");

        driver.navigate().to("https://demowebshop.tricentis.com/");

        computers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//ul[@class='top-menu']//li/a[@href='/computers']")));
        actions.moveToElement(computers).build().perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Notebooks")));
        driver.findElement(By.linkText("Notebooks")).click();

        WebElement addToCardButton1 = driver.findElement(By.cssSelector("[type='button'][class='button-2 product-box-add-to-cart-button']"));
        addToCardButton1.click();

        wait.until(ExpectedConditions.textToBe(By.cssSelector("p[class*='content']"), "The product has been added to your shopping cart"));
        addedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class*='content']")));

        Assert.assertEquals(actualMessage, addedMessage.getText(), "Product added has been failed");

        driver.findElement(By.linkText("Shopping cart")).click();
        wait.until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/cart"));

        driver.findElement(By.name("termsofservice")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        WebElement newA = driver.findElement(By.name("billing_address_id"));
        Select sel = new Select(newA);
        sel.selectByVisibleText("New Address");

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
        Assert.assertEquals(successMessage, "Your order has been successfully processed!", "FAILED");

        driver.findElement(By.linkText("Click here for order details.")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));

        driver.navigate().to("https://demowebshop.tricentis.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//ul[@class='top-menu']//li/a[@href='/computers']")));

        actions.moveToElement(computers).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Notebooks")));
        driver.findElement(By.linkText("Notebooks")).click();

        WebElement addToCardButton3 = driver.findElement(By.cssSelector("[type='button'][class='button-2 product-box-add-to-cart-button']"));
        addToCardButton3.click();

        addedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class*='content']")));

        Assert.assertEquals(actualMessage, addedMessage.getText(), "Product added has been failed");
        driver.findElement(By.linkText("Shopping cart")).click();
        wait.until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/cart"));

        driver.findElement(By.name("termsofservice")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        WebElement newAD = driver.findElement(By.name("billing_address_id"));
        new Select(newAD).selectByValue("3220970");

        driver.findElement(By.cssSelector("[onclick='Billing.save()']")).click();

        driver.findElement(By.name("PickUpInStore")).click();
        driver.findElement(By.cssSelector("[onclick='Shipping.save()']")).click();

        driver.findElement(By.id("paymentmethod_1")).click();
        driver.findElement(By.cssSelector("[onclick='PaymentMethod.save()']")).click();

        String text1 = driver.findElement(By.cssSelector("tr>td> :nth-child(1)")).getText();

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

        Assert.assertEquals(confirm, "6", "it couldn't go to the next page");
    }

   @AfterClass
   public void driverQuit() {
       driver.quit();
   }
}
