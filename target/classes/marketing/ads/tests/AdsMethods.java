package vibe.marketing.ads.tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import common.TestBase;
import vibe.billing.tests.BillingMethods;
import vibe.feedBack.tests.FeedBackMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.tasks.tests.TaskMethods;
import vibe.users.tests.UsersMethods;

public class AdsMethods extends TestBase{

	TaskMethods tm = new TaskMethods();
	NewsMethods nm = new NewsMethods();
	UsersMethods um = new UsersMethods();
	BillingMethods bm = new BillingMethods();
	FeedBackMethods fm = new FeedBackMethods();
	
	public void adsCreation(String url, String ranker, String[] lang,String[] subscription, 
			                String typePublishImdOrNull, String typeWidgetOrShopOrBoth ) throws  InterruptedException, IOException, AWTException, Exception{
		logInfo("Entered adsCreation() method");	
		navigateConfigureAds();
		newAddCreation(url);	 
	 	defineAllRankers();
	 	marketLanguages(lang);
	 	subscriptionPlanSlection(subscription);
	 	selectPublishImmd(typePublishImdOrNull);
	 	adsVisibility(typeWidgetOrShopOrBoth); 		
	 	scrollDown("cssSelector", adAddBannerUpdate);
		waitOnElement("cssSelector", adAddBannerUpdate);
		clickOnButton("cssSelector", adAddBannerUpdate);
		Thread.sleep(6000);	
	 	
	}
	
	
	public void expiredAdCreation(String url, String ranker, String[] lang,String[] subscription, 
            String typePublishImdOrNull, String typeWidgetOrShopOrBoth) throws Exception {		
		logInfo("Entered expiredAdCreation() method");	
		navigateConfigureAds();
		newAddCreation(url);	 
	 	defineAllRankers();
	 	marketLanguages(lang);
	 	subscriptionPlanSlection(subscription);	 
	 	adsVisibility(typeWidgetOrShopOrBoth);	
	 	Thread.sleep(2000);
	 	scrollDown("cssSelector",publishDate);
	 	clickOnElement("cssSelector",publishDate);
	 	clickOnElement("cssSelector",publishDate);
	 	clickOnElement("cssSelector",expireDate);
	 	clickOnElement("cssSelector",expireDate);	 	
		inputTextClear("cssSelector",adURL);
		inputText("cssSelector",adURL, url );
		Thread.sleep(3000);
	 	scrollDown("cssSelector", adAddBannerUpdate);
		waitOnElement("cssSelector", adAddBannerUpdate);
		clickOnButton("cssSelector", adAddBannerUpdate);
		Thread.sleep(4000);	
		
	}
	
	public void updateBanner() throws Exception{
		logInfo("Entered updateBanner() method");
		scrollDown("cssSelector", adAddBannerUpdate);
		waitOnElement("cssSelector", adAddBannerUpdate);
		clickOnButton("cssSelector", adAddBannerUpdate);
		Thread.sleep(4000);			
		confirmationMessage("Banner updated successfully.");
	 	assertTitle("Company Ads");
	}
	
	
	public void newAddCreation(String url) throws  Exception{
		logInfo("Entered into newAddCreation() method");
		    scrollDown("cssSelector",addNewAdLnk);
			waitOnElement("cssSelector",addNewAdLnk);
			clickOnButton("cssSelector",addNewAdLnk);
			waitOnSpinner();
			Thread.sleep(2000);
			inputTextClear("cssSelector",adURL);
			inputText("cssSelector",adURL, url );	
			waitOnSpinner();
			Thread.sleep(2000);			
			uploadFile("Image",adClickToUpload);
			waitOnSpinner();			
			Thread.sleep(2000);
			clickOnElement("xpath", adTaxons);
			List <WebElement> tax = driver().findElements(By.cssSelector(adTaxonsDD));		
			for (WebElement taxon :tax){	
				if(!taxon.isSelected()){
					taxon.click();
				}			
			}
	}
	
	
	public void defineAllRankers() throws  InterruptedException{
		logInfo("Entered into defineAllRankers() method");
		try{			
			clickOnElement("cssSelector", adRankDefine);
			Thread.sleep(1000);				
			WebElement all = driver().findElement(By.cssSelector("#rank_all"));
			if(!all.isSelected()){
				all.click();						
				}
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to define Ranks.");
		}
	}
	
	
	public void marketLanguages(String[] language) throws Exception{
		logInfo("Entered into marketLanguages() method");
		try{
		clickOnElement("cssSelector", adMarketLang);
		waitOnElement("cssSelector", adMarketLangChkBox);
		WebElement all = driver().findElement(By.cssSelector("#market_all"));
		if(all.isSelected()){
			all.click();
			Thread.sleep(2000);
				}	
		
		boolean islangPresent = false;		
		int langSize = language.length;		
		List <WebElement> lang = driver().findElements(By.cssSelector(adMarketLangChkBox));			
		Thread.sleep(3000);
		for (WebElement langs : lang){			
			for (int i=0; i<=langSize-1; i++ ){
			if (langs.getText().equalsIgnoreCase(language[i])){
				islangPresent =true;
				if(!langs.isSelected()){
				langs.click();			
				break;
			}	}}
		}if (islangPresent ==false){
			Assert.assertTrue(islangPresent, language + "- languague is not present.");
		}
		
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select Market Language.");
		}				
	}
	
	public void subscriptionPlanSlection(String[] subcriptionPlan) throws Exception{
		logInfo("Entered into subscriptionPlanSlection() method");	
			try{
			 clickOnElement("xpath", adSubPlan);
			 WebElement all = driver().findElement(By.cssSelector("#subscription_all"));
			 if(all.isSelected()){
				all.click();
				Thread.sleep(2000);
				}	
			int subscrib= subcriptionPlan.length;
			boolean isSubPresent = false;
			List <WebElement> sub = driver().findElements(By.cssSelector(adSubPlanChkBox));	
			for (WebElement subPlan : sub){				
				for (int i=0; i<=subscrib-1; i++){				
				if ((subPlan.getText()).equalsIgnoreCase(subcriptionPlan[i])){	
					isSubPresent =true;
					if(!subPlan.isSelected()){						
						subPlan.click();
						break;
									
					}
				}
			}}if (isSubPresent==false){
				Assert.assertTrue(isSubPresent, subcriptionPlan + " - subscriptions are not present" );
			}}catch (WebDriverException we ){			
				System.err.println("Failed!! Not able to select subscriptionPlan.");
			}	
				
	}	
	
	
	public void modifyMarketLanguages(String[] language) throws Exception{
		logInfo("Entered into modifyMarketLanguages() method");
		try{
		waitOnElement("cssSelector", adMarketLang);	
		clickOnElement("cssSelector", adMarketLang);
		waitOnElement("cssSelector", adMarketLangChkBox);
		WebElement all = driver().findElement(By.cssSelector("#market_all"));
		if(all.isSelected()){
			all.click();
			Thread.sleep(2000);
				}else{
					all.click();
					Thread.sleep(2000);
					all.click();					
				}		
		boolean islangPresent = false;		
		int langSize = language.length;		
		List <WebElement> lang = driver().findElements(By.cssSelector(adMarketLangChkBox));	
		Thread.sleep(3000);
		for (WebElement langs : lang){			
			for (int i=0; i<=langSize-1; i++ ){
			if (langs.getText().equalsIgnoreCase(language[i])){
				islangPresent =true;
				if(!langs.isSelected()){
				langs.click();			
				break;
			}	}}
		}if (islangPresent ==false){
			Assert.assertTrue(islangPresent, language + "- languague is not present.");
		}
		
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select Market Language.");
		}				
	}
	
	public void modifySubscriptionPlanSlection(String[] subcriptionPlan) throws Exception{
		logInfo("Entered into modifySubscriptionPlanSlection() method");	
			try{
				clickOnElement("xpath", adSubPlan);
				WebElement all = driver().findElement(By.cssSelector("#subscription_all"));
				if(all.isSelected()){
					all.click();
					Thread.sleep(2000);
					}else{
						all.click();
						Thread.sleep(2000);
						all.click();
					}
				int subscrib= subcriptionPlan.length;
				boolean isSubPresent = false;
				List <WebElement> sub = driver().findElements(By.cssSelector(adSubPlanChkBox));	
				for (WebElement subPlan : sub){
					
					for (int i=0; i<=subscrib-1; i++){							
					if ((subPlan.getText()).equalsIgnoreCase(subcriptionPlan[i])){	
						isSubPresent =true;
						if(!subPlan.isSelected()){						
							subPlan.click();				
							break;						
						}
					}
			}}if (isSubPresent==false){
				Assert.assertTrue(isSubPresent, subcriptionPlan + " - subscriptions are not present" );
			}}catch (WebDriverException we ){			
				System.err.println("Failed!! Not able to select subscriptionPlan.");
			}	
				
	}		
	
	
	public void selectPublishImmd(String typePublishYesOrNull) throws Exception, Exception{
		logInfo("Entered into selectPublishImmd() method");
				waitOnElement("cssSelector", compPublishArticle);
				if (typePublishYesOrNull==null){			 
				int FutueDate=5;
				String futDate = getDate(FutueDate, "MM/dd/yyyy");
				String ExpDate = getDate(FutueDate+5, "MM/dd/yyyy");
				inputTextClear("cssSelector", adsPubDate);
				inputText("cssSelector", adsPubDate, futDate);
				clickOnElement("cssSelector", adsPubDatePick);
				clickOnElement("cssSelector", adsPubDatePick);				
				inputTextClear("cssSelector", adsExpDate);
				inputText("cssSelector", adsExpDate, ExpDate);
				clickOnElement("cssSelector", adsExpDatePick);
				clickOnElement("cssSelector", adsExpDatePick);
				}else if(typePublishYesOrNull=="Yes"){			
					selectRadioOrCheckbox("cssSelector", compPublishArticle);	
			}
		}
	
	public void selectStatus(String status) throws  InterruptedException{
		logInfo("Entered into selectStatus() method");		
			WebElement sts = driver().findElement(By.cssSelector(adStatusDropdown));
			Select sel = new Select(sts);
			sel.selectByVisibleText(status);		
	}
	
	public void adsVisibility(String typeWidgetOrShopOrBoth) throws Exception{
		logInfo("Entered into adsVisibility() method");		
		clickOnElement("xpath", visbilty);
		waitOnElement("cssSelector", adShopVisibilityChkBox);		
		if(typeWidgetOrShopOrBoth== "Shop"){
			selectRadioOrCheckbox("cssSelector", adShopVisibilityChkBox);
		}else if (typeWidgetOrShopOrBoth=="Widget"){	
			selectRadioOrCheckbox("cssSelector", adWidgetVisibilityChkBox);			
		}else if (typeWidgetOrShopOrBoth=="Both"){	
			selectRadioOrCheckbox("cssSelector", adShopVisibilityChkBox);
			selectRadioOrCheckbox("cssSelector", adWidgetVisibilityChkBox);
		}else if(typeWidgetOrShopOrBoth==null) {			
			List<WebElement> vis = driver().findElements(By.cssSelector(visList));
			for (WebElement visb :vis) {
				if(visb.isSelected()) {
					visb.click();
				}
				
			}
			
		}
		
	}	
	
	
	public void enterPublishDate(int FutueDate) throws Exception{
		logInfo("Entered into enterPublishDate() method");
		String futDate = getDate(FutueDate, "MM/dd/yyyy");
		String ExpDate = getDate(FutueDate+5, "MM/dd/yyyy");
		inputTextClear("cssSelector", adsPubDate);
		inputText("cssSelector", adsPubDate, futDate);	
		inputTextClear("cssSelector", adsExpDate);
		inputText("cssSelector", adsExpDate, ExpDate);
	
	        }	
	
	

	public void navigateConfigureAds() throws Exception{
		logInfo("Entered into navigateConfigureAds() method");			
			driver().navigate().to(appUrl+ "/pyr_core/banners");	
			
	}
	
	public void selectAddBanner() throws Exception{
		logInfo("Entered into selectAddBanner() method");
		try{
			scrollDown("cssSelector", adAddBannerUpdate);
			waitOnElement("cssSelector", adAddBannerUpdate);
			clickOnButton("cssSelector", adAddBannerUpdate);
			Thread.sleep(4000);				
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select Add Banner Button.");
		}				
	}
	
	public void assertTitle(String exptitle) throws Exception{
		logInfo("Entered into assertTitle() method");		
		waitOnElement("cssSelector", adstitle);		
		WebElement title = driver().findElement(By.cssSelector(adstitle));
		String acttitle = title.getText().trim();
		Assert.assertEquals(acttitle, exptitle);
		
		SoftAssert sa = new SoftAssert();
		sa.assertAll();
	
	}
	
	
	public void selectBackToAds() throws Exception{
		logInfo("Entered into selectBackToAds() method");
		try{
			waitOnElement("linkText", "Back To Ads");		
			scrollDown("linkText", "Back To Ads");					
			clickOnLink("linkText", "Back To Ads");	
			}catch (WebDriverException we ){			
				System.err.println("Failed!! Not able to select Back To Ads Button.");
		}		
	}
	
	public void getAllMarketCountries() throws Exception{
		logInfo("Entered into getAllMarketCountries() method");
		try{
		clickOnButton("cssSelector", admarket);		
		List <WebElement> market = driver().findElements(By.cssSelector(admarketDrpdwn));
		for (WebElement markets: market){			
			String cont = markets.getText().trim();
			
			switch (cont) {
			case "US":
				System.out.println(cont + " is match found.");
				break;
			case "CA":
				System.out.println(cont + " is match found.");
				break;
			case "MX":
				System.out.println(cont + " is match found.");
				break;
			case "CN":
				System.out.println(cont + " is match found.");
				break;
			case "DZ":
				System.out.println(cont + " is match found.");
				break;	
			case "VN":
				System.out.println(cont +" is match found.");
				break;
			case "AX":
				System.out.println(cont +" is match found.");
				break;	
			case "AL":
				System.out.println(cont + " is match found.");
				break;
			case "IN":
				System.out.println(cont + " is match found.");
				break;
			case "FR":
				System.out.println(cont + " is match found.");
				break;
			case "AS":
				System.out.println(cont + " is match found.");
				break;
			case "AD":
				System.out.println(cont + " is match found.");
				break;	
			case "AO":
				System.out.println(cont +" is match found.");
				break;
			case "AI":
				System.out.println(cont +" is match found.");
				break;
			case "BI":
				System.out.println(cont + " is match found.");
				break;
			case "IO":
				System.out.println(cont + " is match found.");
				break;
			case "PE":
				System.out.println(cont + " is match found.");
				break;
			case "ZW":
				System.out.println(cont + " is match found.");
				break;
			case "KH":
				System.out.println(cont + " is match found.");
				break;	
			case "DM":
				System.out.println(cont +" is match found.");
				break;
			case "EC":
				System.out.println(cont +" is match found.");
				break;	
			case "AU":
				System.out.println(cont + " is match found.");
				break;
			case "JP":
				System.out.println(cont + " is match found.");
				break;
			case "AW":
				System.out.println(cont + " is match found.");
				break;
			case "BS":
				System.out.println(cont + " is match found.");
				break;	
			case "AQ":
				System.out.println(cont +" is match found.");
				break;
			case "YE":
				System.out.println(cont +" is match found.");
				break;
			case "AF":
				System.out.println(cont + " is match found.");
				break;	
			case "All":
				System.out.println(cont + " is match found.");
				break;		
			default :
				System.err.println("Country is "+cont);			
				}
			}}catch (WebDriverException we ){	
			System.err.println("Failed!! Not able to select Market countries");			
		}
	}
	
	
	public void getAllSubcriptionPlans() throws Exception{
		logInfo("Entered into getAllSubcriptionPlans() method");
		try{
		clickOnButton("cssSelector", sortSub);
		List <WebElement> market = driver().findElements(By.cssSelector(adSubcriptionsDrpdwn));
		for (WebElement markets: market){
			String sub = markets.getText().trim();
			switch (sub){
			case "Vibe Lite - Monthly":
				System.out.println(sub +" is match found.");
				break;
			case "Vibe Lite - Yearly":
				System.out.println(sub +" is match found.");
				break;
			case "Vibe Pro - Monthly":
				System.out.println(sub +" is match found.");
				break;
			case "Vibe Pro - Yearly":
				System.out.println(sub +" is match found.");
				break;	
			case "All":
				System.out.println(sub +" is match found.");
				break;		
			default:
				System.err.println("subscription is "+sub);	
				}}}catch (WebDriverException we ){	
			System.err.println("Failed!! Not able to select Subscription");
		}
	}
	
	public void getAllAvailablity() throws Exception{
		logInfo("Entered into getAllAvailablity() method");
		try{
			clickOnButton("cssSelector", sortAvail);
			List <WebElement> market = driver().findElements(By.cssSelector(adAvailableDrpdwn));
			for (WebElement markets: market){
				String avail = markets.getText().trim();	
				switch (avail){	
				case "Office":
					System.out.println(avail +" is match found.");
					break;
				case "Shop":
					System.out.println(avail +" is match found.");
					break;	
				case "All":
					System.out.println(avail +" is match found.");
					break;		
				default:
					System.err.println("Availablity  is "+avail);	
				
				}
			}
		}catch (WebDriverException we ){	
			System.err.println("Failed!! Not able to select Availability");
		}
	}
	
	public void getAllShopCategory() throws Exception{
		logInfo("Entered into getAllShopCategory() method");
		try{
			clickOnButton("cssSelector", sortShop);
			List <WebElement> market = driver().findElements(By.cssSelector(adShopCatDrpdwn));		
			for (WebElement markets: market){
				System.out.println(markets.getText());
			}
		}catch (WebDriverException we ){	
			System.err.println("Failed!! Not able to select Subscription");
		}
	}
	
	public void getAllLanguages() throws Exception{
		logInfo("Entered into getAllLanguages() method");
		try{
			clickOnButton("cssSelector", sortLang);
			List <WebElement> market = driver().findElements(By.cssSelector(adlanguageDrpdwn));			
			for (WebElement markets: market){
				System.out.println(markets.getText());
			}
		}catch (WebDriverException we ){	
			System.err.println("Failed!! Not able to select Subscription");
		}
	}
	
	public void getAllRankers() throws Exception{
		logInfo("Entered into getAllRankers() method");
		try{
			clickOnButton("cssSelector", sortRanks);
			List <WebElement> market = driver().findElements(By.cssSelector(adRankDrpdwn));		
			for (WebElement markets: market){
				System.out.println(markets.getText());
				}
		}catch (WebDriverException we ){	
			System.err.println("Failed!! Not able to select Subscription");
		}
	}
	
	public void getAllStatus() throws Exception{
		logInfo("Entered into getAllStatus() method");
		try{
			clickOnButton("cssSelector", sortStatus);
			List <WebElement> market = driver().findElements(By.cssSelector(adStatusDrpdwn));
			for (WebElement markets: market){			
				String avail = markets.getText().trim();
			switch (avail){			
			case "All":
				System.out.println(avail +" is match found.");
				break;
			case "Published":
				System.out.println(avail +" is match found.");
				break;
			case "Draft":
				System.out.println(avail +" is match found.");
				break;	
			case "Archived":
				System.out.println(avail +" is match found.");
				break;		
			default:
				System.err.println("Status is "+avail);	
			}
		}}catch (WebDriverException we ){	
			System.err.println("Failed!! Not able to select Subscription");
		}
	}
			
	public void sortSelectMarket(String country) throws Exception{
		logInfo("Entered into sortSelectMarket() method");
		try{
			clickOnElement("cssSelector", admarket);
			List <WebElement> market = driver().findElements(By.cssSelector(admarketDrpdwn));
			for (WebElement markets: market){
				if(markets.getText().equals(country)){
					markets.click();
					break;
				}
			}
		}catch (WebDriverException we ){	
			System.err.println("Failed!! Not able to select either Market or Market optionals.");
		}
	}
	
	public void selectLanguage(String Langauge) throws Exception{
		logInfo("Entered into selectLanguage() method");
		try{
		clickOnElement("cssSelector", adlanguage);
		List <WebElement> lang = driver().findElements(By.cssSelector(adlanguageDrpdwn));	
		for (WebElement langs: lang){
			if(langs.getText().equals(Langauge)){
				langs.click();
				break;
			}			
		}
			
		}catch (WebDriverException we ){	
			System.err.println("Failed!! Not able to select either langugae or langugae options.");
		}
	}
	
	public void selectSubcription(String subcription) throws Exception{
		logInfo("Entered into selectSubcription() method");
		try{
			clickOnElement("cssSelector", adSubcriptions);
			List <WebElement> sub = driver().findElements(By.cssSelector(adSubcriptionsDrpdwn));			
			for (WebElement subs: sub){
				if(subs.getText().equals(subcription)){
					subs.click();
					break;
				}			
			}
		}catch (WebDriverException we ){	
			System.err.println("Failed!! Not able to select either Subcription or subcriptions options.");
		}
	}
	
	

	// Edit Ads and validate URL , if matches url then deletes.
	public void adsStatusUnderImage(String expectedUrl, String expStatus) throws Exception{
		logInfo("Entered adsStatusUnderImage() method");			
		boolean isUrlPresent = false;
		List <WebElement> del = driver().findElements(By.cssSelector(delAds));
		int dele = del.size();			
		for (int i=dele; i>=1;i--){{			
			WebElement edits = driver().findElement(By.cssSelector(ed1+i+ed2));			
			edits.click();
			Thread.sleep(2000);				
			WebElement actUrl= driver().findElement(By.cssSelector(adURL));
			String actualUrl= actUrl.getAttribute("value");
			if (!actualUrl.equalsIgnoreCase(expectedUrl)){				
				selectBackToAds();
			}else{				
				isUrlPresent=true;	
				selectBackToAds();
				Thread.sleep(2000);
				WebElement staus = driver().findElement(By.cssSelector(ed1+i+statusAfter));	
				String adStat = staus.getText().trim();
				boolean isStatusPresent =false;
				if(adStat.equalsIgnoreCase(expStatus)) {
					isStatusPresent=true;
					break;
					}if(isStatusPresent==false) {
					Assert.assertTrue(isStatusPresent, expStatus + " status does not match");
						}
				break;
				}		
		} } if(isUrlPresent==false){		
			Assert.assertTrue(isUrlPresent,expectedUrl + " is not present" );			
		} }
	
	//Delete all Ads from Ads main Page.
		public void deleteAllAdsFromAdmin() throws  InterruptedException, Exception{
			logInfo("Entered into deleteAllAdsFromAdmin() method");
			List <WebElement> del = driver().findElements(By.cssSelector(delAds));
			int dele = del.size();				
			for (int i=del.size(); i>=1;i--){				
				WebElement delete = driver().findElement(By.cssSelector(ed1+i+dele2));			
				delete.click();
				confirmOK();				
				confirmationMessage("Banner deleted successfully.");
				Thread.sleep(4000);
		
			}
		}
	
	// Edit Ads and validate URL , if matches url then deletes.
	public void deleteAd(String expectedUrl) throws Exception{
		logInfo("Entered deleteAd(String expectedUrl) method");			
		boolean isUrlPresent = false;
		List <WebElement> del = driver().findElements(By.cssSelector(delAds));
		int dele = del.size();			
		for (int i=dele; i>=1;i--){{			
			WebElement edits = driver().findElement(By.cssSelector(ed1+i+ed2));			
			edits.click();
			waitOnElement("cssSelector", adURL);
			WebElement actUrl= driver().findElement(By.cssSelector(adURL));
			String actualUrl= actUrl.getAttribute("value");
			if (!actualUrl.equalsIgnoreCase(expectedUrl)){				
				selectBackToAds();
			}else{				
				isUrlPresent=true;	
				selectBackToAds();
				waitOnElement("cssSelector", ed1+i+dele2);
				WebElement delete = driver().findElement(By.cssSelector(ed1+i+dele2));	
				delete.click();
				confirmOK();	
				confirmationMessage("Banner deleted successfully.");					
				break;
				}		
		} } if(isUrlPresent==false){		
			Assert.assertTrue(isUrlPresent,expectedUrl + " is not present" );			
		} }
	
	
	// Edit existing Ads and verify its URL , if matches -break;
		public boolean validateAdsUrl(String expectedUrl) throws Exception{
			logInfo("Entered validateAdsUrl(String expectedUrl) method");			
			boolean isUrlPresent = false;
			waitOnSpinner();
			waitOnElement("cssSelector", delAds);
			Thread.sleep(3000);
			List <WebElement> del = driver().findElements(By.cssSelector(delAds));
			int dele = del.size();				
			for (int i=dele; i>=1;i--){{				
				WebElement edits = driver().findElement(By.cssSelector(ed1+i+ed2));			
				edits.click();
				Thread.sleep(2000);				
				WebElement actUrl= driver().findElement(By.cssSelector(adURL));
				String actualUrl= actUrl.getAttribute("value");				
				if (!actualUrl.equalsIgnoreCase(expectedUrl)){				
					selectBackToAds();
				}else{				
					isUrlPresent=true;	
					updateBanner();
					break;
					}		
			} } if(isUrlPresent==false){		
				Assert.assertTrue(isUrlPresent,expectedUrl + " is not present" );			
			}
			return isUrlPresent;
        }
		
		
		// Edit existing Ads and verify its URL , if matches -break;
				public void adsNotToPresentInAdmin(String expectedUrl, String language) throws Exception{
					logInfo("Entered adsNotToPresentInAdmin(String expectedUrl) method");					
					waitOnSpinner();
					waitOnElement("cssSelector", delAds);
					List <WebElement> del = driver().findElements(By.cssSelector(delAds));
					int listSize = del.size();					
					if(listSize==0){
						WebElement rec = driver().findElement(By.cssSelector(noRecord));
						String actRec = rec.getText().trim();						
						Assert.assertEquals(actRec, noRecordExp);						
						}else{					
						boolean isUrlPresent= true;
					    for (int i=listSize;i>=1;i--){{				
						WebElement edits = driver().findElement(By.cssSelector(ed1+i+ed2));			
						edits.click();
						Thread.sleep(2000);				
						WebElement actUrl= driver().findElement(By.cssSelector(adURL));
						String actualUrl= actUrl.getAttribute("value");					
						if (!actualUrl.equalsIgnoreCase(expectedUrl)){				
							selectBackToAds();
							sortSelectLanguages(language);
						}else{				
							isUrlPresent=false;		
							Assert.assertTrue(isUrlPresent,expectedUrl + " is still present" );	
							break;
							}		
					} } if(isUrlPresent==true){		
							System.out.println("url is not present");	
					}}
					
		        }
		
		public void validateFieldPublishImmd() throws  Exception{
			logInfo("Entered into validateField() method");
			String text = "Publish this article immediately";
			Thread.sleep(3000);
			waitOnElement("cssSelector", adsPubText);
			WebElement chBox = driver().findElement(By.cssSelector(adsPubText));			
			Assert.assertEquals(chBox.getText(), text);
			selectAddBanner();
			confirmationMessage("Banner updated successfully.");			
		}	
	
		public void editExistingAds(String expectedUrl) throws Exception{
			logInfo("Entered into editExistingAds() method");    	
			List <WebElement> del = driver().findElements(By.cssSelector(delAds));
			int dele = del.size();			
			for (int i=dele; i>=1;i--){				
				WebElement edits = driver().findElement(By.cssSelector(ed1+i+ed2));				
				edits.click();
				Thread.sleep(2000);
				boolean isUrlPresent = false;
				WebElement actUrl= driver().findElement(By.cssSelector(adURL));
				String actualUrl= actUrl.getAttribute("value");
				if (actualUrl.equalsIgnoreCase(expectedUrl)){				
					isUrlPresent=true;								
					break;
			}if(isUrlPresent==false){
				selectBackToAds();	
					}
			}
		}	


	public void sortSelectSubcriptionPlans(String subscription) throws Exception{
		logInfo("Entered into sortSelectSubcriptionPlans() method");
		   boolean ispresent= false;
			driver().navigate().refresh();
			waitOnElement("cssSelector", sortSub);
			scrollDown("cssSelector", bannerTurn);
			clickOnButton("cssSelector", sortSub);
			List <WebElement> market = driver().findElements(By.cssSelector(adSubcriptionsDrpdwn));
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(subscription)){
					ispresent=true;
					markets.click();
					break;
					
				}
			}if(ispresent==false) {
				Assert.assertTrue(ispresent, subscription + " is not present in dropdown");
			}
		
	}
	
	public void sortSelectAvailableIn(String availblity) throws Exception{
		logInfo("Entered into sortSelectAvailableIn() method");
		boolean ispresent= false;
		waitOnElement("cssSelector", sortAvail);
		scrollDown("cssSelector", bannerTurn);
			clickOnButton("cssSelector", sortAvail);
			List <WebElement> market = driver().findElements(By.cssSelector(adAvailableDrpdwn));
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(availblity)){
					ispresent=true;
					markets.click();
					break;					
				}
			}	if(ispresent==false) {
				Assert.assertTrue(ispresent, availblity + " is not present in dropdown");
			}	
	}
	
	public void sortSelectShopCategory(String shpCat) throws Exception{
		logInfo("Entered into sortSelectShopCategory() method");
		boolean ispresent= false;
		waitOnElement("cssSelector", sortShop);
			clickOnButton("cssSelector", sortShop);
			waitOnElement("cssSelector", sortStatus);
			List <WebElement> market = driver().findElements(By.cssSelector(adShopCatDrpdwn));		
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(shpCat)){
					ispresent=true;
					markets.click();
					break;
					
				}
			}if(ispresent==false) {
				Assert.assertTrue(ispresent, shpCat + " is not present in dropdown");
			}
		
		
	}
	
	public void sortSelectLanguages(String lang) throws Exception{
		logInfo("Entered into sortSelectLanguages() method");
		    boolean ispresent= false;
			waitOnElement("cssSelector", delAds);
			waitOnElement("cssSelector", sortStatus);
			scrollDown("cssSelector", sortLang);
			clickOnButton("cssSelector", sortLang);
			waitOnElement("cssSelector", adlanguageDrpdwn);	
			waitOnElement("cssSelector", adlanguageDrpdwn);
			List <WebElement> market = driver().findElements(By.cssSelector(adlanguageDrpdwn));
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(lang)){
					ispresent=true;
					markets.click();
					waitOnElement("cssSelector", delAds);	
					Thread.sleep(4000);
					waitOnElement("cssSelector", sortStatus);
					break;				
				}
			}if(ispresent==false) {
				Assert.assertTrue(ispresent, lang + " is not present in dropdown");
			}
		
	}
	
	public void sortSelectRanker(String rank) throws Exception{
		logInfo("Entered into sortSelectRanker() method");
		    boolean ispresent= false;
		    scrollDown("cssSelector", sortLang);
		    waitOnElement("cssSelector", sortStatus);
			clickOnButton("cssSelector", sortRanks);			
			List <WebElement> market = driver().findElements(By.cssSelector(adRankDrpdwn));		
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(rank)){
					ispresent=true;
					markets.click();
					break;				
				}
			}if(ispresent==false) {
				Assert.assertTrue(ispresent, rank + " is not present in dropdown");
			}
		
	}
	
	public void sortSelectStatus(String status) throws Exception{
		logInfo("Entered into sortSelectStatus() method");		
			driver().navigate().refresh();
			scrollDown("cssSelector", sortLang);
			waitOnElement("cssSelector", sortStatus);
			clickOnButton("cssSelector", sortStatus);
			boolean ispresent= false;
			List <WebElement> market = driver().findElements(By.cssSelector(adStatusDrpdwn));		
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(status)){
					ispresent=true;
					markets.click();
					break;				
				}				
			}if(ispresent==false) {
				Assert.assertTrue(ispresent, status + " is not present in dropdown");
			}
		
	}
	
	public void selectTurnBanners(String turn) throws Exception{
		logInfo("Entered into selectTurnBanners(String turn) method");	
		    scrollDown("cssSelector", bannerTurn);
		    waitOnElement("cssSelector", sortStatus);
			clickOnButton("cssSelector", bannerTurn);
			boolean isTurned = false;
			List <WebElement> market = driver().findElements(By.cssSelector(bannerTurnDp));		
			for (WebElement markets: market){				
				if(markets.getText().equalsIgnoreCase(turn)){
					isTurned=true;
					markets.click();
					break;				
				}
			}if(isTurned==false) {
				Assert.assertTrue(isTurned, turn + " is not present in dropdown");
			}
		}

		
	public void dragCompanyAds() throws Exception{
		logInfo("Entered into dragCompanyAds() method");	
		waitOnElement("cssSelector", companyAdsWidgets);
		List <WebElement> ne = driver().findElements(By.xpath(companyAdsWidgets));	
		for (WebElement ns : ne){			
			if (ns.getText().contains("Ads")){				
				Thread.sleep(2000);
				WebElement from = driver().findElement(By.xpath(companyAdsWidgets));
				WebElement to = driver().findElement(By.xpath(dragWidgetToLocation));
				tm.dragAndDropAction(from, to);
				Thread.sleep(5000);
				break;
			}		
		}
	}
		
	
	public void AdNotToBePresentInWidget(String url) throws Exception{
		logInfo("Entered into adNotToBePresentAtShop() method");
		boolean isURLPresent= true;
		waitOnElement("cssSelector", adsWidget);
		List <WebElement> im = driver().findElements(By.cssSelector(imageDot));			
		if(im.size()==0 ){	
			
			}else if(im.size()!=1){		
				for (int i=im.size(); i>=1; i--){				
				Thread.sleep(1000);	
				WebElement img = driver().findElement(By.cssSelector(adUrlBfr+i+adUrlAfr));
				String actualUrl = img.getAttribute("href");					
				Thread.sleep(3000);
				if (actualUrl.equalsIgnoreCase(url)){
					isURLPresent = false;					
					Assert.assertTrue(isURLPresent, url + " - url is still present");	
					break;					
						}}
						if (isURLPresent==true){
							System.out.println(url+ " url is not present");
						
				}	
		 
		} else {
			boolean isURLpresent2 = true;
			WebElement img2 = driver().findElement(By.cssSelector(adsWidget));
			String actualUrl2 = img2.getAttribute("href");			
			if (actualUrl2.equalsIgnoreCase(url)){
				isURLpresent2 = false;				
				Assert.assertTrue(isURLpresent2, url + " 2nd - url is present");
					} if (isURLpresent2==true){
						
					}
				}		
		}
	
	public boolean adNotToBePresentAtShop(String url) throws InterruptedException, Exception{
		logInfo("Entered into adNotToBePresentAtShop() method");		
		boolean isURLPresent= true;	
		List <WebElement> im = driver().findElements(By.xpath(imageDots));		
		if(im.size() ==0 ){
			System.out.println("TestCase passed!! Due to No ads are present.");				
			}else if(im.size()!=1 ) {		
				for (int i=im.size(); i>=1; i--){				
				WebElement imzs = driver().findElement(By.cssSelector(adUrlShopBfr+i+adUrlAfr));				
				String actualUrl = imzs.getAttribute("href");				
				if (actualUrl.equalsIgnoreCase(url)){
					isURLPresent = false;					
					Assert.assertTrue(isURLPresent, url + " - url is still present");	
					break;					
						}}
						if (isURLPresent==true){
							System.out.println(url+ " url is not present");
						
				}	
		 
		} else {
			boolean isURLpresent2 = true;
			WebElement img2 = driver().findElement(By.cssSelector(adsWidget));
			String actualUrl2 = img2.getAttribute("href");			
			if (actualUrl2.equalsIgnoreCase(url)){
				isURLpresent2 = false;			
				Assert.assertTrue(isURLpresent2, url + " 2nd - url is still present");
					} if (isURLpresent2==true){
						System.out.println("Url is not present");
					}
				}
		return isURLPresent;		
   }
	
	
	public boolean verifyAdInWidget(String url) throws Exception{
		logInfo("Entered verifyAdInWidget() method");						
		Thread.sleep(3000);
		waitOnElement("cssSelector", adsWidget);
		boolean isURLPresent= false;
		waitOnElement("cssSelector", imageDot);
		List <WebElement> im = driver().findElements(By.cssSelector(imageDot));			
		if(im.size()==0 ){			
			Assert.assertEquals(null, url);		
			}else if(im.size()!=1 ){				
				for (int i=im.size();i>=1;i--){
				Thread.sleep(1000);					
				waitOnElement("cssSelector", adUrlBfr+i+adUrlAfr);
				WebElement img = driver().findElement(By.cssSelector(adUrlBfr+i+adUrlAfr));				
				String actualUrl = img.getAttribute("href");				
				if (actualUrl.equalsIgnoreCase(url)){
					isURLPresent = true;						
					break;					
						}}
						if (isURLPresent==false){
						Assert.assertTrue(isURLPresent, url + " - url is not present");	
				}	
		 
		} else {			
			WebElement img2 = driver().findElement(By.cssSelector(adsWidget));
			String actualUrl2 = img2.getAttribute("href");			
			if (actualUrl2.equalsIgnoreCase(url)){
				isURLPresent = true;				   
			} if (isURLPresent==false){
						Assert.assertTrue(isURLPresent, url + " 2nd - url is not present");
					}
				}
		return isURLPresent;		
   }	
	
	public void verifyAdAtShopping(String url) throws Exception{
		logInfo("Entered verifyAdAtShopping() method");				
		Thread.sleep(3000);
		waitOnElement("cssSelector", imageDot);
		boolean isURLPresent= false;
		List <WebElement> im = driver().findElements(By.cssSelector(imageDot));		
		if(im.size() ==0 ){
			System.err.println("TestCase Failed!! Due to No ads are present.");
			Assert.assertEquals(null, url);		
			}else if(im.size()!=1 ) {	
				for (int i=im.size(); i>=1; i--){
				Thread.sleep(1000);
				WebElement imzs = driver().findElement(By.cssSelector(adUrlShopBfr+i+adUrlAfr));				
				String actualUrl = imzs.getAttribute("href");				
				System.out.println("all Url are " + i + " "+ actualUrl );
				Thread.sleep(1000);
				if (actualUrl.equalsIgnoreCase(url)){
					isURLPresent = true;
					System.out.println("Expected Url is "+ actualUrl );
					break;					
						}}
						if (isURLPresent==false){
						Assert.assertTrue(isURLPresent, url + " - url is not present");	
				}	
		 
		} else {
			boolean isURLpresent2 = false;
			WebElement img2 = driver().findElement(By.cssSelector(adsWidget));
			String actualUrl2 = img2.getAttribute("href");
			System.out.println("single  Url is "+ actualUrl2 );
			if (actualUrl2.equalsIgnoreCase(url)){
				isURLpresent2 = true;
				System.out.println("single url is presented url is "+ actualUrl2 );				
				   
			} if (isURLpresent2==false){
						Assert.assertTrue(isURLpresent2, url + " 2nd - url is not present");
					}
				}		
   }	
	
	
	public void validateFields() throws Exception{
		logInfo ("entered into validateFields method");		
		String expDateError= "Please Enter Publish Date";
		String alert ="span.help-block";	
		scrollDown("cssSelector", addNewAdLnk);
		waitOnElement("cssSelector",addNewAdLnk);
		clickOnButton("cssSelector",addNewAdLnk);
		waitOnElement("cssSelector",addNewAdLnk);
		Thread.sleep(4000);
		scrollDown("cssSelector", adAddBannerUpdate);
		scrollDown("cssSelector", adAddBannerUpdate);
		waitOnElement("cssSelector", adAddBannerUpdate);
		clickOnButton("cssSelector", adAddBannerUpdate);
		WebElement dateError = driver().findElement(By.cssSelector(dateErr));
		String text = dateError.getText().trim();		
		Assert.assertEquals(text, expDateError);
		waitOnElement("cssSelector", dateOk);
		clickOnButton("cssSelector", dateOk);
		
	}
		
	
}