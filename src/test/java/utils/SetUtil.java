package utils;

import org.openqa.selenium.WebElement;

public class SetUtil {
    public static void SetEle(WebElement element,String str){
        element.sendKeys(str);
    }
}
