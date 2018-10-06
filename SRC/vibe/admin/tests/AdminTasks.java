package vibe.admin.tests;

import org.testng.annotations.Test;

import vibe.widgets.tests.WidgetsMethods;
import common.Priority;
import common.TestBase;
import common.Locators;

@Priority(6)
public class AdminTasks extends Locators {
	
	TestBase tb = new TestBase();
	WidgetsMethods wm = new WidgetsMethods();
	
	@Test(priority=600)
	public void enableAllTasksWidgets() throws Exception{
		tb.logInfo("inside enableAllTasksWidgets() Test");
		
		wm.go2WidgetsPage();
		
		// enable the widgets in admin page.
		wm.selectWidget("Tasks Add");
		wm.selectWidget("Tasks Today");
		wm.selectWidget("Tasks No Due Date");
		wm.selectWidget("Tasks Future");
		wm.selectWidget("Tasks Completed");
		wm.selectWidget("Tasks Overdue");
		wm.selectWidget("Tasks Incomplete");
		wm.selectWidget("Tasks Due Date");
		}
	
	@Test(priority=601)
	public void disableAllTasksWidgets() throws Exception{
		tb.logInfo("inside disableAllTasksWidgets() Test");
		
		wm.go2WidgetsPage();
		
		wm.unselectWidget("Tasks Add");
		wm.unselectWidget("Tasks Today");
		wm.unselectWidget("Tasks No Due Date");
		wm.unselectWidget("Tasks Future");
		wm.unselectWidget("Tasks Completed");
		wm.unselectWidget("Tasks Overdue");
		wm.unselectWidget("Tasks Incomplete");
		wm.unselectWidget("Tasks Due Date");
		
	}
}
