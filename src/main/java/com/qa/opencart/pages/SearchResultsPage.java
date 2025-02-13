package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By productResults = By.cssSelector("div.product-thumb");

	public int getProductResultsCount(String productName) {
		int resultCount = eleUtil.waitForElementsPresence(productResults, AppConstants.DEFAULT_TIME_OUT).size();
		return resultCount;
	}

	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Product name : " + productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
}