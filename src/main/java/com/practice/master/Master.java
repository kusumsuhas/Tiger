package com.practice.master;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.practice.util.Constants;

public class Master {

	public static WebDriver driver;
	public FileInputStream FIS;
	public Properties prop;
	public Logger log = Logger.getLogger(Master.class.getName());
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		PropertyConfigurator.configure(Constants.LOG4JFILEPATH);
		log.info("Master Properties are configured:");
		htmlReporter = new ExtentHtmlReporter(Constants.EXTENTREPORTFILEPATH);

		log.info("Report is created");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("TEST REPORT");
		htmlReporter.config().setReportName("SIT REPORT");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().getClass();
	}

	@BeforeMethod
	public void initExtent(Method method) {
		test = extent.createTest(getClass().getSimpleName() + " -- " + method.getName());
		log.info("Method is created" + method.getName());
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " is PASSED", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "is Failed", ExtentColor.RED));
			test.fail(result.getThrowable());
		}
		extent.flush();
	}

	public void loadProp() {
		try {
			FIS = new FileInputStream(Constants.CONFIGFILEPATH);
			prop = new Properties();
			prop.load(FIS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVERPATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-infobars");
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", Constants.GECKODRIVERPATH);
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, Constants.FIREFOXLOGSPATH);

			FirefoxProfile fireProfile = new FirefoxProfile();
			fireProfile.setPreference("browser.private.browsing.autostart", true);
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", Constants.IEDRIVERPATH);
			InternetExplorerOptions options = new InternetExplorerOptions().requireWindowFocus();
			driver = new InternetExplorerDriver(options);
		}
		driver.manage().window().maximize();
	}

	public void initbrowser() {
		loadProp();
		log.info("Properties are Loaded");
		selectBrowser(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGELOADTIMEOUTWAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.PAGELOADTIMEOUTWAIT, TimeUnit.SECONDS);
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		driver.quit();
		// extent.flush();
	}

}
