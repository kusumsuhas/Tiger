package com.practice.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.practice.master.Master;
import com.practice.pages.HomePage;
import com.practice.pages.LoginPage;
import com.practice.pages.OpportunitiesPage;
import com.practice.util.Constants;

public class OpportunitiesPageTest extends Master {

	LoginPage loginPage;
	HomePage homePage;
	OpportunitiesPage opporPage;

	@BeforeClass
	public void init() {
		initbrowser();
		loginPage = new LoginPage();
		homePage = loginPage.clickOnLogin(prop.getProperty("username"), prop.getProperty("password"));
		opporPage = homePage.clickOnOppor();
	}

	@Test(priority = 0)
	public void verifyTitle() {
		Assert.assertEquals(Constants.OPPORTUNITIESPAGETITLE, driver.getTitle());
		log.info("Opportunities Page title verified.");
	}

	@Test(priority = 1)
	public void displayOppertunities() {
		opporPage.displayOpportunities();
	}
}
