package vibe.teardown.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import vibe.calendar2.tests.CalendarTests;
import common.Priority;
import common.TestBase;
import common.Locators;

@Priority(911)
public class AdminTeardownCalendar extends CalendarTests {


	@Test(priority=91100)
	public void deleteCorpCalendarEvent() throws Exception{
		try{
			System.out.println("inside deleteCorpCalendarEvent() Test");
			go2CalendarPage();
			deleteCalendarEvent("Quick Event",corpEventName_text); //corpEventName_updtext
			boolean isMatchFound = searchCalendarEvent(corpEventName_text);
			if(isMatchFound==true){
				logInfo(corpEventName_text + " could not be deleted.");
				Assert.assertTrue(isMatchFound, corpEventName_text + " could not be deleted.");
			}
			
		}
		catch(Exception ex){
			logInfo("Unable to delete corporate event.");
		}
		
	}	
	
}
