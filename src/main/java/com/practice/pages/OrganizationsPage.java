package com.practice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.practice.master.Master;
import com.practice.util.CommonMethods;

public class OrganizationsPage extends Master {

	@FindBy(xpath = "//table[@class=\"layerPopupTransport\"]/tbody/tr/td[1]")
	WebElement elenoOfrecords;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/status.gif']")
	WebElement eleStatusBar;
	
	@FindBy(xpath = "//img[@src=\"themes/images/next.gif\"]")
	WebElement eleBtnNext;
	
	public OrganizationsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void displayOrgDetails() {
		int totalRecords = CommonMethods.noOfrecords(elenoOfrecords);
		CommonMethods.displayRecords(totalRecords,7, eleBtnNext, eleStatusBar);
	}

}
