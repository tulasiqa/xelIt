package vibe.teardown.tests;


import org.testng.annotations.Test;





import common.Priority;
import common.EnvProperties;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.resourcelibrary3.tests.RL3Methods;

@Priority(912)
public class AdminTearDownMarkNews extends NewsMethods{
	EnvProperties prop = new EnvProperties();		
	RL3Methods rl3 = new RL3Methods();
		@Test(priority=91201)	
		public void deleteUnPublishedNews() throws Exception{
			logInfo("Entered into deleteUnPublishedNews() test");		
			navigateCompanyNews();
			deleteNewsBasedOnExcerpt(excerpt2);	       	
	       	confirmationMessage("Company News is deleted");        //"Company news was successfully destroyed.");
	       	back2Office();
	       	navigateCompanyNews();
	       	isNewsTitleNotToPresent(title5);
		}
		
		// Deletes teh company news based on excerpt
		@Test (priority =91202)		
		public void deletePublishedNews() throws Exception{
			logInfo("Entered into deletePublishedNews() test"); 		
			 navigateCompanyNews();
			 deleteNewsBasedOnExcerpt(excerpt);	       
	       	 confirmationMessage("Company News is deleted");        //"Company news was successfully destroyed.");
	       	 back2Office();
	       	 navigateCompanyNews();
	       	 isNewsTitleNotToPresent(title);	       	 
		}
		
		@Test (priority =91203)		
		public void deleteCatOfRL() throws Exception{
			logInfo("Entered into deleteCatOfRL() test"); 
			rl3.nav2AdminRL();			
			rl3.navigate2ManageCategories();
			rl3.deleteProductCategory(parentCatForNews);			
		}
		
	       
		@Test (priority =91204)		
		public void deleteSubscriber2News() throws Exception{
			logInfo("Entered into deleteSubscriber2News() Test");
	         navigateCompanyNews();
	         deleteNewsBasedOnExcerpt(excerptyearly);	       	 
	       	 confirmationMessage("Company News is deleted"); 	       	  
	   
		}
		
		@Test (priority =91205)		
		public void deleteFutureNews() throws Exception{
			logInfo("Entered into deleteAllCreatedNews() Test");	      	       	 
	       	 navigateCompanyNews();
	       	 deleteNewsBasedOnExcerpt(excerpt3);	       	 
	       	 confirmationMessage("Company News is deleted");   	    
	   
		}
		
		@Test (priority =91206)		
		public void deleteTaggedNews() throws Exception{
			logInfo("Entered into deleteAllCreatedNews() Test");
	       	 navigateCompanyNews();
	       	 deleteNewsBasedOnExcerpt(excerptTag);	       	 
	       	 confirmationMessage("Company News is deleted");   
	   
		}
		
		
		
}
		
