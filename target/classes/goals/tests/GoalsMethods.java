package vibe.goals.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import common.TestBase;
import vibe.tasks.tests.TaskMethods;

public class GoalsMethods extends TestBase {
	
	TaskMethods tm = new TaskMethods();
	
	public void go2Goals() throws Exception{
		logInfo("Entered into go2Goals() method");
		System.out.println("Entered into go2Goals() method");
		driver().navigate().to(appUrl + "community/profile");
		waitOnElement("xpath","//*[@id='user_activity_tabs_nav']/li[5]/a");
		clickOnLink("xpath", "//*[@id='user_activity_tabs_nav']/li[5]/a");
	}
	
	public void addGoal(String goal) throws Exception {
		waitOnElement("linkText",addGoal);
		clickOnLink("linkText", addGoal);
		waitOnElement("cssSelector",goalTitle);
		inputText("cssSelector",goalTitle,goal);	
		uploadFile("Image","#pyr_community_goal_image");
		inputText("cssSelector",goalDesc,Description_text);
		String NoOfDays = getDate(5, "MM/dd/yyyy");
		inputText("cssSelector", completeBy, NoOfDays);		
		WebElement board = driver().findElement(By.cssSelector(showOnBrd));
		if(!board.isSelected()){			
			board.click();
		}	
		clickOnButton("cssSelector", saveGoal);
		waitOnElement("xpath", spinner);
		confirmationMessage("Create Goal");
	}
	
	public boolean verifyGoalIsPresent(String title) throws Exception{
		boolean isGoalFound = false;
		try{
			logInfo("Entered into verifyGoalsIsPresent() method");
			System.out.println("Entered into verifyGoalsIsPresent() method");
			
			waitOnElement("cssSelector",lstGoals);
			List <WebElement> goalsList = driver().findElements(By.cssSelector(lstGoals));
			
			if(goalsList.size() > 0){
				
				String before = "div#goal-details>div:nth-child(";
				String after = ")>div:nth-child(2)>h3>a";
				
				for(int i=1;i<=goalsList.size();i++){
					waitOnElement("cssSelector",before+i+after);
					WebElement el = driver().findElement(By.cssSelector(before+i+after));
					String goalTitle = el.getText().trim();
					if(title.equalsIgnoreCase(goalTitle)){
						isGoalFound = true;
						break;
					}
										
				}
			}
			
		}
		
		catch(Exception ex){
			logInfo("Exception thrown in verifyGoalsIsPresent() method "+ex.getMessage());
			throw ex;
		}
		return isGoalFound;
	}
	
	public boolean verifyAchievedGoalIsPresent(String title) throws Exception{
		boolean isGoalFound = false;
		try{
			logInfo("Entered into verifyAchievedGoalIsPresent() method");
			System.out.println("Entered into verifyAchievedGoalIsPresent() method");
			
			waitOnElement("cssSelector",lstGoals);
			List <WebElement> goalsList = driver().findElements(By.cssSelector(lstGoals));
			
			if(goalsList.size() > 0){
				
				String before = "div#goal-details>div:nth-child(";
				String after = ")>div:nth-child(2)>h3";
				
				for(int i=1;i<=goalsList.size();i++){
					waitOnElement("cssSelector",before+i+after);
					WebElement el = driver().findElement(By.cssSelector(before+i+after));
					String goalTitle = el.getText().trim();
					if(title.contains(goalTitle)){
						isGoalFound = true;
						break;
					}
										
				}
			}
			
		}
		
		catch(Exception ex){
			logInfo("Exception thrown in verifyGoalsIsPresent() method "+ex.getMessage());
			throw ex;
		}
		return isGoalFound;
	}
	
	public boolean updateGoal(String goal,String updateGoal) throws Exception{
		boolean isGoalUpdated = false;
		try{
			logInfo("Entered into updateGoal() method");
			System.out.println("Entered into updateGoal() method");
			waitOnElement("cssSelector",lstGoals);
			List <WebElement> goalsList = driver().findElements(By.cssSelector(lstGoals));
			
			if(goalsList.size() > 0){
				
				String before = "div#goal-details>div:nth-child(";
				String after = ")>div:nth-child(2)>h3>a";
				
				for(int i=1;i<=goalsList.size();i++){
					WebElement el = driver().findElement(By.cssSelector(before+i+after));
					String goalName = el.getText().trim();
					if(goalName.equalsIgnoreCase(goal)){
						
						String beforeEdit = "div#goal-details>div:nth-child(";
						String afterEdit = ")>div:nth-child(2)>p>a:nth-child(3)";
						
						WebElement ele = driver().findElement(By.cssSelector(beforeEdit+i+afterEdit));
						ele.click();
						break;

					}
				}
				updateGoalFromModal(updateGoal);
				isGoalUpdated = verifyGoalIsPresent(updateGoal);
				
				if(isGoalUpdated == false){
					
					Assert.assertTrue(isGoalUpdated, updateGoal+" goal is not updated in the Goals list");
				}

			}
			
		}
		
		catch(Exception ex){
			logInfo("Exception thrown in updateGoal() method "+ex.getMessage());
			System.out.println("Exception thrown in updateGoal() method "+ex.getMessage());
			throw ex;
		}
		return isGoalUpdated;
	}

	private void updateGoalFromModal(String updatedGoalTitle) throws Exception, Exception {
		waitOnElement("cssSelector",goalTitle);
		inputTextClear("cssSelector",goalTitle);
		inputText("cssSelector",goalTitle,updatedGoalTitle);
		clickOnButton("xpath", btnUpdateGoal);
		waitOnElement("xpath", spinner);
	}
	
	public boolean deleteGoal(String goal) throws Exception{
		boolean isGoalDeleted = false;
		try{
			logInfo("Entered into deleteGoal() method");
			System.out.println("Entered into deleteGoal() method");
			waitOnElement("cssSelector",lstGoals);
			List <WebElement> goalsList = driver().findElements(By.cssSelector(lstGoals));
			
			if(goalsList.size() > 0){
				
				String before = "div#goal-details>div:nth-child(";
				String after = ")>div:nth-child(2)>h3>a";
				
				for(int i=1;i<=goalsList.size();i++){
					WebElement el = driver().findElement(By.cssSelector(before+i+after));
					String goalName = el.getText().trim();
					if(goalName.equalsIgnoreCase(goal)){
						isGoalDeleted = true;
						String beforeDelete = "div#goal-details>div:nth-child(";
						String afterDelete = ")>div:nth-child(2)>p>a:nth-child(4)";
						
						WebElement ele = driver().findElement(By.cssSelector(beforeDelete+i+afterDelete));
						ele.click();
						confirmOK();
						confirmationMessage("Goal deleted successfully.");
						break;
					}
				}
				
				
				if(isGoalDeleted == false){
					
					Assert.assertTrue(isGoalDeleted, goal+" goal could not be deleted.");
				}

			}
			
		}
		
		catch(Exception ex){
			logInfo("Exception thrown in deleteGoal() method "+ex.getMessage());
			System.out.println("Exception thrown in deleteGoal() method "+ex.getMessage());
			throw ex;
		}
		return isGoalDeleted;
	}
	
	public boolean markAsComplete(String goalTitle) throws Exception{
		boolean isMarkAsComplete = false;
		try{
			logInfo("Entered into markAsComplete() method");
			System.out.println("Entered into markAsComplete() method");
			waitOnElement("cssSelector",lstGoals);
			List <WebElement> goalsList = driver().findElements(By.cssSelector(lstGoals));
			
			if(goalsList.size() > 0){
				
				String before = "div#goal-details>div:nth-child(";
				String after = ")>div:nth-child(2)>h3>a";
				
				for(int i=1;i<=goalsList.size();i++){
					WebElement el = driver().findElement(By.cssSelector(before+i+after));
					String goalName = el.getText().trim();
					if(goalName.equalsIgnoreCase(goalTitle)){
						isMarkAsComplete = true;
						
						String beforeDelete = "div#goal-details>div:nth-child(";
						String afterDelete = ")>div:nth-child(2)>p>a:nth-child(2)";
						
						WebElement ele = driver().findElement(By.cssSelector(beforeDelete+i+afterDelete));
						ele.click();
						confirmationMessage("Goal completed successfully.");
						break;
					}
				}
	
			}
			
		}
		
		catch(Exception ex){
			logInfo("Exception thrown in markAsComplete() method "+ex.getMessage());
			System.out.println("Exception thrown in markAsComplete() method "+ex.getMessage());
			throw ex;
		}
		return isMarkAsComplete;
	}
	
	public boolean viewGoalFromBoard(String goalTitle) throws Exception{
		boolean isGoalViewed = false;
		try{
			logInfo("Entered into viewGoalFromBoard() method");
			System.out.println("Entered into viewGoalFromBoard() method");
			waitOnElement("cssSelector",lstGoals);
			List <WebElement> goalsList = driver().findElements(By.cssSelector(lstGoals));
			
			if(goalsList.size() > 0){
				
				String before = "//*[@id='goal-details']/div[";
				String after = "]/div[2]/h3/a";
				
				for(int i=1;i<=goalsList.size();i++){
					WebElement el = driver().findElement(By.xpath(before+i+after));
					System.out.println(el.getText().trim());
					String goalName = el.getText().trim();
					if(goalName.equalsIgnoreCase(goalTitle)){
												
						String beforeView = "ul#dream_board_list>li:nth-child(";
						String afterView = ")>a";
						
						WebElement ele = driver().findElement(By.cssSelector(beforeView+i+afterView));
						ele.click();
						
						waitOnElement("cssSelector",txtGoalTitle);
						String goal = driver().findElement(By.cssSelector(txtGoalTitle)).getText().trim();
						if(goal.equalsIgnoreCase(goalTitle)){
							isGoalViewed = true;
						}
						break;
					}
				}
	
			}
			
		}
		
		catch(Exception ex){
			logInfo("Exception thrown in viewGoalFromBoard() method "+ex.getMessage());
			System.out.println("Exception thrown in viewGoalFromBoard() method "+ex.getMessage());
			throw ex;
		}
		return isGoalViewed;
	}
	
	public void closeGoalView() throws  Exception{
		logInfo("Entered into closeGoalView() method");
		System.out.println("Entered into closeGoalView() method");
		waitOnElement("xpath", closeGoal);
		WebElement el = driver().findElement(By.cssSelector(closeGoal));
		el.click();
	}
	
	public boolean likeAGoal(String goalTitle) throws Exception{
		boolean isGoalLiked = false;
		try{
			logInfo("Entered into likeAGoal() method");
			System.out.println("Entered into likeAGoal() method");
			
			waitOnElement("xpath",lnkLikeGoal);
			clickOnElement("xpath", lnkLikeGoal);
			
			waitOnElement("xpath",txtLiked);
			WebElement likeText = driver().findElement(By.xpath(txtLiked));
			if(likeText.getText().trim().equalsIgnoreCase("Liked")){
				isGoalLiked = true;
			}

		}
		catch(Exception ex){
			logInfo("Exception thrown in likeAGoal() method "+ex.getMessage());
			System.out.println("Exception thrown in likeAGoal() method "+ex.getMessage());
			throw ex;
		}
		return isGoalLiked;
	}
	
	public boolean commentAGoal(String goalTitle) throws Exception{
		boolean isGoalCommented = false;
		try{
			logInfo("Entered into commentAGoal() method");
			System.out.println("Entered into commentAGoal() method");
			
			waitOnElement("xpath",lnkCommentGoal);
			clickOnElement("xpath", lnkCommentGoal);
			
			waitOnElement("xpath",inputCommentGoal);
			inputText("xpath",inputCommentGoal,txtGoalComments);
			clickOnElement("xpath",btnCommentGoal);
			System.out.println("Goal has been commented");
			
			waitOnElement("xpath",lstGoalComments);
			List<WebElement> commentsList = driver().findElements(By.xpath(lstGoalComments));
			if(commentsList.size()>0){
				
				String before = "//*[contains(@class,'comments_goal')]/div[contains(@id,'comment_body_comment')][";
				String after = "]/div[2]/p";
				
				for(int i=1;i<=commentsList.size();i++){
					WebElement el = driver().findElement(By.xpath(before+i+after));
					if(el.getText().trim().equalsIgnoreCase(txtGoalComments)){
						isGoalCommented = true;
						break;
					}
				}

			}


		}
		catch(Exception ex){
			logInfo("Exception thrown in commentAGoal() method "+ex.getMessage());
			System.out.println("Exception thrown in commentAGoal() method "+ex.getMessage());
			throw ex;
		}
		return isGoalCommented;
	}
	
	
	public boolean editGoalFromGoalsView(String goalTitle,String updateGoalTitle) throws Exception{
		boolean isGoalUpdated = false;
		try{
			logInfo("Entered into editGoalFromGoalsView() method");
			System.out.println("Entered into editGoalFromGoalsView() method");
			
			waitOnElement("xpath",lnkEditGoal);
			clickOnElement("xpath", lnkEditGoal);
			updateGoalFromModal(updateGoalTitle);
			isGoalUpdated = verifyGoalIsPresent(updateGoalTitle);
			
		}
		catch(Exception ex){
			logInfo("Exception thrown in editGoalFromGoalsView() method "+ex.getMessage());
			System.out.println("Exception thrown in editGoalFromGoalsView() method "+ex.getMessage());
			throw ex;
		}
		return isGoalUpdated;
	}
	
	public void deleteGoalFromGoalView() throws Exception{
		try{
			logInfo("Entered into deleteGoalFromGoalView() method");
			System.out.println("Entered into deleteGoalFromGoalView() method");
			
			waitOnElement("xapth",lnkDeleteGoal);
			clickOnElement("xapth",lnkDeleteGoal);
			confirmOK();
			confirmationMessage("Goal deleted successfully.");

		}
		catch(Exception ex){
			logInfo("Exception thrown in deleteGoalFromGoalView() method "+ex.getMessage());
			System.out.println("Exception thrown in deleteGoalFromGoalView() method "+ex.getMessage());
			throw ex;
		}

	}
	
	public boolean filterGoalByStatus(String status,String goalTitle) throws Exception{
		try{
			boolean isFiltered = false;
			logInfo("Entered into filterGoalsByStatus() method");
			System.out.println("Entered into filterGoalsByStatus() method");
			waitOnElement("xpath",btnFilterGoals);
			clickOnElement("xpath",btnFilterGoals);
			waitOnElement("xpath",lstgoalFilters);
			List<WebElement> li = driver().findElements(By.xpath(lstgoalFilters));
			
			if(li.size()>0){
				String before = "//*[@id='goals_by_status_container']/ul/li[@class='dropdown-option'][";
				String after = "]/a";
				
				for(int i=1;i<=li.size();i++){
					WebElement el = driver().findElement(By.xpath(before+i+after));
					if(el.getText().contains(status)){
						el.click();
						Thread.sleep(2000);
						if(status.equalsIgnoreCase("achieved")){
							isFiltered = verifyAchievedGoalIsPresent(goalTitle);
						}else{
							isFiltered = verifyGoalIsPresent(goalTitle);
						}
						
						switch(status){
						
						case "All":
							if(!isFiltered){
								Assert.assertTrue(isFiltered, "Unable to filter the goal with All Status");
							}
							break;
						case "inprogress":
							if(!isFiltered){
								Assert.assertTrue(isFiltered, "Unable to filter the goal with inprogress Status");
							}
							break;
						case "last 2 months":
							if(!isFiltered){
								Assert.assertTrue(isFiltered, "Unable to filter the goal with last 2 months Status");
							}
							break;
						case "last 6 months":
							if(!isFiltered){
								Assert.assertTrue(isFiltered, "Unable to filter the goal with last 6 months Status");
							}
							break;
						case "last 1 year":
							if(!isFiltered){
								Assert.assertTrue(isFiltered, "Unable to filter the goal with last 1 year Status");
							}
							break;
						case "achieved":
							if(isFiltered){
								Assert.assertFalse(isFiltered, "Unable to filter the goal with achieved Status");
							}
							break;
						case "overdue":
							if(isFiltered){
								Assert.assertFalse(isFiltered, "Unable to filter the goal with overdue Status");
							}
							break;
						}
					}
				}
			}
			
			return isFiltered;
		}
		catch(Exception ex){
			logInfo("Exception thrown in filterGoalsByStatus() method "+ex.getMessage());
			System.out.println("Exception thrown in filterGoalsByStatus() method "+ex.getMessage());
			throw ex;
		}

	}
	
	
	public void addGoalTask(String goalTitle,String taskTitle) throws Exception{
		try{
			logInfo("Entered into addGoalTask() method");
			System.out.println("Entered into addGoalTask() method");
			
			waitOnElement("cssSelector",lstGoals);
			List <WebElement> goalsList = driver().findElements(By.cssSelector(lstGoals));
			
			if(goalsList.size() > 0){
				
				String before = "div#goal-details>div:nth-child(";
				String after = ")>div:nth-child(2)>h3>a";
				
				for(int i=1;i<=goalsList.size();i++){
					waitOnElement("cssSelector",before+i+after);
					WebElement el = driver().findElement(By.cssSelector(before+i+after));
					String title = el.getText().trim();
					if(title.equalsIgnoreCase(goalTitle)){
						String before_add_task = "div#goal-details>div:nth-child(";
						String after_add_task = ")>div:nth-child(2)>p.links>a:nth-child(1)";
						waitOnElement("cssSelector",before_add_task+i+after_add_task);
						clickOnElement("cssSelector",before_add_task+i+after_add_task);
						break;
					}
										
				}
				
				tm.addTaskFromGoal(taskTitle,3);
			}
			
		}
		
		catch(Exception ex){
			logInfo("Exception thrown in addGoalTask() method "+ex.getMessage());
			System.out.println("Exception thrown in addGoalTask() method "+ex.getMessage());
			throw ex;
		}

	}
	
	public boolean verifyGoalTask(String goalTitle,String taskTitle) throws Exception{
		boolean isTaskFound = false;
		try{
			logInfo("Entered into verifyGoalTask() method");
			System.out.println("Entered into verifyGoalTask() method");
			go2Goals();
			waitOnElement("cssSelector",lstGoals);
			List <WebElement> goalsList = driver().findElements(By.cssSelector(lstGoals));
			
			if(goalsList.size() > 0){
				
				String before = "div#goal-details>div:nth-child(";
				String after = ")>div:nth-child(2)>h3>a";
				
				for(int i=1;i<=goalsList.size();i++){
					WebElement el = driver().findElement(By.cssSelector(before+i+after));
					String goal = el.getText().trim();
					if(goalTitle.equalsIgnoreCase(goal)){
						
						String before_task_list = "div#goal-details>div:nth-child(";
						String after_task_list = ")>div:nth-child(4)>div>table>tbody>tr.completed-false";
						
						List<WebElement> tasks = driver().findElements(By.cssSelector(before_task_list+i+after_task_list));
						if(tasks.size()>0){
							for(int j=1;j<=tasks.size();j++){
								String before_task = "//*[@id='goal-details']/div[";
								String middle_task = "]/div[4]/div/table/tbody/tr[@class='completed-false'][";
								String after_task = "]/td[2]/a";
								WebElement goalTask = driver().findElement(By.xpath(before_task+i+middle_task+j+after_task));
								if(goalTask.getText().trim().equalsIgnoreCase(taskTitle)){
									isTaskFound = true;
									goalTask.click();
									break;
								}
							}
						}
						break;
					}
										
				}
			}
		}
		catch(Exception ex){
			logInfo("Exception thrown in verifyGoalTask() method "+ex.getMessage());
			System.out.println("Exception thrown in verifyGoalTask() method "+ex.getMessage());
			throw ex;
		}
		return isTaskFound;
	}
	
	public boolean viewGoalFromTask(String title) throws Exception{
		boolean isGoalFound = false;
		try{
			logInfo("Entered into viewGoalFromTask() method");
			System.out.println("Entered into viewGoalFromTask() method");
			
			waitOnElement("cssSelector",lstGoals);
			List <WebElement> goalsList = driver().findElements(By.cssSelector(lstGoals));
			
			if(goalsList.size() > 0){
				
				String before = "div#goal-details>div:nth-child(";
				String after = ")>div:nth-child(2)>h3>a";
				
				for(int i=1;i<=goalsList.size();i++){
					WebElement el = driver().findElement(By.cssSelector(before+i+after));
					String goalTitle = el.getText().trim();
					if(title.equalsIgnoreCase(goalTitle)){
						String before_add_task = "div#goal-details>div:nth-child(";
						String after_add_task = ")>div:nth-child(2)>p.links>a:nth-child(1)";
						clickOnElement("cssSelector",before_add_task+i+after_add_task);
						break;
					}
										
				}
				
				waitOnElement("xpath",btnViewGoal);
				clickOnElement("xpath",btnViewGoal);
				waitOnElement("cssSelector","#community-tabs>p");
				WebElement goal = driver().findElement(By.cssSelector("#community-tabs>p:nth-child(2)"));
				if(goal.getText().trim().contains(title)){
					isGoalFound = true;
				}

			}
			
		}
		catch(Exception ex){
			logInfo("Exception thrown in viewGoalFromTask() method "+ex.getMessage());
			System.out.println("Exception thrown in viewGoalFromTask() method "+ex.getMessage());
			throw ex;
		}
		return isGoalFound;
	}
	
	public void markTaskAsCompleted() throws Exception{
		try{
			boolean isTaskFound = false;
			logInfo("Entered into markTaskAsCompleted() method");
			System.out.println("Entered into markTaskAsCompleted() method");
			
			waitOnElement("xpath",btnMarkGoal);
			clickOnElement("xpath",btnMarkGoal);
			
			confirmationMessage("Task has been marked complete");
		}
		catch(Exception ex){
			logInfo("Exception thrown in markTaskAsCompleted() method "+ex.getMessage());
			System.out.println("Exception thrown in markTaskAsCompleted() method "+ex.getMessage());
			throw ex;
		}
	}
	
	public void deleteGoalTask(String goalTitle,String taskTitle) throws Exception{
		try{
			boolean isTaskFound = false;
			logInfo("Entered into deleteGoalTask() method");
			System.out.println("Entered into deleteGoalTask() method");
			
			waitOnElement("cssSelector",lstGoals);
			List <WebElement> goalsList = driver().findElements(By.cssSelector(lstGoals));
			
			if(goalsList.size() > 0){
				
				String before = "div#goal-details>div:nth-child(";
				String after = ")>div:nth-child(2)>h3>a";
				
				for(int i=1;i<=goalsList.size();i++){
					WebElement el = driver().findElement(By.cssSelector(before+i+after));
					String goal = el.getText().trim();
					
					if(goalTitle.equalsIgnoreCase(goal)){
						
						String before_task_list = "div#goal-details>div:nth-child(";
						String after_task_list = ")>div:nth-child(4)>div>table>tbody>tr.completed-false";
						
						List<WebElement> tasks = driver().findElements(By.cssSelector(before_task_list+i+after_task_list));
						
						if(tasks.size()>0){
							for(int j=1;j<=tasks.size();j++){
								String before_task = "//*[@id='goal-details']/div[";
								String middle_task = "]/div[4]/div/table/tbody/tr[@class='completed-false'][";
								String after_task = "]/td[2]/a";
								WebElement goalTaskTitle = driver().findElement(By.xpath(before_task+i+middle_task+j+after_task));
								if(goalTaskTitle.getText().trim().equalsIgnoreCase(taskTitle)){
									isTaskFound = true;
									if(isTaskFound){
										String before_task_delete = "//*[@id='goal-details']/div[";
										String middle_task_delete = "]/div[4]/div/table/tbody/tr[@class='completed-false'][";
										String after_task_delete = "]/td[4]/a";
										clickOnElement("xpath",before_task_delete+i+middle_task_delete+j+after_task_delete);
										confirmOK();
										confirmationMessage("Task was successfully Deleted");
										break;
									}
								}
							}
						}
						
						break;
					}
										
				}
			}
		}
		catch(Exception ex){
			logInfo("Exception thrown in deleteGoalTask() method "+ex.getMessage());
			System.out.println("Exception thrown in deleteGoalTask() method "+ex.getMessage());
			throw ex;
		}
	}
	
	public void markAsIncomplete() throws Exception{
		try{
			logInfo("Entered into markAsIncomplete() method");
			System.out.println("Entered into markAsIncomplete() method");
			
			waitOnElement("xpath",btnMarkGoal);
			clickOnElement("xpath",btnMarkGoal);
			
			confirmationMessage("Task has been marked incomplete");
			
		}
		catch(Exception ex){
			logInfo("Exception thrown in markAsIncomplete() method "+ex.getMessage());
			System.out.println("Exception thrown in markAsIncomplete() method "+ex.getMessage());
			throw ex;
		}
		
	}
	
	public void editGoalTask(String taskTitle) throws Exception{
		try{
			logInfo("Entered into editGoalTask() method");
			System.out.println("Entered into editGoalTask() method");
			
			waitOnElement("xpath",btnEditGoalTask);
			clickOnElement("xpath",btnEditGoalTask);
			
			waitOnElement("cssSelector",inputTaskTitle);
			inputTextClear("cssSelector",inputTaskTitle);
			inputText("cssSelector",inputTaskTitle,taskTitle);
			clickOnElement("xpath",btnUpdateTask);
			confirmationMessage("Your task has been updated.");
		}
		catch(Exception ex){
			logInfo("Exception thrown in editGoalTask() method "+ex.getMessage());
			System.out.println("Exception thrown in editGoalTask() method "+ex.getMessage());
			throw ex;
		}
		
	}
	
	public void deleteGoalTask() throws Exception{
		try{
			logInfo("Entered into deleteGoalTask() method");
			System.out.println("Entered into deleteGoalTask() method");
			
			waitOnElement("xpath",btnDeleteTask);
			clickOnElement("xpath",btnDeleteTask);
			confirmOK();
			confirmationMessage("Your task has been deleted.");
			
		}
		catch(Exception ex){
			logInfo("Exception thrown in deleteGoalTask() method "+ex.getMessage());
			System.out.println("Exception thrown in deleteGoalTask() method "+ex.getMessage());
			throw ex;
		}
		
	}
	
	public void selectFacebookIcon() throws Exception{
		logInfo("Entered into selectFacebookIcon() method");
		System.out.println("Entered into selectFacebookIcon() method");
		waitOnElement("xpath", "//div[@class='col-md-12 social-media hidden-xs hidden-sm']/div[contains(@id,'add_this_goal')]/a[1]");
		clickOnElement("xpath", "//div[@class='col-md-12 social-media hidden-xs hidden-sm']/div[contains(@id,'add_this_goal')]/a[1]");	
		
	}
	
	public void shareInFaceBook(String postMessage) throws Exception{
		logInfo("Enter login credentials in FaceBook to share the posts.");
		String wndBeforeWindow = driver().getWindowHandle();	
		for(String w : driver().getWindowHandles()){
				if(!w.equalsIgnoreCase(wndBeforeWindow)){
					driver().switchTo().window(w);			
					waitOnElement("cssSelector", fbEmail);
					inputTextClear("cssSelector", fbEmail);
					inputText("cssSelector", fbEmail, fBuserName_Text);
					waitOnElement("cssSelector", fbPassword);
					inputTextClear("cssSelector", fbPassword);
					inputText("cssSelector", fbPassword, fBPwd_text);
					waitOnElement("cssSelector", fbPassword);
					submitElement("cssSelector", fbPassword);
					 WebElement nam = driver().findElement(org.openqa.selenium.By.xpath(".//*[@id='u_0_s']"));            
			         nam.clear();
			         System.out.println(nam.getAttribute("placeholder"));
			         nam.sendKeys(postMessage);
					clickOnElement("xpath", "//*[@id='u_0_1w']");	
	
					driver().switchTo().window(wndBeforeWindow);
					break;	
				}
		}

	} 
	
	 public void selectTwitterIcon() throws  Exception{
		logInfo("Entered into selectTwitterIcon() method.");
		System.out.println("Entered into selectTwitterIcon() method.");
		waitOnElement("xpath", "//div[@class='col-md-12 social-media hidden-xs hidden-sm']/div[contains(@id,'add_this_goal')]/a[2]");	
		clickOnElement("xpath", "//div[@class='col-md-12 social-media hidden-xs hidden-sm']/div[contains(@id,'add_this_goal')]/a[2]");		

	 }
	   
	   public void shareInTwitter() throws Exception{		
	   logInfo("Entered into shareInTwitter() method.");
			String wndBeforeWindow = driver().getWindowHandle();	
			for(String w : driver().getWindowHandles()){
				if(!w.equalsIgnoreCase(wndBeforeWindow)){
					driver().switchTo().window(w);
					waitOnElement("cssSelector", twitLogin);
					inputTextClear("cssSelector", twitLogin);
					inputText("cssSelector", twitLogin, fBEmail_text);
					inputTextClear("cssSelector", twitPswD);
					inputText("cssSelector", twitPswD, fBPwd_text);	
					submitElement("cssSelector", twitPswD);
					Thread.sleep(3000);
					waitOnElement("cssSelector", twtTweet);	
					getText("cssSelector", twtTweet, "Text us ");	
					clickOnElement("cssSelector", twtTweet);	
					driver().switchTo().window(wndBeforeWindow);
					driver().manage().deleteAllCookies();
					break;
				}
			}  
		} 
	   
	   public void selectShareEmailIcon() throws Exception{
		logInfo("Entered into selectShareEmailIcon() method");
		System.out.println("Entered into selectShareEmailIcon() method");
		waitOnElement("xpath", "//*[@id='page']/div[3]/div/div/div[3]/div[2]/div/div[2]/a");
		clickOnElement("xpath", "//*[@id='page']/div[3]/div/div/div[3]/div[2]/div/div[2]/a");			
	  }
	
	   public void waitOnSpinner() throws  Exception{
		 logInfo("inside waitOnSpinner() method");
		 waitOnElement("xpath","//*[@id='spinner-container'][contains(@style,'display: none')]");
		 verifyElementPresent("xpath","//*[@id='spinner-container'][contains(@style,'display: none')]");
	   }
	   
	   public void shareEventByEmail(  String mailID) throws Exception{
		    logInfo("Inside shareEventByEmail Method");	
		    waitOnElement("cssSelector",recipientsTo);
		  	WebElement composeTo = driver().findElement(org.openqa.selenium.By.cssSelector(recipientsTo)); // inputVibeComposeTo
			inputText("cssSelector", recipientsTo, mailID);
			waitOnElement("xpath","//*[contains(@id,'highlighted')]");
			clickOnElement("xpath","//*[contains(@id,'highlighted')]");
			composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]", inboxMsgBodyText);
			Thread.sleep(10000);
			scrollDown("linkText", "Send");
			clickOnLink("linkText", "Send");
			System.out.println("sent mail to gmail with subject ");
			confirmationMessage("Message sent to: "+mailID);
					
		}	

}
