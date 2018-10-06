package vibe.shopping2.tests;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.apache.commons.lang3.StringUtils;
import common.TestBase;
import common.EnvProperties;import vibe.enrollment.tests.ReleaseEnrollmentMethods;
import vibe.mycommunity.tests.CommunityMethods;

public class Shopping2Methods extends TestBase{
	
	CommunityMethods cm = new CommunityMethods();
	EnvProperties prop = new EnvProperties();
	ReleaseEnrollmentMethods meroll = new ReleaseEnrollmentMethods();
	

	// Navigate to admin options type page.
	
	 public void navigate2AdminOptionTypes() throws Exception{
	    	logInfo("inside navigate2AdminShop() method.");
	    	driver().navigate().to(appUrl + "shop/admin/option_types");		
	    	waitOnElement("xpath",lnkAdminNewOptionTypes);
	    }
	
	 
	// create new option types from admin
	 
	 public void createNewOptionTypes(String optionName, String optionPresentation) throws Exception{
	    	logInfo("inside createNewOptionTypes() method.");
	    	clickOnElement("xpath",lnkAdminNewOptionTypes);
	    	inputTextClear("xpath",inputOptionTypeName);
	    	inputText("xpath",inputOptionTypeName,optionName);
	    	inputTextClear("xpath",inputOptionTypePresentaion);
	    	inputText("xpath",inputOptionTypePresentaion,optionPresentation);
	    	clickOnElement("cssSelector",btnCreateOptionType);
	    	
	    	clickOnLink("linkText","Add Option Value");
	    	clickOnLink("linkText","Add Option Value");
	    	clickOnLink("linkText","Add Option Value");
	    	
	    	
	    	int arr_flavours = flavour.length;
	    	
	    	WebElement tblOptionValues = driver().findElement(By.xpath("//tbody[@id='option_values']"));
	    	List allOptions = tblOptionValues.findElements(By.tagName("tr"));
	    	int all_options = allOptions.size();
	    	
	    	String before_OptionName = "//tbody[@id='option_values']/tr[";
	    	String after_OptionName = "]/td[2]/input";
	    	
	    	String before_OptionDisplay = "//tbody[@id='option_values']/tr[";
	    	String after_OptionDisplay	 = "]/td[3]/input";
	    	
	    	for(int i=1;i<=all_options;i++){
	    			inputText("xpath",before_OptionName+i+after_OptionName,flavour[i-1]);
	    			inputText("xpath",before_OptionDisplay+i+after_OptionDisplay,flavour[i-1]);
	    	}
	    	
	    	clickOnElement("cssSelector",btnUpdatePrices);
	    }
	 
	 
	 // Verify if option type is present
	 
	 public boolean verifyOptionTypeIsPresent(String optiontypeName) throws Exception{
		    logInfo("inside verifyOptionTypeIsPresent() method.");
		    
		    boolean isOptionTypeFound = false;
		    
		    WebElement tbl  = driver().findElement(By.xpath(tblOptionTypes));
		    List allRows = tbl.findElements(By.tagName("tr"));
		    int all_rows = allRows.size();
		    
		    String before_name = "//table[@id='listing_option_types']/tbody/tr[";
		    String after_name = "]/td[2]";
		    
		    if(all_rows > 0){
		    	for(int i=1;i<=all_rows;i++){
		    		WebElement optionName = driver().findElement(By.xpath(before_name+i+after_name));
		    		String actName = optionName.getText().trim();
		    		if(actName.equalsIgnoreCase(optiontypeName)){
		    			isOptionTypeFound = true;
		    			break;
		    		}
		    	}
		    }
			return isOptionTypeFound;
		    
		 }
	 	
	 
	 // delete option types
	 
	 public void deleteOptionTypes(String optiontypeName) throws Exception{
	    logInfo("inside deleteOptionTypes() method.");
	    	
	    WebElement tbl  = driver().findElement(By.xpath(tblOptionTypes));
	    List allRows = tbl.findElements(By.tagName("tr"));
	    int all_rows = allRows.size();
	    
	    String before_name = "//table[@id='listing_option_types']/tbody/tr[";
	    String after_name = "]/td[2]";
	    
	    String before_delete = "//table[@id='listing_option_types']/tbody/tr[";
	    String after_delete = "]/td[4]/a[2]";
	    
	    if(all_rows > 0){
	    	for(int i=1;i<=all_rows;i++){
	    		
	    		WebElement optionName = driver().findElement(By.xpath(before_name+i+after_name));
	    		String actName = optionName.getText().trim();
	    		if(actName.equalsIgnoreCase(optiontypeName)){
	    			WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
	    			delete.click();
	    			// Thread.sleep(2000);
	    		//	confirmEventDeleteModal();
	    			confirmOK();
	    			break;
	    		}
	    	}
	    }
	    
	 }
	 
	 
	// navigate to admin shop products page
	 
    public void navigate2AdminShop() throws Exception{
    	logInfo("inside navigate2AdminShop() method.");
    	driver().navigate().to(appUrl + "shop/admin/products");		
    	waitOnElement("xpath",lnkAdminNewProduct);
    }


    // Create a new product at admin
    
    public void createNewProduct(String prodName) throws Exception{
    	logInfo("inside createNewProduct() method.");
    
    	verifyElementPresent("xpath",lnkAdminNewProduct);
    	clickOnElement("xpath",lnkAdminNewProduct);
    	
    	verifyElementPresent("xpath",inputShopProductName);
    	inputText("xpath",inputShopProductName,prodName);   
    	
    	inputTextClear("xpath",inputShopMasterPrice);
    	inputText("xpath",inputShopMasterPrice,shopMasterPrice_text);
    		
    	inputTextClear("xpath",inputShopProductAvailableOn);
    	inputText("xpath",inputShopProductAvailableOn,shopProductAvailableOn_text);
    	    	
    	verifyElementPresent("xpath",eleShopShippingCategory);
    	clickOnElement("xpath",eleShopShippingCategory);
    	
    	WebElement tbl = driver().findElement(By.xpath(drpdnShopTaxons));
    	List allItems = tbl.findElements(By.tagName("li"));
    	int all_items = allItems.size();
    	String before_item = "//*[@id='select2-drop']/ul[@id='select2-results-2']/li[";
    	String after_item = "]/div";
    	
    	if(all_items>0){
    		for(int j=1;j<=all_items;j++){
    			WebElement item = driver().findElement(By.xpath(before_item+j+after_item));
    			String item_name = item.getText().trim();
    			System.out.println(item_name);
    			if(item_name.matches(shopShippingCategory_text)){
    				System.out.println("clicked item " + shopShippingCategory_text);
    				item.click();
    				break;
    			} 
    		}
    	}
    	
    	clickOnButton("cssSelector",btnShopProductCreate);
    	
     } 
  
    
    
    
    public boolean searchProductByNameOrSKU(String searchType, String value, String searchMarket) throws Exception{
    	logInfo("inside searchProductByNameOrSKU() method.");
    	
    	inputTextClear("xpath",inputSearchProdName);
    	inputTextClear("xpath",inputSearchSKUName);
    	
    	switch(searchType){
	    	case "Product Name" :
	    		inputText("xpath",inputSearchProdName,value);
	    		break;
	    	case "SKU" :
	    		inputText("xpath",inputSearchSKUName,value);
	    		break;
	    	}
    	
	    	
	    	selectFromDropdown("xpath",inputSearchMarket,"byVisibleText",searchMarket);
	    	clickOnButton("xpath",btnSearchProduct);
	    	Thread.sleep(10000);
	    	boolean isProdFound = false;
	    	
	    	waitOnElement("xpath",panelProductsList);
	    	String beforeSKU_Name = "//*[@id='listing_products']/tbody/tr[";
	    	String afterSKU_Name = "]/td[1]";
	    	
	    	String beforeProd_Name = "//*[@id='listing_products']/tbody/tr[";
	    	String afterProd_Name = "]/td[4]/a";
	    	
	    	WebElement prodPanel = driver().findElement(By.xpath("//table[@id='listing_products']/tbody"));
	    	List allProducts = prodPanel.findElements(By.tagName("tr"));
	    	int all_prods = allProducts.size();
	    		    	
	    	System.out.println("Total matching products = " +all_prods);
	 
	    	if(all_prods > 0){
	    		for(int i=1;i<=all_prods;i++){
	    			
	    			switch(searchType){
	    	    	case "Product Name" :
	    	    		WebElement prod = driver().findElement(By.xpath(beforeProd_Name+i+afterProd_Name));
		    			String actProd = prod.getText().trim();
		    			System.out.println("actProd = " +actProd);
		    			if(actProd.equalsIgnoreCase(value)){
		    				System.out.println("matching value = " +value);
		    				isProdFound = true;
		    				logInfo(value + " Product Name match found.");
		    				break;
		    			}
		    			
	    	    	case "SKU" :
	    	    		WebElement sku = driver().findElement(By.xpath(beforeSKU_Name+i+afterSKU_Name));
		    			String actSKU = sku.getText().trim();
		    			System.out.println("actSKU = " +actSKU);
		    			if(actSKU.equalsIgnoreCase(value)){
		    				logInfo(value + " SKU match found.");
		    				isProdFound = true;
		    				break;
		    			}
			    	}
	    			
	    		}
	    	}
    	
  		return isProdFound;
   	
    }
    
    
    
    public boolean selectProductByName(String prodName, String searchMarket) throws Exception{
    	logInfo("inside selectProductByName() method.");
    	
    	 inputTextClear("xpath",inputSearchProdName);
	     inputText("xpath",inputSearchProdName, prodName );
	     selectFromDropdown("xpath",inputSearchMarket,"byVisibleText",searchMarket);
	     clickOnButton("xpath",btnSearchProduct);
	     Thread.sleep(10000);
     	 boolean isProdFound = false;
	     
     	waitOnElement("xpath",panelProductsList);
    	   	
    	String beforeProd_Name = "//*[@id='listing_products']/tbody/tr[";
    	String afterProd_Name = "]/td[4]/a";
    	
    	WebElement prodPanel = driver().findElement(By.xpath("//table[@id='listing_products']/tbody"));
    	List allProducts = prodPanel.findElements(By.tagName("tr"));
    	int all_prods = allProducts.size();
    		    	
    	System.out.println("Total matching products = " +all_prods);
    	
    	if(all_prods > 0){
    		for(int i=1;i<=all_prods;i++){
    			WebElement prod = driver().findElement(By.xpath(beforeProd_Name+i+afterProd_Name));
    			String actProd = prod.getText().trim();
    			System.out.println("actProd = " +actProd);
    			if(actProd.equalsIgnoreCase(prodName)){
    				logInfo(prodName + " Product Name match found.");
    				isProdFound = true;
    				prod.click();
    				break;
    			}
    		
    		}
    	}
		return isProdFound;
	 }
    
    
    
    public boolean selectProductBySKU(String skuName, String searchMarket) throws Exception{
    	logInfo("inside selectProductBySKU() method.");
    	
    	inputTextClear("xpath",inputSearchSKUName);
		inputText("xpath",inputSearchSKUName,skuName);
	     selectFromDropdown("xpath",inputSearchMarket,"byVisibleText",searchMarket);
	     clickOnButton("xpath",btnSearchProduct);
	     Thread.sleep(10000);
     	 boolean isProdFound = false;
	     
     	waitOnElement("xpath",panelProductsList);
    	   	
    	String beforeProd_Name = "//*[@id='listing_products']/tbody/tr[";
    	String afterProd_Name = "]/td[4]/a";
    	
    	WebElement prodPanel = driver().findElement(By.xpath("//table[@id='listing_products']/tbody"));
    	List allProducts = prodPanel.findElements(By.tagName("tr"));
    	int all_prods = allProducts.size();
    		    	
    	System.out.println("Total matching products = " +all_prods);
    	
    	if(all_prods > 0){
    		for(int i=1;i<=all_prods;i++){
    			WebElement prod = driver().findElement(By.xpath(beforeProd_Name+i+afterProd_Name));
    			String actProd = prod.getText().trim();
    			System.out.println("actProd = " +actProd);
    			if(actProd.equalsIgnoreCase(skuName)){
    				logInfo(skuName + " Product Name match found.");
    				isProdFound = true;
    				prod.click();
    				break;
    			}
    		
    		}
    	}
		return isProdFound;
	 }
    
    
    
    public void updateVirtualProductDetails(String prodName,String sku, int masterPrice, String costCurrency) throws Exception{
    	logInfo("inside updateVirtualProductDetails() method.");
    	
    	String taxons = "Categories -> VIBE shopping";
    	int FutueDate =0;
    	String futDate = getDate(FutueDate, "yyyy/MM/dd");
    	int costPrice =masterPrice/90; 
    	String mp = Integer.toString(masterPrice);
    	
    	String cp = Integer.toString(costPrice);
    	clickOnElement("xpath",lnkShopDetails);
    	
    	waitOnElement("xpath",inputShopProductName);
    	inputTextClear("xpath",inputShopProductName);
    	inputText("xpath",inputShopProductName,prodName);
    	
    	inputTextClear("xpath",inputShopMasterPrice);
    	inputText("xpath",inputShopMasterPrice,mp);
    	
    	inputTextClear("xpath",inputCostPrice);
    	inputText("xpath",inputCostPrice, cp);
    	
    	inputTextClear("xpath",inputProdCostCurrency);
    	inputText("xpath",inputProdCostCurrency, costCurrency);
    	
    	inputTextClear("xpath",inputShopProductAvailableOn);
    	inputText("xpath",inputShopProductAvailableOn,futDate);
    	
    	inputTextClear("xpath",inputShopProductSKU);
    	inputText("xpath",inputShopProductSKU, sku);
    	
    	
    	// Taxons
    	
    	clickOnElement("xpath",inputProdTaxons);
    	Thread.sleep(5000);
    	System.out.println("neted into");
    	waitOnElement("xpath","//*[@id='select2-drop']/ul");
    	WebElement panelTaxons = driver().findElement(By.xpath("//*[@id='select2-drop']/ul"));
    	List alltaxons = panelTaxons.findElements(By.tagName("li"));
    	int all_taxons = alltaxons.size();
    	System.out.println("all_taxons =" + all_taxons);
    	
    	String before_taxon = "//*[@id='select2-drop']/ul/li[";
    	String after_taxon = "]/div";
    	
    	if(all_taxons>0){
	    	for(int i=1;i<=all_taxons;i++){
	    		WebElement e = driver().findElement(By.xpath(before_taxon+i+after_taxon));
	    		String acttaxon = e.getText().trim();
	    		System.out.println("acttaxon =" +acttaxon);
	    		if(acttaxon.equalsIgnoreCase(taxons)){
	    			e.click();
	    			Thread.sleep(3000);
	    			break;
	    		}
	    	}
    	}
    	
    	// Option Types
    	String txtOptiontypeName= "TeaFlavour255";
    	
    	clickOnElement("xpath",inputProdOptionTypes);
    	Thread.sleep(5000);
    	
    	waitOnElement("xpath","//*[@id='select2-drop']/ul");
    	WebElement panelOptionTypes = driver().findElement(By.xpath("//*[@id='select2-drop']/ul"));
    	List alloptions = panelOptionTypes.findElements(By.tagName("li"));
    	int all_options = alloptions.size();
    	
    	String before_option = "//*[@id='select2-drop']/ul/li[";
    	String after_option = "]/div";
    	
    	if(all_options>0){
	    	for(int j=1;j<=all_options;j++){
	    		WebElement e = driver().findElement(By.xpath(before_option+j+after_option));
	    		String actoption = e.getText().trim();
	    		System.out.println("actoption =" +actoption);
	    		if(actoption.equalsIgnoreCase(txtOptiontypeName+" ("+txtOptiontypeName+")")){
	    			e.click();
	    			Thread.sleep(3000);
	    			break;
	    		}
	    	}
    	}
    	
    	getText("cssSelector",btnShopProductUpdate, "Test s");
    	clickOnElement("cssSelector",btnShopProductUpdate);
    }

    
    public void updateProductImage() throws  Exception{
    	logInfo("inside updateProductImage() method.");
    	
    	waitOnElement("xpath",lnkShopImage);
    	clickOnElement("xpath",lnkShopImage);
    	
    	clickOnLink("linkText","Add Image");
    	System.out.println("Select add image");
    	
    	waitOnElement("cssSelector",browseAttachment);
    	 uploadFile("Image", browseAttachment);
    	/*//Thread.sleep(3000);
    	
    	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\OrganicTeaIndia.exe");
		Thread.sleep(10000);*/
    	
		inputText("xpath",inputAltText,"organic Prod"+TestBase.generateRandomString());
		clickOnElement("cssSelector",btnCreateShopImg);
    }
    
    
    
    public void updateProductVariants() throws  Exception{
    	logInfo("inside updateProductVariants() method.");
    	
    	clickOnElement("xpath",lnkShopVariants);
    	
    	clickOnLink("linkText","Variant From Existing Product");
    	clickOnElement("xpath",drpdnVariantOption);
    	clickOnElement("xpath","//div[@id='select2-drop']/ul[@class='select2-results']/li[2]/div");
    	inputText("xpath",inputSKUValue,txtSkuValue );  // 0101191009
    	clickOnElement("cssSelector",btnUpdatePrices);
    	Thread.sleep(10000);
    	
    	/*clickOnLink("linkText","Variant From Existing Product");
    	clickOnElement("xpath",drpdnVariantOption);
    	clickOnElement("xpath","//div[@id='select2-drop']/ul[@class='select2-results']/li[3]/div");
    	inputText("xpath",inputSKUValue,"10-0004");
    	clickOnElement("cssSelector",btnUpdatePrices);
    	
    	clickOnLink("linkText","Variant From Existing Product");
    	clickOnElement("xpath",drpdnVariantOption);
    	clickOnElement("xpath","//div[@id='select2-drop']/ul[@class='select2-results']/li[4]/div");
    	inputText("xpath",inputSKUValue,"15-0006");
    	clickOnElement("cssSelector",btnUpdatePrices);
    	
    	clickOnLink("linkText","Variant From Existing Product");
    	clickOnElement("xpath",drpdnVariantOption);
    	clickOnElement("xpath","//div[@id='select2-drop']/ul[@class='select2-results']/li[5]/div");
    	inputText("xpath",inputSKUValue,"15-0011");
    	clickOnElement("cssSelector",btnUpdatePrices);*/
   	
    }
    
    public void updateProductPrices() throws  Exception{
    	logInfo("inside updateProductPrices() method.");
    	
    	clickOnElement("xpath",lnkShopPrices);
    	
    	inputText("xpath",inputRetailPrice,"800");
    	inputText("xpath",inputRetailPV,"801");
    	inputText("xpath",inputRetailCV,"802");
    	
    	inputText("xpath",inputPreferedCustomerPrice,"600");
    	inputText("xpath",inputPreferedCustomerPV,"601");
    	inputText("xpath",inputPreferedCustomerCV,"602");
    	
    	inputTextClear("xpath",inputWholesalePrice);
    	inputText("xpath",inputWholesalePrice,"300");
    	inputTextClear("xpath",inputWholesalePV);
    	inputText("xpath",inputWholesalePV,"301");
    	inputTextClear("xpath",inputWholesaleCV);
    	inputText("xpath",inputWholesaleCV,"302");
    	
    	clickOnElement("cssSelector",btnUpdatePrices);
    	
    }
    
    public void updateProductAssets(String assetTitle) throws  Exception{
    	logInfo("inside updateProductAssets() method.");
    	
    	clickOnElement("xpath",lnkShopAssets);
    	
    	// Add New Document
    	
    	clickOnLink("linkText","New Document");
    	waitOnElement("xpath",inputProdAssetTitle);
    	inputText("xpath",inputProdAssetTitle,assetTitle);
    	waitOnElement("cssSelector",browseDocument);
    	 uploadFile("PDF", browseDocument);
    	
    	/*Thread.sleep(3000);
    	
    	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_PDF.exe");
		Thread.sleep(10000);
		*/
		clickOnElement("xpath",btnCreateProductAsset);
		
		// Add New Video
		
		clickOnLink("linkText","New Video");
    	waitOnElement("xpath",selectVideoType);
    	selectFromDropdown("xpath",selectVideoType, "byVisibleText", "URL");
    	inputText("xpath",inputShoppingVideoTitle, txtVideoAsset);
    	inputText("xpath",inputShoppingVideoURL, "https://www.youtube.com/watch?v=e2qbJVe80kw");
    	
		clickOnElement("xpath",btnCreateVideoAsset);
    }
    
    
    
    
    
    public void go2AdminShopSettingsPage() throws  Exception{
    	logInfo("inside go2AdminShopSettingsPage() method.");
    	driver().navigate().to(appUrl + "shop/admin/general_settings/edit");		
    	Assert.assertTrue(verifyTextPresent("General Settings"));
    }
    
    
     public void setAdminFeatureSettings(String settingsName, String value) throws Exception{
    	logInfo("inside setAdminFeatureSettings() method.");
    	
    	clickOnLink("linkText","Features Settings");
    	
    	switch(settingsName){
	    	case "Markets" :
	    		inputTextClear("cssSelector",inputPreferenceLiveMarkets);
	    		inputText("cssSelector",inputPreferenceLiveMarkets,value);
	    		break;
	    	case "Nfr Markets" :
	    		inputTextClear("cssSelector",inputPreferenceNFRMarkets);
	    		inputText("cssSelector",inputPreferenceNFRMarkets,value);
	    		break;
	    	case "Billing Address Settings" :
	    		selectFromDropdown("cssSelector",drpdnPreferenceBillingAddressSettings,"byVisibleText",value);
	    		break;
	    	case "Reviews Settings" :
	    		selectFromDropdown("cssSelector",drpdnPreferenceReviewsSettings,"byVisibleText",value);
	    		break;
	    	case "Wishlist Settings" :
	    		selectFromDropdown("cssSelector",drpdnPreferenceWishlistSettings,"byVisibleText", value);
	    		break;
	    	case "Analytics Settings" :
	    		selectFromDropdown("cssSelector",drpdnPreferenceAnalyticsSettings,"byVisibleText", value);
	    		break;	
	    	case "Recently Viewed Settings" :
	    		selectFromDropdown("cssSelector",drpdnPreferenceShowRecentlyReviewsItems,"byVisibleText", value);
	    		break;	
	    	case "Show Most Popular" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesShowMostPopularCategories,"byVisibleText", value);
	    		break;	
	    	case "Allow Orders In Other Markets" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesAllowOrdersInOtherMarkets,"byVisibleText", value);
	    		break;		
	    	case "Show Reorder In Order History" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesShowReorderInOrderHistory,"byVisibleText", value);
	    		break;	
	    	case "Can Delete Final Autoship" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesCanDeleteFinalAutoship,"byVisibleText", value);
	    		break;	
	    	case "Can Dispaly Autoship On Homepage" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesCanDisplayAutoshipOnHomepage,"byVisibleText", value);
	    		break;	
	    	case "Can Dispaly Autoship On Cartpage" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesCanDisplayAutoshipOnCartpage,"byVisibleText", value);
	    		break;	
	    	case "Single Autoship" :
	    		selectRadioOrCheckbox("cssSelector",radioPreferenceSingleAUtoship);
	    		break;
	    	case "Multiple Autoships" :
	    		selectRadioOrCheckbox("cssSelector",radioPreferenceMultipleAUtoship);
	    		break;	
    	}
    	
    	clickOnElement("cssSelector",btnUpdateFetureSettings);
    	Thread.sleep(3000);
    	confirmationMessage("Successfully Updated");
    	Thread.sleep(5000);
    	
    }
    
    
    // Shopping User side methods
    
     public void go2Homepage() throws Exception{
 		logInfo("inside go2Homepage() method");
 		
 		driver().navigate().to(appUrl);
 	}
     
     
     public void shopUserLogout() throws  Exception{
    	 logInfo("Entered into shopUserLogout() method");    	 
    	 clickOnElement("cssSelector",shopProfileDrpdn);
    	 waitOnElement("cssSelector",shopLogout);
    	 List <WebElement> lis = driver().findElements(By.cssSelector(shopLogout));
    	 boolean isFound = false;
    	 for(WebElement lists :lis) {
    		 String dpList = lists.getText().trim();
    		 if(dpList.equalsIgnoreCase("Logout")) {
    			 isFound=true;
    			 lists.click();
    			 Thread.sleep(3000);
    			 break;
    		 }
    	 }if(isFound==false) {
    		 Assert.assertTrue(isFound, "Logout is not found");
    	 }
    	 
    	 
     }
     
	public void selectMarket(String market) throws Exception{
		logInfo("inside selectMarket() method");
		
		selectFromDropdown("cssSelector",selectMark,"byVisibleText",market);
		clickOnButton("cssSelector", markOkBtn);
	}
    
	public void custShoppingNavigation() throws Exception{
		logInfo("inside custShoppingNavigation() method");
		
		driver().navigate().to(appUrl + "shop/login");
	}
	
	
	
	//Select Products from search field and the select the product
    public void searchFlexshipProduct(String nameorSku ) throws Exception{
  	  logInfo("Entered into searchItembyNameOrSku() method"); 	    
	    	selectProductsfromCategory("All");
	    	waitOnElement("cssSelector", searchAutoProd);
	    	inputTextClear("cssSelector", searchAutoProd);
	    	inputText("cssSelector", searchAutoProd, nameorSku);
	    	submitElement("cssSelector", searchAutoProd);	    	
	    	selectProdFromList(nameorSku);
	    		
	}
    
    public boolean verifyProductReviewsPresent(String prodName){
    	logInfo("inside verifyProductReviewsPresent() method");    	
    	boolean isReviewPanePresent = false;    	
    	WebElement reviewPane = driver().findElement(By.xpath(panelReviews));
    	if(reviewPane.isDisplayed()){
    		isReviewPanePresent = true;
    		System.out.println("isReviewPanePresent = " +isReviewPanePresent);
    		}    	
    	return isReviewPanePresent;
    }
    
    
    public void existingUserCredentials(String shopUser, String passwd, String seletMarket) throws Exception {
  	  // logInfo("Entered existingUserCredentials() method");   
  	  	logInfo("inside  existingUserCredentials() method");   
  	  	Thread.sleep(5000);
		logOut();	
		custShoppingNavigation();
		selectMarket(seletMarket);		
		// clickOnLink("linkText", "Login");
		waitOnElement("cssSelector","ul.nav.navbar-nav > li:nth-child(1) > a > span");
		clickOnElement("cssSelector","ul.nav.navbar-nav > li:nth-child(1) > a > span");		// login link
		Thread.sleep(2000);		
		//driver().navigate().refresh();
		//Thread.sleep(2000);		
		waitOnElement("xpath", inputName);
		inputTextClear("xpath", inputName);
		inputText("xpath", inputName, shopUser);
		if (driver().getCurrentUrl().contains("branch")){ 
		inputText("cssSelector", custShopPwdInBranch, passwd);
		}else{
			inputText("cssSelector", custShopPwdInMaster, passwd);
		}
	    clickOnButton("cssSelector", userloginBtn);
	    Thread.sleep(10000);
	}
    
    
    public int verifyMarketSelectionsCnt() throws Exception {
    	  logInfo("Entered verifyMarketSelections() method"); 
    	  
    	  clickOnElement("cssSelector","div.pull-right.current-market>a");

			Select select = new Select(driver().findElement(org.openqa.selenium.By.cssSelector("select#market")));
			List <WebElement> allOptions = select.getOptions();
			for(WebElement x : allOptions){
				System.out.println(x.getText().trim());
			}
			
			int cnt = allOptions.size();
			
			clickOnElement("cssSelector", "div.modal-content > div.modal-footer > button.btn.btn-default ");
			Thread.sleep(5000);
			return cnt;
    }
    
    
   
 
   /* // select button either Next or Save& Continue in Shipping 
	    public void selectNextRSaveNdContinueInShipping(String typeNextOrSave) throws Exception{    	  
	  	 logInfo("entered into selectNextRSaveNdContinueInShipping(String typeNextOrSave) method");
	  	  if (typeNextOrSave=="next"){    	  
	  	  clickOnButton("cssSelector", shipNext);
	  	  
	  	  System.out.println("selected Next");
	  	  } else {    		 
	  		  clickOnButton("cssSelector", shipSave); 
	  		  System.out.println("selected save");
	  	  }Thread.sleep(2000);
	    }*/
    
    public int verifyBillingAddressSettings() throws Exception{
    	logInfo("inside verifyBillingAddressSettings() method.");	
    	
    	selectNextRSaveNdContinueInShipping("next");
		selectNextRSaveNdContinueInShipping("next");
		
		clickOnElement("cssSelector","#use_existing_card_no");
		clickOnElement("cssSelector","#use_existing_billing_address_no");
		
		Select select = new Select(driver().findElement(By.cssSelector("#order_payment_source_address_country_id")));
		List allOptions = select.getOptions();
		int all_items = allOptions.size();
		System.out.println("all_items = "+all_items);
		
		return all_items;
    }
    
    
    public void go2orderHistoryPage(){
    	logInfo("inside go2orderHistoryPage() method.");	
    	driver().navigate().to(appUrl + "shop/orderhistory");
    }
    
    public void go2AutoshipPage(){
    	logInfo("inside go2AutoshipPage() method.");	
    	driver().navigate().to(appUrl + "shop/autoships");
    }
    
    
    public boolean verifyAutoshipSettings() throws Exception{
	    	logInfo("inside verifyAutoshipSettings() method.");	
	    	
	    	go2AutoshipPage();
	    		    	
	    	boolean isNewAutoshipPresent = verifyElementPresent("xpath","//*[@id='main-content']/div/div[1]/div[2]/div/div[2]/a");
	    	if(isNewAutoshipPresent){
	    		logInfo("isNewAutoshipPresent =" +isNewAutoshipPresent);
	    		clickOnElement("xpath","//*[@id='main-content']/div/div[1]/div[2]/div/div[2]/a");
	    	}
	    	
	    	waitOnElement("cssSelector","div.panel-tools > nav > ul.nav.navbar-nav.navbar-right  > li:nth-child(2) > a");
	    	
	    	WebElement myAutoships = driver().findElement(By.cssSelector("div.panel-tools > nav > ul.nav.navbar-nav.navbar-right  > li:nth-child(2) > a"));
	    	String act_text = myAutoships.getText().trim();
	    	System.out.println("act_text =" +act_text);
	    	boolean isMyAutoshipPresent = false;
	    	if(act_text.equalsIgnoreCase("My Autoships")){
	    		isMyAutoshipPresent = true;
	    		logInfo("isMyAutoshipPresent =" +isMyAutoshipPresent);
	    	}
	    	
	    	System.out.println("isMyAutoshipPresent =" +isMyAutoshipPresent);
	    	
		return isMyAutoshipPresent;
    }
    
    
    // delete a product from admin
    
    public void deleteProduct(String prodName) throws  Exception{
    	logInfo("inside deleteProduct() method");
    	
    	
    	waitOnElement("xpath",panelProductsList);
    	
    	String before_delete = "//*[@id='listing_products']/tbody/tr[";
    	String after_delete = "]/td[6]/a[2]";
    	
    	String beforeProd_Name = "//*[@id='listing_products']/tbody/tr[";
    	String afterProd_Name = "]/td[4]/a";
    	
    	WebElement prodPanel = driver().findElement(By.xpath("//table[@id='listing_products']/tbody"));
    	List allProducts = prodPanel.findElements(By.tagName("tr"));
    	int all_prods = allProducts.size();
    		    	
    	System.out.println("Total matching products = " +all_prods);
    	if(all_prods>0){
    		for(int i=1;i<=all_prods;i++){
    			WebElement ename = driver().findElement(By.xpath(beforeProd_Name+i+afterProd_Name));
    			String actProd = ename.getText().trim();
    			if(actProd.equalsIgnoreCase(prodName)){
    				WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
    				delete.click();
    				Thread.sleep(2000);
    				try{
    					//confirmEventDeleteModal();
    					confirmOK();
    				} catch(Exception e) {
    					e.getMessage();
    				}
    				Thread.sleep(2000);
    				break;
    			}
    		}
    	
    		
    	}
    }
	
	
	
	// Ravi
    
    
    public void marketSelection(String market) throws Exception{
		logInfo("Entered selectCustMarket method");
		System.out.println("inside selectCustMarket() method.");	
		driver().navigate().to(appUrl + "/shop/login");
		waitOnElement("xpath", marketList);
		List<WebElement> mrt = driver().findElements(By.xpath(marketList));
		for(WebElement mrks :mrt) {
			boolean isMarkFound = false;
			String actMarket = mrks.getText().trim();
			System.out.println("actMarket "+ actMarket);
			if(actMarket.equalsIgnoreCase(market)) {
				isMarkFound=true;
				mrks.click();				
				break;
			}if(isMarkFound==false) {
				Assert.assertTrue(isMarkFound, market+ " is not found");
			}
		}
    }
	
	
		
	public void selectCustMarket(String market) throws Exception{
		logInfo("Entered selectCustMarket method");
		System.out.println("inside selectCustMarket() method.");	
		driver().navigate().to(appUrl + "/shop/login");
		waitOnElement("xpath", marketList);
		Thread.sleep(3000);
		boolean ismarketFound = false;
		List<WebElement> mrt = driver().findElements(By.xpath(marketList));
		for(WebElement mrks :mrt) {
			String actMarket = mrks.getText().trim();
			System.out.println("actMarket "+ actMarket);
			if(actMarket.equalsIgnoreCase(market)) {
				ismarketFound=true;
				mrks.click();			
				break;
			}
		}if (ismarketFound==false) {
			Assert.assertTrue(ismarketFound, market+ " is not found");
		}
		
		

		
	}
	
	public void nav2CustShopping() throws Exception{
		logInfo("Enter url for shopping without user.");			
		driver().navigate().to(appUrl + "/shop");
		Thread.sleep(2000);
	}
	
	public void nav2OrderHistory(){
		logInfo("Enter url for shopping without user.");			
		driver().navigate().to(appUrl + "/shop/orderhistory"); 
	}

	public void navMarketInEnrollment(){
		logInfo("Enter url to access Market .");			
		driver().navigate().to(appUrl +"/pyr_core/v2_enrollments/select_market");
	}
	
	
	public void nav2WishList() throws Exception{
		logInfo("Enter url to access Market .");	
		Thread.sleep(3000);
		driver().navigate().to(appUrl +"shop/wishlists");
	}
	
	public void nav2ShopCart() throws Exception{
		logInfo("Enter url to access Market .");	
		Thread.sleep(3000);
		driver().navigate().to(appUrl +"shop/cart");
	}
	
	public void logoutFromShopCustomer() throws Exception{
		logInfo("Entered into logoutFromShopCustomer() method");
		System.out.println("Entered into logoutFromShopCustomer() method");
		//Thread.sleep(10000);
		clickOnElement("cssSelector", userIconInShop);
		clickOnElement("cssSelector", logoutCust);
		confirmationMessage("Signed out successfully.");

	}	
	
	public void registerNewCustomer(String Fname, String Lname) throws Exception {		
		logInfo("Entered into registerNewCustomer() method");		
		waitOnElement("linkText", "Login");
		clickOnLink("linkText", "Login");
		Thread.sleep(3000);
		waitOnElement("cssSelector", registerNew);	
		waitOnElement("cssSelector", registerNew);	
		clickOnElement("cssSelector", registerNew);	
		waitOnElement("cssSelector", custFN);	
		inputTextClear("cssSelector", custFN);	
		inputTextClear("cssSelector", custLN);
		inputText("cssSelector", custFN, Fname);
		inputText("cssSelector", custLN, Lname);
		waitOnElement("cssSelector", custEmail);	
		inputTextClear("cssSelector", custEmail);
		inputText("cssSelector", custEmail, (Fname+Lname+"@icentris.com"));
		inputText("cssSelector", custPhone, usaPhone);
		inputText("cssSelector", custAd1, usaAdd1);
		inputText("cssSelector", custAd2, usaAdd2);
		inputText("cssSelector", custCounty, usaCounty);
		inputText("cssSelector", custCity, usaCounty );
		stateSelection(usaCounty);		
		waitOnElement("cssSelector", custZip);	
		inputText("cssSelector", custZip, "84087");		
		inputTextClear("cssSelector", custUserName);
		inputText("cssSelector", custUserName, Fname);
		inputText("cssSelector", custPwd, custPwdText);
		inputText("cssSelector", custConPwd, custPwdText);	
		inputTextClear("cssSelector", custFN);	
		inputTextClear("cssSelector", custLN);
		inputText("cssSelector", custFN, Fname);
		inputText("cssSelector", custLN, Lname);
		inputTextClear("cssSelector", custEmail);
		inputText("cssSelector", custEmail, (Fname+Lname+"@icentris.com"));
		clickOnButton("cssSelector", custCreate);		
		confirmationMessage("Signed in successfully.");		
	}
	
	
	public void genderAndDOB(String gender) throws Exception{
		
		inputText("cssSelector", custDOB, "02/01/1990");		
		driver().findElement(By.cssSelector(custGender)).click();
		List <WebElement> gen =driver().findElements(By.cssSelector(custGenderOpt));
		for (WebElement dd : gen){
			System.out.println(dd.getText());
			if (dd.getText().equalsIgnoreCase(gender)){
				Actions act = new Actions(driver());
				act.doubleClick(dd).build().perform();				
				break;
			}			
		}
	}
	
	public void stateSelection (String enterState) throws Exception{		
		boolean isState= false;
		waitOnElement("cssSelector", custStateList);
		List <WebElement> ss = driver().findElements(By.cssSelector(custStateList));		
		for (int i=1; i<=ss.size(); i++){
			WebElement sts = driver().findElement(By.cssSelector(stateBfr+i+")"));
			String states = sts.getText().trim();			
			if (states.equalsIgnoreCase(enterState)){
				isState = true;					
				sts.click();				
				break;
			}			
		}if (isState==false){
			Assert.assertTrue(isState, enterState +" is not present");
		}
		
		

	}
	
	/*public void selectState(String enterState) throws  Exception{			
		logInfo("Select "+ enterState +" State from the USA States");
		   
		    Thread.sleep(2000);
		    clickOnElement("cssSelector", state);
		    
		    Select stat = new Select(driver().findElement(By.cssSelector(state)));
		    stat.selectByVisibleText(enterState);
		    Thread.sleep(1000);
		    stat.selectByVisibleText(enterState);
		    
			
			}	*/
	
	public void selectState_0ld(String enterState) throws  Exception{			
		logInfo("Select "+ enterState +" State from the USA States");
		    boolean isStateFound = false;
		    Thread.sleep(2000);
		    clickOnElement("cssSelector", state);
			List<WebElement> stat = driver().findElements(By.cssSelector(stateOpt));
			for (WebElement state: stat){
				if (state.getText().equalsIgnoreCase(enterState)){
					isStateFound =true;
					System.out.println(enterState + " state is found");
					state.click();
					Thread.sleep(2000);
					break;					
				}				
			}if (isStateFound==false){
				Assert.assertTrue(isStateFound , enterState+ " state is not avaiable In dropdown" );
			}			
			
			}	
	
	
      public void validateFields(String Fname, String Lname) throws Exception {
		
		logInfo("Register New Customer with all fields.");
		try{			
		clickOnLink("linkText", "Login");
		clickOnButton("cssSelector", registerNew);	
		inputTextClear("cssSelector", custFN);
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);		
		WebElement title = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+title.getText());		
		Assert.assertEquals(title.getText(), custAlertText); 
		
		Thread.sleep(2000);
		inputText("cssSelector", custFN, Fname);
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);
		WebElement ln = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+ln.getText());		
		Assert.assertEquals(ln.getText(), custAlertText);
		
		Thread.sleep(2000);
		inputText("cssSelector", custLN, Lname);
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);
		WebElement eml = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+eml.getText());		
		Assert.assertEquals(eml.getText(), emailAlert);
		
		inputText("cssSelector", custEmail, (Fname+"."+Lname+"@icentris.com"));
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);
		WebElement ad1 = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+ad1.getText());		
		Assert.assertEquals(ad1.getText(), custAlertText);
		
		inputText("cssSelector", custAd1, "Roxbury Drive");
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);
		WebElement zip = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+zip.getText());		
		Assert.assertEquals(zip.getText(), custAlertText);
		inputText("cssSelector", custZip, "90210");
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);
		WebElement cit = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+cit.getText());		
		Assert.assertEquals(cit.getText(), custAlertText);
		Thread.sleep(1000);
		inputText("cssSelector", custCity, "CA");
		inputTextClear("cssSelector", custUserName);
		inputText("cssSelector", custUserName, Fname);
		inputText("cssSelector", custPwd, custPwdText);
		inputText("cssSelector", custConPwd, custPwdText);		
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);		
		WebElement country = driver().findElement(By.cssSelector(alertMessage));
		System.out.println("aert message is "+country.getText());	
		Assert.assertEquals(country.getText(), countryAlert);
		stateSelection("Arkansas");		
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);	
		confirmationMessage("Welcome! You have signed up successfully.");
							
		}catch (Exception e){
			
			logInfo("Failed!! Some mandatory fields are missing.");
		}			
		
	}
      
      
      // logout from Admin and login with existing shopping customer
      public void existingUserCredentials(String shopUser) throws Exception {
    	logInfo("Entered existingUserCredentials() method");    	    
    	
  		logOut();	
  		selectCustMarket("United States");	
  		waitOnElement("linkText", "Login");  		
  		clickOnLink("linkText", "Login");  		
  		selectCustMarket("United States");		
		/*clickOnButton("cssSelector", markOkBtn); 	*/	
		waitOnElement("cssSelector", returnExist);	
		clickOnButton("cssSelector", returnExist);	
		waitOnElement("cssSelector", "input#user_login");		
		clickOnButton("cssSelector", returnExist);
		inputText("cssSelector", custShopPwdInMaster, custPwdText);
		WebElement name = driver().findElement(By.cssSelector("input#user_login"));		
		name.clear();
		name.sendKeys(shopUser);
		name.sendKeys(shopUser);
		/*inputTextClear("xpath", inputName);			
		inputText("xpath", inputName, shopUser);*/
	/*	waitOnElement("cssSelector", custShopPwdInMaster);	
		
		waitOnElement("cssSelector", custShopPwdInMaster);
		waitOnElement("cssSelector", custShopPwdInMaster);
		submitElement("cssSelector", custShopPwdInMaster);*/
				
			/*if(driver().findElement(By.cssSelector(error404)).getText().equals(notImplimentedText)){
				System.out.println("Enterd eroor message");
				WebElement panelTit = driver().findElement(By.cssSelector(error404));
				System.out.println(panelTit.getText());
				Assert.assertEquals(panelTit.getText(), "Save");	
			}else {
				WebElement panelTit = driver().findElement(By.cssSelector(panelTitle));
				System.out.println(panelTit.getText());
				Assert.assertEquals(panelTit.getText(), "Shop");			
			}*/		
		}
      
      
      
      public void nav2AutoshipCustomer() throws Exception{			
    	  logInfo("Entered into nav2AutoshipCustomer() method"); 	    
  		driver().navigate().to(appUrl +"/shop/autoships");
  		driver().navigate().refresh();  		
  		}
      
      
      //Select Products Option from Category Drop dow
      public void selectProductsfromCategory(String categoryItems) throws Exception{			
  		logInfo("Entered into selectProductsfromCategory(String categoryItems) method");    		
  		waitOnElement("cssSelector", selectCategory);
  		waitOnElement("cssSelector", selectCategory);
  		Thread.sleep(3000);
  		Select select = new Select(driver().findElement(By.cssSelector(selectCategory)));
		select.selectByVisibleText(categoryItems);				
  	}
      
      //Select Products from search field and the select the product
      public void searchItembyNameOrSku(String nameorSku ) throws Exception{
    	  logInfo("Entered into searchItembyNameOrSku() method"); 	    
	    	selectProductsfromCategory("All");
	    	waitOnElement("cssSelector", searchByName);
	    	inputTextClear("cssSelector", searchByName);
	    	inputText("cssSelector", searchByName, nameorSku);
	    	submitElement("cssSelector", searchByName); 
	    	submitElement("cssSelector", searchByName); 
	    	clickOnElement("cssSelector", searchProduct); 
	    	waitOnElement("cssSelector", searchByName);
	    	selectProdFromList(nameorSku);
	    		
  	}
      
    //Select Products from search field and the select the product
      public void searchFlexshipproduct(String nameorSku ) throws Exception{
    	  logInfo("Entered into searchItembyNameOrSku() method"); 	    
	    	selectProductsfromCategory("All");
	    	waitOnElement("cssSelector", searchAutoProd);
	    	inputTextClear("cssSelector", searchAutoProd);
	    	inputText("cssSelector", searchAutoProd, nameorSku);
	    	submitElement("cssSelector", searchAutoProd);
	    	clickOnElement("cssSelector", searchProduct);
	    	selectProdFromList(nameorSku);
	    		
  	}
      
      public void selectProdFromList(String nameorSku ) throws Exception {
    	  logInfo("Entered into selectProdFromList() method"); 	 
   	        boolean isProdPresent = false;   	       
	    	waitOnElement("cssSelector", productList);
	    	waitOnElement("cssSelector", productList);
	  		List <WebElement> lis = driver().findElements(By.cssSelector(productList));	  		
	  		for (WebElement lt : lis){		  			
	  			if (lt.getText().trim().contains(nameorSku)){	
	  				isProdPresent=true;	  				  				
	  				lt.click();	 
	  				waitOnSpinner();
	  				waitOnElement("cssSelector", flxAddbtn);
	  				break;
	  			}			
	  		}if(isProdPresent==false){
	  			Assert.assertTrue(isProdPresent, nameorSku+ " is not found" );
	  		}  	
   	   
      }
      
      
      public void travelSetProducts() throws Exception {
    	  logInfo("Entered into travelSetProducts() method");   
    	  String expTitle = "Travel Set Products";    	  	  
    	  waitOnElement("cssSelector", travelPopWindList);
    	  List <WebElement> all = driver().findElements(By.cssSelector(travelPopWindList)); 
    	  System.out.println("count is "+ all.size());
    	  for(WebElement pros: all) {
    		  String allTitles = pros.getText().trim(); 
    		  System.out.println("titles"+allTitles);
    		  if(allTitles.equalsIgnoreCase(expTitle)){     			 
    			  waitOnElement("cssSelector", addTocart);    			 
    			  clickOnButton("cssSelector", addTocart);
    	      	  break;    			  
    		  				}System.out.println("no travel set");
    		  			} 
    	  
    	  nav2CustShopping();
    		  
    	  }
      
      
      public void promotionalProd() throws Exception {
    	  logInfo("Entered into promotionalProd() method");   
    	  String expTitle = "Promotional Products";
    	  nav2CustShopping();    	  
    	  waitOnElement("cssSelector", proTitle);
    	  List <WebElement> all = driver().findElements(By.cssSelector(proTitle));    	 
    	  for(WebElement pros: all) {
    		  String allTitles = pros.getText().trim();    		  
    		  if(allTitles.equalsIgnoreCase(expTitle)){     			 
    	          waitOnElement("cssSelector", firstPromoProduct);       	 
  	      	      clickOnElement("cssSelector", firstPromoProduct);
  	      	      scrollDown("cssSelector", skipPromo);  	      	      
  	      	      clickOnButton("cssSelector", skipPromo);  	      	      
    	      	  nav2CustShopping();    	      	
    	      	  clickOnElement("cssSelector", miniCart);
    	      	  waitOnElement("cssSelector", miniCart);    	   
    	      	  clickOnElement("cssSelector", viewCart); 	  
    	      	  break;    			  
    		  				}
    		  			}       		 
    		  waitOnElement("cssSelector", miniCart);
    		  clickOnElement("cssSelector", miniCart);	      	 
	      	  waitOnElement("cssSelector", viewCart);
	      	  clickOnElement("cssSelector", viewCart);   
	      	 // checkout2DaysOrder();
    		  
    	  }
      
      public void selectProduct( ) throws Exception{    
    	  logInfo("Entered into selectProduct() method");      	
	      	clickOnElement("cssSelector", searchProduct);
	      	waitOnElement("cssSelector", productList);
	      	scrollDown("cssSelector", productList);	      	
	      	waitOnElement("cssSelector", productList);
    		List <WebElement> lis = driver().findElements(By.cssSelector(productList));    		
    		for (WebElement lt : lis){    			
    				lt.click();  				
    				break;    				
    		}		
    	}
      
      public void addProduct(int moreQuantiy) throws Exception, Exception {
    	  logInfo("Entered into addProduct() method");   
    	  for(int i=1; i <=moreQuantiy-1; i++){  
				waitOnElement("cssSelector", moreIcon); 
				clickOnElement("cssSelector", moreIcon);
					}
    	  Thread.sleep(5000);
				waitOnElement("cssSelector", addTocart2); 
				waitOnElement("cssSelector", addTocart2);				
				clickOnButton("cssSelector", addTocart2);	
    	  
      }
      
     
      
      public void addProductWithQuanity(int moreQuantiy) throws Exception {
    	  logInfo("Entered into addProductWithQuanity() method");      	  
    	      addProduct( moreQuantiy);   
    	      waitOnElement("cssSelector", viewCart);      	    
    	      clickOnElement("cssSelector", viewCart);
    	      waitOnElement("cssSelector", miniCart);      	
    	      WebElement cart = driver().findElement(By.cssSelector(miniCart));
			  String cartStatus = cart.getAttribute("aria-expanded");	
			  if ((cartStatus==null)) {
				  clickOnElement("cssSelector", miniCart);	
				  waitOnElement("cssSelector", viewCart);
	              clickOnElement("cssSelector", viewCart);
				  }	
			    clickOnElement("cssSelector", todaysOrdertitle);
	    	    clickOnElement("cssSelector", todaysOrdertitle);	  
  			
      }
       
      
      
      public void addQuanityNdCheckOutForCustomer(int moreQuantiy) throws Exception{		
    	  logInfo("Entered into addQuanityNdCheckOutForCustomer() method");    			  	
    	          addProductWithQuanity(moreQuantiy);
	  			  waitOnElement("cssSelector", checkOut);
	  			  clickOnElement("cssSelector", checkOut);   			 
    		}     
      
      
      public void addQuanityNdCheckOut(int moreQuantiy) throws Exception{		
    	  logInfo("Entered into addQuanityNdCheckOut() method");    		    			
    			  addProductWithQuanity(moreQuantiy);    			  
    	    	  waitOnElement("cssSelector", todaysOrdertitle);
    	    	  String tit = driver().findElement(By.cssSelector(todaysOrdertitle)).getText().trim();    	    	
    	    	  Assert.assertEquals(tit, placeOrderTitleTxt);
    	    	  clickOnElement("cssSelector", todaysOrdertitle);
    	    	  clickOnElement("cssSelector", todaysOrdertitle);
	  			  waitOnElement("cssSelector", checkOut);	  		
	  			  clickOnElement("cssSelector", checkOut); 	  			 
    		}
      
      public void checkout2DaysOrder() throws Exception {
    	  logInfo("Entered into checkout2DaysOrder() method");    	  
    	  waitOnElement("cssSelector", autoCheckTitle);
    	  String tit = driver().findElement(By.cssSelector(todaysOrdertitle)).getText().trim();
    	  System.out.println("title" + tit);
    	  Assert.assertEquals(tit, placeOrderTitleTxt);
    	  clickOnElement("cssSelector", todaysOrdertitle);
    	  clickOnElement("cssSelector", todaysOrdertitle);
			  waitOnElement("cssSelector", checkOut);			  
			  clickOnElement("cssSelector", checkOut);    	  
    	  
      }
        
       //12/14/2017      
      public void addAutoshipQuanityNdCheckOut(int moreQuantiy) throws Exception{		
    	  logInfo("Enter into addAutoshipQuanityNdCheckOut(int moreQuantiy) method");	
    	  		  addAutoshipQuanity(moreQuantiy);      	          
				  scrollDown("cssSelector", miniCart);
				  WebElement cart = driver().findElement(By.cssSelector(miniCart));
				  String cartStatus = cart.getAttribute("aria-expanded");				  
				  if ((cartStatus==null)||(cartStatus=="true")) {
					  clickOnElement("cssSelector", miniCart);
					  waitOnElement("cssSelector", miniCart);	
					  clickOnElement("cssSelector", viewCart);
				  }	 
	        	  waitOnSpinner();	 
	        	  clickOnElement("cssSelector", viewCart);
	  			  waitOnElement("cssSelector", checkOut);	  			 
	  			  clickOnButton("cssSelector", checkOut); 
    		}   
       
      
      public void addAutoshipNdCheckoutInVibe(int moreQuantiy) throws Exception{		
    		logInfo("Enter into addAutoshipNdCheckoutInVibe(int moreQuantiy) method");	  		  			
    			for(int i=1; i <=moreQuantiy-1; i++){  	
    				waitOnElement("cssSelector", moreIcon);
    				clickOnButton("cssSelector", moreIcon);				
    				      }    			
    					WebElement chkbox = driver().findElement(By.cssSelector(oneTimeAddInVibe));
  		 			   	if (chkbox.isSelected()){
  		 				   chkbox.click();	
  		 			   		} 
  		 			   	  waitOnElement("cssSelector", autoshipRadioInVibe);	
  		 			   	  clickOnElement("cssSelector",autoshipRadioInVibe); 	
  		 			   	  waitOnElement("cssSelector", addTocart2);
  	    				  clickOnButton("cssSelector", addTocart2);  	    		
  	    				  waitOnElement("cssSelector", viewCart);	    				 
  	    				  clickOnElement("cssSelector", viewCart);
  	    				  waitOnSpinner();	 
  	    				  waitOnElement("cssSelector", checkOut);	  			 
  		  			      clickOnButton("cssSelector", checkOut); 
  	    				     				 		
        				}
      
      
      public void addAutoshipQuanity(int moreQuantiy) throws Exception{		
  		logInfo("Enter into addAutoshipQuanity(int moreQuantiy) method");	  		  			
  			for(int i=1; i <=moreQuantiy-1; i++){  	
  				waitOnElement("cssSelector", moreIcon);
  				scrollDown("cssSelector", moreIcon);
  				clickOnButton("cssSelector", moreIcon);	
  				      }    			
  					WebElement chkbox = driver().findElement(By.cssSelector(oneTimeAdd));
		 			   	if (chkbox.isSelected()){
		 				   chkbox.click();	
		 			   		} 
		 			   	  waitOnElement("cssSelector", autoshipRadio);	
		 			   	  clickOnElement("cssSelector",autoshipRadio); 	
		 			   	  waitOnElement("cssSelector", addTocart2);
	    				  clickOnButton("cssSelector", addTocart2);
	    				  Thread.sleep(2000);
	    				  waitOnElement("cssSelector", viewCart);	    				 
	    				  clickOnElement("cssSelector", viewCart);
	    				  Thread.sleep(2000);
	    				  clickOnElement("cssSelector", viewCart);
	    				  Thread.sleep(2000);
	    				  clickOnElement("cssSelector", viewCart);
	    				     				 		
      				}
      
      public void addFlexshipQuanity(int moreQuantiy) throws Exception{		
    		logInfo("Enter into addAutoshipQuanity(int moreQuantiy) method");	  		  			
    			for(int i=1; i <=moreQuantiy-1; i++){  	
    				waitOnElement("cssSelector", moreIconFlex);
    				scrollDown("cssSelector", moreIconFlex);
    				Thread.sleep(5000);
    				waitOnElement("cssSelector", moreIconFlex);
    				clickOnButton("cssSelector", moreIconFlex);	
    				      } 		 			  
 			   	  waitOnElement("cssSelector", flxAddbtn); 			   	  
				  clickOnButton("cssSelector", flxAddbtn);  
				 // waitOnElement("cssSelector", autoDetTitle);
        	}
      
     
      
      public void flexshipCheckOut() throws Exception {
    	  logInfo("Enter into flexshipCheckOut() method");	    	 
    	  waitOnElement("cssSelector", autoCheckTitle);
    	  Thread.sleep(6000);
    	  String tit = driver().findElement(By.cssSelector(autoCheckTitle)).getText().trim();    	
    	  Assert.assertEquals(tit, "Create Flexship");
    	  calenderDatePicker();        	 
    	  clickOnElement("cssSelector", autoCheckTitle);
    	  waitOnElement("cssSelector", autoCheckout);
    	  clickOnElement("cssSelector", autoCheckout);
    	  waitOnSpinner();
      }
      
      //change date and Eneter survey details and then checkout
      public void flexshipCheckOutAfterSurvey() throws Exception {
    	  logInfo("Enter into flexshipCheckOutAfterSurvey() method");	    	 
    	  waitOnElement("cssSelector", autoCheckTitle);
    	  Thread.sleep(6000);
    	  String tit = driver().findElement(By.cssSelector(autoCheckTitle)).getText().trim();    	
    	  Assert.assertEquals(tit, "Edit Flexship");
    	  updateCalenderDate();
    	  flexshipSurvey();
    	  clickOnElement("cssSelector", autoCheckTitle);
    	  waitOnElement("cssSelector", autoCheckout);
    	  clickOnElement("cssSelector", autoCheckout);
    	  waitOnSpinner();
      }
       
      
      
    
      public void addQuanityNdCheckoutOfNonUser(int moreQuantiy) throws Exception{		
    		logInfo("Entering number of Quanity of products");		
    		try{
    			Thread.sleep(2000);
    			for(int i=1; i <=moreQuantiy-1; i++){  				
    				clickOnButton("cssSelector", moreIcon);  				
    				Thread.sleep(3000);
    			}
    			    selectRadioOrCheckbox("cssSelector", prop.getLocatorForEnvironment(appUrl,"oneTime"));
    				clickOnButton("cssSelector", addTocart);
    				clickOnLink("linkText", "View Cart");
    				Thread.sleep(4000);    				
    				clickOnButton("cssSelector", checkOut);	
    				
    			}catch(Exception e){
    			logInfo("Failed!! Not able to enter quanity of products");
    		}	
    		
    		}
      
      //add quanity in autoship and then checkOut
      public void addQuanityInAutoShipNdCheckOut(int moreQuantiy) throws Exception{		
    		logInfo("Entered addQuanityInAutoShipNdCheckOut() method");		
    		try{
    			Thread.sleep(2000);
    			for(int i=1; i <=moreQuantiy-1; i++){  				
    				clickOnButton("cssSelector", moreIcon);  				
    				Thread.sleep(3000);  
    									}   			
    			clickOnElement("cssSelector", btnAddToAutoship);    				
    			Thread.sleep(2000); 		    				
    			}catch(Exception e){
    			logInfo("Failed!! Not able to enter quanity of products in Autoship");
    		}	
    		
    		}
      
      public void addQuanityInMultiAutoShipNdCheckOut(int moreQuantiy) throws Exception{		
  		logInfo("Entered addQuanityInMultiAutoShipNdCheckOut() method");		
  		   Thread.sleep(3000);
  		   String expTitleProd = "Product Details";
  		   
  		   WebElement prod = driver().findElement(By.cssSelector(prodDetails));
  		   String actTitle = prod.getText().trim();
  		   System.out.println(actTitle);
  		   if(actTitle.equalsIgnoreCase(expTitleProd)){
  		   waitOnElement("cssSelector", moreIconAuto);  	
  			for(int i=1; i <=moreQuantiy-1; i++){  				
  				clickOnButton("cssSelector", moreIconAuto);  	
  				   }  			
  				waitOnElement("cssSelector", autoshipAdd); 							   			
	  			clickOnElement("cssSelector", autoshipAdd); 
  		   }else{
  			   Assert.assertEquals(actTitle, expTitleProd);
  		   }
	  			waitOnElement("cssSelector", autoCheckout);
	  		    WebElement editPage = driver().findElement(By.cssSelector(tubeListTitle));
  			    String title = editPage.getText().trim();
  			    String expTitle = "Edit Autoship Page";  			    
  			    if (title.equalsIgnoreCase(expTitle)){
  			    	editPage.click();
  			    	driver().navigate().refresh();
  			    	System.out.println("@@@@@@@@@@title "+expTitle);
  			    	waitOnElement("cssSelector", autoCheckout);
  			    	inputText("cssSelector", autoCheckout, "text us ");
  		  			clickOnButton("cssSelector", autoCheckout);
  			    	}else{
  			    		Assert.assertEquals(title, expTitle);
  			    	}
	  				  			
  		}
      
  	public boolean verifyProductInWishList(String product) throws Exception{    	
  		logInfo("Entered verifyProductInWishList() method"); 
  	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
  	  System.out.println(lis.size());
  	  boolean isProdPresent = false;
  	  for (int i=1; i<=lis.size(); i++){
  		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+wlItemAft));
  		  System.out.println("products are"+ prod.getText());
  		  if (prod.getText().contains(product)){
  			  isProdPresent=true;    			  
  			  break;
  		  }    		  
  	  }if (isProdPresent==false){
  		  Assert.assertTrue(isProdPresent, product + " is not present." );
  	  }
		return isProdPresent; 	  
  	  
    }
    	
    	public void verifyProductNotToPresentInWishList(String product) throws Exception{    	
    	  
    	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
    	  System.out.println(lis.size());
    	  boolean isProdPresent = true;
    	  for (int i=1; i<=lis.size(); i++){
    		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+wlItemAft));
    		  System.out.println("products are"+ prod.getText());
    		  if (prod.getText().contains(product)){
    			  isProdPresent=false;  
    			  Assert.assertTrue(isProdPresent, isProdPresent + " is still present." );
    			  break;
    		  }    		  
    	  }if (isProdPresent==true){
    		Assert.assertNotSame(isProdPresent, product);
    	  }     	  
      }
    	
    	
    	public boolean verifyProductInCart(String product) throws Exception{    	
    	  
    	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
    	  System.out.println(lis.size());
    	  boolean isProdPresent = false;
    	  for (int i=1; i<=lis.size()*2; i++){
    		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+cartItemAft));
    		  System.out.println("products are in Item"+ prod.getText());
    		  if (prod.getText().contains(product)){
    			  isProdPresent=true;    			  
    			  break;
    		  }i++;    		  
    	  }if (isProdPresent==false){
    		  Assert.assertTrue(isProdPresent, product + " is not present in CartList" );
    	  }
		return isProdPresent; 	  
    	  
      }
    
    	
    	 public void removeProductFromCart(String product) throws Exception{    	  
     	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
     	  System.out.println(lis.size());
     	  boolean isProdPresent = false;
     	  for (int i=1; i<=lis.size()*2; i++){
     		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+cartItemAft));
     		  System.out.println("products are"+ prod.getText());
     		  if (prod.getText().contains(product)){
     			  isProdPresent=true;
     			  WebElement del = driver().findElement(By.cssSelector(wishListBfr+i+cartDeleteAft));
     			  del.click();
     			  break;
     		  }  i++;  		  
     	  }if (isProdPresent==false){
     		  Assert.assertTrue(isProdPresent, product + " is not present." );
     	  }
       }
    	 
    	public void verifyProductNotToPresentInCartList(String product) throws Exception{  	
      	  
      	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
      	  System.out.println(lis.size());
      	  boolean isProdPresent = true;
      	  for (int i=1; i<=lis.size()*2; i++){
      		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+cartItemAft));
      		  System.out.println("products are"+ prod.getText());
      		  if (prod.getText().contains(product)){
      			  isProdPresent=false;  
      			  Assert.assertTrue(isProdPresent, isProdPresent + " is still present." );
      			  break;
      		  } i++;   		  
      	  }if (isProdPresent==true){
      		Assert.assertNotSame(isProdPresent, product);
      	  }     	  
        } 	
    	public void validIncndDescQty(String product, int increaseQty, int decreaseQty) throws Exception{    	  
       	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
       	  System.out.println(lis.size());
       	  boolean isProdPresent = false;
       	  for (int i=1; i<=lis.size()*2; i++){
       		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+cartItemAft));
       		  System.out.println("products are"+ prod.getText());
       		  if (prod.getText().contains(product)){
       			  isProdPresent=true;
       			  Thread.sleep(3000);
       			  WebElement retrieveQty = driver().findElement(By.cssSelector(wishListBfr+i+cartQtyAft));
       			  String  qty= retrieveQty.getAttribute("value").trim();
       			  System.out.println("qty" + qty);
       			  int current = Integer.parseInt(qty);  
       			  
       			  int expIncrQty =  current+ increaseQty;
       			  int expDecsQty = expIncrQty-decreaseQty;
       			  String expctedIncreasedQty = Integer.toString(expIncrQty);
       			  String expctedDescreasedQty = Integer.toString(expDecsQty);
       			 System.out.println("current Qty "+current);
       			System.out.println("expctedIncreasedQty "+expctedIncreasedQty);
       			System.out.println("expctedDescreasedQty Qty "+expctedDescreasedQty);
       			  
       			  for (int j=1; j<=increaseQty; j++){
       				 WebElement incre = driver().findElement(By.cssSelector(wishListBfr+i+cartIncQtyAft));
       				 incre.click();        				  
       			  }
       			 WebElement incredQty = driver().findElement(By.cssSelector(wishListBfr+i+cartQtyAft));
       			 String actualIncreasedQty = incredQty.getAttribute("value").trim();  
       			System.out.println("actualIncreasedQty "+actualIncreasedQty);
       			 Assert.assertEquals(actualIncreasedQty, expctedIncreasedQty);         			  
       			  
       			  
       			 for (int k=1; k<=decreaseQty; k++){
       				 WebElement incre = driver().findElement(By.cssSelector(wishListBfr+i+cartDescQtyAft));
       				 incre.click();        				  
       			  }
       			 WebElement descQty = driver().findElement(By.cssSelector(wishListBfr+i+cartQtyAft));
      			 String actualDescreasedQty = descQty.getAttribute("value").trim();
      			 System.out.println("actualDescreasedQty "+actualDescreasedQty);
      			 int actualDesc = Integer.parseInt(actualDescreasedQty);
      			  if(actualDesc==0){
      				  Assert.assertEquals(actualDescreasedQty, "0");        				  
      			  }else{
      				  Assert.assertEquals(actualDescreasedQty, expctedDescreasedQty);
      			  } 			  
       			
       			  break;
       		  }  i++;  		  
       	  }if (isProdPresent==false){
       		  Assert.assertTrue(isProdPresent, product + " is not present." );
       	  }
         }
    
    	public void writeAReview (String review, int rateStar_1_to_5) throws Exception{
    		logInfo("Entered into writeAReview() method.");     
    		String msg = "Thanks for submitting review, Please note that all reviews are moderated for quality and relevance.";
    		clickOnLink("cssSelector", writeReview);
    		Thread.sleep(3000);      		
    		starRating(rateStar_1_to_5);
    		inputTextClear("cssSelector", revTitle);
    		inputText("cssSelector", revTitle, review);
    		inputText("cssSelector", revComment, review);
    		Thread.sleep(3000);
    		clickOnButton("cssSelector", revSubmit);
    		confirmationMessage(msg);
    		Thread.sleep(3000);
    		System.out.println("completed");
    		
    	}
    		
    		public void starRating (int rateStar_1_to_5) throws Exception{
        		logInfo("Entered into starRating() method.");  		
        		String rated = Integer.toString(rateStar_1_to_5);
        		/*if (rateStar_1_to_5<=0||rateStar_1_to_5<5) {      			
        			Assert.assertEquals(rateStar_1_to_5, "0");
        		}else*/{
        			switch (rated){
        			case "1":
        				clickOnElement("cssSelector", singleStar);
        				break;
        			case "2":
        				clickOnElement("cssSelector", twoStar);
        				break;
        			case "3":
        				clickOnElement("cssSelector", threeStar);
        				break;
        			case "4":
        				clickOnElement("cssSelector", fourStar);
        				break;
        			case "5":
        				clickOnElement("cssSelector", fiveStar);
        				break;
        			default:
        				System.err.println("entered unnecessary Star rated");
        				Assert.assertNull(rated);
        			 }
        			
        		}
    		
    	}
    		
    public void acceptReviewFromAdmin(String product, String userName, String reviewTitle) throws Exception{
  	  selectSideMenuInAdmin("Reviews");
  	  searchReviewsInAdmin(product, userName, reviewTitle);
  	  
  	  
  	  
    }
			
	
   //Can be select side menu like .. Products, OptionTypes, Properties etc.. 		
    public void selectSideMenuInAdmin(String menuType) throws Exception{
  	  logInfo("Enetered into selectSideMenuInAdmin() method");
  	  boolean isMenuPresent = false;
  	  Thread.sleep(3000);
  	  List <WebElement> menu = driver().findElements(By.cssSelector(sideMenu));
  	  System.out.println(menu.size());
  	  for (WebElement menus : menu){
  		  if (menus.getText().trim().equalsIgnoreCase(menuType)){
  			  isMenuPresent = true;
  			  menus.click();
  			  Thread.sleep(2000);
  			  break;
  		  }
  	  }if (isMenuPresent==false){
  		  Assert.assertTrue(isMenuPresent, menuType + " is not present in Menu list");
  	  }   	  
    }
    
    // In Admin - enters text in Review content and selects Reviews based on Product 
    public void searchReviewsInAdmin(String product ,String userName, String reviewTitle) throws Exception{
  	  logInfo("Enetered into searchReviewsInAdmin() method");
  	  Thread.sleep(3000);
  	  inputTextClear("cssSelector", reviewInputText);
  	  inputText("cssSelector", reviewInputText, reviewTitle);
  	  submitElement("cssSelector", reviewInputText);
  	  Thread.sleep(3000);
  	  boolean isProdPresent = false;
  	  List <WebElement> lis = driver().findElements(By.cssSelector(reviewList));
  	  System.out.println("size "+ lis.size());
  	  for (int i = 1; i<=lis.size(); i++){    		  
  		  WebElement element = driver().findElement(By.cssSelector(reviewListBfr+i+reviewListAft));
  		  String productName = element.getText().trim();
  		  if (productName.equalsIgnoreCase(product)){
  			  isProdPresent = true;
  			  WebElement shopUser = driver().findElement(By.cssSelector(reviewListBfr+i+reviewUserAft));
  			  System.out.println("Table user"+shopUser.getText().trim());
  			  Assert.assertEquals(shopUser.getText().trim(), userName);    			  
  			  Thread.sleep(4000);
  			  WebElement approve = driver().findElement(By.cssSelector(reviewListBfr+i+reviewApproveAft));
  			  approve.click();
  			  confirmationMessage("Review approved");    			  
  			  break; 
  		  }    		  
  		  
  	  }if (isProdPresent==false){
  		  Assert.assertTrue(isProdPresent, product + " product is not present in review Table");
  	  }    	  
    }
    
    public String getUserName(){
  	  logInfo("Enetered into getUserName() method");
  	  WebElement user = driver().findElement(By.cssSelector(userName));
  	  String userName = user.getText().trim();    
  	  System.out.println("userName "+userName);
		  return userName;
  	  
    }
    
    public void validateReviews(String reviewTitle){
  	  boolean isPresent = false;
  	  List <WebElement> rev = driver().findElements(By.cssSelector(review));
  	  System.out.println(rev.size());
  	  for (int i= 2; i<= rev.size()+1; i++){    		  
  		  WebElement revs = driver().findElement(By.cssSelector(reviewCom1+i+reviewCom2));
  		  System.out.println("revs.getText()" + revs.getText());
  		  if (revs.getText().equalsIgnoreCase(reviewTitle)){
  			  System.out.println("Suceess");
  			  isPresent = true;    			  
  			  break;   			  
  		  }   		  
  	  }if (isPresent ==false){
  		  Assert.assertTrue(isPresent, reviewTitle + " review is not present.");
  	  }
  	  
    }
    
    
    public void shopPanels() throws Exception{
  	  logInfo("Entered into shopPanels() method.");
  	  
  	  	List<WebElement> shopPane = driver().findElements(By.cssSelector(shopPanel));
		System.out.println("s : "+shopPane.size());
		for(WebElement shopPanels : shopPane){			
			shopPanels.click();  			
			WebElement tit = driver().findElement(By.cssSelector(titlePrice));
			String title = tit.getText().trim();
			Assert.assertEquals(title, titlePriceText);    			
			break;
		}    	  
    }
    
    public void rangeLevelPrice(int level_1_To_4) throws Exception, Exception{
    	logInfo("Entered into rangeLevelPrice() method.");
    	String expectedPanel = "All Products";
    	String priceChkBx1 = "#sidebar_products_search > div > ul > li:nth-child("+level_1_To_4+") > input";
    	boolean isPresent = true;
    	List<WebElement> pane = driver().findElements(By.cssSelector(panelList));
    	for(WebElement all:pane) {
    		String paneName = all.getText().trim();
    		System.out.println("paneName "+paneName);
    		if(paneName.equalsIgnoreCase(expectedPanel)) {
    			all.click(); 
    			break;
    		}   		
    	}if(isPresent==false){
			Assert.assertEquals(isPresent, expectedPanel);
		} 
  	   clickOnElement("cssSelector", priceChkBx1);  	 
	   waitOnElement("cssSelector", taxonSearch);
	   scrollDown("cssSelector", taxonSearch);
	   clickOnButton("cssSelector", taxonSearch);
	   waitOnSpinner();
	   waitOnElement("cssSelector", price); 
  	  
    }
    
    public void deSelectRangeLevel() throws Exception, Exception{
    	logInfo("Entered into deSelectRangeLevel() method.");
    	
    	List<WebElement> chk = driver().findElements(By.cssSelector("#sidebar_products_search > div > ul > li > input"));
    	for (WebElement chks :chk) {
    		if(chks.isSelected()) {
    			chks.click();
    			scrollDown("cssSelector", taxonSearch);
    		}
    		
    	}  	  
     }
     
    
    
    public void rangeValidate(int lowRangePrice, int highRangePrice) throws Exception{
  	  logInfo("Entered into rangeValidate() method.");
  	 
  	  List <WebElement> pr = driver().findElements(By.cssSelector(price));
  	  System.out.println("size is "+ pr.size());
  	  for (int i =2 ; i<=pr.size(); i++){    		  
  		  WebElement prices = driver().findElement(By.cssSelector(priceBfr+i+priceAfr));
  		  String value = prices.getText().trim();
  		  String[] splitValue = StringUtils.split(value,"$");
  		  String [] amount = StringUtils.split(splitValue[0]," ");    		 
  		  String [] amonutDivde = StringUtils.split(amount[0],".");
  		  int actPrice = Integer.parseInt(amonutDivde[0]);  		  
  		  if (actPrice<lowRangePrice  || actPrice > highRangePrice){
  			  Assert.assertSame(actPrice, highRangePrice);
  			  break;
  		  }else {
  			  System.out.println("Success!! Price Range Passed");
  		  }
  		  
  	  }
  	  
  	  
  	  
  	  
    }
      
      public void selectNext() throws Exception{
    	  logInfo("Entered into selectNext() method");    	  
    			Thread.sleep(3000); 
    			waitOnElement("cssSelector", shippingNext);
    			clickOnButton("cssSelector", shippingNext);
    			System.out.println("Select next "); 			  
    		  
    	  
      }
      
      
      
      // select button either Next or Save& Continue in Shipping 
      public void selectNextRSaveNdContinueInShipping(String typeNextOrSave) throws Exception{    	  
    	 logInfo("entered into selectNextRSaveNdContinueInShipping(String typeNextOrSave) method");
    	  
    	 
    	 
    	 if (typeNextOrSave=="next"){      		  
    	  clickOnButton("cssSelector", shipNext);    	  
    	  System.out.println("selected Next");
    	  } else {    		 
    		  clickOnButton("cssSelector", shipSave); 
    		  System.out.println("selected save");
    	  }Thread.sleep(4000);
    	  
      }
      
      
      public void selectNextInAutoshipShipping(String autoShippingTitle) throws Exception{    	  
      	 logInfo("Entered into selectNextInAutoshipShipping() method");        	  
      	  waitOnElement("cssSelector", shipAddTitOfVibe);     
      	  WebElement tit = driver().findElement(By.cssSelector(shipAddTitOfVibe));
      	  String actDelTit=tit.getText().trim();
      	  if (actDelTit.equalsIgnoreCase(autoShippingTitle)){
      		  System.out.println("Now in selectNextInDelivery");
 	     	  waitOnElement("cssSelector", shippingNext);     
 	     	  clickOnButton("cssSelector", shippingNext);
 	     	  waitOnElement("xpath", spinner);
      	  		}else {
      	  			Assert.assertEquals(actDelTit, autoShipTitleTxt);
      	  		}
       
       }  
      
      public void selectNextInDelivery () throws Exception{    	  
     	 logInfo("Entered into selectNextInDelivery() method");     	 
     	  waitOnElement("xpath", spinner);     	  
     	  waitOnElement("cssSelector", delTitle);     
     	  WebElement tit = driver().findElement(By.cssSelector(delTitle));
     	  String actDelTit=tit.getText().trim();
     	  if (actDelTit.equalsIgnoreCase(delTitleTxt)){
     		  System.out.println("Now in selectNextInDelivery");
	     	  waitOnElement("cssSelector", shippingNext);     
	     	  clickOnButton("cssSelector", shippingNext);
	     	  waitOnElement("xpath", spinner);
     	  		}else {
     	  			Assert.assertEquals(actDelTit, delTitleTxt);
     	  		}
      
      }   
  
      
      public void selectNextInCC() throws Exception{    	  
      	 logInfo("Entered into selectNextInDelivery() method");      	 
      	  waitOnElement("xpath", spinner);		 
		  waitOnElement("cssSelector", paymentTitle); 
		  WebElement ccTitle = driver().findElement(By.cssSelector(paymentTitle));
      	  waitOnElement("cssSelector", paymentTitle);      	  
      	  if (ccTitle.getText().trim().equalsIgnoreCase(paymentTitleText)){      		     
		      	  waitOnElement("cssSelector", shippingNext);     
		      	  clickOnButton("cssSelector", shippingNext);
		      	  waitOnElement("xpath", spinner);
		  	}else{
      	  		Assert.assertEquals(ccTitle.getText().trim(), paymentTitleText);
      	  	}
       
       }  
      
      public void selectNextInCCOfAutoship() throws Exception{    	  
       	 logInfo("Entered into selectNextInDelivery() method");      	 
       	  waitOnElement("xpath", spinner);
 		  Thread.sleep(4000);
 		  waitOnElement("cssSelector", paymentTitle2); 
 		  waitOnElement("cssSelector", paymentTitle2); 
       	  WebElement ccTitle = driver().findElement(By.cssSelector(paymentTitle2));
       	  waitOnElement("cssSelector", paymentTitle2);      	  
       	  if (ccTitle.getText().trim().equalsIgnoreCase(paymentTitleText)){
       	  waitOnElement("cssSelector", shippingNext);     
       	  clickOnButton("cssSelector", shippingNext);
       	  waitOnElement("xpath", spinner);
 		  Thread.sleep(4000);
       	  	}else{
       	  		Assert.assertEquals(ccTitle.getText().trim(), paymentTitleText);
       	  	}
        
        }  
      
      public void  directlyEmptyCartProducts() throws Exception{ 
    	  logInfo("Entered into directlyEmptyCartProducts() method"); 
    	        waitOnElement("cssSelector", openViewCart);
    	        clickOnElement("cssSelector", openViewCart);   
    	        waitOnElement("cssSelector", viewCart);
    	        scrollDown("cssSelector", viewCart);    	       
    	        clickOnElement("cssSelector", viewCart);
    	        Thread.sleep(2000);
    	        scrollDown("cssSelector", emptycart);
    	        scrollDown("cssSelector", emptycart);
    	        waitOnElement("cssSelector", emptycart);
	  			clickOnButton("cssSelector", emptycart);
	  			confirmOK(); 	
	  			confirmationMessage("Products deleted successfully");    	        
	  		}
      
      
     
      
      public void  emptyCartProducts() throws Exception{ 
    	  logInfo("Entered into emptyCartProducts() method");
    	        waitOnElement("cssSelector", viewCart);
    	        clickOnElement("cssSelector", viewCart);     	       
    	        waitOnElement("cssSelector", todaysOrdertitle);
  	    	    String tit = driver().findElement(By.cssSelector(todaysOrdertitle)).getText().trim();    	    	
  	    	    Assert.assertEquals(tit, placeOrderTitleTxt);
  	    	    clickOnElement("cssSelector", todaysOrdertitle);
  	    	    clickOnElement("cssSelector", todaysOrdertitle);    	     
    	        scrollDown("cssSelector", emptycart); 
    	        scrollDown("cssSelector", emptycart); 
    	        waitOnElement("cssSelector", emptycart);
	  			clickOnButton("cssSelector", emptycart);
	  			confirmOK(); 	
	  			confirmationMessage("Products deleted successfully");    	        
	  		}
      
      public void emptyAutoshipProducts() throws Exception{ 
    	  logInfo("Entered into emptyAutoshipProducts() method"); 
    	        waitOnElement("cssSelector", emptyAutoCart);
    	        scrollDown("cssSelector", emptyAutoCart);
    	        scrollDown("cssSelector", emptyAutoCart);
    	        waitOnElement("cssSelector", emptyAutoCart);
	  			clickOnButton("cssSelector", emptyAutoCart);
	  			confirmOK(); 	
	  			confirmationMessage("Your flexship order has been reset to its original state");

	  		}
      
      
      public void emailMyWishList() throws Exception{
    	  
    	  clickOnButton("cssSelector", addToWishlistBtn);
    	  clickOnButton("cssSelector", emailWishBtn);    	  
    	  cm.shareByEmail((custFNText+custLNText+"@icentris.com"), shopSubject);
    	  
    	  
      }
      
      public void add2CartFromWishList(String product) throws Exception{
    	  waitOnElement("cssSelector", addToWishlistBtn);
    	  clickOnButton("cssSelector", addToWishlistBtn);
    	  
    	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
    	  System.out.println(lis.size());
    	  boolean isProdPresent = false;
    	  for (int i=1; i<=lis.size(); i++){
    		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+wishListAft));
    		  System.out.println("products are "+ prod.getText());
    		  if (prod.getText().equalsIgnoreCase(product)){
    			  isProdPresent=true;
    			  WebElement add2Cart = driver().findElement(By.cssSelector(wishListBfr+i+add2CartAft));
    			  add2Cart.click();
    			  break;
    		  }    		  
    	  }if (isProdPresent==false){
    		  Assert.assertTrue(isProdPresent, product + " is not present." );
    	  }
    	  
    	  
      }
      
      public void removeProductFromWishList(String product) throws Exception{    	  
    	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
    	  System.out.println(lis.size());
    	  boolean isProdPresent = false;
    	  for (int i=1; i<=lis.size(); i++){
    		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+wishListAft));
    		  System.out.println("products are"+ prod.getText());
    		  if (prod.getText().contains(product)){
    			  isProdPresent=true;
    			  WebElement add2Cart = driver().findElement(By.cssSelector(wishListBfr+i+wlReoveAft));
    			  add2Cart.click();
    			  break;
    		  }    		  
    	  }if (isProdPresent==false){
    		  Assert.assertTrue(isProdPresent, product + " is not present." );
    	  }
    	  
    	  
      }
      
      
      public void addQuanityNdCheckOutFromTodaysOrder(String product, int moreQuantiy) throws Exception{		
    		logInfo("Entering number of Quanity of products in Todays Order");		
    		
    			Thread.sleep(2000);    			
    			List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
    	    	  System.out.println(lis.size());
    	    	  boolean isProdPresent = false;
    	    	  for (int i=1; i<=lis.size()*2; i++){
    	    		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+wishListAft));
    	    		  System.out.println("products are"+ prod.getText());
    	    		  if (prod.getText().equalsIgnoreCase(product)){
    	    			  isProdPresent=true;    	    			  
    	    			  for(int j=1; j <=moreQuantiy-1; j++){  	
    	    				  WebElement quan = driver().findElement(By.cssSelector(wishListBfr+i+quanPlustAft));
    	    				  quan.click(); 				
    	      				Thread.sleep(1000);
    	      				
    	      				}    	    			  
    	    			
	    				/*clickOnButton("cssSelector", addTocart);
	    				clickOnLink("linkText", "View Cart");
	    				Thread.sleep(2000);  */  	
    	    			  waitOnElement("cssSelector", checkOut);		
	    				clickOnButton("cssSelector", checkOut);		
    	    				
    	    				
    	    			  
    	    			  
    	    			  
    	    			  break;
    	    		  } i++;  
    	    	  }if (isProdPresent==false){
    	    		  Assert.assertTrue(isProdPresent, product + " is not present." );
    	    	  }
    			
    			
    			
    			/*for(int i=1; i <=moreQuantiy-1; i++){  				
    				clickOnButton("cssSelector", moreIcon);  				
    				Thread.sleep(3000);
    				} */
    			
    			
    			
    			
    			    
    				
    			
    			
    		}	
    		
    		
      
      
      public void handleCCPayment() throws Exception{
			
			List <WebElement> po = driver().findElements(By.cssSelector(paymentOptions));			
			if (po.size()==0){
				logInfo("No existing card details, Entering New card Details.");
				creditCardDetails();	
				
			}else {				
				logInfo("Select Use new Card option and enter Credit Card Details.");
				clickOnElement("xpath", payOp);
				Thread.sleep(2000);
				creditCardDetails();					
			}	/*selectNextRSaveNdContinueInShipping("next");*/	
			System.out.println("Select next in Payment");
		}
      
      public void creditCardDetails() throws Exception {
    	  logInfo("Entered into creditCardDetails() method");
    	    String CCName = prop.getLocatorForEnvironment(appUrl,"CCName");			
			System.out.println("ccname is "+CCName);
			inputText("cssSelector", CCName, custFNText); 
			inputText("cssSelector", CCNumber, CCCardNo);
			inputText("cssSelector",CCExpire, "08/19");
			inputText("cssSelector",CCCardCode, "489");		
			clickOnButton("cssSelector", shippingNext);
  			System.out.println("Selected next in CC payment");			
		}
     
      //selects place Order confirmation and capture message
      public void placeOrderWithConfimation() throws Exception{
    	logInfo("Entered into placeOrder () method");
    	System.out.println("Place order section entered");
    	  waitOnElement("xpath", spinner);
		  waitOnElement("cssSelector", placeOrderTitle);
     	  WebElement placeTitle = driver().findElement(By.cssSelector(placeOrderTitle));
     	  waitOnElement("cssSelector", placeOrderTitle);      	  
     	  if (placeTitle.getText().trim().equalsIgnoreCase(placeOrderTitleTxt)){
     		 waitOnElement("cssSelector", placeOrder);         	 
         	 clickOnButton("cssSelector", placeOrder);
       		 confirmationMessage("Your order has been processed successfully");
       		 waitOnElement("xpath", spinner);
  		     Thread.sleep(4000);
     	  	}else{
     	  		Assert.assertEquals(placeTitle.getText().trim(), paymentTitleText);
     	  	}
    	
    	
    	
    	 	
  		
      }
      
      //selects place Order confirmation and capture message and then close it      
      public void placeOrderNdClose() throws Exception{
      	logInfo("Entered into placeOrder () method");    	  
      	placeOrderWithConfimation();
      	waitOnElement("xpath", orderClose);
	    clickOnElement("xpath", orderClose);
	    Thread.sleep(2000);	    
	    waitOnElement("linkText", "Go Back To Store");
	    scrollDown("linkText", "Go Back To Store");
	    clickOnLink("linkText", "Go Back To Store");
		Thread.sleep(3000);
		WebElement tit = driver().findElement(By.cssSelector(shopTitle));
		Assert.assertEquals(tit.getText(), "Shop");  	    
        }
      
      
      public void validateOrderIdDetails() throws Exception{  
    	  logInfo("Entered into validateOrderIdDetails() method");   	  
    	  System.out.println("Entered into verifyOrderHistoryDetails() method");
    	  WebElement orderId = driver().findElement(By.cssSelector(orderIdText));
    	  String orderText = orderId.getText();
    	  System.out.println("orderText "+orderText);
    	  waitOnElement("xpath", orderClose);
    	  clickOnElement("xpath", orderClose);
    	     	 
    	 //String orderText = "710987990"; 
    	  
    	  nav2OrderHistory();
    	  List <WebElement> ordList = driver().findElements(By.cssSelector(orderList));
    	  int listSize = ordList.size();
    	  System.out.println("listSize"+ listSize);
    	  boolean ispresent = false;
    	  for(int i=1; i<=listSize; i++){
    		  WebElement ordId = driver().findElement(By.cssSelector(ordIdBfr+i+ordIdAft));
    		  String orderIds=  ordId.getText(); 
    		  System.out.println(orderIds);
    		  String[] splitted = orderIds.split("#"); 
    		  System.out.println("splitted[1] "+splitted[1]);
    		   if (orderText.contains(splitted[1])){
    			   ispresent= true;    			    			       				  		     				  				   
    			   break;   						   
    		   }    		  
    	  if (ispresent==false){    		  
    		  Assert.assertTrue(ispresent,  splitted[1]+ " is not present in Order History" );
    	  }    }  	  
      }
      
      // verify ordered history details with their Id and also product
      public void verifyOrderHistoryDetails(String productName) throws Exception{  
    	  logInfo("Entered into verifyOrderHistoryDetails() method");   	  
    	  System.out.println("Entered into verifyOrderHistoryDetails() method");
    	  String column = "Order";    	  
    	  waitOnElement("cssSelector", orderIdText);
    	  waitOnElement("cssSelector", orderIdText);    	 
    	  WebElement orderId = driver().findElement(By.cssSelector(orderIdText));
    	  String orderText = orderId.getText();
    	  System.out.println("orderText "+orderText);
    	  waitOnElement("xpath", orderClose);
    	  clickOnElement("xpath", orderClose);    	     	 
    	//  String orderText = "Thank you for shopping with us! We received your order: 3493844. If you have any questions please contact us at 1-844-MYMONAT (1-844-696-6628)"; 
    	  
    	  nav2OrderHistory();
    	  List <WebElement> cols = driver().findElements(By.xpath(ordercol));
    	  int columnSize = cols.size();
    	  System.out.println("columnSize"+ columnSize);
    	  boolean isColPresent = false;
    	  for(int i=1; i<=columnSize; i++){
    		  WebElement col = driver().findElement(By.xpath(ordercolBfr+i+"]"));
    		  String actColum = col.getText().trim();
    		  System.out.println("actColum "+ actColum);
    		  if(actColum.contains(column)) {
    			  isColPresent=true;
    			 waitOnElement("cssSelector", orderList);
    			  WebElement ord = driver().findElement(By.cssSelector(orderList));
    			  List ordList = ord.findElements(By.tagName("tr"));
    			  int listSize = ordList.size();    	    	
    	    	  boolean ispresent = false;
    	    	  for(int j=1; j<=listSize; j++){    	 
    	    		  waitOnElement("cssSelector", ordIdBfr+j+ordIdAft+i+")");
    	    		  WebElement ordId = driver().findElement(By.cssSelector(ordIdBfr+j+ordIdAft+i+")"));
    	    		  String orderIds=  ordId.getText();    	    		 
    	    		  String[] splitted = orderIds.split("#"); 
    	    		  System.out.println("splitted[1] "+splitted[1]);
    	    		   if (orderText.contains(splitted[1])){
    	    			   ispresent= true;    			   			
    	    			   WebElement detBtn= driver().findElement(By.cssSelector(ordIdBfr+j+ordDetailsBtn)) ;
    	    			   detBtn.click();    			  
    	    			   verifyItemsInOrderHistory(productName);    				  		     				  				   
    	    			   break;   						   
    	    		   }    		  
    	    	  }if (ispresent==false){    		  
    	    		  Assert.assertTrue(ispresent,  productName+ " is not present in Order History" );
    	    	  }  
    			  break;
    		  }
    	  
    	  }if (isColPresent==false){    		  
    		  Assert.assertTrue(isColPresent,  column + " is not present in Order History" );
    	  }   
    	
      }
      
      //verifies particular item is present in OrderHistory w.r.t OrderId
      public void verifyItemsInOrderHistory(String productName){    
    	  logInfo("Entered verifyItemsInOrderHistory(String productName) method");
    	  List <WebElement> it = driver().findElements(By.cssSelector(orderedItems));
    	  System.out.println(it.size() + " items size");
    	  boolean isItemPresent = false;
    	  for (WebElement its :it){
    		  System.out.println("items "+ its.getText());
    		  if(its.getText().trim().contains(productName)){
    			  isItemPresent = true;
    			  System.out.println("items is present");    			  
    			  break;  			  
    		  }   		  
    	  }if(isItemPresent==false){
    		  Assert.assertTrue(isItemPresent, productName + " - item is not present");
    	  }   	  
      }
      
      
      //Search AutoShip products and navigate to shop and add products.
      
      public void autoshipWithProducts(String productName)throws Exception{
		logInfo("Entered into autoshipWithProducts() method");	 
		clickOnButton("cssSelector", createAutoBtn);
		searchNAddProductDirectlysinAutoship(productName); 	
		
	
  		
  	}
      
      //Search and Add product in autoship.
      public void searchNAddProductDirectlysinAutoship(String product) throws Exception{
    	logInfo("Entered into searchNAddProductDirectlysinAutoship() method"); 
    	String expHead = "Search results for '"+product+ "'";
    	waitOnElement("cssSelector", searchAutoProd);
    	inputTextClear("cssSelector", searchAutoProd);
    	inputText("cssSelector", searchAutoProd, product);
    	submitElement("cssSelector", searchAutoProd);
    	clickOnButton("cssSelector", submitAutoSearch); 	
    	clickOnButton("cssSelector", submitAutoSearch);
    	boolean isProdPresent = false;
    	waitOnElement("cssSelector", productList);
  		List <WebElement> lis = driver().findElements(By.cssSelector(productList));
  		System.out.println(lis.size());
  		for (int i=1; i<=lis.size() ; i++){  			
  			WebElement head = driver().findElement(By.cssSelector(heading));
  			String acthead= head.getText().trim();  						
  			if (acthead.equalsIgnoreCase(expHead)){   				
  				WebElement list = driver().findElement(By.cssSelector(prodListbfr+ i+ prodListAfr));
  	  			String listProd = list.getText().trim();
  	  			System.out.println("listProd"+ listProd);
  	  			if (listProd.equalsIgnoreCase(product)){	
  	  				isProdPresent=true;
  	  			    list.click();  	  			    
  	  			    break;
  	  			}			
  	  		}else {
  				Assert.assertEquals(acthead, expHead);
  	  		}}if(isProdPresent==false){
  	  			Assert.assertTrue(isProdPresent, product+ " is not found" );
  	  		} 
  			
  				
  			}
  			
    	
  		
  	
     
      
     
      
     // select Next buttons in Shipping and also Payment sections in Autoship  
      public void NextInShippingNDeliNPayment() throws Exception{
    	  logInfo("inside NextInShippingNPayment() method"); 
    	    Thread.sleep(3000);	
    	    clickOnButton("cssSelector", nextShipping);
			Thread.sleep(7000);			  			
			clickOnButton("xpath", nextDelivary);
			Thread.sleep(3000);			            
		    clickOnButton("cssSelector", nextPayment);
			Thread.sleep(5000);	    	

  	}
      
      // Handle to Keep As Address Verification in Mannatech
      public void keepAsAddressVerification() throws Exception{
    	logInfo("inside keepAsAddressVerification() method"); 
  		selectRadioOrCheckbox("xpath", keepAs);			
  		clickOnButton("cssSelector", keepSave);
  	}
      
      public void autoIdHandle() throws Exception{
    	  logInfo("inside autoIdHandle() method"); 
    	  WebElement autShipId = driver().findElement(By.cssSelector(autoId));
    	  System.out.println(autShipId.getText());    	  
    	  clickOnLink("cssSelector", autoId);
    	  Thread.sleep(2000);
    	  clickOnLink("linkText", "Edit Autoship");
      }
      
      // Delete latest created Autoship 
      public void deleteAutoShip() throws Exception{ 
    	  logInfo("inside deleteAutoShip() method"); 
    	  nav2AutoshipCustomer();	
    	  waitOnElement("cssSelector", delAutoship);
    	  clickOnElement ("cssSelector", delAutoship);    	  
    	   confirmOK();
    	   confirmationMessage("Autoship is deleted");
    		    		  
    	  
    	  
      }
     
      // fetch the autoship Id and compares/assert  in my autoship page
      public void validateAutoShipId() throws Exception{  
    	  logInfo("inside validateAutoShipId() method");     	 
    	  waitOnElement("cssSelector", autoship_id);
    	  Thread.sleep(3000);    	  
    	  waitOnElement("cssSelector", autoship_id);
    	  WebElement auto = driver().findElement(By.cssSelector(autoship_id));    	  
    	  String expAutoId=  auto.getText(); 
		  String[] expAutoSplitted = expAutoId.split(": ");
		  System.out.println("Expected id is "+ expAutoSplitted[1]);
		  Thread.sleep(3000);
    	  nav2AutoshipCustomer();     	     	  
    	  boolean isIdPresent = false;
    	  List <WebElement> lis = driver().findElements(By.cssSelector(autoList));
    	  System.out.println("Size is "+ lis.size());
    	  for (int i =1; i<=lis.size(); i++){	  
    		  WebElement list= driver().findElement(By.cssSelector(autoListBfr+i+autoListAft));    	  		  
    		  String actAutoId=  list.getText(); 
    		  String[] actAutoSplitted = actAutoId.split("#");
    		   System.out.println("Product is "+ actAutoSplitted[1]); 
    		   if (actAutoSplitted[1].equalsIgnoreCase(expAutoSplitted[1])){
    			   isIdPresent= true;
    			   break;   			   
    		   }i++;	  
    		  
    	  } if (isIdPresent==false){
    		  Assert.assertTrue(isIdPresent, expAutoSplitted[1] + "is not present in MyAutoship List");
    	  }
    	  
      }
      
      // Get AutoShipId and compare it in MyAutoship page and then edit it. 
      public void editAutoShipId() throws Exception{
    	  logInfo("Inside editAutoShipId() method");
    	  waitOnElement("cssSelector", autoship_id);
    	  waitOnElement("cssSelector", autoship_id);   
    	  Thread.sleep(4000);
    	  WebElement auto = driver().findElement(By.cssSelector(autoship_id));    	  
    	  String expAutoId=  auto.getText();     	
		  String[] expAutoSplitted = expAutoId.split(": ");
		  System.out.println("Expected id is "+ expAutoSplitted[1]);
    	  nav2AutoshipCustomer();
    	  boolean isIdPresent = false;
    	  List <WebElement> lis = driver().findElements(By.cssSelector(autoList));
    	  System.out.println("Size is autoships "+ lis.size());
    	  for (int i =1; i<=lis.size(); i++){
    		  WebElement list= driver().findElement(By.cssSelector(autoListBfr+i+autoListAft));    	  		  
    		  String actAutoId=  list.getText(); 
    		  String[] actAutoSplitted = actAutoId.split("#");
    		   System.out.println("autoshipId is "+ i +actAutoSplitted[1]); 
    		   if (actAutoSplitted[1].equalsIgnoreCase(expAutoSplitted[1])){
    			   isIdPresent= true;
    			   WebElement edit = driver().findElement(By.cssSelector(autoListBfr+i+autoEditAft));
    			   edit.click();
    			   WebElement editPage = driver().findElement(By.cssSelector(tubeListTitle));
    			   String title = editPage.getText().trim();
    			   String expTitle = "Edit Autoship Page";
    			   System.out.println("Edit auto title "+expTitle);
    			   if (title.equalsIgnoreCase(expTitle)){
    			   waitOnElement("cssSelector", addProd);
    			   clickOnButton("cssSelector", addProd);
    			   }else{
    				   Assert.assertEquals(title, expTitle);
    			   }
    			   break;   			   
    		   }i++;	  
    		  
    	  } if (isIdPresent==false){
    		  Assert.assertTrue(isIdPresent, expAutoSplitted[1] + "is not present in MyAutoship List");
    	  }    	  
      }
      
   
      
      
   // Get AutoShipId and compare it in MyAutoship page and then delete it and failly verify. 
      public void deleteAutoshipId() throws Exception, AWTException{
    	  logInfo("Inside editAutoShipId() method");
    	  WebElement auto = driver().findElement(By.cssSelector(autoship_id));    	  
    	  String expAutoId=  auto.getText(); 
    	 // String expAutoId = ": 90130084";
		  String[] expAutoSplitted = expAutoId.split(": ");
		  System.out.println("Expected id is "+ expAutoSplitted[1]);
    	  nav2AutoshipCustomer();
    	  boolean isIdPresent = false;
    	  List <WebElement> lis = driver().findElements(By.cssSelector(autoList));
    	  System.out.println("Size is "+ lis.size());
    	  for (int i =1; i<=lis.size(); i++){
    		  WebElement list= driver().findElement(By.cssSelector(autoListBfr+i+autoListAft));    	  		  
    		  String actAutoId=  list.getText(); 
    		  String[] actAutoSplitted = actAutoId.split("#");
    		   System.out.println("Product cost is "+ actAutoSplitted[1]); 
    		   if (actAutoSplitted[1].equalsIgnoreCase(expAutoSplitted[1])){
    			   isIdPresent= true;
    			   WebElement delete = driver().findElement(By.cssSelector(autoListBfr+i+autoDeleAft));
    			   delete.click();
    			   confirmOK();
    	    	   confirmationMessage("Autoship is deleted");
    	    	   
    	    	  nav2AutoshipCustomer();
    	     	  boolean isIdNotToPresent = true;
    	     	 List <WebElement> shipList = driver().findElements(By.cssSelector(autoList));
    	    	 System.out.println("Size is "+ shipList.size());
    	    	 for (int j =1; j<=shipList.size(); j++){
    	    		  WebElement listIds= driver().findElement(By.cssSelector(autoListBfr+j+autoListAft));    	  		  
    	    		  String listIdss=  listIds.getText(); 
    	    		  String[] actListIds = listIdss.split("#");
    	    		   System.out.println("Product cost is "+ actListIds[1]);
    	    		   if (actListIds[1].equalsIgnoreCase(expAutoSplitted[1])){
    	    			   isIdNotToPresent= false;
    	    			   Assert.assertTrue(isIdNotToPresent, expAutoSplitted[1]+ " - autoId is still present, failed!!");    	    			   
    	    			   break;    	    		     	 
    	    	 }}if (isIdNotToPresent==true){
    	    		 System.out.println(expAutoSplitted[1] +" is not present");
    	    	 } 	    	   
    			   break;   			   
    		   }	  
    		  
    	  } if (isIdPresent==false){
    		  Assert.assertTrue(isIdPresent, expAutoSplitted[1] + "is not present in MyAutoship List");
    	  }
    	  
      }
      
      
      public void guestUser(String email) throws Exception{ 
    	  Thread.sleep(2000);
    	  if (driver().getCurrentUrl().contains("master")){ 
    	  selectRadioOrCheckbox("cssSelector", guestCheckOut);
    	  }else{    		  
    	    	clickOnLink("linkText", "Guest Checkout");
    	  }  
    	  Thread.sleep(4000);
    	  inputTextClear("cssSelector", shopEmail);
    	  inputText("cssSelector", shopEmail, email);
    	  Thread.sleep(4000);
    	  clickOnButton("cssSelector", emailCont);
    	  
      }
      
public void custUserLogin(String existingshopUser) throws Exception{
	
    	//selectRadioOrCheckbox("cssSelector", custCheckOut);
	    //clickOnElement ("cssSelector", exisCustLogin);
    	Thread.sleep(2000);
    	clickOnLink("linkText", "Returning Customer");    	
    	Thread.sleep(2000);
    	inputTextClear("xpath", inputName);
  		Thread.sleep(2000);
  		inputText("xpath", inputName, existingshopUser);		
  		Thread.sleep(2020);		
  		inputText("cssSelector", custShopPwdInBranch, custPwdText);    		
  		Thread.sleep(3000);
  	    clickOnButton("cssSelector", userloginBtn);     
    	  
      }   
      
      
      
      public void shipProductToNewAddress(String Fname, String Lname ) throws Exception{    	  
    	  clickOnElement("Xpath", radioUseNewAddr);
    	  shippingAddress(Fname, Lname);      	  
      }
      
      public void addNewCCDetails() throws Exception{
    	  clickOnElement("xpath", radioNewPaymentDetails);
    	  creditCardDetails();
    	  
    	  
    	  
      }
      
      public void creditCardValidation() throws Exception{
    	  logInfo("Entered into creditCardDetails() method");
			Thread.sleep(4000);	
			clickOnButton("cssSelector", shippingNext);
			confirmationMessage("Please enter valid data for Card Number, Expiration, Card Code Fields");
			Thread.sleep(3000);
			ccAlerts();
			Thread.sleep(3000);
			inputText("cssSelector",CCExpire, "08/19");
			Thread.sleep(2000);
			clickOnButton("cssSelector", shippingNext);
			confirmationMessage("Please enter valid data for Card Number, Card Code Fields");
			Thread.sleep(3000);
			ccAlerts();	
		}
      
      
      public void ccAlerts (){
    	  
    	  List <WebElement> al = driver().findElements(By.cssSelector(ccAlerts));
    	  System.out.println(al.size());
    	  for (WebElement alert : al){
    		  String alertMsg = alert.getText().trim();
    		  switch (alertMsg) {    		  
    		  case "Payments credit card Month is not a number":
    			  System.out.println("match found : "+alertMsg );
    			  break;    			  
    		  case "Payments credit card Year is not a number":
    			  System.out.println("match found : "+alertMsg );
    			  break;
    			  
    		  case "Payments credit card Number can't be blank":
    			  System.out.println("match found : "+alertMsg );
    			  break;
    			  
    		  case "Payments credit card Verification Value can't be blank":
    			  System.out.println("match found : "+alertMsg );
    			  break;
    			  
    		  case "Payments credit card Name can't be blank":
    			  System.out.println("match found : "+alertMsg );
    			  break;    			  
    		  default:
    			  System.out.println("match found : "+alertMsg );
    			  break;
    		  }
    		  
    		  
    		  
    	  }   	  
    	  
      }
      
      
      public void creditCardDetailsFirstTime() throws Exception {
    	  logInfo("Entered into creditCardDetails() method");
			
			String CCName = prop.getLocatorForEnvironment(appUrl,"CCName");			
			System.out.println("ccname is "+CCName);
			Thread.sleep(3000);
			waitOnElement("cssSelector", CCName);
			waitOnElement("cssSelector", CCName);
			inputText("cssSelector", CCName, custFNText); 
			inputText("cssSelector", CCNumber, CCCardNo);
			inputText("cssSelector",CCExpire, "08/19");
			inputText("cssSelector",CCCardCode, "489");		
			waitOnElement("cssSelector", shippingNext);
			clickOnButton("cssSelector", shippingNext);
	  		System.out.println("Selected next in CC payment");
			
		}
      
      
      public void handleFromDelivryToSummaryInAutoship() throws Exception{ 
    	  logInfo("Entered into handleFromDelivryToSummaryInAutoship() method");      	  
    	  selectNextInAutoshipDelivery();
    	  selectNextInCCOfAutoship();
  		  waitOnElement("xpath", spinner);
		  Thread.sleep(4000);
		  waitOnElement("cssSelector", autoShipOrderTitle);		  
	   	  WebElement placeTitle = driver().findElement(By.cssSelector(autoShipOrderTitle));
	   	  waitOnElement("cssSelector", autoShipOrderTitle); 
	   	  String actText = placeTitle.getText().trim();
	   	  if (actText.equalsIgnoreCase(autoShipOrderTitleTxt)||
	   			  actText.equalsIgnoreCase(autoShipOrderMntTxt)){
	   		 waitOnElement("cssSelector", placeOrder);	       	 
	       	 clickOnButton("cssSelector", placeOrder);
	       	 waitOnElement("xpath", spinner);
	       	 confirmationMessage("Flexship has been saved successfully");
	         waitOnElement("xpath", spinner);
 		     Thread.sleep(6000);
    	  	}else{
    	  		Assert.assertEquals(actText, autoShipOrderTitleTxt);
    	  	}
	   	  }  
      
      
      public void shippingAddress(String Fname, String Lname ) throws Exception{    	  
      	logInfo("Entered into shippingAddress() method");  
    	    inputTextClear("cssSelector", fName);
    	    inputTextClear("cssSelector", lName);
    		inputText("cssSelector", fName, Fname);
    		inputText("cssSelector", lName, Lname);
    		inputTextClear("cssSelector", street1);
    		inputTextClear("cssSelector", street2);
    		inputText("cssSelector", street1, usaAdd1);
    		inputText("cssSelector", street2, usaAdd2);
    		inputTextClear("cssSelector", city);
    		inputText("cssSelector", city, usaCounty);   
    		inputTextClear("cssSelector", phone);
    		inputText("cssSelector", phone, "1234567891");
    		inputTextClear("cssSelector", zipCode);
    		inputText("cssSelector", zipCode, usaZip);
    		inputTextClear("cssSelector", county);
    		inputText("cssSelector", county, "El Dorado");			
    	    selectFromDropdown("cssSelector", state, "byIndex", "2");
    	    waitOnElement("cssSelector", state);
    	    clickOnButton("cssSelector", shippingNext);  		
    		System.out.println("Selected next in address"); 		    
        }
      
      public void enterShippingAddress(String Fname, String Lname ) throws Exception{    	  
        	logInfo("Entered into shippingAddress() method");
        	
        	/*waitOnElement("cssSelector", addAddrBtn);
        	Thread.sleep(3000);
        	clickOnButton("cssSelector", addAddrBtn);
        	clickOnElement("cssSelector", addAddrBtn);*/
        	waitOnElement("cssSelector", fName);
        	waitOnElement("cssSelector", lName);
      	    inputTextClear("cssSelector", fName);
      	    inputTextClear("cssSelector", lName);
      		inputText("cssSelector", fName, Fname);
      		inputText("cssSelector", lName, Lname);
      		inputTextClear("cssSelector", street1);
      		inputTextClear("cssSelector", street2);
      		inputText("cssSelector", street1, usaAdd1);
      		inputText("cssSelector", street2, usaAdd2);
      		inputTextClear("cssSelector", city);
      		inputText("cssSelector", city, usaCounty);   
      		inputTextClear("cssSelector", phone);
      		inputText("cssSelector", phone, "1234567891");
      		inputTextClear("cssSelector", zipCode);
      		inputText("cssSelector", zipCode, usaZip);      		
      	    waitOnElement("cssSelector", state);
      	    selectFromDropdown("cssSelector", state, "byVisibleText",usaCounty);
      	    inputTextClear("cssSelector", county);
  		    inputText("cssSelector", county, usaCounty);	
  		    waitOnElement("cssSelector", shippingNext);  		
      	    clickOnButton("cssSelector", shippingNext);  		
      		System.out.println("Selected next in address");       		
      		
          }
      
      public void addressInAutoShip(String Fname, String Lname) throws Exception{			
    	  logInfo("Entered into addressInAutoShip() method");	
    	  	waitOnElement("cssSelector", addAddrBtn);
      	  	Thread.sleep(3000);
	      	clickOnButton("cssSelector", addAddrBtn);
	      	clickOnElement("cssSelector", addAddrBtn);
	      	waitOnElement("cssSelector", fNameAs);
	      	waitOnElement("cssSelector", lNameAs);
    	    inputTextClear("cssSelector", fNameAs);
    	    inputTextClear("cssSelector", lNameAs);
			inputText("cssSelector", fNameAs, Fname);
			inputText("cssSelector", lNameAs, Lname);
			inputTextClear("cssSelector", street1AS);
			inputTextClear("cssSelector", street2AS);
			inputTextClear("cssSelector", cityAS); 		
			inputTextClear("cssSelector", phoneAS);
			inputTextClear("cssSelector", zipCodeAS);			
			inputText("cssSelector", street1AS, usaAdd1);
			inputText("cssSelector", street2AS, usaAdd2);
			inputText("cssSelector", cityAS, usaCounty); 		
			inputText("cssSelector", phoneAS, "1234567891");
			inputText("cssSelector", zipCodeAS, usaZip);
			selectFromDropdown("cssSelector", stateAS, "byVisibleText",usaCounty);	
			waitOnElement("cssSelector", shippingNext);  		
      	    clickOnButton("cssSelector", shippingNext);  		
      		System.out.println("Selected next in address");  
		
		}
      
      public void addnewAddressForExistingUser(String Fname, String Lname) throws Exception {
    	  logInfo("Entered into addnewAddressForExistingUser() method");
    	   waitOnSpinner();
    	   waitOnElement("cssSelector", mngAddress);    	  
    	   clickOnElement("cssSelector", mngAddress);
    	   waitOnElement("cssSelector", shipAddCnt);
    	   List<WebElement> cnt= driver().findElements(By.cssSelector(shipAddCnt));
    	   int count = cnt.size();
    	   System.out.println("count "+count);
    	   for(int i=count; i>=1; i--) {
    		   System.out.println("i "+ i);
    		   waitOnElement("cssSelector", shipAddBfr+i+shipAddAft);
    		   WebElement del= driver().findElement(By.cssSelector(shipAddBfr+i+shipAddAft));
    		   System.out.println("i "+ i + del.getText());
    		   del.click();
    		   confirmOK();
    		   confirmationMessage("Deleted successfully.");    		   
    		   Thread.sleep(3000);    		   
    		   waitOnElement("cssSelector", mngAddress);
        	   clickOnElement("cssSelector", mngAddress);
    	   }
    	   clickOnElement("cssSelector", closeAddrShip);
    	   enterShippingAddress(Fname, Lname);   	  
      }
      
      
      
      
      public void handle904NotAuthorized(String expTitle) throws Exception {
    	  logInfo("Enetered into handle904NotAuthorized() method"); 
    	  waitOnElement("cssSelector", shipSubTitle);    	 
    	  WebElement tit = driver().findElement(By.cssSelector(shipSubTitle));
    	  String actTitle = tit.getText().trim();    
    	  System.out.println(actTitle);
    	  if (actTitle.equalsIgnoreCase(expTitle)){ 
	    		  clickOnElement("cssSelector", closeErrToast);
		    	  waitOnElement("cssSelector", Alrt904);
		    	  clickOnElement("cssSelector", Alrt904);    		  
    	    	  waitOnElement("cssSelector", shippingNext);
    	    	  scrollDown("cssSelector", shippingNext);
    	      	  clickOnElement("cssSelector", shippingNext); 	
    	  		}else {
    	    	 System.out.println("Successfully!! entered into delivery");
    	    }   	  
    	  
      }
      
      public void shippingAddressProcess(String Fname, String Lname ) throws Exception {
    	 logInfo("Enetered into shippingAddressProcess() method"); 
    	 String expText = "select";    	 	
    	 waitOnElement("cssSelector", shippingNext);  		
      	 List<WebElement> select = driver().findElements(By.cssSelector(selectAddrOfTodays));
      	 int addSize=select.size();
      	 if(addSize==0) {
      		enterShippingAddress(Fname, Lname);      		 
      	 	}else {      	 
      	 	 for (int i=1; i<=addSize;i++) {
      		 scrollDown("cssSelector", selectAddrOfTodays);
      		 waitOnElement("cssSelector", addselectBfr+i+addselectAfr);
      		 WebElement text= driver().findElement(By.cssSelector(addselectBfr+i+addselectAfr));
      		 String actText = text.getText().toLowerCase();      		 
      		 if(actText.equalsIgnoreCase(expText)) {  
      			System.out.println("1223 "+ i+ " " + actText);
      			text.click();   
      			text.click();
      			waitOnElement("cssSelector", shippingNext); 
      			scrollDown("cssSelector", shippingNext);
      			waitOnElement("cssSelector", shippingNext); 
      			clickOnElement("cssSelector", shippingNext);
      			Thread.sleep(1000);
      			clickOnElement("cssSelector", closeErrToast);
      			
      			break;
      		 }else {
      			System.out.println("Already selected");
      			waitOnElement("cssSelector", shippingNext);
      			scrollDown("cssSelector", shippingNext);
      			waitOnElement("cssSelector", shippingNext); 
      			clickOnElement("cssSelector", shippingNext);
      		 }
      	 }
      }	 
      	 
    }
      
      public void shippingAddresForAutoship(String Fname, String Lname ) throws Exception {
     	 logInfo("Enetered into shippingAddressProcess() method");      	   	
     	 String expText = "select";    	
     	 waitOnElement("cssSelector", shippingNext);  		
       	 List<WebElement> select = driver().findElements(By.cssSelector(addselectAuto));
       	 int addSize=select.size();       	
       	 if(addSize==0) {
       		enterShippingAddress(Fname, Lname);       		 
       	     }else {  
       	    	 for(int i=1; i<=addSize;i++) {
	       		 scrollDown("cssSelector", addselectAuto);
	       		 waitOnElement("cssSelector", addselectAutoBfr+i+addselectAfr);
	       		 WebElement text= driver().findElement(By.cssSelector(addselectAutoBfr+i+addselectAfr));
	       		 String actText = text.getText().toLowerCase();
	       		 if((actText.equalsIgnoreCase(expText))) {  			
	       			text.click();   			 
	       			waitOnElement("cssSelector", shippingNext);  
	       		    break;
	       		 		}  		   		
	       	     	}
	       	     waitOnElement("cssSelector", shippingNext);  		
			   	 clickOnButton("cssSelector", shippingNext); 			   	 
	       	     }
     	  
       }
  		  
		 
  
      
      public void selectNextInShipping() throws Exception{
    	  logInfo("Enetered into selectNextInShipping() method");
    	          waitOnSpinner();    	         
    	          waitOnElement("cssSelector", shipTitle);   
    	     	 Thread.sleep(3000);
    	     	  waitOnElement("cssSelector", shipTitle);     	  
    	     	  if (driver().findElement(By.cssSelector(shipTitle)).getText().contains(shippingTitleTxt)){
    	     		  System.out.println("Entered into shipping");
    	     		  waitOnElement("cssSelector", shippingNext);
    	     		  waitOnElement("cssSelector", shippingNext);   
    	     		  clickOnButton("cssSelector", shippingNext);
    	     		  waitOnElement("xpath", spinner);
    	     		  Thread.sleep(4000);
    	     	  		}  	      
    	      }
      
      public void selectNextInAutoshipDelivery () throws Exception{    	  
      	 logInfo("entered into selectNextInDelivery() method");
      	  waitOnElement("cssSelector", shipAddTit);
      	  WebElement del = driver().findElement(By.cssSelector(shipAddTit));
      	  String delivry = del.getText();      	  
      	  if (delivry.equalsIgnoreCase(prop.getLocatorForEnvironment(appUrl,"autoShipdelvryTitle"))){      			     			  		  
      		waitOnElement("cssSelector", shippingNext);
      		clickOnButton("cssSelector", shippingNext);
      		waitOnElement("xpath", spinner);
  		    Thread.sleep(4000);      		  
      	  }else{
      		  Assert.assertEquals(del.getText().trim(), prop.getLocatorForEnvironment(appUrl,"autoShipdelvryTitle"));
      	  }	  
      	  
        }
      
      public void selectNextInPayment() throws Exception{    	  
       	 logInfo("entered into selectNextInDelivery() method");
       	  Thread.sleep(4000);    
       	  WebElement pay = driver().findElement(By.cssSelector(paymentTitle));
       	  System.out.println("Next in delevery "+pay.getText());
       	  Assert.assertEquals(pay.getText().trim(), paymentTitleText);
       	  clickOnButton("cssSelector", shippingNext);
         }
      
      
      
   /// IdLife - 04012017
      
      public void IDLife_registration(String Fname, String Lname) throws Exception {
  		
  		logInfo("Register New Customer with all fields.");
  		try{
  		waitOnElement("cssSelector", registerNew);	
  		clickOnButton("cssSelector", registerNew); 		
  		Thread.sleep(10000);
  		waitOnElement("cssSelector", custFN);
  		inputTextClear("cssSelector", custFN);
  		inputText("cssSelector", custFN, Fname);
  		inputText("cssSelector", custLN, Lname);
  		inputText("cssSelector", custEmail, (Fname+Lname+"@icentris.com"));
  		inputTextClear("cssSelector", custPhone);
  		inputText("cssSelector", custPhone, "1234567891");
  		inputText("cssSelector", custAd1, "Roxbury Drive");
  		inputText("cssSelector", custAd2, "Beverly Hills");
  		inputText("cssSelector", custCounty, "El Dorado");
  		inputText("cssSelector", custCity, "CA");
  		inputText("cssSelector", custZip, "90210");
  		stateSelection("Arkansas");
  		/*genderAndDOB("Male");*/
  		inputText("cssSelector","#user_profile_attributes_birthday","12/12/1991");
		selectFromDropdown("cssSelector","#user_profile_attributes_gender","byVisibleText","Male");
  		inputTextClear("cssSelector", custUserName);
  		inputText("cssSelector", custUserName, Fname);
  		inputText("cssSelector", custPwd, custPwdText);
  		inputText("cssSelector", custConPwd, custPwdText);	
/*  		inputTextClear("cssSelector", custFN);
  		inputText("cssSelector", custFN, Fname);*/
  	    //waitOnElement("cssSelector", custDOB);	
  	 /*   inputText("cssSelector", birthDate, "02/02/1999");*/
  		clickOnButton("cssSelector", custCreate);	
  		confirmationMessage("Account created successfully");  		
  		/*if(driver().findElement(By.cssSelector(error404)).getText().equals(notImplimentedText)){
  			System.out.println("Enterd eroor message");
  			WebElement panelTit = driver().findElement(By.cssSelector(error404));
  			System.out.println(panelTit.getText());
  			Assert.assertEquals(panelTit.getText(), "Save");	
  		}else {
  			WebElement panelTit = driver().findElement(By.cssSelector(panelTitle));
  			System.out.println(panelTit.getText());
  			Assert.assertEquals(panelTit.getText(), "Back To Shop");	
  			System.out.println("Eaxctly as shop");  			
  				} 	*/
  			}catch (Exception e){  			
  			logInfo("Failed!! Some mandatory fields are missing.");
  		}	
  	}
      
      
      
   public void IDLife_exsitingCustomer(String custUser, String passwd) throws Exception{	   
	    waitOnElement("cssSelector", returnExist);
	    clickOnButton("cssSelector", returnExist);
	    waitOnElement("xpath", inputName);	
		inputTextClear("xpath", inputName);	
		waitOnElement("xpath", inputName);	
		inputText("xpath", inputName, custUser);		
		waitOnElement("xpath", inputName);
		inputText("cssSelector", custShopPwdInMaster, passwd);
		waitOnElement("cssSelector", custShopPwdInMaster);
		submitElement("cssSelector", custShopPwdInMaster);   
	   
   }
      
   public void shippingAddressDetails(String Fname, String Lname ) throws Exception{    	  
	   waitOnElement("cssSelector", shippingNext);	   
	   WebElement section = driver().findElement(By.cssSelector(shippingAddsec));
	   String fields = section.getText();
	   System.out.println("fiels" +fields );	   
	   if(fields.contains("Add New Address")){		
		   waitOnElement("cssSelector", shippingNext);  	
		   clickOnButton("cssSelector", shippingNext);  	
	   }else{
		   shippingAddress(Fname, Lname);		   
	   		} 
	   }
      
   
   public void addAutoshipQuanityNdCheckOutInIDLife(int moreQuantiy) throws Exception{		
		logInfo("Entering number of Quanity of products");		
		try{		
			for(int i=1; i <=moreQuantiy-1; i++){  				
				clickOnButton("cssSelector", moreQuantAuto);  				
				waitOnElement("cssSelector", moreQuantAuto);  
			}
			   getText("cssSelector", shedDeleBtn, "shed ");
			    waitOnElement("cssSelector", shedDeleBtn);
				clickOnButton("cssSelector", shedDeleBtn);
				clickOnLink("linkText", "View Cart");				
				waitOnElement("cssSelector", checkOut);	
				clickOnButton("cssSelector", checkOut);	
				waitOnElement("cssSelector", checkOut);	
				clickOnButton("cssSelector", checkOut);	
				
				
			}catch(Exception e){
			logInfo("Failed!! Not able to enter quanity of products");
		}	
		
		}
      
    	  
   public void handleFromCheckOutToSummaryInAutoshipInIDL() throws Exception{    	  
 	  
 	  selectNextInAutoshipDelivery();
 	  selectNextInPayment();
 	  waitOnElement("cssSelector", confirm);
 	  clickOnButton("cssSelector", confirm);
	  confirmationMessage("Autoship was successfully created/updated");
 	  
   }
     
   
   
   public void selectCalendarDate(String caldate) throws Exception{
		logInfo("Inside selectCalendarDate() method.");
		
		/*SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	    SimpleDateFormat format2 = new SimpleDateFormat("MMM/dd/yyyy");
	    Date dateobj = format1.parse(caldate);
	    String date = format2.format(dateobj);
	    System.out.println("newdt =" +date);*/
	    
		String date =caldate;
		String arrdate[] = date.split("/");
		String expmonth = arrdate[0];
		String expday = arrdate[1];
		String expyear = arrdate[2];
		System.out.println("month =" +expmonth);
		System.out.println("day =" +expday);
		System.out.println("year =" +expyear);
				
		//click on date picker
		//driver.findElement(By.xpath(calendarDatePicker)).click();
		
				
		// select year
		
		WebElement mhead = driver().findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[1]/th[2]"));   // //th[@class='picker-switch']
		String month_header = mhead.getText().trim();
		System.out.println("Month Header =" +month_header);
		mhead.click();
		
		Thread.sleep(2000);
		WebElement yhead = driver().findElement(By.xpath("//div[@class='datepicker-months']/table/thead/tr/th[2]"));  // //th[@class='picker-switch']
		String year_header = yhead.getText().trim();
		System.out.println("year header =" +year_header);
		yhead.click();
		
		Thread.sleep(2000);
		WebElement dtyear = driver().findElement(By.xpath("//div[@class='datepicker-years']/table/tbody/tr/td"));
		List <WebElement> years = dtyear.findElements(By.tagName("span"));
		System.out.println("spans =" +years.size());
		String before_yspan = "//div[@class='datepicker-years']/table/tbody/tr/td/span[";
		String after_yspan = "]";
		for(int j=1; j<= years.size(); j++){
			 WebElement eyear = driver().findElement(By.xpath(before_yspan+j+after_yspan));
			 String year = eyear.getText().trim();
			 if(year.equalsIgnoreCase(expyear)){
				 eyear.click();
				 Thread.sleep(2000);
				 break;
			 }
			}

		// select month
		 	Thread.sleep(2000);
			WebElement dtmonth = driver().findElement(By.xpath("//div[@class='datepicker-months']/table/tbody"));
			List <WebElement> spans = dtmonth.findElements(By.tagName("span"));
			System.out.println("spans =" +spans.size());
			String before_span = "//div[@class='datepicker-months']/table/tbody/tr/td/span[";
			String after_span = "]";
							
			for(int i=1; i<= spans.size(); i++){
			 WebElement emonth = driver().findElement(By.xpath(before_span+i+after_span));
			 String month = emonth.getText().trim();
			 if(month.equalsIgnoreCase(expmonth)){
				 emonth.click();
				 Thread.sleep(2000);
				 break;
			 }
		   }
							
		
		// select day
				
		WebElement dtpicker = driver().findElement(By.xpath("//div[@class='datepicker']/div[@class='datepicker-days']/table/tbody")); 
		List <WebElement> alldays = dtpicker.findElements(By.className("day"));
		System.out.println("days =" + alldays.size());
				
		for(int rows=1;rows <=6;rows++){
			for(int cols=1; cols<=7;cols++){
				WebElement dt = driver().findElement(By.xpath("//div[@class='datepicker']/div[@class='datepicker-days']/table/tbody/tr[" + rows + "]/td[" + cols + "]"));
				if(dt.getText().equalsIgnoreCase(expday)){
				System.out.println("Actual day =" +dt.getText() + " ,Expected day =" +expday);
				dt.click();
				break;
				}
			}
		}
	 }	
   
   
  //Monat 
   
   public void addQuanityNdCheckOutInMonat(int moreQuantiy) throws Exception{		
 		logInfo("Entered into addQuanityNdCheckOutInMonat() method");		
 		try{ 
 			String qty = Integer.toString(moreQuantiy);
 			submitElement("cssSelector", qtyField);
 			inputTextClear("cssSelector", qtyField);
 			inputText("cssSelector", qtyField, qty);			
 			selectRadioOrCheckbox("cssSelector", prop.getLocatorForEnvironment(appUrl,"oneTime")); 
 			clickOnButton("cssSelector", addTocartMonat);  
			waitOnElement("linkText", "View Cart");
			clickOnLink("linkText", "View Cart");			
			waitOnElement("cssSelector", checkOut);
			clickOnButton("cssSelector", checkOut);	  			
 			}catch(Exception e){
 			logInfo("Failed!! Not able to enter quanity of products");
 			}
 		}
   
   
   public void calenderDatePicker() throws Exception{  
	   logInfo("Entered into calenderDatePicker() method");	  	   
	   waitOnElement("cssSelector", autoDateField);	   
	   int todaysDay = getCurrentDay();	   
	   JavascriptExecutor js = (JavascriptExecutor)driver(); 
	   if (todaysDay<=25) {		 	     		   
		   /*String dat = "\"document.getElementById('autoship_next_run_date').value= '"+futDate+"'\"";
		   js.executeScript(dat);*/
		   js.executeScript("document.getElementById('autoship_next_run_date').value= '08/15/2018'");
		   	}else {		    	   
			   waitOnElement("cssSelector", autoDateField);			  
			   int futDate2 =  (todaysDay-25)+20;
			   String futDate = getDate(futDate2, "MM/dd/yyyy");			   
			   waitOnElement("cssSelector", autoCalenderBtn);			            		      
			   /*String dat2 = "\"document.getElementById('autoship_next_run_date').value= '"+futDate+"'\"";			   
			   js.executeScript(dat2);*/
			   js.executeScript("document.getElementById('autoship_next_run_date').value= '08/20/2018'");
			   waitOnElement("cssSelector", autoDateField);
			   clickOnElement("cssSelector", autoDateField);
			      }  
	   waitOnElement("cssSelector", autoCalenderBtn);
       clickOnElement("cssSelector", autoCalenderBtn);
       waitOnElement("cssSelector", autoCalenderBtn);
       clickOnElement("cssSelector", autoCalenderBtn);
	
       
   }   
   
   
   public void updateCalenderDate() throws Exception{  
	   logInfo("Entered into updateCalenderDate() method");		   
	   waitOnElement("cssSelector", autoDateField);
	   scrollDown("cssSelector", autoDateField);
	   JavascriptExecutor js = (JavascriptExecutor)driver();            
       String dat = "document.getElementById('autoship_next_run_date').value= '08/24/2018'";
       js.executeScript(dat);
       waitOnElement("cssSelector", autoCalenderBtn);
       clickOnElement("cssSelector", autoCalenderBtn);
       waitOnElement("cssSelector", autoCalenderBtn);
       clickOnElement("cssSelector", autoCalenderBtn);
       
   }   
  
   
public void orderValidation(int minOrderPrice) throws  Exception{
	logInfo("Entered into orderValidation() method");	
	String helpMessage = "To enroll as a VIP Customer you must purchase at least $84 USD/$110 CAD in products in addition to the VIP Membership cost, and setup your autoship with at least $84 USD/$110 CAD in products as well. Please add additional products to your order.";
	String initialOrderPrice= driver().findElement(By.cssSelector(initalOrderPrc)).getText();
	System.out.println("intial order price is " + initialOrderPrice);
	String autoshipOrderPrice= driver().findElement(By.cssSelector(autoshipOrderPrc)).getText();
	System.out.println("Autoship order price is " + autoshipOrderPrice);
	String[] orderPart1 = initialOrderPrice.split("\\$");
	System.out.println(orderPart1.length);
	System.out.println(orderPart1[1]);
	String[] part2 = 	orderPart1[1].split(" ");
	System.out.println(part2[0]);
	int intitalOrderTotal = Integer.parseInt(part2[0]);
	System.out.println(intitalOrderTotal);
	String[] autoshipPart1 =  autoshipOrderPrice.split("\\$");
	System.out.println(autoshipPart1.length);
	System.out.println(autoshipPart1[1]);
	String[] autoshippart2 = 	autoshipPart1[1].split(" ");
	System.out.println(autoshippart2[0]);
	int autoshipTotal = Integer.parseInt(autoshippart2[0]);
	  
	   if ((intitalOrderTotal<minOrderPrice)||(autoshipTotal<minOrderPrice)){			
			waitOnElement("cssSelector",helpText);
			String help = driver().findElement(By.cssSelector(helpText)).getText();			
			boolean isHelpTextDisplayed = verifyElementPresent("cssSelector",helpText);
			if(!isHelpTextDisplayed){
				logInfo(helpMessage);
				Assert.assertTrue(isHelpTextDisplayed, helpMessage);
				//Assert.assertFalse(isHelpTextDisplayed, "To enroll as a VIP Customer you must purchase at least $84 USD/$110 CAD in products in addition to the VIP Membership cost, and setup your autoship with at least $84 USD/$110 CAD in products as well. Please add additional products to your order.");
			}

	
	
	}
	
	}

   
   public void initalOrderPriceValidation(){
	   logInfo("inside initalOrderPriceValidation method");
	   String initialOrderPrice= driver().findElement(By.cssSelector(initalOrderPrc)).getText();
		System.out.println("intial order price is " + initialOrderPrice);
	   
   }
   
     public void selectSuggestedAddress() throws Exception{
	   logInfo("Entered into selectSuggestedAddress() method");
	   //selectRadioOrCheckbox("cssSelector",sugValAddr);
	   Thread.sleep(2000);
	   waitOnElement("cssSelector",sugAddProced);
	   scrollDown("cssSelector",sugAddProced);
	   clickOnButton("cssSelector",sugAddProced);
	   waitOnElement("cssSelector", shippingNext);  
	   
   }
     
     public void creditCardDetailsInMonat(int frameNo) throws Exception {
      	  logInfo("Entered into creditCardDetailsInMonat() method");        	  	     	    
 	  		clickOnButton("cssSelector", mntCCBtn); 	  				
 	  		driver().switchTo().frame(frameNo);  
 	  		Thread.sleep(3000); 	
 	  		scrollDown("cssSelector", mntCCName);
 	  		waitOnElement("cssSelector", mntCCName);
  			inputTextClear("cssSelector", mntCCName);  			
  			inputTextClear("cssSelector", mntCCNumber);
  			inputText("cssSelector", mntCCName, custFNText); 
  			inputText("cssSelector", mntCCNumber, CCCardNo);
  			selectFromDropdown("cssSelector", mntCCExpiry, "byIndex", "3");			
  			inputText("cssSelector",mntCCCVV, "489");
  			driver().switchTo().defaultContent();  			
  			waitOnElement("cssSelector", shippingNext);
  			clickOnButton("cssSelector", shippingNext); 
  			Thread.sleep(4000);
  		}
     
     
     public void billingInfoSection(String country) throws Exception {
    	 logInfo("Entered into billingInfoSection() method");    	 
    	 driver().switchTo().frame(2);  
    	 Thread.sleep(3000); 
    	 scrollDown("cssSelector", billZip); 
    	 waitOnElement("cssSelector", billCountry);    	 
    	 selectFromDropdown("cssSelector", billCountry, "byVisibleText", country);
    	 inputTextClear("cssSelector", billAdd1); 
    	 inputTextClear("cssSelector", billAdd2); 
    	 inputTextClear("cssSelector", billCity); 
    	 inputTextClear("cssSelector", billZip);     	 
    	 if(country==USA) {
    		 inputText("cssSelector", billAdd1,usaAdd1); 
        	 inputText("cssSelector", billAdd2,usaAdd2); 
        	 inputText("cssSelector", billCity,usaCounty); 
        	 inputText("cssSelector", billZip,usaZip); 
        	 selectFromDropdown("cssSelector", billState, "byVisibleText", usaState);        	  
        	 driver().switchTo().defaultContent();  	
    	 		}else if(country==CA) {
    	 			 inputText("cssSelector", billAdd1,CAAdd1); 
    	        	 inputText("cssSelector", billAdd2,CAAdd2); 
    	        	 inputText("cssSelector", billCity,CACounty); 
    	        	 inputText("cssSelector", billZip,CAZip); 
    	        	 selectFromDropdown("cssSelector", billState, "byVisibleText", CAState);    	        		
    	        	 driver().switchTo().defaultContent();  	
    	 			}else {
    	 				Assert.assertNull(country);
    	 	}
    	     	
     }
     
     
     public void selectState(String state) throws Exception {
    	 logInfo("Entered into selectState() method");
    	 waitOnElement("cssSelector", billState);
    	 clickOnElement("cssSelector", billState);     	 
    	 boolean isPresent = false;
    	 List <WebElement> count = driver().findElements(By.cssSelector(billStateList));
    	 System.out.println("size"+ count.size());
    	 for(int i=1; i<=count.size(); i++) {
    		 WebElement list= driver().findElement(By.cssSelector(billStateListBfr+i+")"));
    	 	 String countList = list.getText().trim();
    		 System.out.println("list" + list.getText().trim());
    		 if (countList.equalsIgnoreCase(state)){
    			 isPresent=true;  
    			 list.click();
    			 list.submit();
    			 list.submit();
    			 Thread.sleep(3000);
    			 break;
    		 }
    	 }if(isPresent==false) {
    		 Assert.assertTrue(isPresent, state+" -country is not found" );
    		 }  
     }
     
     
     public void addAnotherCCInMonat(String country, String firstOrAnotherCard) throws Exception {
     	  logInfo("Entered into creditCardDetails() method");
     	  waitOnElement("xpath", spinner);		  
		  waitOnElement("cssSelector", paymentTitle); 
		  waitOnElement("cssSelector", paymentTitle); 
     	  WebElement ccTitle = driver().findElement(By.cssSelector(paymentTitle));
     	  waitOnElement("cssSelector", paymentTitle); 
     	  System.out.println(ccTitle.getText().trim());
     	  if(ccTitle.getText().trim().equalsIgnoreCase(paymentTitleText)){
     		  if(firstOrAnotherCard=="anotherCard") {
     		   waitOnElement("cssSelector", newCardBtn);
  	     	   clickOnElement("cssSelector", newCardBtn);    			  
     		  	}    	   
     	   billingInfoSection(country);	     	   
     	   creditCardDetailsInMonat(2);    	  
 		   	}else{
	  		Assert.assertEquals(ccTitle.getText().trim(), paymentTitleText);
 		   		}	  					
 		}   
      
        
        public void selectFBIconInShop() throws Exception{
    		logInfo("Entered into selectFacebookIcon() method");
    		waitOnElement("cssSelector", facebook);
    		waitOnElement("cssSelector", facebook);
    		waitOnElement("cssSelector", twitterIcon);
    		clickOnElement("cssSelector", facebook);		
    		
    	}
        
        
        public void selectTwitterInIconInShop() throws  Exception{
			logInfo("Entered into selectTwitterInIcon() method.");
			waitOnElement("cssSelector", twitter);	
			scrollDown("cssSelector", twitterIcon);
			waitOnElement("cssSelector", twitterIcon);
			waitOnElement("cssSelector", twitter);
			Thread.sleep(3000);
			clickOnElement("cssSelector", twitter);	
		}
        
      
        
        public void navigateToCustomerPwp(String webSite) throws  Exception{
    		logInfo("inside navigateToCustomerPwp() method.");		
    		driver().navigate().to(webSite);
    		clickOnLink("linkText","SHOP");
    		
    	}
        
        
        public void addSponser(String sponserId) throws Exception {
        	logInfo("inside addSponser() method.");	
        	waitOnElement("cssSelector", chgSponser);
        	clickOnElement("cssSelector", chgSponser);
        	Thread.sleep(2000);
        	for (int i=1; i<=3;i++) {
        	waitOnElement("cssSelector", searchSponser);
        	clickOnElement("cssSelector", searchSponser);
        	inputTextClear("cssSelector", searchSponser);
        	inputText("cssSelector", searchSponser, sponserId);
        	submitElement("cssSelector", searchSponser);
        	waitOnSpinner();
        	Thread.sleep(10000);
        	    }
        	boolean isResults = false;
        	List <WebElement> res = driver().findElements(By.cssSelector(resSponser));
        	for (WebElement results : res) {
        		String sponResults = results.getText().trim();
        		System.out.println(sponResults);
        		if(sponResults.equalsIgnoreCase(sponserId)){
        			isResults=true;
        			results.click();
        			Thread.sleep(2000);
        		}
        	}if(isResults==false) {
        		Assert.assertTrue(isResults, sponserId + " sserach does not found");
        	}
        	
        	
        	
        }
        
        public void personalInfoInMnt(String userName, String sponserId) throws  Exception {
    		logInfo("inside monatVIPEnrollmentStep2() method.");
    		waitOnElement("cssSelector", eFirst);
    		Thread.sleep(2000);
    		addSponser(sponserId);
    		inputText("cssSelector", eFirst, userName);
    		inputText("cssSelector", eLast, "vipdist");
    		inputText("cssSelector",eCell,"7845964587");
    		inputText("cssSelector", eEmail, "vibe.icentris@gmail.com");    				
    		inputText("cssSelector", eUserName, userName);    		
    		inputText("cssSelector", ePswd, "password1");
    		inputText("cssSelector", eConPswd, "password1");
    		inputText("cssSelector", eHome, "1234567891");
    		inputText("cssSelector", eBirthDay, "12/12/1988");
    		selectFromDropdown("cssSelector", eGender, "byVisibleText", "Male");    	
    		inputTextClear("cssSelector", eFirst);
    		inputTextClear("cssSelector", eLast);
    		inputText("cssSelector", eFirst, userName);
    		inputText("cssSelector", eLast, "vipdist");
    		scrollDown("cssSelector",contPersonal);
    		waitOnElement("cssSelector",contPersonal);
    		getText("cssSelector", contPersonal, "Text");
    		clickOnElement("cssSelector", contPersonal);
    	}
        
        
        
        
        public void monatVIPEnrollmentStep3() throws  Exception {
    		logInfo("inside monatVIPEnrollmentStep3 method");
    		JavascriptExecutor js = (JavascriptExecutor) driver();    		
    		meroll.enrollmentShippingAddress();
    		meroll.selectAddressValidation("Suggested Address");    		
    		WebElement firstaddCC = driver().findElement(By.cssSelector(firstCCAdd));
    		js.executeScript("arguments[0].click();", firstaddCC);    		
    		meroll.enterCreditCardInfo();
    		meroll.submitCCAddressDetails();
    		meroll.selectAddressValidation("Suggested Address");	
    		waitOnElement("cssSelector", saveCCBtn);	
    		clickOnElement("cssSelector", saveCCBtn);	
    		driver().switchTo().defaultContent();	
    		Thread.sleep(3000);    		
    		waitOnElement("cssSelector", enrlContBtn);    		
    		waitOnElement("cssSelector", secondCCAdd);    		
    		Thread.sleep(5000);
    		WebElement addCC = driver().findElement(By.cssSelector(secondCCAdd));    		
    		js.executeScript("arguments[0].click();", addCC);    			
    		Thread.sleep(3000);			  
    		meroll.enterCreditCardInfo();
    		clickOnElement("cssSelector", selectAdd);
    		waitOnElement("cssSelector", saveCCBtn);	
    		clickOnElement("cssSelector", saveCCBtn);
    		Thread.sleep(4000);
    		waitOnElement("cssSelector", enrlContBtn);
    		driver().findElement(By.cssSelector(enrlContBtn)).click();
    		waitOnElement("cssSelector", agreeToc);
    		selectRadioOrCheckbox("cssSelector", agreeToc);
    		waitOnElement("cssSelector", enrlContBtn);
    		clickOnElement("cssSelector", enrlContBtn);
    		
    	}
        
        public void selectTabbings() throws Exception{
        	logInfo("Entered into selectTabbings() method");
        	waitOnSpinner();
        	waitOnElement("cssSelector", prodTabs);        	
        	List<WebElement> tabs = driver().findElements(By.cssSelector(prodTabs));
        	for (WebElement tab :tabs){
        		String actTabName =tab.getText().trim();        		
        		String expectedBlock = "display: block;";        		
        		switch (actTabName) {
        		case "Benefits":
        			Assert.assertEquals(actTabName, "Benefits");
        			tab.click();
        			WebElement bene = driver().findElement(By.cssSelector(beneTabs));
        			String benefitsBlock = bene.getAttribute("style");        			
        			Assert.assertEquals(benefitsBlock, expectedBlock);
        			break;
        		case "Ingredients":
        			Assert.assertEquals(actTabName, "Ingredients");
        			tab.click();
        			WebElement ingre = driver().findElement(By.cssSelector(ingreTabs));
        			String ingreBlock = ingre.getAttribute("style");        		
        			Assert.assertEquals(ingreBlock, expectedBlock);
        			break;
        		case "Directions":
        			Assert.assertEquals(actTabName, "Directions");
        			tab.click();
        			WebElement direct = driver().findElement(By.cssSelector(directTabs));
        			String directBlock = direct.getAttribute("style");        		
        			Assert.assertEquals(directBlock, expectedBlock);
        			break;
        		case "We Say No To":
        			Assert.assertEquals(actTabName, "We Say No To");
        			tab.click();
        			WebElement wesaysNo = driver().findElement(By.cssSelector(weSayTabs));
        			String wesaysNoBlock = wesaysNo.getAttribute("style");        			
        			Assert.assertEquals(wesaysNoBlock, expectedBlock);
        			break;
        		case "Clinical Study Information":
        			Assert.assertEquals(actTabName, "Clinical Study Information");
        			tab.click();
        			WebElement study = driver().findElement(By.cssSelector(studyTabs));
        			String studyBlock = study.getAttribute("style");        			
        			Assert.assertEquals(studyBlock, expectedBlock);
        			break;
        		default:
        			Assert.assertNotNull(actTabName);       		
        		}        		
        	}       	
        }  
        
        
        public void combinationOrderAlert(String typeYesOrNo) throws Exception{
        	logInfo("Entered into combinationOrderAlert() method");
        	waitOnElement("cssSelector", combBody);
        	WebElement body = driver().findElement(By.cssSelector(combBody));
        	String actText =body.getText().trim();        	
        	Assert.assertEquals(actText, comBodyText);
        	if(typeYesOrNo=="Yes") {
        		confirmToOk();        		
        		waitOnElement("cssSelector", viewCart);				
				clickOnElement("cssSelector", viewCart);        		
        	}else if(typeYesOrNo=="No"){         		
        		waitOnSpinner();
        		confirmToNo();        		
        		waitOnElement("cssSelector", viewCart);				
				clickOnElement("cssSelector", viewCart);
        	}else {
        		Assert.assertNull(typeYesOrNo);
        	}        	
        }
        
        
        public void validateVolumeProductInTodaysOrder(String tableTitle, String expectedSKU) throws Exception {
        	logInfo("Entered into validateTodaysOrder() method");
        	System.out.println("Entered into validateTodaysOrder() method");
        	Thread.sleep(9000);
        	waitOnElement("cssSelector", orderDetTitle);
        	WebElement tit = driver().findElement(By.cssSelector(orderDetTitle));
        	String title = tit.getText().trim();        	
        	Assert.assertEquals(title, orderDetTitled);
        	boolean isTitleFound=false;
        	List <WebElement> headers = driver().findElements(By.xpath(orderheads));
        	for(int i=1; i<=headers.size(); i++) {
        		WebElement head = driver().findElement(By.xpath(orderheadsBfr+i+"]")) ;
        		String actHeader = head.getText().trim();        		
        		if(actHeader.equalsIgnoreCase(tableTitle)) {
        			isTitleFound=true;
        			List <WebElement> prod = driver().findElements(By.xpath(prodNames+i+prodNameAfr));
        			int prodSize = prod.size();         			
        			boolean isSkuFound=false;
        			for(int j=1; j<=prodSize; j++) {
        				WebElement prodName = driver().findElement(By.xpath(prodNameBfr+j+prodNameMid+i+prodNameAfr));
        				prodName.click();
        				waitOnElement("xpath", skuText2Days);
        				WebElement sku= driver().findElement(By.xpath(skuText2Days));
        				String actSku = sku.getText().trim();        				
        				if (actSku.equalsIgnoreCase(expectedSKU)) {
        					isSkuFound =true;     
        					nav2CustShopping();        					
        					break;
        				}else {        					
        				    clickOnElement("cssSelector", miniCart);
        					waitOnElement("cssSelector", viewCart);  	    				  
  	    				    clickOnElement("cssSelector", viewCart);  	    				   
  	    				    
        				}       				
        			}if(isSkuFound==false) {
        				Assert.assertTrue(isSkuFound,expectedSKU +" is not found" );
        			}
        			break;
        		}   		
        		
        	}if(isTitleFound==false) {
        		Assert.assertTrue(isTitleFound, tableTitle+ " header is not present");
        	}
        	
        }
        
        
        public String getSKU() throws Exception {
        	logInfo("Entered into getSKU() method");        	
			waitOnElement("xpath", skuText2Days);
			WebElement sku= driver().findElement(By.xpath(skuText2Days));
			String actSku = sku.getText().trim();			
            return actSku;        	
        }
        
        public void validateVolumeProductIFlexship(String tableTitle, String expectedSKU) throws Exception {
        	logInfo("Entered into validateTodaysOrder() method");
        	waitOnElement("cssSelector", autoDetTitle);
        	WebElement tit = driver().findElement(By.cssSelector(autoDetTitle));
        	String title = tit.getText().trim();         	
        	Assert.assertEquals(title, autoDetTitled);
        	boolean isTitleFound=false;
        	List <WebElement> headers = driver().findElements(By.xpath(autoheads));
        	for(int i=1; i<=headers.size(); i++) {
        		WebElement head = driver().findElement(By.xpath(autoheadsBfr+i+"]")) ;
        		String actHeader = head.getText().trim();    
        		System.out.println("actHeader" + actHeader);
        		if(actHeader.equalsIgnoreCase(tableTitle)) {
        			isTitleFound=true;        			
        			List <WebElement> prod = driver().findElements(By.xpath(autoProdNames+i+prodNameAfr));
        			int prodSize = prod.size(); 
        			System.out.println("listOfprodutcs"+prodSize );
        			boolean isSkuFound=false;
        			for(int j=1; j<=prodSize; j++) {
        				WebElement prodName = driver().findElement(By.xpath(autoProdNameBfr+j+prodNameMid+i+prodNameAfr));
        				prodName.click();
        				Thread.sleep(3000);
        				waitOnElement("xpath", skuTextFlex);
        				WebElement sku= driver().findElement(By.xpath(skuTextFlex));
        				String actSku = sku.getText().trim();
        				System.out.println("actSku "+ actSku);
        				if (actSku.equalsIgnoreCase(expectedSKU)) {
        					isSkuFound =true;  
        					 waitOnElement("cssSelector", flxAddbtn);
        					 clickOnButton("cssSelector", flxAddbtn);  
        					 waitOnElement("cssSelector", autoDetTitle);
        					
        					break;
        				}   				
        			}if(isSkuFound==false) {
        				Assert.assertTrue(isSkuFound,expectedSKU +" is not found" );
        			}
        			break;
        		}   		
        		
        	}if(isTitleFound==false) {
        		Assert.assertTrue(isTitleFound, tableTitle+ " header is not present");
        	}
        }
        
        
        public void freeShippingCalculations(float expectedValue) throws Exception{
        	logInfo("Entered into freeShippingCalculations() method");
        	 String subTot =null;
        	 waitOnSpinner();
        	 waitOnElement("cssSelector", miniCart);
        	 WebElement cart = driver().findElement(By.cssSelector(miniCart));
			  String cartStatus = cart.getAttribute("aria-expanded");				  
			  if ((cartStatus==null)||(cartStatus=="true")) {
				  clickOnElement("cssSelector", miniCart);
			      }
        	 waitOnElement("xpath", subTotalAmt);        	
        	 List<WebElement> subs = driver().findElements(By.xpath(subTotalAmt));
        	 int size = subs.size();        	 
        	 if(size>1) {
        		 WebElement sub = driver().findElement(By.xpath(subTotalAmt2));
        		 subTot = sub.getText().trim();
        		 
        	 }else {
        		 WebElement sub = driver().findElement(By.xpath(subTotalAmt));
        		 subTot = sub.getText().trim();
        	 }        	 
        	 String[] splitted = subTot.split("[$ ]");        	 
        	 float subTotal = Float.parseFloat(splitted[1]);        	
        	 WebElement free = driver().findElement(By.xpath(freeAmnt));
        	 String freeText = free.getText().trim();        	
        	 String[] spaceSplit = freeText.split(" ");        	 
        	 System.out.println(freeText + "size by "+spaceSplit.length);        	
    		 String getAmout = spaceSplit[3];
    		 String[] spliAmt = getAmout.split("[$]");    		 
    		 float remainingFree = Float.parseFloat(spliAmt[1]);        	 
    		 System.out.println("spits by "+remainingFree);
    		 float expeptedRemainingFree = expectedValue-subTotal;
        	 System.out.println("expeptedRemainingFree"+ expeptedRemainingFree + " remainingFree"+remainingFree);
        	 Assert.assertEquals(remainingFree, expeptedRemainingFree);        	
            	}
        
        
        public void addPromoproducts(int expectedValue, String typeOfOffer) throws Exception{
        	logInfo("Entered into addPromoproducts() method");             	
        	 WebElement free = driver().findElement(By.cssSelector(promoNdfree));
        	 String freeText = free.getText().trim();       
        	 if(freeText.contains("promo")) {
        		 String total = driver().findElement(By.xpath(autoTotal)).getText().trim();
        		 String[] splitTotal = total.split("[$ ]");    
        		 float autoTot = Float.parseFloat(splitTotal[1]); 
        		 int autoTotl = (int)Math.round(autoTot);
        		 System.out.println("autoTotal "+ autoTotl);
        		 int expectedQtyReach = (expectedValue/autoTotl)+1;
        		 selectProduct();
        		 addFlexshipQuanity(expectedQtyReach);
        		 clickOnElement("xpath", autoTotal);
        		 if(typeOfOffer.equalsIgnoreCase("promo")) {
        			 waitOnElement("xpath", promoAddBtn);
        			 getText("xpath", promoAddBtn, "test ");
        			 clickOnElement("xpath", promoAddBtn);        			 
        		 }else if(typeOfOffer.equalsIgnoreCase("onlyForYou")) {
        			 waitOnElement("xpath", OFYAddBtn);
        			 String ofy = driver().findElement(By.xpath(OFYAddBtn)).getText().trim();
        			 System.out.println("ofy"+ofy);
        			 clickOnElement("xpath", OFYAddBtn);        			 
        		 }else {
        			 Assert.assertNotEquals(null,typeOfOffer);
        		 }        		 
        	 }else {
        		 System.out.println("No promo is not available currently");
        	 }
        	 
        	       	
        	 	}
        
        
        
        
        public void validateDiscount() throws Exception{
        	logInfo("Entered into validateDiscount() method");        	
        	scrollDown("cssSelector", discount);
        	WebElement text = driver().findElement(By.cssSelector(discount));
        	String actText = text.getText().trim();        	
        	Assert.assertEquals(actText, discountText);        	
        	String dis= driver().findElement(By.cssSelector(disPrice)).getText().trim();
       	 	float discountPrice = Float.parseFloat(dis);
       	    String[] tot= driver().findElement(By.xpath(itemTotl)).getText().trim().split("[$]");
	     	float itemTotal = Float.parseFloat(tot[1]);	    	
	    	String[] taxed= driver().findElement(By.xpath(itemTax)).getText().trim().split("[$]"); 	
	    	float tax = Float.parseFloat(taxed[1]);
	    	String[] ship= driver().findElement(By.xpath(shipping)).getText().trim().split("[$]"); 	  
	    	float shippingTotal = Float.parseFloat(ship[1]);	    	
	    	String[] hand= driver().findElement(By.xpath(handlig)).getText().trim().split("[$]"); 
	    	float handling = Float.parseFloat(hand[1]);	    	
	    	String[] OrdTot= driver().findElement(By.xpath(orderTotl)).getText().trim().split("[$]"); 
	    	float orderTotal = Float.parseFloat(OrdTot[1]);
	    	float calculations = itemTotal+tax+shippingTotal+handling+(discountPrice);		    	
	    	if(calculations==orderTotal) {	    		
	    		 nav2CustShopping();
	    		 selectProduct();
	    	     addProductWithQuanity(1);
	    		 emptyCartProducts();	    		
	    	}else {
	    		 nav2CustShopping();
	    		 selectProduct();
	    	     addProductWithQuanity(1);
	    		 emptyCartProducts();	
	    		Assert.assertEquals(calculations, orderTotal);
	    	}
       	    
        }
        
        
        public void expressCheckOut() throws Exception{
        	logInfo("Entered into expressCheckOut() method");        	
        	waitOnElement("cssSelector", checkOut);			
        	scrollDown("cssSelector", expressChkOut);
        	waitOnElement("cssSelector", expressChkOut);
        	clickOnElement("cssSelector", expressChkOut);       	
        }
        
        public void reOrderWithSameProducts() throws Exception{
        	logInfo("Entered into reOrderWithProducts() method");
        	waitOnElement("cssSelector", reOderBtn);
			clickOnButton("cssSelector", reOderBtn);        	
        }
        
        public void validateAutoshipOrder() throws Exception{
        	logInfo("Entered into validateAutoshipOrder() method");
        	String expTitle="Back To Shop";
        	boolean isfound=true;
        	waitOnElement("cssSelector", shopTitle);	
        	WebElement tit = driver().findElement(By.cssSelector(shopTitle));        	
        	String actTitle = tit.getText().trim();        	
        	if(actTitle.equalsIgnoreCase(expTitle)){
        		isfound =false;
        		WebElement alert = driver().findElement(By.cssSelector(alrtMsg2));
        		System.out.println("alrtMsg2" + alert.getText());
        		Assert.assertTrue(isfound,expTitle);  
        		
        	}if(isfound==true) {
        		System.out.println("Successfully!! autoship order is placed");        		
        	}
    		
        }
        
        public void validateFlexshipTitle() throws Exception {
        	logInfo("Entered into validateFlexshipTitle() method");
        	boolean isfound=true;
        	waitOnElement("cssSelector", flexshipTitle);	
        	WebElement tit = driver().findElement(By.cssSelector(flexshipTitle));        	
        	String actTitle = tit.getText().trim();
        	System.out.println("actTitle "+actTitle);
        	if(actTitle.contains(flexshipTitlText)){
        		isfound =true;        	
        	} if(isfound==false) {
        		Assert.assertTrue(isfound, flexshipTitlText + " is not found");
        	}
        	
        }
        
        
        
        public void selectGooglePlusIcon() throws Exception{    		
    		logInfo("Entered into selectGooglePlusIcon() method");
    		waitOnElement("cssSelector", googlePlus);	
    		clickOnElement("cssSelector", googlePlus);	
        }
        
        public void shareInGooglePlus(String gmail, String passwd) throws Exception{
    		logInfo("Enter into shareInGooglePlus() method");
    		String wndBeforeWindow = driver().getWindowHandle();	
    		for(String w : driver().getWindowHandles()){
    				if(!w.equalsIgnoreCase(wndBeforeWindow)){
    		           driver().switchTo().window(w);
    		           Thread.sleep(3000);
    		            waitOnElement("cssSelector",inputGmail);
    					inputTextClear("cssSelector",inputGmail);
    					inputText("cssSelector",inputGmail, gmail);
    					clickOnButton("cssSelector", btnGmailNext);
    					/*WebElement staysigned = driver().findElement(By.xpath(chkStaySignedIn));
    					String isChecked = staysigned.getAttribute("checked").trim();
    					if(isChecked.equalsIgnoreCase("checked")){
    						staysigned.click();
    			}*/
    					
    			 
    		    waitOnElement("cssSelector",inputGmailPasswd);
    			inputText("cssSelector",inputGmailPasswd, passwd);
    			clickOnButton("cssSelector", btnSignIn);
    			Thread.sleep(4000);
    			getText("cssSelector", gmailComment, "product is");
    			inputText("cssSelector", gmailComment, "Superb product");
    			clickOnButton("xpath", gmailShare);
    		/*clickOnLink("linkText", "Close this window");*/	
    		Thread.sleep(8000);
    		driver().switchTo().window(wndBeforeWindow);
    		break;
    		}
    				}
    	
    	}
        
        
        
        public void loginGooglePlus(String uname, String passwd) throws Exception{
    		logInfo("Acess into google plus account with credentials");
    		driver().navigate().to("https://plus.google.com/");	
    		clickOnButton("cssSelector", "a#gb_70");
    		inputText("cssSelector",inputGmail, uname);
    		clickOnButton("cssSelector", btnGmailNext);
    		Thread.sleep(3000);		
    		waitOnElement("cssSelector",inputGmailPasswd);
    		inputText("cssSelector",inputGmailPasswd, passwd);
    		clickOnButton("cssSelector", btnSignIn);
    		
     
     }
     
     

    	public boolean verifyInGooglePlus(String postName) throws Exception{
    		
    		clickOnElement("cssSelector", googAll);
    		clickOnElement ("cssSelector",gogHome );
    		
    		boolean isPostPresent  = false;
    		
    		List <WebElement> list = driver().findElements(By.cssSelector(googPostList));
    		System.out.println(list.size());
    		for (WebElement glist: list){
    			
    			System.out.println(glist.getText()+ " in google plus");
    			
    			if (glist.getText().trim().contains(postName)){
    				isPostPresent = true;					
    				System.out.println("Google Puls Post name is "+ glist.getText());
    				break;
    			}	
    			
    		}if (isPostPresent==false){
    			
    			Assert.assertTrue(isPostPresent, postName + " is not found in googlePlus page" );
    		}	
    		
    		clickOnElement("cssSelector", googAccountIcon);
    		clickOnButton("cssSelector", googLogout);
    		Thread.sleep(3000);
    		return isPostPresent;
    		
    		
    	}	 
    	
    	
    public boolean verifyInGooglePlusForShopProducts(String postName) throws Exception{
    		
    		clickOnElement("cssSelector", googAll);
    		clickOnElement ("cssSelector",gogHome );
    		
    		boolean isPostPresent  = false;
    		
    		List <WebElement> list = driver().findElements(By.cssSelector(gogList));
    		System.out.println(list.size());
    		for (WebElement glist: list){
    			
    			System.out.println(glist.getText()+ " in google plus");
    			
    			if (glist.getText().trim().contains(postName)){
    				isPostPresent = true;					
    				System.out.println("Google Puls product name is "+ glist.getText());
    				break;
    			}	
    			
    		}if (isPostPresent==false){
    			
    			Assert.assertTrue(isPostPresent, postName + " is not found in googlePlus page" );
    		}	
    		
    		clickOnElement("cssSelector", googAccountIcon);
    		clickOnButton("cssSelector", googLogout);
    		Thread.sleep(3000);
    		return isPostPresent;
    		
    		
    	}	
    
    public void selectTypeOfCustomer(String VIPrNonVIP){
      	 logInfo("Entered into selectTypeOfCustomer(String VIPrNonVIP) method");
      	 try{
      		 scrollDown("cssSelector", monatnoThanksBtn);
      		 switch(VIPrNonVIP){       		 
      		 case "No Thanks, Continue With My Order":
      			 clickOnElement("cssSelector", monatnoThanksBtn);
      			 break;
      		 case"Join as a VIP Customer":
      		 	clickOnElement("cssSelector", monatVipBtn);
      		 	break;
      			 
      		 }
      		 
      	 }catch (Exception e){
      		System.err.println(VIPrNonVIP +" type of user is not found."); 
      	 }    	   	 
      	 
       }   
       
       
       public void vipPersonalInfomation(String userName) throws  Exception {
   		logInfo("Entered into monatVIPEnrollmentStep2() method.");
   		waitOnElement("cssSelector", eFirst);
   		inputTextClear("cssSelector", eFirst);
   		inputTextClear("cssSelector", eLast);
   		inputTextClear("cssSelector",eCell);
   		inputTextClear("cssSelector", eEmail);   		
   		inputTextClear("cssSelector", eUserName);    		
   		inputTextClear("cssSelector", ePswd);
   		inputTextClear("cssSelector", eConPswd);
   		inputTextClear("cssSelector", eHome);
   		inputTextClear("cssSelector", eBirthDay);  		
   		inputText("cssSelector", eFirst, vipFNText);
   		inputText("cssSelector", eLast, custLNText);
   		inputText("cssSelector",eCell,usaPhone);
   		inputText("cssSelector", eEmail, recipMailId);   		
   		inputText("cssSelector", eUserName, userName);    		
   		inputText("cssSelector", ePswd, custPwdText);
   		inputText("cssSelector", eConPswd, custPwdText);
   		inputText("cssSelector", eHome, "1234567");
   		inputText("cssSelector", eBirthDay, "12/12/1988");
   		scrollDown("cssSelector", eConPswd);
   		selectFromDropdown("cssSelector", eGender, "byVisibleText", "Male");
   		selectFromDropdown("cssSelector", enrollmentlanguagePreference, "byVisibleText", "English");
   		waitOnElement("linkText", "Continue");
   		clickOnElement("linkText", "Continue");
   	}
       
       public void monat_validateProductsQty(String prodName, int minPrice) throws Exception{
    	   logInfo("inside monat_AutoshipQty..method ");
    		
    		List<WebElement> totrows = driver().findElements(By.xpath("//*[@id='products-table']/tbody/tr"));
    		String before_path= "//*[@id='products-table']/tbody/tr[";
    		String after_Path  = "]";
    		String prodName_path = "/td[2]";
    		String autoShipPrice = "/td[3]";
    		int totrowsCount = totrows.size();	
    	    boolean isProdPresent = false;
    		for(int i=1;i<=totrowsCount;i++){			
    			WebElement selProduct = driver().findElement(By.xpath(before_path+i+after_Path+prodName_path));
    			String expectedProductName = selProduct.getText().trim();			
    			if(expectedProductName.equalsIgnoreCase(prodName)){
    				isProdPresent=true;
    				String prodqty = driver().findElement(By.xpath(before_path+i+after_Path+autoShipPrice)).getText();
    				String[] part1 = prodqty.split("\\$");			
    				String[] part2 = 	part1[1].split(" ");			
    				int result = Integer.parseInt(part2[0]);				
    				int expectedQtyReach = minPrice/result;
    				int selectQty = expectedQtyReach+2;				
    				String autoshipQtypath1 = "//*[@id='products-table']/tbody/tr[";
    				String autoshipQtypath2 = "]/td[4]/div/div/ul/li[";
    				String autoshipQtypath3 = "]/a";
    				String qtypath1 = "//*[@id='products-table']/tbody/tr[";
    				String qtyPath2 ="]/td[5]/div/div/ul/li[";
    				String qtypath3 = "]/a";
    				String tablePart1 = "//*[@id='products-table']/tbody/tr[";
    				String tablePart2 = "]/td[4]/div/div/button";
    				String autoshiptablePart2 ="]/td[5]/div/div/button";
    				clickOnElement("cssSelector", enrollContinueBtn);
    				orderValidation(84);
    				waitOnElement("xpath",tablePart1+i+tablePart2);
    				driver().findElement(By.xpath(tablePart1+i+tablePart2)).click();
    				driver().findElement(By.xpath(autoshipQtypath1+i+autoshipQtypath2+selectQty+autoshipQtypath3)).click();
    				clickOnElement("cssSelector", enrollContinueBtn);
    				orderValidation(84);
    				Thread.sleep(10000);
    				waitOnElement("xpath",tablePart1+i+autoshiptablePart2);
    				driver().findElement(By.xpath(tablePart1+i+autoshiptablePart2)).click();
    				waitOnElement("xpath",qtypath1+i+qtyPath2+selectQty+qtypath3);
    				driver().findElement(By.xpath(qtypath1+i+qtyPath2+selectQty+qtypath3)).click();
    				waitOnElement("cssSelector", enrollContinueBtn);
    				clickOnElement("cssSelector", enrollContinueBtn);
    				waitOnElement("cssSelector",".display.optional.control-label");
    				String  headingText = driver().findElement(By.cssSelector(".display.optional.control-label")).getText();
    				System.out.println(headingText);
    				Assert.assertEquals(headingText, "Sponsor");				
    				break;			
    			}			
    		}if (isProdPresent==false){
    			Assert.assertTrue(isProdPresent, prodName + " product is not present.");
    		}
    		
       }
       
       
       public void selectProdforRegistration(String productName,String add2CartOrFlexship) throws Exception {
    	   logInfo("Entered into searchProdforRegestration method ");
    	   selectProductsfromCategory("All Products");
    	   waitOnSpinner();
    	   waitOnElement("cssSelector",rSearch);
    	   scrollDown("cssSelector",rSearch);
    	   inputTextClear("cssSelector",rSearch);
    	   inputText("cssSelector",rSearch, productName);
    	   inputTextClear("cssSelector",rSearch);
    	   inputText("cssSelector",rSearch, productName);
    	   submitElement("cssSelector",rSearch);    	 
    	   boolean isProdPresent = false;
    	   waitOnSpinner();
	       waitOnElement("cssSelector", productList);
	  		List <WebElement> lis = driver().findElements(By.cssSelector(productList));	  		
	  		for (int i=2; i<=lis.size()+1; i++){
	  			WebElement lt= driver().findElement(By.cssSelector(rprodListBfr+i+rprodListAfr));	  			
	  			if (lt.getText().trim().contains(productName)){	
	  				isProdPresent=true;
	  				boolean isButtonFound = false;
	  				System.out.println("items name is "+lt.getText());
	  				List<WebElement> buttons = driver().findElements(By.cssSelector(rprodListBfr+i+rAdd2CartAfr));
	  				scrollDown("cssSelector",rprodListBfr+i+rAdd2CartAfr);	  				
	  				for(WebElement buts :buttons) {	  					
	  					if(buts.getText().equalsIgnoreCase(add2CartOrFlexship)) {
	  						isButtonFound=true;
	  						buts.click();	  						
	  						break;  						
	  					}
	  				  }if(isButtonFound==false) {
	  					Assert.assertTrue(isButtonFound, add2CartOrFlexship+ " buuton is not found" );
	  				  } 
	  				  break;	 				
	  			}	
	  		}if(isProdPresent==false){
	  			Assert.assertTrue(isProdPresent, productName+ " is not found" );
	  		}  		
    	   
       	}
       
       
       public void addMoreProductsIfRequired(float expTotal) throws Exception {
    	   logInfo("Entered into addMoreProductsIfRequired method");   	   
    	   	   float actSubTotalFlexship = subTotalOfFlexship();    	 
    		   float actSubtotalTodays = subTotalsOfToday();
        	   System.out.println("actSubtotalTodays "+ actSubtotalTodays);
    		   if(expTotal>=actSubtotalTodays) {
    			   System.out.println("less of Todayship");  
        		   waitOnElement("cssSelector", productList);
        		   String price = driver().findElement(By.cssSelector(firstProdPric)).getText().trim();
        		   String[] pricSplit = price.split("[$]");
              	   System.out.println("Product cost is "+ pricSplit[1]);
              	   float actPrice = Float.parseFloat(pricSplit[1]);
              	   float reqTodaysPrice = expTotal-actSubtotalTodays;
              	   int expQuanity = (int) ((reqTodaysPrice/actPrice)+1);
              	   System.out.println("actPrice "+actPrice);
              	   System.out.println("reqPrice" +reqTodaysPrice);
              	   System.out.println("expQuanity "+expQuanity); 
              	   for (int j=1; j<=expQuanity; j++) {
              		   WebElement plusSign = driver().findElement(By.cssSelector(firstProdAddPlus));
              		   plusSign.click();
              		   waitOnElement("cssSelector", productList);
              	   	  }
              	   waitOnElement("cssSelector", firstProdAddCrtBtn);
              	   clickOnElement("cssSelector", firstProdAddCrtBtn);              	   
                   if(expTotal>=actSubTotalFlexship) {
    			   System.out.println("less of Todayship");    		
              	   float reqFlexPrice = expTotal-actSubTotalFlexship;
              	   int expFlexQuanity = (int) ((reqFlexPrice/actPrice)+1);              	   
              	   System.out.println("reqPrice" +reqFlexPrice);
              	   System.out.println("expQuanity "+expFlexQuanity); 
              	   for (int k=1; k<=expFlexQuanity; k++) {
              		   WebElement plusflexSign = driver().findElement(By.cssSelector(firstProdPricFlexPlus));
              		   plusflexSign.click();
              		   waitOnElement("cssSelector", productList);
              	   	  }
              	   waitOnElement("cssSelector", firstProdFlexBtn);
              	   clickOnElement("cssSelector", firstProdFlexBtn);    			   
                   }}else {
    			   System.out.println("Both recached min amount");
                   }
    		   waitOnElement("cssSelector", selProdCont);
    		   scrollDown("cssSelector", selProdCont);
    		   clickOnButton("cssSelector", selProdCont);
        		     	  			
       	  		   
    	   
       }
       
       
       
       public float subTotalsOfToday() throws Exception {
    	   logInfo("Entered into placeOrderSubTotal method ");
    	   waitOnElement("cssSelector", subTot);
    	   scrollDown("cssSelector", subTot);
    	   String tot = driver().findElement(By.cssSelector(subTot)).getText().trim();    	   
    	   String[] splitted = tot.split("[$]");
      	   System.out.println("Product cost is "+ splitted[1]);
      	   float subTotal = Float.parseFloat(splitted[1]);
      	   System.out.println("float is "+subTotal);
		return subTotal;  	   
		
    	   
       }
       
       
       public float subTotalOfFlexship() throws Exception, Exception {
    	   logInfo("Entered into placeOrderSubTotal method ");
    	   waitOnElement("cssSelector", subTotAuto);
  	       String totAuto = driver().findElement(By.cssSelector(subTotAuto)).getText().trim();    	   
  	       String[] splitAuto = totAuto.split("[$]");
    	   System.out.println("Product cost is "+ splitAuto[1]);
    	   float subTotalAutoship = Float.parseFloat(splitAuto[1]);
    	   System.out.println("Autoship "+subTotalAutoship);
		return subTotalAutoship; 
       }
       
       public void validateSelectProductsTitle() throws Exception {
    	   logInfo("Entered into validateTitle method ");
    	   String expMsg = "SELECT PRODUCTS";
    	   String billingText = "BILLING AND SHIPPING";
    	   waitOnElement("cssSelector", regTitle);
    	   String title = driver().findElement(By.cssSelector(regTitle)).getText().trim();    	   
    	   if(!title.equalsIgnoreCase(expMsg)) {
    		   System.out.println("Successfully 11 Navigated to Billing");
    	   }else {    		   
    		   Assert.assertEquals(title, billingText);
    	   }	   
    	   
       }
       
       
    public void registerNewRetailer(String Fname, String Lname) throws Exception {		
   		logInfo("Entered into registerNewRetailerr() method");	   	
   		waitOnElement("cssSelector", registerNew);	
   		waitOnElement("cssSelector", registerNew);	
   		clickOnElement("cssSelector", registerNew);	
   		waitOnElement("cssSelector", custFN);	
   		inputTextClear("cssSelector", custFN);	
   		inputTextClear("cssSelector", custLN);
   		inputText("cssSelector", custFN, Fname);
   		inputText("cssSelector", custLN, Lname);
   		waitOnElement("cssSelector", custEmail);	
   		inputTextClear("cssSelector", custEmail);
   		inputText("cssSelector", custEmail, (Fname+Lname+"@icentris.com"));
   		inputText("cssSelector", custPhone, usaPhone);
   		inputText("cssSelector", custAd1, usaAdd1);
   		inputText("cssSelector", custAd2, usaAdd2);
   		inputText("cssSelector", custCounty, usaCounty);
   		inputText("cssSelector", custCity, usaCounty );
   		stateSelection(usaCounty);		
   		waitOnElement("cssSelector", custZip);	
   		inputText("cssSelector", custZip, "84087");		
   		inputTextClear("cssSelector", custUserName);
   		scrollDown("cssSelector", custUserName);
   		inputText("cssSelector", custUserName, Fname);
   		inputText("cssSelector", custPwd, custPwdText);
   		inputText("cssSelector", custConPwd, custPwdText);	
   		inputTextClear("cssSelector", custFN);	
   		inputTextClear("cssSelector", custLN);
   		scrollDown("cssSelector", custFN);
   		inputText("cssSelector", custFN, Fname);
   		inputText("cssSelector", custLN, Lname);
   		inputTextClear("cssSelector", custEmail);
   		inputText("cssSelector", custEmail, (Fname+Lname+"@icentris.com"));
   		scrollDown("cssSelector", custCreate);
   		clickOnButton("cssSelector", custCreate);
   		selectSuggestedAddress();   				
   		confirmationMessage("Welcome! You have signed up successfully.");   		
       }
    
    public void existingRetailLogin(String shopUser) throws Exception {
    	logInfo("Entered existingUserCredentials() method");      	
		waitOnElement("cssSelector", returnExist);	
		clickOnButton("cssSelector", returnExist);	
		waitOnElement("cssSelector", retailUserName);		
		clickOnButton("cssSelector", returnExist);
		waitOnElement("cssSelector", retailUserName);	
		inputTextClear("cssSelector", retailUserName);
		inputTextClear("cssSelector", retailUserPwd);
		getText("cssSelector", retailUserName, "testts" );
		inputText("cssSelector", retailUserName, shopUser);
		inputText("cssSelector", retailUserPwd, custPwdText);
		inputTextClear("cssSelector", retailUserName);
		inputTextClear("cssSelector", retailUserPwd);
		inputText("cssSelector", retailUserName, shopUser);
		inputText("cssSelector", retailUserPwd, custPwdText);
		submitElement("cssSelector", retailUserPwd);
		Thread.sleep(2000);
		}       
  

    public void pageNavigation(String mainTab, String dropdown) throws Exception{			
    	  logInfo("Entered into pageNavigation() method");    	 
    	  boolean isTabFound = false;
    	  waitOnElement("cssSelector", mainTabs);
    	  scrollDown("cssSelector", mainTabs);
    	  List<WebElement> tabs = driver().findElements(By.cssSelector(mainTabs));
    	  int tabsCount = tabs.size();    	  
    	  for (int i=1; i<=tabsCount;i++) {
    		  waitOnElement("cssSelector", mainTabsBfr+i+mainTabsAft);
    		  WebElement actTab = driver().findElement(By.cssSelector(mainTabsBfr+i+mainTabsAft));
    		  String actTabName =actTab.getText().trim();      		
    		  if(actTabName.equalsIgnoreCase(mainTab)) {
    			isTabFound=true;
    			boolean isDDFound = false;
    			actTab.click();
    			waitOnElement("cssSelector", dropDownTabsBfr+i+dropDownTabsAft);
    			List <WebElement> dpList = driver().findElements(By.cssSelector(dropDownTabsBfr+i+dropDownTabsAft));
    			for(int j=1; j<=dpList.size(); j++) {
    				waitOnElement("cssSelector", dropDownTabsBfr+i+tabsListBfr+j+tabsListAfr);
    				WebElement dp = driver().findElement(By.cssSelector(dropDownTabsBfr+i+tabsListBfr+j+tabsListAfr));
    				String dropDownName = dp.getText().trim();    				
    				if(dropDownName.equalsIgnoreCase(dropdown)) {
    					isDDFound =true;
    					dp.click();     					
    					waitOnSpinner();
    					Thread.sleep(6000);
    					break;
    				}
    				
    					}if(isDDFound==false) {
    						Assert.assertTrue(isDDFound,dropdown+ " dropdown is not found");
    					}
    			break;
    			
    		  }
    	  	}if(isTabFound==false) {
    		  Assert.assertTrue(isTabFound, mainTab+" Main tab is not found");
    	  			} 	
  		}    
      
         public void selectNewFlex() throws Exception {
      	   logInfo("Entered into selectNewFlex() method");
      	   waitOnElement("cssSelector", createflxBtn);      	 
      	   clickOnElement("cssSelector", createflxBtn);      	     
         }
         
         public void alertMsgValidate(String errorMsg) throws Exception {
        	 logInfo("Entered into alertMsgValidate() method");
        	 waitOnElement("cssSelector", emptyAlert);
        	 scrollDown("cssSelector", emptyAlert);
        	 String text = driver().findElement(By.cssSelector(emptyAlert)).getText().trim();
        	 System.out.println(text);
        	 Assert.assertEquals(text, errorMsg);  
        	 waitOnElement("cssSelector", emptyAlert);
        	 
         }            
         
         public void compareDeliveryRate() throws Exception {
        	   logInfo("Entered into selectNewFlex() method");        	   
        	   waitOnElement("xpath", spinner);     	  
          	   waitOnElement("cssSelector", delTitle);     
          	   WebElement tit = driver().findElement(By.cssSelector(delTitle));
          	   String actDelTit=tit.getText().trim();
          	   if (actDelTit.equalsIgnoreCase(delTitleTxt)){
          		   List<WebElement> typ = driver().findElements(By.cssSelector(typesDel));
          		   int noTypes = typ.size();          		  
          		   for(int i=1; i<=noTypes; i++) {          			   
          			   WebElement rate = driver().findElement(By.cssSelector(typesDelRateBfr+i+typesDelRateAfr));
          			   String rated = rate.getText().trim();          	
          			   String[] splitRate= StringUtils.split(rated, "$");
          			   float actRate = Float.parseFloat(splitRate[0]);
          			   System.out.println("actRate "+ actRate);
          			   WebElement radBtn = driver().findElement(By.cssSelector(typesDelRateBfr+i+typesDelRadioAft));
          			   radBtn.click();
          			   waitOnSpinner();
          			   clickOnElement("cssSelector", delTitle);
          			   waitOnElement("xpath", delTotTitle);
          			   String tot =driver().findElement(By.xpath(delTotTitle)).getText().trim();
          			   System.out.println("shipping text us "+i +tot);
          			   if (tot.contains("Shipping total:")){
          				 String tots =driver().findElement(By.xpath(delTot)).getText().trim();
          				 String[] total = StringUtils.split(tots,"$");
          				 float shippingTotal = Float.parseFloat(total[0]);          				
          				 clickOnElement("cssSelector", delTitle);
          				 waitOnElement("cssSelector", shippingNext);  
          				 if(actRate==shippingTotal) {          					
          					 waitOnElement("cssSelector", shippingNext);     
              	     	     clickOnButton("cssSelector", shippingNext);
              	     	     selectNextInCC();
              	     	     String payTot =driver().findElement(By.xpath(delTot)).getText().trim();
           				     String[] payTots = StringUtils.split(payTot,"$");
           				     float paymentShippingTotal = Float.parseFloat(payTots[0]);
           				     System.out.println("paymentShippingTotal"+paymentShippingTotal);
           				     Assert.assertEquals(actRate, paymentShippingTotal);
          				 }else {          				 
          					 Assert.assertEquals(actRate, shippingTotal);          				 
          				 }		
          				   
          			   } waitOnElement("cssSelector", delSectionLnk);
    				     clickOnElement("cssSelector", delSectionLnk);           				     
    				     waitOnElement("xpath", spinner);     	  
              	         waitOnElement("cssSelector", delTitle);               	        
          		   		}        		  
          		
          	  		}else {
          	  			Assert.assertEquals(actDelTit, delTitleTxt);
          	  		}
           }
         
         
        //get list of Flexship per user
        public int getListOfFlexships() {
        	logInfo("Entered into getListOfFlexships() method");
        	List <WebElement> lis = driver().findElements(By.cssSelector(autoList));  
        	int noOfFlexs = lis.size();    
        	System.out.println(noOfFlexs+ " noOfFlexs");
			return noOfFlexs;      	
        	
        }
         
         
         
      // Get AutoShipId and compare it in MyAutoship page and then edit it. 
         public void editFlexship(int expSize) throws Exception{
       	  logInfo("Entered into editFlexship() method");        	  
       	  List <WebElement> lis = driver().findElements(By.cssSelector(autoList));
       	  int actSize = lis.size();
       	  if(actSize > expSize){
       		int i= (actSize*2)-1;  System.out.println(i + "i sis");     		  
       		scrollDown("cssSelector", autoListBfr+i+autoEditAft);  
       		WebElement edit = driver().findElement(By.cssSelector(autoListBfr+i+autoEditAft));
			edit.click();
			String tit = driver().findElement(By.cssSelector(addProd)).getText().trim();       			   
			   String expTitle = "Add Products";       			  
			   if (tit.equalsIgnoreCase(expTitle)){
    			   waitOnElement("cssSelector", addProd);
    			   scrollDown("cssSelector", addProd);
    			   clickOnButton("cssSelector", addProd);
			   }else{
				   Assert.assertEquals(tit, expTitle);
			   }
       		  
       	    }else {
       	    	Assert.assertEquals(actSize, expSize);
       	    }
       	  }	  
         
         
         
         // Get delete flexship if it is more than one
            public void deleteFlexship(int expSize) throws Exception{
          	  logInfo("Entered into editFlexship() method");           	  
	          	  List <WebElement> lis = driver().findElements(By.cssSelector(autoList));
	          	  int actSize = lis.size();
	          	  System.out.println("actSize "+ actSize);
	          	  if(actSize > expSize){
	          	  int i= (actSize*2)-1;  System.out.println(i + "i sis");     		  
	          		scrollDown("cssSelector", autoListBfr+i+felxDeleAft);  
	          		WebElement dele = driver().findElement(By.cssSelector(autoListBfr+i+felxDeleAft));
	   			    System.out.println(dele.getText());   			    
	          		dele.click(); 
	          		confirmOK();
	    	    	confirmationMessage("Flexship is deleted");	          		
	          	     }else {
	          	    	Assert.assertNotEquals(actSize, expSize);
	          	    }
	          	  }	  

         
         public void editFlexship_Original() throws Exception{
          	  logInfo("Entered into editFlexship() method");
          	/*  waitOnElement("cssSelector", autoship_id);
          	  waitOnElement("cssSelector", autoship_id);   
          	  Thread.sleep(4000);
          	  WebElement auto = driver().findElement(By.cssSelector(autoship_id));    	  
          	  String expAutoId=  auto.getText();*/ 
          	  
          	  String expAutoId=  ": 1045234"; 	         	
      		  String[] expAutoSplitted = expAutoId.split(": ");   		    	 
          	  boolean isIdPresent = false;
          	  List <WebElement> lis = driver().findElements(By.cssSelector(autoList));       	  
          	  for (int i =1; i<=lis.size(); i++){
          		  WebElement list= driver().findElement(By.cssSelector(autoListBfr+i+autoListAft));    	  		  
          		  String actAutoId=  list.getText(); 
          		  String[] actAutoSplitted = actAutoId.split("#");       		  
          		   if (actAutoSplitted[1].equalsIgnoreCase(expAutoSplitted[1])){
          			   isIdPresent= true;
          			   WebElement edit = driver().findElement(By.cssSelector(autoListBfr+i+autoEditAft));
          			   edit.click();
          			   String tit = driver().findElement(By.cssSelector(addProd)).getText().trim();       			   
          			   String expTitle = "Add Products";       			  
          			   if (tit.equalsIgnoreCase(expTitle)){
   	       			   waitOnElement("cssSelector", addProd);
   	       			   scrollDown("cssSelector", addProd);
   	       			   clickOnButton("cssSelector", addProd);
          			   }else{
          				   Assert.assertEquals(tit, expTitle);
          			   }
          			   break;   			   
          		   }i++;	  
          		  
          	  } if (isIdPresent==false){
          		  Assert.assertTrue(isIdPresent, expAutoSplitted[1] + "is not present in Flexship List");
          	  }
          	  
            }
         
         // handles to identify survey popwindow and selects radio with comments
         public void flexshipSurvey() throws Exception {
        	 logInfo("Entered into flexshipSurvey() method");  
        	 String expTitle = "Why are you pushing out your FlexShip?";
        	 waitOnElement("cssSelector", flexSurvey);
        	 String sur = driver().findElement(By.cssSelector(flexSurvey)).getAttribute("style");
        	 if (sur.contains("display: block;")) {
        		 waitOnElement("cssSelector", flexSurveyHeader);
        		 String surTitle = driver().findElement(By.cssSelector(flexSurveyHeader)).getText();
        		 if(surTitle.equalsIgnoreCase(expTitle)) {
        			 List<WebElement> allRad = driver().findElements(By.xpath(flexSurveyAllRadios));
        			 int radioSize = allRad.size();
        			 int i= TestBase.generateRandomNumberInRange(1, radioSize);        			        			 
        			 clickOnElement("xpath", flexSurveyRadioBfr+i+flexSurveyRadioAft);
        			 clickOnElement("xpath", flexSurveyRadioBfr+i+flexSurveyRadioAft);
        			 inputText("xpath", flexSurveyComment, flexSurveyCommentText);
        			 submitElement("xpath", flexSurveySubmit);    			
        			 
        		 }else {
        			 Assert.assertEquals(surTitle, expTitle);
        		 }
        	 }else {
        		 System.out.println("survey is not present");
        	 }      	 
        	 
         }
         
         
         
         
         
        
         

	}



	


