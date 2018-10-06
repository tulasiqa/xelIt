package vibe.shopping2.tests;

import org.testng.annotations.Test;

import common.LoginCredentials;
import common.Priority;
import common.SocialNetWorksMethods;
import vibe.mywebsite.tests.MyWebSiteMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(147)
public class VibeMPShoppingTests extends Shopping2Methods{	
	
	LoginCredentials lc = new LoginCredentials();
	ShoppingMethods shop = new ShoppingMethods();	
	MyWebSiteMethods pwp = new MyWebSiteMethods();		
	SocialNetWorksMethods sm = new SocialNetWorksMethods();		
	
	@Test(priority=14731)
	public void placeOrderOfMP() throws Exception{		
		logInfo("Entered placeOrderOfMP() Test");		
		nav2CustShopping();	
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));		
		addQuanityNdCheckOut(1);
		addnewAddressForExistingUser(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"));						
		selectNextInDelivery();		
		selectNextInCC();
		placeOrderNdClose();
		;	
		
	}
	
	
	//Place order Single Product through Shipping Address, Delivery and payment
	@Test(priority=14732)
	public void placeOrderWithMultiProductsOfMP() throws Exception{		
		logInfo("Entered into placeOrderWithMultiProductsOfMP() test");	
		nav2CustShopping();	
        searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
        addAutoshipQuanity(2);  
		emptyAutoshipProducts(); 
		nav2CustShopping();		
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
		addProductWithQuanity(1); 
		nav2CustShopping();		
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
		addQuanityNdCheckOut(1);
		addnewAddressForExistingUser(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"));
		selectSuggestedAddress();
		handle904NotAuthorized(shippingTitleTxt);						
		selectNextInDelivery();			
		selectNextInCC();		
		placeOrderNdClose();
			
	}
	
	
	// Autoship
	
		@Test(priority=14733)
		public void autoshipOrderOfMP() throws Exception{
			logInfo("Entered autoshipOrderOfMP() Test");			
			nav2CustShopping();
			searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
			addProductWithQuanity(2);
			emptyCartProducts();
			nav2CustShopping();	
	        searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
	        addAutoshipQuanity(2);  
			emptyAutoshipProducts(); 
			nav2CustShopping();			
			searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
			addAutoshipQuanityNdCheckOut(1);
			shippingAddresForAutoship(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
					prop.getLocatorForEnvironment(appUrl,"newsLN1"));
			selectNextInAutoshipShipping(felxShipTitleTxt);
			handle904NotAuthorized(felxShipTitleTxt);				
			handleFromDelivryToSummaryInAutoship();			
			validateAutoShipId();						
		}	
		
		
		
		@Test(priority=14734)
		public void emailNdVerifyMyWishListOfMP() throws Exception{	
			logInfo("Entered emailNdVerifyMyWishListOfMP() Test");	
			nav2CustShopping();
			searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
			emailMyWishList();
			confirmationMessage("Your message has been sent");	
			
			
		}
		
	
	@Test(priority=14735)
	public void combineTodaysNdAutoshipOfMP() throws Exception{	
		logInfo("Entered combineTodaysNdFlexshipOfMP() Test");		
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
		addProductWithQuanity(2);
		emptyCartProducts();
		nav2CustShopping();	
        searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName4"));
        addAutoshipQuanity(2);  
		emptyAutoshipProducts();
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
		addProductWithQuanity(1); 	
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
		addAutoshipQuanityNdCheckOut(1);
		shippingAddresForAutoship(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"));		
		shippingAddressProcess(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"));
		handle904NotAuthorized(shippingTitleTxt);
		selectNextInDelivery();
		selectNextInCC();
		placeOrderNdClose();		
	}	
	
	@Test(priority=14736)
	public void validatePriceRanges() throws Exception{
		logInfo("Entered validatePriceRanges() Test");	
		nav2CustShopping();
		rangeLevelPrice(1);
		rangeValidate(1, 50);
		deSelectRangeLevel();
		rangeLevelPrice(2);
		rangeValidate(60,1000);		
	}
	
	@Test(priority=14737)
	public void deletePlaceOrderAndAutoProducts() throws Exception{
	 logInfo("Entered deletePlaceOrderAndAutoProducts() Test");
	    nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName4"));
		addProductWithQuanity(2);
		emptyCartProducts();
		nav2CustShopping();	
        searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName4"));
        addAutoshipQuanity(2);  
		emptyAutoshipProducts(); 
	   
	}
	
	@Test(priority=14750)
	public void shareInAllSocialSites() throws Exception{
		logInfo("Entered shareInAllSocialSites() Test");			
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));
		selectFBIconInShop();		
		sm.shareInFaceBook(fbComments);	
		selectTwitterInIconInShop();
		sm.shareInTwitter();
		/*sm.selectLinkedInIcon();
		sm.shareInLinkedIn();	*/
		/*selectGooglePlusIcon();
		shareInGooglePlus(gmailId_text, gmailPwd_text);*/
		    
	 }
	
	
	@Test(priority=14751, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInFaceBook() throws Exception{	
		logInfo("Entered veriyProductInFaceBook() Test");
		sm.login2FBVerifyPostedDetails(fBuserName_Text,fBPwd_text);  
		sm.getPostsOfFBFrom2ndPart(prop.getLocatorForEnvironment(appUrl,"prodName3")) ; 	
			
	}	
	
	@Test(priority=14752, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInTwitter() throws Exception{
		logInfo("Entered verifyProductInTwitter() Test");
	 sm.login2Twitter() ;
   	 sm.verifyPostsInTwitter(prop.getLocatorForEnvironment(appUrl,"prodName3"));
   	 logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),
    		prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		
	}
	
	//@Test(priority=94753, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInGooglePlus() throws Exception{
		logInfo("Entered verifyProductInGooglePlus() Test");
		shop.loginGooglePlus(gmailId_text, gmailPwd_text);
    	shop.verifyInGooglePlusForShopProducts(prop.getLocatorForEnvironment(appUrl,"prodName3"));		
	}
	
	
	
	//@Test(priority=14754/*, dependsOnMethods = {"shareInAllSocialSites"}*/)
	public void verifyProductInLinkedIn() throws Exception{
		logInfo("Entered verifyProductInLinkedIn() Test");
		try{
		sm.login2LinkedIn();		
	    sm.verifyPostInLinkedAccount(prop.getLocatorForEnvironment(appUrl,"prodName3"));	    
	    logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),
	    		prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
	 	
	    existingUserCredentials(custFNText);
		}catch (Exception ex){
			System.out.println("entered into catch");
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
