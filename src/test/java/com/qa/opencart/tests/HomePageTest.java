package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

public class HomePageTest extends BaseTest {

	@BeforeClass
	public void homePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void homePageTitleTest() {
		String actTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actTitle, AppConstants.HOME_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}

	@Test
	public void homePageURLTest() {
		String actURL = homePage.getHomePageURL();
		Assert.assertTrue(actURL.contains(AppConstants.HOME_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND_ERROR);
	}

	@Test
	public void isLogoutLinkExists() {
		Assert.assertTrue(homePage.logoutLinkExists(), AppError.ELEMENT_NOT_FOUND_ERROR);
	}

	@DataProvider
	public Object[][] getTestData() {
		return new Object[][] { { "macbook", 3 }, { "imac", 1 }, { "samsung", 2 }, { "canon", 1 }, { "airtel", 0 } };
	}

	@Test(dataProvider = "getTestData")
	public void searchTest(String searchKey, int resultCount) {
		searchResultsPage = homePage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getProductResultsCount(searchKey), resultCount);
	}
}