package vibe.contacts3.tests;

import java.util.List;
import java.util.Set;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.testng.Assert;

import vibe.calendar2.tests.CalendarMethods;
import vibe.ecards.tests.EcardMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.tasks.tests.TaskMethods;
import common.TestBase;
import common.EnvProperties;

public class BusinessContactsMethods extends TestBase {
	Logger logger = Logger.getLogger(BusinessContactsMethods.class);
	// public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 	
	 
	 EnvProperties prop = new EnvProperties();
	 CommunityMethods cm = new CommunityMethods();
	 TaskMethods tm = new TaskMethods();
	 EcardMethods em = new EcardMethods();
	
	public void go2ContactsPage() throws Exception{
		logInfo("inside go2ContactsPage() method.");
		driver().navigate().to(appUrl + "crm/v2/contacts/all");
		Thread.sleep(5000);
	}
	
		
	public void selectContactsCategoryList(String listName) throws  Exception{
		logInfo("inside selectContactsCategoryList() method.");
		
		switch(listName){
		case "Recent Contacts":
			waitOnElement("partialLinkText",prop.getLocatorForEnvironment(appUrl,"lnkRecentContacts"));
			clickOnElement("partialLinkText",prop.getLocatorForEnvironment(appUrl,"lnkRecentContacts"));
			Thread.sleep(4000);
			break;
		case "Recent Leads":
			waitOnElement("partialLinkText","Recent Leads (");
			clickOnElement("partialLinkText","Recent Leads (");
			Thread.sleep(4000);
			break;	
		case "Contacts":
			waitOnElement("partialLinkText","Contacts (");
			clickOnElement("partialLinkText","Contacts (");
			Thread.sleep(4000);
			break;	
		case "Team Contacts":
			waitOnElement("partialLinkText","Team Contacts (");
			clickOnElement("partialLinkText","Team Contacts (");
			Thread.sleep(4000);
			break;	
		case "Notes Added in Last 7 days":
			waitOnElement("partialLinkText","Notes Added in Last 7 days (");
			clickOnElement("partialLinkText","Notes Added in Last 7 days (");
			Thread.sleep(4000);
			break;	
		case "Project Simplify Participants":
			waitOnElement("partialLinkText","Project Simplify Participants (");
			clickOnElement("partialLinkText","Project Simplify Participants (");
			Thread.sleep(4000);
			break;	
		case "Assigned":
			waitOnElement("partialLinkText","Assigned (");
			clickOnElement("partialLinkText","Assigned (");
			Thread.sleep(4000);
			break;
		case "Unassigned":
			waitOnElement("partialLinkText","Unassigned (");
			clickOnElement("partialLinkText","Unassigned (");
			Thread.sleep(4000);
			break;	
		case "Registered":
			waitOnElement("partialLinkText","Registered (");
			clickOnElement("partialLinkText","Registered (");
			Thread.sleep(4000);
			break;
		}
	}
	
		
	public void go2adminPage() throws Exception{
		logInfo("inside go2adminPage() method. ");
		driver().navigate().to(appUrl + "pyr_core/pyr_admin/dashboard");
	}

	public void go2CallScriptPage() throws Exception{
		logInfo("inside go2adminPage() method. ");
		driver().navigate().to(appUrl + "pyr_core/pyr_admin/dashboard");
		verifyLinkPresent("Marketing");
		clickOnLink("linkText", "Marketing");
		verifyLinkPresent("Call Scripts");
		clickOnLink("linkText", "Call Scripts");
	}
	
	
	public void addBusinessContacts(String fName, String lName, String email) throws Exception{
		logInfo("inside addBusinessContacts() method. ");
		System.out.println("inside addBusinessContacts() method. ");
		go2ContactsPage();

		waitOnElement("xpath","//div[@class='topbar']/div[1]/a/span");
		clickOnElement("xpath", "//div[@class='topbar']/div[1]/a/span");
		Thread.sleep(10000);
		waitOnElement("cssSelector",inputContactFirstName);
		inputText("cssSelector",inputContactFirstName,fName);  
		inputText("cssSelector",inputContactLastName,lName);    
		inputText("cssSelector",inputContactEmail,email);
		selectFromDropdown("cssSelector","#pyr_crm_contact_contact_phone_numbers_attributes_0_dialing_code","byVisibleText","IN-91");
		inputTextClear("cssSelector","#phone_number");
		inputText("cssSelector","#phone_number",prop.getLocatorForEnvironment(appUrl, "txtPhNumber")); // contactPhone_text
		selectFromDropdown("cssSelector","#pyr_crm_contact_contact_phone_numbers_attributes_0_label","byIndex",1);
		
		/*WebElement element = driver().findElement(By.cssSelector(drpdnContactPhoneType));
		Select select = new Select(element);
		List<WebElement> allItems =select.getOptions();
		int size = allItems.size();
	//	System.out.println("count of items =" +size);
		select.selectByIndex(size-1);*/
		
		selectFromDropdown("cssSelector",drpdnContactType,"byVisibleText",contactType_text);
		selectFromDropdown("cssSelector",drpdnContactInterestLevel,"byVisibleText",contactInterestLevel_text);
		inputText("cssSelector",inputContactAddr1,contactAddr1_text);
		inputText("cssSelector",inputContactAddr2,contactAddr2_text);
		inputText("cssSelector",inputContactCity,contactCity_text);
		
		inputText("cssSelector",inputContactPostalCode,contactPostalCode_text);
		selectFromDropdown("cssSelector",drpdnContactCntry,"byVisibleText",contactCntry_text);
		selectFromDropdown("cssSelector",drpdnContactState,"byVisibleText",contactState_text);
		inputText("cssSelector",inputContactGenInfo,contactGenInfo_text);
			
		logInfo("Entered all the fields in Business -> AddContact page.");
		
		scrollDown("xpath",btnUpdateContact);
		clickOnButton("xpath",btnUpdateContact);
		Thread.sleep(6000); 
	}
	
		
	public boolean verifyBusinessContact(String fName, String lName) throws Exception{
        logInfo("inside verifyBusinessContact() method.");
        System.out.println("inside verifyBusinessContact() method1.");
        
        String expVal = fName + " " + lName;
        expVal = expVal.trim();
        
        boolean isContactFound = false;
        
        waitOnElement("cssSelector","div.contacts-index-list.actions.summary > div#index-contacts-list > div#contacts_selected");
        WebElement panel = driver().findElement(By.cssSelector("div.contacts-index-list.actions.summary > div#index-contacts-list > div#contacts_selected"));
        List allContacts = panel.findElements(By.cssSelector("div.contact-list-item.list-result.media"));
        System.out.println("Total Business Contacts =" + allContacts.size());
        
        if(allContacts.size()>0) {
    	   
    	   String before_contactName = "//div[@class='contacts-index-list actions summary']/div[@id='index-contacts-list']/div[@id='contacts_selected']/div[";
    	   String after_contactName = "]/div/div/div[2]/div[1]/a/span[2]";
    	   
    	   for(int i=1;i<=allContacts.size();i++){
             WebElement e = driver().findElement(By.xpath(before_contactName+i+after_contactName));
             String actText = e.getText().trim();
                   
            // System.out.println("actText =" +actText);
             if(actText.equalsIgnoreCase(expVal)){
            	  System.out.println("Verify Busniess contact match found =" +actText);
            	  System.out.println("expVal =" +expVal);
                  isContactFound = true;
                  logInfo(expVal + " contact match found.");
                  break;
              }
            }
        }     
         return isContactFound;
         
		}
	
	public boolean verifyBusinessContact(String email) throws Exception{
        logInfo("inside verifyBusinessContact() method.");
        System.out.println("inside verifyBusinessContact() method2.");
        
        boolean isContactFound = false;
        
        waitOnElement("cssSelector","div.contacts-index-list.actions.summary > div#index-contacts-list > div#contacts_selected");
        WebElement panel = driver().findElement(By.cssSelector("div.contacts-index-list.actions.summary > div#index-contacts-list > div#contacts_selected"));
        List allContacts = panel.findElements(By.cssSelector("div.contact-list-item.list-result.media"));
        System.out.println("Total Business Contacts =" + allContacts.size());
        
        if(allContacts.size()>0) {
    	  
    	   String before_contactEmail = "//div[@class='contacts-index-list actions summary']/div[@id='index-contacts-list']/div[@id='contacts_selected']/div[";
    	   String after_contactEmail = "]/div/div/div[2]/div[2]";
    	   
    	   for(int i=1;i<=allContacts.size();i++){
             WebElement e = driver().findElement(By.xpath(before_contactEmail+i+after_contactEmail));
             String actEmail = e.getText().trim();
                   
            
             if(actEmail.equalsIgnoreCase(email)){
            	 System.out.println("contact matched actText =" +actEmail + "email =" +email);
                // System.out.println("email =" +email);
                  isContactFound = true;
                  logInfo(email + " business contact match found.");
                  break;
              }
            }
        }     
         return isContactFound;
         
		}
		
	
	public void searchContact(String fName, String lName) throws  Exception{
		logInfo("inside searchContact() method. ");
		System.out.println("inside searchContact() method. ");
		 String expVal = fName + " " + lName;
		 
		waitOnElement("cssSelector","div.search.input-group > input");
		inputTextClear("cssSelector","div.search.input-group > input");
		inputText("cssSelector","div.search.input-group > input", expVal.trim());
		clickOnElement("cssSelector","div.search.input-group > a");
		Thread.sleep(10000);
	}
	
	public void searchContact(String Name) throws  Exception{
		logInfo("inside searchContact() method. ");
		System.out.println("inside searchContact() method. ");
				
		waitOnElement("cssSelector","div.search.input-group > input");
		inputTextClear("cssSelector","div.search.input-group > input");
		inputText("cssSelector","div.search.input-group > input", Name.trim());
		clickOnElement("cssSelector","div.search.input-group > a");
		Thread.sleep(10000);
	}
	
	
	 public void selectBusinessContact(String fName, String lName) throws Exception{
		 logInfo("inside selectBusinessContact() method.");
		 System.out.println("inside selectBusinessContact() method");
		  
		 String expVal = fName + " " + lName;
	        expVal = expVal.trim();
	        
	        boolean isContactFound = false;
	        
	        waitOnElement("cssSelector","div.contacts-index-list.actions.summary > div#index-contacts-list > div#contacts_selected");
	        WebElement panel = driver().findElement(By.cssSelector("div.contacts-index-list.actions.summary > div#index-contacts-list > div#contacts_selected"));
	        List allContacts = panel.findElements(By.cssSelector("div.contact-list-item.list-result.media"));
	        System.out.println("Total Business Contacts =" + allContacts.size());
	        
	        if(allContacts.size()>0) {
	    	   
	    	   String before_contactName = "//div[@class='contacts-index-list actions summary']/div[@id='index-contacts-list']/div[@id='contacts_selected']/div[";
	    	   String after_contactName = "]/div/div/div[2]/div[1]/a/span[2]";
	    	   
	    	   for(int i=1;i<=allContacts.size();i++){
	             WebElement e = driver().findElement(By.xpath(before_contactName+i+after_contactName));
	             String actText = e.getText().trim();
	                   
	            // System.out.println("actText =" +actText);
	             if(actText.equalsIgnoreCase(expVal)){
	            	  System.out.println("Select Busniess contact match found =" +actText);
	            	  isContactFound = true;
	            	  e.click();
	                  logInfo(expVal + " business contact match found to select.");
	                  break;
	              }
	            }
	        }     
	 }
	 
	
	 public void deleteBusinessContact(String fName, String lName) throws Exception{
		  logInfo("inside deleteBusinessContact() method.");
		  System.out.println("inside deleteBusinessContact() method.");
		  go2ContactsPage();
		  selectContactsCategoryList("Recent Contacts");
		  selectBusinessContact(contactFirstName_text,contactLastName_text);
		  
		  waitOnElement("linkText","Delete");
		  clickOnLink("linkText", "Delete");
		  System.out.println("clicked delete contact button");
						
		  Thread.sleep(5000);
		  (new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);
		  (new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);
		  System.out.println("clicked confirm delete button");
		  logInfo("clicked on confirm delete button.");
		  // Thread.sleep(5000);
		  confirmationMessage("Contact Deleted.");
		  
		  String contactsPanel = "//*[@id='all-contacts-list']/div[1]/form";
		  waitOnElement("xpath",contactsPanel);
	  }
		  
	
/*	public void createCallScript(String scriptName) throws Exception{
		 go2adminPage();
		 go2CallScriptPage();
	
		 clickOnLink("linkText","Add New Script");
		 
		 verifyElementPresent("xpath",inputCallScriptName);
		 inputText("xpath",inputCallScriptName,scriptName);
		 selectFromDropdown("xpath",drpdnCallScriptStatus,"byVisibleText",callScriptStatus_text);
	//	 composeTextOnCallScript();
		 composeText("xpath",composeBody,composeBodyText);
		 clickOnElement("xpath",divCallScriptMarketLang);
		 WebElement chkAll = driver().findElement(By.xpath(inputMarketAll));
		 if(!chkAll.isSelected()){
			chkAll.click();
		 }
		 
		 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");   
		 Calendar cal = Calendar.getInstance();
		 String dd = dateFormat.format(cal.getTime());
		 publishedOn_text = dd.trim();
		 publishedOn_text = "01/01/2016 12:00 PM";
		 publishedEndDate_text = "10/10/2020 12:00 PM";
		 System.out.println("Pulish start date: " +publishedOn_text);
		 System.out.println("Pulish end date: " +publishedEndDate_text);
		 inputTextClear("xpath",inputPublishedOn);
		 inputText("xpath",inputPublishedOn,publishedOn_text);
		 inputTextClear("xpath",inputPublishedEndDate);
		 inputText("xpath",inputPublishedEndDate,publishedEndDate_text);
		// saveAndProceed();
		 clickOnButton("cssSelector",btnSubmitCallscript);
		// waitOnElement("linkText","Back To Call Scripts");
		 Thread.sleep(5000);
		 waitOnElement("linkText","back to office");
		 clickOnLink("linkText","back to office");
		 Thread.sleep(5000);
	 }
	 
	*/
	
	 public void createCallScript(String scriptName, String status, String[] markets, String startDate, String endDate) throws Exception{
		 go2adminPage();
		 go2CallScriptPage();
	
		 clickOnLink("linkText","Add New Script");
		 
		 inputTextClear("xpath",inputCallScriptName);
		 inputText("xpath",inputCallScriptName,scriptName);
		 selectFromDropdown("xpath",drpdnCallScriptStatus,"byVisibleText",status);
		 
		 switch(status){
		 case "Active":
			 composeText("xpath",callScriptComposebody,composeBodyText);
		 case "Pending":
			 composeText("xpath",callScriptComposebody,composeBodyText);
		 }
		 
		 
		 WebElement chkAll = driver().findElement(By.xpath(inputMarketAll));
		 if(!chkAll.isSelected()){
			chkAll.click();
		 }
		 
		 // Click Publish On icon
		 clickOnElement("cssSelector","div.form-group.datetime_picker.required.pyr_crm_call_script_publish_on > div.input-group > span > button > span");
		 clickOnElement("cssSelector"," a > span.glyphicon.glyphicon-remove");  // Close link
		
		 
		 clickOnElement("xpath",inputPublishedEndDate);
		 inputTextClear("xpath",inputPublishedEndDate);
		 inputText("xpath",inputPublishedEndDate,endDate);
		
		 clickOnButton("cssSelector",btnSubmitCallscript);   
		 Thread.sleep(10000);
		 // confirmationMessage("Call Script is created");
	 }
	 
	 
	 public void deleteCallScript(String scriptName) throws Exception{
		 logInfo("inside deleteCallScript() method.");
		 go2adminPage();
		 go2CallScriptPage();
	
		 waitOnElement("xpath","//*[@id='call_scripts_list']/tbody");
		 WebElement ecallscripttbl = driver().findElement(By.xpath("//*[@id='call_scripts_list']/tbody"));
		 List allRows = ecallscripttbl.findElements(By.tagName("tr"));
		 
		 String before_name = "//*[@id='call_scripts_list']/tbody/tr[";
		 String after_name = "]/td[1]/a";
		 String before_delete = "//*[@id='call_scripts_list']/tbody/tr[";
		 String after_delete = "]/td[6]/a[2]";
		 		 
		 for(int i=1;i<=allRows.size();i++){
			 WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
			 String strCallscriptName = e.getText().trim();
			 if(scriptName.equalsIgnoreCase(strCallscriptName)){
				 WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
				 delete.click();
				 confirmOK();
				 Thread.sleep(5000);
				 break;
			 }
		 }
	 }
	 
	 public boolean verifyCallScriptFound(String scriptName) throws Exception{
		 logInfo("inside verifyCallScriptFound() method.");
		 go2adminPage();
		 go2CallScriptPage();
		 
		 // verify for zero rows
		 boolean isCallScriptFound = false;
		 
		 WebElement zeroRows = driver().findElement(By.xpath("//*[@id='call_scripts_list']/tbody/tr"));
		 List allColumns = zeroRows.findElements(By.tagName("td"));
		 if(allColumns.size()<=1){
			 logInfo( scriptName + " call script match found.Table has no rows.");
			 isCallScriptFound = false;
		 } else {
			 
		 waitOnElement("xpath","//*[@id='call_scripts_list']/tbody");
		 WebElement ecallscripttbl = driver().findElement(By.xpath("//*[@id='call_scripts_list']/tbody"));
		 List allRows = ecallscripttbl.findElements(By.tagName("tr"));
		 
		 
		 String before_name = "//*[@id='call_scripts_list']/tbody/tr[";
		 String after_name = "]/td[1]/a";
				 		 
		 for(int i=1;i<=allRows.size();i++){
			 WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
			 String strCallscriptName = e.getText().trim();
			 if(scriptName.equalsIgnoreCase(strCallscriptName)){
				 isCallScriptFound=true;
				 logInfo( scriptName + "call script match found in admin page");
				 break;
			 }
		 }
		
	 }
		return isCallScriptFound;
	 }

	 

	 public void submitCallScript(String scriptName, String scriptNotes) throws Exception {
		 logInfo("inside submitCallScript() method.");
		 System.out.println("inside submitCallScript() method.");
		 Thread.sleep(4000);
		 waitOnElement("cssSelector",drpdnPhoneScript);
		 selectFromDropdown("cssSelector",drpdnPhoneScript,"byVisibleText",scriptName);  
		 Thread.sleep(4000);
		 inputText("cssSelector",textareaPhoneScriptNotes,scriptNotes);
		 selectRadioOrCheckbox("cssSelector",radioPhoneScriptInterest);
		 clickOnButton("cssSelector",inputSubmitCallscript);
		 Thread.sleep(5000);
		 System.out.println("clicked save call script button");
	 }
	 
	 public void addNotes2Contacts(String fName, String lName, String notes) throws Exception{
		  logInfo("inside addNotes2Contacts() method.");
		  System.out.println("inside addNotes2Contacts() method.");
			
		  	 waitOnElement("linkText","New Note");	
			 clickOnLink("linkText","New Note");
			 waitOnElement("cssSelector",inputAddContactNotes);
			 inputTextClear("cssSelector",inputAddContactNotes);
			 inputText("cssSelector",inputAddContactNotes, addContactNotes_text);	
			 clickOnButton("cssSelector",btnCreateContactNote);
			 Thread.sleep(5000);
			 logInfo("clicked on create note button.");
		}

	 
	 public boolean verifyNotesIsPresent(String notes) throws Exception{
		  logInfo("inside verifyNotesIsPresent() method.");
		  System.out.println("inside verifyNotesIsPresent() method.");
		  
		  waitOnElement("xpath","//div[@class='contact-details-notes']");
		  WebElement notesPane = driver().findElement(By.xpath("//div[@class='contact-details-notes']/div[@id='notes-list']"));
		 // List allRows = notesPane.findElements(By.className("row"));
		  List allRows = notesPane.findElements(By.cssSelector("div.contact-note.row"));
		  int all_rows = allRows.size();
		  System.out.println("Total Rows = " +all_rows);
		  String before_text = "//div[@class='contact-details-notes']/div[@id='notes-list']/div[";
		  String after_text = "]/div[3]/div";
		  boolean isMatchFound = false;
		  if(all_rows>=0){
			  for(int i=1;i<=all_rows;i++){
				  WebElement x = driver().findElement(By.xpath(before_text+i+after_text));
				  String strNotes = x.getText().trim();
				  System.out.println(strNotes);
				  if(strNotes.equalsIgnoreCase(notes)){
					  logInfo(strNotes + " notes match found.");
					  isMatchFound = true;
					  break;
				  }
			  }
		  }

		  return isMatchFound;
	 }
	 
	 
	 public void manageContactNotes(String notes, String mode) throws Exception{
		  logInfo("inside deleteContactNotes() method.");
		  System.out.println("inside deleteContactNotes() method.");
		  		  
		  clickOnElement("cssSelector",lnkNotes);
		  
		  waitOnElement("xpath","//div[@class='contact-details-notes']");
		  WebElement notesPane = driver().findElement(By.xpath("//div[@class='contact-details-notes']/div[@id='notes-list']"));
		  List allRows = notesPane.findElements(By.cssSelector("div.contact-note.row"));
		  int all_rows = allRows.size();
		  System.out.println("Total Rows = " +all_rows);
		 
		  String before_text = "//div[@class='contact-details-notes']/div[@id='notes-list']/div[";
		  String after_text = "]/div[3]/div";
		  
		  String before_delete = "//div[@class='contact-details-notes']/div[@id='notes-list']/div[";
		  String after_delete = "]/div[2]/a[2]";
		  
		  String before_edit = "//div[@class='contact-details-notes']/div[@id='notes-list']/div[";
		  String after_edit = "]/div[2]/a[1]";
				  
		  boolean isMatchFound = false;
		  if(all_rows>=0){
			  for(int i=1;i<=all_rows;i++){
				  WebElement x = driver().findElement(By.xpath(before_text+i+after_text));
				  String contactNotes = x.getText().trim();
				  System.out.println(contactNotes);
				  if(contactNotes.equalsIgnoreCase(notes)){
					  logInfo(notes + " contact notes match found.");
					  isMatchFound = true;
					 
					  switch(mode){
						case "Delete":
					 	  WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
						  delete.click();
						//  confirmEventDeleteModal();
						  confirmOK();
						  break;
						case "Edit": 
					      WebElement edit = driver().findElement(By.xpath(before_edit+i+after_edit));
					      edit.click();
						  break;
					  }
					  break;
				  }
			  }
		  }
	 }
	 
	 
	 public void sendMessage2Contacts(String fName, String lName, String recepients) throws Exception{
		 logInfo("inside sendMessage2Contacts() method.");
		 System.out.println("inside sendMessage2Contacts() method.");
		 
		/* go2ContactsPage();
		 selectContactsCategoryList("Recent Contacts");
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 selectContactAction("Send Message");
		 */

		 waitOnElement("cssSelector",recipientsTo);
		 clickOnElement("cssSelector",recipientsTo);
		 inputText("cssSelector",recipientsTo, recepients);
		 inputText("cssSelector",subject_Mail, inputSendMessageSubject_text);
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new Message to Contacts");
		 clickOnLink("linkText", "Send");
	 }
	 
	 
	 public void sendMessage2GroupContacts() throws Exception{
		 logInfo("inside sendMessage2GroupContacts() method.");
		 System.out.println("inside sendMessage2GroupContacts() method.");
		
		 inputText("cssSelector",subject_Mail, "messages 2 group");
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new message to Group Contacts");
		 clickOnLink("linkText", "Send");
	 }
	 
	 public void setAppointmentWithContacts(String eventName) throws Exception{
		 logInfo("inside setAppointmentWithContacts() method.");
		 System.out.println("inside setAppointmentWithContacts() method.");
		 
		 	try{
				waitOnElement("xpath",inputEventName);
				inputTextClear("xpath",inputEventName);
				inputText("xpath",inputEventName,eventName);
				//selectFromDropdown("xpath", dpdnEventType, "byIndex", 1);
				selectFromDropdown("xpath", dpdnCalendarType, "byIndex", 1);
				//inputTextClear("xpath",inputEventStartDate);
				//inputText("xpath",inputEventStartDate,newEventStartdate_text);
				//inputTextClear("cssSelector","#pyr_crm_event_start_time_field"); // Start Time
				//inputText("cssSelector","#pyr_crm_event_start_time_field","01:00 AM");
				//inputTextClear("xpath",inputEventEndDate);
				//inputText("xpath",inputEventEndDate, newEventEnddate_text);
				//inputTextClear("cssSelector","#pyr_crm_event_end_time_field"); // End Time
				//inputText("cssSelector","#pyr_crm_event_end_time_field","11:00 PM");
				
				/*clickOnElement("xpath", newEventMoreotions);
				inputText("xpath",inputEventDescription,calendarEventDescription_text);
				inputText("xpath",inputEventLoc, calendarEventLocation_text);
				inputText("xpath",inputEventAddr1, calendarEventAddr1_text);*/
				scrollDown("xpath","//*[@id='submit-form']");
				clickOnElement("xpath","//*[@id='submit-form']");
	    	    
			 } catch(Exception ex){
					logInfo("Unable to create an event.");
					Assert.assertTrue(false, eventName + " Unable to create an event");
				}
	
	 		}
	 
	 

	  public void createTaskFromContacts(String taskName) throws Exception{
		 logInfo("inside createTaskFromContacts() method.");
		 System.out.println("inside createTaskFromContacts() method.");
		 
		 waitOnElement("xpath",taskTitle );
		 inputTextClear("xpath",taskTitle );
		 inputText("xpath",taskTitle ,contactTaskDesc_text);
		
		 inputTextClear("xpath",inputTaskDuedate);
		 inputText("xpath",inputTaskDuedate,taskDuedate_text);
		 
		 selectFromDropdown("xpath",drpdnTaskPriority,"byVisibleText",taskPriority_text);
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new task");
//		 composeTextOnElement();
		 clickOnButton("xpath",inputAddTask);
		 logInfo("clicked on Add Task button in Contacts page.");
		 
	 }
	 
	
	 // Groups related methods.
	 
	 public void go2Groups() throws  Exception{
		 logInfo("inside go2Groups() method.");
	
		 driver().navigate().to(appUrl + "crm/v2/contacts/all");
		 Thread.sleep(5000);
	 }
	  
	  public void selectGroupPanel(String panelItemName) throws  Exception {
		  logInfo("inside selectGroupPanel() method.");
		  
		  waitOnElement("xpath","//div[@id='list-results']");
		  WebElement panel = driver().findElement(By.xpath("//div[@id='list-results']"));
		  List allItems = panel.findElements(By.className("list-result"));
		  System.out.println("Total Group Items =" +allItems.size());
		  
		  String before_grp = "//div[@id='list-results']/div[";
		  String after_grp = "]/a/span[2]";
		  boolean isMatchFound = false;
		  
		  for(int i=1;i<=allItems.size();i++) {
			 
			  WebElement grp = driver().findElement(By.xpath(before_grp+i+after_grp));
			  String item = grp.getText().trim();
			  System.out.println("item = " +item);
			 if(item.contains(panelItemName)) {
				  logInfo(panelItemName + " item found in the Group Panel");
				  isMatchFound = true;
				  grp.click();
				  Thread.sleep(5000);
				  break;
			  } 
		  }
		 
		  if(isMatchFound==false) {
			  logInfo(panelItemName + " item not found in the Group Panel");
			  Assert.assertTrue(isMatchFound, panelItemName + " item not found in the Group Panel");
		  }
		  
	  }
	 
	 public void closeManageGroupsDialog() throws  Exception{
		 logInfo("inside closeManageGroupsDialog() method.");
		 System.out.println("inside closeManageGroupsDialog() method.");
		
		 clickOnElement("cssSelector",btnCloseGroup);
		}
	 
	 public void addGroup(String groupName) throws  Exception{
		 logInfo("inside addGroup() method.");
		 System.out.println("inside addGroup() method.");
		
		 
		// selectGroupPanel("Groups"); 
		 boolean  isGroupAdded = false;
		 
		 waitOnElement("cssSelector","div.col-md-4.col-xs-4.landing-action:nth-child(2) > a > span"); // Add New Group
		 clickOnElement("cssSelector","div.col-md-4.col-xs-4.landing-action:nth-child(2) > a > span");
		 Thread.sleep(5000);
		
		 inputTextClear("cssSelector", "div.modal-content > div.modal-body > div > div > div > form > div.form-group.string.required.pyr_crm_contact_category_category_name > input");  // inputGroupName
		 inputText("cssSelector", "div.modal-content > div.modal-body > div > div > div > form > div.form-group.string.required.pyr_crm_contact_category_category_name > input",groupName);
		 clickOnElement("cssSelector","div.modal-content > div.modal-body > div > div > div > form > div.add-contact-btn > input"); 
		 Thread.sleep(5000);
		 
		 WebElement x = driver().findElement(By.cssSelector("div.panel-column.panel-main-body.col-md-3.scrollable > div:nth-child(1) > div:nth-child(2)"));
		 String actGroupName = x.getText().trim();
		 System.out.println("groupName = " +groupName);
		 System.out.println("actGroupName = " +actGroupName);
		 if(actGroupName.contains(groupName)) {
			 logInfo(groupName + " Group is created");
			 isGroupAdded = true;
		 } else {
			 logInfo(groupName + " Group is not created");
			// Assert.assertTrue(isGroupAdded, groupName + " Group is not created");
		 }
	 }
	 
	 
	 // Veriy group is present.
	 
	 public boolean verifyGroupIsPresent(String grpName) throws  Exception{
		 logInfo("inside verifyGroupIsPresent() method.");
		 System.out.println("inside verifyGroupIsPresent() method.");
		 
		 go2ContactsPage();
		 selectGroupPanel("Groups ("); 		
		 
		 boolean isMatchFound = false;
		 waitOnElement("cssSelector","div#group-manager-list");
		 WebElement tblGroup = driver().findElement(By.cssSelector("div#group-manager-list"));
		 List allGroups = tblGroup.findElements(By.cssSelector("div.group-list-item.clearfix"));
		 int total_groups = allGroups.size();
		 System.out.println("Total groups = " +total_groups);
		 
		 String before_group = "//div[@id='group-manager-list']/div[";
		 String after_group = "]/div/div/div[2]/div[1]/a";
		 System.out.println("grpName =" +grpName);
		 
		// for(int i=2;i<=total_groups*2;i=i+2){
		 for(int i=total_groups*2;i>=2;i=i-2){
			 WebElement grp = driver().findElement(By.xpath(before_group+i+after_group));
			 String groupName = grp.getText().trim();
			 System.out.println("Group Name = " +groupName);
			 if(groupName.equalsIgnoreCase(grpName)){
				 isMatchFound = true;
				 logInfo(grpName + " group found in Manage Groups.");
				break;
			 }
		 }
		 
		 if(isMatchFound==false){
			 logInfo(grpName + " group not found in Manage Groups.");
		 }
		 
		return isMatchFound;
	 }
	 
	
	 public void addContact2Group(String grpName, String contact) throws  Exception{
		 logInfo("inside addContact2Group() method.");
		 System.out.println("inside addContact2Group() method.");
		 
		 go2ContactsPage();
		 selectContactsCategoryList("Recent Contacts");
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 
		 /*Thread.sleep(5000);
		 waitOnElement("xpath",lnkAdd2Group);
		 clickOnElement("xpath",lnkAdd2Group);
		 */
		 
		// selectContactAction("Add To Group");
		 selectContactAction(prop.getLocatorForEnvironment(appUrl,"addtoGrp"));
		 selectGroup2Add(groupName_text);
		
	 }
	 
	 public void selectGroup2Add(String grpName) throws  Exception{
		 logInfo("inside selectGroup2Add() method.");
		 System.out.println("inside selectGroup2Add() method.");
		 
		 waitOnElement("xpath","//*[@id='contact-details-panel']/div[@id='groups-list']");
		 WebElement grpPanel = driver().findElement(By.xpath("//*[@id='contact-details-panel']/div[@id='groups-list']"));
		  List allGrps = grpPanel.findElements(By.className("group-list-item"));
		 // List allGrps = grpPanel.findElements(By.cssSelector(".group-list-item"));
		 System.out.println("Total Groups = " +allGrps.size());
		 
		 String before_grpName = "//*[@id='contact-details-panel']/div[@id='groups-list']/div[";
		 String after_grpName = "]/div[1]/span[2]";
		 
		 String before_grpAdd = "//*[@id='contact-details-panel']/div[@id='groups-list']/div[";
		 String after_grpAdd = "]/div[2]/form/a[1]";
		 
		 boolean isGrpFound = false;
		 System.out.println("expGrpName =" + grpName.trim());
		 
		 for(int i=1;i<=allGrps.size();i++){
			 WebElement eName = driver().findElement(By.xpath(before_grpName+i+after_grpName));
			 WebElement addGrp = driver().findElement(By.xpath(before_grpAdd+i+after_grpAdd));
			 String actGrpName = eName.getText().trim();
			 System.out.println("actGrpName =" + actGrpName);
			 if(actGrpName.contains(grpName)){
				 // eName.click();
				 addGrp.click();
				 isGrpFound = true;
				 System.out.println(grpName + " group match found to add.");
				 WebElement add = driver().findElement(By.xpath(before_grpAdd+i+after_grpAdd));
				 add.click();
				 break;
			 }
		 }
	 }
	 
/*	 public void selectGroupAndMoreOption(String groupName, String moreOption) throws Exception{
		 logInfo("Select particular Group under Manage Group and click 'More' to import");
		 WebElement more;
		 
		 go2ContactsPage();
		 selectGroupPanel("Groups ("); 		
		 boolean isGroupFound = false;
		
		 Thread.sleep(2000);
		 List <WebElement> gr = driver().findElements(By.cssSelector(groupList));
		 System.out.println("Manage Size is "+ gr.size());
		 
		 String beforeGrpName ="div#group-manager-list > div:nth-child(" ;
		 String afterGrpName =") > div.col-md-9 > div > a";
		 
		 String beforeGrpRename = "div#group-manager-list > div:nth-child(" ;
		 String afterGrpRename = ") > div.view-tools.col-md-3 > a:nth-child(1)";
		 
		 String beforeGrpDelete = "div#group-manager-list > div:nth-child(" ;
		 String afterGrpDelete = ") > div.view-tools.col-md-3 > a:nth-child(2)";
		 
		 String beforeGrpMore ="div#group-manager-list > div:nth-child(" ;
		 String afterGrpMore =") > div.view-tools.col-md-3 > a:nth-child(3)";
		 
		 String beforeGrp_Import2Groups ="div#group-manager-list > div:nth-child(" ;
		 String afterGrp_Import2Groups =") > div.view-tools.col-md-3 > ul > li:nth-child(1) > a";
		 
		 String beforeGrp_PrintLbls ="div#group-manager-list > div:nth-child(" ;
		 String afterGrp_PrintLbls =") > div.view-tools.col-md-3 > ul > li:nth-child(2) > a";
		
		 String beforeGrp_exportCSV ="div#group-manager-list > div:nth-child(" ;
		 String afterGrp_exportCSV =") > div.view-tools.col-md-3 > ul > li:nth-child(3) > a";
		
		 String beforeGrp_exportExcel ="div#group-manager-list > div:nth-child(" ;
		 String afterGrp_exportExcel =") > div.view-tools.col-md-3 > ul > li:nth-child(4) > a";
		
		 
		 for (int i=2; i<=gr.size(); i+=2){
			 System.out.println("i =" +i);
			 
			 WebElement gpName = driver().findElement(By.cssSelector(beforeGrpName+i+afterGrpName));	
			 String actGrp = gpName.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.contains(groupName)){
				 isGroupFound = true;
				 
				 	switch(moreOption){
					case "Import Into Group" :
						more = driver().findElement(By.cssSelector(beforeGrpMore+i+afterGrpMore));	
						more.click();
						WebElement import2grp = driver().findElement(By.cssSelector(beforeGrp_Import2Groups+i+afterGrp_Import2Groups));	
						import2grp.click();
					    break;
					case "Print Labels" :
						more = driver().findElement(By.cssSelector(beforeGrpMore+i+afterGrpMore));	
						more.click();
						WebElement printlbl = driver().findElement(By.cssSelector(beforeGrp_PrintLbls+i+afterGrp_PrintLbls));	
						printlbl.click();
						Thread.sleep(5000);
						//handlePDFWindow();
					    break;    
					case "Export Csv" :
						more = driver().findElement(By.cssSelector(beforeGrpMore+i+afterGrpMore));	
						more.click();
						WebElement exportcsv = driver().findElement(By.cssSelector(beforeGrp_exportCSV+i+afterGrp_exportCSV));	
						exportcsv.click();
					    break;  
					case "Export Excel" :
						more = driver().findElement(By.cssSelector(beforeGrpMore+i+afterGrpMore));	
						more.click();
						WebElement exportexcel = driver().findElement(By.cssSelector(beforeGrp_exportExcel+i+afterGrp_exportExcel));	
						exportexcel.click();
					    break;   
				 	}
			 }	
			 
		 }	 if (isGroupFound==false){
			Assert.assertTrue(isGroupFound, groupName + " is not found in the lis of Manage Groups."); 
			 
		 }
	 }
*/
	 
	 
	 public void performGroupAction(String groupName, String actionName) throws Exception{
		 logInfo("inside deleteGroup() method");
		 
		 go2ContactsPage();
		 selectGroupPanel("Groups ("); 		
		
		 boolean isMatchFound = false;
		 waitOnElement("cssSelector","div#group-manager-list");
		 WebElement tblGroup = driver().findElement(By.cssSelector("div#group-manager-list"));
		 List allGroups = tblGroup.findElements(By.cssSelector("div.group-list-item.clearfix"));
		 int total_groups = allGroups.size();
		 System.out.println("Total groups = " +total_groups);
		 
		 String before_group = "//div[@id='group-manager-list']/div[";
		 String after_group = "]/div/div/div[2]/div[1]/a";
		 String after_grpIcon = "]/div/div/div[2]/div[3]/a[2]/i";
		 String after_grpPanel = "]/div/div/div[2]/div[3]/ul";
		 String mid_li = "]/div/div/div[2]/div[3]/ul/li[";
		 String after_li = "]/a";
		 
		 for(int i=total_groups*2;i>=2;i=i-2){
			 WebElement grp = driver().findElement(By.xpath(before_group+i+after_group));
			 String actGroupName = grp.getText().trim();
			 System.out.println("actGroupName = " +actGroupName);
			 
			 if(groupName.equalsIgnoreCase(actGroupName)){
				 isMatchFound = true;
				 logInfo(actGroupName + " group found in Manage Groups.");
				 scrollDown("xpath",before_group+i+after_grpIcon);
				 WebElement btn = driver().findElement(By.xpath(before_group+i+after_grpIcon));
				 scrollDown("xpath",before_group+i+after_grpIcon);
				 btn.click();
				 Thread.sleep(3000);
				 
				 WebElement panel =  driver().findElement(By.xpath(before_group+i+after_grpPanel));
				 List allItems = panel.findElements(By.tagName("li"));
				 for(int j=1;j<=allItems.size();j++) {
					 WebElement x = driver().findElement(By.xpath(before_group+i+mid_li+j+after_li));
					 String item = x.getText().trim();
					 if(item.equalsIgnoreCase(actionName)) {
						 x.click();
						 if(actionName.equalsIgnoreCase("Delete")) {
							 Thread.sleep(3000);
							 confirmOK();
						 }
						 break;
					 }
				 }
				 
				 break;
			 }
			 
		 }
		 
		 if(isMatchFound==false){
			 logInfo(groupName + " group not found in Manage Groups.");
		 }
		 
	 }	
	 
	
	 
	 public void renameGroup(String groupName) throws Exception{
		 logInfo("inside renameGroup() method");
		 System.out.println("inside renameGroup() method");
		 String renameGroup_text = groupName+"Updated";
		 
		 waitOnElement("cssSelector","div.panel-column.panel-main-body.col-md-3.scrollable > div:nth-child(1) > div.row > a:nth-child(1) > i");
		 clickOnElement("cssSelector","div.panel-column.panel-main-body.col-md-3.scrollable > div:nth-child(1) > div.row > a:nth-child(1) > i");
		  
		 Thread.sleep(2000);
		 inputTextClear("cssSelector","#pyr_crm_contact_category_category_name");
		 inputText("cssSelector","#pyr_crm_contact_category_category_name",renameGroup_text);
		 clickOnElement("xpath","//*[@id='new_pyr_crm_contact_category']/div[3]/input[@value='Update']");
		 Thread.sleep(5000);		
	 }
	 
	 
	 public void selectGroup(String grpName) throws  Exception{
		 logInfo("inside selectGroup() method");
		 
		 WebElement grpCategory = driver().findElement(By.cssSelector("div#group-manager-list "));
		 List <WebElement> allGroups = grpCategory.findElements(By.cssSelector("div.group-list-item.clearfix"));
		 System.out.println("Manage Size is "+ allGroups.size());
		 boolean isGroupFound = false;
		 
		 String beforeGpLi ="//div[@id='group-manager-list']/div[" ;
		 String afterGpLi ="]/div[@class='media-body']/div/div[2]/div[1]/a";
		 int all_groups = allGroups.size()*2;
		 for (int i=2; i<=all_groups; i=i+2){
			 WebElement gpList = driver().findElement(By.xpath(beforeGpLi+i+afterGpLi));	
			 String actGrp = gpList.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.equalsIgnoreCase(grpName)){
				 isGroupFound = true;
				 logInfo(grpName + " group found");
				 gpList.click();
				 Thread.sleep(5000);
				 logInfo("clicked on group " + grpName);
				 break;
			 }
		 }	 
			 
		 if (isGroupFound==false){
				Assert.assertTrue(isGroupFound, grpName + " not found in Manage Groups."); 
		 }
	 }
	 
	
	 public void selectContactFromGroupList(String fName, String lName) throws  Exception {
		 logInfo("inside selectContactFromGroupList() method");
		 
		 waitOnElement("xpath","//div[@id='contacts_selected']");
		 WebElement contactsPanel = driver().findElement(By.xpath("//*[@id='contacts_selected']"));
		 List allContacts = contactsPanel.findElements(By.cssSelector("div.contact-list-item.list-result.media"));
		 boolean isMatchFound = false;
		 
		 String before_contact = "//*[@id='contacts_selected']/div[";
		 String after_contact = "]/div/div/div[2]/div[1]/a/span[2]";
		 String before_input = "//*[@id='contacts_selected']/div[";
		 String after_input = "]/div/div/div[3]/input";
				 
		 if(allContacts.size()>0) {
			 for(int i=1;i<=allContacts.size();i++) {
				 WebElement e = driver().findElement(By.xpath(before_contact+i+after_contact));
				 String name = e.getText().trim();
				 if(name.equalsIgnoreCase(fName+" "+lName)) {
					 WebElement input = driver().findElement(By.xpath(before_input+i+after_input));
					 input.click();
					 isMatchFound = true;
					 break;
				 }
			 }
		 }
		 
		 if(isMatchFound==false) {
			 logInfo(fName+" "+lName + " contact not found.");
			 Assert.assertTrue(isMatchFound, fName+" "+lName + " contact not found.");
		 }
	 }
	 
	 
	 public void selectActionFromActionbar(String actionName) throws  Exception {
		 logInfo("inside selectActionFromActionbar() method");
		 
		 waitOnElement("xpath","//*[@id='contacts-bulk-actions-bar']/div[2]");
		 WebElement actionbar = driver().findElement(By.xpath("//*[@id='contacts-bulk-actions-bar']/div[2]/ul"));
		 List allActions = actionbar.findElements(By.tagName("li"));
		 String before_action = "//*[@id='contacts-bulk-actions-bar']/div[2]/ul/li[";
		 String after_action = "]/a";
		 
		 for(int i=1;i<=allActions.size();i++) {
			 WebElement action =  driver().findElement(By.xpath(before_action+i+after_action));
			 String actAction = action.getText().trim();
			 System.out.println("actAction = " +actAction);
			 if(actAction.equalsIgnoreCase(actionName)) {
				 action.click();
				 Thread.sleep(3000);
				 if(actionName.equalsIgnoreCase("Remove From Group")) {
					 Robot rb = new Robot();
					 rb.keyPress(KeyEvent.VK_ENTER);
					 rb.keyRelease(KeyEvent.VK_ENTER);
					 Thread.sleep(3000);
				 }
				break;
			 }
		 }
	 }
	 
	 public boolean verifyFileExistsOnDisk(String filepath){
		 logInfo("inside verifyFileExistsOnDisk() method");
		 
		 File file = new File(filepath);
		 boolean isFileExists = false;
		 
		 System.out.println("filepath =" + filepath);
		 if(file.exists()) {
			 logInfo("File exists");
			 isFileExists= true;
		 } else {
			 logInfo("File does not exists");
			 isFileExists= false;
			 Assert.assertTrue(isFileExists,"File does not exists");
		 }
		 
		return isFileExists;
	 }
	 
	 
	public void searchContactsInGroup(String contactName, String grpName) throws  Exception{
		logInfo("inside searchContactsInGroup() method");
		
		 go2ContactsPage();
		 selectGroupPanel("Groups ("); 		
		
		boolean isGroupFound = false;
			
		 Thread.sleep(2000);
		 List <WebElement> gr = driver().findElements(By.cssSelector(groupList));
		
		 System.out.println("Manage Size is "+ gr.size());
		 String beforeGpLi ="div#group-manager-list > div:nth-child(" ;
		 String afterGpLi =") > div:nth-child(1)";  // remove > div > a 
		
		 
		 for (int i=1; i<=gr.size(); i++){
			 WebElement eleGroup = driver().findElement(By.cssSelector(beforeGpLi+i+afterGpLi));	
			 String actGrp = eleGroup.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.contains(grpName)){
				 logInfo(actGrp + " group match found in the groups table.");
				 isGroupFound = true;
				 String afterGpLia =") > div:nth-child(1) > div > a";
				 WebElement lnk = driver().findElement(By.cssSelector(beforeGpLi+i+afterGpLia));
				 lnk.click();
				 break;
			 }
		 }
	}
	
	
		
	// Select contact from contacts list

			public void selectMultipleContacts(String contactNames,  String actionName) throws  Exception{
		        logInfo("inside selectMultipleContacts() methods");
		        System.out.println("inside selectMultipleContacts() methods");
		        boolean isContactMatchFound = false;
		        
		        waitOnElement("cssSelector","div.contacts-index-list.actions.summary > div#index-contacts-list > div#contacts_selected");
		        WebElement panel = driver().findElement(By.cssSelector("div.contacts-index-list.actions.summary > div#index-contacts-list > div#contacts_selected"));
		        List allContacts = panel.findElements(By.cssSelector("div.contact-list-item.list-result.media"));
		        System.out.println("Total Business Contacts =" + allContacts.size());
		        
		        String before_contactName = "//div[@class='contacts-index-list actions summary']/div[@id='index-contacts-list']/div[@id='contacts_selected']/div[";
		    	String after_contactName = "]/div/div/div[2]/div[1]/a/span[2]";
		    	   
		    	//String before_contactEmail = "//div[@class='contacts-index-list actions summary']/div[@id='index-contacts-list']/div[@id='contacts_selected']/div[";
		    	//String after_contactEmail = "]/div/div/div[2]/div[2]";
		    	   
		    	String before_contactInput = "//div[@class='contacts-index-list actions summary']/div[@id='index-contacts-list']/div[@id='contacts_selected']/div[";
		    	String after_contactInput = "]/div/div/div[3]/input";
		    	
		    	String before_contactsAction = "#contacts-bulk-actions-bar > div.col-md-7 > ul > li:nth-child(";
		    	String after_contactsAction = ") > a";
		    	
		    	 
		        if(allContacts.size()<=0){
		        	System.out.println("No contacts match found");
		        	
		            // WebElement eNoContacts = driver().findElement(By.xpath("//*[@id='all-contacts-list']/div[@id='list-body']/form/div[@class='alert alert-danger']"));
		            //System.out.println("is NoContactsFound =" +eNoContacts.isDisplayed());
		        }
		        
		    	if(allContacts.size()>0) {
		              for(int i=1;i<=allContacts.size();i++){
		                        WebElement eName = driver().findElement(By.xpath(before_contactName+i+after_contactName));
			                    String actContact = eName.getText().trim();
			                    
			                   if(actContact.equalsIgnoreCase(contactNames)){
			                        System.out.println("contact matched...");
			                        isContactMatchFound = true;
			                        WebElement eInput = driver().findElement(By.xpath(before_contactInput+i+after_contactInput));
			                        eInput.click();
			                        System.out.println("selected contact");
			                    }   
			            }
		              
		                 // toggle action
		              
		              		WebElement contactActions = driver().findElement(By.cssSelector("#contacts-bulk-actions-bar > div.col-md-7 > ul"));
		              		List allActions = contactActions.findElements(By.tagName("li"));
		              		for(int k=1; k<=allActions.size();k++){
		                        String before_ActionName = "//div[@id='contacts-bulk-actions-bar']/div[@class='col-md-7']/ul/li[";
		                        String after_ActionName = "]/a";
		                        WebElement action = driver().findElement(By.xpath(before_ActionName+k+after_ActionName));
		                        String actActionName = action.getText().trim();
		                        if(actActionName.equalsIgnoreCase(actionName)){
		                        	action.click();
		                            break;
		                        }
		                    }
		                    
		               }
		        }
	
	
	// Verify Contact History
			
	public void verifyContactHistory(String category, String subject) throws  Exception {
		logInfo("inside verifyTaskInContactHistory() method");
		boolean isSubjectFound = false;
		
		waitOnElement("xpath","//*[@id='history-items']");
		WebElement e = driver().findElement(By.xpath("//*[@id='history-items']"));
		List allRows = e.findElements(By.className("row"));
		if(allRows.size()==1) {
			logInfo("No subject found");
		}
		
		if(allRows.size()>1) {
			String before_histsubj = "//*[@id='history-items']/div[";
			String after_event_task_histsubj = "]/div[2]/a";
			String after_histsubj = "]/div[2]";
			
			for(int i=2;i<=allRows.size(); i++) {
				if(category.equalsIgnoreCase("Task") || category.equalsIgnoreCase("Event") ) {
					WebElement x = driver().findElement(By.xpath(before_histsubj+i+after_event_task_histsubj));
					String actsubj = x.getText().trim();
					if(actsubj.equalsIgnoreCase(subject)) {
						logInfo(subject + " subject found in contact history.");
						isSubjectFound = true;
						break;
					}
				} else {
					WebElement x = driver().findElement(By.xpath(before_histsubj+i+after_histsubj));
					String actsubj = x.getText().trim();
					if(actsubj.equalsIgnoreCase(subject)) {
						logInfo(subject + " subject found in contact history.");
						isSubjectFound = true;
						break;
					}
				}
			}
			
			if(isSubjectFound==false) {
				logInfo(subject + " history match not found");
				Assert.assertTrue(isSubjectFound, subject + " history match not found");
			}
		}
		
		//return isSubjectFound;
		
	}
			
	
	// Select task from contact history.
	
	public void selectTaskInContactHistory(String subject) throws  Exception {
		logInfo("inside verifyTaskInContactHistory() method");
		boolean isSubjectFound = false;
		
		waitOnElement("xpath","//*[@id='history-items']");
		WebElement e = driver().findElement(By.xpath("//*[@id='history-items']"));
		List allRows = e.findElements(By.className("row"));
		if(allRows.size()==1) {
			logInfo("No subject found");
		}
		
		if(allRows.size()>1) {
			String before_subj = "//*[@id='history-items']/div[";
			String after_subj = "]/div[2]/a";
			for(int i=2;i<=allRows.size(); i++) {
				WebElement subj = driver().findElement(By.xpath(before_subj+i+after_subj));
				String act_subject = subj.getText().trim();
				if(act_subject.equalsIgnoreCase(subject)) {
					logInfo(subject + " subject found in contact history.");
					clickOnElement("xpath",before_subj+i+after_subj);
					Thread.sleep(3000);
					waitOnElement("xpath","//a[contains(text(),'Delete Task')]");
					clickOnElement("xpath","//a[contains(text(),'Delete Task')]");
					Thread.sleep(3000);
					confirmOK();
					Thread.sleep(3000);
					logInfo("clicked on Delete Task button");
					break;
				}
			}
		}
	}
	
	
	// Verify Action Items 
	
	/*public boolean takeActionOnContactEvents(String actionName, String actionType) throws  Exception{
		logInfo("inside takeActionOnContactEvents() method");
		boolean isContactMatchFound = false;
		
		 waitOnElement("xpath","//div[@id='actionable-list']/div[@id='actionable-list-items']");
		 WebElement e = driver().findElement(By.xpath("//div[@id='actionable-list']/div[@id='actionable-list-items']"));
	
		 WebElement eActiontbl = driver().findElement(By.xpath("//div[@id='actionable-list']/div[@id='actionable-list-items']"));
		 List allActions = eActiontbl.findElements(By.cssSelector(".row.list-item"));
		 int all_actions = allActions.size();
		
		String before_contactName = "//div[@id='actionable-list-items']/div[";
		String before_actionName = "//div[@id='actionable-list-items']/div[";
		String before_actionType = "//div[@id='actionable-list-items']/div[";
			
		if(all_actions>0){
			for(int i=1;i<=all_actions;i++){
				
				WebElement eactionName = driver().findElement(By.xpath(before_actionName+i+"]/div[2]/span[1]"));  // after_actionName
				String actActionName = eactionName.getText().trim();
				
				if(actActionName.equalsIgnoreCase(actionName)){
					isContactMatchFound = true;
					logInfo(contactName + " match found in contact home page");
					selectFromDropdown("xpath",before_actionType+i+"]/div[3]/form/select","byVisibleText",actionType);  // after_actionType
					if(actionType.equalsIgnoreCase("Delete")) {
					 confirmOK();
					}
					 Thread.sleep(3000);
					logInfo("performed action " +actionType);
					break;
				}
			}
		  }
		// }	
		
		if(isContactMatchFound==false){
			logInfo(contactName + " match not found in contact home page");
		}
		return isContactMatchFound;
	}*/
	
	

	public void viewPersonalProfilePage() throws  Exception{
		logInfo("inside viewPersonalProfilePage() method");
		waitOnElement("linkText","View Personal Profile");
		clickOnElement("linkText","View Personal Profile");
		waitOnElement("cssSelector","div.modal-content > div.modal-header.clearfix > h1#label-for-consultant-details-modal");
		WebElement header = driver().findElement(By.cssSelector("div.modal-content > div.modal-header.clearfix > h1#label-for-consultant-details-modal"));
		String expHeader = header.getText().trim();
		System.out.println("expHeader = " + expHeader);
		if(expHeader.equalsIgnoreCase("Representative Details")){
			logInfo(expHeader + " page title matched");
			
			verifyConsultantDetailsTabs("Business Dashboard");
			verifyConsultantDetailsTabs("Sales Performance");
			verifyConsultantDetailsTabs("Inquiry");
			verifyConsultantDetailsTabs("Leadership History");
			verifyConsultantDetailsTabs("Closed Camp Comp");
			verifyConsultantDetailsTabs("Reports");
			verifyConsultantDetailsTabs("Notes");
			verifyConsultantDetailsTabs("Profile");
			
			waitOnElement("xpath","//*[@id='consultant-details-modal']/div/div[1]/div[1]/button[@class='close']/i");
			clickOnElement("xpath","//*[@id='consultant-details-modal']/div/div[1]/div[1]/button[@class='close']/i");
		} else {
			logInfo(expHeader + " page title could not be matched");
			Assert.assertTrue(expHeader.equalsIgnoreCase("Representative Details"), expHeader + " page title could not be matched");
		}
	}
	
	
	public void verifyConsultantDetailsTabs(String tabName) throws  Exception{
		logInfo("inside verifyConsultantDetailsTabs() method");
		WebElement eTitle;
		String expTitle;
		
		switch(tabName){
		case "Business Dashboard":
			clickOnElement("linkText","Business Dashboard");
			waitOnElement("xpath","//*[@class='panel panel-widget kpi-widget']/div[1]/div[1]");
			eTitle = driver().findElement(By.xpath("//*[@class='panel panel-widget kpi-widget']/div[1]/div[1]"));
			expTitle = eTitle.getText().trim();
			if(expTitle.equalsIgnoreCase("Business Dashboard")){
				logInfo("Business Dashboard title matched");
			} else {
				logInfo("Business Dashboard title could not be matched");
				Assert.assertTrue(expTitle.equalsIgnoreCase("Business Dashboard"), "Business Dashboard title could not be matched");
			}
			break;
		case "Sales Performance":
			clickOnElement("linkText","Sales Performance");
			waitOnElement("xpath","//*[@id='tab-performance']/div[1]/div[1]/div[1]/table/tbody/tr[1]/th[2]");
			eTitle = driver().findElement(By.xpath("//*[@id='tab-performance']/div[1]/div[1]/div[1]/table/tbody/tr[1]/th[2]"));
			expTitle = eTitle.getText().trim();
			if(expTitle.equalsIgnoreCase("Current Balance")){
				logInfo("Current Balance element found");
			} else {
				logInfo("Current Balance element could not be found");
				Assert.assertTrue(expTitle.equalsIgnoreCase("Current Balance"), "Current Balance element could not be found");
			}
			break;
		case "Inquiry":	
			clickOnElement("linkText","Inquiry");
			waitOnElement("xpath","//*[@id='tab-inquiry']/div/div/div");
			eTitle = driver().findElement(By.xpath("//*[@id='tab-inquiry']/div/div/div"));
			expTitle = eTitle.getText().trim();
			System.out.println("Inquiry : " + expTitle);
			//Assert.assertNotEquals(expTitle,"Information is being retrieved - Please standby.");
			break;
		case "Leadership History":
			clickOnElement("linkText","Leadership History");
			waitOnElement("xpath","//*[@id='tab-leadership']/div[1]/div[1]/table/thead/tr/th");
			eTitle = driver().findElement(By.xpath("//*[@id='tab-leadership']/div[1]/div[1]/table/thead/tr/th"));
			expTitle = eTitle.getText().trim();
			if(expTitle.equalsIgnoreCase("Leadership")){
				logInfo("Leadership element found");
			} else {
				logInfo("Leadership element could not be found");
				Assert.assertTrue(expTitle.equalsIgnoreCase("Leadership"), "Leadership element could not be found");
			}
			break;
		case "Closed Camp Comp":
			clickOnElement("linkText","Closed Camp Comp");
			waitOnElement("xpath","//*[@id='tab-comparison']/div/div[1]/div[1]");
			eTitle = driver().findElement(By.xpath("//*[@id='tab-comparison']/div/div[1]/div[1]"));
			expTitle = eTitle.getText().trim();
			if(expTitle.equalsIgnoreCase("Closed Campaign Comparison")){
				logInfo("Closed Campaign Comparison element found");
			} else {
				logInfo("Closed Campaign Comparison element could not be found");
				Assert.assertTrue(expTitle.equalsIgnoreCase("Closed Campaign Comparison"), "Closed Campaign Comparison element could not be found");
			}
			break;
		case "Reports":
			waitOnElement("xpath","//*[@id='consultant-details-tab-links']/li[7]/a");
			clickOnElement("xpath","//*[@id='consultant-details-tab-links']/li[7]/a");
			break;
		
		case "Notes":	
			waitOnElement("xpath","//*[@id='consultant-details-tab-links']/li[8]/a");
			clickOnElement("xpath","//*[@id='consultant-details-tab-links']/li[8]/a");
			waitOnElement("xpath","//*[@id='tab-notes']/div[1]/div[1]");
			Thread.sleep(5000);
			eTitle = driver().findElement(By.xpath("//*[@id='tab-notes']/div[1]/div[1]"));
			expTitle = eTitle.getText().trim();
			if(expTitle.equalsIgnoreCase("Notes")){
				logInfo("Notes element found");
			} else {
				logInfo("Notes element could not be found");
				Assert.assertTrue(expTitle.equalsIgnoreCase("Notes"), "Notes element could not be found");
			}
			break;
		
		case "Profile":
			clickOnElement("linkText","Profile");
			waitOnElement("xpath","//*[@id='tab-profile']/div[1]/div[1]/table/thead/tr/th");
			eTitle = driver().findElement(By.xpath("//*[@id='tab-profile']/div[1]/div[1]/table/thead/tr/th"));
			expTitle = eTitle.getText().trim();
			if(expTitle.equalsIgnoreCase("Location Information")){
				logInfo("Location Information element found");
			} else {
				logInfo("Location Information element could not be found");
				Assert.assertTrue(expTitle.equalsIgnoreCase("Location Information"), "Location Information element could not be found");
			}
			break;
		}
	}
	
	
	public void selectGroupActions(String actionName) throws  Exception{
		logInfo("inside selectGroupActions() method.");
		
		waitOnElement("xpath","//*[@id='contacts_select_all']");
		clickOnElement("xpath","//*[@id='contacts_select_all']");
		
		//waitOnElement("xpath","//*[@id='contacts-bulk-action-dropdown']/button");
		clickOnElement("xpath","//*[@id='contacts-bulk-action-dropdown']/button");
		
		WebElement grpActions = driver().findElement(By.xpath("//ul[@id='contact-dropdown-actions']"));
		List allItems = grpActions.findElements(By.tagName("li"));
		String before_li = "//ul[@id='contact-dropdown-actions']/li[";
		String after_li = "]/a";
		if(allItems.size() >0){
			for(int i=1;i<=allItems.size();i++){
				WebElement eActionName = driver().findElement(By.xpath(before_li+i+after_li));
				String actAction = eActionName.getText().trim();
				if(actAction.equalsIgnoreCase(actionName)){
					logInfo(actionName + " action match found.");
					eActionName.click();
					Thread.sleep(5000);
					break;
				}
			}
		}
	}
	
	
	public void selectGroupsForAdd2Groups(String groupName) throws  Exception{
		logInfo("inside selectGroupsForAdd2Groups() method. ");	
		
		waitOnElement("xpath","//table[@id='listContactGroups']");
		WebElement selectGrpTbl = driver().findElement(By.xpath("//table[@id='listContactGroups']/tbody"));
		List allRows = selectGrpTbl.findElements(By.tagName("tr"));
		String before_grpName = "//table[@id='listContactGroups']/tbody/tr[";
		String after_grpName = "]/td[2]";
		String before_grpInput = "//table[@id='listContactGroups']/tbody/tr[";
		String after_grpInput  = "]/td[1]/input";
		
		if(allRows.size() >0){
			for(int i=1;i<=allRows.size();i++){
				WebElement eGrp = driver().findElement(By.xpath(before_grpName+i+after_grpName));
				String actGrp = eGrp.getText().trim();
				if(actGrp.equalsIgnoreCase(groupName)){
					WebElement input = driver().findElement(By.xpath(before_grpInput+i+after_grpInput));
					input.click();
					break;
				}
			}
		}
		
		scrollDown("xpath","//*[@id='add_to_group']");
		clickOnElement("xpath","//*[@id='add_to_group']");
		
	}
	
 // ****************************************************************************************************************************************************
 
 // ******************** RAVI's METHODS ****************************************************************************************************************
 
	
	
	public void addBussinesscontactwithoutInfo(String fName, String lName) throws Exception{
		logInfo("inside contactwithNoInfomation() method. ");			
		go2ContactsPage();		
		selectContactsCategoryList("Recent Contacts");
		waitOnElement("cssSelector","#add-a-contact>a");
		clickOnLink("cssSelector", "#add-a-contact>a");
		
		Thread.sleep(5000);
		waitOnElement("cssSelector",inputContactFirstName);
		inputText("cssSelector",inputContactFirstName,fName);  // contactFirstName_text
		inputText("cssSelector",inputContactLastName,lName);    // contactLastName_text				
		logInfo("Entered all the fields in Business -> AddContact page.");		
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(5000);
	}
	
	
	 public void verifyGroupNdDelete(String groupName) throws Exception{
		 logInfo("inside verifyGroupNdDelete method");
	
		 go2ContactsPage();
		 selectGroupPanel("Groups ("); 		
		 
		 clickOnLink("linkText", "Manage Groups");
		 boolean isGroupFound = false;
		
		 Thread.sleep(2000);
		 List <WebElement> gr = driver().findElements(By.cssSelector(groupList));
		 System.out.println("Manage Size is "+ gr.size());
		 String beforeGpLi ="div#group-manager-list > div:nth-child(" ;
		 String afterGpLi =") > div:nth-child(1)";
		 String afterdel = ") > div:nth-child(2) > a:nth-child(2)";
		 
		 for (int i=1; i<=gr.size(); i++){
			 WebElement gpList = driver().findElement(By.cssSelector(beforeGpLi+i+afterGpLi));	
			 String actGrp = gpList.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.contains(groupName)){
				 isGroupFound = true;
				 WebElement grpMoreSelect =  driver().findElement(By.cssSelector(beforeGpLi+i+afterdel));
				 grpMoreSelect.click();	
				 tm.deleteConfirm();
				/* confirmEventDeleteModal();*/
				 confirmationMessage("Group Deleted.");
				 break;
			 }			 
		 }	 if (isGroupFound==false){
			Assert.assertTrue(isGroupFound, groupName + " is not found in the list of Manage Groups."); 
			 
		 }
	 }
	 
	 public void reverifyGroup(String groupName) throws Exception{
		 logInfo("inside reverifyGroup method");	
		 clickOnLink("linkText", "Manage Groups");
		 boolean isGroupFound = false;		
		 Thread.sleep(2000);
		 List <WebElement> gr = driver().findElements(By.cssSelector(groupList));
		 System.out.println("Manage Size is "+ gr.size());
		 String beforeGpLi ="div#group-manager-list > div:nth-child(" ;
		 String afterGpLi =") > div:nth-child(1)";		 
		 for (int i=1; i<=gr.size(); i++){
			 WebElement gpList = driver().findElement(By.cssSelector(beforeGpLi+i+afterGpLi));	
			 String actGrp = gpList.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.contains(groupName)){
				 isGroupFound = false;
				 Assert.assertEquals(isGroupFound, groupName + "  - Groupp is found");
				
				 
			 }			 
		 }	 if (isGroupFound==true){
			 System.out.println("Group is not found");
			 
			 
		 }
	 }
	
	
	 public void addToGroupsList(String grpName) throws Exception{
		 logInfo("addToGroupsList(String grpName method  ");
		boolean isGrpPresent = false; 
		List <WebElement> grp =  driver().findElements(By.cssSelector(grpList));
		System.out.println("Size of groups "+ grp.size());
		Thread.sleep(2000);
		for (int i=1; i<=grp.size();i++){
			WebElement grps = driver().findElement(By.cssSelector(grpList1+i+grpList2));
			System.out.println("name are "+grps.getText().trim());
			if (grps.getText().trim().equalsIgnoreCase(grpName)){
				isGrpPresent= true;
				WebElement cb = driver().findElement(By.cssSelector(grpList1+i+grpCheckbox2)) ;
				cb.click();
				clickOnButton("cssSelector", addGrpBtn);				
				break;				
			}			
		}if (isGrpPresent==false){
			Assert.assertTrue(isGrpPresent, grpName + " is not found" );
		}
	 }
	 
	 
	 
	 public void sendMessage2PrefferedContacts(String subject) throws Exception{
		 logInfo("inside sendMessage2PrefferedContacts() method.");
		 System.out.println("inside sendMessage2PrefferedContacts() method.");
		 
		 clickOnElement("xpath",lnkSendMesg);
		 Thread.sleep(3000);
		 waitOnElement("cssSelector",subject_Mail);
		 
		 inputText("cssSelector",subject_Mail,subject );
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor, pyr_core_user_task_description')]",subject + " description.");
		 clickOnLink("linkText", "Send");
		 Thread.sleep(5000);
	 }
	
	 public void sendMessage2Contacts(String fName, String lName, String recepients,String subject) throws Exception{
		 logInfo("inside sendMessage2Contacts() method.");
		 System.out.println("inside sendMessage2Contacts() method.");
		 
		 /*go2ContactsPage();
		 selectContactsCategoryList("Recent Contacts");
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 selectContactAction("Send Message");
		 */
		
		 
		 waitOnElement("cssSelector",recipientsTo);
		 clickOnElement("cssSelector",recipientsTo);
		 inputText("cssSelector",recipientsTo, recepients);
		 inputText("cssSelector",subject_Mail,subject );
		
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","sending message to a contact");
		 clickOnLink("linkText", "Send");
		 Thread.sleep(10000);
	 }
	
	 public void selectContactActions(String contactAction) throws InterruptedException{
		 logInfo("Inside selectContactActions method");		 
		 String B4rAct = "//*[@id='contact-actions']/div[";
		 String A4rAct = "]/a/span[2]";
		 boolean isActionFound = false;
		 List<WebElement> act = driver().findElements(By.xpath(allActions));
		 System.out.println(act.size());
		 Thread.sleep(2000);
		 for (int i = 1; i<=act.size(); i++){
		     WebElement actions = driver().findElement(By.xpath(B4rAct+i+A4rAct));			 
			 String conatActions = actions.getText();
			 System.out.println(conatActions);
			 if (conatActions.contains(contactAction)){
				 isActionFound = true;
				 actions.click();
				 break;				 
			 }				 
		 }if (isActionFound){
			 Assert.assertTrue(isActionFound,contactAction + " item is not found in the Conatct Actions" );
		 }
		 
	 }

	 public void selectSocialNetwork(String socialNetwork){
		 logInfo("Inside selectSocialNetwork method");
		 
		 List <WebElement> soc = driver().findElements(By.cssSelector(socialList));
		 System.out.println(soc.size());
		 for(WebElement social :soc){
			 System.out.println(social.getText());
			 
			 if (social.getText().equalsIgnoreCase(socialNetwork)){
				 social.click();
				 break;				 
			 }			 
		 }		 
	 }
	
	 
	 public void gmailContacts(String gmail, String passwd) throws Exception{
		 logInfo ("Inside gmailContacts method");
		 logInfo("Enter Gmail credentials and Import all contacts");
			String wndBeforeWindow = driver().getWindowHandle();	
			for(String w : driver().getWindowHandles()){
					if(!w.equalsIgnoreCase(wndBeforeWindow)){
			driver().switchTo().window(w);
			Thread.sleep(3000);
				inputTextClear("cssSelector",inputGmail);
				inputText("cssSelector",inputGmail, gmail);
				clickOnButton("cssSelector", btnGmailNext);
				WebElement staysigned = driver().findElement(By.xpath(chkStaySignedIn));
				String isChecked = staysigned.getAttribute("checked").trim();
				if(isChecked.equalsIgnoreCase("checked")){
					staysigned.click();
				}
				
				inputText("cssSelector",inputGmailPasswd, passwd);
				clickOnButton("cssSelector", btnSignIn);
				Thread.sleep(6000);
				clickOnButton("xpath", "//*[@id='submit_approve_access']");
				Thread.sleep(4000);				
				WebElement gText = driver().findElement(By.cssSelector(gmailText));
				String actImportMsg = gText.getText().trim();
				String expImportMsg = "Close this window and see the import status in bell alerts.";
				String errImportMsg ="There was a problem importing contacts, please try again later or contact Customer Service.";
				System.out.println("actImportMsg = " + actImportMsg);
				
				driver().close();
				driver().switchTo().window(wndBeforeWindow);
				/*if(actImportMsg!=expImportMsg){
					Assert.assertFalse(actImportMsg!=expImportMsg, "Import Unsuccessful");
				}*/
				
			break;
			}
					
			}	 
	 }	
	 
	 
	 
	 public void YahooContacts(String yahoo, String passwd) throws Exception{
		 logInfo("Inside YahooContacts method");
		 logInfo("Enter Yahoo credentials and Import all contacts");
			String wndBeforeWindow = driver().getWindowHandle();	
			for(String w : driver().getWindowHandles()){
					if(!w.equalsIgnoreCase(wndBeforeWindow)){
			driver().switchTo().window(w);
			Thread.sleep(3000);
			
			// check if email and pwd are visible. else switch to main window
			WebElement e = driver().findElement(By.xpath(yaEmail));
			if(!e.isDisplayed()){
				driver().switchTo().defaultContent();
				logInfo("switching back to main window." + yaEmail + " element not found.");
				Assert.assertTrue(e.isDisplayed());
			}
				inputTextClear("xpath",yaEmail);
				inputText("xpath",yaEmail, yahoo);
				clickOnButton("xpath", yaNext);		
				Thread.sleep(3000);			
				
				inputText("xpath",yaPwd, passwd);
				clickOnButton("xpath", yaNext);
				Thread.sleep(3000);
				clickOnButton("cssSelector", yahooAgree);					
				Thread.sleep(6000);
				driver().close();			
				driver().switchTo().window(wndBeforeWindow);			
			break;
			}
					
		}	 
	 }	
	 
	 
	 
	 public void hotmailContacts(String hotmail, String passwd) throws Exception{
		 logInfo("Inside hotmailContacts method");		 
		 logInfo("Enter hotmail credentials and Import all contacts");
		 
			String wndBeforeWindow = driver().getWindowHandle();	
			for(String w : driver().getWindowHandles()){
					if(!w.equalsIgnoreCase(wndBeforeWindow)){
			driver().switchTo().window(w);
			Thread.sleep(3000);
				inputTextClear("cssSelector",hotEmail);
				inputText("cssSelector",hotEmail, hotmail);		
				
				inputText("cssSelector",hotPwd, passwd);
				clickOnElement("cssSelector", hotPwd);
				Thread.sleep(3000);
				clickOnButton("cssSelector", hotSign);
				String hotExpText= "Close this window and see the import status in bell alerts.";
				WebElement alertMsg = driver().findElement(By.cssSelector(hotActText));
				Assert.assertEquals(alertMsg.getText(), hotExpText);
				Thread.sleep(6000);
				driver().close();			
				driver().switchTo().window(wndBeforeWindow);						
			break;
			}
					
		}	 
	 }	
	 	 

	
	 public void eventValidate() throws Exception{
	 logInfo("inside eventValidate() Method");
		 Thread.sleep(2000);
		 inputTextClear("cssSelector", eventName);
		 Thread.sleep(2000);
		 clickOnButton("cssSelector", finalSave);
		 confirmationMessage("Please enter Title name"); 
	}
	 
	 
	 public void validatePrintLabelWindow() throws InterruptedException {
		  logInfo("inside validatePrintLabelWindow method");
		  
		  Thread.sleep(5000);
		   String parent = driver().getWindowHandle();   // parent window
		   System.out.println("parent = " +parent);
		   Set<String> childs = driver().getWindowHandles();
		   for(String x : childs) {
			   System.out.println("x = " +x);
			   if(!x.equalsIgnoreCase(parent)) {
				   driver().switchTo().window(x);
				   System.out.println("matched x = " +x);
				   validateTextOnElement("xpath","//*[@id='pageContainer1']/xhtml:div[2]/xhtml:div[1]","Test Contact");
				   break;
			   }
		   }
	 }
 
 
   public void validateGmailImport(String groupName, String moreOption, String gmail, String Pwd) throws Exception{
	   logInfo("inside validateGmailImport Method");
	 
	   waitOnElement("xpath","//div[@id='import-contacts-modal']/div/div/div/form/div/div[1]/a/div[2]");
	   clickOnElement("xpath","//div[@id='import-contacts-modal']/div/div/div/form/div/div[1]/a/div[2]");
	   Thread.sleep(5000);
	   String parent = driver().getWindowHandle();   // parent window
	   System.out.println("parent = " +parent);
	   Set<String> childs = driver().getWindowHandles();
	   for(String x : childs) {
		   System.out.println("x = " +x);
		   if(!x.equalsIgnoreCase(parent)) {
			   driver().switchTo().window(x);
			   System.out.println("matched x = " +x);
			   waitOnElement("xpath","//input[@id='identifierId']");
			   inputText("xpath","//input[@id='identifierId']","vibe.icentris@gmail.com");
			   clickOnElement("xpath","//*[@id='identifierNext']/content/span");
			   Thread.sleep(4000);
			   waitOnElement("xpath","//*[@id='password']/div[1]/div/div[1]/input[@type='password']");
			   inputText("xpath","//*[@id='password']/div[1]/div/div[1]/input[@type='password']","vibe@123");
			   clickOnElement("xpath","//*[@id='passwordNext']/content/span");
			   Thread.sleep(4000);
			   break;
		   }
	   }
   }
   
  
   
   public void validateYahooImport(String groupName, String moreOption, String yahoo, String Pwd) throws Exception{
	   logInfo("inside validateYahooImport Method");
	   
	   waitOnElement("xpath","//div[@id='import-contacts-modal']/div/div/div/form/div/div[2]/a/div[2]");
	   clickOnElement("xpath","//div[@id='import-contacts-modal']/div/div/div/form/div/div[2]/a/div[2]");
	   Thread.sleep(5000);
	   String parent = driver().getWindowHandle();   // parent window
	   System.out.println("parent = " +parent);
	   Set<String> childs = driver().getWindowHandles();
	   for(String x : childs) {
		   System.out.println("x = " +x);
		   if(!x.equalsIgnoreCase(parent)) {
			   driver().switchTo().window(x);
			   System.out.println("matched x = " +x);
			   waitOnElement("xpath","//*[@id='login-username']");
			   inputText("xpath","//*[@id='login-username']","vibe.icentris@yahoo.com");
			   clickOnElement("xpath","//input[@id='login-signin']");
			   Thread.sleep(4000);
			   clickOnElement("xpath","//*[@id='recaptcha-anchor']/div[5]");
			   Thread.sleep(4000);
			   clickOnElement("xpath","//*[@id='recaptcha-submit']");
			   Thread.sleep(4000);
			   waitOnElement("xpath","//*[@id='login-passwd']");
			   inputText("xpath","//*[@id='login-passwd']","vibe@123");
			   clickOnElement("xpath","//*[@id='login-signin']");
			   Thread.sleep(4000);
			   break;
		   }
	   }
   }

   public void validateHotmailImport(String groupName, String moreOption, String hotmail, String Pwd) throws Exception{
	   logInfo("inside validateHotmailImport Method");
	   	WebElement ac = driver().findElement(By.cssSelector(alertCount));			
	    String al = ac.getText();
		int alertsCount = Integer.parseInt(al);
		int increasedAlerts= alertsCount+1;	
		String increasedAlertsCount = Integer.toString(increasedAlerts);
		System.out.println("Normal count is "+ alertsCount);	
		//selectGroupAndMoreOption(groupName , "Import Into Group");
		performGroupAction(groupName_text,"Import Into Group");
		selectSocialNetwork("Import Hotmail");
		hotmailContacts(hotmail, Pwd);
		go2ContactsPage();	
		WebElement aftcount = driver().findElement(By.cssSelector(alertCount));	
		String afterCount = aftcount.getText();			
		System.out.println(afterCount + " and  "+ increasedAlertsCount);  		
		// Assert.assertEquals(afterCount, increasedAlertsCount);	
   
   
   }
   
   
   public void directHotmail(String hotmail, String Pwd) throws Exception{
	   logInfo("inside directHotmail Method");
	   clickOnLink("linkText", "Manage Groups");
	   clickOnLink("linkText", "Import");
	   selectSocialNetwork("Import Hotmail");
		hotmailContacts(hotmail, Pwd);
		go2ContactsPage();	  
		
   }
 
 
 public void historyItems(String historyItems) throws Exception{		 
	 logInfo("inside historyItems Method");
	 boolean isHistoryItem = false;
	 Thread.sleep(4000);
	 String beforHist = "//*[@id='history-items']/div[";
	 String afterHist = "]/div[2]";
	 List<WebElement> act = driver().findElements(By.xpath(histItems));
	 System.out.println(act.size());		 
	 for (int i = 1; i<=act.size(); i++){
	     WebElement items = driver().findElement(By.xpath(beforHist+i+afterHist));			 
		 String conatActions = items.getText();
		 System.out.println(conatActions);
		 if (conatActions.contains(historyItems)){
			 isHistoryItem= true;
			 items.click();
			 break;				 
		 			}	 
		 }if (isHistoryItem){
			 Assert.assertTrue(isHistoryItem,historyItems + " is not found in the History item" );
		 }
	 
 }
 
 public void validateInvalidAlertsforMailing(String recepients1, String recepients2) throws Exception	{	 
	 logInfo("inside validateInvalidAlertsforMailing Method");
	 
	 String alertMsgText ="Invalid email addresses, please correct: ";	
	 String alertMsgOfNoMailText = "Please add a recipient to your message.";
	 selectContactActions("Send Message");		 
	 waitOnElement("cssSelector",recipientsTo);
	 clickOnElement("cssSelector",recipientsTo);
	 inputText("cssSelector",recipientsTo, recepients1);
	 inputText("cssSelector",subject_Mail, subjectContent1);
	 clickOnLink("linkText", "Send");
	 Thread.sleep(3000);		 
	 WebElement alertMsg = driver().findElement(By.cssSelector(aletMsg));
	 System.out.println(" alert msg is "+alertMsg.getText());
	 Assert.assertEquals(alertMsg.getText(), alertMsgText+recepients1);
	 clickOnButton("cssSelector", btnPhoneScriptSave);		 
	 waitOnElement("cssSelector",recipientsTo);
	 /*clickOnElement("cssSelector",recipientsTo);*/
	 inputTextClear("cssSelector",recipientsTo);
	 inputTextClear("cssSelector",recipientsTo);
	 WebElement recp = driver().findElement(By.cssSelector(recipientsTo));
	 recp.sendKeys(Keys.BACK_SPACE);
	 recp.sendKeys(Keys.BACK_SPACE);
	 recp.sendKeys(Keys.BACK_SPACE);
	 recp.sendKeys(Keys.BACK_SPACE);
	 recp.sendKeys("\u0008");
	 recp.sendKeys("\u0008");
	 clickOnLink("linkText", "Send");
	 Thread.sleep(3000);
	 WebElement alertMsg2 = driver().findElement(By.cssSelector(aletMsg));
	 System.out.println(" alert msg is "+alertMsg2.getText());
	 Assert.assertEquals(alertMsg2.getText(), alertMsgOfNoMailText);
	 clickOnButton("cssSelector", btnPhoneScriptSave);		 
	 waitOnElement("cssSelector",recipientsTo);
	 
	 inputText("cssSelector",recipientsTo, recepients2);
	 inputTextClear("cssSelector",subject_Mail);
	 inputText("cssSelector",subject_Mail, subjectContent1);
	 clickOnLink("linkText", "Send");
	 Thread.sleep(3000);
	 confirmationMessage("Your message has been sent to "+recepients2 ); 
	 
 }
 
 public void replyNForwardMail(String recepients2, String subject) throws Exception{
	 logInfo("inside replyNForwardMail Method");
	
	String Attach1 = "Attach To Message" ;
	String Attach2 = "Insert Into Message Editor" ;    
	selectContactActions("View History");
	clickOnLink("linkText", subject);
	clickOnLink("linkText", "Reply");
	waitOnElement("cssSelector",recipientsTo);
	clickOnLink("linkText", "Send");
	Thread.sleep(3000);	 
	// confirmationMessage("Your message has been sent to "+recepients2 ); 
	/*clickOnLink("linkText", subject);
	clickOnLink("linkText", "Forward");
	Thread.sleep(4000);		
	inputText("cssSelector",recipientsTo, recepients2);
	inputTextClear("cssSelector",subject_Mail);
	inputText("cssSelector",subject_Mail, subject);		
	clickOnButton("cssSelector", attach);
	Thread.sleep(4000);	    	
	clickOnElement("xpath", browseInBr);
	Thread.sleep(5000);
	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\ProfAudio.exe");
	Thread.sleep(5000);
	cm.uploadFooter(Attach1);  	
	Thread.sleep(3000);
	clickOnLink("linkText", "Send");
	Thread.sleep(3000);	    	
	confirmationMessage("Your message has been sent to "+recepients2 ); */
	
 }
 
 
 
 public void uploadMoreThan4MBfile(String recepients, String fileName) throws Exception{
	 logInfo("inside uploadMoreThan4MBfile method.");			
		 
		 selectContactAction("Send Message");
		 waitOnElement("cssSelector",recipientsTo);
		 clickOnElement("cssSelector",recipientsTo);
		 inputText("cssSelector",recipientsTo, recepients);
		 inputText("cssSelector",subject_Mail, "Upload more than 4MB file");
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor, email_textarea')]","Upload more than 4MB file.");
		 
		 clickOnButton("xpath", "//label[contains(text(),'Attach A File')]");
	     Thread.sleep(3000);
	     Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\"+fileName+".exe");
		 Thread.sleep(15000);
		 //clickOnLink("linkText", "Send");
		 confirmationMessage(fileName+ "MoreThan4MbImg.jpg could not be saved.Max file size is 4 MB");
 	}
 
 public void validateEmailErrMsg() throws  Exception{
	 logInfo("inside validateEmailErrMsg() Method");
	 
	 	waitOnElement("cssSelector","div.alert.alert-danger > ul > li");
		String actErrMsg = getTextPresentOnElement("cssSelector","div.alert.alert-danger > ul > li");
		System.out.println("actErrMsg =" +actErrMsg);
		Assert.assertEquals(actErrMsg, "Email has already been taken");		
	 
	/* WebElement alert = driver().findElement(By.cssSelector(mailAlert));
	 String msg = "Email has already been taken";
	 Assert.assertEquals(alert.getText(), msg);*/
	 
 }
 
 public void contactSearch(String searchText) throws Exception{		
	 logInfo("inside conatctSearch() method");
	 	String expResult = "No Contacts Found";		 		 
		Thread.sleep(2000);
		inputTextClear("xpath", contSearch);
		inputText("xpath", contSearch, searchText);
		Thread.sleep(2000);
		clickOnElement("xpath", contSearch);	
		waitOnElement("cssSelector","form#contacts_selected > div.alert.alert-danger");
		WebElement searResult =  driver().findElement(By.cssSelector("form#contacts_selected > div.alert.alert-danger"));
		Assert.assertEquals(searResult.getText(), expResult);
 }
 
 
 public void advancedContactSearch(String searchText) throws Exception{		
	 logInfo("inside advancedContactSearch() method");
	 	String expResult = "No Contacts Found";		 		 
	 
	 	
	 	clickOnLink("cssSelector","div.col-md-1.col-sm-1.col-xs-4.landing-action.no-gutter");
		Thread.sleep(3000);
		clickOnElement("cssSelector","div.col-md-1.col-sm-1.col-xs-4.landing-action.no-gutter.open > ul > li > a > span");
		Thread.sleep(3000);
		inputTextClear("cssSelector", contFName);
		inputText("cssSelector",contFName,searchText);
		clickOnButton("cssSelector", advSerch);
		Thread.sleep(2000);
		WebElement searResult2 =  driver().findElement(By.cssSelector("form#contacts_selected > div.alert.alert-danger"));   // searchReslt
		Assert.assertEquals(searResult2.getText(), expResult);	
 }
 
 public void downLineSearch(String searchText) throws Exception{	
	 logInfo("inside downLineSearch(String searchText) Method");
	 	String expResult = "No Contacts Found";
	 	clickOnLink("linkText","Downline (");		 
		Thread.sleep(2000);
		inputTextClear("xpath", contSearch);
		inputText("xpath", contSearch, searchText);
		Thread.sleep(2000);
		clickOnElement("xpath", contSearch);			
		WebElement searResult =  driver().findElement(By.cssSelector("form#contacts_selected > div.alert.alert-danger"));
		Assert.assertEquals(searResult.getText(), expResult);
			 
 }
 
 public void groupSearch(String searchText) throws Exception{		
	 logInfo("inside groupSearch() method");
	 
	String expResult =  "No groups found.";
	inputTextClear("xpath", grpSearch);
	inputText("xpath", grpSearch, searchText);
	Thread.sleep(2000);
	clickOnElement("xpath", grpSearch);			
	WebElement searResult =  driver().findElement(By.cssSelector("form#contacts_selected > div.alert.alert-danger"));
	Assert.assertEquals(searResult.getText(), expResult); 
	Thread.sleep(2000);
 
 }
 
 
 public void conatctList(String FName, String Lname) throws Exception{
	 logInfo("inside conatctList(String FName, String Lname)  Method");
	 
	 
	 Thread.sleep(3000);
	 boolean ispresent = false;
	 List <WebElement> all = driver().findElements(By.xpath(allContacts));
	 System.out.println(" All Size is "+all.size());
	 for (int i = 1; i<=all.size(); i++){
		 String b4r1 = "//*[starts-with(@id,'3')]";
		 String b4r2= "]/div[";
		 String b4Con = "//div/form/div[";
		 String a4Con = "]/div[2]/div/div[2]/h4/div/a/span[2]";  //"]/div[2]/div"; ///div/a/span[2]
		 WebElement lis = driver().findElement(By.xpath(b4Con+i+a4Con));
		 System.out.println("conatcts are "+lis.getText());
		 if (lis.getText().trim().equalsIgnoreCase(FName+" "+Lname)){
			 ispresent =true;
			 String a4CheckBox ="]/div[1]/input";
			 WebElement check = driver().findElement(By.xpath(b4Con+i+a4CheckBox)); 
			 check.click();
			 break;				 
		 }			 
	 }if (ispresent==false){
		 Assert.assertTrue(ispresent,FName+" "+Lname +" contact is not found" );
	 }	 
 }
 
 public boolean verifyContactPresent(String FName, String Lname) throws Exception{
	 logInfo("inside conatctList(String FName, String Lname)  Method");
	 
	 
	 Thread.sleep(3000);
	 boolean ispresent = false;
	 List <WebElement> all = driver().findElements(By.xpath(allContacts));
	 System.out.println(" All Size is "+all.size());
	 for (int i = 1; i<=all.size(); i++){			
		 String b4Con = "//div/form/div[";
		 String a4Con = "]/div[2]/div/div[2]/h4/div/a/span[2]";  //"]/div[2]/div"; ///div/a/span[2]
		 WebElement lis = driver().findElement(By.xpath(b4Con+i+a4Con));
		 System.out.println("conatcts are "+lis.getText());
		 if (lis.getText().trim().equalsIgnoreCase(FName+" "+Lname)){
			 ispresent =true;	
			 logInfo(lis.getText().trim() + " business contact match found.");
			 break;				 
		 }			 
	 }if (ispresent==false){
		 Assert.assertTrue(ispresent,FName+" "+Lname +" contact is not found" );
	 }
	return ispresent;	 
 }
 
 
 	public void selectContactOptions(String toggleType){
		 logInfo("inside selectContactOptions(String toggleType) Method");
		 
		 clickOnElement("cssSelector", toggle);
		 boolean istogglePresent = false;
		 
		 List <WebElement> lit = driver().findElements(By.cssSelector("ul#contact-dropdown-actions > li >a"));
		 System.out.println(lit.size()+ " size is ");
		 for (WebElement list :lit){
			 System.out.println("List is "+ list.getText());
			 if (list.getText().trim().equalsIgnoreCase(toggleType)){
				 istogglePresent = true;
				 list.click();
				 break;	 
			 }			 
			 
		 }if (istogglePresent==false){
			 Assert.assertEquals(istogglePresent, toggleType+ " is not found in the toggle list");
			 
		 }
	 
 	}
 
 
 
 /*public void handlePDFWindow() throws Exception{			
		logInfo("verify details in pdf page");
		String expText = "No contacts with addresses found.";  
		String wndBeforeWindow = driver().getWindowHandle();	
		for(String w : driver().getWindowHandles()){
			if(!w.equalsIgnoreCase(wndBeforeWindow)){
			driver().switchTo().window(w);
			System.out.println(driver().getTitle());
			WebElement text = driver().findElement(By.cssSelector("div#pageContainer1.page > div.textLayer > div"));
			if(text.getText().trim().equalsIgnoreCase(expText)){
				Assert.assertTrue(true, "Able to get addresses in the pdf window for print labels.");
			}
//			Assert.assertEquals(text.getText(), expText);		
			Thread.sleep(3000);
			driver().close();	
			Thread.sleep(8000);
			driver().switchTo().window(wndBeforeWindow);
			break;
			}
		}
	
    } */
 
 	public void handlePDFWindow() throws Exception{			
		logInfo("inside handlePDFWindow() method");
		String expText = "No contacts with addresses found.";  
		
		String parent = driver().getWindowHandle();
		System.out.println("parent =" +parent);
		
		Set <String> allWindows = driver().getWindowHandles();
		int count = allWindows.size();
		for(String child: allWindows) {
			if(!parent.equalsIgnoreCase(child)) {
				driver().switchTo().window(child);
				System.out.println("child = " + child);
				break;
			}
		}
    } 
 
 
 public void taskAlerts() throws Exception{
	 logInfo("inside taskAlerts() Method");
	 
	 String msgText = "can't be blank";
	 String msgText2 = "Task Title can't be blank";
	 
	 selectContactActions("Create Task");	
	 clickOnButton("xpath",inputAddTask);
	 WebElement alertMsg = driver().findElement(By.cssSelector(alrtMsg));
	 Assert.assertEquals(alertMsg.getText(), msgText);
	 WebElement alertMsg2 = driver().findElement(By.cssSelector(alrtMsg2));
	 Assert.assertEquals(alertMsg2.getText(), msgText2);
	 Thread.sleep(2000);
 }
 
 	public void callScriptAlert() throws Exception{
 		logInfo("inside callScriptAlert Method");
	 
	 String msgText = "can't be blank";	 
	 selectContactActions("Start Call Script");	
	 Thread.sleep(2000);
	 clickOnButton("cssSelector",inputSubmitCallscript);
	 Thread.sleep(2000);
	 WebElement alertMsg = driver().findElement(By.cssSelector(alrtMsg));
	 Assert.assertEquals(alertMsg.getText(), msgText);		 
	 Thread.sleep(2000);
 }
 
 public void notesAlert() throws Exception{
	 logInfo("inside notesAlert Method");
		 
		 String msgText = "can't be blank";	 
		 selectContactActions("Notes");	
		 Thread.sleep(2000);
		 waitOnElement("linkText","New Note");	
		 clickOnLink("linkText","New Note");
		 clickOnButton("cssSelector",btnCreateContactNote);
		 Thread.sleep(2000);
		 WebElement alertMsg = driver().findElement(By.cssSelector(alrtMsg));
		 Assert.assertEquals(alertMsg.getText(), msgText);		 
		 Thread.sleep(2000);	 			 
	 }
 
 public void eventAlert() throws Exception{
	 logInfo("inside eventAlert() Method");
	 String alrtMsg =  "Please enter Title name";
	 String alrtMsgforStartDate = "Please enter Start date";
	 selectContactActions("Create Event");
	 Thread.sleep(2000);
	 inputTextClear("cssSelector",inputAppointmentName);
	 clickOnElement("xpath", btnEventSave);	 
	 confirmationMessage(alrtMsg);		 
 }
 
 
 	public void setPreferredEmail2Contact(String email2) throws Exception{	
	 logInfo("inside setPreferredEmail2Contact method");
	 
	 waitOnElement("linkText", "Edit");
	 clickOnLink("linkText", "Edit");
	 
	 Thread.sleep(5000);
	 clickOnElement("xpath",lnkAddMoreEmails);
	
	  String input2 = "//*[@class='simple_form view-container']/div[3]/div/p/div[1]/div/div/div/input";
	  inputText("xpath",input2, email2);
	  clickOnElement("xpath","//*[@class='simple_form view-container']/div[3]/div/p/div[1]/div/div/div/span[1]");
	  clickOnButton("xpath","//input[@value='Update Contact']");
	  confirmationMessage("Contact is updated");
	  Thread.sleep(5000);
 	}
 
 
 /*public void addMultiEmails(String email) throws Exception{	
	 logInfo("inside addMultiEmails Method");
	 
	 String mal1 = "//div[3]/div/p/div[1]/div/div/div/input";
	 String mal2 = "//div[3]/div/p/div[2]/div/div/div/input";
	 String mal3 = "//div[3]/div/p/div[3]/div/div/div/input";
	 String prefer = "//div[3]/div/p/div[3]/div/div/div/span[1]/i";
	 String star = "div[3]/div[2]/div[1]/div[1]/div[3]/span";
	 
	 Thread.sleep(5000);
	 clickOnLink("linkText", "Edit");
	 Thread.sleep(4000);
	 clickOnLink ("linkText", "Add More Email");
	 Thread.sleep(2000);
	 clickOnLink ("linkText", "Add More Email");
	 Thread.sleep(2000);
	 clickOnLink ("linkText", "Add More Email");	
		 
	 inputText("xpath", mal1, recipientsOne_text);
	 Thread.sleep(2000);
	 inputText("xpath", mal2, recipientsGmail_text);
	 inputText("xpath", mal3, email);		
	 clickOnElement ("xpath", prefer);
	 
	 clickOnButton("cssSelector",btnUpdateContact);
	 Thread.sleep(5000);
	 WebElement mailer = driver().findElement(By.cssSelector("div > div > span#preferred-email"));
	 System.out.println(mailer.getText());
	 WebElement mailer1 = driver().findElement(By.cssSelector("div#contact-details-panel > #contact-details > div > div > a"));
	 System.out.println("Mailer1"+mailer1.getText() );
	 WebElement mailer2 = driver().findElement(By.xpath("//*[@id='contact-details']/div[1]/div[1]/div/a"));
	 System.out.println("Mailer2"+mailer2.getText() );
	
 }
*/ 
	 
 
 	public void addContactsAlerts(String FirstName) throws Exception{
		logInfo("inside addContactsAlerts() method. ");
		System.out.println("inside addContactsAlerts() method. ");
		String ExpMsg= "Name and email cannot be blank.";		// Please add the name to your contact. Please add an email to your contact.
		go2ContactsPage();			
		waitOnElement("cssSelector","#add-a-contact>a");
		clickOnLink("cssSelector", "#add-a-contact>a");
		Thread.sleep(5000);
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(2000);
		WebElement actMsg = driver().findElement(By.cssSelector(alrtMsg2));
		String actualMsg = actMsg.getText();
		Assert.assertEquals(actualMsg, ExpMsg);			
		inputText("cssSelector",inputContactLastName,lName);
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(2000);
		WebElement actMsg2 = driver().findElement(By.cssSelector(alrtMsg2));
		String actualMsg2 = actMsg2.getText();
		Assert.assertEquals(actualMsg2, ExpMsg);
		inputText("cssSelector",inputContactFirstName,FirstName); 
		inputTextClear("cssSelector",inputContactLastName);  
		clickOnButton("cssSelector",btnUpdateContact);
		confirmationMessage("Contact is created");
		Thread.sleep(2000);
		clickOnLink("linkText", "Edit");
		Thread.sleep(2000);
		inputTextClear("cssSelector",inputContactFirstName);
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(2000);
		WebElement actMsg3 = driver().findElement(By.cssSelector(alrtMsg2));
		String actualMsg3 = actMsg3.getText();
		Assert.assertEquals(actualMsg3, ExpMsg);
		Thread.sleep(2000);
		inputText("cssSelector",inputContactFirstName,FirstName);
		clickOnButton("cssSelector",btnUpdateContact);			
		confirmationMessage("Contact is updated");
	
	}
 
 	public boolean attachResource2SendMessage(String fName, String lName, String recepients,String resName,String resAsset) throws Exception{
		 logInfo("inside attachResource2SendMessage() method.");
		 System.out.println("inside attachResource2SendMessage() method.");
		 boolean isAssetAttached = false;
		 
		 go2ContactsPage();
		 selectContactsCategoryList("Recent Contacts");
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		
		 waitOnElement("xpath",lnkSendMesg);
		 Thread.sleep(5000);
		 clickOnElement("xpath",lnkSendMesg);
		 
		 waitOnElement("cssSelector",recipientsTo);
		 clickOnElement("cssSelector",recipientsTo);
		 inputText("cssSelector",recipientsTo, recepients);
		 inputText("cssSelector",subject_Mail, inputSendMessageSubject_text);
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","attaching a resoruce and sending a message");
		// Robot rb= new Robot();
		/* rb.delay(2000);
		 rb.keyPress(KeyEvent.VK_TAB);
		 rb.keyRelease(KeyEvent.VK_TAB); */
		// composeTextOnElement();
		 clickOnLink("linkText", "Attach Resource File");
		 Thread.sleep(5000);
		 
		 List<WebElement> el = driver().findElements(By.xpath(lstResources));
		 String before_plus = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[2]/div[3]/div[";
		 String after_plus = "]/div/div[1]/a";
		 
		 String before_check = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[2]/div[3]/div[";
		 String after_check = "]/div/div[2]/div/div/div[2]/input";
		 
		 String before_res = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[2]/div[3]/div[";
		 String after_res = "]/div/div[1]";
		 
		 for(int i=1;i<=el.size();i++){
			 WebElement res = driver().findElement(By.xpath(before_res+i+after_res));
			 if(res.getText().trim().contains(resName)){
				 waitOnElement("xpath",before_plus+i+after_plus);
				 driver().findElement(By.xpath(before_plus+i+after_plus)).click();
				 Thread.sleep(3000);
				 waitOnElement("xpath",before_check+i+after_check);
				 clickOnElement("xpath",before_check+i+after_check);
				 Thread.sleep(3000);
				 clickOnElement("xpath",btnAttachAsset);
				 break;
			 }
		 }
		 Thread.sleep(5000);
		 waitOnElement("xpath",lstAsset);
		 List<WebElement> res = driver().findElements(By.xpath(lstAsset));
		 String before = "//*[@id='new_pyr_crm_message']/div[@class='attachment'][";
		 String after = "]/div[1]";
		 
		 for(int i=1;i<=res.size();i++){
			 WebElement assetType = driver().findElement(By.xpath(before+i+after));
			 if(assetType.getText().trim().contains(resAsset)){
				 isAssetAttached = true;
				 Thread.sleep(3000);
				 clickOnLink("linkText","Send");
				 Thread.sleep(5000);
				 break;
			 }
		 }
		 
		 return isAssetAttached;
	 }
 	


 	public void validateAppointment() throws  Exception{
 		logInfo("inside validateAppointment() method.");
 		System.out.println("inside validateAppointment() method");
 		 		
 		waitOnElement("xpath",lnkCreateEvent);
 		implicityWaits(5);
 		clickOnElement("xpath",lnkCreateEvent);
 		Thread.sleep(5000);
 		waitOnElement("cssSelector",inputAppointmentName);
 		inputTextClear("cssSelector", inputAppointmentName);
 		clickOnLink("linkText", "More Options");
 		confirmationMessage("Please enter a title for your event.");
 	}
 	
 	public void addBusinessContactsWithoutAddress(String fName, String lName, String email) throws Exception{
		logInfo("inside addBusinessContactsWithoutAddress() method. ");
		System.out.println("inside addBusinessContactsWithoutAddress() method. ");
		go2ContactsPage();
		
		waitOnElement("linkText","Add A Contact");
		clickOnLink("linkText", "Add A Contact");
		Thread.sleep(5000);
		waitOnElement("cssSelector",inputContactFirstName);
		inputText("cssSelector",inputContactFirstName,fName);  // contactFirstName_text
		inputText("cssSelector",inputContactLastName,lName);    // contactLastName_text
		inputText("cssSelector",inputContactEmail,email);
		inputText("cssSelector",inputContactPhone,contactPhone_text);

		WebElement element = driver().findElement(By.cssSelector(drpdnContactPhoneType));
		Select select = new Select(element);
		List<WebElement> allItems =select.getOptions();
		int size = allItems.size();
		select.selectByIndex(size-1);
		logInfo("Entered all the fields in Business -> AddContact page.");
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(5000);
	}
 	
 	public boolean validateContact() throws  Exception{
 		logInfo("inside validateContact() method.");
 		System.out.println("inside validateContact() method.");
 		boolean isValidated = false;
 		implicityWaits(3);
 		waitOnElement("linkText","Edit");
 		clickOnElement("linkText", "Edit");
 		waitOnElement("cssSelector","#pyr_crm_contact_first_name");
 		inputTextClear("cssSelector","#pyr_crm_contact_first_name");
 		clickOnButton("cssSelector",btnUpdateContact);
 		//validation msg should display here, bug has been logged for this.
 		return isValidated;
 	}
 	
 	public boolean validateCheckbox4Contact() throws  Exception{
 		logInfo("inside validateCheckbox4Contact() method.");
 		System.out.println("inside validateCheckbox4Contact() method.");
 		boolean isValidated = false;
 
 		String contactsPanel = "//*[@id='all-contacts-list']/div[1]/form";
 		waitOnElement("xpath",contactsPanel);
 		WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
	  
 		String expVal = contactFirstName_text + " " + contactLastName_text;
 		List<WebElement> verifyContactsList = econtactsPanel.findElements(By.cssSelector(".contact-list-item")); // .media
		
		boolean isContactFound = false;
		int count= verifyContactsList.size();
		System.out.println("contacts list count =" +count);
		
		String before_name = "//*[@id='all-contacts-list']/div[1]/form/div[";
		String after_name = "]/div[@class='media-body']/div/div[2]/h4/div/a/span[2]";
		
		String before_check = "//*[@id='all-contacts-list']/div[1]/form/div[";
		String after_check = "]/div[@class='media-left']/input";   
		
		for(int i=1;i<=count;i++){
			WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
			String name = e.getText().trim();
	
			if(name.equalsIgnoreCase(expVal)){
				isContactFound = true;
				logInfo(name + " business contact match found.");
				WebElement cb = driver().findElement(By.xpath(before_check+i+after_check));
				cb.click();
				System.out.println(name + " selected business contact");
				break;
			}
		}
		
		clickOnElement("xpath","//*[@id='contacts-bulk-action-dropdown']/button");
		clickOnElement("xpath", "//*[@id='group']");
		waitOnElement("xpath","//*[@id='add_to_group']");
		clickOnElement("xpath","//*[@id='add_to_group']");
		confirmationMessage("Please select at least one group.");
		
		for(int i=1;i<=count;i++){
			WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
			String name = e.getText().trim();
	
			if(name.equalsIgnoreCase(expVal)){
				isContactFound = true;
				logInfo(name + " business contact match found.");
				WebElement cb = driver().findElement(By.xpath(before_check+i+after_check));
				if(cb.isSelected()){
					isValidated = true;
					break;
				}
				
			}
		}
 		return isValidated;
 	}
 	
 	
 	
	 public void sendecard2Contacts() throws Exception{
		  logInfo("inside sendecard2Contacts() method.");
		  System.out.println("inside sendecard2Contacts() method.");		  
		  go2ContactsPage();
		  selectContactsCategoryList("Recent Contacts");
		  selectBusinessContact(contactFirstName_text,contactLastName_text);
		  selectContactAction("Send Ecard");
		}
	 
	
	 public void selectContactAction(String actionName) throws  Exception {
			logInfo("inside selectContactAction() method");
			
			boolean islinkAvailable = false;
			waitOnElement("xpath","//div[@id='contact-actions']");
			WebElement actions = driver().findElement(By.xpath("//div[@id='contact-actions']"));
			List<WebElement> allActions = actions.findElements(By.xpath("//div[starts-with(@class,'contact-action')]"));
			int linksSize = allActions.size();
			System.out.println("Total Actions = "  +linksSize);
			String beforePath = "//*[@id='contact-actions']/div[";
			String afterPath = "]/a/span[2]";
			for(int i=1;i<=linksSize; i++){
				
			WebElement x =	driver().findElement(org.openqa.selenium.By.xpath(beforePath+i+afterPath));
			waitOnElement("xpath",beforePath+i+afterPath);
			scrollDown("xpath",beforePath+i+afterPath);
			String title = x.getText().trim();
			System.out.println("title is " +title);
			if(title.equalsIgnoreCase(actionName)){
				islinkAvailable= true;
				logInfo(actionName+ " action found in contact details list");
				x.click();
				Thread.sleep(5000);
				break;
			}
			}if (islinkAvailable==false){
				logInfo(actionName+ "action not found in contact details list");
				Assert.assertTrue(islinkAvailable,actionName + " is not found in contact details list");
				
			}
		}
	
	 
	 public void selectAddContacts2Group(String groupName) throws  Exception {
			logInfo("inside selectAddContacts2Group() method");
			
			boolean isGroupFound2Add = false;
			waitOnElement("xpath","//div[@class='categories_list']/form[@id='addtoGroup']/table[@id='listContactGroups']/tbody");
			WebElement groupTbl = driver().findElement(By.xpath("//div[@class='categories_list']/form[@id='addtoGroup']/table[@id='listContactGroups']/tbody"));
			List allRows = groupTbl.findElements(By.tagName("tr"));
			
			String before_grpName = "//div[@class='categories_list']/form[@id='addtoGroup']/table[@id='listContactGroups']/tbody/tr[";
			String after_grpName = "]/td[1]";
			String before_inputSelectGroup = "//div[@class='categories_list']/form[@id='addtoGroup']/table[@id='listContactGroups']/tbody/tr[";
			String after_inputSelectGroup = "]/td[3]/input";
			
			if(allRows.size()>0) {
				for(int i=1;i<=allRows.size();i++) {
					WebElement grpName = driver().findElement(By.xpath(before_grpName+i+after_grpName));
					String actGroup = grpName.getText().trim();
					if(actGroup.equalsIgnoreCase(groupName)) {
						WebElement input = driver().findElement(By.xpath(before_inputSelectGroup+i+after_inputSelectGroup));
						input.click();
						Thread.sleep(2000);
						clickOnElement("xpath","//input[@id='add_to_group']");
						Thread.sleep(5000);
						logInfo(groupName + " found to add to contact.");
						isGroupFound2Add = true;
						break;
					}
				}
			}
			
			if(isGroupFound2Add==false) {
				logInfo(groupName + " not found to add to contact.");
				Assert.assertTrue(isGroupFound2Add, groupName + " not found to add to contact.");
			}
	 }
	 
	
	/*public void selectContactAction(String actionName) throws  Exception 
	{
		logInfo("inside selectContactAction() method");
		boolean islinkAvailable = false;
		List<WebElement> links = driver().findElements(By.xpath("//*[@id='contact-actions']/div"));
		int linksSize = links.size();
		System.out.println(linksSize);
		String beforePath = "//*[@id='contact-actions']/div[";
		String afterPath = "]/a/span[2]";
		for(int i=1;i<=linksSize; i++){
			
		WebElement x =	driver().findElement(org.openqa.selenium.By.xpath(beforePath+i+afterPath));
		waitOnElement("xpath",beforePath+i+afterPath);
		scrollDown("xpath",beforePath+i+afterPath);
		String title = x.getText().trim();
		System.out.println("title is " +title);
		if(title.equalsIgnoreCase(actionName)){
			islinkAvailable= true;
			logInfo(actionName+ " action found in contact details list");
			x.click();
			Thread.sleep(4000);
			break;

		}
		}if (islinkAvailable==false){
			logInfo(actionName+ "action not found in contact details list");
			Assert.assertTrue(islinkAvailable,actionName + " is not found in contact details list");
			
		}
	}*/
	
	public void go2UnassignedContactsPage() throws Exception{
		logInfo("inside go2UnassignedContactsPage() method. ");
		driver().navigate().to(appUrl + "crm/v2/contacts/all");
		clickOnLink("partialLinkText","Unassigned (");
	}
 
		
	
	/* Avon Specific Methods */
	
	
 	//Ganga
 	
 	public void filterSearch(String searchText) throws Exception{		
 		 logInfo("inside filterSearch() method");
 		  go2ContactsPage();
 		 	String expResult = "No Contacts Found";		 		 
 		 	clickOnLink("linkText","Filters");
 			
 			inputTextClear("cssSelector", contFName);
 			inputText("cssSelector",contFName,searchText);
 			clickOnButton("cssSelector", advSerch);
 			
 			WebElement searResult2 =  driver().findElement(By.cssSelector("form#contacts_selected > div.alert.alert-danger"));   // searchReslt
 			Assert.assertEquals(searResult2.getText(), expResult);	
 	 }

 	/*public void searchAndSelectContact(String fName, String lName) throws  Exception {
 		
 		logInfo("inside searchAndSelectContact() method. ");
		System.out.println("inside searchContact() method. ");
		 String expVal = fName + " " + lName;
		 
		 waitOnElement("cssSelector",inputContactSearch);
		 inputText("cssSelector",inputContactSearch, expVal);
		
		  String contactsPanel = "//*[@id='all-contacts-list']/div[1]/form";		 
		  WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
		 Thread.sleep(4000);
		  WebElement link = driver().findElement(By.xpath(joyceAssocClk));
		//  waitOnElement("xPath", joyceAssocClk);
		  link.click();
		  System.out.println(link.getText() + " selected business contact");
			
		  
		  boolean isContactFound = false;
		  

		  List<WebElement> verifyContactsList = econtactsPanel.findElements(By.cssSelector(".contact-list-item")); 
		  
			int count= verifyContactsList.size();
			System.out.println("contacts list count =" +count);
		
			String before_name = "//*[@id='all-contacts-list']/div[1]/form/div[";
			String after_name = "]/div[@class='media-body']/div/div[2]/h4/div/a/span[2]";
			
			String before_link = "//*[@id='all-contacts-list']/div[1]/form/div[";
			String after_link = "]/div[@class='media-body']/div/div[2]/h4/div/a";    
			
			for(int i=2;i<=count;i++){
				WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
				String name = e.getText().trim();
				
				// System.out.println("Act Contact = " +name);
				// System.out.println("Exp Contact = " +expVal);
				if(name.equalsIgnoreCase(expVal)){
					isContactFound = true;
					logInfo(name + " business contact match found.");
					WebElement link = driver().findElement(By.xpath(before_link+i+after_link));
					link.click();
					System.out.println(name + " selected business contact");
					break;
		  
				}
		  
			}
		  */

 	public String getAccountStatus() throws  Exception {
 		waitOnElement("cssSelector", ac);
		WebElement accst = driver().findElement(By.cssSelector(ac));
		String acn = accst.getText();
		
		return acn;
	}
 		
 	public String getContactTitle() throws  Exception {
 		waitOnElement("cssSelector", avtitle);
		WebElement acctit = driver().findElement(By.cssSelector(avtitle));
		String actit = acctit.getText();
		return actit;
		
 		}
 	public String getPcLevel() throws  Exception {
 		waitOnElement("cssSelector", avtitle);
		WebElement pclvl = driver().findElement(By.cssSelector(pclevel));
		String pclevel = pclvl.getText();
		return pclevel;
		
 		}	
 	public String getRepID() throws  Exception {
 		waitOnElement("cssSelector", repid);
		WebElement repi = driver().findElement(By.cssSelector(repid));
		String repd = repi.getText();
		return repd;
		
 		}	
 	
 	public String getLOA() throws  Exception {
 		waitOnElement("cssSelector", loa);
		WebElement lo = driver().findElement(By.cssSelector(loa));
		String looa = lo.getText();
		return looa;
		
 		}	
 	public String getLanguage() throws  Exception {
 		waitOnElement("cssSelector", lang);
		WebElement lan = driver().findElement(By.cssSelector(lang));
		String language = lan.getText();
		return language;
		
 		}	
	public String getRpsCode() throws  Exception {
 		waitOnElement("cssSelector", rps);
		WebElement rpsc = driver().findElement(By.cssSelector(rps));
		String rpscode = rpsc.getText();
		return rpscode;
		
 		}	
 	
	
	public String filterActive() throws Exception{
 		logInfo("inside filterActive() method ");
 		String expVal = fName + " " + lName;
 		go2ContactsPage();
 		clickOnLink("linkText","Filters");
 		waitOnElement("cssSelector", filterBtn);
 		selectFromDropdown("id",accntStatusDropdown,"byVisibleText","Active");
 		clickOnButton("cssSelector", filterBtn);
 		String contactsPanel = "//*[@id='all-contacts-list']/div[1]/form";		 
		WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
		Thread.sleep(4000);
		WebElement link = driver().findElement(By.xpath(joyceAssocClk));
		//  waitOnElement("xPath", joyceAssocClk);
		link.click();
		String expecteduser = link.getText().trim();
		System.out.println(expecteduser + " selected business contact");
		
		return  expecteduser;
 	}
 	
	
	//This testcase is to do a filter search with valid data and verifying the results
 	public void filtersSearchAndValidatingData() throws  Exception {
 		
 		logInfo("inside getCotnactInfo() method ");
 		
 		String contactdata = filterActive();
 		String expectedAccntsts = getAccountStatus();
 		String expectedPCLevel = getPcLevel();
 		System.out.println(expectedPCLevel);
 		String expectedTitle = getContactTitle();
 		System.out.println(expectedTitle);
 		String expectedRepID = getRepID();
 		System.out.println(expectedRepID);
 		String expectedLOA = getLOA();
 		System.out.println(expectedLOA);
 		String expectedLanguage = getLanguage();
 		System.out.println(expectedLanguage);
 		String expectedRPS = getRpsCode();
 		System.out.println(expectedRPS);
 		clickOnLink("linkText","Filters");
 		waitOnElement("id", accntStatusDropdown);
 		selectFromDropdown("id",accntStatusDropdown,"byVisibleText",expectedAccntsts);
 		inputTextClear("id", representativeId);
 		inputText("id", representativeId, expectedRepID);
 		selectFromDropdown("id",titleRank,"byVisibleText",expectedTitle);
 		selectFromDropdown("id",rankPClevel,"byVisibleText",expectedPCLevel);
 		selectFromDropdown("id",languageDrpdn,"byVisibleText",expectedLanguage);
 		clickOnButton("cssSelector", filterBtn);
 		confirmationMessage("1 results found.");
 		  String contactsPanel = "//*[@id='all-contacts-list']/div[1]/form";		 
		  WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
		 Thread.sleep(4000);
		  WebElement link = driver().findElement(By.xpath(joyceAssocClk));
		//  waitOnElement("xPath", joyceAssocClk);
		  String expectedText = link.getText();
		  System.out.println(expectedText + "is the expected contact");
		  Assert.assertEquals(contactdata, expectedText);	
 	}
 	
 	
 /*	public boolean verifyBusinessContact(String uName) throws Exception{
		 logInfo("inside verifyBusinessContact() method.");
		  System.out.println("inside verifyBusinessContact() method.");
		  go2ContactsPage();
		  selectContactsCategoryList("Recent Contacts");
		  
		  String contactsPanel = prop.getLocatorForEnvironment(appUrl,"contactsPanel");
		  WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
		  
		  waitOnElement("xpath",contactsPanel);
		  
		  String before_name = contactsPanel + "/div[";   // "//*[@id='list-results']/div[3]/div/form/div[";  // "//*[@id='all-contacts-list']/div[1]/form/div[";   //
		  String after_name = "]/div[@class='media-body']/div/div[2]/h4/div/a/span[2]";
		  
		  String expVal = fName + " " + lName;
		  List<WebElement> verifyContactsList = econtactsPanel.findElements(By.cssSelector(".contact-list-item.media"));
				
				boolean isContactFound = false;
				int count= verifyContactsList.size();
				System.out.println("Total Business Contacts =" +count);
				logInfo("Total Business Contacts =" +count);
				for(int i=1;i<=count;i++){
					WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
					String actText = e.getText().trim();
					
					System.out.println("actText =" +actText);
					
					if(actText.equalsIgnoreCase(expVal)){
						isContactFound = true;
						logInfo(expVal + " business contact match found.");
						break;
					}
				}
				return isContactFound;
	}	*/
 	
 	
 	
 	/* public void sendecard2Contacts(String fName, String lName) throws Exception{
		  logInfo("inside sendecard2Contacts() method.");
		  System.out.println("inside sendecard2Contacts() method.");
				
		  go2ContactsPage();
		  selectContactsCategoryList("Recent Contacts");
		  selectBusinessContact(contactFirstName_text,contactLastName_text);
		
		  waitOnElement("xpath",lnkSendEcard);
		  clickOnElement("xpath",lnkSendEcard);
		}
 	
 	 
	public void sendEcard() throws Exception{
		logInfo("Inside sendEcard Method");
		Thread.sleep(5000);
		String parentEcard = "Blank Letterhead";
	    String ecardtempName =  "Embedded video";
		em.selectcatNdEcard(parentEcard, ecardtempName);
		em.enterDetails(ecardCatSubText);	
		waitOnElement("cssSelector", ecardMailSendBtn);         	
       	clickOnButton("cssSelector", ecardMailSendBtn);
       	confirmationMessage("Message sent to: "+mailId);     
	}*/
 	
 	public void selectEcard(String ecardCategory, String ecardTemplate) throws  Exception{
 		logInfo("inside selectEcard() method");
 		
 		waitOnElement("xpath","//ul[@class='panel-nav categories']");
 		WebElement ecat = driver().findElement(By.xpath("//ul[@class='panel-nav categories']"));
 		List allCategories  = ecat.findElements(By.tagName("li"));
 		
 		String before_li = "//ul[@class='panel-nav categories']/li[@id=";
 		String after_li = "]/a";
 		
 		if(allCategories.size()>0){
 			for(int i=1;i<=allCategories.size();i++){
 				WebElement ecatName = driver().findElement(By.xpath(before_li+i+after_li));
 				String actCategoryName = ecatName.getText().trim();
 				if(actCategoryName.equalsIgnoreCase(ecardCategory)){
 					ecatName.click();		// select category 
 					
 					waitOnElement("xpath","//*[@id='ecard_query']");
 					inputTextClear("xpath","//*[@id='ecard_query']");
 					inputText("xpath","//*[@id='ecard_query']",ecardTemplate);
 					
 					waitOnElement("xpath","//div[@class='ecard-templates-grid']");
 					WebElement templateGrid = driver().findElement(By.xpath("//div[@class='ecard-templates-grid']"));
 	 				List allGridRows  = templateGrid.findElements(By.className("row"));
 	 				
 	 				String before_tempRow = "//div[@class='ecard-templates-grid']/div[";
 	 				String after_tempRow = "]/div[@class='col-md-12']";
 	 				
 	 				if(allGridRows.size()>0){
 	 					for(int j=1;j<=allGridRows.size();j++){
 	 						WebElement tempRow = driver().findElement(By.xpath(before_tempRow+j+after_tempRow));
 	 						List allTemplates = tempRow.findElements(By.cssSelector(".col-md-2.col-sm-4.col-xs-6"));
 	 						if(allTemplates.size()>0){
 	 							
 	 							String before_template1 = "//div[@class='ecard-templates-grid']/div[";
 	 							String before_template2 = "]/div[@class='col-md-12']/div[";
 	 							String before_template3 = "]/div[1]/div[3]/div[1]";
 	 							for(int k=1;k<=allTemplates.size();k++){
 	 								waitOnElement("xpath",before_template1+j+before_template2+k+before_template3);
 	 								WebElement etempName = driver().findElement(By.xpath(before_template1+j+before_template2+k+before_template3));
 	 								String actualTemplate = etempName.getText().trim();
 	 								if(actualTemplate.equalsIgnoreCase(ecardTemplate)){
 	 									logInfo(ecardTemplate + " template match found");
 	 									etempName.click();
 	 									// click on Next button
 	 									waitOnElement("cssSelector","form.simple_form.edit_pyr_crm_ecard > div.text-right > input.btn.btn-primary");
 	 	 								inputText("xpath","//*[@id='pyr_crm_ecard_subject']",ecardTemplate + " ecard template");
 	 									clickOnElement("cssSelector","form.simple_form.edit_pyr_crm_ecard > div.text-right > input.btn.btn-primary");
 	 	 								// click on Send Ecard button
 	 	 								waitOnElement("cssSelector","div.text-right > button.btn.btn-primary.pull-right");
 	 	 								clickOnElement("linkText","Add Manually");
 	 	 								waitOnElement("xpath","//*[@id='ecard_manual_contacts_container']/div[@class='control-group']/div[2]/div[1]/input");
 	 	 								inputText("xpath","//*[@id='ecard_manual_contacts_container']/div[@class='control-group']/div[2]/div[1]/input",recipientsGmail_text);
 	 	 								clickOnElement("xpath","//*[@id='ecard_manual_contacts']/div/div[1]/div[2]/div[4]/div/button");
 	 	 								clickOnElement("cssSelector","div.text-right > button.btn.btn-primary.pull-right");
 	 	 								break;
 	 								}
 	 							}
 	 						}
 	 					}
 	 				}
 	 				
 					break;
 				}
 			}
 		}
 		
 	}


	public void verifyStaticGroupsIsPresent(String groupName) throws  Exception{
		logInfo("Inside verifyStaticGroupsArePresent method()");
		
		waitOnElement("xpath","//*[@id='list-results']");
		WebElement grpPanel = driver().findElement(By.xpath("//*[@id='list-results']"));
		List allGrps = grpPanel.findElements(By.cssSelector(".list-result"));
		
		String before_grpName = "//*[@id='list-results']/div[";
		String after_grpName = "]/a";
		
		boolean isGroupPresent = false;
	
		if(allGrps.size() >0){
		for(int i=1;i<=allGrps.size();i++){
				WebElement eGrpName = driver().findElement(By.xpath(before_grpName+i+after_grpName));
				String actGrpName = eGrpName.getText().trim();
				if(actGrpName.contains(groupName.trim())){  //equalsIgnoreCase
					System.out.println(actGrpName + " is present");
					isGroupPresent = true;
					break;
				}
			  }
		}
				
	
		if(isGroupPresent==false){
			logInfo(isGroupPresent + " group is not present");
			Assert.assertTrue(isGroupPresent, " group is not present");
		}
	}
	
	
	
	// Method for Avon Coach User
	
	public void loginAsUserFromContactPanel(String fName, String lName) throws Exception{
		logInfo("Inside loginAsUserFromContactPanel method()");
		
		 String contactsPanel = "//*[@id='all-contacts-list']/div[1]/form";
		  waitOnElement("xpath",contactsPanel);
		  WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
		  
		  String expVal = fName + " " + lName;
		  List<WebElement> verifyContactsList = econtactsPanel.findElements(By.cssSelector(".contact-list-item")); // .media
				
				boolean isContactFound = false;
				int count= verifyContactsList.size();
				System.out.println("contacts list count =" +count);
				
			/*	String before_name = "//*[@id='all-contacts-list']/div[1]/form/div[";
				String after_name = "]/div[2]/div/div[2]/h4/a/span[2]";*/
				
				
				String before_name = "//*[@id='all-contacts-list']/div[1]/form/div[";
				String after_name = "]/div[@class='media-body']/div/div[2]/h4/div/a/span[2]";
				
				String before_link = "//*[@id='all-contacts-list']/div[1]/form/div[";
				String after_link = "]/div[@class='media-body']/div/div[2]/h4/div/a";    // "]/div[2]/div/div[2]/h4/a";
				
				String before_loginAsUserlink = "//*[@id='all-contacts-list']/div[1]/form/div[";
				String after_loginAsUserlink = "]/div[@class='media-body']/div/div[2]/h4/a";
				
				for(int i=1;i<=count;i++){
					WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
					String name = e.getText().trim();
					
					// System.out.println("Act Contact = " +name);
					// System.out.println("Exp Contact = " +expVal);
					if(name.equalsIgnoreCase(expVal)){
						isContactFound = true;
						logInfo(name + " business contact match found.");
						WebElement link = driver().findElement(By.xpath(before_loginAsUserlink+i+after_loginAsUserlink));
						link.click();
						System.out.println(name + " selected business contact");
						break;
					}
				}
		}
	
	
	public void verifyUserLoginMessage() throws Exception{
		logInfo("Inside verifyLoginMessage method()");
		waitOnElement("xpath","//*[@id='page']/div[1]");
		String coachUserName = prop.getLocatorForEnvironment(appUrl,"coachFirstName_text") + " " + prop.getLocatorForEnvironment(appUrl,"coachLastName_text");
		String expLoginMsg = "You are logged in as " + coachUserName + ".  Click here to go back to your account.";
		WebElement eMsg = driver().findElement(By.xpath("//*[@id='page']/div[1]"));
		String actLoginMsg = eMsg.getText().trim();
		System.out.println("actLoginMsg = " +actLoginMsg);
		System.out.println("expLoginMsg = " +expLoginMsg);
		Assert.assertEquals(actLoginMsg, expLoginMsg);
	}
	
	
	public void editBusinessContact() throws  Exception {
		logInfo("Inside editBusinessContact method()");
		
		clickOnElement("xpath","//*[@id='contact-summary']/div[1]/a[1]");
		Thread.sleep(5000);
		clickOnElement("xpath", "//form[@class='simple_form view-container']/div[@class='form-group'][1]/div/p/a/span/i");
		inputText("xpath","//form[@class='simple_form view-container']/div[@class='form-group'][1]/div/p/div[1]/div/div/div/input", prop.getLocatorForEnvironment(appUrl,"selfmailId1"));
		clickOnElement("xpath","//form[@class='simple_form view-container']/div[@class='form-group'][1]/div/p/div[1]/div/div/div/span[1]");
		scrollDown("xpath","//input[@value='Update Contact']");
		clickOnButton("xpath","//input[@value='Update Contact']");
		Thread.sleep(6000); 
	}
}
