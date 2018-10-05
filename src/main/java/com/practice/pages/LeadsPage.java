package com.practice.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.practice.master.Master;
import com.practice.util.CommonMethods;

public class LeadsPage extends Master {

	@FindBy(xpath = "//table[@class=\"lvt small\"]/tbody")
	WebElement eleTable;

	@FindBy(xpath = "//img[@src=\"themes/softed/images/btnL3Add.gif\"]")
	WebElement eleBtnCreateLead;

	@FindBy(xpath = "//table[@class=\"layerPopupTransport\"]/tbody/tr/td[1]")
	WebElement elenoOfrecords;

	@FindBy(xpath = "//img[@src=\"themes/images/next.gif\"]")
	WebElement eleBtnNext;

	@FindBy(name = "firstname")
	WebElement eleFirstName;

	@FindBy(name = "lastname")
	WebElement eleLastname;

	@FindBy(name = "company")
	WebElement eleCompany;

	@FindBy(name = "designation")
	WebElement eleTitle;

	@FindBy(name = "mobile")
	WebElement eleMobile;

	@FindBy(name = "email")
	WebElement eleMail;

	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	WebElement eleBtnSave;

	@FindBy(xpath = "//img[@src='themes/softed/images/status.gif']")
	WebElement eleStatusBar;

	@FindBy(xpath = "//input[@class=\"crmbutton small delete\"]")
	WebElement eleBtnDelete;

	@FindBy(name = "search_text")
	WebElement eleSearchFor;

	@FindBy(id = "bas_searchfield")
	WebElement eleSearchFiled;

	@FindBy(xpath = "//input[@value=' Search Now ' and @name='submit']")
	WebElement eleBtnSearchNow;

	@FindBy(xpath = "//*[contains(text(),'No Lead found')]")
	WebElement eleNoLeadsFound;

	public LeadsPage() {
		PageFactory.initElements(driver, this);
	}

/*	private void readRecords(int pageSize) {
		String firstXPath = "//table[@class=\"lvt small\"]/tbody/tr[";
		String secondXPath = "]/td[";

		for (int i = 3; i < pageSize + 3; i++) {
			for (int j = 2; j <= 9; j++) {
				String finalXPath = firstXPath + i + secondXPath + j + "]";
				System.out.print(driver.findElement(By.xpath(finalXPath)).getText() + " || ");
			}
			System.out.println();
		}
	}*/
	
	public void getRecords() {
	
		int totalRecords = CommonMethods.noOfrecords(elenoOfrecords);
		CommonMethods.displayRecords(totalRecords, 9, eleBtnNext, eleStatusBar);
		}

	public void createLead() {
		eleBtnCreateLead.click();
		log.info("Create lead Button clicked:");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(eleFirstName));
		eleFirstName.sendKeys("FirstName2");
		eleLastname.sendKeys("LastName2");
		eleCompany.sendKeys("Company2");
		eleMobile.sendKeys("9876543211");
		eleMail.sendKeys("Mail2@gmail.com");
		eleBtnSave.click();
		log.info("Successfully created Lead.");
	}

	public void deleteLead(String leadNo) {
		boolean btnEnable = true;
		int totalRecords = CommonMethods.noOfrecords(elenoOfrecords);
		int noOfPages = totalRecords / 20;
		int pageSize = 20;
		int lastPageSize = totalRecords % 20;

		String firstXPath = "//table[@class=\"lvt small\"]/tbody/tr[";
		String secondXPath = "]/td[2]";

		for (int i = 0; i <= noOfPages; i++) {
			if (i == noOfPages) {
				pageSize = lastPageSize;
				btnEnable = false;
			}
			for (int k = 3; k <= pageSize + 3; k++) {
				String finalXPath = firstXPath + k + secondXPath;
				String tableLaedNo = driver.findElement(By.xpath(finalXPath)).getText();
				if (leadNo.equalsIgnoreCase(tableLaedNo)) {
					log.info("Lead Found");
					String eleLeadXPath = "//table[@class=\"lvt small\"]/tbody/tr[" + k + "]/td[1]";
					driver.findElement(By.xpath(eleLeadXPath)).click();
					log.info("Lead Record Selected..");
					eleBtnDelete.click();
					Alert alert = driver.switchTo().alert();
					alert.accept();
					log.info("Record deleted...");
				} else {
					log.info("Enter proper Lead No:");
				}
				break;
			}

			if (btnEnable == true) {
				eleBtnNext.click();
				WebDriverWait wait = new WebDriverWait(driver, 40);
				wait.until(ExpectedConditions.invisibilityOf(eleStatusBar));
			}
		}
	}

	public void searchRecord(String strSearchFor) {
		eleSearchFor.clear();
		eleSearchFor.sendKeys(strSearchFor);
		Select select = new Select(eleSearchFiled);
		select.selectByValue("firstname");
		eleBtnSearchNow.click();
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOf(eleStatusBar));

		int searchRecordCount = CommonMethods.noOfrecords(elenoOfrecords);
		if (searchRecordCount == 0) {
			System.out.println("No Leads Found");
		} else {
			System.out.println("Leads Found : " + searchRecordCount);
		}
	}
}
