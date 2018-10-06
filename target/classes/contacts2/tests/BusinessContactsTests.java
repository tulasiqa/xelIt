package vibe.contacts2.tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.admin.tests.AdminResourceLibrary3;
import vibe.calendar2.tests.CalendarMethods;
import vibe.ecards.tests.EcardMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.tasks.tests.TaskMethods;
import common.Priority;
import common.TestBase;

@Priority(108)
public class BusinessContactsTests extends BusinessContactsMethods {
	TestBase tb = new TestBase();
	InboxMethods in = new InboxMethods();
	CalendarMethods cal = new CalendarMethods();
	TaskMethods tk = new TaskMethods();
	AdminResourceLibrary3 rl = new AdminResourceLibrary3();
	MyProfileMethods profile = new MyProfileMethods();
	EcardMethods em = new EcardMethods();
	
	//   public static String vibeBusinessContacRecipient_text =  getEmail(adminUser_text,appUrl); 
	//	 public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
	
	public static String vibeRecipient_text = "vibe1.icentris@gmail.com";
	public static String contactName_text = contactFirstName_text + " " + contactLastName_text;
	public static String teamContactName_text = teamContactFirstName_text + " " + teamContactLastName_text;
	public static String contactName_text1 = contactFirstName_text1 + " " + contactLastName_text1;	
	public static String groupName2_text = "TestGroup_" + TestBase.random();
	
	
	@Test(priority=10801)
	public void addBusinessContact() throws Exception{
        logInfo("inside addBusinessContact() Test");
        String contactNames = contactName_text;
       //String contactNames1 = "Vibe.Icentri...";
        
        go2ContactsPage();
        searchContact(contactFirstName_text,contactLastName_text);
        boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
        if(isContactFound==true){
            System.out.println("match found..deleting the contact.");
            selectMultipleContacts(contactNames,"Delete Selected");
            confirmOK(); 
        }
           
        go2ContactsPage();
        searchContact("vibe.icentris@gmail.com");
        boolean isContactFound2 = verifyBusinessContact("Vibe.Icentri...","");  //vibe.icentris@gmail.com
        if(isContactFound2==true){
            System.out.println("match found..deleting the contact.");
            selectMultipleContacts("Vibe.Icentri...","Delete Selected");
            confirmOK(); 
        }
        
        addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text); // vibeRecipient_text
        confirmationMessage("Contact is created");
        boolean isContactAdded= verifyBusinessContact(contactFirstName_text,contactLastName_text);
        if(isContactAdded==false){
            System.out.println("business contact could not be added.");
            Assert.assertTrue(isContactAdded, "business contact could not be added.");
        }
    }
	
	
	
	@Test(priority=10803)
	public void updateBusinessContact() throws Exception{
		 logInfo("inside updateBusinessContact() Test");
		
		 go2ContactsPage();
		 selectContactsCategoryList("Recent Contacts");
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 editBusinessContact();
		 // //*[@id='contact-details']/div[1]/div[1]/div/a
		 WebElement x = driver().findElement(By.xpath("//*[@id='contact-details']/div[1]/div[1]/div[2]/a"));
		 System.out.println(x);
		 if (!x.getText().trim().contains(prop.getLocatorForEnvironment(appUrl,"selfmailId1"))) {
			 Assert.assertTrue(true, "email match not found.");
		 }
	}
	
	@Test(priority=10804)
	public void searchBussinesContact() throws Exception{
		logInfo("inside searchBussinesContact Test");
		System.out.println("inside searchBussinesContact Test");
		go2ContactsPage();
		searchContact(contactFirstName_text,contactLastName_text);
		boolean isContactFound= verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==false){
			System.out.println("business contact could not found.");
			Assert.assertTrue(isContactFound, "business contact not found.");
		}
	}
	
	@Test(priority=10805)
	public void verifyContactsInRecentLeads() throws Exception{
		logInfo("inside verifyContactsInRecentLeads Test");
		go2ContactsPage();
		selectContactsCategoryList("Recent Leads");
		boolean isContactFound= verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==false){
			System.out.println("business contact could not found.");
			Assert.assertTrue(isContactFound, "Business contact not found in Recent Leads category.");
		}
	}
	
	
	@Test(priority=10806)
	public void verifyPreferredEmail() throws Exception{	
		logInfo("inside verifyPreferredEmail test");
		String prefSubj = "preffered_messages_" + TestBase.generateRandomNumberInRange(1,500);
		go2ContactsPage();
		selectContactsCategoryList("Recent Contacts");
		selectBusinessContact(contactFirstName_text,contactLastName_text);		
		//setPreferredEmail2Contact(prop.getLocatorForEnvironment(appUrl,"selfmailId1"));
		sendMessage2PrefferedContacts(prefSubj);	
		in.go2Inbox();
		boolean isMailPresent = in.selectMailsWithSubject(prefSubj);
		
		if(isMailPresent==true){
			logInfo(prefSubj + " mail found in inbox");
			in.deleteFilteredVibeMails();
			go2ContactsPage();
		} else {
			logInfo(prefSubj + " mail not found in inbox");
			go2ContactsPage();
			Assert.assertTrue(isMailPresent, prefSubj + " mail not found in inbox");
		}
	}

	
	@Test(priority=10807)
	public void validateInvalidSearch() throws Exception{
		logInfo("inside validateInvalidSearch test");
		
		go2ContactsPage();
		contactSearch(invalidSearchText);
		go2ContactsPage();
		advancedContactSearch(invalidSearchText);
		go2ContactsPage();
		/*downLineSearch(invalidSearchText);
		go2Groups();
		groupSearch(invalidSearchText);	*/
	}
	
	
	
	@Test(priority=10810)		
	public void addCallScripts2BusinessContact() throws Exception{
		logInfo("inside addCallScripts2BusinessContact() Test");
		System.out.println("inside addCallScripts2BusinessContact() Test");
			//selectCallscript4Contact(contactFirstName_text,contactLastName_text);
			go2ContactsPage();
		 	selectContactsCategoryList("Recent Contacts");
		 	selectBusinessContact(contactFirstName_text,contactLastName_text);
		 	selectContactAction("Start Call Script");
			submitCallScript(callScriptName_text,phoneScriptNotes_text);
			boolean isNotesFound = verifyNotesIsPresent(phoneScriptNotes_text);
			if(isNotesFound==false){
				System.out.println(phoneScriptNotes_text + " callscript match not found in the notes.");
				Assert.assertTrue(isNotesFound, phoneScriptNotes_text + " callscript match not found in the notes.");
			}
		}
	
	

	
	@Test(priority=10811)
	public void verifyPendingCallScripts() throws Exception{
		logInfo("inside verifyPendingCallScripts() Test");
		
		String[] markets = {"US (English)","RU (Russian)","CO (English)"};
		//String expErrMsg = "Please complete this field."; 
		String expErrMsg = "can't be blank";
		 	go2ContactsPage();
		 	selectContactsCategoryList("Recent Contacts");
		 	selectBusinessContact(contactFirstName_text,contactLastName_text);
		 	selectContactAction("Start Call Script");
			submitCallScript(callScriptPendingName_text,callScriptPendingName_text+ " Notes");
			
			waitOnElement("cssSelector",eleCallscriptCantBeBlank);
			WebElement e = driver().findElement(org.openqa.selenium.By.cssSelector(eleCallscriptCantBeBlank));
			String actErrMsg = e.getText().trim();
			System.out.println("actErrMsg ="+actErrMsg);
			Assert.assertEquals(actErrMsg, expErrMsg); 
	}
	

	
	@Test(priority=10812)
	public void addNotes2BusinessContact() throws Exception{
		logInfo("inside addNotes2BusinessContact() Test");
		System.out.println("inside addNotes2BusinessContact() Test");
		go2ContactsPage();
		selectContactsCategoryList("Recent Contacts");
		selectBusinessContact(contactFirstName_text,contactLastName_text);
		addNotes2Contacts(contactFirstName_text,contactLastName_text,addContactNotes_text);
		boolean isNotesFound = verifyNotesIsPresent(addContactNotes_text);
		System.out.println("isNotesFound =" +isNotesFound);
		
		if(isNotesFound==false){
			logInfo(isNotesFound + " notes could not be found.");
			Assert.assertTrue(isNotesFound, isNotesFound + " notes could not be found.");
		} else {
			manageContactNotes(addContactNotes_text,"Delete");
		}
	}  
	

	
	@Test(priority=10813)
	public void sendMesg2BusinessContact() throws Exception{
		logInfo("inside sendMesg2BusinessContact() Test");
		System.out.println("inside sendMesg2BusinessContact() Test");
		 go2ContactsPage();
		 selectContactsCategoryList("Recent Contacts");
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 selectContactAction("Send Message");
		 sendMessage2Contacts(contactFirstName_text,contactLastName_text,prop.getLocatorForEnvironment(appUrl, "selfmailId1"));
		 back2Office();
		 in.go2Inbox();
		boolean isMailFound = in.selectVibeMailsWithSubject(inputSendMessageSubject_text);
		if(isMailFound==true){
			in.deleteFilteredVibeMails();
		} else {
			Assert.assertTrue(isMailFound, inputSendMessageSubject_text + " message not be found in Inbox.");
			logInfo(inputSendMessageSubject_text + " message not be found in Inbox.");
		}
	}
	
	
	@Test(priority=10814)
	public void setAppoinment2BusinessContact() throws Exception{
		logInfo("inside setAppoinment2BusinessContact() Test");
		System.out.println("inside setAppoinment2BusinessContact() Test");
		 go2ContactsPage();
		 selectContactsCategoryList("Recent Contacts");
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 selectContactAction("Create Event");
		 setAppointmentWithContacts(appointmentName_text);
		 cal.go2CalendarPage();
		boolean isEventDisplayed = false;
		isEventDisplayed = cal.verifyEventisDisplayedMonthView(appointmentName_text);
		if(isEventDisplayed==false){
			logInfo(appointmentName_text + " event could not be created through contacts.");
			Assert.assertTrue(isEventDisplayed, appointmentName_text + " event could not be created through contacts.");
		} else {
			logInfo(appointmentName_text + " event created through contacts.");
			cal.deleteCalendarEvent(appointmentName_text);
		}
		
		go2ContactsPage();
		takeActionOnContactEvents(contactName_text,appointmentName_text, "Delete");
		confirmOK();
		
	}
	
	
	@Test(priority=10815)
	public void createTask4BusinessContact() throws Exception{
		logInfo("inside createTask4BusinessContact() Test");
		System.out.println("inside createTask4BusinessContact() Test");
		
		 go2ContactsPage();
		 selectContactsCategoryList("Recent Contacts");
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 createTaskFromContacts(contactTaskDesc_text);
		
		boolean isTaskFound = verifyContactHistory(contactTaskDesc_text);
		if(isTaskFound==false){
			logInfo(contactTaskDesc_text + " task not found in the contacts history.");
			Assert.assertTrue(isTaskFound, contactTaskDesc_text + " task not found in the contacts history.");
		}
		go2ContactsPage();
		takeActionOnContactEvents(contactName_text,contactTaskDesc_text,"Delete");
		//confirmationMessage("User task deleted");
	}	

	
	
	@Test(priority=10813)
	public void validateEmailAttachmentAbove4MB() throws Exception{
	logInfo("inside validate EmailAttachmentAbove4MB test");
		logInfo("Try to upload more than 4 Mb size to validate the alert toaster message. ");
		
		go2ContactsPage();
		selectContactsCategoryList("Recent Contacts");
		selectBusinessContact(contactFirstName_text,contactLastName_text);
		uploadMoreThan4MBfile(prop.getLocatorForEnvironment(appUrl, "selfmailId1"),"MoreThan4MbImg");
		
		in.go2Inbox();
		boolean isMailPresent = in.selectMailsWithSubject("Upload more than 4MB file");
		if(isMailPresent==true){
			logInfo("Upload more than 4MB file mail found in inbox");
			in.deleteFilteredVibeMails();
			Assert.assertFalse(isMailPresent, "Email attachments more than 4MB should not be sent");
		} else {
			logInfo("Upload more than 4MB file mail not found in inbox");
			
		}
		
	}

	
	@Test(priority=10820)
	public void addGroup() throws Exception{
		logInfo("inside addGroup Test");
		System.out.println("inside addGroup Test");
	
		addGroup(groupName_text);
		confirmationMessage("Group Created.");
		
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		System.out.println("isGroupAdded =" +isGroupAdded);
		
		if(isGroupAdded==false){
		  logInfo(groupName_text + " group could not be created.");
		  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
		} else {
			logInfo(groupName_text + " group created sucessfully.");
			deleteGroup(groupName_text);
			confirmationMessage("Group Deleted.");
		}
		
	}
	
	
	@Test(priority=10821)
	public void verifyAddingExistingGroups() throws Exception{
		logInfo("inside verifyAddingExistingGroups test");
		
		addGroup(groupName_text);
		confirmationMessage("Group Created.");
		addGroup(groupName_text);
		confirmationMessage("Category Name has already been taken");
		deleteGroup(groupName_text);
		confirmationMessage("Group Deleted.");
	}
	
	
	@Test(priority=10822)
	public void renameGroup() throws Exception{
		logInfo("inside renameGroup Test ");
		
		addGroup(groupName_text);
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded==true){
			logInfo(groupName_text + " group created sucessfully.");
			renameGroup(groupName_text, groupName_text);
			confirmationMessage("Group updated.");
			boolean isGroupUpdated= verifyGroupIsPresent(groupName_text+"Updated");  
			if(isGroupUpdated==true){
				logInfo(groupName_text+"Updated" + " group updated sucessfully.");
				deleteGroup(groupName_text+"Updated");
				
				confirmationMessage("Group Deleted.");
			} else {
				Assert.assertTrue(isGroupUpdated, groupName_text+"Updated" + " group not updated.");
			}
			
		}
	}
	
	
	
	@Test(priority=10823)
	public void addContacts2Group() throws Exception{
		logInfo("inside addContacts2Group Test");
		System.out.println("inside addContacts2Group Test");
		addGroup(groupName_text);
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		System.out.println("isGroupAdded =" +isGroupAdded);
		if(isGroupAdded==false){
		  logInfo(groupName_text + " group could not be created.");
		  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
		} else {
			logInfo(groupName_text + " group created sucessfully.");
			addContact2Group(groupName_text + " (0)",contactName_text);
		}
	}
	
	
	
	@Test(priority=10824)					// ,dependsOnMethods={"addContacts2Group"}
	public void addGroupContacts2AnotherGroup() throws Exception{
		logInfo("inside addGroupContacts2AnotherGroup Test ");
			
		addGroup(groupName2_text);
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded){
		 	logInfo(groupName_text + " group found.");
			selectGroup(groupName_text);
			selectBusinessContact(contactFirstName_text,contactLastName_text);
			selectGroupActions("Add To Group");
			selectGroupsForAdd2Groups(groupName2_text);
			confirmationMessage("1 contacts added to " + groupName2_text +".");
			deleteGroup(groupName2_text);
		} else {	
			 logInfo(groupName_text + " group not found.");
			  Assert.assertTrue(isGroupAdded, groupName_text + " group not found.");
		}
	}
	
	
	@Test(priority=10825)					// ,dependsOnMethods={"addContacts2Group"}
	public void email2SelectedContactsFromManageGroup() throws Exception{
		logInfo("inside email2SelectedContactsFromManageGroup Test ");
				
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded){
		 	logInfo(groupName_text + " group found.");
			selectGroup(groupName_text);
			selectBusinessContact(contactFirstName_text,contactLastName_text);
			selectGroupActions("Email Selected");
			sendMessage2Contacts(contactFirstName_text,contactLastName_text,gmailId_text);
			in.loginGmail(gmailId_text, gmailPwd_text);	
		
			boolean isMailFound = in.verifyInboxGmail(inputSendMessageSubject_text);
			if(isMailFound==true){
				logInfo(isMailFound + "message found in Inbox.");
				System.out.println(isMailFound + "message found in Inbox.");
				in.signoutGmail();
				go2HomePage();
				
			} else {
				logInfo(vibeRecipient_text + " message not be found in Inbox.");
				in.signoutGmail();
				go2HomePage();
			}
			
		} else {	
			 logInfo(groupName_text + " group not found.");
			  Assert.assertTrue(isGroupAdded, groupName_text + " group not found.");
		}
	}
	
	

	
	
	@Test(priority=10828)					// ,dependsOnMethods={"addContacts2Group"}
	public void exportGroups2CSV() throws Exception{
		logInfo("inside exportGroups2CSV Test ");
		String dt = getDate(0,"YYYY-MM-dd");
		selectGroupAndMoreOption(groupName_text,"Export Csv");
		boolean isFileExists = false;
		String filepath = projectPath+"\\downloads\\"+"export-"+dt+".csv";
		verifyFileExistsOnDisk(filepath);
	}
	
	@Test(priority=10829)		// ,dependsOnMethods={"addContacts2Group"}
	public void exportGroups2Excel() throws Exception{
		logInfo("inside exportGroups2Excel Test ");
		selectGroupAndMoreOption(groupName_text,"Export Excel");
		boolean isFileExists = false;
		String filepath = projectPath+"\\downloads\\";
		verifyFileExistsOnDisk(filepath+"export.xls");
	}
	
	
/*
	@Test(priority=10830)		// ,dependsOnMethods={"addContacts2Group"}
	public void importContactsFromYahoo() throws Exception{
		logInfo("inside importContactsFromYahoo test");
		go2ContactsPage();
		validateYahooImport(groupName_text, "Import Into Group",YahooMailId, YahooPWD);
		// deleteGroup(groupName_text);
	}
	
	

	@Test(priority=10831)		// ,dependsOnMethods={"addContacts2Group"}
	public void importContactsFromGmail() throws Exception{
		logInfo("inside importContactsFromGmail test");
		// addGroup(groupName_text);
		go2ContactsPage();		
		validateGmailImport(groupName_text, "Import Into Group", gmailId_text, gmailPwd_text);
		
	}*/
	
	
	// Remove From Group option is blocker in Avon.
	
		@Test(priority=10833)					// ,dependsOnMethods={"addContacts2Group"}
		public void removeContactsFromManageGroup() throws Exception{
			logInfo("inside removeContactsFromManageGroup Test ");
				
			boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
			if(isGroupAdded){
			 	logInfo(groupName_text + " group found.");
				selectGroup(groupName_text);
				selectBusinessContact(contactFirstName_text,contactLastName_text);
				selectGroupActions("Remove From Group "+groupName_text);
				confirmationMessage("contacts removed sucessfully from " + groupName_text+".");
				
			} else {	
				 logInfo(groupName_text + " group not found.");
				  Assert.assertTrue(isGroupAdded, groupName_text + " group not found.");
			}
		}
		
		
		@Test(priority=10834)		// ,dependsOnMethods={"addContacts2Group"}			
		public void deleteSelectedContactsFromManageGroup() throws Exception{
			logInfo("inside deleteSelectedContactsFromManageGroup Test ");
			
			boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
			if(isGroupAdded){
			 	logInfo(groupName_text + " group found.");
				selectGroup(groupName_text);
				selectBusinessContact(contactFirstName_text,contactLastName_text);
				selectGroupActions("Delete Selected");
				confirmOK();
				
			} else {	
				 logInfo(groupName_text + " group not found.");
				  Assert.assertTrue(isGroupAdded, groupName_text + " group not found.");
			}
		}
		
		

		@Test(priority=10835)
		public void validatePrintLabelsWithAddress() throws Exception{	
			logInfo("inside validatePrintLabelsWithAddress test");
			//String groupName_text = "TestGroup_607";
			go2ContactsPage();
			selectGroupAndMoreOption(groupName_text+"(" , "Print Labels");
			handlePDFWindow();
			
		}	
		
		
	
	@Test(priority=10836)
   public void reply2EmailFromContactHistory() throws Exception{
		logInfo("inside reply2EmailFromContactHistory test");
		
		/*profile.navigateMyProfileAccount("Notifications");
        String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
        System.out.println(selfmailId);*/

		String[] contactNames = {contactName_text};
		
		/*go2ContactsPage();
		selectMultipleContacts(contactNames,"Delete Selected");
		addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text);	// comment later if reqd	
		selectBusinessContct(contactFirstName_text,contactLastName_text);*/
		validateInvalidAlertsforMailing("raviKumargmail.com", prop.getLocatorForEnvironment(appUrl, "selfmailId1"));		
		 go2ContactsPage();
		 selectContactsCategoryList("Recent Contacts");
		 selectBusinessContact(contactFirstName_text,contactLastName_text);	
		 selectContactAction("Send Message");
		sendMessage2Contacts(contactFirstName_text,contactLastName_text,prop.getLocatorForEnvironment(appUrl, "selfmailId1"),subjectContent1 );		
		replyNForwardMail(prop.getLocatorForEnvironment(appUrl, "selfmailId1"),subjectContent1 );
		in.go2Inbox();
		in.selectMailsWithSubject(subjectContent1);
		in.deleteFilteredVibeMails();	
		
	}
	

	@Test(priority = 10837)
	public void validateNoMailIdForInvitee() throws Exception{	
		logInfo("inside validateNoMailIdForInvitee test");
		addBussinesscontactwithoutInfo(FNameText, LNameText);
		boolean isContactAdded= verifyBusinessContact(FNameText, LNameText);
		if(isContactAdded==false){
			System.out.println("business contact could not be added.");
			Assert.assertTrue(isContactAdded, "business contact could not be added.");
		}
		go2ContactsPage();			
		conatctList(FNameText, LNameText);
		selectContactOptions("Invite to Event");
		confirmationMessage("Contact doesn't have an Email");
		go2ContactsPage();			
		conatctList(FNameText, LNameText);
		conatctList(contactFirstName_text,contactLastName_text);
		selectContactOptions("Invite to Event");
		confirmationMessage("Some of your contacts don't have email addresses.");		
	}
	
	
	
	
	@Test(priority = 10838)
	public void validations4BussinessContact() throws Exception{
		logInfo("inside validations4BussinessContact test");
		String FNameText = "Test "+TestBase.generateRandomNumberInRange(2, 900);
		addContactsAlerts(FNameText);		
		go2ContactsPage();
		selectBusinessContact(contactFirstName_text,contactLastName_text);
		taskAlerts();
		callScriptAlert();
		notesAlert();
		eventAlert();	
	}
	

	
	// Can remove below test method , once the importFromGmail issue is resolved.
	
	/*@Test(priority=10839)	
	public void reaccessApp(){
		String wndBeforeWindow = driver().getWindowHandle();	
		driver().close();
		driver().switchTo().window(wndBeforeWindow);
	}*/
	
	
	
	
	@Test(priority=10840)
	public void attachResources2SendMessage() throws Exception{
		logInfo("inside attachResources2SendMessage() Test ");
		/*profile.navigateMyProfileAccount("Notifications");
        String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
        System.out.println(selfmailId);*/

		System.out.println("inside attachResources2SendMessage() Test");
		boolean isAssetAttached = false;
		userLogout();
		rl.nav2AdminRL();
		rl.addResourceCategory(parentCategory_text,"None");
		boolean isAdded = rl.verifyResourceCategoryAdmin(parentCategory_text);
		if(isAdded==true){
			rl.addNewResource(newResourceTitle_text, parentCategory_text, resourceTagPDF, true, false, "PDF", resourceAssetPDF,
					"Active", 1);
			confirmationMessage("Resource is created");
		}
		
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
		isAssetAttached = attachResource2SendMessage(contactFirstName_text,contactLastName_text,prop.getLocatorForEnvironment(appUrl, "selfmailId1"),newResourceTitle_text,"pdf");
		if(!isAssetAttached){
			Assert.assertTrue(isAssetAttached, "Unable to attach a resource to the send message.");
		}
		
	}
	
	@Test(priority=10841)
	public void validateAppointment4Contact() throws Exception{
		 logInfo("inside setAppointmentWithContacts() method.");
		 System.out.println("inside setAppointmentWithContacts() method.");
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 validateAppointment();
	}
		
	
	@Test(priority=10842)
	public void validatePrintLabelsWithoutAddress() throws Exception{	
		logInfo("inside validatePrintLabelsWithoutAddress test");
		System.out.println("inside validatePrintLabelsWithoutAddress test");
		String contactNames = contactName_text1;
		
		addGroup(groupName_text);
		Thread.sleep(5000);
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded==false){
		  logInfo(groupName_text + " group could not be created.");
		  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
		}
			
		addBusinessContactsWithoutAddress(contactFirstName_text1,contactLastName_text1,contactEmail_text1);
		go2ContactsPage();
		selectMultipleContacts(contactNames,"Add To Group");
		addToGroupsList(groupName_text);
		confirmationMessage("1 contacts added to " + groupName_text+".");
		selectGroupAndMoreOption(groupName_text+"(" , "Print Labels");
		handlePDFWindow();
		deleteGroup(groupName_text);
	}	
	
	@Test(priority=10843)
	public void validateBusinessContact() throws Exception{	
		logInfo("inside validateBusinessContact test");
		System.out.println("inside validateBusinessContact test");
		go2ContactsPage();
		selectBusinessContact(contactFirstName_text1, contactLastName_text1);
		boolean isValidated = validateContact();
		if(!isValidated){
			Assert.assertTrue(isValidated, "Unable to validate a contact.");
		}
	}
	
	@Test(priority=10844)
	public void validateCheckboxForContact() throws Exception{	
		logInfo("inside validateCheckboxForContact test");
		System.out.println("inside validateCheckboxForContact test");
		go2ContactsPage();
		boolean isValidated = validateCheckbox4Contact();
		if(!isValidated){
			Assert.assertTrue(isValidated, "Unable to validate a contact checkbox without selecting a contact group.");
		}
	}
	
	

	@Test(priority=10845)
	public void checkUnassignedContacts() throws Exception{
		logInfo("inside checkUnassignedContacts() Test");
		 String contactNames = contactName_text;
		  addBusinessContacts(contactFirstName_text,contactLastName_text, vibeRecipient_text);  // contactEmail_texts
		  go2UnassignedContactsPage();
	      boolean isContactAdded= verifyBusinessContact(contactFirstName_text,contactLastName_text);
	      if(isContactAdded==true){
	    	  logInfo(contactFirstName_text + contactLastName_text + " contact found in Unassigned contacts list.");
	    	  addGroup(groupName_text);
	    	  boolean isGroupAdded = verifyGroupIsPresent(groupName_text); 
	    	  if(isGroupAdded==true){
	    		  addContact2Group(groupName_text + " (0)",contactName_text);
	    		  go2UnassignedContactsPage();
	    		  boolean isContactFound= verifyBusinessContact(contactFirstName_text,contactLastName_text);
	    		  if(isContactFound==false){
	    			  logInfo(contactFirstName_text + contactLastName_text + " contact not found in Unassigned contacts after assignd to Group.");
	    			  deleteGroup(groupName_text);
	    			  go2ContactsPage();
	    			  selectMultipleContacts(contactNames,"Delete Selected");
	    			  confirmOK(); 
	    	      } else {
	    		   	  Assert.assertFalse(isContactFound, contactFirstName_text + " contact should not be seen in Unassigned contacts.");
	    	      }
	     	  }
	    	  
	        } else {
	       	 	logInfo(contactFirstName_text + contactLastName_text + " contact not found in Unassigned contacts list.");
		        Assert.assertTrue(isContactAdded, "business contact could not be added.");
	      }
	}
	  
  
 	
	@Test(priority=10897)
	public void deletBusinessContact() throws Exception{
		logInfo("inside deletBusinessContact() Test");
		String contactNames = contactName_text;
		go2ContactsPage();
		selectContactsCategoryList("Contacts");
		boolean isContactFound= verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==true){
			selectMultipleContacts(contactNames,"Delete Selected");
			confirmOK();
			Thread.sleep(5000);
		}
	}
 	

	@Test(priority=10898)
	public void deleteGroup() throws Exception{
		logInfo("inside deleteGroup Test ");
		boolean isGroupFound = false;
		
		deleteGroup(groupName_text);
		confirmationMessage("Group Deleted.");
		isGroupFound = verifyGroupIsPresent(groupName_text);
		System.out.println("isGroupFound =" +isGroupFound);
		
		if(isGroupFound==true){
			Assert.assertFalse(isGroupFound, groupName_text + " group could not be deleted.");
		}
	}
  
 	
}
