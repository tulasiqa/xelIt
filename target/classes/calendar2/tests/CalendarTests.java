package vibe.calendar2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.users.tests.UsersMethods;

@Priority(111)
public class CalendarTests extends CalendarMethods {

	BusinessContactsMethods bm = new BusinessContactsMethods();
	 UsersMethods um = new UsersMethods(); 
	@Test(priority = 11100)
	public void verifyCorpCalendarEvent() throws Exception {
		logInfo("Inside verifyCorpCalendarEvent() Test");
		go2CalendarPage();
		verifyEventisDisplayedMonthView(corpEventName_updtext);

	}

	@Test(priority = 11101)
	public void VerifyDismissedCorporateEvent() throws Exception {
		logInfo("inside verifyDismissedCorporateEvent() Test");
		go2CalendarPage();
		verifyEventisDisplayedMonthView(corpEventName_updtext);
		selectCalendarEventInMonthView(corpEventName_updtext);
		dismissCorporateEvent(corpEventName_updtext);

	}


	@Test(priority = 11120)
	public void CreateNewCalendarEventFromLink() throws Exception {
		logInfo("inside createNewCalendarEvent() Test");
		go2CalendarPage();
		addNewCalendarEvent(newEventName_text, newEventStartdate_text, newEventEnddate_text,prop.getLocatorForEnvironment(appUrl, "calendarType"), false,"sdsa");
		go2CalendarPage();
		verifyEventisDisplayedMonthView(newEventName_text);
	}

	@Test(priority = 11121)
	public void SearchCalendarEvent() throws Exception {
		logInfo("inside searchCalendarEvent() Test");
		go2HomePage();
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(newEventName_text);
		if (isMatchFound == false) {
			Assert.assertTrue(isMatchFound, newEventName_text + " calendar event match not found.");
		}
	}

	@Test(priority = 11122)
	public void UpdateCalendarEvent() throws Exception {
		logInfo("inside updateCalendarEvent() Test");
		go2CalendarPage();
		selectCalendarEventInMonthView(newEventName_text);
		viewEvent(newEventName_text);
		updateCalendarEvent(newEventName_updtext);
		go2CalendarPage();
		verifyEventisDisplayedMonthView(newEventName_updtext);

	}

	@Test(priority = 11123)
	public void DeleteCalendarEvent() throws Exception {
		logInfo("inside deleteCalendarEvent() Test");
		go2CalendarPage();
		selectCalendarView("Month");
		verifyEventisDisplayedMonthView(newEventName_updtext);
		selectCalendarEventInMonthView(newEventName_updtext);
		deleteEvent();
		boolean isDeleted = verifyEventNotDisplayedMonthView(newEventName_updtext);
		if (isDeleted == true) {
			logInfo(newEventName_updtext + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, newEventName_updtext + " calendar event could not be deleted.");
		}

	}

	@Test(priority = 11124)
	public void CreateAllDayCalendarEvent() throws Exception {
		logInfo("inside createAllDayCalendarEvent() Test");
		go2CalendarPage();
		addNewCalendarEvent(allDayEventName_text, allDayEventName_text, allDayEventEnddate_text,prop.getLocatorForEnvironment(appUrl, "calendarType"), true,"sdsa");
		verifyEventisDisplayedMonthView(allDayEventName_text);
	}

	@Test(priority = 11125)
	public void SearchAllDayCalendarEvent() throws Exception {
		logInfo("inside searchAllDayCalendarEvent() Test");
		go2HomePage();
		go2CalendarPage();
		searchCalendarEvent(allDayEventName_text);
		boolean isMatchFound = searchCalendarEvent(allDayEventName_text);
		if (isMatchFound == false) {
			logInfo(allDayEventName_text + " Allday calendar event match not found.");
			Assert.assertTrue(isMatchFound, allDayEventName_text + " Allday calendar event match not found.");
		} else {
			logInfo(allDayEventName_text + " Allday calendar event match found.");

		}
	}

	@Test(priority = 11126)
	public void UpdateAllDayCalendarEvent() throws Exception {
		logInfo("inside updateAllDayCalendarEvent() Test");
		go2CalendarPage();
		selectCalendarEventInMonthView(allDayEventName_text);
		viewEvent(allDayEventName_text);
		updateCalendarEvent(allDayEventName_updtext);
		go2CalendarPage();
		verifyEventisDisplayedMonthView(allDayEventName_updtext);
	}

	@Test(priority = 11127)
	public void DeleteAllDayCalendarEvent() throws Exception {
		logInfo("inside deleteAllDayCalendarEvent() Test");
		go2CalendarPage();
		selectCalendarView("Month");
		verifyEventisDisplayedMonthView(allDayEventName_updtext);
		selectCalendarEventInMonthView(allDayEventName_updtext);
		deleteEvent();
		boolean isDeleted = verifyEventNotDisplayedMonthView(allDayEventName_updtext);
		if (isDeleted == true) {
			logInfo(allDayEventName_updtext + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, allDayEventName_updtext + " calendar event could not be deleted.");
		}
	}

	@Test(priority = 11128)
	public void CreateRepeatCalendarEvent() throws Exception {
		logInfo("inside createRepeatCalendarEvent() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEventName_text, repeatEventStartdate_text, repeatEventEnddate_text, "Daily",
				repeatEventEvery_text, "No End Date", 3);
		String expectedAccntsts = getRepeatDate();
		eventCreateBtn();
		go2CalendarPage();
		selectCalendarView("List");
		selectCalendarListViewEvent(repeatEventName_text);
		repeatEventValidation("Daily", expectedAccntsts);
	}

	@Test(priority = 11129)
	public void SearchRepeatCalendarEvent() throws Exception {
		logInfo("inside searchRepeatCalendarEvent() Test");
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(repeatEventName_text);
		if (isMatchFound == false) {
			logInfo(repeatEventName_text + "Repeat calendar event match not found.");
			Assert.assertTrue(isMatchFound, repeatEventName_text + "Repeat calendar event match not found.");
		} else {
			logInfo(repeatEventName_text + "Repeat calendar event match found.");

		}
	}

	@Test(priority = 11130)
	public void UpdateRepeatCalendarEvent() throws Exception {
		logInfo("inside updateRepeatCalendarEvent() Test");
		go2CalendarPage();
		selectCalendarEventInMonthView(repeatEventName_text);
		viewEvent(repeatEventName_text);
		updateCalendarEvent(repeatEventName_updtext);
		go2CalendarPage();
		verifyEventisDisplayedMonthView(repeatEventName_updtext);
	}

	@Test(priority = 11131)
	public void DeleteRepeatCalendarEventSingleInstance() throws Exception {
		logInfo("inside All events in this series() Test");
		go2CalendarPage();
		selectCalendarEventInMonthView(repeatEventName_updtext);
		deleteRepeatEvent("Delete only this instance");

	}

	@Test(priority = 11132)
	public void DeleteRepeatCalendarEventAllEventsInSeries() throws Exception {
		logInfo("inside deleteRepeatCalendarEventSingleInstance() Test");
		go2CalendarPage();
		selectCalendarEventInMonthView(repeatEventName_updtext);
		deleteRepeatEvent("All events in this series");
		boolean isDeleted = verifyEventNotDisplayedMonthView(repeatEventName_updtext);
		if (isDeleted == true) {
			logInfo(repeatEventName_updtext + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, repeatEventName_updtext + " calendar event could not be deleted.");
		}

	}

	@Test(priority = 11133)
	public void CreateQuickCalendarEvent() throws Exception {
		logInfo("inside quickCalendarEvent() Test");
		go2CalendarPage();
		addQuickCalendarEvent(quickEventName_text, quickEventStartdate_text, quickEventEnddate_text);
		go2CalendarPage();
		verifyEventisDisplayedMonthView(quickEventName_text);

	}

	@Test(priority = 11134)
	public void SearchQuickCalendarEvent() throws Exception {
		logInfo("inside searchQuickCalendarEvent() Test");
		go2HomePage();
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(quickEventName_text);
		if (isMatchFound == false) {
			logInfo(quickEventName_text + " Quick calendar event match not found.");
			Assert.assertTrue(isMatchFound, quickEventName_text + " Quick calendar event match not found.");
		} else {
			logInfo(quickEventName_text + " Quick calendar event match found.");

		}
	}

	@Test(priority = 11135)
	public void UpdateQuickCalendarEvent() throws Exception {
		logInfo("inside updateQuickCalendarEvent() Test");
		go2CalendarPage();
		selectCalendarEventInMonthView(quickEventName_text);
		viewEvent(quickEventName_text);
		updateCalendarEvent(quickEventName_updtext);
		go2CalendarPage();
		verifyEventisDisplayedMonthView(quickEventName_updtext);

	}

	@Test(priority = 11136)
	public void DeleteQuickCalendarEvent() throws Exception {
		logInfo("inside deleteQuickCalendarEvent() Test");
		go2CalendarPage();
		selectCalendarEventInMonthView(quickEventName_updtext);
		deleteEvent();
		boolean isDeleted = verifyEventNotDisplayedMonthView(quickEventName_updtext);
		if (isDeleted == true) {
			logInfo(quickEventName_updtext + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, quickEventName_updtext + " calendar event could not be deleted.");
		}
	}

	@Test(priority = 11137)
	public void CreateATaskFromCalendar() throws Exception {
		logInfo("inside createATaskFromCalendar() Test");
		go2CalendarPage();
		createTaskFromCalendar(calendarTask_text);
		verifyTaskPresentinCalendarDay(calendarTask_text);
	}

	@Test(priority = 11138)
	public void MarkTaskAsCompleteFromCalendar() throws Exception {
		logInfo("inside markTaskAsCompleteFromCalendar() Test");
		go2CalendarPage();
		verifyTaskPresentinCalendarDay(calendarTask_text);
		selectTaskPresentInCalendarDayView(calendarTask_text);
		markTaskAsComplete(calendarTask_text);

	}

	@Test(priority = 11139)
	public void MarkTaskAsInCompleteFromCalendar() throws Exception {
		logInfo("inside markTaskAsInCompleteFromCalendar() Test");
		go2CalendarPage();
		verifyTaskPresentinCalendarDay(calendarTask_text);
		selectTaskPresentInCalendarDayView(calendarTask_text);
		markTaskAsInComplete(calendarTask_text);
	}

	@Test(priority = 11140)
	public void EditATaskFromCalendar() throws Exception {
		logInfo("inside editATaskFromCalendar() Test");
		go2CalendarPage();
		verifyTaskPresentinCalendarDay(calendarTask_text);
		selectTaskPresentInCalendarDayView(calendarTask_text);
		editTaskFromCalendar(calendarTaskUpdated_text);
		waitOnSpinner();
		verifyTaskPresentinCalendarDay(calendarTaskUpdated_text);

	}

	@Test(priority = 11141)
	public void DeleteATaskFromCalendar() throws Exception {
		logInfo("inside deleteATaskFromCalendar() Test");
		go2CalendarPage();
		verifyTaskPresentinCalendarDay(calendarTaskUpdated_text);
		selectTaskPresentInCalendarDayView(calendarTaskUpdated_text);
		deleteTaskFromCalendar(calendarTaskUpdated_text);
		boolean isDeleted = verifyTaskNotPresentinCalendarDay(calendarTaskUpdated_text);
		if (isDeleted == true) {
			logInfo(calendarTaskUpdated_text + " calendar task could not be deleted.");
			Assert.assertFalse(isDeleted, calendarTaskUpdated_text + " calendar task could not be deleted.");
		}
	}

	@Test(priority = 11142)
	public void AddCalendarEventFromDayView() throws Exception {
		logInfo("inside addCalendarEventFromDayView() Test");
		go2CalendarPage();
		createCalendarEventFromDayView("1am", allDayCalendar_Text);
		go2CalendarPage();
		verifyCalendarEventInDayView(allDayCalendar_Text);

	}

	@Test(priority = 11143)
	public void ViewCalendarEventFromDayView() throws Exception {
		logInfo("inside viewCalendarEventFromDayView() Test");
		go2HomePage();
		go2CalendarPage();
		selectCalendarEventInDayView(allDayCalendar_Text);
		viewEvent(allDayCalendar_Text);

	}

	@Test(priority = 11144)
	public void ShareCalendarEventFromDayViewToSocialSites() throws Exception {
		logInfo("inside ShareCalendarEventFromDayViewToSocialSites() Test");
		go2CalendarPage();
		selectCalendarEventInDayView(allDayCalendar_Text);
		shareEvent();
		selectFacebookIconFromPopUp();
		sm.shareInFaceBook(allDayCalendar_Text);
		go2HomePage();
		go2CalendarPage();
		selectCalendarEventInDayView(allDayCalendar_Text);
		shareEvent();
		selectTwitterIconFromPopUp();
		sm.shareInTwitter();
		

	}

	@Test(priority = 11145)
	public void DeleteCalendarEventFromDayView() throws Exception {
		logInfo("inside deleteCalendarEventFromDayView() Test");
		go2HomePage();
		go2CalendarPage();
		selectCalendarEventInDayView(allDayCalendar_Text);
		deleteEvent();
		boolean isDeleted = verifyEventNotDisplayedMonthView(allDayCalendar_Text);
		if (isDeleted == true) {
			logInfo(allDayCalendar_Text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, allDayCalendar_Text + " calendar event could not be deleted.");
		}
	}

	@Test(priority = 11146)
	public void AddCalendarEventFromWeekView() throws Exception {
		logInfo("inside addCalendarEventFromDayView() Test");
		go2CalendarPage();
		selectCalendarView("Week");
		createCalendarEventFromDayView("2am", weekCalendar_Text);
		go2CalendarPage();
		verifyCalendarEventInWeekView(weekCalendar_Text);

	}

	@Test(priority = 11147)
	public void viewCalendarEventFromWeekView() throws Exception {
		logInfo("inside viewCalendarEventFromWeekView() Test");
		go2HomePage();
		go2CalendarPage();
		selectCalendarEventInWeekView(weekCalendar_Text);
		viewEvent(weekCalendar_Text);
	}

	@Test(priority = 11148)
	public void ShareCalendarEventFromWeekViewToSocialSites() throws Exception {
		logInfo("inside ShareCalendarEventFromWeekViewToSocialSites() Test");
		go2CalendarPage();
		selectCalendarEventInWeekView(weekCalendar_Text);
		shareEvent();
		selectFacebookIconFromPopUp();
		sm.shareInFaceBook(weekCalendar_Text);
		selectTwitterIconFromPopUp();
		sm.shareInTwitter();
		
	}

	@Test(priority = 11149)
	public void DeleteCalendarEventFromWeekView() throws Exception {
		logInfo("inside deleteCalendarEventFromWeekView() Test");
		go2HomePage();
		go2CalendarPage();
		selectCalendarView("Week");
		selectCalendarEventInWeekView(weekCalendar_Text);
		deleteEvent();
		boolean isDeleted = verifyCalendarEventNotPresentInWeekView(weekCalendar_Text);
		if (isDeleted == true) {
			logInfo(weekCalendar_Text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, weekCalendar_Text + " calendar event could not be deleted.");
		}

	}

	@Test(priority = 11150)
	public void AddCalendarEventFromMonthView() throws Exception {
		logInfo("inside addCalendarEventFromMonthView() Test");
		go2CalendarPage();
		createCurrentDayCalendarEventFromMonthView(monthCalendar_Text);
		go2CalendarPage();
		verifyEventisDisplayedMonthView(monthCalendar_Text);
	}

	@Test(priority = 11151)
	public void ViewCalendarEventFromMonthView() throws Exception {
		logInfo("inside viewCalendarEventFromMonthView() Test");
		go2CalendarPage();
		selectCalendarEventInMonthView(monthCalendar_Text);
		viewEvent(monthCalendar_Text);
	}

	@Test(priority = 11152)
	public void ShareCalendarEventFromMonthViewToSocialSites() throws Exception {
		logInfo("inside shareCalendarEventFromMonthView() Test");
		go2CalendarPage();
		selectCalendarEventInMonthView(monthCalendar_Text);
		shareEvent();
		selectFacebookIconFromPopUp();
		sm.shareInFaceBook(monthCalendar_Text);
		selectTwitterIconFromPopUp();
		sm.shareInTwitter();

	}

	@Test(priority = 11153)
	public void DeleteCalendarEventFromMonthView() throws Exception {
		logInfo("inside deleteCalendarEventFromMonthView() Test");
		go2CalendarPage();
		selectCalendarEventInMonthView(monthCalendar_Text);
		deleteEvent();
		boolean isDeleted = verifyEventNotDisplayedMonthView(monthCalendar_Text);
		if (isDeleted == true) {
			logInfo(monthCalendar_Text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, monthCalendar_Text + " calendar event could not be deleted.");
		}
	}

	@Test(priority = 11154)
	public void AddCalendarEventFromListView() throws Exception {
		logInfo("inside addCalendarEventFromListView() Test");
		go2CalendarPage();
		createCalendarEventFromListView(listCalendar_Text);
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
	}

	@Test(priority = 11155)
	public void SearchCalendarEventListView() throws Exception {
		logInfo("inside SearchCalendarEventListView() Test");
		go2CalendarPage();
		searchCalendarEventListView(listCalendar_Text);
		verifyEventInListView(listCalendar_Text);

	}

//	@Test(priority = 11156)
	public void EventDetailsPage_addAnImageForACalendarEvent() throws Exception {
		logInfo("inside addAnImageForACalendarEvent Test");

		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		eventDetailsPage_editEvent();
		uploadImageToACalendarEvent();

	}

	@Test(priority = 11157)
	public void EventDetailsPage_PostAStatus() throws Exception {
		logInfo("inside EventDetailsPage_PostAStatus Test");	
		go2CalendarPage();
		selectCalendarView("List");
		selectCalendarListViewEvent(listCalendar_Text);
		postCommentInEventDetailsPage(eventComment_Text);
		verifyCommentPresentForAPostInEventDetailsPage(eventComment_Text);
	}

	@Test(priority = 11158)
	public void EventDetailsPage_LikeAPostedStatus() throws Exception {
		logInfo("inside EventDetailsPage_LikeAPostedStatus Test");
		go2CalendarPage();
		selectCalendarView("List");
		selectCalendarListViewEvent(listCalendar_Text);
		verifyCommentPresentForAPostInEventDetailsPage(eventComment_Text);
		likeInEventDetailsPage(eventComment_Text);

	}

	@Test(priority = 11159)
	public void EventDetailsPage_CommentOnAPostedStatus() throws Exception {
		logInfo("inside EventDetailsPage_CommentOnAPostedStatus Test");
		go2CalendarPage();
		selectCalendarView("List");
		selectCalendarListViewEvent(listCalendar_Text);
		commentInEventDetailsPage(eventComment_Text, eventComment_Text1);
		verifyPostedCommentInEventDetailsPage(eventComment_Text1);
	}

	@Test(priority = 11160)
	public void EventDetailsPage_PostAPhoto() throws Exception {
		logInfo("inside EventDetailsPage_PostAPhoto Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		addEventPhoto();
		verifyPostedPhotoInEventDetailsPage();

	}

	@Test(priority = 11161)
	public void EventDetailsPage_LikeAPostedPhoto() throws Exception {
		logInfo("inside eventDetailsPage_LikeAPostedPhoto Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		verifyPostedPhotoInEventDetailsPage();
		likePhotoPostedInEventDetailsPage();

	}

	@Test(priority = 11162)
	public void EventDetailsPage_CommentOnAPostedPhoto() throws Exception {
		logInfo("inside eventDetailsPage_CommentOnAPostedComment Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		verifyPostedPhotoInEventDetailsPage();
		commentForAPhotoInEventDetailsPage(eventPhotoComment_Text1);
		verifyPostedCommentInEventDetailsPage(eventPhotoComment_Text1);

	}

	@Test(priority = 11163)
	public void EventDetailsPage_shareAPostedPhoto() throws Exception {
		logInfo("inside eventDetailsPage_shareAPostedPhoto Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		shareAPhotoInEventDetailsPage(listCalendar_Text);

	}

	@Test(priority = 11164)
	public void EventDetailsPage_PostAVideo() throws Exception {
		logInfo("inside EventDetailsPage_PostAVideo Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		addEventVideo();

	}

	@Test(priority = 11165)
	public void EventDetailsPage_LikeAPostedVideo() throws Exception {
		logInfo("inside eventDetailsPage_LikeAPostedPhoto Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		verifyPostedVideoInEventDetailsPage();
		likeAVideoInEventDetailsPage();

	}

	@Test(priority = 11166)
	public void EventDetailsPage_CommentOnAPostedVideo() throws Exception {
		logInfo("inside eventDetailsPage_CommentOnAPostedComment Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		verifyPostedVideoInEventDetailsPage();
		commentForAVideoInEventDetailsPage(eventVideoComment_Text1);
		verifyPostedCommentInEventDetailsPage(eventVideoComment_Text1);
	}

	@Test(priority = 11167)
	public void EventDetailsPage_shareAPostedVideo() throws Exception {
		logInfo("inside EventDetailsPage_shareAPostedVideo Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		verifyPostedVideoInEventDetailsPage();
		shareAVideoInEventDetailsPage(listCalendar_Text);

	}

	@Test(priority = 11168)
	public void EventDetailsPage_shareEvent2Facebook() throws Exception {
		logInfo("inside eventDetailsPage_shareEvent2Facebook Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		selectFacebookIconFromPopUp();
		sm.shareInFaceBook(listCalendar_Text);
		sm.closeSharePopUp();
		
	}

	@Test(priority = 11169)
	public void EventDetailsPage_shareEvent2Twitter() throws Exception {
		logInfo("inside eventDetailsPage_shareEvent2Facebook Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		sm.shareInTwitter();
	
	}

	@Test(priority = 11170)
	public void EventDetailsPage_shareEventByEmail() throws Exception {
		logInfo("inside eventDetailsPage_shareEventByEmail Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		selectCalendarListViewEvent(listCalendar_Text);
		sm.selectShareEmailIconInPage();
		shareEventByEmail(fBuserName_Text);
		inbox.loginGmail(fBuserName_Text, fBPwd_text);
		boolean isMailFound = inbox.verifyInboxGmail(listCalendar_Text);
		inbox.signoutGmail();
		go2CalendarPage();
		if(isMailFound==false){
			Assert.assertTrue(isMailFound, listCalendar_Text + " email with subject not found in Gmail.");
		}
	}

	@Test(priority = 11171)
	public void DeleteCalendarEventFromListView() throws Exception {
		logInfo("inside deleteCalendarEventFromListView() Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(listCalendar_Text);
		deleteListViewEvent(listCalendar_Text);
		boolean isDeleted = verifyEventNotPresentInListView(listCalendar_Text);
		if (isDeleted == true) {
			logInfo(listCalendar_Text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, listCalendar_Text + " calendar event could not be deleted.");
		}
	}

	@Test(priority = 11172)
	public void ValidateSearchCalendarEventListView() throws Exception {
		logInfo("inside validateSearchCalendarEventListView() Test");
		go2CalendarPage();
		searchCalendarEventListView(invalidEventName);
		validateNoEventsMessage("No Events Found");
	}

	@Test(priority = 11173)
	public void VerifyCreatedEventInShow1DayFilter() throws Exception {
		logInfo("inside verifyCreatedEventIn1Day() Test");
		go2CalendarPage();
		addQuickCalendarEvent(dayEventName_text, quickEventStartdate_text, quickEventEnddate_text);
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(dayEventName_text);
		selectFilter("Show 1 Week");
		verifyEventInListView(dayEventName_text);
		selectFilter("Show 1 Month");
		verifyEventInListView(dayEventName_text);
		deleteListViewEvent(dayEventName_text);
		boolean isDeleted = verifyEventNotPresentInListView(dayEventName_text);
		if (isDeleted == true) {
			logInfo(dayEventName_text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, dayEventName_text + " calendar event could not be deleted.");
		}
	}

	@Test(priority = 11174)
	public void VerifyCreatedEventInShow1WeekFilter() throws Exception {
		logInfo("inside verifyCreatedEventInShow1WeekFilter() Test");
		String newEventFutureStartdate_text = getDate(3, "MM/dd/yyyy");
		String newEventFutureEnddate_text = getDate(4, "MM/dd/yyyy");
		go2CalendarPage();
		addQuickCalendarEvent(weekEventName_text, newEventFutureStartdate_text, newEventFutureEnddate_text);
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventNotPresentInListView(weekEventName_text);
		selectFilter("Show 1 Week");
		waitOnSpinner();
		verifyEventInListView(weekEventName_text);
		selectFilter("Show 1 Month");
		waitOnSpinner();
		verifyEventInListView(weekEventName_text);


	}

	@Test(priority = 11175)
	public void VerifyCreatedEventInShow1MonthFilter() throws Exception {
		logInfo("inside verifyCreatedEventInShow1MonthFilter() Test");
		String newEventFutureStartdate_text = getDate(20, "MM/dd/yyyy");
		String newEventFutureEnddate_text = getDate(20, "MM/dd/yyyy");
		go2CalendarPage();
		addQuickCalendarEvent(monthEventName_text, newEventFutureStartdate_text, newEventFutureEnddate_text);
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventNotPresentInListView(monthEventName_text);
		selectFilter("Show 1 Week");
		waitOnSpinner();
		verifyEventNotPresentInListView(monthEventName_text);
		selectFilter("Show 1 Month");
		waitOnSpinner();
		verifyEventInListView(monthEventName_text);
		deleteListViewEvent(monthEventName_text);
		waitOnSpinner();
		boolean isDeleted = verifyEventNotPresentInListView(monthEventName_text);
		if (isDeleted == true) {
			logInfo(monthEventName_text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, monthEventName_text + " calendar event could not be deleted.");
		}

	}
	
	@Test(priority = 11176) 
	public void VerifyUpcomingEvents() throws Exception {
		logInfo("inside verifyUpcomingEvents() Test.");
		go2CalendarPage();
		selectCalendarView("List");
		boolean isVerified = checkUpcomingEvents();
		if(isVerified) {
			verifyEventInListView(weekEventName_text);
			deleteListViewEvent(weekEventName_text);
			waitOnSpinner();
			boolean isDeleted = verifyEventNotPresentInListView(weekEventName_text);
			if (isDeleted == true) {
				logInfo(weekEventName_text + " calendar event could not be deleted.");
				Assert.assertFalse(isDeleted, weekEventName_text + " calendar event could not be deleted.");
			}
		}
		if (!isVerified) {
			Assert.assertTrue(isVerified, "Unable to verify the upcoming events count.");
		}

	}

	/*@Test(priority = 11176)
	public void FilterByCorprateEventType() throws Exception {
		logInfo("inside FilterByCorprateEventType() Test");
		go2CalendarPage();
		addCalendarEventByType(userCorpEventName_text, newEventStartdate_text, newEventEnddate_text,
				corporateEventType_text);
		go2CalendarPage();
		selectCalendarView("List");
		selectEventType("Show Corporate Event");
		verifyEventInListView(userCorpEventName_text);
		deleteListViewEvent(userCorpEventName_text);
		boolean isDeleted = verifyEventNotPresentInListView(userCorpEventName_text);
		if (isDeleted == true) {
			logInfo(userCorpEventName_text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, userCorpEventName_text + " calendar event could not be deleted.");
		}

	}

	@Test(priority = 11177)
	public void FilterByAppointmentEventType() throws Exception {
		logInfo("inside FilterByAppointmentEventType() Test");
		go2CalendarPage();
		addCalendarEventByType(appointmentEventName_text, newEventStartdate_text, newEventEnddate_text,
				appointmentEventType_text);
		go2CalendarPage();
		selectCalendarView("List");
		selectEventType("Show Appointment");
		verifyEventInListView(appointmentEventName_text);
		deleteListViewEvent(appointmentEventName_text);
		boolean isDeleted = verifyEventInListView(appointmentEventName_text);
		if (isDeleted == true) {
			logInfo(appointmentEventName_text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, appointmentEventName_text + " calendar event could not be deleted.");
		}
	}

	@Test(priority = 11178)
	public void FilterByPresentationEventType() throws Exception {
		logInfo("inside FilterByPresentationEventType() Test");
		go2CalendarPage();
		addCalendarEventByType(PresentationEventName_text, newEventStartdate_text, newEventEnddate_text,
				presentationEventType_text);
		go2CalendarPage();
		selectCalendarView("List");
		selectEventType("Show Presentation");
		verifyEventInListView(PresentationEventName_text);
		deleteListViewEvent(PresentationEventName_text);
		boolean isDeleted = verifyEventNotPresentInListView(PresentationEventName_text);
		if (isDeleted == true) {
			logInfo(PresentationEventName_text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, PresentationEventName_text + " calendar event could not be deleted.");
		}
	}

	@Test(priority = 11179)
	public void FilterByCompanyAnnivarsaryEventType() throws Exception {
		logInfo("inside FilterByCompanyAnnivarsaryEventType() Test");
		go2CalendarPage();
		addCalendarEventByType(aniversaryEventName_text, newEventStartdate_text, newEventEnddate_text,
				anniversaryEventType_text);
		go2CalendarPage();
		selectCalendarView("List");
		selectEventType("Show Company Annivarsary");
		verifyEventInListView(aniversaryEventName_text);
		deleteListViewEvent(aniversaryEventName_text);
		boolean isDeleted = verifyEventNotPresentInListView(aniversaryEventName_text);
		if (isDeleted == true) {
			logInfo(aniversaryEventName_text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, aniversaryEventName_text + " calendar event could not be deleted.");
		}

	}

	@Test(priority = 11180)
	public void FilterByTeamSportsDayEventType() throws Exception {
		logInfo("inside FilterByTeamSportsDayEventType() Test");
		go2CalendarPage();
		addCalendarEventByType(teamEventName_text, newEventStartdate_text, newEventEnddate_text,
				teamSportsEventType_text);
		go2CalendarPage();
		selectCalendarView("List");
		selectEventType("Show Team sports day");
		verifyEventInListView(teamEventName_text);
		deleteListViewEvent(teamEventName_text);
		boolean isDeleted = verifyEventNotPresentInListView(teamEventName_text);
		if (isDeleted == true) {
			logInfo(teamEventName_text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, teamEventName_text + " calendar event could not be deleted.");
		}
	}

	@Test(priority = 11181)
	public void FilterByMeetingEventType() throws Exception {
		logInfo("inside FilterByMeetingEventType() Test");
		go2CalendarPage();
		addCalendarEventByType(meetEventName_text, newEventStartdate_text, newEventEnddate_text, meetingEventType_text);
		go2CalendarPage();
		selectCalendarView("List");
		selectEventType("Show Meeting");
		verifyEventInListView(meetEventName_text);
		deleteListViewEvent(meetEventName_text);
		boolean isDeleted = verifyEventNotPresentInListView(meetEventName_text);
		if (isDeleted == true) {
			logInfo(meetEventName_text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, meetEventName_text + " calendar event could not be deleted.");
		}

	}

	@Test(priority = 11182)
	public void FilterByTrainingEventType() throws Exception {
		logInfo("inside FilterByTrainingEventType() Test");
		go2CalendarPage();
		addCalendarEventByType(trainingEventName_text, newEventStartdate_text, newEventEnddate_text,
				trainingEventType_text);
		go2CalendarPage();
		selectCalendarView("List");
		selectEventType("Show Training");
		verifyEventInListView(trainingEventName_text);
		deleteListViewEvent(trainingEventName_text);
		boolean isDeleted = verifyEventNotPresentInListView(trainingEventName_text);
		if (isDeleted == true) {
			logInfo(meetEventName_text + " calendar event could not be deleted.");
			Assert.assertFalse(isDeleted, trainingEventName_text + " calendar event could not be deleted.");
		}

	}
*/
	//@Test(priority = 11183)
	public void CreateNewEventwithPastDate() throws Exception {
		logInfo("inside createNewEventwithPastDate() Test");
		String newEventStartPastdate_text = getDate(-2, "MM/dd/yyyy");
		System.out.println(newEventStartPastdate_text);
		String newEventEnddate_text = getDate(0, "MM/dd/yyyy");
		go2CalendarPage();
		addQuickCalendarEvent(pastEventName_text, newEventStartPastdate_text, newEventEnddate_text);
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(pastEventName_text);
		if (isMatchFound) {

			deleteCalendarEvent(pastEventName_text);
			logInfo("isMatchFound =" + isMatchFound);
		} else {
			logInfo(pastEventName_text + " calendar event not created for the past dates.");
			Assert.assertTrue(isMatchFound, pastEventName_text + " calendar event not created for the past dates.");
		}
	}

	@Test(priority = 11184)
	public void CreateNewEventwithFutureDate() throws Exception {
		logInfo("inside createNewEventwithFutureDate() Test");
		String newEventFutureStartdate_text = getDate(0, "MM/dd/yyyy");
		String newEventFutureEnddate_text = getDate(2, "MM/dd/yyyy");
		go2CalendarPage();
		addQuickCalendarEvent(futEventName_text, newEventFutureStartdate_text, newEventFutureEnddate_text);
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(futEventName_text);
		if (isMatchFound) {
			deleteCalendarEvent(futEventName_text);
			logInfo("isMatchFound =" + futEventName_text);
		}

		else {
			logInfo(futEventName_text + " calendar event not created for the past date events..");
			Assert.assertTrue(isMatchFound,
					futEventName_text + " calendar event not created for the past date events.");
		}
	}

	@Test(priority = 11186)
	public void SendEventInvitetoAContactAndPerformAllActions() throws Exception {
		logInfo("inside SendEventInvitetoAContactAndPerformAllActions() Test");
		addCalendarEventandInviteGuests(eventmeetingInv, getDate(0, "MM/dd/yyyy"), getDate(2, "MM/dd/yyyy"),inviteGuestEventSubject_text);
		int beforeGoingCount = getGoingCount();
		int beforeMaybeCount = getMaybeCount();
		int beforeDeclinedCount = getDeclinedCount();
		int beforeNoResopnseCount = getNoResponeCount();
		go2CalendarPage();
		userLogout();
		logOut();
		eventInviteToEmail(gmailId_text1, gmailPwd_text3, eventmeetingInv, "Join");
		waitOnSpinner();
		eventInviteToEmail(gmailId_text2, gmailPwd_text3, eventmeetingInv, "Maybe");
		waitOnSpinner();
		eventInviteToEmail(gmailId_text3, gmailPwd_text3, eventmeetingInv, "Decline");
		waitOnSpinner();
		logIn(prop.getLocatorForEnvironment(appUrl, "adminUser_text"),prop.getLocatorForEnvironment(appUrl, "adminPwd_text"));
	    um.go2Users(); 
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "newsFN1"),prop.getLocatorForEnvironment(appUrl, "newsLN1"), prop.getLocatorForEnvironment(appUrl, "newsCon1"));
		go2CalendarPage();
		boolean isFound = searchCalendarEvent(eventmeetingInv);
		if (isFound == true) {
			selectCalendarView("List");
			selectCalendarListViewEvent(eventmeetingInv);
			int afterGoingCount = getGoingCount();
			int afterMaybeCount = getMaybeCount();
			int aftereDeclinedCount = getDeclinedCount();
			int afterNoResopnseCount = getNoResponeCount();
			Assert.assertEquals(afterGoingCount, beforeGoingCount + 1);
			Assert.assertEquals(afterMaybeCount, beforeMaybeCount + 1);
			Assert.assertEquals(aftereDeclinedCount, beforeDeclinedCount + 1);
			Assert.assertEquals(afterNoResopnseCount, beforeNoResopnseCount - 3);
		}
	}

	@Test(priority = 11187)
	public void InviteModal_InviteAGroup() throws Exception {
		logInfo("InviteModal_InviteAGroup");
		go2CalendarPage();
		addQuickCalendarEvent(eventMeeting, quickEventStartdate_text, quickEventEnddate_text);
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(eventMeeting);
		addNewGroupFromContactsToInviteToAnEvent(calendargroupName_text);
		selectCalendarView("List");
		selectCalendarListViewEvent(eventMeeting);
		selectGroupFromInviteModal(calendargroupName_text);

	}

	@Test(priority = 11188)
	public void InviteModal_SearchAndSelectContactInvite() throws Exception {
		logInfo("InviteModal_SearchAndSelectContactInvite.");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(eventMeeting);
		selectCalendarListViewEvent(eventMeeting);
		selectEventInviteButton();
		searchAndSelectContactToInvite(calendarInvitee_text);
		verifyInvitedContact(calendarInvitee_text);
	}

	@Test(priority = 11189)
	public void InviteModal_SearchAndSelectPreviouslyInvitedContact() throws Exception {
		logInfo("InviteModal_SearchAndSelectPreviouslyInvitedContact.");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(eventMeeting);
		selectCalendarListViewEvent(eventMeeting);
		selectEventInviteButton();
		selectPreviouslyInvitedConact(calendarInvitee_text);
		verifyInvitedContact(calendarInvitee_text);
		String contactNames = calendarInvitee_text;
		bm.go2ContactsPage();
		bm.selectContactsCategoryList("Contacts");
		boolean isContactFound= bm.verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==true){
			bm.selectMultipleContacts(contactNames,"Delete Selected");
			confirmOK();
			Thread.sleep(5000);
		}
		bm.go2Groups();
		bm.deleteGroup(calendargroupName_text);

	}

	@Test(priority = 11190)
	public void EventDetails_DeleteEvent() throws Exception {
		logInfo("eventDetails_DeleteEvent.");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(eventMeeting);
		selectCalendarListViewEvent(eventMeeting);
		waitOnSpinner();
		eventDetailsPage_DeletetEvent();

	}



	@Test(priority = 11199)
	public void createANewYourCalendarType() throws Exception {
		logInfo("inside createANewYourCalendarType() Test");
		go2CalendarPage();
		createNewyourCalendar(calendarName_Txt);
		go2HomePage();
		go2CalendarPage();
		verifyCalendarType(calendarName_Txt);
	}

	@Test(priority = 11200)
	public void SelectYourCalendarType() throws Exception {
		logInfo("inside SelectYourCalendarType() Test");
		go2CalendarPage();
		verifyCalendarType(calendarName_Txt);
		go2HomePage();
		go2CalendarPage();
		selectCalendarType(calendarName_Txt);
	}

	@Test(priority = 11201)
	public void AddANewEventToYourCalenadarEvent() throws Exception {
		logInfo("inside AddANewEventToYourCalenadarEvent() Test");
		go2CalendarPage();
		verifyCalendarType(calendarName_Txt);
		addNewCalendarEvent(newEventNameCalendarType_text, newEventStartdate_text, newEventEnddate_text,calendarName_Txt,false, "sdsa");
		go2CalendarPage();
		verifyEventisDisplayedMonthView(newEventNameCalendarType_text);
	}

	@Test(priority = 11202)
	public void ValidateNewlyAddedYourCalendarEventInOtherCalenadarEvent() throws Exception {
		logInfo("inside ValidateNewlyAddedYourCalendarEventInOtherCalenadarEvent() Test");
		go2CalendarPage();
		deselectCalendarType(calendarName_Txt);
		verifyCalendarEventNotPresnetInDayView(newEventNameCalendarType_text);
	}

	@Test(priority = 11203)
	public void DeleteCalendarTypeAssociateWithEventAndValidate() throws Exception {
		logInfo("inside DeleteCalendarTypeAssociateWithEventAndValidate() Test");
		go2CalendarPage();
		deleteCalendarType(calendarName_Txt);
		
	}

	@Test(priority = 11204)
	public void editYourCalendarType() throws Exception {
		logInfo("inside editYourCalendarType() Test");
		go2CalendarPage();
		editCalendarType(calendarName_Txt);
		updateYourCalendar(calendarName_TxtUpdate);
		go2HomePage();
		go2CalendarPage();
		verifyCalendarType(calendarName_TxtUpdate);
	}

	@Test(priority = 11205)
	public void deleteYourCalendarType() throws Exception {
		logInfo("inside editYourCalendarType() Test");
		go2CalendarPage();
		selectCalendarView("List");
		verifyEventInListView(newEventNameCalendarType_text);
		deleteListViewEvent(newEventNameCalendarType_text);
		go2CalendarPage();
		deleteCalendarType(calendarName_TxtUpdate);
	}

	@Test(priority = 11206)
	public void RepeatCalendarEventDailyEndAfter3Occurances() throws Exception {
		logInfo("inside RepeatCalendarEventDailyEndAfter3Occurances() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt, quickEventStartdate_text, quickEventEnddate_text, "Daily", repeatEventEvery_text, "End After", 3);
		String expectedAccntsts = getRepeatDate();
		eventCreateBtn();
		repeatEventValidation("End After", expectedAccntsts);
		
	}

	@Test(priority = 11207)
	public void RepeatCalendarEventDailyEndOnDate() throws Exception {
		logInfo("inside RepeatCalendarEventDailyEndOnDate() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt1, quickEventStartdate_text, quickEventEnddate_text, "Daily",
				repeatEventEvery_text, "On Date", 3);
		String expectedAccntsts = getRepeatDate();
		eventCreateBtn();
		repeatEventValidation("On Date", expectedAccntsts);
		
	}

	@Test(priority = 11208)
	public void CreateWeeklyRepeatCalendarEventWithNoEndDate() throws Exception {
		logInfo("inside CreateWeeklyRepeatCalendarEventWithNoEndDate() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt4, repeatEventStartdate_text, repeatEventEnddate_text, "Weekly",
				repeatEventEvery_text, "No End Date", 3);
		String expectedAccntsts = getRepeatDate();
		waitOnSpinner();
		eventCreateBtn();
		repeatEventValidation("No End Date", expectedAccntsts);
		
	}

	@Test(priority = 11209)
	public void RepeatCalendarEventWeeklyEndAfter3Occurances() throws Exception {
		logInfo("inside RepeatCalendarEventWeeklyEndAfter3Occurances() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt2, quickEventStartdate_text, quickEventEnddate_text, "Weekly",
				repeatEventEvery_text, "End After", 3);
		String expectedAccntsts = getRepeatDate();
		waitOnSpinner();
		eventCreateBtn();
		repeatEventValidation("End After", expectedAccntsts);
		
	}

	@Test(priority = 11210)
	public void RepeatCalendarEventWeeklyEndOnDate() throws Exception {
		logInfo("inside RepeatCalendarEventWeeklyEndOnDate() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt3, quickEventStartdate_text, quickEventEnddate_text, "Weekly",
				repeatEventEvery_text, "On Date", 3);
		String expectedAccntsts = getRepeatDate();
		waitOnSpinner();
		eventCreateBtn();
		repeatEventValidation("On Date", expectedAccntsts);
		
	}

	@Test(priority = 11211)
	public void CreateDayOfTheWeekRepeatCalendarEventWithNoEndDate() throws Exception {
		logInfo("inside CreateDayOfTheWeekRepeatCalendarEventWithNoEndDate() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt6, repeatEventStartdate_text, repeatEventEnddate_text, "Weekly",
				repeatEventEvery_text, "No End Date", 3);
		String expectedAccntsts = getRepeatDate();
		waitOnSpinner();
		eventCreateBtn();
		repeatEventValidation("No End Date", expectedAccntsts);
		
	}

	@Test(priority = 11212)
	public void RepeatCalendarEventDayOfTheWeekWithNoEndDate() throws Exception {
		logInfo("inside RepeatCalendarEventDayOfTheWeekWithNoEndDate() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt9, quickEventStartdate_text, quickEventEnddate_text, "Day Of The Week",repeatEventEvery_text, "No End Date", 3);
		String expectedAccntsts = getRepeatDate();
		waitOnSpinner();
		eventCreateBtn();
		repeatEventValidation("No End Date", expectedAccntsts);
		
	}

	@Test(priority = 11213)
	public void RepeatCalendarEventDayOfTheWeekWithEndAfter3Occurances() throws Exception {
		logInfo("inside RepeatCalendarEventDayOfTheWeekWithEndAfter3Occurances() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt14, quickEventStartdate_text, quickEventEnddate_text, "Day Of The Week",repeatEventEvery_text, "End After", 3);
		String expectedAccntsts = getRepeatDate();
		waitOnSpinner();
		eventCreateBtn();
		repeatEventValidation("End After", expectedAccntsts);
		
	}

	@Test(priority = 11213)
	public void RepeatCalendarEventDayOfTheWeekWithEndEndOnDate() throws Exception {
		logInfo("inside RepeatCalendarEventDayOfTheWeekWithEndEndOnDate Test");
		go2HomePage();
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt10, quickEventStartdate_text, quickEventEnddate_text, "Day Of The Week",repeatEventEvery_text, "On Date", 15);
		String expectedAccntsts = getRepeatDate();
		waitOnSpinner();
		eventCreateBtn();
		repeatEventValidation("On Date", expectedAccntsts);
		
	}

	@Test(priority = 11214)
	public void RepeatCalendarEventMonthlyOnDayOfMonthNoEndDate() throws Exception {
		logInfo("inside RepeatCalendarEventMonthlyOnDayOfMonthNoEndDate() Test");
		go2HomePage();
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt11, quickEventStartdate_text, quickEventEnddate_text, "Monthly",repeatEventEvery_text, "No End Date", 3);
		String expectedAccntsts = getRepeatDate();
		waitOnSpinner();
		eventCreateBtn();
		repeatEventValidation("No End Date", expectedAccntsts);
		
	}
	
	@Test(priority = 11215)
	public void RepeatCalendarEventMonthlyOnDayOfTheMonthAfter3Occurances() throws Exception {
		logInfo("inside RepeatCalendarEventMonthlyOnDayOfTheMonthAfter3Occurances() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt12, quickEventStartdate_text, quickEventEnddate_text, "Monthly",repeatEventEvery_text, "End After", 3);
		String expectedAccntsts = getRepeatDate();
		eventCreateBtn();
		repeatEventValidation("End After", expectedAccntsts);
		
	}

	
	//@Test(priority = 11216)
	public void RepeatCalendarEventMonthlyOnDayOfTheMonthOnDate() throws Exception {
		logInfo("inside RepeatCalendarEventMonthlyOnDayOfTheMonthOnDate Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEvent_Txt13, quickEventStartdate_text, quickEventEnddate_text, "Monthly",repeatEventEvery_text, "On Date", 29);
		String expectedAccntsts = getRepeatDate();
		eventCreateBtn();
		repeatEventValidation("On Month", expectedAccntsts);
	}
	
	
	

@Test(priority=11215, dependsOnMethods=  {"ShareCalendarEventFromDayViewToSocialSites"})
	public void verifyCalendarEventInFaceBook() throws Exception{
		logInfo ("Entered into verifyEcardInFaceBook() test");		
		sm.login2FBVerifyPostedDetails(fBuserName_Text,fBPwd_text);  
		boolean isSharedPostFound = sm.getPostsOfFBFromTopPart(allDayCalendar_Text);
		if (isSharedPostFound==true) {
			sm.getPostsOfFBFromTopPart(monthCalendar_Text);
			 sm.getPostsOfFBFromTopPart(weekCalendar_Text);
			 sm.getPostsOfFBFromTopPart(listCalendar_Text);
			sm.logoutFB();
			
		}else {
			sm.logoutFB();
			Assert.assertTrue(isSharedPostFound, allDayCalendar_Text + "Template is not yet shared in FB");
		}
		
}

@Test(priority=11216, dependsOnMethods= {"ShareCalendarEventFromDayViewToSocialSites"})
public void verifyCalendarEventsInTwitter() throws Exception{
	logInfo ("Entered into verifyEcardInTwitter() test");
	System.out.println(saveAstempName + " saveAstempName");
		 sm.login2Twitter() ;
		 boolean isSharedPostPresent = sm.verifyPostsInTwitter(allDayCalendar_Text);
		 if(isSharedPostPresent==true) {
			 sm.verifyPostsInTwitter(weekCalendar_Text);
			 sm.verifyPostsInTwitter(monthCalendar_Text);
			 sm.verifyPostsInTwitter(listCalendar_Text);
			 sm.logOutTwitter();	
			 logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
			 loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
		                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		    }else {
		    	sm.logOutTwitter();
		    	logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		    	loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
		                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		    	Assert.assertTrue(isSharedPostPresent, allDayCalendar_Text + " -post is not present in Twitter.");
		
		    }		
       }
	
	
	
	
}
