package com.practice.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.practice.master.Master;
import com.practice.pages.HomePage;
import com.practice.pages.LoginPage;
import com.practice.util.Constants;

public class LoginPageTest extends Master {

	LoginPage loginPage;
	HomePage homePage;
	public Logger log = Logger.getLogger(LoginPageTest.class.getName());

	@BeforeClass
	public void init() {
		log.info("LoginPageTest BeforeSuite start executed::");
		initbrowser();
		loginPage = new LoginPage();
	}

	@Test
	public void LoginTest() {
		homePage = loginPage.clickOnLogin(prop.getProperty("username"), prop.getProperty("password"));
		log.info("homePage is Loaded");
	}

	@Test
	public void verifyHomePageTitle() {
		Assert.assertEquals(Constants.HOMEPAGETITLE, driver.getTitle());
		log.info("Title verified");
	}

}
