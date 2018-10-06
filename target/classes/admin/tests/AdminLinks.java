package vibe.admin.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import vibe.adminlinks.tests.AdminLinksMethods;
import vibe.users.tests.UsersMethods;

@Priority(21)
public class AdminLinks extends AdminLinksMethods  {
	UsersMethods um = new UsersMethods();
			
	@Test(priority=2101)
	public void VerifyAllBrokenLinksInAdmin() throws Exception{
		logInfo("inside VerifyAllBrokenLinksInAdmin() Test");
		System.out.println("inside VerifyAllBrokenLinksInAdmin() Test");
		um.go2Users();
		if(driver().getCurrentUrl().contains("retro")){
			verifyHeader("CIO Manager");
			verifySubHeaders("CIO Manager");
			
		}
		else if(driver().getCurrentUrl().contains("thirtyonegifts")){
			verifyHeader("Consultant Manager");
			verifySubHeaders("Consultant Manager");
		}
		else{
			verifyHeader("Distributor Manager");
			verifySubHeaders("Distributor Manager");
		}
		
		verifyHeader("Content");
		verifySubHeaders("Content");
		
		verifyHeader("Setup");
		verifySubHeaders("Setup");
		
		verifyHeader("Backend Settings");
		verifySubHeaders("Backend Settings");
		
		verifyHeader("Marketing");
		verifySubHeaders("Marketing");
		
		verifyHeader("Tools");
		verifySubHeaders("Tools");
		
		verifyHeader("Reports");
		
		verifyHeader("Security");
		verifySubHeaders("Security");
		if(!driver().getCurrentUrl().contains("tupperware")){
			verifyHeader("Shop");
			verifySubHeaders("Shop");
		}

	}
		
}
