package vibe.teardown.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import vibe.resourcelibrary3.tests.RL3Methods;
import common.EnvProperties;

@Priority(915)
public class AdminTearDownRl3 extends RL3Methods {

	EnvProperties prop = new EnvProperties();

	@Test(priority = 91501)
	public void setDefaultLayoutViewFromSettings() throws  Exception {
		logInfo("inside setDefaultLayoutViewFromSettings() Test");
		nav2ResouceSettings();
	//	String value = modifySettingVal(prop.getLocatorForEnvironment(appUrl, "PastByResources"));
	//	enableOrDisableResourceOptions("Ability To View Past By Resources", value);
		enableOrDisableResourceOptions("Ability To View Past By Resources", "14");
		enableOrDisableResourceOptions("Share Resources To Community",
				prop.getLocatorForEnvironment(appUrl, "ShareToCommunity"));
		enableOrDisableResourceOptions("Share Resources To Social Networks",
				prop.getLocatorForEnvironment(appUrl, "ShareToSocialNetworks"));
		enableOrDisableResourceOptions("Share Resources To Pwp", prop.getLocatorForEnvironment(appUrl, "ShareToPwp"));
		enableOrDisableResourceOptions("Resource Library Share Through Email",
				prop.getLocatorForEnvironment(appUrl, "ShareThroughEmail"));
		enableOrDisableResourceOptions("Number Of Resources Per Page",
				prop.getLocatorForEnvironment(appUrl, "ResourcesPerPage"));
	}

	@Test(priority = 91502)
	public void DeleteResourceAssetInAdmin() throws Exception {
		logInfo("inside DeleteResourceAssetInAdmin() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		selectReourceOrCategoryTab("Categories");
		searchCategory(parentCategory_text);
		performActionsFromResourceDropDownAdmin("Edit", multipleResourceTitle_text);
		deleteAssetFromEditResource(resourceAssetPDF);
		boolean isAssetDeleted = verifyAssetsAddedWhileCreatingAResource(resourceAssetPDF);
		if (isAssetDeleted == true) {
			logInfo(resourceAssetPDF + " resource could not be deleted.");
			Assert.assertFalse(isAssetDeleted, resourceAssetPDF + " resource could not be deleted.");
		}
	}

	@Test(priority = 91503)
	public void DeleteResourceInAdmin() throws Exception {
		logInfo("inside DeleteResourceInAdmin() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		selectReourceOrCategoryTab("Categories");
		searchCategory(parentCategory_text);
		performActionsFromResourceDropDownAdmin("Delete", newResourceTitle_text); // newResourceTitle_text2
		confirmationMessage("Resource was successfully deleted.");
	}

	@Test(priority = 91504)
	public void DeleteMultipleResourcesInACategory() throws Exception {
		logInfo("inside DeleteMultipleResourcesInACategory() Test");
		navigate2ManageCategories();
		nav2AdminRL();
		selectReourceOrCategoryTab("Categories");
		searchCategory(parentCategory_text);
		deleteMultipleResources();

	}

	@Test(priority = 91504)
	public void DeleteRLCategory() throws Exception {
		logInfo("inside DeleteRLCategory() Test");
		navigate2ManageCategories();
		deleteProductCategory(parentCategory_text);
		boolean isAdded = verifyResourceCategoryPresentInCategoriesPage(parentCategory_text);
		if (isAdded = true) {
			logInfo(parentCat1 + " product category could not be deleted.");
			Assert.assertTrue(isAdded, parentCat1 + " product category could not be deleted.");
		}
	}

}
