package vibe.teardown.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.contacts2.tests.BusinessContactsMethods;	
import common.Priority;
import common.TestBase;
import common.Locators;

@Priority(908)
public class AdminTeardownContacts extends BusinessContactsMethods {


	
	
	@Test(priority=90800)
	public void deleteCallScripTest() throws Exception{
		logInfo("inside deleteCallScripTest() Test");
		System.out.println("inside deleteCallScripTest() Test");
		deleteCallScript(callScriptName_text); 
		boolean isCallscriptDeleted = verifyCallScriptFound(callScriptName_text);
			if(isCallscriptDeleted==true){	
				logInfo(callScriptName_text + " call script could not be deleted.");
				Assert.assertFalse(isCallscriptDeleted, callScriptName_text + " call script could not be deleted.");
			}
		}


	@Test(priority=90801)
	public void deletePendingCallScripTest() throws Exception{
		logInfo("inside deletePendingCallScripTest() Test");
		System.out.println("inside deleteCallScripTest() Test");
		deleteCallScript(callScriptPendingName_text); 
		boolean isCallscriptDeleted = verifyCallScriptFound(callScriptPendingName_text);
			if(isCallscriptDeleted==true){	
				logInfo(callScriptPendingName_text + " call script could not be deleted.");
				Assert.assertFalse(isCallscriptDeleted, callScriptPendingName_text + " call script could not be deleted.");
			}
		}
	
}
