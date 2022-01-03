package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.WaitUtil;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QA1 extends BaseTest {

    @Test
    public void textBox() {
        driver.get("http://www.leafground.com/pages/Edit.html");
        driver.findElement(By.id("email")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("input[value=\"Append \"]")).sendKeys("test user");
        String attribute = driver.findElement(By.cssSelector("input[value=\"TestLeaf\"]")).getAttribute("value");
        System.out.println(attribute);
        driver.findElement(By.cssSelector("input[value=\"Clear me!!\"]")).clear();
        String disabled = driver.findElement(By.cssSelector("input[style=\"width:350px;background-color:LightGrey;\"]")).getAttribute("disabled");
        System.out.println(disabled);
    }

    @Test
    public void button() {
        driver.get("http://www.leafground.com/pages/Button.html ");
        driver.findElement(By.id("home")).click();
        String expectedUrl = "http://www.leafground.com/home.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Values are not equal");
        driver.get("http://www.leafground.com/pages/Button.html");
        int x = driver.findElement(By.id("position")).getLocation().getX();
        int y = driver.findElement(By.id("position")).getLocation().getY();
        System.out.println("X is value :" + x);
        Assert.assertEquals(x, 240);// get 240 by running the script and put it for assertion from 2nd run
        System.out.println("Y is value :" + y);
        String color = driver.findElement(By.id("color")).getAttribute("style");
        System.out.println("Color of the button is :" + color);
        int height = driver.findElement(By.id("size")).getSize().getHeight();
        int width = driver.findElement(By.id("size")).getSize().getWidth();
        System.out.println("height is value :" + height);
        System.out.println("width is value :" + width);
    }

    @Test
    public void hyperLink() {
        driver.get("http://www.leafground.com/pages/Link.html");
        //         //tagName[@attribude='value of attribute]
        driver.findElement(By.xpath("//a[text()='Go to Home Page']")).click();
        String expectedUrl = "http://www.leafground.com/home.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Values are not equal");
        driver.get("http://www.leafground.com/pages/Link.html");
        String href = driver.findElement(By.xpath("//a[text()='Find where am supposed to go without clicking me?']")).getAttribute("href");
        System.out.println("Href value is :" + href);
        List<WebElement> list = driver.findElements(By.xpath("//a[text()='Go to Home Page']"));
        list.get(1).click();
        expectedUrl = "http://www.leafground.com/home.html";
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Values are not equal");
        driver.get("http://www.leafground.com/pages/Link.html");
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Number of links :" + links.size());
    }

    @Test
    public void image() {
        driver.get("http://www.leafground.com/pages/Image.html");
        driver.findElement(By.cssSelector("img[src=\"../images/home.png\"]")).click();
        String expectedUrl = "http://www.leafground.com/home.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Values are not equal");
        driver.get("http://www.leafground.com/pages/Image.html");
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.cssSelector("[src=\"../images/keyboard.png\"]"))).perform();
        String expectedUrl1 = "http://www.leafground.com/home.html";
        String actualUrl1 = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl1, expectedUrl1, "Values are not equal");
    }

    @Test
    public void testBrokenLink() throws IOException {
        driver.get("http://www.leafground.com/pages/Link.html");
        List<WebElement> webElementList = driver.findElements(By.xpath("//a"));
        for (int i = 0; i < webElementList.size(); i++) {
            WebElement brokenLink = webElementList.get(i);
            String url = brokenLink.getAttribute("href");
            URL u = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();
            huc.setRequestMethod("GET");
            huc.connect();
            int respCode = huc.getResponseCode();
            if (respCode >= 400) {
                System.out.println("Broken link");
            } else {
                System.out.println("Valid link");
            }
        }
    }

    @Test
    public void testDropDown() {
        driver.get("http://www.leafground.com/pages/Dropdown.html");
        Select select = new Select(driver.findElement(By.id("dropdown1")));
        select.selectByIndex(1);
        select = new Select(driver.findElement(By.name("dropdown2")));
        select.selectByVisibleText("Appium");
        select = new Select(driver.findElement(By.id("dropdown3")));
        select.selectByValue("3");
        select = new Select(driver.findElement(By.className("dropdown")));
        List<WebElement> options = select.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println(options.get(i).getText());
        }
        driver.findElement(By.className("dropdown")).sendKeys("Selenium");
    }

    @Test
    public void radioButton() {
        driver.get("http://www.leafground.com/pages/radio.html");
        driver.findElement(By.id("no")).click();
        List<WebElement> radioButtonList = driver.findElements(By.name("news"));
        for (int i = 0; i < radioButtonList.size(); i++) {
            if (radioButtonList.get(i).isSelected()) {
                System.out.println(radioButtonList.get(i).getAttribute("value").trim());
            }
        }

        int age = 25;
        radioButtonList = driver.findElements(By.name("age"));
        List<String> categories = new ArrayList<>();
//        for (int i = 0; i < radioButtonList.size(); i++) {
//            WebElement element = radioButtonList.get(i);
//            categories.add(element.getText().trim());
//        }
        categories.add("1 - 20 years");
        categories.add("21 - 40 years");
        categories.add("Above 40 years");

        for (int i = 0; i < categories.size(); i++) {
            String str = categories.get(i);
            str = str.replace("years", "");
            str = str.replace("Above", "");
            str = str.trim();
            String[] s = str.split("-");
            if (s.length > 1 && age >= Integer.parseInt(s[0].trim()) && age <= Integer.parseInt(s[1].trim())) {
                radioButtonList.get(i).click();
            } else if (age > Integer.parseInt(s[0].trim())) {
                radioButtonList.get(i).click();
            }
        }

//
//        if (age >= 1 && age <= 20) {
//
//        } else if (age >= 21 && age <= 40) {
//
//        } else if (age > 40) {
//
//        }


    }

    @Test
    public void table() {
        driver.get("http://www.leafground.com/pages/table.html");
        WebElement table = driver.findElement(By.id("table_id"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<WebElement> columnsHeaders = table.findElements(By.tagName("th"));
        System.out.println("Rows :" + rows.size());
        System.out.println("Columns :" + columnsHeaders.size());
        List<WebElement> columnsOf3rdRow = rows.get(2).findElements(By.tagName("td"));
        System.out.println("Progress value is : " + columnsOf3rdRow.get(1).getText());
        List<WebElement> progressList = driver.findElements(By.cssSelector("td:nth-child(2)"));
        int min = Integer.MAX_VALUE;
        int minRow = 0;
        for (int i = 0; i < progressList.size(); i++) {
            String progress = progressList.get(i).getText();
            progress = progress.replace("%", "");
            if (min > Integer.parseInt(progress)) {
                min = Integer.parseInt(progress);
                minRow = i;
            }
        }
        rows.get(minRow + 1).findElement(By.cssSelector("td:nth-child(3) input")).click();
        for (int i = 1; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (int j = 0; j < columnsHeaders.size(); j++) {
                System.out.print(cols.get(j).getText() + "     ");
            }
            System.out.println();
        }
    }

    @Test
    public void alert() {
        driver.get("http://www.leafground.com/pages/Alert.html");
        driver.findElement(By.xpath("//*[text()='Alert Box']")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath("//*[text()='Prompt Box']")).click();
        driver.switchTo().alert().sendKeys("alert box");
        driver.switchTo().alert().accept();
        driver.findElement(By.id("btn")).click();
        driver.findElement(By.cssSelector("button[class=\"swal-button swal-button--confirm\"]")).click();
    }

    @Test
    public void frame() {
        driver.get("http://www.leafground.com/pages/frame.html");
        List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
        driver.switchTo().frame(frames.get(0));
        driver.findElement(By.id("Click")).click();
        driver.switchTo().parentFrame();
        driver.switchTo().frame(frames.get(1));
        frames = driver.findElements(By.xpath("//iframe"));
        driver.switchTo().frame(frames.get(0));
        driver.findElement(By.id("Click1")).click();
        driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();
        String s = driver.findElement(By.cssSelector("h1[itemprop=\"name\"]")).getText();
        System.out.println(s);
    }

    @Test
    public void windows() throws InterruptedException {
        driver.get("http://www.leafground.com/pages/Window.html");
        driver.findElement(By.id("home")).click();
        Set<String> windows = driver.getWindowHandles();
        ArrayList<String> arrayList = new ArrayList(windows);
        driver.switchTo().window(arrayList.get(1));
        driver.findElement(By.cssSelector("img[alt=\"Edit / Text Fields\"]")).click();
        driver.close();
        driver.switchTo().window(arrayList.get(0));
        driver.findElement(By.id("color")).click();
        Thread.sleep(5000);
        windows = driver.getWindowHandles();
        Assert.assertEquals(windows.size(), 3);
    }

    @Test
    public void calendar() {
        driver.get("http://www.leafground.com/pages/Calendar.html");
        driver.findElement(By.id("datepicker")).click();
        driver.findElement(By.xpath(String.format("//a[text()='%s']", "10"))).click();
    }

    @Test
    public void draggable() {
        driver.get("http://www.leafground.com/pages/drag.html");
        WebElement draggable = driver.findElement(By.id("draggable"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(draggable, 100, 100).perform();
        System.out.println();
    }

    @Test
    public void droppable() {
        driver.get("http://www.leafground.com/pages/drop.html");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement destination = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, destination).perform();
        System.out.println();
    }

    @Test
    public void selectable() {
        driver.get("http://www.leafground.com/pages/selectable.html");
        List<WebElement> list = driver.findElements(By.cssSelector("[id=\"selectable\"] li"));
        Actions actions = new Actions(driver);
        actions.click(list.get(0)).keyDown(Keys.CONTROL).click(list.get(3)).perform();
        System.out.println();
    }

    @Test
    public void sortable() {
        driver.get("http://www.leafground.com/pages/sortable.html");
        List<WebElement> list = driver.findElements(By.cssSelector("[id=\"sortable\"] li"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(list.get(4), list.get(2)).perform();
        System.out.println();
    }

    @Test
    public void autoComplete() throws InterruptedException {
        driver.get("http://www.leafground.com/pages/autoComplete.html");
        driver.findElement(By.id("tags")).sendKeys("Sele");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("[class=\"ui-menu-item-wrapper\"]")).click();
    }

    @Test
    public void downloadFile() throws InterruptedException {
        driver.get("http://www.leafground.com/pages/download.html");
        final File folder = new File("/Users/cb-sarathkumar/Downloads/");
        int beforeDownload = listFilesForFolder(folder);
        driver.findElement(By.cssSelector("[href=\"../testleaf.xlsx\"]")).click();
        Thread.sleep(1000);
        int afterDownload = listFilesForFolder(folder);
        Assert.assertEquals(beforeDownload + 1, afterDownload);

    }

    public int listFilesForFolder(final File folder) {
        return folder.listFiles().length;
    }

    @Test
    public void uploadFile() {
        driver.get("http://www.leafground.com/pages/upload.html");
        driver.findElement(By.cssSelector("[name=\"filename\"]")).sendKeys("/Users/cb-sarathkumar/Downloads/testleaf (1).xlsx");
    }

    @Test
    public void toolTip() {
        driver.get("http://www.leafground.com/pages/tooltip.html");
        WebElement ele = driver.findElement(By.id("age"));
        Actions actions = new Actions(driver);
        actions.moveToElement(ele).perform();// setTimeout(function(){debugger;}, 5000)
        System.out.println(driver.findElement(By.cssSelector("[class=\"ui-tooltip-content\"]")).getText());
    }

    @Test
    public void disappear() {
        driver.get("http://www.leafground.com/pages/disapper.html");
        WebElement ele = driver.findElement(By.id("btn"));
        WaitUtil.waitTillInVisible(ele);
        System.out.println(driver.findElement(By.id("show")).getText());
    }

    @Test
    public void test() {

    }
}
