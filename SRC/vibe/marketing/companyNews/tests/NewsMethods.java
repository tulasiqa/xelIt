
package vibe.marketing.companyNews.tests;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import vibe.billing.tests.BillingMethods;
import vibe.users.tests.UsersMethods;
import common.TestBase;


public class NewsMethods extends TestBase{		
	
	UsersMethods um = new UsersMethods();
	BillingMethods bm = new BillingMethods();
	
	//Setters
	
	public void navigateCompanyNews() throws Exception{
		logInfo("Entered into navigateCompanyNews() method");
		driver().navigate().to(appUrl+"/pyr_core/company_news");
			
	}
	
	public void selectAddCompanyNews() throws Exception{		 
		logInfo("Entered into selectAddCompanyNews() method");
		waitOnElement("cssSelector",compAddCompanyNews);
		clickOnButton("cssSelector",compAddCompanyNews);
	}
		
	public void enterTitle (String title) throws Exception{
		logInfo("Entered into enterTitle() method");
		inputTextClear("cssSelector", compTitle);
		inputText("cssSelector", compTitle, title);
		waitOnElement("cssSelector", compTitle);		
		
	}
	
	public void enterExcerpt (String Excerpt) throws Exception{
		logInfo("Entered into enterExcerpt() method");
		waitOnElement("cssSelector", compExcerpt);
		inputText("cssSelector", compExcerpt, Excerpt);	
	}
		
	
	public void rankDefinition(String ranker) throws  InterruptedException{
		logInfo("Entered into rankDefinition() method");
		try{
		clickOnElement("cssSelector", compRank);
		Thread.sleep(3000);
		WebElement rank = driver().findElement(By.cssSelector(rankAll));
		if (rank.isSelected()){
			rank.click();
			Thread.sleep(2000);			
		}		
		List <WebElement> status = driver().findElements(By.cssSelector(comRankDef));
		for (WebElement st : status){			
			if (st.getText().equalsIgnoreCase(ranker)){
				if(!st.isSelected()){			
			st.click();	
			break;
			}}
		}}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to define Ranks.");
		}
	}


	
	public void marketLanguages(String[] language) throws  InterruptedException{
		logInfo("Entered into marketLanguages() method");
		try{
		clickOnElement("cssSelector", compLang);
		Thread.sleep(2000);
		WebElement marketAll = driver().findElement(By.cssSelector(markAll));
		if (marketAll.isSelected()){
			marketAll.click();
			Thread.sleep(2000);
		}		
		int langgs = language.length;
		List <WebElement> lang = driver().findElements(By.cssSelector(compLangDD));			
		for (WebElement langs : lang){	
			for (int i=0; i<=langgs-1; i++ )
			if (langs.getText().equalsIgnoreCase(language[i])){
				if(!langs.isSelected()){				
				langs.click();	
				break;
			}}
		}}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select Market Language.");
		}
	}
	
	
	public void subscriptionPlanSlection(String[] SubcriptionPlan) throws Exception{
		logInfo("Entered into subscriptionPlanSlection() method");
		try{			
			clickOnElement("cssSelector", compSubPlan);			
			WebElement subsAll = driver().findElement(By.cssSelector(subAll));
			if (subsAll.isSelected()){
				subsAll.click();
				Thread.sleep(3000);
				}
			boolean isSubFound = false;			
			int subcription = SubcriptionPlan.length;			
			List <WebElement> sub = driver().findElements(By.cssSelector(compSubPlanDD));			
			for (WebElement subPlan : sub){	
				for(int j=0; j<=subcription-1;j++){				
				if (subPlan.getText().equalsIgnoreCase(SubcriptionPlan[j])){
					isSubFound= true;
					if(!(subPlan.isSelected())){
					subPlan.click();
					break;
						}
				    }	
				}
			}if (isSubFound==false){
				Assert.assertTrue(isSubFound, SubcriptionPlan+" - subscription is not found");
			}
			
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select Subcription Plans.");
		}				
	}
	
	public void subscriptionSlectionOfEdited(String[] SubcriptionPlan) throws Exception{
		logInfo("Entered into subscriptionSlectionOfEdited() method");
		try{
			waitOnElement("cssSelector", compSubPlan);
			clickOnElement("cssSelector", compSubPlan);			
			WebElement subsAll = driver().findElement(By.cssSelector(subAll));
			if (!(subsAll.isSelected())){
				subsAll.click();
				Thread.sleep(2000);
				}				
				waitOnElement("cssSelector", subAll);
				subsAll.click();				
			boolean isSubFound = false;
			int subcription = SubcriptionPlan.length;			
			List <WebElement> sub = driver().findElements(By.cssSelector(compSubPlanDD));			
			for (WebElement subPlan : sub){	
				for(int j=0; j<=subcription-1;j++){				
				if (subPlan.getText().equalsIgnoreCase(SubcriptionPlan[j])){
					isSubFound= true;
					if(!(subPlan.isSelected())){
					subPlan.click();
					break;
						}
				    }	
				}
			}if (isSubFound==false){
				Assert.assertTrue(isSubFound, SubcriptionPlan+" - subscription is not found");
			}
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select Subcription Plans.");
		}				
	}
	
	public void selectIsNews() throws Exception{
		logInfo("Entered into selectIsNews() method");
		try{			
			WebElement OV = driver().findElement(By.cssSelector(compIsNews));	
			if(!OV.isSelected()){
				OV.click();			
			}
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select check box Of IsNews .");
		}	
	}
	
	public void selectIsHighLight() throws Exception{
		logInfo("Entered into selectIsHighLight() method");
		try{			
			WebElement OV = driver().findElement(By.cssSelector(compIsHighLights));	
			if(!OV.isSelected()){
				OV.click();
				Thread.sleep(5000);
			}
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select check box Of IsHighLights .");
		}		
		
	}
	
	public void selectPublishImmediately() throws  InterruptedException{
		logInfo("Entered into selectPublishImmediately() method");
		try{			
			WebElement OV = driver().findElement(By.cssSelector(compPublishArticle));	
			if(!OV.isSelected()){
				OV.click();
				Thread.sleep(5000);
			}
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select check box Of 'Publish this Article Immediately.");
		}			
	}

	public void clickOnPublishButton() throws Exception{
		logInfo("Entered into clickOnPublishButton() method");
		waitOnElement("cssSelector", compPublish);
		waitOnElement("cssSelector", compPublish);
		scrollDown("cssSelector", compPublish);
		clickOnButton("cssSelector", compPublish);			
	}
	
	public void enterPublishDate(int FutueDate) throws Exception{
		logInfo("Entered into enterPublishDate() method");
		String futDate = getDate(FutueDate, "MM/dd/yyyy")	;
		waitOnElement("cssSelector", compPublishDate);
		inputTextClear("cssSelector", compPublishDate);
		inputText("cssSelector", compPublishDate, futDate);	
		clickOnElement("cssSelector",compPublishClock);
		Thread.sleep(1000);
		clickOnElement("cssSelector",compPublishClock);
		Thread.sleep(2000);
	        }
	
	public void editNews(String title) throws Exception{	
		logInfo("Entered into editNews() method");
		boolean isNews = false;
		waitOnElement("cssSelector", companyNewsListTitle);
		List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));		
		waitOnElement("cssSelector", companyNewsListTitle);		
		for (WebElement list : newsList){			
			if(list.getText().contains(title)){	
				isNews = true;
				list.click();			
				waitOnElement("cssSelector", compEdit);
				clickOnElement("cssSelector", compEdit);				
				break;
			}
		}if(isNews==false){
			Assert.assertTrue(isNews, title +" is not found");
		}
	}
	
	// verifies News in Admin Page
	public void isNewsTitlePresent(String title) throws Exception{
		logInfo("Entered into isNewsTitlePresent() method");
		waitOnElement("cssSelector", companyNewsListTitle);
		Thread.sleep(2000);
		waitOnElement("cssSelector", companyNewsListTitle);
		List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
		boolean isTitlePresent =false;		
		for (WebElement list : newsList){
			waitOnElement("cssSelector", companyNewsListTitle);			
			if((list.getText()).equalsIgnoreCase(title)){				
				isTitlePresent = true;			
				break;							
			}}
		if(isTitlePresent==false){
				Assert.assertTrue(isTitlePresent,title+" is not available in the grid" );					
			}		
		}	
	
	
	public void selectNewsLinkToOpen(String title) throws Exception{
		logInfo("Entered into selectNewsLinkToOpen() method");
		boolean isNews = false;
		List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));		
		for (WebElement list : newsList){	
			if(list.getText().contains(title)){	
				isNews = true;
				list.click();					
				break;
			}
		}if(isNews==false){
			Assert.assertTrue(isNews, title +" is not found");
		}
	}
	
	// verifies News in Admin Page - not to present
	public boolean isNewsTitleNotToPresent(String title) throws Exception{	
		logInfo("Entered into isNewsTitleNotToPresent() method");	
		waitOnSpinner();
		waitOnElement("cssSelector", companyNewsListTitle);
		List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
		boolean isTitlePresent =true;		
		for (WebElement list : newsList){	
			waitOnElement("cssSelector", companyNewsListTitle);
			if((list.getText()).equalsIgnoreCase(title)){				
				isTitlePresent = false;
				Assert.assertTrue(isTitlePresent,title+" is still available in the grid" );
				break;										
			}}
		if(isTitlePresent==true){
					System.out.println(title + " is not present. Success");
			}
		return isTitlePresent;		
		}
	 //In Home page, drag News widget and select view more link and verify news
  	public boolean verifyNewsInWidget(String isNewsOrHighlight, String title) throws Exception{
		logInfo("Entered into verifyNewsInWidget() method");
		clickOnLink("linkText", isNewsOrHighlight);	
	    clickOnLink("linkText", isNewsOrHighlight);	
	    clickOnLink("linkText", isNewsOrHighlight);	
	    waitOnElement("cssSelector", companyNewsListTitle);
	    Thread.sleep(4000);
	    List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
		boolean isTitlePresent =false;		
		for(WebElement newsLists : newsList ){
			String titles = newsLists.getText().trim();		
			if(titles.equalsIgnoreCase(title)){			
				isTitlePresent = true;					
				break;										
				}}			
			return isTitlePresent;		
			}	  
		   
	
	   
	   //In Home page, drag News widget and select view more link and verify news - not to present
	   	public void verifyNewsNotToPresentInWidget(String isNewsOrHighlight, String title) throws Exception{
	   		logInfo("Entered into verifyNewsNotToPresentInWidget() method");
	     	clickOnLink("linkText", isNewsOrHighlight);		
	     	clickOnLink("linkText", isNewsOrHighlight);	
	     	clickOnLink("linkText", isNewsOrHighlight);	
	     	waitOnElement("cssSelector", compNewsListTitle);
	     	Thread.sleep(4000);
			List<WebElement> newsList =  driver().findElements(By.cssSelector(compNewsListTitle));			
			boolean isTitlePresent =false;		
			for (WebElement list : newsList){					
				if((list.getText()).equalsIgnoreCase(title)){				
					isTitlePresent = false;
					Assert.assertTrue(isTitlePresent,title+" is Still available in the grid" );	
					break;											
						}
				}
			if(isTitlePresent==true){
					System.out.println("Suceess!! Is not available ");	
				}		
			}	   
	
	   public void selectArchievedDate(int ArchievedDate) throws Exception{	
		   logInfo("Entered into selectArchievedDate() method");
		   String Date = getDate(ArchievedDate, "MM/dd/yyyy")	;		   
		   WebElement ad = driver().findElement(By.cssSelector(compScheduleArchive));
		   if(!ad.isSelected()){
			   ad.click();     }	
		   	inputTextClear("cssSelector", archDateField);
			inputText("cssSelector", archDateField, Date);		
			Thread.sleep(1000);
			inputTextClear("cssSelector", archTimeField);
			inputText("cssSelector", archTimeField, "00:00");
			Thread.sleep(1000);
		   }
		   
	   
		   public void validateArchievedDate(int archievedPastDate) throws Exception{	
			   logInfo("Entered into validateArchievedDate() method");
			   String Date = getDate(archievedPastDate, "MM/dd/yyyy")	;		   
			   WebElement ad = driver().findElement(By.cssSelector(compScheduleArchive));
			   if(!ad.isSelected()){
				   	ad.click();				   
			   					}
			   	inputTextClear("cssSelector", archDateField);
				inputText("cssSelector", archDateField, Date);		
				Thread.sleep(1000);
				clickOnPublishButton();
				confirmationMessage("Archive date needs a time!");
				Thread.sleep(4000);
				inputTextClear("cssSelector", archTimeField);
				inputText("cssSelector", archTimeField, "00:00");
				Thread.sleep(1000);	
				clickOnPublishButton();
				confirmationMessage("Archive Date should not be in the past");     
		   
	   }
	
	public void selectNewsBasedOnTitle(String title) throws Exception{	
		logInfo("Entered into selectNewsBasedOnTitle() method");
		List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
		boolean isTitlePresent =false;		
		for (WebElement list : newsList){		
			if((list.getText()).equalsIgnoreCase(title)){			
				isTitlePresent = true;
				list.click();
				break;										
			}}
		if(isTitlePresent==false){
				Assert.assertTrue(isTitlePresent,title+" is not available in the grid" );			
				
			}		
		}
		
	public void deleteNewsBasedOnExcerpt(String expecrt) throws Exception{
		logInfo("entered into deleteNewsBasedOnExcerpt() method");		
		boolean ispresent = false;
		List<WebElement> exList =  driver().findElements(By.cssSelector(excerptDesc));		
		for (int i=1; i<=exList.size(); i++){
			WebElement exrt = driver().findElement(By.cssSelector(newsTitleBfr+i+newsTitleAfr));
			String actualExcept = exrt.getText().trim();			
			 if (actualExcept.contains(expecrt)){
				 ispresent =true;				
				 WebElement delet = driver().findElement(By.cssSelector(newsTitleBfr+i+deleNewsAft));
				 clickOnElement("cssSelector", ".media-block-object>img");
				 delet.click();
				 confirmOK();	
				 break;				 
			 } 
			 }if (ispresent==false){
				 Assert.assertTrue(ispresent, expecrt + " is not present.");
			 }
	}	
	
	// Drag News widget and select ViewMore Link 
	public void dragNewswidgets() throws Exception{
		logInfo("Entered into dragNewswidgets() method");
		/*tm.selectEditWidgetsFromHome();				
		dragCompanyNews();	
		clickOnElement("cssSelector",closeWidgetInHomeBranch);*/
		selectViewMoreLink();
		
	}
	
	public void dragCompanyNews() throws Exception{
		logInfo("Entered into enterTitle() method");			
			WebElement from = driver().findElement(By.xpath(companyNEWSWidgets));	
			WebElement to = driver().findElement(By.xpath(dragWidgetToLocation));  //  dragWidgetToLocationInHome
			dragAndDropAction(from, to);				
	}	   
	
		
	public void selectViewMoreLink() throws Exception{	
		logInfo("Entered into selectViewMoreLink() method");
		waitOnElement("cssSelector", vieMorelnk);
		waitOnElement("cssSelector", vieMorelnk);
		scrollDown("cssSelector", vieMorelnk);		
		waitOnElement("cssSelector", vieMorelnk);		
		clickOnElement("cssSelector", vieMorelnk);
		waitOnElement("cssSelector", vieMorelnk);
		driver().navigate().to(appUrl+"/pyr_core/company_news/list?show_highlights=true");
		
	}
	
	
	public void sortMarket(String country) throws Exception{
		logInfo("Entered into sortMarket() method");	
		waitOnElement("cssSelector", sortMarking);
		WebElement selMar=driver().findElement(By.cssSelector(sortMarking)) ;
		selMar.click();
		boolean ismarkPresent = false;
		List <WebElement> ma = driver().findElements(By.cssSelector(sortMark));
		for (WebElement mark : ma ){
			if(mark.getText().equalsIgnoreCase(country)){
				ismarkPresent=true;
				mark.click();
				waitOnElement("cssSelector", sortMarking);
				Thread.sleep(2000);
				break;
			}
		}if (ismarkPresent==false){
			Assert.assertTrue(ismarkPresent, country + " -market is not present");
		}
		
	}
	
public void sortLanguages(String language) throws Exception{
	logInfo("Entered into sortLanguages() method");
	waitOnElement("cssSelector", sortLangsdp);
	WebElement selMar=driver().findElement(By.cssSelector(sortLangsdp)) ;
	selMar.click();		
	boolean isLangPresent=false;
	waitOnElement("cssSelector", sortLangs);
	List <WebElement> ma = driver().findElements(By.cssSelector(sortLangs));	
	for (WebElement mark : ma ){		
		if(mark.getText().equalsIgnoreCase(language)){
			isLangPresent=true;
					mark.click();
					break;
				}
			}if (isLangPresent==false){
				Assert.assertTrue(isLangPresent, language + " -market is not present");
			}
		
	}
	
    public void sortSubscriptions(String subscription) throws Exception{
    	logInfo("Entered into sortSubscriptions() method ");
    	waitOnElement("cssSelector", sortSubsdp);
	   	WebElement selMar=driver().findElement(By.cssSelector(sortSubsdp)) ;
		selMar.click();	
		boolean isSubPresent =false;
		List <WebElement> ma = driver().findElements(By.cssSelector(sortSubs));
		for (WebElement mark : ma ){
				if(mark.getText().equalsIgnoreCase(subscription)){
					isSubPresent= true;
					mark.click();
					break;
			        }		
		      }if (isSubPresent==false){
		    	  Assert.assertTrue(isSubPresent,subscription +" -subscription is not present" );
		      }
        }

    public void sortRanks(String Rank) throws Exception{
    	logInfo("Entered into sortRanks() method");
    	waitOnElement("cssSelector", sortRankdp);
		WebElement selMar=driver().findElement(By.cssSelector(sortRankdp)) ;
		selMar.click();	
		boolean isRankPresent =false;
		List <WebElement> ma = driver().findElements(By.cssSelector(sortRank));
		for (WebElement mark : ma ){
			if(mark.getText().equalsIgnoreCase(Rank)){
				isRankPresent= true;
				mark.click();
				break;
			         }		
		          }	if (isRankPresent==false){
		        	  Assert.assertTrue(isRankPresent, Rank+ " - Ranker is not present");
		          }
    }
    
    public void sortType(String type) throws Exception{
    logInfo("Entered into sortType() method");
	    waitOnElement("cssSelector", sortRankdp);
		WebElement selMar=driver().findElement(By.cssSelector(sortTypedp)) ;
		selMar.click();
		boolean isTypePresent =false;
		List <WebElement> ma = driver().findElements(By.cssSelector(sortType));
		for (WebElement mark : ma ){
			if(mark.getText().equalsIgnoreCase(type)){
				isTypePresent= true;
				mark.click();
				break;
			    }    
			}if (isTypePresent==false){
				Assert.assertTrue(isTypePresent,type +" - type is not present" );
			}
		}
     

    public void sortStatus(String status) throws Exception{
    	logInfo("Entered into sortStatus() method");
	    boolean isStatusPresent = false;	  
	    waitOnElement("cssSelector", sortStdp);
		WebElement selMar=driver().findElement(By.cssSelector(sortStdp)) ;
		selMar.click();
		List <WebElement> ma = driver().findElements(By.cssSelector(sortSt));
		for (WebElement mark : ma ){
			if(mark.getText().equalsIgnoreCase(status)){
				isStatusPresent = true;
				mark.click();
				waitOnElement("cssSelector", companyNewsListTitle);					
				break;
			        }
			}if (isStatusPresent==false){
				Assert.assertTrue(isStatusPresent, status + "- type of status is not present");
			}
	 }
	
	

	 public void sortTags(String file) throws Exception{	
		logInfo("Entered into sortTags() method");
		waitOnElement("cssSelector", sortTagdp); 
		Thread.sleep(2000);
		WebElement selMar=driver().findElement(By.cssSelector(sortTagdp)) ;
		selMar.click();
		Thread.sleep(2000);
		boolean isPresent = false;
		List <WebElement> ma = driver().findElements(By.cssSelector(sortTag));
		for (WebElement mark : ma ){
			if(mark.getText().equalsIgnoreCase(file)){
				isPresent=true;
				mark.click();
				break;
			}
		}if(isPresent==false){
			Assert.assertTrue(isPresent,file);
		}
	}
	 
	 
	 public void sortTagToNotPresent(String file) throws Exception{	
		logInfo("Entered into sortTagToNotPresent() method");
		waitOnElement("cssSelector", sortTagdp); 		
		WebElement selMar=driver().findElement(By.cssSelector(sortTagdp)) ;
		selMar.click();		
		boolean isPresent = true;
		waitOnElement("cssSelector", sortTag);
		List <WebElement> ma = driver().findElements(By.cssSelector(sortTag));
		for (WebElement mark : ma ){
			if(mark.getText().equalsIgnoreCase(file)){
				isPresent=false;		
				Assert.assertTrue(isPresent,file);
				break;
			}
		}if(isPresent==true){
			System.out.println("success!! Is not present");
		}		
	}    
      
  	public void backToOffice() throws Exception{
  		logInfo("Entered into backToOffice() method");
		waitOnElement("linkText", "back to office");
		clickOnLink("linkText", "back to office");
	}  	
  
  	public void companyNewsCreation(String NewsTitle, String excerpt ,String ranker,String[] markets,  String[] Subscription, String imdPublishYesOrNull, String isNewsYesorNull, String isHighLightYesorNull,String publishYesOrNull ) throws Exception{
  		logInfo("Entered into companyNewsCreation() method");		
		navigateCompanyNews();
		waitOnElement("cssSelector",compAddCompanyNews);
		waitOnElement("cssSelector",compAddCompanyNews);
		clickOnButton("cssSelector",compAddCompanyNews);
		waitOnElement("cssSelector", compPublish);
		waitOnElement("cssSelector", compPublish);
		waitOnSpinner();
		waitOnElement("cssSelector", compExcerpt);
		inputText("cssSelector", compExcerpt, excerpt);	
		marketLanguages(markets);
		scrollDown("xpath",composeTitle);
		waitOnElement("xpath",composeTitle);
		composeText("xpath",composeTitle,NewsTitle);		
		composeText("xpath",composeBody,composeBodyText);
		waitOnElement("xpath",composeTitle);	
		subscriptionPlanSlection(Subscription);		
		uploadFile("Image",newsThumb);
		selectPublishImmd(imdPublishYesOrNull);
		//isNewsSelection(isNewsYesorNull);	
		isHighLighted(isHighLightYesorNull);		
		publishOrDraftSelect(publishYesOrNull);	
		confirmationMessage(compNewsToasterText);
		Thread.sleep(7000);
		waitOnSpinner();	
		waitOnSpinner();					
		waitOnElement("cssSelector", panelTitle);	
		waitOnElement("cssSelector", panelTitle);
		WebElement title = driver().findElement(By.cssSelector(panelTitle));
		String acttitle = title.getText().trim();
			if(!(acttitle==null)|| (acttitle==exptitleInWV)||(acttitle==titleIntupper)|| (acttitle==exptitle)){
				System.out.println(acttitle+ " News created succesfully in WV");
			}else {
				Assert.assertEquals(acttitle, exptitle);
			}			
  		}
  	
  	public void selectPublishImmd(String imdPublishYesOrNull) throws Exception, Exception{
		logInfo("Entered into selectPublishImmd() method");
		waitOnElement("cssSelector", compPublishArticle);
		if (imdPublishYesOrNull==null){
			    int FutueDate=2;
				String futDate = getDate(FutueDate, "MM/dd/yyyy");				
				inputTextClear("cssSelector", compPublishDate);
				inputText("cssSelector", compPublishDate, futDate);	
				clickOnElement("cssSelector",compPublishClock);
				Thread.sleep(1000);
				clickOnElement("cssSelector",compPublishClock);				
			
		}else if(imdPublishYesOrNull=="Yes"){
		selectRadioOrCheckbox("cssSelector", compPublishArticle);	
		}
	}
	
	
	public void isNewsSelection(String isNewsYesorNull) throws  InterruptedException{
		logInfo("Entered into isNewsSelection() method");
		WebElement news = driver().findElement(By.cssSelector(compIsNews));	
		if(isNewsYesorNull== null){
			if(news.isSelected()){
				news.click();	
				System.out.println("nothing is seected");				
				}				
			}else if (isNewsYesorNull=="Yes"){				
				if(!news.isSelected()){
					news.click();		
					}
			}
	}
	
	public void isHighLighted(String isHighLightYesorNull) throws  InterruptedException{
		logInfo("Entered into isHighLighted() method");
		WebElement hl = driver().findElement(By.cssSelector(compIsHighLights));	
		if(isHighLightYesorNull== null){
			if(hl.isSelected()){
				hl.click();	
				System.out.println("nothing is seected");				
				}				
			}else if (isHighLightYesorNull=="Yes"){				
				if(!hl.isSelected()){
					hl.click();		
					}
			}
	}
	
	public void publishOrDraftSelect (String publishYesOrNo) throws Exception{
		logInfo("Entered into publishOrDraftSelect() method");
		waitOnElement("cssSelector", compPublish);
		waitOnElement("cssSelector", compPublish);
		scrollDown("cssSelector", compPublish);
		Thread.sleep(3000);
		if(publishYesOrNo =="Yes"){
			clickOnButton("cssSelector", compPublish);				
		}else if(publishYesOrNo==null){
			waitOnElement("cssSelector", compExcerpt);			
			scrollDown("cssSelector", saveAsDraft);		
			clickOnButton("cssSelector", saveAsDraft);
		}
		waitOnElement("cssSelector", spinner);		
		waitOnElement("cssSelector", spinner);
	  }	
  	
  	public void modifyUsersSubscription(String firstName , String lastName,String consultantid, String subscription ) throws Exception{
  		logInfo("Entered into modifyUsersSubscription() method");  	                                 
  	    um.go2Users(); 
  	    waitOnElement("cssSelector", btnAdvancedSearch);
  	    clickOnElement("cssSelector", btnAdvancedSearch);
  	    waitOnElement("cssSelector", inputAdvConsultantID);
  	    inputTextClear("cssSelector", inputAdvFirstName);
  	    inputText("cssSelector", inputAdvFirstName, firstName);   
  	    inputTextClear("cssSelector", inputAdvLastName);
  	    inputTextClear("cssSelector", inputAdvConsultantID);
  	    inputText("cssSelector", inputAdvLastName, lastName);	      
  	    inputText("cssSelector", inputAdvConsultantID, consultantid);
  	    waitOnElement("xpath", btnAdvSubmit);	
  	    clickOnElement("xpath", btnAdvSubmit);  	    
  	    waitOnElement("cssSelector", footerMessage);  
  	    boolean isUserFound = um.verifyUserPresentWithFname(firstName);
  	    if(isUserFound){  
  	    	editUserWithFName(firstName);
  	 		waitOnElement("linkText", "Manage Subscription");
  	 		clickOnLink("linkText", "Manage Subscription");  	    	
  	    	bm.changeSubscriptionPlan4User(subscription); 
  	    }else {
  	    	Assert.assertTrue(isUserFound, firstName+" user is not found");
  	    	}  	 	
  	  	}
  	  	
  	  	
  	  	public void editUserWithFName(String fName) throws  Exception {
  	  	logInfo("Entered into editUserWithFName() method");  	 
  			waitOnElement("xpath", tblAdminUsers); 
  			WebElement tblUsers = driver().findElement(By.xpath(tblAdminUsers));
  			List allUsers = tblUsers.findElements(By.tagName("tr"));  		
  			boolean isUserFound = false;  			
  				for (int i = 1; i <= allUsers.size(); i++) {
  					waitOnElement("xpath", nameBfr + i + nameAft);
  					WebElement x = driver().findElement(By.xpath(nameBfr + i + nameAft));
  					waitOnElement("xpath", nameBfr + i + nameAft); 
  					String name = x.getText().trim();  					
  					if (name.equalsIgnoreCase(fName)) {
  						isUserFound = true;					
  						x.click();
  						break;
  					}
  				}
  			if (isUserFound == false) {
  				Assert.assertTrue(isUserFound, fName + "User is not found");
  			}
  		}
   	
  	public void verifyAdditionalInformation(String pdfFileName) throws Exception{
  		logInfo("Entered into verifyAdditionalInformation() method");
  		waitOnElement("cssSelector", newsInfo);
  		Thread.sleep(2000);
  		waitOnElement("cssSelector", compEdit);
  		List <WebElement> addInfo = driver().findElements(By.cssSelector(newsInfo));  		
  		int infoSize = addInfo.size();
  		String addInform = Integer.toString(infoSize);  		
  		boolean isfilePresent = false;
  		if (addInfo.size()==0){  			
  			Assert.assertEquals(addInform, "expected Additional files");				
  			} else {  				
  				for (WebElement ai : addInfo){
  					if (ai.getText().trim().contains(pdfFileName)){
  						isfilePresent = true;
  					}  				
  			}  			
  		} if (isfilePresent==false){
  			Assert.assertTrue(isfilePresent, pdfFileName + " file is not present");
  		}
  	}
  	    
  	  public void createNewsAlertMessages(String NewsTitle , String Subscription ) throws Exception{
  		logInfo("Entered into createNewsAlertMessages() method");  	  		
  		String highlights =  "Is News and Is Highlights cannot be blank!";  
  		String titleAlert= "Content cannot be blank,Title cannot be blank";
  		String titleAlert2= "Title cannot be blank";
  		String contAlert  = "Content cannot be blank";  		
		selectAddCompanyNews();
		clickOnPublishButton();
		confirmationMessage(highlights);
		selectIsHighLight();
		clickOnPublishButton();
		confirmationMessage(titleAlert);
		Thread.sleep(6000);
		enterTitle(NewsTitle);
		clickOnPublishButton();
		confirmationMessage(contAlert);
		Thread.sleep(7000);
		inputTextClear("cssSelector", compTitle);
		enterExcerpt(excerpt);
		composeText("xpath",composeBody,composeBodyText);		
		clickOnPublishButton();	
		confirmationMessage(titleAlert2);
		Thread.sleep(7000);
		enterTitle(NewsTitle);
		validateArchievedDate(-2);
		selectArchievedDate(2);		
		clickOnPublishButton();	
		confirmationMessage(compNewsToasterText);    		  
  		  
  	  }
  	  
  	public void editNewsInRecentPosts(String newsTitle){
  		logInfo("Entered into editNewsInRecentPosts() method");     		
   		boolean isRecentPresent =false;
   		List <WebElement> li = driver().findElements(By.cssSelector(recPostList));
   		for (WebElement lis :li ){
   			if(lis.getText().equalsIgnoreCase(newsTitle)){
   				isRecentPresent = true;
   				lis.click();
   				break;  				
   			}	   			
   		}if (isRecentPresent==false){
   			Assert.assertTrue(isRecentPresent, newsTitle + " - News title is not present");
   		}   		
   	}
  	
	
	public void newsfieldValidate(String ranker) throws Exception{	
		logInfo("Entered into newsfieldValidate() method");  	 
		 String newsTitle = "validateTitlewithmaxcharaters"+TestBase.generateRandomString()+" 1234567890abcdefghij1234567890abcdefghij1234567850";
		 String[] reptitle = StringUtils.split(newsTitle," ");
		 String splitedTitle = reptitle[0];
		selectAddCompanyNews();
		enterTitle(newsTitle);
		enterExcerpt(excerpt);
		composeText("xpath",composeBody,composeBodyText);
		rankDefinition(ranker);
		marketLanguages(languageList);
		subscriptionPlanSlection(subscripList);
		selectIsNews();		
		selectPublishImmediately();		
		clickOnPublishButton();
		confirmationMessage(compNewsToasterText);
		
	}
   
   
	public void validateNewsTitleSize(String title) throws Exception{	
		logInfo("Entered into validateNewsTitleSize(String title) method");
		boolean isNews = false;
		List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
		for (WebElement list : newsList){
			if(list.getText().contains(title)){	
				isNews = true;
				list.click();
				WebElement titSize = driver().findElement(By.cssSelector(titleNews));
				int titSiz = titSize.getText().length();
				String AcutTitleSize = Integer.toString(titSiz);				
				Assert.assertEquals(AcutTitleSize, "50");
				break;
			}
		}if(isNews==false){
			Assert.assertTrue(isNews, title +" is not found");
		}
	}
	
	// eidted news will be updated with new file
	public void updateNewsWithFile() throws Exception{
		logInfo("Entered into updateNewsWithFile() method");		
		 uploadFile("PDF",newsPost);	
		 clickOnPublishButton();
		 confirmationMessage(companyNewUpdated);
		
	}
  	
	public void updateNewsWithSubscription(String newsTitle, String[] subscription) throws Exception{		   
		logInfo("Entered into updateNewsWithSubscription() method");  	 
		 navigateCompanyNews();
		 isNewsTitlePresent(newsTitle);			  	  
		 editNews(newsTitle);
		 Thread.sleep(3000);
		 waitOnElement("cssSelector", compEdit);
		 clickOnElement("cssSelector", compEdit);		
		 subscriptionSlectionOfEdited(subscription);
		 clickOnPublishButton();
		 confirmationMessage(companyNewUpdated);		 
	}
	
	public void updateNewsWithMarket(String newsTitle, String[] languages) throws Exception{		   
		logInfo("Entered into updateNewsWithMarket() method");  	 
		 navigateCompanyNews();
		 navigateCompanyNews();
		 isNewsTitlePresent(newsTitle);			  	  
		 editNews(newsTitle);
		 waitOnElement("cssSelector", compEdit);
		 clickOnElement("cssSelector", compEdit);
		 updateMaketOfEditedNews(languages);		 
		 clickOnPublishButton();		
		 confirmationMessage(companyNewUpdated);		 
	}
	
	public void updateMaketOfEditedNews(String[] markets) throws Exception{
		logInfo("Entered into updateMaketOfEditedNews() method");  	 
		try{
			waitOnElement("cssSelector", compLang);
			clickOnElement("cssSelector", compLang);			
			WebElement marketAll = driver().findElement(By.cssSelector(markAll));
			if (!(marketAll.isSelected())){
				marketAll.click();
				waitOnElement("cssSelector", markAll);
				marketAll.click();			
				}
			boolean ismarket = false;
			int subcription = markets.length;			
			List <WebElement> mar = driver().findElements(By.cssSelector(compLangDD));			
			for (WebElement mars : mar){	
				for(int j=0; j<=subcription-1;j++){				
				if (mars.getText().equalsIgnoreCase(markets[j])){
					ismarket = true;
					if(!(mars.isSelected())){
					mars.click();
					break;
						}
				    }	
				}
			}if(ismarket==false){
				Assert.assertTrue(ismarket, markets + "- market lang is not found");
			}
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select markets.");
		}				
	}
	
	public void updateNewsWithPresentDay(String newsTitle,int date) throws Exception{	
		logInfo("Entered into updateNewsWithPresentDay() method");  	 
	     navigateCompanyNews();
		 isNewsTitlePresent(newsTitle);			  	  
		 editNews(newsTitle);
		 Thread.sleep(3000);
		 waitOnElement("cssSelector", compEdit);
		 clickOnElement("cssSelector", compEdit);		
		 enterPublishDate(date);	
		 clickOnPublishButton();
		 confirmationMessage(companyNewUpdated);		 
	}
	
	public void updateNewsWithTags(String newsTitle,String tags) throws Exception{	
		logInfo("Entered into updateNewsWithTags() method");  	 
	     navigateCompanyNews();
		 isNewsTitlePresent(newsTitle);			  	  
		 editNews(newsTitle);
		 Thread.sleep(3000);
		 waitOnElement("cssSelector", compEdit);	
		 waitOnElement("cssSelector", compTagList);
		 inputText("cssSelector", compTagList,tags);
		 clickOnElement("cssSelector", compSubPlan);
		 clickOnElement("cssSelector", compSubPlan);
		 clickOnPublishButton();
		 confirmationMessage(companyNewUpdated);		 
	}
	
public void  verifyTagsofNews(String newsTag) throws Exception{
   		logInfo("Entered into verifyTagsofNews() method");
   		String Text = "Tags";
   		waitOnSpinner();
   		Thread.sleep(2000);
   		WebElement rec = driver().findElement(By.cssSelector(tagsInWid));
   		Assert.assertEquals(rec.getText().trim(), Text);
   		boolean isTagPresent =false;
   		waitOnElement("cssSelector", tagList);
   		List <WebElement> li = driver().findElements(By.cssSelector(tagList));
   		for (WebElement lis :li ){
   			System.out.println(lis.getText());   			
   			if(lis.getText().equalsIgnoreCase(newsTag)){
   				isTagPresent = true;
   				break;  				
   			}	   			
   		}if (isTagPresent==false){
   			Assert.assertTrue(isTagPresent, newsTag + " - News tags is not present");
   		}
	
   		
   	}

	public void searchNewsOrTag(String search) throws Exception {
		logInfo("Entered into searchNewsOrTag() method");
		waitOnSpinner();
		Thread.sleep(2000);
		scrollDown("cssSelector", compSearch);
		clickOnElement("cssSelector", compSearch);
		inputTextClear("cssSelector", compSearch);
		inputText("cssSelector", compSearch, search);
		inputTextClear("cssSelector", compSearch);
		inputText("cssSelector", compSearch, search);
		submitElement("cssSelector", compSearch);
	}
	
	public void selectTypeOfView(String viewType) throws Exception {		
		   logInfo("Entered into selectTypeOfView method");		
		    waitOnElement("cssSelector", resListViewAdmin);
		    Thread.sleep(4000);
		    scrollDown("cssSelector", resGridViewIcon);
		    if(viewType=="Grid") {
		    	clickOnElement("cssSelector", resGridViewIcon);		    	
		    	
		    }else if(viewType=="List") {
		    	clickOnElement("cssSelector", resListViewAdmin);		    	
		    	Thread.sleep(2000);
		    }else {
		    	Assert.assertNotNull(viewType);
		    }		
	}
	
	
	public boolean verifyNewsIsPresent(String title) throws Exception{
		logInfo("Entered into verifyNewsIsPresent method");		
	    waitOnElement("cssSelector", companyNewsListTitle);
	    Thread.sleep(4000);
	    List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
		boolean isTitlePresent =false;			
		for(WebElement newsLists : newsList ){
			String titles = newsLists.getText().trim();		
			if(titles.equalsIgnoreCase(title)){				
				isTitlePresent = true;					
				break;										
				}}
			if(isTitlePresent==false){
					Assert.assertTrue(isTitlePresent,title+" is not available in the grid" );			
					
				}
			return isTitlePresent;		
			}	
	
	public boolean verifyNewsIsListView(String title) throws Exception{
		logInfo("Entered into verifyNewsIsListView method");		
	    waitOnElement("cssSelector", newsListTitleInList);
	    Thread.sleep(4000);
	    List<WebElement> newsList =  driver().findElements(By.cssSelector(newsListTitleInList));
		boolean isTitlePresent =false;			
		for(WebElement newsLists : newsList ){
			String titles = newsLists.getText().trim();			
			if(titles.equalsIgnoreCase(title)){				
				isTitlePresent = true;					
				break;										
				}}
			if(isTitlePresent==false){
					Assert.assertTrue(isTitlePresent,title+" is not available in the grid" );
				}
			return isTitlePresent;		
			}	
	
	//HomePage-  verifies News in Recent Post on left Panel
   	public boolean verifyNewsInRecentPosts(String newsTitle) throws Exception{
   		logInfo("Entered into verifyNewsInRecentPosts method");	
   		String Text = "Recent Posts";
   		waitOnSpinner();
   		waitOnElement("cssSelector", recent);
   		WebElement rec = driver().findElement(By.cssSelector(recent));
   		Assert.assertEquals(rec.getText().trim(), Text);
   		boolean isRecentPresent =false;
   		waitOnElement("cssSelector", recPostList);
   		List <WebElement> li = driver().findElements(By.cssSelector(recPostList));   		
   		for (WebElement lis :li ){   		
   			if(lis.getText().equalsIgnoreCase(newsTitle)){
   				isRecentPresent = true;
   				break;  				
   			}	   			
   		}if (isRecentPresent==false){
   			Assert.assertTrue(isRecentPresent, newsTitle + " - News title is not present");
   		}
		return isRecentPresent;   		
   		
   	}
   	
	public void selectNewsFromRecentPosts(String newsTitle) throws Exception{
   		logInfo("Entered into selectNewsFromRecentPosts() method");	
   		String Text = "Recent Posts";
   		waitOnSpinner();
   		Thread.sleep(2000);
   		WebElement rec = driver().findElement(By.cssSelector(recent));
   		Assert.assertEquals(rec.getText().trim(), Text);
   		boolean isRecentPresent =false;
   		List <WebElement> li = driver().findElements(By.cssSelector(recPostList));   		
   		for (WebElement lis :li ){   			
   			if(lis.getText().equalsIgnoreCase(newsTitle)){
   				isRecentPresent = true;
   				lis.click();
   				break;  				
   			}	   			
   		}if (isRecentPresent==false){
   			Assert.assertTrue(isRecentPresent, newsTitle + " - News title is not present");
   		}
				
   		
   	}
   	
   	public void selectAttachResource() throws Exception {
   		logInfo("Entered into selectAttachResource method");	
   		waitOnElement("cssSelector", attachRes);
   		scrollDown("cssSelector", attachRes);
   		clickOnElement("cssSelector", attachRes);
   		
   	}
   	
   	
   	public void attachRLAsserts(String category, String resource) throws Exception {
   	logInfo("Entered into attachRLAsserts method");	
   	String expTitle = "Resource";
   	waitOnSpinner();
   	waitOnElement("cssSelector", rlAttachTitle);
   	WebElement title = driver().findElement(By.cssSelector(rlAttachTitle));
   	String actTitle = title.getText().trim();   
   	Assert.assertEquals(actTitle, expTitle);
   	boolean isCatFound = false;
   	List <WebElement> catList = driver().findElements(By.cssSelector(rlList));
   	for (int i=1; i<=catList.size(); i++) {
   		WebElement cats = driver().findElement(By.cssSelector(rlListBfr+i+eCardParentCatAft));
   		String actCat = cats.getText().trim();   		
   		if (actCat.equalsIgnoreCase(category)) {
   			isCatFound =true;
   			waitOnElement("cssSelector", rlListBfr+i+eCardParentCatPlusIcon);
   			WebElement catNameSelect = driver().findElement(By.cssSelector(rlListBfr+i+eCardParentCatPlusIcon));
   			String cathref = catNameSelect.getAttribute("href");			
			String expected ="/crm/resource_library";	
			if(cathref.contains(expected)){
				catNameSelect.click();
				catNameSelect.click();		
				Thread.sleep(7000);				
		    	waitOnElement("cssSelector", rlRes);
				
			}else {
				WebElement catNameSelectPlus = driver().findElement(By.cssSelector(rlListBfr+i+eCardParentCatPlusIcon2));
				catNameSelectPlus.click();
				Thread.sleep(3000);		
			}
			selectAssertsUnderResource(resource);
			break;  			
   		}
   		
   		
   	}if(isCatFound==false) {
   		Assert.assertTrue(isCatFound, category+ " - category is not found");
   	}
   	
		   
   	}
   	
   	
   	public void selectAssertsUnderResource(String expRes) throws Exception {
   		logInfo("Entered into selectAssertsUnderResource() method");
   		boolean isFoundRes = false;
   		waitOnElement("cssSelector", rlRes);   		
   		List<WebElement> res = driver().findElements(By.cssSelector(rlRes));
   		for (int i=2; i<=res.size()+1; i++) {
   			WebElement resList = driver().findElement(By.cssSelector(rlResBfr+i+rlResAfr));
   			String resources = resList.getText().trim();   		
   			if (resources.contains(expRes)) {
   				isFoundRes=true;
   				WebElement resListPlus = driver().findElement(By.cssSelector(rlResBfr+i+rlResAfr+ "> a"));
   				resListPlus.click();
   				Thread.sleep(2000);
   				waitOnElement("xpath", assertCheckboxes);
   				List <WebElement> checks = driver().findElements(By.xpath(assertCheckboxes));
   				for(WebElement cks : checks) {
   					if(!cks.isSelected()) {
   						cks.click();
   					}
   					
   				}waitOnElement("cssSelector", deleteConfirmButton);
   				clickOnButton("cssSelector", deleteConfirmButton);
   				Thread.sleep(2000);
   				break;
   			}
   			
   			
   		}if (isFoundRes==false) {
   			Assert.assertTrue(isFoundRes, expRes + " - resource is not found");
   		}
   		
   		
   	}
 	
   	
   	public void publishImdWithAttachment() throws Exception {
   		logInfo("Entered into publishImdWithAttachment() method");   
   	     String exptitle = null;
   		 String yes = "Yes";
   		 selectPublishImmd(yes);
		 publishOrDraftSelect(yes);	
		 confirmationMessage(companyNewUpdated);   		
   		 Thread.sleep(9000);		
		 waitOnElement("cssSelector", panelTitle);		
		 WebElement title = driver().findElement(By.cssSelector(panelTitle));
		 String acttitle = title.getText().trim();		
		 if(!(acttitle==null)|| (acttitle==exptitleInWV)||(acttitle==titleIntupper)|| (acttitle==exptitle)){
				System.out.println(acttitle+ " News created succesfully in WV");
			
			}else {
				Assert.assertEquals(acttitle, exptitle);
			}	
   	}
   	
   	public void verifyAdditionalResources(int noOfResources) throws Exception {
   		logInfo("Entered into verifyAdditionalResources() method");  
   		String expTitle = "Additional Resources";
   		waitOnElement("xpath", addRes);
   		WebElement title = driver().findElement(By.xpath(addRes));
   		String resTitle= title.getText().trim();   		
   		Assert.assertEquals(resTitle, expTitle);
   		List <WebElement> attach = driver().findElements(By.xpath(addResDetls));
   		int totAttach = attach.size()/3;   	
   		if(totAttach<=noOfResources) {
   			Assert.assertEquals(totAttach, noOfResources);   			
   		}else {
   			Assert.assertNotSame(totAttach, noOfResources);
   		}
   		
   	}
   	
   	public void verifyNewsTitle(String expTitle) throws Exception {
   		logInfo("Entered into verifyNewsTitle() method");   		
   		waitOnElement("xpath", newsTitle);
   		WebElement title = driver().findElement(By.xpath(newsTitle));
   		String newsTit= title.getText().trim();   		
   		Assert.assertEquals(newsTit, expTitle);   		
   	}

}