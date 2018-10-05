package com.practice.grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class ChromeGridTest {

	@Test
	public void test() throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("chrome");

		ChromeOptions options = new ChromeOptions();
		options.merge(cap);

		String hubURL = "http://192.168.0.3:4444/wd/hub";
		WebDriver driver = new RemoteWebDriver(new URL(hubURL), options);
		driver.get("http://localhost:8888");
		System.out.println(driver.getTitle());
	}
}
