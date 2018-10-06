package vibe.admin.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.SocialNetWorksMethods;
import common.EnvProperties;
import vibe.calendar2.tests.CalendarMethods;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(11)
public class AdminCalendar2 extends CalendarMethods {
	
	MyProfileMethods profile = new MyProfileMethods();
	ShoppingMethods shop= new ShoppingMethods();
	InboxMethods inbox = new InboxMethods();
	CommunityMethods comm = new CommunityMethods();
	BusinessContactsMethods con = new BusinessContactsMethods();
	NewsMethods nm = new NewsMethods();
	EnvProperties prop = new EnvProperties();
	SocialNetWorksMethods sm = new SocialNetWorksMethods();
	/*String corpEventName_text = "Corp_Event_682_Updated";*/
	
	@Test(priority=1100)
	public void createCorpCalendarEvent() throws Exception{
		logInfo("inside corpCalendarEvent() Test");
		go2CorporateCalendarPage();
		addCorpCalendarEvent(corpEventName_text, corpEventStartdate_text, corpEventEnddate_text,calendarInvitee_text);
		go2CorporateCalendarPage();
		verifyEventisDisplayedMonthView(corpEventName_text);
	
}

		@Test(priority=1111)
		public void searchCorpCalendarEvent() throws Exception{
			logInfo("inside searchCorpCalendarEvent() Test");
			go2CorporateCalendarPage();
			boolean isEventFound = searchCalendarEvent(corpEventName_text);
			if(isEventFound==false){
				logInfo(corpEventName_text +"is not found in the search");   
				Assert.assertTrue(isEventFound, corpEventName_text +"is not found in the search");
				}
		}

	
		@Test(priority=1112)
		public void updateCorpCalendarEvent() throws Exception{
			logInfo("inside updateCorpCalendarEvent() Test");
			go2CorporateCalendarPage();
			verifyEventisDisplayedMonthView(corpEventName_text);
			selectCalendarEventInMonthView(corpEventName_text);
			viewEvent(corpEventName_text);
			updateCalendarEvent(corpEventName_updtext);
			go2CorporateCalendarPage();
			verifyEventisDisplayedMonthView(corpEventName_updtext);
		
		}
		
		
		
	
	 	@Test(priority=1122)
		public void createATaskFromCorporateCalendar() throws Exception{
			logInfo("inside createATaskFromCorporateCalendar() Test");
			go2CorporateCalendarPage();
			createTaskFromCalendar(calendarTask_text);
			go2CorporateCalendarPage();
			boolean isTaskInFirstRow = verifyTaskPresentinCalendarDay(calendarTask_text);
		}
		
		@Test(priority=1123)
		public void markTaskAsCompleteFromCorporateCalendar() throws Exception{
			logInfo("inside markTaskAsCompleteFromCorporateCalendar() Test");
			go2CorporateCalendarPage();
			boolean isTaskFound = verifyTaskPresentinCalendarDay(calendarTask_text);
			if(isTaskFound){
				go2CorporateCalendarPage();
				selectTaskPresentInCalendarDayView(calendarTask_text);
				markTaskAsComplete(calendarTask_text);
			}
			else{
				Assert.assertTrue(isTaskFound, "Could not find task in calendar");
				}
		}
		
		@Test(priority=1124)
		public void markTaskAsInCompleteFromCorporateCalendar() throws Exception{
			logInfo("inside markTaskAsInCompleteFromCorporateCalendar() Test");
			go2CorporateCalendarPage();
			boolean isTaskFound = verifyTaskPresentinCalendarDay(calendarTask_text);
			if(isTaskFound){
				go2CorporateCalendarPage();
				selectTaskPresentInCalendarDayView(calendarTask_text);
				markTaskAsInComplete(calendarTask_text);
			}
			else{
				Assert.assertTrue(isTaskFound, "Could not find task in calendar");
				}
		}
		
		
		@Test(priority=1125)
		public void editATaskFromCorporateCalendar() throws Exception{
			logInfo("inside editATaskFromCorporateCalendar() Test");
			go2CorporateCalendarPage();
			boolean isTaskFound = verifyTaskPresentinCalendarDay(calendarTask_text);
			if(isTaskFound){
				go2CorporateCalendarPage();
				selectTaskPresentInCalendarDayView(calendarTask_text);
				waitOnSpinner();
				editTaskFromCalendar(calendarTaskUpdated_text);
				go2CorporateCalendarPage();
				boolean isTaskUpdated = verifyTaskPresentinCalendarDay(calendarTaskUpdated_text);
			}
			else{
				Assert.assertTrue(isTaskFound, "Could not find task in calendar");
				}
		}
		
		
		@Test(priority=1126)
		public void deleteATaskFromCorporateCalendar() throws Exception{
			logInfo("inside deleteATaskFromCorporateCalendar() Test");
			go2CorporateCalendarPage();
			boolean isTaskFound = verifyTaskPresentinCalendarDay(calendarTaskUpdated_text);
			if(isTaskFound){
				go2CorporateCalendarPage();
				selectTaskPresentInCalendarDayView(calendarTaskUpdated_text);
				waitOnSpinner();
				deleteTaskFromCalendar(calendarTaskUpdated_text);
				
			}
			else{
				Assert.assertTrue(isTaskFound, "Could not find task in calendar");
				}
		}
		
		
   }

