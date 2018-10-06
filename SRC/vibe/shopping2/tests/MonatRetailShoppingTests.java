package vibe.shopping2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.SocialNetWorksMethods;
import vibe.enrollment.tests.ReleaseEnrollmentMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(947)
public class MonatRetailShoppingTests extends Shopping2Methods{	
	
	
	ShoppingMethods shop = new ShoppingMethods();
	SocialNetWorksMethods sm = new SocialNetWorksMethods();	
	ReleaseEnrollmentMethods enroll = new ReleaseEnrollmentMethods();
	
	String addCart = "ADD TO CART";
	String flex = "FLEXSHIP";
	//Enroll new customer for shopping 	
	
	@Test(priority =94731)	  
	public void logOutFromAdmin() throws Exception{		
		logInfo("Entered into logOutFromAdmin() Test");	
		userLogout();
		logOut();
		selectCustMarket("United States");		
	   }
	
	
	//@Test(priority =94732)	  
	public void placeOrderwithNewVIP() throws Exception{		
		logInfo("Entered into placeOrderwithRetail() Test");	
		userLogout();
		logOut();
		selectCustMarket("United States");	
		System.out.println(vipFNText);
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));
		addQuanityNdCheckOutForCustomer(1);
		selectTypeOfCustomer(vipText);
		enroll.selectSponsor(prop.getLocatorForEnvironment(appUrl,"EnrollMPSponsorConsultID"));
		vipPersonalInfomation(vipFNText);
		selectProdforRegistration(prop.getLocatorForEnvironment(appUrl,"prodName4"),flex);		
		addMoreProductsIfRequired((float) 84.00);
		validateSelectProductsTitle();
		
		
		
		
   }
	
	@Test(priority=94733)
	public void placeOrderwithNewRetailer() throws Exception{		
		logInfo("Entered into placeOrderwithNewNonVIP() Test");			
		System.out.println(custFNText);
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));		
		addQuanityNdCheckOut(3);
		selectTypeOfCustomer(nonVipText);
		registerNewRetailer(custFNText, custLNText);
		selectNextInShipping();	
		selectNextInDelivery();
		addAnotherCCInMonat(USA, "first");
		placeOrderNdClose();	
		
	}
	
	
	//logInfo("Place order Single Product through Shipping Address, Delivery and payment.");	
	@Test(priority=94734)
	public void multiProductsOfTodaysOrderOfRetail() throws Exception{		
		logInfo("Entered into multiProductsOfTodaysOrderOfRetail() test");			
		nav2CustShopping();		
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
		addQuanityNdCheckOut(2);
		nav2CustShopping();		
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
		addQuanityNdCheckOut(2);
		selectNextInShipping();	
		selectNextInDelivery();
		selectNextInCC();
		placeOrderNdClose();
		shopUserLogout();
	}
	
	
	// Autoship
	
		@Test(priority=94735)
		public void todaysOrderWithExistingRetail() throws Exception{
			logInfo("Entered into todaysOrderWithExistingRetail() Test");				
			selectCustMarket("United States");	
			System.out.println(custFNText);
			searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));
			addQuanityNdCheckOutForCustomer(1);
			selectTypeOfCustomer(nonVipText);
			existingRetailLogin(custFNText);
			nav2CustShopping();		
			searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
			addQuanityNdCheckOut(2);
			selectNextInShipping();	
			selectNextInDelivery();
			selectNextInCC();
			placeOrderNdClose();
				
		}		
		
	
		
		@Test(priority=94736)
		public void priceRangesInRetail() throws Exception{
			logInfo("Entered into priceRangesInRetail() Test");	
			nav2CustShopping();
			rangeLevelPrice(1);
			rangeValidate(1, 40);
			deSelectRangeLevel();
			rangeLevelPrice(2);
			rangeValidate(40,60);	
			deSelectRangeLevel();
			rangeLevelPrice(3);
			rangeValidate(60,80);	
		}
	
	
	
	
	}
