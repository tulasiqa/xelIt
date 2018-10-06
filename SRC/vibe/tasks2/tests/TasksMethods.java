package vibe.tasks2.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import common.EnvProperties;
import common.TestBase;
import vibe.reports.tests.ReportsMethods;
import vibe.tasks2.tests.TasksMethods;
import vibe.widgets.tests.WidgetsMethods;

public class TasksMethods extends TestBase {
	
	WidgetsMethods widgets= new WidgetsMethods();
	ReportsMethods rep = new ReportsMethods();
	EnvProperties prop = new EnvProperties();
	String widgName;
	WebElement to;
	
	
		
	// Goto Tasks page
	
	public void go2UserTasksPage() {
		logInfo("inside go2UserTasksPage() method");
		driver().navigate().to(appUrl + "pyr_core/user_tasks");
	}
	
	// Select any task panel in task page.  OK
	
	public void selectTasksPanel(String taskPanel) throws Exception{
			logInfo("inside selectTasksPanel() method");
			
			String url = driver().getCurrentUrl();
			if(url.contains("monat") && taskPanel.equalsIgnoreCase("Task Today")) {
				taskPanel = "Today's Tasks";
			} else if(url.contains("monat") && taskPanel.equalsIgnoreCase("Tasks Completed")) {
				taskPanel = "Completed Tasks";
			}
			
			waitOnElement("xpath","//*[@class='tasks-list-widget']/div[1]/ul");
			WebElement panel = driver().findElement(By.xpath("//*[@class='tasks-list-widget']/div[1]/ul"));
			List allTasks = panel.findElements(By.tagName("li"));
			if(allTasks.size() > 0){
				for(int i=1; i<= allTasks.size(); i++){
					String before_taskName = "//*[@class='tasks-list-widget']/div[1]/ul/li[";
					String after_taskName = "]/a";
					WebElement taskName = driver().findElement(By.xpath(before_taskName+i+after_taskName));
					String actTask = taskName.getText().trim();
					if(actTask.equalsIgnoreCase(taskPanel)){
						logInfo(taskPanel + " task panel match found");
						taskName.click();
						Thread.sleep(4000);
						break;
					}
				}
				
			} else {
				logInfo(taskPanel + " Task not found in Tasks list to click");
			}
		}
		
	
		// Add a new task from AllTasks Widget.  OK
		
			public void addTask(String taskType, String taskName) throws Exception{
				logInfo("inside addTask() method");
				
				waitOnElement("xpath","//a[contains(text(),'Add Task')]");
				clickOnElement("xpath","//a[contains(text(),'Add Task')]");
				Thread.sleep(4000);
				
				waitOnElement("cssSelector", taskDescription);
				inputText("cssSelector", taskDescription, taskName);
				
				
				switch(taskType) {
				case "TodaysTask":
					String currentdt = TestBase.getSystemDate();
					inputTextClear("cssSelector","#pyr_core_user_task_due_date");
					inputText("cssSelector","#pyr_core_user_task_due_date", currentdt);
					break;
				case "FutureTask":
					String futuredt = TestBase.getDate(+1, "MM/dd/yyyy");
					inputTextClear("xpath","//*[@id='pyr_core_user_task_due_date']");
					inputText("xpath", "//*[@id='pyr_core_user_task_due_date']" , futuredt);
					break;
				case "OverdueTask":
					String pastdt = TestBase.getDate(-1, "MM/dd/yyyy");
					inputTextClear("xpath","//*[@id='pyr_core_user_task_due_date']");
					inputText("xpath", "//*[@id='pyr_core_user_task_due_date']" , pastdt);
					break;
				case "NoDueDateTask":
					inputTextClear("xpath","//*[@id='pyr_core_user_task_due_date']");	
					break;
				}
				
				
				composeText("xpath","//iframe[@title='Rich Text Editor, pyr_core_user_task_description']", taskName + " description.");
				selectFromDropdown("cssSelector",priority,"byVisibleText","High");
				
				clickOnButton("xpath","//button[contains(text(),'Save')]");
				Thread.sleep(5000);
			}
			
			
			public void addTask(String taskType, String taskName, String contactName) throws Exception{
				logInfo("inside addTask() method");
				
				waitOnElement("xpath","//a[contains(text(),'Add Task')]");
				clickOnElement("xpath","//a[contains(text(),'Add Task')]");
				Thread.sleep(4000);
				
				waitOnElement("cssSelector", taskDescription);
				inputText("cssSelector", taskDescription, taskName);
				
				inputText("xpath","//*[@id='pyr_core_user_task_actionable_item']",contactName);
				Thread.sleep(4000);
				
				WebElement ol = driver().findElement(By.xpath("//ol[@id='mp_pyr_core_user_task[actionable_item]_list']"));
				List allOls = ol.findElements(By.tagName("li"));
				String before_li = "//ol[@id='mp_pyr_core_user_task[actionable_item]_list']/li[";
				String after_li = "]/span[2]";
				
				if(allOls.size()>0) {
					for(int i=1;i<=allOls.size();i++) {
					WebElement contact = driver().findElement(By.xpath(before_li+i+after_li));
					String actContact = contact.getText().trim();
					if(actContact.equalsIgnoreCase(contactName)) {
						contact.click();
						Thread.sleep(4000);
						logInfo("Selected "+contactName);
						break;
					}
				  }
				}
				
				switch(taskType) {
				case "TodaysTask":
					String currentdt = TestBase.getSystemDate();
					inputTextClear("cssSelector","#pyr_core_user_task_due_date");
					inputText("cssSelector","#pyr_core_user_task_due_date", currentdt);
					break;
				case "FutureTask":
					String futuredt = TestBase.getDate(+1, "MM/dd/yyyy");
					inputTextClear("xpath","//*[@id='pyr_core_user_task_due_date']");
					inputText("xpath", "//*[@id='pyr_core_user_task_due_date']" , futuredt);
					break;
				case "OverdueTask":
					String pastdt = TestBase.getDate(-1, "MM/dd/yyyy");
					inputTextClear("xpath","//*[@id='pyr_core_user_task_due_date']");
					inputText("xpath", "//*[@id='pyr_core_user_task_due_date']" , pastdt);
					break;
				case "NoDueDateTask":
					inputTextClear("xpath","//*[@id='pyr_core_user_task_due_date']");	
					break;
				}
				
				
				composeText("xpath","//iframe[@title='Rich Text Editor, pyr_core_user_task_description']", taskName + " description.");
				selectFromDropdown("cssSelector",priority,"byVisibleText","High");
				
				clickOnButton("xpath","//button[contains(text(),'Save')]");
				Thread.sleep(5000);
			}
			
		// Search a task inside the widget  OK
			
		public boolean searchTaskPresentinWidget(String taskName, String taskPanel) throws  Exception{
			logInfo("inside searchTaskPresentinWidget() method.");
				
			boolean isTaskFound = false;
			String panel = null;
			
			// selectTasksPanel(taskPanel);
			
			switch(taskPanel){
			
			case "Task All":
				panel = "//*[@id='tab-all-tasks']/table/tbody";
				break;
			case "Task Today":
				panel = "//*[@id='tab-due-today']/table/tbody";
				break;
			case "Tasks Completed":
				panel = "//*[@id='tab-completed-tasks']/table/tbody";
				break;
			
			}
			
			waitOnElement("xpath","//*[@id='tasks_all_search_all']");
			inputTextClear("xpath","//*[@id='tasks_all_search_all']");
			inputText("xpath","//*[@id='tasks_all_search_all']",taskName+" ");
			Thread.sleep(10000);
			
			// verify if there are no tasks
			
			boolean isNoTaskFound = false;
			waitOnElement("xpath",panel);
			WebElement t = driver().findElement(By.xpath(panel));
			List Zrow = t.findElements(By.tagName("tr"));
			if(Zrow.size()==1) {
				WebElement tskmsg = driver().findElement(By.xpath(panel+"/tr[1]/td[1]"));
				String msg = tskmsg.getText().trim();
				if(msg.equalsIgnoreCase("No Tasks Found")) {
					logInfo("Zero tasks found in " +taskPanel);
					isNoTaskFound=true;
				}
			}
			
			// verify if there are more than one tasks
			
			String before_task = panel + "/tr[";
			String after_task = "]/td[3]/a";
		
			
			if(isNoTaskFound==false) {
				WebElement table = driver().findElement(By.xpath(panel));
				List allRows = table.findElements(By.tagName("tr"));
				if(allRows.size()>1) {
						isTaskFound=true;
						for(int i=2;i<=allRows.size();i++) {
							WebElement task = driver().findElement(By.xpath(before_task+i+after_task));
							String actTask = task.getText().trim();
							if(actTask.equalsIgnoreCase(taskName)) {
								logInfo(taskName + " match found in task pane.");
								isTaskFound = true;
								break;
							}
						}
					}
				}
			
			if(isTaskFound==false && isNoTaskFound==false){
				System.out.println(taskName +  " task not found during search.");
				logInfo(taskName + " task not found in during search.");
			}
				
			return isTaskFound;
		}
	
		
		// Verify if the task present in task panel. OK
		
		public boolean verifyTaskPresentInWidget(String taskPanel,String taskName) throws  Exception{
			logInfo("inside verifyTaskPresentInWidget() method.");
			
			//selectTasksPanel(taskPanel);
			
			boolean isTaskFound = false;
			String path = null;
						
			switch(taskPanel) {
			case "Task All":
				path="//div[@id='tab-all-tasks']/table/tbody";
				break;
			case "Task Today" :   
				path="//div[@id='tab-due-today']/table/tbody";
				break;
			case "Tasks Completed":
				path="//div[@id='tab-completed-tasks']/table/tbody";
				break;
			}
			
			

			// verify if there are no tasks
			
			boolean isNoTaskFound = false;
			waitOnElement("xpath",path);
			WebElement t = driver().findElement(By.xpath(path));
			List Zrow = t.findElements(By.tagName("tr"));
			if(Zrow.size()==1) {
				WebElement tskmsg = driver().findElement(By.xpath(path+"/tr[1]/td[1]"));
				String msg = tskmsg.getText().trim();
				if(msg.equalsIgnoreCase("No Tasks Found")) {
					logInfo("Zero tasks found in " +taskPanel);
					isNoTaskFound=true;
				}
			}
			
			// verify if there are more than one tasks
			
			String before_task = path + "/tr[";
			String after_task = "]/td[3]/a";
			
			if(isNoTaskFound==false) {
				WebElement table = driver().findElement(By.xpath(path));
				List allRows = table.findElements(By.tagName("tr"));
				if(allRows.size()>1) {
						for(int i=2;i<=allRows.size();i++) {
							WebElement task = driver().findElement(By.xpath(before_task+i+after_task));
							String actTask = task.getText().trim();
							if(actTask.equalsIgnoreCase(taskName)) {
								logInfo(taskName + " match found in task pane.");
								isTaskFound = true;
								break;
							}
						}
					}
			}
			
			if(isTaskFound==false){
				System.out.println(taskName +  " task not found.");
				logInfo(taskName + " task not found.");
			}
			return isTaskFound;
			
		}
	
	
	// Select and Perform action on the task. OK
		
		
	public void performActionOnTask(String taskPanel, String taskName, String action) throws  Exception{
		logInfo("inside performActionOnTask() method.");
		
		//selectTasksPanel(taskPanel);
		
		boolean isTaskFound = false;
		String path = null;
					
		switch(taskPanel) {
		case "Task All":
			path="//div[@id='tab-all-tasks']/table/tbody";
			break;
		case "Task Today":
			path="//div[@id='tab-due-today']/table/tbody";
			break;
		case "Tasks Completed":
			path="//div[@id='tab-completed-tasks']/table/tbody";
			break;
		}
		
		String before_task = path + "/tr[";
		String after_task = "]/td[3]/a";
		
		waitOnElement("xpath",path);
		WebElement table = driver().findElement(By.xpath(path));
		List allRows = table.findElements(By.tagName("tr"));
		if(allRows.size()>1) {
				for(int i=2;i<=allRows.size();i++) {
					WebElement task = driver().findElement(By.xpath(before_task+i+after_task));
					String actTask = task.getText().trim();
					if(actTask.equalsIgnoreCase(taskName)) {
						logInfo(taskName + " match found in task pane.");
						isTaskFound = true;
						task.click();
						Thread.sleep(4000);
						switch(action) {
						case "Mark As Complete":
							waitOnElement("xpath","//label[contains(text(),'Mark As Complete')]");
							clickOnElement("xpath","//label[contains(text(),'Mark As Complete')]");
							logInfo("clicked on Mark As Complete button");
							Thread.sleep(4000);
							break;
						case "Mark As Incomplete":
							waitOnElement("xpath","//label[contains(text(),'Mark As Incomplete')]");
							clickOnElement("xpath","//label[contains(text(),'Mark As Incomplete')]");
							logInfo("clicked on Mark As Incomplete button");
							Thread.sleep(4000);
							break;
						case "Edit":
							waitOnElement("xpath","//a[contains(text(),'Edit')]");
							clickOnElement("xpath","//a[contains(text(),'Edit')]");
							logInfo("clicked on Edit Task button");
							Thread.sleep(4000);
							break;
						case "Delete Task":
							waitOnElement("xpath","//a[contains(text(),'Delete Task')]");
							clickOnElement("xpath","//a[contains(text(),'Delete Task')]");
							Thread.sleep(3000);
							confirmOK();
							Thread.sleep(3000);
							logInfo("clicked on Delete Task button");
							break;	
						
						}
						break;
					}
				}
				
			}
		
	}
		
		
		
		public void autoCompleteTask(String taskPanel, String taskName) throws Exception {
			logInfo("inside autoCompleteTask() method.");
			
			boolean isTaskFound = false;
			String panel = null;
			
			// selectTasksPanel(taskPanel);
			
			switch(taskPanel){
			
			case "Task All":
				panel = "//*[@id='tab-all-tasks']/table/tbody";
				break;
			case "Task Today":
				panel = "//*[@id='tab-due-today']/table/tbody";
				break;
			case "Tasks Completed":
				panel = "//*[@id='tab-completed-tasks']/table/tbody";
				break;
			}
			
			// verify if there are no tasks
			
				boolean isNoTaskFound = false;
				waitOnElement("xpath",panel);
				WebElement t = driver().findElement(By.xpath(panel));
				List Zrow = t.findElements(By.tagName("tr"));
					if(Zrow.size()==1) {
						WebElement tskmsg = driver().findElement(By.xpath(panel+"/tr[1]/td[1]"));
						String msg = tskmsg.getText().trim();
						if(msg.equalsIgnoreCase("No Tasks Found")) {
							logInfo("Zero tasks found in " +taskPanel);
							isNoTaskFound=true;
						}
					}
						
				// verify if there are more than one tasks
						
				String before_task = panel + "/tr[";
				String after_task = "]/td[3]/a";
				String after_autocomplete = "]/td[2]/input";
				
				if(isNoTaskFound==false) {
					WebElement table = driver().findElement(By.xpath(panel));
					List allRows = table.findElements(By.tagName("tr"));
					if(allRows.size()>1) {
							isTaskFound=true;
							for(int i=2;i<=allRows.size();i++) {
								WebElement task = driver().findElement(By.xpath(before_task+i+after_task));
								String actTask = task.getText().trim();
								if(actTask.equalsIgnoreCase(taskName)) {
									logInfo(taskName + " match found in task pane.");
									isTaskFound = true;
									WebElement autocomplete = driver().findElement(By.xpath(before_task+i+after_autocomplete));
									autocomplete.click();
									Thread.sleep(3000);
									break;
								}
							}
						}
					}
			
		}
	
			 

		public void configureTasksAllWidget(String[] taskNames) throws  Exception{
			logInfo("inside configureTasksAllWidget() method.");
			
			waitOnElement("xpath","//div[@data-widget-path='widgets/tasks_all/main']/div[1]/span[6]");
			clickOnElement("xpath","//div[@data-widget-path='widgets/tasks_all/main']/div[1]/span[6]");
			
			waitOnElement("xpath","//*[@id='widget_config_modal']/div/div[1]/div[2]/form/div");
			WebElement tabs = driver().findElement(By.xpath("//*[@id='widget_config_modal']/div/div[1]/div[2]/form/div"));
			
			List allCheckboxes = tabs.findElements(By.tagName("span"));
			if(allCheckboxes.size() >0){
				
				String before_input = "//*[@id='widget_config_modal']/div/div[1]/div[2]/form/div/span[";
				String after_input = "]/label/input";
				
				String before_taskName = "//*[@id='widget_config_modal']/div/div[1]/div[2]/form/div/span[";
				String after_taskName = "]/label";
				
				for(int i=1;i<=allCheckboxes.size();i++){
				  for(int j=0;j<=taskNames.length-1;j++){
					WebElement etaskName = driver().findElement(By.xpath(before_taskName+i+after_taskName));
					String actTask = etaskName.getText().trim();
					 WebElement input = driver().findElement(By.xpath(before_input+i+after_input));
					 if(actTask.equalsIgnoreCase(taskNames[j])){
						 System.out.println(i + " isChecked() = " + input.getAttribute("checked"));
						 input.click();
					 }
				  }
			  }
			}
			
			clickOnElement("xpath","//button[contains(text(),'Save')]");
		}

	
		public void viewContact4Task(String taskPanel, String taskName) throws  Exception{
			logInfo("inside viewContact4Task() method.");
			
			// selectTasksPanel(taskPanel);
			
			boolean isTaskFound = false;
			String path = null;
						
			switch(taskPanel) {
			case "Task All":
				path="//div[@id='tab-all-tasks']/table/tbody";
				break;
			case "Task Today":
				path="//div[@id='tab-due-today']/table/tbody";
				break;
			case "Tasks Completed":
				path="//div[@id='tab-completed-tasks']/table/tbody";
				break;
			}
			
			String before_task = path + "/tr[";
			String after_task = "]/td[3]/a";
			String after_viewcontact = "]/td[4]/a";
			
			waitOnElement("xpath",path);
			WebElement table = driver().findElement(By.xpath(path));
			List allRows = table.findElements(By.tagName("tr"));
			if(allRows.size()>1) {
					for(int i=2;i<=allRows.size();i++) {
						WebElement task = driver().findElement(By.xpath(before_task+i+after_task));
						String actTask = task.getText().trim();
						if(actTask.equalsIgnoreCase(taskName)) {
							logInfo(taskName + " match found in task pane.");
							isTaskFound = true;
							WebElement e = driver().findElement(By.xpath(before_task+i+after_viewcontact));
							String contact_name = e.getText().trim();
							System.out.println("contact_name =" +contact_name);
							if(contact_name.equalsIgnoreCase("View Contact")) {
								e.click();
								Thread.sleep(10000);
								validateTextOnElement("xpath","//*[@id='contact-summary']/div[@class='contact-info']/div[@class='contact-display-name']","Test2 Contact2");
								clickOnElement("linkText","Delete");
								confirmOK();
								Thread.sleep(5000);
								break;
							}
							
						}
						break;
					}
				}
					
		}
			
		
		
		public void unconfigureTasksAllWidget(String[] taskNames) throws  Exception{
			logInfo("inside configureTasksAllWidget() method.");
			
			waitOnElement("xpath","//div[@data-widget-path='widgets/tasks_all/main']/div[1]/span[6]");
			clickOnElement("xpath","//div[@data-widget-path='widgets/tasks_all/main']/div[1]/span[6]");
			
			waitOnElement("xpath","//*[@id='widget_config_modal']/div/div[1]/div[2]/form/div");
			WebElement tabs = driver().findElement(By.xpath("//*[@id='widget_config_modal']/div/div[1]/div[2]/form/div"));
			
			List allCheckboxes = tabs.findElements(By.tagName("span"));
			if(allCheckboxes.size() >0){
				
				String before_input = "//*[@id='widget_config_modal']/div/div[1]/div[2]/form/div/span[";
				String after_input = "]/label/input";
				
				String before_taskName = "//*[@id='widget_config_modal']/div/div[1]/div[2]/form/div/span[";
				String after_taskName = "]/label";
				
				for(int i=1;i<=allCheckboxes.size();i++){
				  for(int j=0;j<=taskNames.length-1;j++){
					WebElement etaskName = driver().findElement(By.xpath(before_taskName+i+after_taskName));
					String actTask = etaskName.getText().trim();
					if(actTask.equalsIgnoreCase(taskNames[j]) ){
					 WebElement input = driver().findElement(By.xpath(before_input+i+after_input));
					 System.out.println("i = " + input.getAttribute("checked"));
						input.click();
					}
				}
			  }
			}
			
			clickOnElement("xpath","//button[contains(text(),'Save')]");
		}

	
}
