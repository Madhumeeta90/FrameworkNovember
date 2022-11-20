package com.learnautomation.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.learnautomation.base.BaseClass;
import com.learnautomation.dataProvider.DataProviders;
import com.learnautomation.pages.Dashboard;
import com.learnautomation.pages.LoginPage;

public class LoginTest extends BaseClass {
	
	LoginPage login;
	Dashboard  dash;
	public WebDriver driver;
	
	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	@Test(description ="Login to application",dataProvider="login",dataProviderClass = DataProviders .class)
	public void loginApp(String uname,String pass)
	{
		LoginPage login = new LoginPage(driver);
		
		login.loginToApplication("Admin", "admin123");
		
		Dashboard dash = login.loginToApplication(uname, pass);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Logout failed");
		
		dash.logOut();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Logout failed");
	}

	@Test(description = "Logout from application", dependsOnMethods = "loginApp")
	public void logoutTest()
	{
		dash.logOut();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("login"),"Logout failed");
	}
}
