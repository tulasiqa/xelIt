package vibe.teardown.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import vibe.feedBack.tests.FeedBackMethods;

@Priority(919)
public class AdminTearDownFeedBack extends FeedBackMethods {	
	
	@Test(priority=91901)
	public void validateAdminComments() throws Exception{	
		logInfo("Entered validateAdminComments() test");
		navigate2AdminFeedback();				
		boolean isticketPresent= verifyTicketInAdmin(fbTitcketTitle);
		if (isticketPresent= true){
			deleteTicketInAdmin(fbTitcketTitle);
			ticketNotToBePresentInAdmin(fbTitcketTitle);	
		}if(isticketPresent==false){
			Assert.assertTrue(isticketPresent,fbTitcketTitle);
		}	
	}	
	
	@Test(priority=91902)
	public void validatFeedbackAndDelete() throws Exception{	
		logInfo("Entered validatFeedbackAndDelete() test");		
		navigate2AdminFeedback();
		boolean isFeedbackPresent= verifyFeedbackInAdmin(fbFeedbackTitle);
		if (isFeedbackPresent= true){
			System.out.println("entered ");
			deleteFeedbackInAdmin(fbFeedbackTitle);
			feedbackNotToBePresentInAdmin(fbFeedbackTitle);				
		}if(isFeedbackPresent==false){
			Assert.assertTrue(isFeedbackPresent);
		}	
	}
	
	
	/*
	
	@Test(priority=11905)
	public void validateReportAndDelete() throws Exception{
		logInfo("Entered validateReportAndDelete() test");
		back2Office();
		navigate2Feedback();
		validateTickets(fbTitcketTitle);
		commentToTicket(fbTitcketTitle, fbcomment);
		actionOnComments(fbcomment, "Delete");
		
		
		boolean isticketPresent= verifyTicketInAdmin(fbTitcketTitle);
		if (isticketPresent= true){
			deleteTicketInAdmin(fbTitcketTitle);
			ticketNotToBePresentInAdmin(fbTitcketTitle);			
		}if(isticketPresent==false){
			Assert.assertTrue(isticketPresent);
		}	
	}	
	
	@Test(priority=11906)
	public void validateFeedbackWithVoteNdComment() throws Exception{		
		logInfo("Entered validateFeedbackWithVoteNdComment() Test");			
		back2Office();
		navigate2Feedback();
		System.out.println(fbFeedbackTitle);
		createFeedback(fbFeedbackTitle);		
		navigate2Feedback();		
		boolean isFeedbackPresent= validateFeedbacks(fbFeedbackTitle);
		if (isFeedbackPresent= true){
			validateVotedFeedback(fbFeedbackTitle);			
			commentFeedback(fbFeedbackTitle, commentText);
			
		}if(isFeedbackPresent==false){
			Assert.assertTrue(isFeedbackPresent);
		}		
	}
	
	@Test(priority =11907)
	public void validateSearchedTickets() throws Exception{	
		logInfo("Entered validateSearchedTickets() test");
		back2Office();
		navigate2Feedback();
		System.out.println(fbTitcketTitle);
		reportIssue(fbTitcketTitle);	
		navigate2Feedback();
		searchNdVerifyTickets(fbTitcketTitle);	
	}
		
	@Test(priority =11908)
	public void validateSearchedFeedbacks() throws Exception{	
		logInfo("Entered validateSearchedFeedbacks() test");
		//String fbFeedbackTitle = "Awesome photo 119";
		navigate2Feedback();
		searchNdVerifyFeedbacks(fbFeedbackTitle);	
	}
	
	@Test(priority =11909)
	public void validateInvalidSearchsForFeedbacks() throws Exception{	
		logInfo("Entered validateInvalidSearchsForFeedbacks() test");
		String fbFeedbackTitle = "%euireuiyre%90#@"+TestBase.generateRandomNumberInRange(10, 5000);
		navigate2Feedback();
		invalidSearchs(fbFeedbackTitle);	
	}
	
	@Test(priority =11910)
	public void validateFilterTickets() throws Exception{
		logInfo("Entered validateFilterTickets() test");
		//String fbTitcketTitle= "The worst feeling is feeling 249";
		navigate2Feedback();
		navigate2FeedbackTickets();
		searchFilters("4 Months", fbTitcketTitle);		
		searchFilters("2 Months", fbTitcketTitle);		
		searchFilters("6 Months", fbTitcketTitle);
		searchFilters("All", fbTitcketTitle);
		searchFilters("All Unresolved", fbTitcketTitle);
	}
	
	@Test(priority =11911)
	public void validateBoundariesForFeedback() throws Exception{
		logInfo("Entered validateBoundariesForFeedback() test");
		navigate2Feedback();
		verifyBoundaries();	
		
	}
	
	@Test(priority =11912)
	public void validateUsersTicketAndReport() throws Exception {
		logInfo("Entered validateUsersTicketAndReport() test");
		//todo -  Change this user to newly created Enrollment user.
		//nm.loginAsSubUser(VibeProMonthlyUser);	 	
		System.out.println(fbUsersTitcketTitle);
		System.out.println(fbUsersTitcketComment);
		navigate2Feedback();
		reportIssue(fbUsersTitcketTitle);
		navigate2Feedback();		
		commentToTicket(fbUsersTitcketTitle, fbUsersTitcketComment);
		userLogout();		
		back2Office();
		navigate2AdminFeedback();
		addCommentFeedbackInAdmin(fbUsersTitcketTitle, adminComment);
		//nm.loginAsSubUser(VibeProMonthlyUser);	
		navigate2Feedback();
		boolean isticketPresent=false;
		if(isticketPresent =validateTickets(fbUsersTitcketTitle)){
			actionOnComments(adminComment, "Delete");
			userLogout();
			navigate2AdminFeedback();
			verifyReportedInAdmin(fbUsersTitcketTitle);
		}	if(isticketPresent==false){
			Assert.assertTrue(isticketPresent);
		}		
	}
	
	
	@Test(priority =11913)
	public void validateAdminFeedbacksBasedOnSections() throws Exception{
		logInfo("Entered validateAdminFeedbacksBasedOnSections() test");
		//String fbUsersTitcketTitle = "The worst feeling is feeling 123456";	
		
		navigate2AdminFeedback();
		verifySectionsInAdmin(fbUsersTitcketTitle);
		
	}
	
	//
	
	*/
	
	
	
	
	

}
