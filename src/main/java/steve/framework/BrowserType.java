package steve.framework;

public enum BrowserType {

    FIREFOX("Firefox", "FirefoxDriver"),
    CHROME("Chrome", "ChromeDriver"),
    PHANTOMJS("PhantomJS", "PhantomJSDriver");

    private String browser;
    private String driver;

    BrowserType(String browser, String driver) {
        this.browser = browser;
        this.driver = driver;
    }

    public String getBrowser() {
        return this.browser;
    }
    
    public String getDriver() {
        return this.driver;
    }

    public static BrowserType getByBrowserName(String name) {
        for (BrowserType environmentType : BrowserType.values()) {
            if (environmentType.getBrowser().equalsIgnoreCase(name))
                return environmentType;
        }
        throw new RuntimeException("Incorrect name \"" + name + "\" of browser");
    }
    
    public static BrowserType getByDriverName(String name) {
        for (BrowserType environmentType : BrowserType.values()) {
            if (environmentType.getDriver().equalsIgnoreCase(name))
                return environmentType;
        }
        throw new RuntimeException("Incorrect name \"" + name + "\" of driver");
    }
}
