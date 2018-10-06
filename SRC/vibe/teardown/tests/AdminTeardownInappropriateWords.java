package vibe.teardown.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.EnvProperties;
import common.Priority;
import vibe.content.inappropriate.tests.InappropriateMethods;


@Priority(916)
public class AdminTeardownInappropriateWords extends InappropriateMethods {
	
	@Test(priority=91601)
	public void DeleteInAppropriateWord() throws Exception{
		logInfo("inside DeleteInAppropriateWord() method");
		System.out.println("inside DeleteInAppropriateWord() method");
		navigate2InappropriateWords();
		deleteInAppropriateWord(txtInappropWord);
	}
	
	@Test(priority=91602)
	public void DeleteReservedWord() throws Exception{
		logInfo("inside DeleteReservedWord() method");
		System.out.println("inside DeleteReservedWord() method");
		navigate2ReservedWords();
		deleteReservedWord(txtReserveWord);
	}
	
}
