package steve.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import steve.framework.AbstractPage;

@Component
public class TwoSixtyPage extends AbstractPage {

    public TwoSixtyPage() {
    }

    public WebElement selectProduct() {
        return getDriver().findElement(By.xpath(".//a[text()='Mac']"));
    }

    public WebElement selectModel() {
        return getDriver().findElement(By.xpath(".//a[@class='chapternav-link']"));
    }

    public WebElement buy() {
        return getDriver().findElement(By.xpath(".//a[@class='ac-ln-button']"));
    }

    public WebElement selectSize() {
//        return getDriver().findElement(By.xpath(".//button[@class='as-filter-button ']"));
//        return getDriver().findElement(By.xpath(".//div[@class='as-bundleselection-filter-menuitems']//button[contains(@class,'as-filter-button')]"));
//        return getDriver().findElement(By.xpath(".//div[@class='as-bundleselection-filter-menuitems']//button[contains(text(),'15-inch')]"));
        return getDriver().findElement(By.xpath("//div[@class='as-bundleselection-filter-menuitems']/span[@class='as-filter-buttons']/button[contains(text(),'15-inch')]"));

//        return getDriver().findElement(By.xpath(".//button[contains(@class,'as-filter-button')]"));
    }

    public WebElement selectColour() {
        return waitForElementClickable(By.xpath("//div[contains(@class,'as-macbundle')]//li[contains(@data-ase-materializer,'acc_MNYJ2B/A')]//span"));
    }

    public WebElement selectButton() {
        return waitForElementClickable(By.xpath("//div[@class='as-macbundle-actioncontainer']//span[@class='label']"));
    }

    public WebElement selectProcessor() {
        return getDriver().findElement(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='Processor'])[1]/following::div[14]"));
    }

    public WebElement selectMemory() {
        return getDriver().findElement(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='Memory'])[1]/following::div[4]"));
    }

    public WebElement selectSoftware() {
        return getDriver().findElement(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='None'])[2]/following::div[7]"));
    }

    public WebElement addToCart1() {
        return getDriver().findElement(By.xpath(".//div[@name=='add-to-cart']"));
    }

    public WebElement displayAdapter() {
        return getDriver().findElement(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='£19.00'])[1]/following::span[1]"));
    }

    public WebElement addToCart2() {
        return getDriver().findElement(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='£3,168.99'])[1]/following::span[5]"));
    }
}
