package vibe.tasks2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.contacts2.tests.BusinessContactsTests;
import vibe.tasks.tests.TaskMethods;
import vibe.tasks2.tests.TasksMethods;
import vibe.widgets.tests.WidgetsMethods;

public class TasksTests extends TasksMethods{
	
	
	TaskMethods tm = new TaskMethods();
	BusinessContactsTests bct = new BusinessContactsTests();	
	BusinessContactsMethods bcm = new BusinessContactsMethods();
	WidgetsMethods wm = new WidgetsMethods();
	
	// String ContactName = "Test Contact";

	@Test(priority=10606)
	public void AddTodaysDateTasks() throws Exception{
		logInfo("inside AddTodaysDateTasks() Test");
		
		go2UserTasksPage();	
		addTask("TodaysTask",todaysTask_text);  // 
				
		selectTasksPanel("Task Today");
		boolean isTaskFoundInTodaysWidget = verifyTaskPresentInWidget("Task Today",todaysTask_text);
		System.out.println("isTaskFoundInTodaysWidget =" +isTaskFoundInTodaysWidget);
		if (isTaskFoundInTodaysWidget==false){
			logInfo(todaysTask_text + " task should present in Today's Task widget.");
			Assert.assertTrue(isTaskFoundInTodaysWidget, todaysTask_text + " task is not present in Today's Task widget.");
		} 
		
		selectTasksPanel("Task All");
		boolean isTaskFoundInAllTasksWidget = verifyTaskPresentInWidget("Task All",todaysTask_text);
		System.out.println("isTaskFoundInAllTasksWidget =" +isTaskFoundInAllTasksWidget);
		if (isTaskFoundInAllTasksWidget==false){
			logInfo(todaysTask_text + " task should present in All Task widget.");
			Assert.assertTrue(isTaskFoundInAllTasksWidget, todaysTask_text + " task is not present in All Task widget.");
		} 
		
		selectTasksPanel("Tasks Completed");
		boolean isTaskFoundCompleteWidget = verifyTaskPresentInWidget("Tasks Completed",todaysTask_text);
		System.out.println("isTaskFoundIncompleteWidget =" +isTaskFoundCompleteWidget);
		if (isTaskFoundCompleteWidget==true){
			logInfo(todaysTask_text + " task should not present in completed widget.");
			Assert.assertFalse(isTaskFoundCompleteWidget, todaysTask_text + " task should not present in Completed Task widget.");
		} 
		
		selectTasksPanel("Task Today");
		performActionOnTask("Task Today",todaysTask_text,"Delete Task");
	}
	
	
	@Test(priority=10607)
	public void AddFutureDateTasks() throws Exception{
		logInfo("inside addFutureDateTasks() Test");
		
		go2UserTasksPage();	
		addTask("FutureTask",futureTask_text);
		
		selectTasksPanel("Task Today");
		boolean isFutureTaskFoundInTodaysWidget = verifyTaskPresentInWidget("Task Today",futureTask_text);
		System.out.println("isTaskFoundInTodaysWidget =" +isFutureTaskFoundInTodaysWidget);
		if (isFutureTaskFoundInTodaysWidget==true){
			logInfo(futureTask_text + " task should present in Today's Task widget.");
			Assert.assertTrue(isFutureTaskFoundInTodaysWidget, futureTask_text + " task is not present in Today's Task widget.");
		} 
		
		selectTasksPanel("Task All");
		boolean isFutureTaskFoundInAllTasksWidget = verifyTaskPresentInWidget("Task All",futureTask_text);
		System.out.println("isFutureTaskFoundInAllTasksWidget =" +isFutureTaskFoundInAllTasksWidget);
		if (isFutureTaskFoundInAllTasksWidget==false){
			logInfo(futureTask_text + " task should present in All Task widget.");
			Assert.assertTrue(isFutureTaskFoundInAllTasksWidget, futureTask_text + " task is not present in All Task widget.");
		} 
		
		selectTasksPanel("Tasks Completed");
		boolean isFutureTaskFoundCompleteWidget = verifyTaskPresentInWidget("Tasks Completed",futureTask_text);
		System.out.println("isFutureTaskFoundCompleteWidget =" +isFutureTaskFoundCompleteWidget);
		if (isFutureTaskFoundCompleteWidget==true){
			logInfo(futureTask_text + " task should not present in completed widget.");
			Assert.assertFalse(isFutureTaskFoundCompleteWidget, futureTask_text + " task should not present in Completed Task widget.");
		} 
		
		selectTasksPanel("Task All");
		performActionOnTask("Task All",futureTask_text,"Delete Task");
	}
	
	
	@Test(priority=10608)
	public void SearchATask() throws Exception{
		logInfo("inside SearchATask() Test");
		
		go2UserTasksPage();	
		addTask("TodaysTask",searchTask_text);
		
		selectTasksPanel("Tasks All");
		boolean isSearchTaskFoundInAllTasksWidget = searchTaskPresentinWidget(searchTask_text,"Task All");
		System.out.println("isSearchTaskFoundInAllTasksWidget =" +isSearchTaskFoundInAllTasksWidget);
		if (isSearchTaskFoundInAllTasksWidget==false){
			logInfo(searchTask_text + " task should present in All Task widget.");
			Assert.assertTrue(isSearchTaskFoundInAllTasksWidget, searchTask_text + " task is not present in All Task widget.");
		} 
		
		selectTasksPanel("Task Today");
		boolean isSearchTaskFoundInTodaysWidget = searchTaskPresentinWidget(searchTask_text,"Task Today");
		System.out.println("isSearchTaskFoundInTodaysWidget =" +isSearchTaskFoundInTodaysWidget);
		if (isSearchTaskFoundInTodaysWidget==false){
			logInfo(searchTask_text + " task should present in Today's Task widget.");
			Assert.assertTrue(isSearchTaskFoundInTodaysWidget, searchTask_text + " task is not present in Today's Task widget.");
		} 
			
		selectTasksPanel("Tasks Completed");
		boolean isSearchTaskFoundCompleteWidget = searchTaskPresentinWidget(searchTask_text,"Tasks Completed");
		System.out.println("isSearchTaskFoundCompleteWidget =" +isSearchTaskFoundCompleteWidget);
		if (isSearchTaskFoundCompleteWidget==true){
			logInfo(searchTask_text + " task should not present in completed widget.");
			Assert.assertFalse(isSearchTaskFoundCompleteWidget, searchTask_text + " task should not present in Completed Task widget.");
		} 
		
		selectTasksPanel("Task All");
		performActionOnTask("Task All",searchTask_text,"Delete Task");
	}
	
	@Test(priority=10609)
	public void MarkTaskAsComplete() throws Exception{
		logInfo("inside MarkTaskAsComplete() Test");
		
		go2UserTasksPage();	
		addTask("TodaysTask",completeTask_text);
		//confirmationMessage("Your task has been created.");
		
		selectTasksPanel("Task Today");
		boolean isTaskFoundInTodaysWidget = verifyTaskPresentInWidget("Task Today",completeTask_text);
		System.out.println("isTaskFoundInTodaysWidget =" +isTaskFoundInTodaysWidget);
		if (isTaskFoundInTodaysWidget==false){
			logInfo(completeTask_text + " task should present in Today's Task widget.");
			Assert.assertTrue(isTaskFoundInTodaysWidget, completeTask_text + " task is not present in Today's Task widget.");
		} 
		
		//selectTasksPanel("Task Today");
		performActionOnTask("Task Today",completeTask_text,"Mark As Complete");
		
		selectTasksPanel("Task Today");
		boolean isTaskFoundInTodaysWidget2 = verifyTaskPresentInWidget("Task Today",completeTask_text);
		if (isTaskFoundInTodaysWidget2==true){
			logInfo(completeTask_text + " task should not present in Today's Task widget.");
			Assert.assertFalse(isTaskFoundInTodaysWidget2, completeTask_text + " task should not present in Today's Task widget.");
		} 
		
		selectTasksPanel("Tasks Completed");
		boolean isTaskFoundCompletedWidget = verifyTaskPresentInWidget("Tasks Completed",completeTask_text);
		System.out.println("isTaskFoundCompletedWidget =" + isTaskFoundCompletedWidget);
		if (isTaskFoundCompletedWidget==false){
			logInfo(completeTask_text + " task should present in Completed Task widget");
			Assert.assertTrue(isTaskFoundCompletedWidget, completeTask_text + " task should present in Completed Task widget");
		} 
		
		selectTasksPanel("Task Today");
		selectTasksPanel("Tasks Completed");
		performActionOnTask("Tasks Completed",completeTask_text,"Delete Task");
	}
	
	
	@Test(priority=10610)
	public void AutoCompleteTask() throws Exception{
		logInfo("inside AutoCompleteTask() Test");
		
		go2UserTasksPage();	
		addTask("TodaysTask",autocompleteTask_text);
		//confirmationMessage("Your task has been created.");
		
		selectTasksPanel("Task Today");
		autoCompleteTask("Task Today",autocompleteTask_text);
		
		selectTasksPanel("Task Today");
		boolean isTaskFoundInTodaysWidget2 = verifyTaskPresentInWidget("Task Today",autocompleteTask_text);
		if (isTaskFoundInTodaysWidget2==true){
			logInfo(autocompleteTask_text + " task should not present in Today's Task widget.");
			Assert.assertFalse(isTaskFoundInTodaysWidget2, autocompleteTask_text + " task should not present in Today's Task widget.");
		} 
		
		selectTasksPanel("Tasks Completed");
		boolean isTaskFoundCompletedWidget = verifyTaskPresentInWidget("Tasks Completed",autocompleteTask_text);
		System.out.println("isTaskFoundCompletedWidget =" + isTaskFoundCompletedWidget);
		if (isTaskFoundCompletedWidget==false){
			logInfo(autocompleteTask_text + " task not present in Completed Task widget");
			Assert.assertTrue(isTaskFoundCompletedWidget, autocompleteTask_text + " task not present in Completed Task widget");
		} else {
			logInfo(autocompleteTask_text + " task present in Completed Task widget");
			autoCompleteTask("Tasks Completed",autocompleteTask_text);
		}
		
		selectTasksPanel("Task Today");
		boolean isTaskFoundInTodaysWidget3 = verifyTaskPresentInWidget("Task Today",autocompleteTask_text);
		System.out.println("isTaskFoundInTodaysWidget =" +isTaskFoundInTodaysWidget3);
		if (isTaskFoundInTodaysWidget3==true){
			logInfo(autocompleteTask_text + " task marked as Incomplete sucessfully.");
		} 
		
		selectTasksPanel("Task All");
		performActionOnTask("Task All",autocompleteTask_text,"Delete Task");
	}
	
	
	@Test(priority=10611)  
	public void MarkTaskAsIncomplete() throws Exception{
		logInfo("inside MarkTaskAsIncomplete() Test");
		
		go2UserTasksPage();
		addTask("TodaysTask",incompleteTask_text);
		//confirmationMessage("Your task has been created.");
		
		selectTasksPanel("Task Today");
		boolean isTaskFoundInTodaysWidget1 = verifyTaskPresentInWidget("Task Today",incompleteTask_text);
		if (isTaskFoundInTodaysWidget1==true){
			logInfo(incompleteTask_text + " task present in Today's Task widget.");
			performActionOnTask("Task Today",incompleteTask_text,"Mark As Complete");
		} else {
			logInfo(incompleteTask_text + " task should present in Today's Task widget.");
			Assert.assertTrue(isTaskFoundInTodaysWidget1, incompleteTask_text + " task should present in Today's Task widget.");
		}
		
		selectTasksPanel("Tasks Completed");
		boolean isTaskFoundCompletedWidget = verifyTaskPresentInWidget("Tasks Completed",incompleteTask_text);
		if (isTaskFoundCompletedWidget==true){
			logInfo(incompleteTask_text + " task present in completed task widget.");
			performActionOnTask("Tasks Completed",incompleteTask_text,"Mark As Incomplete");
		} else {
			logInfo(incompleteTask_text + " task not present in completed task widget.");
			Assert.assertTrue(isTaskFoundCompletedWidget, incompleteTask_text + " task not present in completed task widget.");
		}
		
		// Verify incomplete tasks in Completed Task Widget
		
		selectTasksPanel("Tasks Completed");
		boolean isTaskFoundCompletedWidget1 = verifyTaskPresentInWidget("Tasks Completed",incompleteTask_text);
		if (isTaskFoundCompletedWidget1==true){
			logInfo(incompleteTask_text + " task should not present in Completed Task widget");
			Assert.assertFalse(isTaskFoundCompletedWidget1, incompleteTask_text + " task should not in Completed Task widget");
		}
	
		selectTasksPanel("Task All");
		performActionOnTask("Task All",incompleteTask_text,"Delete Task");
	}
	
	
	@Test(priority=10612)  
	public void AddingContact2Tasks() throws Exception{
		logInfo("inside AddingContact2Tasks() Test");
		
		bcm.go2ContactsPage();
		bcm.addBusinessContacts("Test2", "Contact2", "test2contact2@gmail.com");
		go2UserTasksPage();
		addTask("TodaysTask",todaysTask_text,"Test2 Contact2");
		
		selectTasksPanel("Task Today");
		boolean isTaskFoundInTodaysWidget = verifyTaskPresentInWidget("Task Today",todaysTask_text);
		System.out.println("isTaskFoundInTodaysWidget =" +isTaskFoundInTodaysWidget);
		if (isTaskFoundInTodaysWidget==false){
			logInfo(todaysTask_text + " task should present in Today's Task widget.");
			Assert.assertTrue(isTaskFoundInTodaysWidget, todaysTask_text + " task is not present in Today's Task widget.");
		} else {
			
		selectTasksPanel("Task All");
		viewContact4Task("Task All",todaysTask_text);
			
		go2UserTasksPage();
		selectTasksPanel("Task All");
		performActionOnTask("Task All",todaysTask_text,"Delete Task");
		
		}
		
	}
	
}
