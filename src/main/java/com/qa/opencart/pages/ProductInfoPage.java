package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private HashMap<String, String> map;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By productName = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'][1])//li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'][2])//li");

	public String getProductHeader() {
		String product = eleUtil.doElementGetText(productName);
		System.out.println("Product name : " + product);
		return product;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsPresence(productImages, AppConstants.DEFAULT_TIME_OUT).size();
		System.out.println("Images count for " + getProductHeader() + " is " + imagesCount);
		return imagesCount;
	}

	/**
	 * get product metadata
	 * 
	 * @return
	 */

	public Map<String, String> getProductInfo() {
		map = new HashMap<String, String>();
		map.put("header", getProductHeader());
		map.put("images count", getProductImagesCount() + "");
		getProductMetaData();
		getProductPriceData();
		return map;
	}

	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.waitForElementsPresence(productMetaData, AppConstants.DEFAULT_TIME_OUT);
		for (WebElement e : metaList) {
			String metaText = e.getText();
			String metaArr[] = metaText.split(":");
			String key = metaArr[0].trim();
			String value = metaArr[1].trim();
			map.put(key, value);
		}
	}

	private void getProductPriceData() {
		List<WebElement> metaList = eleUtil.waitForElementsPresence(productPriceData, AppConstants.DEFAULT_TIME_OUT);
		String productPrice = metaList.get(0).getText().trim();
		String productTax = metaList.get(1).getText().split(":")[1].trim();
		map.put("price", productPrice);
		map.put("extax", productTax);
	}
}