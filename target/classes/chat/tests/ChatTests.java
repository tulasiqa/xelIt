package vibe.chat.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.EnvProperties;
import vibe.calendar2.tests.CalendarTests;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.contacts2.tests.BusinessContactsTests;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.people.tests.PeopleMethods;
import vibe.reports.tests.ReportsMethods;
import vibe.resourcelibrary3.tests.RL3Methods;
import vibe.tasks.tests.TaskMethods;
import vibe.training.tests.TrainingMethods;
import vibe.users.tests.UsersMethods;

@Priority(130)
public class ChatTests extends ChatMethods {
	CalendarTests cm = new CalendarTests();
	BusinessContactsMethods bc = new BusinessContactsMethods();
	InboxMethods inbox = new InboxMethods();
	CommunityMethods comm = new CommunityMethods();
	MyProfileMethods profile = new MyProfileMethods();
	PeopleMethods ppl = new PeopleMethods();
	ReportsMethods reports = new ReportsMethods();
	RL3Methods rl2 = new RL3Methods();
	TaskMethods tm = new TaskMethods();
	TrainingMethods train = new TrainingMethods(); 
	NewsMethods nm = new NewsMethods();
	BusinessContactsTests cont = new BusinessContactsTests();
	UsersMethods um = new UsersMethods();
	EnvProperties prop = new EnvProperties();
	
	
	public static String vibeTochatUser= "";// vibeUser(adminUser_text); 
	
	@Test(priority=13000)
	public void createUser() throws Exception{
		logInfo("Entered into createUser test");
		String email = "icentris.qa31@gmail.com";
		um.go2Users();
		um.addAdminUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"),email);
		back2Office();
		
	}
	
	@Test(priority=13001)
	public void assertsChatScreen(){
		logInfo("Assertion of all fields on Chat Screen");
		try{
			go2ChatPage();	
			Assert.assertTrue(isChatTitlePresent());
			Assert.assertTrue(isNewMessagePresent());
			Assert.assertTrue(isFilterByPresent());
		}catch(Exception e){
			logInfo("Failed!! Some fields does not matches with expected Objects");
		}
	}
	
	// chat with online user and verifies same user in Quick Chat along with message.
	@Test(priority=13002)
	public void liveChatwithUser() throws Exception{
		go2ChatPage();	
		selectChatOptions("Online");
		validateRecentChat();
		}
	
	@Test(priority=13003)
	public void validateAlertMessageForMandatoryFields() throws Exception{
		go2ChatPage();			
		validateErrorsOfNewMessage();
	}

	@Test(priority=13004)
	public void inviteUsers2Chat_old() throws  Exception{
		
		String email = "icentris.qa31@gmail.com";
		um.go2Users();
		um.addAdminUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"),email);
		back2Office();		
		go2People();					
		searchChatUser(userName_text);
		boolean isMatchFound = verifyIfUserFound(userName_text);
		if (isMatchFound==true){
			inviteUser2Chat(userName_text);
			logOut();
			logIn(userName_text, userPassword_text);
			go2People();
			searchUser(prop.getLocatorForEnvironment(appUrl,"adminUser_text"));
			boolean isSearchFound = verifyIfUserFound(prop.getLocatorForEnvironment(appUrl,"adminUser_text"));
			if (isSearchFound==true){
				acceptChatRequest(prop.getLocatorForEnvironment(appUrl,"adminUser_text"));
				logOut();
			}
			if (isSearchFound==false){
				Assert.assertTrue(isSearchFound, userName_text + " user could not found in search people page." );
			}
		}
	}
	
	@Test(priority=13004)
	public void verifyQuickChat() throws Exception{		
		back2Office();
		validateQuickChat();				
		cm.go2CalendarPage();
		validateQuickChat();
		bc.go2ContactsPage();
		validateQuickChat();
		inbox.go2Inbox();
		validateQuickChat();
		comm.nav2Community();
		validateQuickChat();
		profile.nav2profile();
		validateQuickChat();
		ppl.go2PeoplePage();
		validateQuickChat();
		reports.navigate2Report();
		validateQuickChat();
		rl2.navigate2UserRL();
		validateQuickChat();
		tm.navigate2BusinessTasks();
		validateQuickChat();
				
	}
	
	@Test(priority=13005)		
	  public void chatwithVibeUsers() throws Exception{          
         /* String userName = "toni";
          String loginUser = "mariehelenelauziere";*/
          back2Office();          
          loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
          expandNdSelectTypeOfChat("Recent Chats");
        //  selectUser();
          
          selectAUserFromQuickchat(prop.getLocatorForEnvironment(appUrl,"newsFN2"));    
         sendMsgFomChatBox(chatMessageText1);
          userLogout();            
          loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon2"));
          back2Office();
          expandNdSelectTypeOfChat("Recent Chats");    
          selectUserFromRecentChats("Marie");
          validateMsgInChat(chatMessageText1);
        sendMsgFomChatBox(chatMessageText2);
          userLogout(); 
          loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
          expandNdSelectTypeOfChat("Recent Chats");    
          selectUserFromRecentChats(prop.getLocatorForEnvironment(appUrl,"newsFN1"));
          validateMsgInChat(chatMessageText2);
          userLogout();
		}
	
	
	
	
	
	
	@Test(priority=13005 )
	public void chatWithUser() throws  Exception{
		logInfo("Entered into chatWithUser() Test");
		go2ChatPage();
		boolean isChatUserPresent = verifyUserPresentInChatPage(userName_text);
		if(isChatUserPresent==true){
			sendMessage2ChatUser("sample message");
			verifyMessageSent2User(userName_text,"sample message");
		}
	}	
	

	// Create a user and send chat message to user2 and respond from user2
	@Test(priority=13006)
	public void chattingBetweenTwoUsers() throws  Exception{
		logInfo("Entered into chattingBetweenTwoUsers() Test");
		 String email = "icentris.qa31@gmail.com";
		//String userName_text2 = "chatUser843";
		um.go2Users();
		System.out.println(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		addUserToChat(prop.getLocatorForEnvironment(appUrl,"userName_text2"),email);
		back2Office();
		String FName = profile.getAccountProfileFirstName();	
		System.out.println(FName);
		go2ChatPage();					
		searchChatUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		Thread.sleep(7000);
		boolean isMatchFound = isUserPresent(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		if(isMatchFound==true){
			sendChatMessage(prop.getLocatorForEnvironment(appUrl,"userName_text2"), chatMessageText1);			
			logOut();
			logIn(prop.getLocatorForEnvironment(appUrl,"userName_text2"), userPassword_text);
			go2ChatPage();	
			searchChatUser(FName);
			boolean isSearchFound = isUserPresent(FName);
			if (isSearchFound==true){
				validateMsgInChat(chatMessageText1);
				sendChatMessage(FName, chatMessageText3);
				logOut();
				logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
				
			}
			if (isSearchFound==false){
				Assert.assertTrue(isSearchFound, prop.getLocatorForEnvironment(appUrl,"adminUser_text") + " user could not found in search people page." );
			}
		}
	}
	
  @Test(priority = 13007)
	  
	  public void reloginAsAdmin() throws Exception{
		  logOut();
		  logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
	  }	
	
	
	// incomplete
	@Test(priority=13008)
	public void verifyPendingIncomingChats() throws Exception{
		//String userName_text =  "testuser374";
		/*String vibeTochatUser=" icentris.qa003@gmail.com";*/
		//nm.loginAsUser(userName_text);			
		ppl.go2PeoplePage();
		ppl.searchUser(vibeTochatUser);
		ppl.chatPeople();
		clickOnLink("cssSelector", logoutHere);
		Thread.sleep(5000);		
		back2Office();
		expandNdSelectTypeOfChat("Pending Chats");
		pendingIncomingList("Incoming Chat Requests", userName_text);	
		verifyChatRquests();
	}
	
	
	
	@Test(priority=13009)
	public void verifyPendingOutgoingChats() throws  Exception{
	    logInfo("inside verifyPendingOutgoingChats() Test");	
	   // String userName_text2 =  "testuser374";
	 	um.go2Users();
	 	um.addAdminUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"), userSecurityGrps2_text);   // change userName_text2 to when user created from enrollment
	 	System.out.println(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
	 	ppl.go2PeoplePage();
		ppl.searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		ppl.chatPeople();
		back2Office();
		expandNdSelectTypeOfChat("Pending Chats");
		pendingOutgoingList("Outgoing Chat Requests", prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		verifyChatRquests();
		sendMsgToNotAcceptedUser();	 	
	}	
	
	@Test(priority=13010)
	public void addNdRemoveResourceAttach() throws Exception{		
		back2Office();
		expandNdSelectTypeOfChat("Recent Chats");		
		/*selectChatOptionsInQuickChat("Online");		*/
		selectUser();
		addToRemovefiles("Visible to all");
		isAttachedPresentInChatWindow();
		
	}

	@Test(priority=13011)
	public void verifyResourceAttach() throws Exception{		
		back2Office();
		expandNdSelectTypeOfChat("All Chats");		
		selectChatOptionsInQuickChat("Online");		
		selectUser();
		addfiles("CorporateImage71304130");
		isAttachedPresentInChatWindow();
		
	}
	
	@Test(priority=13012)
	public void groupChat() throws Exception{
		back2Office();
		grpChat("Kevin", "Srinivas", chatMessageText1);
		expandNdSelectTypeOfChat("Recent Chats");
		selectAUserFromQuickchat("Srinivas");	
		validateChatMsg(chatMessageText1);	
		
		
	}
		
	@Test(priority=13006)		
	  public void chatwithMonatUsers() throws Exception{    
            //String chatMessageText1 = "Hello, Wazzup 11307";
            //String userName = "toni vanschoyck";
            //String userName = "toni";           
            back2Office();
            //nm.loginAsUser(prop.getLocatorForEnvironment(appUrl,"loginUser"));            
            expandNdSelectTypeOfChat("Recent Chats");
            selectAUserFromQuickchat(prop.getLocatorForEnvironment(appUrl,"adfirstname"));    
           /* chat.sendMsgFomChatBox(chatMessageText1);
            nm.userLogout();  
            loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"adfirstname"),
            		                prop.getLocatorForEnvironment(appUrl,"adlasttname"),
            		                prop.getLocatorForEnvironment(appUrl,"adconsultantid"));
                     
            back2Office();
            expandNdSelectTypeOfChat("Recent Chats");    
            selectUserFromRecentChats("Marie");
            validateMsgInChat(chatMessageText1);
            chat.sendMsgFomChatBox(chatMessageText2);
            nm.userLogout(); 
            nm.loginAsUser(prop.getLocatorForEnvironment(appUrl,"loginUser"));
            expandNdSelectTypeOfChat("Recent Chats");    
            selectUserFromRecentChats(userName);
            validateMsgInChat(chatMessageText2);
            nm.userLogout();*/
		}
		
		@Test(priority=13006)
		public void chattingBetweenTwoUsersinIDLife() throws Exception{	
			//String chatMessageText1 = "Hello, Wazzup 11307";
			//String userName = "icentris.qa130+1013092@gm";						
			back2Office();
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			
			ppl.go2PeoplePage();
			ppl.searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));
			ppl.searchAndChatWithUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));
			sendMsgFomChatBox(chatMessageText1);
			validateMsgInChat(chatMessageText1);	
			
		}
		
		@Test(priority=13007)
		public void userLogoutFromChat() throws Exception{
			userLogout();
			
			
		}
		
		

}
