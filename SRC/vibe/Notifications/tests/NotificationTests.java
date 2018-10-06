package vibe.Notifications.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import vibe.inbox.tests.InboxMethods;
import common.EnvProperties;

@Priority(128)
public class NotificationTests extends NotificationMethods{
	
	
	EnvProperties prop = new EnvProperties();
	InboxMethods inbox = new InboxMethods();

	
	@Test(priority=12801)
	public void verifyTitlesInNotification() throws Exception{		
		 logInfo("Entered into verifyTitlesCountsInNotification() Test");			
		 navAlerts();
		 verifyPanelTitle();
		 navMailNotifications();
		 verifyPanelTitle();			
		 getListOfPriority();
		 selectPriority(prMed);			 
	}
	
	/*@Test(priority=12803)	
    public void verifyEmailAndReplyThroughTools() throws Exception{
		 logInfo("Entered into verifyEmailAndReplyThroughTools() test");			
         inbox.composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"), txtGmailSubject);
		 navMailNotifications();
		 selectNotifactionPanel(toolsMsgRecipient);
		 selectMsgOfChatUser(msgText2 + prop.getLocatorForEnvironment(appUrl,"selfmailId1"));
		 replyToMail();
		
	}*/	
	
	

	@Test(priority=12802)
    public void handleMailsAlerts() throws Exception{
		 logInfo("Entered into handleMailsAlerts() Test");
		 navMailNotifications();
		 int allCount = getCountOfAll();
		 int alertCount = getAlertCount();
		 Assert.assertEquals(allCount, alertCount);
		 
		/* getCountOfAlerts();
		 getCountOfMails();
		 getListOfNotifactionPanel();
		 getListOfPriority();	*/
		  
		  
		  /*.selectNotifactionPanel(toolsMsgRecipient)		 
		  .replyToMailFromPanel();	*/
		  
		  
		  ;
		}
	
	
	
	
	
	
	/*
	
	 @Test(priority=12804)	
	public void markAllSeenInNotification() throws Exception{	
		 logInfo("Entered into markAllSeenInNotification() test");
		navMailNotifications();
		 selectNotifactionPanel("All");
		 verifyMarkAllSeen();

	}
	
	@Test(priority=12805)	
    public void forwardMailThroughMessage() throws Exception{
		logInfo("Entered into forwardMailThroughMessage() test");
		try{	
			 navMailNotifications();
			 selectNotifactionPanel(toolsMsgRecipient);	 
			 forwardMailFromPanel();	

		}
		catch(Exception ex){
		ex.printStackTrace();
		}
		 
	}
	
	@Test(priority=12806)	
    public void Alerts_VerifyTasksDetails() throws Exception{
  		 logInfo("Retrieve Count of Alerts and Create the Task with tommorow's Date. \n"
				+ "Again retrieve increased count and verify the Tasks Notifcations with details");			
		 retrieveCountsWithTasks();
			
	}
	
	
	@Test(priority=12807)
	public void Notification_ValidatePage() throws Exception{
		logInfo("Entered into Notification_ValidatePage Test");				
		navMailNotifications();
		//getPanelTitle(panelTitleText2);
		getListOfNotifactionPanel();
		selectPriorityInAvon(prMed);
		allMessageHeadersInPanel();				
		
	}
	
	 //"Validate Panel Titles, Alert,Notification, All counts");
	@Test(priority=12840)
	public void avonNotification_ValidatePage() throws Exception{
		logInfo("Entered into avonNotification_ValidatePage Test");
		
		
		avon_navNotifications();
		getPanelTitle(panelTitleText2);
		getListOfNotifactionPanel();
		selectPriorityInAvon(prMed);
		//allMessageHeadersInPanel();		


		
	}
	

@Test(priority=12841)
	
	public void notification_marKAllSeen_forAvon() throws Exception{
		
	     navMailNotifications();
		 selectNotifactionPanel("All");
		 verifyMarkAllSeen();
		 
		
	}*/

	
}
