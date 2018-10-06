package vibe.setting.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.EnvProperties;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.profile.account.ProfileAccountMethods;
import vibe.users.tests.UsersMethods;

@Priority(103)
public class SettingsTests extends SettingsMethods {
	
	MyProfileMethods mp= new MyProfileMethods();
	ProfileAccountMethods pa = new ProfileAccountMethods();
	UsersMethods um = new UsersMethods();
	MyProfileMethods profile = new MyProfileMethods();
	EnvProperties prop = new EnvProperties();
	
	@Test(priority=10301)
	public void VerifyPreferredContactMethod() throws Exception{
		logInfo("inside verifyPreferredContactMethodPage() Test");
		System.out.println("inside verifyPreferredContactMethodPage() Test");
		go2Notifications();
		boolean isVerified = verifyPreferredContactMethodPage();
		if(isVerified){
			Assert.assertTrue(isVerified, "Able to verify the preferred contact method verification page.");
		}
		else{
			Assert.assertTrue(isVerified, "Unable to verify the preferred contact method verification page.");
		}

	}
	
	@Test(priority=10302)
	public void verifyAccountInformation() throws Exception{
		logInfo("inside verifyAccountInformation() Test");
		System.out.println("inside verifyAccountInformation() Test");
		go2Accounts();
		boolean isVerified = verifyAccntInfo();
		if(isVerified){
			updateAccountInfo(prop.getLocatorForEnvironment(appUrl,"newsFN3"),prop.getLocatorForEnvironment(appUrl,"newsLN3"),"icentris.vibe001@gmail.com","Salt Lake City");
		}
		else{
			Assert.assertTrue(isVerified, "Unable to verify the account information.");
		}

	}
	
	@Test(priority=10303)
	public void ChangeCommunityProfilePhoto() throws Exception{
		logInfo("inside ChangeCommunityProfilePhoto() Test ");
		System.out.println("inside ChangeCommunityProfilePhoto() Test");
		go2Accounts();
		selectAccountsSection("Community Profile");
		boolean isVerified = verifyCommunityProfileInfo();
		if(isVerified){
			isVerified = mp.addProfilePhoto();
			if(!isVerified){
				Assert.assertTrue(isVerified, "Unable to change the profile photo on the Community Profile section.");
			}
		}
		else{
			Assert.assertTrue(isVerified, "Unable to verify the Community Profile information.");
		}
	}
	
	@Test(priority=10304)
	public void ChangeInvalidProfilePhoto() throws Exception{
		logInfo("inside ChangeInvalidProfilePhoto() Test ");
		System.out.println("inside ChangeInvalidProfilePhoto() Test");
		go2Accounts();
		selectAccountsSection("Community Profile");
		boolean isVerified = mp.validateProfilePhoto();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to validate the profile photo with invalid file on the Community Profile section.");
		}
		
	}
	
	@Test(priority=10305)
	public void UpdateCommunityProfileInfo() throws Exception{
		logInfo("inside UpdateCommunityProfileInfo() Test ");
		System.out.println("inside UpdateCommunityProfileInfo() Test");
		go2Accounts();
		selectAccountsSection("Community Profile");
		pa.updateCommProfileInfo();
		boolean isVerified = verifyCommProfileInfoInAboutSection(communityFName_text,profileMyStory_text);
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to verify the updated Community Profile info, under About section in community profile page.");
		}
		else{
			isVerified = verifyPrivateInfoInAboutSection(nonadminUser1_text);
			if(!isVerified){
				Assert.assertTrue(isVerified, "Unable to verify the private Community Profile info, under About section in community profile page.");
			}
		}
		
	}
	
	@Test(priority=10306)
	public void ResetCommunityProfileInfo() throws Exception{
		logInfo("inside resetCommunityProfileInfo() Test ");
		System.out.println("inside resetCommunityProfileInfo() Test");
		go2Accounts();
		selectAccountsSection("Community Profile");
		boolean isVerified = verifyResetCommunityInfo();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to reset the community profile information.");
		}
	}
	
	@Test(priority=10307)
	public void UpdateUserInfoByAdmin() throws Exception{
		logInfo("inside UpdateUserInfoByAdmin() Test ");
		System.out.println("inside UpdateUserInfoByAdmin() Test");
		um.go2Users();
		um.searchUser(nonadminUser1_text + " ");
		um.editUser(nonadminUser1_text);
		um.changeUserName(txtFirstName,txtLastName);
		boolean isVerified = verifyUpdatedUserInfo(txtFirstName,txtLastName);
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to verify the updated user info by admin, on the user side.");
		}
	}
	
	@Test(priority=10308)
	public void ChangeUserPasswordAndValidate() throws Exception{
		
		logInfo("inside changeUserPasswordAndValidate() Test ");
		System.out.println("inside changeUserPasswordAndValidate() Test");
		go2Accounts();
		boolean isVerified = verifyChangeUserPassword();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to change / validate password fields under Security section.");
		}
	}
	
	@Test(priority=10309)
	public void UpdateEmailNotificationSettings() throws Exception{
		logInfo("inside updateEmailNotificationSettings() Test ");
		System.out.println("inside updateEmailNotificationSettings() Test");
		go2Accounts();
		verifyEmailNotificationSettings();
	}
	
	@Test(priority=10310)
	public void UpdateCellNotificationSettings() throws Exception{
		logInfo("inside updateCellNotificationSettings() Test ");
		System.out.println("inside updateCellNotificationSettings() Test");
		go2Accounts();
		verifyCellNotificationSettings();
	}
	
	@Test(priority=10311)
	public void UpdateLanguageSettings() throws  Exception{
		try{
			logInfo("inside changeUserPassword() Test ");
			System.out.println("inside changeUserPassword() Test");
			go2Accounts();
			verifyChangeLanguageSettings();
		}
		catch(Exception ex){
		}
		
		
	}
	
	@Test(priority=10312)
	public void PwdRecoveryEmailFromNotifications() throws  Exception{
		logInfo("inside pwdRecoveryEmailFromNotifications() Test ");
		System.out.println("inside pwdRecoveryEmailFromNotifications() Test");
		go2Accounts();
		verifyPasswordRecoveryEmail();
		
	}
	
	@Test(priority=10313)
	public void SetEmailNotifications() throws  Exception{
		logInfo("inside setEmailNotifications() Test ");
		System.out.println("inside setEmailNotifications() Test");
		go2Accounts();
		verifyEmailNotifications();
		
	}
	
	@Test(priority=10314)
	public void VerifyCommissionPaymentOptions() throws  Exception{
		logInfo("inside verifyCommissionPaymentOptions() Test ");
		System.out.println("inside verifyCommissionPaymentOptions() Test");
		go2Accounts();
		boolean isVerified = verifyCommPaymentOptions();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to verify all the options under Commission Payment.");
		}
		
	}
	
	@Test(priority=10315)
	public void UpdateReportSettings() throws  Exception{
		logInfo("inside UpdateReportSettings() Test ");
		System.out.println("inside UpdateReportSettings() Test");
		go2ReportSettings();
		updateReportOutput();
		updateReportConstraints();
	}
	
	
	

}
