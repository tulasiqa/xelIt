package vibe.admin.tests;


import org.testng.annotations.Test;
import vibe.widgets.tests.WidgetsMethods;
import common.Priority;
import common.TestBase;
import common.Locators;

@Priority(17)
public class AdminWidgets extends Locators {
	
	WidgetsMethods wm = new WidgetsMethods();
	TestBase tb = new TestBase();
	
	@Test(priority=1700)
	public void enableActionItemWidget() throws Exception{
		tb.logInfo("inside enableAboutWidget() Test");
		
		wm.go2WidgetsPage();
		wm.selectWidget("Action Items");	
	}
	
	@Test(priority=1701)
	public void enableCarouselAdsWidget() throws Exception{
		tb.logInfo("inside enableCarouselAdsWidget() Test");
		
		wm.go2WidgetsPage();
		wm.selectWidget("Carousel Ads");	
	}
	
	@Test(priority=1750)
	public void enableAllWidgets() throws Exception{
		tb.logInfo("inside enableAboutWidget() Test");
		
		wm.go2WidgetsPage();
		
		wm.selectWidget("About");
		wm.selectWidget("Action Items");	
		wm.selectWidget("Tabbed Information");
		wm.selectWidget("Calendar Types");
		wm.selectWidget("Community Stream");
		wm.selectWidget("Company News");
		wm.selectWidget("Company Social Networks");
		wm.selectWidget("Contact Groups");
		wm.selectWidget("Events");
		wm.selectWidget("Featured Blogs");
		wm.selectWidget("Featured Photos");
		wm.selectWidget("Featured Videos");
		wm.selectWidget("Following");
		wm.selectWidget("Goals");
		wm.selectWidget("My Blog");
		wm.selectWidget("My Photos");
		wm.selectWidget("My Videos");
		wm.selectWidget("My Websites");
		wm.selectWidget("Notifications");
		wm.selectWidget("Progress Report");
		wm.selectWidget("Quick Links Button View");
		wm.selectWidget("Quick Links List View");
		wm.selectWidget("Recent Activity");
		wm.selectWidget("Report");
		wm.selectWidget("Resource Library");
		wm.selectWidget("Search Contacts");
		wm.selectWidget("Search Events");
		wm.selectWidget("Tasks Add");
		wm.selectWidget("Tasks Completed");
		wm.selectWidget("Tasks Completed Incomplete");
		wm.selectWidget("Tasks Future");
		wm.selectWidget("Tasks Incomplete");
		wm.selectWidget("Tasks No Due Date");
		wm.selectWidget("Tasks Overdue");
		wm.selectWidget("Tasks Today");
		wm.selectWidget("Ticker");
		wm.selectWidget("Trending Photos");
		wm.selectWidget("Trending Videos");
		wm.selectWidget("Carousel Ads");
		wm.selectWidget("Featured Photos");
		wm.selectWidget("Add Contact");
		wm.selectWidget("Business Alerts");
		wm.selectWidget("CRM Alerts");
		wm.selectWidget("Community Stream");
		wm.selectWidget("Contact Groups");
	}

	@Test(priority=1790)
	public void disableAllWidgets() throws Exception{
		tb.logInfo("inside disableAllWidget() Test");
		
		wm.go2WidgetsPage();
		
		wm.unselectWidget("About");
		wm.unselectWidget("Action Items");	
		wm.unselectWidget("Tabbed Information");
		wm.unselectWidget("Calendar Types");
		wm.unselectWidget("Community Stream");
		wm.unselectWidget("Company News");
		wm.unselectWidget("Company Social Networks");
		wm.unselectWidget("Contact Groups");
		wm.unselectWidget("Events");
		wm.unselectWidget("Featured Blogs");
		wm.unselectWidget("Featured Photos");
		wm.unselectWidget("Featured Videos");
		wm.unselectWidget("Following");
		wm.unselectWidget("Goals");
		wm.unselectWidget("My Blog");
		wm.unselectWidget("My Photos");
		wm.unselectWidget("My Videos");
		wm.unselectWidget("My Websites");
		wm.unselectWidget("Notifications");
		wm.unselectWidget("Progress Report");
		wm.unselectWidget("Quick Links Button View");
		wm.unselectWidget("Quick Links List View");
		wm.unselectWidget("Recent Activity");
		wm.unselectWidget("Report");
		wm.unselectWidget("Resource Library");
		wm.unselectWidget("Search Contacts");
		wm.unselectWidget("Search Events");
		wm.unselectWidget("Tasks Add");
		wm.unselectWidget("Tasks Completed");
		wm.unselectWidget("Tasks Completed Incomplete");
		wm.unselectWidget("Tasks Future");
		wm.unselectWidget("Tasks Incomplete");
		wm.unselectWidget("Tasks No Due Date");
		wm.unselectWidget("Tasks Overdue");
		wm.unselectWidget("Tasks Today");
		wm.unselectWidget("Ticker");
		wm.unselectWidget("Trending Photos");
		wm.unselectWidget("Trending Videos");
		wm.unselectWidget("Carousel Ads");
		wm.unselectWidget("Featured Photos");
		wm.unselectWidget("Add Contact");
		wm.unselectWidget("Business Alerts");
		wm.unselectWidget("CRM Alerts");
		wm.unselectWidget("Community Stream");
		wm.unselectWidget("Contact Groups");
		
	}
}
