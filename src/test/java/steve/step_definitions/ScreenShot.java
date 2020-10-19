package steve.step_definitions;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

public class ScreenShot {
    private static final String CHROME_DRIVER_PATH = "C:\\WebDrivers\\Chrome\\chromedriver.exe";


    @Test
    public void ScreenShot() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

// Maximize the window
        driver.manage().window().maximize();

// Pass the url
        driver.get("http://www....");

// Take screenshot and store as a file format
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // now copy the  screenshot to desired location using copyFile //method
            FileUtils.copyFile(src, new File("S:\\ScreenShots\\Derivative proj\\Sprint1\\Inter-Fund transfer XYZ-123"));
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }
}