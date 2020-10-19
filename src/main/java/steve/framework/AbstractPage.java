package steve.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import steve.configuration.Hosts;
import steve.exception.EntityNotFoundException;

@Component
public abstract class AbstractPage {

	protected static final Logger LOG = LoggerFactory.getLogger(AbstractPage.class);

	private static final int DEFAULT_DRIVER_WAIT_TIME = 40; //seconds
	private static final int CUSTOM_CONFIG_MANAGER_WAIT_TIME = 3; //seconds

	@Autowired
	private Hosts hostsConfig;
	@Autowired
	private WebDriverDiscovery driver;

	public WebDriver getDriver() {
		return driver.getRemoteWebDriver();
	}

	public JavascriptExecutor getJSExecutor() {
		return ((JavascriptExecutor) getDriver());
	}

	public Hosts getHostsConfig() {
		return hostsConfig;
	}

	public final int getDriverTimeout() {
		return DEFAULT_DRIVER_WAIT_TIME;
	}

//	public final String getEmailApplicationUrl() {
//		return hostsConfig.getEmailEnvironmentUrl();
//	}

	public void navigateURL(String applicationURL) {
		getDriver().manage().deleteAllCookies();
		// this workaround for Outlook cookies
		if (!getDriver().manage().getCookies().isEmpty()) {
			getDriver().manage().deleteAllCookies();
		}
		getDriver().navigate().to(applicationURL);
		if (isAlertPresent()) {
			acceptDialogIfAppears();
		}
	}

	public void navigateURLSimple(String applicationURL) {
		getDriver().navigate().to(applicationURL);
	}

	public void refreshPage() {
		String currentURL = getDriver().getCurrentUrl();
		getDriver().navigate().to(currentURL);
		getDriver().navigate().refresh();
	}

	/**
	 * Methods below are explicit waiters for elements
	 */
	public WebElement waitForElementClickable(final By by) {
		waitForJSLoadComplete();


		Wait<WebDriver> wait = new WebDriverWait(getDriver(), getDriverTimeout());
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public WebElement waitForElementVisible(final By by) {
		waitForJSLoadComplete();
		Wait<WebDriver> wait = new WebDriverWait(getDriver(), getDriverTimeout());
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public WebElement waitForElementPresent(final By by) {
		waitForJSLoadComplete();
		Wait<WebDriver> wait = new FluentWait<WebDriver>((WebDriver) getDriver())
				.withTimeout(getDriverTimeout(), TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public String waitForElementAndGetText(By by) {
		try {
			return waitForElementPresent(by).getText();
		} catch (StaleElementReferenceException e) {
			return waitForElementPresent(by).getText();
		}
	}

	public void waitForTextPresent(final By by, String txt, int timeOut) {
		Wait<WebDriver> wait = new WebDriverWait(getDriver(), timeOut);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(by, txt));
	}

	public String getTitle() {
		return getDriver().getTitle();
	}

	public void waitForPageTitle(String title) {
		pageTitleWait(title, getDriverTimeout());
	}

	public void pageTitleWait(String title, int timeOut) {
		Wait<WebDriver> wait = new WebDriverWait(getDriver(), timeOut);
		wait.until(ExpectedConditions.titleContains(title));
	}

	public Boolean isElementVisible(By by) {
		waitForJSLoadComplete();
		List<WebElement> elems = getDriver().findElements(by);
		if (elems.isEmpty()) {
			return false;
		} else {
			return elems.get(0).isDisplayed();
		}
	}

	public Boolean isTextPresent(By by, String txt) {
		try {
			waitForTextPresent(by, txt, getDriverTimeout());
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public Boolean isTextPresent(String text) {
		return getDriver().getPageSource().contains(text);
	}

	public boolean isElementDisplayed(By by) {
		waitForJSLoadComplete();
		return !getDriver().findElements(by).isEmpty();
	}

	public String getAlertDialogMsg() {
		Alert alert = getDriver().switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		return alertText;
	}

	public boolean isAlertPresent() {
		try {
			if (getDriver() instanceof PhantomJSDriver) {
				getJSExecutor().executeScript("window.confirm = function(msg){return true;};");
				return false;
			} else {
				Alert alert = getDriver().switchTo().alert();
				LOG.info("Alert '" + alert.getText() + "' is present");
				return true;
			}
		} catch (NoAlertPresentException e) {
			LOG.info("There are no alerts on the page");
			return false;
		}
	}

	public void acceptDialogIfAppears() {
		try {
			Alert alert = null;
			alert = getDriver().switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			LOG.info("There are no alerts on the page");
		}
	}

	public void dismissDialogIfAppears() {
		Alert alert = getDriver().switchTo().alert();
		alert.dismiss();
	}

	public boolean isOnPage(String pageTitle) {
		try {
			waitForPageTitle(pageTitle);
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public void deleteAllCookies() {
		getDriver().manage().deleteAllCookies();
	}

	public void selectDropDown(WebElement element, String option) {
		Select s = new Select(element);
		s.selectByValue(option);
	}

	public void selectDropDownByVisibleText(WebElement element, String option) {
		Select s = new Select(element);
		s.selectByVisibleText(option);
	}

	public WebElement getElementByLinkText(String linkText) {
		return getDriver().findElement(By.linkText(linkText));
	}

	public void goTo(String url) {
		getDriver().get(url);
	}

	public String getCurrentUrl() {
		return getDriver().getCurrentUrl();
	}

	public final String getDemoUrl() {
		return hostsConfig.getDemoUrl();
	}

	public void maximizeWindow() {
//		getDriver().manage().window().maximize();
	}

	public WebElement findElementByText(List<WebElement> eList, String text) {
		WebElement webElement = null;
		for (WebElement e : eList) {
			if (e.getText().equalsIgnoreCase(text)) {
				webElement = e;
				break;
			}
		}
		return webElement;
	}

	public WebElement findElementByAttribute(List<WebElement> eList, String attribute, String value) {
		WebElement webElement = null;
		for (WebElement e : eList) {
			if (e.getAttribute(attribute).equalsIgnoreCase(value)) {
				webElement = e;
				break;
			}
		}
		return webElement;
	}

	public void waitUntilRowPopulates(WebElement element, final int rowCount) {
		final WebElement table = element;

		new FluentWait<WebDriver>((WebDriver) getDriver())
				.withTimeout(getDriverTimeout(), TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.until(new Predicate<WebDriver>() {

					public boolean apply(WebDriver d) {
						List<WebElement> rawList = table.findElements(By.xpath("//tbody/tr"));
						return (rawList.size() >= rowCount);
					}
				});
	}

	public void waitUntilTableExactCountOfRowsPopulates(final String xpathExp, final int rowCount) {
		new FluentWait<WebDriver>((WebDriver) getDriver())
				.withTimeout(getDriverTimeout(), TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.until(new Predicate<WebDriver>() {

					public boolean apply(WebDriver d) {
						List<WebElement> rawList = d.findElements(By.xpath(xpathExp));
						return (rawList.size() == rowCount);
					}
				});
	}

	public void waitUntilMoreThanOptionsPopulates(final String xpathExp, final int rowCount) {
		new FluentWait<WebDriver>((WebDriver) getDriver())
				.withTimeout(getDriverTimeout(), TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.ignoring(StaleElementReferenceException.class)
				.until(new Predicate<WebDriver>() {

					public boolean apply(WebDriver d) {
						waitForJSLoadComplete();
						List<WebElement> rawList = d.findElements(By.xpath(xpathExp));
						return (rawList.size() >= rowCount && !rawList.get(0).getText().contains("Searching"));
					}
				});

	}

	public int getColumnIndexByName(WebElement table, String columnName) {
		waitForJSLoadComplete();
		List<WebElement> heads = table.findElements(By.xpath(".//thead/tr//th"));
		if (heads.isEmpty())
			heads = table.findElements(By.xpath(".//tbody/tr//th"));
		for (int i = 0; i < heads.size(); i++) {
			if (heads.get(i).getText().equals(columnName)) {
				return i + 1;
			}
		}
		throw new EntityNotFoundException("Column with name '" + columnName + "' not found");
	}

	public boolean isColumnExist(WebElement table, String columnName) {
		List<WebElement> heads = table.findElements(By.xpath(".//thead/tr//th"));
		if (heads.isEmpty())
			heads = table.findElements(By.xpath(".//tbody/tr//th"));
		for (int i = 0; i < heads.size(); i++) {
			if (heads.get(i).getText().contains(columnName)) {
				return true;
			}
		}
		return false;
	}

	public boolean isItemExistInTable(WebElement table, String columnName, String value) {
		int columnIndex = getColumnIndexByName(table, columnName);
		List<WebElement> rows = table.findElements(By.xpath(".//tbody//tr"));
		for (int i = 0; i < rows.size(); i++) {
			if (rows.get(i).findElement(By.xpath(".//td[" + columnIndex + "]")).getText().contains(value)) {
				return true;
			}
		}
		return false;
	}

	public boolean isItemExistInResultTable(String columnName, String value) {
		return isItemExistInTable(resultTableOnSearch(), columnName, value);
	}

	public int getTableSize(WebElement table) {
		return table.findElement(By.xpath(".//tbody")).findElements(By.tagName("tr")).size();
	}

	public int getRowIndexByColumnNameAndValue(WebElement table, String columnName, String value) {
		int columnIndex = getColumnIndexByName(table, columnName);
		List<WebElement> rows = table.findElements(By.xpath(".//tbody//tr"));
		for (int i = 0; i < rows.size(); i++) {
			if (rows.get(i).findElement(By.xpath(".//td[" + columnIndex + "]")).getText().contains(value)) {
				return i + 1;
			}
		}
		throw new EntityNotFoundException("Cell with value '" + value + "' for column '" + columnName + "' not found");
	}

	public int getRowIndexByColumnNameAndStrictValue(WebElement table, String columnName, String value) {
		int columnIndex = getColumnIndexByName(table, columnName);
		List<WebElement> rows = table.findElements(By.xpath(".//tbody//tr"));
		for (int i = 0; i < rows.size(); i++) {
			if (rows.get(i).findElement(By.xpath(".//td[" + columnIndex + "]")).getText().equals(value)) {
				return i + 1;
			}
		}
		throw new EntityNotFoundException("Cell with value '" + value + "' for column '" + columnName + "' not found");
	}

	public int getRowIndexByColumnNameAndValue(WebElement table, int columnIndex, String value) {
		List<WebElement> rows = table.findElements(By.xpath(".//tbody//tr"));
		for (int i = 0; i < rows.size(); i++) {
			if (rows.get(i).findElement(By.xpath(".//td[" + columnIndex + "]")).getText().contains(value)) {
				return i + 1;
			}
		}
		throw new EntityNotFoundException("Cell with value '" + value + "' for column '" + columnIndex + "' not found");
	}

	public String getCellValue(WebElement table, String columnName, int rowIndex) {
		int columnIndex = getColumnIndexByName(table, columnName);
		return table.findElement(By.xpath(String.format(".//tbody/tr[%d]/td[%d]", rowIndex, columnIndex))).getText()
				.trim();
	}

	public String getCellValueByColumnNumber(WebElement table, int column, int rowIndex) {
		return table.findElement(By.xpath(String.format(".//tbody/tr[%d]/td[%d]", rowIndex, column))).getText().trim();
	}

	public WebElement getResultsTableCell(String columnName, int rowIndex) {
		int columnIndex = getColumnIndexByName(resultTableOnSearch(), columnName);
		return resultTableOnSearch().findElement(
				By.xpath(String.format("./tbody/tr[%d]/td[%d]", rowIndex, columnIndex)));
	}

	public WebElement getResultsTableByName(String name) {
		return waitForElementVisible(By.xpath(String.format(
				"//div[contains(@id, \"able\")][./div[contains(@class,\"eader\") and contains(.,\"%s\")]]", name)));
	}

	public void clickOnResultsTableCell(String columnName, int rowIndex) {
		WebElement cell = getResultsTableCell(columnName, rowIndex);
		cell.click();
	}

	public void clickOnResultsTableRow(String columnName, String value) {
		int row = getRowIndexFromResultTable(columnName, value);
		clickOnResultsTableCell(columnName, row);
	}

	public WebElement getCellFirstRowFromResultTable(String columnName) {
		return getCell(resultTableOnSearch(), columnName, 1);
	}

	public WebElement getCell(WebElement table, String columnName, int rowIndex) {
		int columnIndex = getColumnIndexByName(table, columnName);
		return table.findElement(By.xpath(String.format(".//tbody/tr[%d]/td[%d]", rowIndex, columnIndex)));
	}

	public WebElement getCellFromResultTable(String columnName, int rowIndex) {
		return getCell(resultTableOnSearch(), columnName, rowIndex);
	}

	public WebElement resultTableOnSearch() {
		return waitForElementClickable(By.xpath("//table[@class='ct-resultsTable']"));
	}

	public WebElement resultsTableFooterMessage() {
		return waitForElementClickable(By.xpath("//div[@class='ct-resultsTableFooter-pagingStatus']"));
	}

	public WebElement resultDocumentQueueTableOnSearch() {
		return waitForElementClickable(
				By.xpath("//div[@class='ui-dialog-content ui-widget-content']//table[@class='ct-resultsTable']"));
	}

	public WebElement resultsTableFooterMessageInPopup() {
		return waitForElementClickable(By.xpath("//div[@id='resultsAll']/div[2]"));
	}

	public WebElement resultsTableCell(String columnName, int rowIndex) {
		int columnIndex = getColumnIndexByName(resultTableOnSearch(), columnName);
		return resultTableOnSearch().findElement(
				By.xpath(String.format("./tbody/tr[%d]/td[%d]", rowIndex, columnIndex)));
	}

	public String getCellValueFromResultTable(String columnName, int rowIndex) {
		waitForJSLoadComplete();
		return getCellValue(resultTableOnSearch(), columnName, rowIndex);
	}

	public String getCellValueFirstRowFromResultTable(String columnName) {
		waitForJSLoadComplete();
		return getCellValue(resultTableOnSearch(), columnName, 1);
	}

	public int getRowIndexFromResultTable(String columnName, String value) {
		return getRowIndexByColumnNameAndValue(resultTableOnSearch(), columnName, value);
	}

	public boolean isResultsTablePopulated(int rowCount) {
		try {
			waitUntilMoreThanOptionsPopulates("//table[@class='ct-resultsTable']//tbody/tr", rowCount);
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isResultTableDisplayed(String tableTitle) {
		return isElementDisplayed(By
				.xpath("//table[@class='ct-resultsTable']/preceding-sibling::div/div[@class='ct-resultsTableHeader-title' and text()='"
						+ tableTitle + "']"));
	}

	public int getResultsTableSize() {
		return waitForElementVisible(By.xpath("//table[@class='ct-resultsTable']//tbody")).findElements(
				By.tagName("tr")).size();
	}

	public void moveToElement(WebElement element) {
		Actions builder = new Actions(getDriver());
		builder.moveToElement(element).build().perform();
	}

	public void moveToElement(String xpath) {
		Actions builder = new Actions(getDriver());
		WebElement element = waitForElementVisible(By.xpath(xpath));
		builder.moveToElement(element).build().perform();
	}

	public boolean waitForJSLoadComplete() {
		WebDriverWait wait = new WebDriverWait(getDriver(), getDriverTimeout());

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) (getJSExecutor()).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (getJSExecutor()).executeScript("return document.readyState").toString().equals("complete");
			}
		};

		boolean jQuery = wait.until(jQueryLoad);
		boolean js = wait.until(jsLoad);
		return jQuery && js;
	}

	public void waitUntilMoreWindowsPresent(final int count) {
		new FluentWait<WebDriver>((WebDriver) getDriver())
				.withTimeout(120, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.until(new Predicate<WebDriver>() {

					public boolean apply(WebDriver d) {
						return getDriver().getWindowHandles().size() >= count;
					}
				});
	}

	public void waitForDropDownToHaveMoreThanOneOptions(final Select dropDown, final int count) {
		waitForJSLoadComplete();
		new FluentWait<WebDriver>((WebDriver) getDriver())
				.withTimeout(getDriverTimeout(), TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.until(new Predicate<WebDriver>() {

					public boolean apply(WebDriver d) {
						return (dropDown.getOptions().size() >= count);
					}
				});
	}

	public void switchToNewWindowAndCloseCurrent() {
		waitUntilMoreWindowsPresent(2);
		String currentWindow = getDriver().getWindowHandle();

		for (String window : getDriver().getWindowHandles()) {
			if (!window.equals(currentWindow)) {
				getDriver().close();
				getDriver().switchTo().window(window);
				getDriver().manage().window().maximize();
			}
		}
	}

	public void switchToAnotherWindow() {
		String currentWindow = getDriver().getWindowHandle();

		for (String window : getDriver().getWindowHandles()) {
			if (!window.equals(currentWindow)) {
				getDriver().close();
				getDriver().switchTo().window(window);
				getDriver().manage().window().maximize();
			}
		}
	}

	public void openNewWindow() {
		getJSExecutor().executeScript("window.open()");
	}

	public void openNewWindowAndNavigateUrl(String applicationURL) {
		openNewWindow();
		String currentWindow = getDriver().getWindowHandle();

		for (String window : getDriver().getWindowHandles()) {
			if (!window.equals(currentWindow)) {
				getDriver().switchTo().window(window);
				getDriver().manage().window().maximize();
				this.navigateURLSimple(applicationURL);
			}
		}
	}

	public void sendKeysWithJS(WebElement element, String text) {
		getJSExecutor().executeScript("arguments[0].value=\"" + text + "\";", element);
	}

	public void scrollElementIntoView(WebElement element) {
		getJSExecutor().executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToTheUpForBlock(WebElement element) {
		waitForJSLoadComplete();
		String keyChordEnd = Keys.chord(Keys.CONTROL, Keys.HOME);
		element.click();
		element.sendKeys(keyChordEnd);
	}

	public void scrollToTheDownForBlock(WebElement element) {
		waitForJSLoadComplete();
		String keyChordEnd = Keys.chord(Keys.CONTROL, Keys.END);
		element.click();
		element.sendKeys(keyChordEnd);
	}

	public void scrollToTheRightForBlock(WebElement element) {
		waitForJSLoadComplete();
		String keyChordEnd = Keys.chord(Keys.END);
		element.click();
		element.sendKeys(keyChordEnd);
	}

	public void scrollToTheLeftForBlock(WebElement element) {
		waitForJSLoadComplete();
		String keyChordEnd = Keys.chord(Keys.HOME);
		element.click();
		element.sendKeys(keyChordEnd);
	}

	public void triggerOnChangeFunction(WebElement element) {
		getJSExecutor().executeScript("$(arguments[0]).change();", element);
	}

	public void triggerOnBlurFunction(WebElement element) {
		getJSExecutor().executeScript("$(arguments[0]).blur();", element);
	}

	public String triggerOnHoverFunctionAndGetAttribute(final WebElement element, final String attribute) {
		new FluentWait<WebDriver>((WebDriver) getDriver())
				.withTimeout(getDriverTimeout(), TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.until(new Predicate<WebDriver>() {
					public boolean apply(WebDriver d) {
						moveToElement(element);
						getJSExecutor().executeScript("$(arguments[0]).hover();", element);
						getJSExecutor().executeScript("$(arguments[0]).focus();", element);
						return element.getAttribute(attribute) != null;
					}
				});
		return element.getAttribute(attribute);
	}

	public void doubleClickElementAndSendKeys(WebElement element, String message) {
		Actions builder = new Actions(getDriver());
		builder.doubleClick(element).sendKeys(element, message).sendKeys(Keys.ENTER).build().perform();
	}

	public void doubleClickElementAndSendKeys(String xpath, String message) {
		Actions builder = new Actions(getDriver());
		WebElement element = waitForElementVisible(By.xpath(xpath));
		builder.doubleClick(element).sendKeys(element, message).sendKeys(Keys.ENTER).build().perform();
	}

	public void doubleClick(WebElement element) {
		Actions builder = new Actions(getDriver());
		builder.doubleClick(element).build().perform();
	}

	public void dragAndDrop(WebElement source, WebElement target) {
		waitForJSLoadComplete();
		Actions builder = new Actions(getDriver());
		builder.clickAndHold(source).moveToElement(target).release().build().perform();
	}

	public void sendKeysToDateField(WebElement element, String text) {
		new Actions(getDriver()).sendKeys(element, text).sendKeys(element, Keys.ENTER).build().perform();
	}

	public List<String> getOptions(Select dropDounElement) {
		List<String> values = new ArrayList<String>();
		for (WebElement webElement : dropDounElement.getOptions()) {
			if (!webElement.getText().equals("")) {
				values.add(webElement.getText());
			}
		}
		return values;
	}

	public WebElement getActiveCheckBox() {
		return waitForElementClickable(By.xpath("//*[@type='checkbox']"));
	}

	/**
	 * Methods below are working with 'type ahead' dropdown
	 */
	public void enterTextInTypeAhead(String value) {
		waitForElementPresent(By.xpath("//input[contains(@class,'select2-input') and @role='combobox']"));
		List<WebElement> searchObjs = getDriver().findElements(
				By.xpath("//input[contains(@class,'select2-input') and @role='combobox']"));
		for (WebElement webElement : searchObjs) {
			if (webElement.isDisplayed()) {
				webElement.clear();
				sendKeysWithJS(webElement, value);
				webElement.sendKeys(Keys.SPACE);
				webElement.sendKeys(Keys.BACK_SPACE);
				break;
			}
		}
	}

	public void selectValueInTypeAheadDropDownByValue(String dropDownValue) {
		waitUntilMoreThanOptionsPopulates("//*[@class='select2-result-label']", 1);
		String locator = String.format(
				"//*[@class='select2-result-label' or @class='select2-match' and contains(text(), \"%s\")]",
				dropDownValue);
		if (getDriver().findElements(By.xpath("//*[@class='select2-result-label']")).size() > 1)
			locator = String.format("//*[@class='select2-results']//*[starts-with(text(),\"%s\")]", dropDownValue);
		waitForElementPresent(By.xpath(locator)).click();
	}

	public void selectExactValueInTypeAheadDropDownByValue(String dropDownValue) {
		waitUntilMoreThanOptionsPopulates("//*[@class='select2-result-label']", 1);
		String locator = String.format(
				"//*[@class='select2-result-label' or @class='select2-match' and contains(text(), \"%s\")]",
				dropDownValue);
		if (getDriver().findElements(By.xpath("//*[@class='select2-result-label']")).size() > 1)
			locator = String.format("//*[@class='select2-result-label'][.=\"%s\"]", dropDownValue);
		waitForElementPresent(By.xpath(locator)).click();
	}

	public WebElement selectValueInTypeAheadDropDownByIndex(int index) {
		waitUntilMoreThanOptionsPopulates("//*[@class='select2-result-label']", 1);
		return getDriver().findElements(By.xpath("//div[@class='select2-result-label']")).get(index);
	}

	public WebElement resultFromTypeAheadDropDown() {
		return getDriver().findElement(By.xpath("//div[@class='select2-result-label']"));
	}

	public List<String> getValuesFromTypeAheadDropDown() {
		String locator = "//div[contains(@class,'ct-autocomplete-result') and contains(@style,'display: block')]//ul[@class='select2-results']/li";
		waitUntilMoreThanOptionsPopulates(locator, 1);
		List<String> values = new ArrayList<String>();
		for (WebElement webElement : getDriver().findElements(By.xpath(locator))) {
			values.add(webElement.getText());
		}
		return values;
	}

    public List<String> getValuesFromTypeAheadDropDownCourt() {
        String locator = "//div[contains(@class,'select2-drop select2-display-none select2-with-searchbox select2-drop-active') and contains(@style,'display: block')]//ul[@id='select2-results-2']/li";
        waitUntilMoreThanOptionsPopulates(locator, 1);
        List<String> values = new ArrayList<String>();
        for (WebElement webElement : getDriver().findElements(By.xpath(locator))) {
            values.add(webElement.getText());
        }
        return values;
    }

	public boolean valueExistsInTypeAheadDropDown(String value) {
		typeAheadDropDown().click();
		enterTextInTypeAhead(value);
		// size() == 1 because only one value should be displayed, without duplicates
		return getDriver().findElements(
				By.xpath("//*[(@class='select2-result-label' and contains(text(), '" + value
						+ "')) or (@class='select2-match' and contains(text(), '" + value + "'))]")).size() == 1 ?
				true :
				false;
	}

	public boolean valueExistsInTypeAheadDropDownByIndex(int dropdownIndex, String value) {
		typeAheadDropDownByIndex(dropdownIndex).click();
		enterTextInTypeAhead(value);
		// size() == 1 because only one value should be displayed, without duplicates
		return getDriver().findElements(
				By.xpath("//*[(@class='select2-result-label' and contains(text(), '" + value
						+ "')) or (@class='select2-match' and contains(text(), '" + value + "'))]")).size() == 1 ?
				true :
				false;
	}

	public void selectTypeFromDropdown(String type) {
		typeAheadDropDown().click();
		enterTextInTypeAhead(type);
		selectValueInTypeAheadDropDownByValue(type);
	}

	public void selectTypeFromDropdownByIndex(int dropdownIndex, String type) {
		typeAheadDropDownByIndex(dropdownIndex).click();
		enterTextInTypeAhead(type);
		selectValueInTypeAheadDropDownByValue(type);
	}

	public WebElement typeAheadDropDown() {
		return waitForElementVisible(By.xpath("//a[contains(@class,'select2-choice')]"));
	}

	public WebElement typeAheadDropDownByIndex(int index) {
		waitForJSLoadComplete();
		List<WebElement> objs = getDriver().findElements(By.xpath("//a[contains(@class, 'select2-choice')]"));
		return objs.get(index);
	}

	public void selectTypeFromDropdownByIndexMultiDayEvent(int dropdownIndex, String type) {
		typeAheadDropDownByIndexMultiDayEvent(dropdownIndex).click();
		enterTextInTypeAhead(type);
		selectValueInTypeAheadDropDownByValue(type);
	}

	public WebElement typeAheadDropDownByIndexMultiDayEvent(int index) {
		waitForJSLoadComplete();
		List<WebElement> objs = getDriver().findElements(
				By.xpath("//div[@id='resources']//a[contains(@class, 'select2-choice')]"));
		return objs.get(index);
	}

	/**
	 * Select and return cell of Handson Table (Config Manager) by row and column indexes
	 * <p>
	 * NOTE: indexes start from 0
	 *
	 * @return cell webelement
	 */
	public WebElement selectAndGetConfigManagerCell(int rowIndex, int columnIndex) {
		(getJSExecutor()).executeScript("widget.getInstance().selectCell(" + rowIndex + ", " + columnIndex + ");");
		return (WebElement) (getJSExecutor()).executeScript(
				"return widget.getInstance().getCell(" + rowIndex + ", " + columnIndex + ");");
	}

	/**
	 * Deselect cell of Handson Table (Config Manager)
	 */
	public void deselectConfigManagerCell() {
		(getJSExecutor()).executeScript("widget.getInstance().deselectCell();");
	}

	/**
	 * Get count of rows of Handson Table (Config Manager)
	 */
	public int getConfigManagerCountRows() {
		return ((Long) getJSExecutor().executeScript("return widget.getInstance().countRows();")).intValue();
	}

	/**
	 * Return true if row is empty and vice versa in Handson Table (Config Manager)
	 *
	 * @param rowIndex
	 * @return boolean value (true/false)
	 */
	public boolean isEmptyConfigManagerRow(int rowIndex) {
		return ((Boolean) getJSExecutor().executeScript("return widget.getInstance().isEmptyRow(" + rowIndex + ");"))
				.booleanValue();
	}

	/**
	 * Get index of first visible row in Handson Table (Config Manager)
	 *
	 * @return row index
	 */
	public int getIndexOfFirstVisibleConfigManagerRow() {
		return ((Long) getJSExecutor().executeScript("return widget.getInstance().rowOffset();")).intValue();
	}

	/**
	 * Get cell value of Handson Table (Config Manager)
	 * <p>
	 * NOTE: indexes start from 0
	 *
	 * @param rowIndex
	 * @param columnIndex
	 * @return text
	 */
	public String getConfigManagerDataAtCell(int rowIndex, int columnIndex) {
		return ((Object) getJSExecutor().executeScript(
				"return widget.getInstance().getDataAtCell(" + rowIndex + ", " + columnIndex + ");")).toString();
	}

	/**
	 * Find editable row index from Config Manager
	 * element by locator //td[text()='<PRJT IMPL>' or text()='<CRT CHNG>' or text()='<CORE>']/..
	 * <p>
	 * NOTE: indexes start from 0
	 *
	 * @return row index (first row = 0)
	 */
	public Integer getEditableConfigManagerRowIndex() {
		int count = getConfigManagerCountRows();
		int visibleRow = getIndexOfFirstVisibleConfigManagerRow();
		for (int i = visibleRow; i < count; i++) {
			if (getConfigManagerDataAtCell(i, 0).isEmpty()) {
				return i;
			}
		}
		return -1; // no editable rows
	}

	/**
	 * Autocomplete value of Config Manager cell
	 *
	 * @param cell
	 * @param value
	 */
	public void autoCompleteValueOfConfigManagerCell(WebElement cell, String value) {
		cell.sendKeys(Keys.ESCAPE);
		cell.sendKeys(Keys.CLEAR);
		enterTextInTypeAhead(value);
		cell.sendKeys(Keys.ENTER);
	}

	public boolean isConfigManagerElementPresent(final By by) {
		waitForJSLoadComplete();
		try {
			new FluentWait<WebDriver>((WebDriver) getDriver())
					.withTimeout(CUSTOM_CONFIG_MANAGER_WAIT_TIME, TimeUnit.SECONDS)
					.pollingEvery(10, TimeUnit.MILLISECONDS).until(new Predicate<WebDriver>() {

				public boolean apply(WebDriver d) {
					List<WebElement> rows = d.findElements(by);
					if (!rows.isEmpty()) {
						return true;
					}
					return false;
				}
			});
			return true;
		} catch (TimeoutException ex) {
			return false;
		}
	}

	/**
	 * Add hook to wait for HandsonTableLoad
	 */
	public void addHandsonLoadHook() {
		waitForJSLoadComplete();
		getJSExecutor().executeScript("if (window.handsonLoaded === undefined) "
				+ "{ Handsontable.hooks.add('afterRender', function() { window.handsonLoaded = true; }); };"
				+ " window.handsonLoaded = false;");
	}

	/**
	 * Wait until handson table renders. Use hook added by method addLoadHook(). In case no hooks were added just do
	 * nothing.
	 */
	public void waitForHandsonLoad() {
		waitForJSLoadComplete();
		Boolean result = (Boolean) getJSExecutor().executeScript("return window.handsonLoaded;");
		if (result != null) {
			new FluentWait<WebDriver>((WebDriver) getDriver())
					.withTimeout(CUSTOM_CONFIG_MANAGER_WAIT_TIME, TimeUnit.SECONDS)
					.until(new Predicate<WebDriver>() {

						public boolean apply(WebDriver d) {
							return (Boolean) getJSExecutor().executeScript("return window.handsonLoaded;");
						}
					});
		}
	}
}
