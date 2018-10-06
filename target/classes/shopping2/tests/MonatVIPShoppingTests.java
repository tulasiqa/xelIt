package vibe.shopping2.tests;

import org.testng.annotations.Test;
import common.Priority;
import common.SocialNetWorksMethods;
import vibe.enrollment.tests.ReleaseEnrollmentMethods;
import vibe.mywebsite.tests.MyWebSiteMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(946)
public class MonatVIPShoppingTests extends Shopping2Methods{	
	
	ShoppingMethods shop = new ShoppingMethods();	
	SocialNetWorksMethods sm = new SocialNetWorksMethods();	
	MyWebSiteMethods pwp = new MyWebSiteMethods();
	ReleaseEnrollmentMethods enr= new ReleaseEnrollmentMethods();	
	
	String tools = "Tools";
	
	@Test(priority=94600)
	public void loginAsVIPUser() throws Exception{		
		logInfo("Entered loginAsVIPUser() Test");   
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"vipFN1"),
				prop.getLocatorForEnvironment(appUrl,"vipLN1"),prop.getLocatorForEnvironment(appUrl,"vipCon1"));			
	   }
	
	@Test(priority=94601)
	public void placeOrderWithVIPUser() throws Exception{		
		logInfo("Entered placeOrderWithExistingVip() Test");   	
		nav2CustShopping();	
		travelSetProducts();
		promotionalProd();		
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName3"));		
		addQuanityNdCheckOut(2);
		promotionalProd();
		checkout2DaysOrder();
		addnewAddressForExistingUser(prop.getLocatorForEnvironment(appUrl,"vipFN1"),
				prop.getLocatorForEnvironment(appUrl,"vipLN1"));		
		/*selectSuggestedAddress();
		handle904NotAuthorized(shippingTitleTxt);*/
		selectNextInDelivery();			
		selectNextInCC();
		placeOrderWithConfimation();	
		//verifyOrderHistoryDetails(prop.getLocatorForEnvironment(appUrl,"prodName3"));
	}
	
	@Test(priority=94602)
	public void productDescriptions() throws Exception{		
		logInfo("Entered productDescriptions() Test");   
		nav2CustShopping();	
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));	
		selectTabbings();	
	   }
	
	
	@Test(priority=94603)
	public void freeShippingForVIP() throws Exception{
		logInfo("Entered freeShippingForVIP() Test");	
		nav2CustShopping();	
		pageNavigation(tools, flex);
		pageNavigation(tools, flex);
		selectNewFlex();
		selectProduct();
		addFlexshipQuanity(1);   
		freeShippingCalculations((float) 84.00);	
	}	
	
	
	@Test(priority=94604)
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
		/*promotionalProd();
		checkout2DaysOrder();*/
		expressCheckOut();
		validateDiscount();
	}
	
	@Test(priority=94605)
	public void placeOrderWithExpressCheckout() throws Exception{		
	logInfo("Entered placeOrderWithExpressCheckout() Test");   	  
		nav2CustShopping();	
		searchItembyNameOrSku(prop.getLocatorForEnvironment(appUrl,"prodName1"));
		addProductWithQuanity(1);	
		travelSetProducts();
		promotionalProd();
		checkout2DaysOrder();	
		expressCheckOut();
		placeOrderWithConfimation();
		verifyOrderHistoryDetails(prop.getLocatorForEnvironment(appUrl,"prodName1"));
	}
	
	@Test(priority=94606 /*, dependsOnMethods= {"placeOrderWithExpressCheckout"}*/)
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
	
	
	@Test(priority=94607)
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
	
	@Test(priority=94608)
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
	
	
	@Test(priority=94609)
	public void flexshipOrderOfVIP() throws Exception{
		logInfo("Entered flexshipOrderOfVIP() Test");	
		pageNavigation(tools, flex);
		pageNavigation(tools, flex);
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
	
	
	@Test(priority=94610)
	public void priceRangesInVIP() throws Exception{
		logInfo("Entered priceRangesInVIP() Test");	
		nav2CustShopping();
		rangeLevelPrice(1);
		rangeValidate(1, 30);
		deSelectRangeLevel();
		rangeLevelPrice(2);
		rangeValidate(30,50);	
		deSelectRangeLevel();
		rangeLevelPrice(3);
		rangeValidate(50,70);	
	}
	
	
	@Test(priority=94611)
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
