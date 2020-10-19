package steve.step_definitions;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import steve.framework.AbstractPage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class gmail extends AbstractPage {
    private static final String CHROME_DRIVER_PATH = "C:\\WebDrivers\\Chrome85\\chromedriver.exe";


//    @Test
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
// Open Firefox
//        WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();

// Maximize the window
        driver.manage().window().maximize();

// Pass the url
        driver.get("http://www.gmail.com");

        WebElement el = driver.findElement(By.xpath(".//input[@type = 'email']"));
        el.sendKeys("steve.mcdonaldd@gmail.com");

//        el.sendKeys(Keys.ENTER);

//        el = driver.findElement(By.xpath(".//div[@class='VfPpkd-RLmnJb']"));
//        el = driver.findElement(By.xpath(".//span[contains(text(), 'Next']/../../../div"));
        el = driver.findElement(By.xpath(".//span[contains(text(), 'Next')]/following-sibling::div"));

        el.click();

        el = driver.findElement(By.xpath(".//input[@type = 'password']"));
        el.sendKeys("Dublin02");

        el = driver.findElement(By.xpath(".//span[contains(text(), 'Next')]/following-sibling::div"));
        el.click();

        el = driver.findElement(By.xpath(".//tbody/tr/td/div/span[contains(text(), 'Universal')]/ancestor::tr/td/div[@role='checkbox']"));

        el.click();

//need to check below
//        el = driver.findElement(By.xpath(".//tbody/tr/td/div/span[contains(text(), 'Universal')]/following::tr/td/div[@role='checkbox']"));
//        el.click();

//        driver.findElement(By.xpath(".//span[contains(text(), 'Donegal')]/preceding::td[99]")).getText();

//        driver.findElement(By.xpath(".//span[contains(text(), 'Donegal')]/ancestor::tr/td[9]")).getText();
//        is same as
//        driver.findElement(By.xpath(".//span[contains(text(), 'Donegal')]/following::td[4]")).getText();

//last col on first row
//        driver.findElement(By.xpath(".//span[contains(text(), 'Donegal')]/preceding::tr/td[9]")).getText()
//last col on row above
//        driver.findElement(By.xpath(".//span[contains(text(), 'Donegal')]/preceding::tr[1]/td[9]")).getText()



//apple
//      String xPathText = "/html/body/nav/div/ul[2]//li/a";
//        String xPathText = "/html/body/nav/div//ul/li/a";
//        String xPathText = "/html/body/nav/div//ul/*";

    }
}