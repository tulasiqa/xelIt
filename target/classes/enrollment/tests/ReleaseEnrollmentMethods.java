package vibe.enrollment.tests;

import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import common.TestBase;
import common.EnvProperties;

//Enrollment for branch-1-10.vibeoffice.com environment.

public class ReleaseEnrollmentMethods extends TestBase {
	EnvProperties prop = new EnvProperties();
	
	//String NewWebsite ="g.khanuma"+TestBase.generateRandomNumberInRange(100, 999);
	//String ExisintWebsite="g.khanuma";
	String setFlexshipDate =  getDateByMonth(2, "MM/dd/yyyy");
	 
	// Generic methods
	
	
	public void verifyEnrollmentLayoutInNav() throws ConfigurationException, IOException {
		logInfo("inside verifyEnrollmentLayoutInNav() method.");
		
		String before_mainNav = "//div[@class='container-fluid']/div[@id='navbar-collapsable']/ul[@class='nav navbar-nav']/li[";
		String after_mainNav = "]/a/span[2]";
		
		WebElement monatMainNav = driver().findElement(By.xpath("//div[@class='container-fluid']/div[@id='navbar-collapsable']/ul[@class='nav navbar-nav']"));
		List allMainMenus = monatMainNav.findElements(By.tagName("li"));  // .className("dropdown")
		System.out.println("allMainMenus.size() = " +allMainMenus.size());
		
		for(int i=1;i<=6;i++) {
			
			WebElement m = driver().findElement(By.xpath(before_mainNav+i+after_mainNav));
			//System.out.println("m =" + m.getText().trim());
			
			String before_mainUl = "//div[@class='container-fluid']/div[@id='navbar-collapsable']/ul[@class='nav navbar-nav']/li[";
			String after_mainUl = "]";
			
			String before_subUl = "//div[@class='container-fluid']/div[@id='navbar-collapsable']/ul[@class='nav navbar-nav']/li[";
			String mid_subUl = "]/ul/li[";
			String after_subUl = "]/a/span";
			
			WebElement mainMenuUl = driver().findElement(By.xpath(before_mainUl+i+after_mainUl));
			List allSubmenus = mainMenuUl.findElements(By.tagName("li"));
			if(allSubmenus.size()>0) {
				System.out.println("Main Menu =  > " + m.getText().trim());
				mainMenuUl.click();
				for(int j=1;j<=allSubmenus.size();j++) {
					WebElement submenu =  driver().findElement(By.xpath(before_subUl+i+mid_subUl+j+after_subUl));
					System.out.println("submenu = " +submenu.getText().trim());
					String act_menu = submenu.getText().trim();
					switch(act_menu) {
					case "Enroll MP":
						prop.setLocatorForEnvironment(appUrl, "EnrollPageLayout", "individual");
						//submenu.click();
						break;
					case "Enrollment":
						prop.setLocatorForEnvironment(appUrl, "EnrollPageLayout", "single");
						//submenu.click();
						break;
					}
				}
				mainMenuUl.click();
			}
			
		}
	}
	
	
	public void readLayoutToolsMenu() throws  Exception {
		logInfo("inside readLayoutToolsMenu() method.");
		
		waitOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		String before_menu = "//ul[@class='nav navbar-nav']/li[2]/ul/li[";
		String after_menu = "]/a/span";
		boolean isLinkFound = false;
		clickOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		Thread.sleep(2000);
		WebElement panel = driver().findElement(By.xpath("//ul[@class='nav navbar-nav']/li[2]/ul"));
		List allMenus = panel.findElements(By.tagName("li"));
		if(allMenus.size() >0) {
			for(int i=1; i<=allMenus.size(); i++) {
				WebElement x = driver().findElement(By.xpath(before_menu+i+after_menu));
				String act_menu = x.getText().trim();
				switch(act_menu) {
				case "Enroll MP":
					prop.setLocatorForEnvironment(appUrl, "EnrollPageLayout", "individual");
					break;
				case "Enrollment":
					prop.setLocatorForEnvironment(appUrl, "EnrollPageLayout", "single");
					break;
				}
				
			}
		}
		
		clickOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		
		if(isLinkFound==false) {
			logInfo("Menu options match not found to select");
			// Assert.assertTrue(isLinkFound, menuItem + " menu item match not found to select");
		}
	}
	
	public void selectToolsMenu(String menuItem) throws  Exception {
		logInfo("inside selectToolsMenu() method.");
		
		waitOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		String before_menu = "//ul[@class='nav navbar-nav']/li[2]/ul/li[";
		String after_menu = "]/a/span";
		boolean isLinkFound = false;
		clickOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		Thread.sleep(2000);
		WebElement panel = driver().findElement(By.xpath("//ul[@class='nav navbar-nav']/li[2]/ul"));
		List allMenus = panel.findElements(By.tagName("li"));
		if(allMenus.size() >0) {
			for(int i=1; i<=allMenus.size(); i++) {
				WebElement x = driver().findElement(By.xpath(before_menu+i+after_menu));
				String act_menu = x.getText().trim();
				if(act_menu.equalsIgnoreCase(menuItem)) {
					logInfo(menuItem + " menu item match found to select");
					isLinkFound = true;
					x.click();
					Thread.sleep(5000);
					break;
				}
			}
		}
		
		if(isLinkFound==false) {
			logInfo(menuItem + " menu item match not found to select");
			// Assert.assertTrue(isLinkFound, menuItem + " menu item match not found to select");
		}
	}
	
	
	/*public void readLayoutToolsMenu() throws  Exception {
		logInfo("inside readLayoutToolsMenu() method.");
		
		waitOnElement("xpath","//ul[@class='nav navbar-nav']/li[3]/a/span[2]");
		String before_menu = "//ul[@class='nav navbar-nav']/li[3]/ul/li[";
		String after_menu = "]/a/span";
		boolean isLinkFound = false;
		clickOnElement("xpath","//ul[@class='nav navbar-nav']/li[3]/a/span[2]");
		Thread.sleep(2000);
		WebElement panel = driver().findElement(By.xpath("//ul[@class='nav navbar-nav']/li[3]/ul"));
		List allMenus = panel.findElements(By.tagName("li"));
		if(allMenus.size() >0) {
			for(int i=1; i<=allMenus.size(); i++) {
				WebElement x = driver().findElement(By.xpath(before_menu+i+after_menu));
				String act_menu = x.getText().trim();
				switch(act_menu) {
				case "Enroll MP":
					prop.setLocatorForEnvironment(appUrl, "EnrollPageLayout", "individual");
					break;
				case "Enrollment":
					prop.setLocatorForEnvironment(appUrl, "EnrollPageLayout", "single");
					break;
				}
				
			}
		}
		
		clickOnElement("xpath","//ul[@class='nav navbar-nav']/li[3]/a/span[2]");
		
		if(isLinkFound==false) {
			logInfo("Menu options match not found to select");
			// Assert.assertTrue(isLinkFound, menuItem + " menu item match not found to select");
		}
	}
	
	public void selectToolsMenu(String menuItem) throws  Exception {
		logInfo("inside selectToolsMenu() method.");
		
		waitOnElement("xpath","//ul[@class='nav navbar-nav']/li[3]/a/span[2]");
		String before_menu = "//ul[@class='nav navbar-nav']/li[3]/ul/li[";
		String after_menu = "]/a/span";
		boolean isLinkFound = false;
		clickOnElement("xpath","//ul[@class='nav navbar-nav']/li[3]/a/span[2]");
		Thread.sleep(2000);
		WebElement panel = driver().findElement(By.xpath("//ul[@class='nav navbar-nav']/li[3]/ul"));
		List allMenus = panel.findElements(By.tagName("li"));
		if(allMenus.size() >0) {
			for(int i=1; i<=allMenus.size(); i++) {
				WebElement x = driver().findElement(By.xpath(before_menu+i+after_menu));
				String act_menu = x.getText().trim();
				if(act_menu.equalsIgnoreCase(menuItem)) {
					logInfo(menuItem + " menu item match found to select");
					isLinkFound = true;
					x.click();
					Thread.sleep(5000);
					break;
				}
			}
		}
		
		if(isLinkFound==false) {
			logInfo(menuItem + " menu item match not found to select");
			// Assert.assertTrue(isLinkFound, menuItem + " menu item match not found to select");
		}
	}*/
	
	public void get2EnrollPage(String layout, String enrollType, String market) throws  Exception {
		logInfo("inside get2EnrollPage() method.");
		
		readLayoutToolsMenu();
		//verifyEnrollmentLayoutInNav();
		
		if(layout.equalsIgnoreCase("single")) {
			 //go2EnrollmentPage();
			selectToolsMenu("Enrollment");
			 chooseEnrollmentMarkets(market);
		} else if(layout.equalsIgnoreCase("individual") && enrollType.equalsIgnoreCase("MP")) {
			selectToolsMenu("Enroll MP");
			selectMarket(market);
		} else if(layout.equalsIgnoreCase("individual") && enrollType.equalsIgnoreCase("VIP")) {
			selectToolsMenu("Enroll VIP");
			selectMarket(market);
		} else if(layout.equalsIgnoreCase("individual") && enrollType.equalsIgnoreCase("Retail")) {
			selectToolsMenu("Enroll Retail");
			selectMarket(market);
		} else {
			logInfo("Invalid options selected");
		}
	}
	
	public void validateUSWebsiteName(String enrollType, boolean isWebsiteAlreadyExists) throws  Exception {
		logInfo("inside validateUSWebsiteName() method.");
		
		String websiteName = null;
		int val = TestBase.generateRandomNumberInRange(1, 9999);
		
		switch(enrollType){
		case "MP":
			websiteName = prop.getLocatorForEnvironment(appUrl,"EnrollUSMPWebsite") +val;
			break;
		case "VIP":
			websiteName = prop.getLocatorForEnvironment(appUrl,"EnrollVIPUSWebsite")+val;
			break;	
		case "Retail":
			websiteName = prop.getLocatorForEnvironment(appUrl,"EnrollRetailWebsite")+val;
			break;	
		case "Default":
			websiteName = "";
		}
		
		waitOnElement("cssSelector","#pyr_core_v2_enrollment_username");
		inputTextClear("cssSelector","#pyr_core_v2_enrollment_username");
		inputText("cssSelector","#pyr_core_v2_enrollment_username",websiteName);
		clickOnElement("cssSelector","a#check-username");
		Thread.sleep(5000);
		
		if(isWebsiteAlreadyExists==true) {
			validateTextOnElement("cssSelector", "div.badge.verification.primary-background", "Name Taken");
		} else {
			validateTextOnElement("cssSelector", "div.badge.verification.label-success", "Name Free");
		}
		
		//return false;
	}
	
	
	public void validateUKWebsiteName(String enrollType, boolean isWebsiteAlreadyExists) throws  Exception {
		logInfo("inside validateUKWebsiteName() method.");
		
		String websiteName = null;
		int val = TestBase.generateRandomNumberInRange(1, 9999);
		
		switch(enrollType){
		case "MP":
			websiteName = prop.getLocatorForEnvironment(appUrl,"EnrollUKMPWebsite")+val;
			break;
		case "VIP":
			websiteName = prop.getLocatorForEnvironment(appUrl,"EnrollUKVIPWebsite")+val;
			break;	
		case "Retail":
			websiteName = prop.getLocatorForEnvironment(appUrl,"EnrollRetailWebsite")+val;
			break;		
		}
		
		waitOnElement("cssSelector","#pyr_core_v2_enrollment_username");
		inputTextClear("cssSelector","#pyr_core_v2_enrollment_username");
		inputText("cssSelector","#pyr_core_v2_enrollment_username",websiteName);
		clickOnElement("cssSelector","a#check-username");
		Thread.sleep(5000);
		
		if(isWebsiteAlreadyExists==true) {
			validateTextOnElement("cssSelector", "div.badge.verification.primary-background", "Name Taken");
		} else {
			validateTextOnElement("cssSelector", "div.badge.verification.label-success", "Name Free");
		}
		
		//return false;
	}
	

	public void validateCanadaWebsiteName(String enrollType, boolean isWebsiteAlreadyExists) throws  Exception {
		logInfo("inside validateRetailWebsiteName() method.");
		
		String websiteName = null;
		int val = TestBase.generateRandomNumberInRange(1, 9999);
		
		switch(enrollType){
		case "MP":
			websiteName = "mp-ca-web"+num;
			break;
		case "VIP":
			websiteName = "vip-ca-web"+num;
			break;	
		case "Retail":
			websiteName = "retail-ca-web"+num;
			break;		
		}
		
		waitOnElement("cssSelector","#pyr_core_v2_enrollment_username");
		inputTextClear("cssSelector","#pyr_core_v2_enrollment_username");
		inputText("cssSelector","#pyr_core_v2_enrollment_username",websiteName);
		clickOnElement("cssSelector","a#check-username");
		Thread.sleep(5000);
		
		if(isWebsiteAlreadyExists==true) {
			validateTextOnElement("cssSelector", "div.badge.verification.primary-background", "Name Taken");
		} else {
			validateTextOnElement("cssSelector", "div.badge.verification.label-success", "Name Free");
		}
		
		//return false;
	}
	
	
	/*public void go2EnrollmentPage() throws  Exception {
		logInfo("inside go2EnrollmentPage() method.");
		
		waitOnElement("xpath","//*[@id='navbar-collapsable']/ul[2]/li[2]/a/span[2]");
		clickOnElement("xpath","//*[@id='navbar-collapsable']/ul[2]/li[2]/a/span[2]");
		
		waitOnElement("xpath","//*[@id='navbar-collapsable']/ul[2]/li[2]/ul/li[6]/a/span");
		clickOnElement("xpath","//*[@id='navbar-collapsable']/ul[2]/li[2]/ul/li[6]/a/span");
		Thread.sleep(5000);
		//driver().navigate().refresh();
	}*/
	
	
	public void chooseEnrollmentMarkets(String market) throws  Exception {
		logInfo("inside chooseEnrollmentMarkets() method.");
		
		waitOnElement("xpath","//*[@class='row choose_market_container']/div");
		WebElement e = driver().findElement(By.xpath("//*[@class='row choose_market_container']/div"));
		List allMarkets = e.findElements(By.cssSelector("div.col-md-2.col-xs-6.text-center"));
		boolean isMarketFound = false;
		
		String before_market = "//*[@class='row choose_market_container']/div/div[";
		String after_market = "]/a/h3";
		System.out.println("Total Markets =" +allMarkets.size());
		for(int i=1;i<=allMarkets.size();i++) {
			WebElement emarket = driver().findElement(By.xpath(before_market+i+after_market));
			String act_market = emarket.getText().trim();
			if(act_market.equalsIgnoreCase(market)) {
				logInfo(market + " market match fouund.");
				isMarketFound = true;
				emarket.click();
				break;
			}
		}
	
		if(isMarketFound==false) {
			logInfo(market + " market match not found.");
			Assert.assertTrue(isMarketFound, market + " market match not found.");
		}
		
	}
	
	
	public void addProducts2CartOrFlexWithSKUID(String prodCategory, String SKUName) throws  Exception {
		logInfo("inside addProducts2CartOrFlexWithSKUID() method.");
		System.out.println("inside addProducts2CartOrFlexWithSKUID() method.");
		
		boolean isProductFound = false;
		String before_prodname = "//*[@class='products-table-container']/div[@class='row products_page']/div[";
		String after_prodname = "]/div/div[2]/a";
		
		String before_cart = "//*[@class='products-table-container']/div[@class='row products_page']/div[";
		String after_cart = "]/div/div[3]/div[1]/div[2]/button/span";
		
		String before_flex = "//*[@class='products-table-container']/div[@class='row products_page']/div[";
		String after_flex = "]/div/div[3]/div[2]/div[2]/button/span";
		
		inputTextClear("cssSelector","input#search_keywords");
		selectFromDropdown("cssSelector",selectCategory,"byVisibleText",prodCategory);
		Thread.sleep(5000);
		inputText("cssSelector","input#search_keywords",SKUName);
		Thread.sleep(5000);
		clickOnElement("cssSelector","#search_btn");
		Thread.sleep(20000);
		
		WebElement container = driver().findElement(By.xpath("//*[@class='products-table-container']/div[@class='row products_page']"));  
		List allProds = container.findElements(By.cssSelector("div.col-lg-3.col-md-4.col-sm-6.enrollment-additional-product.product-row"));
		System.out.println("allProds.size() = " +allProds.size());
		
		if(allProds.size()>0) {
			for(int i=2;i<=allProds.size()+1;i++) {
				WebElement e = driver().findElement(By.xpath(before_prodname+i+after_prodname));
				String actProd = e.getText().trim();
				if(actProd.contains(SKUName)) {
					logInfo(SKUName+ " product match found in search results");
					isProductFound=true;
					WebElement cart = driver().findElement(By.xpath(before_cart+i+after_cart));
					cart.click();
					Thread.sleep(5000);
					/*WebElement promodialog = driver().findElement(By.cssSelector("div#modal_popup_with_promo_items > div > div > div > button.close"));
					if(promodialog.isDisplayed()) {
						promodialog.click();
						Thread.sleep(5000);
					} */
					if(prop.getLocatorForEnvironment(appUrl, "EnrollHandlePromoAlert").equalsIgnoreCase("yes")) {
						WebElement promodialog = driver().findElement(By.cssSelector("div#modal_popup_with_promo_items > div > div > div > button.close"));
						if(promodialog.isDisplayed() ) {
							promodialog.click();
							Thread.sleep(5000);
							}
						}
						
						if(prop.getLocatorForEnvironment(appUrl, "EnrollHandleTravelAlert").equalsIgnoreCase("yes")) {
						WebElement travelPromoDialog = driver().findElement(By.xpath("//*[@id='modal_popup_with_travel_promo_items']/div/div[1]/div/button/i"));
						if(travelPromoDialog.isDisplayed()) {
							travelPromoDialog.click();
							Thread.sleep(5000);
						  }
						}
					
					WebElement flex = driver().findElement(By.xpath(before_flex+i+after_flex));
					flex.click();
					Thread.sleep(5000);
					break;
				}
			}
		}
		
		if(isProductFound==false) {
			logInfo(SKUName+ " product match not found in search results");
			Assert.assertTrue(isProductFound, SKUName+ " product match not found in search results");
		}
	}
	
	
	
	public void chkAddationalProducts2Cart(String addProds2Cart) throws  Exception {
		logInfo("inside chkAddationalProducts2Cart() method.");
		System.out.println("inside addAddationalProducts2Cart() method.");
		
		WebElement chkbox = driver().findElement(By.cssSelector("#pyr_core_v2_enrollment_optional_additional_products_for_todays_order"));
		
		System.out.println("chkbox.isSelected() = " +chkbox.isSelected());
		if((chkbox.isSelected()==false) && addProds2Cart.equalsIgnoreCase("yes")) {
			chkbox.click();
		} else if ((chkbox.isSelected()==true) && addProds2Cart.equalsIgnoreCase("no")) {  //EnrollMPUseSameAddressForShipping
			chkbox.click();
		} 
	}
	
	public void chkAddationalProducts2Flex(String addProds2Flex) throws  Exception {
		logInfo("inside chkAddationalProducts2Flex() method.");
		System.out.println("inside addAddationalProducts2Flex() method.");
		
		WebElement chkbox = driver().findElement(By.cssSelector("#pyr_core_v2_enrollment_optional_additional_products_for_flexship"));
		
		System.out.println("chkbox.isSelected() = " +chkbox.isSelected());
		if((chkbox.isSelected()==false) && addProds2Flex.equalsIgnoreCase("yes")) {
			chkbox.click();
		} else if ((chkbox.isSelected()==true) && addProds2Flex.equalsIgnoreCase("no")) {  
			chkbox.click();
		} 
	}
	
// #################################################################################################################################################
	
	// Enroll an Market Partner
		
	public void go2EnrollMarketPartner() throws  Exception {
		logInfo("inside go2EnrollMarketPartner() method.");
		
		waitOnElement("xpath","//*[@id='navbar-collapsable']/ul[2]/li[2]/a/span[2]");
		clickOnElement("xpath","//*[@id='navbar-collapsable']/ul[2]/li[2]/a/span[2]");
		
		clickOnElement("xpath","//span[contains(text(),'Enroll MP')]");
		Thread.sleep(5000);
		//driver().navigate().refresh();
		//Thread.sleep(10000);
	}
	
	
	public void selectMarket(String market) throws  Exception {
		logInfo("inside selectMarket() method.");
		
		boolean isMarketFound = false;
		//driver().navigate().refresh();
		
		waitOnElement("cssSelector","div.row.choose_market_container> div.col-md-12");
		WebElement e = driver().findElement(By.cssSelector("div.row.choose_market_container > div.col-md-12"));
		List<WebElement> allMarkets = e.findElements(By.cssSelector("div.col-md-2.col-xs-6.text-center"));
		String before_market = "div.row.choose_market_container > div.col-md-12  > div:nth-child(";
		String after_market = ") > a > h3";
		
		System.out.println("Market size = " +allMarkets.size());
		
		if(allMarkets.size()>1) {
			for(int i=2;i<=allMarkets.size()+1;i++) {
				WebElement m = driver().findElement(By.cssSelector(before_market+i+after_market));
				String actMarket = m.getText().trim();
				System.out.println("actMarket = " +actMarket);
				if(actMarket.equalsIgnoreCase(market)) {
					logInfo(market + " market found in the enrollment profile");
					isMarketFound = true;
					m.click();
					Thread.sleep(8000);
					break;
				}
			}
		}
		
		if(isMarketFound==false) {
			logInfo(market + " market not found in the enrollment profile");
			Assert.assertTrue(isMarketFound, market + " market not found in the enrollment profile");
		}
	}
	

	
	public void selectMPProductPackSelection(String addProds2Cart, String addProds2Flex) throws  Exception {
		logInfo("inside selectMPProductPackSelection() method.");
	
		WebElement paneProdPack = driver().findElement(By.xpath("//div[@class='starter-kits-table-container']"));
		List<WebElement> allProducts = paneProdPack.findElements(By.cssSelector("div.starter-kit"));
		int total_prods = allProducts.size();
		boolean isProdSelected = false;
		
		String before_prodName = "//div[@class='starter-kits-table-container']/div[";
		String after_prodName = "]/div[@class='row']/div[2]/h3";
		
		String before_select = "//div[@class='starter-kits-table-container']/div[";
		String after_select = "]/div[@class='row']/div[3]/div/a/label";
		
		String before_price = "//div[@class='starter-kits-table-container']/div[";
		String after_price = "]/div[@class='row']/div[3]/h1";
		
		/*if(total_prods > 0){
			for(int i=total_prods;i>=1;i--){
				System.out.println("i =" +i);
				WebElement lbl = driver().findElement(By.xpath(before_select+i+after_select));
				WebElement p = driver().findElement(By.xpath(before_prodName+i+after_prodName));
				String lblstatus = lbl.getText().trim();
				String prod = p.getText().trim();
				System.out.println("prod =" +prod);
					if(i==total_prods && lblstatus.equalsIgnoreCase("Selected")) {
						logInfo(prod + " Highest product is selected.");
						System.out.println(prod +" Highest product is selected.");
						isProdSelected=true;
						break;
					}
				}
		 }
		*/
		
		// chkAddationalProducts2Cart(addProds2Cart);   // removed this option in enrollment
		 chkAddationalProducts2Flex(addProds2Flex);
		
		if(isProdSelected==false) {
			logInfo("Highest product not selected.");
			// Assert.assertTrue(isProdSelected, "Highest product not selected.");
		}
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(10000);

	}
		
	
	public void updateMPPersonalInfo(String market, String essNo, String email) throws  Exception {		// monatEnrollmentStep3
		logInfo("inside updateMarketPartnerPersonalInformation() method.");
		
		waitOnElement("cssSelector", enrollFirstName);
		//selectSponsor(prop.getLocatorForEnvironment(appUrl,"EnrollMPSponsorConsultID"));		// comment for non admin user
		
		inputTextClear("cssSelector", enrollFirstName);
		inputTextClear("cssSelector",enrollLastName);
		inputTextClear("cssSelector", eBirthDay);
		inputTextClear("cssSelector", enrollEmail);
		inputTextClear("cssSelector",eHome);
		inputTextClear("cssSelector", eSSN);
		
		switch(market){
		case "US":
			inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollMPUSFName")+num);
			inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollMPUSLName")+num);
			selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_business_type","byVisibleText",prop.getLocatorForEnvironment(appUrl,"EnrollMPBusinessType"));
			inputText("cssSelector", eSSN, essNo); 
			inputText("cssSelector",enrollEmail, email);
			validateUSWebsiteName("jayadev.m",false);
			validateUSWebsiteName("MP",true);  // prop.getLocatorForEnvironment(appUrl,"EnrollMPWebsite")+num
			break;
		case "CA" :
			inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollMPCAFName")+num);
			inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollMPCALName")+num);
			selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_business_type","byVisibleText",prop.getLocatorForEnvironment(appUrl,"EnrollMPBusinessType"));
			inputText("cssSelector", eSSN, essNo); 
			inputText("cssSelector",enrollEmail,email);
			validateCanadaWebsiteName("jayadev.m",false);
			validateCanadaWebsiteName("MP",true);  // prop.getLocatorForEnvironment(appUrl,"EnrollMPWebsite")+num
			break;
		case "UK" :
			inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollMPUKFName")+num);
			inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollMPUKLName")+num);
			inputText("cssSelector",enrollEmail,email);
			inputText("cssSelector", eSSN, essNo); 
			validateUKWebsiteName("jayadev.m",false);
			validateUKWebsiteName("MP",true);  // prop.getLocatorForEnvironment(appUrl,"EnrollMPWebsite")+num
			break;
		case "default" :
			logInfo("Incorrect market selected");
			break;
		}
		
		inputText("cssSelector", eBirthDay, "12/12/1991");
		inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollMPHome"));
		selectFromDropdown("cssSelector",enrollGender,"byVisibleText","Male");
		
		inputText("cssSelector",enrollPassword,"password1");
		inputText("cssSelector",enrollConfirmPassword,"password1");
	
		clickOnElement("linkText", "Continue");
		Thread.sleep(30000);
	}
	
	
	
	
	
	public void validateMPPersonalInfoWithInvalidData(String market) throws  Exception {		// monatEnrollmentStep3
		
		logInfo("inside validateMPPersonalInfoWithInvalidData() method");
		waitOnElement("cssSelector", enrollFirstName);
		//selectSponsor(prop.getLocatorForEnvironment(appUrl,"EnrollMPSponsorConsultID"));		// comment for non admin user
		
		inputTextClear("cssSelector", enrollFirstName);
		inputTextClear("cssSelector",enrollLastName);
		inputTextClear("cssSelector", eBirthDay);
		inputTextClear("cssSelector", enrollEmail);
		inputTextClear("cssSelector",eHome);
		inputTextClear("cssSelector", eSSN);
		inputTextClear("cssSelector",enrollPassword);
		inputTextClear("cssSelector",enrollConfirmPassword);
		inputTextClear("cssSelector",monatuserName);
		
		switch(market){
		case "US":
			inputText("cssSelector", eSSN, "11546332");
			break;
		case "CA" :
			inputText("cssSelector", eSSN, "11546332");
			validateCanadaWebsiteName("jayadev.m",false);
			validateCanadaWebsiteName("MP",true);  // prop.getLocatorForEnvironment(appUrl,"EnrollMPWebsite")+num
			break;
		case "UK" :
			validateUKWebsiteName("jayadev.m",false);
			validateUKWebsiteName("MP",true);  // prop.getLocatorForEnvironment(appUrl,"EnrollMPWebsite")+num
			break;
		case "default" :
			logInfo("Incorrect market selected");
			break;
		}
		
		
		inputText("cssSelector", eBirthDay, "12/12/2017");
		inputText("cssSelector",enrollEmail,"tempemail@gmail");
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(25000);
		
		// validation
		
		validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(3) > div.col-md-6:nth-child(1) > div > span","Age should be 18+");  // Incorrect Age
		validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(5) > div.col-md-6:nth-child(2) > div > span","Please enter valid tax information."); // Existing SSN
		validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(6) > div.col-md-6:nth-child(1) > div > span","is invalid"); // Email is invalid
		validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(6) > div.col-md-6:nth-child(2) > div > span","Please complete this field."); // Gender not selected
		validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(7) > div.col-md-7 > div:nth-child(2) > span","Please enter a valid user name."); // Username field empty
		validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(8)  > div:nth-child(1) > div > span","Please enter a valid password. and Please complete this field."); // Password field empty
		//validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(6) > div.col-md-6:nth-child(2) > div > span","Please complete this field.");
		//validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(8) > div.col-md-6:nth-child(2) > div > span","doesn't match Password");
	}

	
	public void selectSponsor(String consID) throws IOException, Exception {
		logInfo("inside selectSponsor() method.");
		
		clickOnElement("xpath","//a[contains(text(),'Change Sponsor')]");
		Thread.sleep(5000);
		waitOnElement("cssSelector",enrollConsultID);
		selectFromDropdown("cssSelector",enrollConsultID,"byVisibleText","Consultant  Id");
		inputTextClear("cssSelector",searchSponser);
		inputText("cssSelector",searchSponser,prop.getLocatorForEnvironment(appUrl,"EnrollMPSponsorConsultID"));
		clickOnElement("cssSelector","form.simple_form.consultant_search > span > button");
		Thread.sleep(5000);
		
		String before_id = "table#consultant_search_results > tbody > tr:nth-child(";
		String after_id = ") > td:nth-child(2) > a";
		boolean isConsultantFound = false;
		
		WebElement e = driver().findElement(By.cssSelector("table#consultant_search_results > tbody"));
		List allRows = e.findElements(By.tagName("tr"));
		if(allRows.size()>0) {
			for(int i=1;i<=allRows.size();i++) {
				WebElement id = driver().findElement(By.cssSelector(before_id+i+after_id));
				String actID = id.getText().trim();
				if(actID.equalsIgnoreCase(consID)) {
					logInfo(consID + " consultant id match found in sponsor search results");
					isConsultantFound = true;
					id.click();
					Thread.sleep(4000);
					break;
				}
			}
		}
		
		if(isConsultantFound==false) {
			logInfo(consID + " consultant id match not found in sponsor search results");
			Assert.assertTrue(isConsultantFound, consID + " consultant id match not found in sponsor search results");
		}
	}
	
	
	public void addMarketPartnerBillingInformation() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside addNewMarketPartnerBillingInformation() method.");
		addNewCCDetails();
		addNewBillingAddress();
		//addAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPAddationalProducts"));
		useSameAddressForShipping(prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping"));
		clickOnElement("linkText", "Continue");
		Thread.sleep(6000);
		
		String isShipAndBillSame = prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping");
		if(isShipAndBillSame.equalsIgnoreCase("yes")) {
			selectBillingAddressMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPBillingAddressMode"));
		} else if(isShipAndBillSame.equalsIgnoreCase("no")) {
			selectBillingAndShippingMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl, "EnrollMPShippingAddressMode"));
			//selectBillingAndShipping("Suggested Address","Suggested Address");
		} else {
			logInfo("invalid option selected");
		}
		
		Thread.sleep(4000);
		clickOnElement("linkText", "Continue"); // remove comment later
		Thread.sleep(25000);
	}
	
	
	public void addMarketPartnerAddationalProdsBillingInformation() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside addMarketPartnerAddationalProdsBillingInformation() method.");
		addNewCCDetails();
		addNewBillingAddress();
		useSameAddressForShipping(prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping"));
		addAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPAddationalProducts2"));
		clickOnElement("linkText", "Continue");
		Thread.sleep(6000);
		String isShipAndBillSame = prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping");
		if(isShipAndBillSame.equalsIgnoreCase("yes")) {
			selectBillingAddressMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPBillingAddressMode"));
		} else if(isShipAndBillSame.equalsIgnoreCase("no")) {
			selectBillingAndShippingMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl, "EnrollMPShippingAddressMode"));
		} else {
			logInfo("invalid option selected");
		}
		
		Thread.sleep(6000);
		clickOnElement("linkText", "Continue");
		Thread.sleep(20000);
		
		addVolumeSystem4Add2Cart();
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div:nth-child(2) > div:nth-child(2)","$69.00");
	
		addVolumeSystem4Add2Flex();
		validateTextOnElement("cssSelector","div#autoship_details_in_minicart  > div.autoship-items > div.row > div:nth-child(2) > div:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div#autoship_details_in_minicart  > div.autoship-items > div.row > div:nth-child(2) > div:nth-child(2) > div:nth-child(2)","$69.00");
	
		clickOnElement("linkText", "Continue");
		Thread.sleep(25000);
	}
	
	public void addNewCCDetails() throws  Exception {
		logInfo("insdie addNewCCDetails() method");
		
		clickOnElement("cssSelector","a#new-credit-card-link");		// Add Credit Card link
		waitOnElement("cssSelector",eCardHolder);
		inputText("cssSelector",eCardHolder,"Visa Card");  //Master Card
		inputText("cssSelector",ccNumber,txtVisaCard); //txtMasterCard
		inputText("cssSelector",ccCvv,"123");
		selectFromDropdown("cssSelector",enrollmentCreditcardYear,"byVisibleText","2021");
	}
	
	public void addNewBillingAddress() throws  Exception {
		logInfo("insdie addNewBillingAddress() method");
		
		clickOnElement("xpath","//a[contains(text(),'Add Address')]");		// Add Address link
		waitOnElement("cssSelector",ccAddress);
		inputTextClear("cssSelector",ccAddress);
		inputText("cssSelector",ccAddress,"707 W 700 S");
		inputTextClear("cssSelector",ccCity);
		inputText("cssSelector",ccCity,"Woods Cross");
		selectFromDropdown("cssSelector",ccState,"byVisibleText","Utah");
		inputTextClear("cssSelector",ccZipCode);
		inputText("cssSelector",ccZipCode,"84087");
		selectFromDropdown("cssSelector",ccCountry,"byVisibleText","United States");
	}
	
	
	
	public void addAddationalProducts(String addMoreProds) throws  IOException, Exception {
		logInfo("inside addAddationalProducts() method.");
		
		WebElement chkbox = driver().findElement(By.cssSelector("#pyr_core_v2_enrollment_optional_additional_products"));
																 
		System.out.println("chkbox.isSelected() = " +chkbox.isSelected());
		if((chkbox.isSelected()==false) && addMoreProds.equalsIgnoreCase("yes")) {
			chkbox.click();
		} else if ((chkbox.isSelected()==true) && addMoreProds.equalsIgnoreCase("no")) {
			chkbox.click();
		}
	}
	
	
	public void validateMPAddationalProducts2Cart() throws  IOException, Exception {
		logInfo("inside validateMPAddationalProducts2Cart() method.");
		
		validateVolumeSystem4Add2Cart();
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row:nth-child(2)  > div:nth-child(2) > div:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row:nth-child(2)  > div:nth-child(2) > div:nth-child(2) > div:nth-child(2)","$69.00");
	
		addVolumeSystem4Add2Flex();
		validateTextOnElement("cssSelector","div#autoship_details_in_minicart  > div.autoship-items > div.row > div:nth-child(2) > div:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div#autoship_details_in_minicart  > div.autoship-items > div.row > div:nth-child(2) > div:nth-child(2) > div:nth-child(2)","$69.00");
	
		/*clickOnElement("linkText", "Continue");
		Thread.sleep(10000);*/
	}
	
	public void useSameAddressForShipping(String shipWithSameAddress) throws  Exception {
		logInfo("inside useSameAddressForShipping() method.");
		System.out.println("inside useSameAddressForShipping() method.");
		
		WebElement chkbox = driver().findElement(By.cssSelector("input#ship-same-as-bill"));
		
		System.out.println("chkbox.isSelected() = " +chkbox.isSelected());
		if((chkbox.isSelected()==false) && shipWithSameAddress.equalsIgnoreCase("yes")) {
			chkbox.click();
			Thread.sleep(4000);
		} else if ((chkbox.isSelected()==true) && shipWithSameAddress.equalsIgnoreCase("no")) {  //EnrollMPUseSameAddressForShipping
			chkbox.click();
			Thread.sleep(4000);
			addNewShippingAddressDetails();
		} 
	}
	
	public void addNewShippingAddressDetails() throws  Exception {
		logInfo("inside addNewShippingAddressDetails() method");
		
		Thread.sleep(10000);
		scrollDown("cssSelector",enrollmentShippingAddress1);
		
		inputText("cssSelector",enrollmentShippingAddress1,"4295 Suwanee Mill Dr");
		inputText("cssSelector",enrollmentShippingCity,"Buford");
		selectFromDropdown("cssSelector",enrollmentShippingState,"byVisibleText","Georgia");
		inputText("cssSelector",enrollmentShippingzipcode,"30518");
		selectFromDropdown("cssSelector",enrollmentShippingCountry,"byVisibleText","United States");
	
	}
	

	public void selectBillingAddressMode(String address) throws  Exception {
		logInfo("inside selectBillingAddressMode() method.");
		
		/*// WebElement egivaddr = driver().findElement(By.xpath("//*[@id='squire_address_validation']/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/input"));
		WebElement egivaddr = driver().findElement(By.xpath("//*[@id='squire_address_validation']/div/div/div[2]/div[2]/div/div[1]/div[1]/div[1]/input"));
		WebElement esugaddr = driver().findElement(By.xpath("//*[@id='squire_address_validation']/div/div/div[2]/div[2]/div/div[2]/div/div[1]/input"));
		
		if(address.equalsIgnoreCase("Given") && !egivaddr.isSelected() ) {
			logInfo("clicked Billing Given Address");
			//waitOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/input");
			//clickOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/input");
			egivaddr.click();
			Thread.sleep(2000);
		} else if (address.equalsIgnoreCase("Suggested") && !esugaddr.isSelected() ) {
			logInfo("clicked Billing Suggested Address");
			//waitOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/input");
			//clickOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/input");
			esugaddr.click();
			Thread.sleep(2000);
		} else {
			logInfo("invalid address mode selection");
		}*/
		
		
		clickOnElement("cssSelector","input#address_proceed");
		Thread.sleep(8000);
	}
	
	public void selectBillingAndShippingMode(String billingAddr, String shippingAddr) throws  Exception {
		logInfo("inside selectBillingAndShippingMode() method.");
		
		/*// billing address 
		WebElement ebillgivaddr = driver().findElement(By.xpath("//*[@id='squire_address_validation']/div/div/div[2]/div[2]/div/div[1]/div[1]/div[1]/input"));
		WebElement ebillsugaddr = driver().findElement(By.xpath("//*[@id='squire_address_validation']/div/div/div[2]/div[2]/div/div[2]/div/div[1]/input"));
		
		if(billingAddr.equalsIgnoreCase("Given") && !ebillgivaddr.isSelected() ) {
			logInfo("clicked Billing Given Address");
			//waitOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/input");
			//clickOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/input");
			ebillgivaddr.click();
			Thread.sleep(2000);
		} else if (billingAddr.equalsIgnoreCase("Suggested") && !ebillsugaddr.isSelected() ) {
			logInfo("clicked Billing Suggested Address");
			//waitOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[1]/div/div[2]/div/div[1]/input");
			//clickOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[1]/div/div[2]/div/div[1]/input");
			ebillsugaddr.click();
			Thread.sleep(2000);
		} else {
			logInfo("invalid billing address mode selection");
		}
		
		
		// shipping address
			WebElement eshipgivaddr = driver().findElement(By.xpath("//*[@id='squire_address_validation']/div/div/div[2]/div[2]/div/div[1]/div/div[1]/input"));
			WebElement eshipsugaddr = driver().findElement(By.xpath("//*[@id='squire_address_validation']/div/div/div[2]/div[2]/div/div[2]/div/div[1]/input"));
				
			if(shippingAddr.equalsIgnoreCase("Given") && !eshipgivaddr.isSelected() ) {
				logInfo("clicked Shipping Given Address");
				waitOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[2]/div/div[1]/div/div[1]/input");
				clickOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[2]/div/div[1]/div/div[1]/input");
				Thread.sleep(2000);
			} else if (shippingAddr.equalsIgnoreCase("Suggested") && !eshipsugaddr.isSelected() ) {
				logInfo("clicked Shipping Suggested Address");
				waitOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[2]/div/div[2]/div/div[1]/input");
				clickOnElement("xpath","//*[@id='squire_address_validation']/div/div/div[2]/div[2]/div/div[2]/div/div[1]/input");
				Thread.sleep(2000);
			} else {
				logInfo("invalid billing address mode selection");
			}*/
		
		clickOnElement("cssSelector","input#address_proceed");
		Thread.sleep(8000);
	}
	
	
	public void setFlexshipDate(String flexShipdate) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor)driver();       
	      String dateField = "div.input-group.date.date_picker > input#pyr_core_v2_enrollment_auto_ship_date";
	      implicityWaits(5);        
	      WebElement da = driver().findElement(By.cssSelector(dateField));
	      js.executeScript("document.getElementById('pyr_core_v2_enrollment_auto_ship_date').value= ''");
	      js.executeScript("document.getElementById('pyr_core_v2_enrollment_auto_ship_date').value= '05/05/2018'");
	      js.executeScript("document.getElementById('pyr_core_v2_enrollment_auto_ship_date').value= '05/05/2018'");
	      Thread.sleep(2000);
	}
	
	public void addVolumeSystem4Add2Cart() throws  IOException, Exception {
		logInfo("inside validateVolumeSystem4Add2Cart() method.");
		
				
		boolean isProd1Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName1"));
		if(isProd1Found==true) {
			addProducts2CartOrFlex(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName1"));
		}
		
		boolean isProd2Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName2"));
		if(isProd2Found==true) {
			addProducts2CartOrFlex(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName2"));
		}
		
		boolean isProd3Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName3"));
		if(isProd3Found==true) {
			addProducts2CartOrFlex(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName3"));
		}
		
		confirmToOk();
		Thread.sleep(6000);
		
		try {
			
		
		if(prop.getLocatorForEnvironment(appUrl, "EnrollHandlePromoAlert").equalsIgnoreCase("yes")) {
		WebElement promodialog = driver().findElement(By.cssSelector("div#modal_popup_with_promo_items > div > div > div > button.close"));
		if(promodialog.isDisplayed() ) {
			promodialog.click();
			Thread.sleep(5000);
			}
		}
		
		if(prop.getLocatorForEnvironment(appUrl, "EnrollHandleTravelAlert").equalsIgnoreCase("yes")) {
		WebElement travelPromoDialog = driver().findElement(By.xpath("//*[@id='modal_popup_with_travel_promo_items']/div/div[1]/div/button/i"));
		if(travelPromoDialog.isDisplayed()) {
			travelPromoDialog.click();
			Thread.sleep(5000);
		  }
		}
		
		} catch(Exception e) {
			 e.getMessage();
		}
	}
	
	public void validateVolumeSystem4Add2Cart() throws  IOException, Exception {
		logInfo("inside validateVolumeSystem4Add2Cart() method.");
		
		/*validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(2) > div:nth-child(2)","$69.00");
	*/
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(10000);
	}
	
	public void validateUSMPVolumeSystem4Cart() throws InterruptedException {
		logInfo("inside validateUSMPVolumeSystem4Cart() method.");
		
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(2) > div:nth-child(2)","$69.00");
	
		/*clickOnElement("linkText", "Continue");
		Thread.sleep(10000);*/
	}
	
	public void addVolumeSystem4Add2Flex() throws  IOException, Exception {
		logInfo("inside addVolumeSystem4Add2Flex() method.");
		
		boolean isProd1Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName1"));
		if(isProd1Found==true) {
			addProducts2Flexship(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName1"));
		}
		
		boolean isProd2Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName2"));
		if(isProd2Found==true) {
			addProducts2Flexship(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName2"));
		}
		
		boolean isProd3Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName3"));
		if(isProd3Found==true) {
			addProducts2Flexship(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName3"));
		}
		
		confirmToOk();
		Thread.sleep(6000);
		
	}
	
	
	public void validateUSMPVolumeSystem4Flex() throws InterruptedException {
		logInfo("inside validateUSMPVolumeSystem4Flex() method.");
		/*
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(2) > div:nth-child(2)","$69.00");
	*/
		clickOnElement("linkText", "Continue");
		Thread.sleep(15000);
	}

	public boolean searchAddationalProducts(String prodCategory, String SKUName) throws  Exception {
		logInfo("inside searchAddationalProducts() method.");
		boolean isProductFound = false;
		
		//driver().navigate().refresh();
		//clickOnElement("cssSelector","input#search_keywords");  // comment later 
	
		inputTextClear("cssSelector","input#search_keywords");
		selectFromDropdown("cssSelector",selectCategory,"byVisibleText",prodCategory);
		Thread.sleep(3000);
		inputText("cssSelector","input#search_keywords",SKUName);
		clickOnElement("cssSelector","#search_btn");
		Thread.sleep(20000);
		
		waitOnElement("xpath","//div[@class='row products_page']"); 
		WebElement container = driver().findElement(By.xpath("//div[@class='row products_page']"));  
		List allRows = container.findElements(By.cssSelector("div.col-lg-3.col-md-4.col-sm-6.enrollment-additional-product.product-row"));
		
		String before_xpath = "//div[@class='row products_page']/div[";   // "//div[@class='products-table-container']/div[";
		String after_xpath = "]/div/div[2]/a";  
		
		if(allRows.size()>0) {
			for(int i=2;i<=allRows.size();i++) {
				WebElement prod = driver().findElement(By.xpath(before_xpath+i+after_xpath));
				String act_prod = prod.getText().trim();
				System.out.println("act_prod = " +act_prod);
				if(act_prod.contains(SKUName)) {
					System.out.println(act_prod + " match found.");
					isProductFound=true;
					break;
				}
			}
		}
		
		if(isProductFound==false) {
			logInfo(SKUName + " product not found in search results.");
			Assert.assertTrue(isProductFound, SKUName + " product not found in search results.");
		}
		return isProductFound;
	}
	
	
	public void addProducts2CartOrFlex(String prodCategory, String SKUName) throws  Exception {
		logInfo("inside addProducts2CartOrFlex() method.");
		
		boolean isProductFound = false;
		String before_prodname = "//div[@class='row products_page']/div[";
		String after_prodname = "]/div/div[2]/a";
		
		String before_cart = "//div[@class='row products_page']/div[";
		String after_cart = "]/div/div[3]/div[1]/div[2]/button/span";
		
		waitOnElement("xpath","//div[@class='row products_page']"); 
		WebElement container = driver().findElement(By.xpath("//div[@class='row products_page']"));  
		List allProds = container.findElements(By.cssSelector("div.col-lg-3.col-md-4.col-sm-6.enrollment-additional-product.product-row"));
	
		if(allProds.size()>0) {
			for(int i=2;i<=allProds.size()+1;i++) {
				WebElement e = driver().findElement(By.xpath(before_prodname+i+after_prodname));
				String actProd = e.getText().trim();
				if(actProd.contains(SKUName)) {
					logInfo(SKUName+ " product match found in search results");
					isProductFound=true;
					WebElement cart = driver().findElement(By.xpath(before_cart+i+after_cart));
					cart.click();
					// Thread.sleep(5000);
					Thread.sleep(8000);
					break;
				}
			}
		}
		
		if(isProductFound==false) {
			logInfo(SKUName+ " product match not found in search results");
			Assert.assertTrue(isProductFound, SKUName+ " product match not found in search results");
		}
	}
	
	
	public void addProducts2Flexship(String prodCategory, String SKUName) throws  Exception {
		logInfo("inside addProducts2Flexship() method.");
		
		boolean isProductFound = false;
		
		String before_prodname = "//div[@class='row products_page']/div[";
		String after_prodname = "]/div/div[2]/a";
		
		String before_flex = "//div[@class='row products_page']/div[";
		String after_flex = "]/div/div[3]/div[2]/div[2]/button/span";
		
		waitOnElement("xpath","//div[@class='row products_page']"); 
		WebElement container = driver().findElement(By.xpath("//div[@class='row products_page']")); 
		List allProds = container.findElements(By.cssSelector("div.col-lg-3.col-md-4.col-sm-6.enrollment-additional-product.product-row"));
		
		if(allProds.size()>0) {
			for(int i=2;i<=allProds.size();i++) {
				WebElement e = driver().findElement(By.xpath(before_prodname+i+after_prodname));
				String actProd = e.getText().trim();
				if(actProd.equalsIgnoreCase(SKUName)) {
					logInfo(SKUName+ " product match found in search results");
					isProductFound=true;
					WebElement flex = driver().findElement(By.xpath(before_flex+i+after_flex));
					flex.click();
					Thread.sleep(6000);
					break;
				}
			}
		}
		
		if(isProductFound==false) {
			logInfo(SKUName+ " product match not found in search results");
			Assert.assertTrue(isProductFound, SKUName+ " product match not found in search results");
		}
	}
	
	
	public void submitAndReview4AddationalProdsMarketingPartner(String essNo, String email) throws Exception {
		logInfo("inside submitAndReview4AddationalProdsMarketingPartner() method.");
	
		selectShippingMethod("Ground");
		//selectShippingMethod("Two Day");
		validateMPPersonalInfOnReviewAndSubmit(essNo, email);  
		//validateBillingAndShippingAddressInfo(prop.getLocatorForEnvironment(appUrl,"EnrollMPUseSameAddressForShipping"),prop.getLocatorForEnvironment(appUrl,"EnrollMPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollMPShippingAddressMode"));
		selectToc(true,true);
		scrollDown("linkText", "Submit");
		clickOnElement("linkText", "Submit");
		Thread.sleep(15000);
		driver().navigate().refresh();
		Thread.sleep(10000);
		
	}
	
	public void submitAndReviewMarketingPartner(String essn, String email) throws Exception {
		logInfo("inside submitAndReviewMarketingPartner() method.");
	
		selectShippingMethod("Ground");
		//validateTextOnElement("cssSelector","table.formatted_totals>tbody>tr:nth-child(2)> td:nth-child(2)","$9.00");
		selectShippingMethod("Two Day");
		//validateTextOnElement("cssSelector","table.formatted_totals>tbody>tr:nth-child(2)> td:nth-child(2)","$39.99");
		
		validateMPPersonalInfOnReviewAndSubmit(essn, email);
		//validateBillingAndShippingAddressInfo(prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping"),prop.getLocatorForEnvironment(appUrl,"EnrollMPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollMPShippingAddressMode"));
		selectToc(true,true);
		scrollDown("linkText", "Submit");
		clickOnElement("linkText", "Submit");
		Thread.sleep(15000);	
		driver().navigate().refresh();
		Thread.sleep(10000);
	}
	
	
	
	public void validateMPPersonalInfOnReviewAndSubmit(String essNo, String email) throws  Exception {
		logInfo("inside validatePersonalInfoOnReviewAndSubmit() method.");
		
		// validate personal details 
		//validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[1]/div[1]/div",prop.getLocatorForEnvironment(appUrl,"newsCon1"));  // review-sub-page clearfix
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPUSFName")+num);
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPUSLName")+num);
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[1]/div/span[1]","12/12/1991 12:00 AM");
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPHome"));  // day phone
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPBusinessType"));
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[5]/div[2]/div/span[1]", essNo);  
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[6]/div[1]/div/span[1]", email);
		
		// validate website details 
		
		// validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[7]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPWebsite")+num);
	}
	
	
	public void validateBillingAndShippingAddressInfo(String isShippingSame, String billmode, String shipmode) throws IOException {
		logInfo("inside validateBillingAndShippingAddressInfo() method.");
		
		//String isShippingSame = prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping");
	
		if(isShippingSame.equalsIgnoreCase("yes")) {
			validateBillingInfo(billmode);
		} else if(isShippingSame.equalsIgnoreCase("no")){
			validateShippingAndBillingInfo(billmode,shipmode);
		} else {
			logInfo("incorrect value");
		}
	}
	
	
	public void validateBillingInfo(String billmode) throws IOException {
		logInfo("inside validateBillingInfo() method");
		
		//String billmode = prop.getLocatorForEnvironment(appUrl, "EnrollMPBillingAddressMode");
		if(billmode.equalsIgnoreCase("Given")) {
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(3)","707 W 700 S");
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(5)","Woods Cross - UT - 84087");
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(6)","US");
		} else if(billmode.equalsIgnoreCase("Suggested")) {
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(3)","707 W 700 S");
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(5)","Woods Cross - UT - 84087-1454");
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(6)","US");
		} else {
			logInfo("incorrect billing mode");
		}
	}
	
	public void validateShippingAndBillingInfo(String billmode, String shipmode) throws IOException {
		logInfo("inside validateShippingAndBillingInfo() method");
		
		// validating billing address info
		
		if(billmode.equalsIgnoreCase("Given")) {
			logInfo("validating Billing info for Given Address....");
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(3)","707 W 700 S");
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(5)","Woods Cross - UT - 84087");
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(6)","US");
		} else if(billmode.equalsIgnoreCase("Suggested")) {
			logInfo("validating Billing info for Suggested Address....");
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(3)","707 W 700 S");
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(5)","WOODS CROSS - UT - 84087-1454");
			validateTextOnElement("cssSelector","div.credit-card-details > div.address-block:nth-child(1) > span:nth-child(6)","US");
		} else {
			logInfo("incorrect billing mode");
		}
		
		// validating shipping address info
		if(shipmode.equalsIgnoreCase("Given")) {
			logInfo("validating Shipping info for Given Address....");
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(1)","4295 Suwanee Mill Dr");
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(3)","Buford , GA  30518");
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(4)","US");
		} else if(shipmode.equalsIgnoreCase("Suggested")) {
			logInfo("validating Shipping info for Suggested Address....");
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(1)","4295 SUWANEE MILL DR");
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(3)","BUFORD, GA  30518-9247");
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(4)","US");
		} else {
			logInfo("incorrect shipping mode");
		}
	}
	
	public void selectShippingMethod(String shippingMethod) throws InterruptedException, Exception {
		logInfo("inside selectShippingMethod() method");
		
		switch(shippingMethod) {
		
		case "Ground":
			//selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_shipping_method_1","byVisibleText","Ground");
			selectRadioOrCheckbox("cssSelector","#pyr_core_v2_enrollment_shipping_method_1");
			Thread.sleep(5000);
			//validateTextOnElement("cssSelector","table.formatted_totals>tbody>tr:nth-child(2)> td:nth-child(2)","$9.00");
			//Thread.sleep(5000);
			break;
		case "Two Day":
			//selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_shipping_method_3","byVisibleText","Two Day");
			selectRadioOrCheckbox("cssSelector","#pyr_core_v2_enrollment_shipping_method_3");
			Thread.sleep(5000);
			//validateTextOnElement("cssSelector","table.formatted_totals>tbody>tr:nth-child(2)> td:nth-child(2)","$39.99");
			//Thread.sleep(5000);
			break;
		}
	}
	
	
		public void selectToc(boolean MPAgreement, boolean MPDownloadPolicies) throws  Exception {
			logInfo("inside selectToc() method");
			Thread.sleep(3000);
			waitOnElement("cssSelector","#pyr_core_v2_enrollment_tac2");
			WebElement ag = driver().findElement(By.cssSelector("#pyr_core_v2_enrollment_tac2"));
			if(!ag.isSelected() && MPAgreement==true ) {
				clickOnElement("cssSelector","#pyr_core_v2_enrollment_tac2");
				Thread.sleep(3000);
			}
				
			WebElement pol = driver().findElement(By.cssSelector("#pyr_core_v2_enrollment_toc_acceptance"));
			if(!pol.isSelected() && MPDownloadPolicies==true ) {
				clickOnElement("cssSelector","#pyr_core_v2_enrollment_toc_acceptance");
				Thread.sleep(3000);
			}
		}
		
		
		public boolean verifyMPEnrollmentIsSuccessful() throws  Exception {
			logInfo("inside verifyMPEnrollmentIsSuccessful() method.");
			waitOnElement("cssSelector", "#main-content");
			WebElement eactMsg = driver().findElement(org.openqa.selenium.By.cssSelector("#main-content"));
			String actMsg = eactMsg.getText().trim();
			System.out.println("actEnrollMsg =" + actMsg);
			System.out.println("expEnrollMsg =" + expEnrollMsg);
			boolean isTextFound = validateTextPresentInPage("cssSelector", "#main-content", expEnrollMsg);
			return isTextFound;
		}
		
		// MP with Addational Products
		
		public void submitAndReviewMPWithAddationalProducts(String essn, String email) throws Exception {
			logInfo("inside submitAndReviewMPWithAddationalProducts() method.");
			
			// validate Initial Order - Volume System
			
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(2)>td:nth-child(1)","10140000 - VOLUME SYSTEM");
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(2)>td:nth-child(2)","$69.00");
			
			// Your Initial Order Shipping Method
			
			selectRadioOrCheckbox("xpath","//input[@id='pyr_core_v2_enrollment_shipping_method_1']");
			Thread.sleep(5000);
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tfoot>tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2)","$0.00"); // Ground Shipping Total
						
			selectRadioOrCheckbox("xpath","//*[@id='pyr_core_v2_enrollment_shipping_method_3']");
			Thread.sleep(10000);
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tfoot>tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2)","$39.99"); // Ground Shipping Total
			
						
			// Your Flex Order
						
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tbody>tr:nth-child(1)>td:nth-child(1)","10140000 - VOLUME SYSTEM");
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tbody>tr:nth-child(1)>td:nth-child(2)","$69.00");
		//	validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tbody>tr:nth-child(1)>td:nth-child(4)","$69.00");
						
			// Your Flex Order Shipping Method
						
			selectRadioOrCheckbox("xpath","//*[@id='pyr_core_v2_enrollment_auto_shipping_method_1']");
			Thread.sleep(5000);
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tfoot>tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2)","$0.00"); // Ground Shipping Total
								
			validateMPReviewPageStarterKitInfo();
			validateMPPersonalInfOnReviewAndSubmit(essn, email);
			validateBillingAndShippingAddressInfo(prop.getLocatorForEnvironment(appUrl,"EnrollMPUseSameAddressForShipping"),prop.getLocatorForEnvironment(appUrl,"EnrollMPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollMPShippingAddressMode"));
			selectToc(true,true);
			scrollDown("linkText", "Submit");
			clickOnElement("linkText", "Submit");
			Thread.sleep(30000);	// uncomment later
		}
		
		public void validateMPReviewPageStarterKitInfo() throws  Exception {
			logInfo("validateMPReviewPageStarterKitInfo");
			
			// Your Starter Kit
			
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.starter-kit-order>tbody>tr>td:nth-child(1)","OVERACHVPC - OVERACHIEVER PRODUCT PACK");
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.starter-kit-order>tbody>tr>td:nth-child(2)","$599.00");
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.starter-kit-order>tbody>tr>td:nth-child(4)","$599.00");
			
			// Your Initial Order
			
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(1)>td:nth-child(1)","10140000 - VOLUME SYSTEM");
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(1)>td:nth-child(2)","$69.00");
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(1)>td:nth-child(4)","$69.00");
			
			// Your Initial Order Shipping Method
			
			selectRadioOrCheckbox("xpath","//input[@id='pyr_core_v2_enrollment_shipping_method_1']");
			Thread.sleep(5000);
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tfoot>tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2)","$0.00"); // Ground Shipping Total
			
			selectRadioOrCheckbox("xpath","//*[@id='pyr_core_v2_enrollment_shipping_method_3']");
			Thread.sleep(5000);
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tfoot>tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2)","$39.99"); // Ground Shipping Total
			
			
			// Your Flex Order
			
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tbody>tr:nth-child(1)>td:nth-child(1)","10140000 - VOLUME SYSTEM");
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tbody>tr:nth-child(1)>td:nth-child(2)","$69.00");
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tbody>tr:nth-child(1)>td:nth-child(4)","$69.00");
			
			// Your Flex Order Shipping Method
			
			selectRadioOrCheckbox("xpath","//*[@id='pyr_core_v2_enrollment_auto_shipping_method_1']");
			Thread.sleep(5000);
			validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tfoot>tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2)","$0.00"); // Ground Shipping Total
					
		}
		
	
// ###########################################################################################################	
	
	
	// Enroll an VIP User
		
	
	  public void validateVIPProductsPageWithEmptyProducts() throws Exception {
			logInfo("inside validateVIPProductsPageWithEmptyProducts() method.");
			
			clickOnElement("linkText", "Continue");
			Thread.sleep(10000);
			validateTextOnElement("cssSelector","div.alert.alert-danger>ul>li>span>b","Oops! Your Today’s Order and Flexship total must EACH be at least $84 USD / $110 CAD.");
			Thread.sleep(5000);
	  }	
	  
	  
	  public void validateVIPVolumeSystem2Cart4USMarket() throws InterruptedException {
			logInfo("inside validateVIPVolumeSystem2Cart4USMarket() method.");
			
			/*validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row:nth-child(2)  > div > div.row:nth-child(1) > div","VOLUME SYSTEM");
			validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row:nth-child(2)  > div > div.row:nth-child(2) > div:nth-child(2)","$84.00");
		  */
			clickOnElement("linkText", "Continue");
			Thread.sleep(15000);
	  }
		
		public void validateVIPVolumeSystem4Add2Flex() throws  IOException, Exception {
			logInfo("inside validateVIPVolumeSystem4Add2Flex() method.");
			
			boolean isProd1Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdSKUName1"));
			if(isProd1Found==true) {
				addProducts2CartOrFlex(prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdSKUName1"));
			}
			
			boolean isProd2Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdSKUName2"));
			if(isProd2Found==true) {
				addProducts2CartOrFlex(prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdSKUName2"));
			}
			
			boolean isProd3Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdSKUName3"));
			if(isProd3Found==true) {
				addProducts2CartOrFlex(prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdSKUName3"));
			}
			
			confirmToOk();
			Thread.sleep(6000);
			
			if(prop.getLocatorForEnvironment(appUrl, "EnrollHandlePromoAlert").equalsIgnoreCase("yes")) {
				WebElement promodialog = driver().findElement(By.cssSelector("div#modal_popup_with_promo_items > div > div > div > button.close"));
				if(promodialog.isDisplayed() ) {
					promodialog.click();
					Thread.sleep(5000);
					}
				}
				
				if(prop.getLocatorForEnvironment(appUrl, "EnrollHandleTravelAlert").equalsIgnoreCase("yes")) {
				WebElement travelPromoDialog = driver().findElement(By.xpath("//*[@id='modal_popup_with_travel_promo_items']/div/div[1]/div/button/i"));
				if(travelPromoDialog.isDisplayed()) {
					travelPromoDialog.click();
					Thread.sleep(5000);
				  }
				}
			
			
			/*validateTextOnElement("cssSelector","div.enrollment-cart-container > div#autoship_details_in_minicart > div.autoship-items > div.row > div:nth-child(2) > div:nth-child(1) > div","VOLUME SYSTEM");
			validateTextOnElement("cssSelector","div.enrollment-cart-container > div#autoship_details_in_minicart > div.autoship-items > div.row > div:nth-child(2) > div:nth-child(2) > div:nth-child(2)","$84.00");
		
			clickOnElement("linkText", "Continue");
			Thread.sleep(10000);*/
		}
		
	public void validateVIPFlexVolumeSystem4USMarket() throws InterruptedException {
		logInfo("inside validateVIPFlexVolumeSystem4USMarket() method.");
		
		/*validateTextOnElement("cssSelector","div.enrollment-cart-container > div#autoship_details_in_minicart > div.autoship-items > div.row > div:nth-child(2) > div:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div#autoship_details_in_minicart > div.autoship-items > div.row > div:nth-child(2) > div:nth-child(2) > div:nth-child(2)","$84.00");
	   */
		clickOnElement("linkText", "Continue");
		Thread.sleep(10000);
	}
		
	
	/*public void submitVIPPersonalInfo() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside submitVIPPersonalInfo() method.");
		
		waitOnElement("cssSelector", enrollFirstName);
	//	selectSponsor(prop.getLocatorForEnvironment(appUrl,"newsFN1")); // when login as user
		inputTextClear("cssSelector", enrollFirstName);
		inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollVIPFName")+num);
		inputTextClear("cssSelector",enrollLastName);
		inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollVIPLName")+num);
		inputTextClear("cssSelector", eBirthDay);
		inputText("cssSelector", eBirthDay, "12/12/1991");
		inputTextClear("cssSelector", eHome);
		inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollVIPHome"));
		inputTextClear("cssSelector", eSSN);
		inputText("cssSelector", eSSN, prop.getLocatorForEnvironment(appUrl,"EnrollVIPEssn") + num);
		inputTextClear("cssSelector", enrollEmail);
		inputText("cssSelector",enrollEmail,"mntvip"+num+"@icentris.com");
		selectFromDropdown("cssSelector",enrollGender,"byVisibleText","Male");
		//inputTextClear("cssSelector",monatuserName);
		//inputText("cssSelector",monatuserName,"monatvip"+num);
		validateUSWebsiteName("jayadev.m",false);
		validateUSWebsiteName("VIP",true);  // prop.getLocatorForEnvironment(appUrl,"EnrollMPWebsite")+num
		
		inputTextClear("cssSelector", enrollPassword);
		inputText("cssSelector",enrollPassword,"password1");
		inputTextClear("cssSelector",enrollConfirmPassword);
		inputText("cssSelector",enrollConfirmPassword,"password1");
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(10000);
	}
		*/
	
	
	public void submitVIPPersonalInfo(String market, String email) throws  Exception {		// monatEnrollmentStep3
		logInfo("inside submitVIPPersonalInfo() method.");
		
		waitOnElement("cssSelector", enrollFirstName);
	//	selectSponsor(prop.getLocatorForEnvironment(appUrl,"newsFN1")); // when login as user
		inputTextClear("cssSelector", enrollFirstName);
		inputTextClear("cssSelector",enrollLastName);
		inputTextClear("cssSelector", eBirthDay);
		inputTextClear("cssSelector", eHome);
		inputTextClear("cssSelector", enrollEmail);
		inputTextClear("cssSelector", eUserName);
		
		switch(market) {
			case "US":
				inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollVIPUSFName")+num);
				inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollVIPUSLName")+num);
				inputText("cssSelector", eBirthDay, "12/12/1991");
				inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollVIPHome"));
				inputText("cssSelector",enrollEmail,email);
				selectFromDropdown("cssSelector",enrollGender,"byVisibleText","Male");
				validateUSWebsiteName("jayadev.m",false);
				validateUSWebsiteName("VIP",true);  // prop.getLocatorForEnvironment(appUrl,"EnrollMPWebsite")+num
				break;
			case "CA":
				inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollVIPCAFName")+num);
				inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollVIPCALName")+num);
				inputText("cssSelector", eBirthDay, "12/12/1991");
				inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollVIPHome"));
				inputText("cssSelector",enrollEmail,email);
				selectFromDropdown("cssSelector",enrollGender,"byVisibleText","Male");
				inputText("cssSelector", eUserName, prop.getLocatorForEnvironment(appUrl,"EnrollVIPCAUserName")+num);
				break;		
			}
		
		inputTextClear("cssSelector", enrollPassword);
		inputText("cssSelector",enrollPassword,"password1");
		inputTextClear("cssSelector",enrollConfirmPassword);
		inputText("cssSelector",enrollConfirmPassword,"password1");
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(10000);
	}
		
	
	public void validateVIPPersonalInfoWithInvalidData() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside validateVIPPersonalInfoWithInvalidData() method.");
		
		waitOnElement("cssSelector", enrollFirstName);
		// selectSponsor(prop.getLocatorForEnvironment(appUrl,"EnrollVIPSponsorConsultID")); // when login as user
		inputTextClear("cssSelector", enrollFirstName);
		inputTextClear("cssSelector",enrollLastName);
		inputTextClear("cssSelector", eBirthDay);
		inputText("cssSelector", eBirthDay, "12/12/2017");
		inputTextClear("cssSelector", eHome);
		inputTextClear("cssSelector",enrollEmail);
		inputText("cssSelector",enrollEmail,"testmail@gmail");
		inputTextClear("cssSelector",monatuserName);
		inputTextClear("cssSelector",enrollPassword);
		inputTextClear("cssSelector",enrollConfirmPassword);
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(10000);
		
		// validation
		
		validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(3) > div.col-md-6:nth-child(1) > div > span","Age should be 18+");  // Age less than 18
		validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(4) > div.col-md-6:nth-child(2) > div > span","is invalid"); // Email is invalid
		validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(5) > div.col-md-6:nth-child(1) > div > span","Please complete this field."); // Gender not selected
		validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(6) > div.col-md-7 > div:nth-child(2) > span","Please enter a valid user name."); // Username field empty
		validateTextOnElement("cssSelector","form > section> div.row > div:nth-child(1) > div > div:nth-child(7) > div.col-md-6:nth-child(1) > div  > span","Please enter a valid password. and Please complete this field."); // Password field empty

	}

	
	
	public void selectVIPProducts2Cart() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside selectVIPProducts2Cart() method.");
		validateTextOnElement("cssSelector","div.order-items >  div.row:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div","VIP Customer Registration");
		validateTextOnElement("cssSelector","div.order-items >  div.row:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2)","$19.99");
		searchVIPProducts("All Products",prop.getLocatorForEnvironment(appUrl,"EnrollVIPProdSKUName"));
		addVIPProduct2Cart("All Products",prop.getLocatorForEnvironment(appUrl,"EnrollVIPProdSKUName"));
		addVIPProduct2Flexship("All Products",prop.getLocatorForEnvironment(appUrl, "EnrollVIPProdSKUName"));
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(20000);
	}
	
	
	public void addVIPAddationalProducts2Cart() throws  IOException, Exception {
		logInfo("inside addVIPAddationalProducts2Cart() method.");
		
		validateTextOnElement("cssSelector","div.order-items >  div.row:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div","VIP Customer Registration");
		validateTextOnElement("cssSelector","div.order-items >  div.row:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2)","$19.99");
		
		validateVolumeSystem4Add2Cart();
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row:nth-child(2)  > div:nth-child(2) > div:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row:nth-child(2)  > div:nth-child(2) > div:nth-child(2) > div:nth-child(2)","$84.00");
	
		addVolumeSystem4Add2Flex();
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div > div.order-items > div.row:nth-child(2) > div:nth-child(2) > div:nth-child(1)> div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div > div.order-items > div.row:nth-child(2) > div:nth-child(2) > div:nth-child(2)> div:nth-child(2)","$84.00");
	
		clickOnElement("linkText", "Continue");
		Thread.sleep(20000);
	}
	
	public boolean searchVIPProducts(String categoryName, String prodName) throws  Exception {
		logInfo("inside searchVIPProducts() method.");
		
		boolean isProductFound = false;
		waitOnElement("cssSelector",selectCategory);
		selectFromDropdown("cssSelector",selectCategory,"byVisibleText",categoryName);
		inputTextClear("cssSelector","input#search_keywords");
		inputText("cssSelector","input#search_keywords",prodName);
		clickOnElement("cssSelector","button#search_btn");
		Thread.sleep(10000);
		waitOnElement("cssSelector","div.row.products_page");
		WebElement resultsContainer = driver().findElement(By.cssSelector("div.row.products_page"));
		List all_skuprods = resultsContainer.findElements(By.cssSelector("div.enrollment-additional-product.product-row"));
		String before_prod = "//div[@class='row products_page']/div[1]/div[2]/div[";
		String after_prod = "]/div/div[2]/a";
		
		System.out.println("Total SKU or Products after search = " + all_skuprods.size());
		
		if(all_skuprods.size()>=1){
			for(int i=2;i<=all_skuprods.size()+1;i++){
				logInfo(prodName + " product or SKU match found in search results");
				isProductFound = true;
				break;
			}
		}
		
		if(isProductFound==false){
			logInfo(prodName + " product or SKU mach not found in search results");
			Assert.assertTrue(isProductFound, prodName + " product or SKU mach not found in search results");
		}
		return isProductFound;
	}
	
	
	public boolean addVIPProduct2Cart(String prodSelection, String prodSkuName) throws Exception{
		logInfo("inside addVIPProduct2Cart() method");
		
		boolean isProdMatchFound = false;
		waitOnElement("cssSelector","div.products-table-container > div.row.products_page");
		
		WebElement resultsContainer = driver().findElement(By.cssSelector("div.products-table-container > div.row.products_page"));
		List all_skuprods = resultsContainer.findElements(By.cssSelector("div.enrollment-additional-product"));
		
		String before_skuName = "//div[@class='products-table-container']/div[@class='row products_page']/div[";
		String after_skuName = "]/div/div[2]/a";
		String before_add2Cart = "//div[@class='products-table-container']/div[@class='row products_page']/div[";
		String after_add2Cart = "]/div/div[3]/div[1]/div[2]/button/span";  
		
		if(all_skuprods.size()>=1){
			for(int i=2;i<=all_skuprods.size()+1;i++){
			logInfo(prodSkuName + " product or SKU mach found in search results");
			scrollDown("xpath",before_add2Cart+i+after_add2Cart);
			WebElement prod = driver().findElement(By.xpath(before_skuName+i+after_skuName));
			String actProd = prod.getText().trim();
			System.out.println("actProd = " +actProd);
			if(actProd.equalsIgnoreCase(prodSkuName)) {
				isProdMatchFound = true;
				clickOnElement("xpath",before_add2Cart+i+after_add2Cart);
				logInfo(prodSkuName + " product clicked for cart");
				Thread.sleep(4000);
				break;
			}
		   }
		}
			
		if(isProdMatchFound==false){
			logInfo(prodSkuName + " product or SKU mach not found in search results");
			Assert.assertTrue(isProdMatchFound, prodSkuName + " product or SKU mach not found in search results");
		}
		
		return isProdMatchFound;
	}
	
	
	public boolean addVIPProduct2Flexship(String prodSelection, String prodSkuName) throws Exception{
		logInfo("inside addVIPProduct2Flexship() method");
		
		boolean isProdMatchFound = false;
		waitOnElement("cssSelector","div.products-table-container > div.row.products_page");
		
		WebElement resultsContainer = driver().findElement(By.cssSelector("div.products-table-container > div.row.products_page"));
		List all_skuprods = resultsContainer.findElements(By.cssSelector("div.enrollment-additional-product"));
		
		String before_skuName = "//div[@class='products-table-container']/div[@class='row products_page']/div[";
		String after_skuName = "]/div/div[2]/a";
		String before_add2Flex = "//div[@class='products-table-container']/div[@class='row products_page']/div[";
		String after_add2Flex = "]/div/div[3]/div[2]/div[2]/button/span";  
		
		if(all_skuprods.size()>=1){
			for(int i=2;i<=all_skuprods.size()+1;i++){
			logInfo(prodSkuName + " product or SKU mach found in search results");
			scrollDown("xpath",before_add2Flex+i+after_add2Flex);
			WebElement prod = driver().findElement(By.xpath(before_skuName+i+after_skuName));
			String actProd = prod.getText().trim();
			System.out.println("actProd = " +actProd);
			if(actProd.equalsIgnoreCase(prodSkuName)) {
				isProdMatchFound = true;
				clickOnElement("xpath",before_add2Flex+i+after_add2Flex);
				logInfo(prodSkuName + " product clicked for cart");
				Thread.sleep(4000);
				break;
			}
		   }
		}
			
			
		if(isProdMatchFound==false){
			logInfo(prodSkuName + " product or SKU mach not found in search results");
			Assert.assertTrue(isProdMatchFound, prodSkuName + " product or SKU mach not found in search results");
		}
		
		return isProdMatchFound;
	}
	
	
	public void addNewVIPBillingInformation() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside addNewVIPBillingInformation() method.");
		addNewCCDetails();
		addNewBillingAddress();
		useSameAddressForShipping(prop.getLocatorForEnvironment(appUrl, "EnrollVIPUseAddressForShipping"));
		clickOnElement("linkText", "Continue");
		Thread.sleep(4000);
		
		String isShipAndBillSame = prop.getLocatorForEnvironment(appUrl, "EnrollVIPUseAddressForShipping");
		if(isShipAndBillSame.equalsIgnoreCase("yes")) {
			selectBillingAddressMode(prop.getLocatorForEnvironment(appUrl,"EnrollVIPBillingAddressMode"));
		} else {
			selectBillingAndShippingMode(prop.getLocatorForEnvironment(appUrl,"EnrollVIPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollVIPShippingAddressMode"));
		}
		clickOnElement("linkText", "Continue");
		Thread.sleep(15000);
	}
	
	
	// 
	public void reviewAndSubmitVIP(String market, String email) throws Exception {
		logInfo("inside reviewAndSubmitVIP() method.");
	
		validateVIPInitialOrderReviewAndSubmit();
		validateVIPPersonalInfoReviewAndSubmit(market, email);
	
		//validateBillingAndShippingAddressInfo(prop.getLocatorForEnvironment(appUrl,"EnrollVIPUseAddressForShipping"),prop.getLocatorForEnvironment(appUrl,"EnrollVIPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollVIPShippingAddressMode"));
		
		selectToc(true,true);
		
		scrollDown("xpath", "//a[contains(text(),'Submit')]");
		clickOnElement("xpath", "//a[contains(text(),'Submit')]");
		Thread.sleep(15000);
		driver().navigate().refresh();
		Thread.sleep(15000);
	}
	
	
	public void submitAndReviewVIPWithAddationalProducts() throws Exception {
		logInfo("inside submitAndReviewVIPWithAddationalProducts() method.");
		
		// validate VIP Registration fees
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(1)>td:nth-child(2)","$19.99");
		
		// validate Initial Order - Volume System
		
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(2)>td:nth-child(1)","10140000 - VOLUME SYSTEM");
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(2)>td:nth-child(2)","$84.00");
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(2)>td:nth-child(4)","$84.00");
		
		// Your Initial Order Shipping Method
		
		selectRadioOrCheckbox("xpath","//input[@id='pyr_core_v2_enrollment_shipping_method_1']");
		Thread.sleep(5000);
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tfoot>tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2)","$0.00"); // Ground Shipping Total
					
		/*selectRadioOrCheckbox("xpath","//*[@id='pyr_core_v2_enrollment_shipping_method_3']");
		Thread.sleep(5000);
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tfoot>tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2)","$39.99"); // Ground Shipping Total
		*/
					
		// Your Flex Order
					
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tbody>tr:nth-child(1)>td:nth-child(1)","10140000 - VOLUME SYSTEM");
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tbody>tr:nth-child(1)>td:nth-child(2)","$84.00");
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tbody>tr:nth-child(1)>td:nth-child(4)","$84.00");
					
		// Your Flex Order Shipping Method
					
		selectRadioOrCheckbox("xpath","//*[@id='pyr_core_v2_enrollment_auto_shipping_method_1']");
		Thread.sleep(5000);
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.auto-ship-order>tfoot>tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2)","$0.00"); // Ground Shipping Total
							
		
		validateVIPPersonalInfOnReviewAndSubmit();
		validateBillingAndShippingAddressInfo(prop.getLocatorForEnvironment(appUrl,"EnrollVIPUseAddressForShipping"),prop.getLocatorForEnvironment(appUrl,"EnrollVIPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollVIPShippingAddressMode"));
		
		scrollDown("xpath", "//a[contains(text(),'Submit')]");
		clickOnElement("xpath", "//a[contains(text(),'Submit')]");
		Thread.sleep(30000);
	}
	
	public void validateVIPInitialOrderReviewAndSubmit() {
		logInfo("inside validateVIPInitialOrderReviewAndSubmit() method.");
		
		// validate vip registration fees
		//validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(1)>td:nth-child(2)","$19.99");
		
	}
	
	public void validateVIPPersonalInfOnReviewAndSubmit() throws  Exception {
		logInfo("inside validateVIPPersonalInfOnReviewAndSubmit() method.");
		
		// validate personal details 
		//validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[1]/div[1]/div",prop.getLocatorForEnvironment(appUrl,"newsCon1"));  // review-sub-page clearfix
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPFName")+num);
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPLName")+num);
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[1]/div/span[1]","12/12/1991 12:00 AM");
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPHome"));  // day phone
		//validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[5]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPEssn")+num);
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPEmail")+num+"@icentris.com");
		
		// validate website details 
		
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[6]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollUSVIPWebsite")+num);  // EnrollVIPWebsite
	}
	
// ###########################################################################################################	
	
	// Enroll Retail User
	
	/*public void updateUSRetailPersonalInformation() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside updateRetialPersonalInformation() method.");
		waitOnElement("cssSelector", enrollFirstName);
	//	selectSponsor(prop.getLocatorForEnvironment(appUrl,"EnrollRetailSponsorConsultID"));
		inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollRetailFName")+num);
		inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollRetailLName")+num);
	//	inputText("cssSelector", eCell,"12345678");
		inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollRetailHome"));
		inputText("cssSelector", eBirthDay, "12/12/1991");
		inputText("cssSelector",enrollEmail, prop.getLocatorForEnvironment(appUrl,"EnrollRetailEmail")+num+"@icentris.com");
		
		inputText("cssSelector", eSt1, "707 W 700 S");
		inputText("cssSelector", eCity, "Woods Cross");
		selectFromDropdown("cssSelector",eState,"byVisibleText","Utah");
		inputText("cssSelector", ePost, "84087");
		selectFromDropdown("cssSelector",eCountry,"byVisibleText","United States");
		
		inputText("cssSelector", eSt1, "4295 Suwanee Mill Dr");
		inputText("cssSelector", eCity, "Buford");
		selectFromDropdown("cssSelector",eState,"byVisibleText","Georgia");
		inputText("cssSelector", ePost, "30518");
		selectFromDropdown("cssSelector",eCountry,"byVisibleText","United States");
		
		inputText("cssSelector",monatuserName,"monatusretail"+num);
		inputText("cssSelector",enrollPassword,"password1");
		inputText("cssSelector",enrollConfirmPassword,"password1");
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(20000);
	}
	*/
	
	public void updateRetailPersonalInformation(String market, String email) throws  Exception {		// monatEnrollmentStep3
		logInfo("inside updateRetialPersonalInformation() method.");
		waitOnElement("cssSelector", enrollFirstName);
	//	selectSponsor(prop.getLocatorForEnvironment(appUrl,"EnrollRetailSponsorConsultID"));
		
		switch(market){
		case "US" :
			inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollRetUSFName")+num);
			inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollRetUSLName")+num);
			inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollRetailHome"));
			inputText("cssSelector", eBirthDay, "12/12/1991");
			inputText("cssSelector",enrollEmail, email );
			inputText("cssSelector", eSt1, "707 W 700 S");
			inputText("cssSelector", eCity, "Woods Cross");
			selectFromDropdown("cssSelector",eState,"byVisibleText","Utah");
			inputText("cssSelector", ePost, "84087");
			selectFromDropdown("cssSelector",eCountry,"byVisibleText","United States");
			inputText("cssSelector",monatuserName,prop.getLocatorForEnvironment(appUrl,"EnrollRetUSUserName")+num);
			break;
		case "CA" :
			inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollRetCAFName")+num);
			inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollRetCALName")+num);
			inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollRetailHome"));
			inputText("cssSelector", eBirthDay, "12/12/1991");
			inputText("cssSelector",enrollEmail, email);
			inputText("cssSelector", eSt1, "234 Laurier Avenue West");
			inputText("cssSelector", eCity, "Ottawa");
			selectFromDropdown("cssSelector",eState,"byVisibleText","Ontario");
			inputText("cssSelector", ePost, "K1A 0G9");
			selectFromDropdown("cssSelector",eCountry,"byVisibleText","Canada");
			inputText("cssSelector",monatuserName,prop.getLocatorForEnvironment(appUrl,"EnrollRetCAUserName")+num);
			
		}
	
		inputText("cssSelector",enrollPassword,"password1");
		inputText("cssSelector",enrollConfirmPassword,"password1");
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(20000);
	}
	
	
	
	public void validateRetailPersonalInfoWithInvalidData() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside validateRetailPersonalInfoWithInvalidData() method.");
		waitOnElement("cssSelector", enrollFirstName);
		selectSponsor(prop.getLocatorForEnvironment(appUrl,"EnrollRetailSponsorConsultID"));
		inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollRetailFName")+num);
		inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollRetailLName")+num);
	//	inputText("cssSelector", eCell,"12345678");
		inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollRetailHome"));
		inputText("cssSelector", eBirthDay, "12/12/2017");
		inputText("cssSelector",enrollEmail, prop.getLocatorForEnvironment(appUrl,"EnrollRetailEmail")+num+"@icentris.com");
		inputText("cssSelector", eSt1, "707 W 700 S");
		inputText("cssSelector", eCity, "Woods Cross");
		selectFromDropdown("cssSelector",eState,"byVisibleText","Utah");
		inputText("cssSelector", ePost, "84087");
		selectFromDropdown("cssSelector",eCountry,"byVisibleText","United States");
		
		/*inputText("cssSelector", eSt1, "4295 Suwanee Mill Dr");
		inputText("cssSelector", eCity, "Buford");
		selectFromDropdown("cssSelector",eState,"byVisibleText","Georgia");
		inputText("cssSelector", ePost, "30518");*/
		
		inputText("cssSelector",monatuserName,"monatretail"+num);
		inputText("cssSelector",enrollPassword,"password1");
		inputText("cssSelector",enrollConfirmPassword,"password1");
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(20000);
	}
	
	
	
	
	public void selectRetialProducts2Cart() throws IOException, Exception {
		logInfo("inside selectRetialProducts2Cart() method.");
		
		searchRetialProducts("All Products",prop.getLocatorForEnvironment(appUrl,"EnrollRetailProdSKUName"));
		addRetialProduct2Cart("All Products",prop.getLocatorForEnvironment(appUrl,"EnrollRetailProdSKUName"));
		clickOnElement("linkText", "Continue");
		Thread.sleep(10000);
	}
	
	
	public void addRetialAddationalProducts2Cart() throws IOException, Exception {
		logInfo("inside addRetialAddationalProducts2Cart() method.");
		
		addVolumeSystem4Add2Cart();
		validateRetailVolumeSystem4Add2Cart();
		
	}
	
	public void validateRetailVolumeSystem4Add2Cart() throws  IOException, Exception {
		logInfo("inside validateRetailVolumeSystem4Add2Cart() method.");
		
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(2) > div:nth-child(2)","$99.00");
	
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(10000);
	}
	
	public boolean searchRetialProducts(String categoryName, String prodName) throws  Exception {
		logInfo("inside searchRetialProducts() method.");
		
		boolean isProductFound = false;
		waitOnElement("cssSelector",selectCategory);
		selectFromDropdown("cssSelector",selectCategory,"byVisibleText",categoryName);
		inputTextClear("cssSelector","input#search_keywords");
		inputText("cssSelector","input#search_keywords",prodName);
		clickOnElement("cssSelector","button#search_btn");
		Thread.sleep(5000);
		waitOnElement("cssSelector","div.row.products_page");
		WebElement resultsContainer = driver().findElement(By.cssSelector("div.row.products_page"));
		List all_skuprods = resultsContainer.findElements(By.cssSelector("div.enrollment-additional-product.product-row"));
		String before_prod = "//div[@class='row products_page']/div[";
		String after_prod = "]/div/div[2]/a";
		
		System.out.println("Total SKU or Products after search = " + all_skuprods.size());
		
		if(all_skuprods.size()>=1){
			for(int i=2;i<=all_skuprods.size()+1;i++){
				logInfo(prodName + " product or SKU mach found in search results");
				isProductFound = true;
				break;
			}
		}
		
		if(isProductFound==false){
			logInfo(prodName + " product or SKU mach not found in search results");
			Assert.assertTrue(isProductFound, prodName + " product or SKU mach not found in search results");
		}
		return isProductFound;
	}
	
	public boolean addRetialProduct2Cart(String prodSelection, String prodSkuName) throws Exception{
		logInfo("inside addRetialProduct2Cart() method");
		
		boolean isProdMatchFound = false;
		
		waitOnElement("cssSelector","select#taxons-list");
		
		WebElement resultsContainer = driver().findElement(By.cssSelector("div.row.products_page"));
		List all_skuprods = resultsContainer.findElements(By.cssSelector("div.enrollment-additional-product.product-row"));
		
		//String before_skuName = "//div[@class='row products_page']/div[1]/div[2]/div[";
		//String after_skuName = "]/div/div[2]/a";
		String before_add2Cart = "//div[@class='row products_page']/div[";
		String after_add2Cart = "]/div/div[3]/div/div[2]/button/span";
		
		System.out.println("Total SKU or Products after search = " + all_skuprods.size());
		
		if(all_skuprods.size()>=1){
			for(int i=2;i<=all_skuprods.size()+1;i++){
			logInfo(prodSkuName + " product or SKU mach found in search results");
			isProdMatchFound = true;
			WebElement add2cart = driver().findElement(By.xpath(before_add2Cart+i+after_add2Cart));
			add2cart.click();
			Thread.sleep(4000);
			break;
			}
		}
			
		if(isProdMatchFound==false){
			logInfo(prodSkuName + " product or SKU mach not found in search results");
			Assert.assertTrue(isProdMatchFound, prodSkuName + " product or SKU mach not found in search results");
		}
		
		return isProdMatchFound;
	}
	
	public void selectHighestPackInStarterKit() throws  Exception{  // String prodName
		logInfo("inside selectHighestPackInStarterKit() method.");
		// using for Retail to MP upgrade US market
		
		waitOnElement("xpath","//div[@class='starter-kits-table-container']");
		WebElement paneProdPack = driver().findElement(By.xpath("//div[@class='starter-kits-table-container']"));
		List<WebElement> allProducts = paneProdPack.findElements(By.cssSelector("div.starter-kit"));
		int total_prods = allProducts.size();
		
		//String before_prodName = "//div[@class='starter-kits-table-container']/div[";
		//String after_prodName = "]/div[@class='row']/div[2]/h3";
		
		//String before_select = "//div[@class='starter-kits-table-container']/div[";
		//String after_select = "]/div[@class='row']/div[3]/div/a/label";
		
		WebElement select = driver().findElement(By.xpath("//div[@class='starter-kits-table-container']/div[1]/div[@class='row']/div[3]/div/a/label"));
		if(!select.isSelected() && select.isDisplayed()) {
			select.click();
		}
	
		scrollDown("linkText", "Continue");
		clickOnElement("linkText", "Continue");
		Thread.sleep(5000);
	}
	
	public void addRetailBillingInformation() throws  Exception {		
		logInfo("inside addRetailBillingInformation() method.");
		addNewCCDetails();
		validateRetailBillingInfo();
		selectRetailBillingAddress();
		//selectShippingAddress();
		useSameAddressForRetailShipping(prop.getLocatorForEnvironment(appUrl, "EnrollRetailUseSameAddressForShipping"));
		/*
		String isShipAndBillSame = prop.getLocatorForEnvironment(appUrl, "EnrollRetailUseSameAddressForShipping");
		if(isShipAndBillSame.equalsIgnoreCase("yes")) {
			//selectShippingAddress();
			//validateRetailShippingInfo();
			scrollDown("linkText", "Continue");
			clickOnElement("linkText", "Continue");
			selectBillingAddressMode(prop.getLocatorForEnvironment(appUrl, "EnrollRetailBillingAddressMode"));
		} else if(isShipAndBillSame.equalsIgnoreCase("no")) {
			//validateRetailShippingInfo();
			addNewRetailShippingAddress();
			scrollDown("linkText", "Continue");
			clickOnElement("linkText", "Continue");
			selectBillingAndShippingMode(prop.getLocatorForEnvironment(appUrl, "EnrollRetailBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollRetailShippingAddressMode")); 
			
		} else {
			logInfo("invalid option selected");
		} */

		clickOnElement("linkText", "Continue");
		Thread.sleep(15000);
	}
	
	public void addNewRetailShippingAddress() throws Exception {
		logInfo("inside addNewRetailShippingAddress() method.");
		
		clickOnElement("cssSelector","div#ship-address > div > div > ul > li:nth-child(1) > a");
		Thread.sleep(5000);
		scrollDown("cssSelector","#pyr_core_v2_enrollment_tmp_shipping_address_address1");
		//clickOnElement("cssSelector","#pyr_core_v2_enrollment_tmp_shipping_address_address1");
		inputTextClear("cssSelector","#pyr_core_v2_enrollment_tmp_shipping_address_address1");
		inputText("cssSelector","#pyr_core_v2_enrollment_tmp_shipping_address_address1","4295 Suwanee Mill Dr");
		inputTextClear("cssSelector","#pyr_core_v2_enrollment_tmp_shipping_address_city");
		inputText("cssSelector","#pyr_core_v2_enrollment_tmp_shipping_address_city","Buford");
		selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_tmp_shipping_address_state","byVisibleText","Georgia");
		inputTextClear("cssSelector","#pyr_core_v2_enrollment_tmp_shipping_address_postal_code");
		inputText("cssSelector","#pyr_core_v2_enrollment_tmp_shipping_address_postal_code","30518");
		selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_tmp_shipping_address_country","byVisibleText","United States");
	}
	
	
	public void validateRetailBillingInfo() throws Exception {
		logInfo("inside validateRetailBillingInfo() method.");
	//	scrollDown("xpath","//*[@id='section_addresses_billing_0']");
		validateTextOnElement("xpath","//*[@id='address_list_pane']/section/div[2]/div/div/span[1]","707 W 700 S,");  //4295 Suwanee Mill Dr,
		validateTextOnElement("xpath","//*[@id='address_list_pane']/section/div[2]/div/div/span[2]","Woods Cross, UT, 84087");  //Buford, GA, 30518
		validateTextOnElement("xpath","//*[@id='address_list_pane']/section/div[2]/div/div/span[3]","US");
	}
	
	public void validateRetailShippingInfo() throws Exception {
		logInfo("inside validateRetailShippingInfo() method.");
	//	scrollDown("xpath","//*[@id='ship_address_list_pane']/section/div[2]/div/div/span[1]");
		validateTextOnElement("xpath","//*[@id='ship_address_list_pane']/section/div[2]/div/div/span[1]","707 W 700 S,");  //4295 Suwanee Mill Dr,
		validateTextOnElement("xpath","//*[@id='ship_address_list_pane']/section/div[2]/div/div/span[2]","Woods Cross, UT, 84087"); //Buford, GA, 30518
		validateTextOnElement("xpath","//*[@id='ship_address_list_pane']/section/div[2]/div/div/span[3]","US");
	}
	
	public void selectRetailBillingAddress() throws Exception {
		logInfo("inside selectRetailBillingAddress() method");
		scrollDown("xpath","//*[@id='address_list_pane']/section/div[2]/div/div/div/div[1]/a");
		WebElement bill = driver().findElement(By.xpath("//*[@id='address_list_pane']/section/div[2]/div/div/div/div[1]/a"));
		if(bill.getText().trim().equalsIgnoreCase("Select")) {
			bill.click();
		}
	}
	
	public void selectShippingAddress() throws Exception {
		logInfo("inside selectShippingAddress() method");
		scrollDown("xpath","//*[@id='ship_address_list_pane']/section/div[2]/div/div/div/div[1]/a");
		WebElement ship = driver().findElement(By.xpath("//*[@id='ship_address_list_pane']/section/div[2]/div/div/div/div[1]/a"));
		if(ship.getText().trim().equalsIgnoreCase("Select")) {
			ship.click();
		}
	}
	
	public void useSameAddressForRetailShipping(String shipWithSameAddress) throws  Exception {
		logInfo("inside useSameAddressForRetailShipping() method.");
		System.out.println("inside useSameAddressForRetailShipping() method.");
		
		WebElement chkbox = driver().findElement(By.cssSelector("input#ship-same-as-bill"));
		
		System.out.println("chkbox.isSelected() = " +chkbox.isSelected());
		if((chkbox.isSelected()==false) && shipWithSameAddress.equalsIgnoreCase("yes")) {
			chkbox.click();
			Thread.sleep(4000);
		} else if ((chkbox.isSelected()==true) && shipWithSameAddress.equalsIgnoreCase("no")) {  //EnrollMPUseSameAddressForShipping
			chkbox.click();
			Thread.sleep(4000);
			addNewShippingAddressDetails();
			//validateRetailShippingInfo();
		} 
	}
		
	public void reviewAndSubmitRetail() throws Exception {
		logInfo("inside reviewAndSubmitRetial() method.");
		
		selectRetailShippingMethod("Ground");
		selectRetailShippingMethod("Two Day");
		//validateRetailPersonalInfOnReviewAndSubmit();
		//validateRetialBillingAndShippingAddressInfo();
		//validateBillingAndShippingAddressInfo(prop.getLocatorForEnvironment(appUrl,"EnrollRetailUseSameAddressForShipping"),prop.getLocatorForEnvironment(appUrl,"EnrollRetailBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollRetailShippingAddressMode"));
		
		clickOnElement("cssSelector","#pyr_core_v2_enrollment_tac2");
		clickOnElement("cssSelector","#pyr_core_v2_enrollment_toc_acceptance");
		
		clickOnElement("cssSelector", "a.btn.btn-primary.btn.btn-enrollment-continue");
		Thread.sleep(30000);
	}
	
	
	public void validateRetailPersonalInfOnReviewAndSubmit(String market, String email) throws  Exception {
		logInfo("inside validateRetailPersonalInfOnReviewAndSubmit() method.");
		
		switch(market) {
		case "US" :
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollRetUSFName")+num);
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollRetUSLName")+num);
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[1]/div/span[1]","12/12/1991 12:00 AM");  //  AM
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollRetailHome"));  // day phone
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[2]/div/span[1]",email);
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[6]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollRetUSUserName")+num);
			break;
		case "CA" :
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollRetCAFName")+num);
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollRetCALName")+num);
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[1]/div/span[1]","12/12/1991 12:00 AM");
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollRetailHome"));  // day phone
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[2]/div/span[1]", email);
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[6]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollRetCAUserName")+num);
			break;
		}
		
	}
	
	public void selectRetailShippingMethod(String shippingMethod) throws InterruptedException, Exception {
		logInfo("inside selectRetailShippingMethod() method");
		
		switch(shippingMethod) {
		
		case "Ground":
			//selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_shipping_method","byVisibleText","Ground");
			selectRadioOrCheckbox("cssSelector","#pyr_core_v2_enrollment_shipping_method_1");
			Thread.sleep(5000);
			validateTextOnElement("cssSelector","table.formatted_totals>tbody>tr:nth-child(2)> td:nth-child(2)","$0.00");
			Thread.sleep(10000);
			break;
		case "Two Day":
			//selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_shipping_method","byVisibleText","Two Day");
			selectRadioOrCheckbox("cssSelector","#pyr_core_v2_enrollment_shipping_method_3");
			Thread.sleep(5000);
			validateTextOnElement("cssSelector","table.formatted_totals>tbody>tr:nth-child(2)> td:nth-child(2)","$37.99");  // $26.99
			Thread.sleep(10000);
			break;
		}
	}
	
	public void validateRetialBillingAndShippingAddressInfo() throws Exception {
		logInfo("inside validateRetialBillingAndShippingAddressInfo() method.");
		
		String isShippingSame = prop.getLocatorForEnvironment(appUrl, "EnrollRetailUseSameAddressForShipping");
	
		if(isShippingSame.equalsIgnoreCase("yes")) {
			validateRetailBillingAddress();
		} else if(isShippingSame.equalsIgnoreCase("no")){
			validateRetailShippingAndBillingAddress();
		} else {
			logInfo("incorrect value");
		}
	}
	
	public void validateRetailBillingAddress() throws IOException {
		logInfo("inside validateRetailBillingAddress() method");
		
		String billmode = prop.getLocatorForEnvironment(appUrl, "EnrollRetailBillingAddressMode");
		if(billmode.equalsIgnoreCase("Given")) {
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(3)","4295 Suwanee Mill Dr");
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(5)","Buford - GA - 30518");
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(6)","US");
		} else if(billmode.equalsIgnoreCase("Suggested")) {
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(3)","4295 SUWANEE MILL DR");
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(5)","BUFORD, GA  30518-9247");
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(6)","US");
		} else {
			logInfo("incorrect billing mode");
		}
	}
	
	public void validateRetailShippingAndBillingAddress() throws IOException {
		logInfo("inside validateRetailShippingAndBillingAddress() method");
		
		// validating billing address info
		String billmode = prop.getLocatorForEnvironment(appUrl, "EnrollRetailBillingAddressMode");
		if(billmode.equalsIgnoreCase("Given")) {
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(3)","4295 Suwanee Mill Dr");
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(5)","Buford - GA - 30518");
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(6)","US");
		} else if(billmode.equalsIgnoreCase("Suggested")) {
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(3)","4295 SUWANEE MILL DR");
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(5)","BUFORD, GA  30518-9247");
			validateTextOnElement("cssSelector","div.address-block:nth-child(1) > span:nth-child(6)","US");
		} else {
			logInfo("incorrect billing mode");
		}
		
		// validating shipping address info
		String shipmode = prop.getLocatorForEnvironment(appUrl, "EnrollRetailShippingAddressMode");
		if(shipmode.equalsIgnoreCase("Given")) {
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(1)","4295 Suwanee Mill Dr");
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(3)","Buford , GA  30518");
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(4)","US");
		} else if(shipmode.equalsIgnoreCase("Suggested")) {
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(1)","4295 SUWANEE MILL DR");
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(3)","BUFORD, GA  30518-9247");
			validateTextOnElement("cssSelector","div.address-block:nth-child(2) > span:nth-child(4)","US");
		} else {
			logInfo("incorrect shipping mode");
		}
	}
	
	public void reviewAndSubmitRetailWithAddationalProducts(String market, String email) throws Exception {
		logInfo("inside reviewAndSubmitRetailWithAddationalProducts() method.");
		
		// validate Initial Order - Volume System
		
		/*validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(1)>td:nth-child(1)","10140000 - VOLUME SYSTEM");
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(1)>td:nth-child(2)","$99.00");
		validateTextOnElement("cssSelector","table.table-striped.table-order-review.initial-order>tbody>tr:nth-child(1)>td:nth-child(4)","$99.00");
		 */
		
		//selectRetailShippingMethod("Ground");
		//selectRetailShippingMethod("Two Day");
		validateRetailPersonalInfOnReviewAndSubmit(market, email);
		//validateRetialBillingAndShippingAddressInfo();
		clickOnElement("cssSelector","#pyr_core_v2_enrollment_tac2");
		clickOnElement("cssSelector","#pyr_core_v2_enrollment_toc_acceptance");
		
		clickOnElement("cssSelector", "a.btn.btn-primary.btn.btn-enrollment-continue");
		Thread.sleep(15000);
		driver().navigate().refresh();
		Thread.sleep(10000);
	}
	
// ###########################################################################################################	

	
	public void chooseCardOnFile() throws  Exception {
		logInfo("inside chooseCardOnFile() method.");
		
		waitOnElement("cssSelector","a#existing-credit-card-link ");
		clickOnElement("cssSelector","a#existing-credit-card-link ");
		
		
		
	}
	
	
// ###########################################################################################################	

	
	// Upgrade to Member Partner scripts
	
	public void selectUpgrade2MemberPartner() throws  Exception {
		logInfo("inside selectUpgrade2MemberPartner() method.");
		
		waitOnElement("linkText","Upgrade To Market Partner - 1");
		clickOnElement("linkText","Upgrade To Market Partner - 1");
		Thread.sleep(8000);
	}
	
	
	public void updateRetailPersonalInformation() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside updateRetailPersonalInformation() method.");
		waitOnElement("cssSelector", eBirthDay);
		inputText("cssSelector", eBirthDay, "12/12/1991");
		inputText("cssSelector", eSSN, "83789" + TestBase.generateRandomNumberInRange(1000, 9999));
		inputText("cssSelector", eCell,"12345678");
		clickOnElement("linkText", "Continue");
		Thread.sleep(5000);
	}
	
	
	public void upgradePersonalInfoRetail2MP(String market) throws  Exception {		// monatEnrollmentStep3
		logInfo("inside upgradePersonalInfo4Retail2MP() method.");
		
		switch(market) {
		case "US" :
			inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollRetUSFName")+num);
			inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollRetUSLName")+num);
			inputText("cssSelector", eBirthDay, "12/12/1991");
			inputText("cssSelector", eCell,"12345678");
			inputText("cssSelector", eSSN, prop.getLocatorForEnvironment(appUrl,"EnrollRetailEssn")+num); 
			inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollMPHome"));
			inputText("cssSelector",enrollEmail,prop.getLocatorForEnvironment(appUrl,"EnrollRETUSEmail")+num+"@icentris.com");
			selectFromDropdown("cssSelector",enrollGender,"byVisibleText","Male");
			break;
		case "CA" :
			inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollRetCAFName")+num);
			inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollRetCALName")+num);
			inputText("cssSelector", eBirthDay, "12/12/1991");
			inputText("cssSelector", eCell,"12345678");
			inputText("cssSelector", eSSN, prop.getLocatorForEnvironment(appUrl,"EnrollRetailEssn")+num); 
			inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollMPHome"));
			inputText("cssSelector",enrollEmail,prop.getLocatorForEnvironment(appUrl,"EnrollRETCAEmail")+num+"@icentris.com");
			selectFromDropdown("cssSelector",enrollGender,"byVisibleText","Male");
			break;
	
					
		}
	
		clickOnElement("linkText", "Continue");
		Thread.sleep(5000);
	}

	
	public void updateRetailBillingAndShipping() throws  Exception {		// monatEnrollmentStep4
		logInfo("inside updateRetailBillingAndShipping method");
		
		addNewCCDetails();
		
		// select address
		clickOnElement("cssSelector","div#credit-card-address > div > div >div.tab-content > div#address_list_pane > section > div.row > div > div.address > div.controls > div > a");
				
		waitOnElement("linkText", "Continue");
		clickOnElement("linkText", "Continue");
		Thread.sleep(5000);
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(8000);
	}
	
	
	public void updateUSBillingRetail2MPUpgrade() throws  Exception {		// monatEnrollmentStep4
		logInfo("inside updateUSBillingRetail2MPUpgrade method");
		
		addNewCCDetails();
		addNewBillingAddress();
		useSameAddressForShipping(prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping"));
		clickOnElement("linkText", "Continue");
		Thread.sleep(6000);
		
		String isShipAndBillSame = prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping");
		if(isShipAndBillSame.equalsIgnoreCase("yes")) {
			selectBillingAddressMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPBillingAddressMode"));
		} else if(isShipAndBillSame.equalsIgnoreCase("no")) {
			selectBillingAndShippingMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl, "EnrollMPShippingAddressMode"));
			//selectBillingAndShipping("Suggested Address","Suggested Address");
		} else {
			logInfo("invalid option selected");
		}
		
		Thread.sleep(4000);
		clickOnElement("linkText", "Continue"); // remove comment later
		Thread.sleep(25000);
		/*
		// select address
		clickOnElement("cssSelector","div#credit-card-address > div > div >div.tab-content > div#address_list_pane > section > div.row > div > div.address > div.controls > div > a");
				
		waitOnElement("linkText", "Continue");
		clickOnElement("linkText", "Continue");
		Thread.sleep(5000);
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(8000);*/
	}
	
	public void updateRetailReviewAndSubmit() throws  Exception {		// monatEnrollmentStep4
		logInfo("inside updateRetailReviewAndSubmit method");
		
		waitOnElement("cssSelector", agreeToc2);
		clickOnElement("cssSelector", agreeToc2);
		clickOnElement("cssSelector", inputTOC);
		clickOnElement("cssSelector", enrlContBtn);
		Thread.sleep(15000);
		driver().navigate().refresh();
		Thread.sleep(10000);
	}
	
	
	// Upgrade to VIP scripts
	
	public void selectUpgrade2VIP() throws  Exception {
		logInfo("inside selectUpgrade2VIP() method.");
		
		waitOnElement("linkText","Upgrade to VIP");
		clickOnElement("linkText","Upgrade to VIP");
		Thread.sleep(6000);
	}
	
	public void updateRetialPersonalInformation4VIP() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside updateRetialPersonalInformation4VIP() method.");
		waitOnElement("cssSelector", eBirthDay);
		inputText("cssSelector", eBirthDay, "12/12/1991");
		inputText("cssSelector", eCell,"12345678");
		selectFromDropdown("cssSelector", enrollGender,"byVisibleText","Male");
		clickOnElement("linkText", "Continue");
		Thread.sleep(8000);
	}
	
	
	public void selectProductByNameOrSKUbyRetailUser() throws Exception{
		logInfo("inside selectProductByNameOrSKUbyRetailUser() method");
		
		verifyVIPCustomerRegistrationDetails();		// Select Product page
		verifyErrorMsg4VIPOrderSubtotalLessthan84USD();
		setFlexshipDate("05/05/2018");
		addProducts2CartOrFlexWithSKUID("All Products", "HYDRATION SYSTEM");
		
		clickOnElement("linkText","Continue");
		Thread.sleep(8000);    // uncomment later
	}

	public void selectNextPage() throws InterruptedException {
		logInfo("inside selectProductByNameOrSKUbyRetailUser() method");
		
		clickOnElement("linkText","Continue");
		Thread.sleep(8000); 
	}
	
	public boolean addProduct2Cart(String prodSelection, String prodSkuName) throws Exception{
		logInfo("inside addProduct2Cart() method");
		
		boolean isProdMatchFound = false;
		
		waitOnElement("cssSelector","select#taxons-list");
		selectFromDropdown("cssSelector","select#taxons-list","byVisibleName",prodSelection);
		Thread.sleep(8000);
		inputTextClear("cssSelector","input#search_keywords");
		inputText("cssSelector","input#search_keywords",prodSkuName);
		clickOnElement("cssSelector","button#search_btn");
		Thread.sleep(10000);
		waitOnElement("cssSelector","div.row.products_page");
		WebElement resultsContainer = driver().findElement(By.cssSelector("div.row.products_page"));
		List all_skuprods = resultsContainer.findElements(By.cssSelector("div.enrollment-additional-product.product-row"));
		
		//String before_skuName = "//div[@class='row products_page']/div[1]/div[2]/div[";
		//String after_skuName = "]/div/div[2]/a";
		String before_add2Cart = "//div[@class='row products_page']/div[";
		String after_add2Cart = "]/div[1]/div[3]/div[1]/div[2]/button/span";
		
		System.out.println("Total SKU or Products after search = " + all_skuprods.size());
		
		if(all_skuprods.size()>=1){
			for(int i=2;i<=all_skuprods.size()+1;i++){
			logInfo(prodSkuName + " product or SKU mach found in search results");
			isProdMatchFound = true;
			WebElement add2cart = driver().findElement(By.xpath(before_add2Cart+i+after_add2Cart));
			add2cart.click();
			Thread.sleep(3000);
			break;
			}
		}
			
		if(isProdMatchFound==false){
			logInfo(prodSkuName + " product or SKU mach not found in search results");
			Assert.assertTrue(isProdMatchFound, prodSkuName + " product or SKU mach not found in search results");
		}
		
		return isProdMatchFound;
	}

	
	public boolean addProduct2Flexship(String prodSelection, String prodSkuName) throws Exception{
		logInfo("inside addProduct2Cart() method");
		
		boolean isProdMatchFound = false;
		
		waitOnElement("cssSelector","select#taxons-list");
		selectFromDropdown("cssSelector","select#taxons-list","byVisibleName",prodSelection);
		inputTextClear("cssSelector","input#search_keywords");
		inputText("cssSelector","input#search_keywords",prodSkuName);
		clickOnElement("cssSelector","button#search_btn");
		Thread.sleep(5000);
		waitOnElement("cssSelector","div.row.products_page");
		WebElement resultsContainer = driver().findElement(By.cssSelector("div.row.products_page"));
		List all_skuprods = resultsContainer.findElements(By.cssSelector("div.enrollment-additional-product.product-row"));
		
		//String before_skuName = "//div[@class='row products_page']/div[";
		//String after_skuName = "]/div[1]/div[2]/a";
		String before_add2Flex = "//div[@class='row products_page']/div[";
		String after_add2Flex = "]/div[1]/div[3]/div[2]/div[2]/button/span";
		
		System.out.println("Total SKU or Products after search = " + all_skuprods.size());
		
		if(all_skuprods.size()>=1){
			for(int i=2;i<=all_skuprods.size()+1;i++){
				//WebElement eSKUProdName = driver().findElement(By.xpath(before_skuName+i+after_skuName ));
				//String actSKUProdName = eSKUProdName.getText().trim();
				//if(actSKUProdName.contains(prodSkuName)){
					logInfo(prodSkuName + " product or SKU mach found in search results");
					isProdMatchFound = true;
					WebElement add2flexship = driver().findElement(By.xpath(before_add2Flex+i+after_add2Flex));
					add2flexship.click();
					Thread.sleep(3000);
					break;
				//}
			}
			
		}
		
		if(isProdMatchFound==false){
			logInfo(prodSkuName + " product or SKU mach not found in search results");
			Assert.assertTrue(isProdMatchFound, prodSkuName + " product or SKU mach not found in search results");
		}
		
		return isProdMatchFound;
	}
	
	
	public void verifyVIPCustomerRegistrationDetails() {
		logInfo("inside verifyVIPCustomerRegistration() method");
		
		validateTextOnElement("xpath","//div[@class='order-items']/div[1]/div[2]/div[1]/div","VIP Customer Registration");
		validateTextOnElement("xpath","//div[@class='order-items']/div[1]/div[2]/div[2]/div[2]","$19.99");
				
		}
	
	public void verifyErrorMsg4VIPOrderSubtotalLessthan84USD() throws  Exception {
		logInfo("inside verifyErrorMsg4VIPOrderSubtotalLessthan84USD() method");
		
		WebElement orderSub = driver().findElement(By.xpath("//div[1]/div[@class='row order-subtotal']/div[3]/strong/span"));
		String actSubTotal = orderSub.getText().trim();
		
		float intialSubtotal = convertUSD2Foat(actSubTotal);
		if(intialSubtotal<=84) {
			clickOnElement("linkText","Continue");
			Thread.sleep(5000);
			WebElement ealert1 = driver().findElement(By.xpath("//div[@class='alert alert-danger']/ul/li/span/b"));
			String alert1 = ealert1.getText().trim();
			Assert.assertEquals(alert1, "Oops! Your Today’s Order and Flexship total must EACH be at least $84 USD / $110 CAD.");
		}
	}
	
	
	public float convertUSD2Foat(String value) {
		logInfo("inside convertUSD2Foat() method");
		
		String price[] = value.split("\\$");
		float f = Float.parseFloat(price[1]);
		System.out.println("f =" +f);
		
		return f;
	}
	
	
	
	// Upgrade VIP to MP Script
	
	public void validateUpgrade2MPLinkPresent() throws  Exception {
		logInfo("inside validateUpgrade2MPLinkPresent() method.");
		
		driver().navigate().refresh();
		
		waitOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		clickOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		Thread.sleep(4000);
		validateTextOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/ul/li[3]/a/span","Upgrade to Market Partner");
		Thread.sleep(2000);
	}
	
	/*public void validateMPLinkPresent() throws  Exception {
		logInfo("inside validateMPLinkPresent() method.");
		
		boolean isLinkFound = false;
		//waitOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		clickOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		Thread.sleep(2000);
		validateTextOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/ul/li[1]/a/span","Enroll MP");  // changed li[2]/a/span to li[1]/a/spam for vip 2 mp upgrade scenario
		validateTextOnElement("linkText","Enroll MP","Enroll MP");
		Thread.sleep(2000);
		
	}*/
	
	public boolean validateLinkPresentInToolsMenu(String menuItem) throws  Exception {
		logInfo("inside validateMPLinkPresent() method.");
		
		boolean isLinkFound = false;
		waitOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		String before_menu = "//ul[@class='nav navbar-nav']/li[2]/ul/li[";
		String after_menu = "]/a/span";
		
		clickOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		Thread.sleep(2000);
		WebElement panel = driver().findElement(By.xpath("//ul[@class='nav navbar-nav']/li[2]/ul"));
		List allItems = panel.findElements(By.tagName("li"));
		if(allItems.size()>0) {
			for(int i=1;i<=allItems.size(); i++) {
				WebElement x = driver().findElement(By.xpath(before_menu+i+after_menu));
				String act_Item = x.getText().trim();
				if(act_Item.equalsIgnoreCase(menuItem)) {
					logInfo(menuItem + " menu item match found in Tools menu.");
					isLinkFound = true;
					break;
				}
			}
		}
		
		clickOnElement("xpath","//ul[@class='nav navbar-nav']/li[2]/a/span[2]");
		
		
		return isLinkFound;
		
	}

		
	
	
	public void updateVIPPersonalInformation(String essn) throws  Exception {		// vip upgrade to MP
		logInfo("inside updateVIPPersonalInformation() method.");
		waitOnElement("cssSelector", eBirthDay);
		inputText("cssSelector", eSSN, essn); // prop.getLocatorForEnvironment(appUrl, "EnrollVIPEssn")+num
		inputText("cssSelector", eCell,"12345678");
		selectFromDropdown("cssSelector","select#pyr_core_v2_enrollment_gender","byVisibleText","Male");
		clickOnElement("linkText", "Continue");
		Thread.sleep(5000);
	}
	
	
	
	// PWP user scripts
	
	
	public void go2PwpUserPage() throws  Exception{
		logInfo("inside go2PwpUserPage() method.");
		driver().navigate().to(prop.getLocatorForEnvironment(appUrl, "pwpEnrollUrl"));
		
		waitOnElement("linkText","Join Now");
		clickOnElement("linkText","Join Now");
	}

	
	public void navigate2MPEnrollment() throws InterruptedException {
		logInfo("inside navigate2MPEnrollment() method.");
		driver().navigate().to(appUrl+"pyr_core/v2_enrollments/select_market?enroll_params=profile_form_type%3Dexpress&mp_only=true");
		driver().navigate().refresh();
		Thread.sleep(5000);
	}
	
	public void go2BusinessEnrollmentPage() {
		logInfo("inside go2BusinessEnrollmentPage() method.");
		driver().navigate().to(appUrl + "pyr_core/v2_enrollments/select_market");
	}

	public boolean verifyEnrollMarketIsPresent(String marketName) {
		logInfo("inside verifyEnrollMarketIsPresent() method.");
		boolean isMarketFound = false;
		WebElement paneEnrollMarkets = driver().findElement(By.cssSelector("div.row.choose_market_container > div.col-md-12"));
		List<WebElement> allMarkets = paneEnrollMarkets.findElements(By.cssSelector("div.col-md-2.text-center > a > h3"));
		for (WebElement x : allMarkets) {
			String actMarket = x.getText().trim();
			if (actMarket.equalsIgnoreCase(marketName)) {
				isMarketFound = true;
				logInfo(marketName + " markets found in Business Enrollment page.");
				break;
			}
		}
		return isMarketFound;
	}
	
	
	
	public void selectBusinessEnrollMarket(String marketName) throws InterruptedException {
		logInfo("inside selectBusinessEnrollMarket() method.");
		boolean isMarketFound = false;
		WebElement paneEnrollMarkets = driver().findElement(By.cssSelector("div.row.choose_market_container > div.col-md-12"));
		List<WebElement> allMarkets = paneEnrollMarkets.findElements(By.cssSelector("div.col-md-2.text-center > a > h3"));
		for (WebElement x : allMarkets) {
			String actMarket = x.getText().trim();
			if (actMarket.equalsIgnoreCase(marketName)) {
				isMarketFound = true;
				logInfo(marketName + " markets found in Business Enrollment page.");
				x.click();
				Thread.sleep(5000);
				logInfo("clicked on enrollment market - " + marketName);
				break;
			}
		}
		
		if(isMarketFound==false){
			logInfo(marketName + " markets not found in Business Enrollment page.");
			Assert.assertTrue(isMarketFound, marketName + " markets not found in Business Enrollment page.");
		}

	}


	

	public void enterWebsiteDetails() throws  Exception {
		logInfo("inside enterWebsiteDetails() method.");
		//waitOnElement("cssSelector", eUserName);
		inputTextClear("cssSelector", eUserName);
		inputText("cssSelector", eUserName, prop.getLocatorForEnvironment(appUrl, "pwpEnrollFirstName"+TestBase.generateRandomNumberInRange(1, 100)));
	}

	
	public boolean searchProductByNameOrSKU(String prodSelection, String prodSkuName) throws Exception{
		logInfo("inside searchProductByNameOrSKU() method");
		
		boolean isProdMatchFound = false;
		
		waitOnElement("cssSelector","select#taxons-list");
		selectFromDropdown("cssSelector","select#taxons-list","byVisibleName",prodSelection);
		Thread.sleep(5000);
		inputTextClear("cssSelector","input#search_keywords");
		inputText("cssSelector","input#search_keywords",prodSkuName);
		clickOnElement("cssSelector","button#search_btn");
		Thread.sleep(5000);
		waitOnElement("cssSelector","div.row.products_page");
		WebElement resultsContainer = driver().findElement(By.cssSelector("div.row.products_page"));
		List all_skuprods = resultsContainer.findElements(By.cssSelector("div.col-md-3.enrollment-additional-product.product-row"));
		
		String before_skuName = "//div[@class='row products_page']/div[";
		String after_skuName = "]/div/div[2]/a";
		String before_Add2Bag = "//div[@class='row products_page']/div[";
		String after_Add2Bag = "]/div/div[3]/div[1]/div[2]/button/span";
		
		System.out.println("Total SKU or Products after search = " + all_skuprods.size());
		
		if(all_skuprods.size()>=1){
			for(int i=2;i<=all_skuprods.size()+1;i++){
				WebElement eSKUProdName = driver().findElement(By.xpath(before_skuName+i+after_skuName ));
				String actSKUProdName = eSKUProdName.getText().trim();
				if(actSKUProdName.contains(prodSkuName)){
					logInfo(prodSkuName + " product or SKU mach found in search results");
					isProdMatchFound = true;
					WebElement add2bag = driver().findElement(By.xpath(before_Add2Bag+i+after_Add2Bag));
					add2bag.click();
					Thread.sleep(3000);
					break;
				}
			}
			
		}
		
		waitOnElement("linkText", "Continue");
		clickOnElement("linkText", "Continue");
		
		if(isProdMatchFound==false){
			logInfo(prodSkuName + " product or SKU mach not found in search results");
			Assert.assertTrue(isProdMatchFound, prodSkuName + " product or SKU mach not found in search results");
		}
		
		return isProdMatchFound;
	}

	
	public void enterCreditCardInfo() throws  Exception {
		logInfo("inside enterCreditCardInfo method");
		
		waitOnElement("cssSelector",enrollmentCreditcardName);
		inputText("cssSelector",enrollmentCreditcardName,prop.getLocatorForEnvironment(appUrl, "enrollLDCardName_text"));
		inputText("cssSelector",enrollmentCreditcardNumber,prop.getLocatorForEnvironment(appUrl, "enrollLDCardNumber_text"));
		inputText("cssSelector",enrollmentCreditcardcvv,prop.getLocatorForEnvironment(appUrl, "enrollLDCardCvv_text"));
		selectFromDropdown("cssSelector",enrollmentCreditcardMonth,"ByVisibleText",enrollLDCardExpiryMonth_text);
		selectFromDropdown("cssSelector",enrollmentCreditcardYear,"ByVisibleText",enrollLDCardExpiryYear_text);
	}
	
	public void submitCCAddressDetails() throws Exception {
		logInfo("inside submitCCAddressDetails() method.");
	
		inputText("cssSelector", ccAddress, enrollLDAddress1_text);
		inputText("cssSelector", ccCity, enrollLDCity_text);
		selectFromDropdown("cssSelector", ccState, "byVisibleText", enrollLDState_text);
		inputText("cssSelector", ccZipCode, enrollLDPostalCode_text);
		selectFromDropdown("cssSelector", ccCountry, "byVisibleText", enrollLDCountry_text);
		
		WebElement eUseSameAddress = driver().findElement(By.cssSelector("input#ship-same-as-bill"));
		if(!eUseSameAddress.isSelected()){
			eUseSameAddress.click();
		}
	}
	
	
	//###############################################################################################
	
	/*public void go2ExpressEnrollmentPage() {
		logInfo("inside go2ExpressEnrollmentPage() method.");
		driver().navigate().to(appUrl + "pyr_core/v2_enrollments/select_market?enroll_params=profile_form_type%3Dexpress&mp_only=true");
	}									 
	 */
	
	/*public void go2VIPEnrollmentPage() {
		logInfo("inside go2VIPEnrollmentPage() method.");
		driver().navigate().to(appUrl + "pyr_core/v2_enrollments/select_market?enroll_params=profile_form_type%3Denrollvip");
	}*/
	
	public void go2RetialEnrollmentPage() throws  Exception {
		logInfo("inside go2RetialEnrollmentPage() method.");
			// driver().navigate().to(appUrl + "pyr_core/v2_enrollments/select_market?enroll_params=profile_form_type%3Dcustomer");
			waitOnElement("xpath","//*[@id='navbar-collapsable']/ul[2]/li[2]/a/span[2]");
			clickOnElement("xpath","//*[@id='navbar-collapsable']/ul[2]/li[2]/a/span[2]");
			
			clickOnElement("xpath","//span[contains(text(),'Enroll Retail')]");
			//Thread.sleep(5000);
			//driver().navigate().refresh();
			Thread.sleep(10000);
		}
	
	public void closeFeedbackWindow(){
		logInfo("inside closeFeedbackWindow() method");
		WebElement feedback = driver().findElement(By.xpath("//*[@id='feedback_modal']/div/div[1]/div[1]/button/i"));
		if(feedback.isDisplayed()){
			feedback.click();
		}
	}
	
	public void selectSubscriptionPlan(String subscriptionName) throws  Exception{
		logInfo("inside selectSubscriptionPlan() method.");
		
		scrollDown("xpath","+");
		
		WebElement promotionPanel = driver().findElement(By.xpath("//*[@class='subscription-with-promotion review']/div[2]/div[2]"));
		List all_promotions = promotionPanel.findElements(By.cssSelector(".col-md-6.plan"));
		System.out.println("Total promos =" +all_promotions.size());
		
		if(all_promotions.size() >0){
			String before_promoName = "//div[@class='form-group string optional pyr_core_v2_enrollment_subscription_with_promotion']/div[@class='row']/div[";
			String after_promoName = "]/div[2]/p";  // ]/div[2]/h3
			
			String before_select = "//div[@class='form-group string optional pyr_core_v2_enrollment_subscription_with_promotion']/div[@class='row']/div[";
			String after_select =  "]/div[2]/span[2]/div/a[2]"; // "]/div[2]/span[2]/div/a";
					
			for(int i=1;i<=all_promotions.size();i++){
				
				WebElement promoName = driver().findElement(By.xpath(before_promoName+i+after_promoName));
				String actSubscrition = promoName.getText().trim();
				System.out.println("actSubscrition = "+actSubscrition);
				System.out.println("expectedSubscrition = "+subscriptionName);
				if(actSubscrition.contains(subscriptionName)){
					logInfo(subscriptionName + " subscription match found to select");
					
					WebElement select = driver().findElement(By.xpath(before_select+i+after_select));
					scrollDown("xpath",before_select+i+after_select);
					select.click();
					Thread.sleep(2000);
					break;
				}
			}
		}
	
	}
	
	
	public void showAlertMessages() {
		logInfo("inside showAlertMessages() method.");
		
		String before_msg = "div.alert.alert-danger > ul > li:nth-child(";
		String after_msg = ")";
		WebElement x = driver().findElement(By.cssSelector("div.alert.alert-danger > ul"));
		List allMsgs = x.findElements(By.tagName("li"));
		if(allMsgs.size()>0) {
			for(int i=1;i <=allMsgs.size(); i++) {
				WebElement msg = driver().findElement(By.cssSelector(before_msg+i+after_msg));
				System.out.println("msg = " +msg);
			}
		}
	}
	
	
	public boolean verifyEnrollmentSuccessfulAtUser() throws  Exception {
		logInfo("inside verifyEnrollmentSuccessfulAtUser() method.");
		boolean isEnrollmentSuccessful = false;
		waitOnElement("cssSelector", "#main-content");
		driver().navigate().refresh();
		Thread.sleep(5000);
		WebElement x = driver().findElement(org.openqa.selenium.By.cssSelector("#main-content"));
		String actMsg = x.getText().trim();
		System.out.println("actEnrollMsg =" + actMsg);
		if(actMsg.contains("Your enrollment is successful. Your consultant id is")) {
			logInfo(actMsg + " message found.");
			isEnrollmentSuccessful = true;
		} else {
			WebElement d = driver().findElement(By.cssSelector("div.alert.alert-danger> ul"));
			List allLis = d.findElements(By.tagName("li"));
			if(allLis.size()>0) {
				for(int i=1;i<=allLis.size();i++) {
					WebElement err = driver().findElement(By.xpath("//div[@class='alert alert-danger']/ul/li["+i+"]"));
					logInfo("err = " + err.getText().trim());
					System.out.println("err = " + err.getText().trim());
				}
			}
		}
		return isEnrollmentSuccessful;
	}
	
	
	public void readConsultantID2File(String enrollmentType,String market) throws  Exception {
		logInfo("inside readConsultantID2File() method.");
		
		WebElement x = driver().findElement(org.openqa.selenium.By.cssSelector("#main-content"));
		String actMsg = x.getText().trim();
		String [] stringParts = actMsg.split(" id is");
		System.out.println("stringParts[1] is " + stringParts[1]);
		System.out.println("actEnrollMsg =" + actMsg);
		
		if (enrollmentType.equalsIgnoreCase("MP") && market.equalsIgnoreCase("US")){
			prop.setLocatorForEnvironment(appUrl, "NewConUSMP", stringParts[1]);
		} else if (enrollmentType.equalsIgnoreCase("VIP") && market.equalsIgnoreCase("US")){
			prop.setLocatorForEnvironment(appUrl, "NewConUSVIP", stringParts[1]);
		} else if (enrollmentType.equalsIgnoreCase("Retail") && market.equalsIgnoreCase("US")){
			prop.setLocatorForEnvironment(appUrl, "NewConUSRetail", stringParts[1]);
		} else if (enrollmentType.equalsIgnoreCase("MP") && market.equalsIgnoreCase("Canada")){
			prop.setLocatorForEnvironment(appUrl, "NewConCAMP", stringParts[1]);
		}  else if (enrollmentType.equalsIgnoreCase("VIP") && market.equalsIgnoreCase("Canada")){
			prop.setLocatorForEnvironment(appUrl, "NewConCAVIP", stringParts[1]);
		}  else if (enrollmentType.equalsIgnoreCase("Retail") && market.equalsIgnoreCase("Canada")){
			prop.setLocatorForEnvironment(appUrl, "NewConCARetail", stringParts[1]);
		} if (enrollmentType.equalsIgnoreCase("MP") && market.equalsIgnoreCase("UK")){
			prop.setLocatorForEnvironment(appUrl, "NewConUKMP", stringParts[1]);
		} else {
			logInfo("Invalid Selection");
		}
		
	}
	
	// ==========================================================================================================
	
	// CORP ENROLLMENT METHODS
	
	public void go2CorpEnrollmentPage() {
		logInfo("inside go2CorpEnrollmentPage method");
		
		 String[] url1 = appUrl.split("//");
		 String corpUrl =  "corp." + url1[1];
		 System.out.println("corpUrl =" +corpUrl);
		 driver().navigate().to("http://"+corpUrl);
	}
	
	public void joinNowMP() throws InterruptedException {
		logInfo("inside joinNowMP method");
		
		clickOnElement("linkText","Join Now");
		Thread.sleep(5000);
	}
	
	public boolean searchMPLookUp(String userName, String state) throws Exception {
		logInfo("inside searchMPLookUp method");
		
			//clickOnElement("linkText","Join Now");
			
			//boolean isZeroRowsFound = false;
			boolean isConsFound = false;
			
			inputTextClear("cssSelector","#name-input");
			inputText("cssSelector","#name-input",userName);
			selectFromDropdown("cssSelector","#state-input","byVisibleText",state);
			clickOnElement("cssSelector","form.simple_form.known_search > input[value='Search']");
			Thread.sleep(10000);
			
			WebElement tbl = driver().findElement(By.cssSelector("div.search-result-container >table#search-results > tbody"));
			List allRows = tbl.findElements(By.tagName("tr"));
			
			int total_rows = allRows.size();
			if(total_rows==0) {
				logInfo("No matching users found in MP Lookup");
			}
			
			System.out.println("userName =" +userName);
			
			if(total_rows>0) {
				for(int i=1;i<=total_rows;i++) {
					String before_name = "//div[@class='search-result-container']/table[@id='search-results']/tbody/tr[";
					String after_name = "]/td[2]";
					WebElement name = driver().findElement(By.xpath(before_name+i+after_name));
					String actName = name.getText().trim();
					System.out.println("actName =" +actName);
					if(actName.contains(userName)) {
						logInfo(userName + " Consultant name match found");
						isConsFound = true;
						break;
					}
				} 
				
				} else {
					logInfo(userName + " Consultant match not found");
					isConsFound = false;
			}
			
			return isConsFound;
		
	}
	
	
	public void selectMPLookUp(String userName, String state) throws Exception {
		logInfo("inside selectMPLookUp method");
		
			inputTextClear("cssSelector","#name-input");
			inputText("cssSelector","#name-input",userName);
			selectFromDropdown("cssSelector","#state-input","byVisibleText",state);
			clickOnElement("cssSelector","form.simple_form.known_search > input[value='Search']");
			Thread.sleep(5000);
			
			WebElement tbl = driver().findElement(By.cssSelector("div.search-result-container >table#search-results > tbody"));
			List allRows = tbl.findElements(By.tagName("tr"));
			
			int total_rows = allRows.size();
			if(total_rows==0) {
				logInfo("No matching users found to select in MP Lookup");
			}
			
			if(total_rows>0) {
				for(int i=1;i<=total_rows;i++) {
					String before_name = "//div[@class='search-result-container']/table[@id='search-results']/tbody/tr[";
					String after_name = "]/td[2]";
					String after_enroll = "]/td[7]/a";
					
					WebElement name = driver().findElement(By.xpath(before_name+i+after_name));
					String actId = name.getText().trim();
					if(actId.contains(userName)) {
						logInfo(userName + " Consultant name match found");
						WebElement enroll = driver().findElement(By.xpath(before_name+i+after_enroll));
						enroll.click();
						logInfo(userName + " Consultant name selected.");
						break;
					}
				} 
					
				} else {
					logInfo(userName + " Consultant name match not found");
			}
	}
	
	// ==========================================================================================================
	
	
	
	public void enrollmentShippingAddress() throws Exception {
		logInfo("inside enrollmentShippingAddress method");
		System.out.println("inside enrollmentShippingAddress method");
		waitOnElement("linkText", "Add / Change Address");
		clickOnElement("linkText", "Add / Change Address");
		waitOnElement("cssSelector", enrollmentShippingAddress1);
		inputText("cssSelector", enrollmentShippingAddress1, "707 w 700 S");
		inputText("cssSelector", enrollmentShippingzipcode, "84087");
		inputText("cssSelector", enrollmentShippingCity, "woods cross");
		selectFromDropdown("cssSelector", enrollmentShippingCountry, "byVisibleText", "United States");
		selectFromDropdown("cssSelector", enrollmentShippingState, "byVisibleText", "Utah");
		clickOnElement("cssSelector", enrollmentShippingAddressSave);
	}

	// Using by Ravi

	public void selectAddressValidation(String address) throws  Exception {
		logInfo("inside selectAddressValidation() method");
		waitOnElement("xpath", "//*[@id='squire_address_validation']/div/div/div[2]/div");
		WebElement eAddrValidation = driver().findElement(By.xpath("//*[@id='squire_address_validation']/div/div/div[2]/div"));
		List allInputs = eAddrValidation.findElements(By.className("col-md-6"));
		String before_addrInput = "//*[@id='squire_address_validation']/div/div/div[2]/div/div[";
		String after_addrInput = "]/div/div/input";		
		String after_addrHeader = "]/h4";
		boolean isSuggPresent = false;
		for (int i = 1; i <= allInputs.size(); i++) {
			WebElement eHeader = driver().findElement(By.xpath(before_addrInput+i+after_addrHeader));
			String actHeader = eHeader.getText().trim();
			if (actHeader.equalsIgnoreCase(address)) {
				isSuggPresent =true;
				WebElement eInput = driver().findElement(By.xpath(before_addrInput+i+after_addrInput));
				eInput.click();
				clickOnElement("xpath", "//*[@id='address_proceed']");
				break;
			}
		}if (isSuggPresent==false){
			Assert.assertTrue(isSuggPresent, address+ " is not found");
		}
	}


	//############################ CANADA Market ############################################################
	
	/*public void addCanadaMarketPartnerBillingInformation() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside addNewMarketPartnerBillingInformation() method.");
		
		addNewCCDetails();
		addCANewBillingAddress();
		//addAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPAddationalProducts"));
		useSameAddressForShipping(prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping"));
		clickOnElement("linkText", "Continue");
		Thread.sleep(6000);
		
		String isShipAndBillSame = prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping");
		if(isShipAndBillSame.equalsIgnoreCase("yes")) {
			selectBillingAddressMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPBillingAddressMode"));
		} else if(isShipAndBillSame.equalsIgnoreCase("no")) {
			selectBillingAndShippingMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl, "EnrollMPShippingAddressMode"));
			//selectBillingAndShipping("Suggested Address","Suggested Address");
		} else {
			logInfo("invalid option selected");
		}
		
		Thread.sleep(4000);
		clickOnElement("linkText", "Continue"); // remove comment later
		Thread.sleep(25000);
	}
	*/
	
	
	public void addCanadaMarketPartnerBillingInformation() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside addCanadaMarketPartnerBillingInformation() method.");
		
		addNewCCDetails();
		addCANewBillingAddress();
		useSameAddressForShipping(prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping"));
		//addAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPAddationalProducts"));
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(6000);
		
		String isShipAndBillSame = prop.getLocatorForEnvironment(appUrl, "EnrollMPCAUseSameAddressForShipping");
		if(isShipAndBillSame.equalsIgnoreCase("yes")) {
			selectBillingAddressMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPCABillingAddressMode"));
		} else if(isShipAndBillSame.equalsIgnoreCase("no")) {
			selectBillingAndShippingMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPCABillingAddressMode"),prop.getLocatorForEnvironment(appUrl, "EnrollMPCAShippingAddressMode"));
			//selectBillingAndShipping("Suggested Address","Suggested Address");
		} else {
			logInfo("invalid option selected");
		}
		
		Thread.sleep(4000);
		clickOnElement("linkText", "Continue"); // remove comment later
		Thread.sleep(25000);
		
		//clickOnElement("linkText", "Continue");
		//Thread.sleep(15000);
		
	}
	
	public void addCanadaMPAddationalProds4BillingInfo() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside addCanadaMPAddationalProds4BillingInfor() method.");
		
		addNewCCDetails();
		addCANewBillingAddress();
		useSameAddressForShipping(prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping"));
		addAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPAddationalProducts2"));
		
		scrollDown("linkText", "Continue");
		clickOnElement("linkText", "Continue");
		Thread.sleep(25000);
		
		String isShipAndBillSame = prop.getLocatorForEnvironment(appUrl, "EnrollMPCAUseSameAddressForShipping");
		if(isShipAndBillSame.equalsIgnoreCase("yes")) {
			selectBillingAddressMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPCABillingAddressMode"));
		} else if(isShipAndBillSame.equalsIgnoreCase("no")) {
			selectBillingAndShippingMode(prop.getLocatorForEnvironment(appUrl, "EnrollMPCABillingAddressMode"),prop.getLocatorForEnvironment(appUrl, "EnrollMPCAShippingAddressMode"));
			//selectBillingAndShipping("Suggested Address","Suggested Address");
		} else {
			logInfo("invalid option selected");
		}
		
		clickOnElement("linkText", "Continue"); // remove comment later
		Thread.sleep(25000);
	}
	
	
	public void addAddationalProds4MPCAMarket() throws  IOException, Exception {
		logInfo("inside addAddationalProds4MPCAMarket() method");
		
		addVolumeSystem4Add2Cart();
		validateMPVolumeSystem4CanadaMarket();
		
		addVolumeSystem4Add2Flex();
		validateMPFlexVolume4CanadaMarket();
		
		clickOnElement("linkText", "Continue"); // remove comment later
		Thread.sleep(15000);
	}
	
	
	public void addCANewBillingAddress() throws  Exception {
		logInfo("insdie addCANewBillingAddress() method");
		
		clickOnElement("xpath","//a[contains(text(),'Add Address')]");		// Add Address link
		waitOnElement("cssSelector",ccAddress);
		inputTextClear("cssSelector",ccAddress);
		inputText("cssSelector",ccAddress,"234 Laurier Avenue West");
		inputTextClear("cssSelector",ccCity);
		inputText("cssSelector",ccCity,"Ottawa");
		selectFromDropdown("cssSelector",ccState,"byVisibleText","Ontario");
		inputTextClear("cssSelector",ccZipCode);
		inputText("cssSelector",ccZipCode,"K1A 0G9");
		selectFromDropdown("cssSelector",ccCountry,"byVisibleText","Canada");
		Thread.sleep(5000);
	}
	
	
	
	public void selectMPProductPackSelectionCAMarket() throws  Exception {
		logInfo("inside selectMPProductPackSelectionCAMarket() method.");
	
		WebElement paneProdPack = driver().findElement(By.xpath("//div[@class='starter-kits-table-container']"));
		List<WebElement> allProducts = paneProdPack.findElements(By.cssSelector("div.starter-kit"));
		int total_prods = allProducts.size();
		boolean isProdSelected = false;
		
		String before_prodName = "//div[@class='starter-kits-table-container']/div[";
		String after_prodName = "]/div[@class='row']/div[2]/h3";
		
		String before_select = "//div[@class='starter-kits-table-container']/div[";
		String after_select = "]/div[@class='row']/div[3]/div/a/label";
		
		String before_price = "//div[@class='starter-kits-table-container']/div[";
		String after_price = "]/div[@class='row']/div[3]/h1";
		
		if(total_prods > 0){
			// for(int i=total_prods;i>=1;i--){
			for(int i=1;i<=total_prods;i++){
				System.out.println("i =" +i);
				WebElement lbl = driver().findElement(By.xpath(before_select+i+after_select));
				WebElement p = driver().findElement(By.xpath(before_prodName+i+after_prodName));
				String lblstatus = lbl.getText().trim();
				String prod = p.getText().trim();
				System.out.println("prod =" +prod);
					if(i==1 && lblstatus.equalsIgnoreCase("Selected")) {
						logInfo(prod + " Highest product is selected.");
						System.out.println(prod +" Highest product is selected.");
						isProdSelected=true;
						break;
					}
				}
		 }
		
		if(isProdSelected==false) {
			logInfo("Highest product not selected.");
			Assert.assertTrue(isProdSelected, "Highest product not selected.");
		}
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(10000);
	}
	
	
	public void submitAndReviewMP4CAMarket(String essNo, String email) throws Exception {
		logInfo("inside submitAndReviewMP4CAMarket() method.");
	
		selectShippingMethod4CAMarket("Ground");
		selectShippingMethod4CAMarket("2 Day");
		validateMPPersonalInfOnCAMarketReviewAndSubmit(essNo, email); //prop.getLocatorForEnvironment(appUrl,"EnrollMPCAEssn")+num);
		//validateBillingAndShippingAddressInfo(prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping"),prop.getLocatorForEnvironment(appUrl,"EnrollMPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollMPShippingAddressMode"));
		selectToc(true,true);
		scrollDown("linkText", "Submit");
		clickOnElement("linkText", "Submit");
		Thread.sleep(15000);	// uncomment later
		driver().navigate().refresh();
		Thread.sleep(15000);
	}
	
	
	public void selectShippingMethod4CAMarket(String shippingMethod) throws InterruptedException, Exception {
		logInfo("inside selectShippingMethod4CAMarket() method");
		
		switch(shippingMethod) {
		
		case "Ground":
			//selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_shipping_method_1","byVisibleText","Ground");
			selectRadioOrCheckbox("xpath","//div[@class='shipping_methods']/div/span[1]/label/input[@name='pyr_core_v2_enrollment[shipping_method]']");
			Thread.sleep(5000);
			//validateTextOnElement("cssSelector","table.formatted_totals>tbody>tr:nth-child(2)> td:nth-child(2)","$26.99");
			//Thread.sleep(5000);
			break;
		case "2 Day":
			//selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_shipping_method_3","byVisibleText","Two Day");
			selectRadioOrCheckbox("xpath","//div[@class='shipping_methods']/div/span[2]/label/input[@name='pyr_core_v2_enrollment[shipping_method]']");
			Thread.sleep(5000);
			//validateTextOnElement("cssSelector","table.formatted_totals>tbody>tr:nth-child(2)> td:nth-child(2)","$39.99");
			//Thread.sleep(5000);
			break;
		}
	}

	
	public void validateMPPersonalInfOnCAMarketReviewAndSubmit(String essNo, String email) throws  Exception {
		logInfo("inside validateMPPersonalInfOnCAMarketReviewAndSubmit() method.");
		
		// validate personal details 
		//validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[1]/div[1]/div",prop.getLocatorForEnvironment(appUrl,"newsCon1"));  // review-sub-page clearfix
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPCAFName")+num);
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPCALName")+num);
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[2]/div/span[1]","12/12/1991 12:00 AM");
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPHome"));  // day phone
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPBusinessType"));
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[5]/div[2]/div/span[1]",essNo);  
		validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[6]/div[1]/div/span[1]",email);
		
		// validate website details 
		
		// validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[7]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPWebsite")+num);
	}
	
	public void validateVolumeSystem4Add2CartCAMarket() throws  IOException, Exception {
		logInfo("inside validateVolumeSystem4Add2CartCAMarket() method.");
		
		boolean isProd1Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName1"));
		if(isProd1Found==true) {
			addProducts2CartOrFlex(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName1"));
			addProducts2Flexship(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName1"));
		}
		
		boolean isProd2Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName2"));
		if(isProd2Found==true) {
			addProducts2CartOrFlex(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName2"));
			addProducts2Flexship(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName1"));
		}
		
		boolean isProd3Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName3"));
		if(isProd3Found==true) {
			addProducts2CartOrFlex(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName3"));
			addProducts2Flexship(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName1"));
		}
		
		confirmToOk();
		Thread.sleep(6000);
		
		
		if(prop.getLocatorForEnvironment(appUrl, "EnrollHandlePromoAlert").equalsIgnoreCase("yes")) {
			WebElement promodialog = driver().findElement(By.cssSelector("div#modal_popup_with_promo_items > div > div > div > button.close"));
			if(promodialog.isDisplayed() ) {
				promodialog.click();
				Thread.sleep(5000);
				}
			}
			
			if(prop.getLocatorForEnvironment(appUrl, "EnrollHandleTravelAlert").equalsIgnoreCase("yes")) {
				WebElement travelPromoDialog = driver().findElement(By.xpath("//*[@id='modal_popup_with_travel_promo_items']/div/div[1]/div/button/i"));
				if(travelPromoDialog.isDisplayed()) {
					travelPromoDialog.click();
					Thread.sleep(5000);
				  }
			}
		
		
		//validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(1) > div","VOLUME SYSTEM");
		//validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(2) > div:nth-child(2)","$90.00");
	
		//clickOnElement("linkText", "Continue");
		//Thread.sleep(10000);
	}
	
	public void validateVolumeSystem4Add2FlexCAMarket() throws  IOException, Exception {
		logInfo("inside validateVolumeSystem4Add2FlexCAMarket() method.");
		
		boolean isProd1Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName1"));
		if(isProd1Found==true) {
			addProducts2Flexship(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName1"));
		}
		
		boolean isProd2Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName2"));
		if(isProd2Found==true) {
			addProducts2Flexship(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName2"));
		}
		
		boolean isProd3Found = searchAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName3"));
		if(isProd3Found==true) {
			addProducts2Flexship(prop.getLocatorForEnvironment(appUrl, "EnrollMPProdCategory"), prop.getLocatorForEnvironment(appUrl, "EnrollMPProdSKUName3"));
		}
		
		confirmToOk();
		Thread.sleep(6000);
		
		//validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(1) > div","VOLUME SYSTEM");
		//validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(2) > div:nth-child(2)","$90.00");
	
		//clickOnElement("linkText", "Continue");
		//Thread.sleep(10000);
	}
	
	
	public void validateMPVolumeSystem4CanadaMarket() throws  IOException, Exception {
		logInfo("inside validateMPVolumeSystem4CanadaMarket() method.");
		
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(2) > div:nth-child(2)","$90.00");
	
		
		//clickOnElement("linkText", "Continue");
		Thread.sleep(5000);
	}
	
	
	public void validateMPFlexVolume4CanadaMarket() throws InterruptedException {
		logInfo("inside validateMPFlexVolume4CanadaMarket() method.");
		
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(1) > div","VOLUME SYSTEM");
		validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(2) > div:nth-child(2)","$90.00");
	
		clickOnElement("linkText", "Continue");
		Thread.sleep(25000);
	}

	
	/*public void submitVIPCAPersonalInfo() throws  Exception {		// monatEnrollmentStep3
		logInfo("inside submitVIPCAPersonalInfo() method.");
		
		waitOnElement("cssSelector", enrollFirstName);
	//	selectSponsor(prop.getLocatorForEnvironment(appUrl,"newsFN1")); // when login as user
		inputTextClear("cssSelector", enrollFirstName);
		inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollVIPFName")+num);
		inputTextClear("cssSelector",enrollLastName);
		inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollVIPLName")+num);
		inputTextClear("cssSelector", eBirthDay);
		inputText("cssSelector", eBirthDay, "12/12/1991");
		inputTextClear("cssSelector", eHome);
		inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollVIPHome"));
		inputTextClear("cssSelector", eSSN);
		inputText("cssSelector", eSSN, prop.getLocatorForEnvironment(appUrl,"EnrollVIPEssn") + num);
		inputTextClear("cssSelector", enrollEmail);
		inputText("cssSelector",enrollEmail,"mntcavip"+num+"@icentris.com");
		selectFromDropdown("cssSelector",enrollGender,"byVisibleText","Male");
		//inputTextClear("cssSelector",monatuserName);
		//inputText("cssSelector",monatuserName,"monatvip"+num);
		validateCanadaWebsiteName("jayadev.m",false);
		validateCanadaWebsiteName("VIP",true);  // prop.getLocatorForEnvironment(appUrl,"EnrollMPWebsite")+num
		
		inputTextClear("cssSelector", enrollPassword);
		inputText("cssSelector",enrollPassword,"password1");
		inputTextClear("cssSelector",enrollConfirmPassword);
		inputText("cssSelector",enrollConfirmPassword,"password1");
		
		clickOnElement("linkText", "Continue");
		Thread.sleep(10000);
	}*/
	
	public void submitAndReviewAddationalProdsMP4CAMarket(String essNo, String email) throws Exception {
		logInfo("inside submitAndReviewAddationalProdsMP4CAMarket() method.");
		
	
		selectShippingMethod4CAMarket("Ground");
		//validateTextOnElement("cssSelector","table.formatted_totals>tbody>tr:nth-child(2)> td:nth-child(2)","$0.00");  // $26.99
		selectShippingMethod4CAMarket("2 Day");
		//validateTextOnElement("cssSelector","table.formatted_totals>tbody>tr:nth-child(2)> td:nth-child(2)","$39.99");
		validateMPPersonalInfOnCAMarketReviewAndSubmit(essNo, email); //prop.getLocatorForEnvironment(appUrl,"EnrollMPCAAddProdEssn")+num);
		//validateBillingAndShippingAddressInfo(prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping"),prop.getLocatorForEnvironment(appUrl,"EnrollMPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollMPShippingAddressMode"));
		selectToc(true,true);
		scrollDown("linkText", "Submit");
		clickOnElement("linkText", "Submit");
		Thread.sleep(25000);			
		driver().navigate().refresh();
		Thread.sleep(15000);
	}
	
	
	 public void validateVIPVolumeSystem2Cart4CanadaMarket() throws InterruptedException {
			logInfo("inside validateVIPVolumeSystem2Cart4CanadaMarket() method.");
			
			validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row:nth-child(2)  > div > div.row:nth-child(1) > div","VOLUME SYSTEM");
			validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row:nth-child(2)  > div > div.row:nth-child(2) > div:nth-child(2)","$110.00");
		
			//clickOnElement("linkText", "Continue");
			//Thread.sleep(15000);
	  }
	 
	 
	 public void validateVIPVolumeSystem4Flex4CanadaMarket() throws InterruptedException {
			logInfo("inside validateVIPVolumeSystem4Flex4CanadaMarket() method.");
			
			validateTextOnElement("cssSelector","div.enrollment-cart-container > div#autoship_details_in_minicart > div.autoship-items > div.row > div.col-md-9 > div.row:nth-child(1) > div","VOLUME SYSTEM");
			validateTextOnElement("cssSelector","div.enrollment-cart-container > div#autoship_details_in_minicart > div.autoship-items > div.row > div.col-md-9 > div:row:nth-child(2) > div.col-md-6.col-sm-6.col-xs-6.text-right","$110.00");
		
			clickOnElement("linkText", "Continue");
			Thread.sleep(10000);
		}
	 
	 
	 public void addNewVIPBillingInfo4CanadaMarket() throws  Exception {		// monatEnrollmentStep3
			logInfo("inside addNewVIPBillingInfo4CanadaMarket() method.");
			addNewCCDetails();
			addCANewBillingAddress();
			useSameAddressForShipping(prop.getLocatorForEnvironment(appUrl, "EnrollVIPUseAddressForShipping"));
			clickOnElement("linkText", "Continue");
			Thread.sleep(4000);
			
			String isShipAndBillSame = prop.getLocatorForEnvironment(appUrl, "EnrollVIPUseAddressForShipping");
			if(isShipAndBillSame.equalsIgnoreCase("yes")) {
				selectBillingAddressMode(prop.getLocatorForEnvironment(appUrl,"EnrollVIPBillingAddressMode"));
			} else {
				selectBillingAndShippingMode(prop.getLocatorForEnvironment(appUrl,"EnrollVIPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollVIPShippingAddressMode"));
			}
			clickOnElement("linkText", "Continue");
			Thread.sleep(15000);
		}
	 
	 
	 /*public void validateVIPPersonalInfOnReviewAndSubmit4CanadaMarket() throws  Exception {
			logInfo("inside validateVIPPersonalInfOnReviewAndSubmit4CanadaMarket() method.");
			
			// validate personal details 
			//validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[1]/div[1]/div",prop.getLocatorForEnvironment(appUrl,"newsCon1"));  // review-sub-page clearfix
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPCAFName")+num);
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPCALName")+num);
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[1]/div/span[1]","12/12/1991 12:00 AM");
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPHome"));  // day phone
			//validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[5]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPEssn")+num);
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPCAEmail")+num+"@icentris.com");
			
			// validate website details 
			
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[6]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPCAWebsite")+num);
		}*/
	 
	 
	 public void validateVIPPersonalInfoReviewAndSubmit(String market, String email) throws  Exception {
			logInfo("inside validateVIPPersonalInfoReviewAndSubmit() method.");
			
			switch(market) {
			case "US" :
				//validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[1]/div[1]/div",prop.getLocatorForEnvironment(appUrl,"newsCon1"));  // review-sub-page clearfix
				validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPUSFName")+num);
				validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPUSLName")+num);
				validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[1]/div/span[1]","12/12/1991 12:00 AM");
				validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPHome"));  // day phone
				validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[2]/div/span[1]",email);
				// validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[6]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPUSWebsite")+num);
				break;
			case "CA" :
				//validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[1]/div[1]/div",prop.getLocatorForEnvironment(appUrl,"newsCon1"));  // review-sub-page clearfix
				validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPCAFName")+num);
				validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPCALName")+num);
				validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[1]/div/span[1]","12/12/1991 12:00 AM");
				validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPHome"));  // day phone
				validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[2]/div/span[1]",email);
				validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[6]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollVIPCAUserName")+num);
			}
			
		}
	 
	/* public void updateCanadaRetailPersonalInformation() throws  Exception {		// monatEnrollmentStep3
			logInfo("inside updateCanadaRetailPersonalInformation() method.");
			waitOnElement("cssSelector", enrollFirstName);
		//	selectSponsor(prop.getLocatorForEnvironment(appUrl,"EnrollRetailSponsorConsultID"));
			inputText("cssSelector", enrollFirstName,prop.getLocatorForEnvironment(appUrl,"EnrollRetailFName")+num);
			inputText("cssSelector", enrollLastName,prop.getLocatorForEnvironment(appUrl,"EnrollRetailLName")+num);
		//	inputText("cssSelector", eCell,"12345678");
			inputText("cssSelector", eHome,prop.getLocatorForEnvironment(appUrl,"EnrollRetailHome"));
			inputText("cssSelector", eBirthDay, "12/12/1991");
			inputText("cssSelector",enrollEmail, prop.getLocatorForEnvironment(appUrl,"EnrollRetailEmail")+num+"@icentris.com");
			
			inputText("cssSelector", eSt1, "234 Laurier Avenue West");
			inputText("cssSelector", eCity, "Ottawa");
			selectFromDropdown("cssSelector",eState,"byVisibleText","Ontario");
			inputText("cssSelector", ePost, "K1A 0G9");
			selectFromDropdown("cssSelector",eCountry,"byVisibleText","Canada");
			
			
			inputText("cssSelector",monatuserName,"monatretail"+num);
			inputText("cssSelector",enrollPassword,"password1");
			inputText("cssSelector",enrollConfirmPassword,"password1");
			
			clickOnElement("linkText", "Continue");
			Thread.sleep(20000);
		}*/
		
	 public void validateRetailVolumeSystem4Add2Cart4CAMarket() throws  IOException, Exception {
			logInfo("inside validateRetailVolumeSystem4Add2Cart4CAMarket() method.");
			
			validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(1) > div","VOLUME SYSTEM");
			validateTextOnElement("cssSelector","div.enrollment-cart-container > div:nth-child(1) > div.order-items > div.row > div:nth-child(2) > div.row:nth-child(2) > div:nth-child(2)","$129.00");
		
			clickOnElement("linkText", "Continue");
			Thread.sleep(10000);
		}
	 
	 public void addRetialAddationalProducts2Cart4CanadaMarket() throws IOException, Exception {
			logInfo("inside addRetialAddationalProducts2Cart4CanadaMarket() method.");
			
			addVolumeSystem4Add2Cart();
			validateRetailVolumeSystem4Add2Cart4CAMarket();
		}
	 
	 public void addCanadaRetailBillingInfo() throws  Exception {		
			logInfo("inside addCanadaRetailBillingInfo() method.");
			addNewCCDetails();
			validateCARetailBillingInfo();
			selectRetailBillingAddress();
			//selectShippingAddress();
			useSameAddressForRetailShipping(prop.getLocatorForEnvironment(appUrl, "EnrollRetailUseSameAddressForShipping"));
			
			String isShipAndBillSame = prop.getLocatorForEnvironment(appUrl, "EnrollRetailUseSameAddressForShipping");
			if(isShipAndBillSame.equalsIgnoreCase("yes")) {
				//selectShippingAddress();
				//validateRetailShippingInfo();
				scrollDown("linkText", "Continue");
				clickOnElement("linkText", "Continue");
				selectBillingAddressMode(prop.getLocatorForEnvironment(appUrl, "EnrollRetailBillingAddressMode"));
			} else if(isShipAndBillSame.equalsIgnoreCase("no")) {
				//validateRetailShippingInfo();
				addNewRetailShippingAddress();
				scrollDown("linkText", "Continue");
				clickOnElement("linkText", "Continue");
				selectBillingAndShippingMode(prop.getLocatorForEnvironment(appUrl, "EnrollRetailBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollRetailShippingAddressMode")); 
				
			} else {
				logInfo("invalid option selected");
			}

			clickOnElement("linkText", "Continue");
			Thread.sleep(15000);
		}
	 
	 public void validateCARetailBillingInfo() throws Exception {
			logInfo("inside validateCARetailBillingInfo() method.");
		//	scrollDown("xpath","//*[@id='section_addresses_billing_0']");
			validateTextOnElement("xpath","//*[@id='address_list_pane']/section/div[2]/div/div/span[1]","234 Laurier Avenue West,");  //4295 Suwanee Mill Dr,
			validateTextOnElement("xpath","//*[@id='address_list_pane']/section/div[2]/div/div/span[2]","Ottawa, ON, K1A 0G9");  //Buford, GA, 30518
			validateTextOnElement("xpath","//*[@id='address_list_pane']/section/div[2]/div/div/span[3]","CA");
		}
	 
	 
	// ########################### UK MARKET ################################################################
	 
	 public void selectMPProductPackSelectionUKMarket() throws  Exception {
			logInfo("inside selectMPProductPackSelectionUKMarket() method.");
		
			WebElement paneProdPack = driver().findElement(By.xpath("//div[@class='starter-kits-table-container']"));
			List<WebElement> allProducts = paneProdPack.findElements(By.cssSelector("div.starter-kit"));
			int total_prods = allProducts.size();
			boolean isProdSelected = false;
			
			String before_prodName = "//div[@class='starter-kits-table-container']/div[";
			String after_prodName = "]/div[@class='row']/div[2]/h3";
			
			String before_select = "//div[@class='starter-kits-table-container']/div[";
			String after_select = "]/div[@class='row']/div[3]/div/a/label";
			
			String before_price = "//div[@class='starter-kits-table-container']/div[";
			String after_price = "]/div[@class='row']/div[3]/h1";
			
			if(total_prods > 0){
				for(int i=total_prods;i>=1;i--){
					System.out.println("i =" +i);
					WebElement lbl = driver().findElement(By.xpath(before_select+i+after_select));
					WebElement p = driver().findElement(By.xpath(before_prodName+i+after_prodName));
					String lblstatus = lbl.getText().trim();
					String prod = p.getText().trim();
					System.out.println("prod =" +prod);
						if(i==1 && lblstatus.equalsIgnoreCase("Selected")) {
							logInfo(prod + " Highest product is selected.");
							System.out.println(prod +" Highest product is selected.");
							isProdSelected=true;
							break;
						}
					}
			 }
			
			if(isProdSelected==false) {
				logInfo("Highest product not selected.");
				// Assert.assertTrue(isProdSelected, "Highest product not selected.");
			}
			
			clickOnElement("linkText", "Continue");
			Thread.sleep(10000);
		}
		
		
	 public void addUKNewBillingAddress() throws  Exception {
			logInfo("insdie addUKNewBillingAddress() method");
			
			clickOnElement("xpath","//a[contains(text(),'Add Address')]");		// Add Address link
			waitOnElement("cssSelector",ccAddress);
			inputTextClear("cssSelector",ccAddress);
			inputText("cssSelector",ccAddress,"135 Branton Street");
			inputTextClear("cssSelector",ccCity);
			inputText("cssSelector",ccCity,"Knightsbridge");
			selectFromDropdown("cssSelector",ccState,"byVisibleText","London, City of");
			inputTextClear("cssSelector",ccZipCode);
			inputText("cssSelector",ccZipCode,"SW1X 7XL");
			selectFromDropdown("cssSelector",ccCountry,"byVisibleText","United Kingdom");
			Thread.sleep(5000);
		}
	 
	 
	 public void addUKMarketPartnerBillingInformation() throws  Exception {		// monatEnrollmentStep3
			logInfo("inside addUKMarketPartnerBillingInformation() method.");
			
			addNewCCDetails();
			addUKNewBillingAddress();
			useSameAddressForShipping(prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping"));
			//addAddationalProducts(prop.getLocatorForEnvironment(appUrl, "EnrollMPAddationalProducts"));
			
			clickOnElement("linkText", "Continue");
			Thread.sleep(20000);
		}
	 
	 public void submitAndReviewMP4UKMarket(String essNo, String email) throws Exception {
			logInfo("inside submitAndReviewMP4UKMarket() method.");
		
			//selectShippingMethod4CAMarket("Ground");
			//selectShippingMethod4CAMarket("2 Day");
			validateMPPersonalInfoUKMarketReviewAndSubmit(essNo, email); 
			//validateBillingAndShippingAddressInfo(prop.getLocatorForEnvironment(appUrl, "EnrollMPUseSameAddressForShipping"),prop.getLocatorForEnvironment(appUrl,"EnrollMPBillingAddressMode"),prop.getLocatorForEnvironment(appUrl,"EnrollMPShippingAddressMode"));
			selectUKToc(true,true);
			scrollDown("linkText", "Submit");
			clickOnElement("linkText", "Submit");
			Thread.sleep(15000);	// uncomment later
			driver().navigate().refresh();
			Thread.sleep(15000);
		}
	 
	 
	public void validateMPPersonalInfoUKMarketReviewAndSubmit(String essNo, String email) throws  Exception {
			logInfo("inside validateMPPersonalInfOnUKMarketReviewAndSubmit() method.");
			
			// validate personal details 
			//validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[1]/div[1]/div",prop.getLocatorForEnvironment(appUrl,"newsCon1"));  // review-sub-page clearfix
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPUKFName")+num);
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[2]/div[2]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPUKLName")+num);
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[3]/div[1]/div/span[1]","12/12/1991 12:00 AM");
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[4]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPHome"));  // day phone
			validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[5]/div[1]/div/span[1]",email);
			
			// validate website details 
			
			// validateTextOnElement("xpath","//*[@class='review-sub-page review_personal_information'][1]/div[6]/div[1]/div/span[1]",prop.getLocatorForEnvironment(appUrl,"EnrollMPWebsite")+num);
		}
	
	
	public void selectUKToc(boolean MPAgreement, boolean MPDownloadPolicies) throws  Exception {
		logInfo("inside selectUKToc() method");
		
		waitOnElement("cssSelector","#pyr_core_v2_enrollment_tac2");
		WebElement ag = driver().findElement(By.cssSelector("#pyr_core_v2_enrollment_tac2"));
		if(!ag.isSelected() && MPAgreement==true ) {
			clickOnElement("cssSelector","#pyr_core_v2_enrollment_tac2");
		}
			
		WebElement pol = driver().findElement(By.cssSelector("#pyr_core_v2_enrollment_toc_acceptance"));
		if(!pol.isSelected() && MPDownloadPolicies==true ) {
			clickOnElement("cssSelector","#pyr_core_v2_enrollment_toc_acceptance");
		}
	}
	 
	 
	// #############################################################################################


		public void monatChooseYourPack() throws Exception {		// monatEnrollmentStep1
			logInfo("inside monatChooseYourPack()  method");
			selectStarterKit(prop.getLocatorForEnvironment(appUrl, "pwpEnrollStarterKitSelection"));
			//enterWebsiteDetails();
			waitOnElement("linkText", "Continue");
			clickOnElement("linkText", "Continue");
			Thread.sleep(5000);
		}
		
		public void monatEnrollmentStep2() throws  Exception {
			logInfo("inside monatEnrollmentStep2() method.");
			
			// Search SKU or ProductName and Add to Bag
			searchProductByNameOrSKU("Products and Systems","Revive Shampoo");
		}
		
		public void monatPersonalInfo() throws  Exception {		// monatEnrollmentStep3
			logInfo("inside monatEnrollmentStep3() method.");
			waitOnElement("cssSelector", eFirst);
			inputText("cssSelector", eFirst, prop.getLocatorForEnvironment(appUrl, "pwpEnrollFName"));
			inputText("cssSelector", eLast, prop.getLocatorForEnvironment(appUrl, "pwpEnrollLastName"));
			inputText("cssSelector", eBirthDay, "12/12/1991");
			inputText("cssSelector", eSSN, "83789" + TestBase.generateRandomNumberInRange(1000, 9999));
			inputText("cssSelector", eHome, "1234567");
			inputText("cssSelector", eEmail,"vibe.icentris@gmail.com");
			selectFromDropdown("cssSelector", eGender,"byVisibleText","Male");
			selectFromDropdown("cssSelector", enrollmentlanguagePreference,"byVisibleText","English");
			enterWebsiteDetails();
			inputText("cssSelector", ePswd, "password1");
			inputText("cssSelector", eConPswd, "password1");
			clickOnElement("linkText", "Continue");
			Thread.sleep(5000);
		}

		
		public void monatBillingAndShipping() throws  Exception {		// monatEnrollmentStep4
			logInfo("inside monatEnrollmentStep4 method");
			
			enterCreditCardInfo();
			submitCCAddressDetails();
			// selectSubscriptionPlan(prop.getLocatorForEnvironment(appUrl, "pwpEnrollSubscriptionPlan"));
			
			clickOnElement("linkText", "Continue");
			waitOnElement("xpath","//input[@value='Proceed']");
			clickOnElement("xpath","//input[@value='Proceed']");
			clickOnElement("linkText", "Continue");
			Thread.sleep(5000);
		}
		
		
	public void selectStarterKit(String packName) throws  Exception{  // String prodName
		logInfo("inside selectStarterKit() method.");
		
		waitOnElement("xpath","//div[@class='starter-kits-table-container']");
		WebElement paneProdPack = driver().findElement(By.xpath("//div[@class='starter-kits-table-container']"));
		List<WebElement> allProducts = paneProdPack.findElements(By.cssSelector("div.starter-kit"));
		int total_prods = allProducts.size();
		
		String before_prodName = "//div[@class='starter-kits-table-container']/div[";
		String after_prodName = "]/div[@class='row']/div[2]/h3";
		
		String before_select = "//div[@class='starter-kits-table-container']/div[";
		String after_select = "]/div[@class='row']/div[3]/div/a/label";
		
		if(total_prods > 0){
			for(int i=1;i<=total_prods;i++){
				WebElement prod = driver().findElement(By.xpath(before_prodName+i+after_prodName));
				scrollDown("xpath",before_prodName+i+after_prodName);
				String actProdName = prod.getText().trim();
				System.out.println("Prod = " + actProdName);
				if(actProdName.equalsIgnoreCase(packName)){
					scrollDown("xpath",before_select+i+after_select);
					WebElement select = driver().findElement(By.xpath(before_select+i+after_select));
					select.click();
					clickOnElement("linkText", "Continue");
					Thread.sleep(5000);
					logInfo(actProdName +"is selected");
					break;
				}
			}
		}
		
	}
	
		
}
