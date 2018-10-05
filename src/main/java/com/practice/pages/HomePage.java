package com.practice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.practice.master.Master;
import com.practice.util.CommonMethods;

public class HomePage extends Master {

	@FindBy (xpath="//a[@href=\"index.php?module=Leads&action=index\"]")
	WebElement eleBtnLeads;
	
	@FindBy (xpath="//a[@href=\"index.php?module=Leads&action=index\"]//following::a[1]")
	WebElement eleBtnOrg;
	
	@FindBy(xpath="//a[@href=\"index.php?module=Calendar&action=index\"]")
	WebElement eleBtnCalendar; 
	
	@FindBy(xpath="//a[@href=\"index.php?module=Accounts&action=index\"]")
	WebElement eleBtnOrganizations;
	
	@FindBy(xpath="//a[@href=\"index.php?module=Contacts&action=index\"]")
	WebElement eleBtnContacts;
	
	@FindBy(xpath="//a[@href=\"index.php?module=Potentials&action=index\"]")
	WebElement eleBtnOpportunities;
	
	@FindBy(xpath="//a[@href=\"index.php?module=Products&action=index\"]")
	WebElement eleBtnProducts;
	
	@FindBy(xpath="//a[@href=\"index.php?module=Documents&action=index\"]")
	WebElement eleBtnDocuments;
	
	@FindBy(xpath="//a[@href=\"index.php?module=Dashboard&action=index\"]")
	WebElement eleBtnDashboard;
	
	@FindBy(xpath="//a[@href=\"index.php?module=Emails&action=index\"]")
	WebElement eleBtnEmail;

	@FindBy(xpath="//td[@class=\"calHdr calTopRight componentName\"]")
	WebElement eleCalendorLabel;
	
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public LeadsPage clickOnleads() {
		eleBtnLeads.click();
		return new LeadsPage();
	}
	
	public CalendarPage clickonCalendor() {
		eleBtnCalendar.click();
		return new CalendarPage();
	}
	
	public OrganizationsPage clickOnOrg() {
		eleBtnOrganizations.click();
		return new OrganizationsPage();
	}
	
	public ContactsPage clickOnContacts() {
		eleBtnContacts.click();
		return new ContactsPage();
	}
	
	public OpportunitiesPage clickOnOppor() {
		eleBtnOpportunities.click();
		return new OpportunitiesPage();
	}
	
	public ProductsPage clicOnProducts() {
		eleBtnProducts.click();
		return new ProductsPage();
	}
	
	public DocumentsPage clickOnDocuments() {
		eleBtnDocuments.click();
		return new DocumentsPage();
	}
	
	public DashboardPage clickOnDashboard() {
		eleBtnDashboard.click();
		return new DashboardPage();
	}
}
