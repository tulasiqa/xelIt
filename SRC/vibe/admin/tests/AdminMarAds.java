package vibe.admin.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.EnvProperties;
import vibe.billing.tests.BillingMethods;
import vibe.marketing.ads.tests.AdsMethods;
import vibe.shopping2.tests.Shopping2Methods;
import vibe.widgets.tests.WidgetsMethods;

@Priority(13)
public class AdminMarAds extends AdsMethods{
	
	Shopping2Methods sm = new Shopping2Methods();
	BillingMethods bm = new BillingMethods();

	WidgetsMethods wm= new WidgetsMethods();
	EnvProperties prop = new EnvProperties();
	
	String yes = "Yes";
	String both = "Both";		
	
	
	// Creates ad with subscription, lang And verifies in office ads widget and then finally deletes
	@Test(priority = 1302)
	public void createNewAds() throws Exception{
		logInfo("Entered into createNewAds() Test");		
		adsCreation(urlAds1, ranker5, languageList,subscripList,yes,both);	 
	 	confirmationMessage(bannerConfirm);
	 	assertTitle(titleAds);	
	 	
	 }	
	   // Creates ad with subscription, lang And verifies in office ads widget and then finally deletes
		@Test(priority = 1301)
		public void validateFutureAds() throws Exception{
			logInfo("Entered into validateFutureAds() Test");				
			adsCreation(urlAds6, ranker5, languageList,subscripList, null, both);	  // change sub scription based on admins		
			confirmationMessage(bannerConfirm);
			assertTitle(titleAds);
		    navigateConfigureAds();			    
		    boolean ispresent = validateAdsUrl(urlAds6);
		    if (ispresent==true){	
		    	editExistingAds(urlAds6);
		    	validateFieldPublishImmd();		    	
		    }else {
		    	Assert.assertTrue(ispresent, urlAds6+ " -url is not present" );
		    }
		}	
		
	
	
	@Test(priority = 1303)
	public void adsWithSubscrb1() throws Exception{		
		logInfo("Entered into adsWithSubscrb1() Test");				
		 	navigateConfigureAds();
		 	adsCreation(urlAds2, ranker5, languageList,subscrb1,yes, both);
		 	confirmationMessage("Banner created successfully.");
		 	assertTitle(titleAds);
		 	
	}  
	
	
	@Test(priority = 1304)
	public void adsWithSubscrb2() throws Exception{		
		logInfo("Entered into adsWithSubscrb2() Test");			
		 	adsCreation(urlAds3, ranker5, languageList,subscrb2 ,yes, both);
		 	confirmationMessage(bannerConfirm);
		 	assertTitle(titleAds);
		 	
	}  
	
	@Test(priority = 1305)
	public void adsWithCountry() throws Exception{		
		logInfo("Entered into adsWithCountry() Test");			
		 	adsCreation(urlAds6,ranker5, languageList2,subscrb1,yes,both);
		 	confirmationMessage(bannerConfirm);
		 	assertTitle(titleAds);
		 	
	} 	

	
	//@Test(priority = 1306)
	public void validateSorting() throws Exception{		
		logInfo("Entered into validateSorting() Test");			
		    String language ="French";
		    adsCreation(urlAds4, ranker5, languageList, subscrb2 ,null, both);		 	
		 	confirmationMessage(bannerConfirm);
		 	assertTitle(titleAds);
		    navigateConfigureAds();
		 	editExistingAds(urlAds4);		 	
		 	modifyMarketLanguages(languageList);
		 	modifySubscriptionPlanSlection(subscrb1);
		 	updateBanner();	
		 	assertTitle(titleAds);
		 	navigateConfigureAds();
			sortSelectLanguages("English");
		 	validateAdsUrl(urlAds4);		 	
		 	sortSelectLanguages(language);		 	
		 	adsNotToPresentInAdmin(urlAds4, language);		 					 	
		 	sortSelectStatus("Pending");
		 	validateAdsUrl(urlAds4);		 	
		 	sortSelectSubcriptionPlans(prop.getLocatorForEnvironment(appUrl,"singleSubscription1"));
		 	validateAdsUrl(urlAds4);		
		 
	}	
	
	@Test(priority = 1307)
	public void validateTurnOffBanner() throws Exception{		
		logInfo("Entered into validateTurnOffBanner() Test");		
		navigateConfigureAds();
		selectTurnBanners("Turn off Shop Banner");
		confirmationMessage("Shop Banner settings successfully updated.");
		go2HomePage();
		sm.nav2CustShopping();
		adNotToBePresentAtShop(urlAds1);
		navigateConfigureAds();
		selectTurnBanners("Turn on Shop Banner");
		confirmationMessage("Shop Banner settings successfully updated.");
			
	}
	
	@Test(priority = 1308)
	public void validateMandatoryfields() throws Exception{		
		logInfo("Entered into validateMandatoryfields() Test");	
		navigateConfigureAds();
		selectTurnBanners("Turn on Shop Banner");
		confirmationMessage("Shop Banner settings successfully updated.");	
		navigateConfigureAds();
		validateFields();
			
	}
	
	@Test(priority = 1309)
	public void validateExpiredAds() throws Exception{		
		logInfo("Entered into validateExpiredAds() Test");			
		expiredAdCreation(urlAds5, ranker5, languageList,subscrb2 ,yes, both);
		confirmationMessage(bannerConfirm);
		assertTitle(titleAds);
	 	go2HomePage();
		navigateConfigureAds();
	 	adsStatusUnderImage(urlAds5, "Expired");
		sortSelectStatus("Expired");
		validateAdsUrl(urlAds5);
	}  
		
}