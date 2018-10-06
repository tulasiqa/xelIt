package vibe.feedBack.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
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

@Priority(119)
public class FeedBackTests extends FeedBackMethods {
	
	
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
	
	@Test(priority=11901)
	public void verifyFeedbackLinkInAllScreens() throws Exception{
		logInfo("Entered verifyFeedbackLinkInAllScreens() test");
		back2Office();
		verifyFeedbackLink();		
		bc.go2ContactsPage();
		verifyFeedbackLink();
		inbox.go2Inbox();
		verifyFeedbackLink();
		comm.nav2Community();
		verifyFeedbackLink();
		profile.nav2profile();
		verifyFeedbackLink();
		ppl.go2PeoplePage();
		verifyFeedbackLink();
		reports.navigate2Report();
		verifyFeedbackLink();
		rl2.navigate2UserRL();
		verifyFeedbackLink();
		tm.navigate2BusinessTasks();
		verifyFeedbackLink();
		
	}
	
	@Test(priority=11902)
	public void validateFeedBack() throws Exception{
		logInfo("Entered validateFeedBack() test");	
		navigate2Feedback();
		objectsVerifications();
		validateFeedBackFields();		
	}
	
	@Test(priority=11903)
	public void validateCommentedTicketAndDelete() throws Exception{
		logInfo("Entered validateCommentedReport() Test");				
		navigate2Feedback();
		reportIssue(fbTitcketTitle);
		verifyFeedbackLink();
		navigate2Feedback();
		nav2ViewTickets();
		boolean isTicket = validateTickets(fbTitcketTitle);
			if(isTicket = true){
			    commentToTicket(fbTitcketTitle, fbcomment);
			    actionOnComments(fbcomment, "Delete");					
				boolean isPresent = isCommentPresent(fbcomment);
				if(isPresent==false) {
					System.out.println("Success!! not present");
				}else {
					Assert.assertTrue(isPresent, fbcomment + " still comment is present");
				}			
				
			}if(isTicket==false){
				Assert.assertTrue(isTicket, fbTitcketTitle +" is ticket is not found");
			}	
	}		
	
	@Test(priority=11904)
	public void editCommentedTicket() throws Exception{
		logInfo("Entered editCommentedTicket() Test");			
		navigate2Feedback();
		nav2ViewTickets();	
		boolean isTicket = validateTickets(fbTitcketTitle);
			if(isTicket = true){
			    commentToTicket(fbTitcketTitle, fbcomment);
			    nav2ViewTickets();
				actionOnComments(fbcomment, "Edit");
				boolean isPresent = isCommentPresent(recommentText);
				if(isPresent==true) {
					System.out.println("Sucess!!  present");
				}else {
					Assert.assertTrue(isPresent, recommentText + " comment is not present");
				}
				
				
			}if(isTicket==false){
				Assert.assertTrue(isTicket, fbTitcketTitle +" is ticket is not found");
			}
	}
	
	@Test(priority=11905)
	public void validateReportAndDelete() throws Exception{
		logInfo("Entered validateReportAndDelete() test");		
		navigate2Feedback();
		reportIssue(fbTitcketTitle);
		navigate2Feedback();		
		commentToTicket(fbTitcketTitle, fbcomment);
		actionOnComments(fbcomment, "SelfReport");	
		actionOnComments(fbcomment, "Delete");		
	}		
	
	@Test(priority=11906)
	public void validateFeedbackWithVoteNdComment() throws Exception{		
		logInfo("Entered validateFeedbackWithVoteNdComment() Test");		
		navigate2Feedback();		
		createFeedback(fbFeedbackTitle);	
		navigate2Feedback();
		nav2ViewFeedbacks();
		boolean isFeedbackPresent= validateFeedbacks(fbFeedbackTitle);		
		if (isFeedbackPresent= true){
			validateVotedFeedback(fbFeedbackTitle);			
			commentFeedback(fbFeedbackTitle, recommentText);
			
		}if(isFeedbackPresent==false){
			Assert.assertTrue(isFeedbackPresent);
		}		
	}	
	
	
	@Test(priority =11907)
	public void searchFeedbacksAndTickets() throws Exception{	
		logInfo("Entered searchFeedbacksAndTickets() test");		
		String invalidTitle = "%euireuiyre%90#@"+TestBase.generateRandomNumberInRange(10, 5000);
		navigate2Feedback();
		nav2ViewFeedbacks();		
		searchFeedbacks(invalidTitle);
		invalidFeedBackSearchs();
		searchFeedbacks(fbFeedbackTitle);
		validateFeedbacks(fbFeedbackTitle);	
		navigate2Feedback();
		nav2ViewTickets();
		searchTickets(fbTitcketTitle);
		boolean isTicket = validateTickets(fbTitcketTitle);
		if(isTicket = true){
			searchTickets(invalidTitle);
			invalidTicketSearchs();			
		 }if(isTicket==false){
			Assert.assertTrue(isTicket, fbTitcketTitle +" is ticket is not found");
		}		
	}	
	
	
	@Test(priority =11908)
	public void validateFilterTickets() throws Exception{
		logInfo("Entered validateFilterTickets() test");		
		navigate2Feedback();
		nav2ViewTickets();
		searchFilters("4 Months", fbTitcketTitle);		
		searchFilters("2 Months", fbTitcketTitle);		
		searchFilters("6 Months", fbTitcketTitle);
		searchFilters("All", fbTitcketTitle);
		searchFilters("All Unresolved", fbTitcketTitle);
	}
	
}
