package vibe.monatGear.tests;

import org.testng.annotations.Test;

import common.Priority;

@Priority(146)
public class MonatGearTest extends MonatGearMethods{
	
	
	@Test(priority=14601)
	public void validateUSMonatGear() throws Exception{
		
		logOut();		
		nav2USGear();
		validateUSGearHomePage();
		getListOfTabs();
		
		
	}
	

}
