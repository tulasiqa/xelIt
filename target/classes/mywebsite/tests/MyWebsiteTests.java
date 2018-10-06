package vibe.mywebsite.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.SocialNetWorksMethods;
import common.TestBase;
import vibe.enrollment.tests.ReleaseEnrollmentMethods;
import common.EnvProperties;


@Priority(129)

public class MyWebsiteTests extends MyWebSiteMethods {
	SocialNetWorksMethods sm = new SocialNetWorksMethods();
	TestBase tb = new TestBase();
	EnvProperties prop = new EnvProperties();
	String[] appUrl1 = appUrl.split("/");
	String url1part2 = appUrl1[2];
	
	String[] urlpart = appUrl.split("//");		
	String urlpart0 = urlpart[0];
	String urlpart1 = urlpart[1];
//	String  websiteURL_text = urlpart0 + "//" + websiteName_text + "." + url1part2;  // for Release
	String  websiteURL_text = "http:" + "//" + websiteName_text + "." + url1part2; // for SABA
	String  websiteURL_text_updated = "http:" + "//" + websiteName_text + "Updated." + url1part2; 
		
	ReleaseEnrollmentMethods rm = new ReleaseEnrollmentMethods();
	
	@Test(priority=12901)
	public void AddWebsite() throws Exception{
		logInfo("inside AddWebsite() Test.");
		System.out.println("URL= "+ websiteURL_text);
		String websiteTemplate_text = prop.getLocatorForEnvironment(appUrl,"websiteTemplate_text");		
		go2MyWebsite();
		addMyWebsite(websiteTemplate_text,websiteName_text);
		boolean isWebsiteFound = verifyWebsiteIsPresent(websiteURL_text);
		if(isWebsiteFound==false){
			logInfo(websiteURL_text + " website could not be created.");
			Assert.assertTrue(isWebsiteFound, websiteURL_text + " website could not be created.");
		}
	}


	@Test(priority=12905)
	public void VerifyDefaultWebsiteAvailable() throws Exception{
		logInfo("inside verifyDefaultWebsiteAvailable() method.");
		String expWebsiteURL_text = prop.getLocatorForEnvironment(appUrl,"defaultWebsite");
		go2MyWebsite();
		boolean isWebsiteFound = verifyWebsiteIsPresent(expWebsiteURL_text);
		
		if(isWebsiteFound==false){
			logInfo(expWebsiteURL_text + " default website could not be created.");
		
			Assert.assertTrue(isWebsiteFound, expWebsiteURL_text + " default website could not be created.");
		}
	}

	
	
	@Test(priority=12903)
	public void EnrollPWPUser() throws Exception{
		logInfo("inside EnrollPWPUser() method.");
		go2HomePage();
		enrollFromWebsite(prop.getLocatorForEnvironment(appUrl,"defaultWebsite"));
	}
	
	@Test(priority=12904)
	public void EnrollPWPVIPUser() throws Exception{
		logInfo("inside EnrollPWPVIPUser() method.");
		go2HomePage();
		becomeVIPUser(websiteURL_text);
	}
	
	
	@Test(priority=12902)
	public void ManageWebsite() throws Exception{
		logInfo("inside ManageWebsite() method.");
		go2MyWebsite();
		boolean isWebsiteFound = verifyWebsiteIsPresent(websiteURL_text);
		if(isWebsiteFound){
			logInfo("updating the website");
			updateWebSiteContent(websiteURL_text);
		}	else {
			logInfo(websiteURL_text + " website not found.");
			Assert.assertTrue(isWebsiteFound, websiteURL_text + " website not found.");
		}
	}
	
	@Test(priority=12906)
	public void DeleteWebsite() throws Exception{
		logInfo("inside DeleteWebsite() method.");
		go2MyWebsite();
		boolean isWebsiteFound = verifyWebsiteIsPresent(websiteURL_text_updated);
		if(isWebsiteFound){
			deleteWebsite(websiteURL_text_updated);
			boolean isWebsiteFound2 = verifyWebsiteIsPresent(websiteURL_text_updated);
			if(isWebsiteFound2==true) {
				logInfo(websiteURL_text_updated + " website could not be deleted.");
				Assert.assertFalse(isWebsiteFound2, websiteURL_text_updated + " website could not be deleted.");
			}
		}	else {
			logInfo(websiteURL_text_updated + " website not found.");
			Assert.assertTrue(isWebsiteFound, websiteURL_text_updated + " website not found.");
		}
	}
	
	@Test(priority=12904)
	public void GoogleAnalyticFieldValidation() throws Exception{
		logInfo("inside googleAnalyticFieldValidation() method.");
		go2MyWebsite();
		confirmGoogleAnalyticsTrackingMsg(analyticsCode_text);
	}
	
	
	/*@Test(priority=12905)
	public void VerifyDefaultWebsiteAvailable() throws Exception{
		logInfo("inside verifyDefaultWebsiteAvailable() method.");
		String expWebsiteURL_text = prop.getLocatorForEnvironment(appUrl,"defaultWebsite");
		go2MyWebsite();
		boolean isWebsiteFound = verifyWebsiteIsPresent(expWebsiteURL_text);
		
		if(isWebsiteFound==false){
			logInfo(expWebsiteURL_text + " default website could not be created.");
		
			Assert.assertTrue(isWebsiteFound, expWebsiteURL_text + " default website could not be created.");
		}
	}*/
	
	@Test(priority=12906)
	public void VerifyPWPLinksInFacebook() throws Exception{
		logInfo("inside verifyPWPLinksInFacebook() method.");
		
		sm.go2FacebookPage();
		sm.login2FaceBook("icentris.vibe001@gmail.com","ICt3st3r");
		//postPWPInFaceBook(prop.getLocatorForEnvironment(appUrl,"pwpUrl"));
		verifyIfPwpPostPresentInFaceBook();
	}
	
	
	// PWP Related Tests
	
	@Test(priority=12907)
	public void VerifyLeadPWP() throws Exception{
		logInfo("inside verifyLeadPWP() Test.");
		String websiteURL_text = "http://557808.saba-rc.vibeoffice.com";
		go2MyWebsite();
		boolean isWebsiteFound = verifyWebsiteIsPresent(websiteURL_text);
		if(isWebsiteFound==false){
			logInfo(websiteURL_text + " website could not be created.");
			Assert.assertTrue(isWebsiteFound, websiteURL_text + " website could not be created.");
		} else {
			viewWebSite(websiteURL_text);
			handleWindow();
		}
		
	}
	
}