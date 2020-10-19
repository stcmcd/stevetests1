package steve.framework;

import steve.configuration.SystemProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;


public class BrowserStackDriver extends RemoteWebDriver {

    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BrowserStackDriver.class);
    private static final String BROWSER_STACK_DRIVER_URL = "http://nirajpatel:r6u9ugARJEKBJQsdZ9Xx@hub.browserstack.com/wd/hub";

    public BrowserStackDriver() {
        super(createURl(), caps());
    }

    private static URL createURl() {
        try {
            return new URL(BROWSER_STACK_DRIVER_URL);
        } catch (MalformedURLException e) {
            LOG.warn(e.getMessage());
        }
        return null;
    }

    private static Capabilities caps() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String buildName = SystemProperties.getCurrentShortDate() + " "
                + WebDriverDiscovery.getBrowserName() + " "
                + WebDriverDiscovery.getBrowserVersion() + " "
                + WebDriverDiscovery.getOperatingSystem() + " "
                + WebDriverDiscovery.getOs_Version();
        capabilities.setCapability("browser", WebDriverDiscovery.getBrowserName());
        capabilities.setCapability("browser_version", WebDriverDiscovery.getBrowserVersion());
        capabilities.setCapability("os", WebDriverDiscovery.getOperatingSystem());
        capabilities.setCapability("os_version", WebDriverDiscovery.getOs_Version());
        capabilities.setCapability("resolution", "1024x768");
        capabilities.setCapability("build", buildName);
        capabilities.setCapability("browserstack.local", false);
        capabilities.setCapability("browserstack.debug", true);
        capabilities.setCapability("browserstack.ie.noFlash", true);
        capabilities.setCapability("browserstack.ie.enablePopups", true);
        capabilities.setCapability("name", "Steve Automation Tests");
        LOG.info("capabilities : " + capabilities.asMap().toString());
        return capabilities;
    }

}
