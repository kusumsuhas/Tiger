package com.practice.master;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.practice.util.ExtentManager;

public class TestBase {

	public  WebDriver driver;
	public FileInputStream FIS;
	public Properties prop;
	public Logger log = Logger.getLogger(TestBase.class.getName());
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public ExtentTest test;
	protected final String htmlFilePath = "E:\\Temp Eclipse Repository\\Tiger\\src\\main\\java\\com\\practice\\reports\\TestReport.html";
	
	private static TestBase tb = null;
	
	private TestBase() {
		
		initbrowser(); 
	}
	
	public static TestBase getTestBaseinstance() {
		if (tb==null) {
			tb = new TestBase();
		} 
		return tb;
	}
	
	@BeforeSuite
	public void initLogs() {
		String logPath = "E:\\Temp Eclipse Repository\\Tiger\\Log4J.properties";
		PropertyConfigurator.configure(logPath);
		
		/*htmlReporter = new ExtentHtmlReporter(
				"E:\\Temp Eclipse Repository\\Tiger\\src\\main\\java\\com\\practice\\reports\\TestRepor.html");
		log.info("Report is created");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("TEST REPORT");
		htmlReporter.config().setReportName("SIT REPORT");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);*/
		
		/*htmlReporter = new ExtentHtmlReporter(htmlFilePath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);*/
		
		extent = ExtentManager.getInstance();
		
	}
	
	@BeforeTest
	public void beforeTest() {
		test = extent.createTest(getClass().getSimpleName());
	}

	@BeforeMethod
	public void initExtent(Method method) {
		log.info("Method is created" + method.getName());
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " is PASSED", ExtentColor.GREEN));
		} else if (result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ "is Failed", ExtentColor.RED));
			test.fail(result.getThrowable());
		}
	}
	private void loadProp() {
		String filePath = "E:\\Temp Eclipse Repository\\Tiger\\src\\main\\java\\com\\practice\\config\\config.Properties";
		try {
			FIS = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(FIS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void selectBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"E:\\Trainings\\Selenium\\Drivers\\chromedriver_win32_V2.38\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-infobars");
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"E:\\Trainings\\Selenium\\Drivers\\geckodriver-v0.20.1-win64\\geckodriver.exe");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "E:\\logs.txt");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver",
					"E:\\Trainings\\Selenium\\Drivers\\IEDriverServer_x64_3.13.0\\IEDriverServer.exe");
			InternetExplorerOptions options = new InternetExplorerOptions().requireWindowFocus();
			driver = new InternetExplorerDriver(options);
		}
		driver.manage().window().maximize();
	}

	private void initbrowser() {
		loadProp();
		log.info("Properties are Loaded");
		selectBrowser(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	// @Test
	public void test1() {
		loadProp();
		log.info("Properties are loaded");
		// selectBrowser(prop.getProperty("browser"));
		// driver.get(prop.getProperty("url"));
	}

}
