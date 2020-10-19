package steve.framework;

import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class ScreenShotHook {

    protected static final Logger LOG = LoggerFactory.getLogger(ScreenShotHook.class);

    @Autowired
    private WebDriverDiscovery webDriverDiscovery;
    
    @After
    public void embedScreenShot(Scenario scenario) {
        try {
            Map<String, Object> screenShots = ScreenShots.getScreenShotsForCurrentTest();
            for (Map.Entry<String, Object> screenShot : screenShots.entrySet()) {
                scenario.write(screenShot.getKey());
                scenario.embed((byte[]) screenShot.getValue(), "image/png");
            }

            ScreenShots.tidyUpAfterTestRun();

            if (scenario.isFailed() && webDriverDiscovery.getRemoteWebDriver() != null) {
                scenario.write(webDriverDiscovery.getRemoteWebDriver().getCurrentUrl());
                byte[] screenShot = ((TakesScreenshot) webDriverDiscovery.getRemoteWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenShot, "image/png");
            }

        } catch (WebDriverException wde) {
            LOG.error(wde.getMessage());
        }
    }
}
