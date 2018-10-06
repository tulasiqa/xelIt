package vibe.marketing.ads.tests;

import org.testng.annotations.Test;
import common.Priority;
import org.testng.Assert;
import common.EnvProperties;
import vibe.billing.tests.BillingMethods;
import vibe.shopping2.tests.Shopping2Methods;
import vibe.widgets.tests.WidgetsMethods;

@Priority(113)
public class AdsTests extends AdsMethods{
	
	Shopping2Methods sm = new Shopping2Methods();
	BillingMethods bm = new BillingMethods();
	WidgetsMethods wm= new WidgetsMethods();
	EnvProperties prop = new EnvProperties();	
	
	// Creates ad with subscription, lang And verifies in office ads widget and then finally deletes
	@Test(priority = 11301)
	public void validateCreatedAds() throws Exception{
		logInfo("Entered into validateCreatedAds() Test");		
		//companyAdswidgets();		
		verifyAdInWidget(urlAds1);			
		go2HomePage();
		AdNotToBePresentInWidget(urlAds3);
	 }	
	
	@Test(priority = 11302)
	public void verifyAdsInShoppingNdWidget() throws Exception{
		logInfo("Entered into verifyAdsInShoppingNdWidget() Test");			
		go2HomePage();			
		Boolean isPresent = verifyAdInWidget(urlAds2);	
		if(isPresent=true) {
			sm.nav2CustShopping();
			verifyAdAtShopping(urlAds2);
			adNotToBePresentAtShop(urlAds3);					
			}else {
			    Assert.assertTrue(isPresent, urlAds2 +" is not present");				
			} 
	 }
		
		
		// Creates ad with subscription, lang And verifies in office ads widget and then finally deletes
	@Test(priority = 11303)
	public void validateAdsWithCountry() throws Exception{
		logInfo("Entered into validateAdsWithCountry() Test");
		go2HomePage();	
		AdNotToBePresentInWidget(urlAds6);
		sm.nav2CustShopping();				
		adNotToBePresentAtShop(urlAds6);						    
	 }
				
   }
	
	