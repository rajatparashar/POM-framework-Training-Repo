package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By firstName_Ele = By.id("input-firstname");
	private By lastName_Ele = By.id("input-lastname");
	private By email_Ele = By.id("input-email");
	private By telephone_Ele = By.id("input-telephone");
	private By password_Ele = By.id("input-password");
	private By confirmPassword_Ele = By.id("input-confirm");
	private By privacyPolicy_Ele = By.xpath("//input[@type='checkbox']");
	private By continueBtn_Ele = By.xpath("//input[@type='submit']");

	public NewAccountsPage doRegistration(String fName, String lName, String email, String tel, String pass,
			String confPass) {
		eleUtil.waitForElementVisible(firstName_Ele, AppConstants.DEFAULT_TIME_OUT).sendKeys(fName);
		eleUtil.doSendKeys(lastName_Ele, lName);
		eleUtil.doSendKeys(email_Ele, email);
		eleUtil.doSendKeys(telephone_Ele, tel);
		eleUtil.doSendKeys(password_Ele, pass);
		eleUtil.doSendKeys(confirmPassword_Ele, confPass);
		eleUtil.doClick(privacyPolicy_Ele);
		eleUtil.doClick(continueBtn_Ele);
		return new NewAccountsPage(driver);

	}
}