package vibe.shopping2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.LoginCredentials;
import common.Priority;
import common.SocialNetWorksMethods;
import common.TestBase;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.mywebsite.tests.MyWebSiteMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(947)
public class SabaShopping2Tests extends Shopping2Methods{	
	
	LoginCredentials lc = new LoginCredentials();
	ShoppingMethods shop = new ShoppingMethods();
	MyProfileMethods profile = new MyProfileMethods();
	MyWebSiteMethods pwp = new MyWebSiteMethods();		
	SocialNetWorksMethods sm = new SocialNetWorksMethods();
		String  txtVirtualProdName11 = "VIBE Energy";//"Energy Sticks";
		String  txtVirtualProdName21 ="VIBE BodyButter";//"Stout Advice";                      //"Variant pro";           //master Stout Advice
		String  txtVirtualProdName31 ="Moisturizer 2"; // "Stout Advice";
        String  txtVirtualProdName41 ="B-Energy Drink 5";// "Stout Advice";
	
		
		
	
	//TC3636 logInfo("Enroll new customer for shopping and capture confirmation message.");	
	@Test(priority =94731)	  
	public void newShopCustomerEnrollment() throws Exception{		
		logInfo("Entered NewShopCustomerEnrollment() Test");		
	    driver().navigate().to(appUrl);
		logOut();			
		selectCustMarket("United States");	
		System.out.println(custFNText);
		registerNewCustomer(custFNText, custLNText);  //shopUser  //custFNText		
			}	
	
	
	
	//Place order Single Product through Shipping Address, Delivery and payment.	
	@Test(priority=94732 /*, dependsOnMethods ={"newShopCustomerEnrollment"}*/)
	public void placeOrderProducts() throws Exception{		
		logInfo("Entered placeOrderProducts() Test");		
		nav2CustShopping();		
		selectProduct();
		addQuanityNdCheckOut(2);
		selectNextInShipping();		
		selectNextInDelivery();			
		creditCardDetailsFirstTime();
		placeOrderWithConfimation();
		/*
		verifyOrderHistoryDetails(txtVirtualProdName11);*/
		
	}
	
	@Test(priority=94732 /*, dependsOnMethods ={"newShopCustomerEnrollment"}*/)
	public void placeOrderProducts_old() throws Exception{		
		logInfo("Entered placeOrderProducts() Test");		
		nav2CustShopping();		
		searchItembyNameOrSku(txtVirtualProdName11);
		addQuanityNdCheckOut(2);
		selectNextInShipping();		
		selectNextInDelivery();			
		creditCardDetailsFirstTime();
		placeOrderWithConfimation();
		verifyOrderHistoryDetails(txtVirtualProdName11);
		
	}
	
	//logInfo("Place order Single Product through Shipping Address, Delivery and payment.");	
	@Test(priority=94733)
	public void placeOrderWithMultiProducts() throws Exception{		
		logInfo("Entered into placeOrderWithMultiProducts() test");	
		
		//existingUserCredentials("viber.2618");
		nav2CustShopping();		
		searchItembyNameOrSku(txtVirtualProdName11);
		addQuanityNdCheckOut(3);
		nav2CustShopping();		
		searchItembyNameOrSku(txtVirtualProdName21);
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
			searchItembyNameOrSku(txtVirtualProdName41);
			addAutoshipQuanityNdCheckOut(4);
			selectNextInAutoshipShipping(autoShipTitleTxt);
			handleFromDelivryToSummaryInAutoship();		
			validateAutoShipId();			
			deleteAutoShip();		
		}		
		
		
		@Test(priority=94735)
		public void shipProductFromMyWish() throws Exception{
			logInfo("Entered ValiditionCustomerFields() Test");
			
			//existingUserCredentials("viber.1609");
			nav2CustShopping();
			searchItembyNameOrSku(txtVirtualProdName31);			
			add2CartFromWishList(txtVirtualProdName31);
			addQuanityNdCheckOutFromTodaysOrder(txtVirtualProdName31, 3);
			selectNextInShipping();	
			selectNextInDelivery();
			selectNext(); 		
			placeOrderNdClose();		
			
		}
		
		@Test(priority=94736)
		public void emailNdVerifyMyWishList() throws Exception{	
			logInfo("Entered emailNdVerifyMyWishList() Test");	
			nav2CustShopping();
			searchItembyNameOrSku(txtVirtualProdName11);
			emailMyWishList();
			confirmationMessage("Your message has been sent");	
			
			
		}
		
	
	//TC3645 -Deleting Products From The shopping cart page	
	@Test(priority=94737)
	public void emptyTheProducts() throws Exception{
		logInfo("Entered emptyTheProducts() Test");		
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName11);		
		//emptyCartProducts("placeOrder");	
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName31);
		//emptyCartProducts("autoship");
	}
	
	@Test(priority=94738)
	public void combineOrderNdAutoship() throws Exception{	
		logInfo("Entered combineOrderNdAutoship() Test");		
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName41);
		addQuanityNdCheckOut(2);		
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName21);
		addAutoshipQuanityNdCheckOut(4);
		selectNextInShipping();	
		selectNextInDelivery();
		selectNextInPayment();	
		placeOrderNdClose();	
	}
	
	
	
	
	@Test(priority=94739)
	public void shipMultiProductsByAutoship() throws Exception{	
		logInfo("Entered shipMultiProductsByAutoship() Test");		
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName11);
		addAutoshipQuanityNdCheckOut(4);		
		selectNextInAutoshipShipping(autoShipTitleTxt);
		handleFromDelivryToSummaryInAutoship();
		editAutoShipId();	
		searchNAddProductDirectlysinAutoship(txtVirtualProdName31);
		addQuanityInMultiAutoShipNdCheckOut(4);
		selectNextInAutoshipShipping(autoShipTitleTxt);
		handleFromDelivryToSummaryInAutoship();
		validateAutoShipId();
	}	
	
	@Test(priority=94740)
	public void shareInAllSocialSites() throws Exception{
		logInfo("Entered shareInAllSocialSites() Test");			
		selectCustMarket("United States");	
		searchItembyNameOrSku(txtVirtualProdName31);
		selectFBIconInShop();		
		sm.shareInFaceBook(fbComments);	
		sm.selectLinkedInIcon();
		sm.shareInLinkedIn();		
		selectTwitterInIconInShop();    
		sm.shareInTwitter();		
                                      
		
		/*   	profile.closeSharePopUp();    	 
    	shop.selectGooglePlusIcon();     	
     	shop.shareInGooglePlus(gmailId_text, gmailPwd_text);
     	profile.closeSharePopUp();*/

	}

	
	
	@Test(priority=94740)
	public void deleteNdVerifyAutoship() throws Exception{
		logInfo("Entered deleteNdVerifyAutoship() Test");
				
		nav2AutoshipCustomer();
		autoshipWithProducts(txtVirtualProdName21);
		addQuanityInAutoShipNdCheckOut(2);
		selectNextInAutoshipShipping(autoShipTitleTxt);
		handleFromDelivryToSummaryInAutoship();
		deleteAutoshipId();
	}
	
	
	@Test(priority=94741)
	public void shoppingNdLoginWithShopUser() throws Exception{	
		logInfo("Entered shoppingNdLoginWithShopUser() Test");		
		
		nav2CustShopping();
		Thread.sleep(5000);
		logoutFromShopCustomer();
		selectCustMarket("United States");
		searchItembyNameOrSku(txtVirtualProdName31);
		addQuanityNdCheckoutOfNonUser(3);
		custUserLogin(custFNText);
		shippingAddress(custFNText, custLNText);	
		selectNextInDelivery();
		creditCardDetails();
		placeOrderWithConfimation();
		verifyOrderHistoryDetails(txtVirtualProdName31);
		
	}


		
	
	@Test(priority=94743, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInFaceBook() throws Exception{	
		logInfo("Entered veriyProductInFaceBook() Test");
		sm.login2FBVerifyPostedDetails(fBuserName_Text,fBPwd_text);  
		sm.getPostsOfFBFrom2ndPart(txtVirtualProdName31) ; 	
			
	}	
	
	@Test(priority=94744, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInGooglePlus() throws Exception{
		logInfo("Entered verifyProductInGooglePlus() Test");
		shop.loginGooglePlus(gmailId_text, gmailPwd_text);
    	shop.verifyInGooglePlusForShopProducts(txtVirtualProdName31);		
	}
	
	@Test(priority=94745, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInTwitter() throws Exception{
		logInfo("Entered verifyProductInTwitter() Test");
	 sm.login2Twitter() ;
   	 sm.verifyPostsInTwitter(txtVirtualProdName31);	   	
		
	}
	
	@Test(priority=94746, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInLinkedIn() throws Exception{
		logInfo("Entered verifyProductInLinkedIn() Test");
		try{
		sm.login2LinkedIn();		
	    sm.verifyPostInLinkedAccount(txtVirtualProdName31);	    
	    lc.login();	    
	    existingUserCredentials(custFNText);
		}catch (Exception ex){
			System.out.println("entered into catch");
			
			
		}
	}
	
	@Test(priority=94747)
	public void relogin() throws Exception{	
		logInfo("Entered temporarily() Test");
		lc.login();	    
	    existingUserCredentials(custFNText);
		
	} 
	
	@Test(priority=94748)
	public void shareProductByEmail() throws Exception{	
		logInfo("Entered shareProductByEmail() Test");
		selectCustMarket("United States");
		searchItembyNameOrSku(txtVirtualProdName11);			
		shop.selectEmailIcon();		
		shop.shareByEmail(guestEmail,subText);
		//ConfirmationMessage("Your photo has been sent to "+selfmailId);  //barnch
    	confirmationMessage("Message sent to: "+guestEmail); //master
		sm.closeSharePopUp();	
		
		
	}
	
	
	@Test(priority=94749)
	public void shopWithGuestUser() throws Exception{
		logInfo("Entered shopWithGuestUser() Test");
		
		//logOut();
		logoutFromShopCustomer();		
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName21);
		addQuanityNdCheckoutOfNonUser(3);
		guestUser(guestEmail);
		shippingAddress(guestFname, guestLname);
		selectNext();
			  // to be continued after issue resolved		
		
		
	/*	lc.login();
		existingUserCredentials(custFNText);    
		Thread.sleep(5000);*/
		
		
	}
	
	// This is implemeneted tempritly 
	@Test(priority=94750)
	public void temporarily() throws Exception{	
		logInfo("Entered temporarily() Test");
		driver().navigate().back();
		lc.login();
		existingUserCredentials(custFNText);    
		Thread.sleep(5000);
		
	}	
	
	
	
	
	@Test(priority=94799)
	public void placeOrderWithNewDetails() throws Exception{
		logInfo("Entered placeOrderWithNewDetails() Test");
		String Fname = "Mike"+ generateRandomNumberInRange(1, 111);
		String Lname = "Nelson"+ generateRandomNumberInRange(1, 111);
		
		//existingUserCredentials(custFNText);      // Temp - remove
		Thread.sleep(5000);
		logoutFromShopCustomer();
		selectCustMarket("United States");
		searchItembyNameOrSku(txtVirtualProdName11);
		addQuanityNdCheckOut(3);
		shipProductToNewAddress(Fname, Lname);
		selectNext(); // select next in delivery
		addNewCCDetails();	
		placeOrderWithConfimation();	
		
	}
	
	@Test(priority=94752)
	public void RemoveProductFromWishList() throws Exception{
		logInfo("Entered ValiditionCustomerFields() Test");
		
		//existingUserCredentials("viber.1609");
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName31);			
		add2CartFromWishList(txtVirtualProdName31);
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName21);			
		add2CartFromWishList(txtVirtualProdName21);		
		nav2WishList();	
		
		boolean ispresent = verifyProductInWishList(txtVirtualProdName31);
		if (ispresent ==true){
			removeProductFromWishList(txtVirtualProdName31);
			nav2WishList();
			verifyProductNotToPresentInWishList(txtVirtualProdName31);	
			
		}if(ispresent==false)	{
			Assert.assertTrue(ispresent, txtVirtualProdName31 +" prouct is not Present in wishList");
		}
	}
	
	@Test(priority=94753)
	public void validateIncAndDescQTY() throws Exception{
		logInfo("Entered ValiditionCustomerFields() Test");		
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName31);
		addQuanityNdCheckOut(3);		
		nav2ShopCart();			
		boolean ispresent = verifyProductInCart(txtVirtualProdName31);
		if (ispresent ==true){
			validIncndDescQty(txtVirtualProdName31, 3, 2);						
		}if(ispresent==false){
			Assert.assertTrue(ispresent, txtVirtualProdName31 +" prouct is not Present in Shop Cart List");
		}
	}
	
	@Test(priority=94754)
	public void RemoveProductFromCart() throws Exception{
		logInfo("Entered RemoveProductFromCart() Test");			
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName31);
		addQuanityNdCheckOut(3);		
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName21);			
		addQuanityNdCheckOut(2);	
		nav2ShopCart();			
		boolean ispresent = verifyProductInCart(txtVirtualProdName31);
		if (ispresent ==true){
			removeProductFromCart(txtVirtualProdName31);
			nav2ShopCart();	
			verifyProductNotToPresentInCartList(txtVirtualProdName31);			
		}if(ispresent==false)	{
			Assert.assertTrue(ispresent, txtVirtualProdName31 +" prouct is not Present in Shop Cart List");
		}
	}
	
	@Test(priority=94755)
	public void validateProductReviews() throws Exception{
		logInfo("Entered validateProductReviews() Test");		
		
		String shopUserName = getUserName();
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName31);
		writeAReview(prodReviewText, 3);		
		logoutFromShopCustomer();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		navigate2AdminShop();
		acceptReviewFromAdmin(txtVirtualProdName31, shopUserName, prodReviewText );
		existingUserCredentials(custFNText);
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName31);
		validateReviews(prodReviewText);
		
	}
	
	@Test(priority=94756)
	public void validatePriceRanges() throws Exception{
		logInfo("Entered validateProductReviews() Test");			
		nav2CustShopping();
		shopPanels();
		rangeLevelPrice(1);
		rangeValidate(0,20);
		nav2CustShopping();
		shopPanels();
		rangeLevelPrice(2);
		rangeValidate(20,60);
		shopPanels();
		rangeLevelPrice(3);
		rangeValidate(60,100);
		shopPanels();
		rangeLevelPrice(4);
		rangeValidate(100,140);
		shopPanels();
		rangeLevelPrice(5);
		rangeValidate(180,500);
	}	
	

	
	}
