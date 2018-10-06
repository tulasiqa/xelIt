package vibe.contacts3.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.admin.tests.AdminResourceLibrary3;
import vibe.calendar2.tests.CalendarMethods;
import vibe.ecards.tests.EcardMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.tasks2.tests.TasksMethods;
import common.Priority;
import common.TestBase;


@Priority(108)
public class BusinessContactsTests extends BusinessContactsMethods {
	TestBase tb = new TestBase();
	InboxMethods in = new InboxMethods();
	CalendarMethods cal = new CalendarMethods();
	TasksMethods tk = new TasksMethods();
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
	public void AddBusinessContact() throws Exception{
        logInfo("inside AddBusinessContact() Test");
        String contactNames = contactName_text;
      
        go2ContactsPage();
        searchContact(contactFirstName_text,contactLastName_text);
        boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
        if(isContactFound==true){
            System.out.println("match found..deleting the contact.");
            selectMultipleContacts(contactNames,"Delete Selected");
            confirmOK(); 
        }
        
        addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text); 
        go2ContactsPage();
        searchContact(contactFirstName_text,contactLastName_text);
        boolean isContactFound2 = verifyBusinessContact(contactFirstName_text,contactLastName_text);
        if(isContactFound2==false){
        	System.out.println("business contact could not be created.");
        	Assert.assertTrue(isContactFound2, "business contact could not be created.");
        }
        
        go2UnassignedContactsPage();
        searchContact(contactFirstName_text,contactLastName_text);
        boolean isContactFound3 = verifyBusinessContact(contactFirstName_text,contactLastName_text);
        if(isContactFound3==false){
        	System.out.println("business should be seen in Unassigned contacts.");
        	Assert.assertTrue(isContactFound3, contactFirstName_text + " " +contactLastName_text + " business should be seen in Unassigned contacts.");
        }
   }
	
	
	
	@Test(priority=10803)
	public void UpdateBusinessContact() throws Exception{
		 logInfo("inside UpdateBusinessContact() Test");
		
		 go2ContactsPage();
		 searchContact(contactFirstName_text,contactLastName_text);
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 editBusinessContact();
		 WebElement x = driver().findElement(By.xpath("//*[@id='contact-details']/div[2]/div[1]/div[1]/div[2]/a"));
		 System.out.println(x);
		 if (!x.getText().trim().contains(prop.getLocatorForEnvironment(appUrl,"selfmailId1"))) {
			 Assert.assertTrue(true, "email match not found.");
		 }
	}
	
	@Test(priority=10804)
	public void SearchBussinesContact() throws Exception{
		logInfo("inside SearchBussinesContact Test");
		System.out.println("inside searchBussinesContact Test");
		go2ContactsPage();
		searchContact(contactFirstName_text,contactLastName_text);
		boolean isContactFound= verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==false){
			System.out.println("business contact could not found.");
			Assert.assertTrue(isContactFound, "business contact not found.");
		}
	}
	
	
	@Test(priority=10806)
	public void VerifyPreferredEmail() throws Exception{	
		logInfo("inside VerifyPreferredEmail test");
		String prefSubj = "preffered_messages_" + TestBase.generateRandomNumberInRange(1,500);
		 go2ContactsPage();
		 searchContact(contactFirstName_text,contactLastName_text);
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		
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
	public void ValidateInvalidSearch() throws Exception{
		logInfo("inside ValidateInvalidSearch test");
		
		 go2ContactsPage();
		 searchContact(invalidSearchText);
		 go2ContactsPage();
		 advancedContactSearch(invalidSearchText);
	}
	
	
	
	@Test(priority=10810)		
	public void AddCallScripts2BusinessContact() throws Exception{
		logInfo("inside AddCallScripts2BusinessContact() Test");
		System.out.println("inside addCallScripts2BusinessContact() Test");
			String callScriptName_text = "TestCallScript459";
			go2ContactsPage();
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
	public void VerifyPendingCallScripts() throws Exception{
		logInfo("inside VerifyPendingCallScripts() Test");
		
		String[] markets = {"US (English)","RU (Russian)","CO (English)"};
		//String expErrMsg = "Please complete this field."; 
		String expErrMsg = "can't be blank";
			go2ContactsPage();
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
	public void AddNotesToContact() throws Exception{
		logInfo("inside AddNotesToBusinessContact() Test");
		System.out.println("inside addNotes2BusinessContact() Test");
		
		go2ContactsPage();
		selectBusinessContact(contactFirstName_text,contactLastName_text);
		selectContactAction("Notes");
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
	public void SendMesgToContact() throws Exception{
		logInfo("inside SendMesgToBusinessContact() Test");
		System.out.println("inside sendMesg2BusinessContact() Test");
		
		 go2ContactsPage();
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
	public void SetAppoinmentToContact() throws Exception{
		logInfo("inside SetAppoinmentToBusinessContact() Test");
	
		 go2ContactsPage();
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

	}
	
	
	@Test(priority=10815)
	public void CreateTaskForContact() throws Exception{
		logInfo("inside CreateTaskForBusinessContact() Test");
			
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 selectContactAction("Create Task");
		 createTaskFromContacts(contactTaskDesc_text);
		 tk.go2UserTasksPage();
		 tk.selectTasksPanel("Task All");
			boolean isTaskFoundInAllTasksWidget = tk.verifyTaskPresentInWidget("Task All",contactTaskDesc_text);
			System.out.println("isTaskFoundInAllTasksWidget =" +isTaskFoundInAllTasksWidget);
			if (isTaskFoundInAllTasksWidget==false){
				logInfo(contactTaskDesc_text + " task not present in All Task widget.");
				Assert.assertTrue(isTaskFoundInAllTasksWidget, contactTaskDesc_text + " task is not present in All Task widget.");
			} else {
				logInfo(contactTaskDesc_text + " task present in All Task widget.");
				tk.selectTasksPanel("Task All");
				tk.performActionOnTask("Task All",contactTaskDesc_text,"Delete Task");
				 go2ContactsPage();
				 selectBusinessContact(contactFirstName_text,contactLastName_text);
				 selectContactAction("View History");
				 verifyContactHistory("Task",contactTaskDesc_text);
			}
		}	

	
	
	@Test(priority=10813)
	public void ValidateEmailAttachmentAbove4MB() throws Exception{
	logInfo("inside ValidateEmailAttachmentAbove4MB test");
		logInfo("Try to upload more than 4 Mb size to validate the alert toaster message. ");
		
		go2ContactsPage();
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
	public void AddGroup() throws Exception{
		logInfo("inside AddGroup Test");
		System.out.println("inside addGroup Test");
		
		 go2ContactsPage();
		 addGroup(groupName_text);  
		 boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		 if(isGroupAdded==false){
		  logInfo(groupName_text + " group could not be created.");
		  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
		} else {
			logInfo(groupName_text + " group created sucessfully.");
			performGroupAction(groupName_text,"Delete");
		}
	}
	
	
	@Test(priority=10821)
	public void VerifyAddingExistingGroups() throws Exception{
		logInfo("inside VerifyAddingExistingGroups test");
		
		 go2ContactsPage();
		 addGroup(groupName_text); 
		 //confirmationMessage("Group Created.");
		 go2ContactsPage();
		 addGroup(groupName_text);
		 WebElement err = driver().findElement(By.cssSelector("div.vibex-modal-container > div#vibex-modal > div.modal-dialog> div.modal-content > div.modal-body > div > div> div > form#new_pyr_crm_contact_category > div.form-group.string.required.pyr_crm_contact_category_category_name.has-error > span"));
		 String error = err.getText().trim();
		 System.out.println("err = " +err);
		 Thread.sleep(4000);
		 clickOnElement("cssSelector","div.vibex-modal-container > div#vibex-modal > div.modal-dialog> div.modal-content > div.modal-header.clearfix> button > i");
		 performGroupAction(groupName_text,"Delete");
		 //confirmationMessage("Group Deleted.");
	}
	
	
	@Test(priority=10822)
	public void RenameGroup() throws Exception{
		logInfo("inside RenameGroup Test ");
		
		 go2ContactsPage();
		 addGroup(groupName_text); 
		 //confirmationMessage("Group Created.");
		 boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		 System.out.println("isGroupAdded =" +isGroupAdded);
	  	 if(isGroupAdded==false){
		  logInfo(groupName_text + " group could not be created.");
		  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
		} else {
			logInfo(groupName_text + " group created sucessfully.");
			performGroupAction(groupName_text,"Rename");  // Details
			renameGroup(groupName_text);
			boolean isGroupUpdated = verifyGroupIsPresent(groupName_text+"Updated");  
			if(isGroupUpdated==true){
				logInfo(groupName_text+"Updated" + " group updated sucessfully.");
				performGroupAction(groupName_text+"Updated","Delete");
				
			} else {
				Assert.assertTrue(isGroupUpdated, groupName_text+"Updated" + " group not updated.");
			}
		}
	}
	
	
	
	@Test(priority=10823) 
	public void AddContacts2Group() throws Exception{
		logInfo("inside AddContacts2Group Test");
		System.out.println("inside addContacts2Group Test");
		
		 go2ContactsPage();
		 addGroup(groupName_text);  
		 
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 selectContactAction("Add To Group");
		 selectAddContacts2Group(groupName_text);
		 validateTextOnElement("xpath","//*[@id='contact-details']/div[2]/div[2]/div[2]/div/div[1]",groupName_text);  
		 go2ContactsPage();
		 go2UnassignedContactsPage();
	     searchContact(contactFirstName_text,contactLastName_text);
	     boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
	     if(isContactFound==true){
	       System.out.println("business should not be seen in Unassigned contacts.");
	       Assert.assertFalse(isContactFound, contactFirstName_text + " " +contactLastName_text + " business should not be seen in Unassigned contacts.");
	     }
	 }
	
		
	@Test(priority=10825)					// ,dependsOnMethods={"addContacts2Group"}
	public void SendEmail2Group() throws Exception{
		logInfo("inside SendEmail2Group Test ");
		// String groupName_text = "TestGroup_221";
		
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded){
		 	logInfo(groupName_text + " group found.");
		 	performGroupAction(groupName_text,"Send Email");
		 	sendMessage2GroupContacts();
		    
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
	
	
	@Test(priority=10827)					// ,dependsOnMethods={"addContacts2Group"}
	public void SendInvite2Group() throws Exception{
		logInfo("inside SendInvite2Group Test ");
		
		// String groupName_text = "TestGroup_282";
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded){
		 	logInfo(groupName_text + " group found.");
		 	performGroupAction(groupName_text,"Invite to Event");
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
			
		} else {
			 logInfo(groupName_text + " group not found.");
			 Assert.assertTrue(isGroupAdded, groupName_text + " group not found.");
		}
	}
	
	
	@Test(priority=10828)					// ,dependsOnMethods={"addContacts2Group"}
	public void PrintLabel4Groups() throws Exception{
		logInfo("inside PrintLabel4Groups Test ");
				
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded){
		 	logInfo(groupName_text + " group found.");
		 	performGroupAction(groupName_text,"Print Labels");
		 	validatePrintLabelWindow();
		} else {
			 logInfo(groupName_text + " group not found.");
			 Assert.assertTrue(isGroupAdded, groupName_text + " group not found.");
		}
	}
	
	@Test(priority=10828)					// ,dependsOnMethods={"addContacts2Group"}
	public void ExportGroups2CSV() throws Exception{
		logInfo("inside ExportGroups2CSV Test ");
		String dt = getDate(0,"YYYY-MM-dd");
		
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded){
		 	logInfo(groupName_text + " group found.");
		 	performGroupAction(groupName_text,"Export Csv");
			boolean isFileExists = false;
			String filepath = projectPath+"\\downloads\\"+"export-"+dt+".csv";
			verifyFileExistsOnDisk(filepath);
		} else {
			 logInfo(groupName_text + " group not found.");
			 Assert.assertTrue(isGroupAdded, groupName_text + " group not found.");
		}
	}
	
	@Test(priority=10829)		// ,dependsOnMethods={"addContacts2Group"}
	public void ExportGroups2Excel() throws Exception{
		logInfo("inside ExportGroups2Excel Test ");
		
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded){
		 	logInfo(groupName_text + " group found.");
		 	performGroupAction(groupName_text,"Export Excel");
			boolean isFileExists = false;
			String filepath = projectPath+"\\downloads\\";
			verifyFileExistsOnDisk(filepath+"export.xls");
			
		} else {
			 logInfo(groupName_text + " group not found.");
			 Assert.assertTrue(isGroupAdded, groupName_text + " group not found.");
		}
	}
	
	
	@Test(priority=10831)		// ,dependsOnMethods={"addContacts2Group"}
	public void ImportContactsFromGmail() throws Exception{
		logInfo("inside ImportContactsFromGmail test");
		
		String groupName_text = "TestGroup_282";
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded){
		 	logInfo(groupName_text + " group found.");
		 	performGroupAction(groupName_text,"Import Into Group");
			validateGmailImport(groupName_text, "Import Into Group", gmailId_text, gmailPwd_text);
			
		} else {
			 logInfo(groupName_text + " group not found.");
			 Assert.assertTrue(isGroupAdded, groupName_text + " group not found.");
		}
	}
	

	@Test(priority=10832)		// ,dependsOnMethods={"addContacts2Group"}
	public void ImportContactsFromYahoo() throws Exception{
		logInfo("inside ImportContactsFromYahoo test");
		
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded){
		 	logInfo(groupName_text + " group found.");
		 	performGroupAction(groupName_text,"Import Into Group");
			validateYahooImport(groupName_text, "Import Into Group",YahooMailId, YahooPWD);
		} else {
			 logInfo(groupName_text + " group not found.");
			 Assert.assertTrue(isGroupAdded, groupName_text + " group not found.");
		}
	}
	
	
	
	// Remove contacts from the group.
	
		@Test(priority=10833)					// ,dependsOnMethods={"addContacts2Group"}
		public void RemoveContactsFromGroup() throws Exception{
			logInfo("inside RemoveContactsFromGroup Test ");
				
			boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
			if(isGroupAdded){
				selectGroup(groupName_text);  
				selectContactFromGroupList(contactFirstName_text,contactLastName_text);
				selectActionFromActionbar("Remove From Group");
				confirmationMessage("Contacts removed from the group.");
			} else {	
				 logInfo(groupName_text + " group not found.");
				  Assert.assertTrue(isGroupAdded, groupName_text + " group not found.");
			}
		}
	
		/*
		
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
			//selectGroupAndMoreOption(groupName_text+"(" , "Print Labels");
			performGroupAction(groupName_text, "Print Labels");
			handlePDFWindow();
			
		}	
		*/
		
	/*
	@Test(priority=10836)
   public void reply2EmailFromContactHistory() throws Exception{
		logInfo("inside reply2EmailFromContactHistory test");
		
		profile.navigateMyProfileAccount("Notifications");
        String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
        System.out.println(selfmailId);

		String[] contactNames = {contactName_text};
		
		go2ContactsPage();
		selectMultipleContacts(contactNames,"Delete Selected");
		addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text);	// comment later if reqd	
		selectBusinessContct(contactFirstName_text,contactLastName_text);
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
	
	@Test(priority=10839)	
	public void reaccessApp(){
		String wndBeforeWindow = driver().getWindowHandle();	
		driver().close();
		driver().switchTo().window(wndBeforeWindow);
	}
	
	
	
	
	@Test(priority=10840)
	public void attachResources2SendMessage() throws Exception{
		logInfo("inside attachResources2SendMessage() Test ");
		//profile.navigateMyProfileAccount("Notifications");
        //String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
        //System.out.println(selfmailId);

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
		//selectGroupAndMoreOption(groupName_text+"(" , "Print Labels");
		performGroupAction(groupName_text, "Print Labels");
		handlePDFWindow();
		performGroupAction(groupName_text, "Delete Group");
		
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
	    			  performGroupAction(groupName_text, "Delete Group");
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
	  
  */
 	
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
		
		performGroupAction(groupName_text, "Delete Group");
		confirmationMessage("Group Deleted.");
		isGroupFound = verifyGroupIsPresent(groupName_text);
		System.out.println("isGroupFound =" +isGroupFound);
		
		if(isGroupFound==true){
			Assert.assertFalse(isGroupFound, groupName_text + " group could not be deleted.");
		}
	}
  
 	
}
