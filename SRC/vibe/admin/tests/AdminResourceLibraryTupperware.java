package vibe.admin.tests;

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.inbox.tests.InboxMethods;
import vibe.marketing.YouTube.tests.YoutubeMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.resourcelibrary3.tests.RL3Methods;
import vibe.shopping.tests.ShoppingMethods;
import vibe.shopping2.tests.Shopping2Methods;
import vibe.training.tests.TrainingMethods;
import common.Priority;
import common.EnvProperties;

@Priority(15)
public class AdminResourceLibraryTupperware extends RL3Methods {

	InboxMethods inbox = new InboxMethods();
	YoutubeMethods ym = new YoutubeMethods();
	EnvProperties prop = new EnvProperties();
	ShoppingMethods sm = new ShoppingMethods();
	CommunityMethods cm = new CommunityMethods();
	MyProfileMethods profile = new MyProfileMethods();
	TrainingMethods tm = new TrainingMethods();
	Shopping2Methods sm2 = new Shopping2Methods();
	
	/*  String parentCategory_text = "PCAT_810"; 
	  String multipleResourceTitle_text ="Business Multiple307"; 
	  String resourceAssetPDF = "PDF Asset 379";
	 */

	@Test(priority = 1501)
	public void getDefaultLayoutViewFromSettings() throws  Exception {
		logInfo("inside DownloadAResourceAssetFromOptions() Test");
		nav2ResouceSettings();
		String resourceView = getDefaultView("Resource Library Layout");
		prop.setLocatorForEnvironment(appUrl, "resourceLayout", resourceView);
		String resMarketFilter = getDefaultView("Resource Market Filter");
		prop.setLocatorForEnvironment(appUrl, "resourceMarketFilter", resMarketFilter);
		String resviewPastRes = getDefaultView("Ability To View Past By Resources");
		prop.setLocatorForEnvironment(appUrl, "PastByResources", resviewPastRes);
		String resMaxIcnSiz = getDefaultView("Resource Max Icon Size Limit");
		prop.setLocatorForEnvironment(appUrl, "MaxIconSize", resMaxIcnSiz);
		String resMaxFileSiz = getDefaultView("Resource Max File Size Limit");
		prop.setLocatorForEnvironment(appUrl, "MaxFileSize", resMaxFileSiz);
		String resMaxVidSiz = getDefaultView("Resource Max Video Size Limit");
		prop.setLocatorForEnvironment(appUrl, "MaxVideoSize", resMaxVidSiz);
		String resSTSN = getDefaultView("Share Resources To Social Networks");
		prop.setLocatorForEnvironment(appUrl, "ShareToSocialNetworks", resSTSN);
		String resToCom = getDefaultView("Share Resources To Community");
		prop.setLocatorForEnvironment(appUrl, "ShareToCommunity", resToCom);
		String resToPwp = getDefaultView("Share Resources To Pwp");
		prop.setLocatorForEnvironment(appUrl, "ShareToPwp", resToPwp);
		String resSEL = getDefaultView("Resource Library Send Email As Link Only");
		prop.setLocatorForEnvironment(appUrl, "SendEmailAsLink", resSEL);
		String resSTE = getDefaultView("Resource Library Share Through Email");
		prop.setLocatorForEnvironment(appUrl, "ShareThroughEmail", resSTE);
		String resREP = getDefaultView("Number Of Resources Per Page");
		prop.setLocatorForEnvironment(appUrl, "ResourcesPerPage", resREP);
		String resAW = getDefaultView("Open Asset Workspace From Resource Overlay");
		prop.setLocatorForEnvironment(appUrl, "AssetWorkspaceOverlay", resAW);
		String resAC = getDefaultView("Ability To Search Assets Content");
		prop.setLocatorForEnvironment(appUrl, "SearchAssetsContent", resAC);
		String resABU = getDefaultView("Ability To Add Resources By Users");
		prop.setLocatorForEnvironment(appUrl, "AddResourcesByUsers", resABU);
		String resULU = getDefaultView("Resources Upload Limit For Users");
		prop.setLocatorForEnvironment(appUrl, "UploadLimitForUsers", resULU);

	}

	@Test(priority = 1502)
	public void setDefaultLayoutViewFromSettings() throws Exception {
		logInfo("inside setDefaultLayoutViewFromSettings() Test");
		nav2ResouceSettings();
		enableOrDisableResourceOptions("Open Asset Workspace From Resource Overlay", "off");

	}

	@Test(priority = 1503)
	public void CreateRLCategory() throws Exception {
		logInfo("inside createRLCategory() Test");
		navigate2ManageCategories();
		addResourceCategory(parentCat1, "None");
		nav2AdminRL();
		navigate2ManageCategories();
		addResourceCategory(parentCat1, childCat1);
		waitOnSpinner();
		boolean isAdded = verifyResourceCategoryPresentInCategoriesPage(parentCat1);
		if (isAdded = false) {
			logInfo(parentCat1 + " product category could not be added.");
			Assert.assertTrue(isAdded, parentCat1 + " product category could not be added.");
		}
	}

	@Test(priority = 1504)
	public void EditRLCategory() throws Exception {
		logInfo("inside EditRLCategory() Test");
		nav2AdminRL();
		navigate2ManageCategories();
		editResourceCategory(parentCat1, parentCategory_text);
		navigate2ManageCategories();
		boolean isCatFound = verifyResourceCategoryPresentInCategoriesPage(parentCategory_text);
		if (isCatFound = false) {
			logInfo(parentCategory_text + " Resource category could not be edited.");
			Assert.assertTrue(isCatFound, parentCategory_text + " Resource category could not be edited.");
		}
	}

	@Test(priority = 1505)
	public void DeleteChildRLCategory() throws Exception {
		logInfo("inside DeleteChildRLCategory() Test");
		nav2AdminRL();
		navigate2ManageCategories();
		deleteChildResourceCategories(parentCategory_text, childCat1);
		waitOnSpinner();
		boolean isAdded = verifyResourceCategoryPresentInCategoriesPage(childCat1);
		if (isAdded = true) {
			logInfo(childCat1 + " product category could not be deleted.");
			Assert.assertTrue(isAdded, childCat1 + " product category could not be deleted.");
		}
		// confirmationMessage("Resource Category deleted successfully.");
	}

	@Test(priority = 1506)
	public void AddResourceWithMultipleAssets() throws Exception {
		logInfo("inside AddResourceWithMultipleAssets() Test");
		navigate2ManageCategories();
		String categoryId = getCategoryId(parentCategory_text);
		prop.setLocatorForEnvironment(appUrl, "categoryIdURl", categoryId);
		nav2AdminRL();
		addNewResourceWithMultipleAssets(multipleResourceTitle_text, parentCategory_text, true, false, "Active", 1);
		waitOnSpinner();
		navigate2ManageCategories();
		nav2AdminRL();
		searchResourceAdmin(multipleResourceTitle_text);
		// String resourceId = getResourceId(); prop.setLocatorForEnvironment(appUrl,
		// "resId", resourceId);
		boolean isReosurcePresent = verifyResourcePresentAdmin(multipleResourceTitle_text);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(multipleResourceTitle_text);
			if (isReosurcePresent = false) {
				logInfo(multipleResourceTitle_text + " Resource is not present ");
				Assert.assertTrue(isReosurcePresent, multipleResourceTitle_text + " Resource is not present");
			}
		}
	}

	@Test(priority = 1507)
	public void VerifySearchResourceAdmin() throws Exception {
		logInfo("inside VerifySearchResourceAdmin() Test");
		nav2AdminRL();
		searchResourceAdmin(multipleResourceTitle_text);

	}

	@Test(priority = 1508)
	public void VerifySearchAssetAdmin() throws Exception {
		logInfo("inside VerifySearchAssetAdmin() Test");
		nav2AdminRL();
		searchAsset(resourceAssetPDF);

	}

	// @Test(priority = 1508)
	public void VerifySearchTagAdmin() throws Exception {
		logInfo("inside VerifySearchTagAdmin() Test");
		nav2AdminRL();
		searchAsset(resourceTagPDF);

	}

	@Test(priority = 1509)
	public void PreviewResourceAssetAdmin() throws Exception {
		logInfo("inside PreviewAResourceAssetAdmin() Test");
		nav2AdminRL();
		selectReourceOrCategoryTab("Categories");
		searchCategory(parentCategory_text);
		verifyResourcePresentAdmin(multipleResourceTitle_text);
		performActionsFromResourceDropDownAdmin("Preview", multipleResourceTitle_text);

	}

	@Test(priority = 1510)
	public void cloneResourceInAdmin() throws Exception {
		logInfo("inside cloneResourceInAdmin() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		selectReourceOrCategoryTab("Categories");
		searchCategory(parentCategory_text);
		performActionsFromResourceDropDownAdmin("Clone", multipleResourceTitle_text);
		cloneResource(newCloneResourceTitle_text);
		boolean isReosurcePresent = verifyResourcePresentAdmin(newCloneResourceTitle_text);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(newCloneResourceTitle_text);
			if (isReosurcePresent = false) {
				logInfo(newCloneResourceTitle_text + " Resource is not present ");
				Assert.assertTrue(isReosurcePresent, newCloneResourceTitle_text + " Resource is not present");
			}
		}
	}

	@Test(priority = 1511)
	public void EditResourceInAdmin() throws Exception {
		logInfo("inside editResourceInAdmin() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		selectReourceOrCategoryTab("Categories");
		searchCategory(parentCategory_text);
		performActionsFromResourceDropDownAdmin("Edit", newCloneResourceTitle_text);
		editResourceTitle(multipleResourceTitle_textUpd);
		// confirmationMessage("Resource is updated");
		boolean isReosurcePresent = verifyResourcePresentAdmin(multipleResourceTitle_textUpd);
		if (isReosurcePresent = false) {
			logInfo(multipleResourceTitle_textUpd + " Resource is not present ");
			Assert.assertTrue(isReosurcePresent, multipleResourceTitle_textUpd + " Resource is not present");
		}

	}

	@Test(priority = 1512)
	public void DownloadResourceAssetAdmin() throws Exception {
		logInfo("inside DownloadResourceAssetAdmin() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		selectReourceOrCategoryTab("Categories");
		searchCategory(parentCategory_text);
		performActionsFromResourceDropDownAdmin("Download", multipleResourceTitle_text);

	}

	@Test(priority = 1513)
	public void AccessNextResourceFromWorkspaceAdmin() throws Exception {
		logInfo("inside AccessNextResourceFromWorkspace() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		selectReourceOrCategoryTab("Categories");
		searchCategory(parentCategory_text);
		selectResourcePresentAdmin(multipleResourceTitle_text);
		selectAResourceAssetAdminSide(resourceAssetPDF);
		nextResource();
		waitOnSpinner();
		boolean isHeadingMatch = verifyResourcePreviewHeading(multipleResourceTitle_text);
		if (isHeadingMatch) {
			logInfo(multipleResourceTitle_text + " next resource link is not working");
			Assert.assertFalse(isHeadingMatch, multipleResourceTitle_text + " next resource link is not working");
		}

	}

	@Test(priority = 1514)
	public void AccessPreviousResourceFromWorkspaceAdmin() throws Exception {
		logInfo("inside AccessPreviousResourceFromWorkspace() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		selectReourceOrCategoryTab("Categories");
		searchCategory(parentCategory_text);
		selectResourcePresentAdmin(multipleResourceTitle_text);
		selectAResourceAssetAdminSide(resourceAssetPDF);
		previousResource();
		waitOnSpinner();
		boolean isHeadingMatch = verifyResourcePreviewHeading(multipleResourceTitle_text);
		if (isHeadingMatch) {
			logInfo(multipleResourceTitle_text + " next resource link is not working");
			Assert.assertFalse(isHeadingMatch, multipleResourceTitle_text + " next resource link is not working");
		}

	}

	@Test(priority = 1515)
	public void AddResourceWithPdfAsset() throws Exception {
		logInfo("inside AddResourceWithPdfAsset() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		addNewResource(newResourceTitle_text, parentCategory_text, resourceTagPDF, true, false, "PDF", resourceAssetPDF,
				"Active", 1);
		navigate2ManageCategories();
		nav2AdminRL();
		boolean isReosurcePresent = verifyResourcePresentAdmin(newResourceTitle_text);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(newResourceTitle_text);
			selectAResourceAssetAdminSide(resourceAssetPDF);
			verifyResourcePreviewHeading(newResourceTitle_text);

		}
	}

	@Test(priority = 1516)
	public void AddResourceWithVideoAsset() throws Exception {
		logInfo("inside AddResourceWithVideoAsset() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		addNewResource(newResourceTitle_text1, parentCategory_text, resourceTagVideo, true, false, "Video",
				resourceAssetVideo, "Active", 1);
		waitOnSpinner();
		navigate2ManageCategories();
		nav2AdminRL();
		boolean isReosurcePresent = verifyResourcePresentAdmin(newResourceTitle_text1);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(newResourceTitle_text1);
			selectAResourceAssetAdminSide(resourceAssetVideo);
			verifyResourcePreviewHeading(newResourceTitle_text1);

		}

	}

	@Test(priority = 1517)
	public void AddResourceWithTextAsset() throws Exception {
		logInfo("inside AddResourceWithTextAsset() Test");
		nav2AdminRL();
		addNewResource(newResourceTitle_text2, parentCategory_text, resourceTagText, true, false, "Text",
				resourceAssetText, "Active", 1);
		waitOnSpinner();
		navigate2ManageCategories();
		nav2AdminRL();
		boolean isReosurcePresent = verifyResourcePresentAdmin(newResourceTitle_text2);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(newResourceTitle_text2);
			selectAResourceAssetAdminSide(resourceAssetText);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text2);
			if (isHeadingMatch) {
				navigate2ManageCategories();
				nav2AdminRL();
				performActionsFromResourceDropDownAdmin("Delete", newResourceTitle_text2);
			}

		}
	}

	@Test(priority = 1518)
	public void AddResourceWithDocumentAsset() throws Exception {
		logInfo("inside AddResourceWithDocumentAsset() Test");
		nav2AdminRL();
		addNewResource(newResourceTitle_text3, parentCategory_text, resourceTagDocument, true, false, "Document",
				resourceAssetDocument, "Active", 1);
		waitOnSpinner();
		navigate2ManageCategories();
		nav2AdminRL();
		boolean isReosurcePresent = verifyResourcePresentAdmin(newResourceTitle_text3);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(newResourceTitle_text3);
			selectAResourceAssetAdminSide(resourceAssetDocument);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text3);
			if (isHeadingMatch) {
				navigate2ManageCategories();
				nav2AdminRL();
				performActionsFromResourceDropDownAdmin("Delete", newResourceTitle_text3);
			}

		}
	}

	@Test(priority = 1519)
	public void AddResourceWithSpreadsheetAsset() throws Exception {
		logInfo("inside AddResourceWithSpreadsheetAsset() Test");
		nav2AdminRL();
		addNewResource(newResourceTitle_text4, parentCategory_text, resourceTagSheet, true, false, "Spreadsheet",
				resourceAssetSSheet, "Active", 1);
		waitOnSpinner();
		navigate2ManageCategories();
		nav2AdminRL();
		boolean isReosurcePresent = verifyResourcePresentAdmin(newResourceTitle_text4);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(newResourceTitle_text4);
			selectAResourceAssetAdminSide(resourceAssetSSheet);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text4);
			if (isHeadingMatch) {
				navigate2ManageCategories();
				nav2AdminRL();
				performActionsFromResourceDropDownAdmin("Delete", newResourceTitle_text4);
			}

		}
	}

	@Test(priority = 1520)
	public void AddResourceWithZIPAsset() throws Exception {
		logInfo("inside AddResourceWithZIPAsset() Test");
		nav2AdminRL();
		addNewResource(newResourceTitle_text5, parentCategory_text, resourceTagZip, true, false, "ZIP",
				resourceAssetZip, "Active", 1);
		waitOnSpinner();
		navigate2ManageCategories();
		nav2AdminRL();
		boolean isReosurcePresent = verifyResourcePresentAdmin(newResourceTitle_text5);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(newResourceTitle_text5);
			selectAResourceAssetAdminSide(resourceAssetZip);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text5);
			if (isHeadingMatch) {
				navigate2ManageCategories();
				nav2AdminRL();
				performActionsFromResourceDropDownAdmin("Delete", newResourceTitle_text5);
			}
		}
	}

	@Test(priority = 1521)
	public void AddResourceWithPresentationAsset() throws Exception {
		logInfo("inside AddResourceWithPresentationAsset() Test");
		nav2AdminRL();
		addNewResource(newResourceTitle_text6, parentCategory_text, resourceTagPresentation, true, false,
				"Presentation", resourceAssetPresentation, "Active", 1);
		waitOnSpinner();
		navigate2ManageCategories();
		nav2AdminRL();
		boolean isReosurcePresent = verifyResourcePresentAdmin(newResourceTitle_text6);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(newResourceTitle_text6);
			selectAResourceAssetAdminSide(resourceAssetPresentation);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text6);
			if (isHeadingMatch) {
				navigate2ManageCategories();
				nav2AdminRL();
				performActionsFromResourceDropDownAdmin("Delete", newResourceTitle_text6);
			}

		}
	}

	@Test(priority = 1522)
	public void AddResourceWithImageAsset() throws Exception {
		logInfo("inside AddResourceWithImageAsset() Test");
		nav2AdminRL();
		addNewResource(newResourceTitle_text7, parentCategory_text, resourceTagImage, true, false, "Image",
				resourceAssetImage, "Active", 1);
		waitOnSpinner();
		navigate2ManageCategories();
		nav2AdminRL();
		boolean isReosurcePresent = verifyResourcePresentAdmin(newResourceTitle_text7);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(newResourceTitle_text7);
			selectAResourceAssetAdminSide(resourceAssetImage);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text7);
			if (isHeadingMatch) {
				navigate2ManageCategories();
				nav2AdminRL();
				performActionsFromResourceDropDownAdmin("Delete", newResourceTitle_text7);
			}

		}

	}

	@Test(priority = 1523)
	public void AddResourceWithContentBlockAsset() throws Exception {
		logInfo("inside AddResourceWithContentBlockAsset() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		addNewResource(newResourceTitle_text8, parentCategory_text, resourceTagCB, true, false, "Content Block",
				resourceAssetCB, "Active", 1);
		waitOnSpinner();
		navigate2ManageCategories();
		nav2AdminRL();
		boolean isReosurcePresent = verifyResourcePresentAdmin(newResourceTitle_text8);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(newResourceTitle_text8);
			selectAResourceAssetAdminSide(resourceAssetCB);
			verifyResourcePreviewHeading(newResourceTitle_text8);

		}
	}

	@Test(priority = 1525)
	public void AddPendingResourceAndVerify() throws Exception {
		logInfo("inside AddPendingResourceAndVerify() Test");
		nav2AdminRL();
		addNewResource(newResourceTitle_text11, parentCategory_text, resourceTag, true, false, "PDF", resourceAsset,
				"Pending", 1);
		waitOnSpinner();
		navigate2ManageCategories();
		nav2AdminRL();
		selectStatus("Pending");
		boolean isResourcePresent = verifyResourcePresentAdmin(newResourceTitle_text11);
		if (isResourcePresent) {
			selectResourcePresentAdmin(newResourceTitle_text11);
			selectAResourceAssetAdminSide(resourceAsset);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text11);
			if (isHeadingMatch) {
				navigate2ManageCategories();
				nav2AdminRL();
				performActionsFromResourceDropDownAdmin("Delete", newResourceTitle_text11);
			}

		}
	}

	@Test(priority = 1526)
	public void RL_Settings_AbilityToViewNewestResources() throws Exception {
		logInfo("inside RL_Settings_AbilityToViewNewestResources() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		goToResourceSettings();
		enableOrDisableResourceOptions("Ability To View Past By Resources", " ");
		nav2AdminRL();
		addNewResource(newResourceTitle_text12, parentCategory_text, resourceTagVideo, true, true, "Video",
				resourceAssetVideo, "Active", 1);
		waitOnSpinner();
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "newsFN1"),
				prop.getLocatorForEnvironment(appUrl, "newsLN1"), prop.getLocatorForEnvironment(appUrl, "newsCon1"));
		navigate2UserRL();
		waitOnSpinner();
		boolean isCategoryFound = verifyAResourceCategoryUserSide(prop.getLocatorForEnvironment(appUrl, "newest"));
		if (isCategoryFound) {
			selectAResourceCategoryUserSide(prop.getLocatorForEnvironment(appUrl, "newest"));
			verifyResourcePresentUserSide(newResourceTitle_text12);
			selectAResourceUserSide(newResourceTitle_text12);
			verifyAResourceAssetUserSide(resourceAssetVideo);
			previewAssetFromOptionsUserSide(resourceAssetVideo);
			navigate2UserRL();
			go2HomePage();
			userLogout();

		}
		if (isCategoryFound == false) {
			logInfo("Newest Resources Category is not found ");
			go2HomePage();
			userLogout();
			Assert.assertTrue(isCategoryFound, "Newest Resources Category is not found ");
		}

	}

	@Test(priority = 1527)
	public void RL_Settings_AbilityToViewPastResourcesBy14Ddays() throws Exception {
		logInfo("inside AbilityToViewPastResourcesBy14Ddays() Test");
		nav2AdminRL();
		goToResourceSettings();
		enableOrDisableResourceOptions("Ability To View Past By Resources", "1");
		nav2AdminRL();
		addNewResource(newResourceTitle_text13, parentCategory_text, resourceAssetVideo, true, false, "Video",
				resourceAssetVideo, "Active", 1);
		waitOnSpinner();
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "newsFN1"),
				prop.getLocatorForEnvironment(appUrl, "newsLN1"), prop.getLocatorForEnvironment(appUrl, "newsCon1"));
		navigate2UserRL();
		boolean isCategoryFound = verifyAResourceCategoryUserSide(prop.getLocatorForEnvironment(appUrl, "newest"));
		if (isCategoryFound) {
			selectAResourceCategoryUserSide(prop.getLocatorForEnvironment(appUrl, "newest"));
			verifyResourcePresentUserSide(newResourceTitle_text13);
			selectAResourceUserSide(newResourceTitle_text13);
			verifyAResourceAssetUserSide(resourceAssetVideo);
			previewAssetFromOptionsUserSide(resourceAssetVideo);
			navigate2UserRL();
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Ability To View Past By Resources", "14");

		}
		if (isCategoryFound == false) {
			logInfo(prop.getLocatorForEnvironment(appUrl, "newest") + " Category is not found ");
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Ability To View Past By Resources", "14");
			Assert.assertTrue(isCategoryFound,
					prop.getLocatorForEnvironment(appUrl, "newest") + " Category is not found");
		}

	}

	@Test(priority = 1528)
	public void AddOtherMarketResourceAndVerify() throws Exception {
		logInfo("inside AddOtherMarketResourceAndVerify() Test");
		nav2AdminRL();
		addNewCAResource(newResourceTitle_text10, parentCategory_text, true, false, "PDF", resourceAsset, "Active", 1);
		navigate2ManageCategories();
		nav2AdminRL();
		selectMarkets("US (Spanish)");
		boolean isReosurcePresent = verifyResourcePresentAdmin(newResourceTitle_text10);
		if (isReosurcePresent) {
			selectResourcePresentAdmin(newResourceTitle_text10);
			selectAResourceAssetAdminSide(resourceAsset);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text10);
			if (isHeadingMatch == false) {
				logInfo(newResourceTitle_text10 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch,
						newResourceTitle_text10 + " does not matches the title in resource preview page.");
			}
			if (isReosurcePresent = false)

			{
				logInfo(newResourceTitle_text10 + " Resource is not found ");
				Assert.assertTrue(isReosurcePresent, newResourceTitle_text10 + " Resource is not present");
			}
		}

	}

	@Test(priority = 1529)
	public void RL_Settings_verifySharetoCommunityOff() throws Exception {
		logInfo("inside Share Resources To Community() Test");
		goToResourceSettings();
		enableOrDisableResourceOptions("Share Resources To Community", "off");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "newsFN1"),
				prop.getLocatorForEnvironment(appUrl, "newsLN1"), prop.getLocatorForEnvironment(appUrl, "newsCon1"));
		navigate2UserRL();
		boolean isSearchFound  = searchResourceUserSide(multipleResourceTitle_text,parentCategory_text);
		if(isSearchFound) {
		boolean isResourceAssetFound = verifyAResourceAssetUserSide(resourceAssetPDF);
		if (isResourceAssetFound) {
			previewAssetFromOptionsUserSide(resourceAssetPDF);
			boolean issharetoCommuntiyFound = verifyElementPresent("cssSelector", postToCommIc);
			closeWorkSpace();
			go2HomePage();
			userLogout();
			nav2AdminRL();
			goToResourceSettings();
			enableOrDisableResourceOptions("Share Resources To Community", "on");

			if (issharetoCommuntiyFound) {
				go2HomePage();
				userLogout();
				goToResourceSettings();
				enableOrDisableResourceOptions("Share Resources To Community", "on");
				logInfo("Able to see share to community icon is showing even after setting is truned off");
				Assert.assertFalse(issharetoCommuntiyFound,
						"Able to see share to community icon is showing even after setting is truned off");
			}
		}
		if (!isResourceAssetFound)

		{
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Share Resources To Community", "on");
			logInfo("Able to see share to community icon is showing even after setting is truned off");
			logInfo(resourceAssetPDF + " Resource Asset is not found ");
			Assert.assertTrue(isResourceAssetFound, resourceAssetPDF + " Resource Asset is not present");
		}
		}
		if (!isSearchFound) {
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Share Resources To Community", "on");
			logInfo(multipleResourceTitle_text + "is not showing in search Resources");
			Assert.assertTrue(isSearchFound, multipleResourceTitle_text + "is not showing in search Resources");
		}
	}

	@Test(priority = 1530)
	public void RL_Settings_verifySharetoSocialNetworksOff() throws Exception {
		logInfo("inside RL_Settings_verifySharetoSocialNetworksOff Test");
		goToResourceSettings();
		enableOrDisableResourceOptions("Share Resources To Social Networks", "off");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "newsFN1"),
				prop.getLocatorForEnvironment(appUrl, "newsLN1"), prop.getLocatorForEnvironment(appUrl, "newsCon1"));
		navigate2UserRL();
		boolean isSearchFound  = searchResourceUserSide(multipleResourceTitle_text,parentCategory_text);
		if(isSearchFound) {
		boolean isResourceAssetFound = verifyAResourceAssetUserSide(resourceAssetPDF);
		if (isResourceAssetFound) {
			previewAssetFromOptionsUserSide(resourceAssetPDF);
			boolean issharetoTwitterFound = verifyElementPresent("cssSelector", twitterIcon);
			closeWorkSpace();
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Share Resources To Social Networks", "on");
			if (issharetoTwitterFound) {
				go2HomePage();
				userLogout();
				goToResourceSettings();
				enableOrDisableResourceOptions("Share Resources To Social Networks", "on");
				logInfo("Able to see share to social network icons even after setting is truned off");
				Assert.assertFalse(issharetoTwitterFound,
						"Able to see share to social network icons even after setting is truned off.");
			}
		}
		if (!isResourceAssetFound)

		{
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Share Resources To Social Networks", "on");
			Assert.assertTrue(isResourceAssetFound, resourceAssetPDF + " Resource Asset is not present");
		}
		}
		if (!isSearchFound) 
			go2HomePage();
		userLogout();
		goToResourceSettings();
		enableOrDisableResourceOptions("Share Resources To Social Networks", "on");{
			logInfo(multipleResourceTitle_text + "is not showing in search Resources");
			Assert.assertTrue(isSearchFound, multipleResourceTitle_text + "is not showing in search Resources");
		}

	}

	@Test(priority = 1531)
	public void RL_Settings_verifySharetoPwpOff() throws Exception {
		logInfo("inside RL_Settings_verifySharetoPwpOff Test");
		goToResourceSettings();
		enableOrDisableResourceOptions("Share Resources To Pwp", "off");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "newsFN1"),
				prop.getLocatorForEnvironment(appUrl, "newsLN1"), prop.getLocatorForEnvironment(appUrl, "newsCon1"));
		navigate2UserRL();
		boolean isSearchFound  = searchResourceUserSide(multipleResourceTitle_text,parentCategory_text);
		if(isSearchFound) {
		boolean isResourceAssetFound = verifyAResourceAssetUserSide(resourceAssetPDF);
		if (isResourceAssetFound) {
			previewAssetFromOptionsUserSide(resourceAssetPDF);
			boolean issharetoPwpFound = verifyElementPresent("cssSelector", sharePwpIc);
			closeWorkSpace();
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Share Resources To Pwp", "on");
			if (issharetoPwpFound) {
				go2HomePage();
				userLogout();
				goToResourceSettings();
				enableOrDisableResourceOptions("Share Resources To Pwp", "on");
				logInfo("Able to see share to pwp icon even after setting is truned off");
				Assert.assertFalse(issharetoPwpFound,
						"Able to see share to pwp icon even after setting is truned off.");
			}
		}
		if (!isResourceAssetFound)
		{
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Share Resources To Pwp", "on");
			Assert.assertTrue(isResourceAssetFound, resourceAssetPDF + " Resource Asset is not present");
		}
		}
		if (!isSearchFound) {
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Share Resources To Pwp", "on");
			logInfo(multipleResourceTitle_text + "is not showing in search Resources");
			Assert.assertTrue(isSearchFound, multipleResourceTitle_text + "is not showing in search Resources");
		}
	}

	@Test(priority = 1532)
	public void RL_Settings_verifySharetoEmail() throws Exception {
		logInfo("inside RL_Settings_verifySharetoEmail Test");
		goToResourceSettings();
		enableOrDisableResourceOptions("Resource Library Share Through Email", "off");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "newsFN1"),
				prop.getLocatorForEnvironment(appUrl, "newsLN1"), prop.getLocatorForEnvironment(appUrl, "newsCon1"));
		navigate2UserRL();
		boolean isSearchFound  = searchResourceUserSide(multipleResourceTitle_text,parentCategory_text);
		if(isSearchFound) {
		boolean isResourceAssetFound = verifyAResourceAssetUserSide(resourceAssetPDF);
		if (isResourceAssetFound) {
			previewAssetFromOptionsUserSide(resourceAssetPDF);
			boolean issharetoEMailFound = verifyElementPresent("cssSelector", shareEmailIc);
			closeWorkSpace();
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Resource Library Share Through Email", "on");
			if (issharetoEMailFound) {
				go2HomePage();
				userLogout();
				goToResourceSettings();
				enableOrDisableResourceOptions("Resource Library Share Through Email", "on");
				logInfo("Able to see share to email icon even after setting is truned off");
				Assert.assertFalse(issharetoEMailFound,
						"Able to see share to email icon even after setting is truned off.");
			}
		}
		if (!isResourceAssetFound)

		{
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Resource Library Share Through Email", "on");
			Assert.assertTrue(isResourceAssetFound, resourceAssetPDF + " Resource Asset is not present");
		}
		}
		if (!isSearchFound) {
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Resource Library Share Through Email", "on");
			logInfo(multipleResourceTitle_text + "is not showing in search Resources");
			Assert.assertTrue(isSearchFound, multipleResourceTitle_text + "is not showing in search Resources");
		}

	}

	@Test(priority = 1533)
	public void RL_Settings_verifyAbilityToAddResourcesByUsers_On() throws Exception {
		logInfo("inside RL_Settings_verifyAbilityToAddResourcesByUsers_On Test");
		goToResourceSettings();
		enableOrDisableResourceOptions("Ability To Add Resources By Users", "on");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "newsFN1"),
				prop.getLocatorForEnvironment(appUrl, "newsLN1"), prop.getLocatorForEnvironment(appUrl, "newsCon1"));
		navigate2UserRL();
		boolean isaddResourceLink = verifyElementPresent("cssSelector", addResourceUser);
		go2HomePage();
		userLogout();
		if (isaddResourceLink == false) {
			go2HomePage();
			userLogout();
			logInfo("Unable to see add resource link even after setting is truned off");
			Assert.assertFalse(isaddResourceLink, "Unable to see add resource link  even after setting is truned off.");
		}

	}

	@Test(priority = 1534)
	public void RL_Settings_verifyNumberOfResourcePerPageAdmin() throws Exception {
		logInfo("inside RL_Settings_verifyNumberOfResourcePerPageAdmin() Test");
		goToResourceSettings();
		enableOrDisableResourceOptions("Number Of Resources Per Page", "11");
		nav2AdminRL();
		int expectedCountAdmin = getResourceCount();
		Assert.assertEquals(expectedCountAdmin, 11);

	}

	@Test(priority = 1535)
	public void RL_Settings_verifyNumberOfCategoriesPerPageAdmin() throws Exception {
		logInfo("inside RL_Settings_verifyNumberOfCategoriesPerPageAdmin() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		selectReourceOrCategoryTab("Categories");
		int expectedCountAdmin = getResourceCount();
		go2HomePage();
		userLogout();
		goToResourceSettings();
		enableOrDisableResourceOptions("Number Of Resources Per Page", "25");
		Assert.assertEquals(expectedCountAdmin, 11);
	}

//	@Test(priority = 1536)
	public void RL_Settings_verifyNumberOfResourcesPerPageUser() throws Exception {
		logInfo("inside RL_Settings_verifyNumberOfResourcesPerPageUser() Test");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "newsFN1"),
				prop.getLocatorForEnvironment(appUrl, "newsLN1"), prop.getLocatorForEnvironment(appUrl, "newsCon1"));
		navigate2UserRL();
		boolean isCategoryFound = verifyAResourceCategoryUserSide(prop.getLocatorForEnvironment(appUrl, "newest"));
		selectAResourceCategoryUserSide(prop.getLocatorForEnvironment(appUrl, "newest"));
		go2HomePage();
		userLogout();
		goToResourceSettings();
		enableOrDisableResourceOptions("Number Of Resources Per Page", "25");
		if (isCategoryFound) {
			int expectedCountAdmin = getResourceCount();
			Assert.assertEquals(expectedCountAdmin, 11);
		}
		if (isCategoryFound == false) {
			logInfo(prop.getLocatorForEnvironment(appUrl, "newest") + " Category is not found ");
			Assert.assertTrue(isCategoryFound,
					prop.getLocatorForEnvironment(appUrl, "newest") + " Category is not found");
		}
	}

//	@Test(priority = 1537)
	public void RL_Settings_verifyNumberOfCategoriesPerPageUser() throws Exception {
		logInfo("inside RL_Settings_verifyNumberOfCategoriesPerPageUser() Test");
		navigate2UserRL();
		boolean isCategoryFound = verifyAResourceCategoryUserSide(prop.getLocatorForEnvironment(appUrl, "newest"));
		if (isCategoryFound) {
			int expectedCountAdmin = getResourceCount();
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Number Of Resources Per Page",
					prop.getLocatorForEnvironment(appUrl, "ResourcesPerPage"));
			Assert.assertEquals(expectedCountAdmin, 11);
		}
		if (isCategoryFound == false) {
			logInfo(prop.getLocatorForEnvironment(appUrl, "newest") + " Category is not found ");
			go2HomePage();
			userLogout();
			goToResourceSettings();
			enableOrDisableResourceOptions("Number Of Resources Per Page",
					prop.getLocatorForEnvironment(appUrl, "ResourcesPerPage"));
			Assert.assertTrue(isCategoryFound,
					prop.getLocatorForEnvironment(appUrl, "newest") + " Category is not found");
		}
	}

}
