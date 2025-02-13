package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class NewAccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public NewAccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By confirmMessage = By.xpath("//div//h1");

	private By logoutLink = By.linkText("Logout");

	public boolean isSuccessfulMessageDisplayed() {
		return eleUtil.doIsElementDisplayed(confirmMessage);
	}

	public void doLogout() {
		eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_TIME_OUT).click();
	}
}