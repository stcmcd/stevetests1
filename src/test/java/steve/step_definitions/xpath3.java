package steve.step_definitions;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class xpath3 {
    private static final String CHROME_DRIVER_PATH = "C:\\WebDrivers\\Chrome\\chromedriver.exe";


    @Test
    public void xpath3() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
// Open Firefox
//        WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();

// Maximize the window
        driver.manage().window().maximize();

// Pass the url
        driver.get("http://www.mastercard.com");

        WebElement el = driver.findElement(By.xpath(".//*[contains(text(), 'Accept')]"));
        el.click();
//        el = driver.findElement(By.xpath(".//*[contains(text(),'8')]"));
//        el = driver.findElement(By.xpath("//tr[4]//td[contains(text(),'')]"));
        driver.findElement(By.xpath(".//span[contains(text(),'Consumers')]/ancestor::li/following-sibling::li[2]/a")).click();

        el = driver.findElement(By.xpath(".//span[contains(text(), 'Consumers')]"));
        el.findElement(By.xpath(".//parent::a")).click();
//        el = driver.findElement(By.xpath("/html/body/div[1]/div/header/div[1]/nav/div[3]/div/ul/li[1]/a"));
//        el = driver.findElement(By.xpath("/html/body/div[1]/div/header/div[1]/div[1]/div[1]/div/ul/li[1]/a"));
//        el.click();


//        WebElement el = driver.findElement(By.xpath("//tr[3]//td[contains(text(),'6')]"));
//
//        el.findElement(By.xpath("./preceding::tr/td[1]")).getText()
//        1
//        el.findElement(By.xpath(".//preceding::tr[1]/td[2]")).getText()
//        5
//        el.findElement(By.xpath(".//preceding::tr[2]/td[2]")).getText()
//        2
//        el.findElement(By.xpath(".//following::tr[1]/td[1]")).getText()
//        el.findElement(By.xpath(".//following::tr/td[1]")).getText()
//        6
//        el.findElement(By.xpath(".//following::tr[2]/td[1]")).getText()
//        el.findElement(By.xpath(".//following::tr/td[2]")).getText()
//        7
//
//        el.findElement(By.xpath(".//ancestor::tr")).getText()
//        el.findElement(By.xpath(".//parent::tr")).getText()
//        5 6
//        el.findElement(By.xpath(".//ancestor::tr/td[2]")).getText()
//        el.findElement(By.xpath(".//ancestor::tr/td[2]")).getText()
//        6
//
//
        el = driver.findElement(By.xpath(".//tr[4]//td[contains(text(),'7')]"));
//        el.findElement(By.xpath(".//following-sibling::td")).getText()
//        8
//        el.findElement(By.xpath(".//following-sibling::td")).getText()
//        6

    }
}
