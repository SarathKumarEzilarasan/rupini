package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.DriverUtil.getDriver;

public class WaitUtil {
    static WebDriver driver = getDriver();
    static WebDriverWait wait = new WebDriverWait(driver, 20);

    public static void waitTillVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitTillInVisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
