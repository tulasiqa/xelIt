package vibe.idlifeassessments.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import common.EnvProperties;

@Priority(141)
public class idlvAssessments_Tests extends idlvassessmentMethods  {
	EnvProperties prop = new EnvProperties();
	
	@Test(priority = 14101)
	public void idNutritionAssessmentTest() throws Exception{
		logInfo("inside idNutritionAssessmentTest.. test ");
		back2Office();
		gotoIDNutiritionPage();
		idlifeNutiritionAssesment();
	}
		
	
	@Test(priority = 14102)
	public void myexperienceAssessmentTest() throws Exception{
		logInfo("inside myexperienceAssessmentTest.. test ");		
		back2Office();
		gotoMyExperiencePage();
		 idlifeMyExperienceAssesment();
	}
	
		
	
	@Test(priority = 14103)
	public void idWellnessTest() throws Exception{
		// userLogin(prop.getLocatorForEnvironment(appUrl,"User"),prop.getLocatorForEnvironment(appUrl,"Passwd"));
		back2Office();
		validateIDwellness();
		userLogout();
	}
	
	@Test(priority=14104)
	public void loginAsAdminFromAssessments() throws Exception{
		logInfo("inside loginAsAdminFromAssessments() Test");
		
		adminLogin();
		
		
	}
	
	
}
