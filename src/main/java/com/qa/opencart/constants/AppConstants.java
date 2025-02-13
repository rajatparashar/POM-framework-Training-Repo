package com.qa.opencart.constants;

public abstract class AppConstants {
	public static final int DEFAULT_TIME_OUT = 5;
	public static final int SHORT_TIME_OUT = 10;
	public static final int MEDIUM_TIME_OUT = 15;
	public static final int LONG_TIME_OUT = 20;

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";

	public static final String HOME_PAGE_TITLE = "My Account";
	public static final String HOME_PAGE_URL_FRACTION = "route=account/account";

	public static final String DEV_CONFIG_PROP_FILE_PATH = "./src/test/resources/config/dev.config.properties";
	public static final String PROD_CONFIG_PROP_FILE_PATH = "./src/test/resources/config/prod.config.properties";
	public static final String QA_CONFIG_PROP_FILE_PATH = "./src/test/resources/config/qa.config.properties";
	public static final String STAGE_CONFIG_PROP_FILE_PATH = "./src/test/resources/config/stage.config.properties";
	public static final String UAT_CONFIG_PROP_FILE_PATH = "./src/test/resources/config/uat.config.properties";

	public static final String PRODUCT_SHEET_NAME = "product";
	public static final String USER_REGISTRATION_SHEET_NAME = "userregistration";
}