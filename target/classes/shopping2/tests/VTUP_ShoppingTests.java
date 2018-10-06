package vibe.shopping2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.LoginCredentials;
import common.Priority;
import common.TestBase;
import common.EnvProperties;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.shopping.tests.ShoppingMethods;
@Priority(147)
public class VTUP_ShoppingTests extends VTUP_ShoppingMethods{
	
	EnvProperties prop = new EnvProperties();
	
	
	
	@Test(priority=14701)
	public void verifyPlaceOrders() throws Exception{
		logInfo("inside verifyPlaceOrders() test");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		validatePlaceOrders();
		
	
	}
	
	@Test(priority=14702)
	public void verifyAddRecruits() throws Exception{
		logInfo("inside verifyAddRecruits() test");
		validateAddRecruitsPage();
		
	
	}
	

	@Test(priority=14703)
	public void verifyPersonalSummary() throws Exception{
		logInfo("inside verifyPersonalSummary() test");
			validatePersonalSummaryPage();
		
	
	}
	
	@Test(priority=14704)
	public void verifyCommissionsPage() throws Exception{
		logInfo("inside verifyCommissionsPage() test");
			validateCommissionsPage();
	
	}
	
	@Test(priority=14705)
	public void verifyMyTeam() throws Exception{
		logInfo("inside verifyMyTeam() test");
			validateMyTeamPage(); 
	
	}
	
	@Test(priority=14706)
	public void userLogoutFromShopping() throws Exception{
		logInfo("inside userLogoutFromShopping() test");
		userLogout();
		
	}
	
}
