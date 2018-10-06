package vibe.content.inappropriate.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.EnvProperties;
import common.Priority;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.training.tests.TrainingMethods;

@Priority(116)
public class InappropriateTests extends InappropriateMethods{
	CommunityMethods comm = new CommunityMethods();
	TrainingMethods tm = new TrainingMethods();
		
	@Test(priority=11601)
	public void VerifyInappropriateWordInCommunity() throws Exception{
		logInfo("inside VerifyInappropriateWordInCommunity() method");
		System.out.println("inside VerifyInappropriateWordInCommunity() method");
		comm.nav2Community();
		comm.addCommunityPhoto(titleInapprop, visibilitySettings_text);
		comm.nav2Community();
		boolean isInappropriateWordFound = comm.verifyPostIsPresent("***");
		if(!isInappropriateWordFound){
			Assert.assertTrue(isInappropriateWordFound, "Unable to mask the photo "+titleInapprop);
		}

	}
	
}
