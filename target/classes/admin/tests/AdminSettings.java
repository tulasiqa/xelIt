package vibe.admin.tests;

import org.testng.annotations.Test;

import vibe.mycommunity.tests.CommunityMethods;
import vibe.profile.account.ProfileAccountMethods;
import vibe.setting.tests.SettingsMethods;
import vibe.users.tests.UsersMethods;
import common.Priority;
import common.EnvProperties;

@Priority(3)
public class AdminSettings extends SettingsMethods {
	
	CommunityMethods cm = new CommunityMethods();
	ProfileAccountMethods pa = new ProfileAccountMethods();
	UsersMethods um = new UsersMethods();
	EnvProperties prop = new EnvProperties();
	
	@Test(priority=301)
	public void enableAccountSideNavs() throws  Exception{
		logInfo("inside enableAccountSideNavs() Test ");
		System.out.println("inside enableAccountSideNavs() Test");
		go2SpecialSettings();
		enableAllAccountSideNavs();
	}


	@Test(priority=302)
	public void enableCommissionPaymentOptions() throws  Exception{
		logInfo("inside enableCommissionPaymentOptions() Test ");
		System.out.println("inside enableCommissionPaymentOptions() Test");
		go2PaymentSettings();
		enableConfigurePaymentOptions();
		
	}
	
	@Test(priority=303)
	public void selectMarketsForAllPaymentOptions() throws  Exception{
		logInfo("inside selectMarketsForAllPaymentOptions() Test ");
		System.out.println("inside selectMarketsForAllPaymentOptions() Test");
		go2PaymentSettings();
		selectMarketsForPaymentOptions();
				
	}
		
}
