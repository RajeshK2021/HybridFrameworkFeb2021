package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedInLoggedInPage extends BasePageWeb{
	
	private Logger log= Logger.getLogger(LinkedInLoggedInPage.class);
	//create a constructor
	public LinkedInLoggedInPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class*='profile-rail-card']")
	private WebElement profileRailCard;
	
	@FindBy(xpath="//img[@class='global-nav__me-photo ember-view']")
	private WebElement profileImageIcon;
	
	@FindBy(xpath ="//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
	private WebElement signOutLink;
	
	//@FindBy(xpath = "//input[@class='search-global-typeahead']")
	@FindBy(xpath = "//input[contains(@class,'input always-show-placeholder')]")
	private WebElement searchEditBox;
	
	public void verifyProfileRailCard() {
		log.debug("wait for the profile rail card");
		wait.until(ExpectedConditions.visibilityOf(profileRailCard));
		Assert.assertTrue(profileRailCard.isDisplayed(), "Profile Rail card is not available");
	}
	
	public void doLogOut() throws InterruptedException {
		log.debug("Perform the logout operation....");
		click(profileImageIcon);
		click(signOutLink);
	}
	
	public SearchResultsPage doPeopleSearch(String serchKeyword) throws InterruptedException {
		log.debug("Perform the People search");
		sendKey(searchEditBox, serchKeyword);
		Thread.sleep(3000);
		log.debug("Press the enter key to search for the search keyword");
		searchEditBox.sendKeys(Keys.ENTER);
		return new SearchResultsPage();
	}

}
