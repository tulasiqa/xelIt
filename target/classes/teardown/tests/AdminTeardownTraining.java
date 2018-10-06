package vibe.teardown.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.contacts2.tests.BusinessContactsMethods;

import vibe.training.tests.TrainingMethods;
import common.Priority;
import common.TestBase;
import common.Locators;

@Priority(910)
public class AdminTeardownTraining extends TrainingMethods {

	@Test(priority = 91001)
	public void DeleteTrainingCategory() throws Exception {
		logInfo("inside DeleteTrainingCategory() Test");
		System.out.println("inside DeleteTrainingCategory() Test");
		go2AdminTrainingPage();
		deleteCategory(categoryName);
		deleteCategory(categoryName2); 
	}
	
	@Test(priority=91002)
	public void DeleteTrainingVideo() throws Exception{
		logInfo("inside DeleteTrainingVideo() test");
		System.out.println("inside DeleteTrainingVideo() test");
		go2AdminTrainingPage();
		go2AddSeriesPage(categoryName3+" Updated");
		boolean isSeriesClicked = clickTrainingSeries(seriesName4+" Updated");
		if(isSeriesClicked){
			boolean isTrainingFound = verifyTrainingPresent(trainingTitle4+" Updated");
			if(isTrainingFound){
				deleteTrainingVideo(trainingTitle4+" Updated");
			}
		}
		else{
			Assert.assertTrue(isSeriesClicked, "Unable to view the training category.");
		}
		
	}
	
	@Test(priority = 91003)
	public void DeleteTrainingSeries() throws Exception {
		logInfo("inside DeleteTrainingSeries() Test");
		System.out.println("inside DeleteTrainingSeries() Test");
		go2AdminTrainingPage();
		go2AddSeriesPage(categoryName3+" Updated");
		deleteTrainingSeries(seriesName4+" Updated");
		go2AdminTrainingPage();
		deleteCategory(categoryName3+" Updated"); 
	}

}
