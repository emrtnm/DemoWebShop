package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EdgeDriver extends BaseDriver {
    public static WebDriver driver;

    public static WebDriverWait wait;

    static {
        driver = new org.openqa.selenium.edge.EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
}
