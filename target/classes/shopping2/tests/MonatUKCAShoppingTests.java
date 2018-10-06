package vibe.shopping2.tests;

import org.testng.annotations.Test;
import common.Priority;
import common.SocialNetWorksMethods;
import common.UserLogout;
import vibe.enrollment.tests.ReleaseEnrollmentMethods;
import vibe.mywebsite.tests.MyWebSiteMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(947)
public class MonatUKCAShoppingTests extends Shopping2Methods{	
	
	ShoppingMethods shop = new ShoppingMethods();	
	SocialNetWorksMethods sm = new SocialNetWorksMethods();	
	MyWebSiteMethods pwp = new MyWebSiteMethods();
	ReleaseEnrollmentMethods enr= new ReleaseEnrollmentMethods();	
	UserLogout out = new UserLogout();
	
	String tools = "Tools";
	
	String CAMPFN ="Amanda";
	String CAMPLN ="Anderson";
	String CAMPConId ="778539";
	
	@Test(priority=94701)
	public void loginAsCAMPUser() throws Exception{		
		logInfo("Entered loginAsCAMPUser() Test");   
		/*out.logoutUser();*/
		loginAsUserFromAdvanced(CAMPFN,CAMPLN,CAMPConId);			
	   }
	
	@Test(priority=94702)
	public void placeOrderOfCAMP() throws Exception{		
		logInfo("Entered placeOrderOfMP() Test");
		nav2CustShopping();
		travelSetProducts();
		promotionalProd();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
		addQuanityNdCheckOut(3);	
		shippingAddressProcess(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"));	
		selectNextInDelivery();
		addAnotherCCInMonat(CA, "anotherCard");
		placeOrderNdClose();			
		verifyOrderHistoryDetails(prop.getLocatorForEnvironment(appUrl,"prodName2"));	
		
	}
	
	
	
	@Test(priority=94703)
	public void productDescriptionsOfCA() throws Exception{		
		logInfo("Entered productDescriptionsOfCA() Test");   
		nav2CustShopping();	
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));	
		selectTabbings();	
	   }
	
	@Test(priority=94704)
	public void expressOrderforCAMP() throws Exception{		
	logInfo("Entered expressOrderforCAMP() Test");   	  
		nav2CustShopping();	
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
		addProductWithQuanity(1);	
		travelSetProducts();
		promotionalProd();		
		expressCheckOut();
		placeOrderWithConfimation();
		//verifyOrderHistoryDetails(prop.getLocatorForEnvironment(appUrl,"prodName1"));
	}
	
	@Test(priority=94705 /*, dependsOnMethods= {"placeOrderWithExpressCheckout"}*/)
	public void reOrderProducts() throws Exception{		
	logInfo("Entered reOrderProducts() Test");
		reOrderWithSameProducts();
		checkout2DaysOrder();	
		shippingAddressProcess(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"));			
		selectNextInDelivery();	
		addAnotherCCInMonat(USA, "anotherCard");
		placeOrderWithConfimation();
		verifyOrderHistoryDetails(prop.getLocatorForEnvironment(appUrl,"prodName1"));	
	}
	
	
	@Test(priority=94706)
	public void priceRangesforCAMP() throws Exception{
		logInfo("Entered priceRangesforCAMP() Test");	
		nav2CustShopping();
		rangeLevelPrice(1);
		rangeValidate(1, 30);
		deSelectRangeLevel();
		rangeLevelPrice(2);
		rangeValidate(30,50);	
		deSelectRangeLevel();
		rangeLevelPrice(3);
		rangeValidate(50,70);	
		deSelectRangeLevel();
		rangeLevelPrice(4);
		rangeValidate(70,90);
		deSelectRangeLevel();
		rangeLevelPrice(5);
		rangeValidate(90,1000);
		
	}
	
	
	@Test(priority=94707)
	public void flexshipOrderOfCAMP() throws Exception{
		logInfo("Entered flexshipOrderOfCAMP() Test");	
		pageNavigation(BUSINESS_tab, flex);	
		pageNavigation(BUSINESS_tab, flex);			
		selectNewFlex();
		searchFlexshipProduct(prop.getLocatorForEnvironment(appUrl,"prodName2"));		
		addFlexshipQuanity(2);
		flexshipCheckOut();
		shippingAddresForAutoship(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"));
		selectNextInAutoshipShipping(felxShipTitleTxt);
		handleFromDelivryToSummaryInAutoship();	  
		validateFlexshipTitle();
		//validateAutoShipId();			
			
	}	
	
	// Edit autoship and change the date and add products. 
	@Test(priority=94708)
	public void editAutoshipForCAMP() throws Exception{
		logInfo("Entered editAutoshipForCAMP() Test");			
		pageNavigation(BUSINESS_tab, flex);	
		pageNavigation(BUSINESS_tab, flex);	
		//editFlexship();
		
		
		/*selectNewFlex();
		selectProduct();
		addFlexshipQuanity(1);   
		freeShippingCalculations((float) 84.00);*/	
	}	
	

	
	/*
	
	
	
	@Test(priority=94705)
	public void mixAndMatchOfVIP() throws Exception{
		logInfo("Entered mixAndMatchOfVIP() Test");	
		nav2CustShopping();
		travelSetProducts();
		promotionalProd();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName4"));
		addProductWithQuanity(2);
		emptyCartProducts();
		nav2CustShopping();
		searchItembyNameOrSku(volProdA);
		addProductWithQuanity(1);
		nav2CustShopping();
		travelSetProducts();
		promotionalProd();
		searchItembyNameOrSku(mixProdB);
		addProductWithQuanity(1);
		nav2CustShopping();
		searchItembyNameOrSku(mixProdC);
		addProductWithQuanity(1);
		promotionalProd();
		checkout2DaysOrder();
		expressCheckOut();
		validateDiscount();
	}*/
	
	
	
	
	
	
	@Test(priority=94708)
	public void noVolumeCombinationOfTodaysOrderOfVIP() throws Exception{
		logInfo("Entered noVolumeCombinationOfTodaysOrderOfVIP() Test");
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName2"));
		addProductWithQuanity(2);
		travelSetProducts();
		promotionalProd();	
		emptyCartProducts();			
		nav2CustShopping();
		searchItembyNameOrSku(volProdA);	
		String expSKU = getSKU();
		System.out.println(expSKU);
		addProductWithQuanity(2);		
		nav2CustShopping();
		searchItembyNameOrSku(volProdB);
		addProductWithQuanity(2);
		nav2CustShopping();
		searchItembyNameOrSku(volProdC);		
		addProduct(2);
		combinationOrderAlert("No");	
		travelSetProducts();
		promotionalProd();
		/*
		checkout2DaysOrder();*/
		validateVolumeProductInTodaysOrder("Item",expSKU);	
		nav2CustShopping();
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName4"));
		addProductWithQuanity(2);
		emptyCartProducts();
	}
	
	@Test(priority=94709)
	public void volumeCombinationOfFlexshipOfVIP() throws Exception{
		logInfo("Entered volumeCombinationOfFlexshipOfVIP() Test");				
		pageNavigation(tools, flex);
		pageNavigation(tools, flex);
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
	
	
	
	
	
	
	
	
	@Test(priority=94712)
	public void validateDeliveryRates() throws Exception{
		logInfo("Entered validateDeliveryRates() Test");	
		nav2CustShopping();	
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));		
		addQuanityNdCheckOut(1);
		travelSetProducts();
		promotionalProd();
		checkout2DaysOrder();
		shippingAddressProcess(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"));
		compareDeliveryRate();
		
	}
	
}
