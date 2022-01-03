package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static utils.DriverUtil.getDriver;

public class ClickUtil {
    static Actions actions = new Actions(getDriver());

    public static void clickEle(WebElement element) {
        element.click();
    }

    public static void clickEleAction(WebElement element) {
        actions.click(element).perform();
    }

    public static void clickEleJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
    }


}
