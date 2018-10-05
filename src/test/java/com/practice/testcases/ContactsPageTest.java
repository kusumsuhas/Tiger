package com.practice.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.practice.master.Master;
import com.practice.pages.ContactsPage;
import com.practice.pages.HomePage;
import com.practice.pages.LoginPage;
import com.practice.util.Constants;

public class ContactsPageTest extends Master{

	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	@BeforeSuite
	public void init() {
		initbrowser();
		loginPage = new LoginPage();
		homePage = loginPage.clickOnLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.clickOnContacts();
	}
	
	@Test(priority=0)
	public void verifyTitle() {
		Assert.assertEquals(Constants.CONTACTSPAGETITLE, driver.getTitle());
	}
	
	@Test (priority=1)
	public void displayContacts() {
		contactsPage.displayContacts();
	}
}
