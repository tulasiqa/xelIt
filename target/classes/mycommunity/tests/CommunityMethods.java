package vibe.mycommunity.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import common.EnvProperties;
import common.SocialNetWorksMethods;
import common.TestBase;


public class CommunityMethods extends TestBase{
	
	EnvProperties prop = new EnvProperties();
	SocialNetWorksMethods sm = new SocialNetWorksMethods();
	
	public void nav2Community() throws Exception {
		logInfo("inside navigate2CommunityPage() method");
		System.out.println("inside navigate2CommunityPage() method");
		driver().navigate().to(appUrl + "/community/wall");
	}
	
	public void go2AbuseReports() throws Exception {
		logInfo("inside go2AbuseReports() method");
		System.out.println("inside go2AbuseReports() method");
		driver().navigate().to(appUrl + "/community/abuse_reports");
	}
		
	public void addBlogPost(String postName) throws Exception{
		logInfo("inside addBlogPost() method..");
		nav2Community();		
		clickOnLink("linkText", "Add Blog Post");
		waitOnElement("cssSelector",inputAddPostTitle);
		inputText("cssSelector",inputAddPostTitle,postName); 		
		composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new blog");
		inputText("cssSelector",inputAddPostExcerpt,addPostExcerpt_text);
		selectFromDropdown("cssSelector",drpdnAddPostVisibility,"byVisibleText",visibility_text_community);
		selectRadioOrCheckbox("cssSelector", chkPublishPost);
		clickOnButton("cssSelector",btnCreatePost);
		String url = driver().getCurrentUrl();
		if(url.contains("idlife-stage")){
			String idLife =  "//*[contains(@id,'rules_web_alert_modal_')]/div/div[1]/div/div/div[3]/button";
			waitOnElement("xpath",idLife);
			clickOnElement("xpath",idLife);
		}		
	}

	public boolean verifyPostIsPresent(String postName) throws Exception {
		logInfo("inside verifyPostisPresent() method.");
		nav2Community();	
		waitOnElement("xpath",allStat);	
		List<WebElement> allPhotos = driver().findElements(By.xpath(allStat));		
		boolean isMatchFound = false;
		for(WebElement x : allPhotos){
			String actPostName = x.getText().trim();
			System.out.println("actPostName "+ actPostName);
			if (actPostName.contains(postName)){ 				
				isMatchFound = true;
				break;
			}
		}		
		return isMatchFound;
	}

	public void postNotToBePresent(String postName) throws Exception {
		logInfo("inside postNotToBePresent() method.");		
		nav2Community();
		waitOnElement("xpath",paneCommunityWall);	
		List<WebElement> allPhotos = driver().findElements(By.xpath(allStat));	
		System.out.println(allPhotos.size());
		boolean isMatchFound = true;
		for (int i =1; i<=allPhotos.size()-1; i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+afterPost));
			String actPostName = x.getText().trim();
			System.out.println(x.getText().trim());
			if (actPostName.equalsIgnoreCase(postName)){ 				
				isMatchFound = false;
				Assert.assertTrue(isMatchFound, postName + " is still present");
				break;
				}
		}		
		if(isMatchFound==true){		
			Assert.assertNotSame(isMatchFound, postName + " community post match not found.");
		}
		
	}
	
	public void deleteCommunityPost(String postName) throws Exception {
		logInfo("inside deleteCommunityPost() method.");
		nav2Community();
		List <WebElement> allVideos = driver().findElements(By.xpath(paneFeaturedVideos));
		int totalVideos = allVideos.size();
		logInfo("Total posts = " +totalVideos);			
		boolean isMatchFound = false;			
		List<WebElement> allMessages= driver().findElements(By.cssSelector(commList));
		if(totalVideos >0){				
			for (WebElement all : allMessages){
				String actPost = all.getText().trim();
				System.out.println(" name = " +actPost);
				if(actPost.contains(postName)){
					isMatchFound = true;
					System.out.println(" match found = " +actPost);
					all.click();
					try {
						waitOnElement("linkText","Delete");
						WebElement delete = driver().findElement(By.linkText("Delete"));
						delete.click();
						confirmOK();
						break;
					 }
					 catch (Exception e) {
						 Assert.assertFalse(true,"Unable to find the delete link in the workspace.");
					 }
					
				}
			}
		}
		
		if(isMatchFound==false){
			
			Assert.assertTrue(isMatchFound, postName + " community post match not found to delete.");
		}
	}
	
		
	public void postStatus(String statusMessage) throws Exception {
		logInfo("inside postStatus() method.");
		nav2Community();		
		verifyElementPresent("cssSelector",inputCommunityStatus);
		inputText("cssSelector",inputCommunityStatus,statusMessage);
		clickOnElement("xpath",btnCommunityPost);
		
	}	
	
	public boolean verifyStatusPostIsPresent(String statusMessage) throws Exception {
		logInfo("inside verifyStatusPostIsPresent() method.");
		nav2Community();
		waitOnElement("xpath",paneCommunityWall);
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		waitOnElement("xpath",paneCommunityWallStatus);
		List<WebElement> allStatus = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allStatus.size());	
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";
		for(int i=1;i<=allStatus.size();i++){
			waitOnElement("xpath",before_name+i+after_name);
			WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
			System.out.println(e.getText().trim());
			String status = e.getText().trim();
				if(status.contains(statusMessage)){
					isMatchFound = true;
					logInfo(statusMessage + " status post match found.");
					break;
				}
			}
		
		if(isMatchFound==false){
			logInfo(statusMessage + " status post match not found.");
			Assert.assertTrue(isMatchFound, statusMessage + " status post match not found in community wall.");
		}
		return isMatchFound;
	}
	
	
	public void setStatusForPosts(String postName , String status) throws Exception {
		logInfo("Entered into  setStatusForPosts() method.");		
		nav2Community();
		waitOnElement("xpath",allStat);		
		List<WebElement> allMessages = driver().findElements(By.xpath(allStat));
		System.out.println(allMessages.size());
		boolean isMatchFound = false;			
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]/a";
		
		for(int i=1;i<=allMessages.size()-1;i++){			
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();	
			System.out.println(name);
			if(name.equalsIgnoreCase(postName)){				
				isMatchFound = true;
				boolean isStatusFound = false;
				waitOnElement("xpath",before_name+i+allsStatus);
				List<WebElement> all = driver().findElements(By.xpath(before_name+i+allsStatus));
				System.out.println("All status "+ all.size());
				for (int j=1; j<=all.size(); j++){
					waitOnElement("xpath", allStat);
					WebElement stat = driver().findElement(By.xpath(before_name+i+statusBfr+j+statusAft)) ;
					String actStatus = stat.getText().trim();
					System.out.println("actStatus "+ actStatus);
					if(actStatus.equalsIgnoreCase(status)){
						isStatusFound = true;
						stat.click();
						Thread.sleep(5000);
						break;	
					}
				}
				if(!isStatusFound){
					Assert.assertTrue(isStatusFound, status + " Status is not present under post - "+ postName);
				}
				break;
			}
			
		}
		if(!isMatchFound){
			Assert.assertTrue(isMatchFound, postName + " post is not present.");
		}
	}
	
	
	public void postActivityLinks(String linkStatus) throws Exception, InterruptedException{
		logInfo("inside postActivityLinks() method.");
		List<WebElement> links = driver().findElements(By.xpath("//div[@class='activities-stream']/div[1]/div[1]/div[2]/span[1]/span"));
		int  allLInks = links.size();
		System.out.println("allLInks" + allLInks);
		String beforePath = "//div[@class='activities-stream']/div[1]/div[1]/div[2]/span[1]/span[";
		String afterPath = "]";
		for(int i=1;i<=allLInks;i++){			
			WebElement e = driver().findElement(By.xpath(beforePath+i+afterPath));
			String expectedLinkName = e.getText().trim();	
			System.out.println("expectedLinkName" + expectedLinkName);
			if(expectedLinkName.equalsIgnoreCase(linkStatus)){
				e.click();
				Thread.sleep(5000);				
				break;
			}
			 Assert.assertEquals(expectedLinkName, linkStatus);
		}
		
	}
	
	public boolean verifyPostActivityLinks( String posName, String linkStatus) throws Exception{
		logInfo("inside verifyPostActivityLinks() method.");	
		nav2Community();
		boolean isPostPresent= false;   
		waitOnElement("xpath",allStat);		
		List<WebElement> allMessages = driver().findElements(By.xpath(allStat));
		System.out.println(allMessages.size());
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]/a";
		
		for(int i=1;i<=allMessages.size()-1;i++){			
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();	
			System.out.println(name);
			if(name.equalsIgnoreCase(posName)){				

				 isPostPresent=true;
				 List <WebElement > status = driver().findElements(By.cssSelector(postNameBfr+i+allPostStatus));						 
					int allLInks= status.size();	
					boolean isLinkPresents = false;
					for(int j=1;j<=allLInks;j++){
						WebElement st = driver().findElement(By.cssSelector(postNameBfr+i+poststatusBfr+j+")"));
						if (st.getText().equalsIgnoreCase(linkStatus)){									
							isLinkPresents =true;		
							System.out.println("st.getText()" + st.getText());
							break;								
						}
					}
					if (isLinkPresents==false){
						Assert.assertTrue(isLinkPresents,linkStatus+ " is not present" );
					}	 
					break;
			}
		}
		if (!isPostPresent){
			Assert.assertTrue(isPostPresent, posName+ " post is not present");
		}
		return isPostPresent;	
	}
	
	/*public boolean verifyActivityCount( String posName, String activity) throws Exception{
		logInfo("inside verifyActivityCount() method.");	
		System.out.println("inside verifyActivityCount() method.");	
		boolean isPostPresent= false;  
		boolean isCountPresent = false;
		nav2Community();
		waitOnElement("xpath",allStat);		
		List<WebElement> allMessages = driver().findElements(By.xpath(allStat));
		System.out.println(allMessages.size());
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";
		
		for(int i=1;i<=allMessages.size()-1;i++){			
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();	
			System.out.println(name);
			if(name.contains(posName)){				
				 isPostPresent=true;
				 
				 if(activity.equalsIgnoreCase("Likes")){
					 String before_like_count = "div.activities-stream > div:nth-child(";
					 String after_like_count = ") > div.media-body > div.community_activity_action > span:nth-child(2) >span:nth-child(2)>span";
					 Thread.sleep(3000);
					 waitOnElement("cssSelector",before_like_count+i+after_like_count);
					 WebElement likesCount = driver().findElement(By.cssSelector(before_like_count+i+after_like_count));
					 if(likesCount.getText().equalsIgnoreCase("1")){
						 isCountPresent = true;
						 break;
					 }
				 }
				 else if(activity.equalsIgnoreCase("Comments")){
					 String before_comment_count = "div.activities-stream > div:nth-child(";
					 String after_comment_count = ") > div.media-body > div.community_activity_action > span:nth-child(2) >span:nth-child(1)";
					 Thread.sleep(3000);
					 waitOnElement("cssSelector",before_comment_count+i+after_comment_count);
					 WebElement commentsCount = driver().findElement(By.cssSelector(before_comment_count+i+after_comment_count));
					 if(commentsCount.getText().contains("1")){
						 isCountPresent = true;
						 break;
					 }
				 }
		
			  break;
			}
		}
		if(!isPostPresent){
			Assert.assertTrue(isPostPresent,"Unable to find a post "+posName+" on community wall");
		}
		if(!isCountPresent){
			Assert.assertTrue(isCountPresent,"Unable to find the activity count "+activity);
		}
		
		return isCountPresent;	
	}*/
	
	
	public boolean verifyActivityCount( String posName, String activity) throws Exception{
		logInfo("inside verifyActivityCount() method.");	
		System.out.println("inside verifyActivityCount() method.");	
		boolean isPostPresent= false;  
		boolean isCountPresent = false;
		nav2Community();
		waitOnElement("xpath",allStat);		
		List<WebElement> allMessages = driver().findElements(By.xpath(allStat));
		System.out.println(allMessages.size());
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]/a";
		
		for(int i=1;i<=allMessages.size()-1;i++){			
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();	
			System.out.println(name);
			if(name.equalsIgnoreCase(posName)){				
				 isPostPresent=true;
				 
				 if(activity.equalsIgnoreCase("Likes")){
					 String before_like_count = "div.activities-stream > div:nth-child(";
					 String after_like_count = ") > div.media-body > div.community_activity_action > span:nth-child(2) >span:nth-child(2)>span";
					 Thread.sleep(3000);
					 waitOnElement("cssSelector",before_like_count+i+after_like_count);
					 WebElement likesCount = driver().findElement(By.cssSelector(before_like_count+i+after_like_count));
					 if(likesCount.getText().equalsIgnoreCase("1")){
						 isCountPresent = true;
						 break;
					 }
				 }
				 else if(activity.equalsIgnoreCase("Comments")){
					 String before_comment_count = "div.activities-stream > div:nth-child(";
					 String after_comment_count = ") > div.media-body > div.community_activity_action > span:nth-child(2) >span:nth-child(1)";
					 Thread.sleep(3000);
					 waitOnElement("cssSelector",before_comment_count+i+after_comment_count);
					 WebElement commentsCount = driver().findElement(By.cssSelector(before_comment_count+i+after_comment_count));
					 if(commentsCount.getText().contains("1")){
						 isCountPresent = true;
						 break;
					 }
				 }
		
			  break;
			}
		}
		if(!isPostPresent){
			Assert.assertTrue(isPostPresent,"Unable to find a post "+posName+" on community wall");
		}
		if(!isCountPresent){
			Assert.assertTrue(isCountPresent,"Unable to find the activity count "+activity);
		}
		
		return isCountPresent;	
	}
	
	public boolean verifyStatusActivityLinks( String posName, String linkStatus) throws Exception{
		logInfo("inside verifyStatusActivityLinks() method.");	
		System.out.println("inside verifyStatusActivityLinks() method.");	
		nav2Community();
		boolean isPostPresent= false;   
		waitOnElement("xpath",allStat);		
		List<WebElement> allMessages = driver().findElements(By.xpath(allStat));
		System.out.println(allMessages.size());
	    String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";
		for(int i=1;i<=allMessages.size()-1;i++){			
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();	
			System.out.println(name);
			if(name.equalsIgnoreCase(posName)){				

				 isPostPresent=true;
				 List <WebElement > status = driver().findElements(By.cssSelector(postNameBfr+i+allPostStatus));						 
					int allLInks= status.size();	
					boolean isLinkPresents = false;
					for(int j=1;j<=allLInks;j++){
						WebElement st = driver().findElement(By.cssSelector(postNameBfr+i+poststatusBfr+j+")"));
						if (st.getText().equalsIgnoreCase(linkStatus)){									
							isLinkPresents =true;		
							System.out.println("st.getText()" + st.getText());
							break;								
						}
					}
					if (isLinkPresents==false){
						Assert.assertTrue(isLinkPresents,linkStatus+ " is not present" );
					}	 
					break;
			}
		}
		if (!isPostPresent){
			Assert.assertTrue(isPostPresent, posName+ " post is not present");
		}
		return isPostPresent;	
	}
	
	public boolean verifyStatusActivityCount( String posName, String activity) throws Exception{
		logInfo("inside verifyActivityCount() method.");	
		System.out.println("inside verifyActivityCount() method.");	
		boolean isPostPresent= false;  
		boolean isCountPresent = false;
		nav2Community();
		waitOnElement("xpath",allStat);		
		List<WebElement> allMessages = driver().findElements(By.xpath(allStat));
		System.out.println(allMessages.size());
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";
		
		for(int i=1;i<=allMessages.size()-1;i++){			
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();	
			System.out.println(name);
			if(name.equalsIgnoreCase(posName)){				
				 isPostPresent=true;
				 
				 if(activity.equalsIgnoreCase("Likes")){
					 String before_like_count = "div.activities-stream > div:nth-child(";
					 String after_like_count = ") > div.media-body > div.community_activity_action > span:nth-child(2) >span:nth-child(2)>span";
					 waitOnElement("cssSelector",before_like_count+i+after_like_count);
					 WebElement likesCount = driver().findElement(By.cssSelector(before_like_count+i+after_like_count));
					 if(likesCount.getText().equalsIgnoreCase("1")){
						 isCountPresent = true;
						 break;
					 }
				 }
				 else if(activity.equalsIgnoreCase("Comments")){
					 String before_comment_count = "div.activities-stream > div:nth-child(";
					 String after_comment_count = ") > div.media-body > div.community_activity_action > span:nth-child(2) >span:nth-child(1)";
					 waitOnElement("cssSelector",before_comment_count+i+after_comment_count);
					 WebElement commentsCount = driver().findElement(By.cssSelector(before_comment_count+i+after_comment_count));
					 if(commentsCount.getText().contains("1")){
						 isCountPresent = true;
						 break;
					 }
				 }
		
			  break;
			}
		}
		if(!isPostPresent){
			Assert.assertTrue(isPostPresent,"Unable to find a post "+posName+" on community wall");
		}
		if(!isCountPresent){
			Assert.assertTrue(isCountPresent,"Unable to find the activity count "+activity);
		}
		
		return isCountPresent;	
	}
	
	public void commentPostOnCommunity(String postName, String status ,String comment) throws Exception {
		logInfo("inside commentStatusOnCommunity() method.");
		nav2Community();
		waitOnElement("xpath",paneCommunityWall);
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allMessages.size());				
		System.out.println("Total messages ="+ allMessages.size());
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";
		for(int i=1;i<=allMessages.size();i++){
			waitOnElement("xpath",before_name+i+after_name);
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			System.out.println("name "+name);
			if(name.contains(postName)){				
				isMatchFound = true;				
				boolean isStatusFound = false;
				List<WebElement> all = driver().findElements(By.xpath(before_name+i+allsStatus));
				System.out.println("All status "+ all.size());
				for (int j=1; j<=all.size(); j++){
					waitOnElement("xpath", allStat);
					WebElement stat = driver().findElement(By.xpath(before_name+i+statusBfr+j+statusAft)) ;
					String actStatus = stat.getText().trim();
					System.out.println("actStatus "+ actStatus);
					if(actStatus.equalsIgnoreCase(status)){
						isStatusFound = true;
						stat.click();
						waitOnElement("xpath",before_name+i + "]/div[1]/div[@class='comments']/div/form/div/div/div/textarea[@id='pyr_community_comment_content']");
						inputTextClear("xpath",before_name+i + "]/div[1]/div[@class='comments']/div/form/div/div/div/textarea[@id='pyr_community_comment_content']");
						inputText("xpath",before_name+i + "]/div[1]/div[@class='comments']/div/form/div/div/div/textarea[@id='pyr_community_comment_content']", comment);
						waitOnElement("xpath",before_name+i + "]/div[1]/div[@class='comments']/div/form/div/div/span/input");
						clickOnButton("xpath",before_name+i + "]/div[1]/div[@class='comments']/div/form/div/div/span/input");
						waitOnSpinner();
						break;
						}						
					}
					if(!isStatusFound){
						Assert.assertTrue(isStatusFound, status + " Status is not present under post - "+ postName);
					}
					
					break;		
				}
			
		}
		
	}
	
	public void hideStatusPost(String statusMessage) throws Exception {
		logInfo("inside hideStatusPost() method.");
		nav2Community();		
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allStatus = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allStatus.size());	
		
		String before_hide = "//div[@class='activities-stream']/div[";
		String after_hide = "]/div/div[2]/span[1]/span[3]";   // /a
	
		for(int i=1;i<=allStatus.size();i++){
		WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
		String status = e.getText().trim();
			if(status.matches(statusMessage)){
				isMatchFound = true;
				WebElement hide = driver().findElement(By.xpath(before_hide+i+after_hide));
				hide.click();
				logInfo("clicked on hide button of " +status);
				break;
			}
		}
		
	   if(isMatchFound==false){
			logInfo(statusMessage + " status post match not found in community wall.");
			Assert.assertTrue(isMatchFound, statusMessage + " status post match not found in community wall.");
		}
	}
	
	
	public void addCommunityPhoto(String photoTitle, String visibleSettings) throws Exception {
		logInfo("inside addCommunityPhoto() method");
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Add Photo / Video");
		waitOnElement("xpath",inputPhotoURL);
		inputText("xpath",inputPhotoURL,photoURL_text);
		inputText("xpath",inputPhotoTitle,photoTitle);		
		inputText("xpath",inputPhotoDesc,photoDesc_text);
		inputText("xpath",inputPhotoTags,photoTags_text);
		selectFromDropdown("xpath",drpdnVisibilitySettings,"byVisibleText",visibleSettings);
		waitOnElement("xpath",btnSharePhoto);
		clickOnButton("xpath",btnSharePhoto);
		//waitOnSpinner();
	}
	
	public void addCommunityPhotoFromMachine(String photoTitle, String visibleSettings) throws Exception {
		logInfo("inside addCommunityPhoto() method");
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Add Photo / Video");
		waitOnElement("cssSelector","#pyr_community_photo_photo");
		uploadFile("Image","#pyr_community_photo_photo");
		inputText("xpath",inputPhotoTitle,photoTitle);		
		inputText("xpath",inputPhotoDesc,photoDesc_text);
		inputText("xpath",inputPhotoTags,photoTags_text);
		selectFromDropdown("xpath",drpdnVisibilitySettings,"byVisibleText",visibleSettings);
		waitOnElement("xpath","//*[@id='new_pyr_community_photo']/div[7]/input");
		clickOnElement("xpath","//*[@id='new_pyr_community_photo']/div[7]/input");	
		//waitOnSpinner();
	}
	
	public void addCommunityVideo(String videoTitle, String visibleSettings) throws Exception {
		logInfo("inside addCommunityVideo() method ...");
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Add Photo / Video");	
		verifyLinkPresent("Add Video");
		clickOnLink("linkText", "Add Video");		
		verifyElementPresent("xpath",inputVideoURL);
		inputText("xpath",inputVideoURL,videoURL_text);
		inputText("xpath",inputVideoTitle,videoTitle);
		inputText("xpath",inputVideoDesc,videoDesc_text);
		inputText("xpath",inputVideoTags,videoTags_text);
		selectFromDropdown("cssSelector",drpdnVisibilityVideoSettings,"byVisibleText",visibleSettings);
		waitOnElement("xpath",btnShareVideo);
		clickOnElement("xpath",btnShareVideo);
		//waitOnSpinner();
	}

	 public void waitOnSpinner() throws Exception{
		 logInfo("inside waitOnSpinner() method");
		 waitOnElement("xpath","//*[@id='spinner-container'][contains(@style,'display: none')]");
		 verifyElementPresent("xpath","//*[@id='spinner-container'][contains(@style,'display: none')]");
	 }
	
	public void closePopupWindow() throws Exception{
		waitOnElement("cssSelector",closeSharePopUp);
		clickOnElement("cssSelector", closeSharePopUp);
	}	
	
	 public void emailWithAttachment(String mailId, String subject) throws Exception, Exception{    	
	    	logInfo("Enter recipient mailId & compose and send a mail");
	    	String Attach1 = "Attach To Message" ;
	    	String Attach2 = "Insert Into Message Editor" ;    	
	    	recepAndSubject(mailId, subject);    	
	    	clickOnButton("cssSelector", attach);
	    	waitOnElement("cssSelector", browseInBr);
	      	uploadFile("Image",browseInBr);
	    	uploadFooter(Attach1); 
	    	waitOnElement("cssSelector", send_Mail);     	
	    	clickOnButton("cssSelector", send_Mail);    	
		 }
	 
	 	public void recepAndSubject(String emailId, String subject) throws Exception{
	 		logInfo("Inside recepAndSubject() method.");
	    	waitOnElement("cssSelector",recipientsTo);   
	    	inputTextClear("cssSelector", recipientsTo);
	    	inputText("cssSelector", recipientsTo, emailId);
	    	submitElement("cssSelector", recipientsTo);
	    	waitOnElement("cssSelector", mailSub);
	    	inputTextClear("cssSelector", mailSub);
	    	inputText("cssSelector", mailSub, subject); 		    	
		 }
	 	
	 	
	 	public void uploadFooter(String attachType) throws Exception{
	 	 	logInfo("Inside uploadFooter() method");
	 	 	List <WebElement> att = driver().findElements(By.cssSelector(foot));
	 	 	System.out.println("foters size "+att.size());
	 	 	for (WebElement attach: att){	 
	 	 		System.out.println("enterred");
	 	 		System.out.println("footers"+ attach.getText());
	 	 		if(attach.getText().equalsIgnoreCase(attachType));{	    			
	 	 			attach.click(); 	 			
	 	 			break;
	 	 		}
	 	 	}
	 	 }
	 	 
	 	 //Validating Post textbox without entering data.
	 	public void validatePostComment() throws Exception {
	    	logInfo("Entered into validatePostComment() method.");		
	    	waitOnElement("xpath",btnCommunityPost);
			clickOnElement("xpath",btnCommunityPost);
			confirmationMessage("Status can't be blank");
			 
		}
	 	
	 	
	 		 	
	 	public void validateMandatoryFields4Photo(String photoTitle) throws Exception {	
	 		logInfo("Entered into validateMandatoryFields4Photo() method");
	 		System.out.println("Entered into validateMandatoryFields4Photo() method");
	 		nav2Community();
			verifyLinkPresent("Add Photo / Video");
			clickOnLink("linkText", "Add Photo / Video");
			verifyElementPresent("xpath",inputPhotoURL);			
			inputText("xpath",inputPhotoTitle,photoTitle);			
			inputText("xpath",inputPhotoDesc,photoDesc_text);
			inputText("xpath",inputPhotoTags,photoTags_text);
			selectFromDropdown("xpath",drpdnVisibilitySettings,"byVisibleText",visibilitySettings_text);
			clickOnButton("xpath",btnSharePhoto);		
			assertAlertMessage(validateText);					
			verifyElementPresent("xpath",inputPhotoURL);
			inputText("xpath",inputPhotoURL,photoURL_text);
			inputTextClear("xpath",inputPhotoTitle);			
			clickOnButton("xpath",btnSharePhoto);		
			assertAlertMessage(validateText);	
			inputTextClear("xpath",inputPhotoTitle);		
			closePopUpWindow();			
		}
	 	
	 	public void closePopUpWindow() throws Exception{
	 		logInfo("Enetered into closePopUpWindow() method");
	 		waitOnElement("cssSelector", closePhoto);
			clickOnElement("cssSelector", closePhoto);
	 	}
	 	
	 	
	 	 public void validateMandatoryFields4BlogPost() throws Exception{	 		
	 		logInfo("inside validateMandatoryFields4BlogPost() method..");	
	 		System.out.println("inside validateMandatoryFields4BlogPost() method..");	
	 		clickOnLink("linkText", addBlogPost);
	 		waitOnElement("cssSelector",inputAddPostTitle);
	 		inputTextClear("cssSelector",inputAddPostTitle); 
	 		inputText("cssSelector",inputAddPostTitle,subjectContent); 
	 		clickOnButton("cssSelector",btnCreatePost);	 		
	 		assertAlertMessage(validateText);	 		
			waitOnElement("cssSelector",btnCreatePost);			
			inputTextClear("cssSelector",inputAddPostTitle); 			
	 		composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new blog");
	 		inputText("cssSelector",inputAddPostExcerpt,addPostExcerpt_text);
	 		selectFromDropdown("cssSelector",drpdnAddPostVisibility,"byVisibleText",visibility_text_community);
	 		selectRadioOrCheckbox("cssSelector", chkPublishPost);
	 		clickOnButton("cssSelector",btnCreatePost);	 	
	 		assertAlertMessage(validateText);	
	 		closePopUpWindow();	

	 	}
	 	 
	 	 
	 public void validateMandatoryFields4Video(String videoTitle) throws Exception {
		logInfo("Entered into validateMandatoryFields4Video() method");
		System.out.println("Entered into validateMandatoryFields4Video() method");
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Add Photo / Video");		
		verifyLinkPresent("Add Video");
		clickOnLink("linkText", "Add Video");			
		verifyElementPresent("xpath",inputVideoURL);			
		inputText("xpath",inputVideoTitle,videoTitle);
		inputText("xpath",inputVideoDesc,videoDesc_text);
		inputText("xpath",inputVideoTags,videoTags_text);
		selectFromDropdown("cssSelector",drpdnVisibilityVideoSettings,"byVisibleText",drpdnVisibilityVideoSettings_text);
		clickOnButton("xpath",btnShareVideo);
		assertAlertMessage(validateText );
		closePopUpWindow();	
		nav2Community();
		clickOnLink("linkText", "Add Photo / Video");
		waitOnElement("linkText", "Add Video");	
		clickOnLink("linkText", "Add Video");	
		waitOnElement("xpath",btnShareVideo);	 		
 		inputText("xpath",inputVideoURL,invalidUrl);
 		inputTextClear("xpath",inputVideoDesc);
		inputTextClear("xpath",inputVideoTags);
		inputText("xpath",inputVideoDesc,videoDesc_text);
		inputText("xpath",inputVideoTags,videoTags_text);
		waitOnElement("xpath",btnShareVideo);
		clickOnButton("xpath",btnShareVideo);
		clickOnButton("xpath",btnShareVideo);
		assertAlertMessage(validateText );						
		closePopUpWindow();	
	}
	 
	 
	public void assertAlertMessage(String alertMsg) throws Exception{
		 logInfo("Enetered into assertAlertMessage() method");
		    waitOnElement("cssSelector", imgAlert);
			WebElement alertMsgs = driver().findElement(By.cssSelector(imgAlert));		
			String actualMsg = alertMsgs.getText();
			Assert.assertEquals(actualMsg, alertMsg);			
	 }
	 
	 public void validateCountOfPost(String postName, String status) throws Exception {
		 logInfo("inside verifyCommentsCount () method.");		 
		 nav2Community();		
		 int intialCount = getCountOfPost(postName, status)+1;
		 nav2Community();
		 int afterCount = getCountOfPost(postName,status);
		 System.out.println(intialCount + " and " + afterCount);
		 Assert.assertEquals(afterCount, intialCount);
		 
		 
	 }
	 
	 
	 
		

	    public void selectGooglePlusIcon() throws Exception, InterruptedException{
	    		
			logInfo("Select GooglePlus Icon for sharing  to share the posts.");
			Thread.sleep(4000);
			clickOnElement("cssSelector", googlePlus);	
	    }
	    
	    public void shareInGooglePlus(String gmail, String passwd) throws Exception{
			logInfo("Enter Gmail credentials to share Posts");
			String wndBeforeWindow = driver().getWindowHandle();	
			for(String w : driver().getWindowHandles()){
					if(!w.equalsIgnoreCase(wndBeforeWindow)){
			           driver().switchTo().window(w);
			           Thread.sleep(3000);
						inputTextClear("cssSelector",inputGmail);
						inputText("cssSelector",inputGmail, gmail);
						clickOnButton("cssSelector", btnGmailNext);
						WebElement staysigned = driver().findElement(By.xpath(chkStaySignedIn));
						String isChecked = staysigned.getAttribute("checked").trim();
						if(isChecked.equalsIgnoreCase("checked")){
							staysigned.click();
				}
				
				inputText("cssSelector",inputGmailPasswd, passwd);
				clickOnButton("cssSelector", btnSignIn);
				Thread.sleep(4000);
				getText("cssSelector", gmailComment, "product is");
				inputText("cssSelector", gmailComment, "Superb product");
				clickOnButton("xpath", gmailShare);
			/*clickOnLink("linkText", "Close this window");*/	
			Thread.sleep(8000);
			driver().switchTo().window(wndBeforeWindow);
			break;
			}
					}
		
		}
	    
	    
	    
	    public void loginGooglePlus(String uname, String passwd) throws Exception{
			logInfo("Acess into google plus account with credentials");
			driver().navigate().to("https://plus.google.com/");	
			clickOnButton("cssSelector", "a#gb_70");
			inputText("cssSelector",inputGmail, uname);
			clickOnButton("cssSelector", btnGmailNext);
			Thread.sleep(3000);		
			waitOnElement("cssSelector",inputGmailPasswd);
			inputText("cssSelector",inputGmailPasswd, passwd);
			clickOnButton("cssSelector", btnSignIn);
			
	 
	 }
	 
	 

		public boolean verifyInGooglePlus(String postName) throws Exception{
			
			clickOnElement("cssSelector", googAll);
			clickOnElement ("cssSelector",gogHome );
			
			boolean isPostPresent  = false;
			
			List <WebElement> list = driver().findElements(By.cssSelector(googPostList));
			System.out.println(list.size());
			for (WebElement glist: list){
				
				System.out.println(glist.getText()+ " in google plus");
				
				if (glist.getText().trim().contains(postName)){
					isPostPresent = true;					
					System.out.println("Google Puls Post name is "+ glist.getText());
					break;
				}	
				
			}if (isPostPresent==false){
				
				Assert.assertTrue(isPostPresent, postName + " is not found in googlePlus page" );
			}	
			
			clickOnElement("cssSelector", googAccountIcon);
			clickOnButton("cssSelector", googLogout);
			Thread.sleep(3000);
			return isPostPresent;
			
			
		}	 
		
		
	public boolean verifyInGooglePlusForShopProducts(String postName) throws Exception{
			
			clickOnElement("cssSelector", googAll);
			clickOnElement ("cssSelector",gogHome );
			
			boolean isPostPresent  = false;
			
			List <WebElement> list = driver().findElements(By.cssSelector(gogList));
			System.out.println(list.size());
			for (WebElement glist: list){
				
				System.out.println(glist.getText()+ " in google plus");
				
				if (glist.getText().trim().contains(postName)){
					isPostPresent = true;					
					System.out.println("Google Puls product name is "+ glist.getText());
					break;
				}	
				
			}if (isPostPresent==false){
				
				Assert.assertTrue(isPostPresent, postName + " is not found in googlePlus page" );
			}	
			
			clickOnElement("cssSelector", googAccountIcon);
			clickOnButton("cssSelector", googLogout);
			Thread.sleep(3000);
			return isPostPresent;
			
			
		}	 
		 
		
	    
	    //Select Email Icon for sharing
	    public void selectEmailIcon() throws Exception{    		
		logInfo("Entered into selectEmailIcon() method");
		waitOnElement("cssSelector", shareByMail);	
		clickOnElement("cssSelector", shareByMail);	
		
	    }
	    
	    public void selectPWPInIcon() throws Exception{
			logInfo("Select LinkedIn Icon for sharing");
			Thread.sleep(2000);		
			clickOnElement("cssSelector", pwp);		
		}
	    

	    public void shareInFaceBook() throws Exception{
			
		logInfo("Enter login credentials in FaceBook to share the posts.");
		Thread.sleep(5000);
		String wndBeforeWindow = driver().getWindowHandle();	
		for(String w : driver().getWindowHandles()){
				if(!w.equalsIgnoreCase(wndBeforeWindow)){
				driver().switchTo().window(w);			
				waitOnElement("cssSelector", fbEmail);
				inputText("cssSelector", fbEmail, fBEmail_text2);
				Thread.sleep(5000);
				inputText("cssSelector", fbPassword, fBPwd_text2);	
				submitElement("cssSelector", fbPassword);
				//clickOnButton("cssSelector", fbLoginButton);
				Thread.sleep(5000);			
				clickOnButton("name", fbShareLink);	
				Thread.sleep(8000);
				driver().switchTo().window(wndBeforeWindow);
				/*driver().manage().deleteAllCookies();*/
				break;	
			}
				}
		
		
	} 


	    public void shareInLinkedIn() throws Exception{
			
			logInfo("Enter login credentials in LinkedIn to share the posts.");
			String wndBeforeWindow = driver().getWindowHandle();	
			for(String w : driver().getWindowHandles()){
					if(!w.equalsIgnoreCase(wndBeforeWindow)){
					driver().switchTo().window(w);
			waitOnElement("cssSelector", linkedInEmail);	
			inputText("cssSelector", linkedInEmail, fBEmail_text);
			inputText("cssSelector", linkedInPwd, fBPwd_text);	
			clickOnButton("cssSelector", linkedInLogin);	
			inputText("cssSelector", linkedShareaUpdate, "Hey Its a Good Product");	
			clickOnButton("cssSelector", linkedShare);
			Thread.sleep(7000);
			clickOnLink("linkText", "Close this window");	
			Thread.sleep(8000);
			driver().switchTo().window(wndBeforeWindow);
			break;
			}
					}
		
	    } 

	    public void shareInTwitter() throws Exception{
			
		logInfo("Enter login credentials in Twitter to share the posts.");
		String wndBeforeWindow = driver().getWindowHandle();	
		for(String w : driver().getWindowHandles()){
		if(!w.equalsIgnoreCase(wndBeforeWindow)){
		driver().switchTo().window(w);
		waitOnElement("cssSelector", twitLogin);
		inputTextClear("cssSelector", twitLogin);
		inputText("cssSelector", twitLogin, fBEmail_text);
		inputTextClear("cssSelector", twitPswD);
		inputText("cssSelector", twitPswD, fBPwd_text);	
		submitElement("cssSelector", twitPswD);
		Thread.sleep(3000);
		String expTitle = "Share a link with your followers";
		String twitTitle="div#bd > h2.action-information";
		WebElement title = driver().findElement(By.cssSelector(twitTitle));
		if(title.getText().contains(expTitle)){
			waitOnElement("cssSelector", twtTweet);
			submitElement("cssSelector", twtTweet);				
		}else{
			Assert.assertEquals(title.getText(), expTitle);
		}
		
		driver().switchTo().window(wndBeforeWindow);
		break;
		}}  
		} 
	    
	    
	    public void shareByEmail(String mailId, String subject) throws Exception{    
	    	logInfo("Entered into shareByEmail() method");
	    	recepAndSubject(mailId, subject);    	
	    	scrollDown("cssSelector", sendMail);     
	    	clickOnButton("cssSelector", sendMail);     	
	    }

		public void shareProdByGoogle() throws Exception {
			logInfo("Enter login credentials in FaceBook");
			clickOnElement("cssSelector", googlePlus);

		}


		public void retrieveGoogleAccounts() throws Exception {
		
			List <WebElement> acc = driver().findElements(By.cssSelector(googleAccounts));
			System.out.println("get size "+ acc.size());
			for (WebElement googleAcc: acc){
				System.out.println(googleAcc.getText());
				
			}
		}
		
		
		

		public void googlePlusShare() throws Exception{
			String googleText = " Awesome Product, my count is "+generateRandomNumberInRange(5, 500);
			inputText("cssSelector", googleEmail, "icentrissoft@gmail.com");
			driver().findElement(By.cssSelector(googleEmail)).submit();
			inputText("cssSelector", googlePassword, "testing@123");
			driver().findElement(By.cssSelector(googlePassword)).submit();
			/*inputText("cssSelector", googleMsg, googleText);*/
			clickOnButton("xpath", googleShare);

		}
	 
	 
	 public int getCountOfPost(String postName, String linkStatus) throws Exception {
		 logInfo("inside getCount(String postName) method.");
		 driver().navigate().refresh();
		 int intialCount = 0;
			waitOnElement("xpath",paneCommunityWall);	
			List<WebElement> allStatus = driver().findElements(By.cssSelector(allPostStat));
			boolean isMatchFound = false;
			for (int i =1; i<=allStatus.size(); i++){
				WebElement post = driver().findElement(By.cssSelector(postNameBfr+i+postNameAft));	
				String actPostName = post.getText().trim();
				System.out.println(actPostName);
				if (actPostName.equalsIgnoreCase(postName)){ 				
					isMatchFound = true;										
					boolean isStatusFound = false;
					List <WebElement > status = driver().findElements(By.cssSelector(postNameBfr+i+allPostStatus));
					System.out.println("All status "+ status.size());
					for (int j=1; j<=status.size(); j++){						
						WebElement st = driver().findElement(By.cssSelector(postNameBfr+i+poststatusBfr+j+")"));
						String actStatus = st.getText().trim();
						System.out.println("actStatus "+ actStatus);
						if(actStatus.contains(linkStatus)){
							isStatusFound = true;			
							System.out.println("eeneted status");
							switch (linkStatus){
						    case "Like":
								WebElement likeCount = driver().findElement(By.xpath(before_name+i+likeCountAft));
								String intCount =likeCount.getText().trim();
								intialCount  =Integer.parseInt(intCount);
								System.out.println("intialCount like "+ intialCount);
								st.click();
								break;	
						    case "Liked":
								WebElement likedCount = driver().findElement(By.xpath(before_name+i+likeCountAft));
								String intLikeCount =likedCount.getText().trim();
								intialCount  =Integer.parseInt(intLikeCount);
								System.out.println("intialCount AfterLiked "+ intialCount);								
								break;		
							case "Comment":
								WebElement comCount = driver().findElement(By.xpath(before_name+i+commentCountAft));
								String commCount =comCount.getText().trim();
								intialCount  =Integer.parseInt(commCount);
								System.out.println("intialCount Comment"+ intialCount);
								st.click();
								inputTextClear("cssSelector",inputCommunityStatusComment);
								inputText("cssSelector",inputCommunityStatusComment, communityStatusComment_text);
								clickOnButton("cssSelector",btnCommunityStatusComment);									
								break;
							
							case "Share":
								WebElement shCount = driver().findElement(By.xpath(before_name+i+shareCountAft));
								String shareCount =shCount.getText().trim();
								intialCount  =Integer.parseInt(shareCount);
								System.out.println("intialCount Share"+ intialCount);
								st.click();	
								sm.selectFacebookIcon();		
								shareInFaceBook();
								sm.selectTwitterIcon();
								shareInTwitter();
								closePopupWindow();	
								break;								
							default:
								Assert.assertNotNull(status);							
							}break;
							
					} 
			}if(isStatusFound==false){
				Assert.assertTrue(isStatusFound, status + " Status is not present under post - "+ postName);
			}break;		
				}}if(isMatchFound==false){		
				Assert.assertTrue(isMatchFound, postName + " community post match not found.");
			}
			return intialCount;		
		}
	 
	 
	 public int getCountOfPostAfterSelected(String postName, String status) throws Exception {
		 logInfo("inside getCount(String postName) method.");
		 driver().navigate().refresh();
		 int intialCount = 0;
			waitOnElement("xpath",paneCommunityWall);	
			List<WebElement> allStatus = driver().findElements(By.cssSelector(allPostStat));
			boolean isMatchFound = false;
			for (int i =1; i<=allStatus.size(); i++){
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String actPostName = x.getText().trim();
				System.out.println(x.getText().trim());
				if (actPostName.equalsIgnoreCase(postName)){ 				
					isMatchFound = true;										
					boolean isStatusFound = false;
					List<WebElement> all = driver().findElements(By.xpath(before_name+i+allsStatus));
					System.out.println("All status "+ all.size());
					for (int j=1; j<=all.size(); j++){
						waitOnElement("xpath", allStat);
						WebElement stat = driver().findElement(By.xpath(before_name+i+statusBfr+j+statusAft)) ;
						String actStatus = stat.getText().trim();
						System.out.println("actStatus "+ actStatus);
						if(actStatus.contains(status)){
							isStatusFound = true;							
							switch (status){
							case "Like":
								WebElement likeCount = driver().findElement(By.xpath(before_name+i+likeCountAft));
								String intCount =likeCount.getText().trim();
								intialCount  =Integer.parseInt(intCount);
								System.out.println("intialCount AfterLike "+ intialCount);								
								break;
							case "Liked":
								WebElement likedCount = driver().findElement(By.xpath(before_name+i+likeCountAft));
								String intLikeCount =likedCount.getText().trim();
								intialCount  =Integer.parseInt(intLikeCount);
								System.out.println("intialCount AfterLiked "+ intialCount);								
								break;	
								
							case "Comment":
								WebElement comCount = driver().findElement(By.xpath(before_name+i+commentCountAft));
								String commCount =comCount.getText().trim();
								intialCount  =Integer.parseInt(commCount);
								System.out.println("intialCount AfterComment"+ intialCount);														
								break;							
							case "Share":
								WebElement shCount = driver().findElement(By.xpath(before_name+i+shareCountAft));
								String shareCount =shCount.getText().trim();
								intialCount  =Integer.parseInt(shareCount);
								System.out.println("intialCount AfterShare"+ intialCount);								
								break;								
							default:
								Assert.assertNotNull(status);							
							}break;
							
					} 
			}if(isStatusFound==false){
				Assert.assertTrue(isStatusFound, status + " Status is not present under post - "+ postName);
			}break;		
				}}if(isMatchFound==false){		
				Assert.assertTrue(isMatchFound, postName + " community post match not found.");
			}
			return intialCount;		
		}
	 
	 
	 public int getCountOfPost_old(String postName, String status) throws Exception {
		 logInfo("inside getCount(String postName) method.");
		// String comment = "Comment";
		 int intialCount = 0;
			waitOnElement("xpath",paneCommunityWall);	
			List<WebElement> allPhotos = driver().findElements(By.xpath(allStat));		
			boolean isMatchFound = false;
			for (int i =1; i<=allPhotos.size()-1; i++){
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String actPostName = x.getText().trim();
				System.out.println(x.getText().trim());
				if (actPostName.equalsIgnoreCase(postName)){ 				
					isMatchFound = true;										
					boolean isStatusFound = false;
					List<WebElement> all = driver().findElements(By.xpath(before_name+i+allsStatus));
					System.out.println("All status "+ all.size());
					for (int j=1; j<=all.size(); j++){
						waitOnElement("xpath", allStat);
						WebElement stat = driver().findElement(By.xpath(before_name+i+statusBfr+j+statusAft)) ;
						String actStatus = stat.getText().trim();
						System.out.println("actStatus "+ actStatus);
						if(actStatus.equalsIgnoreCase(status)){
							isStatusFound = true;							
							switch (status){
							case "Like":
								WebElement likeCount = driver().findElement(By.xpath(before_name+i+likeCountAft));
								String intCount =likeCount.getText().trim();
								intialCount  =Integer.parseInt(intCount);
								System.out.println("intialCount like "+ intialCount);
								stat.click();
								break;								
							case "Comment":
								WebElement comCount = driver().findElement(By.xpath(before_name+i+commentCountAft));
								String commCount =comCount.getText().trim();
								intialCount  =Integer.parseInt(commCount);
								System.out.println("intialCount Comment"+ intialCount);
								stat.click();
								inputTextClear("cssSelector",inputCommunityStatusComment);
								inputText("cssSelector",inputCommunityStatusComment, communityStatusComment_text);
								clickOnButton("cssSelector",btnCommunityStatusComment);									
								break;
							
							case "Share":
								WebElement shCount = driver().findElement(By.xpath(before_name+i+shareCountAft));
								String shareCount =shCount.getText().trim();
								intialCount  =Integer.parseInt(shareCount);
								System.out.println("intialCount Share"+ intialCount);
								stat.click();	
								sm.selectFacebookIcon();		
								shareInFaceBook();
								sm.selectTwitterIcon();
								shareInTwitter();
								closePopupWindow();	
								break;								
							default:
								Assert.assertNotNull(status);							
							}
							
					}
			}if(isStatusFound==false){
				Assert.assertTrue(isStatusFound, status + " Status is not present under post - "+ postName);
			}break;		
				}}if(isMatchFound==false){		
				Assert.assertTrue(isMatchFound, postName + " community post match not found.");
			}
			return intialCount;		
		}
	 
	 
	
	 
	 public int getCountAfterCommented(String postName) throws Exception {
		 logInfo("inside getCount(String postName) method.");
		 int afterCount = 0;
			waitOnElement("xpath",paneCommunityWall);	
			List<WebElement> allPhotos = driver().findElements(By.xpath(allStat));		
			boolean isMatchFound = false;
			for (int i =1; i<=allPhotos.size()-1; i++){
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String actPostName = x.getText().trim();
				System.out.println(x.getText().trim());
				if (actPostName.equalsIgnoreCase(postName)){ 				
					isMatchFound = true;
					WebElement iCount = driver().findElement(By.xpath(before_name+i+commentCountAft));
					String intCount =iCount.getText().trim();
					afterCount  =Integer.parseInt(intCount);
					System.out.println("afterCount "+ afterCount );													
					break;
					}
			}		
			if(isMatchFound==false){		
				Assert.assertTrue(isMatchFound, postName + " community post match not found.");
			}
			return afterCount;		
		}
	 
	 
	 
	 public boolean verifyCommentsCountForBlog(String postName) throws Exception{
			logInfo("inside verifyCommentsCountForBlog() method.");
			WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
			List<WebElement> allPosts = panel.findElements(By.xpath(paneCommunityWallContent));
			boolean isCommentsFound = false;
			
			String before = "//div[@class='activities-stream']/div[";
			String after = "]/div/div[1]/a";
			
		
			String commentsAfter = "]/div/div[@class='community_activity_action']/span[2]/span[1]";
			
			
			String lnkCommentAfter = "]/div/div[@class='community_activity_action']/span[1]/span[2]/a";	
					
			for(int i=1;i<=allPosts.size();i++){
				String blogPostName = driver().findElement(By.xpath(before+i+after)).getText().trim();
				System.out.println(blogPostName + " blogPostName");
				if (blogPostName.equalsIgnoreCase(postName.trim())){ 
					driver().findElement(By.xpath(before+i+lnkCommentAfter)).click();
//					Thread.sleep(3000);
					waitOnElement("xpath",txtComment);
					inputText("xpath",txtComment, commentBlogText );
					clickOnElement("cssSelector", btnPost);
					int commentsCount = getCommentsCountForBlog(postName);
					if(commentsCount > 0)
						isCommentsFound = true;
					break; 
					
				}
			}
			return isCommentsFound;
		}
		
		public int getCommentsCountForBlog(String postName) throws Exception{
			logInfo("inside getCommentsCountForBlog() method.");
			int commentsCount = 0;
			nav2Community();
			WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
			List<WebElement> allPosts = panel.findElements(By.xpath(paneCommunityWallContent));
					
			String before = "//div[@class='activities-stream']/div[";
			String after = "]/div/div[1]/a";
			
			String commentsBefore = "//div[@class='activities-stream']/div[";
			String commentsAfter = "]/div/div[@class='community_activity_action']/span[2]/span[1]";
					
			for(int i=1;i<=allPosts.size();i++){
				String blogPostName = driver().findElement(By.xpath(before+i+after)).getText().trim();
				if (blogPostName.equalsIgnoreCase(postName.trim())){ 
					System.out.println(postName + " community post match found.");
					String commentsValue = driver().findElement(By.xpath(commentsBefore+i+commentsAfter)).getText();
					commentsCount = Integer.parseInt(commentsValue);
					System.out.println("comments count for this post "+postName+" is "+commentsCount);
					break; 
					
				}
			}
			return commentsCount;
		}
		
		
		//Verify the activity like photo, video, blog presence in my Recent activity(MyProfile)
		public boolean isActivitypresentInMyRecentActivities(String activityName) throws Exception{		  			
		    logInfo("Entered into isActivitypresentInMyRecentActivities() method");
			 nav2Community();
			 for (int i=0; i<=2;i++){
			 clickOnLink("cssSelector",vieMor );
			 			}
			 boolean isactivityPresent = false;
			 List <WebElement> act = driver().findElements(By.cssSelector(actList));
			 System.out.println(act.size());		 
			 for (WebElement acts : act){			 
				 if (acts.getText().contains(activityName)){
					 System.out.println(acts.getText());
					 isactivityPresent= true;				 
					 break;
				 }}if (isactivityPresent == false){			 
				 Assert.assertTrue(isactivityPresent, activityName +" is mismatched in My Recent Activities List." );
			 } 		 
			return isactivityPresent;	 
		 }
		
		
		public void addBlogPost(String postName, String visibleSettings, Boolean published) throws Exception{
			logInfo("inside addBlogPost() method..");
			waitOnElement("linkText", "Add Blog Post");
			clickOnLink("linkText", "Add Blog Post");
			waitOnElement("cssSelector",inputAddPostTitle);
			inputText("cssSelector",inputAddPostTitle,postName); 
			composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new blog");
//			composeTextOnElement();
			inputText("cssSelector",inputAddPostExcerpt,addPostExcerpt_text);
			selectFromDropdown("cssSelector",drpdnAddPostVisibility,"byVisibleText",visibleSettings);
			if(published)
				selectRadioOrCheckbox("cssSelector", chkPublishPost);
			clickOnButton("cssSelector",btnCreatePost);
			Thread.sleep(3000);
			clickOnButton("cssSelector",okFirstTime);
			logInfo("clicked on create post button.");
		}
		
		public boolean filterByType(String type,String title) throws Exception{
	 		boolean isFiltered = false;
	 		logInfo("inside filterByType() method..");
	 		System.out.println("inside filterByType() method..");
	 		nav2Community();
	 		waitOnElement("cssSelector","#filterid");
	 		clickOnElement("cssSelector","#filterid");
	 		
	 		List<WebElement> filters = driver().findElements(By.cssSelector(".dropdown-menu.filter-by>li>a"));
	 		if(filters.size()>0){
	 			for(WebElement e :filters){
	 				if(e.getText().equalsIgnoreCase(type)){
	 					e.click();
	 					break;
	 				}
	 			}
	 		}
	 		
	 		switch(type){
	 		case "Photo":
	 			List<WebElement> photos = driver().findElements(By.xpath("//*[@class='activities-stream']/div[contains(@id,'pyr_community_activity')]"));
	 			if(photos.size()>0){
	 				String before_photo = "//*[@class='activities-stream']/div[contains(@id,'pyr_community_activity')][";
	 				String after_photo = "]/div/div[1]/a";
	 				for(int i=1;i<=photos.size();i++){
	 					WebElement elPhoto = driver().findElement(By.xpath(before_photo+i+after_photo));
	 					if(elPhoto.getText().trim().equalsIgnoreCase(title)){
	 						isFiltered = true;
	 						break;
	 					}
	 				}
	 			}
	 			break;
	 			
	 		case "Video":
	 			List<WebElement> videos = driver().findElements(By.xpath("//*[@class='activities-stream']/div[contains(@id,'pyr_community_activity')]"));
	 			if(videos.size()>0){
	 				String before_video = "//*[@class='activities-stream']/div[contains(@id,'pyr_community_activity')][";
	 				String after_video = "]/div/div[1]/a";
	 				for(int i=1;i<=videos.size();i++){
	 					WebElement elVideo = driver().findElement(By.xpath(before_video+i+after_video));
	 					if(elVideo.getText().trim().equalsIgnoreCase(title)){
	 						isFiltered = true;
	 						break;
	 					}
	 				}
	 			}
	 			break;
	 		case "Status":
	 			List<WebElement> status = driver().findElements(By.xpath("//*[@class='activities-stream']/div[contains(@id,'pyr_community_activity')]"));
	 			if(status.size()>0){
	 				String before_status = "//*[@class='activities-stream']/div[contains(@id,'pyr_community_activity')][";
	 				String after_status = "]/div/div[1]";
	 				for(int i=1;i<=status.size();i++){
	 					WebElement elStatus = driver().findElement(By.xpath(before_status+i+after_status));
	 					if(elStatus.getText().trim().equalsIgnoreCase(title)){
	 						isFiltered = true;
	 						break;
	 					}
	 				}
	 			}
	 			break;
	 		}
	 		clickOnElement("xpath","//*[@id='widget-recent-activity']/div[2]/a");
	 		return isFiltered;
	 		
		}
		
		public boolean verifyActivityPresent(String postName) throws Exception {
			logInfo("inside verifyActivityPresent() method.");
			System.out.println("inside verifyActivityPresent() method.");
			nav2Community();	
			waitOnElement("xpath",allStat);	
			List<WebElement> allPhotos = driver().findElements(By.xpath(allStat));		
			boolean isMatchFound = false;
			for(WebElement x : allPhotos){
				String actPostName = x.getText().trim();
				System.out.println("actPostName "+ actPostName);
				if (actPostName.contains(postName)){ 				
					isMatchFound = true;
					x.click();
					Thread.sleep(3000);
					break;
					}
			}		
			if(isMatchFound==false){		
				Assert.assertTrue(isMatchFound, postName + " community post match not found.");
			}
			return isMatchFound;
		}
	 	
	 	public void updatePhoto(String activity,String updatedActivity) throws Exception{
	 		try{
	 			logInfo("inside updatePhoto() method..");
		 		System.out.println("inside updatePhoto() method..");
		 		boolean isActivityPresent = verifyActivityPresent(activity);
		 		if(isActivityPresent){
		 			verifyElementPresent("linkText", "Edit");
		 			clickOnElement("linkText", "Edit");
		 			waitOnElement("xpath","//*[@id='pyr_community_photo_title']");
		 			inputTextClear("xpath","//*[@id='pyr_community_photo_title']");
		 			inputText("xpath","//*[@id='pyr_community_photo_title']",updatedActivity);
		 			waitOnElement("xpath",btnSharePhoto);
		 			clickOnButton("xpath",btnSharePhoto);
		 			waitOnSpinner();
		 			confirmationMessage("Your photo has been updated.");
		 		}
				
		 		
	 		}
	 		catch(Exception ex){
	 			logInfo("unable to update a photo "+photoTitle);
	 			System.out.println("unable to update a photo "+photoTitle);
	 			throw ex;
	 		}

	 	}
	 	
	 	public void updateVideo(String activity,String activityUpdated) throws Exception{
	 		try{
	 			logInfo("inside updateVideo() method..");
		 		System.out.println("inside updateVideo() method..");
		 		boolean isActivityPresent = verifyActivityPresent(activity);
		 		if(isActivityPresent){
		 			verifyElementPresent("linkText", "Edit");
		 			clickOnElement("linkText", "Edit");
		 			waitOnElement("xpath","//*[@id='pyr_community_video_title']");
		 			inputTextClear("xpath","//*[@id='pyr_community_video_title']");
		 			inputText("xpath","//*[@id='pyr_community_video_title']",activityUpdated);
		 			waitOnElement("xpath","//*[@value='Share Video']");
		 			clickOnButton("xpath","//*[@value='Share Video']");
		 			waitOnSpinner();
		 			confirmationMessage("Updated.");
		 		}
	 		}
	 		catch(Exception ex){
	 			logInfo("unable to update a photo "+photoTitle);
	 			System.out.println("unable to update a photo "+photoTitle);
	 			throw ex;
	 		}
	 	}
	 	
	 	public void updateBlogPost(String activity,String activityUpdated) throws Exception{
	 		try{
	 			logInfo("inside updateBlogPost() method..");
		 		System.out.println("inside updateBlogPost() method..");
		 		boolean isActivityPresent = verifyActivityPresent(activity);
		 		if(isActivityPresent){
		 			verifyElementPresent("linkText", "Edit");
		 			clickOnElement("linkText", "Edit");
		 			waitOnElement("xpath","//*[@id='pyr_community_post_title']");
		 			inputTextClear("xpath","//*[@id='pyr_community_post_title']");
		 			inputText("xpath","//*[@id='pyr_community_post_title']",activityUpdated);
		 			waitOnElement("xpath",btnUpdateDraft);
		 			clickOnButton("xpath",btnUpdateDraft);
		 			waitOnSpinner();
		 			confirmationMessage("Post was successfully updated.");
		 		}
	 		}
	 		catch(Exception ex){
	 			logInfo("unable to update a photo "+photoTitle);
	 			System.out.println("unable to update a photo "+photoTitle);
	 			throw ex;
	 		}
	 	}
	 	
	 	
	 	
	 	public boolean verifyCommentPresent(String statusMessage) throws Exception {
			logInfo("inside verifyStatusPostIsPresent() method.");
			nav2Community();
			waitOnElement("xpath",paneCommunityWall);
			WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
			waitOnElement("xpath",paneCommunityWallStatus);
			List<WebElement> allStatus = panel.findElements(By.xpath(paneCommunityWallStatus));
			boolean isMatchFound = false;
			logInfo("Total posts =" +allStatus.size());	
			String before_name = "//div[@class='activities-stream']/div[";
			String after_name = "]/div/div[1]";
			for(int i=1;i<=allStatus.size();i++){
				WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
				System.out.println(e.getText().trim());
				String status = e.getText().trim();
					if(status.contains(statusMessage)){
						isMatchFound = true;
						logInfo(statusMessage + " status post match found.");
						break;
					}
				}
			
			if(isMatchFound==false){
				logInfo(statusMessage + " status post match not found.");
				Assert.assertTrue(isMatchFound, statusMessage + " status post match not found in community wall.");
			}
			return isMatchFound;
		}
	 	
	 	public void setCommunityStatus(String postName , String status) throws Exception {
			logInfo("Entered into  setCommunityStatus() method.");
			System.out.println("Entered into  setCommunityStatus() method.");
			nav2Community();
			waitOnElement("xpath",paneCommunityWall);		
			List<WebElement> allMessages = driver().findElements(By.xpath(allStat));
			System.out.println(allMessages.size());
			boolean isMatchFound = false;	
			String before_name = "//div[@class='activities-stream']/div[";
			String after_name = "]/div/div[1]";
				
			for(int i=1;i<=allMessages.size();i++){			
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String name = x.getText().trim();	
				System.out.println(name);
				if(name.equalsIgnoreCase(postName)){				
					isMatchFound = true;
					boolean isStatusFound = false;
					List<WebElement> all = driver().findElements(By.xpath(before_name+i+allsStatus));
					System.out.println("All status "+ all.size());
					for (int j=1; j<=all.size(); j++){
						waitOnElement("xpath", allStat);
						WebElement stat = driver().findElement(By.xpath(before_name+i+statusBfr+j+statusAft)) ;
						String actStatus = stat.getText().trim();
						System.out.println("actStatus "+ actStatus);
						if(actStatus.equalsIgnoreCase(status)){
							isStatusFound = true;
							stat.click();
							Thread.sleep(3000);
							break;	
						}
					}
					if(!isStatusFound){
						Assert.assertTrue(isStatusFound, status + " Status is not present under post - "+ postName);
					}
					break;
				}
				
			}
			if(!isMatchFound){
				Assert.assertTrue(isMatchFound, postName + " post is not present.");
			}
		}
	 	
	 	public void hideCommunityStatusPost(String postName , String status) throws Exception {
			logInfo("Entered into  hideCommunityStatusPost() method.");	
			System.out.println("Entered into  hideCommunityStatusPost() method.");	
			nav2Community();
			waitOnElement("xpath","//div[@class='activities-stream']/div/div/div[1]");		
			List<WebElement> allMessages = driver().findElements(By.xpath("//div[@class='activities-stream']/div/div/div[1]"));
			System.out.println(allMessages.size());
			boolean isMatchFound = false;	
			String before_name = "//div[@class='activities-stream']/div[";
			String after_name = "]/div/div[1]";
			for(int i=1;i<=allMessages.size()-1;i++){			
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String name = x.getText().trim();	
				System.out.println(name);
				if(name.equalsIgnoreCase(postName)){				
					isMatchFound = true;
					boolean isStatusFound = false;
					List<WebElement> all = driver().findElements(By.xpath(before_name+i+allsStatus));
					System.out.println("All status "+ all.size());
					for (int j=1; j<=all.size(); j++){
						waitOnElement("xpath", allStat);
						WebElement stat = driver().findElement(By.xpath(before_name+i+statusBfr+j+statusAft)) ;
						String actStatus = stat.getText().trim();
						System.out.println("actStatus "+ actStatus);
						if(actStatus.equalsIgnoreCase(status)){
							isStatusFound = true;
							stat.click();
							break;	
						}
					}
					if(!isStatusFound){
						Assert.assertTrue(isStatusFound, status + " Status is not present under post - "+ postName);
					}
					break;
				}
				
			}
			if(!isMatchFound){
				Assert.assertTrue(isMatchFound, postName + " post is not present.");
			}
		}
	 	
	 	public void manageActionsOnCommunityComment(String postName, String comment, String commentUpdated, String action) throws Exception {
			logInfo("inside manageActionsOnCommunityComment() method.");
			System.out.println("inside manageActionsOnCommunityComment() method.");
			nav2Community();
			waitOnElement("xpath",paneCommunityWall);
			WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
			List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
			boolean isMatchFound = false;
			boolean iscommentFound = false;
			logInfo("Total posts =" +allMessages.size());				
			System.out.println("Total messages ="+ allMessages.size());
			
			String before_name = "//div[@class='activities-stream']/div[";
			String after_name = "]/div/div[1]/a";
			String comments = "]/div[1]/div[@class='comments']/div[2]/div[contains(@id,'comment_body')]";
			
			for(int i=1;i<=allMessages.size();i++){
				waitOnElement("xpath",before_name+i+after_name);
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String name = x.getText().trim();
				System.out.println("name "+name);
				if(name.equalsIgnoreCase(postName)){				
					isMatchFound = true;	
					waitOnElement("xpath",before_name+i+comments);
					List<WebElement> lstComments = driver().findElements(By.xpath(before_name+i+comments));
					if(lstComments.size()>0){
						
						String before_comment = "]/div[1]/div[@class='comments']/div[2]/div[contains(@id,'comment_body')][";
						String after_comment = "]/div[2]/p";
						
						for(int j=1;j<=lstComments.size();j++){
							waitOnElement("xpath",before_name+i+before_comment+j+after_comment);
							WebElement elComment = driver().findElement(By.xpath(before_name+i+before_comment+j+after_comment));
							
							if(action.equalsIgnoreCase("Edit")){
								if(elComment.getText().equalsIgnoreCase(comment)){
									iscommentFound = true;
								
									String before_edit = "]/div[2]/span[2]/a";
									WebElement editComment = driver().findElement(By.xpath(before_name+i+before_comment+j+before_edit));
									editComment.click();
									
									String before_txtComment = "]/div[2]/p/form/div/div/div/textarea";
									waitOnElement("xpath",before_name+i+before_comment+j+before_txtComment);
									inputTextClear("xpath",before_name+i+before_comment+j+before_txtComment);
									inputText("xpath",before_name+i+before_comment+j+before_txtComment,commentUpdated);
									
									String txtCommentPost = "]/div[2]/p/form/div/div/span";
									waitOnElement("xpath",before_name+i+before_comment+j+txtCommentPost);
									clickOnElement("xpath",before_name+i+before_comment+j+txtCommentPost);
									confirmationMessage("Comment was successfully updated.");
									break;
								}
										
							}
							else if(action.equalsIgnoreCase("Delete")){
								if(elComment.getText().equalsIgnoreCase(commentUpdated)){
									iscommentFound = true;
									String before_delete = "]/div[2]/span[1]/a";
									WebElement deleteComment = driver().findElement(By.xpath(before_name+i+before_comment+j+before_delete));
									deleteComment.click();
									confirmationMessage("Comment Deleted");
									break;
								}
			
							}
							if(!iscommentFound){
								Assert.assertTrue(iscommentFound, comment + " comment not found.");
							}
						}
					}
						
					break;		
				}
				
			}
			if(!isMatchFound){
				Assert.assertTrue(isMatchFound, postName + " post is not present.");
			}
		}
			
	 	public void commentStatusOnCommunity(String postName, String status ,String comment) throws Exception {
			logInfo("inside commentStatusOnCommunity() method.");
			System.out.println("inside commentStatusOnCommunity() method.");
			nav2Community();
			waitOnElement("xpath",paneCommunityWall);
			WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
			waitOnElement("xpath",paneCommunityWallStatus);
			List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
			boolean isMatchFound = false;
			logInfo("Total posts =" +allMessages.size());				
			System.out.println("Total messages ="+ allMessages.size());
			String before_name = "//div[@class='activities-stream']/div[";
			String after_name = "]/div/div[1]";
			for(int i=1;i<=allMessages.size();i++){
				waitOnElement("xpath",before_name+i+after_name);
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String name = x.getText().trim();
				System.out.println("name "+name);
				if(name.equalsIgnoreCase(postName)){				
					isMatchFound = true;				
					boolean isStatusFound = false;
					List<WebElement> all = driver().findElements(By.xpath(before_name+i+allsStatus));
					System.out.println("All status "+ all.size());
					for (int j=1; j<=all.size(); j++){
						waitOnElement("xpath", before_name+i+statusBfr+j+statusAft);
						WebElement stat = driver().findElement(By.xpath(before_name+i+statusBfr+j+statusAft)) ;
						String actStatus = stat.getText().trim();
						System.out.println("actStatus "+ actStatus);
						if(actStatus.equalsIgnoreCase(status)){
							isStatusFound = true;
							stat.click();
							Thread.sleep(2000);
							waitOnElement("cssSelector",inputCommunityStatusComment);
							inputTextClear("cssSelector",inputCommunityStatusComment);
							inputText("cssSelector",inputCommunityStatusComment, comment);
							waitOnElement("cssSelector",btnCommunityStatusComment);
							clickOnButton("cssSelector",btnCommunityStatusComment);		
							Thread.sleep(3000);
							break;
							}						
						}
						if(!isStatusFound){
							Assert.assertTrue(isStatusFound, status + " Status is not present under post - "+ postName);
						}
						
						break;		
					}
				
			}
			
		}
	 	
	 	public boolean viewMoreActivities() throws Exception{
			boolean isRecordsFound = false;
			logInfo("Entered into viewMoreActivities() method");
			System.out.println("Entered into viewMoreActivities() method");
			waitOnElement("xpath","//*[@id='widget-recent-activity']/div[3]/a");
			clickOnElement("xpath","//*[@id='widget-recent-activity']/div[3]/a");
			waitOnSpinner();
			waitOnElement("xpath",paneCommunityWallStatus);
			List<WebElement> el = driver().findElements(By.xpath(paneCommunityWallStatus));
			if(el.size()>10){
				isRecordsFound = true;
			}
			return isRecordsFound;
		}
	 	
	 	public boolean navigateUserProfile() throws Exception{
			boolean isUserFound = false;
			logInfo("Entered into navigateUserProfile() method");
			System.out.println("Entered into navigateUserProfile() method");
			waitOnElement("xpath","//div[@class='activities-stream']/div[1]/div/h4/a");
			WebElement user = driver().findElement(By.xpath("//div[@class='activities-stream']/div[1]/div/h4/a"));
			String txtUser = user.getText();
			clickOnElement("xpath","//div[@class='activities-stream']/div[1]/div/h4/a");
			waitOnElement("xpath","//*[@id='page-header']/header/div/div[1]/div[2]");
			WebElement userProfile = driver().findElement(By.xpath("//*[@id='page-header']/header/div/div[1]/div[2]"));
			if(txtUser.contains(userProfile.getText().trim())){
				isUserFound = true;
			}
			return isUserFound;
		}
	 	
	 	public boolean abuseCommunityActivity(String activity) throws Exception{
	 		boolean isAbused = false;
	 		try{
	 			logInfo("Entered into abuseCommunityActivity() method");
				System.out.println("Entered into abuseCommunityActivity() method");
				
				userLogout();
				loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),
						prop.getLocatorForEnvironment(appUrl,"newsLN2"),prop.getLocatorForEnvironment(appUrl,"newsCon2"));	
				
				nav2Community();
				isAbused = abuseActivity(activity,"Report",txtAbuseReport,"First");
	
				userLogout();
				loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN3"),
						prop.getLocatorForEnvironment(appUrl,"newsLN3"),prop.getLocatorForEnvironment(appUrl,"newsCon3"));
				
				nav2Community();
				isAbused = abuseActivity(activity,"Report",txtAbuseReport,"Second");
					
				nav2Community();
				boolean isPhotoFoundInCommunity = verifyPostIsPresent(activity); 
				if(isPhotoFoundInCommunity){
					Assert.assertFalse(isPhotoFoundInCommunity, activity+" activity appeared in community even after reported as abused by 2 users.");
				}
				
	 			userLogout();
				/*loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
						prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));*/	
				
	 		}
	 		catch(Exception ex){
	 			userLogout();
				/*loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
						prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	*/
	 		}
			return isAbused;
		}
	 	
	 	public boolean abuseActivity(String postName, String status ,String reportText,String abuseStatus) throws Exception {
			logInfo("inside abuseActivity() method.");
			System.out.println("Entered into abuseActivity() method");
			boolean isAbusedOnce = false;
			boolean isAbused = false;
			nav2Community();
			waitOnElement("xpath",paneCommunityWall);
			WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
			List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
			boolean isMatchFound = false;
			logInfo("Total posts =" +allMessages.size());				
			System.out.println("Total messages ="+ allMessages.size());
			String before_name = "//div[@class='activities-stream']/div[";
			String after_name = "]/div/div[1]/a";
			//String abuse_report = "]/div[1]/div[contains(@id,'new_report')]";
			String abuse_report = "]/div[1]/div[contains(@id,'new_report')]/form/div/div/div/textarea";
			//String btnReport = "/form/div/div/input";
			String btnReport = "]/div[1]/div[contains(@id,'new_report')]/form/div/div/input";
			String lblReport = "]";
			
			for(int i=1;i<=allMessages.size();i++){
				waitOnElement("xpath",before_name+i+after_name);
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String name = x.getText().trim();
				System.out.println("name "+name);
				if(name.equalsIgnoreCase(postName)){				
					isMatchFound = true;				
					
					List<WebElement> all = driver().findElements(By.xpath(before_name+i+allsStatus));
					System.out.println("All status "+ all.size());
					for (int j=1; j<=all.size(); j++){
						waitOnElement("xpath", allStat);
						WebElement stat = driver().findElement(By.xpath(before_name+i+statusBfr+j+statusAft)) ;
						String actStatus = stat.getText().trim();
						System.out.println("actStatus "+ actStatus);
						if(actStatus.equalsIgnoreCase(status)){
							stat.click();
							Thread.sleep(5000);
							waitOnElement("xpath",before_name+i+abuse_report);
							inputTextClear("xpath",before_name+i+abuse_report);
							inputText("xpath",before_name+i+abuse_report, reportText);
							waitOnElement("xpath",before_name+i+btnReport);
							clickOnButton("xpath",before_name+i+btnReport);
							if(abuseStatus.equalsIgnoreCase("First")){
								confirmationMessage("Report has been recorded");
								waitOnElement("xpath",before_name+i+statusBfr+j+lblReport);
								WebElement txtReport = driver().findElement(By.xpath(before_name+i+statusBfr+j+lblReport)) ;
								if(txtReport.getText().trim().contains("Reported")){
									isAbused = true;
									isAbusedOnce = true;
								}
							}else{
								isAbused = true;
								confirmationMessage("Post has been removed");
							}

							break;
						}						
					}
					if(!isAbused){
						Assert.assertTrue(isAbused, status + " link is not present under post - "+ postName);
					}
						
						break;		
					}
				}
				return isAbused;
			}
	 	
	 	public boolean abuseCommunityActivityOnce(String activity) throws Exception{
	 		boolean isAbusedOnce = false;
	 		try{
	 			logInfo("Entered into abuseCommunityActivityOnce() method");
				System.out.println("Entered into abuseCommunityActivityOnce() method");
				
				userLogout();
				loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),
						prop.getLocatorForEnvironment(appUrl,"newsLN2"),prop.getLocatorForEnvironment(appUrl,"newsCon2"));	
				
				nav2Community();
				isAbusedOnce = abuseActivity(activity,"Report",txtAbuseReport,"First");
				if(!isAbusedOnce){
					Assert.assertTrue(isAbusedOnce, "Unable to reported as abuse "+activity);
				}
				confirmationMessage("Report has been recorded");
				userLogout();
				/*loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
						prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
				*/
	 		}
	 		catch(Exception ex){
	 			userLogout();
				/*loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
						prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	*/
	 		}
	 		return isAbusedOnce;
		}
	 	
	 	public boolean verifyAbusedCommunityActivitesInAdmin(String activity) throws Exception{
	 		boolean isActivityFound = false;
			logInfo("inside verifyAbusedCommunityActivitesInAdmin() method");
			System.out.println("inside verifyAbusedCommunityActivitesInAdmin() method");
			go2AbuseReports();
			waitOnElement("xpath","//*[@id='abuse']/table/tbody/tr");
			List<WebElement> lstAbuse = driver().findElements(By.xpath("//*[@id='abuse']/table/tbody/tr"));
			String before = "//*[@id='abuse']/table/tbody/tr[";
			String after = "]/td[1]/div[2]/a";
			
			if(lstAbuse.size()>0){
				for(int i=1;i<=lstAbuse.size();i++){
					waitOnElement("xpath",before+i+after);
					WebElement el = driver().findElement(By.xpath(before+i+after));
					if(el.getText().equalsIgnoreCase(activity)){
						isActivityFound = true;
						break;
					}
				}
			}
			return isActivityFound;
		}
	 	
	 	public boolean restoreAbusedActivitesInAdmin(String activity,String subject,String reason) throws Exception{
	 		boolean isActivityFound = false;
			logInfo("inside restoreAbusedActivitesInAdmin() method");
			System.out.println("inside restoreAbusedActivitesInAdmin() method");
			go2AbuseReports();
			waitOnElement("xpath","//*[@id='abuse']/table/tbody/tr");
			List<WebElement> lstAbuse = driver().findElements(By.xpath("//*[@id='abuse']/table/tbody/tr"));
			String before = "//*[@id='abuse']/table/tbody/tr[";
			String after = "]/td[1]/div[2]/a";
			
			if(lstAbuse.size()>0){
				for(int i=1;i<=lstAbuse.size();i++){
					waitOnElement("xpath",before+i+after);
					WebElement el = driver().findElement(By.xpath(before+i+after));
					if(el.getText().equalsIgnoreCase(activity)){
						isActivityFound = true;
						String before_restore = "//*[@id='abuse']/table/tbody/tr[";
						String after_restore = "]/td[6]/a[2]/i";
						waitOnElement("xpath",before_restore+i+after_restore);
						WebElement lnkRestore = driver().findElement(By.xpath(before_restore+i+after_restore));
						lnkRestore.click();
						confirmAlert();
						waitOnElement("xpath","//*[@id='_subject']");
						inputText("xpath","//*[@id='_subject']",subject);
						inputText("xpath","//*[@id='_reason']",reason);
						clickOnElement("xpath","//*[@value='Send Email']");
						confirmationMessage("Abuse Report restored successfully.");
						break;
					}
				}
			}
			return isActivityFound;
		}
	 	
	 	public boolean deleteAbusedActivitesInAdmin(String activity,String subject,String reason) throws Exception{
	 		boolean isActivityFound = false;
			logInfo("inside deleteAbusedActivitesInAdmin() method");
			System.out.println("inside deleteAbusedActivitesInAdmin() method");
			
			waitOnElement("xpath","//*[@id='abuse']/table/tbody/tr");
			List<WebElement> lstAbuse = driver().findElements(By.xpath("//*[@id='abuse']/table/tbody/tr"));
			String before = "//*[@id='abuse']/table/tbody/tr[";
			String after = "]/td[1]/div[2]/a";
			
			if(lstAbuse.size()>0){
				for(int i=1;i<=lstAbuse.size();i++){
					WebElement el = driver().findElement(By.xpath(before+i+after));
					if(el.getText().equalsIgnoreCase(activity)){
						isActivityFound = true;
						String before_delete = "//*[@id='abuse']/table/tbody/tr[";
						String after_delete = "]/td[6]/a[1]/i";
						waitOnElement("xpath",before_delete+i+after_delete);
						WebElement lnkDelete = driver().findElement(By.xpath(before_delete+i+after_delete));
						lnkDelete.click();
						confirmAlert();
						waitOnElement("xpath","//*[@id='_subject']");
						inputText("xpath","//*[@id='_subject']",subject);
						inputText("xpath","//*[@id='_reason']",reason);
						clickOnElement("xpath","//*[@value='Send Email']");
						confirmationMessage("Abuse Report deleted successfully.");
						break;
					}
				}
			}
			return isActivityFound;
		}
	 	
	 	public boolean verifyDeletedAbuseReportHistory(String subject,String reason) throws Exception{
	 		boolean isActivityFound = false;
			logInfo("inside verifyDeletedAbuseReportHistory() method");
			System.out.println("inside verifyDeletedAbuseReportHistory() method");
			
			waitOnElement("xpath","//*[@id='abuse_reports_actions']/table/tbody/tr");
			List<WebElement> lstAbuseHistory = driver().findElements(By.xpath("//*[@id='abuse_reports_actions']/table/tbody/tr"));
			String before = "//*[@id='abuse_reports_actions']/table/tbody/tr[";
			String after = "]/td[5]/a";
			String actionTaken = "]/td[7]";
			
			if(lstAbuseHistory.size()>0){
				for(int i=1;i<=lstAbuseHistory.size();i++){
					WebElement el = driver().findElement(By.xpath(before+i+after));
					el.click();
					waitOnElement("xpath","//*[@id='abuse_action_email_modal']/div/div[1]/div[2]/div/div[4]");
					WebElement txtSubject = driver().findElement(By.xpath("//*[@id='abuse_action_email_modal']/div/div[1]/div[2]/div/div[4]"));
					WebElement txtReason = driver().findElement(By.xpath("//*[@id='abuse_action_email_modal']/div/div[1]/div[2]/div/div[5]"));
					
					if(txtSubject.getText().trim().contains(subject) && txtReason.getText().trim().contains(reason)){
						WebElement txtActionTaken = driver().findElement(By.xpath(before+i+actionTaken));
						if(txtActionTaken.getText().contains("Deleted from Community")){
							isActivityFound = true;
							break;
						}
					}
				}
			}
			return isActivityFound;
		}
	 	
	 	public boolean verifyRestoredAbuseReportHistory(String subject,String reason) throws Exception{
	 		boolean isActivityFound = false;
			logInfo("inside verifyRestoredAbuseReportHistory() method");
			System.out.println("inside verifyRestoredAbuseReportHistory() method");
			go2AbuseReports();
			waitOnElement("xpath","//*[@id='abuse_reports_actions']/table/tbody/tr");
			List<WebElement> lstAbuseHistory = driver().findElements(By.xpath("//*[@id='abuse_reports_actions']/table/tbody/tr"));
			String before = "//*[@id='abuse_reports_actions']/table/tbody/tr[";
			String after = "]/td[5]/a";
			String actionTaken = "]/td[7]";
			
			if(lstAbuseHistory.size()>0){
				for(int i=1;i<=lstAbuseHistory.size();i++){
					waitOnElement("xpath",before+i+after);
					WebElement el = driver().findElement(By.xpath(before+i+after));
					el.click();
					/*waitOnSpinner();*/
					Thread.sleep(2000);
					waitOnElement("xpath","//*[@id='abuse_action_email_modal']/div/div[1]/div[2]/div/div[4]");
					WebElement txtSubject = driver().findElement(By.xpath("//*[@id='abuse_action_email_modal']/div/div[1]/div[2]/div/div[4]"));
					WebElement txtReason = driver().findElement(By.xpath("//*[@id='abuse_action_email_modal']/div/div[1]/div[2]/div/div[5]"));
					
					if(txtSubject.getText().trim().contains(subject) && txtReason.getText().trim().contains(reason)){
						waitOnElement("xpath","//*[@id='abuse_action_email_modal']/div/div[1]/div[1]/button");
						clickOnElement("xpath","//*[@id='abuse_action_email_modal']/div/div[1]/div[1]/button");
						Thread.sleep(2000);
						waitOnElement("xpath",before+i+actionTaken);
						WebElement txtActionTaken = driver().findElement(By.xpath(before+i+actionTaken));
						if(txtActionTaken.getText().contains("Restored to Community")){
							isActivityFound = true;
							break;
						}
					}
				}
			}
			return isActivityFound;
		}
	 	
	 	public boolean abuseCommunityStatusActivity(String activity) throws Exception{
	 		boolean isAbused = false;
	 		try{
	 			logInfo("Entered into abuseCommunityStatusActivity() method");
				System.out.println("Entered into abuseCommunityStatusActivity() method");
				
				userLogout();
				loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),
						prop.getLocatorForEnvironment(appUrl,"newsLN2"),prop.getLocatorForEnvironment(appUrl,"newsCon2"));	
				
				nav2Community();
				isAbused = abuseStatusActivity(activity,"Report",txtAbuseReport,"First");
	
				userLogout();
				loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN3"),
						prop.getLocatorForEnvironment(appUrl,"newsLN3"),prop.getLocatorForEnvironment(appUrl,"newsCon3"));
				
				nav2Community();
				isAbused = abuseStatusActivity(activity,"Report",txtAbuseReport,"Second");
					
				nav2Community();
				boolean isPhotoFoundInCommunity = verifyPostIsPresent(activity); 
				if(isPhotoFoundInCommunity){
					Assert.assertFalse(isPhotoFoundInCommunity, activity+" activity appeared in community even after reported as abused by 2 users.");
				}
				
	 			userLogout();
				/*loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
						prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	*/
				
	 		}
	 		catch(Exception ex){
	 			userLogout();
				/*loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
						prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	*/
	 		}
			return isAbused;
		}
	 	
	 	public boolean abuseStatusActivity(String postName, String status ,String reportText,String abuseStatus) throws Exception {
			logInfo("inside abuseStatusActivity() method.");
			System.out.println("Entered into abuseStatusActivity() method");
			boolean isAbusedOnce = false;
			boolean isAbused = false;
			nav2Community();
			waitOnElement("xpath",paneCommunityWall);
			WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
			List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
			boolean isMatchFound = false;
			logInfo("Total posts =" +allMessages.size());				
			System.out.println("Total messages ="+ allMessages.size());
			String before_name = "//div[@class='activities-stream']/div[";
			String after_name = "]/div/div[1]";
			//String abuse_report = "]/div[1]/div[contains(@id,'new_report')]";
			String abuse_report = "]/div[1]/div[contains(@id,'new_report')]/form/div/div/div/textarea";
			//String btnReport = "/form/div/div/input";
			String btnReport = "]/div[1]/div[contains(@id,'new_report')]/form/div/div/input";
			String lblReport = "]";
			
			for(int i=1;i<=allMessages.size();i++){
				waitOnElement("xpath",before_name+i+after_name);
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String name = x.getText().trim();
				System.out.println("name "+name);
				if(name.equalsIgnoreCase(postName)){				
					isMatchFound = true;				
					
					List<WebElement> all = driver().findElements(By.xpath(before_name+i+allsStatus));
					System.out.println("All status "+ all.size());
					for (int j=1; j<=all.size(); j++){
						waitOnElement("xpath", allStat);
						WebElement stat = driver().findElement(By.xpath(before_name+i+statusBfr+j+statusAft)) ;
						String actStatus = stat.getText().trim();
						System.out.println("actStatus "+ actStatus);
						if(actStatus.equalsIgnoreCase(status)){
							stat.click();
							Thread.sleep(3000);
							waitOnElement("xpath",before_name+i+abuse_report);
							inputTextClear("xpath",before_name+i+abuse_report);
							inputText("xpath",before_name+i+abuse_report, reportText);
							waitOnElement("xpath",before_name+i+btnReport);
							clickOnButton("xpath",before_name+i+btnReport);
							if(abuseStatus.equalsIgnoreCase("First")){
								confirmationMessage("Report has been recorded");
								waitOnElement("xpath",before_name+i+statusBfr+j+lblReport);
								WebElement txtReport = driver().findElement(By.xpath(before_name+i+statusBfr+j+lblReport)) ;
								if(txtReport.getText().trim().contains("Reported")){
									isAbused = true;
									isAbusedOnce = true;
								}
							}else{
								isAbused = true;
								confirmationMessage("Post has been removed");
							}

							break;
						}						
					}
					if(!isAbused){
						Assert.assertTrue(isAbused, status + " link is not present under post - "+ postName);
					}
						
						break;		
					}
				}
				return isAbused;
			}
	 	
	 	public boolean abuseCommunityStatusActivityOnce(String activity) throws Exception{
	 		boolean isAbusedOnce = false;
	 		
	 		try{
	 			logInfo("Entered into abuseCommunityStatusActivityOnce() method");
				System.out.println("Entered into abuseCommunityStatusActivityOnce() method");
				
				userLogout();
				loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),
						prop.getLocatorForEnvironment(appUrl,"newsLN2"),prop.getLocatorForEnvironment(appUrl,"newsCon2"));	
				
				nav2Community();
				isAbusedOnce = abuseStatusActivity(activity,"Report",txtAbuseReport,"First");
				if(!isAbusedOnce){
					Assert.assertTrue(isAbusedOnce, "Unable to reported as abuse "+activity);
				}
				confirmationMessage("Report has been recorded");
				userLogout();
	 		}
	 		catch(Exception ex){
	 			userLogout();
	 		}
	 		return isAbusedOnce;
		}
	 	
	 	public void manageActionsOnCommunityStatusComment(String postName, String comment, String commentUpdated, String action) throws Exception {
			logInfo("inside manageActionsOnCommunityStatusComment() method.");
			System.out.println("inside manageActionsOnCommunityStatusComment() method.");
			nav2Community();
			waitOnElement("xpath",paneCommunityWall);
			WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
			List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
			boolean isMatchFound = false;
			boolean iscommentFound = false;
			logInfo("Total posts =" +allMessages.size());				
			System.out.println("Total messages ="+ allMessages.size());
			
			String before_name = "//div[@class='activities-stream']/div[";
			String after_name = "]/div/div[1]";
			String comments = "]/div[1]/div[@class='comments']/div[2]/div[contains(@id,'comment_body')]";
			
			for(int i=1;i<=allMessages.size();i++){
				waitOnElement("xpath",before_name+i+after_name);
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String name = x.getText().trim();
				System.out.println("name "+name);
				if(name.equalsIgnoreCase(postName)){				
					isMatchFound = true;		
					List<WebElement> lstComments = driver().findElements(By.xpath(before_name+i+comments));
					if(lstComments.size()>0){
						
						String before_comment = "]/div[1]/div[@class='comments']/div[2]/div[contains(@id,'comment_body')][";
						String after_comment = "]/div[2]/p";
						
						for(int j=1;j<=lstComments.size();j++){
							WebElement elComment = driver().findElement(By.xpath(before_name+i+before_comment+j+after_comment));
							
							if(action.equalsIgnoreCase("Edit")){
								if(elComment.getText().equalsIgnoreCase(comment)){
									iscommentFound = true;
								
									String before_edit = "]/div[2]/span[2]/a";
									WebElement editComment = driver().findElement(By.xpath(before_name+i+before_comment+j+before_edit));
									editComment.click();
									
									String before_txtComment = "]/div[2]/p/form/div/div/div/textarea";
									waitOnElement("xpath",before_name+i+before_comment+j+before_txtComment);
									inputTextClear("xpath",before_name+i+before_comment+j+before_txtComment);
									inputText("xpath",before_name+i+before_comment+j+before_txtComment,commentUpdated);
									
									String txtCommentPost = "]/div[2]/p/form/div/div/span";
									waitOnElement("xpath",before_name+i+before_comment+j+txtCommentPost);
									clickOnElement("xpath",before_name+i+before_comment+j+txtCommentPost);
									confirmationMessage("Comment was successfully updated.");
									break;
								}
										
							}
							else if(action.equalsIgnoreCase("Delete")){
								if(elComment.getText().equalsIgnoreCase(commentUpdated)){
									iscommentFound = true;
									String before_delete = "]/div[2]/span[1]/a";
									WebElement deleteComment = driver().findElement(By.xpath(before_name+i+before_comment+j+before_delete));
									deleteComment.click();
									confirmationMessage("Comment Deleted");
									break;
								}
			
							}
							if(!iscommentFound){
								Assert.assertTrue(iscommentFound, comment + " comment not found.");
							}
						}
					}
						
					break;		
				}
				
			}
			if(!isMatchFound){
				Assert.assertTrue(isMatchFound, postName + " post is not present.");
			}
		}
	 	
	 	public void selectFacebookIcon() throws Exception {
			logInfo("Entered into selectFacebookIcon() method");
			waitOnElement("cssSelector", facebookIconComm);
			clickOnElement("cssSelector", facebookIconComm);
		}
}