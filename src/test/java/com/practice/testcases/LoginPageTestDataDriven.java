package com.practice.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.excel.ReadExcel;
import com.practice.master.Master;
import com.practice.pages.HomePage;
import com.practice.pages.LoginPage;
import com.practice.util.Constants;

public class LoginPageTestDataDriven extends Master {

	LoginPage loginPage;
	HomePage homePage;

	@BeforeClass
	public void init() {
		initbrowser();
		loginPage = new LoginPage();
	}

	@DataProvider(name = "testdata")
	public Object[][] getdata() {
		Object[][] data = ReadExcel.excelData();
		return data;
	}

	@Test(dataProvider = "testdata", priority = 0)
	public void LoginTest(String userName, String password) {
		loginPage.clickOnLogin(userName, password);
		log.info("Logged with " + userName + " " + password);
		loginPage.logOut();
	}

	@Test
	public void verifyHomePageTitle() {
		Assert.assertEquals(Constants.HOMEPAGETITLE, driver.getTitle());
		log.info("HomePage Title verified");
	}

}
