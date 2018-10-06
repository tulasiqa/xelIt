package vibe.mywebsite.tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import common.TestBase;
import common.EnvProperties;

import vibe.enrollment.tests.ReleaseEnrollmentMethods;

public class MyWebSiteMethods extends TestBase {
	EnvProperties prop = new EnvProperties();
	ReleaseEnrollmentMethods rm = new ReleaseEnrollmentMethods();
	 int num = TestBase.generateRandomNumberInRange(1000, 9999);

	public void go2MyWebsite() throws InterruptedException{
		logInfo("Inside go2MyWebsite() method.");
		String go2MyWebsite = appUrl + "pyr_pwp/sites";		
		driver().navigate().to(go2MyWebsite);
		Thread.sleep(5000);
	}


	public void addMyWebsite(String templateName,String websiteName) throws Exception{
		logInfo("Inside addMyWebsite() method.");
		System.out.println("Inside addMyWebsite() method.");
		//go2MyWebsite();
		boolean isTemplateFound = false;
		waitOnElement("xpath",panelAvailableTemplates);
		WebElement templateRow = driver().findElement(By.xpath(panelAvailableTemplates));
		List<WebElement> templatestList = templateRow.findElements(By.cssSelector("div.row"));
		int rows = templatestList.size();
		 System.out.println("number of rows = "+rows);
		if(rows>=1){
		for(int i=1;i<=rows;i++){
			  String beforeRow = "//*[@id='available_templates']/div[";		// row
			  String afterRow  = "]";
			  WebElement ecols = driver().findElement(By.xpath(beforeRow+i+afterRow));
			  List<WebElement> colsList = ecols.findElements(By.cssSelector("div.col-md-6"));
			  int cols = colsList.size();
			  System.out.println("number of cols in each row = "+cols);
			  if(cols>=1){
			  for(int j=1; j<= cols; j++){
					String beforeAddWebsite= "//*[@id='available_templates']/div[";  // row
					String 	midAddWebsite = "]/div[";	
					String afterAddWebsite = "]/div/div[2]/h4";
					String actTempname = driver().findElement(By.xpath(beforeAddWebsite+i+midAddWebsite+j+afterAddWebsite)).getText().trim();
					if(actTempname.equalsIgnoreCase(templateName)){
					isTemplateFound = true;
					String afterButton = "]/div/div[3]/div/div/button";
					String afterAddwebsite =  "]/div/div[3]/div/div/ul/li[2]/a";
					driver().findElement(By.xpath(beforeAddWebsite+i+midAddWebsite+j+afterButton)).click();
					System.out.println("clicked...");
					waitOnElement("xpath",beforeAddWebsite+i+midAddWebsite+j+afterAddwebsite);			///ul/li[2]/a
					clickOnElement("xpath",beforeAddWebsite+i+midAddWebsite+j+afterAddwebsite);
					Thread.sleep(3000);
					inputTextClear("xpath",inputWebsiteName);
					inputText("xpath",inputWebsiteName,websiteName_text);					
					clickOnButton("xpath",btnAddSite);
					Thread.sleep(3000);
					confirmationMessage("Your site has been created.");
					Thread.sleep(3000);
					break;
				}
				
			}
		 }
		}	
	}
		
		if(isTemplateFound==false){
			logInfo(templateName + " template not found in the webpage.");
			Assert.assertTrue(isTemplateFound, templateName + " template not found in the webpage.");
		}
	}
			

 
	 public boolean verifyWebsiteIsPresent(String websiteURL) throws  Exception{
			logInfo("Inside verifyWebsiteIsPresent() method.");
			System.out.println("Inside verifyWebsiteIsPresent() method.");
			boolean isWebsiteFound = false;
			//go2MyWebsite();
			waitOnElement("cssSelector","div#active_websites");
			List<WebElement> activeWeb = driver().findElements(By.xpath("//*[@id='active_websites']/div/div"));
			int rows = activeWeb.size();		
			System.out.println("number of rows "+rows);
			// for(int i=2;i<=rows+1;i++){			
			for(int i=1;i<=rows;i++){			
				String beforePath = "//*[@id='active_websites']/div[@class='col-md-6'][";// "//*[@id='active_websites']/div[";
				String afterPath  = "]/div/div[3]/h4[2]"; //"]/div/div[3]/h4[2]";
				WebElement  text = driver().findElement(By.xpath(beforePath+i+afterPath));
				String website = text.getText().trim();
				System.out.println("website name are "+website); 
				if(website.contains(websiteURL)){
					logInfo(websiteURL + " website match found in available websites.");
					System.out.println(" website match found.");
					isWebsiteFound = true;
					break;
				}
			}
			
			if(isWebsiteFound==false){
				logInfo(websiteURL + " website match not found in available websites.");
				// Assert.assertTrue(isWebsiteFound, websiteURL + " website not found in the webpage.");
			}
			return isWebsiteFound;
			
		}
	
	public void updateWebSiteContent(String websiteURL) throws Exception{
		logInfo("inside updateWebSiteContent() method.");
		
		waitOnElement("cssSelector","div#active_websites");
		WebElement websitePanel = driver().findElement(By.cssSelector("div#active_websites"));
		List<WebElement> allIwebsites = websitePanel.findElements(By.cssSelector("div.col-md-6 > div.media.actionable.site"));
		int total_websites = allIwebsites.size();
		System.out.println("Total websites = " +total_websites);
		
		String before_edit = "div#active_websites > div.col-md-6:nth-child(";
		String after_edit = ") > div.media.actionable.site   > div.more-options > div.btn-group > ul > li:nth-child(2) >a";
		
		for(int i=2;i<=total_websites+1;i++){
			waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)");
			WebElement x = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)"));
		
			String website = x.getText().trim();
			System.out.println("i =" +i + ", site name = " + website);
			if(website.equalsIgnoreCase(websiteURL)){
				logInfo(websiteURL + " website match found in available websites.");
				System.out.println(" website match found.");
				WebElement btn = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child("+i+") > div.media.actionable.site > div.more-options > div.btn-group > button > i"));
				btn.click();
				waitOnElement("cssSelector",before_edit+i+after_edit);
				clickOnLink("cssSelector",before_edit+i+after_edit);
				Thread.sleep(3000);
				//enterOwnerContactDetails(websiteName_text,prop.getLocatorForEnvironment(appUrl,"newsFN1"), txtWebOwnerPhone,recipientsGmail_text );
				enterMonatOwnerContactDetails(websiteName_text,prop.getLocatorForEnvironment(appUrl,"newsFN1"), txtWebOwnerPhone,recipientsGmail_text);
				
				break;
			}
		}
	}
	
	// Owner details for Monat template.
	
		public void enterMonatOwnerContactDetails(String siteName, String ownerName, String ownerPhone, String ownerEmail) throws Exception{
			logInfo("inside enterMonatOwnerContactDetails() method.");
			
			waitOnElement("cssSelector",inputPwpSite);  //  WebsiteName
			inputTextClear("cssSelector", inputPwpSite);
			inputText("cssSelector", inputPwpSite, siteName+"Updated");
			inputTextClear("cssSelector", inputWebOwnerName);
			inputText("cssSelector", inputWebOwnerName,ownerName );
			inputTextClear("cssSelector", inputWebOwnerPhone);
			inputText("cssSelector", inputWebOwnerPhone, ownerPhone );	
			inputTextClear("cssSelector", inputWebOwnerEmail);
			inputText("cssSelector", inputWebOwnerEmail, ownerEmail );	
			clickOnButton("cssSelector", btnWebsiteSave);
			confirmationMessage("Your website has been updated.");
			clickOnElement("cssSelector", prop.getLocatorForEnvironment(appUrl,"btnCloseWebsite"));
			Thread.sleep(5000);
		}
		
	// Owner details for Generic2 template.
	
	public void enterOwnerContactDetails(String siteName, String ownerName, String ownerPhone, String ownerEmail) throws Exception{
		logInfo("inside enterOwnerContactDetails() method.");
		
		waitOnElement("cssSelector",inputPwpSite);  //  inputWebsiteName
		inputTextClear("cssSelector", inputPwpSite);
		inputText("cssSelector", inputPwpSite, siteName);
		inputTextClear("cssSelector", inputWebOwnerName);
		inputText("cssSelector", inputWebOwnerName,ownerName );
		inputTextClear("cssSelector", inputWebOwnerPhone);
		inputText("cssSelector", inputWebOwnerPhone, ownerPhone );	
		inputTextClear("cssSelector", inputWebOwnerEmail);
		inputText("cssSelector", inputWebOwnerEmail, ownerEmail );	
		composeText("xpath","//iframe[contains(@title,'Rich Text Editor, pyr_pwp_site_site_user_editables_attributes_4_content')]","Website story");		
		clickOnButton("cssSelector", btnWebsiteSave);
		confirmationMessage("Your website has been updated.");
		clickOnElement("cssSelector", prop.getLocatorForEnvironment(appUrl,"btnCloseWebsite"));
		Thread.sleep(5000);
	}
	
	
	// Owner details for Saba template.
	
		public void enterOwnerContactDetails(String ownerName) throws Exception{
			logInfo("inside enterOwnerSabaContactDetails() method.");
			
			waitOnElement("cssSelector",inputPwpSite);  //  inputWebsiteName
			inputTextClear("cssSelector", inputPwpSite);
			inputText("cssSelector", inputPwpSite, ownerName);
			clickOnButton("cssSelector", "div.row > div.col-md-12:nth-child(2) > input[value='Save']");
			confirmationMessage("Your website has been updated.");
			clickOnElement("cssSelector", prop.getLocatorForEnvironment(appUrl,"btnCloseWebsite"));
			Thread.sleep(5000);
		}
	
	public void deleteWebsite(String websiteURL) throws Exception{
		logInfo("inside deleteWebsite() method.");
		
		waitOnElement("cssSelector","div#active_websites");
		WebElement websitePanel = driver().findElement(By.cssSelector("div#active_websites"));
		List<WebElement> allIwebsites = websitePanel.findElements(By.cssSelector("div.col-md-6 > div.media.actionable.site"));
		int total_websites = allIwebsites.size();
		System.out.println("Total websites = " +total_websites);
		String before_delete = "div#active_websites > div.col-md-6:nth-child(";
		String after_delete = ") > div.media.actionable.site   > div.more-options > div.btn-group > ul > li:nth-child(3) >a";
		
		for(int i=2;i<=total_websites+1;i++){
			waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)");
			WebElement x = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)"));
			String website = x.getText().trim();
			System.out.println("i =" +i + ", site name = " + website);
			if(website.equalsIgnoreCase(websiteURL)){
				logInfo(websiteURL + " website match found in available websites.");
				System.out.println(" website match found.");
				WebElement btn = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child("+i+") > div.media.actionable.site > div.more-options > div.btn-group > button > i"));
				btn.click();
				waitOnElement("cssSelector",before_delete+i+after_delete);
				clickOnElement("cssSelector",before_delete+i+after_delete);
				Thread.sleep(2000);
				confirmOK();
				Thread.sleep(3000);
				confirmationMessage("Site was successfully deleted.");
				Thread.sleep(5000);
				break;
			}
		}
	}
		
		
	
	
	public String getUrl(String websiteURL) throws Exception{
        logInfo("inside getUrl() method.");
        
        waitOnElement("cssSelector","div#active_websites");
        WebElement websitePanel = driver().findElement(By.cssSelector("div#active_websites"));
        List<WebElement> allIwebsites = websitePanel.findElements(By.cssSelector("div.col-md-6 > div.media.actionable.site"));
        int total_websites = allIwebsites.size();
        System.out.println("Total websites = " +total_websites);
        for(int i=2;i<=total_websites+1;i++){
            waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)");
            WebElement x = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)"));
            String website = x.getText().trim();
            System.out.println("i =" +i + ", site name = " + website);
            if(website.equalsIgnoreCase(websiteURL)){
                logInfo(websiteURL + " website match found in available websites.");
                System.out.println(" website match found.");
                // waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child("+i+") > div.media.actionable.site > div.more-options > div.btn-group > button > i");
                WebElement btn = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child("+i+") > div.media.actionable.site > div.more-options > div.btn-group > button > i"));
                btn.click();
                waitOnElement("linkText","Edit Website");
                clickOnLink("linkText","Edit Website");
                break;
                }
            }
        	Thread.sleep(5000);
            WebElement urlSite = driver().findElement(By.cssSelector(webUrl));
            String webSiteUrl = urlSite.getText();
            System.out.println("url is "+ webSiteUrl);
            clickOnElement("xpath","//*[@id='page']/div[9]/div/div/div[1]/div/div[3]/div/div/button");
            
            return webSiteUrl;
  		}


	public void confirmGoogleAnalyticsTrackingMsg(String analyticsCode) throws Exception{
        logInfo("inside confirmGoogleAnalyticsTrackingMsg() method.");
        waitOnElement("cssSelector","#_gatc");
        inputText("cssSelector","#_gatc",analyticsCode);
        clickOnElement("cssSelector",".btn.btn-default.btn-sm");
        confirmationMessage("Tracking Code Updated Successfully!");
	}
		
	
	public void postPWPInFaceBook(String pwpName) throws  Exception{
		  logInfo("inside verifyIfPwpPostPresentInFaceBook() method.");
		
		  waitOnElement("xpath","//a[@id='u_0_c']");		// Home button
		  clickOnElement("xpath","//a[@id='u_0_c']");
		  
		  waitOnElement("xpath","//div[@id='feedx_sprouts_container']/div/div[1]/span[1]/a/span[1]");   // Create Post button
		  clickOnElement("xpath","//div[@id='feedx_sprouts_container']/div/div[1]/span[1]/a/span[1]");
		  
		  waitOnElement("xpath","//div[@data-contents='true']");
		  clickOnElement("xpath","//div[@data-contents='true']");
		  //inputTextClear("xpath","//div[@data-offset-key='d3f89-0-0']");
		  inputText("xpath","//*[@id='js_xt']/div[1]/div/div[1]/div[2]/div[@role='presentation']/div/div/div/div/div[1]/div",pwpName);
		  
		  clickOnElement("xpath","//*[@id='js_3sq']/div[2]/div[3]/div/div[2]/div/button");
	}
	
	public void verifyIfPwpPostPresentInFaceBook() throws  Exception{
		  logInfo("inside verifyIfPwpPostPresentInFaceBook() method.");
		  
		  waitOnElement("xpath","//div[@role='navigation']/div[1]/div[2]/div/a");		// Home button
		  clickOnElement("xpath","//div[@role='navigation']/div[1]/div[2]/div/a");
		  
		  Thread.sleep(10000);
		  waitOnElement("xpath","//div[starts-with(@id,'feed_stream_')]");
		  
	       WebElement newsFeed = driver().findElement(By.xpath("//div[starts-with(@id,'feed_stream_')]"));
	       List allFeeds = newsFeed.findElements(By.xpath("div[@role='article']"));
	      
	       System.out.println("----- Total feeds = " +allFeeds.size());
	       if(allFeeds.size()>0){
	    	  
	    	   String before_pwpUrl = "//div[starts-with(@id,'feed_stream_')]";  // "//*[@id='feed_stream_59afbc7b104e86a36191634']/div[";
	    	   String after_pwpUrl = "]/div[1]/div[2]/div[1]/div[2]/div[2]/p/a";
	    	   
	    	   String before_ogTitle = "//div[starts-with(@id,'feed_stream_')]";
	    	   String after_ogTitle = "]/div/div/div/div[2]/div[3]/div/div/div/div/div[1]/span/div[2]/div/div/a";
	    	   
	    	   String before_ogDesc = "//div[starts-with(@id,'feed_stream_')]";
	    	   String after_ogDesc = "]/div/div/div/div[2]/div[3]/div/div/div/div/div[1]/span/div[2]/div/div[2]";
	       
	    	   String before_ogDomain = "//div[starts-with(@id,'feed_stream_')]";
	    	   String after_ogDomain = "]/div/div/div/div[2]/div[3]/div/div/div/div/div[1]/span/div[2]/div/div[3]/div/div[1]";
	    	   
	    	   for(int i=1;i<=allFeeds.size();i++){
	    		   
	    		   WebElement pwpUrl = driver().findElement(By.xpath(before_pwpUrl+i+after_pwpUrl));
	    		   String actPwpUrl = pwpUrl.getText().trim();
	    		   
	    		   WebElement pwpTitle = driver().findElement(By.xpath(before_ogTitle+i+after_ogTitle));
	    		   String actPwpTitle = pwpTitle.getText().trim();
	    		   
	    		   WebElement pwpDesc = driver().findElement(By.xpath(before_ogDesc+i+after_ogDesc));
	    		   String actPwpDesc = pwpDesc.getText().trim();
	    		   
	    		   WebElement pwpDomain = driver().findElement(By.xpath(before_ogDomain+i+after_ogDomain));
	    		   String actPwpDomain = pwpDomain.getText().trim();
	    		 
	    		   logInfo("actPwpUrl = " +actPwpUrl);
	    		   logInfo("actPwpTitle = " +actPwpTitle);
	    		   logInfo("actPwpDesc = " +actPwpDesc);
	    		   logInfo("actPwpDomain = " +actPwpDomain);
	    		   
	    		   Assert.assertEquals(actPwpUrl,prop.getLocatorForEnvironment(appUrl,"pwpUrl"));
	    		   Assert.assertEquals(actPwpTitle,prop.getLocatorForEnvironment(appUrl,"pwpTitle"));
	    		   Assert.assertEquals(actPwpDesc,prop.getLocatorForEnvironment(appUrl,"pwpDesc"));
	    		   Assert.assertEquals(actPwpDomain,prop.getLocatorForEnvironment(appUrl,"pwpDomain"));
		    		 
	    		   break;
	    		   
	    	   }
	       }
	}
	
	
	public void viewWebSite(String websiteURL) throws Exception{
		logInfo("inside viewWebSite() method.");
		
		waitOnElement("cssSelector","div#active_websites");
		WebElement websitePanel = driver().findElement(By.cssSelector("div#active_websites"));
		List<WebElement> allIwebsites = websitePanel.findElements(By.cssSelector("div.col-md-6 > div.media.actionable.site"));
		int total_websites = allIwebsites.size();
		System.out.println("Total websites = " +total_websites);
		
		String before_view = "div#active_websites > div.col-md-6:nth-child(";
		String after_view = ") > div.media.actionable.site   > div.more-options > div.btn-group > ul > li:nth-child(1) >a";
		
		for(int i=2;i<=total_websites+1;i++){
			waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)");
			WebElement x = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)"));
		
			String website = x.getText().trim();
			System.out.println("i =" +i + ", site name = " + website);
			if(website.equalsIgnoreCase(websiteURL)){
				logInfo(websiteURL + " website match found in available websites.");
				System.out.println(" website match found.");
				WebElement btn = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child("+i+") > div.media.actionable.site > div.more-options > div.btn-group > button > i"));
				btn.click();
				waitOnElement("cssSelector",before_view+i+after_view);
				clickOnLink("cssSelector",before_view+i+after_view);
				break;
			}
		}
	}
	
	public void go2userPWPSite() throws IOException{
		logInfo("inside go2userPWPSite() method.");
		driver().navigate().to(prop.getLocatorForEnvironment(appUrl,"pwpUrl"));
	}
	
	
	public void handleWindow() throws  Exception{
		logInfo("inside handleWindow() method.");
		
		String Parent_Window = driver().getWindowHandle();  
		System.out.println("Parent_Window = " +Parent_Window);
		
		for (String Child_Window : driver().getWindowHandles()) {  
			driver().switchTo().window(Child_Window);  
			System.out.println("Child_Window = " +Child_Window);
			handleSecurityException();
			verifyPWPUserTitle();
		}
		
		//Switching back to Parent Window  
	     driver().switchTo().window(Parent_Window);  
	}
	
	public void handleSecurityException() throws  Exception{
		logInfo("inside handleSecurityException() method.");
		Thread.sleep(5000);
		waitOnElement("cssSelector","#advancedButton");
		doubleClick("cssSelector","#advancedButton");
		Thread.sleep(5000);
		waitOnElement("cssSelector","#exceptionDialogButton");
		doubleClick("cssSelector","#exceptionDialogButton");
		Thread.sleep(5000);
		
		Robot rb = new Robot();
		rb.delay(2000);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.delay(2000);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.delay(2000);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.delay(2000);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.delay(2000);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		rb.delay(5000);
	}
	
	public void verifyPWPUserTitle() throws Exception{
		logInfo("inside verifyPWPUserTitle() method.");
		String pwpUserName = prop.getLocatorForEnvironment(appUrl, "newsFN1") + " " + prop.getLocatorForEnvironment(appUrl, "newsLN1");
		boolean isPWPTitleMatching = false;
		WebElement name = driver().findElement(By.cssSelector("span.name"));
		if(name.getText().trim().equalsIgnoreCase(pwpUserName)){
			logInfo(pwpUserName = " pwp user name is matching.");
			isPWPTitleMatching = true;
		} else {
			logInfo(pwpUserName = " pwp user name is not matching.");
			Assert.assertTrue(isPWPTitleMatching, isPWPTitleMatching + " PWP title is not matching");
		}
	}
	

	public void enrollFromWebsite(String website) throws  Exception {
		logInfo("inside enrollFromWebsite() method");
		String essn = prop.getLocatorForEnvironment(appUrl,"EnrollMPUSEssn")+num;
		String email = prop.getLocatorForEnvironment(appUrl,"EnrollMPUSEmail02")+num;
		// go2HomePage();
		driver().navigate().to(website);
		driver().get(website);
		
		Thread.sleep(20000);
		clickOnElement("linkText","Join Now");
		 rm.go2EnrollMarketPartner();
		 rm.selectMarket("Market Partner - US New");  
		// go2EnrollmentPage();
		// chooseEnrollmentMarkets("Market Partner - US New");
		// selectMPProductPackSelection(prop.getLocatorForEnvironment(appUrl, "EnrollMPChkProducts2Cart"),prop.getLocatorForEnvironment(appUrl, "EnrollMPChkProducts2Flex"));
		 rm.validateMPPersonalInfoWithInvalidData("US");
		 rm.updateMPPersonalInfo("US", essn, email);
		 rm.addMarketPartnerBillingInformation();
		 rm.submitAndReviewMarketingPartner(essn, email); 
		 boolean isEnrollSuccessful = rm.verifyEnrollmentSuccessfulAtUser();
		 if(isEnrollSuccessful==true){
			 logInfo("Enrollment is successful");
			 rm.readConsultantID2File("MP","US");
			 Thread.sleep(5000);
		 } else {
			 logInfo("Enrollment is not successful");
			 Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
		 }	
		
		}
	
	
	public void becomeVIPUser(String website) throws  Exception {
		logInfo("inside becomeVIPUser() method");
		String essn = prop.getLocatorForEnvironment(appUrl,"EnrollMPUSEssn")+num;
		String email = prop.getLocatorForEnvironment(appUrl,"EnrollMPUSEmail02")+num;
		
		driver().navigate().to(website);
		
		//waitOnElement("cssSelector","nav.navigation-container > div#menu > ul#menu-main-menu > li:nth-child(2) > a > span:nth-child(1)");
		hoverOnElement("cssSelector","nav.navigation-container > div#menu > ul#menu-main-menu > li:nth-child(2) > a > span:nth-child(1)");
		clickOnElement("cssSelector","nav.navigation-container > div#menu > ul#menu-main-menu > li:nth-child(2) > ul > li:nth-child(2) > a> span");
		Thread.sleep(10000);
		
		 rm.selectMarket("VIP Enrollment - US");  //EnrollMPMarket
		 rm.submitVIPPersonalInfo("US", email);
		 rm.selectVIPProducts2Cart();
		 rm.addNewVIPBillingInformation();
		 rm.reviewAndSubmitVIP("US", email);
		 boolean isEnrollSuccessful = rm.verifyEnrollmentSuccessfulAtUser();
		 if(isEnrollSuccessful==true){
			 logInfo("Enrollment is successful");
		 } else {
			 logInfo("Enrollment is not successful");
			 Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
		 }    
	}
	
	
}