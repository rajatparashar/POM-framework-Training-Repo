package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: design login page for open cart")
@Story("Story 101: design the various features of open cart login page")
@Feature("Feature 50: Login page feature")
@Owner("Rajat Parashar")
public class LoginPageTest extends BaseTest {

	@Description("checking Login Page Title")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = false)
	public void loginPageTitleTest() {
		ChainTestListener.log("Verifying Login page title..");
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Login page title : " + actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}

	@Description("checking Login URL")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND_ERROR);
	}

	@Description("checking Forgot Password Link")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void isForgottenPasswordLinkExists() {
		Assert.assertTrue(loginPage.forgottenPwdLinkExists(), AppError.ELEMENT_NOT_FOUND_ERROR);
	}

	@Description("checking user is able to Login with right creds")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
}