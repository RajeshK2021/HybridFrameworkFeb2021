package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb {
	private Logger log= Logger.getLogger(LinkedinHomePage.class);
	//create a constructor
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="a.nav__logo-link")
	private WebElement linkedinLogo;
	
	@FindBy(linkText="Sign in")
	private WebElement singnInLink;
	
	@FindBy(xpath="//h1[contains(@class,'header__content__heading')]")
	private WebElement signInHeaderText;
	
	@FindBy(id="username")
	private WebElement emailEditBox;
	
	@FindBy(name="session_password")
	private WebElement passwordEditBox;
	
	@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
	private WebElement signInButton;
	
	
	
	
	String signInPageTitle = "LinkedIn Login, Sign in | LinkedIn";
	String homePageTitle = "LinkedIn: Log In or Sign Up";

	public void verifyLinkedinHomePageTitle() {
		log.debug("wait for the linkedin home page title");
		wait.until(ExpectedConditions.titleContains(homePageTitle));
		Assert.assertEquals(driver.getTitle(), homePageTitle);
	}
	
	public void verifyLinkedinLogo() {
		log.debug("wait for the Linkedin logo");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed(), "LinkedIn logo is not present");
	}
	
	public void clickOnSignInLink() throws InterruptedException {
		log.debug("click on signin link");
		click(singnInLink);
		
	}
	
	public void verifyLinkedinSignInPageTitle() {
		log.debug("wait for the linkedin signIn page title");
		wait.until(ExpectedConditions.titleContains(signInPageTitle));
		Assert.assertEquals(driver.getTitle(), signInPageTitle);
	}
	
	public void verifySignInHeaderText() {
		log.debug("wait for the SignIn Header Text");
		wait.until(ExpectedConditions.visibilityOf(signInHeaderText));
		Assert.assertTrue(signInHeaderText.isDisplayed(), "SignIn header text is not present");
	}
	
	public void clickOnSignInButton() throws InterruptedException {
		log.debug("click on signin button");
		click(signInButton);
		}
	
	public LinkedInLoggedInPage doLogin(String uname,String pwd) throws InterruptedException {
		log.debug("Login process started.......");
		sendKey(emailEditBox, uname);
		sendKey(passwordEditBox, pwd);
		clickOnSignInButton();
		
		return new LinkedInLoggedInPage();
	}
	
}
