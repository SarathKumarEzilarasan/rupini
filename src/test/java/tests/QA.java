package tests;

import listeners.TestNGListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.TextBoxPage;
import utils.PropUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class QA extends BaseTest {

    @Test
    public void textBox() throws Exception {
        String url = PropUtil.getProperty("url");
        driver.get(url + "pages/Edit.html");
        TextBoxPage textBoxPage = new TextBoxPage(driver);
        textBoxPage.setEmailInput("test@gmail.com");
        throw new Exception();
    }

}