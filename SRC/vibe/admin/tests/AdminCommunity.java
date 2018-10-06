package vibe.admin.tests;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import common.LoginCredentials;
import common.Priority;
import common.TestBase;
import common.EnvProperties;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.people.tests.PeopleMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(5)
public class AdminCommunity extends CommunityMethods {
	
	@Test(priority=501)
	public void FeatureCommunityPhoto() throws Exception{
		logInfo("inside FeatureCommunityPhoto() Test");
		System.out.println("inside FeatureCommunityPhoto() Test");
		nav2Community();
		addCommunityPhoto(inputPhotoTitle_text, visibility_text_community);	
		boolean isPostFound = verifyPostIsPresent(inputPhotoTitle_text); 
		if(isPostFound){
			setStatusForPosts(inputPhotoTitle_text, "Feature");
			verifyPostActivityLinks(inputPhotoTitle_text, "Unfeature");
			boolean isPhotoFound = verifyPostIsPresent(inputPhotoTitle_text);
			if (isPhotoFound){
				deleteCommunityPost(inputPhotoTitle_text);
				postNotToBePresent(inputPhotoTitle_text);				
			}		
		
		}
	}

	@Test(priority=502)
	public void FeatureCommunityVideo() throws Exception{
		logInfo("inside FeatureCommunityVideo() Test..");
		System.out.println("inside FeatureCommunityVideo() Test");
		nav2Community();
		addCommunityVideo(videoTitle_text,visibility_text_community );		
		boolean isVideoFound = verifyPostIsPresent(videoTitle_text);
		if(isVideoFound){			
			setStatusForPosts(videoTitle_text, "Feature");
			verifyPostActivityLinks(videoTitle_text, "Unfeature");
			boolean isPostFound = verifyPostIsPresent(videoTitle_text);
			if (isPostFound){
				deleteCommunityPost(videoTitle_text);
				postNotToBePresent(videoTitle_text);				
			}		
		}
	}	
	
	@Test(priority=503)
	public void FeatureCommunityBlog() throws Exception{
		logInfo("inside FeatureCommunityBlog() Test");
		System.out.println("inside FeatureCommunityBlog() Test");
		nav2Community();
		addBlogPost(addPostTitle_text);
		boolean isBlogPostFound = verifyPostIsPresent(addPostTitle_text);	
		if(isBlogPostFound){			
			setStatusForPosts(addPostTitle_text, "Feature");
			verifyPostActivityLinks(addPostTitle_text, "Unfeature");
			boolean isPostFound = verifyPostIsPresent(addPostTitle_text);
			if (isPostFound){
				deleteCommunityPost(addPostTitle_text);
				postNotToBePresent(addPostTitle_text);				
			}		
		}
	}	

	
}
