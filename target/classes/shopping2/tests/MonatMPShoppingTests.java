package vibe.shopping2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.LoginCredentials;
import common.Priority;
import common.SocialNetWorksMethods;
import vibe.mywebsite.tests.MyWebSiteMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(147)
public class MonatMPShoppingTests extends Shopping2Methods{	
	
	
	ShoppingMethods shop = new ShoppingMethods();	
	MyWebSiteMethods pwp = new MyWebSiteMethods();	
	SocialNetWorksMethods sm = new SocialNetWorksMethods();		
	
	String promo = "promo";
	String onlyForYou = "onlyForYou";

	
	@Test(priority=14731)
	public void placeOrderOfMP() throws Exception{		
		logInfo("Entered placeOrderOfMP() Test");
		nav2CustShopping();
		travelSetProducts();
		promotionalProd();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
		addQuanityNdCheckOut(3);	
		addnewAddressForExistingUser(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"));				
		selectSuggestedAddress();
		handle904NotAuthorized(shippingTitleTxt);						
		selectNextInDelivery();		
		//selectNextInCC();
		addAnotherCCInMonat(USA, "anotherCard");
		placeOrderNdClose();		
		//promotionalProd();			
		//verifyOrderHistoryDetails(prop.getLocatorForEnvironment(appUrl,"prodName2"));	
		
	}
	
	
	//Place order Single Product through Shipping Address, Delivery and payment
	@Test(priority=14732)
	public void placeOrderWithMultiProductsOfMP() throws Exception{		
		logInfo("Entered into placeOrderWithMultiProductsOfMP() test");	
		nav2CustShopping();	
        searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
        addProductWithQuanity(1); 
        travelSetProducts();
		promotionalProd();
        emptyCartProducts();
        nav2CustShopping();	
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));
		addProductWithQuanity(1); 
		travelSetProducts();
		promotionalProd();
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
		public void flexshipOrderOfMP() throws Exception{
			logInfo("Entered flexshipOrderOfMP() Test");			
			nav2CustShopping();
			pageNavigation(business, flex);
			pageNavigation(business, flex);
			selectNewFlex();
			searchFlexshipProduct(prop.getLocatorForEnvironment(appUrl,"prodName2"));		
			addFlexshipQuanity(1);
			flexshipCheckOut();			
			shippingAddresForAutoship(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
					prop.getLocatorForEnvironment(appUrl,"newsLN1"));				
			handleFromDelivryToSummaryInAutoship();	   
			validateFlexshipTitle();
			//validateAutoShipId();	
		}			
	
	
	@Test(priority=14736)
	public void validatePriceRanges() throws Exception{
		logInfo("Entered validatePriceRanges() Test");	
		nav2CustShopping();	
		rangeLevelPrice(1);
		rangeValidate(1,30);
		deSelectRangeLevel();
		rangeLevelPrice(2);
		rangeValidate(30,40);	
		deSelectRangeLevel();
		rangeLevelPrice(3);
		rangeValidate(40,50);	
		deSelectRangeLevel();
		rangeLevelPrice(4);
		rangeValidate(60,100);	
	}
	
	@Test(priority=14737)
	public void deleteTodaysOrderProducts() throws Exception{
	 logInfo("Entered deletePlaceOrderAndAutoProducts() Test");
	    nav2CustShopping();	
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName4"));
		addProductWithQuanity(2);
		emptyCartProducts();
		alertMsgValidate(emptyAlertText);
	   
	}
       
	
	@Test(priority=14738)
	public void freeShippingForMP() throws Exception{
		logInfo("Entered freeShippingForMP() Test");			
		pageNavigation(business, flex);
		pageNavigation(business, flex);
		selectNewFlex();
		selectProduct();
		addFlexshipQuanity(1);  
		freeShippingCalculations((float) 69.00);
		
	}	
	
	@Test(priority=14739)
	public void volumeCombinationOfTodaysOrderOfMP() throws Exception{
		logInfo("Entered volumeCombinationOfTodaysOrderOfMP() Test");		
		nav2CustShopping();	
		travelSetProducts();
		promotionalProd();
		nav2CustShopping();			
	    searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName4"));
		addProductWithQuanity(2);
		promotionalProd();
		emptyCartProducts();		
		nav2CustShopping();	
		searchItembyNameOrSku(volProdA);               
		addProductWithQuanity(2);
		travelSetProducts();
		promotionalProd();
		nav2CustShopping();
		searchItembyNameOrSku(volProdB);
		addProductWithQuanity(2);
		nav2CustShopping();
		searchItembyNameOrSku(volProdC);
		addProduct(2);		
		combinationOrderAlert("Yes");
		promotionalProd();
		validateVolumeProductInTodaysOrder("Item", "#10150000");		
	}
	
	@Test(priority=14740)
	public void volumeCombinationOfFlexshipOfMP() throws Exception{
		logInfo("Entered volumeCombinationOfFlexshipOfMP() Test");				
		pageNavigation(business, flex);
		pageNavigation(business, flex);
		selectNewFlex();
		searchFlexshipProduct(volProdA);
		addFlexshipQuanity(2);	
		searchFlexshipProduct(volProdB);
		addFlexshipQuanity(2);		
		searchFlexshipProduct(volProdC);
		addFlexshipQuanity(2);
		combinationOrderAlert("Yes");	
		validateVolumeProductIFlexship("Item", "10150000");
	}
	
	
	@Test(priority=14741)
	public void mixAndMatchOfMP() throws Exception{
		logInfo("Entered mixAndMatchOfMP() Test");			
		nav2CustShopping();	
	    searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName4"));
		addProductWithQuanity(2);
		travelSetProducts();
		promotionalProd();
		emptyCartProducts();
		nav2CustShopping();
		searchItembyNameOrSku(volProdA);
		addProductWithQuanity(1); 
		travelSetProducts();
		promotionalProd();		
	    nav2CustShopping();
		searchItembyNameOrSku(mixProdB);
		addProductWithQuanity(1); 		
		nav2CustShopping();
		searchItembyNameOrSku(mixProdC);
		addQuanityNdCheckOut(1);
		shippingAddressProcess(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"));						
		selectNextInDelivery();			
		selectNextInCC();		
		validateDiscount();	
	}
	
	
	// add promo flexship and then edit same flexship and add additional product 
	@Test(priority=14742)
	public void promoAndEditOfFlexshipForMP() throws Exception{
		logInfo("Entered promoAndEditOfFlexshipForMP() Test");			
		pageNavigation(business, flex);
		pageNavigation(business, flex);
		int beforeList =getListOfFlexships();
		selectNewFlex();
		selectProduct();
		addFlexshipQuanity(1);  
		addPromoproducts(69, onlyForYou);
		calenderDatePicker();
		selectProduct();
		addFlexshipQuanity(1); 
		flexshipCheckOut();
		shippingAddresForAutoship(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"));
		handleFromDelivryToSummaryInAutoship();	   
		validateFlexshipTitle();
		pageNavigation(business, flex);
		int afterList = getListOfFlexships();
		if(afterList>beforeList){
			System.out.println("entered into loop");
			editFlexship(afterList);
			searchFlexshipProduct(volProdB);
			addFlexshipQuanity(2);			
			flexshipCheckOutAfterSurvey();
			shippingAddresForAutoship(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
					prop.getLocatorForEnvironment(appUrl,"newsLN1"));
			selectNextInAutoshipShipping(felxShipTitleTxt);
			handleFromDelivryToSummaryInAutoship();	  
			validateFlexshipTitle();
		}else {			
			Assert.assertNotEquals(afterList,beforeList);
		}		
	}	
	
	
	@Test(priority=14743)
	public void changeDateNextRunforFlexshipForMP() throws Exception{
		logInfo("Entered changeDateNextRunforFlexshipForMP() Test");	
		pageNavigation(business, flex);
		pageNavigation(business, flex);
		int beforeList =1;
		int afterList = getListOfFlexships();	
		for (int i=1; i<afterList; i++){
			int current = getListOfFlexships();	
			if(current>beforeList){
				deleteFlexship(beforeList);					
				}
			}	
			
		}
	
	
	
	
	
	@Test(priority=14743)
	public void deleteOfFlexshipForMP() throws Exception{
		logInfo("Entered deleteOfFlexshipForMP() Test");	
		pageNavigation(business, flex);
		pageNavigation(business, flex);
		int beforeList =1;
		int afterList = getListOfFlexships();	
		for (int i=1; i<afterList; i++){
			int current = getListOfFlexships();	
			if(current>beforeList){
				deleteFlexship(beforeList);					
				}
			}	
			
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
	 boolean isPresent = sm.verifyPostsInTwitter(prop.getLocatorForEnvironment(appUrl,"prodName3"));
	 if(isPresent=true) {   	 
   	 logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),
    		prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
   	 loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
			prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
	 }else {
		 logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),
		    		prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		 loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
					prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		 Assert.assertTrue(isPresent, prop.getLocatorForEnvironment(appUrl,"prodName3")+" -product is not present");
		 
	 }

   	 
		
	}
	
	//@Test(priority=94753, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInGooglePlus() throws Exception{
		logInfo("Entered verifyProductInGooglePlus() Test");
		loginGooglePlus(gmailId_text, gmailPwd_text);
    	verifyInGooglePlusForShopProducts(prop.getLocatorForEnvironment(appUrl,"prodName3"));		
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
