package vibe.reports.tests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.apache.commons.lang3.StringUtils;

import common.TestBase;
import common.EnvProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class AVNReportsMethods extends TestBase {

	EnvProperties prop  = new EnvProperties();	
	
	 String prodUrl = "https://avonvibe.com/";
	 String adminUser = "prodadmin";
	 String pwd = "password1";
	
	
	public void navigateReports() throws Exception{	
		logInfo("Entered into navigateReports() method");	
			driver().navigate().to(appUrl+"reports/run_report");	
		}
	
	
	//"Retreive Report Titles from Table and select link of table 
		public void reportstable(String report) throws Exception{	 
			logInfo("Entered into reportstable(String report) method");				
				boolean isTablePresent = false;
				waitOnElement("xpath", reportTableFrame);
				List <WebElement> reportstable = driver().findElements(By.xpath(reportTableFrame));
				for (int i =2; i<=reportstable.size(); i++)			
				{			
					WebElement rp = driver().findElement(By.xpath(rp1+i+rp2))	;
					System.out.println(rp.getText());
					if (rp.getText().equalsIgnoreCase(report)){		
						isTablePresent = true;
						rp.click();					
						break;
					}
				}if (isTablePresent==false){
					Assert.assertTrue(isTablePresent,  report + " - report is not present.");
				}
			
				
			}
    
   
    
      public void selectFilterOption(String filterOption1) throws Exception{
    	 Thread.sleep(2000);
 	    boolean isOptionPresent = false; 
 		List <WebElement> op2 = driver().findElements(By.cssSelector(outputFilterOptions));   
 		for (int i =3; i<=op2.size(); i++){
     		WebElement opName2 = driver().findElement(By.cssSelector(outputFilterOpBfr+i+outputFilterOpAfr));     		
     		WebElement opChkBox2 = driver().findElement(By.cssSelector(outputFilterOpBfr+i+outputFilterOpAfr+filterChkBox));
     		if (!(opName2.getText().equalsIgnoreCase(filterOption1))){
     			isOptionPresent = true;     			
 			    if(opChkBox2.isSelected()){
 				   opChkBox2.click();   				
 						}
 			       	}
 		     }if (isOptionPresent==false){
 		    	 Assert.assertTrue(isOptionPresent, filterOption1 + " filter option is not present in OutputFilters" );
 		     }
     }
    	
    
      
      
      //Send Bulk Email to all with Subject & Message. 
      public void sendBulkMail(String message) throws Exception{		  
  		logInfo("Entered into sendBulkMail(String message) method");  	
  		waitOnElement("cssSelector", bulkSubject);
  		inputText("cssSelector", bulkSubject, message); 
  		composeText("xpath",composeBody,composeBodyText);
  		inputText("cssSelector", bulkMsg, msgText);		
  			
  		/*clickOnButton("cssSelector", bulkSendMail);		 */

  	 }
  		
  		 //  Search Contact from the Widget. 		
  	  	 public boolean searchContactFromWidget(String contactName) throws Exception{
  			 logInfo("Inside searchContactFromWidget() method.");  			 
  			 waitOnElement("xpath","//div[@id='search_contacts']");
  			 inputTextClear("xpath", "//div[@id='search_contacts']/div[2]/div/div[1]/input[@id='contact_query']");
  			 inputText("xpath", "//div[@id='search_contacts']/div[2]/div/div[1]/input[@id='contact_query']",contactName );
  			 clickOnElement("xpath","//div[@id='search_contacts']/div[2]/div/div[2]/button[@id='search-contacts-widget-button']/i");
  			 boolean isContactMatchFound = false;  			 
  			 WebElement eautoSearch = driver().findElement(By.cssSelector(".ui-autocomplete.ui-front.ui-menu.ui-widget.ui-widget-content"));
  			 List<WebElement> allItems = eautoSearch.findElements(By.cssSelector(".ui-menu-item"));
  			 if(allItems.size() >0){
  				for(WebElement e : allItems){
  					String x = e.getText().trim();
  					System.out.println("x =" +x);
  					if(x.equalsIgnoreCase(contactName)){
  						isContactMatchFound=true;
  						logInfo(contactName + " contact found in Search Contact widget.");
  						e.click();
  						clickOnElement("cssSelector","#search-contacts-widget-button");
  						waitOnElement("cssSelector", contName);
  						WebElement actNa = driver().findElement(By.cssSelector(contName));
  						String actualName = actNa.getText().trim();
  						System.out.println("actualName"+ actualName);
  						clickOnElement("linkText","Delete");
  						confirmOK();
  						Assert.assertEquals(actualName, contactName);
  						break;
  					}
  				}
  			 }
  			 
  			 if(isContactMatchFound==false){
  				 logInfo(contactName + " contact could not be found in Search Contact widget.");
  				 Assert.assertTrue(isContactMatchFound, contactName + " contact could not be found in Search Contact widget.");
  			 }
  			return isContactMatchFound;
  		 }
  	  	 
  	  	 
  	 	public void verifyBusinessDashBoard() throws Exception{
  	  		logInfo("Entered into verifyBusinessDashBoard() method ");  		
  	  		waitOnElement("linkText", "Show More");
  	  		clickOnLink("linkText", "Show More");
  	  		verifyCampaign("Current Campaign");
  	  		verifyCampaign("Next Campaign");
  	  		verifyCampaign("Previous Campaign");
  	  	
  	  	}
  		
  	 	
  	 	public void verifyCampaign(String campaignType) throws Exception{
  	  		logInfo("Entered into verifyCampaign() method ");
  	  		boolean isfound = false;
  	  		clickOnElement("cssSelector", camp);
  	  		waitOnElement("cssSelector", "input#widgets_search_text");
  	  		List <WebElement> camps = driver().findElements(By.cssSelector(campOp));
  	  		System.out.println("size us "+ camps.size());
  	  		for (WebElement campnign :camps){
  	  			String campaign = campnign.getText().trim();
  	  			
  	  			if (campaign.contains(campaignType)){
  	  				isfound =true;
  	  				System.out.println(campaign);
  	  				break; 				
  	  			}
  	  			
  	  		}if (isfound==false){
  	  			Assert.assertTrue(isfound, campaignType+ "is not found " );
  	  		}
  	  		 		
  	  	}
  	 	
  	 	
  	 	public void verifylinksOfActionAlerts() throws Exception{
  	  		logInfo("Entered into verifylinksOfActionAlerts method.");
  	  		
  	  		List <WebElement> links = driver().findElements(By.cssSelector(acLink));
  	  		System.out.println("size links "+ links.size());
  	  		for (int i=1; i<=links.size(); i++){
  	  			WebElement link = driver().findElement(By.cssSelector(acLinkBfr+i+acLinkAft));
  	  			String LinkText =link.getText().trim();  			
  	  			String[] spitLink = StringUtils.split(LinkText," ");
  	  			String count = spitLink[0];
  	  			int expectedCount = Integer.parseInt(count);
  	  			System.out.println(i+ " count is "+ expectedCount);
  	  			link.click();
  	  			waitOnElement("xpath", reportTableFrame);
  	  			int actualcount  = resultFound();
  	  			System.out.println("actula count "+ actualcount);
  	  			Assert.assertEquals(actualcount, expectedCount);
  	  			back2Office();
  	  		}
  	  		
  	  	}
  	 	
  	 	public int resultFound() throws Exception{
  	 	logInfo("Entered into resultFound() method");
  		   WebElement count = driver().findElement(By.cssSelector(result));
  		   String resCount = count.getText().trim();
  		    int resultCount = Integer.parseInt(resCount);	   
  		   System.out.println("Result count is "+resultCount );
  		   return resultCount;
  		   
  	   } 	
	 
  	 //Assertions all types of reports with their Table title.	
	 public void validateTableTitles() throws Exception{			
			logInfo("Entered into navToProdTable(String avonReportTable) method");
				List <WebElement> reportstable = driver().findElements(By.cssSelector(rpTab));
				System.out.println("No of Reports in the table is "+reportstable.size());
				if(reportstable.size()==0){
					Assert.assertNotEquals(reportstable.size(), 0);
				}else{
				
				for (int i=2; i<=reportstable.size()+1; i++){				
				WebElement title = driver().findElement(By.cssSelector(rptitle1+i+rptitle2));
				String ReportTitle = title.getText().toLowerCase();		
				System.out.println("ReportTitle is "+ReportTitle);				
				waitOnElement("cssSelector", rptitle1+i+rptitle2);
				title.click();
				if(driver().findElement(By.cssSelector(error404)).getText().equals(notImplimentedText)){
					WebElement errMsg = driver().findElement(By.cssSelector(notImplimented));
					System.out.println(errMsg.getText());
					Assert.assertEquals(errMsg.getText(), ReportTitle);	
					
				}else{	
				waitOnElement("cssSelector", repTitle);	
				WebElement pageTit = driver().findElement(By.cssSelector(repTitle));
				String pageTitle = pageTit.getText().toLowerCase();
				System.out.println("page  Title is "+ pageTitle);
				Assert.assertEquals(ReportTitle,pageTitle);	
				waitOnElement("cssSelector", repTitle);
				navigateReports();
							}
						}
				}
				
		}
	 
	 public void contactList(String typeOfContact) throws Exception{
	 		waitOnElement("cssSelector", conatctButton);
	 		 boolean isInContact = false;
	 		 clickOnElement("cssSelector", conatctButton); 		 	
	 		 List <WebElement> exList = driver().findElements(By.cssSelector(conatctDP));
	 		 System.out.println(exList.size());
	 		 for (WebElement el : exList){		
	 		    System.out.println(el.getText());
	 			if (el.getText().equalsIgnoreCase(typeOfContact)){
	 				isInContact = true;
	 				el.click();
	 				break;
	 			}
	 		 }if (isInContact==false){
	 			 Assert.assertTrue(isInContact, typeOfContact + " is not present in Contatct options");
	 		 }

	 	 }
  	
	 public void createEvent(String event, int noOfDaysToCurrent) throws Exception{		  
			logInfo("Entered into createEvent(String event, int noOfDaysToCurrent) method");
			String eventDate = getDate(noOfDaysToCurrent, "MM/dd/yyyy")	;	
			String endDate = getDate(noOfDaysToCurrent+2, "MM/dd/yyyy")	;
			//clickOnLink("linkText", "Add Event");
			waitOnElement("cssSelector", repAddevent);
			clickOnElement("cssSelector", repAddevent);
			waitOnElement("cssSelector", evName);
			inputText("cssSelector", evName, event); 
			inputText("cssSelector",sDate, eventDate ); 
			inputText("cssSelector",eDate, endDate );
			clickOnButton("cssSelector", eventSave);
			confirmationMessage("Your event has been created.");			
			selectEvent(event);
			inputText("cssSelector",sub, event ); 
			inputText("cssSelector",eventbody, subjectBulk);
			clickOnButton("cssSelector", sendInv);
			confirmationMessage("Invitations Sent");
		 }
	 
	 
	 
	 public void selectEvent (String EventText) throws Exception{
		 logInfo("Entered into selectEvent (String EventText) method");			
			WebElement eve = driver().findElement(By.cssSelector(eventID));		
			Select sel = new Select (eve);
			sel.selectByVisibleText(EventText);		
		}
	 
	 public void navToProdTable() throws Exception{
		 logInfo("Entered into navToProdTable(String avonReportTable) method");		
		 userLogout();
		 logOut();
		 driver().navigate().to(prodUrl + "/login");		         
         waitOnElement("xpath", inputName);
         inputText("xpath", inputName, adminUser);
         inputText("xpath", inputPwd, pwd);        
         clickOnButton("xpath", btnLogin);	
		 driver().navigate().to(prodUrl + "/pyr_core/users");
		 advancedSearchToSelectUser(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
					prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));		
		 driver().navigate().to(prodUrl+"/reports/run_report");	
		 
		 
	 }
	 
	 
	 public void validateReports(String avonReportTable,String constriantTitle,String colName) throws Exception{
		 logInfo("Entered into validateReports() method");		 
		 reportstable(avonReportTable);		 
		 validateRepresentativeDetails(avonReportTable, constriantTitle,colName);	
		 
		 
		 
		 
		 
	 }
	 
	 
	 public void updateGenerate(int classType) throws Exception{
    	 logInfo("Entered into updateGenerate(int classType) method.");
    	 String value = Integer.toString(classType);
    	
    	 inputTextClear("cssSelector", generationFrom);
    	 inputTextClear("cssSelector", generationTo);
    	 inputText("cssSelector", generationFrom, "1");
    	 inputText("cssSelector", generationTo, value);
    	 waitOnElement("cssSelector", advancedFilterOpt);
    	 clickOnElement("cssSelector", advancedFilterOpt);
     }
	 
	 
	 // retrieves total count and representative details from prod and then validates with Stage or RC
	 public void validateRepresentativeDetails(String reportTable, String constraintTitle, String colName) throws Exception{
		 logInfo("Entered into validateRepresentativeDetails() method");
		 String expFromConstnt = null, expToConstnt =null;
		 String accNumber ="Account Number"; 
		 String email ="Email";
		 String phone = "Phone";		 
	       	clickOnElement ("cssSelector",filterOp );
	    	waitOnElement("cssSelector", filterAchievementTitleBetween);  	    	
	    	List<WebElement> consHead = driver().findElements(By.cssSelector(constrnt));	    	
	    	boolean isTitleFound = false;
	    	for (int i=1; i<=consHead.size(); i++){
	    		WebElement consHeader = driver().findElement(By.cssSelector(constrntBfr+i+constrntAft));    		
	    		String actHeader = consHeader.getText().trim();	    		
	    		int j=i-1;     		
	    		if (actHeader.equalsIgnoreCase(constraintTitle)){	    			
	    			isTitleFound=true;    			
	    			Select fr = new Select(driver().findElement(By.cssSelector(constrntLeadBfr+j+constrntLeadAft)));
	    			expFromConstnt = fr.getFirstSelectedOption().getText();		    			
	    			Select to = new Select(driver().findElement(By.cssSelector(constrntLeadBfr+j+constrntLeaderToAft)));
	    			expToConstnt = to.getFirstSelectedOption().getText();	    		    			
	    			updateGenerate(3);
	    			setColumnsVisibilities(AVoutputColmns);	
	    			int expResultsCount = resultFound();	    			
	    			String expAccount = retrieveRepresentativeDetails(colName);
	    			System.out.println("Finall expAccount "+expAccount);
	    			String expProfileAcc= retrievePersonalProfile(accNumber);
	    			String expProfileEmail= retrievePersonalProfile(email);
	    			String expProfilePhone= retrievePersonalProfile(phone);
	    			System.out.println("expProfileAcc " + expProfileAcc);
	    			System.out.println("expProfileEmail " + expProfileEmail);
	    			System.out.println("expProfilePhone " + expProfilePhone);
	    			
	    		  profileSections("Sales Performance");
	    		  waitOnElement("cssSelector", totalCMP);
	  	    	  WebElement tCMP = driver().findElement(By.cssSelector(totalCMP));	  	    	  
	  	    	  WebElement AvgeCMP = driver().findElement(By.cssSelector(AvgCMP));
	  	    	  WebElement TPYAwards = driver().findElement(By.cssSelector(TotalPYAwards));
		  	   	  WebElement AvgePYAwards = driver().findElement(By.cssSelector(AvgPYAwards));
		  	  	  WebElement TCYAwards = driver().findElement(By.cssSelector(TotalCYAwards));
		  	      WebElement AvgeCYAwards = driver().findElement(By.cssSelector(AvgCYAwards));
	  	    	  String expTCmp = tCMP.getText();
	  	    	  String expAvgCMP = AvgeCMP.getText();
	  	    	  String expTotalPYAwards = TPYAwards.getText();
	  	    	  String expAvgPYAwards = AvgePYAwards.getText();	  	    	  
	  	    	  String expTotalCYAwards = TCYAwards.getText();	  	    	  
	  	    	  String expAvgCYAwards = AvgeCYAwards.getText();
	  	    	  	System.out.println("expTCmp "+ expTCmp);
		  	    	System.out.println("expAvgCMP "+ expAvgCMP);
		  	    	System.out.println("expTotalPYAwards "+ expTotalPYAwards);
		  	    	System.out.println("expAvgPYAwards "+ expAvgPYAwards);
		  	    	System.out.println("expTotalCYAwards "+ expTotalCYAwards);
		  	    	System.out.println("expAvgCYAwards "+ expAvgCYAwards);
		  	    	clickOnElement("cssSelector", repClose);	    			
		  	    	userLogout();
		  	    	logOut();
		  	    	logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		  	    	loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
		  	    						prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			navigateReports();	
			reportstable(reportTable);
			updateGenerate(3);
			selectAdvancedFilterOptions(constraintTitle, expFromConstnt, expToConstnt);
			setColumnsVisibilities(AVoutputColmns);
			int actualResults = resultFound();			
			if ((actualResults==expResultsCount||(actualResults==(expResultsCount+1))||
					(actualResults==(expResultsCount-1))||(actualResults==(expResultsCount-2)))){
			
			selectRepresentativeDetails(colName, expAccount);
			personalProfile(accNumber, expProfileAcc);
			//personalProfile(email, expProfileEmail);
			//personalProfile(phone, expProfilePhone);			
			salesPerformance(expTCmp, expAvgCMP, expTotalPYAwards, expAvgPYAwards,expTotalCYAwards,expAvgCYAwards);
					}else {
						Assert.assertEquals(actualResults, expResultsCount);
							}
				break;					
	    			}
	    	}if (isTitleFound==false){
				Assert.assertTrue(isTitleFound, constraintTitle + " - Constriant title is not present");
	    		
	    			}	    							
	    }
	 

	//Select Add/Remove Column, Then select column labels from Output section for visibility of columns under table
	 public void setColumnsVisibilities(String[] colNames) throws Exception{	
	 	logInfo("Entered into setColumnsVisibilities() method");	
	 	clickOnElement("cssSelector", addRemoveColumns);	
	 	String attClass = driver().findElement(By.cssSelector(addRemoveColumns)).getAttribute("class");
	 	System.out.println("attClass " + attClass);
	 	if (attClass.contains("collapsed")){	
	 		Thread.sleep(3000);
	 		clickOnElement("cssSelector", addRemoveColumns);
	 		}else{
	 		System.out.println("Sucess opened");	 		
	 			}
	 		waitOnElement("cssSelector", opFields);
	 	
	 	List <WebElement> selectedCol = driver().findElements(By.cssSelector(opFields));
	 	for (WebElement totalSelected :selectedCol){
	 		totalSelected.click();	 		
	 	}
	 	
	 	boolean isColFound=false;
	 	int colLeng = colNames.length;	 	
	 	List <WebElement> colmns = driver().findElements(By.cssSelector(colOutputBtns));	 
	 	for (int i=1; i<=colmns.size(); i++){
	 	WebElement columns = driver().findElement(By.cssSelector(colOutputBtnsBfr+i+colOutputBtnsAft));	 	
	 	for (int j=0; j<=colLeng-1; j++){	
	 		if(columns.getText().trim().equalsIgnoreCase(colNames[j])){	
	 		     isColFound=true;
	 			 columns.click();	 				 
	 			 break;
	 				}
	 			}
	 		}
	 	if(isColFound==false){
	 		Assert.assertTrue(isColFound, colNames +" is/are not found output Column");
	 	}	
	 	    waitOnElement("cssSelector", runReports);
	 		clickOnElement("cssSelector", runReports);	
	 		Thread.sleep(3000);
	   }
	 
	 
	 
	// -- 04232017 open user- Representative  details by column with row data 	
			public String retrieveRepresentativeDetails(String colName) throws Exception{			
				logInfo("Enterted into selectRepresentativeDetails(String colName, String value) method");	
				
				String expectedAccountNumber =null;				
				WebElement result = driver().findElement(By.cssSelector(resultsCount));
				String actResultsFound =result.getText();
				int resultCount = Integer.parseInt(actResultsFound);
				System.out.println("ActResult Count is "+resultCount );
					boolean isColumnPresent = false;
					List <WebElement> colmns = driver().findElements(By.cssSelector(tableCol));	
					System.out.println("colmns "+colmns.size());
					for (int i=1 ; i<=colmns.size(); i++ ){				
						WebElement allColumns = driver().findElement(By.cssSelector(tableColBfr+i+tableColAft));
						System.out.println("col Name "+allColumns.getText());
						if(allColumns.getText().equalsIgnoreCase(colName)){
							isColumnPresent = true;													
							JavascriptExecutor js = (JavascriptExecutor)driver();		
						/*	WebElement scroll = driver().findElement(By.cssSelector(totRowsFrst+resultCount+totRowsScnd+i+totRowsThd));
							js.executeScript("arguments[0].scrollIntoView(true);", scro ll);*/
							
							List<WebElement> selectLink = driver().findElements(By.cssSelector(totRows+i+totRowsThd));
							System.out.println("size in rows "+ selectLink.size());
							int j = TestBase.generateRandomNumberInRange(1, selectLink.size());	
							System.out.println("dianamic genarted J value is "+ j);
							WebElement rowValue = driver().findElement(By.cssSelector(totRowsFrst+j+totRowsScnd+i+totRowsThd));
							expectedAccountNumber= rowValue.getText();													
							System.out.println("exepected account s "+expectedAccountNumber);														
								
									System.out.println("Selected Account Number");
									Actions act = new Actions (driver());
									act.doubleClick(rowValue).build().perform();	
									System.out.println("Doule Selected Account Number");
									break;		
						
							}if(isColumnPresent==false){
								
								Assert.assertTrue(isColumnPresent, colName + " column is not present in Table" );
							}					
					}
					return expectedAccountNumber;
				}
	 
	 
	 // -- 04232017 open user- Representative  details by column with row data 	
		public void selectRepresentativeDetails(String colName, String selectValue) throws Exception{			
			logInfo("Enterted into selectRepresentativeDetails(String colName, String value) method");			
			
			WebElement result = driver().findElement(By.cssSelector(resultsCount));
			String actResultsFound =result.getText();
			int resultCount = Integer.parseInt(actResultsFound)-20;
			System.out.println("ActResult Count is "+resultCount );
				boolean isColumnPresent = false;
				List <WebElement> colmns = driver().findElements(By.cssSelector(tableCol));	
				System.out.println("colmns "+colmns.size());
				for (int i=1 ; i<=colmns.size(); i++ ){				
					WebElement allColumns = driver().findElement(By.cssSelector(tableColBfr+i+tableColAft));
					System.out.println("col Name "+allColumns.getText());
					if(allColumns.getText().equalsIgnoreCase(colName)){
						isColumnPresent = true;
						boolean isRowValuePresent = false;				
						
						JavascriptExecutor js = (JavascriptExecutor)driver();		
					/*	WebElement scroll = driver().findElement(By.cssSelector(totRowsFrst+resultCount+totRowsScnd+i+totRowsThd));
						js.executeScript("arguments[0].scrollIntoView(true);", scroll);*/
						
						List<WebElement> selectLink = driver().findElements(By.cssSelector(totRows+i+totRowsThd));
						System.out.println("size in rows "+ selectLink.size());
						for (int j =1; j<=selectLink.size();j++){
							WebElement rowValue = driver().findElement(By.cssSelector(totRowsFrst+j+totRowsScnd+i+totRowsThd));
							System.out.println(rowValue.getText());
							
							
							
							if(rowValue.getText().equalsIgnoreCase(selectValue)){
								String element = totRowsFrst+j+totRowsScnd+i+totRowsThd;
								System.out.println("exepected account s "+rowValue.getText());
								isRowValuePresent=true;	
								
							/*	WebElement scrollDn = driver().findElement(By.cssSelector(element));
								js.executeScript("arguments[0].scrollIntoView(true);", scrollDn);
								//js.executeScript("window.scrollBy(0,-50)",scroll ); 
								Thread.sleep(4000);	
								
								scrollDn.click();	
									*/			
								/*waitOnElement("cssSelector", element);
								rowValue.click();*/
								System.out.println("Selected Account Number");
								Actions act = new Actions (driver());
								act.doubleClick(rowValue).build().perform();	
								System.out.println("Doule Selected Account Number");
								break;
						}}if(isRowValuePresent==false){
								Assert.assertTrue(isRowValuePresent, selectValue + " value is not present in column under "+ colName );								
							}break;				
						}}if(isColumnPresent==false){							
							Assert.assertTrue(isColumnPresent, colName + " column is not present in Table" );
						}					
				}
		
		
		
		public void profileSections(String sectionName) throws Exception{
	    	  boolean isSecPresent = false;    	 
	    	  waitOnElement("cssSelector", "div.panel-title");
	    	  List <WebElement> sec = driver().findElements(By.cssSelector(profSec));	    	  
	    	  for (int i=1; i<=sec.size();i++){	    		
	    		WebElement sections = driver().findElement(By.cssSelector(profSecBfr+i+profSecAft));	    		 
	    		  if (sections.getText().equalsIgnoreCase(sectionName)){
	    			  isSecPresent= true;
	    			  sections.click();
	    			  break;
	    		  }
	    	  }if (isSecPresent==false){
	    		  Assert.assertTrue(isSecPresent, sectionName+" is not present");
	    	  }    	  
	      }
	      
		
		
		public String retrievePersonalProfile (String detailType) throws Exception{
			logInfo("Entered into retrievePersonalProfile() method");
			String expectedProfDetails=null;
	    	   boolean isPresent = false;
	    	   waitOnElement("cssSelector", pProfile2);
	    	   List <WebElement> proSec = driver().findElements(By.cssSelector(pProfile2));
	    	  	   for (int i=1; i<=proSec.size(); i++){
	    		   WebElement profdetls = driver().findElement(By.cssSelector(pProfile2Bfr+i+pProfile2Aft));
	    		   String profileDetails = profdetls.getText().trim();
	    		   if (profileDetails.equalsIgnoreCase(detailType)){	    			   
	    			   isPresent= true;
	    			   WebElement profdata = driver().findElement(By.cssSelector(pProfile2Bfr+i+pProfile2DataAft));
	    			   expectedProfDetails = profdata.getText();
	    			   System.out.println("expectedProfDetails "+ expectedProfDetails); 
	    			   break; 
	    			   	   }  	
	    	   }if (isPresent==false){
	    		   Assert.assertTrue(isPresent, detailType + " is not present in Personal Profile.");
	    	   }
			return expectedProfDetails;   	  
	      }
	      
		 public void personalProfile (String detailType, String expectedData) throws Exception{	  
	    	   boolean isPresent = false;
	    	   waitOnElement("cssSelector", pProfile2);
	    	   List <WebElement> proSec = driver().findElements(By.cssSelector(pProfile2));
	    	   System.out.println("No of profiledetails "+ proSec.size());
	    	   for (int i=1; i<=proSec.size(); i++){
	    		   WebElement profdetls = driver().findElement(By.cssSelector(pProfile2Bfr+i+pProfile2Aft));
	    		   String profileDetails = profdetls.getText().trim();
	    		   System.out.println(profileDetails);
	    		
	    		   if (profileDetails.equalsIgnoreCase(detailType)){
	    			   System.out.println(profileDetails );
	    			   isPresent= true;
	    			   WebElement profdata = driver().findElement(By.cssSelector(pProfile2Bfr+i+pProfile2DataAft));
	    			   String ActualData = profdata.getText();	    			   
	    			   Assert.assertEquals(ActualData, expectedData);
	    			   break; 
	    			   	   }  		   
	    		   
	    	   }if (isPresent==false){
	    		   Assert.assertTrue(isPresent, detailType + " is not present in Personal Profile.");
	    	   }   	  
	      }
		 
		 
		 //select Advanced filter and selects constraints data.
		 public void selectAdvancedFilterOptions(String constriantTitle, String fromFieldData, String toFiledData) throws Exception{
		       logInfo("Entered into selectAdvancedFilterOptions() method");	
			 	clickOnElement ("cssSelector",filterOp );
		    	waitOnElement("cssSelector", filterAchievementTitleBetween);    	
		    	
		    	List<WebElement> consHead = driver().findElements(By.cssSelector(constrnt));		    	
		    	boolean isTitleFound = false;
		    	for (int i=1; i<=consHead.size(); i++){
		    		WebElement consHeader = driver().findElement(By.cssSelector(constrntBfr+i+constrntAft));    		
		    		String actHeader = consHeader.getText().trim();		    		
		    		int j=i-1; 
		    		if (actHeader.equalsIgnoreCase(constriantTitle)){
		    			isTitleFound=true;
		    			clickOnElement("cssSelector", constrntLeadBfr+j+constrntLeadAft);
						List<WebElement> fromOp = driver().findElements(By.cssSelector(constrntLeadBfr+j+constrntLeadOptionsAft));
		    			boolean isFromOptionFound = false;
		    			for(WebElement from:fromOp){
		    				String fromOption = from.getText().trim();		    				
		    				if(fromOption.equalsIgnoreCase(fromFieldData)){
		    				isFromOptionFound =true;
							from.click();    				
		    				break;
		    						}
		    			}if (isFromOptionFound==false){
		    				Assert.assertTrue(isFromOptionFound,fromFieldData+" -from option is not found in Current Leadership Title Between");
								}
		    			clickOnElement("cssSelector", constrntLeadBfr+j+constrntLeaderToAft);
		    			List<WebElement> toOp = driver().findElements(By.cssSelector(constrntLeadBfr+j+constrntLeaderToOptionsAft));
		    			boolean isToOptionFound = false;
		    			for(WebElement to:toOp){    				
		    				String toOptions = to.getText().trim();		    				
		    				if(toOptions.equalsIgnoreCase(toFiledData)){
		    					isToOptionFound =true;
		    					to.click();
		    					break;
		    				}
		    			}if (isToOptionFound==false){
		    				Assert.assertTrue(isToOptionFound,toFiledData+" -to option is not found in Current Leadership Title Between");
		    			}   			
		    			
		    			break;   			
		    		}
		    		
		    		
		    	}if (isTitleFound==false){
					Assert.assertTrue(isTitleFound, constriantTitle + " - Constriant title is not present");
		    		
		    			}
		    	clickOnElement ("cssSelector",filterOp );
	 
		 }
		 
		 
		 public void salesPerformance(String expTotal_cmp, String expAvg_cmp,
	    			String expTotPyAwardSales, String expAvgPyAwardSales, String expTotCyAwards, String expAvgCyAwards) throws Exception{
	    	  
	    	  profileSections("Sales Performance");	    	  
	    	  WebElement tCMP = driver().findElement(By.cssSelector(totalCMP));
	    	  String ActTotalCMP = tCMP.getText(); 	    	 
	    	  
	    	  WebElement AvgeCMP = driver().findElement(By.cssSelector(AvgCMP));
	    	  String ActAvgCMP = AvgeCMP.getText();	    	  
	    	  WebElement TPYAwards = driver().findElement(By.cssSelector(TotalPYAwards));
	    	  String ActTotalPYAwards = TPYAwards.getText();
	    	  
	    	  WebElement AvgePYAwards = driver().findElement(By.cssSelector(AvgPYAwards));
	    	  String ActAvgPYAwards = AvgePYAwards.getText();
	    	  
	    	  WebElement TCYAwards = driver().findElement(By.cssSelector(TotalCYAwards));
	    	  String ActTotalCYAwards = TCYAwards.getText();
	    	  
	    	  WebElement AvgeCYAwards = driver().findElement(By.cssSelector(AvgCYAwards));
	    	  String ActAvgCYAwards = AvgeCYAwards.getText();
	    	  
	    	  System.out.println("ActTotalCMP         " + ActTotalCMP   + " " + expTotal_cmp);
	    	  System.out.println("ActAvgCMP    " + ActAvgCMP+ "   " +expAvg_cmp );
	    	  System.out.println("ActTotalPYAwards  " + ActTotalPYAwards+ " " +expTotPyAwardSales );
	    	  System.out.println("ActAvgPYAwards      " + ActAvgPYAwards + "" + expAvgPyAwardSales );
	    	  System.out.println("ActTotalCYAwards   " + ActTotalCYAwards+ " " +expTotCyAwards );
	    	  System.out.println("ActAvgCYAwards    " + ActAvgCYAwards+ "" + expAvgCyAwards );
	    	  
	    	  Assert.assertEquals(ActTotalCMP, expTotal_cmp);
	    	  Assert.assertEquals(ActAvgCMP,  expAvg_cmp);
	    	  Assert.assertEquals(ActTotalPYAwards, expTotPyAwardSales); 
	    	  Assert.assertEquals(ActAvgPYAwards, expAvgPyAwardSales);
	    	  Assert.assertEquals(ActTotalCYAwards, expTotCyAwards);	    	  
	    	  Assert.assertEquals(ActAvgCYAwards, expAvgCyAwards);	    	  
	    	  clickOnElement("cssSelector", repClose);	    	    	 
	      }
		 
		 
		 public void validateConstriantsTitle() throws Exception{
			 logInfo("Entered into validateConstriantsTitle() method")	;	
			 waitOnElement("cssSelector", consts);
			 WebElement co = driver().findElement(By.cssSelector(consts));
			 String acctTitle = co.getText().trim();
			 System.out.println("acctTitle"+acctTitle);
			 Assert.assertEquals(acctTitle, "Constraints");
			 
		 }
		 
		 public void constriantTitleNotTobePresent() throws Exception{
			 logInfo("Entered into validateConstriantsTitle() method")	;		 
			 waitOnElement("cssSelector", consts);
			 WebElement co = driver().findElement(By.cssSelector(consts));
			 String acctTitle = co.getText().trim();
			 System.out.println("acctTitle"+acctTitle);
			 Assert.assertNotEquals(acctTitle, "Constraints");
			 
		 }
		 
		 

}