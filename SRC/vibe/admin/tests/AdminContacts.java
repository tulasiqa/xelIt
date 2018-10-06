package vibe.admin.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.contacts2.tests.BusinessContactsMethods;
import common.Priority;
import common.TestBase;
import common.Locators;

@Priority(8)
public class AdminContacts extends BusinessContactsMethods {

	
	@Test(priority=800)
	public void createCallScripTest() throws Exception{
		 String[] markets = {"US (English)","RU (Russian)","CO (English)"};
			 
		 createCallScript(callScriptName_text, "Active", markets, publishedOn_text, publishedEndDate_text);
		 boolean isCallscriptFound = verifyCallScriptFound(callScriptName_text);
			if(isCallscriptFound==false){	
				logInfo(callScriptName_text + " call script could not be created.");
				Assert.assertTrue(isCallscriptFound, callScriptName_text + " call script could not be created.");
			}
	}


	@Test(priority=801)
	public void createPendingCallScripTest() throws Exception{
		 String[] markets = {"US (English)","RU (Russian)","CO (English)"};
			 
		 createCallScript(callScriptPendingName_text, "Pending", markets, publishedOn_text, publishedEndDate_text);
		 boolean isCallscriptFound = verifyCallScriptFound(callScriptPendingName_text);
			if(isCallscriptFound==false){	
				logInfo(callScriptName_text + " call script could not be created.");
				Assert.assertTrue(isCallscriptFound, callScriptName_text + " call script could not be created.");
			}
	}
}