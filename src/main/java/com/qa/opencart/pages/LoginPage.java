package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 1. By locators
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");

	// 2. Action methods
	@Step("get Login Page Title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Login page title is : " + title);
		return title;
	}

	@Step("get Login Page URL")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Login page URL : " + url);
		return url;
	}

	@Step("checking forgotten Pwd Link displayed")
	public boolean forgottenPwdLinkExists() {
		return eleUtil.doIsElementDisplayed(forgotPwdLink);
	}

	@Step("Login with username: {0} and password: {1}")
	public HomePage doLogin(String username, String pass) {
		eleUtil.waitForElementVisible(email, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("username: " + username + " and password : " + pass);
		eleUtil.doSendKeys(email, username);
		eleUtil.doSendKeys(password, pass);
		eleUtil.doClick(loginBtn);
		return new HomePage(driver);
	}
}