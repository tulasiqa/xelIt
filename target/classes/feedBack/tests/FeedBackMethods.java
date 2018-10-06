package vibe.feedBack.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.By;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import common.TestBase;

import vibe.marketing.companyNews.tests.NewsMethods;


public class FeedBackMethods extends TestBase {
	NewsMethods nm = new NewsMethods();

	public void navigate2Feedback() throws Exception{	
		logInfo("Entered navigate2Feedback() method");
		back2Office();
		if(!driver().getCurrentUrl().contains("worldventures")) {
		scrollDown("cssSelector", navFeedBack);
		Boolean isPresent = false;			
		List<WebElement> feed = driver().findElements(By.cssSelector(navFeedBack));	
	    for (WebElement links :feed){	    	
	    	if(links.getText().trim().equalsIgnoreCase(feedbackText)){
	    		isPresent=true;
	    		links.click();
	    		break;
	    	}
	    }if(isPresent==false){			
		Assert.assertEquals(isPresent, feedbackText);		
		}}else {
			waitOnElement("cssSelector", support);
			scrollDown("cssSelector", support);
			scrollDown("cssSelector", toolsIcon);
			clickOnElement("cssSelector", support);
		}		
		waitOnElement("cssSelector", fbTitle);
		waitOnElement("cssSelector", fbTitle);
		WebElement tit = driver().findElement(By.cssSelector(fbTitle));		
		Assert.assertEquals(tit.getText(), fbTitleText);
	    
	}
	
	public void navigate2AdminFeedback() throws Exception{
		logInfo("Entered navigate2AdminFeedback() method");		
		driver().navigate().to(appUrl+"community/admin_feedbacks");
	}
	
		public void nav2ViewTickets() throws Exception{
			logInfo("Entered navigate2FeedbackTickets() method");
			 navigate2Feedback();
			 waitOnSpinner();
			 waitOnElement("cssSelector", fbViewTickets);	
			 clickOnElement("cssSelector", fbReport);
			 waitOnElement("cssSelector", fbViewTickets);	
			 clickOnButton("cssSelector", fbViewTickets);				
		}
		
		public void verifyFeedbackLink() throws Exception{	
			logInfo("Entered objectsVerifications() method");
			if(!driver().getCurrentUrl().contains("worldventures")) {
			Boolean isPresent = false;	
			waitOnElement("cssSelector", navFeedBack);
			scrollDown("cssSelector", navFeedBack);
			List<WebElement> feed = driver().findElements(By.cssSelector(navFeedBack));	
		    for (WebElement links :feed){		    	
		    	if(links.getText().trim().equalsIgnoreCase(feedbackText)){
		    		isPresent=true;		    		
		    		break;
		    	}
		    }if(isPresent==false){			
			Assert.assertEquals(isPresent, feedbackText);		
			}}else {
				waitOnElement("cssSelector", support);
				scrollDown("cssSelector", support);
				String sup = driver().findElement(By.cssSelector(support)).getText().trim();
				Assert.assertEquals(sup, supportText);
			}
		}
		
		
	public void objectsVerifications() throws Exception{
		logInfo("Entered objectsVerifications() method");
		waitOnElement("cssSelector", fbTitle);
		waitOnElement("cssSelector", fbTitle);
		WebElement tit = driver().findElement(By.cssSelector(fbTitle));		
		Assert.assertEquals(tit.getText(), fbTitleText);
		waitOnElement("cssSelector", fbViewTickets);
		WebElement vTickets = driver().findElement(By.cssSelector(fbViewTickets));		
		Assert.assertEquals(vTickets.getText(), fbViewText);
		WebElement reportIssue = driver().findElement(By.cssSelector(fbReport));		
		Assert.assertEquals(reportIssue.getText(), fbReportText);		
		WebElement feedBack = driver().findElement(By.xpath(fbFeedback));		
		Assert.assertEquals(feedBack.getText(), fbFeedbackText);	
		}
	
	 public void validateFeedBackFields() throws Exception{
		logInfo("Entered into validateFeedBackFields() method");
		waitOnElement("cssSelector", fbSubmit);
		clickOnButton("cssSelector", fbSubmit);
		waitOnElement("cssSelector", fbSubmit);
	    Thread.sleep(1000);
		waitOnElement("cssSelector", imgAlert);
		String title = driver().findElement(By.cssSelector(imgAlert)).getText();			
		Assert.assertEquals(title, validateText); 
	 	}
	 
	 // Report an issue 
	 public void reportIssue(String reportTitle) throws Exception{
		 logInfo ("Entered  reportIssue() method");
		 waitOnElement("cssSelector", fbReport);
		 clickOnElement("cssSelector", fbReport);
		 Thread.sleep(1000);		 
		 inputTextClear("cssSelector", fbMsgTitle);
		 inputText("cssSelector", fbMsgTitle, reportTitle);	
		 inputTextClear("cssSelector", fbMsgTitle);
		 inputText("cssSelector", fbMsgTitle, reportTitle);	
		 waitOnElement("cssSelector", fbChooseFile);
		 uploadFile("Image", fbChooseFile);	
		 inputText("xpath", fbDescp, fbReportText+TestBase.generateRandomNumberInRange(10, 5000));	
		 Thread.sleep(2000);
	     WebElement submit = driver().findElement(By.cssSelector(fbSubmit));
		 submit.click();	 
		 
	 }	
	 
	 //select View tickets and validate the latest created Report
	 public boolean validateTickets(String ticket) throws Exception{
		 logInfo("Entered into validateTickets() method");		 
		 waitOnSpinner();
		 waitOnElement("cssSelector", fbTickList);
		 List<WebElement> tic = driver().findElements(By.cssSelector(fbTickList));		
		 boolean isTicketPresent = false;
		 for (int i=2; i<=tic.size()*2; i++){
			 WebElement tList = driver().findElement(By.cssSelector(fbTickList1+i+fbTickList2));		
			 String actTicket = tList.getText().trim();
			 System.out.println("actTicket "+actTicket);
			 if(actTicket.equalsIgnoreCase(ticket)){
				 isTicketPresent=true;					 
				 break;
			 }
			 i++;
		 }
		return isTicketPresent;
		 
	 }
	 
	 
	 public void commentToTicket(String ticket, String commentText) throws Exception{
		 logInfo("Inside commentToTicket() method");		 
		 boolean isTicketPresent = false;
		 waitOnElement("cssSelector", fbReport);		 
		 clickOnElement("cssSelector", fbReport);
		 Thread.sleep(3000);		 
		 clickOnButton("cssSelector", fbViewTickets);		 
		 List<WebElement> tic = driver().findElements(By.cssSelector(fbTickList));	 
		 for (int i=2; i<=tic.size()*2; i++){
			 WebElement tList = driver().findElement(By.cssSelector(fbTickList1+i+fbTickList2));			
			 if(tList.getText().equalsIgnoreCase(ticket)){				 
				 isTicketPresent=true;
				 int j= i+1;
				 WebElement comment = driver().findElement(By.cssSelector(fbTickList1+j+fbTicComment));
				 comment.click();
				 waitOnElement("cssSelector", fbCommentTextField);
				 inputText("cssSelector", fbCommentTextField, commentText);				 
				 clickOnButton("cssSelector", fbCommSubmit);
				 waitOnElement("cssSelector", fbTickList);					 
				 break;
			 }
			 i++;
		 }if (isTicketPresent==false){
			 Assert.assertTrue(isTicketPresent, ticket +" is not found");
		 }
		 
	 }
	 
	 
	 public void actionOnComments(String ticketComment, String type) throws Exception{
		 logInfo("Entered verifyCommentAndDelete() method");	
		 waitOnSpinner();
		 waitOnElement("cssSelector", fbExistComment);	
		 List<WebElement> com = driver().findElements(By.cssSelector(fbExistComment));		 
		 boolean iscommPresent = false;
		 for (int i=1; i<=com.size()*2; i++){
			 WebElement et =driver().findElement(By.cssSelector(fbExistCommentB4r+i+fbExistCommentA4r));
			 if(et.getText().trim().equalsIgnoreCase(ticketComment)){				 
				 iscommPresent=true;				 
				 switch (type) {
				 case "Delete":
					 WebElement del = driver().findElement(By.cssSelector(fbExistCommentB4r+i+fbDelete));
					 del.click();
					 confirmationMessage("Comment Deleted");					 
					 break;	
				 case "Edit":					
					 waitOnElement("cssSelector", fbExistCommentB4r+i+fbEdit);
					 WebElement edi = driver().findElement(By.cssSelector(fbExistCommentB4r+i+fbEdit));
					 edi.click();					
					 waitOnElement("cssSelector", fbExistCommentB4r+i+fbExistReCommentB4r);					 
					 inputTextClear("cssSelector", fbExistCommentB4r+i+fbExistReCommentB4r);					 
					 inputText("cssSelector", fbExistCommentB4r+i+fbExistReCommentB4r, recommentText);	
					 inputTextClear("cssSelector", fbExistCommentB4r+i+fbExistReCommentB4r);					 
					 inputText("cssSelector", fbExistCommentB4r+i+fbExistReCommentB4r, recommentText);	
					 clickOnButton("cssSelector", fbExistCommentB4r+i+fbCommReSubmit);
					 confirmationMessage("Comment was successfully updated.");					 
					 break;
				 case "SelfReport":
					 WebElement selfRep = driver().findElement(By.cssSelector(fbExistCommentB4r+i+fbReportLnk));
					 selfRep.click();	
					 inputText("cssSelector", fbReportTextarea, claBodyText);
					 clickOnButton("cssSelector", fbRepbtn);
					 confirmationMessage("Cannot report abuse on your own items");
					 break;						 	 	 
				 case "Report":
					 WebElement rep = driver().findElement(By.cssSelector(fbExistCommentB4r+i+fbReportLnk));
					 rep.click();	
					 inputText("cssSelector", fbReportTextarea, claBodyText);
					 clickOnButton("cssSelector", fbRepbtn);
					 confirmationMessage("Report has been recorded");
					 break;		
					 	} break;
					 }	i++;
					 waitOnSpinner();
		 }if(iscommPresent==false){
			 Assert.assertTrue(iscommPresent,comment+" is not found");
		 }		 
	 }
	 
	 
	//select View tickets and validate the latest created Report
		 public boolean isCommentPresent(String comment) throws Exception{
			logInfo("Entered into isCommentPresent() method");				
			 waitOnElement("cssSelector", fbExistComment);	
			 List<WebElement> com = driver().findElements(By.cssSelector(fbExistComment));			
			 boolean iscommPresent = false;			 
			 for(WebElement comments : com ) {
				 String comList = comments.getText().trim();
				 System.out.println("Present lis is "+comList);
				 if(comList.equalsIgnoreCase(comment)){				 
					 iscommPresent=true;	
					 break;
				 	} 	 
			 	}return iscommPresent;
			 
		 }	 
	 
	 public boolean verifyTicketInAdmin(String ticket) throws Exception{
		 logInfo("Entered verifyTicketInAdmin() method");		 
		 boolean isfeedbackInAdmin = false;		 
		 waitOnElement("cssSelector", fbAdmTickAll);
		 clickOnLink("cssSelector", fbAdmTickAll);	
		 clickOnLink("cssSelector", fbAdmTickAll);	
		 waitOnElement("cssSelector", fdAdminList);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));		
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 if (list.getText().trim().equalsIgnoreCase(ticket)){
				 isfeedbackInAdmin =true;
				 break;
			 }	 
			 
		 }if(isfeedbackInAdmin==false){
			 Assert.assertTrue(isfeedbackInAdmin, ticket +" - ticket is not found.");
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 
	 
        public boolean ticketNotToBePresentInAdmin(String ticket) throws Exception{
        logInfo("Entered ticketNotToBePresentInAdmin() method");		 
			 boolean isfeedbackInAdmin = true;
			 navigate2AdminFeedback();
			 clickOnLink("cssSelector", fbAdmTickAll);
			 Thread.sleep(3000);		
			 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
			 System.out.println("Size of list is "+ lis.size());
			 for (int i=2 ;i<=lis.size(); i++){
				 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));			 
				 if (list.getText().trim().equalsIgnoreCase(ticket)){
					 isfeedbackInAdmin =false;
					 Assert.assertTrue(isfeedbackInAdmin, ticket +" - ticket is still present.");
					 break;
					 }				 
			 }if(isfeedbackInAdmin==true){
				 System.out.println("No feedback is present");
				
			 }
			return isfeedbackInAdmin;	 
		 }
	 
	 
    public boolean deleteTicketInAdmin(String ticket) throws Exception{
    	logInfo("Entered deleteFeedbackInAdmin(String ticket) method");
		 boolean isfeedbackInAdmin = false;		
		 Thread.sleep(3000);
		 clickOnLink("cssSelector", fbAdmTickAll);
		 Thread.sleep(3000);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 System.out.println(list.getText());
			 if (list.getText().trim().equalsIgnoreCase(ticket)){
				 isfeedbackInAdmin =true;
				 WebElement del = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListChkBxAtr));
				 if (!del.isSelected()){
					 del.click();	
					 waitOnElement("cssSelector",fbAdminDelete );	
				 		}				 
				 clickOnElement("cssSelector",fbAdminDelete );				
				 confirmationMessage("Ticket(s) successfully deleted.");				 
				 break;
			 }	 
			 
		 }if(isfeedbackInAdmin==false){
			 Assert.assertTrue(isfeedbackInAdmin, ticket +" - ticket is not found.");
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 
    // create feedback
	 public void createFeedback(String reportTitle) throws Exception{
		 logInfo ("Entered  createFeedback() method");		
		 waitOnElement("cssSelector", fbTitle);
		 waitOnElement("cssSelector", fbTitle);
		 WebElement tit = driver().findElement(By.cssSelector(fbTitle));		
		 Assert.assertEquals(tit.getText(), fbTitleText);
		 waitOnElement("xpath", fbFeedback);
		 clickOnElement("xpath", fbFeedback);
		 waitOnElement("cssSelector", fbMsgTitle);
		 inputTextClear("cssSelector", fbMsgTitle);
		 inputText("cssSelector", fbMsgTitle, reportTitle);
		 waitOnElement("cssSelector", fbChooseFile);
		 uploadFile("Image", fbChooseFile);		
		 inputText("xpath", fbDescp, fbFeedbackcomment);
		 inputTextClear("cssSelector", fbMsgTitle);
		 inputText("cssSelector", fbMsgTitle, reportTitle);
		 clickOnElement("cssSelector", fbFeedbackSubmit);		
		 confirmationMessage("Feedback successfully created");
	 }
	 
	 
	 public void nav2ViewFeedbacks() throws Exception{
		 logInfo("Inside nav2ViewFeedbacks() method");			
		 waitOnElement("xpath", fbFeedback);
		 clickOnElement("xpath", fbFeedback);
		 waitOnElement("cssSelector", fbViewFeedback);		
		 clickOnButton("cssSelector", fbViewFeedback);	
		 
		 
	 }
	 
	 
	//select View feedback and validate the latest created feedbacks
		 public boolean validateFeedbacks(String feedback) throws Exception{
			 logInfo("Entered into validateFeedbacks() method");			
			 waitOnSpinner();
			 waitOnElement("cssSelector", fbFeedList);
			 List<WebElement> tic = driver().findElements(By.cssSelector(fbFeedList));
			 System.out.println("list size is "+ tic.size());
			 boolean isTicketPresent = false;
			 for (int i=2; i<=tic.size(); i++){
				 String tList = driver().findElement(By.cssSelector(fbFeedList1+i+fbFeedList2)).getText().trim();				
				 System.out.println(i+ " list is "+ tList);
				 if(tList.equalsIgnoreCase(feedback)){
					 isTicketPresent=true;					 			 
					 break;
				 }i++;
				 
			 }if (isTicketPresent==false){
				 Assert.assertTrue(isTicketPresent, feedback +" is not found");
			 }
			return isTicketPresent;
			 
		 }		 
		 
		 //select View feedback and validate the latest created feedbacks
		 public void commentFeedback(String feedback, String comment) throws Exception{
			 logInfo("Inside commentFeedback() method");
			 Thread.sleep(5000);				 
			 List<WebElement> tic = driver().findElements(By.cssSelector(fbFeedList));
			 System.out.println(tic.size());
			 boolean isTicketPresent = false;
			 for (int i=2; i<=tic.size()*2; i++){
				 WebElement tList = driver().findElement(By.cssSelector(fbFeedList1+i+fbFeedList2));		 
				 System.out.println("Retrieved as "+tList.getText());
				 if(tList.getText().equalsIgnoreCase(feedback)){					
					 isTicketPresent=true;					 
					 int j= i+1;
					 waitOnElement("cssSelector", fbFeedList1+j+fbFeedCommentCount);
					 WebElement countCom = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedCommentCount));							 
					 String count= countCom.getText();
					 String[] parts = StringUtils.split(count," ");
					 String countsCom = parts[0];
					 int con =Integer.parseInt(countsCom);
					 int aftercomneted = con+1;
					 String afterExpCount = Integer.toString(aftercomneted);   					 
					 System.out.println("Before commented the count is "+countsCom + " And also expected count woud be "+ afterExpCount);							 
					 
					 WebElement com = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedComment));
					 com.click();					 
					 waitOnElement("cssSelector", fbFeedList1+j+fbFeedCommentCount);
					 inputText("cssSelector",fbCommnet,comment);
					 Thread.sleep(4000);
					 getText("cssSelector",fbCommPost, "Post text is ");
					 clickOnButton("cssSelector",fbCommPost);						
					 Thread.sleep(5000);					 
					 WebElement aftCount = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedCommentCount));
					 System.out.println("After "+aftCount.getText());
					 String Aftercount= aftCount.getText();
					 String[] afterParts = StringUtils.split(Aftercount," ");
					 String retriveAfterCount = afterParts[0];							 
					 Assert.assertEquals(retriveAfterCount, afterExpCount);							 
					 break;
				 }	i++;					 
			 }if (isTicketPresent==false){
				 Assert.assertTrue(isTicketPresent, feedback +" is not found");
			 }
			 
		 }
				 
	 	//select View feedback and validate the voted feedback
		public void validateVotedFeedback(String feedback ) throws Exception{
			 logInfo("Inside validateVotedFeedback() method");			
			 Thread.sleep(2000);
			 clickOnElement("xpath", fbFeedback);
			 Thread.sleep(3000);
			 clickOnButton("cssSelector", fbViewFeedback);			 
			 List<WebElement> tic = driver().findElements(By.cssSelector(fbFeedList));
			 System.out.println(tic.size());
			 boolean isTicketPresent = false;
			 for (int i=2; i<=tic.size(); i++){
				 WebElement tList = driver().findElement(By.cssSelector(fbFeedList1+i+fbFeedList2));		 
				 System.out.println("tList.getText()"+ tList.getText());
				 if(tList.getText().equalsIgnoreCase(feedback)){
					 isTicketPresent=true;
					 int j= i+1;
					 WebElement countCom = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedVoteCount));							 
					 String count= countCom.getText();
					 String[] parts = StringUtils.split(count," ");
					 String countsCom = parts[0];
					 int con =Integer.parseInt(countsCom);
					 int aftercomneted = con+1;
					 String afterExpCount = Integer.toString(aftercomneted);   
					 
					 System.out.println("Before vote, the count is "+countsCom + " And also expected count woud be "+ afterExpCount);							 
					 
					 WebElement com = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedVote));
					 com.click();
					 Thread.sleep(6000);							 
					 
					 WebElement aftCount = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedVoteCount));
					 System.out.println("After "+aftCount.getText());
					 String Aftercount= aftCount.getText();
					 String[] afterParts = StringUtils.split(Aftercount," ");
					 String retriveAfterCount = afterParts[0];
					 
					 Assert.assertEquals(retriveAfterCount, afterExpCount);							 
					 break;
				 }
				 i++;
			 }if (isTicketPresent==false){
				 Assert.assertTrue(isTicketPresent, feedback +" is not found");
			 }				 
			}	 
		 
		 public boolean addCommentFeedbackInAdmin(String ticket, String comments) throws Exception{
			 logInfo("Entered addCommentFeedbackInAdmin() method");
			 boolean isfeedbackInAdmin = false;
			 waitOnSpinner();
			 waitOnElement("cssSelector", fdAdminList);
			 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
			 System.out.println("Size of list is "+ lis.size());
			 for (int i=2 ;i<=lis.size()*2; i++){
				 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
				 System.out.println(list.getText());
				 if (list.getText().trim().equalsIgnoreCase(ticket)){
					 isfeedbackInAdmin =true;					
					 list.click();					
					 String com = driver().findElement(By.cssSelector(fbAdminComment)).getText().trim();
					 System.out.println("com " + com);
					 Assert.assertEquals(com, "Add Comment");
					 Thread.sleep(5000);
					 waitOnElement("cssSelector",fbAdminComment);
					 WebElement coment = driver().findElement(By.cssSelector(fbAdminComment));
					 coment.click();					
					 Thread.sleep(7000);
					 /*waitOnElement("cssSelector", fbCommSubmit);
					 String post = driver().findElement(By.cssSelector(fbCommSubmit)).getText().trim();
					 System.out.println("post name " + post);
					 Assert.assertEquals(post, "Post");*/
					 waitOnElement("cssSelector",fbCommentTextField);
					 inputTextClear("cssSelector", fbCommentTextField);	
					 inputText("cssSelector", fbCommentTextField, comments);							
					 clickOnButton("cssSelector", fbCommSubmit);					 
					 break;
				 }	 
				 
			 }if(isfeedbackInAdmin==false){
				 Assert.assertTrue(isfeedbackInAdmin, ticket +" - ticket is not found.");
			 }
			return isfeedbackInAdmin;	 
		 }
		 
		 
	    public void searchTickets(String ticket) throws Exception{
	    	logInfo("Entered searchNdVerifyTickets() method");
	    	 waitOnElement("cssSelector", fbViewTickets);	
			 clickOnButton("cssSelector", fbViewTickets);	
			 inputTextClear("cssSelector", fbTickSearh);
			 inputText("cssSelector", fbTickSearh,ticket);
			 submitElement("cssSelector", fbTickSearh);	    	
	    }		    
				    
				    
	    public void searchFeedbacks(String feedback) throws Exception{
	     logInfo("Entered searchFeedbacks() method");	  
		
		 waitOnElement("cssSelector", fbFeedbackSearh);		
		 inputTextClear("cssSelector", fbFeedbackSearh);
		 inputText("cssSelector", fbFeedbackSearh,feedback);
		 submitElement("cssSelector", fbFeedbackSearh);		    	
	    }
	    
	 public void invalidFeedBackSearchs() throws Exception{	   
		 logInfo("Entered invalidFeedBackSearchs() method");
		 waitOnSpinner();
		 waitOnElement("cssSelector", fbFeedbackInvalidSearh);    	
		 WebElement tick = driver().findElement(By.cssSelector(fbFeedbackInvalidSearh));
		 System.out.println(tick.getText());
		 Assert.assertEquals(tick.getText(), invalidFeedbackText);					
	   }
	 
	 public void invalidTicketSearchs() throws Exception{	   
		 logInfo("Entered invalidTicketSearchs() method");
		 waitOnSpinner();
		 waitOnElement("cssSelector", fbTicketsInvalidSearh);    	
		 WebElement tick = driver().findElement(By.cssSelector(fbTicketsInvalidSearh));
		 System.out.println(tick.getText());
		 Assert.assertEquals(tick.getText(), invalidTicketText);					
	   }
	 
	 
	 public void searchFilters(String filterOption, String ticket) throws Exception{	
		 logInfo(" Entered into 'searchFilters' method");
		 waitOnElement("cssSelector", fbFilter);
		 clickOnElement ("cssSelector", fbFilter);		 
		 boolean isOptionPresent = false;
		 List <WebElement> lis = driver().findElements(By.cssSelector(filterOpt));
		 System.out.println("Size is "+ lis.size());
		 for (WebElement opt : lis){
			 System.out.println("options are "+opt.getText());
			 if(opt.getText().equalsIgnoreCase(filterOption)){
				 isOptionPresent=true;
				 opt.click();
				 validateTickets(ticket);				 
				 break;
			 }			 
		 }if(isOptionPresent==false){
			 Assert.assertTrue(isOptionPresent, filterOption +" option is not found");
		 }		 
	 }
	 
	 
	 public void getTicketLength(String ticket) throws Exception{
		 logInfo("Inside getTicketLenths() method");		 
		 waitOnElement("cssSelector", fbViewTickets);	
		 clickOnButton("cssSelector", fbViewTickets);		 
		 List<WebElement> tic = driver().findElements(By.cssSelector(fbTickList));
		 boolean isTicketPresent = false;
		 for (int i=1; i<=tic.size()*2; i++){
			 WebElement tList = driver().findElement(By.cssSelector(fbTickList1+i+fbTickList2));	
			 String actualTicket = tList.getText();
			 System.out.println(actualTicket);
			 if(actualTicket.contains(ticket)){
				isTicketPresent=true;
				int lenOfTick = actualTicket.length();
				 String lengthOfTickets = Integer.toString(lenOfTick);				 				 
				 Assert.assertEquals(lengthOfTickets, "50");
				 break;
			 }
			 i++;
		 }if (isTicketPresent==false){
			 Assert.assertTrue(isTicketPresent, ticket +" is not found");
		 }
		 
	 }
	 
	 public boolean getFeedbackLength(String feedback) throws Exception{
		 logInfo("Inside validateFeedbackLength() method");			
		 waitOnElement("xpath", fbFeedback);
		 clickOnElement("xpath", fbFeedback);
		 waitOnElement("cssSelector", fbViewFeedback);	
		 clickOnButton("cssSelector", fbViewFeedback);			 
		 List<WebElement> tic = driver().findElements(By.cssSelector(fbFeedList));		 
		 boolean isTicketPresent = false;
		 for (int i=1; i<=tic.size(); i++){
			 WebElement tList = driver().findElement(By.cssSelector(fbFeedList1+i+fbFeedList2));			 
			 String feedText = tList.getText();
			 if(feedText.contains(feedback)){
				 isTicketPresent=true;
				 int feedSize = feedText.length();
				 String feedActualSize = Integer.toString(feedSize)	;				 
				 Assert.assertEquals(feedActualSize, "50");;
				 break;
			 }
			 
		 }if (isTicketPresent==false){
			 Assert.assertTrue(isTicketPresent, feedback +" is not found");
		 }
		return isTicketPresent;		 
	 } 
	 
	 public void verifyBoundaries() throws Exception{
		 logInfo("Entered verifyBoundaries() method");		
		 String reportTitle = "validateTitlewithmaxcharaters"+TestBase.generateRandomString()+" 1234567890abcdefghij1234567890abcdefghij1234567850";
		 String[] reptitle = StringUtils.split(reportTitle," ");
		 String splitedTitle = reptitle[0];
		 clickOnElement("cssSelector", fbReport);
		 waitOnElement("cssSelector", fbMsgTitle);
		 inputTextClear("cssSelector", fbMsgTitle);
		 inputText("cssSelector", fbMsgTitle, reportTitle);
		 implicityWaits(5);
		 waitOnElement("xpath", fbDescp);
		 inputText("xpath", fbDescp, reportTitle);	
		 waitOnElement("cssSelector", fbSubmit);
	     WebElement submit = driver().findElement(By.cssSelector(fbSubmit));
		 submit.click();		
	     navigate2Feedback();		
		 getTicketLength(splitedTitle);		 
		 navigate2Feedback();
		 createFeedback(reportTitle);
		 navigate2Feedback();
		 getFeedbackLength(splitedTitle);		 
	 }
	 
	 public boolean verifyReportedInAdmin(String ticket) throws Exception{
		 logInfo("Entered verifyReportedInAdmin() method");
		 boolean isfeedbackInAdmin = false;		 
		 Thread.sleep(3000);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 System.out.println(list.getText());
			 if (list.getText().trim().equalsIgnoreCase(ticket)){
				 isfeedbackInAdmin =true;
				 list.click();				 
				 Thread.sleep(2000);
				 WebElement reported = driver().findElement(By.cssSelector(fbAdminRep));
				 System.out.println(reported.getText());
				 Assert.assertEquals(reported.getText(), "Reported");				 
				 break;
			 }	 
			 
		 }if(isfeedbackInAdmin==false){
			 Assert.assertTrue(isfeedbackInAdmin, ticket +" - ticket is not found.");
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 public boolean reportNoTobePresentInAdmin(String ticket) throws Exception{	
		 logInfo("Entered reportNoTobePresentInAdmin() method");
		 boolean isTicketInAdmin = true;		 
		 Thread.sleep(3000);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 System.out.println(list.getText());
			 if (list.getText().trim().equalsIgnoreCase(ticket)){
				 isTicketInAdmin =false;
				 Assert.assertTrue(isTicketInAdmin, ticket +" is still present.");								 
				 break;
			 }	 
			 
		 }if(isTicketInAdmin==true){
			 Assert.assertNotSame(isTicketInAdmin, ticket);
		 }
		return isTicketInAdmin;	 
	 }
	 
	 
	 
	 
	 
	 public void verifySectionsInAdmin(String ticket) throws Exception{
		 logInfo("Entered verifySectionsInAdmin() method");
		 
		 clickOnLink("cssSelector", fbAdmTickAll);
		 Thread.sleep(3000);
		 verifyTicketInAdmin(ticket);
		 clickOnLink("cssSelector", fbAdmTickOpen);
		 Thread.sleep(3000);
		 verifyTicketInAdmin(ticket);
		 clickOnLink("cssSelector", fbAdmTickClosed);
		 Thread.sleep(3000);
		 reportNoTobePresentInAdmin(ticket);
		 
	 }
	 
public boolean verifyFeedbackInAdmin(String feedback) throws Exception{
	logInfo("Entered verifyFeedbackInAdmin() method");		 
		 boolean isfeedbackInAdmin = false;	
		 waitOnElement("cssSelector", fbAdmFeedAll);
		 clickOnLink("cssSelector", fbAdmFeedAll);	
		 clickOnLink("cssSelector", fbAdmFeedAll);			 
		 waitOnElement("cssSelector", fdAdminList);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));		
		 for (int i=2 ;i<=lis.size()+1; i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 System.out.println("names"+ i  + list.getText());
			 if (list.getText().trim().equalsIgnoreCase(feedback)){
				 isfeedbackInAdmin =true;
				 break;
			 }	 
			 
		 }if(isfeedbackInAdmin==false){
			 Assert.assertTrue(isfeedbackInAdmin, feedback +" - feedback is not found.");
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 
	 
        public boolean feedbackNotToBePresentInAdmin(String feedback) throws Exception{
        	logInfo("Entered feedbackNotToBePresentInAdmin() method");
		 
		 boolean isfeedbackInAdmin = true;
		/* navigate2AdminFeedback();
		 clickOnLink("cssSelector", fbAdmFeedAll);
		 Thread.sleep(3000);*/		
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size()+1; i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));			 
			 System.out.println("list.getText().trim() "+list.getText().trim());
			 if (list.getText().trim().equalsIgnoreCase(feedback)){
				 isfeedbackInAdmin =false;
				 Assert.assertTrue(isfeedbackInAdmin, feedback +" - feedback is still present.");
				 break;
			 }	 
			 
		 }if(isfeedbackInAdmin==true){
			 System.out.println("No feedback is present");
			
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 
    public boolean deleteFeedbackInAdmin(String feedback) throws Exception{
    	logInfo("Entered deleteFeedbackInAdmin(String feedback) method");
		 boolean isfeedbackInAdmin = false;			 
		 waitOnElement("cssSelector", fdAdminList);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());				
		 for (int i=2 ;i<=lis.size()+1; i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 if (list.getText().trim().equalsIgnoreCase(feedback)){
				 isfeedbackInAdmin =true;
				 WebElement del = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListChkBxAtr));
				 if (!del.isSelected()){
					 del.click();						 		 
				 		} 				 
				 clickOnElement("cssSelector",feedDeleteInAdmin);				
				 confirmationMessage("Feedback(s) successfully deleted.");	
				 break;
			 }		
		 }if(isfeedbackInAdmin==false){
			 Assert.assertTrue(isfeedbackInAdmin, feedback +" - feedback is not found.");
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 
	 
	 
	 
	 
}
