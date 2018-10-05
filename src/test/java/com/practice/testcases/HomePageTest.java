package com.practice.testcases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.practice.master.Master;
import com.practice.pages.CalendarPage;
import com.practice.pages.ContactsPage;
import com.practice.pages.DashboardPage;
import com.practice.pages.DocumentsPage;
import com.practice.pages.HomePage;
import com.practice.pages.LeadsPage;
import com.practice.pages.LoginPage;
import com.practice.pages.OpportunitiesPage;
import com.practice.pages.OrganizationsPage;
import com.practice.pages.ProductsPage;
import com.practice.util.CommonMethods;
import com.practice.util.Constants;

public class HomePageTest extends Master {

	LoginPage loginPage;
	HomePage homePage;
	LeadsPage leadsPage;
	CalendarPage calendarPage;
	OrganizationsPage orgPage;
	ContactsPage contactsPage;
	OpportunitiesPage opporPage;
	ProductsPage productsPage;
	DocumentsPage documentsPage;
	DashboardPage dashboardPage;
	// public Logger log = Logger.getLogger(HomePageTest.class.getName());

	@FindBy(xpath = "//td[@class=\"calHdr calTopRight componentName\"]")
	WebElement eleCalendorLabel;

	@BeforeClass(alwaysRun = true)
	public void init() {
		System.out.println("execution started");
		initbrowser();
		loginPage = new LoginPage();
		homePage = loginPage.clickOnLogin(prop.getProperty("username"), prop.getProperty("password"));
		leadsPage = new LeadsPage();
	}

	@Test(priority = 0, groups = { "Sanity" })
	public void verifyHomePageTitle() {
		Assert.assertEquals("Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM", driver.getTitle());
		log.info("Homepage Title Verified..");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// @Test (priority = -1)
	public void linksVerify() {
		CommonMethods.verifyAllLinks();
	}

	@Test(priority = 1, groups = { "Sanity" })
	public void CalendarPageTest() {
		calendarPage = homePage.clickonCalendor();
		log.info("Navigated to CalendarPage");
	}

	@Test(priority = 2, groups = { "Sanity" })
	public void verifyCalendarPageTitle() {
		Assert.assertEquals(Constants.CALENDARPAGETITLE, driver.getTitle());
		log.info("Calendar Page title verified.");
	}

	@Test(priority = 3, groups = { "Sanity" })
	public void orgPageTest() {
		orgPage = homePage.clickOnOrg();
		log.info("Organizations Page is loaded");
	}

	@Test(priority = 4, groups = { "Sanity" })
	public void verifyOrgPageTitle() {
		Assert.assertEquals(Constants.ORGPAGETITLE,	driver.getTitle());
		log.info("Organizations Page title verified.");
	}

	@Test(priority = 5, groups = { "Sanity" })
	public void contactsPageTest() {
		contactsPage = homePage.clickOnContacts();
		log.info(" Contacts Page is loaded.");
	}

	@Test(priority = 6, groups = { "Sanity" })
	public void verifyContactsPageTitle() {
		Assert.assertEquals(Constants.CONTACTSPAGETITLE, driver.getTitle());
		log.info("Contacts Page title verified.");
	}

	@Test(priority = 7)
	public void opportunitiesPageTest() {
		opporPage = homePage.clickOnOppor();
		log.info("Opportunities page loaded");
	}

	@Test(priority = 8)
	public void verifyOppoPageTitle() {
		Assert.assertEquals(Constants.OPPORTUNITIESPAGETITLE, driver.getTitle());
		log.info("Opportunities Page title verified");
	}

	@Test(priority = 9)
	public void productsPageTest() {
		productsPage = homePage.clicOnProducts();
		log.info("Products Page is loaded");
	}

	@Test(priority = 10)
	public void verifyProductsPageTitle() {
		Assert.assertEquals(Constants.PRODUCTSPAGETITLE, driver.getTitle());
		log.info("Verified Products page title");
	}

	@Test(priority = 11)
	public void documentsPageTest() {
		documentsPage = homePage.clickOnDocuments();
		log.info("Documents Page is loaded");
	}

	@Test(priority = 12)
	public void verifyDocumentsPagetitle() {
		Assert.assertEquals(Constants.DOCUMENTSPAGETITLE, driver.getTitle());
		log.info("Documents Page title verified");
	}

	@Test(priority = 13)
	public void dashboardPageTest() {
		dashboardPage = homePage.clickOnDashboard();
		log.info("Dashboard Page is loaded");
	}

	@Test(priority = 14)
	public void verifyDashboardPageTitle() {
		Assert.assertEquals(Constants.DASHBOARDPAGETITLE, driver.getTitle());
		log.info("Dashboard Page title verified.");
	}
}
