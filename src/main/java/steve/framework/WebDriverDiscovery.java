package steve.framework;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import steve.exception.UndefinedBrowserException;

@Scope("prototype")
public class WebDriverDiscovery {

    protected static final Logger LOG = LoggerFactory.getLogger(WebDriverDiscovery.class);
    
    private static final String DOWNLOAD_FILES_PATH = "C:\\selenium-java-3.141.59";
    private static final String CHROME_DRIVER_PATH = "C:\\WebDrivers\\Chrome\\chromedriver.exe";
    private static final String GHOST_DRIVER_PATH = "src/main/resources/drivers/phantomjs.exe";
    private static final String PROXY_URL = "webproxy.lon.corp.services:80";

    private final BrowserType browser;
    private RemoteWebDriver remoteWebDriver;
    private String driverTypePath;
    private static String operatingSystem;
    private static String os_Version;
    private static String browserName;
    private static String browserVersion;

    public static String getOperatingSystem() {
        return operatingSystem;
    }

    public static String getOs_Version() {
        return os_Version;
    }

    public static String getBrowserName() {
        return browserName;
    }

    public static String getBrowserVersion() {
        return browserVersion;
    }
    
    public RemoteWebDriver getRemoteWebDriver() {
        return remoteWebDriver;
    }

    public WebDriverDiscovery(String driverType) {
        String commandArg = System.getProperty("driverType");
        if (StringUtils.isNotBlank(commandArg)) {
            this.browser = BrowserType.getByBrowserName(commandArg);
            driverType = this.browser.getDriver();
        } else {
            this.browser = BrowserType.getByDriverName(driverType);
        }
        LOG.debug("Command line 'browser' argument: " + this.browser.toString());
        
        String runConfig = System.getProperty("browserConfig");
        if (StringUtils.isNotBlank(runConfig)) {
            List<String> commandLineArgumentsList = Arrays.asList(runConfig.split(","));
            for (String configType : commandLineArgumentsList) {
                if (configType.contains("=")) {
                    String configKey = configType.substring(0, configType.lastIndexOf("="));
                    String configValue = configType.substring(configType.indexOf("=") + 1);
                    if (configKey.equalsIgnoreCase("driverName")) {
                        driverTypePath = getDriverTypePath(configValue);
                    } else if (configKey.equalsIgnoreCase("OS")) {
                        operatingSystem = configValue;
                    } else if (configKey.equalsIgnoreCase("OS_Version")) {
                        os_Version = configValue;
                    } else if (configKey.equalsIgnoreCase("browserName")) {
                        browserName = configValue;
                    } else if (configKey.equalsIgnoreCase("browserVersion")) {
                        browserVersion = configValue;
                    }
                }
            }
        } else {
            driverTypePath = getDriverTypePath(driverType);
//            operatingSystem = "BrowserStackDriver.OS";
//            os_Version = "BrowserStackDriver.OS_Version";
//            browserName = "BrowserStackDriver.browserName";
//            browserVersion = "BrowserStackDriver.browserVersion";
        }
    }

    private String getDriverTypePath(String browserDriver) { 
        String driverTypePath = null;
//        if (browserDriver.equalsIgnoreCase("BrowserStackDriver")) {
//            driverTypePath = "steve.framework.BrowserStackDriver";
        if (browserDriver.equalsIgnoreCase("FirefoxDriver")) {
            driverTypePath = "org.openqa.selenium.firefox.FirefoxDriver";
        } else if (browserDriver.equalsIgnoreCase("ChromeDriver")) {
            driverTypePath = "org.openqa.selenium.chrome.ChromeDriver";
        } else if (browserDriver.equalsIgnoreCase("HtmlUnitDriver")) {
            driverTypePath = "org.openqa.selenium.htmlunit.HtmlUnitDriver";
        } else if (browserDriver.equalsIgnoreCase("InternetExplorerDriver")) {
            driverTypePath = "org.openqa.selenium.ie.InternetExplorerDriver";
        } else if (browserDriver.equalsIgnoreCase("SafariDriver")) {
            driverTypePath = "org.openqa.selenium.safari.SafariDriver";
        } else if (browserDriver.equalsIgnoreCase("PhantomJSDriver")) {
            driverTypePath = "org.openqa.selenium.phantomjs.PhantomJSDriver";
        }
        return driverTypePath;
    }

//    @PostConstruct
    public void init() {
        try {
            initializeDriver();
        } catch (WebDriverException e) {
            LOG.error("Error upon initializing a driver:" + e.getMessage());
            e.printStackTrace();

            LOG.warn("Trying to open browser again...");
            initializeDriver();
        }
    }

    private void initializeDriver(){
    	switch (browser) {
            case FIREFOX:
                FirefoxProfile profile = getFirefoxProfile();
                DesiredCapabilities firefoxCap = DesiredCapabilities.firefox();
                firefoxCap.setCapability(FirefoxDriver.PROFILE, profile);
//                firefoxCap.setCapability(CapabilityType.PROXY, createProxy());
                this.remoteWebDriver = new FirefoxDriver(firefoxCap);
                break;
//            case PHANTOMJS:
//                DesiredCapabilities phantomCap = DesiredCapabilities.phantomjs();
//                phantomCap.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//                        GHOST_DRIVER_PATH);
//                phantomCap.setCapability("takesScreenshot", true);
//                phantomCap.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
//                String[] phantomJsArgs = {"--web-security=no", "--ignore-ssl-errors=yes",
//                        "--proxy-type=none",
//                        "--disk-cache=yes", "--ssl-protocol=tlsv1", "--webdriver-loglevel=NONE"};
//                phantomCap.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "userAgent",
//                                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
//                phantomCap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomJsArgs);
//                phantomCap.setJavascriptEnabled(true);
//                Proxy proxy = new Proxy();
//                proxy.setAutodetect(true);
//                phantomCap.setCapability(CapabilityType.PROXY, proxy);
//                this.remoteWebDriver = new PhantomJSDriver(phantomCap);
//                break;
            case CHROME:
                DesiredCapabilities chromeCap = DesiredCapabilities.chrome();
                chromeCap.setJavascriptEnabled(true);
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                this.remoteWebDriver = new ChromeDriver(chromeCap);
                break;
            default:
                throw new UndefinedBrowserException("Undefined browser " + browser.getBrowser());
        }
        this.remoteWebDriver.manage().window().maximize();
        this.remoteWebDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        
        LOG.info(String.format("Browser Name is: %s and it's version is: %s", this.remoteWebDriver
                .getCapabilities().getBrowserName(), this.remoteWebDriver.getCapabilities().getVersion()));
    }
    
    private FirefoxProfile getFirefoxProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAlwaysLoadNoFocusLib(true);
        profile.setEnableNativeEvents(false);
        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setAcceptUntrustedCertificates(true);
        profile.setPreference("intl.accept_languages", "en-US, en");
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.panel.shown", false);
        profile.setPreference("browser.download.dir", DOWNLOAD_FILES_PATH); 
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/html, application/pdf, "
                + "application/octet-stream, application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, "
                + "application/zip");
        profile.setPreference("pdfjs.disabled", true); // disable the built-in PDF viewer
        return profile;
    }

    public void close() {
        try {
            if (this.remoteWebDriver != null) {
                this.remoteWebDriver.close();
                this.remoteWebDriver.quit();
            }
        } catch (WebDriverException ex) {
            LOG.error("Error upon closing a driver: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

//    public void startBrowserStackLocal() throws IOException, InterruptedException {
//    }

    private Proxy createProxy() {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(PROXY_URL).setFtpProxy(PROXY_URL).setSslProxy(PROXY_URL).setNoProxy("*Steve-app.com");
        return proxy;
    }

}