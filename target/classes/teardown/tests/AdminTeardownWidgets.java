package vibe.teardown.tests;

import org.testng.annotations.Test;

import vibe.widgets.tests.WidgetsMethods;
import common.Priority;
import common.TestBase;
import common.Locators;

@Priority(917)
public class AdminTeardownWidgets extends Locators {
	
	WidgetsMethods wm = new WidgetsMethods();
	TestBase tb = new TestBase();
	

	@Test(priority=91700)
	public void disableAllWidgets() throws Exception{
		tb.logInfo("inside disableAllWidget() Test");
		
		wm.go2WidgetsPage();
		
		wm.setWidgetStatus("About",false);
		wm.setWidgetStatus("Action Items",false);	
		wm.setWidgetStatus("Tabbed Information",false);
		wm.setWidgetStatus("Calendar Types",false);
		wm.setWidgetStatus("Community Stream",false);
		wm.setWidgetStatus("Company News",false);
		wm.setWidgetStatus("Company Social Networks",false);
		wm.setWidgetStatus("Contact Groups",false);
		wm.setWidgetStatus("Events",false);
		wm.setWidgetStatus("Featured Blogs",false);
		wm.setWidgetStatus("Featured Photos",false);
		wm.setWidgetStatus("Featured Videos",false);
		wm.setWidgetStatus("Following",false);
		wm.setWidgetStatus("Goals",false);
		wm.setWidgetStatus("My Blog",false);
		wm.setWidgetStatus("My Photos",false);
		wm.setWidgetStatus("My Videos",false);
		wm.setWidgetStatus("My Websites",false);
		wm.setWidgetStatus("Notifications",false);
		wm.setWidgetStatus("Progress Report",false);
		wm.setWidgetStatus("Quick Links Button View",false);
		wm.setWidgetStatus("Quick Links List View",false);
		wm.setWidgetStatus("Recent Activity",false);
		wm.setWidgetStatus("Report",false);
		wm.setWidgetStatus("Resource Library",false);
		wm.setWidgetStatus("Search Contacts",false);
		wm.setWidgetStatus("Search Events",false);
		wm.setWidgetStatus("Tasks Add",false);
		wm.setWidgetStatus("Tasks Completed",false);
		wm.setWidgetStatus("Tasks Completed Incomplete",false);
		wm.setWidgetStatus("Tasks Future",false);
		wm.setWidgetStatus("Tasks Incomplete",false);
		wm.setWidgetStatus("Tasks No Due Date",false);
		wm.setWidgetStatus("Tasks Overdue",false);
		wm.setWidgetStatus("Tasks Today",false);
		wm.setWidgetStatus("Ticker",false);
		wm.setWidgetStatus("Trending Photos",false);
		wm.setWidgetStatus("Trending Videos",false);
		wm.setWidgetStatus("Carousel Ads",false);
		wm.setWidgetStatus("Featured Photos",false);
		wm.setWidgetStatus("Add Contact",false);
		wm.setWidgetStatus("Business Alerts",false);
		wm.setWidgetStatus("CRM Alerts",false);
		wm.setWidgetStatus("Community Stream",false);
		wm.setWidgetStatus("Contact Groups",false);
		
	}
}
