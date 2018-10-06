package vibe.admin.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.EnvProperties;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.resourcelibrary3.tests.RL3Methods;

@Priority(12)
public class AdminMarNews extends NewsMethods{
	EnvProperties prop = new EnvProperties();
	RL3Methods rl3 = new RL3Methods();

	String yes = "Yes";

	//"Create a company News and verifies in office and also in Users widget
		@Test(priority=1201)	
			public void createCompanyNews() throws Exception{	
				logInfo("Entered into createCompanyNews test");			   		
				System.out.println(title);				
				 companyNewsCreation(title,excerpt, ranker5,languageList,subscripList, yes,yes,yes,yes);
				 back2Office();
				 back2Office();
				 dragNewswidgets();				
				 boolean isPresent = verifyNewsInWidget("All", title);
				 if(isPresent==true) {
					 verifyNewsInWidget("Highlights", title);
				 }else {
					Assert.assertTrue(isPresent, title+ " title is not present");
				 }					 
			}			
		
		// unpublish news and verifies in Office side
		@Test(priority=1202)	
		public void unPublishAndVerifyNews() throws Exception{
			logInfo("Entered into unPublishAndVerifyNews() test");				 
			 companyNewsCreation(title5,excerpt2, ranker5,languageList,subscripList, yes,yes,yes,null);
			 navigateCompanyNews();	
			 sortStatus(st2);
			 isNewsTitlePresent(title5);	
			 sortStatus(st1);
			 isNewsTitleNotToPresent(title5);	 
			 back2Office();	
			 back2Office();	
			 selectViewMoreLink();		
			 verifyNewsNotToPresentInWidget("All",title5);					
		}		
	
	
	// Updates two Users with subscriptions and Updates existing News "Pro-Monthly"
   @Test (priority =1203)		
	public void updateNewsAndUsersSubscriptions() throws Exception{
		logInfo("Entered into updateNewsAndUsersSubscriptions() test");	
		modifyUsersSubscription(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
				                prop.getLocatorForEnvironment(appUrl,"newsCon1"), prop.getLocatorForEnvironment(appUrl,"singleSubscription1"));
		modifyUsersSubscription(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
                                prop.getLocatorForEnvironment(appUrl,"newsCon2"), prop.getLocatorForEnvironment(appUrl,"singleSubscription2"));
		updateNewsWithSubscription(title,subscrb1);		
		}
   
  
	
	
         //"Creation of News with Subscription- Yearly in Master,Pro - Monthly - CA for monat "
	@Test(priority=1204)
	public void createNewsWithSubscriber2() throws Exception{
		logInfo("Entered into createNewsWithYearlySubscriber() test");			     
	     companyNewsCreation(titleYearly,excerptyearly, ranker5,languageList,subscrb2, yes,yes,yes,yes);		
		 back2Office();		
			
	}
	
	//Verify the created NEWS in main screen and Edit that NewS and update by uploading with PDF.
	@Test (priority =1205)		
	public void updateNews() throws Exception{	   
		logInfo("Entered into updateNews() test");	        
		    String pdfFile = "icentris_pdf.pdf"; 		    
		    navigateCompanyNews();
		    isNewsTitlePresent(titleYearly);	
		    editNews(titleYearly);
		    updateNewsWithFile();	
		    isNewsTitlePresent(titleYearly); 		    
		    selectNewsLinkToOpen(titleYearly);
		    verifyAdditionalInformation(pdfFile);	
	}
	
	//Create News with future Date. This News should not be published 
	//until Date matches with system Date.
	@Test(priority=1206)
	public void futureNewsNdVerifyinWidget() throws Exception{
		logInfo("Entered into futureNewsNdVerifyinWidget() test");	
		 companyNewsCreation(title3,excerpt3, ranker5,languageList,subscripList, null,yes,yes,yes);			 
	     back2Office();	
	     selectViewMoreLink();
	     verifyNewsInWidget("All", title3);	
	     
	  }		
	
	// Create News with todays date and verify presence in Widgets
	@Test(priority=1207)
	public void presentNewsNdVerifyinWidget() throws Exception{
		logInfo("Entered into presentNewsNdVerifyinWidget() test");		
		 navigateCompanyNews();
		 updateNewsWithPresentDay(title3, 0);		 
	     back2Office();	
	     selectViewMoreLink();
	     verifyNewsInWidget("All", title3);	
	     verifyNewsInWidget("Highlights", title3);	    
	  }			
	

		@Test(priority=1208)
		public void sortingNews() throws Exception{
			logInfo("Entered into SortingNews() test");	
			String lang = "US (Spanish)";
			 navigateCompanyNews();
			 sortMarket("US");
			 isNewsTitlePresent(title3);
			 sortLanguages(lang);
			 isNewsTitleNotToPresent(title3);
			 updateNewsWithMarket(title3, languageList2);			 
			 sortMarket("US");
		     isNewsTitleNotToPresent(title3);
		     sortMarket("CA");
			 isNewsTitlePresent(title3); 			 	 
		}

		
		@Test(priority=1209)	
		public void tagsValidation() throws Exception{	
			logInfo("Entered into tagsValidation test");				 
			 companyNewsCreation(titleTag,excerptTag, ranker5,languageList,subscripList, yes,yes,yes,yes);
			 navigateCompanyNews();			 
			 updateNewsWithTags(titleTag, newsTagText);	
			 sortTags(newsTagText);
			 isNewsTitlePresent(titleTag);
		}
		
		
		@Test(priority=1210)	
		public void mutliAttachmentsForNews() throws Exception{	
			logInfo("Entered into mutliAttachmentsForNews test");
			 rl3.nav2AdminRL();			
			 rl3.navigate2ManageCategories();
			 rl3.addResourceCategory(parentCatForNews, "None");
			 rl3.nav2AdminRL();			 
			 rl3.addNewResourceWithMultipleAssets(multipleResourceforNews, parentCatForNews, true, false,"Active",1);
			 navigateCompanyNews();
			 isNewsTitlePresent(title);			  	  
			 editNews(title);
			 selectAttachResource();
			 attachRLAsserts(parentCatForNews, multipleResourceforNews);
			 publishImdWithAttachment();		
			
		}
		
}
		
