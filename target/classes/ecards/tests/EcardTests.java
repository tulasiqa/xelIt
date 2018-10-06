package vibe.ecards.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.SocialNetWorksMethods;
import common.TestBase;
import common.EnvProperties;
import vibe.inbox.tests.InboxMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.reports.tests.ReportsMethods;
import vibe.shopping2.tests.Shopping2Methods;



@Priority(120)
public class EcardTests extends EcardMethods{	
    
     InboxMethods inbox = new InboxMethods();
     ReportsMethods report = new ReportsMethods();
     CommunityMethods cm = new CommunityMethods();    
     vibe.contacts2.tests.BusinessContactsMethods bcm = new vibe.contacts2.tests.BusinessContactsMethods();
     EnvProperties prop = new EnvProperties();
     Shopping2Methods shop = new Shopping2Methods();
     SocialNetWorksMethods sm = new SocialNetWorksMethods();    
     
    String savedCards = "Saved Ecards";      
    
   /* String parentEcard = "Ecard Parent 119140";
    */   
    
    @Test(priority=12001)
	public void verifyExistingEcardcategory() throws Exception{	
		logInfo("Entered into verifyexistingEcardcategory() test");	
		nav2Ecard();
		verifyCategoryName(parentEcard);
		selectCategory(parentEcard);		
		boolean isfound =verifyEcardTemplate(ecardtempName);	
		if (isfound==false) {
			Assert.assertTrue(isfound, ecardtempName + " is not found");
		}
	}
	
	
	@Test(priority=12002)
	public void createNdVerifySavedEcard() throws Exception{
		logInfo("Entered into createNdVerifySavedEcard() test");	
		nav2Ecard();	
		selectCatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		saveCardAs(saveAstempName);
		verifyEcardInSavedEcards(saveAstempName);
		}	
	
	
	
	@Test (priority =12003)	
	public void validateFirstEcardWithTwoDiffSubscribers() throws Exception{
		logInfo("Entered into validateFirstEcardWithTwoDiffSubscribers() test");			
		nav2Ecard();		
		selectCategory(parentEcard2);		
		boolean isfound =verifyEcardTemplate(ecardtempName2);	
		if(isfound=true) {
			boolean isTempNotFound =verifyEcardTemplate(ecardtempName3);
			if(isTempNotFound=true){ 	
				userLogout();
				loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
		                prop.getLocatorForEnvironment(appUrl,"newsCon2"));
				Assert.assertTrue(isTempNotFound, ecardtempName3+" is still present under"+prop.getLocatorForEnvironment(appUrl,"newsCon1") );
				}
			userLogout();
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon2"));
			
		  }
		if (isfound==false) {
			userLogout();
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon2"));
			Assert.assertTrue(isfound, ecardtempName + " is not found");
			}	
		   	
		}	
        
	@Test(priority=12004)
	public void verifySecondEcardWithDiffSubscribers() throws Exception{	
		logInfo("Entered into verifySecondEcardWithDiffSubscribers() test");			
		nav2Ecard();		
		selectCategory(parentEcard2);		
		boolean isfound =verifyEcardTemplate(ecardtempName3);	
		if(isfound=true) {
			boolean isTempNotFound =verifyEcardTemplate(ecardtempName2);
			if(isTempNotFound=true){ 	
				userLogout();
				loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
		                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
				Assert.assertTrue(isTempNotFound, ecardtempName2+" is still present under"+prop.getLocatorForEnvironment(appUrl,"newsCon2") );
				}
			
			userLogout();
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			
		  }
		if (isfound==false) {
			userLogout();
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			Assert.assertTrue(isfound, ecardtempName3 + " is not found");
			}		
		
	}		

	@Test(priority=12003)
	public void sendEcardByMail() throws Exception{	
		logInfo("Entered into sendEcardByMail() test");				
		nav2Ecard();		
		selectCatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		sendMail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"), ecardCatSubText);	
		confirmationMessage("Message sent to: "+prop.getLocatorForEnvironment(appUrl,"selfmailId1"));    
		if ((!(driver().getCurrentUrl().contains("thirtyonegifts"))||
				(driver().getCurrentUrl().contains("avon")))){
			inbox.go2Inbox();
			inbox.selectVibeMailsWithSubject(ecardCatSubText);
			inbox.deleteFilteredVibeMails();			
			}		
		}
	
	
	@Test(priority = 12004)
	public void ecardHistory() throws Exception{
		logInfo("Entered into ecardHistory() test");
		String history = "Ecard History";
		String past7 = "Past 7 Days";
		String pastMon = "Past Month";
		String past3Mon = "Past 3 Months";
		String past6Mon = "Past 6 Months";	
		try{	
			nav2Ecard();		
			selectHistory(history);
			selectHistoryList(past7);		
			verfiyHistoryCard(ecardtempName);		
			selectHistoryList(pastMon);		
			verfiyHistoryCard(ecardtempName);		
			selectHistoryList(past3Mon);		
			verfiyHistoryCard(ecardtempName);		
			selectHistoryList(past6Mon);		
			verfiyHistoryCard(ecardtempName);
			selectHistoryList("Ecard Manager");
		   }catch(Exception ex){
			   System.err.println("Ecard Manager is not found");
			   selectHistoryList("Ecard Manager");
		   			}
	}		

	
	@Test(priority=12005)
	public void validateAlertOfEcardMails() throws Exception{
		logInfo ("Entered into validateAlertOfEcardMails() test");		
		nav2Ecard();		
		selectCatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		validateSubject(ecardCatSubText2);
		validationMails(enrollPDEmail_text);			
	}
	
	@Test(priority=12006)
	public void validateListndGridViews() throws Exception{
		logInfo ("Entered into validateListndGridViews() test");		
		nav2Ecard();
		selectcategory(parentEcard);
		gridNdLsitView();		
	}		
	
	
	@Test(priority=12007)
	public void validateListndGridViewsInSavedCards() throws Exception{
		logInfo ("Entered into validateListndGridViewsInSavedCards() test");	
		nav2Ecard();
		gridNdListViewInSavedCards(savedCard, saveAstempName);
		
	}
	
	
	@Test(priority=12008)
	public void sendEcardBymultiMails() throws Exception{
		logInfo ("Entered into sendEcardBymultiMails() test");	
		nav2Ecard();		
		selectCatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		sendManualMailToManyRecepients(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),4);	
		
		
	}
	  
	
	@Test(priority=12009)
	public void verifySubjectBVAAndSizes() throws Exception{
		logInfo ("Entered into verifySubjectBVAAndSizes() test");
		nav2Ecard();		
		selectCatNdEcard(parentEcard, ecardtempName);
		validateSubjectLength();
		nav2Ecard();
		selectcategory(parentEcard);
		gridNdLsitViewEcardSizes();	
	}
	
	@Test(priority=12010)
	public void validateEcardBySearch() throws Exception{
		logInfo ("Entered into validateEcardBySearch() test");
		nav2Ecard();
		searchEcard(ecardtempName);
		boolean isfound =verifyEcardTemplate(ecardtempName);	
		if (isfound==false) {
			Assert.assertTrue(isfound, ecardtempName + " is not found");
		}		
	}	
	
	@Test(priority=12011)
	public void validateEcardInWebsite() throws Exception{
		logInfo ("Entered into validateEcardInWebsite() test");
		nav2Ecard();
		boolean isSavedCardsPresent = isCategoryPresent(savedCards);
		if(isSavedCardsPresent==true) {
			editEcardInListView(saveAstempName);
			sm.selectPwpIcon();;
			shareInWebsite();
			handleWebsiteWindow();				
		}else {
			Assert.assertTrue(isSavedCardsPresent, savedCards + " category is not present");
		}
			
	}	
	
	@Test(priority=12012)
	public void sendEcard2BusinessContact() throws Exception{
		logInfo("inside contactManager_sendEcard2BusinessContact() Test");
	    String mail = "automationQa_"+TestBase.generateRandomNumberInRange(10, 2500)+"@sample.com"; 
		bcm.addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text);
	    bcm.sendecard2Contacts();	
		selectCatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		sendMail(mail, ecardCatSubText2);			
	}	
	
	@Test(priority=12013)
	public void sendEcardFromReports() throws Exception{	
		logInfo ("Entered into sendEcardFromReports() test");
		report.navigate2Report();			
		report.reportstable(prop.getLocatorForEnvironment(appUrl,"reportName")) ;
		report.exportList("Send Ecard");
		selectCatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		sendMail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"), ecardCatSubText2);
		confirmationMessage("Message sent to: "+prop.getLocatorForEnvironment(appUrl,"selfmailId1"));		
	}	
	
/*	String parentEcard = "Ecard Parent 198";
	String ecardtempName = "template14";*/
	
	@Test(priority=12014)
	public void shareEcardInsocialSites() throws Exception{	
		logInfo ("Entered into shareEcardInsocialSites() test");
		nav2Ecard();
		selectCatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);		
		selectFBIconInEcard();
		sm.shareInFaceBook(saveAstempName);		
	    selectTweetIconInEcard();
		 sm.shareInTwitter();
		    closeCardPopUp();		
		}
	
	@Test(priority=12015, dependsOnMethods= {"shareEcardInsocialSites"},alwaysRun=true)
	public void verifyEcardInFaceBook() throws Exception{
		logInfo ("Entered into verifyEcardInFaceBook() test");		
		sm.login2FBVerifyPostedDetails(fBuserName_Text,fBPwd_text);  
		boolean isSharedPostFound = sm.getPostsOfFBFromTopPart(saveAstempName);
		if (isSharedPostFound==true) {
			sm.logoutFB();
		}else {
			sm.logoutFB();
			Assert.assertTrue(isSharedPostFound, saveAstempName + "Template is not yet shared in FB");
		}
		
	}
	
	@Test(priority=12016, dependsOnMethods= {"shareEcardInsocialSites"},alwaysRun=true)
	public void verifyEcardInTwitter() throws Exception{
		logInfo ("Entered into verifyEcardInTwitter() test");
		System.out.println(saveAstempName + " saveAstempName");
			 sm.login2Twitter() ;
			 boolean isSharedPostPresent = sm.verifyPostsInTwitter(ecardtempName);
			 if(isSharedPostPresent==true) {
				 sm.logOutTwitter();	
				 logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
				 loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
			                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			    }else {
			    	sm.logOutTwitter();
			    	logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
			    	loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
			                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			    	Assert.assertTrue(isSharedPostPresent, saveAstempName + " -post is not present in Twitter.");
			
			    }		
	       }
	
	
	@Test(priority=12017)
	public void postToCommunity() throws Exception{
		logInfo ("Entered into postToCommunity() test");
		nav2Ecard();
		selectCatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);	
		selectOptions(optSettingsPost);
		sharetoCommunity(ecardCatSubText2);
	 }
	
	
	@Test(priority=12018)
	public void saveAsFavoriteEcard() throws Exception{
		logInfo ("Entered into saveAsFavoriteEcard() test");
		String favor = "Favorites";
		nav2Ecard();
		selectCatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);	
		selectOptions(optSettingsFav);
		nav2Ecard();
		selectCategory(favor);
		
		boolean isfound =verifyEcardTemplate(ecardtempName);	
		if (isfound==true) {
			nav2Ecard();
			selectCatNdEcard(parentEcard, ecardtempName);
			enterDetails(ecardCatSubText);	
			selectOptions("Remove As Favorite");
			nav2Ecard();
			selectCategory(favor);
			boolean isRemoved = verifyEcardTemplate(ecardtempName);
			if (isRemoved==false) {
				System.out.println("success!! is not found");
			}else {
				Assert.assertFalse(isRemoved, ecardtempName + " is still found");
			}
			
		} else {
			Assert.assertTrue(isfound, ecardtempName + " is not found");
		}	
	 }
	
	
	@Test(priority=12018)
	public void ecardAdvanceSettings() throws Exception{
		logInfo ("Entered into ecardAdvanceSettings() test");		
		nav2Ecard();
		selectCatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);	
		selectOptions(optSettingAdv);
		advanceSettingOfPWP(prop.getLocatorForEnvironment(appUrl,"websiteTemplate_text"));
		
	 }	
	
	
	@Test(priority = 12030)
	public void deleteSavedCard() throws Exception{
		logInfo ("Entered into deleteSavedCard() test");
		nav2Ecard();
		selectCategory(savedCard);
		deleteEcardInListView(saveAstempName);
		boolean isSavedCardsPresent = isCategoryPresent(savedCards);
		if (!(isSavedCardsPresent)==true) {			
			System.out.println("Successfully!! deleted Saved Ecards of"+saveAstempName);
		}else {
			selectCategory(savedCards);
			notToPresentSavedEcards(saveAstempName);		
		}
	}
	
}
