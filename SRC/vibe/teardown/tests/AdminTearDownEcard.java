package vibe.teardown.tests;



import org.testng.annotations.Test;
import common.Priority;
import vibe.ecards.tests.EcardMethods;




@Priority(920)
public class AdminTearDownEcard extends EcardMethods{	    
 
	@Test(priority=92001)
   	public void deleteEcardTemplate() throws Exception{
		logInfo("Entered into deleteEcardTemplate() test"); 
		nav2AdminEcard();
		selectMoreOptionsOfEcardTemplate(ecardtempName, "Delete");
		confirmOK();
		nav2AdminEcard();
		templateNotToPresent(ecardtempName);		
	}		
	
	@Test(priority=92002)
   	public void deleteParentCategory() throws Exception{
     	 logInfo("Entered into deleteParentCategory() test");     	 
     	 nav2AdminEcard();
     	 deleteParentCategories(parentEcard);
     	 notPresentParentCategories(parentEcard);
   		
   	}	
	
	
	@Test(priority=92003)
   	public void deleteRemainingTemplates() throws Exception{
		logInfo("Entered into deleteRemainingTemplates() test"); 
		nav2AdminEcard();
		selectMoreOptionsOfEcardTemplate(ecardtempName2, "Delete");
		confirmOK();
		selectMoreOptionsOfEcardTemplate(ecardtempName3, "Delete");
		confirmOK();
		selectMoreOptionsOfEcardTemplate(ecardtempName4, "Delete");
		confirmOK();
		nav2AdminEcard();
		templateNotToPresent(ecardtempName);		
	}	
	
	@Test(priority=92004)
   	public void deleteSecondParentCategory() throws Exception{
     	 logInfo("Entered into deleteParentCategory() test");     	 
     	 nav2AdminEcard();
     	 deleteParentCategories(parentEcard2);
     	 notPresentParentCategories(parentEcard2);
   		
   	}	
	
	
	



	

}
