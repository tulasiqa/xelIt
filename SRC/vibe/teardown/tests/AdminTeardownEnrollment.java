package vibe.teardown.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.TestBase;
import vibe.enrollment.tests.ReleaseEnrollmentMethods;
import common.Locators;

@Priority(902)
public class AdminTeardownEnrollment extends Locators {
	
	TestBase tb = new TestBase();
	ReleaseEnrollmentMethods em = new ReleaseEnrollmentMethods();   // Vibe Lite - Monthly
	
	/*@Test(priority = 90200)
	public void deleteEnrollmentProfile() throws Exception{
		
		String profileName = "Profile842";
		 tb.logInfo("inside deleteEnrollmentProfile Test");
		 boolean isProfilePresent = em.verifyEnrollmentProfileIsPresent(profileName);
		 if(isProfilePresent){
			 em.editEnrollmentProfile(profileName);
		 }
		 
		 boolean isProfileDeleted = em.verifyEnrollmentProfileIsPresent(profileName);
		 if(isProfileDeleted==true){
			Assert.assertFalse(isProfileDeleted, profileName + " enrollement profile could not be deleted.");
			tb.logInfo(profileName + " enrollement profile could not be deleted.");
		 }
		 
	}
*/
}
