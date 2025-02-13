package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class HomePage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By logoutLink = By.linkText("Logout");
	private By searchbox = By.xpath("//input[@name='search']");
	private By searchBtn = By.xpath("//div[@class='input-group']//button");

	public String getHomePageTitle() {
		String title = eleUtil.waitForTitleContains(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Home page title is : " + title);
		return title;
	}

	public String getHomePageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Home page URL : " + url);
		return url;
	}

	public boolean logoutLinkExists() {
		return eleUtil.doIsElementDisplayed(logoutLink);
	}

	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("search key : " + searchKey);
		eleUtil.getElement(searchbox).clear();
		eleUtil.doSendKeys(searchbox, searchKey);
		eleUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);
	}
}