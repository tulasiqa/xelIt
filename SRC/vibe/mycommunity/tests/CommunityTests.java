package vibe.mycommunity.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.SocialNetWorksMethods;
import common.EnvProperties;
import vibe.inbox.tests.InboxMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(105)
public class CommunityTests extends CommunityMethods {	
	
	MyProfileMethods profile= new MyProfileMethods();

	@Test(priority= 10501)
	public void AddPhotoInCommunity() throws Exception{
		logInfo("inside AddPhotoInCommunity() Test");	
		System.out.println("inside AddPhotoInCommunity() Test");	
		nav2Community();
		addCommunityPhotoFromMachine(photoTitle_text, visibilitySettings_text);
		nav2Community();
		boolean isPhotoFoundInCommunity = verifyPostIsPresent(photoTitle_text); 
		if(!isPhotoFoundInCommunity){
			logInfo(photoTitle_text + " photo not found in community wall page.");
			Assert.assertTrue(isPhotoFoundInCommunity, photoTitle_text + " photo not found in community wall page.");
		}
	}

	@Test(priority= 10502)
	public void EditPhotoInCommunity() throws Exception{
		logInfo("inside EditPhotoInCommunity() Test");	
		System.out.println("inside EditPhotoInCommunity() Test");	
		nav2Community();
		boolean isPhotoFoundInCommunity = verifyPostIsPresent(photoTitle_text); 
		if(isPhotoFoundInCommunity){
			logInfo(photoTitle_text + " photo found in community wall page.");
			updatePhoto(photoTitle_text,photoTitle_text+"_updated");
		}else {
			logInfo(photoTitle_text + " photo not found in community wall page.");
			Assert.assertTrue(isPhotoFoundInCommunity, photoTitle_text + " photo not found in community wall page.");
		}
	}
	
	@Test(priority= 10503)
	public void DeletePhotoInCommunity() throws Exception{
		logInfo("inside DeletePhotoInCommunity() Test");	
		System.out.println("inside DeletePhotoInCommunity() Test");	
		nav2Community();
		boolean isPhotoFoundInCommunity = verifyPostIsPresent(photoTitle_text+"_updated"); 
		if(isPhotoFoundInCommunity){
			logInfo(photoTitle_text+"_updated photo found in community wall page.");
			deleteCommunityPost(photoTitle_text+"_updated");
		}else {
			logInfo(photoTitle_text + "_updated photo not found in community wall page.");
			Assert.assertTrue(isPhotoFoundInCommunity, photoTitle_text + "_updated photo not found in community wall page.");
		}
	}
	
	@Test(priority= 10504)
	public void AddCommunityPhotoWithURL() throws Exception{
		logInfo("inside AddCommunityPhotoWithURL() Test");	
		System.out.println("inside AddCommunityPhotoWithURL() Test");	
		nav2Community();
		addCommunityPhoto(photoTitle_text, visibilitySettings_text);
		nav2Community();
		boolean isPhotoFoundInCommunity = verifyPostIsPresent(photoTitle_text); 
		if(!isPhotoFoundInCommunity){
			logInfo(photoTitle_text + " photo not found in community wall page.");
			Assert.assertTrue(isPhotoFoundInCommunity, photoTitle_text + " photo not found in community wall page.");
		}

	}
	
	@Test(priority=10505)
	public void FilterCommunityPhotos() throws Exception{
		logInfo("inside FilterCommunityPhotos() Test");			
		System.out.println("inside FilterCommunityPhotos() Test");			
		boolean isPhotosFiltered = filterByType("Photo",photoTitle_text);
		if(!isPhotosFiltered){
			Assert.assertTrue(isPhotosFiltered, "Unable to filter the community photos");
		}
	}
	
	@Test(priority=10506)
	public void CommentCommunityPhoto() throws Exception{
		logInfo("inside CommentCommunityPhoto() Test");			
		System.out.println("inside CommentCommunityPhoto() Test");	
		nav2Community();
		boolean isphotoFound = verifyPostIsPresent(photoTitle_text);
		if(isphotoFound){			
			commentPostOnCommunity(photoTitle_text, "Comment",communityStatusComment_text);
			verifyCommentPresent(communityStatusComment_text);
			verifyActivityCount(photoTitle_text,"Comments");
		}
		else{
			Assert.assertTrue(isphotoFound, photoTitle_text + " photo not found on community wall page.");
		}
	}
	
	@Test(priority=10507)
	public void LikeCommunityPhoto() throws Exception{
		logInfo("inside LikeCommunityPhoto() Test");			
		System.out.println("inside LikeCommunityPhoto() Test");	
		nav2Community();
		boolean isphotoFound = verifyPostIsPresent(photoTitle_text);
		if(isphotoFound){			
			setStatusForPosts(photoTitle_text, "Like");		 
			verifyPostActivityLinks(photoTitle_text, "Liked");
			verifyActivityCount(photoTitle_text,"Likes");
 		}	
		else{
			Assert.assertTrue(isphotoFound, photoTitle_text + " photo not found on community wall page.");
		}
	}
	
	@Test(priority=10508)
	public void EditCommentOnCommunityPhoto() throws Exception{
		logInfo("inside EditCommentOnCommunityPhoto() Test");			
		System.out.println("inside EditCommentOnCommunityPhoto() Test");	
		nav2Community();
		boolean isphotoFound = verifyPostIsPresent(photoTitle_text);
		if(isphotoFound){			
			manageActionsOnCommunityComment(photoTitle_text,communityStatusComment_text,communityStatusComment_text+"_Updated","Edit");
 		}
		else{
			Assert.assertTrue(isphotoFound, photoTitle_text + " photo not found on community wall page.");
		}
	}
	
	@Test(priority=10509)
	public void DeleteCommentOnCommunityPhoto() throws Exception{
		logInfo("inside DeleteCommentOnCommunityPhoto() Test");			
		System.out.println("inside DeleteCommentOnCommunityPhoto() Test");	
		nav2Community();
		boolean isphotoFound = verifyPostIsPresent(photoTitle_text);
		if(isphotoFound){			
			manageActionsOnCommunityComment(photoTitle_text,communityStatusComment_text,communityStatusComment_text+"_Updated","Delete");
 		}
		else{
			Assert.assertTrue(isphotoFound, photoTitle_text + " photo not found on community wall page.");
		}
	}
	
	@Test(priority=10510)	
    public void SharePhotoInFacebook() throws Exception{	
		logInfo("inside SharePhotoInFacebook() Test");			
		System.out.println("inside SharePhotoInFacebook() Test");	
		nav2Community();
		boolean isPostFound = verifyPostIsPresent(photoTitle_text);
		if(isPostFound){			
			setStatusForPosts(photoTitle_text, "Share");		
			selectFacebookIcon();
	        sm.shareInFaceBook(photoTitle_text+" has been shared");
	        sm.go2FacebookPage();
	        sm.getPostsFromFB(photoTitle_text);

		}else{
			Assert.assertTrue(isPostFound, photoTitle_text +" unable to share a photo in facebook");
		}	
	}

	@Test(priority=10511)	
    public void SharePhotoInTwitter() throws Exception{	
		logInfo("inside SharePhotoInTwitter() Test");			
		System.out.println("inside SharePhotoInTwitter() Test");
		nav2Community();
		boolean isPostFound = verifyPostIsPresent(photoTitle_text);
		if(isPostFound){			
			setStatusForPosts(photoTitle_text, "Share");		
			sm.selectTwitterIcon();
	        sm.shareInTwitter();
	        sm.go2TwitterPage();
	        boolean isSharedPostPresent = sm.verifyPostsInTwitter(photoTitle_text);
	        if(!isSharedPostPresent){
				Assert.assertTrue(isPostFound, photoTitle_text + " unable to verify a shared photo in Twitter");
			}
			
		}else{
			Assert.assertTrue(isPostFound, photoTitle_text + " unable to share a photo in Twitter");
		}	
	}
	
   	@Test(priority=10512)
	public void HideCommunityPhoto() throws Exception{
		logInfo("inside HideCommunityPhoto() Test");			
		System.out.println("inside HideCommunityPhoto() Test");	
		nav2Community();
		boolean isphotoFound = verifyPostIsPresent(photoTitle_text);
		if(isphotoFound){			
			setStatusForPosts(photoTitle_text, "Hide");		 
 		}	
		else{
			Assert.assertTrue(isphotoFound, photoTitle_text + " photo not found on community wall page.");
		}
	}
   	
	@Test(priority= 10513)
	public void AddVideoInCommunity() throws Exception{
		logInfo("inside AddVideoInCommunity() Test");	
		System.out.println("inside AddVideoInCommunity() Test");	
		nav2Community();
		addCommunityVideo(videoTitle_text, visibilitySettings_text);
		nav2Community();
		boolean isVideoFound = verifyPostIsPresent(videoTitle_text); 
		if(!isVideoFound){
			logInfo(videoTitle_text + " video not found in community wall page.");
			Assert.assertTrue(isVideoFound, videoTitle_text + " video not found in community wall page.");
		}
	}

	@Test(priority= 10514)
	public void EditVideoInCommunity() throws Exception{
		logInfo("inside EditVideoInCommunity() Test");	
		System.out.println("inside EditVideoInCommunity() Test");	
		nav2Community();
		boolean isVideoFound = verifyPostIsPresent(videoTitle_text); 
		if(isVideoFound){
			logInfo(videoTitle_text + " video found in community wall page.");
			updateVideo(videoTitle_text,videoTitle_text+"_updated");
		} else {
			logInfo(videoTitle_text + " video not found in community wall page.");
			Assert.assertTrue(isVideoFound, videoTitle_text + "_updated video not found in community wall page.");
		}
	}
	
	@Test(priority=10515)
	public void FilterCommunityVideos() throws Exception{
		logInfo("inside FilterCommunityVideos() Test.");			
		System.out.println("inside FilterCommunityVideos() Test.");		
		nav2Community();
		boolean isVideosFiltered = filterByType("Video",videoTitle_text+"_updated");
		if(isVideosFiltered){
			boolean isVideoFound = verifyPostIsPresent(videoTitle_text+"_updated"); 
			if(!isVideoFound){
				logInfo(videoTitle_text + "_updated video not found in community wall page.");
				Assert.assertTrue(isVideoFound, videoTitle_text + "_updated video not found in community wall page.");
			}
		}
		else{
			Assert.assertTrue(isVideosFiltered, "Unable to filter the community videos");
		}
	}
		
	@Test(priority= 10516)
	public void DeleteVideoInCommunity() throws Exception{
		logInfo("inside DeleteVideoInCommunity() Test");	
		System.out.println("inside DeleteVideoInCommunity() Test");	
		nav2Community();
		boolean isVideoFound = verifyPostIsPresent(videoTitle_text+"_updated"); 
		if(isVideoFound){
			logInfo(videoTitle_text + "_updated video found in community wall page.");
			deleteCommunityPost(videoTitle_text+"_updated");
		} else {
			logInfo(videoTitle_text + "_updated video not found in community wall page.");
			Assert.assertTrue(isVideoFound, videoTitle_text + "_updated video not found in community wall page.");
		}
	}
	
	@Test(priority=10517)
	public void CommentCommunityVideo() throws Exception{
		logInfo("inside CommentCommunityVideo() Test");			
		System.out.println("inside CommentCommunityVideo() Test");	
		nav2Community();
		addCommunityVideo(videoTitle_text, visibilitySettings_text);
		boolean isVideoFound = verifyPostIsPresent(videoTitle_text);
		if(isVideoFound){			
			commentPostOnCommunity(videoTitle_text, "Comment",communityStatusComment_text);
			verifyCommentPresent(communityStatusComment_text);
			verifyActivityCount(videoTitle_text,"Comments");
 		}
		else{
			Assert.assertTrue(isVideoFound, videoTitle_text + " video not found on community wall page.");
		}
	}
	
	@Test(priority=10518)
	public void LikeCommunityVideo() throws Exception{
		logInfo("inside LikeCommunityVideo() Test");			
		System.out.println("inside LikeCommunityVideo() Test");	
		nav2Community();
		boolean isVideoFound = verifyPostIsPresent(videoTitle_text);
		if(isVideoFound){			
			setStatusForPosts(videoTitle_text, "Like");		 
			verifyPostActivityLinks(videoTitle_text, "Liked");
			verifyActivityCount(videoTitle_text,"Likes");
 		}	
		else{
			Assert.assertTrue(isVideoFound, videoTitle_text + " video not found on community wall page.");
		}
	}
	
	
	
	@Test(priority=10519)
	public void EditCommentOnCommunityVideo() throws Exception{
		logInfo("inside EditCommentOnCommunityVideo() Test");			
		System.out.println("inside EditCommentOnCommunityVideo() Test");	
		nav2Community();
		boolean isVideoFound = verifyPostIsPresent(videoTitle_text);
		if(isVideoFound){			
			manageActionsOnCommunityComment(videoTitle_text,communityStatusComment_text,communityStatusComment_text+"_Updated","Edit");
 		}
		else{
			Assert.assertTrue(isVideoFound, videoTitle_text + " video not found on community wall page.");
		}
	}
	
	@Test(priority=10520)
	public void DeleteCommentOnCommunityVideo() throws Exception{
		logInfo("inside DeleteCommentOnCommunityVideo() Test");			
		System.out.println("inside DeleteCommentOnCommunityVideo() Test");	
		nav2Community();
		boolean isVideoFound = verifyPostIsPresent(videoTitle_text);
		if(isVideoFound){			
			manageActionsOnCommunityComment(videoTitle_text,communityStatusComment_text,communityStatusComment_text+"_Updated","Delete");
			//deleteCommunityPost(videoTitle_text);
		}
		else{
			Assert.assertTrue(isVideoFound, videoTitle_text + " video not found on community wall page.");
		}
	}

	@Test(priority=10521)	
    public void ShareVideoInFacebook() throws Exception{	
		logInfo("inside ShareVideoInFacebook() Test");			
		System.out.println("inside ShareVideoInFacebook() Test");	
		nav2Community();
		addCommunityVideo(videoTitle_text, visibilitySettings_text);
		nav2Community();
		boolean isPostFound = verifyPostIsPresent(videoTitle_text);
		if(isPostFound){			
			setStatusForPosts(videoTitle_text, "Share");
			selectFacebookIcon();
	        sm.shareInFaceBook(videoTitle_text+" has been shared");
	        sm.go2FacebookPage();
	        sm.getPostsFromFB(videoTitle_text);
		}
		else{
			Assert.assertTrue(isPostFound, videoTitle_text +" unable to share a video in facebook");
		}	
	}

	@Test(priority=10522)	
    public void ShareVideoInTwitter() throws Exception{	
		logInfo("inside ShareVideoInTwitter() Test");			
		System.out.println("inside ShareVideoInTwitter() Test");
		nav2Community();
		boolean isPostFound = verifyPostIsPresent(videoTitle_text);
		if(isPostFound){			
			setStatusForPosts(videoTitle_text, "Share");		
			sm.selectTwitterIcon();
	        sm.shareInTwitter();
	        sm.go2TwitterPage();
	        sm.verifyPostsInTwitter(videoTitle_text);
		}else{
			Assert.assertTrue(isPostFound, videoTitle_text + " unable to share a photo in Twitter");
		}	
	}
	
	@Test(priority=10523)
	public void HideCommunityVideo() throws Exception{
		logInfo("inside HideCommunityVideo() Test");			
		System.out.println("inside HideCommunityVideo() Test");	
		nav2Community();
		boolean isVideoFound = verifyPostIsPresent(videoTitle_text);
		if(isVideoFound){			
			setStatusForPosts(videoTitle_text, "Hide");		 
 		}	
		else{
			Assert.assertTrue(isVideoFound, videoTitle_text + " video not found on community wall page.");
		}
	}
	
	@Test(priority= 10524)
	public void AddBlogInCommunity() throws Exception{
		logInfo("inside AddBlogInCommunity() Test");	
		System.out.println("inside AddBlogInCommunity() Test");	
		nav2Community();
		addBlogPost(addPostTitle_text);
		nav2Community();
		boolean isBlogPostFound = verifyPostIsPresent(addPostTitle_text);
		if(!isBlogPostFound){
			Assert.assertTrue(isBlogPostFound, addPostTitle_text + " blog not found in community wall page.");
		} 

	}
	

	@Test(priority= 10525)
	public void EditBlogInCommunity() throws Exception{
		logInfo("inside EditBlogInCommunity() Test");	
		System.out.println("inside EditBlogInCommunity() Test");	
		nav2Community();
		boolean isBlogPostFound = verifyPostIsPresent(addPostTitle_text);
		if(isBlogPostFound){
			updateBlogPost(addPostTitle_text,addPostTitle_text+"_updated");
		} 
		else{
			Assert.assertTrue(isBlogPostFound, addPostTitle_text + " blog not found in community wall page.");
		}
	}
	
	@Test(priority= 10526)
	public void DeleteBlogInCommunity() throws Exception{
		logInfo("inside DeleteBlogInCommunity() Test");	
		System.out.println("inside DeleteBlogInCommunity() Test");	
		nav2Community();
		boolean isBlogPostFound = verifyPostIsPresent(addPostTitle_text+"_updated");
		if(isBlogPostFound){
			deleteCommunityPost(addPostTitle_text+"_updated");
		} 
		else{
			Assert.assertTrue(isBlogPostFound, addPostTitle_text + "_updated blog not found in community wall page.");
		}

	}
		
	@Test(priority= 10527)
	public void DraftCommunityBlog() throws Exception{
		logInfo("Entered DraftCommunityBlog() Test");	
		System.out.println("Entered DraftCommunityBlog() Test");	
		nav2Community();
		profile.addDraftBlogPost(myPostTitle);
		nav2Community();
		boolean isBlogPostFound = verifyPostIsPresent(myPostTitle);
		if(!isBlogPostFound){
			boolean isBlogFoundInProfile = profile.verifyDraftPost(myPostTitle);
			if(!isBlogFoundInProfile){
				Assert.assertTrue(isBlogFoundInProfile, myPostTitle + " blog not drafted in community profile page.");
			}
		}
		else{			
			Assert.assertFalse(isBlogPostFound, myPostTitle + " drafted blog is found in community wall page.");
		}	
	}
	
	@Test(priority= 10528)
	public void EditDraftCommunityBlog() throws Exception{
		logInfo("Entered EditDraftCommunityBlog() Test");	
		System.out.println("Entered EditDraftCommunityBlog() Test");	
		boolean isBlogFoundInProfile = profile.verifyDraftPost(myPostTitle);
		if(isBlogFoundInProfile){
			profile.editDraftBlogPost(BLOG_text, myPostTitle,myPostTitle+"_updated");						
		}
		else{
			Assert.assertTrue(isBlogFoundInProfile, myPostTitle + " blog not drafted in community profile page.");
		}
	}
	
	@Test(priority= 10529)
	public void DeleteDraftCommunityBlog() throws Exception{
		logInfo("Entered DeleteDraftCommunityBlog() Test");	
		System.out.println("Entered DeleteDraftCommunityBlog() Test");	
		boolean isBlogFoundInProfile = profile.verifyDraftPost(myPostTitle+"_updated");
		if(isBlogFoundInProfile){
			profile.deleteDraftBlogPost(BLOG_text, myPostTitle+"_updated");						
		}
		else{
			Assert.assertTrue(isBlogFoundInProfile, myPostTitle+"_updated" + " blog not drafted in community profile page.");
		}
	}
	
	@Test(priority=10530)
	public void CommentCommunityBlog() throws Exception{
		logInfo("inside CommentCommunityBlog() Test");			
		System.out.println("inside CommentCommunityBlog() Test");	
		nav2Community();
		addBlogPost(addPostTitle_text);
		boolean isBlogFound = verifyPostIsPresent(addPostTitle_text);
		if(isBlogFound){			
			commentPostOnCommunity(addPostTitle_text, "Comment",communityStatusComment_text);
			verifyCommentPresent(communityStatusComment_text);
			verifyActivityCount(addPostTitle_text,"Comments");
		}
		else{
			Assert.assertTrue(isBlogFound, addPostTitle_text + " blog not found on community wall page.");
		}
	}
	
	@Test(priority=10531)
	public void LikeCommunityBlog() throws Exception{
		logInfo("inside LikeCommunityBlog() Test");			
		System.out.println("inside LikeCommunityBlog() Test");	
		nav2Community();
		boolean isBlogFound = verifyPostIsPresent(addPostTitle_text);
		if(isBlogFound){			
			setStatusForPosts(addPostTitle_text, "Like");		 
			verifyPostActivityLinks(addPostTitle_text, "Liked");
			verifyActivityCount(addPostTitle_text,"Likes");
 		}	
		else{
			Assert.assertTrue(isBlogFound, addPostTitle_text + " blog not found on community wall page.");
		}
	}
	
	@Test(priority=10532)
	public void EditCommentOnCommunityBlog() throws Exception{
		logInfo("inside EditCommentOnCommunityBlog() Test");			
		System.out.println("inside EditCommentOnCommunityBlog() Test");	
		nav2Community();
		boolean isBlogFound = verifyPostIsPresent(addPostTitle_text);
		if(isBlogFound){			
			manageActionsOnCommunityComment(addPostTitle_text,communityStatusComment_text,communityStatusComment_text+"_Updated","Edit");
 		}
		else{
			Assert.assertTrue(isBlogFound, addPostTitle_text + " blog not found on community wall page.");
		}
	}
	
	@Test(priority=10533)
	public void DeleteCommentOnCommunityBlog() throws Exception{
		logInfo("inside DeleteCommentOnCommunityBlog() Test");			
		System.out.println("inside DeleteCommentOnCommunityBlog() Test");	
		nav2Community();
		boolean isBlogFound = verifyPostIsPresent(addPostTitle_text);
		if(isBlogFound){			
			manageActionsOnCommunityComment(addPostTitle_text,communityStatusComment_text,communityStatusComment_text+"_Updated","Delete");
		}
		else{
			Assert.assertTrue(isBlogFound, addPostTitle_text + " blog not found on community wall page.");
		}
	}
	
	@Test(priority=10534)
	public void HideCommunityBlog() throws Exception{
		logInfo("inside HideCommunityBlog() Test");			
		System.out.println("inside HideCommunityBlog() Test");	
		nav2Community();
		boolean isBlogFound = verifyPostIsPresent(addPostTitle_text);
		if(isBlogFound){			
			setStatusForPosts(addPostTitle_text, "Hide");		 
 		}	
		else{
			Assert.assertTrue(isBlogFound, addPostTitle_text + " blog not found on community wall page.");
		}
	}
	
	@Test(priority=10535)	
    public void ShareBlogInFacebook() throws Exception{		  
		logInfo("inside ShareBlogInFacebook() Test");			
		System.out.println("inside ShareBlogInFacebook() Test");	
		nav2Community();
		addBlogPost(addPostTitle_text);
		nav2Community();
		boolean isPostFound = verifyPostIsPresent(addPostTitle_text);
		if(isPostFound){			
			setStatusForPosts(addPostTitle_text, "Share");		
			selectFacebookIcon();
	        sm.shareInFaceBook(addPostTitle_text+" has been shared");
	        sm.go2FacebookPage();
	        sm.getPostsFromFB(addPostTitle_text);
		}else{
			Assert.assertTrue(isPostFound, addPostTitle_text +" unable to share a blog in facebook");
		}	
	}

	@Test(priority=10536)	
    public void ShareBlogInTwitter() throws Exception{	
		logInfo("inside ShareBlogInTwitter() Test");			
		System.out.println("inside ShareBlogInTwitter() Test");
		nav2Community();
		boolean isPostFound = verifyPostIsPresent(addPostTitle_text);
		if(isPostFound){			
			setStatusForPosts(addPostTitle_text, "Share");		
			sm.selectTwitterIcon();
	        sm.shareInTwitter();
	        sm.go2TwitterPage();
	        sm.verifyPostsInTwitter(addPostTitle_text);
			nav2Community();
			deleteCommunityPost(addPostTitle_text);
			
		}else{
			Assert.assertTrue(isPostFound, addPostTitle_text + " unable to share a blog in Twitter");
		}	
	}

	@Test(priority= 10537)
	public void AddStatusInCommunity() throws Exception{
		logInfo("Entered AddStatusInCommunity() Test");	
		System.out.println("Entered AddStatusInCommunity() Test");	
		nav2Community();
		postStatus(communityStatus_text);		
		nav2Community();
		boolean isPostFound = verifyStatusPostIsPresent(communityStatus_text);
		if(!isPostFound){			
			Assert.assertTrue(isPostFound, communityStatus_text+" post is not found");
		}	
	}

	@Test(priority=10538)
	public void FilterCommunityStatuses() throws Exception{
		logInfo("inside FilterCommunityStatuses() Test..");			
		System.out.println("inside FilterCommunityStatuses() Test.");			
		nav2Community();
		boolean isStatusFiltered = filterByType("Status",communityStatus_text);
		if(!isStatusFiltered){
			Assert.assertTrue(isStatusFiltered, communityStatus_text + " Unable to filter the community statuses");
		}
		
	}
	
	@Test(priority=10539)
	public void CommentCommunityStatus() throws Exception{
		logInfo("inside CommentCommunityStatus() Test");			
		System.out.println("inside CommentCommunityStatus() Test");	
		nav2Community();
		boolean isPostFound = verifyStatusPostIsPresent(communityStatus_text);
		if(isPostFound){			
			commentStatusOnCommunity(communityStatus_text, "Comment",communityStatusComment_text);
			verifyCommentPresent(communityStatusComment_text);
			verifyStatusActivityCount(communityStatus_text,"Comments");
		}
		else{
			Assert.assertTrue(isPostFound, communityStatus_text + " status post not found on community wall page.");
		}
	}
	
	@Test(priority=10540)
	public void LikeCommunityStatus() throws Exception{
		logInfo("inside LikeCommunityStatus() Test");			
		System.out.println("inside LikeCommunityStatus() Test");	
		nav2Community();
		boolean isPostFound = verifyStatusPostIsPresent(communityStatus_text);
		if(isPostFound){			
			setCommunityStatus(communityStatus_text, "Like");		 
			verifyStatusActivityLinks(communityStatus_text, "Liked");
			verifyStatusActivityCount(communityStatus_text,"Likes");
			
 		}	
		else{
			Assert.assertTrue(isPostFound, communityStatus_text + " status post not found on community wall page.");
		}
	}
	
	@Test(priority=10541)
	public void EditCommentOnCommunityStatusPost() throws Exception{
		logInfo("inside EditCommentOnCommunityStatusPost() Test");			
		System.out.println("inside EditCommentOnCommunityStatusPost() Test");	
		nav2Community();
		boolean isPostFound = verifyStatusPostIsPresent(communityStatus_text);
		if(isPostFound){			
			manageActionsOnCommunityStatusComment(communityStatus_text,communityStatusComment_text,communityStatusComment_text+"_Updated","Edit");
 		}
		else{
			Assert.assertTrue(isPostFound, communityStatus_text + " post not found on community wall page.");
		}
	}
	
	@Test(priority=10542)
	public void DeleteCommentOnCommunityStatusPost() throws Exception{
		logInfo("inside DeleteCommentOnCommunityStatusPost() Test");			
		System.out.println("inside DeleteCommentOnCommunityStatusPost() Test");	
		nav2Community();
		boolean isPostFound = verifyStatusPostIsPresent(communityStatus_text);
		if(isPostFound){			
			manageActionsOnCommunityStatusComment(communityStatus_text,communityStatusComment_text,communityStatusComment_text+"_Updated","Delete");
		}
		else{
			Assert.assertTrue(isPostFound, communityStatus_text + " post not found on community wall page.");
		}
	}

	@Test(priority=10543)
	public void HideCommunityStatus() throws Exception{
		logInfo("inside HideCommunityStatus() Test..");			
		System.out.println("inside HideCommunityStatus() Test.");			
		nav2Community();
		boolean isPostFound = verifyStatusPostIsPresent(communityStatus_text);
		if(isPostFound){			
			hideCommunityStatusPost(communityStatus_text, "Hide" );
		    confirmationMessage("Activity hidden from community stream");
		} else{
			Assert.assertTrue(isPostFound, communityStatus_text+" -post is not found");
		}	
	}
	
	@Test(priority=10544)	
    public void ShareStatusInFacebook() throws Exception{		  
		logInfo("inside ShareStatusInFacebook() Test");			
		System.out.println("inside ShareStatusInFacebook() Test");	
		nav2Community();
		postStatus(communityStatus_text);
		nav2Community();
		boolean isPostFound = verifyStatusPostIsPresent(communityStatus_text);
		if(isPostFound){			
			setCommunityStatus(communityStatus_text, "Share");	
			selectFacebookIcon();
	        sm.shareInFaceBook(communityStatus_text+" has been shared");
	        sm.go2FacebookPage();
	        sm.getPostsFromFB(communityStatus_text);
		}
		else{
			Assert.assertTrue(isPostFound, communityStatus_text +" unable to share a status in facebook");
		}	
	}

	@Test(priority=10545)	
    public void ShareStatusInTwitter() throws Exception{	
		logInfo("inside ShareBlogInTwitter() Test");			
		System.out.println("inside ShareBlogInTwitter() Test");
		nav2Community();
		boolean isPostFound = verifyStatusPostIsPresent(communityStatus_text);
		if(isPostFound){			
			setCommunityStatus(communityStatus_text, "Share");		
			sm.selectTwitterIcon();
	        sm.shareInTwitter();
	        sm.go2TwitterPage();
	        sm.verifyPostsInTwitter(communityStatus_text);
			nav2Community();
			hideCommunityStatusPost(communityStatus_text, "Hide" );

		}else{
			Assert.assertTrue(isPostFound, communityStatus_text + " unable to share a photo in Twitter");
		}	
	}
	
	@Test(priority= 10546)
	public void ViewMoreActivitiesInCommunity() throws Exception{
		logInfo("Entered ViewMoreActivitiesInCommunity() Test");	
		System.out.println("Entered ViewMoreActivitiesInCommunity() Test");	
		nav2Community();
		boolean isRecordsFound = viewMoreActivities();
		if(!isRecordsFound){
			Assert.assertTrue(isRecordsFound, "Unable to view more records under Recent activity widget in community wall page.");
		}
	}
	
	@Test(priority= 10547)
	public void NavigateUserProfileFromCommunity() throws Exception{
		logInfo("Entered NavigateUserProfileFromCommunity() Test");	
		System.out.println("Entered NavigateUserProfileFromCommunity() Test");	
		nav2Community();
		boolean isUserFound = navigateUserProfile();
		if(!isUserFound){
			Assert.assertTrue(isUserFound, "Unable to navigate to the user profile from Community wall.");
		}
	}

   @Test(priority=10548)	
    public void validateCommunityStatus() throws Exception{	     
	 	logInfo("Entered into validateCommunityStatus() test");	
	 	System.out.println("Entered validateCommunityStatus() Test");	
	 	nav2Community(); 	
	 	validatePostComment();		 	
    }
	   
	
   @Test(priority=10549)	
    public void ValidateCommunityPhoto() throws Exception{	    	 
    	logInfo("Enetered into ValidateCommunityPhoto() test");
    	System.out.println("Entered ValidateCommunityPhoto() Test");	
    	nav2Community();
    	validateMandatoryFields4Photo(photoTitle_text2);	    	  	
    }
   
   @Test(priority=10550)
    public void ValidateCommunityBlogPost() throws Exception{
	   logInfo("Enetered into ValidateCommunityBlogPost() Test");
	   System.out.println("Entered ValidateCommunityBlogPost() Test");	
    	nav2Community();
    	validateMandatoryFields4BlogPost();
    }

   @Test(priority=10551)	
    public void ValidateCommunityVideo() throws Exception{
	   logInfo("Entered into ValidateCommunityVideo() Test");    
	   System.out.println("Entered ValidateCommunityVideo() Test");	
	   nav2Community();
	   validateMandatoryFields4Video(photoTitle_text2);    		 
    }
   
   //Below are the test scenarios for Abuse Reports
   
  /* @Test(priority=10552)	
   public void AbusedCommunityPhoto() throws Exception{
	   logInfo("Entered into AbusedCommunityPhoto() Test");    
	   System.out.println("Entered AbusedCommunityPhoto() Test");	
	   nav2Community();
	   addCommunityPhoto(photoTitle_text, visibilitySettings_text);
	   nav2Community();
	   boolean isPhotoFoundInCommunity = verifyPostIsPresent(photoTitle_text); 
	   if(isPhotoFoundInCommunity){ 
		   boolean isAbused = abuseCommunityActivity(photoTitle_text);
		   if(!isAbused){
			   Assert.assertTrue(isAbused, "Unable to reported the photo as abuse "+photoTitle_text);
		   }
	   }
   }
   
   @Test(priority=10553)	
   public void VerifyAbusedPhotoInAdmin() throws Exception{
	   logInfo("Entered into VerifyAbusedPhotoInAdmin() Test");    
	   System.out.println("Entered VerifyAbusedPhotoInAdmin() Test");	
	   go2AbuseReports();
	   boolean isAbusedPhotoFound = verifyAbusedCommunityActivitesInAdmin(photoTitle_text);
	   if(!isAbusedPhotoFound){
		   Assert.assertTrue(isAbusedPhotoFound, "Unable to find the abused photo on admin side "+photoTitle_text);
	   }
   }
   
   @Test(priority=10554)	
   public void RestoreAbusedPhotoInAdmin() throws Exception{
	   logInfo("Entered into RestoreAbusedPhotoInAdmin() Test");    
	   System.out.println("Entered RestoreAbusedPhotoInAdmin() Test");	
	   go2AbuseReports();
	   boolean isAbusedPhotoFound = restoreAbusedActivitesInAdmin(photoTitle_text,"Restored this Photo","content found is ok");
	   if(!isAbusedPhotoFound){
		   Assert.assertTrue(isAbusedPhotoFound, "Unable to restore the abused photo on admin side "+photoTitle_text);
	   }
   }
   
   @Test(priority=10555)	
   public void VerifyRestoredAbusePhotoHistory() throws Exception{
	   logInfo("Entered into VerifyRestoredAbusePhotoHistory() Test");    
	   System.out.println("Entered VerifyRestoredAbusePhotoHistory() Test");	
	   go2AbuseReports();
 	   boolean isAbusedPhotoFound = verifyRestoredAbuseReportHistory("Restored this Photo","content found is ok");
 	   if(!isAbusedPhotoFound){
 		   Assert.assertTrue(isAbusedPhotoFound, "Unable to restore the abused photo on admin side "+photoTitle_text);
 	   }
   }
   
    @Test(priority=10556)
	public void AbusedCommunityVideo() throws Exception{
	   logInfo("Entered into AbusedCommunityVideo() Test");    
	   System.out.println("Entered AbusedCommunityVideo() Test");	
	   loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
	   nav2Community();
	   addCommunityVideo(videoTitle_text, visibilitySettings_text);
	   nav2Community();
	   boolean isVideoFoundInCommunity = verifyPostIsPresent(videoTitle_text); 
	   if(isVideoFoundInCommunity){ 
		   boolean isAbused = abuseCommunityActivity(videoTitle_text);
		   if(!isAbused){
			   Assert.assertTrue(isAbused, "Unable to reported the video as abuse "+videoTitle_text);
		   }
	   }
	}
  
    @Test(priority=10557)
	public void VerifyAbusedVideoInAdmin() throws Exception{
    	logInfo("Entered into VerifyAbusedVideoInAdmin() Test");    
 	   System.out.println("Entered VerifyAbusedVideoInAdmin() Test");	
 	   go2AbuseReports();
 	   boolean isAbusedVideoFound = verifyAbusedCommunityActivitesInAdmin(videoTitle_text);
	   if(!isAbusedVideoFound){
		   Assert.assertTrue(isAbusedVideoFound, "Unable to find the abused video on admin side "+videoTitle_text);
	   }
	}
    
    @Test(priority=10558)
  	public void RestoreAbusedVideoInAdmin() throws Exception{
      	logInfo("Entered into RestoreAbusedVideoInAdmin() Test");    
   	   System.out.println("Entered RestoreAbusedVideoInAdmin() Test");	
   	   go2AbuseReports();
   	   boolean isAbusedVideoFound = restoreAbusedActivitesInAdmin(videoTitle_text,"Restored this Video","content found is ok");
	   if(!isAbusedVideoFound){
		   Assert.assertTrue(isAbusedVideoFound, "Unable to restore the abused video on admin side "+videoTitle_text);
	   }
  	}
    
    @Test(priority=10559)
  	public void VerifyRestoredAbuseVideoHistory() throws Exception{
      	logInfo("Entered into VerifyRestoredAbuseVideoHistory() Test");    
   	   System.out.println("Entered VerifyRestoredAbuseVideoHistory() Test");	
   	   go2AbuseReports();
   	   boolean isAbusedVideoFound = verifyRestoredAbuseReportHistory("Restored this Video","content found is ok");
	   if(!isAbusedVideoFound){
		   Assert.assertTrue(isAbusedVideoFound, "Unable to restore the abused video on admin side "+videoTitle_text);
	   }
  	}
   
   @Test(priority=10560)
	public void AbusedCommunityBlog() throws Exception{
	   logInfo("Entered into AbusedCommunityBlog() Test");    
	   System.out.println("Entered AbusedCommunityBlog() Test");	
	   loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
	   nav2Community();
	   addBlogPost(addPostTitle_text);
	   nav2Community();
	   boolean isBlogFoundInCommunity = verifyPostIsPresent(addPostTitle_text); 
	   if(isBlogFoundInCommunity){ 
		   boolean isAbused = abuseCommunityActivity(addPostTitle_text);
		   if(!isAbused){
			   Assert.assertTrue(isAbused, "Unable to reported the blog as abuse "+addPostTitle_text);
		   }
	   }
	}
   
    @Test(priority=10561)
	public void VerifyAbusedBlogInAdmin() throws Exception{
      	logInfo("Entered into VerifyAbusedBlogInAdmin() Test");    
   	   System.out.println("Entered VerifyAbusedBlogInAdmin() Test");	
   	   go2AbuseReports();
   	   boolean isAbusedBlogFound = verifyAbusedCommunityActivitesInAdmin(addPostTitle_text);
	   if(!isAbusedBlogFound){
		   Assert.assertTrue(isAbusedBlogFound, "Unable to find the abused blog on admin side "+addPostTitle_text);
	   }
  	}
    
    @Test(priority=10562)
	public void RestoreAbusedBlogInAdmin() throws Exception{
      	logInfo("Entered into RestoreAbusedBlogInAdmin() Test");    
   	   System.out.println("Entered RestoreAbusedBlogInAdmin() Test");	
   	   go2AbuseReports();
   	   boolean isAbusedBlogFound = restoreAbusedActivitesInAdmin(addPostTitle_text,"Restored this blog","content found is ok");
	   if(!isAbusedBlogFound){
		   Assert.assertTrue(isAbusedBlogFound, "Unable to restore the abused blog on admin side "+addPostTitle_text);
	   }
  	}
    
    @Test(priority=10563)
	public void VerifyRestoredAbuseBlogHistory() throws Exception{
      	logInfo("Entered into VerifyRestoredAbuseBlogHistory() Test");    
   	   System.out.println("Entered VerifyRestoredAbuseBlogHistory() Test");	
   	   go2AbuseReports();
   	   boolean isAbusedBlogFound = verifyRestoredAbuseReportHistory("Restored this blog","content found is ok");
	   if(!isAbusedBlogFound){
		   Assert.assertTrue(isAbusedBlogFound, "Unable to restore the abused blog on admin side "+addPostTitle_text);
	   }
  	}
    
    @Test(priority=10564)
	public void AbusedCommunityStatus() throws Exception{
	   logInfo("Entered into AbusedCommunityStatus() Test");    
	   System.out.println("Entered AbusedCommunityStatus() Test");	
	   loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
	   nav2Community();
	   postStatus(communityStatus_text);		
	   nav2Community();
	   boolean isStatusFound = verifyStatusPostIsPresent(communityStatus_text);
	   if(isStatusFound){ 
		   boolean isAbused = abuseCommunityStatusActivity(communityStatus_text);
		   if(!isAbused){
			   Assert.assertTrue(isAbused, "Unable to reported the status as abuse "+communityStatus_text);
		   }
	   }
	}
    
    @Test(priority=10565)
	public void VerifyAbusedStatusInAdmin() throws Exception{
       logInfo("Entered into VerifyAbusedStatusInAdmin() Test");    
   	   System.out.println("Entered VerifyAbusedStatusInAdmin() Test");	
   	   go2AbuseReports();
   	   boolean isAbusedStatusFound = verifyAbusedCommunityActivitesInAdmin(communityStatus_text);
	   if(!isAbusedStatusFound){
		   Assert.assertTrue(isAbusedStatusFound, "Unable to find the abused status on admin side "+communityStatus_text);
	   }
  	}
    
    @Test(priority=10566)
	public void RestoreAbusedStatusInAdmin() throws Exception{
       logInfo("Entered into RestoreAbusedStatusInAdmin() Test");    
   	   System.out.println("Entered RestoreAbusedStatusInAdmin() Test");	
   	   go2AbuseReports();
   	   boolean isAbusedStatusFound = restoreAbusedActivitesInAdmin(communityStatus_text,"Restored this status","content found is ok");
	   if(!isAbusedStatusFound){
		   Assert.assertTrue(isAbusedStatusFound, "Unable to restore the abused status on admin side "+communityStatus_text);
	   }
  	}
    
    @Test(priority=10567)
	public void VerifyRestoredAbuseStatusHistory() throws Exception{
       logInfo("Entered into VerifyRestoredAbuseStatusHistory() Test");    
   	   System.out.println("Entered VerifyRestoredAbuseStatusHistory() Test");	
   	   go2AbuseReports();
   	   boolean isAbusedStatusFound = verifyRestoredAbuseReportHistory("Restored this status","content found is ok");
	   if(!isAbusedStatusFound){
		   Assert.assertTrue(isAbusedStatusFound, "Unable to restore the abused status on admin side "+communityStatus_text);
	   }
  	}

   @Test(priority=10568)	
   public void AbusedCommunityPhotoOnce() throws Exception{
	   logInfo("Entered into AbusedCommunityPhotoOnce() Test");    
	   System.out.println("Entered into AbusedCommunityPhotoOnce() Test");	
	   loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
	   nav2Community();
	   addCommunityPhoto(photoTitle_text, visibilitySettings_text);
	   nav2Community();
	   boolean isPhotoFoundInCommunity = verifyPostIsPresent(photoTitle_text); 
	   if(isPhotoFoundInCommunity){ 
		   boolean isAbusedOnce = abuseCommunityActivityOnce(photoTitle_text);
		   if(!isAbusedOnce){
			   Assert.assertTrue(isAbusedOnce, "Unable to reported the Photo as abused once "+photoTitle_text);
		   }
	   }
   }
   
   @Test(priority=10569)	
   public void DeleteAbusedPhotoInAdmin() throws Exception{
	   logInfo("Entered into DeleteAbusedPhotoInAdmin() Test");    
	   System.out.println("Entered into DeleteAbusedPhotoInAdmin() Test");	
	   go2AbuseReports();
	   boolean isAbusedPhotoFound = deleteAbusedActivitesInAdmin(photoTitle_text,"Deleted this Photo","Inappropriate content found");
	   if(!isAbusedPhotoFound){
		   Assert.assertTrue(isAbusedPhotoFound, "Unable to delete the abused photo on admin side "+photoTitle_text);
	   }
   }
   
   @Test(priority=10570)	
   public void VerifyDeletedAbuseReportHistory() throws Exception{
	   logInfo("Entered into VerifyDeletedAbuseReportHistory() Test");    
	   System.out.println("Entered into VerifyDeletedAbuseReportHistory() Test");	
	   go2AbuseReports();
	   boolean isAbusedPhotoFound = verifyDeletedAbuseReportHistory("Deleted this Photo","Inappropriate content found");
	   if(!isAbusedPhotoFound){
		   Assert.assertTrue(isAbusedPhotoFound, "Unable to delete the abused photo on admin side "+photoTitle_text);
	   }
   }
   
   @Test(priority=10571)	
   public void AbusedCommunityVideoOnce() throws Exception{
	   logInfo("Entered into AbusedCommunityVideoOnce() Test");    
	   System.out.println("Entered into AbusedCommunityVideoOnce() Test");	
	   loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
	   nav2Community();
	   addCommunityVideo(videoTitle_text, visibilitySettings_text);
	   nav2Community();
	   boolean isVideoFoundInCommunity = verifyPostIsPresent(videoTitle_text); 
	   if(isVideoFoundInCommunity){ 
		   boolean isAbusedOnce = abuseCommunityActivityOnce(videoTitle_text);
		   if(!isAbusedOnce){
			   Assert.assertTrue(isAbusedOnce, "Unable to reported the Video as abused once "+videoTitle_text);
		   }
	   }
   }
   
   @Test(priority=10572)	
   public void DeleteAbusedVideoInAdmin() throws Exception{
	   logInfo("Entered into DeleteAbusedVideoInAdmin() Test");    
	   System.out.println("Entered into DeleteAbusedVideoInAdmin() Test");	
	   go2AbuseReports();
	   boolean isAbusedVideoFound = deleteAbusedActivitesInAdmin(videoTitle_text,"Deleted this Video","Inappropriate content found");
	   if(!isAbusedVideoFound){
		   Assert.assertTrue(isAbusedVideoFound, "Unable to delete the abused video on admin side "+videoTitle_text);
	   }
   }
   
   @Test(priority=10573)	
   public void VerifyDeletedAbuseVideoHistory() throws Exception{
	   logInfo("Entered into VerifyDeletedAbuseVideoHistory() Test");    
	   System.out.println("Entered into VerifyDeletedAbuseVideoHistory() Test");	
	   go2AbuseReports();
	   boolean isAbusedVideoFound = verifyDeletedAbuseReportHistory("Deleted this Video","Inappropriate content found");
	   if(!isAbusedVideoFound){
		   Assert.assertTrue(isAbusedVideoFound, "Unable to delete the abused video on admin side "+videoTitle_text);
	   }
   }
   
    @Test(priority=10574)
	public void AbusedCommunityBlogOnce() throws Exception{
	   logInfo("Entered into AbusedCommunityBlogOnce() Test");    
	   System.out.println("Entered AbusedCommunityBlogOnce() Test");	
	   loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
	   nav2Community();
	   addBlogPost(addPostTitle_text);
	   nav2Community();
	   boolean isBlogFoundInCommunity = verifyPostIsPresent(addPostTitle_text); 
	   if(isBlogFoundInCommunity){ 
		   boolean isAbusedOnce = abuseCommunityActivityOnce(addPostTitle_text);
		   if(!isAbusedOnce){
			   Assert.assertTrue(isAbusedOnce, "Unable to reported the blog as abused once "+addPostTitle_text);
		   }
	   }
	}
    
    @Test(priority=10575)	
    public void DeleteAbusedBlogInAdmin() throws Exception{
 	   logInfo("Entered into DeleteAbusedBlogInAdmin() Test");    
 	   System.out.println("Entered into DeleteAbusedBlogInAdmin() Test");	
 	   go2AbuseReports();
 	   boolean isAbusedBlogFound = deleteAbusedActivitesInAdmin(addPostTitle_text,"Deleted this blog","Inappropriate content found");
	   if(!isAbusedBlogFound){
		   Assert.assertTrue(isAbusedBlogFound, "Unable to delete the abused blog on admin side "+addPostTitle_text);
	   }
    }
    
    @Test(priority=10576)	
    public void VerifyDeletedAbuseBlogHistory() throws Exception{
 	   logInfo("Entered into VerifyDeletedAbuseBlogHistory() Test");    
 	   System.out.println("Entered into VerifyDeletedAbuseBlogHistory() Test");	
 	   go2AbuseReports();
 	   boolean isAbusedBlogFound = verifyDeletedAbuseReportHistory("Deleted this blog","Inappropriate content found");
	   if(!isAbusedBlogFound){
		   Assert.assertTrue(isAbusedBlogFound, "Unable to delete the abused blog on admin side "+addPostTitle_text);
	   }
    }
  
    @Test(priority=10577)
	public void AbusedCommunityStatusOnce() throws Exception{
	  logInfo("Entered into AbusedCommunityStatusOnce() Test");    
	   System.out.println("Entered AbusedCommunityStatusOnce() Test");	
	   loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
	   nav2Community();
	   postStatus(communityStatus_text);		
	   nav2Community();
	   boolean isStatusFound = verifyStatusPostIsPresent(communityStatus_text);
	   if(isStatusFound){ 
		   boolean isAbusedOnce = abuseCommunityStatusActivityOnce(communityStatus_text);
		   if(!isAbusedOnce){
			   Assert.assertTrue(isAbusedOnce, "Unable to reported the status as abused once "+communityStatus_text);
		   }
	   }
	}
    
    @Test(priority=10578)	
    public void DeleteAbusedStatusInAdmin() throws Exception{
 	   logInfo("Entered into DeleteAbusedStatusInAdmin() Test");    
 	   System.out.println("Entered into DeleteAbusedStatusInAdmin() Test");	
 	   go2AbuseReports();
 	   boolean isAbusedStatusFound = deleteAbusedActivitesInAdmin(communityStatus_text,"Deleted this status","Inappropriate content found");
	   if(!isAbusedStatusFound){
		   Assert.assertTrue(isAbusedStatusFound, "Unable to delete the abused status on admin side "+communityStatus_text);
	   }
    }
    
    @Test(priority=10579)	
    public void VerifyDeletedAbuseStatusHistory() throws Exception{
 	   logInfo("Entered into VerifyDeletedAbuseStatusHistory() Test");    
 	   System.out.println("Entered into VerifyDeletedAbuseStatusHistory() Test");	
 	   go2AbuseReports();
 	   boolean isAbusedStatusFound = verifyDeletedAbuseReportHistory("Deleted this status","Inappropriate content found");
	   if(!isAbusedStatusFound){
		   Assert.assertTrue(isAbusedStatusFound, "Unable to delete the abused status on admin side "+communityStatus_text);
	   }
    }*/
   

}