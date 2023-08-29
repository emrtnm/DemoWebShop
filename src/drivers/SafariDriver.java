package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SafariDriver extends BaseDriver {
    public static WebDriver driver;

    public static WebDriverWait wait;

    static {
        driver = new org.openqa.selenium.safari.SafariDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
}
