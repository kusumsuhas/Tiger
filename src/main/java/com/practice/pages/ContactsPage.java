package com.practice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.practice.master.Master;
import com.practice.util.CommonMethods;


public class ContactsPage extends Master{

	@FindBy(xpath = "//table[@class=\"layerPopupTransport\"]/tbody/tr/td[1]")
	WebElement elenoOfrecords;

	@FindBy(xpath = "//img[@src=\"themes/images/next.gif\"]")
	WebElement eleBtnNext;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/status.gif']")
	WebElement eleStatusBar;
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void displayContacts() {
		int noOfRecords = CommonMethods.noOfrecords(elenoOfrecords);
		CommonMethods.displayRecords(noOfRecords, 9, eleBtnNext, eleStatusBar);
		
	}
}
