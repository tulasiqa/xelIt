package vibe.resourcelibrary3.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.SocialNetWorksMethods;
import vibe.inbox.tests.InboxMethods;

public class RL3Tests extends RL3Methods {

	SocialNetWorksMethods sm = new SocialNetWorksMethods();
	InboxMethods in = new InboxMethods();
	String resourceCategoryId = null;




	@Test(priority = 11500)
	public void SelectAResourceAsset() throws Exception {
		logInfo("inside SelectAResourceAsset() Test");
		navigate2UserRL();
		boolean isSearchFound = searchResourceUserSide(multipleResourceTitle_text,parentCategory_text);
		if(isSearchFound) {
		verifyAResourceAssetUserSide(resourceAssetPDF);
		selectAResourceAssetUserSide(resourceAssetPDF);
		closeWorkSpace();
		}
		if (!isSearchFound) {
			logInfo(multipleResourceTitle_text + "is not showing in search Resources");
			Assert.assertTrue(isSearchFound, multipleResourceTitle_text + "is not showing in search Resources");
		}

	}

	@Test(priority = 11501)
	public void PreviewAResourceAsset() throws Exception {
		logInfo("inside PreviewAResourceAsset() Test");
		navigate2UserRL();
		boolean isSearchFound = searchResourceUserSide(multipleResourceTitle_text,parentCategory_text);
		if(isSearchFound) {
		verifyAResourceAssetUserSide(resourceAssetPDF);
		previewAssetFromOptionsUserSide(resourceAssetPDF);
		closeWorkSpace();
		}
		if (!isSearchFound) {
			logInfo(multipleResourceTitle_text + "is not showing in search Resources");
			Assert.assertTrue(isSearchFound, multipleResourceTitle_text + "is not showing in search Resources");
		}
	}

	@Test(priority = 11502)
	public void DownloadAResourceAssetFromOptions() throws Exception {
		logInfo("inside DownloadAResourceAssetFromOptions() Test");
		navigate2UserRL();
		boolean isSearchFound =searchResourceUserSide(multipleResourceTitle_text,parentCategory_text);
		if(isSearchFound) {
		verifyAResourceAssetUserSide(resourceAssetPDF);
		downloadAssetFromOptions(resourceAssetPDF);
	}
	if (!isSearchFound) {
		logInfo(multipleResourceTitle_text + "is not showing in search Resources");
		Assert.assertTrue(isSearchFound, multipleResourceTitle_text + "is not showing in search Resources");
	}
}

	@Test(priority = 11503)
	public void LikeAResourceAsset() throws Exception {
		logInfo("Inside LikeAResourceAsset() Test");
		navigate2UserRL();
		boolean isAssetFound = searchAsset(resourceAssetPDF);
		if(isAssetFound) {
		likeAnAssetWorkSpace();
		closeWorkSpace();
		}
		if (!isAssetFound) {
			logInfo(resourceAssetPDF + "is not showing in search Assets");
			Assert.assertTrue(isAssetFound, resourceAssetPDF + "is not showing in search Resources");
		}
		
		
	}

	@Test(priority = 11504)
	public void UnlikeAResourceAsset() throws Exception {
		logInfo("Inside UnlikeAResourceAsset() Test");
		navigate2UserRL();
		boolean isAssetFound = searchAsset(resourceAssetPDF);
		if(isAssetFound) {
		unlikeAnAssetWorkSpace();
		closeWorkSpace();
		}
		if (!isAssetFound) {
			logInfo(resourceAssetPDF + "is not showing in search Assets");
			Assert.assertTrue(isAssetFound, resourceAssetPDF + "is not showing in search Resources");
		}
	}

	@Test(priority = 11505)
	public void SaveAsFavoriteAResourceAsset() throws Exception {
		logInfo("Inside SaveAsFavoriteAResourceAsset() Test");
		navigate2UserRL();
		boolean isAssetFound =searchAsset(resourceAssetPDF);
		if(isAssetFound) {
		saveAsFavoriteWorkSpace();
		navigate2UserRL();
		verifyAssetFromFavoritesSection(resourceAssetPDF);
		unlikeAnAssetWorkSpace();
		closeWorkSpace();
		}
		if (!isAssetFound) {
			logInfo(resourceAssetPDF + "is not showing in search Assets");
			Assert.assertTrue(isAssetFound, resourceAssetPDF + "is not showing in search Resources");
		}


	}

	@Test(priority = 11506)
	public void RemoveAsFavoriteAResourceAsset() throws Exception {
		logInfo("Inside RemoveAsFavoriteAResourceAsset() Test");
		navigate2UserRL();
		boolean isAssetFound =searchAsset(resourceAssetPDF);
		if(isAssetFound) {
		removeAsFavoriteWorkSpace();
		closeWorkSpace();
		}
		if (!isAssetFound) {
			logInfo(resourceAssetPDF + "is not showing in search Assets");
			Assert.assertTrue(isAssetFound, resourceAssetPDF + "is not showing in search Resources");
		}
	}

	@Test(priority = 11507)
	public void VerifyDownloadAssetByClickingDownloadIcon() throws Exception {
		logInfo("inside VerifyDownloadAssetByClickingDownloadIcon() Test");
		navigate2UserRL();
		boolean isAssetFound =searchAsset(resourceAssetPDF);
		if(isAssetFound) {
		downloadassetFromWs();
		closeWorkSpace();
		}
		if (!isAssetFound) {
			logInfo(resourceAssetPDF + "is not showing in search Assets");
			Assert.assertTrue(isAssetFound, resourceAssetPDF + "is not showing in search Resources");
		}

	}

	@Test(priority = 11508)
	public void ShareResourceAsset2Community() throws Exception {
		logInfo("inside shareResourceAsset2Community() Test.");
		navigate2UserRL();
		boolean isAssetFound =searchAsset(resourceAssetPDF);
		if(isAssetFound) {
		boolean isAssetShared = shareAssetToCommunityFromWs();
		if (isAssetShared) {
			closeWorkSpace();
		} else {
			logInfo(isAssetShared + " Unable to share the The asset to community.");
			Assert.assertTrue(isAssetShared, " Unable to find the asset to community");
		}
		}
		if (!isAssetFound) {
			logInfo(resourceAssetPDF + "is not showing in search Assets");
			Assert.assertTrue(isAssetFound, resourceAssetPDF + "is not showing in search Resources");
		}

	}

	@Test(priority = 11509)
	public void ShareResourceAsset2FaceBook() throws Exception {
		logInfo("Inside ShareResourceAsset2FaceBook Method");
		navigate2UserRL();
		boolean isAssetFound =searchAsset(resourceAssetPDF);
		if(isAssetFound) {
		selectFacebookIconFromWs_RL();
		sm.shareInFaceBook(resourceAssetPDF);
		sm.go2FacebookPage();
		sm.getPostsFromFB(resourceAssetPDF);
		}
		if (!isAssetFound) {
			logInfo(resourceAssetPDF + "is not showing in search Assets");
			Assert.assertTrue(isAssetFound, resourceAssetPDF + "is not showing in search Resources");
		}
	}

	@Test(priority = 11510)
	public void ShareResourceAsset2Twitter() throws Exception {
		logInfo("Inside ShareResourceAsset2Twitter Method");
		navigate2UserRL();
		boolean isAssetFound =searchAsset(resourceAssetPDF);
		if(isAssetFound) {	
		selectTwitterIconFromWs_Rl();
		sm.shareInTwitter();
		sm.go2TwitterPage();
		sm.verifyPostsInTwitter(resourceAssetPDF);
		}
		if (!isAssetFound) {
			logInfo(resourceAssetPDF + "is not showing in search Assets");
			Assert.assertTrue(isAssetFound, resourceAssetPDF + "is not showing in search Resources");
		}

	}

	@Test(priority = 11511)
	public void ShareResourceAsset2PWP() throws Exception {
		logInfo("inside ShareResourceAsset2PWP() Test.");
		navigate2UserRL();
		boolean isAssetFound =searchAsset(resourceAssetPDF);
		if(isAssetFound) {	
	boolean isResAssetShared = shareToMyWebsite(resourceAssetPDF);
		if (!isResAssetShared) {
			Assert.assertTrue(isResAssetShared, "Unable to share the resource to the PWP.");
		}

		}
		if (!isAssetFound) {
			logInfo(resourceAssetPDF + "is not showing in search Assets");
			Assert.assertTrue(isAssetFound, resourceAssetPDF + "is not showing in search Resources");
		}
	}

	@Test(priority = 11512) 
	public void ShareResourceAsset2Email() throws Exception {
		logInfo("inside sShareResourceAsset2Email() Test.");
		navigate2UserRL();
		boolean isAssetFound =searchAsset(resourceAssetPDF);
		if(isAssetFound) {	
		shareResourceByEmail(resourceAssetPDF, prop.getLocatorForEnvironment(appUrl, "selfmailId1"));
		in.go2Inbox();
		boolean isMailFound = in.selectVibeMailsWithSubject(composeEmailSubject_text);
		if (isMailFound == true) {
			in.deleteFilteredVibeMails();
		} else {
			Assert.assertTrue(isMailFound, rlMessageSubject_text + " message not be found in Inbox.");
			logInfo(rlMessageSubject_text + " message not be found in Inbox.");
		}
		}
		if (!isAssetFound) {
			logInfo(resourceAssetPDF + "is not showing in search Assets");
			Assert.assertTrue(isAssetFound, resourceAssetPDF + "is not showing in search Resources");
		}

	}

	@Test(priority = 11514)
	public void AccessResourceAssetFromNewestFiles() throws Exception {
		logInfo("Inside AccessResourceAssetFromNewestFiles() Test");
		navigate2UserRL();
		verifyAndSelectAssetFromNewstFilesSection(resourceAssetCB);
		closeWorkSpace();

	}

	@Test(priority = 11515) 
	public void SelectAllAssetsAndSendBulkEmail() throws Exception {
		logInfo("Inside SelectAllAssetsAndSendBulkEmail() Test");
		navigate2UserRL();
		boolean isSearchFound = searchResourceUserSide(multipleResourceTitle_text,parentCategory_text);
		if(isSearchFound) {
		selectallAssetsAndSendBulkEMail(prop.getLocatorForEnvironment(appUrl, "selfmailId1"));
		closeSelectionWindow();
		in.go2Inbox();
		boolean isMailFound = in.selectVibeMailsWithSubject(composeEmailSubject_text);
		if (isMailFound == true) {
			in.deleteFilteredVibeMails();
		} else {
			Assert.assertTrue(isMailFound, rlMessageSubject_text + " message not be found in Inbox.");
			logInfo(rlMessageSubject_text + " message not be found in Inbox.");
		}
		}
		if (!isSearchFound) {
			logInfo(multipleResourceTitle_text + "is not showing in search Resources");
			Assert.assertTrue(isSearchFound, multipleResourceTitle_text + "is not showing in search Resources");
		}

	}

	@Test(priority = 11516)
	public void SelectAllAssetsAndDownload() throws Exception {
		logInfo("Inside SelectAllAssetsAndDownload() Test");
		navigate2UserRL();
		boolean isSearchFound =searchResourceUserSide(multipleResourceTitle_text,parentCategory_text);
		if(isSearchFound) {
		selectallAssetsAndDownload();
		verifyDownloadedFile("Image");
		verifyDownloadedFile("PDF");
		verifyDownloadedFile("Text");
		verifyDownloadedFile("Document");
		verifyDownloadedFile("Spreadsheet");
		verifyDownloadedFile("Presentation");
		verifyDownloadedFile("Zip");
		closeSelectionWindow();
		navigate2UserRL();
		}
		if (!isSearchFound) {
			logInfo(multipleResourceTitle_text + "is not showing in search Resources");
			Assert.assertTrue(isSearchFound, multipleResourceTitle_text + "is not showing in search Resources");
		}
	}

	
	@Test(priority=11517)
	public void AccessNextResourceFromWorkspace() throws Exception{
		logInfo("inside AccessNextResourceFromWorkspace() Test");
		navigate2UserRL();
		nav2ResouceCategoryUser();
		selectAResourceUserSide(multipleResourceTitle_text);
		selectAResourceAssetUserSide(resourceAssetPDF);
		nextResource();
		boolean isHeadingMatch = verifyResourcePreviewHeading(multipleResourceTitle_text);
		if (isHeadingMatch == true) {
			logInfo(multipleResourceTitle_text + " next resource link is not working");
			Assert.assertFalse(isHeadingMatch, multipleResourceTitle_text + " next resource link is not working");
		}

	}
		

	@Test(priority=11518)
	public void AccessPreviousResourceFromWorkspace() throws Exception{
		logInfo("inside AccessPreviousResourceFromWorkspace() Test");
		navigate2UserRL();
		nav2ResouceCategoryUser();
		selectAResourceUserSide(multipleResourceTitle_text);
		selectAResourceAssetUserSide(resourceAssetPDF);
		previousResource();
		boolean isHeadingMatch = verifyResourcePreviewHeading(multipleResourceTitle_text);
		if (isHeadingMatch == true) {
			logInfo(multipleResourceTitle_text + " next resource link is not working");
			Assert.assertFalse(isHeadingMatch, multipleResourceTitle_text + " next resource link is not working");
		}
		closeWorkSpace();
	}
		
	@Test(priority=11519)
	public void VerifySearchAssetUser() throws Exception{
		logInfo("inside VerifySearchAssetUser() Test");
		navigate2UserRL();
		boolean isAssetFound =searchAsset(resourceAssetPDF);
		if(isAssetFound) {	
		closeWorkSpace();
		}
		if (!isAssetFound) {
			logInfo(resourceAssetPDF + "is not showing in search Assets");
			Assert.assertTrue(isAssetFound, resourceAssetPDF + "is not showing in search Resources");
		}
	}
	
//	@Test(priority=11520)
	public void VerifyPendingResourceUser() throws Exception{
		logInfo("inside VerifyPendingResourceUser() Test");
		navigate2UserRL();
		boolean isSearchFound = searchResourceUserSide(newResourceTitle_text11,parentCategory_text);
		if (!isSearchFound) {
			logInfo(multipleResourceTitle_text + "is not showing in search Resources");
			Assert.assertTrue(isSearchFound, multipleResourceTitle_text + "is not showing in search Resources");
		}
	}
	
	@Test(priority = 11520)
	public void AddMyResourceUser() throws Exception {
		logInfo("inside AddMyResourceUser() Test");
		go2HomePage();
		navigate2UserRL();
		addNewResourceUserSide(newResourceTitle_text14, true, 1);
		boolean isCategoryFound = verifyAResourceCategoryUserSide("My Resources");
		if(isCategoryFound) {
		selectAResourceCategoryUserSide("My Resources");
		selectAResourceUserSide(newResourceTitle_text14);
		selectAResourceAssetUserSide(resourceUserAssetPDF);
		closeWorkSpace();
		}
		else {
			logInfo("My Resources" + " Unable to find the The category");
			Assert.assertTrue(isCategoryFound, "My Resources" + " Unable to find the category");
		}
	}
	
	
	@Test(priority = 11521)
	public void EditMyResourceUser() throws Exception {
		logInfo("inside EditMyResourceUser() Test");
		navigate2UserRL();
		boolean isCategoryFound = verifyAResourceCategoryUserSide("My Resources");
		if(isCategoryFound) {
		selectAResourceCategoryUserSide("My Resources");
		performActionsFromResourceDropDownUser("Edit",newResourceTitle_text14);
		editResourceTitle_User(myResourceTitle_textUpd);
		closeWorkSpace();
		/*selectAResourceUserSide(myResourceTitle_textUpd);
		selectAResourceAssetUserSide(resourceUserAssetPDF);*/
		}
		else {
			logInfo("My Resources" + " Unable to find the The category");
			Assert.assertTrue(isCategoryFound, "My Resources" + " Unable to find the category");
		}
	}
	
	@Test(priority = 11522)
	public void CloneMyResourceUser() throws Exception {
		logInfo("inside CloneMyResourceUser() Test");
		navigate2UserRL();
		boolean isCategoryFound = verifyAResourceCategoryUserSide("My Resources");
		if(isCategoryFound) {
		selectAResourceCategoryUserSide("My Resources");
		performActionsFromResourceDropDownUser("Clone",myResourceTitle_textUpd);
		cloneResourceUser(myResourceTitle_textCln);
		closeWorkSpace();
		}
		else {
			logInfo("My Resources" + " Unable to find the The category");
			Assert.assertTrue(isCategoryFound, "My Resources" + " Unable to find the category");
		}
	}
	
	
	@Test(priority = 11523)
	public void DeleteMyResourceUser() throws Exception {
		logInfo("inside DeleteMyResourceUser() Test");
		navigate2UserRL();
		boolean isCategoryFound = verifyAResourceCategoryUserSide("My Resources");
		if(isCategoryFound) {
			selectAResourceCategoryUserSide("My Resources");
			performActionsFromResourceDropDownUser("Delete",myResourceTitle_textCln);
		//	confirmationMessage("Resource Was Successfully Deleted");
			userLogout();
			goToResourceSettings();
		}
		else {
			userLogout();
			logInfo("My Resources" + " Unable to find the The category");
			Assert.assertTrue(isCategoryFound, "My Resources" + " Unable to find the category");
		}
	}
	
}
