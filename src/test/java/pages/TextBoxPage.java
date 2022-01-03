package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.SetUtil;

import java.util.List;

public class TextBoxPage {
    //     driver.findElement(By.id("email")).sendKeys("test@gmail.com");
//        driver.findElement(By.cssSelector("input[value=\"Append \"]")).sendKeys("test user");
//    String attribute = driver.findElement(By.cssSelector("input[value=\"TestLeaf\"]")).getAttribute("value");
//        System.out.println(attribute);
//        driver.findElement(By.cssSelector("input[value=\"Clear me!!\"]")).clear();
//    String disabled = driver.findElement(By.cssSelector("input[style=\"width:350px;background-color:LightGrey;\"]")).getAttribute("disabled");
//        System.out.println(disabled);
//
    //Page object Model --> Page Factory model
    WebDriver driver;

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /////////////////////////////
    @FindBy(id = "email")
    List<WebElement> emailInput;

    public void setEmailInput(String email) {
        SetUtil.SetEle(emailInput.get(0),email);
    }

}
