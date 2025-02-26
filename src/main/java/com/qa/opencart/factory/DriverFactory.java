package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	private static final Logger log = LogManager.getLogger(DriverFactory.class);

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
//		System.out.println("browser name : " + browserName);
		log.info("browser name : " + browserName);

		optionsManager = new OptionsManager(prop);

		boolean remoteExecution = Boolean.parseBoolean(prop.getProperty("remote"));
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			if (remoteExecution) {
				//run test cases on remote/grid
				initRemoteDriver("chrome");
			} else {
				//run test cases on local
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox":
			if (remoteExecution) {
				//run test cases on remote/grid
				initRemoteDriver("firefox");
			} else {
				//run test cases on local
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
		case "edge":
			if (remoteExecution) {
				//run test cases on remote/grid
				initRemoteDriver("edge");
			} else {
				//run test cases on local
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;
		default:
			log.error("Please pass the right browser.." + browserName);
			throw new FrameworkException("Invalid browser name.." + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	private void initRemoteDriver(String browserName) {
		System.out.println("Running tests on grid on " + browserName);
		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;

			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;

			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
				break;

			default:
				break;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * return driver using threadLocal
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	// mvn clean install -Denv="qa"
	public Properties initProp() {

		String envName = System.getProperty("env");
		System.out.println("Running test suite on env : " + envName);
		log.info("Running test suite on env : " + envName);
		FileInputStream fis = null;
		prop = new Properties();

		try {
			if (envName == null) {
//				System.out.println("No env is passed, hence running test suite in qa env...");
				log.warn("No env is passed, hence running test suite in qa env...");
				fis = new FileInputStream(AppConstants.QA_CONFIG_PROP_FILE_PATH);
			} else {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					fis = new FileInputStream(AppConstants.QA_CONFIG_PROP_FILE_PATH);
					break;
				case "dev":
					fis = new FileInputStream(AppConstants.DEV_CONFIG_PROP_FILE_PATH);
					break;
				case "stage":
					fis = new FileInputStream(AppConstants.STAGE_CONFIG_PROP_FILE_PATH);
					break;
				case "uat":
					fis = new FileInputStream(AppConstants.UAT_CONFIG_PROP_FILE_PATH);
					break;
				case "prod":
					fis = new FileInputStream(AppConstants.PROD_CONFIG_PROP_FILE_PATH);
					break;

				default:
//					System.out.println("Please pass the right env name..." + envName);
					log.error("Please pass the right env name..." + envName);
					throw new FrameworkException("===INVALID ENV===");
				}
			}
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return prop;
	}

	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}
}