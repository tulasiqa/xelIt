package vibe.myprofile.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import vibe.mycommunity.tests.CommunityMethods;

@Priority(125)

public class MyProfileTests extends MyProfileMethods{
	
	CommunityMethods comm = new CommunityMethods();		
	
	@Test(priority= 12501, groups= {"abc"})
	public void AddPhotoInProfile() throws Exception{
		logInfo("inside addPhotoInProfile() Test");	
		System.out.println("inside addPhotoInProfile() Test");	
		nav2profile();
		comm.addCommunityPhotoFromMachine(photoTitle_text, visibilitySettings_text);
		boolean isPhotoFoundInProfile = verifyInMyRecentActivity(photoTitle_text);
		if(isPhotoFoundInProfile){
			boolean isPhotoFoundInCommunity = comm.verifyPostIsPresent(photoTitle_text); 
			if(!isPhotoFoundInCommunity){
				logInfo(photoTitle_text + " photo not found in community wall page.");
				Assert.assertTrue(isPhotoFoundInCommunity, photoTitle_text + " photo not found in community wall page.");
			}
		}
		else{
			Assert.assertTrue(isPhotoFoundInProfile, photoTitle_text + " photo not found in community profile page.");
		}
		
	}
	
	@Test(priority= 12502)
	public void EditPhotoInProfile() throws Exception{
		logInfo("inside EditPhotoInProfile() Test");	
		System.out.println("inside EditPhotoInProfile() Test");	
		boolean isPhotoFoundInProfile = verifyInMyRecentActivity(photoTitle_text);
		if(isPhotoFoundInProfile){
			boolean isPhotoFoundInCommunity = comm.verifyPostIsPresent(photoTitle_text); 
			if(isPhotoFoundInCommunity){
				logInfo(photoTitle_text + " photo found in community wall page.");
				comm.updatePhoto(photoTitle_text,photoTitle_text+"_updated");
			} else {
				logInfo(photoTitle_text + " photo not found in community wall page.");
				Assert.assertTrue(isPhotoFoundInCommunity, photoTitle_text + " photo not found in community wall page.");
			}
		}
		else{
			Assert.assertTrue(isPhotoFoundInProfile, photoTitle_text + " photo not found in community profile page.");
		}
		
	}
	
	@Test(priority= 12503)
	public void DeletePhotoInProfile() throws Exception{
		logInfo("inside DeletePhotoInProfile() Test");	
		System.out.println("inside DeletePhotoInProfile() Test");	
		boolean isPhotoFoundInProfile = verifyInMyRecentActivity(photoTitle_text+"_updated");
		if(isPhotoFoundInProfile){
			boolean isPhotoFoundInCommunity = comm.verifyPostIsPresent(photoTitle_text+"_updated"); 
			if(isPhotoFoundInCommunity==true){
				logInfo(photoTitle_text + " photo found in community wall page.");
				comm.deleteCommunityPost(photoTitle_text+"_updated");
				
			} else {
				logInfo(photoTitle_text + " photo not found in community wall page.");
				Assert.assertTrue(isPhotoFoundInCommunity, photoTitle_text + "_updated photo not found in community wall page.");
			}
		}
		else{
			Assert.assertTrue(isPhotoFoundInProfile, photoTitle_text + " photo not found in community profile page.");
		}
		
	}
	
	@Test(priority= 12504)
	public void AddProfilePhotoWithURL() throws Exception{
		logInfo("inside AddProfilePhotoWithURL() Test");	
		System.out.println("inside AddProfilePhotoWithURL() Test");	
		nav2profile();
		comm.addCommunityPhoto(photoTitle_text, visibilitySettings_text);
		boolean isPhotoFoundInProfile = verifyInMyRecentActivity(photoTitle_text);
		if(isPhotoFoundInProfile){
			boolean isPhotoFoundInCommunity = comm.verifyPostIsPresent(photoTitle_text); 
			if(!isPhotoFoundInCommunity){
				logInfo(photoTitle_text + " photo not found in community wall page.");
				Assert.assertTrue(isPhotoFoundInCommunity, photoTitle_text + " photo not found in community wall page.");
			}
		}
		else{
			Assert.assertTrue(isPhotoFoundInProfile, photoTitle_text + " photo not found in community profile page.");
		}
		
	}
	
	@Test(priority= 12505)
	public void AddVideoInProfile() throws Exception{
		logInfo("inside AddVideoInProfile() Test");	
		System.out.println("inside AddVideoInProfile() Test");	
		nav2profile();
		comm.addCommunityVideo(videoTitle_text, visibilitySettings_text);
		boolean isVideoFoundInProfile = verifyInMyRecentActivity("Posted A Video From YouTube");
		if(isVideoFoundInProfile){
			boolean isVideoFound = comm.verifyPostIsPresent(videoTitle_text); 
			if(!isVideoFound){
				logInfo(videoTitle_text + " video not found in community wall page.");
				Assert.assertTrue(isVideoFound, videoTitle_text + " video not found in community wall page.");
			}
		}
		else{
			Assert.assertTrue(isVideoFoundInProfile, videoTitle_text + " video not found in community profile page.");
		}
			
	  }
	
	@Test(priority= 12505)
	public void EditVideoInProfile() throws Exception{
		logInfo("inside EditVideoInProfile() Test");	
		System.out.println("inside EditVideoInProfile() Test");	
		boolean isVideoFoundInProfile = verifyInMyRecentActivity("Posted A Video From YouTube");
		if(isVideoFoundInProfile){
			boolean isVideoFound = comm.verifyPostIsPresent(videoTitle_text); 
			if(isVideoFound){
				logInfo(videoTitle_text + " video found in community wall page.");
				comm.updateVideo(videoTitle_text,videoTitle_text+"_updated");
			} else {
				logInfo(videoTitle_text + " video not found in community wall page.");
				Assert.assertTrue(isVideoFound, videoTitle_text + " video not found in community wall page.");
			}
		}
		else{
			Assert.assertTrue(isVideoFoundInProfile, videoTitle_text + " video not found in community profile page.");
		}
			
	  }
	
	@Test(priority= 12507)
	public void DeleteVideoInProfile() throws Exception{
		logInfo("inside DeleteVideoInProfile() Test");	
		System.out.println("inside DeleteVideoInProfile() Test");	
		boolean isVideoFoundInProfile = verifyInMyRecentActivity("Posted A Video From YouTube");
		if(isVideoFoundInProfile){
			boolean isVideoFound = comm.verifyPostIsPresent(videoTitle_text+"_updated"); 
			if(isVideoFound){
				logInfo(videoTitle_text + "_updated video found in community wall page.");
				comm.deleteCommunityPost(videoTitle_text+"_updated");
			} else {
				logInfo(videoTitle_text + "_updated video not found in community wall page.");
				Assert.assertTrue(isVideoFound, videoTitle_text + "_updated video not found in community wall page.");
			}
		}
		else{
			Assert.assertTrue(isVideoFoundInProfile, videoTitle_text + "_updated video not found in community profile page.");
		}
			
	  }
	
	@Test(priority= 12508)
	public void AddProfileBlog() throws Exception{
		logInfo("inside addProfileBlog() Test");	
		System.out.println("inside addProfileBlog() Test");	
		nav2profile();	
		addBlogPost(addPostTitle_text);
		nav2profile();
		boolean isBlogFoundInProfile = verifyInMyRecentActivity(addPostTitle_text);
		if(isBlogFoundInProfile){
			boolean isBlogPostFound = comm.verifyPostIsPresent(addPostTitle_text);
			if(!isBlogPostFound){		
				Assert.assertTrue(isBlogPostFound, addPostTitle_text + " blog not found in community wall page.");
			}	
		}
		else{
			Assert.assertTrue(isBlogFoundInProfile, addPostTitle_text + " blog not found in community profile page.");
		}
		
	}
	
	@Test(priority= 12509)
	public void EditProfileBlog() throws Exception{
		logInfo("inside EditProfileBlog() Test");	
		System.out.println("inside EditProfileBlog() Test");	
		boolean isBlogFoundInProfile = verifyInMyRecentActivity(addPostTitle_text);
		if(isBlogFoundInProfile){
			boolean isBlogPostFound = comm.verifyPostIsPresent(addPostTitle_text);
			if(isBlogPostFound){
				nav2profile();	
				updateBlogPostInProfile(addPostTitle_text, BLOG_text, addPostTitle_text+"_update");
			} else {			
				Assert.assertTrue(isBlogPostFound, addPostTitle_text + " blog not found in community wall page.");
			}	
		}
		else{
			Assert.assertTrue(isBlogFoundInProfile, addPostTitle_text + " blog not found in community profile page.");
		}
		
	}

	@Test(priority= 12510)
	public void DeleteProfileBlog() throws Exception{
		logInfo("inside DeleteProfileBlog() Test");	
		System.out.println("inside DeleteProfileBlog() Test");	
		boolean isBlogFoundInProfile = verifyInMyRecentActivity(addPostTitle_text+"_update");
		if(isBlogFoundInProfile){
			boolean isBlogPostFound = comm.verifyPostIsPresent(addPostTitle_text+"_update");
			if(isBlogPostFound==true){
				nav2profile();	
				deleteBlogPost(BLOG_text, addPostTitle_text+"_update");				
				nav2profile();
				verifyPostNotPresentInMyRecentActivity(addPostTitle_text+"_update");
			} else {			
				Assert.assertTrue(isBlogPostFound, addPostTitle_text + "_update blog not found in community wall page.");
			}	
		}
		else{
			Assert.assertTrue(isBlogFoundInProfile, addPostTitle_text + "_updated blog not found in community profile page.");
		}
		
	}
		
	@Test(priority= 12511)
	public void DraftProfileBlog() throws Exception{
		logInfo("Entered draftProfileBlog() Test");	
		System.out.println("Entered draftBlog() Test");	
		nav2profile();	
		selectActivityTab("Blog");
		addDraftBlogPost(myPostTitle);
		boolean isBlogDrafted = verifyDraftPost(myPostTitle);
		if(isBlogDrafted){
			deleteDraftBlogPost(BLOG_text, myPostTitle);			
		}
		else{
			Assert.assertTrue(isBlogDrafted, myPostTitle + " blog not drafted in community profile page.");
		}

	}
		
	@Test(priority= 12512)
	public void ViewMoreActivitiesInProfile() throws Exception{
		logInfo("Entered ViewMoreActivitiesInProfile() Test");	
		System.out.println("Entered ViewMoreActivitiesInProfile() Test");	
		nav2profile();	
		boolean isRecordsFound = viewMoreActivities();
		if(isRecordsFound){
			Assert.assertTrue(isRecordsFound, "Able to find more records.");
		}
		else{
			Assert.assertTrue(isRecordsFound, "Unable to view more records under Recent activity widget in my profile.");
		}

	}

	@Test(priority= 12513)
	public void ChangeProfilePhoto() throws Exception{
		logInfo("inside ChangeProfilePhoto() Test");	
		System.out.println("inside ChangeProfilePhoto() Test");	
		nav2profile();
		String actSrc = getSrcForImage("//div[starts-with(@class,'profile-picture')]/img");
		System.out.println("actSrc =" +actSrc);
		changeProfileAvatar();
		confirmationMessage("Picture updated");
		nav2profile();
		String actSrc1 = getSrcForImage("//div[starts-with(@class,'profile-picture')]/img");
		System.out.println("actSrc1 =" +actSrc1);
		if(!actSrc.equalsIgnoreCase(actSrc1)){
			Assert.assertFalse(true, "Unable to change the profile photo.");
		}
			
	  }
	
	
	@Test(priority= 12514)
	public void ChangeCoverPhoto() throws Exception{
		logInfo("inside ChangeCoverPhoto() Test");	
		System.out.println("inside ChangeCoverPhoto() Test");	
		nav2profile();
		changeCoverPhoto();
		confirmationMessage("Profile cover updated");
	}

	@Test(priority= 12515)
	public void AddPhotoFromPhotosSection() throws Exception{
		logInfo("inside AddPhotoFromPhotosSection() Test");	
		System.out.println("inside AddPhotoFromPhotosSection() Test");	
		nav2profile();
		AddPhotoFromPhotos(photoTitle_text);
		boolean isPhotoFoundInProfile = verifyInMyRecentActivity(photoTitle_text);
		if(isPhotoFoundInProfile){
			boolean isPhotoFoundInPhotos = verifyPhotosInPhotosSection(photoTitle_text);
			
			if(isPhotoFoundInPhotos){
				boolean isPhotoFoundInCommunity = comm.verifyPostIsPresent(photoTitle_text); 
				if(isPhotoFoundInCommunity==true){
					logInfo(photoTitle_text + " photo found in community wall page.");
					comm.deleteCommunityPost(photoTitle_text);
				} else {
					logInfo(photoTitle_text + " photo not found in community wall page.");
					Assert.assertTrue(isPhotoFoundInCommunity, photoTitle_text + " photo not found in community wall page.");
				}
			}
			else{
				Assert.assertTrue(isPhotoFoundInPhotos, photoTitle_text + " photo not found under photos section of profile page.");
			}
			
		}
		else{
			Assert.assertTrue(isPhotoFoundInProfile, photoTitle_text + " photo not found in community profile page.");
		}
	}
	
	@Test(priority= 12516)
	public void AddVideoFromVideosSection() throws Exception{
		logInfo("inside AddVideoFromVideosSection() Test");	
		System.out.println("inside AddVideoFromVideosSection() Test");	
		nav2profile();
		AddVideoFromVideos(videoTitle_text);
		boolean isVideoFoundInProfile = verifyInMyRecentActivity("Posted A Video From YouTube");
		if(isVideoFoundInProfile){
			boolean isVideoFoundInVideos = verifyVideosInVideosSection(videoTitle_text);
			
			if(isVideoFoundInVideos){
				boolean isVideoFound = comm.verifyPostIsPresent(videoTitle_text); 
				if(isVideoFound==true){
					logInfo(videoTitle_text + " video found in community wall page.");
					comm.deleteCommunityPost(videoTitle_text);
				} else {
					logInfo(videoTitle_text + " video not found in community wall page.");
					Assert.assertTrue(isVideoFound, videoTitle_text + " video not found in community wall page.");
				}
			}
			else{
				Assert.assertTrue(isVideoFoundInVideos, videoTitle_text + " video not found under videos section in profile page.");
			}
			
		}
		else{
			Assert.assertTrue(isVideoFoundInProfile, videoTitle_text + " video not found in community profile page.");
		}
			
	  }
	
	

}