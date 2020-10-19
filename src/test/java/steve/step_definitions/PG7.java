package steve.step_definitions;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class PG7 {
    private static final String CHROME_DRIVER_PATH = "C:\\WebDrivers\\Chrome85\\chromedriver.exe";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();


        driver.manage().window().maximize();

// Pass the url
//        driver.get("http://www.apple.com");
        String baseUrl = "http://demo.guru99.com/test/newtours/";
        driver.get(baseUrl);

        WebElement link_Home = driver.findElement(By.linkText("Home"));
        WebElement td_Home = driver
                .findElement(By
                        .xpath("//html/body/div"
                                + "/table/tbody/tr/td"
                                + "/table/tbody/tr/td"
                                + "/table/tbody/tr/td"
                                + "/table/tbody/tr"));

        Actions builder = new Actions(driver);
        Action mouseOverHome = builder
                .moveToElement(link_Home)
                .build();

        String bgColor = td_Home.getCssValue("background-color");
        System.out.println("Before hover: " + bgColor);
        mouseOverHome.perform();
        bgColor = td_Home.getCssValue("background-color");
        System.out.println("After hover: " + bgColor);
        driver.close();
    }
}
