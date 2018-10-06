package vibe.admin.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import vibe.training.tests.TrainingMethods;

@Priority(10)
public class AdminTraining extends TrainingMethods {
	
	@Test(priority=1001)
	public void AddSingleTrainingSeries() throws Exception{
		logInfo("inside AddSingleTrainingSeries() test");
		System.out.println("inside AddSingleTrainingSeries() test");
		go2AdminTrainingPage();
		addTrainingCategory(categoryName);
		boolean isCategoryFound = verifyCategoryPresent(categoryName);
		if(isCategoryFound){
			go2AddSeriesPage(categoryName);
			logInfo("Adding series without dependency.");
			addSeriesToTrainingCategory(seriesName1,seriesDesc1);
			boolean isSeriesFound = verifySeriesPresent(seriesName1);
			if(isSeriesFound){
				logInfo("Adding training without dependency.");
				addTraining2Series(seriesName1,trainingTitle,trainingDesc,fileType,uploadPdfPath1);
				boolean isTrainingFound = verifyTrainingPresent(trainingTitle);
				if(!isTrainingFound){
					Assert.assertTrue(isTrainingFound, trainingTitle + " training could not be added.");
				}
			}
			else{
				Assert.assertTrue(isSeriesFound, seriesName1 + " series could not be added.");
			}
		}
		else{
			Assert.assertTrue(isCategoryFound, categoryName + " category could not be added.");
		}
		
		logInfo("Added single training series successfully.");
	}
	
	
	
	
	@Test(priority=1002)
	public void AddMultipleTrainingSeries() throws Exception{
		logInfo("inside AddMultipleTrainingSeries() test");
		System.out.println("inside AddMultipleTrainingSeries() test");
		go2AdminTrainingPage();
		addTrainingCategory(categoryName2);
		boolean isCategory2Found = verifyCategoryPresent(categoryName2);
		if(isCategory2Found){
			go2AddSeriesPage(categoryName2);
			// Add training series without dependency
			logInfo("Adding series without dependency.");
			addSeriesToTrainingCategory(seriesName1,seriesDesc1);
			boolean isSeriesFound = verifySeriesPresent(seriesName1);
			if(isSeriesFound){
				addTraining2Series(seriesName1,trainingTitle,trainingDesc,fileType,uploadPdfPath1);
				boolean isTrainingFound = verifyTrainingPresent(trainingTitle);
				if(!isTrainingFound){
					Assert.assertTrue(isTrainingFound, trainingTitle + " training could not be added.");
				}
			}
			else{
				Assert.assertTrue(isSeriesFound, seriesName1 + " series could not be added.");
			}
			
			// Add training series with dependency
			go2AddSeriesPage(categoryName2);
			logInfo("Adding series with dependency.");
			addSeriesToTrainingCategory(seriesName2,seriesDesc2,seriesWithDependency1);
			boolean isSeries2Found = verifySeriesPresent(seriesName2);
			if(isSeries2Found){
				// Add training with dependency
				addTraining2Series(seriesName2,trainingTitle,trainingDesc,fileType,uploadPdfPath1);
				boolean isTraining2Found = verifyTrainingPresent(trainingTitle);
				if(isTraining2Found){
					back2Series();
					addTraining2Series(seriesName2,trainingTitle2,trainingDesc2,fileType,uploadPdfPath1,trainingWithDependency1);
				}
				else{
					Assert.assertTrue(isTraining2Found, trainingTitle2 + " training could not be added.");
				}
			}
			else{
				Assert.assertTrue(isSeries2Found, seriesName2 + " series could not be added.");
			}
			
			// Add training series with multiple dependencies
			go2AddSeriesPage(categoryName2);
			logInfo("Adding series with multiple dependencies.");
			addSeriesToTrainingCategory(seriesName3,seriesDesc3,seriesWithDependency2);
			boolean isSeries3Found = verifySeriesPresent(seriesName3);
			if(isSeries3Found){
				
				// Add training with multiple dependencies
				addTraining2Series(seriesName3,trainingTitle,trainingDesc,fileType,uploadPdfPath1);
				boolean isTraining3Found = verifyTrainingPresent(trainingTitle);
				if(isTraining3Found){
					back2Series();
					addTraining2Series(seriesName3,trainingTitle2,trainingDesc2,fileType,uploadPdfPath1,trainingWithDependency1);
					back2Series();
					addTraining2Series(seriesName3,trainingTitle3,trainingDesc3,fileType,uploadPdfPath1,trainingWithDependency2);
				}
				else{
					Assert.assertTrue(isTraining3Found, trainingTitle3 + " training could not be added.");
				}

			}
			else{
				Assert.assertTrue(isSeries3Found, seriesName3 + " series could not be added.");
			}
			
		}
		else{
			Assert.assertTrue(isCategory2Found, categoryName2 + " category could not be added.");
		}
		
		logInfo("Added multiple training series successfully.");
	}

	@Test(priority=1003)
	public void EditTrainingCategory() throws Exception{
		logInfo("inside EditTrainingCategory() test");
		System.out.println("inside EditTrainingCategory() test");
		go2AdminTrainingPage();
		addTrainingCategory(categoryName3);
		boolean isCategoryFound = verifyCategoryPresent(categoryName3);
		if(isCategoryFound){
			updateTrainingCategory(categoryName3,categoryName3+" Updated");
			boolean isUpdatedCategoryFound = verifyCategoryPresent(categoryName3+" Updated");
			if(!isUpdatedCategoryFound){
				Assert.assertTrue(isUpdatedCategoryFound, "Unable to find the updated training category.");
			}
		}
	}
	
	@Test(priority=1004)
	public void ViewTrainingCategory() throws Exception{
		logInfo("inside ViewTrainingCategory() test");
		System.out.println("inside ViewTrainingCategory() test");
		go2AdminTrainingPage();
		boolean isCategoryFound = verifyCategoryPresent(categoryName3+" Updated");
		if(isCategoryFound){
			boolean isCategoryViewed = viewTrainingCategory(categoryName3+" Updated");
			if(!isCategoryViewed){
				Assert.assertTrue(isCategoryViewed, "Unable to view the training category.");
			}
		}
	}
	
	@Test(priority=1005)
	public void EditTrainingSeries() throws Exception{
		logInfo("inside EditTrainingSeries() test");
		System.out.println("inside EditTrainingSeries() test");
		go2AdminTrainingPage();
		boolean isCategoryFound = verifyCategoryPresent(categoryName3+" Updated");
		if(isCategoryFound){
			go2AddSeriesPage(categoryName3+" Updated");
			logInfo("Adding series without dependency.");
			addSeriesToTrainingCategory(seriesName4,seriesDesc4);
			boolean isSeriesFound = verifySeriesPresent(seriesName4);
			if(isSeriesFound){
				updateTrainingSeries(seriesName4,seriesName4+" Updated");
			}
		}
	}
	
	@Test(priority=1006)
	public void ViewTrainingSeries() throws Exception{
		logInfo("inside ViewTrainingSeries() test");
		System.out.println("inside ViewTrainingSeries() test");
		go2AdminTrainingPage();
		go2AddSeriesPage(categoryName3+" Updated");
		boolean isSeriesViewed = viewTrainingSeries(seriesName4+" Updated");
		if(!isSeriesViewed){
			Assert.assertTrue(isSeriesViewed, "Unable to view the training category.");
		}
		
	}
	
	@Test(priority=1007)
	public void EditTraining() throws Exception{
		logInfo("inside EditTraining() test");
		System.out.println("inside EditTraining() test");
		go2AddSeriesPage(categoryName3+" Updated");
		addTraining2Series(seriesName4+" Updated",trainingTitle4,trainingDesc4,fileType,uploadPdfPath1);
		boolean isTrainingFound = verifyTrainingPresent(trainingTitle4);
		if(isTrainingFound){
			updateTraining(trainingTitle4,trainingTitle4+" Updated");
		}
		else{
			Assert.assertTrue(isTrainingFound, trainingTitle4 + " Updated training could not be added.");
		}
	}
	
	@Test(priority=1008)
	public void ViewTraining() throws Exception{
		logInfo("inside ViewTraining() test");
		System.out.println("inside ViewTraining() test");
		go2AdminTrainingPage();
		go2AddSeriesPage(categoryName3+" Updated");
		boolean isSeriesClicked = clickTrainingSeries(seriesName4+" Updated");
		if(isSeriesClicked){
			boolean isTrainingFound = verifyTrainingPresent(trainingTitle4+" Updated");
			if(isTrainingFound){
				boolean isTrainingViewed = viewTrainingVideo(trainingTitle4+" Updated");
				if(!isTrainingViewed){
					Assert.assertTrue(isTrainingViewed, trainingTitle4 + " Updated training could not found.");
				}
			}
		}
		else{
			Assert.assertTrue(isSeriesClicked, "Unable to view the training category.");
		}
		
	}
	
/*	
	//@Test(priority=1005)
	public void ReorderTrainingCategory() throws Exception{
		logInfo("inside ReorderTrainingCategory() test");
		System.out.println("inside ReorderTrainingCategory() test");
		go2AdminTrainingPage();
		boolean isCategoryFound = verifyCategoryPresent(categoryName3+" Updated");
		if(isCategoryFound){
			boolean isCategoryReordered = reorderTrainingCategory(categoryName3+" Updated");
			if(!isCategoryReordered){
				Assert.assertTrue(isCategoryReordered, "Unable to reorder the training category.");
			}
		}
	}*/
	
	@Test(priority=1009)
	public void EnableTrainingComments() throws Exception{
		logInfo("inside EnableTrainingComments() test");
		System.out.println("inside EnableTrainingComments() test");
		go2AdminTrainingPage();
		setTrainingCommentSettings();
	}
	
	
	//@Test(priority=1004)
	public void vtup_addSingleTrainingSeries() throws Exception{
		logInfo("This method adds single training series, which means single category,series and training.");
		go2AdminTrainingPage();
		addTrainingCategory(categoryName);
		boolean isCategoryFound = verifyCategoryPresent(categoryName);
		if(isCategoryFound){
			go2AddSeriesPage(categoryName);
			logInfo("Adding series without dependency.");
			addSeriesToTrainingCategory(seriesName1,seriesDesc1);
			boolean isSeriesFound = verifySeriesPresent(seriesName1);
			if(isSeriesFound){
				logInfo("Adding training without dependency.");
				vtup_addTraining2Series(seriesName1,trainingTitle,trainingDesc,fileType,uploadPdfPath1);
				boolean isTrainingFound = verifyTrainingPresent(trainingTitle);
				if(!isTrainingFound){
					Assert.assertTrue(isTrainingFound, trainingTitle + " training could not be added.");
				}
			}
			else{
				Assert.assertTrue(isSeriesFound, seriesName1 + " series could not be added.");
			}
		}
		else{
			Assert.assertTrue(isCategoryFound, categoryName + " category could not be added.");
		}
		
		logInfo("Added single training series successfully.");
	}
	
	
}
