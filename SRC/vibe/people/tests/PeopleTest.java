package vibe.people.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.EnvProperties;
import common.Priority;
import vibe.chat.tests.ChatMethods;
@Priority(131)
public class PeopleTest extends PeopleMethods {	
	
	EnvProperties prop = new EnvProperties();
	ChatMethods cm = new ChatMethods();
	
	
	@Test(priority =13101)
	public void searchPeople() throws Exception{
		logInfo("inside searchPeople() Test.");	
		go2PeoplePage();	
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		boolean isPeopleFound = verifyChatUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));	
		if(isPeopleFound==false){
			Assert.assertTrue(isPeopleFound, prop.getLocatorForEnvironment(appUrl,"userName_text2") + " people match not found in people page.");
		}
	}	

	@Test(priority =13141)
	public void searchPeople_InvalidUserValidation() throws Exception{
		logInfo("inside searchPeople() Test.");	
		go2PeoplePage();	
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text3"));
		boolean isUsersFound = verifyPeopleListFilter();	
		if(isUsersFound==false){			
			Assert.assertTrue(isUsersFound,"Users are found in the table");
		}
	}	

		
	@Test(priority =13102)
	public void followAndUnfollowUser() throws Exception{
		logInfo("inside followAndUnfollowUser() Test.");
		int followersCountBefore, followersCountAfter;
		go2MyProfilePage();
		followersCountBefore = countMyprofileFollowers();
		System.out.println(followersCountBefore);
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"),"Follow");
		confirmationMessage("You are now following "+ prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		go2MyProfilePage();
		followersCountAfter = countMyprofileFollowers();
		System.out.println(followersCountAfter);		
		Assert.assertEquals(followersCountAfter, followersCountBefore+1);		
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"),"Unfollow");
		confirmationMessage("You are no longer following "+ prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		go2MyProfilePage();
		int afterUnFollower = countMyprofileFollowers();	
		Assert.assertEquals(afterUnFollower, followersCountBefore);		
	}
	
	
	
	@Test(priority =13103)
	public void ChatWithOnlineUsers() throws Exception{
		logInfo("inside followAndUnfollowUser() Test.");
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		chatPeople();
		//setChatFilter("Online","Newest User");
		sendMessage2ChatUser(chatString);
		boolean isMsgFound = verifyChatMessage(chatString);
		if(!isMsgFound){
			Assert.assertTrue(isMsgFound, "chat string not found in chat window");
		}
	}

	
	@Test(priority =13104)
	public void blockAndUnblockUser() throws Exception{
		logInfo("inside blockAndUnblockUser() Test.");
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));	
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"),"Block User");
		confirmationMessage("You blocked "+ prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		go2HomePage();
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"),"Unblock User");
		confirmationMessage("You unblocked "+ prop.getLocatorForEnvironment(appUrl,"userName_text2"));
	
	}
	
	
	@Test(priority =13105)
	public void chatWithOfflineUsers() throws Exception{
		logInfo("inside chatWithOfflineUsers() Test.");
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));	
		sendOfflineMessage();
	}
	
	
	@Test(priority =13106)
	public void verifyProfilePage() throws Exception{
		logInfo("inside verifyProfilePage() Test.");
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		boolean isPeopleFound = verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text"));	
		if(isPeopleFound==false){
			Assert.assertTrue(isPeopleFound, prop.getLocatorForEnvironment(appUrl,"userName_text") + " people match not found in people page.");
		}
		followUser();
		go2MyProfilePage();
		boolean isMatchFound = verifyUserInMyprofilePage(prop.getLocatorForEnvironment(appUrl,"userName_text"));	
		if(isMatchFound==false){
			Assert.assertTrue(isMatchFound, "Title or Header did not match in user profile page.");
		}
		
	}
	
	@Test(priority = 13120)
	public void filterByDownline() throws Exception {
		logInfo("inside filterByDownline() Test.");
		go2PeoplePage();
		selectFromFilter("Downline");
		boolean isUsersFound = verifyPeopleListFilter();
		if(isUsersFound==false){			
			Assert.assertTrue(isUsersFound,"Users are found in the table");
		}	
	}
	
	@Test(priority = 13121)
	public void filterByFollowers() throws Exception {
		logInfo("inside filterByFollowers() Test.");
		userLogout();
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN5"),
				prop.getLocatorForEnvironment(appUrl,"newsLN5"),prop.getLocatorForEnvironment(appUrl,"newsCon5"));	
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text"),"Follow");
		confirmationMessage("You are now following "+ prop.getLocatorForEnvironment(appUrl,"userName_text"));
		userLogout();
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
		go2PeoplePage();
		selectFromFilter("Followers");
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));	
	}
	
	@Test(priority = 13122)
	public void filterByFollowing() throws Exception {
		logInfo("inside filterByFollowing() Test.");
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"),"Follow");
		confirmationMessage("You are now following "+ prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		selectFromFilter("Following");
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));	
	}
	
	@Test(priority = 13123)
	public void filterByNewFollowers() throws Exception {
		logInfo("inside filterByNewFollowers() Test.");
		go2PeoplePage();
		userLogout();
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN5"),
				prop.getLocatorForEnvironment(appUrl,"newsLN5"),prop.getLocatorForEnvironment(appUrl,"newsCon5"));	
		go2PeoplePage();
		selectFromFilter("New Followers");
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text"),"Unfollow");
		confirmationMessage("You are no longer following "+ prop.getLocatorForEnvironment(appUrl,"userName_text"));
		userLogout();
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
		go2PeoplePage();
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"),"Unfollow");
		confirmationMessage("You are no longer following "+ prop.getLocatorForEnvironment(appUrl,"userName_text2"));
	}
	
	
	
	@Test(priority = 13123)
	public void filterByTeam() throws Exception {
		logInfo("inside filterByTeam() Test.");
		go2HomePage();
		go2PeoplePage();
		selectFromFilter("My Team");
		boolean isUsersFound = verifyPeopleListFilter();
		if(isUsersFound==false){			
			Assert.assertTrue(isUsersFound,"Users are found in the table");
		}
		
	}
	
	
	@Test(priority = 13124)
	public void filterByOnline() throws Exception {
		logInfo("inside filterByTeam() Test.");
		go2HomePage();
		go2PeoplePage();
		selectFromFilter("Online");
		boolean isUsersFound = verifyPeopleListFilter();
		if(isUsersFound==false){			
			Assert.assertTrue(isUsersFound,"Users are found in the table");
		}
		
	}
	
	@Test(priority = 13125)
	public void filterByConsultantRank() throws Exception {
		logInfo("inside filterByConsultantRank() Test.");
		go2PeoplePage();
		selectFromFilter("Rank");
		selectRanks("Consultant");
		verifyRanksFiltered("Consultant");
	}
	
	@Test(priority = 13126)
	public void filterByManagerRank() throws Exception {
		logInfo("inside filterByManagerRank() Test.");
		go2HomePage();
		go2PeoplePage();
		selectFromFilter("Rank");
		selectRanks("Manager");
		verifyRanksFiltered("Manager");
	}
	
	@Test(priority = 13127)
	public void filterByDirectorRank() throws Exception {
		logInfo("inside filterByDirectorRank() Test.");
		go2HomePage();
		go2PeoplePage();
		selectFromFilter("Rank");
		selectRanks("Director");
		verifyRanksFiltered("Director");
	}
	
	
	@Test(priority = 13128)
	public void filterByEmeraldRank() throws Exception {
		logInfo("inside filterByEmeraldRank() Test.");
		go2HomePage();
		go2PeoplePage();
		selectFromFilter("Rank");
		selectRanks("Emerald");
		verifyRanksFiltered("Emerald");
	}

	@Test(priority = 13129)
	public void filterByDiamondRank() throws Exception {
		logInfo("inside filterByEmeraldRank() Test.");
		go2HomePage();
		go2PeoplePage();
		selectFromFilter("Rank");
		selectRanks("Diamond");
		verifyRanksFiltered("Diamond");
	}
	
	@Test(priority = 13130)
	public void filterByDiamondBlackPearlRank() throws Exception {
		logInfo("inside filterByDiamondBlackPearlRank() Test.");
		go2HomePage();
		go2PeoplePage();
		selectFromFilter("Rank");
		selectRanks("Diamond Black Pearl");
		verifyRanksFiltered("Diamond Black Pearl");
	}
	
	@Test(priority = 13131)
	public void filterByDoubleDiamondRank() throws Exception {
		logInfo("inside filterByDiamondBlackPearlRank() Test.");
		go2HomePage();
		go2PeoplePage();
		selectFromFilter("Rank");
		selectRanks("Double Diamond");
		verifyRanksFiltered("Double Diamond");
	}
	
	@Test(priority = 13132)
	public void filterByTripleDiamondRank() throws Exception {
		logInfo("inside filterByTripleDiamondRank() Test.");
		go2HomePage();
		go2PeoplePage();
		selectFromFilter("Rank");
		selectRanks("Triple Diamond");
		verifyRanksFiltered("Triple Diamond");
	}
	
	@Test(priority = 13133)
	public void filterByPresidentailDiamondRank() throws Exception {
		logInfo("inside filterByPresidentailDiamondRank() Test.");
		go2HomePage();
		go2PeoplePage();
		selectFromFilter("Rank");
		selectRanks("Presidential Diamond");
		verifyRanksFiltered("Presidential Diamond");
	}
	
	@Test(priority = 13134)
	public void filterByPresidentailRhodiumRank() throws Exception {
		logInfo("inside filterByPresidentailRhodiumRank() Test.");
		go2HomePage();
		go2PeoplePage();
		selectFromFilter("Rank");
		selectRanks("Presidential Rhodium");
		verifyRanksFiltered("Presidential Rhodium");
	}
	
	@Test(priority =13126)
	public void inviteToChat() throws Exception{
		logInfo("inside inviteToChat() Test.");
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));	
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"),"Invite to chat");
		userLogout();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));	
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN5"),
				prop.getLocatorForEnvironment(appUrl,"newsLN5"),prop.getLocatorForEnvironment(appUrl,"newsCon5"));
		go2PeoplePage();
		cm.expandNdSelectTypeOfChat("Pending Chats");
		cm.pendingIncomingList("Incoming Chat Requests", prop.getLocatorForEnvironment(appUrl,"userName_text"));	
		cm.selectAUserFromQuickchat(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		cm.selectChatRequestType("Reject Chat Request");
		
	
	}
	
}
