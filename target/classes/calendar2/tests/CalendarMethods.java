package vibe.calendar2.tests;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import common.EnvProperties;
import common.SocialNetWorksMethods;
import common.TestBase;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.tasks.tests.TaskMethods;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.Interactive;

public class CalendarMethods extends TestBase {

	InboxMethods inbox = new InboxMethods();
	EnvProperties prop = new EnvProperties();
	TaskMethods tm = new TaskMethods();
	MyProfileMethods profile = new MyProfileMethods();
	BusinessContactsMethods cm = new BusinessContactsMethods();
	SocialNetWorksMethods sm = new SocialNetWorksMethods();

	// Create a new calendar event.
	public void addNewCalendarEvent(String eventName, String startDate, String endDate,String calendarType, boolean allDay,String inviteeName)
			throws Exception {
			logInfo("inside addNewCalendarEvent() method...");
			createCalendarLink();
			waitOnElement("xpath", inputEventName);
			inputTextClear("xpath", inputEventName);
			inputText("xpath", inputEventName, eventName);
			selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText",calendarType);
			inputTextClear("xpath", inputEventStartDate);
			inputText("xpath", inputEventStartDate, startDate);
			clickOnElement("cssSelector",startDateCal);
			clickOnElement("cssSelector",startDateCal);
			inputTextClear("xpath", inputEventEndDate);
			inputText("xpath", inputEventEndDate, endDate);
			clickOnElement("cssSelector",endDateCal);
			clickOnElement("cssSelector",endDateCal);
			if(allDay==true) {
			selectRadioOrCheckbox("cssSelector", chkAllday);
			}
			
			clickOnElement("xpath", newEventMoreotions);
			inputTextClear("xpath", inputEventLoc);
			inputText("xpath", inputEventLoc, calendarEventLocation_text);
			inputTextClear("xpath", inputEventAddr1);
			inputText("xpath", inputEventAddr1, calendarEventAddr1_text);
			waitOnElement("xpath", calendarSave);
			scrollDown("xpath", calendarSave);
			clickOnElement("xpath", calendarSave);
			waitOnElement("xpath", inputSearchContact);
			inputText("xpath", inputSearchContact, inviteeName);
			waitOnElement("xpath", calInvSearch);
			WebElement econtactlist = driver().findElement(By.xpath(calInvSearch));
			List<WebElement> allContacts = econtactlist.findElements(By.cssSelector(calInvModal));
			System.out.println("Total contacts = " + allContacts.size());
			String before_name = "//*[@id='search-list']/div[";
			String after_name = "]/div[2]/h4";
			String before_input = "//*[@id='search-list']/div[";
			String after_input = "]/div[3]/input";
			if (allContacts.size() > 0) {
				for (int i = 1; i <= allContacts.size(); i++) {
					WebElement ename = driver().findElement(By.xpath(before_name + i + after_name));
					String actInviteeName = ename.getText().trim();
					if (actInviteeName.equalsIgnoreCase(inviteeName)) {
						clickOnElement("xpath", before_input + i + after_input);
					}
				}
			}
			scrollDown("xpath", eventSaveBtn);
			clickOnElement("xpath", eventSaveBtn);
			waitOnSpinner();
			waitOnElement("linkText","Back To Calendar");
			clickOnLink("linkText", "Back To Calendar");

	}

	public void addNewCalendarEventFromListView(String eventName, String startDate, String endDate, String typeOfEvent)
			throws Exception {
		logInfo("inside addNewCalendarEvent() method...");
		verifyElementPresent("xpath", btnAddEventFromList);
		clickOnLink("xpath", btnAddEventFromList);
		verifyElementPresent("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		inputTextClear("xpath", inputEventStartDate);
		inputText("xpath", inputEventStartDate, startDate);
		inputTextClear("xpath", inputEventEndDate);
		inputText("xpath", inputEventEndDate, endDate);
		clickOnElement("xpath", btnMoreOptions);
		verifyElementPresent("xpath", dpdnEventType);
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", typeOfEvent);
		inputText("xpath", inputEventLoc, eventLoc_text);
		inputText("xpath", inputEventAddr1, eventAddr1_text);
		inputText("xpath", inputEventCity, eventCity_text);
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", "India");
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", eventCtry_text);
		selectFromDropdown("xpath", drpdnEventState, "byVisibleText", eventState_text);
		inputText("xpath", inputEventPincode, eventPincode_text);
		// Thread.sleep(6000);
		waitOnElement("cssSelector", "div > input#submit-form.btn.btn-primary");
		clickOnElement("cssSelector", "div > input#submit-form.btn.btn-primary");
	}

	public void addAlldayCalendarEvent(String eventName, String startDate, String endDate, String inviteeName)
			throws Exception {
		logInfo("inside addAlldayCalendarEvent() method...");
		try {
			createCalendarLink();
			waitOnElement("xpath", inputEventName);
			inputTextClear("xpath", inputEventName);
			inputText("xpath", inputEventName, eventName);
			selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);
			selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", calendarType_text);
			inputTextClear("xpath", inputEventStartDate);
			inputText("xpath", inputEventStartDate, startDate);
			inputTextClear("xpath", inputEventEndDate);
			inputText("xpath", inputEventEndDate, endDate);
			selectRadioOrCheckbox("cssSelector", chkAllday);
			clickOnElement("xpath", newEventMoreotions);
			inputTextClear("xpath", inputEventDescription);
			inputText("xpath", inputEventDescription, calendarEventDescription_text);
			inputText("xpath", inputEventLoc, calendarEventLocation_text);
			inputText("xpath", inputEventAddr1, calendarEventAddr1_text);
			// clickOnElement("xpath",calendarEventImg);
			clickOnElement("xpath", "//*[@id='submit-form']");
			waitOnElement("xpath", inputSearchContact);
			inputText("xpath", inputSearchContact, inviteeName);
			// Select Invitees
			waitOnElement("xpath", "//*[@id='search-list']");
			WebElement econtactlist = driver().findElement(By.xpath("//*[@id='search-list']"));
			List<WebElement> allContacts = econtactlist.findElements(By.cssSelector("div.contact.media"));

			System.out.println("Total contacts = " + allContacts.size());

			String before_name = "//*[@id='search-list']/div[";
			String after_name = "]/div[2]/h4";

			String before_input = "//*[@id='search-list']/div[";
			String after_input = "]/div[3]/input";

			if (allContacts.size() > 0) {
				for (int i = 1; i <= allContacts.size(); i++) {
					WebElement ename = driver().findElement(By.xpath(before_name + i + after_name));
					String actInviteeName = ename.getText().trim();

					if (actInviteeName.equalsIgnoreCase(inviteeName)) {
						clickOnElement("xpath", before_input + i + after_input);

					}
				}
			}

			clickOnElement("xpath", "//*[@id='invite_save']");
			clickOnLink("linkText", "Back To Calendar");
		}

		catch (Exception ex) {
			logInfo("Unable to create an event.");
			Assert.assertTrue(false, eventName + " Unable to create an event");
		}
	}

	public void addRepeatCalendarEvent(String eventName, String startDate, String endDate,String repeatMode, String recur, String eventRecurenceEnd,int onDate)throws Exception {
		logInfo("inside addRepeatCalendarEvent() method.");
		createCalendarLink();
			waitOnElement("xpath", inputEventName);
			inputTextClear("xpath", inputEventName);
			inputText("xpath", inputEventName, eventName);
			selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", prop.getLocatorForEnvironment(appUrl, "calendarType"));
			inputTextClear("xpath", inputEventStartDate);
			inputText("xpath", inputEventStartDate, startDate);
			inputTextClear("xpath", inputEventEndDate);
			inputText("xpath", inputEventEndDate, endDate);
			repeatEventSettings(repeatMode, recur,eventRecurenceEnd,onDate);
		//	clickOnElement("xpath", eventCreateBtn);
			waitOnSpinner();

	}

	public void eventCreateBtn() throws  Exception {
		logInfo("inside eventCreateBtn() method.");
		waitOnElement("xpath", eventCreateBtn);
		clickOnElement("xpath", eventCreateBtn);
		waitOnSpinner();
		waitOnSpinner();
	}
	
	
 	public String getRepeatDate() throws  Exception {
 		waitOnElement("cssSelector","div#edit-recur-summary>div");
		WebElement accst = driver().findElement(By.cssSelector("#recur-summary"));
		scrollDown("cssSelector","#recur-summary");
		String acn = accst.getText();
		System.out.println(acn);
		
		return acn;
	}
	
 	public void createCalendarLink() throws Exception {
 		logInfo("inside createCalendarLink() method...");	
 		waitOnElement("cssSelector",createEventLink);
		clickOnLink("cssSelector", createEventLink);

 		
 	}
	public void addQuickCalendarEvent(String eventName, String startDate, String endDate) throws Exception {
		logInfo("inside addQuickCalendarEvent() method...");		
	//	clickOnElement("cssSelector", addEventListBtn);
		createCalendarLink();
		createQuickEvent(eventName,startDate, endDate);
	}
	
	public void createQuickEvent(String eventName, String startDate, String endDate) throws Exception {
		logInfo("inside addQuickCalendarEvent() method...");		
		verifyElementPresent("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", prop.getLocatorForEnvironment(appUrl, "calendarType"));
		waitOnElement("xpath", inputEventStartDate);
		inputTextClear("xpath", inputEventStartDate);
		inputText("xpath", inputEventStartDate, startDate);
		inputTextClear("xpath", inputEventEndDate);
		inputText("xpath", inputEventEndDate, endDate);
		inputTextClear("xpath", inputEventStartDate);
		inputText("xpath", inputEventStartDate, startDate);
		waitOnElement("xpath", btnEventSave);
		clickOnButton("xpath", btnEventSave);
		waitOnSpinner();
		Thread.sleep(5000);
	}

	public void addCalendarEventByType(String eventName, String startDate, String endDate, String eventType)
			throws Exception {
		logInfo("inside addCalendarEventByType() method...");
		go2CalendarPage();
		createCalendarLink();
		verifyElementPresent("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", eventType);
		selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", calendarType_text);
		inputTextClear("xpath", inputEventStartDate);
		inputText("xpath", inputEventStartDate, startDate);
		inputTextClear("xpath", inputEventEndDate);
		inputText("xpath", inputEventEndDate, endDate);
		waitOnElement("xpath", btnEventSave);
		clickOnButton("xpath", btnEventSave);
	}

	public boolean validateQuickCalendarEvent(String eventName) throws Exception {
		logInfo("inside validateQuickCalendarEvent() method...");
		System.out.println("inside validateQuickCalendarEvent() method...");
		boolean isValidated = false;

		go2CalendarPage();
		// Thread.sleep(3000);
		waitOnElement("xpath", btnNewEvent);
		clickOnElement("xpath", btnNewEvent);

		waitOnElement("xpath", inputEventName);
		inputTextClear("xpath", inputEventName);

		verifyElementPresent("xpath", btnEventSave);
		clickOnButton("xpath", btnEventSave);

		confirmationMessage("Please enter a title for your event.");

		clickOnElement("linkText", "More Options");
		waitOnElement("xpath", "//*[@id='event_form']/div[2]/div[1]/div/span");
		WebElement e = driver().findElement(By.xpath("//*[@id='event_form']/div[2]/div[1]/div/span"));
		if (e.getText().trim().contains("can't be blank")) {
			isValidated = true;
		}

		if (isValidated) {
			inputText("xpath", inputEventName, eventName);
			String startDate = getDate(0, "MM/dd/yyyy");
			String endDate = getDate(-2, "MM/dd/yyyy");

			inputTextClear("xpath", inputEventStartDate);
			inputText("xpath", inputEventStartDate, startDate);

			inputTextClear("xpath", inputEventEndDate);
			inputText("xpath", inputEventEndDate, endDate);

			verifyElementPresent("xpath", btnEventSave);
			clickOnButton("xpath", btnEventSave);

			confirmationMessage("Event end date cannot be before event start date.");
			isValidated = true;
		}

		if (isValidated) {
			go2CalendarPage();
			// Thread.sleep(3000);
			waitOnElement("xpath", btnNewEvent);
			clickOnElement("xpath", btnNewEvent);

			inputText("xpath", inputEventName, eventName);
			String startTime = "08:00 AM";
			String endTime = "07:00 AM";
			inputTextClear("xpath", eventStartTime);
			inputText("xpath", eventStartTime, startTime);
			inputTextClear("xpath", eventEndTime);
			inputText("xpath", eventEndTime, endTime);
			verifyElementPresent("xpath", btnEventSave);
			clickOnButton("xpath", btnEventSave);

			confirmationMessage("Event start time must be before event end time");
			isValidated = true;
		}

		return isValidated;
	}

	public void go2CalendarPage() throws Exception {
		logInfo("inside go2CalendarPage() method...");
		driver().navigate().to(appUrl + "crm/calendar");
	}

	public void go2CorporateCalendarPage() throws  Exception {
		System.out.println("inside go2CorporateCalendarPage() method");
		driver().navigate().to(appUrl + "crm/calendar?admin=true");
	}

	public void addCorpCalendarEvent(String eventName, String startDate, String endDate, String inviteeName)
			throws Exception {
		logInfo("inside addCorpCalendarEvent() method...");
		createCalendarLink();
		waitOnElement("xpath", "//*[@id='submit-form']");
		inputTextClear("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		waitOnElement("xpath", dpdnEventType);
		selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText",
				prop.getLocatorForEnvironment(appUrl, "corpcalendarType"));
		inputTextClear("xpath", inputEventStartDate);
		inputText("xpath", inputEventStartDate, startDate);
		inputTextClear("xpath", inputEventEndDate);
		inputText("xpath", inputEventEndDate, endDate);
		waitOnElement("xpath", newEventMoreotions);
		clickOnElement("xpath", newEventMoreotions);
		inputTextClear("xpath", inputEventDescription);
		inputText("xpath", inputEventDescription, eventName + " description");
		inputText("xpath", inputEventLoc, calendarEventLocation_text);
		inputText("xpath", inputEventAddr1, calendarEventAddr1_text);
		// clickOnElement("xpath",calendarEventImg);
		clickOnElement("xpath", "//*[@id='submit-form']");

		// Select Invitees

		waitOnElement("xpath", inputSearchContact);
		inputText("xpath", inputSearchContact, inviteeName);
		Thread.sleep(2000);

		// Select Invitees

		waitOnElement("xpath", "//*[@id='search-list']");
		WebElement econtactlist = driver().findElement(By.xpath("//*[@id='search-list']"));
		List<WebElement> allContacts = econtactlist.findElements(By.cssSelector("div.contact.media"));

		System.out.println("Total contacts = " + allContacts.size());

		String before_name = "//*[@id='search-list']/div[";
		String after_name = "]/div[2]/h4";

		String before_input = "//*[@id='search-list']/div[";
		String after_input = "]/div[3]/input";

		if (allContacts.size() > 0) {
			for (int i = 1; i <= allContacts.size(); i++) {
				WebElement ename = driver().findElement(By.xpath(before_name + i + after_name));
				String actInviteeName = ename.getText().trim();

				if (actInviteeName.equalsIgnoreCase(inviteeName)) {
					clickOnElement("xpath", before_input + i + after_input);
				}
			}
		}

		clickOnElement("xpath", "//*[@id='invite_save']");
		clickOnLink("linkText", "Back To Calendar");
	}

	public void addCorpCalendarEvent(String eventName, String inviteeName) throws Exception {
		logInfo("inside addCorpCalendarEvent() method...");
		createCalendarLink();
		waitOnElement("xpath", inputEventName);
		inputTextClear("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);
		selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", calendarType_text);
		waitOnElement("xpath", newEventMoreotions);
		clickOnElement("xpath", newEventMoreotions);
		inputTextClear("xpath", inputEventDescription);
		inputText("xpath", inputEventDescription, eventName + " description");
		inputText("xpath", inputEventLoc, calendarEventLocation_text);
		inputText("xpath", inputEventAddr1, calendarEventAddr1_text);
		// clickOnElement("xpath",calendarEventImg);
		clickOnElement("xpath", "//*[@id='submit-form']");

		// Select Invitees

		waitOnElement("xpath", inputSearchContact);
		inputText("xpath", inputSearchContact, inviteeName);
		Thread.sleep(2000);

		// Select Invitees

		waitOnElement("xpath", "//*[@id='search-list']");
		WebElement econtactlist = driver().findElement(By.xpath("//*[@id='search-list']"));
		List<WebElement> allContacts = econtactlist.findElements(By.cssSelector("div.contact.media"));

		System.out.println("Total contacts = " + allContacts.size());

		String before_name = "//*[@id='search-list']/div[";
		String after_name = "]/div[2]/h4";

		String before_input = "//*[@id='search-list']/div[";
		String after_input = "]/div[3]/input";

		if (allContacts.size() > 0) {
			for (int i = 1; i <= allContacts.size(); i++) {
				WebElement ename = driver().findElement(By.xpath(before_name + i + after_name));
				String actInviteeName = ename.getText().trim();

				if (actInviteeName.equalsIgnoreCase(inviteeName)) {
					clickOnElement("xpath", before_input + i + after_input);
				}
			}
		}

		clickOnElement("xpath", "//*[@id='invite_save']");
		clickOnLink("linkText", "Back To Calendar");
	}

	// Verify if event is displayed in the calendar dashboard.

	public boolean verifyEventisDisplayedMonthView(String eventName) throws Exception {
		logInfo("inside verifyEventisDisplayedMonthView() method.");
		selectCalendarView("Month");
		searchQuery(eventName);
		boolean isMatchFound = false;
		try {
			waitOnElement("cssSelector", eleMatchEvent);
			List<WebElement> allElements = driver().findElements(By.cssSelector(eleMatchEvent));
			for (WebElement x : allElements) {
				String event = x.getText().trim();
				System.out.println("event = " +event);
				if (event.equalsIgnoreCase(eventName)) {
					System.out.println(eventName + " match found in the Month view.");
					logInfo(eventName + " match found in the Month view.");
					isMatchFound = true;
					break;
				}
			}
			if (isMatchFound == false) {
				logInfo(eventName + " match not found.");
					Assert.assertTrue(isMatchFound, eventName + " match not found in Month view.");
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return isMatchFound;
	}

	

	public boolean verifyEventNotDisplayedMonthView(String eventName) throws Exception {
		logInfo("inside verifyEventNotDisplayedMonthView() Method.");
		selectCalendarView("Month");
		boolean isMatchFound = false;
		waitOnElement("cssSelector", eleMatchEvent);
		List<WebElement> allElements = driver().findElements(By.cssSelector(eleMatchEvent));
		for (WebElement x : allElements) {
			String event = x.getText().trim();
			if (event.equalsIgnoreCase(eventName)) {
				System.out.println(eventName + " match found in the Month view.");
				logInfo(eventName + " match found in the Month view.");
				isMatchFound = false;
				Assert.assertTrue(isMatchFound, eventName + " is available in Calender Month view.");
				break;
			}
		}

		return isMatchFound;
	}

	public void selectCalendarEventInMonthView(String eventName) throws Exception {
		logInfo("inside selectCalendarEventInMonthView() Method.");
		selectCalendarView("Month");
		searchQuery(eventName);
		waitOnElement("cssSelector", ".fc-content>span.fc-title");
		boolean isMatchFound = false;
		waitOnElement("cssSelector", eleMatchEvent);
		List<WebElement> allElements = driver().findElements(By.cssSelector(eleMatchEvent));
		for (WebElement x : allElements) {
			String event = x.getText().trim();
			if (event.equalsIgnoreCase(eventName)) {
				System.out.println(eventName + " match found in the Month view.");
				logInfo(eventName + " match found in the Month view.");
				isMatchFound = true;
				x.click();
				break;
			}
		}
		if (isMatchFound == false) {
			logInfo(eventName + " match not found.");
			Assert.assertTrue(isMatchFound, eventName + " match not found in Month view.");
		}

	}

	// Search for an event in the calendar
	public boolean searchCalendarEvent(String eventName) throws Exception {
		System.out.println("inside searchCalendarEvent() method");
		logInfo("inside searchCalendarEvent() method.");
		searchQuery(eventName);
		waitOnElement("cssSelector", ".fc-content>span.fc-title");
		boolean isSearchMatchFound = false;
		List<WebElement> dayEvent = driver().findElements(By.cssSelector(".fc-content>.fc-title"));
		System.out.println("Events" + dayEvent.size());
		for (WebElement events : dayEvent) {
			String dayeventName = events.getText().trim();
			if (dayeventName.equalsIgnoreCase(eventName)) {
				isSearchMatchFound = true;
				logInfo(eventName + " calendar event match found ");
				break;
			}
		}
		if (isSearchMatchFound == false) {
			logInfo(eventName + " calendar event match not found in day calendar view.");
			Assert.assertTrue(isSearchMatchFound, eventName + " calendar event match not found in day calendar view.");
		}

		return isSearchMatchFound;
	}

	public void searchCalendarEventListView(String eventName) throws Exception {
		System.out.println("inside searchCalendarEvent() method");
		logInfo("inside searchCalendarEvent() method.");
		selectCalendarView("List");
		waitOnElement("xpath", inputSearchListView);
		inputTextClear("xpath", inputSearchListView);
		inputText("xpath", inputSearchListView, eventName);

	}

	public void validateNoEventsMessage(String message) throws  Exception {
		logInfo("inside validateNoEventsMessage() Method.");
		waitOnElement("cssSelector", ".clearfix.no-events");
		verifyElementPresent("cssSelector", ".clearfix.no-events");
		String expectedText = driver().findElement(By.cssSelector(".clearfix.no-events")).getText().trim();
		System.out.println(expectedText);
		Assert.assertEquals(expectedText, message);

	}

	public void updateCalendarEvent(String eventName) throws Exception {
		logInfo("inside updateCalendarEvent() method.");
		System.out.println("inside updateCalendarEvent() method.");
		eventDetailsPage_editEvent();
		waitOnElement("xpath", inputEventName);
		inputTextClear("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		waitOnElement("cssSelector", eventDetaulsSaveBtn);
		scrollDown("cssSelector", eventDetaulsSaveBtn);
		clickOnLink("cssSelector", eventDetaulsSaveBtn);
		waitOnElement("xpath", btnEventSave);
		scrollDown("xpath", btnEventSave);
		clickOnElement("xpath", btnEventSave);
		waitOnSpinner();

	}

	public void deleteCalendarEvent(String eventType, String eventName) throws  Exception {
		System.out.println("inside deleteCalendarEvent() method.");
		logInfo("inside deleteCalendarEvent() method."); //
		waitOnElement("cssSelector", tblCalendar);
		waitOnElement("cssSelector", eleMatchEvent);
		List<WebElement> allElements = driver().findElements(By.cssSelector(eleMatchEvent));
		for (WebElement element : allElements) {
			if (element.getText().equalsIgnoreCase(eventName)) {
				logInfo(eventName + " - event match found ...");
				element.click();

				switch (eventType) {
				case "New Event":
					try {
						waitOnElement("linkText", "Delete");
						clickOnElement("linkText", "Delete");
						confirmAlert(); // confirmationMessage("Your event has
										// been deleted.");
						logInfo("clicked on delete button of calendar event");
						break;
					} catch (Exception e) {
						logInfo(e.getMessage());
					}
				case "Allday Event":
					try {
						System.out.println("all day event");
						waitOnElement("linkText", "Delete");
						clickOnElement("linkText", "Delete");
						confirmAlert(); //
						confirmationMessage("Your event has been deleted.");
						logInfo("clicked on delete button of calendar event");
						break;
					} catch (Exception e) {
						logInfo(e.getSuppressed());
					}

				case "Repeat Event":
					try {
						verifyElementPresent("xpath", btnDeleteAllInstances);
						clickOnButton("xpath", btnDeleteAllInstances);
						confirmOK();
						logInfo("clicked on delete button of calendar event");
						break;
					} catch (Exception e) {
						logInfo(e.getSuppressed());
					}
				case "Quick Event":
					try {
						verifyElementPresent("xpath", btnDeleteEvent);
						clickOnButton("xpath", btnDeleteEvent);
						confirmAlert();
						logInfo("clicked on delete button of calendar event");
						break;
					} catch (Exception e) {
						logInfo(e.getSuppressed());
					}
				case "Corporate Calendar Event":
					try {
						verifyElementPresent("xpath", btnDeleteEvent);
						clickOnButton("xpath", btnDeleteEvent);
						confirmAlert();
						logInfo("clicked on delete button of calendar event");
						break;
					} catch (Exception e) {
						logInfo(e.getSuppressed());
					}
				default:
					logInfo("Invalid Event Type entered in updateCalendarEvent() method.");
					break;
				}
			}
		}
	}

	public void deleteCalendarEvent(String eventName) throws  Exception {
		System.out.println("inside deleteCalendarEvent() method.");
		logInfo("inside deleteCalendarEvent() method.");
		boolean isEventFound = false;
		// waitOnElement("cssSelector",tblCalendar);
		waitOnElement("cssSelector", eleMatchEvent);
		List<WebElement> allElements = driver().findElements(By.cssSelector(eleMatchEvent));
		for (WebElement element : allElements) {
			if (element.getText().equalsIgnoreCase(eventName)) {
				logInfo(eventName + " - event match found to delete.");
				isEventFound = true;
				element.click();
				waitOnElement("linkText", "Delete");
				clickOnElement("linkText", "Delete");
				Thread.sleep(3000);
				// confirmAlert();
				confirmOK();
				//confirmationMessage("Your event has been deleted.");
				break;
			}
		}

		if (isEventFound == false) {
			logInfo(eventName + " - event match not found to delete.");
		}
	}

	public void addNewEventInvitation(String eventName, String startDate, String endDate) throws Exception {
		logInfo("inside addNewEventInvitation() method...");
		waitOnElement("cssSelector", tblCalendar);
		verifyElementPresent("xpath", btnNewEvent);
		clickOnLink("xpath", btnNewEvent);

		verifyElementPresent("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		inputTextClear("xpath", inputEventStartDate);
		inputText("xpath", inputEventStartDate, startDate);
		inputTextClear("xpath", inputEventEndDate);
		inputText("xpath", inputEventEndDate, endDate);

		clickOnElement("xpath", btnMoreOptions);
		// Thread.sleep(5000);
		waitOnElement("xpath", dpdnEventType);
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);

		inputText("xpath", inputEventLoc, eventLoc_text);
		inputText("xpath", inputEventAddr1, eventAddr1_text);
		inputText("xpath", inputEventCity, eventCity_text);
		inputText("xpath", inputEventPincode, eventPincode_text);

		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", "India");
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", eventCtry_text);
		verifyElementPresent("xpath", drpdnEventState);
		selectFromDropdown("xpath", drpdnEventState, "byVisibleText", eventState_text);

		verifyElementPresent("xpath", btnInviteGuests);
		clickOnButton("xpath", btnInviteGuests);

		selectContact2Event(inviteGuestFName_text + " " + inviteGuestLName_text);
		// Thread.sleep(3000);
		verifyElementPresent("xpath", inputCalendarSubject);
		inputText("xpath", inputCalendarSubject, inviteGuestEventSubject_text);

		verifyElementPresent("xpath", btnEventSave);
		clickOnButton("xpath", btnEventSave);
		// Thread.sleep(5000);
	}

	public void selectContact2Event(String guestName) throws Exception {
		logInfo("inside selectContact2Event() method.");

		verifyLinkPresent("All Contacts");
		clickOnLink("linkText", "All Contacts");

		verifyElementPresent("xpath", inputSearchContact);
		inputText("xpath", inputSearchContact, guestName);

		// Thread.sleep(5000);
		waitOnElement("xpath", panelInviteGuests);
		WebElement contactsPane = driver().findElement(By.xpath(panelInviteGuests));
		List allContacts = contactsPane.findElements(By.tagName("div"));
		int count = allContacts.size();
		System.out.println("total contacts =" + count);

		String beforeName = "//*[@id='search-list']/div[";
		String afterName = "]/div[2]/h4";

		String beforeInput = "//*[@id='search-list']/div[";
		String afterInput = "]/div[3]/input[@type='checkbox']";

		boolean isContactFound = false;
		if (count > 0) {
			for (int i = 1; i <= count; i++) {
				waitOnElement("xpath", beforeName + i + afterName);
				WebElement heading = driver().findElement(By.xpath(beforeName + i + afterName));
				String headingName = heading.getText().trim();
				System.out.println("Heading =" + headingName);
				System.out.println("Heading =" + guestName.trim());
				if (headingName.equalsIgnoreCase(guestName)) {
					System.out.println("match found " + guestName);
					isContactFound = true;
					waitOnElement("xpath", beforeInput + i + afterInput);
					WebElement input = driver().findElement(By.xpath(beforeInput + i + afterInput));
					input.click();
					// Thread.sleep(3000);
					verifyElementPresent("cssSelector", btnSaveGuests);
					clickOnButton("cssSelector", btnSaveGuests);
					logInfo("clicked on Save button of Invite Guests pane.");
					break;
				}
			}
		}
		if (isContactFound == false) {
			logInfo("contact could not be found = " + guestName);
			Assert.assertTrue(isContactFound, "contact could not be found = " + guestName);
		}
	}

	public void verifyCalendarEventInAllViews(String eventName) throws Exception {
		logInfo("inside verifyCalendarEventInAllViews() method.");
		waitOnElement("xpath", calendarViewPane);
		WebElement viewpane = driver().findElement(By.xpath(calendarViewPane));
		List<WebElement> allButtons = viewpane.findElements(By.tagName("button"));
		for (WebElement x : allButtons) {
			String buttonName = x.getText().trim();
			x.click();
			logInfo("clicked on " + buttonName + " view.");
			System.out.println("clicked on " + buttonName + " view.");
			switch (buttonName) {
			case "Day":
				logInfo("inside Day view...");
				go2CalendarPage();
				verifyCalendarEventInDayView(eventName);
			case "Week":
				logInfo("inside Week view...");
				go2CalendarPage();
				verifyCalendarEventInWeekView(eventName);
			case "Month":
				logInfo("inside Month view...");
				go2CalendarPage();
				verifyEventisDisplayedMonthView(eventName);
			}
		}
	}

	public boolean verifyPreviousEventDisplayed(String eventName) throws Exception {
		logInfo("Inside verifyPreviousEventDisplayed() method.");
		boolean isEventFound = false;
		// Thread.sleep(5000);
		waitOnElement("xpath", btnPrevMonth);
		driver().findElement(By.xpath(btnPrevMonth)).click();
		// Thread.sleep(3000);
		isEventFound = verifyEventisDisplayedMonthView(eventName);
		return isEventFound;
	}

	public boolean verifyFutureEventDisplayed(String eventName) throws Exception {
		logInfo("Inside verifyFutureEventDisplayed() method.");
		boolean isEventFound = false;
		// Thread.sleep(5000);
		waitOnElement("xpath", btnNextMonth);
		driver().findElement(By.xpath(btnNextMonth)).click();
		// Thread.sleep(4000);
		isEventFound = verifyEventisDisplayedMonthView(eventName);
		return isEventFound;
	}
/*
	public boolean verifyRepeatCalendarSettings(String eventName, String startDate, String endDate) throws Exception {
		logInfo("Inside verifyRepeatCalendarSettings() method.");
		boolean isRepeatSettingsFound = false;
		clickOnLink("linkText", "Create Event");
		waitOnElement("xpath", inputEventName);
		inputTextClear("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);
		selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", calendarType_text);
		inputTextClear("xpath", inputEventStartDate);
		inputText("xpath", inputEventStartDate, startDate);
		inputTextClear("xpath", inputEventEndDate);
		inputText("xpath", inputEventEndDate, endDate);
		verifyElementPresent("xpath", chkRepeatEvent);
		selectRadioOrCheckbox("xpath", chkRepeatEvent);
		repeatEventSettings("Daily", "End After");
		clickOnElement("xpath", eventCreateBtn);
		clickOnLink("linkText", "Settings");
		waitOnElement("linkText", "Edit");
		clickOnElement("linkText", "Edit");
		waitOnElement("linkText", "Edit");
		clickOnElement("linkText", "Edit");
		WebElement repeatDaily = driver().findElement(By.xpath(chkRepeatDaily));
		WebElement endsAfter = driver().findElement(By.xpath(chkEndsAfter));
		if (repeatDaily.isSelected() && endsAfter.isSelected()) {
			isRepeatSettingsFound = true;
		}
		verifyElementPresent("xpath", btnRepEventSave);
		clickOnButton("xpath", btnRepEventSave);
		return isRepeatSettingsFound;

	}*/

	public boolean verifyEventsListOptions(String eventName) throws InterruptedException {
		logInfo("Inside verifyEventsListOptions() method.");
		boolean isOptionsFound = false;
		driver().findElement(By.xpath(btnList)).click();
		verifyElementPresent("xpath", eventsList);
		List<WebElement> events = driver().findElements(By.xpath(eventsList));

		String before = "//tr[contains(@id,'event')][";
		String after = "]/td[1]/a";

		String optionBefore = "//tr[contains(@id,'event')][";
		String optionAfter = "]/td[3]/div/div/div/button";

		String eventOptionBefore = "//tr[contains(@id,'event')][";
		String eventOptionAfter = "]/td[3]/div/div/div/ul/li[";
		String txtOption = "]/a";

		for (int i = 1; i <= events.size(); i++) {
			WebElement event = driver().findElement(By.xpath(before + i + after));
			if (event.getText().equalsIgnoreCase(eventName)) {
				WebElement eventOptions = driver().findElement(By.xpath(optionBefore + i + optionAfter));
				eventOptions.click();
				String shareEvent = driver()
						.findElement(By.xpath(eventOptionBefore + i + eventOptionAfter + 1 + txtOption)).getText();
				String editEvent = driver()
						.findElement(By.xpath(eventOptionBefore + i + eventOptionAfter + 2 + txtOption)).getText();
				String deleteEvent = driver()
						.findElement(By.xpath(eventOptionBefore + i + eventOptionAfter + 3 + txtOption)).getText();
				if (shareEvent.equalsIgnoreCase(txtShare) && editEvent.equalsIgnoreCase(txtEdit)
						&& deleteEvent.equalsIgnoreCase(txtDeleteEvent)) {
					isOptionsFound = true;
				}
			}
		}
		return isOptionsFound;
	}

	/*
	 * public void back2Calendar() throws Exception {
	 * logInfo("inside back2Calendar Method"); verifyElementPresent("linkText",
	 * "Back To Calendar"); clickOnLink("linktext", "Back To Calendar");
	 * 
	 * }
	 */

	public void editCalendarEvent(String eventName) throws Exception {
		logInfo("Edit the calender event ");
		boolean isMatchFound = false;
		waitOnElement("cssSelector", eleMatchEvent);
		List<WebElement> allElements = driver().findElements(By.cssSelector(eleMatchEvent));
		for (WebElement x : allElements) {
			String event = x.getText().trim();
			if (event.equalsIgnoreCase(eventName)) {
				logInfo(eventName + " match found.");
				x.click();
				isMatchFound = true;
				// Thread.sleep(2000);
				waitOnElement("xpath", btnEditEvent);
				clickOnElement("xpath", btnEditEvent);
				// Thread.sleep(5000);
				break;
			}
		}
		if (isMatchFound == false) {
			Assert.assertTrue(isMatchFound, eventName + " is not available in Calender.");
		}
	}

	public void editListViewEvent(String eventName) throws  Exception {
		logInfo("inside editListViewEvent() method.");
		boolean isEventPresent = false;
		waitOnElement("xpath", "//*[@id='agenda-widget']/div[2]/table/tbody/tr/td[2]");
		WebElement tabl = driver().findElement(By.xpath("//*[@id='agenda-widget']/div[2]/table/tbody"));
		List<WebElement> itemss = tabl.findElements(By.tagName("tr"));
		System.out.println(itemss.size());
		for (int i = 1; i <= itemss.size(); i++) {
			String part1 = "//*[@id='agenda-widget']/div[2]/table/tbody/tr[";
			String part2 = "]";
			WebElement tdlist = driver().findElement(By.xpath(part1 + i + part2));
			List<WebElement> itemsss = tdlist.findElements(By.tagName("td"));
			int links = itemsss.size();
			System.out.println(links);
			if (links > 1) {
				String eventAft = "]/td[2]/a";
				String more = "]/td[5]/div/div[1]/div/button";
				String edit = "]/td[5]/div/div[1]/div/ul/li[2]";
				WebElement event = driver().findElement(By.xpath(part1 + i + eventAft));
				String events = event.getText().trim();
				System.out.println(events);
				if (events.equalsIgnoreCase(eventName)) {
					System.out.println("to delete  " + events);
					isEventPresent = true;
					waitOnElement("xpath", part1 + i + more);
					clickOnButton("xpath", part1 + i + more);
					waitOnElement("xpath", part1 + i + edit);
					clickOnButton("xpath", part1 + i + edit);
					confirmOK();
					break;

				}
			}

		}
		if (isEventPresent == false) {
			Assert.assertTrue(isEventPresent,
					eventName + " is not available in Calender list view to delete the event.");

		}
	}

	public void getInvitedcount(String guestType) {
		List<WebElement> gst = driver().findElements(By.cssSelector(guest));
		System.out.println(gst.size());
		for (int i = 1; i <= gst.size(); i++) {
			WebElement guests = driver().findElement(By.cssSelector(guest1 + i + guest2));
			String g = guests.getText().trim();
			if (g.equalsIgnoreCase(guestType)) {
				WebElement count = driver().findElement(By.cssSelector(guest1 + i + inviteguest2));
				count.getText();
				System.out.println(count.getText());
				break;
			}
		}
	}

	public void verifyInvitedGuests(String guestType, String eventName) throws Exception {
		logInfo("Get count of Guests and Invite the guest by selecting conatct mail Ids, verify invited conatcts.");
		List<WebElement> gst = driver().findElements(By.cssSelector(guest));
		for (int i = 1; i <= gst.size(); i++) {
			WebElement guests = driver().findElement(By.cssSelector(guest1 + i + guest2));
			String g = guests.getText().trim();
			if (g.equalsIgnoreCase(guestType)) {
				WebElement count = driver().findElement(By.cssSelector(guest1 + i + inviteguest2));
				String b4Invite = count.getText();
				int b4i = Integer.parseInt(b4Invite);
				System.out.println("before count is " + b4i);
				clickOnButton("cssSelector", inviteBtn);
				waitOnElement("cssSelector", titleInvite);
				clickOnLink("linkText", "All Contacts");
				List<WebElement> mails = driver().findElements(By.cssSelector(emailList));
				System.out.println(mails.size());
				if (mails.size() == 0) {
					System.err.println("No contacts are available to invite");
				} else if (mails.size() == 15) {
					for (int j = 1; j <= mails.size() - 10; j++) {
						WebElement mm = driver().findElement(By.cssSelector(emailList1 + j + emailList2));
						System.out.println(mm.getText());
						mm.click();
						// Thread.sleep(2000);
					}
				} else {
					for (int j = 1; j <= mails.size() - 22; j++) {
						WebElement mm = driver().findElement(By.cssSelector(emailList1 + j + emailList2));
						System.out.println("original " + mm.getText());
						mm.click();
						// Thread.sleep(2000);
					}
				}

				System.out.println("Retrive count of Selected mail Ids");
				List<WebElement> selectedMail = driver().findElements(By.cssSelector(conList));
				int noMailsId = selectedMail.size();
				System.out.println("selected mail Id count is " + noMailsId);
				int aftInviatation = b4i + noMailsId;
				String afterInvited = Integer.toString(aftInviatation);
				// Thread.sleep(2000);
				waitOnElement("cssSelector", saveInv);
				getText("cssSelector", saveInv, "Text is");
				clickOnButton("cssSelector", saveInv);
				// Thread.sleep(5000);
				waitOnElement("xpath", btnEventSave);
				clickOnButton("xpath", btnEventSave);
				// confirmationMessage(eventUpdate);
				// Thread.sleep(5000);
				go2CalendarPage();
				editCalendarEvent(eventName);
				// Thread.sleep(4000);
				waitOnElement("cssSelector", invCount);
				WebElement Invitedcount = driver().findElement(By.cssSelector(invCount));
				Assert.assertEquals(Invitedcount.getText(), afterInvited);
				break;
			}
		}
	}

	public void invitation(String eventName) throws Exception {
		logInfo("Verify the invited contact");
		clickOnButton("cssSelector", inviteBtn);
		waitOnElement("cssSelector", titleInvite);
		clickOnLink("linkText", "All Contacts");
		List<WebElement> mails = driver().findElements(By.cssSelector(emailList));
		System.out.println(mails.size());
		if (mails.size() == 0) {
			System.err.println("No contacts are available to invite");
		} else {
			for (int i = 1; i <= mails.size(); i++) {
				WebElement mm = driver().findElement(By.cssSelector(emailList1 + i + emailList2));
				mm.click();
				// Thread.sleep(2000);
				waitOnElement("cssSelector", conList);
				List<WebElement> selectedMail = driver().findElements(By.cssSelector(conList));
				for (WebElement Selectedemails : selectedMail) {
					String selectedContact = Selectedemails.getText();
					System.out.println("selectedContact" + selectedContact);
					clickOnButton("cssSelector", saveInv);
					// Thread.sleep(5000);
					waitOnElement("xpath", btnEventSave);
					inputText("cssSelector", claBody, claBodyText);
					clickOnButton("xpath", btnEventSave);
					confirmationMessage(eventUpdate);

					// String selectedContact = "Rani Kidambi";
					go2CalendarPage();
					editCalendarEvent(eventName);
					waitOnElement("cssSelector", invCount);
					WebElement Invitedcount = driver().findElement(By.cssSelector(invCount));
					Invitedcount.click();

					boolean isInivtedContact = false;
					waitOnElement("cssSelector", invitees);
					List<WebElement> li = driver().findElements(By.cssSelector(invitees));
					for (WebElement list : li) {
						System.out.println(list.getText() + "names ");
						if (list.getText().equalsIgnoreCase(selectedContact))
							;
						isInivtedContact = true;
						Assert.assertEquals(list.getText(), selectedContact);
						inputTextClear("cssSelector", inputComposeEmailSubject);
						inputText("cssSelector", inputComposeEmailSubject, QuickEventDescription_text);
						clickOnButton("cssSelector", send_Cal);
						// confirmationMessage("Message sent to:
						// "+selectedContact);
						break;
					}
					if (isInivtedContact == false) {
						Assert.assertTrue(isInivtedContact, selectedContact + " - contact is not available.");

					}
					break;
				}
				break;
			}
		}
	}

	public void deleteListEvent(String eventname) throws Exception {
		List<WebElement> itemss = driver().findElements(By.cssSelector(items));
		System.out.println(itemss.size());
		boolean isEventPresent = false;

		for (WebElement event : itemss) {
			String events = event.getText().trim();
			if (events.equalsIgnoreCase(eventname)) {
				isEventPresent = true;
				event.click();
				// Thread.sleep(2000);
				waitOnElement("cssSelector", deleteCal);
				clickOnButton("cssSelector", deleteCal);
				confirmAlert();
				break;
			}
		}
		if (isEventPresent == false) {
			Assert.assertTrue(isEventPresent, eventname + " is not available in the list view.");
		}

	}

	public boolean verifyListEventNotToPresent(String eventname) throws Exception {
		List<WebElement> itemss = driver().findElements(By.cssSelector(items));
		System.out.println(itemss.size());
		boolean isEventPresent = true;

		for (WebElement event : itemss) {
			String events = event.getText().trim();
			System.out.println(events);
			if (events.equalsIgnoreCase(eventname)) {
				isEventPresent = false;
				Assert.assertTrue(isEventPresent, eventname + " is available in the list view.");
				break;
			}
		}
		if (isEventPresent == true) {
			System.out.println("Succeed , Event is present  - " + eventname);
		}
		return isEventPresent;
	}

	public void selectMoreOptioninListView(String eventName, String actionType) throws Exception {
		// calenderToolBar("List");

		clickOnElement("xpath", "//div[@class='fc-left']/button[4]");
		boolean isPresent = verifyEventInListView(eventName);
		if (isPresent = true) {
			selectEventActionsUnderListView(eventName, actionType);

		} else {
			Assert.assertTrue(isPresent, eventName + " event is not present");
		}

		/*
		 * inputTextClear("cssSelector", filtCal); inputText("cssSelector", filtCal,
		 * eventName); submitElement("cssSelector", filtCal);
		 */
	}

	public void sendMessasge() throws  Exception {
		logInfo("inside sendMessage Method");
		waitOnElement("xpath", sendEmailBtn);
		clickOnButton("xpath", sendEmailBtn);
		confirmationMessage("Message sent to: " + prop.getLocatorForEnvironment(appUrl, "selfmaildId1"));

	}

	public void closeShareEmailModal() throws  Exception {
		logInfo("inside closeShareEmailModal Method");
		waitOnElement("cssSelector", shareemailClose);
		clickOnButton("cssSelector", shareemailClose);
	}

	public void selectEventActionsUnderListView(String eventname, String actionType) throws Exception {
		logInfo("In List View, verifies the event then select option like Share, edit , delete");
		String eventB4r = "table.events-list>tbody>tr:nth-child(";
		String eventAft = ")>td:nth-child(2)>a";
		String actionAft = ")>td:nth-child(5)>div>div.more-options>div>button";
		String moreOptAft = ")>td:nth-child(5)>div>div.more-options>div>ul>li:nth-child(1)>a";

		List<WebElement> itemss = driver().findElements(By.cssSelector(items));
		System.out.println(itemss.size());
		boolean isEventPresent = false;
		for (int i = 2; i <= itemss.size() * 2; i++) {
			WebElement event = driver().findElement(By.cssSelector(eventB4r + i + eventAft));
			String events = event.getText().trim();
			System.out.println(events);
			if (events.equalsIgnoreCase(eventname)) {
				isEventPresent = true;
				WebElement moreOp = driver().findElement(By.cssSelector(eventB4r + i + actionAft));
				moreOp.click();
				boolean isoptionsPresent = false;
				List<WebElement> options = driver().findElements(By.cssSelector(eventB4r + i + moreOptAft));
				for (WebElement op : options) {
					if (op.getText().trim().equalsIgnoreCase(actionType)) {
						isoptionsPresent = true;
						op.click();
						waitOnElement("xpath", shareEmailIcon);
						clickOnElement("xpath", shareEmailIcon);
						break;
					}

				}
				if (isoptionsPresent == false) {
					Assert.assertTrue(isoptionsPresent, actionType + " options is present in Action Type Options.");
				}
				break;
			}
			i++;
		}
		if (isEventPresent == false) {
			Assert.assertTrue(isEventPresent, eventname + " is not available in the list view.");
		}

	}

	public void close() throws Exception {
		logInfo("Entered into close() method");
		waitOnElement("cssSelector", closeSharePopUp);
		clickOnElement("cssSelector", closeSharePopUp);

	}

	public void verifyInvitedGuestsInAdmin(String guestType, String eventName) throws Exception {
		logInfo("Admin- Get count of Guests and Invite the guest by selecting conatct mail Ids, verify invited conatcts.");
		List<WebElement> gst = driver().findElements(By.cssSelector(guest));
		for (int i = 1; i <= gst.size(); i++) {
			WebElement guests = driver().findElement(By.cssSelector(guest1 + i + guest2));
			String g = guests.getText().trim();
			if (g.equalsIgnoreCase(guestType)) {
				WebElement count = driver().findElement(By.cssSelector(guest1 + i + inviteguest2));
				String b4Invite = count.getText();
				int b4i = Integer.parseInt(b4Invite);
				System.out.println("before count is " + b4i);
				clickOnButton("cssSelector", inviteBtn);
				waitOnElement("cssSelector", titleInvite);
				clickOnLink("linkText", "All Contacts");
				List<WebElement> mails = driver().findElements(By.cssSelector(emailList));
				System.out.println(mails.size());
				if (mails.size() == 0) {
					System.err.println("No contacts are available to invite");
				} else if (mails.size() == 15) {
					for (int j = 1; j <= mails.size() - 10; j++) {
						WebElement mm = driver().findElement(By.cssSelector(emailList1 + j + emailList2));
						System.out.println(mm.getText());
						mm.click();
						// Thread.sleep(2000);
					}
				} else {
					for (int j = 1; j <= mails.size() - 22; j++) {
						WebElement mm = driver().findElement(By.cssSelector(emailList1 + j + emailList2));
						System.out.println("original " + mm.getText());
						mm.click();
						// Thread.sleep(2000);
					}
				}

				System.out.println("Retrive count of Selected mail Ids");
				List<WebElement> selectedMail = driver().findElements(By.cssSelector(conList));
				int noMailsId = selectedMail.size();
				System.out.println("selected mail Id count is " + noMailsId);
				int aftInviatation = b4i + noMailsId;
				String afterInvited = Integer.toString(aftInviatation);
				// Thread.sleep(2000);
				waitOnElement("cssSelctor", saveInv);
				getText("cssSelector", saveInv, "Text is");
				clickOnButton("cssSelector", saveInv);
				// Thread.sleep(5000);
				clickOnButton("xpath", btnEventSave);
				// confirmationMessage(eventUpdate);
				// Thread.sleep(5000);
				go2CorporateCalendarPage();
				editCalendarEvent(eventName);
				// Thread.sleep(4000);
				waitOnElement("cssSelector", invCount);
				WebElement Invitedcount = driver().findElement(By.cssSelector(invCount));
				Assert.assertEquals(Invitedcount.getText(), afterInvited);
				break;
			}
		}
	}

	// Try to select Event Type which would be beside AddEvent in List View
	// page.
	public void eventTypeSelection(String typeOfEvent) throws Exception {
		logInfo("Entered into 'eventTypeSelection' method");
		boolean isTypePresent = false;
		clickOnElement("cssSelector", eventTypeDots);
		// Thread.sleep(2000);
		waitOnElement("cssSelector", eventType);
		List<WebElement> et = driver().findElements(By.cssSelector(eventType));
		System.out.println(et.size() + " size is");
		for (WebElement ets : et) {
			System.out.println("ets are " + ets.getText());
			if (ets.getText().equalsIgnoreCase(typeOfEvent)) {
				isTypePresent = true;
				ets.click();
				// Thread.sleep(5000);
				break;
			}
		}
		if (isTypePresent == false) {
			Assert.assertTrue(isTypePresent, typeOfEvent + " is not present in options.");
		}
	}

	public void verifyNewInvitees(String guestType, String eventName) throws Exception {
		logInfo("inside verifyNewInvitees() method.");
		System.out.println("inside verifyNewInvitees() method.");

		waitOnElement("xpath", btnInviteGuests);
		clickOnElement("xpath", btnInviteGuests);

		waitOnElement("cssSelector", titleInvite);
		// clickOnLink("linkText","Previously Invited");
		clickOnLink("linkText", "All Contacts");
		waitOnElement("cssSelector", emailList);
		List<WebElement> mails = driver().findElements(By.cssSelector(emailList));
		System.out.println(mails.size());

		if (mails.size() == 0) {
			System.err.println("No contacts are available to invite");
		} else {
			for (int j = 1; j <= mails.size(); j++) {
				WebElement e = driver().findElement(By.xpath(
						"//*[@id='contact-lists']/div[@class='contact-list']/div/div[@class='contact media row'][1]/div[@class='media-right']/input"));
				e.click();
				break;
			}
		}

		/*
		 * else if(mails.size()==15){ for (int j =1; j<=mails.size()-10; j++){
		 * WebElement mm =
		 * driver().findElement(By.cssSelector(emailList1+j+emailList2));
		 * System.out.println(mm.getText()); mm.click(); Thread.sleep(2000); } } else{
		 * for(int j =1; j<=mails.size()-22; j++){ WebElement mm =
		 * driver().findElement(By.cssSelector(emailList1+j+emailList2));
		 * System.out.println("original "+mm.getText()); mm.click(); Thread.sleep(2000);
		 * } }
		 */

		System.out.println("Retrive count of Selected mail Ids");
		List<WebElement> selectedMail = driver().findElements(By.cssSelector(conList));
		int inviteeList = selectedMail.size();
		System.out.println("selected mail Id count is " + inviteeList);
		int aftInviatation = inviteeList;
		String afterInvited = Integer.toString(aftInviatation);
		waitOnElement("cssSelector", saveInv);
		clickOnButton("cssSelector", saveInv);

		waitOnElement("cssSelector", "#new-invitees");
		String newInvitees = driver().findElement(By.cssSelector("#new-invitees")).getText().trim();
		Assert.assertEquals(afterInvited, newInvitees);

		Thread.sleep(3000);
		waitOnElement("cssSelector", inviteBtn);
		clickOnButton("cssSelector", inviteBtn);
		waitOnElement("cssSelector", titleInvite);

		List<WebElement> selectedContacts = driver().findElements(By.cssSelector(conList));
		for (int i = 1; i <= selectedContacts.size(); i++) {
			WebElement e = driver()
					.findElement(By.cssSelector("#invite-contacts > div > div > div > ul > li:nth-child(" + i + ")"));
			e.click();
		}

		int after_contacts_deletion = driver().findElements(By.cssSelector(conList)).size();
		String afterDeletion = Integer.toString(after_contacts_deletion);
		clickOnButton("cssSelector", saveInv);
		waitOnElement("cssSelector", "#new-invitees");
		String newInvitees1 = driver().findElement(By.cssSelector("#new-invitees")).getText().trim();
		Assert.assertEquals(afterDeletion, newInvitees1);

	}

	public void verifyEventLink(String eventName) throws Exception {
		logInfo("inside verifyEventLink() method.");
		System.out.println("inside verifyEventLink() method.");
		waitOnElement("xpath", btnInviteGuests);
		clickOnElement("xpath", btnInviteGuests);
		waitOnElement("cssSelector", titleInvite);
		clickOnLink("linkText", "All Contacts");
		List<WebElement> mails = driver().findElements(By.cssSelector(emailList));
		System.out.println(mails.size());
		if (mails.size() == 0) {
			System.err.println("No contacts are available to invite");
		} else if (mails.size() == 15) {
			for (int j = 1; j <= mails.size() - 10; j++) {
				WebElement mm = driver().findElement(By.cssSelector(emailList1 + j + emailList2));
				System.out.println(mm.getText());
				mm.click();
				// Thread.sleep(2000);
			}
		} else {
			for (int j = 1; j <= mails.size() - 22; j++) {
				WebElement mm = driver().findElement(By.cssSelector(emailList1 + j + emailList2));
				System.out.println("original " + mm.getText());
				mm.click();
				// Thread.sleep(2000);
			}
		}

		clickOnButton("cssSelector", saveInv);
		// Thread.sleep(5000);
		waitOnElement("xpath", btnSave);
		clickOnElement("xpath", btnSave);
		go2CalendarPage();
		// viewCalendarEvent(eventName); even if we select recipient, it is
		// saying plz select atleast one recipient.
	}

	public void dismissCorporateEvent(String eventName) throws  Exception {
		logInfo("inside dismissCorporateEvent() method.");
		waitOnElement("linkText", "Dismiss Event");
		clickOnLink("linkText", "Dismiss Event");
		confirmAlert();

	}

	/**/
	public boolean verifyBackFromEventDetailsPage(String eventName) throws  Exception {
		logInfo("inside verifyBackFromEventDetailsPage() method.");
		System.out.println("inside verifyBackFromEventDetailsPage() method.");
		boolean isVerified = false;
		waitOnElement("linkText", "View");
		clickOnElement("linkText", "View");
		waitOnElement("xpath", btnBackEvent);
		clickOnElement("xpath", btnBackEvent);
		// Thread.sleep(5000);
		isVerified = verifyEventisDisplayedMonthView(eventName);
		return isVerified;
	}

	public boolean verifyEventInListView(String eventName) throws  Exception {
		logInfo("inside verifyEventInListView() method.");
	
		boolean isEventPresent = false;
		waitOnElement("xpath", tableListBdy);
		WebElement tabl = driver().findElement(By.xpath(tableListBdy));
		List<WebElement> itemss = tabl.findElements(By.tagName("tr"));
		System.out.println(itemss.size());
		for (int i = 1; i <= itemss.size(); i++) {
			WebElement tdlist = driver().findElement(By.xpath(evenDetailspart1 + i + evenDetailspart2));
			List<WebElement> itemsss = tdlist.findElements(By.tagName("td"));
			int links = itemsss.size();
			System.out.println(links);
			if (links > 1) {
				WebElement event = driver().findElement(By.xpath(evenDetailspart1 + i + eventDetailsAft));
				String events = event.getText().trim();
				System.out.println(events);
				if (events.equalsIgnoreCase(eventName)) {
					System.out.println("to verify  " + events);
					isEventPresent = true;

					break;
				}
			}
		}
		if (isEventPresent == false) {
			Assert.assertTrue(isEventPresent, eventName + " is not available in Calender list view.");
		}

		return isEventPresent;
	}

	public boolean selectCalendarListViewEvent(String eventName) throws  Exception {
		logInfo("inside selectCalendarListViewEvent() method.");
		boolean isEventPresent = false;
		waitOnElement("xpath", tableListBdy);
		WebElement tabl = driver().findElement(By.xpath(tableListBdy));
		List<WebElement> itemss = tabl.findElements(By.tagName("tr"));
		for (int i = 1; i <= itemss.size(); i++) {
			WebElement tdlist = driver().findElement(By.xpath(evenDetailspart1 + i + evenDetailspart2));
			List<WebElement> itemsss = tdlist.findElements(By.tagName("td"));
			int links = itemsss.size();
			if (links > 1) {
				WebElement event = driver().findElement(By.xpath(evenDetailspart1 + i + eventDetailsAft));
				String events = event.getText().trim();
				if (events.equalsIgnoreCase(eventName)) {
					isEventPresent = true;
					clickOnElement("xpath", evenDetailspart1 + i + eventDetailsAft);
					break;
				}
			}
		}
		if (isEventPresent == false) {
			Assert.assertTrue(isEventPresent, eventName + " is not available in Calender list view.");
		}

		return isEventPresent;
	}

	public boolean verifyEventNotPresentInListView(String eventName) throws  Exception {
		logInfo("inside verifyEventNotPresentInListView() Method.");
		boolean isEventPresent = false;
		boolean isMessageFound = verifyElementPresent("xpath", "//*[@id='agenda-widget']/div[2]/p");
		if (isMessageFound) {

			String expectedText = driver().findElement(By.xpath("//*[@id='agenda-widget']/div[2]/p")).getText().trim();
			Assert.assertEquals("No Events Found", expectedText);

		} else if (isMessageFound = false) {
			waitOnElement("xpath", tableListBdy);
			WebElement tabl = driver().findElement(By.xpath(tableListBdy));
			List<WebElement> itemss = tabl.findElements(By.tagName("tr"));
			System.out.println(itemss.size());
			for (int i = 1; i <= itemss.size(); i++) {
				String part1 = "//*[@id='agenda-widget']/div[2]/div/table/tbody//tr[";
				String part2 = "]";
				WebElement tdlist = driver().findElement(By.xpath(evenDetailspart1 + i + evenDetailspart2));
				List<WebElement> itemsss = tdlist.findElements(By.tagName("td"));
				int links = itemsss.size();
				System.out.println(links);
				if (links > 1) {
					WebElement event = driver().findElement(By.xpath(evenDetailspart1 + i + eventDetailsAft));
					String events = event.getText().trim();
					System.out.println(events);
					if (events.equalsIgnoreCase(eventName)) {
						System.out.println("to verify  " + events);
						isEventPresent = false;
						Assert.assertTrue(isEventPresent, eventName + " is available in Calender list view.");

						break;
					}
				}

			}

		}
		return isEventPresent;
	}

	public void deleteListViewEvent(String eventName) throws  Exception {
		logInfo("inside deleteListViewEvent() method.");
		System.out.println("inside deleteListViewEvent() method.");
		boolean isEventPresent = false;
		waitOnElement("xpath", tableListBdy);
		WebElement tabl = driver().findElement(By.xpath(tableListBdy));
		List<WebElement> itemss = tabl.findElements(By.tagName("tr"));
		System.out.println(itemss.size());
		for (int i = 1; i <= itemss.size(); i++) {
			String part1 = "//*[@id='agenda-widget']/div[2]/table/tbody/tr[";
			String part2 = "]";
			WebElement tdlist = driver().findElement(By.xpath(evenDetailspart1 + i + evenDetailspart2));
			List<WebElement> itemsss = tdlist.findElements(By.tagName("td"));
			int links = itemsss.size();
			System.out.println(links);
			if (links > 1) {
				String eventAft = "]/td[2]/a";
				String more = "]/td[5]/div/div[1]/div/button";
				String delete = "]/td[5]/div/div[1]/div/ul/li[3]";
				WebElement event = driver().findElement(By.xpath(evenDetailspart1 + i + eventDetailsAft));
				String events = event.getText().trim();
				System.out.println(events);
				if (events.equalsIgnoreCase(eventName)) {
					System.out.println("to delete  " + events);
					isEventPresent = true;
					waitOnElement("xpath", evenDetailspart1 + i + more);
					clickOnButton("xpath", evenDetailspart1 + i + more);
					waitOnElement("xpath", evenDetailspart1 + i + delete);
					clickOnButton("xpath", evenDetailspart1 + i + delete);
					confirmOK();
					break;

				}
			}

		}
		if (isEventPresent == false) {
			Assert.assertTrue(isEventPresent,
					eventName + " is not available in Calender list view to delete the event.");

		}
	}
	
	
	public void deleteAllListViewEvent() throws  Exception {
		logInfo("inside deleteAllListViewEvent() method.");
		waitOnElement("xpath", tableListBdy);
		WebElement tabl = driver().findElement(By.xpath(tableListBdy));
		List<WebElement> itemss = tabl.findElements(By.tagName("tr"));
		System.out.println(itemss.size());
		for (int i = 1; i <= itemss.size(); i++) {
			String part1 = "//*[@id='agenda-widget']/div[2]/table/tbody/tr[";
			String part2 = "]";
			WebElement tdlist = driver().findElement(By.xpath(evenDetailspart1 + i + evenDetailspart2));
			List<WebElement> itemsss = tdlist.findElements(By.tagName("td"));
			int links = itemsss.size();
			System.out.println(links);
			if (links > 1) {
				String eventAft = "]/td[2]/a";
				String more = "]/td[5]/div/div[1]/div/button";
				String delete = "]/td[5]/div/div[1]/div/ul/li[3]";
					waitOnElement("xpath", evenDetailspart1 + i + more);
					clickOnButton("xpath", evenDetailspart1 + i + more);
					waitOnElement("xpath", evenDetailspart1 + i + delete);
					clickOnButton("xpath", evenDetailspart1 + i + delete);
					boolean isRepeatButnFound= verifyElementPresent("xpath", btnDeleteAllInstances);
					if(isRepeatButnFound) {
						waitOnElement("xpath", btnDeleteAllInstances);
						clickOnElement("xpath", btnDeleteAllInstances);
						confirmOK();
					}
					else {
					confirmOK();
					}
				}
			}

		
	}
	

	public boolean checkCorporateDefaultOptions(String eventName, String startDate, String endDate) throws Exception {
		logInfo("inside checkCorporateDefaultOptions() method...");
		boolean isVerified = true;
		createCalendarLink();
		waitOnElement("xpath", inputEventName);
		inputTextClear("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);
		selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", calendarType_text);
		inputTextClear("xpath", inputEventStartDate);
		inputText("xpath", inputEventStartDate, startDate);
		inputTextClear("xpath", inputEventStartTime);
		inputText("xpath", inputEventStartTime, "01:00 AM");
		inputTextClear("xpath", inputEventEndDate);
		inputText("xpath", inputEventEndDate, endDate);
		inputTextClear("xpath", inputEventEndTime);
		inputText("xpath", inputEventEndTime, "10:00 PM");
		clickOnElement("xpath", rankDefTab);
		waitOnElement("xpath", chkRankAll);
		WebElement rank = driver().findElement(By.xpath(chkRankAll));
		clickOnElement("xpath", marktLangsTab);
		// Thread.sleep(2000);
		waitOnElement("xpath", chkMarketsAll);
		WebElement markets = driver().findElement(By.xpath(chkMarketsAll));
		waitOnElement("xpath", subPlansTab);
		clickOnElement("xpath", subPlansTab);
		// Thread.sleep(2000);
		WebElement subscription = driver().findElement(By.xpath(chkSubscriptionAll));

		if (!rank.isSelected() || !markets.isSelected() || !subscription.isSelected()) {
			isVerified = false;
		}

		return isVerified;
	}

	public boolean checkUpcomingEvents() throws  Exception {
		logInfo("inside checkUpcomingEvents() method...");
		boolean isVerified = false;
		waitOnElement("xpath", txtUpcomingEvents);
		WebElement upcomingEvents = driver().findElement(By.xpath(txtUpcomingEvents));
		if (upcomingEvents.getText().trim().contains("upcoming event(s) in the next")) {
			isVerified = true;
			upcomingEvents.click();
		}
		return isVerified;
	}

	public void deleteEventFromEditPage() throws  Exception {
		System.out.println("inside deleteEventFromEditPage() method");
		logInfo("inside deleteEventFromEditPage() method...");
		waitOnElement("linkText", "Delete");
		clickOnElement("linkText", "Delete");
		confirmAlert();
		confirmationMessage("Your event has been deleted.");
	}

	public boolean verifyBackFromEditEvent() throws  Exception {
		System.out.println("inside verifyBackFromEditEvent() method");
		logInfo("inside verifyBackFromEditEvent() method...");
		boolean isValidated = false;
		// Thread.sleep(3000);
		waitOnElement("xpath", inputEventTitle);
		inputTextClear("xpath", inputEventTitle);
		inputText("xpath", inputEventTitle, "new event");
		clickOnElement("xpath", btnBackEditEvent);
		waitOnElement("xpath", unsavedChanges);
		WebElement e = driver().findElement(By.xpath(unsavedChanges));

		if (e.getText().trim().contains("You have unsaved changes")) {
			isValidated = true;
		}
		if (isValidated) {
			// Thread.sleep(3000);
			waitOnElement("linkText", "Proceed");
			clickOnElement("linkText", "Proceed");
			// Thread.sleep(3000);
			waitOnElement("xpath", calendarTitle);
			WebElement el = driver().findElement(By.xpath(calendarTitle));
			if (el.getText().trim().contains("Calendar")) {
				isValidated = true;
			} else {
				isValidated = false;
			}
		}
		return isValidated;
	}

	public boolean verifyGuestInvMail(String eventName, String startDate, String endDate) throws Exception {
		System.out.println("inside verifyGuestInvMail() method");
		logInfo("inside verifyGuestInvMail() method...");
		boolean isVerified = false;
		addNewEventInvitation(eventName, startDate, endDate);
		// Thread.sleep(5000);
		inbox.go2Inbox();
		isVerified = inbox.selectVibeMailsWithSubject(inviteGuestEventSubject_text);
		return isVerified;
	}

	public boolean sortEventsByEventType(String eventType, String eventName) throws  Exception {
		System.out.println("inside sortEventsByEventType() method");
		logInfo("inside sortEventsByEventType() method...");
		boolean isVerified = false;
		waitOnElement("xpath", "//*[@id='pyr_crm_event_event_type_id']");
		selectFromDropdown("xpath", "//*[@id='pyr_crm_event_event_type_id']", "byVisibleText", "Anniversary");
		clickOnElement("xpath", "//*[@id='submit-form']");
		// Thread.sleep(5000);
		waitOnElement("xpath", eventTypes);
		clickOnElement("xpath", eventTypes);
		clickOnElement("linkText", eventType);
		// Thread.sleep(3000);
		isVerified = verifyEventInListView(eventName);
		if (!isVerified) {
			Assert.assertTrue(isVerified, "Unable to find the event in the ListView. " + eventName);
		}
		return isVerified;
	}

	public void addNewemailsToInviteToAnEvent() throws Exception {
		logInfo("inside addNewemailsToInviteToAnEvent() Method");
		waitOnElement("cssSelector", eventInviteBtn);
		clickOnElement("cssSelector", eventInviteBtn);
		verifyLinkPresent("All Contacts");
		clickOnLink("linkText", "All Contacts");
		waitOnElement("linkText", "Add New Contact");
		clickOnLink("linkText", "Add New Contact");
		inputText("cssSelector", inputCalendarContactEmail, gmailId_text1);
		clickOnLink("linkText", "Add More Email");
		waitOnElement("cssSelector", inputCalendarContactEmail1);
		inputTextClear("cssSelector", inputCalendarContactEmail1);
		inputText("cssSelector", inputCalendarContactEmail1, gmailId_text2);
		clickOnLink("linkText", "Add More Email");
		waitOnElement("cssSelector", inputCalendarContactEmail2);
		inputText("cssSelector", inputCalendarContactEmail2, gmailId_text3);
		clickOnLink("linkText", "Add More Email");
		waitOnElement("cssSelector", inputCalendarContactEmail2);
		inputText("cssSelector", inputCalendarContactEmail3, gmailId_text4);
		clickOnButton("xpath", addEmailCal_Btn);
		waitOnElement("cssSelector", saveInv);
		clickOnElement("cssSelector", saveInv);
		clickOnElement("cssSelector", saveInv);
		waitOnSpinner();
		confirmationMessage("Fantastic! Your invites have been successfully sent out to 4 recipients.");
		
	}

	public void addCalendarEventandInviteGuests(String eventName, String startDate, String endDate, String eventSub)
			throws Exception {
		logInfo("inside addCalendarEventandInviteGuests() method...");
		go2CalendarPage();
		createCalendarLink();
		waitOnElement("xpath", inputEventName);
		inputTextClear("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);
		selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", calendarType_text);
		inputTextClear("xpath", inputEventStartDate);
		inputText("xpath", inputEventStartDate, startDate);
		inputTextClear("xpath", inputEventEndDate);
		inputText("xpath", inputEventEndDate, endDate);
		selectRadioOrCheckbox("cssSelector", chkAllday);
		clickOnElement("xpath", newEventMoreotions);
		inputTextClear("xpath", inputEventDescription);
		inputText("xpath", inputEventDescription, calendarEventDescription_text);
		inputText("xpath", inputEventLoc, calendarEventLocation_text);
		inputText("xpath", inputEventAddr1, calendarEventAddr1_text);
		waitOnElement("xpath", calendarSave);
		scrollDown("xpath", calendarSave);
		clickOnElement("xpath", calendarSave);
		addNewemailsToInviteToAnEvent();
	}

	public void eventInviteLink() throws Exception {
		logInfo("inside eventInviteLink Method");
		waitOnElement("linkText", "Click Here To View The Event");
		verifyElementPresent("linkText", "Click Here To View The Event");
		clickOnLink("linkText", "Click Here To View The Event");

	}

	public void selectEventResponseOptions(String option) throws  Exception {
		logInfo("inside selectEventResponseOptions() Method");
		String wndBeforeWindow = driver().getWindowHandle();
		for (String w : driver().getWindowHandles()) {
			if (!w.equalsIgnoreCase(wndBeforeWindow)) {
				driver().switchTo().window(w);
				waitOnElement("xpath", eventResponseDDwn);
				clickOnElement("xpath", eventResponseDDwn);
				List<WebElement> responseLi = driver()
						.findElements(By.xpath("//*[@id='event_response_container']/ul/li"));
				int responseOptions = responseLi.size();
				System.out.println("total no of options" + responseOptions);
				for (int i = 1; i <= responseOptions; i++) {
					String part1 = "//*[@id='event_response_container']/ul/li[";
					String part2 = "]/a";
					String part3 = "/i";
					WebElement expectedOption = driver().findElement(By.xpath(part1 + i + part2));
					String expectedOptionName = expectedOption.getText().trim();
					if (expectedOptionName.equalsIgnoreCase(option)) {
						expectedOption.click();
						Thread.sleep(10000);
						boolean isClicked = verifyElementPresent("xpath", part1 + i + part2 + part3);
						if (isClicked = false) {
							logInfo(expectedOptionName + " is showing as not selected ");
							Assert.assertTrue(isClicked, expectedOptionName + " is showing as not selected ");
						}
						break;
					}

				}
				driver().close();
				driver().switchTo().window(wndBeforeWindow);
			}

		}

	}

	public void eventInviteToEmail(String userName, String passWord, String subject, String responseOpt)
			throws Exception {
		inbox.loginGmail(userName, passWord);
		boolean isMailFound = inbox.verifyInboxGmail(subject);
		if (isMailFound) {
			inbox.openMailInGmail(subject);
			eventInviteLink();
			selectEventResponseOptions(responseOpt);
			inbox.signoutGmail();
		}
	}

	public int getGoingCount() throws  Exception {
		logInfo("Inside getGoingCount Method");
		waitOnElement("cssSelector", goingCount);
		WebElement countbefore = driver().findElement(By.cssSelector(goingCount));
		String b4InviteGoin = countbefore.getText();
		int b4InviteGoing = Integer.parseInt(b4InviteGoin);
		System.out.println("no of people going" + b4InviteGoing);
		logInfo("no of people going" + b4InviteGoing);
		return b4InviteGoing;
	}

	public int getMaybeCount() throws  Exception {
		logInfo("Inside getMaybeCount Method");
		waitOnElement("cssSelector", maybeCount);
		WebElement countbefore = driver().findElement(By.cssSelector(maybeCount));
		String b4InviteMayb = countbefore.getText();
		int b4InviteMaybe = Integer.parseInt(b4InviteMayb);
		System.out.println("no of people Maybe going" + b4InviteMaybe);
		logInfo("no of people Maybe going" + b4InviteMaybe);
		return b4InviteMaybe;
	}

	public int getDeclinedCount() throws  Exception {
		logInfo("Inside getNoResponseCount Method");
		waitOnElement("cssSelector", declinedCount);
		WebElement countbefore = driver().findElement(By.cssSelector(declinedCount));
		String b4InviteDecline = countbefore.getText();
		int b4InviteDeclined = Integer.parseInt(b4InviteDecline);
		System.out.println("no of people declined" + b4InviteDeclined);
		logInfo("no of people declined" + b4InviteDeclined);
		return b4InviteDeclined;
	}

	public int getNoResponeCount() throws  Exception {
		logInfo("Inside getNoResponeCount Method");
		waitOnElement("cssSelector", noResponseCount);
		WebElement countbefore = driver().findElement(By.cssSelector(noResponseCount));
		String b4InviteNoRespons = countbefore.getText();
		int b4InviteNoResponse = Integer.parseInt(b4InviteNoRespons);
		System.out.println("no of people no response" + b4InviteNoResponse);
		logInfo("no of people no response" + b4InviteNoResponse);
		return b4InviteNoResponse;
	}

	public void createNewyourCalendar(String calendarType) throws  Exception {
		logInfo("Inside createNewyourCalendar Method");
		// waitOnElement("cssSelector", yourCalendarDrpDwn);
		// clickOnLink("cssSelector", yourCalendarDrpDwn);
		clickOnLink("linkText", "Your Calendars");
		waitOnElement("cssSelector", createNewLink);
		clickOnLink("cssSelector", createNewLink);
		Thread.sleep(20000);
		waitOnElement("cssSelector", calendarName);
		inputTextClear("cssSelector", calendarName);
		inputText("cssSelector", calendarName, calendarType);
		inputTextClear("cssSelector", calendarColor);
		inputText("cssSelector", calendarColor, calendarColor_Txt);
		clickOnElement("cssSelector", calendarName);
		clickOnElement("cssSelector", createCalendarTypeBtn);
		confirmationMessage("CalendarType created successfully.");
	}

	public void updateYourCalendar(String calendarType) throws  Exception {
		logInfo("Inside updateYourCalendar Method");
		waitOnElement("cssSelector", calendarName);
		Thread.sleep(10000);
		String calTypeHeader = driver().findElement(By.xpath("//*[@id='label-for-create_new_calendar']")).getText();
		System.out.println(calTypeHeader);
		inputTextClear("cssSelector", calendarName);
		inputText("cssSelector", calendarName, calendarType);
		inputTextClear("cssSelector", calendarColor);
		inputText("cssSelector", calendarColor, calendarColor_Txt);
		clickOnElement("cssSelector", calendarName);
		clickOnElement("cssSelector", createCalendarTypeBtn);
		confirmationMessage("CalendarType updated successfully.");
		
	}

	public boolean verifyCalendarType(String calendarType) throws  Exception {
		logInfo("Inside verifyCalendarType Method");
		boolean isCalendarTypeFound = false;
		clickOnLink("linkText", "Your Calendars");
		waitOnElement("cssSelector", calendarsTypeList);
		WebElement calendarList = driver().findElement(org.openqa.selenium.By.cssSelector(calendarsTypeList));
		List<WebElement> allTypes = calendarList.findElements(By.tagName("li"));
		int totalCalendars = allTypes.size();

		System.out.println(totalCalendars + "is the size");
		String ycPart1="//*[starts-with(@id,'calendar_li_')][";
		String ycPart2="]";

		for (int i = 2; i <= totalCalendars; i++) {
			WebElement calTyp = driver().findElement(By.xpath(ycPart1 + i + ycPart2));
			String actCalendarType = calTyp.getText().trim();
			if (actCalendarType.equalsIgnoreCase(calendarType)) {
				System.out.println(actCalendarType);
				isCalendarTypeFound = true;
				logInfo(isCalendarTypeFound + "is found in the your calendars list");
				break;
			}

		}
		if (isCalendarTypeFound == false) {
			logInfo(isCalendarTypeFound + "is not found in the your calendars list");
			Assert.assertTrue(isCalendarTypeFound, "is not found in the your calendars list");

		}
		return isCalendarTypeFound;

	}

	public void editCalendarType(String calendarType) throws  Exception {
		logInfo("Inside editCalendarType Method");
		boolean isCalendarTypeFound = false;
		clickOnLink("linkText", "Your Calendars");
		waitOnElement("cssSelector", calendarsTypeList);
		WebElement calendarList = driver().findElement(org.openqa.selenium.By.cssSelector(calendarsTypeList));
		List<WebElement> allTypes = calendarList.findElements(By.tagName("li"));
		int totalCalendars = allTypes.size();
		System.out.println(totalCalendars + "is the size");
		String ycPart1="//*[starts-with(@id,'calendar_li_')][";
		String ycPart2="]";
		String edit = "/div/a[1]/i";
		
		for (int i = 2; i <= totalCalendars; i++) {
			WebElement calTyp = driver().findElement(By.xpath(ycPart1 + i + ycPart2));
			String actCalendarType = calTyp.getText().trim();
			if (actCalendarType.equalsIgnoreCase(calendarType)) {
				System.out.println(actCalendarType);
				WebElement mainMenuBTN=driver().findElement(By.xpath(ycPart1+i+ycPart2));
				hoverOnElement("xpath", ycPart1+i+ycPart2);
			//	scrollDown("xpath",ycPart1+i+ycPart2+"/div");
				Actions builder = new Actions(driver());
				builder.moveToElement(mainMenuBTN).build().perform();
				builder.click();
				WebElement subMenuBTN = driver().findElement(By.xpath( ycPart1+i+ycPart2+edit));
				subMenuBTN.click();
				isCalendarTypeFound = true;
				break;
			}
		
		}
		if (isCalendarTypeFound == false) {
			logInfo(isCalendarTypeFound + "is not found in the your calendars list");
			Assert.assertTrue(isCalendarTypeFound, "is not found in the your calendars list");

		}

	}
	
	public void deleteCalendarType(String calendarType) throws  Exception {
		logInfo("Inside deleteCalendarType Method");
		boolean isCalendarTypeFound = false;
		clickOnLink("linkText", "Your Calendars");
		waitOnElement("cssSelector", calendarsTypeList);
		WebElement calendarList = driver().findElement(org.openqa.selenium.By.cssSelector(calendarsTypeList));
		List<WebElement> allTypes = calendarList.findElements(By.tagName("li"));
		int totalCalendars = allTypes.size();
		System.out.println(totalCalendars + "is the size");
		String ycPart1="//*[starts-with(@id,'calendar_li_')][";
		String ycPart2="]";
		String delete = "/div/a[2]/i";
		
		for (int i = 2; i <= totalCalendars; i++) {
			WebElement calTyp = driver().findElement(By.xpath(ycPart1 + i + ycPart2));
			String actCalendarType = calTyp.getText().trim();
			if (actCalendarType.equalsIgnoreCase(calendarType)) {
				System.out.println(actCalendarType);
				WebElement mainMenuBTN=driver().findElement(By.xpath(ycPart1+i+ycPart2));
				hoverOnElement("xpath", ycPart1+i+ycPart2);
				WebElement subMenuBTN = driver().findElement(By.xpath( ycPart1+i+ycPart2+delete));
				subMenuBTN.click();
				confirmToOk();
				isCalendarTypeFound = true;
			//	confirmationMessage(calendarType +"calendar type deleted successfully.");
				break;
			}
		
		}
		if (isCalendarTypeFound == false) {
			logInfo(isCalendarTypeFound + "is not found in the your calendars list");
			Assert.assertTrue(isCalendarTypeFound, "is not found in the your calendars list");

		}

	}

	public void selectCalendarType(String calendarType) throws  Exception {
		logInfo("Inside selectCalendarType Method");
		boolean isCalendarTypeFound = false;
		clickOnLink("linkText", "Your Calendars");
		waitOnElement("cssSelector", calendarsTypeList);
		WebElement calendarList = driver().findElement(org.openqa.selenium.By.cssSelector(calendarsTypeList));
		List<WebElement> allTypes = calendarList.findElements(By.tagName("li"));
		int totalCalendars = allTypes.size();
		System.out.println(totalCalendars + "is the size");
		String ycPart1="//*[starts-with(@id,'calendar_li_')][";
		String ycPart2="]";
		String checkBx = "/input";
		
		for (int i = 2; i <= totalCalendars; i++) {
			WebElement calTyp = driver().findElement(By.xpath(ycPart1 + i + ycPart2));
			String actCalendarType = calTyp.getText().trim();
			if (actCalendarType.equalsIgnoreCase(calendarType)) {
				System.out.println(actCalendarType);
				WebElement subMenuBTN = driver().findElement(By.xpath( ycPart1+i+ycPart2+checkBx));
				if (!subMenuBTN.isSelected()) {
					System.out.println("checkbox is toggled on");
					subMenuBTN.click();
					waitOnSpinner();
				}
				isCalendarTypeFound = true;
				break;
			}

		}
		if (isCalendarTypeFound == false) {
			logInfo(isCalendarTypeFound + "is not found in the your calendars list");
			Assert.assertTrue(isCalendarTypeFound, "is not found in the your calendars list");

		}

	}

	
	public void deselectCalendarType(String calendarType) throws  Exception {
		logInfo("Inside deselectCalendarType Method");
		boolean isCalendarTypeFound = false;
		clickOnLink("linkText", "Your Calendars");
		waitOnElement("cssSelector", calendarsTypeList);
		WebElement calendarList = driver().findElement(org.openqa.selenium.By.cssSelector(calendarsTypeList));
		List<WebElement> allTypes = calendarList.findElements(By.tagName("li"));
		int totalCalendars = allTypes.size();
		System.out.println(totalCalendars + "is the size");
		String ycPart1="//*[starts-with(@id,'calendar_li_')][";
		String ycPart2="]";
		String checkBx = "/input";
		
		for (int i = 2; i <= totalCalendars; i++) {
			WebElement calTyp = driver().findElement(By.xpath(ycPart1 + i + ycPart2));
			String actCalendarType = calTyp.getText().trim();
			if (actCalendarType.equalsIgnoreCase(calendarType)) {
				System.out.println(actCalendarType);
				WebElement subMenuBTN = driver().findElement(By.xpath( ycPart1+i+ycPart2+checkBx));
				if (subMenuBTN.isSelected()) {
					System.out.println("checkbox is toggled off");
					subMenuBTN.click();
					waitOnSpinner();
					Thread.sleep(20000);
				}
				isCalendarTypeFound = true;
				break;
			}

		}
		if (isCalendarTypeFound == false) {
			logInfo(isCalendarTypeFound + "is not found in the your calendars list");
			Assert.assertTrue(isCalendarTypeFound, "is not found in the your calendars list");

		}

	}

	public void createTaskFromCalendar(String taskName) throws  Exception {
		logInfo("Inside createTaskFromCalendar Method");
		waitOnElement("xpath", calendarTaskBtn);
		clickOnLink("xpath", calendarTaskBtn);
		waitOnElement("xpath", taskTitle);
		inputTextClear("xpath", taskTitle);
		inputText("xpath", taskTitle, taskName);
		inputTextClear("cssSelector", "#pyr_core_user_task_due_date");
		inputText("cssSelector", "#pyr_core_user_task_due_date", TestBase.getSystemDate());
		waitOnElement("cssSelector", priority);
		selectFromDropdown("cssSelector", priority, "byVisibleText", "Medium");
		clickOnButton("xpath", calendarTasksCreateBtn);
		confirmationMessage("Your task has been created.");
		waitOnSpinner();

	}

	public void editTaskFromCalendar(String taskName) throws  Exception {
		logInfo("Inside editTaskFromCalendar Method");
		waitOnSpinner();
		waitOnElement("xpath", calendarTasksEditBtn);
		clickOnElement("xpath", calendarTasksEditBtn);
		waitOnElement("xpath", taskTitle);
		inputTextClear("xpath", taskTitle);
		inputText("xpath", taskTitle, taskName);
		waitOnElement("cssSelector", priority);
		selectFromDropdown("cssSelector", priority, "byVisibleText", "Low");
		clickOnButton("xpath", calendarTasksUpdateBtn);
		confirmationMessage("Your task has been updated.");

	}

	public void deleteTaskFromCalendar(String taskName) throws  Exception {
		logInfo("Inside deleteTaskFromCalendar Method");
		waitOnElement("xpath", calendarTasksDelBtn);
		clickOnElement("xpath", calendarTasksDelBtn);
		confirmOK();
		confirmationMessage("Your task has been deleted.");

	}

	public void markTaskAsComplete(String taskName) throws  Exception {
		logInfo("inside markTaskAsComplete() Method.");
		waitOnElement("cssSelector",
				"div.panel-title > ul.panel-nav > li.btn-group.pull-right > label.btn.btn-default");
		clickOnElement("cssSelector",
				"div.panel-title > ul.panel-nav > li.btn-group.pull-right > label.btn.btn-default");
		waitOnElement("xpath", "//*[@id='tasks_completed']");
		logInfo("clicked on Mark As Complete button");
		confirmationMessage("Task has been marked complete");
		// driver().navigate().refresh();
		selectTaskPresentInCalendarDayView(taskName);
		waitOnElement("cssSelector",
				"div.panel-title > ul.panel-nav > li.btn-group.pull-right > label.btn.btn-default");
		String expectedText = driver()
				.findElement(By.cssSelector(
						"div.panel-title > ul.panel-nav > li.btn-group.pull-right > label.btn.btn-default"))
				.getText().trim();
		Assert.assertEquals(expectedText, "Mark As Incomplete");
	}

	public void markTaskAsInComplete(String taskName) throws  Exception {
		logInfo("inside markTaskAsInComplete() Method.");
		waitOnElement("cssSelector",
				"div.panel-title > ul.panel-nav > li.btn-group.pull-right > label.btn.btn-default");
		clickOnElement("cssSelector",
				"div.panel-title > ul.panel-nav > li.btn-group.pull-right > label.btn.btn-default");
		waitOnElement("xpath", "//*[@id='tasks_incomplete']");
		logInfo("clicked on Mark As Incomplete button");
		confirmationMessage("Task has been marked incomplete");
		selectTaskPresentInCalendarDayView(taskName);
		waitOnElement("cssSelector",
				"div.panel-title > ul.panel-nav > li.btn-group.pull-right > label.btn.btn-default");
		String expectedText = driver()
				.findElement(By.cssSelector(
						"div.panel-title > ul.panel-nav > li.btn-group.pull-right > label.btn.btn-default"))
				.getText().trim();
		Assert.assertEquals(expectedText, "Mark As Complete");
	}

	public boolean verifyTaskPresentinCalendarDay(String taskName) throws  Exception {
		logInfo("inside verifyTaskPresentinCalendarDay() Method.");
		boolean isTaskFound = false;
		List<WebElement> tasksList = driver().findElements(By.xpath("//*[@id='user_tasks_on_date']/li"));
		int totaltasks = tasksList.size();
		System.out.println(totaltasks + "is the size");
		String part1 = "//*[@id='user_tasks_on_date']/li[";
		String part2 = "]/a";
		for (int i = 1; i <= totaltasks; i++) {
			String expectedtaskName = driver().findElement(By.xpath(part1 + i + part2)).getText().trim();
			if (expectedtaskName.equalsIgnoreCase(taskName)) {
				System.out.println(expectedtaskName);
				isTaskFound = true;
				logInfo(isTaskFound + "is found in the your calendars Tasks ");
				break;
			}

		}
		if (isTaskFound == false) {
			logInfo(isTaskFound + "is not found in the your calendars Tasks");
			Assert.assertTrue(isTaskFound, taskName + "is not found in the your calendars Tasks");
		}
		return isTaskFound;

	}

	public boolean verifyTaskNotPresentinCalendarDay(String taskName) throws  Exception {
		logInfo("inside verifyTaskNotPresentinCalendarDay() Method.");
		boolean isTaskFound = false;
		boolean isMessageFound = verifyElementPresent("xpath", "//*[@id='user_tasks_on_date']/li/label");
		if (isMessageFound) {
			isTaskFound = false;
			String actualText = driver().findElement(By.xpath("//*[@id='user_tasks_on_date']/li/label")).getText()
					.trim();
			Assert.assertEquals("No Tasks Found", actualText);

		} else {
			List<WebElement> tasksList = driver().findElements(By.xpath("//*[@id='user_tasks_on_date']/li"));
			int totaltasks = tasksList.size();
			System.out.println(totaltasks + "is the size");
			String part1 = ".//*[@id='user_tasks_on_date']/li[";
			String part2 = "]/a";
			for (int i = 1; i <= totaltasks; i++) {
				String expectedtaskName = driver().findElement(By.xpath(part1 + i + part2)).getText().trim();
				if (expectedtaskName.equalsIgnoreCase(taskName)) {
					System.out.println(expectedtaskName);
					isTaskFound = false;
					Assert.assertTrue(isTaskFound, eventName + " is  available in Calender list view.");

				}
				break;

			}
		}
		return isTaskFound;

	}

	public void selectTaskPresentInCalendarDayView(String taskName) throws  Exception {
		logInfo("inside selectTaskPresentInCalendarDayView() Method.");
		boolean isTaskFound = false;
		List<WebElement> tasksList = driver().findElements(By.xpath("//*[@id='user_tasks_on_date']/li"));
		int totaltasks = tasksList.size();
		System.out.println(totaltasks + "is the size");
		String part1 = ".//*[@id='user_tasks_on_date']/li[";
		String part2 = "]/a";
		for (int i = 1; i <= totaltasks; i++) {
			WebElement taskNa = driver().findElement(By.xpath(part1 + i + part2));
			String expectedtaskName = taskNa.getText().trim();
			if (expectedtaskName.equalsIgnoreCase(taskName)) {
				System.out.println(expectedtaskName);
				isTaskFound = true;
				taskNa.click();
				waitOnElement("xpath", "//*[@id='spinner-container'][@display='none']");
				break;
			}
		}
		if (isTaskFound == false) {
			logInfo(taskName + " task not found in ");
		}
	}

	
	public void createCalendarEventFromDayView(String time, String eventName) throws  Exception {
		logInfo("inside createCalendarEventFromDayView Method");
		boolean isTimeFound = false;
		waitOnElement("xpath", calendarDayTime);
		List<WebElement> times = driver().findElements(By.xpath(calendarDayTime));
		int timeList = times.size();
		for (int i = 1; i <= timeList; i += 2) {
			String part1 = "//*[@id='calendar']/div/div/table/tbody/tr/td/div[2]/div/div[2]/table/tbody/tr[";
			String part2 = "]/td[1]/span";
			String part3 = "]/td[2]";
			String expectedTime = driver().findElement(By.xpath(part1 + i + part2)).getText().trim();
			System.out.println(expectedTime + " is to select");
			if (expectedTime.equalsIgnoreCase(time)) {
				isTimeFound = true;
				waitOnElement("xpath", part1 + i + part3);
				clickOnElement("xpath", part1 + i + part3);
				waitOnSpinner();
				break;
			}

		}
		waitOnElement("xpath", inputEventName);
		inputTextClear("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", prop.getLocatorForEnvironment(appUrl, "calendarType"));
		waitOnSpinner();
		waitOnElement("xpath", inputEventEndTime);
		inputTextClear("xpath", inputEventEndTime);
		inputText("xpath", inputEventEndTime, "04:00 AM");
		waitOnSpinner();
		waitOnElement("xpath", btnEventSave);
		clickOnButton("xpath", btnEventSave);
		waitOnSpinner();
		waitOnSpinner();
		if (isTimeFound == false) {
			logInfo(time + "  not found in day calendar view.");
			Assert.assertTrue(isTimeFound, time + "  not found in day calendar view.");

		}
	}

	
	public void createCurrentDayCalendarEventFromMonthView(String eventName) throws  Exception {
		logInfo("inside createCalendarEventFromDayView Method");
		selectCalendarView("Month");
		boolean isDayFound = verifyElementPresent("xpath",
				"//*[@id='calendar']/div/div/table/tbody/tr/td/div/div/div/div[2]/table/thead/tr/td[contains(@class,'fc-today')]");
		if (isDayFound) {
			clickOnElement("xpath","//*[@id='calendar']/div/div/table/tbody/tr/td/div/div/div/div[2]/table/thead/tr/td[contains(@class,'fc-today')]");
			waitOnElement("xpath", inputEventName);
			inputTextClear("xpath", inputEventName);
			inputText("xpath", inputEventName, eventName);
			selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);
			selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", calendarType_text);
			waitOnElement("xpath", btnEventSave);
			clickOnButton("xpath", btnEventSave);
			Thread.sleep(5000);
		} else {
			logInfo("Current date is  not found in  calendar Month view.");
			Assert.assertTrue(isDayFound, "  Current date is  not found in calendar Month view");

		}

	}

	public void createCalendarEventFromListView(String eventName) throws  Exception {
		logInfo("inside createCalendarEventFromListView Method");
		selectCalendarView("List");
		boolean isButtonFound = verifyElementPresent("cssSelector", addEventListBtn);
		if (isButtonFound) {
			clickOnElement("cssSelector", addEventListBtn);
			createQuickEvent(eventName,quickEventStartdate_text, quickEventEnddate_text);
		
			go2CalendarPage();
		} else {
			logInfo("Add Event button is not found in list view.");
			Assert.assertTrue(isButtonFound, "  Add Event button is not found in list view.");

		}

	}

	public void back2CalendarListView() throws Exception {
		logInfo("inside back2CalendarListView Method");
		verifyElementPresent("xpath", back2CalListBtn);
		clickOnButton("xpath", back2CalListBtn);

	}
	public boolean verifyCalendarEventInDayView(String eventName) throws Exception {
		logInfo("inside verifyCalendarEventInDayView() Method.");
		boolean isDayEventMatch = false;
		searchQuery(eventName);
		waitOnElement("xpath", calendarViewPane);
		List<WebElement> dayEvent = driver().findElements(By.cssSelector(calDayContainerLnk));
		System.out.println("dayEvents" + dayEvent.size()+" is the size");
		for (int i = 1; i <= dayEvent.size(); i++) {
			System.out.println("insdie sadasd");
			logInfo("insdie sadasdasdasdsa");
			String aElement = driver().findElement(By.cssSelector(calDayPart1 + i + calDayPart2)).getText().trim();
				System.out.println(aElement);
				if (aElement.contains(eventName)) {
					logInfo(eventName + " event name matching");
					isDayEventMatch = true;
					break;
				}
			}
		if (isDayEventMatch == false) {
			logInfo(eventName + " calendar event match not found in day calendar view.");
			Assert.assertTrue(isDayEventMatch, eventName + " calendar event match not found in day calendar view.");
		}
		return isDayEventMatch;
	}
	public boolean verifyCalendarEventNotPresnetInDayView(String eventName) throws Exception {
		logInfo("inside verifyCalendarEventNotPresnetInDayView() Method.");
		waitOnElement("xpath", calendarViewPane);
		List<WebElement> dayEvent = driver().findElements(By.cssSelector("div.fc-title"));
		System.out.println("dayEvents" + dayEvent.size());
		boolean isDayEventMatch = false;
		for (WebElement events : dayEvent) {
			System.out.println(events.getText());
			String dayeventName = events.getText().trim();
			if (dayeventName.equalsIgnoreCase(eventName)) {
				isDayEventMatch = false;
				Assert.assertTrue(isDayEventMatch, eventName + " calendar event match  found in day calendar view.");
				break;
			}
		}
		return isDayEventMatch;
	}

	public boolean verifyCalendarEventInWeekView(String eventName) throws Exception {
		logInfo("inside verifyCalendarEventInWeekView Method");
		selectCalendarView("Week");
		searchQuery(eventName);
		waitOnElement("cssSelector", ".fc-content>span.fc-title");
		WebElement table = driver().findElement(By.xpath(calendarWeekViewPane));
		boolean isWeekEventMatch = false;
		List alltds = table.findElements(By.tagName("td"));
		int totalDays = alltds.size();
		logInfo("Total days = " + totalDays);
		for (int i = 2; i <= totalDays; i++) {
			String a_before = "//*[@id='calendar']/div/div/table/tbody/tr/td/div[2]/div/div[4]/table/tbody/tr/td[";
			String a_mid = "]/div";
			WebElement aElement = driver().findElement(By.xpath(a_before + i + a_mid));
			List<WebElement> allLinks = aElement.findElements(By.tagName("a"));
			for (WebElement l : allLinks) {
				String linkName = l.getText().trim();
				System.out.println(linkName);
				if (linkName.contains(eventName)) {
					logInfo(eventName + " event name matching in the week view");
					isWeekEventMatch = true;
					break;
				}
			}
		}
		if (isWeekEventMatch = false) {
			logInfo(eventName + " event name not matching in the week view");
			Assert.assertTrue(isWeekEventMatch, eventName + " event name not matching in the week view");
		}
		return isWeekEventMatch;
	}

	public boolean verifyCalendarEventNotPresentInWeekView(String eventName) throws Exception {
		logInfo("inside verifyCalendarEventNotPresentInWeekView Method");
		selectCalendarView("Week");
		WebElement table = driver().findElement(By.xpath(calendarWeekViewPane));
		boolean isWeekEventMatch = false;
		List alltds = table.findElements(By.tagName("td"));
		int totalDays = alltds.size();
		logInfo("Total days = " + totalDays);
		for (int i = 2; i <= totalDays; i++) {
			String a_before = "//*[@id='calendar']/div/div/table/tbody/tr/td/div[2]/div/div[4]/table/tbody/tr/td[";
			String a_mid = "]/div";
			WebElement aElement = driver().findElement(By.xpath(a_before + i + a_mid));
			List<WebElement> allLinks = aElement.findElements(By.tagName("a"));
			for (WebElement l : allLinks) {
				String linkName = l.getText().trim();
				System.out.println(linkName);
				if (linkName.contains(eventName)) {
					logInfo(eventName + " event name matching in the week view");
					isWeekEventMatch = false;
					Assert.assertTrue(isWeekEventMatch, eventName + " event name not matching in the week view");
					break;
				}
			}
		}

		return isWeekEventMatch;
	}

	public void searchQuery(String eventName) throws  Exception {
		logInfo("inside searchQuery() Method.");
		waitOnElement("xpath", inputEventQuery);
		inputTextClear("xpath", inputEventQuery);
		inputText("xpath", inputEventQuery, eventName);
		waitOnElement("cssSelector", calsearchBtn);
		clickOnElement("cssSelector", calsearchBtn);
	//	waitOnSpinner();
	}

	public void selectCalendarEventInDayView(String eventName) throws Exception {
		logInfo("inside verifyCalendarEventInDayView() Method.");
		searchQuery(eventName);
		boolean isDayEventMatch = false;
		waitOnElement("xpath", calendarViewPane);
		List<WebElement> dayEvent = driver().findElements(By.cssSelector("div.fc-content>div.fc-title"));
		System.out.println("dayEvents" + dayEvent.size());
		waitOnSpinner();
		for (WebElement events : dayEvent) {
			System.out.println(events.getText().trim() +"is everew");
			String dayeventName = events.getText().trim();
			if (dayeventName.equalsIgnoreCase(eventName)) {
				isDayEventMatch = true;
				clickOnElement("cssSelector", "div.fc-time");
				waitOnSpinner();
				logInfo(eventName + " calendar event match found in day calendar view.");
				break;
			}
		}
		if (isDayEventMatch == false) {
			logInfo(eventName + " calendar event match not found in day calendar view.");
			Assert.assertTrue(isDayEventMatch, eventName + " calendar event match not found in day calendar view.");
		}
	}

	public void selectCalendarEventInWeekView(String eventName) throws  Exception {

		logInfo("inside selectCalendarEventInWeekView Method");
		selectCalendarView("Week");
		// waitOnElement("xpath", calendarWeekViewPane);
		searchQuery(eventName);
		waitOnElement("xpath", calendarWeekViewPane);
		WebElement table = driver().findElement(By.xpath(calendarWeekViewPane));
		boolean isWeekEventMatch = false;
		List alltds = table.findElements(By.tagName("td"));
		int totalDays = alltds.size();
		logInfo("Total days = " + totalDays);
		for (int i = 2; i <= totalDays; i++) {
			String a_before = "//*[@id='calendar']/div/div/table/tbody/tr/td/div[2]/div/div[4]/table/tbody/tr/td[";
			String a_mid = "]/div";
			String a_end = "/a";
			WebElement aElement = driver().findElement(By.xpath(a_before + i + a_mid));
			List<WebElement> allLinks = aElement.findElements(By.tagName("a"));
			for (WebElement l : allLinks) {
				String linkName = l.getText().trim();
				System.out.println(linkName);
				if (linkName.contains(eventName)) {
					logInfo(eventName + " event name matching in the week view");
					isWeekEventMatch = true;
					l.click();
					waitOnSpinner();
					break;
				}
			}
		}
		if (isWeekEventMatch = false) {
			logInfo(eventName + " event name not matching in the week view");
			Assert.assertTrue(isWeekEventMatch, eventName + " event name not matching in the week view");
		}
	}

	public void viewEvent(String EventName) throws  Exception {
		logInfo("inside viewEvent() Method.");
		waitOnElement("cssSelector", calendarviewBtn);
		clickOnElement("cssSelector", calendarviewBtn);
		String expected_Text = driver().findElement(By.cssSelector(eventNameTxt)).getText().trim();
		System.out.println(expected_Text);
		Assert.assertEquals(EventName, expected_Text);

	}

	public void shareEvent() throws  Exception {
		logInfo("inside viewEvent() Method.");
		waitOnElement("cssSelector", calendarshareBtn);
		clickOnElement("cssSelector", calendarshareBtn);

	}

	public void deleteEvent() throws  Exception {
		logInfo("inside deleteEvent() Method.");
		waitOnElement("cssSelector", calendardeleteBtn);
		clickOnElement("cssSelector", calendardeleteBtn);
		confirmOK();
		}

	public void deleteRepeatEvent(String btnName) throws  Exception {
		logInfo("inside deleteRepeatEvent() Method.");
		switch (btnName) {
		case "Delete only this instance":
			waitOnElement("xpath", btnDeleteSingleInstance);
			clickOnElement("xpath", btnDeleteSingleInstance);
			confirmOK();
		//	confirmationMessage("Your event has been deleted.");
			break;
		case "All events in this series":
			waitOnElement("xpath", btnDeleteAllInstances);
			clickOnElement("xpath", btnDeleteAllInstances);
			confirmOK();
		//	confirmationMessage("Your event has been deleted.");
			break;
		default:
			logInfo("unable to delete");
			break;
		}

	}

	public void selectCalendarView(String toolItem) throws  Exception {
		logInfo("inside selectCalendarView() Method.");
		boolean isviewFound = false;
		waitOnElement("cssSelector", calViewType);
		List<WebElement> tb = driver().findElements(By.cssSelector(caltoolBar));
		for (WebElement toolbar : tb) {
			String tools = toolbar.getText();
			if (tools.trim().equalsIgnoreCase(toolItem)) {
				isviewFound = true;
				toolbar.click();
				Thread.sleep(3000);
				//waitOnSpinner();
				break;
			}
		}
		if (isviewFound == false) {
			logInfo(toolItem + " calendar view type not found to switch views ");
			Assert.assertTrue(isviewFound, toolItem + " calendar view type not found to switch views");
		}

	}

	public boolean verifyEventByFilterTypes(String eventName) throws  Exception {
		logInfo("inside verifyEventByFilterTypes() Method...");
		selectCalendarView("List");
		boolean isVerified = false;
		String[] options = { "Show 1 Day", "Show 1 Week", "Show 1 Month" };
		selectCalendarView("List");
		waitOnElement("xpath", drpdwnEventFilters);
		clickOnElement("xpath", drpdwnEventFilters);

		List<WebElement> el = driver().findElements(By.xpath("//*[@id='filter_by_type_container']/ul/li"));
		String before = "//*[@id='filter_by_type_container']/ul/li[";
		String after = "]/a";
		int j = 0;

		for (int i = 1; i <= el.size(); i++) {
			WebElement e = driver().findElement(By.xpath(before + i + after));
			if (e.getText().trim().contains(options[j])) {
				/* Thread.sleep(5000); */
				isVerified = verifyEventInListView(eventName);
				j++;
			}

		}
		return isVerified;
	}

	public void selectFilter(String filterName) throws  Exception {
		logInfo("inside selectFilter() Method...");
		boolean isFilterFound = false;
		waitOnElement("xpath", "//*[@id='filter_by_type_container']/button");
		clickOnElement("xpath", "//*[@id='filter_by_type_container']/button");
		waitOnElement("xpath", "//*[@id='filter_by_type_container']/ul/li");
		WebElement FilterType = driver().findElement(By.xpath("//*[@id='filter_by_type_container']/ul"));
		List<WebElement> filters = driver().findElements(By.tagName("li"));
		String part1 = "//*[@id='filter_by_type_container']/ul/li[";
		String part2 = "]";
		int filtersList = filters.size();
		for (int i = 1; i <= filtersList; i++) {
			String expectedFilter = driver().findElement(By.xpath(part1 + i + part2)).getText().trim();
			if (expectedFilter.equalsIgnoreCase(filterName)) {
				isFilterFound = true;
				clickOnElement("xpath", part1 + i + part2);
				waitOnElement("xpath", "//*[@id='spinner-container'][@display='none']");
				break;
			}
		}
		if (isFilterFound == false) {
			logInfo(filterName + " calendar Filter type not found ");
			Assert.assertTrue(isFilterFound, filterName + " calendar Filter type not found");
		}

	}

	public void selectEventType(String type) throws  Exception {
		logInfo("inside selectEventType() Method...");
		boolean isEventTypeFound = false;
		waitOnElement("xpath", drpdwnEventTypeBtn);
		clickOnElement("xpath", drpdwnEventTypeBtn);
		waitOnElement("xpath", "//*[@id='events_by_type_container']/ul/li");
		List<WebElement> events = driver().findElements(By.xpath("//*[@id='events_by_type_container']/ul/li"));
		String part1 = "//*[@id='events_by_type_container']/ul/li[";
		String part2 = "]/a";
		int filtersList = events.size();
		for (int i = 3; i <= filtersList; i++) {
			String expectedFilter = driver().findElement(By.xpath(part1 + i + part2)).getText().trim();
			if (expectedFilter.equalsIgnoreCase(type)) {
				isEventTypeFound = true;
				clickOnElement("xpath", part1 + i + part2);
				Thread.sleep(5000);
				break;
			}
		}
		if (isEventTypeFound == false) {
			logInfo(type + " calendar Event type not found in the dropdown");
			Assert.assertTrue(isEventTypeFound, type + " calendar Filter type not found in the dropdown");
		}

	}

	public void postCommentInEventDetailsPage(String message) throws  Exception {
		logInfo(" inside postCommentInEventDetailsPage Method");
		waitOnElement("xpath", eventCommentTxtBx);
		inputTextClear("xpath", eventCommentTxtBx);
		inputText("xpath", eventCommentTxtBx, message);
		clickOnElement("xpath", eventCommentTxtBx);
		driver().findElement(By.xpath(eventCommentTxtBx)).sendKeys(Keys.ENTER);
	}

	public boolean verifyCommentPresentForAPostInEventDetailsPage(String message) throws InterruptedException {
		logInfo(" inside verifyCommentPresentForAPostInEventDetailsPage Method");
		boolean isCommentFound = false;
		WebElement eventPart = driver().findElement(By.cssSelector(eventDet_xp1));
		List<WebElement> events = eventPart.findElements(By.cssSelector(".activity_content"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String part2 = "]/div/div[1]";
		int filtersList = events.size();
		for (int i = filtersList; i <= 1; i--) {
			String expectedFilter = driver().findElement(By.xpath(part1 + i + part2)).getText().trim();
			System.out.println(expectedFilter);
			if (expectedFilter.equalsIgnoreCase(message)) {
				isCommentFound = true;
				break;
			}
		}
	 return isCommentFound;

	}

	public boolean verifyPostedCommentInEventDetailsPage(String message) throws InterruptedException {
		logInfo(" inside verifyPostedCommentInEventDetailsPage Method");
		boolean isCommentFound = false;
		WebElement eventPart = driver()
				.findElement(By.xpath("//div[@class='row comment-post']/div[@class='media']/div[2]"));
		List<WebElement> events = eventPart.findElements(By.className("media"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String part2 = "]/div/div[4]/div[2]/div[1]/div[2]/p";
		int filtersList = events.size();
		for (int i = 1; i <= filtersList; i++) {
			String expectedFilter = driver().findElement(By.xpath(part1 + i + part2)).getText().trim();
			System.out.println(expectedFilter);
			if (expectedFilter.equalsIgnoreCase(message)) {
				isCommentFound = true;
				break;
			}
		}
		if (isCommentFound == false) {
			logInfo(message + " comment added is  not found in the dropdown");
			Assert.assertTrue(isCommentFound, message + " comment added is  not found in the dropdown");
		}
		return isCommentFound;

	}

	public boolean verifyPostedPhotoInEventDetailsPage() throws InterruptedException {
		logInfo(" inside verifyPostedPhotoInEventDetailsPage Method");
		boolean isphototFound = false;
		WebElement eventPart = driver().findElement(By.cssSelector(eventDet_xp1));
		List<WebElement> events = eventPart.findElements(By.className("media"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String activityMain = "]/div/div[1]";
		String part2 = "/p/img";
		int filtersList = events.size();
		for (int i = 1; i <= filtersList; i++) {
			String imageVal = driver().findElement(By.xpath(part1 + i + activityMain + part2)).getAttribute("src");
			System.out.println(imageVal);
			driver().findElement(By.xpath(part1 + i + activityMain)).getClass();
			if (imageVal.contains(".jpeg")) {
				isphototFound = true;
				System.out.println(isphototFound);
				break;
			}
		}
		if (isphototFound == false) {
			logInfo(" photo added is  not found in the feed");
			Assert.assertTrue(isphototFound, " photo added is  not found in the feed");
		}
		return isphototFound;

	}

	public boolean verifyPostedVideoInEventDetailsPage() throws InterruptedException {
		logInfo(" inside verifyPostedVideoInEventDetailsPage Method");
		boolean isvideoFound = false;
		WebElement eventPart = driver()
				.findElement(By.xpath("//div[@class='row comment-post']/div[@class='media']/div[2]"));
		List<WebElement> events = eventPart.findElements(By.className("media"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String activityMain = "]/div/div[1]/a";
		int filtersList = events.size();

		for (int i = 1; i <= filtersList; i++) {
			String imageVal = driver().findElement(By.xpath(part1 + i + activityMain)).getAttribute("href");
			System.out.println(imageVal);
			if (imageVal.contains("http")) {
				isvideoFound = true;
				System.out.println(isvideoFound);
				break;
			}
		}
		if (isvideoFound == false) {
			logInfo(" photo added is  not found in the feed");
			Assert.assertTrue(isvideoFound, " photo added is  not found in the feed");
		}
		return isvideoFound;

	}

	public void likeInEventDetailsPage(String message) throws  Exception {
		logInfo(" inside likeInEventDetailsPage Method");

		WebElement eventPart = driver().findElement(By.cssSelector(eventDet_xp1));
		List<WebElement> events = eventPart.findElements(By.cssSelector(".activity_content"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String activityMain = "]/div/div[1]";
		String part2 = "]/div/div[2]";
		String likeLnk = "/div[1]/span[1]/a";
		String likeCnt = "/div[2]/span[1]";
		int filtersList = events.size();
		for (int i = 1; i <= filtersList; i++) {
			String expectedFilter = driver().findElement(By.xpath(part1 + i + activityMain)).getText().trim();
			if (expectedFilter.equalsIgnoreCase(message)) {
				WebElement like = driver().findElement(By.xpath(part1 + i + part2 + likeCnt));
				String likeCount = like.getText().trim();
				int likecount = Integer.parseInt(likeCount);
				logInfo("old like count = " + likecount);
				System.out.println("old like count = " + likecount);
				String likeTxt = driver().findElement(By.xpath(part1 + i + part2 + likeLnk)).getText().trim();
				System.out.println("liketext  " + likeTxt);
				if (likeTxt.equalsIgnoreCase("Like")) {
					scrollDown("xpath", part1 + i + part2 + likeLnk);
					waitOnElement("xpath", part1 + i + part2 + likeLnk);
					clickOnElement("xpath", part1 + i + part2 + likeLnk);
					Thread.sleep(8000);
					driver().navigate().refresh();
				}
				WebElement newlike = driver().findElement(By.xpath(part1 + i + part2 + likeCnt));
				String NewlikeCount = newlike.getText().trim();
				int newlikecount = Integer.parseInt(NewlikeCount);
				logInfo("new like count = " + newlikecount);
				System.out.println("new like count = " + newlikecount);

				if (newlikecount == likecount + 1) {
					logInfo("Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ").");
					System.out.println(
							"Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ").");
					Assert.assertTrue(newlikecount == likecount + 1,
							"Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ").");
				} else {
					logInfo("Likes(" + likecount + ") could not be incremented to Likes(" + newlikecount + ").");
					System.out.println(
							"Likes(" + likecount + ") could not be incremented to Likes(" + newlikecount + ").");
				}
				Assert.assertTrue(newlikecount == likecount + 1,
						"Likes(" + likecount + ") not incremented sucessfully to Likes(" + newlikecount + ").");
				break;
			}
		}

	}

	public void commentInEventDetailsPage(String message, String commentMessage) throws  Exception {
		logInfo(" inside commentInEventDetailsPage Method");
		WebElement eventPart = driver().findElement(By.cssSelector(eventDet_xp1));
		List<WebElement> events = eventPart.findElements(By.className("media"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String activityMain = "]/div/div[1]";
		String part2 = "]/div/div[2]";
		String commentLnk = "/div[1]/span[2]/a";
		String commentCnt = "/div[2]/span[2]";
		String commentBox = "]/div/div[@class='comments']/div[1]/form/div/div/div/textarea";
		int filtersList = events.size();
		for (int i = 1; i <= filtersList; i++) {
			String expectedFilter = driver().findElement(By.xpath(part1 + i + activityMain)).getText().trim();
			if (expectedFilter.equalsIgnoreCase(message)) {
				WebElement comment = driver().findElement(By.xpath(part1 + i + part2 + commentCnt));
				String commentCount = comment.getText().trim();
				int commentcount = Integer.parseInt(commentCount);
				logInfo("old comment count = " + commentCount);
				System.out.println("old comment count = " + commentCount);
				String commentTxt = driver().findElement(By.xpath(part1 + i + part2 + commentLnk)).getText().trim();
				System.out.println("commenttext  " + commentTxt);
				if (commentTxt.equalsIgnoreCase("Comment")) {
					scrollDown("xpath", part1 + i + part2 + commentLnk);
					waitOnElement("xpath", part1 + i + part2 + commentLnk);
					clickOnElement("xpath", part1 + i + part2 + commentLnk);
					waitOnElement("xpath", part1 + i + commentBox);
					inputTextClear("xpath", part1 + i + commentBox);
					inputText("xpath", part1 + i + commentBox, commentMessage);
					clickOnElement("xpath", part1 + i + commentBox);
					driver().findElement(By.xpath(part1 + i + commentBox)).sendKeys(Keys.ENTER);
				}
				
				Thread.sleep(10000);
				driver().navigate().refresh();
				WebElement newComm = driver().findElement(By.xpath(part1 + i + part2 + commentCnt));
				String NewCommCount = newComm.getText().trim();
				int newCommentcount = Integer.parseInt(NewCommCount);
				logInfo("new comment count = " + newCommentcount);
				System.out.println("new comment count = " + newCommentcount);

				if (newCommentcount == commentcount + 1) {
					logInfo("comments(" + commentcount + ") incremented sucessfully to comments(" + newCommentcount
							+ ").");
					System.out.println("comments(" + commentcount + ") incremented sucessfully to comments("
							+ newCommentcount + ").");
					Assert.assertTrue(newCommentcount == commentcount + 1, "comments" + commentcount
							+ ") incremented sucessfully to comments(" + newCommentcount + ").");
				} else {
					logInfo("comments(" + commentcount + ") could not be incremented to comments(" + newCommentcount
							+ ").");
					System.out.println("comments(" + commentcount + ") could not be incremented to comments("
							+ newCommentcount + ").");
				}
				Assert.assertTrue(newCommentcount == commentcount + 1, "comments(" + commentcount
						+ ") not incremented sucessfully to comments(" + newCommentcount + ").");
				break;
			}
		}

	}

	public void likePhotoPostedInEventDetailsPage() throws  Exception {
		logInfo(" inside likePhotoPostedInEventDetailsPage Method");
		WebElement eventPart = driver()
				.findElement(By.xpath("//div[@class='row comment-post']/div[@class='media']/div[2]"));
		List<WebElement> events = eventPart.findElements(By.className("media"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String activityMain = "]/div/div[1]/p/img";
		String part2 = "]/div/div[2]";
		String likeLnk = "/div[1]/span[1]/a";
		String likeCnt = "/div[2]/span[1]";
		int filtersList = events.size();
		for (int i = 1; i <= filtersList; i++) {
			String imageVal = driver().findElement(By.xpath(part1 + i + activityMain)).getAttribute("src");
			System.out.println(imageVal);
			if (imageVal.contains(".jpeg")) {
				WebElement like = driver().findElement(By.xpath(part1 + i + part2 + likeCnt));
				String likeCount = like.getText().trim();
				int likecount = Integer.parseInt(likeCount);
				logInfo("old like count = " + likecount);
				System.out.println("old like count = " + likecount);
				String likeTxt = driver().findElement(By.xpath(part1 + i + part2 + likeLnk)).getText().trim();
				System.out.println("liketext  " + likeTxt);
				if (likeTxt.equalsIgnoreCase("Like")) {
					scrollDown("xpath", part1 + i + part2 + likeLnk);
					waitOnElement("xpath", part1 + i + part2 + likeLnk);
					clickOnElement("xpath", part1 + i + part2 + likeLnk);
					Thread.sleep(8000);
					driver().navigate().refresh();
				}
				WebElement newlike = driver().findElement(By.xpath(part1 + i + part2 + likeCnt));
				String NewlikeCount = newlike.getText().trim();
				int newlikecount = Integer.parseInt(NewlikeCount);
				logInfo("new like count = " + newlikecount);
				System.out.println("new like count = " + newlikecount);

				if (newlikecount == likecount + 1) {
					logInfo("Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ").");
					System.out.println(
							"Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ").");
					Assert.assertTrue(newlikecount == likecount + 1,
							"Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ").");
				} else {
					logInfo("Likes(" + likecount + ") could not be incremented to Likes(" + newlikecount + ").");
					System.out.println(
							"Likes(" + likecount + ") could not be incremented to Likes(" + newlikecount + ").");
				}
				Assert.assertTrue(newlikecount == likecount + 1,
						"Likes(" + likecount + ") not incremented sucessfully to Likes(" + newlikecount + ").");
				break;
			}
		}

	}

	public void commentForAPhotoInEventDetailsPage(String commentPost) throws  Exception {
		logInfo(" inside commentForAPhotoInEventDetailsPage Method");
		WebElement eventPart = driver()
				.findElement(By.xpath("//div[@class='row comment-post']/div[@class='media']/div[2]"));
		List<WebElement> events = eventPart.findElements(By.className("media"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String activityMain = "]/div/div[1]/p/img";
		String part2 = "]/div/div[2]";
		String commentLnk = "/div[1]/span[2]/a";
		String commentCnt = "/div[2]/span[2]";
		String commentBox = "]/div/div[@class='comments']/div[1]/form";
		int filtersList = events.size();
		for (int i = 1; i <= filtersList; i++) {
			String imageVal = driver().findElement(By.xpath(part1 + i + activityMain)).getAttribute("src");
			System.out.println(imageVal);
			if (imageVal.contains(".jpeg")) {
				WebElement comment = driver().findElement(By.xpath(part1 + i + part2 + commentCnt));
				String commentCount = comment.getText().trim();
				int commentcount = Integer.parseInt(commentCount);
				logInfo("old comment count = " + commentCount);
				System.out.println("old comment count = " + commentCount);
				String commentTxt = driver().findElement(By.xpath(part1 + i + part2 + commentLnk)).getText().trim();
				System.out.println("commenttext  " + commentTxt);
				if (commentTxt.equalsIgnoreCase("Comment")) {
					scrollDown("xpath", part1 + i + part2 + commentLnk);
					waitOnElement("xpath", part1 + i + part2 + commentLnk);
					clickOnElement("xpath", part1 + i + part2 + commentLnk);
					waitOnElement("xpath", part1 + i + commentBox);
					/*inputTextClear("xpath", part1 + i + commentBox);
					inputText("xpath", part1 + i + commentBox, commentPost);
					clickOnElement("xpath", part1 + i + commentBox);
					driver().findElement(By.xpath(part1 + i + commentBox)).sendKeys(Keys.ENTER);*/
					waitOnElement("xpath", eventCommentTxtbx1);
					inputTextClear("xpath",eventCommentTxtbx1);
					inputText("xpath", eventCommentTxtbx1, commentPost);
	
					driver().findElement(By.xpath(eventCommentTxtbx1)).sendKeys(Keys.ENTER);
					Thread.sleep(10000);

				}
				driver().navigate().refresh();
				WebElement newComm = driver().findElement(By.xpath(part1 + i + part2 + commentCnt));
				String NewCommCount = newComm.getText().trim();
				int newCommentcount = Integer.parseInt(NewCommCount);
				logInfo("new comment count = " + newCommentcount);
				System.out.println("new comment count = " + newCommentcount);

				if (newCommentcount == commentcount + 1) {
					logInfo("comments(" + commentcount + ") incremented sucessfully to comments(" + newCommentcount
							+ ").");
					System.out.println("comments(" + commentcount + ") incremented sucessfully to comments("
							+ newCommentcount + ").");
					Assert.assertTrue(newCommentcount == commentcount + 1, "comments" + commentcount
							+ ") incremented sucessfully to comments(" + newCommentcount + ").");
				} else {
					logInfo("comments(" + commentcount + ") could not be incremented to comments(" + newCommentcount
							+ ").");
					System.out.println("comments(" + commentcount + ") could not be incremented to comments("
							+ newCommentcount + ").");
				}
				Assert.assertTrue(newCommentcount == commentcount + 1, "comments(" + commentcount
						+ ") not incremented sucessfully to comments(" + newCommentcount + ").");
				break;
			}
		}

	}

	public void likeAVideoInEventDetailsPage() throws  Exception {
		logInfo(" inside likeAVideoInEventDetailsPage Method");
		WebElement eventPart = driver()
				.findElement(By.xpath("//div[@class='row comment-post']/div[@class='media']/div[2]"));
		List<WebElement> events = eventPart.findElements(By.className("media"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String activityMain = "]/div/div[1]/a";
		String part2 = "]/div/div[2]";
		String likeLnk = "/div[1]/span[1]/a";
		String likeCnt = "/div[2]/span[1]";
		int filtersList = events.size();
		for (int i = 1; i <= filtersList; i++) {
			String imageVal = driver().findElement(By.xpath(part1 + i + activityMain)).getAttribute("href");
			System.out.println(imageVal);
			if (imageVal.contains("http")) {
				WebElement like = driver().findElement(By.xpath(part1 + i + part2 + likeCnt));
				String likeCount = like.getText().trim();
				int likecount = Integer.parseInt(likeCount);
				logInfo("old like count = " + likecount);
				System.out.println("old like count = " + likecount);
				String likeTxt = driver().findElement(By.xpath(part1 + i + part2 + likeLnk)).getText().trim();
				System.out.println("liketext  " + likeTxt);
				if (likeTxt.equalsIgnoreCase("Like")) {
					scrollDown("xpath", part1 + i + part2 + likeLnk);
					waitOnElement("xpath", part1 + i + part2 + likeLnk);
					clickOnElement("xpath", part1 + i + part2 + likeLnk);
					Thread.sleep(80000);
					driver().navigate().refresh();
				}
				WebElement newlike = driver().findElement(By.xpath(part1 + i + part2 + likeCnt));
				String NewlikeCount = newlike.getText().trim();
				int newlikecount = Integer.parseInt(NewlikeCount);
				logInfo("new like count = " + newlikecount);
				System.out.println("new like count = " + newlikecount);

				if (newlikecount == likecount + 1) {
					logInfo("Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ").");
					System.out.println(
							"Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ").");
					Assert.assertTrue(newlikecount == likecount + 1,
							"Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ").");
				} else {
					logInfo("Likes(" + likecount + ") could not be incremented to Likes(" + newlikecount + ").");
					System.out.println(
							"Likes(" + likecount + ") could not be incremented to Likes(" + newlikecount + ").");
				}
				Assert.assertTrue(newlikecount == likecount + 1,
						"Likes(" + likecount + ") not incremented sucessfully to Likes(" + newlikecount + ").");
				break;
			}
		}

	}

	public void commentForAVideoInEventDetailsPage(String messagePost) throws  Exception {
		logInfo(" inside commentForAVideoInEventDetailsPage Method");
		WebElement eventPart = driver()
				.findElement(By.xpath("//div[@class='row comment-post']/div[@class='media']/div[2]"));
		List<WebElement> events = eventPart.findElements(By.className("media"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String activityMain = "]/div/div[1]/a";
		String part2 = "]/div/div[2]";
		String commentLnk = "/div[1]/span[2]/a";
		String commentCnt = "/div[2]/span[2]";
		String commentBox = "]/div/div[@class='comments']/div[1]/form";
		int filtersList = events.size();
		for (int i = 1; i <= filtersList; i++) {
			String imageVal = driver().findElement(By.xpath(part1 + i + activityMain)).getAttribute("href");
			System.out.println(imageVal);
			if (imageVal.contains("http")) {
				WebElement comment = driver().findElement(By.xpath(part1 + i + part2 + commentCnt));
				String commentCount = comment.getText().trim();
				int commentcount = Integer.parseInt(commentCount);
				logInfo("old comment count = " + commentCount);
				System.out.println("old comment count = " + commentCount);
				String commentTxt = driver().findElement(By.xpath(part1 + i + part2 + commentLnk)).getText().trim();
				System.out.println("commenttext  " + commentTxt);
				if (commentTxt.equalsIgnoreCase("Comment")) {
					scrollDown("xpath", part1 + i + part2 + commentLnk);
					waitOnElement("xpath", part1 + i + part2 + commentLnk);
					clickOnElement("xpath", part1 + i + part2 + commentLnk);
					/*waitOnElement("xpath", part1 + i + commentBox);
					inputTextClear("xpath", part1 + i + commentBox);
					inputText("xpath", part1 + i + commentBox, messagePost);
					clickOnElement("xpath", part1 + i + commentBox);
					driver().findElement(By.xpath(part1 + i + commentBox)).sendKeys(Keys.ENTER);
					*/
					waitOnElement("xpath", eventCommentTxtbx1);
					inputTextClear("xpath",eventCommentTxtbx1);
					inputText("xpath", eventCommentTxtbx1, messagePost);
					Thread.sleep(8000);
					driver().findElement(By.xpath(eventCommentTxtbx1)).sendKeys(Keys.ENTER);
					waitOnSpinner();
					Thread.sleep(8000);
				}
				driver().navigate().refresh();
				WebElement newComm = driver().findElement(By.xpath(part1 + i + part2 + commentCnt));
				String NewCommCount = newComm.getText().trim();
				int newCommentcount = Integer.parseInt(NewCommCount);
				logInfo("new comment count = " + newCommentcount);
				System.out.println("new comment count = " + newCommentcount);

				if (newCommentcount == commentcount + 1) {
					logInfo("comments(" + commentcount + ") incremented sucessfully to comments(" + newCommentcount
							+ ").");
					System.out.println("comments(" + commentcount + ") incremented sucessfully to comments("
							+ newCommentcount + ").");
					Assert.assertTrue(newCommentcount == commentcount + 1, "comments" + commentcount
							+ ") incremented sucessfully to comments(" + newCommentcount + ").");
				} else {
					logInfo("comments(" + commentcount + ") could not be incremented to comments(" + newCommentcount
							+ ").");
					System.out.println("comments(" + commentcount + ") could not be incremented to comments("
							+ newCommentcount + ").");
				}
				Assert.assertTrue(newCommentcount == commentcount + 1, "comments(" + commentcount
						+ ") not incremented sucessfully to comments(" + newCommentcount + ").");
				break;
			}
		}

	}

	public void eventDetailsPage_editEvent() throws  Exception {
		logInfo("Inside eventDetailsPage_editEvent Method");
		waitOnElement("xpath", eventDetailsStngsBtn);
		clickOnElement("xpath", eventDetailsStngsBtn);
		waitOnElement("xpath", eventDetailsEditBtn);
		clickOnElement("xpath", eventDetailsEditBtn);
	}

	public void eventDetailsPage_DeletetEvent() throws  Exception {
		logInfo("Inside eventDetailsPage_DeletetEvent Method");
		waitOnElement("xpath", eventDetailsStngsBtn);
		clickOnElement("xpath", eventDetailsStngsBtn);
		waitOnElement("xpath", eventDetailsDeltBtn);
		clickOnElement("xpath", eventDetailsDeltBtn);
		waitOnElement("xpath", eventDeleteDetailsPage);
		clickOnElement("xpath", eventDeleteDetailsPage);
		confirmOK();
		waitOnSpinner();
	}

	public void uploadImageToACalendarEvent() throws  Exception {
		logInfo("Inside uploadImageToACalendarEvent Method");
		waitOnElement("xpath", eventImageSettings);
		clickOnElement("xpath", eventImageSettings);
		uploadEventImages();
		clickOnElement("xpath", calendarSave);
		waitOnElement("xpath", calendarSkip);
		clickOnElement("xpath", calendarSkip);
		eventDetailsPage_editEvent();
		waitOnElement("xpath", eventImageSettings);
		clickOnElement("xpath", eventImageSettings);
		waitOnElement("xpath", recommendedImages);
		clickOnElement("xpath", recommendedImages);
		clickOnElement("xpath", calendarSave);
		waitOnElement("xpath", calendarSkip);
		clickOnElement("xpath", calendarSkip);
		verifyImagePresent();

	}

	public void uploadEventImages() throws  IOException, AWTException, InterruptedException {
		logInfo("Entered into uploadEventImages() method");
		try {
			clickOnElement("xpath", uploadImageCal);
			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Image.exe");
			Thread.sleep(30000);
		} catch (WebDriverException we) {

			System.err.println("Failed!! Not able to upload Image File.");
		}
	}

	public boolean verifyImagePresent() throws  Exception {
		logInfo("Entered into verifyImagePresent() method");
		boolean isImagePresent = false;
		waitOnElement("xpath", calendarImage);
		String imageVal = driver().findElement(By.xpath(calendarImage)).getAttribute("src");
		System.out.println(imageVal);
		if (imageVal.contains(".jpg")) {
			isImagePresent = true;
			System.out.println(isImagePresent);

		} else {
			logInfo(message + " Unable to add an image to the event");
			Assert.assertTrue(isImagePresent, message + " Unable to add an image to the event");
		}

		return isImagePresent;
	}

	public void addEventPhoto() throws Exception {
		logInfo("inside addEventPhoto() method");
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Upload Photo/Video");
		clickOnLink("linkText", "Add Photo");
		uploadFile("Image", caluploadPhoto);
		waitOnSpinner();
		waitOnElement("cssSelector", eventDet_up);
		clickOnElement("cssSelector", eventDet_up);
	}

	public void addEventVideo() throws Exception {
		logInfo("inside addEventVideo() method");
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Upload Photo/Video");
		clickOnLink("linkText", "Add Video");
		inputText("cssSelector", caluploadVideo, videoURL_text);
		waitOnElement("cssSelector", "div#video>form>div.form-group>input.btn.btn-primary.pull-right");
		clickOnElement("cssSelector", "div#video>form>div.form-group>input.btn.btn-primary.pull-right");
		confirmationMessage("Video Uploaded Successfully");
	}
	
	public void shareEventByEmail(String mailId) throws Exception {
		logInfo("Inside shareEventByEmail() Method");
		waitOnSpinner();
		waitOnElement("cssSelector", recipientsTo);
		WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); // inputVibeComposeTo
		inputText("cssSelector", recipientsTo, mailId);
		composeTo.sendKeys(Keys.TAB);
		composeTo.sendKeys(Keys.TAB);
		waitOnElement("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]");
		Thread.sleep(3000);
		scrollDown("linkText", "Send");
		clickOnLink("linkText", "Send");
		waitOnSpinner();
		Thread.sleep(10000);

	}

	public void selectTwitterIconToShare() throws Exception {
		logInfo("Entered into selectTwitterIconToShare() method");
		waitOnElement("xpath", eventDetailsShareTwittIcn);
		clickOnElement("xpath", eventDetailsShareTwittIcn);
	}

	public void calendar_closeSharePopUp() throws Exception {
		logInfo("Entered into calendar_closeSharePopUp() method");
		waitOnElement("cssSelector", calCloseSharePopup);
		clickOnElement("cssSelector", calCloseSharePopup);

	}

	public void addCalendarEventandInviteAGroup(String eventName, String startDate, String endDate, String eventSub)
			throws Exception {
		logInfo("inside addCalendarEventandInviteGuests() method...");
		go2CalendarPage();
		createCalendarLink();
		waitOnElement("xpath", inputEventName);
		inputTextClear("xpath", inputEventName);
		inputText("xpath", inputEventName, eventName);
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);
		selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", calendarType_text);
		inputTextClear("xpath", inputEventStartDate);
		inputText("xpath", inputEventStartDate, startDate);
		inputTextClear("xpath", inputEventEndDate);
		inputText("xpath", inputEventEndDate, endDate);
		selectRadioOrCheckbox("cssSelector", chkAllday);
		clickOnElement("xpath", newEventMoreotions);
		inputTextClear("xpath", inputEventDescription);
		inputText("xpath", inputEventDescription, calendarEventDescription_text);
		inputText("xpath", inputEventLoc, calendarEventLocation_text);
		inputText("xpath", inputEventAddr1, calendarEventAddr1_text);
		clickOnElement("xpath", "//*[@id='submit-form']");
		addNewemailsToInviteToAnEvent();
	}

	public void addNewGroupFromContactsToInviteToAnEvent(String groupName) throws Exception {
		logInfo("inside addNewGroupFromContactsToInviteToAnEvent() Method");
		String contactName_text = calendarcontactFirstName_text + " " + contactLastName_text;
		String[] contactNames = { contactName_text };
	//	cm.go2ContactsPage();
	//	cm.addBusinessContacts(calendarcontactFirstName_text, contactLastName_text, gmailId_text4);
	//	cm.addBusinessContacts(calendarcontactFirstName_text, contactLastName_text, gmailId_text3);
	//	cm.addBusinessContacts(calendarcontactFirstName_text, contactLastName_text, gmailId_text2);
	//	cm.addBusinessContacts(calendarcontactFirstName_text, contactLastName_text, gmailId_text1);
		cm.addGroup(groupName);
		cm.verifyGroupIsPresent(groupName);
		cm.go2ContactsPage();
		cm.selectContactsCategoryList("Contacts");
		cm.searchContact(" ","vibe");
		waitOnElement("xpath", "//*[@id='contacts_select_all']");
		clickOnElement("xpath", "//*[@id='contacts_select_all']");
		waitOnElement("xpath", "//*[@id='contacts-bulk-action-dropdown']/button");
		clickOnElement("xpath", "//*[@id='contacts-bulk-action-dropdown']/button");
		waitOnElement("linkText", "Add To Group");
		clickOnElement("linkText", "Add To Group");
		cm.selectGroupsForAdd2Groups(groupName);
		go2CalendarPage();

	}

	public void selectGroupFromInviteModal(String groupName) throws  Exception {
		logInfo("inside selectGroupFromInviteModal() Method");
		String contactName_text = calendarcontactFirstName_text + " " + contactLastName_text;
		String[] contactNames = { contactName_text };
		waitOnElement("cssSelector", eventInviteBtn);
		clickOnElement("cssSelector", eventInviteBtn);
		boolean isGroupFound = false;
		WebElement groups = driver().findElement(By.xpath("//*[@id='invite-contacts']/div[2]/ul[2]"));
		List<WebElement> groupsList = groups.findElements(By.tagName("Li"));
		int listsi = groupsList.size();
		System.out.println(listsi);
		String part1 = "//*[@id='invite-contacts']/div[2]/ul[2]/li[";
		String part2 = "]/div/div/a";
		for (int i = 1; i <= listsi; i++) {
			String actualGrp = driver().findElement(By.xpath(part1 + i + part2)).getText().trim();
			if (actualGrp.equalsIgnoreCase(groupName)) {
				isGroupFound = true;
				clickOnElement("xpath", part1 + i + part2);
				Thread.sleep(10000);
				waitOnElement("xpath", "//*[@id='select_all']");
				clickOnElement("xpath", "//*[@id='select_all']");
				waitOnElement("cssSelector", saveInv);
				clickOnElement("cssSelector", saveInv);
				confirmationMessage("Fantastic! Your invites have been successfully sent out to 4 recipients.");
				break;
			}
		}
		verifyInvitedContact(contactName_text);
		/*
		 * cm.go2ContactsPage(); cm.deleteGroup(groupName);
		 * confirmationMessage("Group Deleted."); cm.go2ContactsPage();
		 * cm.selectContactsCategoryList("Contacts");
		 * cm.selectMultipleContacts(contactNames,"Delete Selected"); confirmOK();
		 */
		if (isGroupFound == false) {
			Assert.assertTrue(isGroupFound, groupName + " is not avaiable in Invite Modal");

		}

	}

	public void selectEventInviteButton() throws  Exception {
		logInfo("inside selectEventInviteButton() Method");
		waitOnElement("cssSelector", eventInviteBtn);
		clickOnElement("cssSelector", eventInviteBtn);
	}

	public void selectPreviouslyInvitedConact(String inviteeName) throws  Exception {
		logInfo("inside selectPreviouslyInvitedConact() Method");
		waitOnElement("linkText", "Previously Invited");
		clickOnElement("linkText", "Previously Invited");
		waitOnElement("xpath", "//*[@id='contacts-search-list']/div/div");
		boolean isContactFound = false;
		WebElement econtactlist = driver().findElement(By.xpath("//*[@id='contacts-search-list']/div/div"));
		List<WebElement> allContacts = econtactlist.findElements(By.cssSelector("div.contact.media.row"));
		System.out.println("Total contacts = " + allContacts.size());
		String before_name = "//*[@id='contacts-search-list']/div/div/div[";
		String after_name = "]/div[2]/h4";
		String after_input = "]/div[3]/input";
		if (allContacts.size() > 0) {
			for (int i = 1; i <= allContacts.size(); i++) {
				WebElement ename = driver().findElement(By.xpath(before_name + i + after_name));
				String actInviteeName = ename.getText().trim();
				if (actInviteeName.equalsIgnoreCase(inviteeName)) {
					clickOnElement("xpath", before_name + i + after_input);

					isContactFound = true;
					break;
				}
			}

		}
		clickOnElement("xpath", "//*[@id='invite_save']");
		confirmationMessage("Fantastic! Your invites have been successfully sent out to 1 recipients.");
		if (isContactFound == false) {
			Assert.assertTrue(isContactFound, inviteeName + " is not avaiable in Invite Modal");
		}
	}

	public void searchAndSelectContactToInvite(String inviteeName) throws  Exception {
		logInfo("inside searchAndSelectContactToInvite() Method");
		boolean isContactFound = false;
		waitOnElement("xpath", inputSearchContact);
		inputText("xpath", inputSearchContact, inviteeName);
		waitOnElement("xpath", "//*[@id='search-list']");
		WebElement econtactlist = driver().findElement(By.xpath("//*[@id='search-list']"));
		List<WebElement> allContacts = econtactlist.findElements(By.cssSelector("div.contact.media"));
		System.out.println("Total contacts = " + allContacts.size());
		String before_name = "//*[@id='search-list']/div[";
		String after_name = "]/div[2]/h4";
		String before_input = "//*[@id='search-list']/div[";
		String after_input = "]/div[3]/input";
		if (allContacts.size() > 0) {
			for (int i = 1; i <= allContacts.size(); i++) {
				WebElement ename = driver().findElement(By.xpath(before_name + i + after_name));
				String actInviteeName = ename.getText().trim();
				if (actInviteeName.equalsIgnoreCase(inviteeName)) {
					clickOnElement("xpath", before_input + i + after_input);
					isContactFound = true;
					break;
				}
			}

		}
		clickOnElement("xpath", "//*[@id='invite_save']");
		confirmationMessage("Fantastic! Your invites have been successfully sent out to 1 recipients.");
		if (isContactFound == false) {
			Assert.assertTrue(isContactFound, inviteeName + " is not avaiable in Invite Modal");
		}

	}

	public boolean verifyInvitedContact(String contactName) throws  Exception {
		logInfo("Inside verifyInvitedContact Method");
		boolean isContactFound = false;
		waitOnElement("cssSelector", noResponseCount);
		clickOnElement("cssSelector", noResponseCount);
		waitOnElement("xpath", "allContacts");
		WebElement econtactlist = driver()
				.findElement(By.xpath("//*[@id='view_invitees']/div/div[1]/div[2]/div[1]/ol"));
		List<WebElement> allContacts = econtactlist.findElements(By.tagName("li"));
		System.out.println("Total contacts invited= " + allContacts.size());
		String before_name = "//*[@id='view_invitees']/div/div[1]/div[2]/div[1]/ol/li[";
		String after_name = "]/div/div/div[1]/h4";
		if (allContacts.size() > 0) {
			for (int i = 1; i <= allContacts.size(); i++) {
				WebElement ename = driver().findElement(By.xpath(before_name + i + after_name));
				String actInviteeName = ename.getText().trim();

				if (actInviteeName.equalsIgnoreCase(contactName)) {
					System.out.println(actInviteeName);
					isContactFound = true;

				}
				break;
			}

		}
		if (isContactFound == false) {
			Assert.assertTrue(isContactFound, contactName + " is not avaiable in the invitedlist");
		}

		return isContactFound;

	}

	public void shareAVideoInEventDetailsPage(String eventName) throws  Exception {
		logInfo(" inside shareAVideoInEventDetailsPage Method");
		WebElement eventPart = driver()
				.findElement(By.xpath("//div[@class='row comment-post']/div[@class='media']/div[2]"));
		List<WebElement> events = eventPart.findElements(By.className("media"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String activityMain = "]/div/div[1]/a";
		String part2 = "]/div/div[2]";
		String shareLnk = "/div[1]/span[3]/a";
		String shaeCnt = "/div[2]/span[3]";
		int filtersList = events.size();
		for (int i = 1; i <= filtersList; i++) {
			String imageVal = driver().findElement(By.xpath(part1 + i + activityMain)).getAttribute("href");
			System.out.println(imageVal);
			if (imageVal.contains("http")) {
				WebElement share = driver().findElement(By.xpath(part1 + i + part2 + shaeCnt));
				String shareCount = share.getText().trim();
				int sharecount = Integer.parseInt(shareCount);
				logInfo("old Share count = " + sharecount);
				System.out.println("old Share count = " + sharecount);
				String likeTxt = driver().findElement(By.xpath(part1 + i + part2 + shareLnk)).getText().trim();
				System.out.println("sharetext  " + likeTxt);
				if (likeTxt.equalsIgnoreCase("Share")) {
					scrollDown("xpath", part1 + i + part2 + shareLnk);
					waitOnElement("xpath", part1 + i + part2 + shareLnk);
					clickOnElement("xpath", part1 + i + part2 + shareLnk);
					selectTwitterIconToShare();
					sm.shareInTwitter();
					sm.go2TwitterPage();
				}
				WebElement newShare = driver().findElement(By.xpath(part1 + i + part2 + shaeCnt));
				String newShareCount = newShare.getText().trim();
				int newSharecount = Integer.parseInt(newShareCount);
				logInfo("new share count = " + newShareCount);
				System.out.println("new share count = " + newShareCount);

				if (newSharecount == sharecount + 1) {
					logInfo("share(" + sharecount + ") incremented sucessfully to shares(" + newSharecount + ").");
					System.out.println(
							"share(" + sharecount + ") incremented sucessfully to shares(" + newSharecount + ").");
					Assert.assertTrue(newSharecount == sharecount + 1,
							"share(" + sharecount + ") incremented sucessfully to shares(" + newSharecount + ").");
				} else {
					logInfo("share(" + sharecount + ") could not be incremented to shares(" + newSharecount + ").");
					System.out.println(
							"share(" + sharecount + ") could not be incremented to shares(" + newSharecount + ").");
				}
				Assert.assertTrue(newSharecount == sharecount + 1,
						"share(" + sharecount + ") not incremented sucessfully to shares(" + newSharecount + ").");
				break;
			}
		}

	}

	public void shareAPhotoInEventDetailsPage(String eventName) throws  Exception {
		logInfo(" inside shareAPhotoInEventDetailsPage Method");
		WebElement eventPart = driver()
				.findElement(By.xpath("//div[@class='row comment-post']/div[@class='media']/div[2]"));
		List<WebElement> events = eventPart.findElements(By.className("media"));
		String part1 = "//div[@class='row comment-post']/div[@class='media']/div[2]/div[";
		String activityMain = "]/div/div[1]/p/img";
		String part2 = "]/div/div[2]";
		String shareLnk = "/div[1]/span[3]/a";
		String shaeCnt = "/div[2]/span[3]";
		int filtersList = events.size();
		for (int i = 1; i <= filtersList; i++) {
			String imageVal = driver().findElement(By.xpath(part1 + i + activityMain)).getAttribute("src");
			System.out.println(imageVal);
			if (imageVal.contains(".jpeg")) {
				WebElement share = driver().findElement(By.xpath(part1 + i + part2 + shaeCnt));
				String shareCount = share.getText().trim();
				int sharecount = Integer.parseInt(shareCount);
				logInfo("old Share count = " + sharecount);
				System.out.println("old Share count = " + sharecount);
				String shareTxt = driver().findElement(By.xpath(part1 + i + part2 + shareLnk)).getText().trim();
				System.out.println("sharetext  " + shareTxt);
				if (shareTxt.equalsIgnoreCase("Share")) {
					scrollDown("xpath", part1 + i + part2 + shareLnk);
					waitOnElement("xpath", part1 + i + part2 + shareLnk);
					clickOnElement("xpath", part1 + i + part2 + shareLnk);
					selectTwitterIconToShare();
					sm.shareInTwitter();
				}
				WebElement newShare = driver().findElement(By.xpath(part1 + i + part2 + shaeCnt));
				String newShareCount = newShare.getText().trim();
				int newSharecount = Integer.parseInt(newShareCount);
				logInfo("new share count = " + newShareCount);
				System.out.println("new share count = " + newShareCount);

				if (newSharecount == sharecount + 1) {
					logInfo("share(" + sharecount + ") incremented sucessfully to shares(" + newSharecount + ").");
					System.out.println(
							"share(" + sharecount + ") incremented sucessfully to shares(" + newSharecount + ").");
					Assert.assertTrue(newSharecount == sharecount + 1,
							"share(" + sharecount + ") incremented sucessfully to shares(" + newSharecount + ").");
				} else {
					logInfo("share(" + sharecount + ") could not be incremented to shares(" + newSharecount + ").");
					System.out.println(
							"share(" + sharecount + ") could not be incremented to shares(" + newSharecount + ").");
				}
				Assert.assertTrue(newSharecount == sharecount + 1,
						"share(" + sharecount + ") not incremented sucessfully to shares(" + newSharecount + ").");
				break;
			}
		}

	}

	public void pageRefresh() {
		driver().navigate().refresh();
	}

	public void repeatEventSettings(String repeatMode, String recur, String eventRecurenceEnd,int onDate) throws Exception {
		logInfo("inside repeatEventSettings() method");
		String futDate = getDate(onDate, "MM/dd/yyyy")	;
		System.out.println(futDate);
		waitOnElement("xpath", chkRepeatEvent);
		selectRadioOrCheckbox("xpath", chkRepeatEvent);
		switch (repeatMode) {
		case "Daily":
			waitOnElement("xpath", radioRepEventDaily);
			verifyElementPresent("xpath", radioRepEventDaily);
			selectRadioOrCheckbox("xpath", radioRepEventDaily);
			inputTextClear("xpath", inputRepeatEventEvery);
			inputText("xpath", inputRepeatEventEvery, repeatEventEvery_text);
			break;
		case "Day Of The Week":
			selectRadioOrCheckbox("xpath", radioRepEventDayOfWeek);
			waitOnElement("xpath", radioRepEventDays);
			selectRadioOrCheckbox("xpath", radioRepEventDays);
			break;
		case "Weekly":
			selectRadioOrCheckbox("xpath", radioRepEventWeekly);
			inputTextClear("xpath", inputRepeatEventEvery);
			inputText("xpath", inputRepeatEventEvery, recur);
			break;
		case "Monthly":
			selectRadioOrCheckbox("xpath", radioRepEventMonthly);
			waitOnElement("xpath", radioRepMod);
			selectRadioOrCheckbox("xpath", radioRepMod);
			break;
		case "Yearly":
			selectRadioOrCheckbox("xpath", radioRepEventYearly);
			inputTextClear("xpath", inputRepeatEventEvery);
			inputText("xpath", inputRepeatEventEvery, recur);
			break;
		}

		switch (eventRecurenceEnd) {
		case "No End Date":
			selectRadioOrCheckbox("xpath", radioRepEventNoEndDate);
			break;
		case "End After":
			selectRadioOrCheckbox("xpath", radioRepEventEndAfter);
			inputTextClear("xpath", inputEventOccurance);
			inputText("xpath", inputEventOccurance, eventOccurance_text);
			break;
		case "On Date":
			selectRadioOrCheckbox("xpath", radioRepEventOnDate);
			inputTextClear("xpath", inputRepEventDate);
			inputText("xpath", inputRepEventDate, futDate);
			break;
		}
		waitOnElement("xpath",btnRecurSave);
		clickOnElement("xpath",btnRecurSave);
		/*clickOnButton("xpath",btnEditSave);
		verifyElementPresent("xpath", btnEventSave);
		clickOnButton("xpath", btnEventSave);
		waitOnElement("cssSelector",saveInv);
		clickOnElement("cssSelector",saveInv);*/
	}
	
	public void repeatEventValidation(String repeatType, String expectedText) throws  Exception {
		logInfo("inside repeatEventValidation() method");
		waitOnElement("cssSelector","#recur-summary");
		switch (repeatType) {
		case "No End Date":
			String newText = driver().findElement(By.cssSelector("#recur-summary")).getText();
			System.out.println(newText);
			String[] fisrtP = newText.split("Repeats");
			String part4 =fisrtP[1];
			String part1 = fisrtP[0];
			String[] part2 = part1.split("g");
			String startDate = part2[1].trim();
			String formattedDate = formatDate(startDate);
			String srt = "Starting ";
			String rep = " Repeats";
			String fiaStr= srt+formattedDate+rep+part4;
			System.out.println(fiaStr);
			eventDetailsPage_DeletetEvent();
			waitOnSpinner();
			Assert.assertEquals( fiaStr,expectedText);
			break;
		case "End After":
			String newText1 = driver().findElement(By.cssSelector("#recur-summary")).getText();
			System.out.println(newText1);
			String[] ssp = newText1.split("Repeats");
			String partt1 =ssp[1];
			String partt11 = ssp[0];
			String[] partt21 = partt11.split("g");
			String startDate1 = partt21[1].trim();
			String formattedDate1 = formatDate(startDate1);
			String srt1 = "Starting ";
			String rep1 = " Repeats";
			String fiaStr1= srt1+formattedDate1+rep1+partt1;
			System.out.println(fiaStr1+ "is the formatted date");
			eventDetailsPage_DeletetEvent();
			waitOnSpinner();
			Assert.assertEquals( fiaStr1,expectedText);
			break;
		case "On Date":
			String newText2 = driver().findElement(By.cssSelector("#recur-summary")).getText();
			System.out.println(newText2);
			String[] ys1 = newText2.split("Repeats");
			String sndPart =ys1[1];
			String[]ys2=sndPart.split("on");
			String thrdPart1= ys2[0];
			String part31 = ys1[0];
			String[] part61 = part31.split("g");
			String startDate11 = part61[1].trim();
			String formattedDate11 = formatDate(startDate11);
			System.out.println("THIS IS THE FINAL"+formattedDate11);
			String[] s2 = sndPart.split("on");
			String thrdPart =s2[1].trim();
			String endDate = formatDate(thrdPart);
			String srt11 = "Starting ";
			String rep11 = " Repeats";
			String endsON = "On ";
			String fiaStr11= srt11+formattedDate11+rep11+thrdPart1+endsON+endDate;
			System.out.println(fiaStr11);
			eventDetailsPage_DeletetEvent();
			waitOnSpinner();
			Assert.assertEquals( fiaStr11,expectedText);
			
			break;
		case "On Month":
			String newText3 = driver().findElement(By.cssSelector("#recur-summary")).getText();
			System.out.println(newText3);
			String[] ys11 = newText3.split("Repeats");
			String sndPart1 =ys11[1];
			System.out.println(sndPart1);
			String[]ys21=sndPart1.split("On");
			String thrdPart11= ys21[0];
			String part311 = ys11[0];
			System.out.println(part311);
			String[] part611 = part311.split("g");
			String startDate111 = part611[1].trim();
			System.out.println(startDate111);
			String[] s21 = sndPart1.split("On");
			String thrdPart111 =s21[1].trim();
			System.out.println(thrdPart111);
			String srt111 = "Starting ";
			String rep111 = " Repeats";
			String textOn = "On ";
			String fiaStr111= srt111+startDate111+rep111+thrdPart111+textOn+thrdPart111;
			System.out.println(fiaStr111);
			eventDetailsPage_DeletetEvent();
			waitOnSpinner();
			Assert.assertEquals( fiaStr111,expectedText);
			
			break;
		}
		
	}

	public String formatDate(String date) throws ParseException {
		logInfo("inside repeatEventValidation() method");
		DateFormat df = new SimpleDateFormat("MMMMM dd, yyyy HH:mm");
		Date td = df.parse(date);
		System.out.println(df.format(td));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/YYYY");
		String replaceDate = simpleDateFormat.format(td);
		System.out.println(replaceDate);
		
		return replaceDate;
	}
	
	public void selectFacebookIconFromPopUp() throws Exception {
		logInfo("Entered into selectFacebookIconFromPopUp() method");
		waitOnElement("cssSelector", shareCalendarFB);
		clickOnElement("cssSelector", shareCalendarFB);
	}
	
	public void selectTwitterIconFromPopUp() throws Exception {
		logInfo("Entered into selectTwitterIconFromPopUp() method");
		waitOnElement("cssSelector", shareCalendarTwitter);
		clickOnElement("cssSelector", shareCalendarTwitter);
	}
}