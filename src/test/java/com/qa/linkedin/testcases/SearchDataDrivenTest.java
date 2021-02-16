package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedInLoggedInPage;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDataDrivenTest extends TestBase {
	private Logger log = Logger.getLogger(SearchDataDrivenTest.class);
	private String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchdata.xlsx";
	LinkedinHomePage linkedinHomePage;
	LinkedInLoggedInPage linkedinLoggedInPage;
	SearchResultsPage searchResultsPage;

	@BeforeClass
	public void beforeClass() throws Exception {
		log.debug("Instantiate the Pages classes");
		linkedinHomePage = new LinkedinHomePage();
		linkedinLoggedInPage = new LinkedInLoggedInPage();
		searchResultsPage = new SearchResultsPage();
		log.debug("calling for title verification");
		linkedinHomePage.verifyLinkedinHomePageTitle();
		linkedinHomePage.verifyLinkedinLogo();
		// linkedinHomePage.verifyLinkedinSignInPageTitle();
		linkedinHomePage.clickOnSignInLink();
		linkedinHomePage.verifySignInHeaderText();
		log.debug("processing to login page");
		linkedinHomePage.doLogin(readPropertyValue("username"), readPropertyValue("password"));
		linkedinLoggedInPage.verifyProfileRailCard();
	}

	@AfterClass
	public void afterClass() throws Exception {
		log.debug("Perform the logout opration from the application");
		linkedinLoggedInPage.doLogOut();
		linkedinHomePage.verifyLinkedinHomePageTitle();
	}

	@Test(dataProvider = "getData")
	public void searchDataDrivenTest(String keyword) throws Exception {
		log.debug("start searching for the people"+keyword);
		linkedinLoggedInPage.doPeopleSearch(keyword);
		searchResultsPage.validateSearchResultsPageTitle();
		Long count = searchResultsPage.getResultsCount();
		log.debug("search results for"+keyword+" are: "+count);
		log.debug("click on home tab to go for Loggin page");
		searchResultsPage.clickHomeTab();
		log.debug("*************Iteration done******************");
		

	}

	@DataProvider
	public Object[][] getData() throws InvalidFormatException, IOException {
		Object[][] data = new ExcelUtils().getTestData(path, "Sheet1");
		return data;
	}

}
