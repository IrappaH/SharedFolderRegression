package cocoro.lib.uiauto;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import cocoro.enums.ConfigType;
import cocoro.lib.central.ContextConfiguration;

public class DriverFactory {

	private RemoteWebDriver driver = null;

	public RemoteWebDriver getDriver() {
		return driver;
	}

	public void setDriver(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public RemoteWebDriver seleniumGridDriver(String browser, String url, String port) throws Exception {

		// a composition of the target grid address and port
		String GRID_URL = "http://" + url + ":" + port + "/wd/hub";

		return new RemoteWebDriver(new URL(GRID_URL), returnCapability(browser));

	}

	private DesiredCapabilities returnCapability(String browser) throws Exception {
		DesiredCapabilities desiredCapabilities;

		switch (browser.toLowerCase()) {

		case "chrome":
			desiredCapabilities = DesiredCapabilities.chrome();
			break;

		case "firefox":
			desiredCapabilities = DesiredCapabilities.firefox();
			break;

		case "msedge":
			desiredCapabilities = DesiredCapabilities.edge();
			desiredCapabilities.setPlatform(Platform.WINDOWS);
			break;
		case "safari":
			desiredCapabilities = DesiredCapabilities.safari();
			desiredCapabilities.setPlatform(Platform.MAC);
			break;

		default:
			throw new Exception("Browser not supported or misspelled");
		}

		return desiredCapabilities;
	}

	public DriverFactory createDriver(ContextConfiguration cConfig) throws Exception {

		String browser = cConfig.getConfigValue(ConfigType.BROWSER);
		String url = cConfig.getConfigValue(ConfigType.GRID_URL)== null?"":cConfig.getConfigValue(ConfigType.GRID_URL);
		String port = cConfig.getConfigValue(ConfigType.GRID_PORT)== null?"":cConfig.getConfigValue(ConfigType.GRID_PORT);

		if (!url.isEmpty() && !port.isEmpty()) {
			setDriver(seleniumGridDriver(browser, url, port));
		} else {
			setDriver(testngDriver(browser));
		}
		getDriver().manage().timeouts().implicitlyWait(Long.parseLong(cConfig.getConfigValue(ConfigType.WAIT_IMPLICIT)),
				TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		return this;
	}

	public RemoteWebDriver testngDriver(String browser) throws Exception {

		String dir = System.getProperty("user.dir");
		RemoteWebDriver driver = null;
		switch (browser) {
		case "chrome":
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("disable-infobars");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", dir + "/driver/chromedriver-128.exe");
			driver = new ChromeDriver(options);

			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", dir + "\\IEDriverServer.exe");
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieOptions.setCapability("nativeEvents", false);
			driver = new InternetExplorerDriver(ieOptions);
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", dir + "/driver/msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", dir + "\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "safari": // this will not work;
			driver = new SafariDriver();
			break;
		default:
			throw new Exception("Browser not supported or misspelled");
		}

		return driver;

	}

}
