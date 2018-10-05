package com.practice.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.practice.master.Master;
import com.practice.pages.CalendarPage;
import com.practice.pages.HomePage;
import com.practice.pages.LoginPage;
import com.practice.util.Constants;

public class CalendarPageTest extends Master{

	LoginPage loginPage;
	HomePage homePage;
	CalendarPage calendarPage;
	
	
	@BeforeClass
	public void init() {
		initbrowser();
		loginPage = new LoginPage();
		homePage = loginPage.clickOnLogin(prop.getProperty("username"), prop.getProperty("password"));
		calendarPage = homePage.clickonCalendor();
	}
	
	@Test(priority=1)
	public void verifyTitle() {
		Assert.assertEquals(Constants.CALENDARPAGETITLE, driver.getTitle());
		log.info("Calendar Page title verified.");
	}
	
	@Test (priority=2)
	public void addEventTest() {
		calendarPage.addEvent("2019","May","19");
	}
}
