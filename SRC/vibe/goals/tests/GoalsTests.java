package vibe.goals.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.SocialNetWorksMethods;
import vibe.calendar2.tests.CalendarMethods;
import vibe.myprofile.tests.MyProfileMethods;

@Priority(133)
public class GoalsTests extends GoalsMethods {
	
	SocialNetWorksMethods sm = new SocialNetWorksMethods();
	
	// Below are the tests for Goals 
	
	@Test(priority=13301)
	public void CreateGoal() throws Exception{
		logInfo("Entered into CreateGoal() test");
		System.out.println("Entered into CreateGoal() test");
		go2Goals();
		addGoal(goalTit);
		boolean isGoalFound = verifyGoalIsPresent(goalTit);
		if(!isGoalFound){
			Assert.assertTrue(isGoalFound, "Goal "+ goalTit+" couldn't found in the goals list");
		}
	}

	@Test(priority=13302)
	public void EditGoal() throws Exception{
		logInfo("Entered into EditGoal() test");
		System.out.println("Entered into EditGoal() test");
		go2Goals();
		boolean isGoalFound = verifyGoalIsPresent(goalTit);
		if(isGoalFound){
			boolean isGoalUpdated = updateGoal(goalTit,goalTitUpdate);
			if(!isGoalUpdated){
				Assert.assertTrue(isGoalUpdated, "Goal "+ goalTitUpdate+" could not be updated in the goals list");
			}
		}
		else{
			Assert.assertTrue(isGoalFound, "Goal "+ goalTit+" couldn't found in the goals list");
		}
	}
	
	
	
	@Test(priority=13303)
	public void GoalMarkAsComplete() throws Exception{
		logInfo("Entered into GoalMarkAsComplete() test");
		System.out.println("Entered into GoalMarkAsComplete() test");
		go2Goals();
		boolean isGoalFound = verifyGoalIsPresent(goalTitUpdate);
		if(isGoalFound){
			boolean isMarkAsComplete = markAsComplete(goalTitUpdate);
			if(!isMarkAsComplete){
				Assert.assertTrue(isMarkAsComplete, "Goal "+ goalTitUpdate+" could not be completed successfully.");
			}
			
		}
	}
	
	@Test(priority=13304)
	public void ViewGoalFromGoalBoard() throws Exception{
		logInfo("Entered into ViewGoalFromGoalBoard() test");
		System.out.println("Entered into ViewGoalFromGoalBoard() test");
		go2Goals();
		addGoal(goalTit);
		
		boolean isGoalViewed = viewGoalFromBoard(goalTit);
		if(isGoalViewed){
			closeGoalView();
			Assert.assertTrue(isGoalViewed, "Goal "+ goalTit+" is able to viewed successfully.");
		}
		else{
			Assert.assertTrue(isGoalViewed,  goalTit+" Unable to view the goal ");
		}

	}
	
	@Test(priority=13305)
	public void shareGoal2SocialNetworks() throws Exception{
		logInfo("Entered into shareGoal2SocialNetworks() test");
		System.out.println("Entered into shareGoal2SocialNetworks() test");
		go2Goals();
		boolean isGoalViewed = viewGoalFromBoard(goalTit);
		
		if(isGoalViewed){
			selectFacebookIcon();
			sm.shareInFaceBook("Goal "+ goalTit + " has been shared to facebook successfully");
			selectTwitterIcon();
			sm.shareInTwitter();
			selectShareEmailIcon();
			shareEventByEmail(recipientsGmail_text);
			closeGoalView();
		}
		else{
			Assert.assertTrue(isGoalViewed, "Unable to view the goal" + goalTit);
		}

	}
	
	@Test(priority=13306)
	public void LikeGoal() throws Exception{
		logInfo("Entered into LikeGoal() test");
		System.out.println("Entered into LikeGoal() test");
		go2Goals();
		boolean isGoalViewed = viewGoalFromBoard(goalTit);
		
		if(isGoalViewed){
			boolean isLiked = likeAGoal(goalTit);
			closeGoalView();
			if(isLiked){
				Assert.assertTrue(isLiked, " Able to Liked the goal" + goalTit);
			}
			else{
				Assert.assertTrue(isLiked, "Unable to like the goal" + goalTit);
			}
			
		}
		else{
			Assert.assertTrue(isGoalViewed, "Unable to view the goal" + goalTit);
		}

	}
	
	@Test(priority=13307)
	public void CommentGoal() throws Exception{
		logInfo("Entered into CommentGoal() test");
		System.out.println("Entered into CommentGoal() test");
		go2Goals();
		boolean isGoalViewed = viewGoalFromBoard(goalTit);
		
		if(isGoalViewed){
			boolean isCommented = commentAGoal(goalTit);
			/*closeGoalView();*/
			if(isCommented){
				Assert.assertTrue(isCommented, "Able to commented the goal " + goalTit);
			}
			else{
				Assert.assertTrue(isCommented, "Unable to comment the goal " + goalTit);
			}
			
		}
		else{
			Assert.assertTrue(isGoalViewed, "Unable to view the goal" + goalTit);
		}

	}
	
	@Test(priority=13308)
	public void EditGoalFromGoalsView() throws Exception{
		logInfo("Entered into EditGoalFromGoalsView() test");
		System.out.println("Entered into EditGoalFromGoalsView() test");
		go2Goals();
		boolean isGoalViewed = viewGoalFromBoard(goalTit);
		
		if(isGoalViewed){
			boolean isGoalUpdated = editGoalFromGoalsView(goalTit,goalTitUpdate);

			if(isGoalUpdated){
				Assert.assertTrue(isGoalUpdated, "Able to update goal from Modal" + goalTitUpdate);
			}
			else{
				Assert.assertTrue(isGoalUpdated, "Unable to update the goal from Modal" + goalTitUpdate);
			}
			
		}
		else{
			Assert.assertTrue(isGoalViewed, "Unable to view the goal" + goalTit);
		}

	}
	
	@Test(priority=13309)
	public void DeleteGoalFromGoalsView() throws Exception{
		logInfo("Entered into DeleteGoalFromGoalsView() test");
		System.out.println("Entered into DeleteGoalFromGoalsView() test");
		go2Goals();
		boolean isGoalViewed = viewGoalFromBoard(goalTitUpdate);
		
		if(isGoalViewed){
			deleteGoalFromGoalView();
		}
		else{
			Assert.assertTrue(isGoalViewed, "Unable to view the goal" + goalTitUpdate);
		}

	}
	
	
	@Test(priority=13310)
	public void FilterGoals() throws Exception{
		logInfo("Entered into FilterGoals() test");
		System.out.println("Entered into FilterGoals() test");
		go2Goals();
		addGoal(goalTit);
		boolean isGoalFound = verifyGoalIsPresent(goalTit);
		if(isGoalFound){
			filterGoalByStatus("All",goalTit);
			filterGoalByStatus("inprogress",goalTit);
			filterGoalByStatus("last 2 months",goalTit);
			filterGoalByStatus("last 6 months",goalTit);
			filterGoalByStatus("last 1 year",goalTit);
			filterGoalByStatus("achieved",goalTit);
			filterGoalByStatus("overdue",goalTit);
		}
		else{
			Assert.assertTrue(isGoalFound, "Unable to view the goal" + goalTit);
		}
		
	}
	
	@Test(priority=13311)
	public void DeleteGoal() throws Exception{
		logInfo("Entered into DeleteGoal() test");
		System.out.println("Entered into DeleteGoal() test");
		go2Goals();
		boolean isGoalFound = verifyGoalIsPresent(goalTit);
		if(isGoalFound){
			boolean isGoalDeleted = deleteGoal(goalTit);
			if(isGoalDeleted){
				Assert.assertTrue(isGoalDeleted, "Goal "+ goalTit+" has been deleted successfully");
			}
			else{
				Assert.assertTrue(isGoalDeleted, "Goal "+ goalTit+" could not be deleted from the goals list");
			}
		}
		else{
			Assert.assertTrue(isGoalFound, "Goal "+ goalTit+" couldn't found in the goals list");
		}
	}
	
	
	//Below are the tests for Goal Tasks
	
	@Test(priority=13312)
	public void AddTaskFromGoal() throws Exception{
		logInfo("Entered into AddTaskFromGoal() test");
		System.out.println("Entered into AddTaskFromGoal() test");
		go2Goals();
		addGoal(goalTit);
		boolean isGoalFound = verifyGoalIsPresent(goalTit);
		if(isGoalFound){
			addGoalTask(goalTit,todaysTask_text);
			boolean isGoalTaskFound = verifyGoalTask(goalTit,todaysTask_text);
			if(!isGoalTaskFound){
				Assert.assertTrue(isGoalTaskFound, "Unable to find the goal task "+todaysTask_text);
			}
		}
		else{
			Assert.assertTrue(isGoalFound, "Goal "+ goalTit+" couldn't found in the goals list");
		}
	}

	@Test(priority=13313)
	public void ViewGoalFromAddTask() throws Exception{
		logInfo("Entered into ViewGoalFromAddTask() test");
		System.out.println("Entered into ViewGoalFromAddTask() test");
		go2Goals();
		boolean isGoalFound = verifyGoalIsPresent(goalTit);
		
		if(isGoalFound){
			boolean isGoalViewed = viewGoalFromTask(goalTit);
			
			if(!isGoalViewed){
				Assert.assertTrue(isGoalViewed, "Unable to viewed the goal "+goalTit+" from add task");
			}
		}
		else{
			Assert.assertTrue(isGoalFound, "Goal "+ goalTit+" couldn't found in the goals list");
		}
	}
	
	@Test(priority=13314)
	public void DeleteTaskFromGoals() throws Exception{
		logInfo("Entered into DeleteTaskFromGoals() test");
		System.out.println("Entered into DeleteTaskFromGoals() test");
		go2Goals();
		boolean isGoalFound = verifyGoalIsPresent(goalTit);
		
		if(isGoalFound){
			deleteGoalTask(goalTit,todaysTask_text);
		}
		else{
			Assert.assertTrue(isGoalFound, "Goal "+ goalTit+" couldn't found in the goals list");
		}
	}
	

	@Test(priority=13315)
	public void MarkTaskAsComplete() throws Exception{
		logInfo("Entered into MarkTaskAsComplete() test");
		System.out.println("Entered into MarkTaskAsComplete() test");
		go2Goals();
		boolean isGoalFound = verifyGoalIsPresent(goalTit);
		
		if(isGoalFound){
			addGoalTask(goalTit,todaysTask_text);
			boolean isGoalTaskFound = verifyGoalTask(goalTit,todaysTask_text);
			if(isGoalTaskFound){
				markTaskAsCompleted();
			}
		}
		else{
			Assert.assertTrue(isGoalFound, "Goal "+ goalTit+" couldn't found in the goals list");
		}
	}
	
	@Test(priority=13316)
	public void MarkTaskAsInComplete() throws Exception{
		logInfo("Entered into MarkTaskAsInComplete() test");
		System.out.println("Entered into MarkTaskAsInComplete() test");
		go2Goals();
		boolean isGoalFound = verifyGoalIsPresent(goalTit);
		
		if(isGoalFound){
			boolean isGoalTaskFound = verifyGoalTask(goalTit,todaysTask_text);
			if(isGoalTaskFound){
				markAsIncomplete();
			}
		}
		else{
			Assert.assertTrue(isGoalFound, "Goal "+ goalTit+" couldn't found in the goals list");
		}
	}
	
	@Test(priority=13317)
	public void EditTaskFromTaskDetails() throws Exception{
		logInfo("Entered into EditTaskFromTaskDetails() test");
		System.out.println("Entered into EditTaskFromTaskDetails() test");
		go2Goals();
		boolean isGoalFound = verifyGoalIsPresent(goalTit);
		
		if(isGoalFound){
			boolean isGoalTaskFound = verifyGoalTask(goalTit,todaysTask_text);
			if(isGoalTaskFound){
				editGoalTask(todaysTask_text+"_Updated");
			}
		}
		else{
			Assert.assertTrue(isGoalFound, "Goal "+ goalTit+" couldn't found in the goals list");
		}
	}
	
	@Test(priority=13318)
	public void DeleteTaskFromTaskDetails() throws Exception{
		logInfo("Entered into DeleteTaskFromTaskDetails() test");
		System.out.println("Entered into DeleteTaskFromTaskDetails() test");
		go2Goals();
		boolean isGoalFound = verifyGoalIsPresent(goalTit);
		
		if(isGoalFound){
			boolean isGoalTaskFound = verifyGoalTask(goalTit,todaysTask_text+"_Updated");
			if(isGoalTaskFound){
				deleteGoalTask();
			}
		}
		else{
			Assert.assertTrue(isGoalFound, "Goal "+ goalTit+" couldn't found in the goals list");
		}
	}
	
	
}
