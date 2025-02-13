package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSteup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "imac", "iMac" },
				{ "samsung", "Samsung Galaxy Tab 10.1" } };
	}

	@Test(dataProvider = "getProductData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultsPage = homePage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actualHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actualHeader, productName);
	}

	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object[][] { { "macbook", "MacBook Pro", 3 }, { "macbook", "MacBook Air", 4 }, { "imac", "iMac", 3 },
				{ "samsung", "Samsung SyncMaster 941BW", 2 }, { "samsung", "Samsung Galaxy Tab 10.1", 7 } };
	}

	@Test(dataProvider = "getProductImagesData")
	public void productImagesCountTest(String searchKey, String productName, int expectedImagesCOunt) {
		searchResultsPage = homePage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actualImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualImagesCount, expectedImagesCOunt);
	}

	@Test
	public void productInfoTest() {
		searchResultsPage = homePage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = productInfoPage.getProductInfo();
		productInfoMap.forEach((k, v) -> System.out.println(k + " : " + v));

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInfoMap.get("header"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfoMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("extax"), "$2,000.00");

		softAssert.assertAll();
	}

	@DataProvider
	public Object[][] getProductImageSheetData() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}

	@Test(dataProvider = "getProductImageSheetData")
	public void productImagesCountTestWithExcel(String searchKey, String productName, String expectedImagesCOunt) {
		searchResultsPage = homePage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actualImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualImagesCount, Integer.parseInt(expectedImagesCOunt));
	}

}