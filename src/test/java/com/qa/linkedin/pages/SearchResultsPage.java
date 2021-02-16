package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchResultsPage extends BasePageWeb {

	private Logger log = Logger.getLogger(SearchResultsPage.class);

	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'search-results__cluster-bottom-banner')]/a")
	private WebElement seeAllPeopleResults;

	@FindBy(xpath = "//div[contains(@class,'search-results-page core-rail')]/div")
	private WebElement searchResultsText;

	@FindBy(xpath = "//ul[@class='global-nav__primary-items']/li/a")
	private WebElement homeTab;

	String searchResultsPageTitle = "Search | LinkedIn";

	public void validateSearchResultsPageTitle() {
		log.debug("wait for the searchResultsPageTitle..");
		wait.until(ExpectedConditions.titleContains(searchResultsPageTitle));
		Assert.assertTrue(driver.getPageSource().contains(searchResultsPageTitle));
	}

	public long getResultsCount() {
		log.debug("Perform fetching the results count for the given people");
		try {
			if (isPresentElement(seeAllPeopleResults)) {
				click(seeAllPeopleResults);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("wait for the Search results text");
		wait.until(ExpectedConditions.visibilityOf(searchResultsText));
		log.debug("Get the search results text from the web page ");
		String txt = searchResultsText.getText();
		System.out.println("Search Results are: " + txt);
		String[] str = txt.split(" ");
		log.debug("Results count in String format.." + str[1]);
		String finalCount = str[1].replace(",", "").trim();
		log.debug("covert string in long integer fromat");
		long count = Long.parseLong(finalCount);
		return count;

	}
	public void clickHomeTab() throws InterruptedException {
		log.debug("click on Home icon");
		clickUsingJsExecutor(homeTab);
	}
}
