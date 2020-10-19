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

public class xpath1 {
    private static final String CHROME_DRIVER_PATH = "C:\\WebDrivers\\Chrome85\\chromedriver.exe";

    @Test
    public void xpath1() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
// Open Firefox
//        WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();

// Maximize the window
        driver.manage().window().maximize();

// Pass the url
        driver.get("http://www.apple.com");
        WebElement el = driver.findElement(By.xpath("/html/body/nav/div/ul[2]/li[3]/a"));
// in inTellij debug
//        driver.findElement(By.xpath(".//span[contains(text(),'iPad')]")).getText()
//        parent is just the one above the span which is an a
//        driver.findElement(By.xpath(".//span[contains(text(),'iPad')]/parent::a")).click()
//        above same as driver.findElement(By.xpath(".//span[contains(text(),'iPad')]/../../a")).click() ie up a couple of levels the /a
//        to go 2 above and navigate to the next a you need to go 2 above which is the li
//        driver.findElement(By.xpath(".//span[contains(text(),'iPad')]/ancestor::li/a")).click()
//        to go 2 to the righ and click
//        driver.findElement(By.xpath(".//span[contains(text(),'iPad')]/ancestor::li/following-sibling::li[2]/a")).click()
//          to select all the spans to get listOfElements.get(0).getText() etc
//        String xPathText = ".//span";
//        List<WebElement> listOfElements = driver.findElements(By.xpath(xPathText));

//apple
//      String xPathText = "/html/body/nav/div/ul[2]//li/a";
//        String xPathText = "/html/body/nav/div//ul/li/a";
//        String xPathText = "/html/body/nav/div//ul/*";
        String xPathText = "/html/body/nav/div/ul//li/a/span";
        List<String> textArray = new ArrayList<String>();

        List<WebElement> listOfElements = driver.findElements(By.xpath(xPathText));
        StringBuilder sb = new StringBuilder();

        for( WebElement x : listOfElements) {
            textArray.add(x.getText());
            sb.append(x.getText());
        }
        String x = sb.toString();
    }
}