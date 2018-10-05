package com.practice.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.practice.master.Master;
import com.practice.pages.HomePage;
import com.practice.pages.LoginPage;
import com.practice.pages.OrganizationsPage;
import com.practice.util.Constants;

public class OrganizationsPageTest extends Master {

	LoginPage loginPage;
	HomePage homePage;
	OrganizationsPage orgPage;

	@BeforeSuite
	public void init() {
		initbrowser();
		loginPage = new LoginPage();
		homePage = loginPage.clickOnLogin(prop.getProperty("username"), prop.getProperty("password"));
		orgPage = homePage.clickOnOrg();
	}

	@Test(priority = 0)
	public void verifyTitle() {
		Assert.assertEquals(Constants.ORGPAGETITLE,	driver.getTitle());
		log.info("Organizations Page title verified.");
	}

	@Test(priority = 1)
	public void DisplayRecords() {
		orgPage.displayOrgDetails();
	}
}
