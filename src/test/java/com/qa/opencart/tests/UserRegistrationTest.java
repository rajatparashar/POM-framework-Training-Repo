package com.qa.opencart.tests;

/**
 *    ############### Assignment ###############A
 * While Implementing the page object model, use Excel sheet to populate the data on User Registration page and perform registration for 3 different users.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ExcelUtil;

public class UserRegistrationTest extends BaseTest {

	@DataProvider
	public Object[][] getProductImageSheetData() {
		return ExcelUtil.getTestData(AppConstants.USER_REGISTRATION_SHEET_NAME);
	}

	@BeforeMethod
	public void registrationPageSetup() {
		DriverFactory.getDriver().get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");
	}

	@Test(dataProvider = "getProductImageSheetData")
	public void productImagesCountTestWithExcel(String fName, String lName, String email, String tel, String pass,
			String confPass) {
		String mail = new SimpleDateFormat("mmddssyyyy").format(new Date());
		newAccountsPage = registrationPage.doRegistration(fName, lName, mail + email, tel, pass, confPass);
		Assert.assertTrue(newAccountsPage.isSuccessfulMessageDisplayed());
	}

	@AfterMethod
	public void userLogout() {
		newAccountsPage.doLogout();
	}
}