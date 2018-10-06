package vibe.marketing.companyNews.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.EnvProperties;


@Priority(112)
public class NewsTests extends NewsMethods{
	EnvProperties prop = new EnvProperties();
	
	String grid ="Grid";
	String list ="List";	
		
			// verifies News which has Monthly subscription. Shoud be present for Monthly subscrp user and as well as Yearly (not to present)   
		@Test (priority =11201)	
		public void validateNewsOf2DiffSubscribers() throws Exception{
			logInfo("Entered into validateNewsOf2DiffSubscribers() test");		
			go2HomePage();
			dragNewswidgets();
			boolean isPresent = verifyNewsInWidget("All", title);
			if(isPresent==true){
				userLogout();
				loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
		                prop.getLocatorForEnvironment(appUrl,"newsCon2"));
				dragNewswidgets();
		        verifyNewsNotToPresentInWidget("All", title);
		        back2Office();	
			}else{
				userLogout();
				loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
		                prop.getLocatorForEnvironment(appUrl,"newsCon2"));			
				Assert.assertTrue(isPresent, title+" -News title is not present");
							}        	
			}	
	        
		@Test(priority=11202)
		public void verifyNewsInSub2NdSub1() throws Exception{	
			logInfo("Entered into verifyNewsInSub2NdSub1() test");		
			selectViewMoreLink();
			boolean isPresent = verifyNewsInWidget("All", titleYearly);
			if(isPresent==true){      
	        userLogout();	
	        loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
	        selectViewMoreLink();
			verifyNewsNotToPresentInWidget("All", titleYearly);
			back2Office();
			}else{
				userLogout();	
		        loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
		                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		        Assert.assertTrue(isPresent, titleYearly+" -News title is not present");
			}	
			
		}		

		@Test(priority=11203)
		public void validateNewsAndTagsonLeftPanel() throws Exception{	
			logInfo("Entered into validateNewsAndTagsonLeftPanel() test");
			go2HomePage();
			dragNewswidgets();
			verifyNewsInRecentPosts(title);
			verifyTagsofNews(newsTagText);
			selectNewsFromRecentPosts(title);			
			verifyNewsTitle(title);
	  
		}	
		
		@Test(priority=11204)
		public void validateNewsWithTags() throws Exception{	
			logInfo("Entered into validateNewsWithTags() test");
			go2HomePage();
			dragNewswidgets();
			searchNewsOrTag(newsTagText);
			verifyNewsIsPresent(titleTag);			
		}
		
		
		
		@Test(priority=11205)
		public void searchNewsInGridNdListViews() throws Exception{	
			logInfo("Entered into searchNewsInGridNdListViews() test");
			go2HomePage();
			dragNewswidgets();
			searchNewsOrTag(title);
			verifyNewsIsPresent(title);
			selectTypeOfView(list);
			searchNewsOrTag(title);
			verifyNewsIsListView(title);
			
		}
		
		@Test(priority=11206)
		public void verifyAttachemnts() throws Exception{	
			logInfo("Entered into verifyAttachemnts() test");			
			go2HomePage();
			dragNewswidgets();			
			selectNewsFromRecentPosts(title);
			verifyAdditionalResources(8);
			verifyNewsTitle(title);
			
		}
		
}
		
