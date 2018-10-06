package vibe.myprofile.tests;
import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import common.TestBase;


public class MyProfileMethods extends TestBase {
	
	
	public void nav2profile() throws InterruptedException {
		logInfo("inside nav2profile() method");
		driver().navigate().to(appUrl + "community/profile");		
		
	}
     
     public void navigateMyProfileAccount(String AccountName) throws Exception{	 
    		logInfo("Edit my Profile and navigate to Notifications page.");
    		
    		driver().navigate().to(appUrl + "pyr_core/account#account-info");
    		boolean isAccountPresent = false;
    		
    		waitOnElement("xpath","accountPanel");
    		WebElement e = driver().findElement(By.xpath(accountPanel));
    		List<WebElement> accountPanel = e.findElements(By.tagName("li"));
    		System.out.println("size "+ accountPanel.size());
    		
    		String bfr = "div.panel.panel-columnar.panel-widget > div > ul.panel-nav > li:nth-child(";
    		String aft = ") > a";
    				
    		for (int i=1 ; i <= accountPanel.size(); i++){		    		 
    			WebElement eLinkName  = driver().findElement(By.cssSelector(bfr+i+aft));
    			String actLink = eLinkName.getText().trim();
    			if(actLink.equalsIgnoreCase(AccountName)){
    				logInfo(AccountName + " match found in notifications page.");
    				isAccountPresent= true;
    				eLinkName.click();
    				break;
    			}		
    		} 	
    		
    		if (isAccountPresent ==false){
    				logInfo(AccountName + " match not found in notifications page.");
    				 Assert.assertTrue(isAccountPresent, AccountName + " match not found in notifications page.");
    		 }	 
     	} 
		
		public void addBlogPost(String postName) throws Exception{
			logInfo("Entered into addBlogPost() method");
			System.out.println("Entered into addBlogPost() method");
			waitOnElement("linkText","Add Blog Post");
			clickOnElement("linkText","Add Blog Post");
			waitOnElement("cssSelector",inputAddPostTitle);
			inputText("cssSelector",inputAddPostTitle,postName); 		
			composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new blog");
			inputText("cssSelector",inputAddPostExcerpt,addPostExcerpt_text);
			selectFromDropdown("cssSelector",drpdnAddPostVisibility,"byVisibleText",visibility_text_community);
			selectRadioOrCheckbox("cssSelector", chkPublishPost);
			clickOnButton("cssSelector",btnCreatePost);
			confirmationMessage("Your post has been created");
			String url = driver().getCurrentUrl();
			if(url.contains("idlife-stage")){
				String idLife =  "//*[contains(@id,'rules_web_alert_modal_')]/div/div[1]/div/div/div[3]/button";
				waitOnElement("xpath",idLife);
				clickOnElement("xpath",idLife);
				confirmationMessage("Your post has been created");
			}	
			
		}
		
		public void addDraftBlogPost(String postName) throws Exception{
			logInfo("Entered into addDraftBlogPost() method");
			System.out.println("Entered into addDraftBlogPost() method");
			waitOnElement("linkText","Add Blog Post");
			clickOnElement("linkText","Add Blog Post");
			waitOnElement("cssSelector",inputAddPostTitle);
			inputText("cssSelector",inputAddPostTitle,postName); 		
			composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new blog");
			inputText("cssSelector",inputAddPostExcerpt,addPostExcerpt_text);
			selectFromDropdown("cssSelector",drpdnAddPostVisibility,"byVisibleText",visibility_text_community);
			clickOnButton("cssSelector",btnCreatePost);
			String url = driver().getCurrentUrl();
			if(url.contains("idlife-stage")||url.contains("retro")){
				String idLife =  "//*[contains(@id,'rules_web_alert_modal_')]/div/div[1]/div/div/div[3]/button";
				waitOnElement("xpath",idLife);
				clickOnElement("xpath",idLife);
				if(!driver().getCurrentUrl().contains("retro")){
					confirmationMessage("Post is created");
				}else{
					confirmationMessage("Your post has been created");
				}
				
			}	
			
		}
		
		public boolean verifyDraftPost(String postName) throws Exception{
			logInfo("inside verifyDraftPost() method..");
			nav2profile();	
			clickOnLink("linkText", lnkBlog);
			boolean isPostPresent = false;
			waitOnElement("xpath","//*[@id='drafts']/div[2]/article");
			int draftsCount = driver().findElements(By.xpath("//*[@id='drafts']/div[2]/article")).size();
			String before = "//*[@id='drafts']/div[2]/article[";
			String after = "]/div[1]/h3/a";
			for(int i=1; i <= draftsCount; i++ ){
				WebElement post = driver().findElement(By.xpath(before+i+after));
				String draftPost = post.getText().trim();
				if(draftPost.equalsIgnoreCase(postName.trim())){
					isPostPresent = true;
					break;
				}
				else{
					isPostPresent = false;
				}
			}
			System.out.println("Post "+postName+" is located in drafts.");
			logInfo("Post "+postName+" is located in drafts.");
			return isPostPresent;
			
		}
		
		public String getSrcForImage(String img){
			logInfo("inside getSrcForImage() method.");
			WebElement eimg = driver().findElement(By.xpath(img));  
			String src = eimg.getAttribute("src");
			src = src.trim();
			String tagName = eimg.getTagName();
			String alt = eimg.getAttribute("alt");
			System.out.println("Src =" +src);
			System.out.println("TagName =" +tagName);
			System.out.println("Alt =" +alt);
			return alt;
		}
		
		 public void changeProfileAvatar() throws IOException, AWTException, Exception{
	    	logInfo("inside changeProfileAvatar() method.");
	    	clickOnElement("xpath", "//div[@class='profile-update-picture']/a/i");
	    	
	    	waitOnElement("cssSelector", profileAvatar);
	    	uploadFile("Image", profileAvatar);
	    	    	
	    	waitOnElement("cssSelector",saveButton);
	    	clickOnElement("cssSelector",saveButton);
	    	waitOnSpinner();
	    	waitOnElement("cssSelector",photoSubmit);
	    	clickOnElement("cssSelector",photoSubmit);

		  }
		 
		 public void changeCoverPhoto() throws IOException, AWTException, Exception{
	    	logInfo("inside changeCoverPhoto() method.");
	    	verifyElementPresent("xpath", "//div[@class='profile-update-cover-photo']/a/i");
	    	clickOnElement("xpath", "//div[@class='profile-update-cover-photo']/a/i");
	    	
	    	waitOnElement("cssSelector",changeCoverPic);
	    	uploadFile("Image", changeCoverPic);
	    
	    	waitOnElement("cssSelector",saveBtnOfCover);
	    	clickOnElement("cssSelector",saveBtnOfCover);
	    	
	    	
	    	waitOnElement("cssSelector",photoSubmit);
	    	clickOnElement("cssSelector",photoSubmit);
	    	
	    }  
		 
		public boolean viewMoreActivities() throws Exception{
			boolean isRecordsFound = false;
			logInfo("Entered into viewMoreActivities() method");
			System.out.println("Entered into viewMoreActivities() method");
			waitOnElement("xpath","//*[@id='profile-timeline']/div[3]/a");
			clickOnElement("xpath","//*[@id='profile-timeline']/div[3]/a");
			waitOnElement("cssSelector",actMsg);
			List<WebElement> el = driver().findElements(By.cssSelector(actMsg));
			if(el.size()>10){
				isRecordsFound = true;
			}
			return isRecordsFound;
		}
		
		public boolean verifyInMyRecentActivity(String activity) throws Exception{
			logInfo("Entered into verifyMyRecentActivity() method");
			nav2profile();
			waitOnElement("cssSelector",actMsg);
			List <WebElement> act = driver().findElements(By.cssSelector(actMsg));
			System.out.println("Activities size "+ act.size());
			boolean isActivityPresent = false;
			for (WebElement acts : act){
				if (acts.getText().trim().contains(activity)){
					isActivityPresent = true;
					System.out.println(acts.getText()+" matches");
					break;
				}
				
			} if(isActivityPresent==false){			
				Assert.assertTrue(isActivityPresent, activity + " status post match not found in  myProfile-Recent Activities.");
			}
			return isActivityPresent;		

		}
		
		public void verifyPostNotPresentInMyRecentActivity(String activity){
			logInfo("Entered into verifyPostNotPresentInMyRecentActivity() method");
			List <WebElement> act = driver().findElements(By.cssSelector(actMsg));
			System.out.println("Activities size "+ act.size());
			boolean isActivityPresent = true;
			for (WebElement acts : act){
				System.out.println(acts.getText());			
				if (acts.getText().contains(activity)){
					isActivityPresent = false;
					System.out.println(acts.getText()+" matches");
					Assert.assertTrue(isActivityPresent, activity + " status post matches in myProfile.");				
					break;
				}
				
			} if(isActivityPresent==true){
				System.out.println("Suceesss! Not matched in Recent activities");
				
			}		

		}
		
		
		public void selectActivityTab(String activityTab) {			
			logInfo("Entered into selectActivityTab() method");
			boolean isTabPresent = false;
			   List <WebElement> act = driver().findElements(By.cssSelector(activityLinks));
			   for (WebElement activity :act){
				   String actvty = activity.getText().trim();
				   if (actvty.equalsIgnoreCase(activityTab)){
					   isTabPresent =true;
					   activity.click();
					   break;
				   }
			   }if(isTabPresent==false){
				   Assert.assertTrue(isTabPresent, activityTab + " - activity tab is not present");
			   }
			
		}
		
		//Update details by selecting post and then edit to update.
		 public void updateBlogPostInProfile(String postName, String activityTab, String NewBlogName) throws Exception{
			 logInfo("Entered into updateBlogInProfile() method");
			   boolean isPresent = false;	
			   selectActivityTab(activityTab);
			   waitOnElement("cssSelector",blgList);
			   List <WebElement> blg = driver().findElements(By.cssSelector(blgList));
			   for (WebElement blogs : blg){
				   if (blogs.getText().equalsIgnoreCase(postName)){				   
					   isPresent =true;
					   blogs.click();
					   waitOnElement("linkText", "Edit");
					   clickOnLink("linkText", "Edit");		
					   waitOnElement("cssSelector",inputAddPostTitle);
					   inputTextClear("cssSelector",inputAddPostTitle);
					   inputText("cssSelector",inputAddPostTitle,NewBlogName); 					  
					   clickOnButton("cssSelector",btnCreatePost);
					   confirmationMessage("Post was successfully updated.");
					   break;
				   }
				   
			   }if(isPresent==false){
				   Assert.assertTrue(isPresent, postName + " - post is not present");
			   }   
			   
		 }
		 
		 
		//Update details by selecting edit under post.
		 public void updatePostByEditIconInProfile(String postName, String activityTab, String NewBlogName) throws Exception{
			 logInfo("Entered into updatePostInProfile() method");
			   boolean isPresent = false;	
			   selectActivityTab(activityTab);
			   waitOnElement("cssSelector",blgList);
			   List <WebElement> post = driver().findElements(By.cssSelector(blgList));
			   for (int i=1; i<=post.size(); i++){
				   WebElement poster = driver().findElement(By.cssSelector(postbfr+i+postAft));
			       System.out.println(poster.getText().trim()); 
				   if (poster.getText().trim().equalsIgnoreCase(postName)){				   
					   isPresent =true;
					   WebElement edit = driver().findElement(By.cssSelector(postbfr+i+editIcon));
					   edit.click();					  		   
					   inputTextClear("cssSelector",inputAddPostTitle);
					   inputText("cssSelector",inputAddPostTitle,NewBlogName); 					  
					   clickOnButton("cssSelector",btnCreatePost);
					   confirmationMessage("Post was successfully updated.");
					   break;
				   }
				   
			   }if(isPresent==false){
				   Assert.assertTrue(isPresent, postName + " - post is not present");
			   }   
			   
		 }
		
		//Update details by selecting edit under post.
		 public void deletePostByIconInProfile(String activityTab, String postName) throws Exception{
			 logInfo("Entered into updatePostInProfile() method");
			   boolean isPresent = false;	
			   selectActivityTab(activityTab);
			   waitOnElement("cssSelector",blgList);
			   List <WebElement> post = driver().findElements(By.cssSelector(blgList));
			   for (int i=1; i<=post.size(); i++){
				   WebElement poster = driver().findElement(By.cssSelector(postbfr+i+postAft));
			       System.out.println(poster.getText().trim()); 
				   if (poster.getText().trim().equalsIgnoreCase(postName)){				   
					   isPresent =true;
					   WebElement edit = driver().findElement(By.cssSelector(postbfr+i+deletIcon));
					   edit.click();					  		   
					   confirmOK();
					   break;
				   }
				   
			   }if(isPresent==false){
				   Assert.assertTrue(isPresent, postName + " - post is not present");
			   }   
			   
		 }
		 
		 public void deleteBlogPost(String activityTab, String postName) throws Exception{
			 logInfo("Entered into deletePostBylink() method");
			   boolean isPresent = false;	
			   selectActivityTab(activityTab);
			   waitOnElement("cssSelector",blgList);
			   List <WebElement> post = driver().findElements(By.cssSelector(blgList));
			   for (int i=1; i<=post.size(); i++){
				   WebElement poster = driver().findElement(By.cssSelector(postbfr+i+postAft));
			       System.out.println(poster.getText().trim()); 
				   if (poster.getText().trim().equalsIgnoreCase(postName)){				   
					   isPresent =true;
					   poster.click();
					   verifyElementPresent("linkText", "Delete");
					   WebElement delete = driver().findElement(By.linkText("Delete"));
						delete.click();
						confirmOK();
						confirmationMessage("Post was successfully deleted.");
						break;			   
				   }			 
		 }if (isPresent==false){
			 Assert.assertTrue(isPresent, postName + " - post is not present");
		 			}
		 } 
		 
		 public void deleteDraftBlogPost(String activityTab, String postName) throws Exception{
			 logInfo("Entered into deleteDraftBlogPost() method");
			 System.out.println("Entered into deleteDraftBlogPost() method");
			   boolean isPresent = false;	
			   selectActivityTab(activityTab);
			   waitOnElement("cssSelector",".post-title>a");
			   List <WebElement> post = driver().findElements(By.cssSelector(".post-title>a"));
			   String postbfr = "//*[@id='drafts']/div[2]/article[";
			   String postAft = "]/div[1]/h3/a";
			   for (int i=1; i<=post.size(); i++){
				   WebElement poster = driver().findElement(By.xpath(postbfr+i+postAft));
			       System.out.println(poster.getText().trim()); 
				   if (poster.getText().trim().equalsIgnoreCase(postName)){				   
					   isPresent =true;
					   poster.click();
					   verifyElementPresent("linkText", "Delete");
					   WebElement delete = driver().findElement(By.linkText("Delete"));
					   delete.click();
					   confirmOK();
					   confirmationMessage("Post was successfully deleted.");
					   break;			   
				   }			 
		 }if (isPresent==false){
			 Assert.assertTrue(isPresent, postName + " - post is not present");
		 			}
		 } 
		 
		 public void editDraftBlogPost(String activityTab, String postName,String postNameUpdated) throws Exception{
			 logInfo("Entered into editDraftBlogPost() method");
			 System.out.println("Entered into editDraftBlogPost() method");
			   boolean isPresent = false;	
			   selectActivityTab(activityTab);
			   waitOnElement("cssSelector",".post-title>a");
			   List <WebElement> post = driver().findElements(By.cssSelector(".post-title>a"));
			   String postbfr = "//*[@id='drafts']/div[2]/article[";
			   String postAft = "]/div[1]/h3/a";
			   for (int i=1; i<=post.size(); i++){
				   WebElement poster = driver().findElement(By.xpath(postbfr+i+postAft));
			       System.out.println(poster.getText().trim()); 
				   if (poster.getText().trim().equalsIgnoreCase(postName)){				   
					   isPresent =true;
					   poster.click();
					   Thread.sleep(3000);
					   verifyElementPresent("linkText", "Edit");
					   WebElement edit = driver().findElement(By.linkText("Edit"));
					   edit.click();
					   waitOnElement("xpath","//*[@id='pyr_community_post_title']");
					   inputTextClear("xpath","//*[@id='pyr_community_post_title']");
					   inputText("xpath","//*[@id='pyr_community_post_title']",postNameUpdated);
					   waitOnElement("xpath",btnUpdateDraft);
					   clickOnElement("xpath",btnUpdateDraft);
					   waitOnSpinner();
					   confirmationMessage("Post was successfully updated.");
					   break;			   
				   }			 
			   }
			   if (!isPresent){
				 Assert.assertTrue(isPresent, postName + " - post is not present");
			   }
		 } 
		 
		 public String getAccountProfileFirstName() throws Exception, Exception{
				logInfo("Entered into getAccountProfileFirstName () method ");
				navigateMyProfileAccount("Community Profile");				
				waitOnElement("xpath",txtDisplayFirstName);
				/*clickOnElement ("cssSelector", editFName);			
				Thread.sleep(3000);
				waitOnElement("cssSelector",inputCommunityFName);
				inputTextClear("cssSelector",inputCommunityFName);
				Thread.sleep(3000);
				inputText("cssSelector",inputCommunityFName,custFNText);
				clickOnButton("xpath",btnCommunityFNameSave);*/				
				WebElement fn = driver().findElement(By.xpath(txtDisplayFirstName));
				String FirstName = fn.getText();				
				return FirstName;
				
			}

		   public void delete() throws Exception{
					
					try{	
						Thread.sleep(5000);
						List <WebElement> del = driver().findElements(By.cssSelector(deletelnk));				
						for (WebElement dels : del){					
							if (dels.getText().equalsIgnoreCase("Delete")){						
								dels.click();
									
								waitOnElement("cssSelector", comDeleteOk);		
								clickOnButton("cssSelector", comDeleteOk);						
								break;
							}					
						}				
					}catch(Exception e){				
						logger.error("Failed !! Not able to deleted .");
						
					}
				}

		   
		   public boolean addProfilePhoto() throws Exception{
		  		logInfo("inside addProfilePhoto() method..");
		  		boolean isProfilePhotoAdded = false;
		  		clickOnLink("linkText", CHANGEPROFILEPHOTO_text);
		  		implicityWaits(3);
		  		waitOnElement("cssSelector", btnBrowseProfilePhoto);
		  		 uploadFile("Image", btnBrowseProfilePhoto);
		      	/*Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\ProfileImage.exe");*/
		      	implicityWaits(5);
		      	driver().findElement(By.xpath(btnSaveProfilePhoto)).click();
		      	implicityWaits(5);
		      	driver().findElement(By.xpath(btnProfileSubmit)).click();
		      	implicityWaits(5);
		      	waitOnElement("xpath", imageSrc);
		      	WebElement element = driver().findElement(By.xpath(imageSrc));
		      	String imageSrc = element.getAttribute("alt");
		      	if(!imageSrc.contains(txtRemoveImage))
		      		isProfilePhotoAdded = true;
		      	return isProfilePhotoAdded;
		  	}
		   
		   public boolean validateProfilePhoto() throws Exception {
				logInfo("inside validateProfilePhoto() method..");
				boolean isPhotoValidated = false;
				clickOnLink("linkText", CHANGEPROFILEPHOTO_text);
				implicityWaits(3);
		    	waitOnElement("cssSelector", btnBrowseProfilePhoto);
		    	 uploadFile("pdf", btnBrowseProfilePhoto);
		    /*	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_PDF.exe");*/
		    	implicityWaits(5);
		    	driver().findElement(By.xpath(btnSaveProfilePhoto)).click();
		    	implicityWaits(10);
		    	String txtCoverPhoto = driver().findElement(By.xpath(ValidatePhoto)).getText();
		    	if(txtCoverPhoto.equalsIgnoreCase(txtValidateProfilePhoto))
		    		isPhotoValidated = true;
		    	return isPhotoValidated;
			}
		   

			public void AddPhotoFromPhotos(String photoTitle) throws Exception{	
				logInfo("Inside AddPhotoFromPhotos() Method");
				System.out.println("Inside AddPhotoFromPhotos() Method");
				nav2profile();
				clickOnLink("linkText","Photos");
				waitOnSpinner();
				clickOnLink("linkText", "Add Photo");
				waitOnElement("xpath",inputPhotoURL);
				inputText("xpath",inputPhotoURL,inputPhotoURL_text);	
				inputText("xpath",inputPhotoTitle,photoTitle);
				inputText("xpath",inputPhotoDesc,inputPhotoDesc_text);
				inputText("xpath",inputPhotoTags,inputPhotoTags_text);	
				clickOnButton("xpath",btnSharePhoto);	
				waitOnSpinner();
				confirmationMessage("Your photo has been added");
			}
			
			public boolean verifyPhotosInPhotosSection(String photoTitle) throws Exception{
				logInfo("Inside verifyPhotosInPhotosSection() Method");
				System.out.println("Inside verifyPhotosInPhotosSection() Method");
				boolean isPhotoFound = false;
				nav2profile();
				clickOnLink("linkText","Photos");
				List<WebElement> photos = driver().findElements(By.xpath("//*[@id='profile_photos']/div[2]/div/div"));
				if(photos.size()>0){
					String before = "//*[@id='profile_photos']/div[2]/div/div[";
					String after = "]/span";
					for(int i=1;i<=photos.size();i++){
						WebElement photo = driver().findElement(By.xpath(before+i+after));
						if(photo.getText().trim().equalsIgnoreCase(photoTitle)){
							isPhotoFound = true;
							break;
						}
					}
				}
				return isPhotoFound;
			}
			
			public void AddVideoFromVideos(String videoTitle) throws Exception{
				logInfo("inside AddVideoFromVideos() method");
				System.out.println("inside AddVideoFromVideos() method");
				nav2profile();
				clickOnLink("linkText", "Videos");
				waitOnSpinner();
				clickOnLink("linkText", "Add Video");
				waitOnElement("xpath",inputVideoURL);
				inputText("xpath",inputVideoURL,inputVideoURL_text);
				inputText("xpath",inputVideoTitle,videoTitle);
				inputText("xpath",inputVideoDesc,inputVideoDesc_text);
				inputText("xpath",inputVideoTags,inputVideoTags_text);
				selectFromDropdown("cssSelector",drpdnVisibilityVideoSettings,"byVisibleText",drpdnVisibilityVideoSettings_text);
				clickOnButton("cssSelector",shareVideoButton);
				waitOnSpinner();
				confirmationMessage("Your video has been added");
			}
			
			public boolean verifyVideosInVideosSection(String videoTitle) throws Exception{
				logInfo("Inside verifyVideosInVideosSection() Method");
				System.out.println("Inside verifyVideosInVideosSection() Method");
				boolean isVideoFound = false;
				nav2profile();
				clickOnLink("linkText","Videos");
				List<WebElement> photos = driver().findElements(By.xpath("//*[@id='profile_videos']/div[2]/div/div"));
				if(photos.size()>0){
					String before = ".//*[@id='profile_videos']/div[2]/div/div[";
					String after = "]/span";
					for(int i=1;i<=photos.size();i++){
						WebElement photo = driver().findElement(By.xpath(before+i+after));
						if(photo.getText().trim().equalsIgnoreCase(videoTitle)){
							isVideoFound = true;
							break;
						}
					}
				}
				return isVideoFound;
			}
			
			
			
}