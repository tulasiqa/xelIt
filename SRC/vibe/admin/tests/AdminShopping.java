package vibe.admin.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.LoginCredentials;
import common.Priority;
import common.TestBase;
import common.EnvProperties;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.shopping.tests.ShoppingMethods;
import vibe.shopping2.tests.Shopping2Methods;
@Priority(47)
public class AdminShopping extends Shopping2Methods{
	
	
	LoginCredentials lc = new LoginCredentials();
	ShoppingMethods shop = new ShoppingMethods();
	MyProfileMethods profile = new MyProfileMethods();
	EnvProperties prop = new EnvProperties();	

	@Test(priority= 4701)
	public void createOptionTypes() throws Exception{
		logInfo("inside createOptionTypes() Test");
		navigate2AdminOptionTypes();
		createNewOptionTypes(txtOptiontypeName,txtOptiontypeName);
		boolean isOptiontypeFound = verifyOptionTypeIsPresent(txtOptiontypeName);
		if(isOptiontypeFound==false){
			logInfo(txtOptiontypeName + " option type could not be created.");
			Assert.assertTrue(isOptiontypeFound, txtOptiontypeName + " option type could not be created.");
		}
	}

	@Test(priority= 4702)
	public void createVirtualProduct() throws Exception{
		logInfo("inside createVirtualProduct() Test");
		
		navigate2AdminShop();
		//createNewVirtualProduct(txtVirtualProdName1);
		navigate2AdminShop();
		boolean isProdFound = false;
		isProdFound = searchProductByNameOrSKU("Product Name",txtVirtualProdName1,"All");				
		if(!isProdFound){
			Assert.assertTrue(isProdFound, " product match not found.");
		}
	}
	
	String txtVirtualProdName1 = "TestVProd903";
	
	
	int masterPrice = TestBase.generateRandomNumberInRange(1, 10);
	@Test(priority= 4703)
	public void editVirtualProduct() throws Exception{
		logInfo("inside editVirtualProduct() Test");
		navigate2AdminShop();
		boolean isProdFound = false;
		isProdFound = searchProductByNameOrSKU("Product Name",txtVirtualProdName1,"All");
		if(isProdFound){
			logInfo("product match found in search page.");
			selectProductByName(txtVirtualProdName1,"All");
			updateVirtualProductDetails(txtVirtualProdName1,txtProdSKU, masterPrice,"10");
			/*updateProductImage();
			//updateProductVariants();
			updateProductPrices();*/
			// updateProductAssets(txtVirtualProdName1);
		} else {
			logInfo(txtVirtualProdName1 + " product match not found in search page.");
			Assert.assertTrue(isProdFound, txtVirtualProdName1 + " product match not found in search page.");
		}
	}
	
	
	@Test(priority= 4704)
	public void setAdminMarketSettings() throws Exception{
		logInfo("inside setAdminMarketSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Markets",txtPreferenceLiveMarkets);
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isChangeLinkPresent = verifyElementPresent("cssSelector","div.pull-right.current-market>a");
		
		if(isChangeLinkPresent==true){
				int act_items = verifyMarketSelectionsCnt();
				
		} else {
			logInfo("isChangeLinkPresent =" +isChangeLinkPresent);
			// Assert.assertFalse(isChangeLinkPresent, " Change link is not visible.");
		} 
		
		shopUserLogout();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Markets","US,CA");
		go2Homepage();
		
		Assert.assertFalse(isChangeLinkPresent, " Change link is not visible.");
	}
	
	
	
	@Test(priority= 4706)
	public void setAdminNfrMarketSettings() throws Exception{
		logInfo("inside setAdminNfrMarketSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Nfr Markets",txtPreferenceNFRMarkets);
		
	} 
	
	
	@Test(priority= 4707)
	public void setAdminBillingAddressSettings() throws Exception{
		logInfo("inside setAdminBillingAddressSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Markets","US,CA");
		setAdminFeatureSettings("Billing Address Settings","No");
		back2Office(); 
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		addQuanityNdCheckOut(1);
		int act_cnt = verifyBillingAddressSettings();
		if(act_cnt==1){
			logInfo("correct count");
		} else {
			logInfo("incorrect count");
			// Assert.assertTrue(act_cnt==1, "Country has incorrect count");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		go2AdminShopSettingsPage();
		Assert.assertTrue(act_cnt==1, "Country has incorrect count");
		setAdminFeatureSettings("Billing Address Settings", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		addQuanityNdCheckOut(1);
		int act_cnt1 = verifyBillingAddressSettings();
		if(act_cnt1==2){
			logInfo("correct count");
		} else {
			logInfo("incorrect count");
			Assert.assertTrue(act_cnt1==2, "Country has incorrect count");
		}
				
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		
	}
	

	@Test(priority= 4708)
	public void setAdminReviewsSettings() throws Exception{
		logInfo("inside setAdminReviewsSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Reviews Settings", "No");  // txtPreferenceReviewsSettings
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isWriteReviewsPresent = verifyLinkPresent("Write A Review");
		if(isWriteReviewsPresent){
			logInfo("isWriteReviewsPresent =" +isWriteReviewsPresent);
			// Assert.assertFalse(isWriteReviewsPresent, " Reviews pane should not be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		go2AdminShopSettingsPage();
		Assert.assertFalse(isWriteReviewsPresent, " Reviews pane should not be visible.");
		setAdminFeatureSettings("Reviews Settings", "Yes");
		go2Homepage(); 
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		
		boolean isWriteReviewsPresent1 = verifyLinkPresent("Write A Review");
		if(isWriteReviewsPresent1==false){
			logInfo("isWriteReviewsPresent1 =" +isWriteReviewsPresent1);
			// Assert.assertTrue(isWriteReviewsPresent1, " Reviews pane should be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		Assert.assertTrue(isWriteReviewsPresent1, " Reviews pane should be visible.");
	}
	
	@Test(priority= 4709)
	public void setAdminWishlistSettings() throws Exception{
		logInfo("inside setAdminWishlistSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Wishlist Settings","No"); 
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isWishlistPresent = verifyLinkPresent("Add to Wishlist");
		if(isWishlistPresent){
			logInfo("isWishlistPresent =" +isWishlistPresent);
			// Assert.assertFalse(isWishlistPresent, " Wishlist should not be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		go2AdminShopSettingsPage();
		Assert.assertFalse(isWishlistPresent, " Wishlist should not be visible.");
		setAdminFeatureSettings("Wishlist Settings", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isWishlistPresent1 = verifyLinkPresent("Add to Wishlist");
		if(isWishlistPresent1==false){
			logInfo("isWishlistPresent1 =" +isWishlistPresent1);
			// Assert.assertTrue(isWishlistPresent1, " Wishlist should be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		Assert.assertTrue(isWishlistPresent1, " Wishlist should be visible.");
	}
	
	
	@Test(priority= 4710)
	public void setAdminAnalyticsSettings() throws Exception{
		logInfo("inside setAdminAnalyticsSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Analytics Settings","No");  
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isAnalyticsPresent = verifyElementPresent("xpath",panelProdAnalytics);
		if(isAnalyticsPresent){
			logInfo("isAnalyticsPresent =" +isAnalyticsPresent);
			// Assert.assertFalse(isAnalyticsPresent, " Product Analytics should not be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		go2AdminShopSettingsPage();
		Assert.assertFalse(isAnalyticsPresent, " Product Analytics should not be visible.");
		setAdminFeatureSettings("Analytics Settings", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isAnalyticsPresent1 = verifyElementPresent("xpath",panelProdAnalytics);
		if(isAnalyticsPresent1==false){
			logInfo("isAnalyticsPresent1 =" +isAnalyticsPresent1);
			// Assert.assertTrue(isAnalyticsPresent1, " Product Analytics should be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		Assert.assertTrue(isAnalyticsPresent1, " Product Analytics should be visible.");
	}
	
	
	@Test(priority= 4711)
	public void setAdminShowRecentlyViewedItemsSettings() throws Exception{
		logInfo("inside setAdminShowRecentlyViewedItemsSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Recently Viewed Settings","No");
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isRecentlyViewedPresent = verifyElementPresent("xpath",panelRecentlyViewed);
		if(isRecentlyViewedPresent){
			logInfo("isRecentlyViewedPresent =" +isRecentlyViewedPresent);
			// Assert.assertFalse(isRecentlyViewedPresent, " Recently Viewed pane should not be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		go2AdminShopSettingsPage();
		Assert.assertFalse(isRecentlyViewedPresent, " Recently Viewed pane should not be visible.");
		setAdminFeatureSettings("Recently Viewed Settings", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isRecentlyViewedPresent1 = verifyElementPresent("xpath",panelRecentlyViewed);
		if(isRecentlyViewedPresent1==false){
			logInfo("isRecentlyViewedPresent1 =" +isRecentlyViewedPresent1);
			// Assert.assertTrue(isRecentlyViewedPresent1, " Recently Viewed pane should be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		Assert.assertTrue(isRecentlyViewedPresent1, " Recently Viewed pane should be visible.");
		
	}
	
	
	@Test(priority= 4712)
	public void setAdminShowMostPopularCategoriesSettings() throws Exception{
		logInfo("inside setAdminShowMostPopularCategoriesSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Show Most Popular","No"); 
		
	}
	
	
	@Test(priority= 4713)
	public void setAdminAllowOrdersInOtherMarketsSettings() throws Exception{
		logInfo("inside setAdminAllowOrdersInOtherMarketsSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Allow Orders In Other Markets", "No"); 
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isChangeLinkPresent = verifyElementPresent("cssSelector","div.pull-right.current-market>a");
		if(isChangeLinkPresent){
			logInfo("isChangeLinkPresent =" +isChangeLinkPresent);
			// Assert.assertFalse(isChangeLinkPresent, " Change link should not be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		go2AdminShopSettingsPage();
		Assert.assertFalse(isChangeLinkPresent, " Change link should not be visible.");
		setAdminFeatureSettings("Allow Orders In Other Markets", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isChangeLinkPresent1 = verifyElementPresent("cssSelector","div.pull-right.current-market>a");
		if(isChangeLinkPresent1==false){
			logInfo("isChangeLinkPresent1 =" +isChangeLinkPresent1);
			// Assert.assertTrue(isChangeLinkPresent1, " Change link should be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		Assert.assertTrue(isChangeLinkPresent1, " Change link should be visible.");
	}
	
	
	@Test(priority= 4714)
	public void setAdminShowReorderInOrderHistorySettings() throws Exception{
		logInfo("inside setAdminShowReorderInOrderHistorySettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Show Reorder In Order History","No"); 
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isReorderPresent = verifyElementPresent("xpath",lnkReorder);
		System.out.println("isReorderPresent =" +isReorderPresent);
		if(isReorderPresent){
			logInfo(" Reorder link should not be visible.");
			// Assert.assertFalse(isReorderPresent, " Reorder link should not be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		go2AdminShopSettingsPage();
		Assert.assertFalse(isReorderPresent, " Reorder link should not be visible.");
		setAdminFeatureSettings("Show Reorder In Order History", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		go2orderHistoryPage();
		boolean isReorderPresent1 = verifyElementPresent("xpath",lnkReorder);
		System.out.println("isReorderPresent1 =" +isReorderPresent1);
		if(isReorderPresent1==false){
			logInfo(" Reorder link should be visible.");
			// Assert.assertTrue(isReorderPresent1, " Reorder link should be present.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		Assert.assertTrue(isReorderPresent1, " Reorder link should be present.");
	}
	
	
	@Test(priority= 4715)
	public void setAdminCanDeleteFinalAutoshipSettings() throws Exception{
		logInfo("inside setAdminCanDeleteFinalAutoshipSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Can Delete Final Autoship",txtPreferencesCanDeleteFinalAutoship);
		
		
	}
	
	
	@Test(priority= 4716)
	public void setAdminCanDisplayAutoshipOnHomepageSettings() throws Exception{
		logInfo("inside setAdminCanDisplayAutoshipOnHomepageSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Can Dispaly Autoship On Homepage","No");
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isAdd2AutoshipPresent = verifyElementPresent("cssSelector","button#add-to-autoship-button_on");
		System.out.println("isAdd2AutoshipPresent = " +isAdd2AutoshipPresent);
		if(isAdd2AutoshipPresent){
			logInfo(" Add To Autoship button should not be present");
			// Assert.assertFalse(isAdd2AutoshipPresent, " Add To Autoship button should not be present");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Can Dispaly Autoship On Homepage", "Yes");
		Assert.assertFalse(isAdd2AutoshipPresent, " Add To Autoship button should not be present");
		back2Office();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isAdd2AutoshipPresent1 = verifyElementPresent("cssSelector","button#add-to-autoship-button_on");
		System.out.println("isAdd2AutoshipPresent1 = " +isAdd2AutoshipPresent1);
		if(isAdd2AutoshipPresent1==false){
			logInfo(" Add To Autoship button should be present");
			// Assert.assertFalse(isAdd2AutoshipPresent1, " Add To Autoship button should be present");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		Assert.assertFalse(isAdd2AutoshipPresent1, " Add To Autoship button should be present");
	}
	
	
	@Test(priority= 4717)
	public void setAdminCanDisplayAutoshipOnCartpageSettings() throws Exception{
		logInfo("inside setAdminCanDisplayAutoshipOnCartpageSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Can Dispaly Autoship On Cartpage","Yes"); 
		
		
	}
	
	
	
	@Test(priority= 4718)
	public void setAdminSingleAutoshipSettings() throws Exception{
		logInfo("inside setAdminSingleAutoshipSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Single Autoship","Yes"); 
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isMyAutoshipsPresent = verifyAutoshipSettings();
		if(isMyAutoshipsPresent){
			logInfo("My Autoships link should not be visible.");
			// Assert.assertFalse(isMyAutoshipsPresent, "My Autoships link should not be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		Assert.assertFalse(isMyAutoshipsPresent, "My Autoships link should not be visible.");
	}
	
	
	@Test(priority= 4719)
	public void setAdminMultipleAutoshipSettings() throws Exception{
		logInfo("inside setAdminMultipleAutoshipSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Multiple Autoships","Yes"); 
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isMyAutoshipsPresent = verifyAutoshipSettings();
		if(isMyAutoshipsPresent==false){
			logInfo("My Autoships link should be visible.");
			// Assert.assertTrue(isMyAutoshipsPresent, "My Autoships link should be visible.");
		}
		
		shopUserLogout();
		go2Homepage();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		Assert.assertTrue(isMyAutoshipsPresent, "My Autoships link should be visible.");
	}
	
	
	@Test(priority= 4720)
	public void deleteOptionTypes() throws Exception{
		logInfo("inside deleteOptionTypes() Test");
		navigate2AdminOptionTypes();
		boolean isOptiontypeFound = verifyOptionTypeIsPresent(txtOptiontypeName);
		if(isOptiontypeFound){
			deleteOptionTypes(txtOptiontypeName);
		}
		
		boolean isOptiontypeDeleted = verifyOptionTypeIsPresent(txtOptiontypeName);
		if(isOptiontypeDeleted){
			logInfo(" Option Type could not be deleted.");
			Assert.assertFalse(isOptiontypeDeleted, " Option Type could not be deleted.");
		}
		
		validateTextPresentInPage("xpath", alertSuccess, alertSuccessMsg);
	}
	
	
	@Test(priority= 4721)
	public void deleteShopProduct() throws Exception{
		logInfo("inside deleteShopProduct() Test");
		navigate2AdminShop();
		
		boolean isProdFound = false;
		isProdFound = searchProductByNameOrSKU("Product Name",txtVirtualProdName,"All");
		if(isProdFound){
			logInfo("product match found in search page.");
			deleteProduct(txtVirtualProdName);
			
		} else {
			Assert.assertTrue(isProdFound, txtVirtualProdName + " product could not be found.");
		}
	
		navigate2AdminShop();
		boolean isProdDeleted = false;
		isProdDeleted = searchProductByNameOrSKU("Product Name",txtVirtualProdName,"All");
		if(isProdDeleted==false){
			Assert.assertTrue(isProdDeleted, txtVirtualProdName + " product could not be deleted.");
		}
	}
	
	
	
	
	
	
	
	
	
}
