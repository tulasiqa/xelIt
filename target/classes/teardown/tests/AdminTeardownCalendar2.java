package vibe.teardown.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.calendar2.tests.CalendarMethods;
import vibe.contacts2.tests.BusinessContactsMethods;	
import common.Priority;
import common.TestBase;
import common.Locators;

@Priority(911)
public class AdminTeardownCalendar2 extends CalendarMethods {


	@Test(priority=91100)
	public void deleteCorpCalendarEvent() throws Exception{
		try{
			System.out.println("inside deleteCorpCalendarEvent() Test");
			go2CorporateCalendarPage();			
			selectCalendarView("Month");
			verifyEventisDisplayedMonthView(corpEventName_updtext);
			selectCalendarEventInMonthView(corpEventName_updtext);
			deleteEvent();
			boolean isDeleted = verifyEventNotDisplayedMonthView(corpEventName_updtext);
			if (isDeleted == true) {
				logInfo(newEventName_updtext + " calendar event could not be deleted.");
				Assert.assertFalse(isDeleted, newEventName_updtext + " calendar event could not be deleted.");
			}
			
		}
		catch(Exception ex){
			logInfo("Unable to delete corporate event.");
		}
	}	
	}
