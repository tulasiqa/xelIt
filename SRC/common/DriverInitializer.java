package common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverInitializer extends ExtentReportNG {
	

	public DesiredCapabilities capabilities;
	// public static ExtentReports extent;
	public static final ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<RemoteWebDriver>();

	@Parameters({ "browser", "appUrl" })
	@BeforeTest

	public WebDriver openBrowser(String browser, String appUrl) throws MalformedURLException {
		// launch the specified browser

		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", gecko_driver);
				FirefoxProfile fprofile = new FirefoxProfile();
				fprofile.setAcceptUntrustedCertificates(true);
				fprofile.setAssumeUntrustedCertificateIssuer(false);
				fprofile.setPreference("browser.download.dir", filepath);
				fprofile.setPreference("browser.download.folderList", 2);
				fprofile.setPreference("browser.download.manager.showWhenStarting", false);
				fprofile.setPreference("pdfjs.disabled", true);
				fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;" + "application/pdf;" 
						+"image/jpeg"+ "application/msword" + "application/vnd.openxmlformats-officedocument.presentationml.presentation"
						+ "application/video/mp4" + "application/zip"
						+ "application/wav"+ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
						+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document;"
						+ "text/plain;" // MIME types Of text File.
						+ "text/csv"); // MIME types Of CSV File.
				fprofile.setPreference("geo.enabled", false);
				fprofile.setPreference("geo.provider.use_corelocation", false);
				fprofile.setPreference("geo.prompt.testing", false);
				fprofile.setPreference("geo.prompt.testing.allow", false);
				fprofile.setPreference("permissions.default.desktop-notification", 1);
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				new FirefoxOptions().setProfile(fprofile).addTo(capabilities);
				capabilities.setPlatform(Platform.WINDOWS);
				capabilities.setBrowserName("firefox");

			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chrome_driver);

				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", filepath);
				ChromeOptions options = new ChromeOptions();
				HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
				options.setExperimentalOption("prefs", chromePrefs);
				capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities.setPlatform(Platform.WINDOWS);
				capabilities.setBrowserName("chrome");
				

			} else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", ie_driver);
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setPlatform(Platform.WINDOWS);

			}
			webDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
			driver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver().manage().window().maximize();
			webDriver.get().get(appUrl);
			return webDriver.get();

		} catch (Exception e) {
			e.getMessage();
		}
		return webDriver.get();
	}

	public WebDriver driver() {
		return webDriver.get();
	}

}
