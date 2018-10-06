package vibe.training.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;


@Priority(110)
public class TrainingTests extends TrainingMethods {

	@Test(priority=11001)
	public void CompleteSingleTraining() throws Exception{
		try{
			logInfo("inside CompleteMultipleTrainings() method");
			System.out.println("inside CompleteMultipleTrainings() method");
			go2TrainingUsers();
			boolean isCategoryPresent = verifyCategoryPresentAtUser(categoryName);
			if(isCategoryPresent)
			{
				selectCategory(categoryName);
				boolean isSeriesPresent = verifySeriesAtUser(seriesName1);
				boolean isTrainingPresent = verifyTrainingAtUser(trainingTitle);
				if(isSeriesPresent){
					if(isTrainingPresent){
						completeTraining(trainingTitle);
						logInfo("Verifying the training is completed for this series");
					}
					else{
						Assert.assertTrue(isTrainingPresent, trainingTitle + " training could not be found.");
					}
				}
				else{
					Assert.assertTrue(isSeriesPresent, seriesName1 + " series could not be found.");
				}
			}
			else{
				Assert.assertTrue(isCategoryPresent, categoryName + " category could not be found.");
			}
		}
		catch(Exception ex){
			logInfo("Exception thrown in CompleteSingleTraining() test "+ex.getMessage());
		}
	}
	
	@Test(priority=11002)
	public void AddComments2Training() throws Exception{
		try{
			
			logInfo("inside AddComments2Training() method");
			System.out.println("inside AddComments2Training() method");
			go2TrainingUsers();
			boolean isCategoryPresent = verifyCategoryPresentAtUser(categoryName);
			if(isCategoryPresent)
			{
				selectCategory(categoryName);
				boolean isSeriesPresent = verifySeriesAtUser(seriesName1);
				boolean isTrainingPresent = verifyTrainingAtUser(trainingTitle);
				if(isSeriesPresent){
					if(isTrainingPresent){
						addComments2Training(trainingTitle,txtTrainingComment);
						boolean isCommentFound = verifyTrainingComments(txtTrainingComment);
						if(!isCommentFound){
							Assert.assertTrue(isCommentFound, "Unable to verify the training comments for " + trainingTitle);
						}
					}
					else{
						Assert.assertTrue(isTrainingPresent, trainingTitle + " training could not be found.");
					}
				}
				else{
					Assert.assertTrue(isSeriesPresent, seriesName1 + " series could not be found.");
				}
			}
			else{
				Assert.assertTrue(isCategoryPresent, categoryName + " category could not be found.");
			}
		}
		catch(Exception ex){
			logInfo("Exception thrown in CompleteSingleTraining() test "+ex.getMessage());
		}
	}
	
	//@Test(priority=11003)
	public void CommentOnTrainingComments() throws Exception{
		try{
			logInfo("inside CommentOnTrainingComments() method");
			System.out.println("inside CommentOnTrainingComments() method");
			go2TrainingUsers();
			boolean isCategoryPresent = verifyCategoryPresentAtUser(categoryName);
			if(isCategoryPresent)
			{
				selectCategory(categoryName);
				boolean isSeriesPresent = verifySeriesAtUser(seriesName1);
				boolean isTrainingPresent = verifyTrainingAtUser(trainingTitle);
				if(isSeriesPresent){
					if(isTrainingPresent){
						viewTraining(trainingTitle);
						boolean isCommentFound = verifyTrainingComments(txtTrainingComment);
						if(isCommentFound){
							boolean isLiked = likeTrainingComment(txtTrainingComment);
							if(!isLiked){
								Assert.assertTrue(isCommentFound, "Unable to like the training comments for " + trainingTitle);
							}
						}
						else{
							Assert.assertTrue(isCommentFound, "Unable to verify the training comments for " + trainingTitle);
						}
					}
					else{
						Assert.assertTrue(isTrainingPresent, trainingTitle + " training could not be found.");
					}
				}
				else{
					Assert.assertTrue(isSeriesPresent, seriesName1 + " series could not be found.");
				}
			}
			else{
				Assert.assertTrue(isCategoryPresent, categoryName + " category could not be found.");
			}
		}
		catch(Exception ex){
			logInfo("Exception thrown in CompleteSingleTraining() test "+ex.getMessage());
		}
	}
	
	@Test(priority=11004)
	public void LikeTrainingComments() throws Exception{
		try{
			logInfo("inside LikeTrainingComments() method");
			System.out.println("inside LikeTrainingComments() method");
			go2TrainingUsers();
			boolean isCategoryPresent = verifyCategoryPresentAtUser(categoryName);
			if(isCategoryPresent)
			{
				selectCategory(categoryName);
				boolean isSeriesPresent = verifySeriesAtUser(seriesName1);
				boolean isTrainingPresent = verifyTrainingAtUser(trainingTitle);
				if(isSeriesPresent){
					if(isTrainingPresent){
						viewTraining(trainingTitle);
						boolean isCommentFound = verifyTrainingComments(txtTrainingComment);
						if(isCommentFound){
							boolean isLiked = likeTrainingComment(txtTrainingComment);
							if(!isLiked){
								Assert.assertTrue(isCommentFound, "Unable to like the training comments for " + trainingTitle);
							}
						}
						else{
							Assert.assertTrue(isCommentFound, "Unable to verify the training comments for " + trainingTitle);
						}
					}
					else{
						Assert.assertTrue(isTrainingPresent, trainingTitle + " training could not be found.");
					}
				}
				else{
					Assert.assertTrue(isSeriesPresent, seriesName1 + " series could not be found.");
				}
			}
			else{
				Assert.assertTrue(isCategoryPresent, categoryName + " category could not be found.");
			}
		}
		catch(Exception ex){
			logInfo("Exception thrown in CompleteSingleTraining() test "+ex.getMessage());
		}
	}
	
	@Test(priority=11005)
	public void DeleteTrainingComment() throws Exception{
		try{
				
			logInfo("inside DeleteTrainingComment() method");
			System.out.println("inside DeleteTrainingComment() method");
			go2TrainingUsers();
			boolean isCategoryPresent = verifyCategoryPresentAtUser(categoryName);
			if(isCategoryPresent)
			{	
				selectCategory(categoryName);
				boolean isSeriesPresent = verifySeriesAtUser(seriesName1);
				boolean isTrainingPresent = verifyTrainingAtUser(trainingTitle);
				if(isSeriesPresent){
					if(isTrainingPresent){
						viewTraining(trainingTitle);
						boolean isCommentFound = verifyTrainingComments(txtTrainingComment);
						if(isCommentFound){
							deleteTrainingComment(txtTrainingComment);
						}
					}
					else{
						Assert.assertTrue(isTrainingPresent, trainingTitle + " training could not be found.");
					}
				}
				else{
					Assert.assertTrue(isSeriesPresent, seriesName1 + " series could not be found.");
				}
			}
			else{
				Assert.assertTrue(isCategoryPresent, categoryName + " category could not be found.");
			}
		}
		catch(Exception ex){
			logInfo("Exception thrown in CompleteSingleTraining() test "+ex.getMessage());
		}
	}
	
	
	
	@Test(priority=11006)
	public void verifySeriesDependency() throws Exception{
		logInfo("This method verifies whether the first series is completed before going to the second series.");
		go2TrainingUsers();
		logInfo("verify category present at user side");
		boolean isCategoryPresent = verifyCategoryPresentAtUser(categoryName2);
		if(isCategoryPresent)
		{
			selectCategory(categoryName2);
			logInfo("verify series present at user side");
			boolean isSeriesPresent = verifySeriesAtUser(seriesName2);
			logInfo("verify training present at user side");
			boolean isTrainingPresent = verifyTrainingAtUser(trainingTitle2);
			if(isSeriesPresent){
				if(isTrainingPresent){
					logInfo("checking the dependency factor of training series.");
					checkTrainingDependencyError(seriesName2,trainingTitle2,seriesName1);
				}
				else{
					Assert.assertTrue(isTrainingPresent, trainingTitle2 + " training could not be found.");
				}
			}
			else{
				Assert.assertTrue(isSeriesPresent, seriesName2 + " series could not be found.");
			}
			
		}
		else{
			Assert.assertTrue(isCategoryPresent, categoryName2 + " category could not be found.");
		}
		
		
	}
	
	@Test(priority=11007)
	public void CompleteMultipleTrainings() throws Exception{
		logInfo("inside CompleteMultipleTrainings() method");
		System.out.println("inside CompleteMultipleTrainings() method");
		go2TrainingUsers();
		logInfo("verify category present"); 
		boolean isCategoryPresent = verifyCategoryPresentAtUser(categoryName2);
		if(isCategoryPresent){
			selectCategory(categoryName2);
			completeAllTrainings();
			boolean isVerifyAllTrainings = verifyAllTrainingsCompleted();
			if(isVerifyAllTrainings){
				logInfo("Completed all trainings successfully.");
				Assert.assertTrue(isVerifyAllTrainings, "Able to complete all the training series in this category "+categoryName2);
			}
			else{
				Assert.assertTrue(isVerifyAllTrainings, "Unable to complete all the series in this category : " + categoryName2);
			}
			
		}
		else{
			Assert.assertTrue(isCategoryPresent, categoryName2+" category could not be found.");
		}
		logInfo("Completed multiple training series successfully.");
	}
	
	
	
}
