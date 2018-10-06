package vibe.reports.tests;

import java.util.List;

import org.testng.Assert;
import common.TestBase;
import common.EnvProperties;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ReportsMethods extends TestBase {
	
	EnvProperties prop = new EnvProperties();		
	
	
	public void navigate2Report(){
		logInfo("Entered into navigate2Report() method..");
		clickOnElement("cssSelector", REPORTS_tab);
		driver().navigate().to(appUrl + "reports/run_report");
	}
	
	public void nav2TreeViewReports() throws Exception{
		logInfo("Entered into nav2TreeViewReports() method");
		    waitOnElement("cssSelector", REPORTS_tab);	
			clickOnElement("cssSelector", REPORTS_tab);	
			clickOnLink("linkText", "Tree View");				
	}
	
	//Assertions all types of reports with their Table title.
	public void typesofReports() throws Exception{		 
		logInfo("Entered into typesofReports() method");
			List <WebElement> reportstable = driver().findElements(By.cssSelector(rpTab));
			if(reportstable.size()==0){
				Assert.assertNotEquals(reportstable.size(), 0);
			}else{
			
			for (int i=2; i<=reportstable.size()+1; i++){				
			WebElement title = driver().findElement(By.cssSelector(rptitle1+i+rptitle2));
			String ReportTitle = title.getText().toLowerCase();				
			waitOnElement("cssSelector", rptitle1+i+rptitle2);
			title.click();
			
			if(driver().findElement(By.cssSelector(error404)).getText().equals(notImplimentedText)){
				WebElement errMsg = driver().findElement(By.cssSelector(notImplimented));
				Assert.assertEquals(errMsg.getText(), ReportTitle);	
				
			}else{
			waitOnSpinner();
			waitOnElement("cssSelector", repTitle);	
			WebElement pageTit = driver().findElement(By.cssSelector(repTitle));
			String pageTitle = pageTit.getText().toLowerCase();			
			Assert.assertEquals(ReportTitle,pageTitle);	
			waitOnElement("cssSelector", repTitle);
			navigate2Report();
						}
					}
			}
			
	}
	
	//"Retreive Report Titles from Table and select link of table 
		public void reportstable(String report) throws Exception{	 
			logInfo("Entered into reportstable(String report) method");				
				boolean isTablePresent = false;
				waitOnElement("xpath", reportTableFrame);
				List <WebElement> reportstable = driver().findElements(By.xpath(reportTableFrame));
				for (int i =2; i<=reportstable.size(); i++)			
				{						
					WebElement rp = driver().findElement(By.xpath(rp1+i+rp2));					
					if (rp.getText().equalsIgnoreCase(report)){		
						isTablePresent = true;
						rp.click();					
						break;
					}
				}if (isTablePresent==false){
					Assert.assertTrue(isTablePresent,  report + " - report is not present.");
				}	
				
			}	
		
		
		 public void exportList(String eportList) throws Exception{
			 logInfo("Entered into exportList(String eportList) method");	
			 boolean isInExport = false;
			 waitOnElement("cssSelector", exportBtn);
			 clickOnElement("cssSelector", exportBtn);
			 Thread.sleep(2000);		
			 List <WebElement> exList = driver().findElements(By.cssSelector(exportdp));			
			 for (WebElement el : exList){			    
				if (el.getText().equalsIgnoreCase(eportList)){
					isInExport = true;
					el.click();
					break;
				}
			 }if (isInExport==false){
				 Assert.assertTrue(isInExport, eportList + " is not present in Export options");
			 }
		 }
		 
		 public void sendBulkMessage(String message) throws Exception{		  
				logInfo("Entered into sendBulkMessage(String message) method");
				waitOnElement("cssSelector", bulkSubject);
				inputTextClear("cssSelector", bulkSubject);
				inputText("cssSelector", bulkSubject, message); 
				composeText("xpath",composeBodyInReport,composeBodyText);
				if(!(driver().getCurrentUrl().contains("worldventures"))) {
				WebElement selAl =  driver().findElement(By.cssSelector(bulkSelectAll));
				Thread.sleep(5000);
				if (!selAl.isSelected()){
					selAl.click();			
				}}
				waitOnElement("cssSelector", bulkSendEMail);	
				clickOnButton("cssSelector", bulkSendEMail);		 

			 }
		 
		//Create Event from Reports
		 public void createEvent(String event, int noOfDaysToCurrent) throws Exception{
			logInfo("Entered into createEvent() method");		
			String eventDate = getDate(noOfDaysToCurrent, "MM/dd/yyyy")	;	
			String endDate = getDate(noOfDaysToCurrent+2, "MM/dd/yyyy")	;
			waitOnElement("linkText", "Add Event");
			clickOnLink("linkText", "Add Event");				
			verifyElementPresent("xpath", inputEventName);
			inputText("xpath", inputEventName, event);
			selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);
			selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", calendarType_text);
			inputTextClear("xpath", inputEventStartDate);
			inputText("xpath", inputEventStartDate, eventDate);
			inputTextClear("xpath", inputEventEndDate);
			inputText("xpath", inputEventEndDate, endDate);
			waitOnElement("xpath", btnEventSave);
			clickOnButton("xpath", btnEventSave);			
			confirmationMessage("Your event has been created.");
			
			 }
		 
		 
		 
		 public void selectEventAndInvitation (String eventText ) throws Exception{	
			 logInfo("Entered into selectEventAndInvitation (String eventText) method");		
				waitOnElement("cssSelector", eventID);
				clickOnElement("cssSelector", eventID);
				List <WebElement> opt = driver().findElements(By.cssSelector(eventIDOption));
				boolean isPresent =false;
				for(WebElement opts:opt) {
					String options = opts.getText().trim();					
					if(options.equalsIgnoreCase(eventText)) {
						isPresent =true;
						opts.click();
						WebElement eve = driver().findElement(By.cssSelector(eventID));		
						Select sel = new Select (eve);
						sel.selectByVisibleText(eventText);	
						break;
					}
					
				}if(isPresent==false) {
					Assert.assertTrue(isPresent, eventText);
					
				}
				waitOnElement("cssSelector",sub);
				inputText("cssSelector",sub, eventText); 
				inputText("cssSelector",eventbody, subjectBulk);
				waitOnElement("cssSelector", sendInv);
				clickOnButton("cssSelector", sendInv);
				confirmationMessage("Invitations Sent");
				
				
			}
		 
		 
	public void directMessage() throws Exception{
		logInfo("Entered into directMessage() method");
		waitOnElement("cssSelector",dirMsgBody);
		inputText("cssSelector",dirMsgBody, eventText);
		WebElement chk = driver().findElement(By.cssSelector(dirMsgChkAll));
		if(chk.isSelected()) {
			chk.click();			
				}
		waitOnElement("cssSelector",dirMsgChks);
		List<WebElement> chks = driver().findElements(By.cssSelector(dirMsgChks));
		for(WebElement chkBx : chks) {
			if(!chkBx.isSelected()) {
				chkBx.click();				
				break;
			}			
		}
		clickOnButton("cssSelector", crtDirtMsg);
		
	}
	
	
	public void downLoadSelection(String downloadList) throws Exception{
		 logInfo("Entered into downLoadSelection(String downloadList) method");	
		 boolean isdownLoadPresent = false;
		 waitOnElement("cssSelector", rptDwnld);
		 clickOnElement("cssSelector", rptDwnld);
		 Thread.sleep(2000);		
		 List <WebElement> exList = driver().findElements(By.cssSelector(exportdp));		
		 for (WebElement el : exList){			    
			if (el.getText().equalsIgnoreCase(downloadList)){
				isdownLoadPresent = true;
				el.click();
				break;
			}
		 }if (isdownLoadPresent==false){
			 Assert.assertTrue(isdownLoadPresent, downloadList + " is not present in Export options");
		 }
	 }
	
	
	public void downloadConfirmation() throws Exception {
		logInfo("Entered into downloadConfirmation() method");	
		waitOnElement("cssSelector", downloadAlert);
		WebElement msg = driver().findElement(By.cssSelector(downloadAlert));
		String actMsg = msg.getText().trim();		
		if((actMsg==downloadAlertMsg1)||(actMsg==downloadAlertMsg2)){
			Assert.assertEquals(actMsg, downloadAlertMsg1);
			WebElement msg2 = driver().findElement(By.cssSelector(downloadAlert));
			String actMsg2 = msg2.getText().trim();			
			Assert.assertEquals(actMsg2, downloadAlertMsg2);
			
		}else if(actMsg==downloadAlertMsg2){			
			Assert.assertEquals(actMsg, downloadAlertMsg2);
		}else {
			Assert.assertNotSame(actMsg, downloadAlertMsg2);
		}
		
		
	}
	
	
	
	
	
	
}
	
