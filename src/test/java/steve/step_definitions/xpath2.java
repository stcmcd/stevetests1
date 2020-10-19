package steve.step_definitions;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
public class xpath2 {
    private static final String CHROME_DRIVER_PATH = "C:\\WebDrivers\\Chrome\\chromedriver.exe";

    @Test
    public void xpath1() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
// Open Firefox
//        WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();

// Maximize the window
        driver.manage().window().maximize();

// Pass the url
        driver.get("http://www.demo.guru99.com/test/table.html");

       WebElement el = driver.findElement(By.xpath(".//tr[3]/td[contains(text(),'6')]"));
//        el = driver.findElement(By.xpath(".//*[contains(text(),'8')]"));
//        el = driver.findElement(By.xpath("//tr[4]//td[contains(text(),'')]"));
        el = driver.findElement(By.xpath(".//tr[4]/td[contains(.,'8')]"));
        el = driver.findElement(By.xpath("//html/body/table/tbody/tr[4]/td[2]/following::td[1]"));


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
