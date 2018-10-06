package vibe.shopping2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.SocialNetWorksMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(947)
public class VibeRetailShoppingTests extends Shopping2Methods{	
	
	
	ShoppingMethods shop = new ShoppingMethods();
	SocialNetWorksMethods sm = new SocialNetWorksMethods();
	
	
	//Enroll new customer for shopping 	
	@Test(priority =94731)	  
	public void newShopCustomerEnrollment() throws Exception{		
		logInfo("Entered NewShopCustomerEnrollment() Test");		
		//logOut();			
		marketSelection("United States");	
		System.out.println(custFNText);
		registerNewCustomer(custFNText, custLNText);  //shopUser  //custFNText		
			}	
	
	
	
	//Place order Single Product through Shipping Address, Delivery and payment.	
	@Test(priority=94732 /*, dependsOnMethods ={"newShopCustomerEnrollment"}*/)
	public void placeOrderProducts() throws Exception{		
		logInfo("Entered placeOrderProducts() Test");		
		nav2CustShopping();		
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
		addQuanityNdCheckOut(2);
		selectNextInShipping();		
		selectNextInDelivery();			
		creditCardDetailsFirstTime();
		placeOrderWithConfimation();		
		verifyOrderHistoryDetails(prop.getLocatorForEnvironment(appUrl,"prodName1"));
		
	}
	
	
	//logInfo("Place order Single Product through Shipping Address, Delivery and payment.");	
	@Test(priority=94733)
	public void placeOrderWithMultiProducts() throws Exception{		
		logInfo("Entered into placeOrderWithMultiProducts() test");			
		nav2CustShopping();		
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
		addQuanityNdCheckOut(3);
		nav2CustShopping();		
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
		addQuanityNdCheckOut(2);
		selectNextInShipping();	
		selectNextInDelivery();
		selectNext(); 		
		placeOrderNdClose();			
	}
	
	
	// Autoship
	
		@Test(priority=94734)
		public void placeAutoshipOrder() throws Exception{
			logInfo("Entered placeAutoshipOrder() Test");		
			//existingUserCredentials("shopper1055");
			nav2CustShopping();
			searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName4"));
			addAutoshipQuanityNdCheckOut(4);
			selectNextInAutoshipShipping(autoShipTitleTxt);
			handleFromDelivryToSummaryInAutoship();
			validateAutoShipId();						
		}		
		
		
		@Test(priority=94735)
		public void shipProductFromMyWish() throws Exception{
			logInfo("Entered ValiditionCustomerFields() Test");
			
			//existingUserCredentials("viber.1609");
			nav2CustShopping();
			searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));			
			add2CartFromWishList(prop.getLocatorForEnvironment(appUrl,"prodName3"));
			addQuanityNdCheckOutFromTodaysOrder(prop.getLocatorForEnvironment(appUrl,"prodName3"), 3);
			selectNextInShipping();	
			selectNextInDelivery();
			selectNext(); 		
			placeOrderNdClose();		
			
		}
		
		@Test(priority=94736)
		public void emailNdVerifyMyWishList() throws Exception{	
			logInfo("Entered emailNdVerifyMyWishList() Test");	
			nav2CustShopping();
			searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
			emailMyWishList();
			confirmationMessage("Your message has been sent");	
			
			
		}
		
	
	//TC3645 -Deleting Products From The shopping cart page	
	@Test(priority=94737)
	public void emptyTheProducts() throws Exception{
		logInfo("Entered emptyTheProducts() Test");		
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));		
		//emptyCartProducts("placeOrder");	
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));
		emptyCartProducts();
	}
	
	@Test(priority=94738)
	public void combineOrderNdAutoship() throws Exception{	
		logInfo("Entered combineOrderNdAutoship() Test");		
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName4"));
		addQuanityNdCheckOut(2);		
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
		addAutoshipQuanityNdCheckOut(4);
		selectNextInAutoshipShipping(autoShipTitleTxt);
		handleFromDelivryToSummaryInAutoship();
		
	}
	
	
	
	
	@Test(priority=94739)
	public void shipMultiProductsByAutoship() throws Exception{	
		logInfo("Entered shipMultiProductsByAutoship() Test");		
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
		addAutoshipQuanityNdCheckOut(4);		
		selectNextInAutoshipShipping(autoShipTitleTxt);
		handleFromDelivryToSummaryInAutoship();
		editAutoShipId();	
		searchNAddProductDirectlysinAutoship(prop.getLocatorForEnvironment(appUrl,"prodName3"));
		addQuanityInMultiAutoShipNdCheckOut(4);
		selectNextInAutoshipShipping(autoShipTitleTxt);
		handleFromDelivryToSummaryInAutoship();
		
		/*
		validateAutoShipId();*/
	}	
	
	@Test(priority=94740)
	public void shareProductByEmail() throws Exception{	
		logInfo("Entered shareProductByEmail() Test");
		selectCustMarket("United States");	
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));			
		shop.selectEmailIcon();		
		shop.shareByEmail(guestEmail,subText);
		//ConfirmationMessage("Your photo has been sent to "+selfmailId);  //barnch
    	confirmationMessage("Message sent to: "+guestEmail); //master
		sm.closeSharePopUp();	
		
		
	}
	
	
	

	
	

	@Test(priority=94741)
	public void RemoveProductFromWishList() throws Exception{
		logInfo("Entered ValiditionCustomerFields() Test");
		
		//existingUserCredentials("viber.1609");
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));			
		add2CartFromWishList(prop.getLocatorForEnvironment(appUrl,"prodName3"));
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));			
		add2CartFromWishList(prop.getLocatorForEnvironment(appUrl,"prodName2"));		
		nav2WishList();	
		
		boolean ispresent = verifyProductInWishList(prop.getLocatorForEnvironment(appUrl,"prodName3"));
		if (ispresent ==true){
			removeProductFromWishList(prop.getLocatorForEnvironment(appUrl,"prodName3"));
			nav2WishList();
			verifyProductNotToPresentInWishList(prop.getLocatorForEnvironment(appUrl,"prodName3"));	
			
		}if(ispresent==false)	{
			Assert.assertTrue(ispresent, prop.getLocatorForEnvironment(appUrl,"prodName3") +" prouct is not Present in wishList");
		}
	}
	
	
	@Test(priority=94742)
	public void RemoveProductFromCart() throws Exception{
		logInfo("Entered RemoveProductFromCart() Test");		
		//existingUserCredentials("viber.1609");
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));
		addQuanityNdCheckOut(3);		
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));			
		addQuanityNdCheckOut(2);	
		nav2ShopCart();			
		boolean ispresent = verifyProductInCart(prop.getLocatorForEnvironment(appUrl,"prodName3"));
		if (ispresent ==true){
			removeProductFromCart(prop.getLocatorForEnvironment(appUrl,"prodName3"));
			nav2ShopCart();	
			verifyProductNotToPresentInCartList(prop.getLocatorForEnvironment(appUrl,"prodName3"));			
		}if(ispresent==false)	{
			Assert.assertTrue(ispresent, prop.getLocatorForEnvironment(appUrl,"prodName3") +" prouct is not Present in Shop Cart List");
		}
	}
	
	
	@Test(priority=94743)
	public void shareInAllSocialSites() throws Exception{
		logInfo("Entered shareInAllSocialSites() Test");			
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));
		selectFBIconInShop();		
		sm.shareInFaceBook(fbComments);	
		selectTwitterInIconInShop();    
		sm.shareInTwitter();
		sm.selectLinkedInIcon();
		sm.shareInLinkedIn();			
				
                                      
		
		/*   	profile.closeSharePopUp();    	 
    	shop.selectGooglePlusIcon();     	
     	shop.shareInGooglePlus(gmailId_text, gmailPwd_text);
     	profile.closeSharePopUp();*/

	}		
	
	@Test(priority=94744, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInFaceBook() throws Exception{	
		logInfo("Entered veriyProductInFaceBook() Test");
		sm.login2FBVerifyPostedDetails(fBuserName_Text,fBPwd_text);  
		sm.getPostsOfFBFrom2ndPart(prop.getLocatorForEnvironment(appUrl,"prodName3")) ; 	
			
	}	
	
	@Test(priority=94746, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInTwitter() throws Exception{
		logInfo("Entered verifyProductInTwitter() Test");
	 sm.login2Twitter() ;
   	 sm.verifyPostsInTwitter(prop.getLocatorForEnvironment(appUrl,"prodName3"));	   	
		
	}
	
	@Test(priority=94745, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInGooglePlus() throws Exception{
		logInfo("Entered verifyProductInGooglePlus() Test");
		shop.loginGooglePlus(gmailId_text, gmailPwd_text);
    	shop.verifyInGooglePlusForShopProducts(prop.getLocatorForEnvironment(appUrl,"prodName3"));		
	}
	
	
	
	@Test(priority=94747, dependsOnMethods = {"shareInAllSocialSites"})
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
