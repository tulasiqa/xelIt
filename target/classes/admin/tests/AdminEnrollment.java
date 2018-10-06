package vibe.admin.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.TestBase;
import vibe.enrollment.tests.ReleaseEnrollmentMethods;


@Priority(2)
public class AdminEnrollment extends ReleaseEnrollmentMethods {
	 String market = "United States";
	 String profileName = "Profile" + TestBase.generateRandomNumberInRange(1, 999); 
	 String[] enrollProfileConfigFields={"Subscription with promotion"}; // , "Work phone"
	 String enrollSponsor_text = "315351";
	

	/* @Test(priority = 201)
	 public void createEnrollmentProfile() throws Exception{
		 logInfo("inside createEnrollmentProfile Test");
		 
		 go2EnrollmentProfilePage();
	
		 addNewProfile(profileName, "icentris");
		 boolean isProfileCreated = verifyEnrollmentProfileIsPresent(profileName);
		 if(isProfileCreated){
			 logInfo(profileName + " profile found in enrollment profile page.");
			 editEnrollmentProfile(profileName);
			 addPage("Personal Details", "Page");
			 clickOnElement("xpath","//*[@class='simple_form edit_pyr_core_enrollment_profile']/div[4]/input");
			 editEnrollmentProfile(profileName);
			 addPage("Login Details", "Page");
			 clickOnElement("xpath","//*[@class='simple_form edit_pyr_core_enrollment_profile']/div[4]/input");
			 editEnrollmentProfile(profileName);
			 addReviewPage("Final Review", "Page");
			 clickOnElement("xpath","//*[@class='simple_form edit_pyr_core_enrollment_profile']/div[4]/input");
			 
		 } else {
			 logInfo(profileName + " profile could not be created in enrollment profile page.");
			 Assert.assertTrue(isProfileCreated, profileName + " profile could not be created in Enrollment profile page.");
		 }
	 }
	 
	 
	 
	 @Test(priority = 202)
	 public void addFields2EnrollmentProfile() throws Exception{
		 logInfo("inside addFields2EnrollmentProfile Test");
		
		 go2EnrollmentProfilePage();
		 boolean isProfileCreated = verifyEnrollmentProfileIsPresent(profileName);
		 if(isProfileCreated){
			 logInfo(profileName + " profile found in enrollment profile page.");
			 editEnrollmentProfile(profileName);
			 
			 addFields2Page("Personal Details", "Select", enrollPersonalDetailsFields);
			 addRequiredFields2Page("Personal Details","Required", enrollPersonalDetailsRequiredFields);
			 addConfirmFields2Page("Personal Details","Confirm", enrollPersonalDetailsConfirmFields);
			 
			 addFields2Page("Login Details", "Select", enrollLoginDetailsFields);
			 addRequiredFields2Page("Login Details","Required", enrollLoginDetailsRequiredFields);
			 addConfirmFields2Page("Login Details","Confirm", enrollLoginDetailsConfirmFields);
			 
			 addFields2Page("Final Review", "Select", enrollFinalReviewFields);
			 addRequiredFields2Page("Final Review","Required", enrollFinalReviewRequiredFields);
			// addConfirmFields2Page("Final Review","Confirm", enrollFinalReviewConfirmFields);
	 
			// clickOnElement("xpath","//*[@class='simple_form edit_pyr_core_enrollment_profile']/div[4]/input");
			 
			selectPaymentOptions(enrollPaymentOptions);
			selectProfileConfigFields(enrollProfileConfigFields);
			setProfileConfigurationDefaultFields();
			
		 } else {
			 
		 logInfo(profileName + " profile could not be created in enrollment profile page.");
		 Assert.assertTrue(isProfileCreated, profileName + " profile could not be created in Enrollment profile page.");
	   }
   }
	 */

}
