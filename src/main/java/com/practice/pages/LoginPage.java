package com.practice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.practice.master.Master;

public class LoginPage extends Master {

	@FindBy(name="user_name")
	WebElement eleUserName;

	@FindBy(name="user_password")
	WebElement elePassword;

	@FindBy(id="submitButton")
	WebElement eleBtnLogin;

	@FindBy(xpath="//img[@src=\"themes/softed/images/user.PNG\"]")
	WebElement eleLogOutmenu;
	
	@FindBy(xpath="//a[@href=\"index.php?module=Users&action=Logout\"]")
	WebElement eleBtnLogout;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public HomePage clickOnLogin(String username, String password) {
		eleUserName.sendKeys(username);
		elePassword.sendKeys(password);
		eleBtnLogin.click();
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HomePage();
	}
	
	public void logOut() {
		Actions action = new Actions(driver);
		action.moveToElement(eleLogOutmenu);
		action.perform();
		eleBtnLogout.click();
	}
	
}
