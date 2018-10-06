package vibe.tupperware.tests;

import org.testng.annotations.Test;

import common.Priority;
import common.EnvProperties;
import vibe.marketing.companyNews.tests.NewsMethods;

@Priority(144)
public class PartyPlusTest extends PartyPlusMethods {
	EnvProperties prop = new EnvProperties();
	NewsMethods nm = new NewsMethods();
	
	@Test(priority=14401)
	public void verifyPlatinumUser() throws Exception{
		logInfo("Entered into verifyPlatinumUser test");
		selectTab("Party+");		
		validatePlatinumUser();	
	}
	
	
	@Test(priority=14402)
	public void verifyNonPlatinumUser() throws Exception{
		logInfo("Entered into verifyNonPlatinumUser test");
		back2Office();
		userLogout();		
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
                prop.getLocatorForEnvironment(appUrl,"newsCon2"));	
		selectTab("Party+");
		validateNonPlatinumOrSilverUsers();			
		
	}
	
	@Test(priority=14403)
	public void verifyBasicUser() throws Exception{
		logInfo("Entered into verifyBasicUser test");
		selectTab("My Profile");
		back2Office();
		userLogout();			
		nm.modifyUsersSubscription(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
                prop.getLocatorForEnvironment(appUrl,"newsCon2"), prop.getLocatorForEnvironment(appUrl,"basicSub"));
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
                prop.getLocatorForEnvironment(appUrl,"newsCon2"));	
		selectTab("Party+");
		validateNonPlatinumOrSilverUsers();
		
	}
	
	@Test(priority=14404)
	public void verifySliverUser() throws Exception{
		logInfo("Entered into verifySliverUser test");
		back2Office();
		userLogout();			
		nm.modifyUsersSubscription(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
                prop.getLocatorForEnvironment(appUrl,"newsCon2"), prop.getLocatorForEnvironment(appUrl,"silverSub"));
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
                prop.getLocatorForEnvironment(appUrl,"newsCon2"));	
		selectTab("Party+");
		validatePlatinumUser();	
		
	}
	
	@Test(priority=14405)
	public void reassignSubscription() throws Exception{
		logInfo("Entered into reassignSubscription test");
		back2Office();
		userLogout();			
		nm.modifyUsersSubscription(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
                prop.getLocatorForEnvironment(appUrl,"newsCon2"), prop.getLocatorForEnvironment(appUrl,"basicSub"));
				
	}
	
	
	
	

}
