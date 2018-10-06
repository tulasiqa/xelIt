package vibe.inbox.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.widgets.tests.WidgetsMethods;
import common.Priority;
import common.TestBase;
import common.EnvProperties;


@Priority(127)
 public class InboxTests extends InboxMethods {

	
     public static String txtImpVibeEmailSubject = "Important_" + TestBase.random();
     public static String txtStarVibeEmailSubject = "Starred_" + TestBase.random();
     public static String txtReadVibeEmailSubject = "ReadUnRead_" + TestBase.random();
     public static String txtTrashVibeEmailSubject = "TrashUntrash_" + TestBase.random();
     public static String txtSpamVibeEmailSubject = "SpamUnspam_" + TestBase.random();
     public static String txtVibeOptoutEmail = "Vibe_Optout_Mail_"+ TestBase.random();
     public static String txtReplyVibeEmailSubject = "Reply_" + TestBase.random();
     public static String txtForwardVibeEmailSubject = "Forward_" + TestBase.random();
     public static String txtDeleteForEverVibeEmailSubject = "DeleteForEver_" + TestBase.random();
     
    
     WidgetsMethods wm = new WidgetsMethods();
     MyProfileMethods profile = new MyProfileMethods();
     EnvProperties prop  = new EnvProperties();

     @Test(priority=12701)
		public void SendEmailFromGmail() throws Exception{
			logInfo("inside sendEmailFromGmail() Test");
			
			loginGmail(gmailId_text, gmailPwd_text);
			composeGmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),composeSubject_text,composeDesc_text);
			signoutGmail();
			go2Inbox();
			boolean isMailFound = selectVibeMailsWithSubject(composeSubject_text);
			if(isMailFound==true){
				deleteFilteredVibeMails(); 
			} else {
				Assert.assertTrue(isMailFound, composeSubject_text + " email with subject not found in vibe Inbox.");
			}
  	}	
	   
	   
	   @Test(priority=12702)
		public void SendEmail2GmailUsers() throws Exception{
			logInfo("inside sendEmail2GmailUsers() Test");
			
			composeVibeEmail(recipientsGmail_text, txtGmailSubject);
			loginGmail(gmailId_text, gmailPwd_text);
			boolean isMailFound = verifyInboxGmail(txtGmailSubject);
			signoutGmail();
			go2Inbox();
			if(isMailFound==false){
				Assert.assertTrue(isMailFound, txtGmailSubject + " email with subject not found in Gmail.");
			}
		}



	
	//  Mark an email as starred and verify if displayed in starred emails section. 
	
	@Test(priority=12704)
	public void MarkEmailAsStarred() throws Exception{
		logInfo("inside markEmailAsStarred() Test");
		
		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),txtStarVibeEmailSubject);  
		boolean isMailPresent = false;
		isMailPresent = selectMailsWithSubject(txtStarVibeEmailSubject);
		if(isMailPresent==true){
			markEmailStarred(txtStarVibeEmailSubject);
			logInfo(txtStarVibeEmailSubject + " mail match found in Inbox.");
			selectInboxFolder("Starred");
			boolean isMailStarred = selectMailsWithSubject(txtStarVibeEmailSubject);
			 if(isMailStarred==true){
				 logInfo(txtStarVibeEmailSubject + " email found in Starred folder.");
				 Assert.assertTrue(isMailStarred, txtStarVibeEmailSubject + " email found in Starred folder.");
			 } else {
				 logInfo(txtStarVibeEmailSubject  + " email not found in Starred folder.");
			 }
		} else {
			logInfo(txtStarVibeEmailSubject + " mail match not found in Inbox.");
			Assert.assertTrue(isMailPresent, txtStarVibeEmailSubject + " mail match not found in Inbox.");
		}
	}
	
	
	@Test(priority=12705) 
	public void UnmarkStarredEmail() throws Exception{
		logInfo("inside unmarkStarredEmail() Test");
		boolean isMailPresent = false;
			performMoreActionsForSelectedEmails("Mark As Unstarred");
			
			isMailPresent = selectMailsWithSubject(txtStarVibeEmailSubject);
			if(isMailPresent==true){
				logInfo(txtStarVibeEmailSubject + " unstarred email should not be present in starred folder.");
				deleteFilteredVibeMails();
				Assert.assertFalse(isMailPresent, txtStarVibeEmailSubject + " unstarred email should not be present in starred folder.");
			} else {
				selectInboxFolder("Inbox");
				boolean isMailUnstarred = selectMailsWithSubject(txtStarVibeEmailSubject);
				 if(isMailUnstarred==true){
					 logInfo(txtStarVibeEmailSubject + " unstarred email found in Inbox folder.");
					 deleteFilteredVibeMails();
					 
				 } else {
					 logInfo(txtStarVibeEmailSubject + " unstarred email not found in Inbox folder.");
					 Assert.assertTrue(isMailUnstarred, txtStarVibeEmailSubject + " unstarred email not found in Inbox folder.");
				 }
		}
	}
	 	
	//Mark an email as important and verify if displayed in important emails section.

	
	@Test(priority=12706)
	public void MarkEmailAsImportant() throws Exception{
		logInfo("inside markEmailAsImportant() Test");

		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),txtImpVibeEmailSubject);  
		boolean isMailPresent = false;
		isMailPresent = selectMailsWithSubject(txtImpVibeEmailSubject);
		if(isMailPresent==true){
			markEmailsImportant(txtImpVibeEmailSubject);
			logInfo(txtImpVibeEmailSubject + " mail match found in Inbox.");
			selectInboxFolder("Important");
			boolean isMailImportant = false;
			isMailImportant = selectMailsWithSubject(txtImpVibeEmailSubject);
			 if(isMailImportant==true){
				 logInfo(txtImpVibeEmailSubject + " email found in Important folder.");
			} else {
				 logInfo(txtImpVibeEmailSubject + " email not found in Important folder.");
				 Assert.assertTrue(isMailImportant, txtImpVibeEmailSubject + " email found in Important folder.");
			 }
		} else {
			logInfo(txtImpVibeEmailSubject + " mail match not found in Inbox.");
			Assert.assertTrue(isMailPresent, txtImpVibeEmailSubject + " mail match not found in Inbox.");
		}
	}
	
	
	@Test(priority=12707)   //, dependsOnMethods={"markEmailAsImportant"})
	public void UnmarkImportantEmails() throws Exception{
		logInfo("inside unmarkImportantEmails() Test");
		boolean isMailPresent = false;
		  	performMoreActionsForSelectedEmails("Mark As Unimportant");
			
			isMailPresent = selectMailsWithSubject(txtImpVibeEmailSubject);
			if(isMailPresent==true){
				logInfo(txtImpVibeEmailSubject + " Unimportant email should not be present in Important folder.");
				deleteFilteredVibeMails();
				Assert.assertFalse(isMailPresent, txtImpVibeEmailSubject + " Unimportant email should not be present in Important folder.");
			} else {
				selectInboxFolder("Inbox");
				boolean isMailUnimportant = selectMailsWithSubject(txtImpVibeEmailSubject);
				 if(isMailUnimportant==true){
					 logInfo(txtImpVibeEmailSubject + " Unimportant email found in Inbox folder.");
					 deleteFilteredVibeMails();
				 } else {
					 logInfo(txtImpVibeEmailSubject + " Unimportant email not found in Inbox folder.");
					 Assert.assertTrue(isMailUnimportant, txtImpVibeEmailSubject + " Unimportant email not found in Inbox folder.");
				 }
		}
	}
	 	

	//Add Label to emails and verify if label is associated to the email.
		
	@Test(priority=12708)
	public void SetEmailLabel() throws Exception{
		logInfo("inside setEmailLabel() Test");
		
		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),txtVibeEmailSubject);  
		createLabel(labelName_text);
		boolean isMailFound = selectVibeMailsWithSubject(txtVibeEmailSubject);
		 if(isMailFound){
			 applyLabel(labelName_text);
			 boolean isLabelFound = verifyLabeledEmail(txtVibeEmailSubject);
			 if(isLabelFound){
					deleteFilteredVibeMails();
					deleteLabel(labelName_text);
					
					boolean isLabelDeleted = verifyEmailLabelIsPresent(labelName_text);
					if(isLabelDeleted==true) {
						logInfo(labelName_text+" email label could not be deleted.");
						Assert.assertFalse(isLabelDeleted, labelName_text+" email label could not be deleted.");
					} else {
						logInfo(labelName_text+" email label deleted successfully.");
					}
				}  
		  } else {
			  logInfo(txtVibeEmailSubject + " email not found in Inbox folder");
			  Assert.assertTrue(isMailFound,txtVibeEmailSubject + " email not found in Inbox folder");
		  }
	}
			
			
	@Test(priority=12709)
	public void SendEmail2VibeUserwithResourceAttachment() throws Exception{
		logInfo("inside sendEmail2VibeUserwithResourceAttachment() Test");
	
		composeVibeEmailwithRescAttachment(prop.getLocatorForEnvironment(appUrl,"selfmailId1"), "resource attached", "Black Cream Shave January 17, 2018","Black Cream Shave");
		boolean isMailPresent = selectMailsWithSubject("resource attached");
		if(isMailPresent==true){
			logInfo("resource attached mail found in inbox");
			deleteFilteredVibeMails();
		} else {
			logInfo("resource attached mail not found in inbox");
			Assert.assertTrue(isMailPresent, "resource attached mail not found in inbox");
		}
	
	}
	
	
	@Test(priority=12711)
	public void SendEmailAsAttachment() throws Exception{
		logInfo("inside sendEmailAsAttachment() Test");
		
		txtVibeEmailSubject = txtVibeEmailSubject + " file attachment";  // uncomment later
		composeVibeEmailwithAttachment(prop.getLocatorForEnvironment(appUrl,"selfmailId1") ,txtVibeEmailSubject);
	
		boolean isMailPresent = selectVibeMailsWithSubject(txtVibeEmailSubject);
		if(isMailPresent==false){
			 logInfo(txtVibeEmailSubject + " email with attachment not found in Inbox.");
			 Assert.assertTrue(isMailPresent, txtVibeEmailSubject + " email with attachment not found in Inbox.");
		 } else {
			 logInfo(txtVibeEmailSubject + " email found in Inbox.");
			 selectInboxFolder("Attachments");
			 boolean isAttachmentFound = verifyAttachmentIsPresent(prop.getLocatorForEnvironment(appUrl,"selfmailId1"));
			 if(isAttachmentFound==false){
				 logInfo(prop.getLocatorForEnvironment(appUrl,"selfmailId1") + " email is not present in Attachment folder.");
				 //Assert.assertTrue(isAttachmentFound, prop.getLocatorForEnvironment(appUrl,"selfmailId1") + " email is not present in Attachment folder.");
			 } else {
				 logInfo(prop.getLocatorForEnvironment(appUrl,"selfmailId1") + " email is present in Attachment folder.");
				 
				 /*boolean isFileDownloaded =  downloadAttachment(prop.getLocatorForEnvironment(appUrl,"selfmailId1"));
				 if(isFileDownloaded==false){
					 logInfo("file could not be downloaded successfully in c:\\vibe\\downloads folder.");
					 Assert.assertTrue(isFileDownloaded, "file could not be downloaded successfully in c:\\downloads folder.");
				 }*/
					 
			 }
		 } 
		
		 go2Inbox();
		 boolean isMailFound = selectMailsWithSubject(txtVibeEmailSubject);
		 if(isMailFound==true){
			 logInfo(txtStarVibeEmailSubject + " unstarred email found in Inbox folder.");
			 deleteFilteredVibeMails();
		 } 

	}
	
	@Test(priority=12712)
	public void SendEmail2otherVibeUser() throws Exception{
		logInfo("inside sendEmail2otherVibeUser() Test");
		boolean isMailPresent = false;
		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"adminEmail_text"),"send email to other users");  // 
		clickHere2Go2AdminAccount();
		go2Inbox();
		
		isMailPresent = selectVibeMailsWithSubject("send email to other users");
		if(isMailPresent){
			logInfo(txtVibeEmailSubject + " email present in Inbox");
			deleteFilteredVibeMails();
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
					prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
			go2Inbox();
		} else {
			logInfo(txtVibeEmailSubject + " email not present in Inbox");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
					prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
			go2Inbox();
			Assert.assertTrue(isMailPresent, txtVibeEmailSubject + " email not present in Inbox");
		}
	}
  
	
	@Test(priority=12714)
	public void Show10EmailsPerpage() throws Exception{
		logInfo("inside show10EmailsPerpage() Test");
		
		go2Inbox();
		int actEmailsCnt = 0;
		actEmailsCnt = countEmailsInInboxPage("10 emails per page","All");
		System.out.println("Total emails = " +actEmailsCnt);
		//Assert.assertEquals(actEmailsCnt, 10);
		
	}
	
	@Test(priority=12715)
	public void Show25EmailsPerpage() throws Exception{
		logInfo("inside show25EmailsPerpage() Test");
		
		go2Inbox();
		int actEmailsCnt = 0;
		actEmailsCnt = countEmailsInInboxPage("25 emails per page","All");
		System.out.println("Total emails = " +actEmailsCnt);
		//Assert.assertEquals(actEmailsCnt, 25);
		
	}
	
		
	@Test(priority=12716)
	public void Show50EmailsPerpage() throws Exception{
		logInfo("inside show50EmailsPerpage() Test");
		
		go2Inbox();
		//setMailsPerPage("50 emails per page");
		int actEmailsCnt = 0;
		actEmailsCnt = countEmailsInInboxPage("50 emails per page","All");
		System.out.println("Total emails = " +actEmailsCnt);
		//Assert.assertEquals(actEmailsCnt, 50);
		
	}
	
	
	@Test(priority=12717)
	public void Show100EmailsPerpage() throws Exception{
		logInfo("inside show100EmailsPerpage() Test");
		
		go2Inbox();
		// setMailsPerPage("100 emails per page");
		int actEmailsCnt = 0;
		actEmailsCnt = countEmailsInInboxPage("100 emails per page","All");
		System.out.println("Total emails = " +actEmailsCnt);
		//Assert.assertEquals(actEmailsCnt, 100);
	}
	
	
	@Test(priority=12718)
	public void SearchInboxMail() throws Exception{
		logInfo("inside searchInboxMail() Test");
		
		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),"Mail Search Test"); 
	    searchEmail("Mail Search Test");
	    boolean isMailPresent = false;
	    isMailPresent = selectMailsWithSubject("Mail Search Test");
	    if(isMailPresent){
	    	logInfo("Mail Search Test" + " email with subject found in the search");
	    	go2Inbox();
	    	selectMailsWithSubject("Mail Search Test");
	    	deleteFilteredVibeMails();
	    	boolean isMailDeleted = false;
	    	isMailDeleted = selectMailsWithSubject("Mail Search Test");
	    	if(isMailDeleted==true){
	    		logInfo("Mail Search Test" + " email could not be deleted");
		    	Assert.assertFalse(isMailPresent, "Mail Search Test" + " email could not be deleted");
	    	}
	    } else {
	    	logInfo("Mail Search Test" + " email with subject not found in the search");
	    	Assert.assertTrue(isMailPresent, "Mail Search Test" + " email with subject not found in the search");
	    }
	}
	
	
	@Test(priority=12720)
	public void MarkEmailAsRead() throws Exception{
		logInfo("inside markEmailAsRead() Test");
		
		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),txtReadVibeEmailSubject);  
		boolean isMailPresent = false;
		isMailPresent = selectMailsWithSubject(txtReadVibeEmailSubject);
		if(isMailPresent==true){
			logInfo(txtReadVibeEmailSubject + " mail match found.");
			viewEmailsWithSubject(txtReadVibeEmailSubject);				// view email
			boolean isMailRead = false;
			go2Inbox();
			 isMailRead = verifyEmailsCheckedForFilter(txtReadVibeEmailSubject,"Read");
			 System.out.println("is mail read = " +isMailRead);
			 if(isMailRead==false){
				 logInfo(txtReadVibeEmailSubject + " email not marked as Read.");
				 Assert.assertTrue(isMailRead, txtReadVibeEmailSubject + " email not marked as Read.");
			 } else {
				 logInfo(txtReadVibeEmailSubject + " email marked as Read.");
			 }
		} else {
			logInfo(txtReadVibeEmailSubject + " mail match not found.");
			Assert.assertTrue(isMailPresent, txtReadVibeEmailSubject + " mail match not found.");
		}
	}
	
	
	
	@Test(priority=12721) //  , dependsOnMethods={"markEmailAsRead"}) 
	public void MarkEmailAsUnread() throws Exception{
		logInfo("inside markEmailAsUnread() Test");
		boolean isMailFound = false;
		
		go2Inbox();	
		isMailFound = selectMailsWithSubject(txtReadVibeEmailSubject);
				
		if(isMailFound==false){
				 logInfo(txtReadVibeEmailSubject + " email not found in Inbox.");
				 Assert.assertTrue(isMailFound, txtReadVibeEmailSubject + " email not found in Inbox.");
			 } else {
				 performMoreActionsForSelectedEmails("Mark As Unread");
				 logInfo(txtReadVibeEmailSubject + " email marked as Unread.");
				 boolean isMailUnread = verifyEmailsCheckedForFilter(txtReadVibeEmailSubject,"Unread");
				 if(isMailUnread==false){
					 logInfo(txtReadVibeEmailSubject + " email could not be found in Unread emails.");
					 Assert.assertTrue(isMailUnread, txtReadVibeEmailSubject + " email could not be found in Unread emails.");
				 } else {
					 logInfo(txtReadVibeEmailSubject + " email found in Unread emails.");
					 go2Inbox();
					 selectMailsWithSubject(txtReadVibeEmailSubject);
					 deleteFilteredVibeMails();
				 }
		 }
	} 
	
	
	@Test(priority=12722)
	public void MarkEmailASpam() throws Exception{
		logInfo("inside MarkEmailASpam() Test");
		
		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),txtSpamVibeEmailSubject);  
		boolean isMailPresent = false;
		isMailPresent = selectMailsWithSubject(txtSpamVibeEmailSubject);
		if(isMailPresent==true){
			logInfo(txtReadVibeEmailSubject + " mail match found.");
			viewEmailsWithSubject(txtSpamVibeEmailSubject);	
			markEmailAsSpam();
			boolean isMailSpam = false;
			
			selectInboxFolder("Inbox");
			boolean isSpamInInbox = selectMailsWithSubject(txtSpamVibeEmailSubject);
			System.out.println("is spam mail in Inbox = " +isSpamInInbox);
			if(isSpamInInbox==true) {
				logInfo(txtSpamVibeEmailSubject + "spam mail should not be inbox.");
				Assert.assertFalse(isSpamInInbox, txtSpamVibeEmailSubject + "spam mail should not be inbox.");
			 }
			
			selectInboxFolder("Spam");
			isMailSpam = selectMailsWithSubject(txtSpamVibeEmailSubject);
			 System.out.println("is mail spam = " +isMailSpam);
			 if(isMailSpam==false){
				 logInfo(txtSpamVibeEmailSubject + " email not marked as Spam.");
				 Assert.assertTrue(isMailSpam, txtSpamVibeEmailSubject + " email not marked as Spam.");
			 } else {
				 logInfo(txtSpamVibeEmailSubject + " email marked as Spam.");
			 }
		} else {
			logInfo(txtSpamVibeEmailSubject + " mail match not found.");
			Assert.assertTrue(isMailPresent, txtSpamVibeEmailSubject + " mail match not found.");
		}
	}
	
	
	@Test(priority=12723) //  , dependsOnMethods={"markEmailAsRead"}) 
	public void UnmarkEmailAsUnspam() throws Exception{
		logInfo("inside UnmarkEmailAsUnspam() Test");
		boolean isMailFound = false;
		
		performMoreActionsForSelectedEmails("Mark As Not Spam");
		selectInboxFolder("Spam");
		boolean isUnspamEmailFound = false;
		isUnspamEmailFound = selectMailsWithSubject(txtSpamVibeEmailSubject);
		if(isUnspamEmailFound==true){
			 logInfo(txtSpamVibeEmailSubject + " unspam email should not present in spam folders.");
			 Assert.assertFalse(isUnspamEmailFound, txtSpamVibeEmailSubject + " unspam email should not present in spam folders.");
		 }
		
		go2Inbox();	
		isMailFound = selectMailsWithSubject(txtSpamVibeEmailSubject);
				
		if(isMailFound==true){
			 logInfo(txtSpamVibeEmailSubject + " unspam email found in Inbox folder.");
			 selectMailsWithSubject(txtSpamVibeEmailSubject);
			 deleteFilteredVibeMails();
		 } else {
			 logInfo(txtSpamVibeEmailSubject + " unspam email not found in Inbox.");
			 Assert.assertTrue(isMailFound, txtSpamVibeEmailSubject + " unspam email should present in Inbox.");
		 }
		
		 }
	 
	
	@Test(priority=12724)
	public void VerifyEmailInSentbox() throws Exception{
		logInfo("inside verifyEmailInSentbox() Test");
		boolean isMailSent = false;
		
		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),txtVibeEmailSubject);  
		go2Inbox();
		selectInboxFolder("Sent");
		isMailSent = selectMailsWithSubject(txtVibeEmailSubject);
		if(isMailSent==false){
				 logInfo(txtVibeEmailSubject + " email not visible in Sent folder.");
				 go2Inbox();
				 Assert.assertTrue(isMailSent, txtVibeEmailSubject + " email not visible in Sent folder.");
			 } else {
				 logInfo(txtVibeEmailSubject + " email is visible in Sent folder.");
				 deleteFilteredVibeMails();
				 go2Inbox();
		 }
	}
	
	
	
	@Test(priority=12725)
	public void DeleteEmail2Trash() throws Exception{
		logInfo("inside deleteEmail2Trash() Test");
		
		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),txtTrashVibeEmailSubject);  
		
		boolean isMailFound = selectMailsWithSubject(txtTrashVibeEmailSubject);
		if(isMailFound==false){
				 logInfo(txtTrashVibeEmailSubject + " email not visible in Inbox.");
				 Assert.assertTrue(isMailFound, txtTrashVibeEmailSubject + " email not visible in in Inbox.");
			 } else {
				 logInfo(txtTrashVibeEmailSubject + " email is visible in Inbox.");
				 clickOnElement("xpath",btnDeleteInboxMail);
				 selectInboxFolder("Trash");
				 boolean isMailFoundInTrash = selectMailsWithSubject(txtTrashVibeEmailSubject);;
				 if(isMailFoundInTrash==false){
					 logInfo(txtTrashVibeEmailSubject + " email not visible in Trash folder.");
					 Assert.assertTrue(isMailFoundInTrash, txtTrashVibeEmailSubject + " email not visible in Trash folder.");
				 } else {
					 logInfo(txtTrashVibeEmailSubject + " email is visible in Trash folder.");
			 }
		 }
	}
	
	
	@Test(priority=12726)  // , dependsOnMethods={"deleteEmail2Trash"})
	public void UndeleteEmailFromTrash() throws Exception{
		logInfo("inside undeleteEmailFromTrash() Test");
		
		// go2Inbox();
		// selectInboxFolder("Trash");
		
		clickOnElement("xpath",btnDeleteInboxMail);
		// ConfirmationMessage("Messages restored successfully.");
		selectInboxFolder("Inbox");
		boolean isMailFound = selectMailsWithSubject(txtTrashVibeEmailSubject);
		if(isMailFound==false){
				 logInfo(txtTrashVibeEmailSubject + " undelete emails could not be restored.");
				 Assert.assertTrue(isMailFound, txtTrashVibeEmailSubject + " undelete emails could not be restored.");
			 } else {
				 logInfo(txtTrashVibeEmailSubject + " undelete emails restored successfully.");
				 deleteFilteredVibeMails();
			 }
	}
	
	
	@Test(priority=12727)
	public void DeleteEmailForEver() throws Exception{
		logInfo("inside deleteEmailForEver() Test");
		
		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),txtDeleteForEverVibeEmailSubject);  
		boolean isMailFound = selectMailsWithSubject(txtDeleteForEverVibeEmailSubject);
		if(isMailFound==true){
			 clickOnElement("xpath",btnDeleteInboxMail);
			 selectInboxFolder("Trash");
			boolean isMailFoundInTrash = selectMailsWithSubject(txtDeleteForEverVibeEmailSubject);
			System.out.println("isMailFoundInTrash = " +isMailFoundInTrash);
			if(isMailFoundInTrash==true){
				clickOnElement("xpath","//*[@id='bulk-controls']/div/button[2]"); // btnDeleteForEver
				logInfo("clicked on Delete For Ever button");
				boolean isMailDeletedInTrash = selectMailsWithSubject(txtDeleteForEverVibeEmailSubject);
				if(isMailDeletedInTrash==true){
						 logInfo(txtDeleteForEverVibeEmailSubject + " deleted emails still seen in Trash folder.");
						 Assert.assertFalse(isMailDeletedInTrash, txtDeleteForEverVibeEmailSubject + " deleted emails still seen in Trash folder.");
					 } 
				
				selectInboxFolder("Inbox");
				boolean isMailDeletedInInbox = selectMailsWithSubject(txtDeleteForEverVibeEmailSubject);
				if(isMailDeletedInInbox==true){
						 logInfo(txtDeleteForEverVibeEmailSubject + " deleted emails still seen in Inbox folder.");
						 Assert.assertFalse(isMailFound, txtDeleteForEverVibeEmailSubject + " deleted emails still seen in Inbox folder.");
					 } 
				
			} else {
				Assert.assertTrue(isMailFoundInTrash, txtDeleteForEverVibeEmailSubject + " deleted email not found in Trash folder.");
			}
		
		} else {
			Assert.assertTrue(isMailFound, txtDeleteForEverVibeEmailSubject + " email not found in Inbox folder.");
		}
	}
	
	
	@Test(priority=12728)
	public void CreateDuplicateLabels() throws Exception{
		logInfo("inside CreateDuplicateLabels() Test");
		go2Inbox();
		createLabel(labelName_text);
		go2Inbox();
		createLabel(labelName_text);
		confirmationMessage("Unable to create label: - Name " + labelName_text + " is already being used.");
		deleteLabel(labelName_text);
	}
	
	

	@Test(priority=12729)
	public void ManageLabels() throws Exception{
		logInfo("inside ManageLabels() Test");
		go2Inbox();
		createLabel(labelName_text);
		updateEmailLabel(labelName_text);
		boolean isLabelUpdated = verifyEmailLabelIsPresent(labelName_text+"_Updated");
		if(isLabelUpdated==true) {
			deleteLabel(labelName_text+"_Updated");
		} else {
			logInfo(labelName_text+"_Updated" + " label match not found.");
			Assert.assertTrue(isLabelUpdated, labelName_text+"_Updated" + " label match not found.");
		}
	}
	

	@Test(priority=12730)
	public void Reply2Users() throws Exception{
		logInfo("inside reply2Users() Test");
		boolean isMailPresent = false;
		boolean isReplyMailPresent = false;
		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"adminEmail_text"),txtReplyVibeEmailSubject);
		clickHere2Go2AdminAccount();
		go2Inbox();
		isMailPresent = selectVibeMailsWithSubject(txtReplyVibeEmailSubject);
		if(isMailPresent){
			logInfo(txtReplyVibeEmailSubject + " email present in Inbox");
			viewEmailsWithSubject(txtReplyVibeEmailSubject);
			submitReply(txtReplyVibeEmailSubject + " replied");
			go2Homepage();
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
					prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
			go2Inbox();
			isReplyMailPresent = selectVibeMailsWithSubject(txtReplyVibeEmailSubject + " replied");
			if(isReplyMailPresent== true){
				logInfo(txtReplyVibeEmailSubject + " replied" + " present in Inbox");
				deleteFilteredVibeMails(); 
			} else {
				logInfo(txtReplyVibeEmailSubject + " replied" + " not present in Inbox");
				Assert.assertTrue(isReplyMailPresent, txtReplyVibeEmailSubject + " replied" + " not present in Inbox");
			}
		} else {
			logInfo(txtReplyVibeEmailSubject + " email not present in Inbox");
			Assert.assertTrue(isMailPresent, txtReplyVibeEmailSubject + " email not present in Inbox");
		}
	
	}
	
	
	
	@Test(priority=12731)
	public void SendEmailWithNoData() throws Exception{
		logInfo("inside sendEmailWithNoData() Test");
		
		composeEmailWithNoData();
	}
	
	// Unsubscribe link will present when email is sent to non vibe users.
	
	@Test(priority=12732)
	public void UnSubscribeFromGmailUser() throws Exception{
		logInfo("inside unSubscribeFromGmailUser() Test");
		
		composeVibeEmail(recipientsGmail_text, txtGmailSubject);
		loginGmail(gmailId_text, gmailPwd_text);
		boolean isMailFound = verifyInboxGmail(txtGmailSubject);
		if(isMailFound==true) {
			openMailInGmail(txtGmailSubject);  // txtGmailSubject
			unsubscribeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"), "Optout", recipientsGmail_text);
			signoutGmail();
			composeVibeEmail(recipientsGmail_text, txtVibeOptoutEmail);
			loginGmail(gmailId_text, gmailPwd_text);
			boolean isOptoutMailFound = verifyInboxGmail(txtVibeOptoutEmail);
			openMailInGmail(txtGmailSubject);  // txtGmailSubject
			unsubscribeEmail(prop.getLocatorForEnvironment(appUrl,"selfmailId1"), "Optin", recipientsGmail_text);
			signoutGmail();
			go2Inbox();
			if(isOptoutMailFound==true){
				logInfo(txtVibeOptoutEmail + " vibe mail not found after opting out.");
				Assert.assertFalse(isOptoutMailFound, txtVibeOptoutEmail + " vibe mail not found after opting out.");
			}
			
		} else {
			logInfo(txtGmailSubject + " vibe mail not found");
			Assert.assertTrue(isMailFound, txtGmailSubject + " vibe mail not found");
		
		}
		
	}
	
	

	@Test(priority=12733)
	public void ForwardEmail2Users() throws Exception{
		logInfo("inside forwardEmail2Users() Test");
		boolean isMailPresent = false;
	  
		composeVibeEmail(prop.getLocatorForEnvironment(appUrl,"adminEmail_text"),txtForwardVibeEmailSubject);
		clickHere2Go2AdminAccount();
		go2Inbox();
		isMailPresent = selectVibeMailsWithSubject(txtForwardVibeEmailSubject);
		if(isMailPresent){
			logInfo(txtForwardVibeEmailSubject + " email present in Inbox");
			viewEmailsWithSubject(txtForwardVibeEmailSubject);
			forwardEmail(txtForwardVibeEmailSubject + " forwarded", prop.getLocatorForEnvironment(appUrl,"selfmailId1"));
			go2Homepage();
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
					prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
			go2Inbox();
			boolean isForwardMailPresent = selectVibeMailsWithSubject(txtForwardVibeEmailSubject + " forwarded");
			if(isForwardMailPresent){
				logInfo(txtForwardVibeEmailSubject + " forwarded" + " present in Inbox");
				deleteFilteredVibeMails(); 
			} else {
				logInfo(txtForwardVibeEmailSubject + " forwarded" + " not present in Inbox");
				Assert.assertTrue(isForwardMailPresent, txtForwardVibeEmailSubject+ " forwarded" + " not present in Inbox");
			}
		} else {
			logInfo(txtForwardVibeEmailSubject + " email not present in Inbox");
		}
		
	}
	
	
	@Test(priority=12735)
	public void SearchInboxMailForInvalidData() throws Exception{
		logInfo("inside searchInboxMailForInvalidData() Test");
		String txtVibeEmailInvalidSubject = "abcd@gmail.com";
		go2Inbox();
	    searchEmail(txtVibeEmailInvalidSubject);
	    String noEmailMsg = getTextPresentOnElement("xpath",inboxNoEmails);
	    Assert.assertEquals(noEmailMsg, "You Don't Have Any Messages");
	}
 
	
 // Email Signatures
 	
 	@Test(priority=12750)
 	public void CreateEmailSignature() throws Exception{
 		logInfo("inside CreateEmailSignature Test");
 		go2EmailSignaturesPage();
		addSignature(signatureName);
 		boolean isSignatureFound = 	verifyEmailSignatureIsPresent(signatureName);
 		if(isSignatureFound){
 			logInfo(signatureName + " signature created sucessfully.");
 		}else{
 			logInfo(signatureName + " signature could not be created.");
 	 		Assert.assertTrue(isSignatureFound, signatureName + " signature could not be created.");
 		}
 	}

	@Test(priority=12751)		// dependsOn{"CreateEmailSignature"}
 	public void MarkSignatureAsDefault() throws Exception{
		logInfo("inside markSignatureAsDefault Test");
	
		go2HomePage();
 		go2EmailSignaturesPage();
 		boolean isSignatureFound = 	verifyEmailSignatureIsPresent(signatureName);
 		if(isSignatureFound){
 			selectSignatureAction(signatureName,"Set as Default");
 			go2HomePage();
 	 		go2EmailSignaturesPage();
 			verifySignatureIsMarkedDefault(signatureName);
 		}else{
 			logInfo(signatureName + " signature could not be created.");
 			Assert.assertTrue(isSignatureFound, signatureName + " signature could not be created."); 
 		}
 	}
	
 	
 	@Test(priority=12752)  // dependsOn{"MarkSignatureAsDefault"}
 		public void ComposeEmailWithDefaultSignature() throws Exception{
 		logInfo("inside ComposeEmailWithDefaultSignature Test");
 		go2Inbox();
 		composeVibeEmailWithSingature(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),composeBodySignatureText, signatureName);  
 		boolean isMailFound = selectVibeMailsWithSubject(composeBodySignatureText);
			if(isMailFound==true){
				logInfo(composeBodySignatureText + " vibe email match found");
				deleteFilteredVibeMails(); 
			} else {
				logInfo(composeBodySignatureText + " vibe mail not found");
				Assert.assertTrue(isMailFound, composeBodySignatureText + " vibe mail not found");
			}
 		}
 	
 	
 	@Test(priority=12753)  // dependsOn{"MarkSignatureAsDefault"}
		public void ComposeEmail31GiftsWithDefaultSignature() throws Exception{
		logInfo("inside ComposeEmail31GiftsWithDefaultSignature Test");
		go2Inbox();
		composeVibeEmailWithSingature("vibe.icentris@gmail.com",composeBodySignatureText, signatureName);  
		loginGmail(gmailId_text, gmailPwd_text);
		boolean isMailFound = verifyInboxGmail(composeBodySignatureText);
		signoutGmail();
		go2Inbox();
		if(isMailFound==true){
			logInfo(composeBodySignatureText + " gmail email match found");
		} else {
			logInfo(composeBodySignatureText + " gmail mail not found");
			Assert.assertTrue(isMailFound, composeBodySignatureText + " gmail mail not found");
		}
	}
	
 	
 	
 	@Test(priority=12754)			// dependsOn{"CreateEmailSignature"}
 	public void DeleteEmailSignature() throws Exception{
 		logInfo("inside DeleteEmailSignature Test");
 		go2EmailSignaturesPage();
 		selectSignatureAction(signatureName,"Delete");
 		boolean isSignatureDeleted = verifyEmailSignatureIsPresent(signatureName);
 		if(isSignatureDeleted==true){
 			logInfo(signatureName + " signature could not be deleted.");
 			Assert.assertFalse(isSignatureDeleted, signatureName + " signature could not be deleted.");
 		}else{
 			logInfo(signatureName + " signature deleted successfully.");
 		}
 	}
 
 	
	@Test(priority=12755)  
 	public void ManageEmailSignature() throws Exception{
		logInfo("inside ManageEmailSignature Test");
		go2HomePage();
		go2EmailSignaturesPage();
 		addSignature(signatureName);
 		go2HomePage();
 		go2EmailSignaturesPage();
 		boolean isSignatureFound = 	verifyEmailSignatureIsPresent(signatureName);
 		if(isSignatureFound){
 			selectSignatureAction(signatureName,"Edit");
 			editAnEmailSignature(signatureName,signatureName+"_Updated");
 			boolean isSignatureUpdated = 	verifyEmailSignatureIsPresent(signatureName+"_Updated");
 	 		if(isSignatureUpdated){
 	 			logInfo(signatureName + " signature is updated");
 	 			selectSignatureAction(signatureName+"_Updated","Delete");
 	 			boolean isSignatureDeleted = 	verifyEmailSignatureIsPresent(signatureName+"_Updated");
 	 	 		if(isSignatureDeleted){
 	 	 			logInfo(signatureName+"_Updated signature could not be deleted");
 	 	 			Assert.assertFalse(isSignatureDeleted, signatureName+"_Updated signature could not be deleted");
 	 	 		}
 	 		} else {
 	 			logInfo(signatureName + " signature not updated");
 				Assert.assertTrue(isSignatureUpdated, signatureName + " signature not updated");
 	 		}
 		} else {
			logInfo(signatureName + " could not be created");
			Assert.assertTrue(isSignatureFound, signatureName + " could not be created");
 		}
 	
	}
	
	
	// Email Templates
	
 	@Test(priority=12756)
 	public void  CreateEmailTemplate() throws Exception{
 		logInfo("inside CreateEmailTemplate Test");
 		go2HomePage();
 		go2EmailTemplatespage();
 		addEmailTemplate(templateName,templateName+" subject",templateName+" content");
 		boolean isTemplateAvailable = verifyEmailTemplateIsPresent(templateName);
 		if(isTemplateAvailable){
 			logInfo(templateName + " email template could not be created.");
 			Assert.assertTrue(isTemplateAvailable, templateName + " email template could not be created.");
 		}
   }
 	
 	
	@Test(priority=12757)  // dependsOn{"CreateEmailTemplate"}
		public void ComposeEmailWithEmailTemplate() throws Exception{
		logInfo("inside ComposeEmailWithEmailTemplate Test");
		go2Inbox();
		composeVibeEmailWithTemplate(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),composeBodyTempText, templateName);  
		boolean isMailFound = selectVibeMailsWithSubject(templateName+" subject");
		if(isMailFound==true){
			logInfo(composeBodyTempText + " vibe email match found");
			deleteFilteredVibeMails(); 
		} else {
			logInfo(composeBodyTempText + " vibe mail not found");
			Assert.assertTrue(isMailFound, composeBodyTempText + " vibe mail not found");
		}
	}
	
	
	@Test(priority=12758)  // dependsOn{"CreateEmailTemplate"}
	public void ComposeEmail31giftsWithEmailTemplate() throws Exception{
		logInfo("inside ComposeEmail31giftsWithEmailTemplate Test");
		go2Inbox();
		composeVibeEmailWithTemplate(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),composeBodyTempText, templateName);  
		loginGmail(gmailId_text, gmailPwd_text);
		boolean isMailFound = verifyInboxGmail(composeBodyTempText);
		signoutGmail();
		go2Inbox();
		if(isMailFound==true){
			logInfo(composeBodyTempText+ " email match found in Gmail.");
		} else {
			logInfo(composeBodyTempText + " gmail mail not found");
			Assert.assertTrue(isMailFound, composeBodyTempText + " email with subject not found in Gmail.");
		}
	}
	
 	@Test(priority=12760) 	// dependsOn{"CreateEmailTemplate"}
 	public void  DeleteEmailTemplate() throws Exception{
 		logInfo("inside DeleteEmailTemplate Test");
 		go2HomePage();
 		go2EmailTemplatespage();
 		selectTemplateAction(templateName,"Delete");
 		boolean isTemplateDeleted = verifyEmailTemplateIsPresent(templateName);
 		if(isTemplateDeleted){
 			logInfo(templateName + " email template could not be deleted.");
 			Assert.assertFalse(isTemplateDeleted, templateName + " email template could not be deleted.");
 		} else {
			logInfo(templateName + " email template deleted sucessfully.");
	 	}
   }
 	
 
 	@Test(priority=12762)  
 	public void ManageEmailTemplate() throws Exception{
		logInfo("inside ManageEmailTemplate Test");
		go2HomePage();
		go2EmailTemplatespage();
		addEmailTemplate(templateName,templateName+" subject",templateName+" content");
		go2HomePage();
		go2EmailTemplatespage();
 		boolean isTemplateFound = verifyEmailTemplateIsPresent(templateName);
 		if(isTemplateFound){
 			selectTemplateAction(templateName,"Edit");
 			editEmailTemplate(templateName,templateName+"_Updated");
 			go2HomePage();
 			go2EmailTemplatespage();
 			boolean isTemplateUpdated = verifyEmailTemplateIsPresent(templateName+"_Updated");
 	 		if(isTemplateUpdated){
 	 			logInfo(templateName + " template is updated");
 	 			selectTemplateAction(templateName+"_Updated","Delete");
 	 			boolean isTemplateDeleted = verifyEmailTemplateIsPresent(templateName+"_Updated");
 	 	 		if(isTemplateDeleted){
 	 	 			logInfo(templateName+"_Updated templateName could not be deleted");
 	 	 			Assert.assertFalse(isTemplateDeleted, templateName+"_Updated templateName could not be deleted");
 	 	 		}
 	 		} else {
 	 			logInfo(templateName + " signature not updated");
 				Assert.assertTrue(isTemplateUpdated, templateName + " template not updated");
 	 		}
 	 	 } else {
			logInfo(templateName + " template could not be created");
			Assert.assertTrue(isTemplateFound, templateName + " template could not be created");
 		}
 	}
	

 	@Test(priority=12763)  
	public void CreateAndSaveTemplateFromVibeEmail() throws Exception{
		logInfo("inside CreateAndSaveTemplateFromVibeEmail Test");
		go2Inbox();
		compose2CreateSaveAsTemplate(prop.getLocatorForEnvironment(appUrl,"selfmailId1"),"test email",templateName);  
		System.out.println(saveTemplateName+" subject");
		boolean isMailFound = selectVibeMailsWithSubject(templateName+" subject");
		if(isMailFound==true){
			logInfo("save as template mail" + " vibe email match found");
			deleteFilteredVibeMails(); 
			go2EmailTemplatespage();
			boolean isTemplateFound = verifyEmailTemplateIsPresent(saveTemplateName);
	 		if(isTemplateFound){
	 			logInfo(saveTemplateName + " template found.");
	 			selectTemplateAction(saveTemplateName,"Delete");
	 		} else {
	 			logInfo(saveTemplateName + " template not found.");
	 			Assert.assertTrue(isMailFound, saveTemplateName + " template not found.");
	 		}
			
		} else {
			logInfo("save as template mail" + " vibe email not found");
			Assert.assertTrue(isMailFound, "save as template mail" + " vibe mail not found");
		} 
 	}
 	
}
