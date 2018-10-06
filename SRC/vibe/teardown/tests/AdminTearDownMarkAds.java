package vibe.teardown.tests;

import org.testng.annotations.Test;
import common.Priority;
import common.EnvProperties;
import vibe.billing.tests.BillingMethods;
import vibe.marketing.ads.tests.AdsMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.shopping.tests.ShoppingMethods;
import vibe.widgets.tests.WidgetsMethods;

@Priority(913)
public class AdminTearDownMarkAds extends AdsMethods{
	
	ShoppingMethods sm = new ShoppingMethods();
	BillingMethods bm = new BillingMethods();
	NewsMethods nm = new NewsMethods();
	WidgetsMethods wm= new WidgetsMethods();
	EnvProperties prop = new EnvProperties();
	
	String lang = "All";
	
	
	// Creates ad with subscription, lang And verifies in office ads widget and then finally deletes
	@Test(priority = 91301)
	public void deleteAds() throws Exception{
		logInfo("Entered into DeleteAds() Test");			
	    navigateConfigureAds();
	    deleteAd(urlAds1);
	    /*navigateConfigureAds();
	    adsNotToPresentInAdmin(urlAds1, lang);*/
	 }	
	
	@Test(priority = 91302)
	public void deleteAdsWithCountry() throws Exception{
		logInfo("Entered into deleteAdsWithCountry() Test");			
	    navigateConfigureAds();
	    deleteAd(urlAds6);
	    assertTitle(titleAds);
	    assertTitle(titleAds);	    
	 }
	
	@Test(priority = 91303)
	public void deleteExpiredAds() throws Exception{
		logInfo("Entered into deleteExpiredAds() Test");		    
	    navigateConfigureAds();
	    deleteAd(urlAds5); 	 
	    assertTitle(titleAds);
	    assertTitle(titleAds);	   
	 }
	
	//@Test(priority = 91304)
	public void deleteSortedAds() throws Exception{
		logInfo("Entered into deleteSortedAds() Test");	
		navigateConfigureAds();
	    deleteAd(urlAds4); 	   
	    assertTitle(titleAds);
	    assertTitle(titleAds);	   
	 }
	
	
	@Test(priority = 91305)
	public void deleteSecondSubscriberAds() throws Exception{
		logInfo("Entered into deleteSecondSubscriberAds() Test");		   
	    navigateConfigureAds();
	    deleteAd(urlAds3); 	   
	    assertTitle(titleAds);
	    assertTitle(titleAds);
	    adsNotToPresentInAdmin(urlAds3, lang);
	 }
	
	@Test(priority = 91306)
	public void deleteFirstSubscriberAds() throws Exception{
		logInfo("Entered into deleteFirstSubscriberAds() Test");	    
	    navigateConfigureAds();
	    deleteAd(urlAds2); 	  
	    assertTitle(titleAds);
	    assertTitle(titleAds);
	    adsNotToPresentInAdmin(urlAds2, lang);
	 }	
	//@Test(priority = 91350)		
	public void deleteAllAds() throws Exception{
		logInfo("Entered into deleteAllAds() Test");	
	   navigateConfigureAds(); 		  
	   deleteAllAdsFromAdmin();
	  
	}
	
}
