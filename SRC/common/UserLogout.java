package common;

import org.testng.annotations.Test;

import vibe.marketing.ads.tests.AdsMethods;

@Priority(890)
public class UserLogout extends TestBase {
	 AdsMethods ads = new AdsMethods();
	 @Test(priority=89100)
		public void logoutUser() throws  Exception{
		logInfo("inside logoutUser() Test.");
		userLogout();
		ads.assertTitle("User Management");
		
	 }
		

}
