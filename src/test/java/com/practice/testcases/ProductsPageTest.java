package com.practice.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.practice.master.Master;
import com.practice.pages.HomePage;
import com.practice.pages.LoginPage;
import com.practice.pages.ProductsPage;
import com.practice.util.Constants;

public class ProductsPageTest extends Master {

	LoginPage loginPage;
	HomePage homePage;
	ProductsPage productsPage;

	@BeforeSuite
	public void init() {
		initbrowser();
		loginPage = new LoginPage();
		homePage = loginPage.clickOnLogin(prop.getProperty("username"), prop.getProperty("password"));
		productsPage = homePage.clicOnProducts();
	}

	@Test(priority = 0)
	public void verifyTitle() {
		Assert.assertEquals(Constants.PRODUCTSPAGETITLE, driver.getTitle());
		log.info("Organizations Page title verified.");
	}

	@Test(priority = 1)
	public void DisplayRecords() {
		productsPage.displayProductDetails();
	}
}
