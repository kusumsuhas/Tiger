package com.practice.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.practice.master.Master;
import com.practice.pages.HomePage;
import com.practice.pages.LeadsPage;
import com.practice.pages.LoginPage;
import com.practice.util.Constants;

public class LeadsPageTest extends Master {

	LoginPage loginPage;
	HomePage homePage;
	LeadsPage leadsPage;

	@BeforeSuite
	public void init() {
		initbrowser();
		loginPage = new LoginPage();
		homePage = loginPage.clickOnLogin(prop.getProperty("username"), prop.getProperty("password"));
		leadsPage = homePage.clickOnleads();
	}

	//@Test(priority = 0)
	public void verifyTitle() {
		Assert.assertEquals(Constants.LEADSPAGETITLE, driver.getTitle());
	}

	@Test (priority=2)
	public void GetLeadsInfo() {
		leadsPage.getRecords();
	}

	//@Test(priority = 3)
	public void createLeadTest() {
		leadsPage.createLead();
	}
	
	//@Test(priority=4)
	public void deleteLead() {
		leadsPage.deleteLead("LEA37");
	}
	
	//@Test
	public void searchRecord() {
		leadsPage.searchRecord("rajk");
	}

}
