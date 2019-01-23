package com.practice.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.practice.master.Master;
import com.practice.util.CommonMethods;

public class CalendarPage extends Master {

	@FindBy(xpath = "//td[@class=\"calAddButton\"]")
	WebElement eleBtnAddEvent;

	@FindBy(id = "addcall")
	WebElement eleBtnAddCall;

	@FindBy(id = "addmeeting")
	WebElement eleBtnAddMeeting;

	@FindBy(id = "addtodo")
	WebElement eleBtnAddToDo;

	@FindBy(id = "activitytype")
	WebElement dropEventType;

	@FindBy(name = "subject")
	WebElement eleEventName;

	@FindBy(name = "description")
	WebElement eleDescription;

	@FindBy(name = "location")
	WebElement eleLocation;

	@FindBy(name = "eventstatus")
	WebElement eleEventStatus;

	@FindBy(name = "starthr")
	WebElement eleStartHr;

	@FindBy(name = "startmin")
	WebElement eleStartMin;

	@FindBy(name = "startfmt")
	WebElement eleStartAMPM;

	@FindBy(name = "endhr")
	WebElement eleEndHr;

	@FindBy(name = "endmin")
	WebElement eleEndMin;

	@FindBy(name = "endfmt")
	WebElement eleEndAMPM;

	@FindBy(name = "eventsave")
	WebElement eleSave;

	@FindBy(id = "jscal_trigger_date_start")
	WebElement eleBtnSetDate;

	@FindBy(xpath = "//div[@class=\"calendar\"]/table/thead/tr[1]/td[2]")
	WebElement eleSelectMothYear;

	@FindBy(xpath = "//div[@class=\"calendar\"]/table/thead/tr[2]/td[4]")
	WebElement eleMonthNext;

	@FindBy(xpath = "//div[@class=\"calendar\"]/table/thead/tr[2]/td[5]")
	WebElement eleYearnext;

	@FindBy(xpath = "//div[@class=\"calendar\"]/table/thead/tr[2]/td[2]")
	WebElement eleMonthPrevious;

	@FindBy(xpath = "//div[@class=\"calendar\"]/table/tbody/tr")
	WebElement eleDayRows;

	@FindBy(xpath = "//div[@class=\"calendar\"]")
	WebElement eleDayRow;

	@FindBy(name = "date_start")
	WebElement eleStartDate;

	public CalendarPage() {
		PageFactory.initElements(driver, this);
	}

	
	
	public void addEvent(String strYear, String strMonth, String strDate) {
		Actions action = new Actions(driver);
		action.moveToElement(eleBtnAddEvent);
		action.perform();
		eleBtnAddCall.click();
		Set<String> windows = driver.getWindowHandles();
		String childWindow = driver.getWindowHandle();
		Iterator<String> it = windows.iterator();
		it.hasNext();
		driver.switchTo().window(childWindow);

		eleEventName.sendKeys("event1");
		eleDescription.sendKeys("Description");
		eleLocation.sendKeys("Hyderabad");

		CommonMethods.selectDropDown(eleStartHr, "08");
		CommonMethods.selectDropDown(eleStartAMPM, "pm");
		CommonMethods.selectDropDown(eleEndHr, "09");
		CommonMethods.selectDropDown(eleEndAMPM, "pm");
		eleBtnSetDate.click();

		String mothYear = eleSelectMothYear.getText();
		String[] words = mothYear.split(" ");

		int yearDiff = Integer.parseInt(strYear) - Integer.parseInt(words[1]);
		if (yearDiff > 0) {
			for (int i = 1; i <= yearDiff; i++) {
				eleYearnext.click();
			}
		}

		String[] monthAndYear = eleSelectMothYear.getText().split(", ");
		String month = monthAndYear[0];
		int calMonthIndex = CommonMethods.getMonthIndex(month);
		
		int givenMonthIndex = CommonMethods.getMonthIndex(strMonth);
		int monthDiff = givenMonthIndex - calMonthIndex;
		if (monthDiff > 0) {
			for (int i = 1; i < monthDiff; i++) {
				eleMonthNext.click();
			}
		} else if (monthDiff < 0) {
			for (int i = 1; i <= monthDiff * (-1); i++) {
				eleMonthPrevious.click();
			}
		}
		
		int noOfRows = driver.findElements(By.xpath("//div[@class=\"calendar\"]/table/tbody/tr")).size();

		String xpath1 = "//div[@class=\"calendar\"]/table/tbody/tr[";
		String xpath2 = "]/td[";
		String xpath3 = null;
		for (int i = 1; i <= noOfRows; i++) {
			for (int j = 2; j <= 8; j++) {
				xpath3 = xpath1 + i + xpath2 + j + "]"; //
				if (driver.findElement(By.xpath(xpath3)).getText().equalsIgnoreCase(strDate)) {
					driver.findElement(By.xpath(xpath3)).click();
					break;
				}
			}
		}

		System.out.println(eleStartDate.getAttribute("value"));
		// eleSave.click();

	}
}
