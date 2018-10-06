package vibe.admin.tests;



import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.TestBase;
import common.EnvProperties;
import vibe.ecards.tests.EcardMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(20)
public class AdminEcard extends EcardMethods{
	
     ShoppingMethods sm = new ShoppingMethods();
     EnvProperties prop = new EnvProperties();       
     
    // Create parentCat, two ecards,  update one ecard , delete 1 card and verify deleted card.
     @Test(priority=2001)
   	public void createEcardCategory() throws Exception{    	 
    	 logInfo("Entered into createEcardCategory() test");    	
       	 nav2AdminEcard();
    	 addEcardCategory(parentEcard, "None");
    	 nav2AdminEcard();
    	 addEcardCategory(childEcard, parentEcard);      	
    	 validateCategories(parentEcard, childEcard);    	 
      }
     
     
       @Test(priority=2002)
    	public void updateEcard() throws Exception{    	 
     	 logInfo("Entered into updateEcard() test");
     	 String childEcard2 = "Ecard Child2 "+TestBase.generateRandomString();
     	 String updateCategory = childEcard2 +" updated";
     	 System.out.println(parentEcard);
     	 System.out.println(childEcard);
     	 System.out.println(childEcard2);     	 
     	 nav2AdminEcard();
     	 addEcardCategory(childEcard2, parentEcard);       	 
     	 boolean isCatPresent = validateCategories(parentEcard, childEcard2);
     	 if (isCatPresent=true){
     		 updateChildCategories(parentEcard, childEcard2, updateCategory);
     		 deleteChildCategories(parentEcard, updateCategory);
     		 NotPresentChildCategories(parentEcard, updateCategory);
     	       }    	 
     	 if (isCatPresent==false){
     		 Assert.assertTrue(isCatPresent, parentEcard+" or "+childEcard + " either or both not present." );
     	 }
       }        
     
     // String parentEcard = "Ecard Parent 246139";
       
     @Test(priority=2003)
   	public void createNewEcard() throws Exception{
     	 logInfo("Entered into createNewEcard() test");  
     	 nav2AdminEcard();
     	 createECard(ecardtempName,parentEcard, childEcard);
     	 validateTitle(ecardtempName);
   	     }	
     
 
    
     // navigate to office and verify the created Cat and Ecard
	@Test(priority=2004)
	public void verifyEcardInOffice() throws Exception{	
		logInfo("Entered into verifyCardInOffice() test");		
		nav2Ecard();
		verifyCategoryName(parentEcard);		
		selectCategory(parentEcard);		
		verifyEcardTemplate(ecardtempName);	
		
	}	
	
	
	@Test(priority=2005)
	public void ecardSubscrpPlan1() throws Exception{	
		logInfo("Entered into ecardSubscrpPlan1() test");	
		nav2AdminEcard();
   	    addEcardCategory(parentEcard2, "None");
   	    nav2AdminEcard();
 	    createECard(ecardtempName2,parentEcard2, childEcard);
		nav2AdminEcard();   
 	    filterCategory(parentEcard2);
 	    boolean isTemplateFound =verifyEcardTemplate(ecardtempName2);
 	    if(isTemplateFound=true) {
 	    	selectMoreOptionsOfEcardTemplate(ecardtempName2, "Edit");
 	    	selectMarkets(languageList);
 	    	selectSubscriptionPlan(subscrb1);
 	    	selectPublish();
 	    	}if (isTemplateFound==false) { 	    
 	    	Assert.assertTrue(isTemplateFound, ecardtempName2+" is not present." );
 	    }		
	}
	
	@Test(priority=2006)
	public void ecardSubscrpPlan2() throws Exception{
		logInfo("Entered into ecardSubscrpPlan2() test");			
   	    nav2AdminEcard();
 	    createECard(ecardtempName3,parentEcard2, childEcard);
		nav2AdminEcard();   
 	    filterCategory(parentEcard2);
 	    boolean isTemplateFound =verifyEcardTemplate(ecardtempName3);
 	    if(isTemplateFound=true) {
 	    	selectMoreOptionsOfEcardTemplate(ecardtempName3, "Edit");
 	    	selectMarkets(languageList);
 	    	selectSubscriptionPlan(subscrb2);
 	    	selectPublish();
 	    	}if (isTemplateFound==false) { 	    
 	    	Assert.assertTrue(isTemplateFound, ecardtempName3+" is not present." );
 	    }		
	}
	
	@Test(priority=2007)
	public void validateEcardStatus() throws Exception{	
		logInfo("Entered into validateEcardStatus() test");			
   	    nav2AdminEcard();   	
	    filterCategory(parentEcard2);
	    filterMarket(language1);
	    filterSubscription(prop.getLocatorForEnvironment(appUrl,"singleSubscription1"));
	    filterStatus("Active");
	    boolean isTemplateFound =verifyEcardTemplate(ecardtempName2);
 	    if(isTemplateFound=true) {
 	    	filterSubscription(prop.getLocatorForEnvironment(appUrl,"singleSubscription2"));
 	    	boolean isTempNotFound =verifyEcardTemplate(ecardtempName2);
 	    	if(isTempNotFound=true){ 	    		
 	    		Assert.assertTrue(isTempNotFound, ecardtempName2+" is still present." );
 	    		} 	    	
 	    	}if (isTemplateFound==false) { 	    
 	    	Assert.assertTrue(isTemplateFound, ecardtempName2+" is not present." );
 	    }	
	}
	
	@Test(priority=2008)
	public void validateEcardAllStatus() throws Exception{	
		logInfo("Entered into validateEcardAllStatus() test");	
		nav2AdminEcard();
 	    createECard(ecardtempName4,parentEcard2, childEcard); 	    
 	    nav2AdminEcard();
 	    filterStatus("Active");
	    boolean isTemplateFound =verifyEcardTemplate(ecardtempName4);	    
	    	if(isTemplateFound=true){
	 	    	selectMoreOptionsOfEcardTemplate(ecardtempName4, "Edit");
	 	    	selectAsDraft();
	 	    	nav2AdminEcard();
	 	    	filterStatus(draft);	
	 	    	boolean isDraftFound =verifyEcardTemplate(ecardtempName4);	
	 	    	if(isDraftFound==false) {
	 	    		Assert.assertTrue(isDraftFound, ecardtempName4+" is not present under "+draft);
	 	    	}
	 	    	
	    	}if (isTemplateFound==false) { 	    
	    	Assert.assertTrue(isTemplateFound, ecardtempName4+" is not present." );
	    	}
		}
	
	@Test(priority=2009)
	public void ecardLibrarySettingsToOff() throws Exception{
		logInfo("Entered into ecardLibrarySettingsToOff() test");			
		nav2AdminEcard();
		listOfPanelTitles(settings);
		enableOrDisableLibrarySettings(settingMarket,statusOff);
		nav2AdminEcard();
		listOfPanelTitles(settings);
		enableOrDisableLibrarySettings(settingSocial,statusOff);
		nav2AdminEcard();
		listOfPanelTitles(settings);
		enableOrDisableLibrarySettings(settingCommunity,statusOff);
		nav2AdminEcard();
		listOfPanelTitles(settings);
		enableOrDisableLibrarySettings(settingPWP,statusOff);	
		
	}
	
	
	
	@Test(priority = 2010)
	public void validateLibrarySetting() throws Exception{
		logInfo ("Entered into validateLibrarySetting() test");
		nav2Ecard();
		verifyLibraryFilterSettings();				
		selectCategory(parentEcard);		
		editEcard(ecardtempName);	
		socialNetworkIconValidation();
		optionsSettingsNotToPresent(optSettingsPost);		
	  }
	
	@Test(priority=2011)
	public void ecardLibrarySettingsToOn() throws Exception{
		logInfo("Entered into ecardLibrarySettingsToOn() test");			
		nav2AdminEcard();
		listOfPanelTitles(settings);
		enableOrDisableLibrarySettings(settingMarket,statusOn);
		nav2AdminEcard();
		listOfPanelTitles(settings);
		enableOrDisableLibrarySettings(settingSocial,statusOn);
		nav2AdminEcard();
		listOfPanelTitles(settings);
		enableOrDisableLibrarySettings(settingCommunity,statusOn);
		nav2AdminEcard();
		listOfPanelTitles(settings);
		enableOrDisableLibrarySettings(settingPWP,statusOn);			
	}
	
	
	

}
