package vibe.resourcelibrary3.tests;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import common.TestBase;
import common.EnvProperties;
import vibe.inbox.tests.InboxMethods;

public class RL3Methods extends TestBase {

	EnvProperties prop = new EnvProperties();
	InboxMethods in = new InboxMethods();
	/*String parentCategory_text = "PCAT_560";*/
	public void nav2AdminRL() throws Exception {
		logInfo("Navigate to Admin Resource Library");
		driver().navigate().to(appUrl + "pyr_core/resources");
	}

	public void navigate2ManageCategories() throws Exception {
		logInfo("inside navigate2ManageCategories() method.");
		driver().navigate().to(appUrl + "pyr_core/resource_categories");
	}

	public void navigate2UserRL() throws IOException, Exception {
		System.out.println("inside the navigate2UserRL() method..");
		logInfo("inside navigate2UserRL() method.");
		driver().navigate().to(appUrl + "crm/resource_library");
	}


	public void  nav2ResouceCategoryUser() throws Exception {
		logInfo("Navigate to nav2ResouceCategoryUser Method");
		driver().navigate().to(appUrl + "crm/resource_library/r/"+prop.getLocatorForEnvironment(appUrl,"categoryIdURl"));
	
	}
	
	public void  nav2ResouceSettings() throws Exception {
		logInfo("Navigate to SettingIcRl Method");
		driver().navigate().to(appUrl + "pyr_core/pyr_admin/resource_library_settings");
	
	}
	
	
	
	public void addResourceCategory(String parentCategory, String childCategory) throws Exception {
		logInfo("inside addResourceCategory() method.");
		waitOnElement("cssSelector", addNewCategoryBtn);
		clickOnElement("cssSelector", addNewCategoryBtn);
		waitOnElement("cssSelector", inputAddNewCategory);
		inputTextClear("cssSelector", inputAddNewCategory);
		inputText("cssSelector", inputAddNewCategory, parentCategory);
		List<WebElement> options = driver()
				.findElements(By.cssSelector("#pyr_core_resource_category_parent_category_id>option"));
		for (int i = 0; i < options.size(); i++) {
			String optionName = options.get(i).getText();
			if (optionName.equalsIgnoreCase(parentCategory)) {
				System.out.println(optionName);
				options.get(i).click();
				inputTextClear("cssSelector", inputAddNewCategory);
				inputText("cssSelector", inputAddNewCategory, childCategory);
				break;
			}
		}
		// selectFromDropdown("cssSelector", drpdnParentCategory, "byVisibleText",
		// parentCategory);
		waitOnElement("cssSelector", chkMarketAll);
		verifyElementPresent("cssSelector", chkMarketAll);
		WebElement e = driver().findElement(By.cssSelector(chkMarketAll));
		if (e.isSelected()) {
			System.out.println("checkbox is toggled on");
			e.click();
		}
		selectMarketlangsWhileCreatingACategory(languageList);
		uploadFile("Image", inputCatThumbnail);
		clickOnButton("cssSelector", btnCreateResourceCategory);
		waitOnSpinner();
	//	confirmationMessage("Resource Category created successfully.");
		
	}

	public void selectMarketlangsWhileCreatingACategory(String[] marketLanguages) throws Exception {
		logInfo("inside selectMarketlangsWhileCreatingACategory() Method");
		waitOnElement("xpath", catMarketlist);
		WebElement rankPane = driver().findElement(By.xpath(catMarketlist));
		List<WebElement> allRanks = rankPane.findElements(By.tagName("span"));
		int cntRanks = allRanks.size();
		String before_langName = "//*[@id='market_language_checkbox']/div/span[";
		String after_langName = "]/label";
		String before_langInput = "//*[@id='market_language_checkbox']/div/span[";
		String after_langInput = "]/label/input";
		int langList = marketLanguages.length;
		System.out.println("list size =" + langList);
		for (int i = 1; i <= cntRanks; i++) {
			for (int j = 0; j <= langList - 1; j++) {
				WebElement erank = driver().findElement(By.xpath(before_langName + i + after_langName));
				String rankName = erank.getText().trim();
				WebElement einput = driver().findElement(By.xpath(before_langInput + i + after_langInput));
				// String isSelected = einput.getAttribute("checked").trim();
				// System.out.println("status=" +status);
				if (rankName.equalsIgnoreCase(marketLanguages[j])) { // &&
					// isSelected.equalsIgnoreCase("false")
					einput.click();
					break;
				}
			}
		}
	}

	// This method selects resource category in AddNewResource pane.

	public void selectCategoryWhileCreatingResource(String resCategory) throws Exception {
		System.out.println("inside selectResourceCategory() method");
		waitOnElement("xpath", "//*[@id='categories_list']");
		WebElement catPanel = driver().findElement(By.xpath("//*[@id='categories_list']"));
		List<WebElement> allRows = catPanel.findElements(By.tagName("li"));
		int cntChecks = allRows.size();
		System.out.println("Total checks = " + cntChecks);
		if (cntChecks > 0) {
			waitOnSpinner();
			for (int i = 1; i <= cntChecks; i++) {
				WebElement e = driver().findElement(By.xpath("//*[@id='categories_list']/li[" + i + "]"));
				String name = e.getText().trim();
				if (name.contains(resCategory)) {
					WebElement pinput = driver()
							.findElement(By.xpath("//*[@id='categories_list']/li[" + i + "]/input"));
					scrollDown("xpath", "//*[@id='categories_list']/li[" + i + "]/input");
					pinput.click();

					List allInputs = e.findElements(By.tagName("input"));
					System.out.println("i =" + i + ", inputs = " + allInputs.size());
					if (allInputs.size() >= 2) {
						for (int j = 2; j <= allInputs.size(); j++) {
							WebElement ce = driver().findElement(By.xpath("//*[@id='categories_list']/li[" + i + "]"));
							String cname = ce.getText().trim();
							System.out.println("cname =" + cname);
							WebElement input = driver()
									.findElement(By.xpath("//*[@id='categories_list']/li[" + i + "]/ul/li/input"));
							/*
							 * scrollDown("xpath", "//*[@id='categories_list']/li["+i+ "]/ul/li/input");
							 * Thread.sleep(3000);
							 */
							input.click();
						}
					}
					break;
				}
			}
		}
	}

	public void selectRankDefs(String expirDate) throws Exception {
	logInfo("inside selectRankDefs() method");
		WebElement e = driver().findElement(By.cssSelector(rankAll));
		if (e.isSelected()) {
			System.out.println("checkbox is toggled on");
			e.click();
		}
		List<WebElement> allRanks = driver().findElements(By.xpath("//*[@id='rank_definition_checkboxes']/div"));
		int cntRanks = allRanks.size();
		System.out.println("Total checks = " + cntRanks);

		String before_rankName = "//*[@id='rank_definition_checkboxes']/div[";
		String after_rankName = "]/div[1]";

		String before_rankInput = "//*[@id='rank_definition_checkboxes']/div[";
		String after_rankInput = "]/div[1]/input[2]";

		String before_startdate = "//*[@id='rank_definition_checkboxes']/div[";
		String after_startdate = "]/div[2]/div/div[2]/span/button";

		String before_enddate = "//*[@id='rank_definition_checkboxes']/div[";
		String after_enddate = "]/div[3]/div/div[2]/span/button";

		int rankList = rankdeflist.length;
		System.out.println("list size =" + rankList);
		for (int i = 1; i <= cntRanks; i++) {
			for (int j = 0; j <= rankList - 1; j++) {
				WebElement erank = driver().findElement(By.xpath(before_rankName + i + after_rankName));
				String rankName = erank.getText().trim();
				WebElement einput = driver().findElement(By.xpath(before_rankInput + i + after_rankInput));
				if (rankName.equalsIgnoreCase(rankdeflist[j])) { 
					einput.click();
					WebElement startDate = driver().findElement(By.xpath(before_startdate + i + after_startdate));
					startDate.click();
					waitOnElement("xpath", "//a[@data-action='close']/span");
					clickOnElement("xpath", "//a[@data-action='close']/span");
					String endDatepart1 = "#pyr_core_resource_rank_definition_resources_attributes_";
					String endDatepart2 = "_end_date";
					int k = i - 1;
					String totalEnddate = endDatepart1 + k + endDatepart2;
					WebElement endDate = driver().findElement(By.cssSelector(totalEnddate));
					endDate.sendKeys(expirDate);
					WebElement endDDate = driver().findElement(By.xpath(before_enddate + i + after_enddate));
					endDDate.click();
					waitOnElement("xpath", "//a[@data-action='close']/span");
					clickOnElement("xpath", "//a[@data-action='close']/span");

					break;
				}
			}
		}
	}

	public void selectMarketlangs(String[] marketslanguages) throws Exception {
		System.out.println("inside selectMarketlangs() method");
		WebElement e = driver().findElement(By.cssSelector(subscriptionchkMarketAll));
		if (e.isSelected()) {
			System.out.println("checkbox is toggled on");
			e.click();
		}
		WebElement rankPane = driver().findElement(By.cssSelector(resMarketlistChckBoxes));
		List<WebElement> allRanks = rankPane.findElements(By.tagName("span"));
		int cntRanks = allRanks.size();
		System.out.println("Total checks = " + cntRanks);

		String before_langName = "div#market_language_checkboxes>div.form-group.check_boxes.optional.pyr_core_resource_market_languages>span:nth-child(";
		String after_langName = ")>label";
		String after_langInput = ")>label>input";

		int langList = marketslanguages.length;
		System.out.println("list size =" + langList);

		for (int i = 1; i <= cntRanks; i++) {
			for (int j = 0; j <= langList - 1; j++) {
				WebElement erank = driver().findElement(By.cssSelector(before_langName + i + after_langName));
				String rankName = erank.getText().trim();				
				WebElement einput = driver().findElement(By.cssSelector(before_langName + i + after_langInput));
				if (rankName.equalsIgnoreCase(marketslanguages[j])) { 
					scrollDown("cssSelector", before_langName + i + after_langInput);
					einput.click();
					break;
				}
			}
		}
	}

	public void selectSubscriptionPlans() throws Exception {
		System.out.println("inside selectSubscriptionPlans() method");

		WebElement e = driver().findElement(By.cssSelector(subscriptionchkAll));
		if (e.isSelected()) {
			System.out.println("checkbox is toggled on");
			e.click();
		}

		WebElement rankPane = driver().findElement(By.xpath("//*[@id='subscription_plan_checkboxes']/div"));
		List<WebElement> allRanks = rankPane.findElements(
				By.cssSelector(".form-group.check_boxes.optional.pyr_core_resource_subscription_plans>span.checkbox"));
		int cntRanks = allRanks.size();
		System.out.println("Total checks = " + cntRanks);

		String before_langName = "//*[@id='subscription_plan_checkboxes']/div/span[";
		String after_langName = "]/label";

		String before_langInput = "//*[@id='subscription_plan_checkboxes']/div/span[";
		String after_langInput = "]/label/input";

		int subList = subplanslist.length;
		System.out.println("list size =" + subList);

		for (int i = 1; i <= cntRanks; i++) {
			for (int j = 0; j <= subList - 1; j++) {
				WebElement erank = driver().findElement(By.xpath(before_langName + i + after_langName));
				String rankName = erank.getText().trim();
				WebElement einput = driver().findElement(By.xpath(before_langInput + i + after_langInput));
				if (rankName.equalsIgnoreCase(subplanslist[j])) { // && // isSelected.equalsIgnoreCase("false")
					einput.click();
					break;
				}
			}
		}
	}

	public void selectAssets(String assetType, String assetName) throws Exception {
		logInfo("inside selectAssets() method.");
		switch (assetType) {
		case "PDF":
			addAsset(assetName, "PDF");
			break;
		case "Text":
			addAsset(assetName, "Text");
			break;
		case "Document":
			addAsset(assetName, "Document");
			break;
		case "Spreadsheet":
			addAsset(assetName, "Spreadsheet");
			break;
		case "ZIP":
			addAsset(assetName, "ZIP");
			break;
		case "Presentation":
			addAsset(assetName, "Presentation");
			break;
		case "Image":
			addAsset(assetName, "Image");
			break;
		case "Video":
			addAsset(assetName, "Video");
			break;
		case "Content Block":
			addAsset(assetName, "Content Block");
			break;
		default:
			addAsset(assetName, "PDF");
			break;
		}
	}

	public void addAsset(String assetName, String assetType) throws Exception {
		logInfo("inside addAsset() method.");
		waitOnElement("partialLinkText", "Upload Assets");
		clickOnLink("partialLinkText", "Upload Assets");
		waitOnSpinner();
	//	waitOnElement("xpath",assetAdd);
		List<WebElement> boxL = driver().findElements(By.xpath(assetAdd));
		int liM = boxL.size();
		System.out.println(liM);
		for (int i = liM; i >= 1; i--) {
			String part1 = "//*[starts-with(@id,'modal-')][";
			String part2 = "]";
			String part3 = "/div/div[1]/div[1]/h1";
			String header = driver().findElement(By.xpath(part1 + i + part2 + part3)).getText().trim();
			System.out.println(header);
			if (header.equalsIgnoreCase("Add Asset")) {
				String assetWinId = driver().findElement(By.xpath(part1 + i + part2)).getAttribute("aria-labelledby");
				System.out.println(assetWinId);
				String[] part = assetWinId.split("-");
				System.out.println(part[3]);
				String assetTitle1 = "//*[@id='pyr_core_resource_resource_assets_attributes_";
				String assetTitle2 = "_title']";
				String assetDesc = "_file_description']";
				String assetfileType = "_file_type']";
				String assetfilePath = "_file_path";
				String assetEmbedCode = "_embed_code']";
				String assetFp1 = "#pyr_core_resource_resource_assets_attributes_";
				String inputAssetPath = assetFp1 + part[3] + assetfilePath;
				String up1 = "//*[@id='div_embed_code_";
				String up2 = "']/a";
				String btn1 = "//*[@id='modal-";
				String btn2 = "']/div/div[1]/div[3]/a[1]";
				waitOnElement("xpath", assetTitle1 + part[3] + assetTitle2);
				inputTextClear("xpath", assetTitle1 + part[3] + assetTitle2);
				inputText("xpath", assetTitle1 + part[3] + assetTitle2, assetName);
				selectFromDropdown("xpath", assetTitle1 + part[3] + assetfileType, "byVisibleText", assetType);
				inputText("xpath", assetTitle1 + part[3] + assetDesc, assetType + " asset description.");
				switch (assetType) {
				case "PDF":
					uploadFile("PDF", inputAssetPath);
					break;
				case "Text":
					uploadFile("Text", inputAssetPath);
					break;
				case "Document":
					uploadFile("Document", inputAssetPath);
					break;
				case "Spreadsheet":
					uploadFile("Excel", inputAssetPath);
					break;
				case "ZIP":
					uploadFile("Zip", inputAssetPath);

					break;
				case "Presentation":
					uploadFile("PPT", inputAssetPath);

					break;
				case "Image":
					uploadFile("Image", inputAssetPath);
					/*
					 * waitOnElement("cssSelector", siteTemp); selectFromDropdown("cssSelector",
					 * siteTemp, "byVisibleText", "Saba"); clickOnElement("cssSelector",
					 * allowDownload); clickOnElement("cssSelector", allowEmail);
					 * clickOnElement("cssSelector", allowShare); clickOnElement("cssSelector",
					 * allowDownload); clickOnElement("cssSelector", allowEmail);
					 * clickOnElement("cssSelector", allowShare)
					 */;
					break;
				case "Video":
					inputTextClear("xpath", assetTitle1 + part[3] + assetEmbedCode);
					inputText("xpath", assetTitle1 + part[3] + assetEmbedCode, embedVid);
					break;
				case "Content Block":
					composeText("xpath", "//iframe[contains(@title,'Rich Text Editor')]", "This is a new asset");
					break;
				default:
					System.out.println("Invalid file type.");
					break;
				}

				clickOnButton("xpath", btn1 + part[3] + btn2);
				waitOnElement("partialLinkText", "Upload Assets");
				clickOnLink("linkText", "Next");
				clickOnLink("linkText", "Previous");
				break;
			}

		}

	}

	public void editResourceCategory(String pcategory, String pCategoryUpdate) throws Exception {
		logInfo("inside editResourceCategory() Method.");
		boolean isCategoryFound = false;

	/*	List<WebElement> allPlus = driver().findElements(By.cssSelector(".ic-icon-regular.ic-icon-add"));
		for (WebElement x : allPlus) {
			x.click();
		}
	*/	WebElement tblResource = driver().findElement(By.xpath("//*[@id='index_page']/div[2]"));
		List parentDivs = tblResource.findElements(By.className("col-md-10"));
		int parent_divs = parentDivs.size();
		System.out.println("Total divs = " + parent_divs);

		String before_pname = "//*[@id='index_page']/div[2]/div[";
		String after_pname = "]/div[3]";

		String before_btn = "//*[@id='index_page']/div[2]/div[";
		String after_btn = "]/div[5]/div/button";

		String before_edit = "//*[@id='index_page']/div[2]/div[";
		String after_edit = "]/div[5]/div/ul/li[1]";

		for (int i = 2; i <= parent_divs; i++) {
			WebElement e = driver().findElement(By.xpath(before_pname + i + after_pname));
			String name = e.getText().trim();
			System.out.println("pname =" + name);
			if (pcategory.equalsIgnoreCase(name)) {
				WebElement btn = driver().findElement(By.xpath(before_btn + i + after_btn));
				btn.click();
				WebElement edit = driver().findElement(By.xpath(before_edit + i + after_edit));
				edit.click();
				verifyElementPresent("cssSelector", inputAddNewCategory);
				inputTextClear("cssSelector", inputAddNewCategory);
				inputText("cssSelector", inputAddNewCategory, pCategoryUpdate);
				clickOnButton("cssSelector", btnCreateResourceCategory);
				waitOnElement("xpath", tblManageCategories);
				break;
			}
		}
	}

	public void deleteResourceCategory(String pcategory) throws Exception {
		logInfo("inside deleteResourceCategory() method.");
		waitOnElement("xpath", productCategoryList);
		boolean isCategoryFound = false;
		WebElement tblResource = driver().findElement(By.xpath("//*[@id='index_page']/div[2]"));
		List parentDivs = tblResource.findElements(By.className("col-md-3"));
		int parent_divs = parentDivs.size();
		System.out.println("Total divs = " + parent_divs);

		String before_pname = "//*[@id='index_page']/div[2]/div[";
		String after_pname = "]/div[3]";

		String before_btn = "//*[@id='index_page']/div[2]/div[";
		String after_btn = "]/div[5]/div/button";

		String before_delete = "//*[@id='index_page']/div[2]/div[";
		String after_delete = "]/div[5]/div/ul/li[2]";

		for (int i = 2; i <= parent_divs; i++) {
			WebElement e = driver().findElement(By.xpath(before_pname + i + after_pname));
			String name = e.getText().trim();
			System.out.println("pname =" + name);
			if (pcategory.equalsIgnoreCase(name)) {
				WebElement btn = driver().findElement(By.xpath(before_btn + i + after_btn));
				btn.click();
				waitOnElement("xpath", before_delete + i + after_delete);
				WebElement delete = driver().findElement(By.xpath(before_delete + i + after_delete));
				delete.click();
				((JavascriptExecutor) driver()).executeScript("window.confirm = function(msg){return true;};");
				break;
			}
		}
	}

	public void deleteChildResourceCategories(String category, String subCategory) throws Exception {
		logInfo("Entered into deleteChildCategories() method ");
		List<WebElement> cat = driver().findElements(By.cssSelector(rescatList));
		System.out.println("size" + cat.size());
		boolean isParentPresent = false;
		String catListBfr = "#index_page > div.row.category.ui-sortable > div:nth-child(";
		String catListAfr = ") > div.col-md-3";
		String catListPlus = ") > div.col-md-1.category-toggle > a > i";
		String subCatList = ") > div.sub-category.ui-sortable > div > div.col-md-3";
		String subCatListBfr = ") > div.sub-category.ui-sortable > div:nth-child(";
		String subCatListAfr = ") > div.col-md-3";
		String catActionAfr = ") > div:nth-child(5)>div";
		String catActionDelete = " > ul > li:nth-child(2)";
		for (int i = 3; i <= cat.size(); i++) {
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr + i + catListAfr));
			String pCatgName = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)) {
				System.out.println("pCatgName " + pCatgName);
				isParentPresent = true;
				WebElement plusClick = driver().findElement(By.cssSelector(catListBfr + i + catListPlus));
				plusClick.click();
				Thread.sleep(4000);
				List<WebElement> subCat = driver().findElements(By.cssSelector(catListBfr + i + subCatList));
				System.out.println("Sub cat Size " + subCat.size());
				boolean isSubPresent = false;
				for (int j = 1; j <= subCat.size(); j++) {
					WebElement subCatNm = driver()
							.findElement(By.cssSelector(catListBfr + i + subCatListBfr + j + subCatListAfr));
					String subCatgName = subCatNm.getText().trim();
					System.out.println("subCatgName " + subCatgName);
					if (subCatgName.equalsIgnoreCase(subCategory)) {
						isSubPresent = true;
						System.out.println("Sucess!! present");
						WebElement actionClick = driver()
								.findElement(By.cssSelector(catListBfr + i + subCatListBfr + j + catActionAfr));
						actionClick.click();
						Thread.sleep(3000);
						WebElement delete = driver().findElement(
								By.cssSelector(catListBfr + i + subCatListBfr + j + catActionAfr + catActionDelete));
						delete.click();

						confirmToOk();
						waitOnSpinner();

					//	confirmationMessage("Resource Category deleted successfully.");
						break;
					}
				}
				if (isSubPresent == false) {
					Assert.assertTrue(isSubPresent, subCategory + " Subcategory is not present under " + category);
				}
				break;
			}
		}
		if (isParentPresent == false) {
			Assert.assertTrue(isParentPresent, category + " Parent Category is not present.");
		}

	}

	public boolean verifyResourcePreviewHeading(String resourceTitle) throws Exception {
		logInfo("inside verifyResourcePreviewHeading() method.");
		waitOnElement("cssSelector", resourcePreviewHeader);
		WebElement header = driver().findElement(By.cssSelector(resourcePreviewHeader));
		String headerName = header.getText().trim();
		System.out.println("name is " + headerName);
		boolean isHeaderCorrect = false;
		if (headerName.equalsIgnoreCase(resourceTitle)) {
			logInfo(resourceTitle + " matches the title in resource preview page.");
			isHeaderCorrect = true;
			
		}
		return isHeaderCorrect;
	}
	
		public void selectYoutubeVideo(String title) throws Exception {
		logInfo("Inside selectYoutubeVideo() method...");
		waitOnElement("xpath", listYoutubeVideos);
		List<WebElement> rows = driver().findElements(By.xpath(listYoutubeVideos));
		for (int i = 2; i <= rows.size() + 1; i++) {
			String before = "//*[@id='youtube-videos-create-modal']/div/div[1]/div[2]/table/tbody/tr[";
			String after = "]/td[2]";
			String selectVideo = "]/td[1]";
			WebElement el = driver().findElement(By.xpath(before + i + after));
			if (el.getText().trim().equalsIgnoreCase(title.trim())) {
				WebElement video = driver().findElement(By.xpath(before + i + selectVideo));
				video.click();
				break;
			}
		}
	}

	public boolean verifyResourceCategoryPresentInCategoriesPage(String prodCategory) throws Exception {
		System.out.println("inside verifyResourceCategoryPresentInCategoriesPage() method.");
		logInfo("inside verifyResourceCategoryPresent() method.");
		boolean isCategoryPresent = false;
		waitOnElement("cssSelector", ".ic-icon-regular.ic-icon-add");
		List<WebElement> allPlus = driver().findElements(By.cssSelector(".ic-icon-regular.ic-icon-add"));
		for (WebElement x : allPlus) {
			scrollDown("cssSelector", ".ic-icon-regular.ic-icon-add");
			waitOnElement("cssSelector", ".ic-icon-regular.ic-icon-add");
			x.click();
		}
		System.out.println("No of plus in the product table = " + allPlus.size());
		List<WebElement> allRows = driver().findElements(By.cssSelector(".col-md-5"));
		System.out.println("No of rows in the product table = " + allRows.size());

		for (WebElement x : allRows) {
			String prodName = x.getText().trim();
			if (prodName.equalsIgnoreCase(prodCategory)) {
				System.out.println("product found " + prodName);
				isCategoryPresent = true;
				x.click();
				break;
			}
		}

		clickOnLink("linkText", "Back");
		
		return isCategoryPresent;
	}

	public void selectReourceOrCategoryTab(String btnName) throws  Exception {
		logInfo("inside selectReourceOrCategoryTab() Method.");
		switch (btnName) {
		case "Resources":
			waitOnElement("cssSelector", selectResTab);
			clickOnElement("cssSelector", selectResTab);
			waitOnElement("cssSelector", headingPanelTit);
			Thread.sleep(12000);
			String actualTxt = driver().findElement(By.cssSelector(headingPanelTit)).getText().trim();
			Assert.assertEquals(actualTxt, "Resources");
			break;
		case "Categories":
			waitOnElement("cssSelector", selectResTab);
			clickOnElement("cssSelector", selectCatTab);
			//waitOnElement("cssSelector", headingPanelTit);
			Thread.sleep(12000);
			String actualTxt1 = driver().findElement(By.cssSelector(headingPanelTit)).getText().trim();
			Assert.assertEquals(actualTxt1, "Categories");
			break;
		default:
			logInfo("unable to click");
			break;
		}

	}

	
	public boolean verifyResourceCategoryAdminListView(String categoryName) throws  Exception {
		logInfo("inside verifyResourceCategoryAdminListView() method to verify the resource.");
		boolean isCategoryFound = false;
		waitOnElement("cssSelector", categoriesListP);
		WebElement e = driver().findElement(By.cssSelector(categoriesListP));
		List<WebElement> cats = e.findElements(By.cssSelector(categoriestot));
		int all_lis = cats.size();
		System.out.println("Total Lis =" + all_lis);
		String part1 = "div.col-md-2.col-sm-2.col-xs-6:nth-child(";
		String name = ")>div>div.main-container-info";
		String exp = ")>div>div.thumb-container";
		String view = ">div.view-file>div.label>a";
		for (int i = 1; i <= all_lis; i++) {
			WebElement liName = driver().findElement(By.cssSelector(part1 + i + name));
			String itemName = liName.getText().trim();
			if (itemName.contains(categoryName)) {
				isCategoryFound = true;
				System.out.println("Category Name =" + itemName);
				break;
			}
		}
		if (isCategoryFound == false) {
			logInfo(categoryName + " Unable to find the category to click.");
			Assert.assertTrue(isCategoryFound, categoryName + " Unable to find the category to click");
		}
		return isCategoryFound;
	}
	
	public boolean verifyResourceCategoryAdminGridView(String categoryName) throws  Exception {
		logInfo("inside verifyResourceCategoryAdminGridView() method to verify the resource.");
		boolean isCategoryFound = false;
		waitOnElement("xpath", categoriesListPListView);
		WebElement e = driver().findElement(By.xpath(categoriesListPListView));
		List<WebElement> cats = e.findElements(By.cssSelector(categoriestotListView));
		int all_lis = cats.size();
		System.out.println("Total Lis =" + all_lis);
		String part1 = "//*[@id='main-content']/div/div[2]/div[1]/div[2]/div[3]/div[";
		String name = "]/div/div[2]/div/a";
		for (int i = 1; i <= all_lis; i++) {
			WebElement liName = driver().findElement(By.xpath(part1 + i + name));
			String itemName = liName.getText().trim();
			if (itemName.equalsIgnoreCase(categoryName)) {
				isCategoryFound = true;
				System.out.println("Category Name =" + itemName);
				break;
			}
		}
		if (isCategoryFound == false) {
			logInfo(categoryName + " Unable to find the category to click.");
			Assert.assertTrue(isCategoryFound, categoryName + " Unable to find the category ");
		}
		return isCategoryFound;

	}

	public boolean verifyResourceCategoryAdmin(String categoryName) throws  Exception {
		logInfo("inside verifyResourceCategoryAdmin() method to verify the resource.");
		boolean isCategoryFound = false;
		String expTit = prop.getLocatorForEnvironment(appUrl,"resourceLayout");
		System.out.println(expTit);
		if (expTit.equals("list")) {
			System.out.println("Current view is list View");
			verifyResourceCategoryAdminListView(categoryName);
		} else {

			System.out.println("Current view is grid View");
			verifyResourceCategoryAdminGridView(categoryName);
		}
		return isCategoryFound;
	}

	
	
	public boolean selectResourceCategoryAdminListView(String categoryName) throws  Exception {
		logInfo("inside selectResourceCategoryAdminListView() method to select the resource.");
		boolean isCategoryFound = false;
		waitOnElement("xpath", categoriesListPListView);
		WebElement e = driver().findElement(By.xpath(categoriesListPListView));
		List<WebElement> cats = e.findElements(By.cssSelector(categoriestotListView));
		int all_lis = cats.size();
		System.out.println("Total Lis =" + all_lis);
		String part1 = "//*[@id='main-content']/div/div[2]/div[1]/div[2]/div[3]/div[";
		String name = "]/div/div[2]/div/a";
		for (int i = 1; i <= all_lis; i++) {
			WebElement liName = driver().findElement(By.xpath(part1 + i + name));
			String itemName = liName.getText().trim();
			if (itemName.equalsIgnoreCase(categoryName)) {
				System.out.println("Category Name =" + itemName);
				scrollDown("xpath", part1 + i + name);
				clickOnElement("xpath", part1 + i + name);
				waitOnSpinner();
				isCategoryFound = true;
				break;
			}
		}
	if (isCategoryFound == false) {
			logInfo(categoryName + " Unable to find the category to click.");
			Assert.assertTrue(isCategoryFound, categoryName + " Unable to find the category to click");
		}
		return isCategoryFound;
	}
	
	public boolean selectResourceCategoryAdminGridView(String categoryName) throws  Exception {
		logInfo("inside selectResourceCategoryAdminGridView() method to verify the resource.");
		boolean isCategoryFound = false;
		waitOnElement("cssSelector", categoriesListP);
		WebElement e = driver().findElement(By.cssSelector(categoriesListP));
		List<WebElement> cats = e.findElements(By.cssSelector(categoriestot));
		int all_lis = cats.size();
		System.out.println("Total Lis =" + all_lis);
		String part1 = "div.col-md-2.col-sm-2.col-xs-6:nth-child(";
		String name = ")>div>div.main-container-info";
		String exp = ")>div>div.thumb-container";
		String view = ">div.view-file>div.label>a";
		for (int i = 1; i <= all_lis; i++) {
			WebElement liName = driver().findElement(By.cssSelector(part1 + i + name));
			String itemName = liName.getText().trim();
			if (itemName.contains(categoryName)) {
				System.out.println("Category Name =" + itemName);
				clickOnElement("cssSelector", part1 + i + exp);
				clickOnElement("cssSelector", part1 + i + exp + view);
				waitOnSpinner();
				isCategoryFound = true;
				break;
			}
		}		if (isCategoryFound == false) {
			logInfo(categoryName + " Unable to find the category to click.");
			Assert.assertTrue(isCategoryFound, categoryName + " Unable to find the category ");
		}
		return isCategoryFound;

	}

	
	
	
	public boolean selectResourceCategoryAdmin(String categoryName) throws  Exception {
		logInfo("inside selectResourceCategory() method to view the resource.");
		String expTit = prop.getLocatorForEnvironment(appUrl,"resourceLayout");
		boolean 	isCategoryFound = false;
		if (expTit.equals("list")) {
			selectResourceCategoryAdminListView(categoryName);
		} else {
			selectResourceCategoryAdminGridView(categoryName);
			
		}
		return isCategoryFound;
	}

	
	public boolean selectResourcePresentAdmin(String resourceName) throws  Exception {
		logInfo("inside selectResourcePresentAdmin() method to select the resource.");
		String expTit = prop.getLocatorForEnvironment(appUrl,"resourceLayout");
		System.out.println(expTit);
		boolean isResourcePresent = false;
		if (expTit.equals("list")) {
			selectResourcePresentAdminListView(resourceName);
			}
		 else {
			selectResourcePresentAdminGridView(resourceName);
		}
		return isResourcePresent;
}




	public boolean selectResourcePresentAdminListView(String resourceName) throws  Exception {
		logInfo("inside selectResourcePresentAdminListView() method to select the resource.");
		boolean isResourcePresent = false;
		waitOnElement("cssSelector", resourcesTabAdmin);
		WebElement row = driver().findElement(By.cssSelector(resourcesTabAdmin));
		List<WebElement> rows = row.findElements(By.cssSelector(categoriestotListView));
		int rowsCnt = rows.size();
		System.out.println(rowsCnt);
		for (int i = 2; i <= rowsCnt+1; i++) {
			WebElement rest = driver().findElement(By.cssSelector(resPagepart1 + i + resPagename));
			String res = rest.getText().trim();
			if (res.equalsIgnoreCase(resourceName)) {
				System.out.println(res);
				waitOnElement("cssSelector", resPagepart1 + i + resPagename);
				clickOnElement("cssSelector", resPagepart1 + i + resPagename);
				isResourcePresent=true;
				break;
			}
		}
		return isResourcePresent;
	}
	
	
	
	public boolean selectResourcePresentAdminGridView(String resourceName) throws  Exception {
		logInfo("inside selectResourcePresentAdminGridView() method to select the resource.");
		boolean isResourcePresent = false;
		waitOnElement("cssSelector", resourcesTabAdmin);
		WebElement row = driver().findElement(By.cssSelector(resourcesTabAdmin));
		List<WebElement> rows = row.findElements(By.cssSelector(resourcesListGridView));
		int rowsCnt = rows.size();
		System.out.println("resources to view are" + rowsCnt);
		String part1 = "div.clearfix>div>div.thumb-item:nth-child(";
		String name = ")>div>div:nth-child(4)>span:nth-child(1)";
		String part2 = ")>div>div:nth-child(3)";
		String viewFile = ">div.view-file>div>a";
		for (int i = 1; i <= rowsCnt; i++) {
			String res = driver().findElement(By.cssSelector(part1 + i + name)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(resourceName)) {
				clickOnElement("cssSelector", part1 + i + part2);
				clickOnElement("xpath", part1 + i + part2 + viewFile);
				isResourcePresent=true;
				break;
			}
	}
		return isResourcePresent;

	}
	

	
	public boolean verifyResourcePresentAdmin(String resourceName) throws  Exception {
		logInfo("inside verifyResourcePresentAdmin() method to verify the resource.");
		boolean isResourcePresent = false;
		String expTit = prop.getLocatorForEnvironment(appUrl,"resourceLayout");
		System.out.println(expTit);
		if (expTit.equals("list")) {
			waitOnElement("cssSelector", resourcesTabAdmin);
			WebElement row = driver().findElement(By.cssSelector(resourcesTabAdmin));
			List<WebElement> rows = row.findElements(By.cssSelector(categoriestotListView));
			int rowsCnt = rows.size();
			System.out.println(rowsCnt);
			for (int i = 2; i <= rowsCnt+1; i++) {
				WebElement rest = driver().findElement(By.cssSelector(resPagepart1 + i + resPagename));
				String res = rest.getText().trim();
				if (res.equalsIgnoreCase(resourceName)) {
					System.out.println(res);
					waitOnElement("cssSelector", resPagepart1 + i + resPagename);
					clickOnElement("cssSelector", resPagepart1 + i + resPagename);
					isResourcePresent=true;
					break;
				}
			}
			if (!isResourcePresent) {
				logInfo(resourceName + " Resource is not found ");
				Assert.assertTrue(isResourcePresent, resourceName + " Resource is not present");
			}
		
		} else {
			waitOnElement("cssSelector", resourcesTabAdmin);
			WebElement row = driver().findElement(By.cssSelector(resourcesTabAdmin));
			List<WebElement> rows = row.findElements(By.cssSelector(resourcesListGridView));
			int rowsCnt = rows.size();
			String part1 = "//*[@id='main-content']/div[1]/div/div[2]/div[1]/div[2]/div[3]/div[1]/div[";
			String name = "]/div/div[4]/span[1]";
			for (int i = 1; i <= rowsCnt; i++) {
				String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
				System.out.println(res);
				if (res.equalsIgnoreCase(resourceName)) {
					isResourcePresent = true;
					break;
				}
			}

			if (!isResourcePresent) {
				logInfo(resourceName + " Resource is not found ");
				Assert.assertTrue(isResourcePresent, resourceName + " Resource is not present");
			}	
		}
		return isResourcePresent;
	}

	
	
	
	
	public void addNewResource(String resName, String pCategory,String tagName,boolean resPublish, boolean isResNewest,
			String fileType, String assetName, String status,int futueDate) throws Exception {
		logInfo("inside addNewResource() method.");
		String futDate = getDate(futueDate, "MM/dd/yyyy");
		waitOnElement("cssSelector", addResourceBtn);
		clickOnLink("cssSelector", addResourceBtn);
		inputTextClear("cssSelector", inputNewResourceTitle);
		inputText("cssSelector", inputNewResourceTitle, resName);
		uploadFile("Image", inputBrowseThumbnail);
		inputTextClear("xpath", inputResourceTags);
		inputText("xpath", inputResourceTags, tagName);
		selectAssets(fileType, assetName);
		clickOnLink("linkText", "Next");
		selectCategoryWhileCreatingResource(pCategory);
		selectSubscriptionPlans();
		selectMarketlangs(languageList);
		selectRankDefs(futDate);
		selectFromDropdown("cssSelector", drpdnNewResourceStatus, "byVisibleText", status);
		if (resPublish == true) {
			waitOnElement("cssSelector", publishImmed);
			scrollDown("cssSelector", publishImmed);
			WebElement epub = driver().findElement(By.cssSelector(publishImmed));
			epub.click();
			inputTextClear("cssSelector", inputExpiryDate);
			inputText("cssSelector", inputExpiryDate, futDate);
			clickOnElement("cssSelector",closeDatePicker);
			clickOnElement("cssSelector",closeDatePicker);
		}

		if (isResNewest == true) {
			WebElement eresnew = driver().findElement(By.xpath(chkNewResourceDisplayAsNewest));
			boolean isresNewchked = eresnew.isSelected();
			selectRadioOrCheckbox("xpath", chkNewResourceDisplayAsNewest);
			inputTextClear("xpath", inputDisplayAsNewest);
			inputText("xpath", inputDisplayAsNewest, futDate);
			clickOnElement("cssSelector",closeDatePicker);
			clickOnElement("cssSelector",closeDatePicker);

		}
		waitOnElement("cssSelector", btnCreateNewResource);
		scrollDown("cssSelector", btnCreateNewResource);
		clickOnButton("cssSelector", btnCreateNewResource);
		waitOnSpinner();
	//	confirmationMessage("Resource is created");

	}

	public void addNewCAResource(String resName, String pCategory, boolean resPublish, boolean isResNewest,String fileType, String assetName, String status,int futueDate) throws Exception {
		logInfo("inside addNewResource() method.");
		String futDate = getDate(futueDate, "MM/dd/yyyy");
		waitOnElement("cssSelector", addResourceBtn);
		clickOnLink("cssSelector", addResourceBtn);
		inputTextClear("cssSelector", inputNewResourceTitle);
		inputText("cssSelector", inputNewResourceTitle, resName);
		uploadFile("Image", inputBrowseThumbnail);
		inputTextClear("xpath", inputResourceTags);
		inputText("xpath", inputResourceTags, resourceTagName);
		selectAssets(fileType, assetName);
		clickOnLink("linkText", "Next");
		selectCategoryWhileCreatingResource(pCategory);
		selectSubscriptionPlans();
		selectMarketlangs(languageList3);
		selectRankDefs(futDate);
		selectFromDropdown("cssSelector", drpdnNewResourceStatus, "byVisibleText", status);
		if (resPublish == true) {
			waitOnElement("cssSelector", publishImmed);
			scrollDown("cssSelector", publishImmed);
			WebElement epub = driver().findElement(By.cssSelector(publishImmed));
			epub.click();
			inputTextClear("cssSelector", inputExpiryDate);
			inputText("cssSelector", inputExpiryDate, futDate);
			clickOnElement("cssSelector",closeDatePicker);
			clickOnElement("cssSelector",closeDatePicker);
		}

		if (isResNewest == true) {
			WebElement eresnew = driver().findElement(By.xpath(chkNewResourceDisplayAsNewest));
			boolean isresNewchked = eresnew.isSelected();
			selectRadioOrCheckbox("xpath", chkNewResourceDisplayAsNewest);
			inputTextClear("xpath", inputDisplayAsNewest);
			inputText("xpath", inputDisplayAsNewest, futDate);

		}
		waitOnElement("cssSelector",drpdnNewResourceStatus);
		selectFromDropdown("cssSelector", drpdnNewResourceStatus, "byVisibleText", "Active");
		waitOnElement("cssSelector", btnCreateNewResource);
		scrollDown("cssSelector", btnCreateNewResource);
		clickOnButton("cssSelector", btnCreateNewResource);
		waitOnSpinner();
		Thread.sleep(5000);
	}

	public boolean verifyAResourceCategoryUserSide(String categoryName) throws  Exception {
		logInfo("inside verifyAResourceCategoryUserSide() method to verify the resource.");
		boolean isCategoryFound=false;
		String expTit = prop.getLocatorForEnvironment(appUrl,"resourceLayout");
		if (expTit.equals("list")) {	
			isCategoryFound=verifyAResourceCategoryUserSideListView(categoryName);
		} else {
			isCategoryFound=verifyAResourceCategoryUserSideGridView(categoryName);
	}
		return isCategoryFound;
	}
	
	
	public boolean verifyAResourceCategoryUserSideGridView(String categoryName) throws  Exception {
		logInfo("inside verifyAResourceCategoryUserSideGridView() method to verify the resource.");
		boolean isCategoryFound = false;
		waitOnElement("cssSelector", categoriesListPUser);
		WebElement e = driver().findElement(By.cssSelector(categoriesListPUser));
		List<WebElement> cats = e.findElements(By.cssSelector(resourcesListGridView));
		int all_lis = cats.size();
		System.out.println("Total Lis =" + all_lis);
		String part1 = "div.col-md-3.col-sm-4.col-xs-6:nth-child(";
		String name = ")>div>div.main-container-info>div.category-name";
		for (int i = 1; i <= all_lis; i++) {
			String liName = driver().findElement(By.cssSelector(part1 + i + name)).getAttribute("title");
			System.out.println(liName);
			if (liName.equalsIgnoreCase(categoryName)) {
				System.out.println("Category Name =" + liName);
				isCategoryFound = true;
				break;
			}
		}
		
		return isCategoryFound;

		
	}
	
	public boolean verifyAResourceCategoryUserSideListView(String categoryName) throws  Exception {
		logInfo("inside verifyAResourceCategoryUserSideListView() method to verify the resource.");
		boolean isCategoryFound = false;
		waitOnElement("xpath", categoriesListPListView);
		WebElement e = driver().findElement(By.xpath(categoriesListPListViewUser));
		List<WebElement> cats = e.findElements(By.cssSelector(categoriestotListView));
		int all_lis = cats.size();
		System.out.println("Total Lis =" + all_lis);
		String part1 = "//*[@id='main-content']/div/div[2]/div[3]/div[";
		String name = "]/div/div[2]/div/a";
		for (int i = 1; i <= all_lis; i++) {
			WebElement liName = driver().findElement(By.xpath(part1 + i + name));
			String itemName = liName.getText().trim();
			if (itemName.equalsIgnoreCase(categoryName)) {
				isCategoryFound = true;
				System.out.println("Category Name =" + itemName);
				break;
			}
		}
	
		return isCategoryFound;
		
}	


	public void selectAResourceCategoryUserSide(String categoryName) throws  Exception {
		logInfo("inside selectAResourceCategoryUserSide() method to view the resource.");
		boolean isCategoryClicked = false;
		String expTit = prop.getLocatorForEnvironment(appUrl,"resourceLayout");
		if (expTit.equals("list")) {
			selectAResourceCategoryUserSideListView(categoryName);
		}
		else {
			selectAResourceCategoryUserSideGridView(categoryName);
		}
	}

	public void selectAResourceCategoryUserSideGridView(String categoryName) throws  Exception {
		logInfo("inside selectAResourceCategoryUserSideGridView() method to view the resource.");
		boolean isCategoryClicked = false;
		waitOnElement("cssSelector", categoriesListPUser);
		WebElement e = driver().findElement(By.cssSelector(categoriesListPUser));
		List<WebElement> cats = e.findElements(By.cssSelector(resourcesListGridView));
		int all_lis = cats.size();
		System.out.println("Total Lis =" + all_lis);
		String part1 = "div.col-md-3.col-sm-4.col-xs-6:nth-child(";
		String name = ")>div>div.main-container-info";
		String exp = ")>div>div.thumb-container";
		String view = ">div.view-file>div>a";
		for (int i = 1; i <= all_lis; i++) {
			WebElement liName = driver().findElement(By.cssSelector(part1 + i + name));
			String itemName = liName.getText().trim();
			if (itemName.contains(categoryName)) {
				System.out.println("Category Name =" + itemName);
			/*	WebElement sa1 = driver().findElement(By.cssSelector(part1 + i + exp));
				sa1.click();*/
				WebElement sa2 = driver().findElement(By.cssSelector(part1 + i + exp + view));
				sa2.click();
				
				waitOnSpinner();
				isCategoryClicked = true;
				break;
			}
		}
	}
	
	public void selectAResourceCategoryUserSideListView(String categoryName) throws  Exception {
		logInfo("inside selectAResourceCategoryUserSideListView() method to view the resource.");
		boolean isCategoryClicked = false;
		waitOnElement("xpath", categoriesListPListView);
		WebElement e = driver().findElement(By.xpath(categoriesListPListViewUser));
		List<WebElement> cats = e.findElements(By.cssSelector(categoriestotListView));
		int all_lis = cats.size();
		System.out.println("Total Lis =" + all_lis);
		String part1 = "//*[@id='main-content']/div/div[2]/div[3]/div[";
		String name = "]/div/div[2]/div/a";
		for (int i = 1; i <= all_lis; i++) {
			WebElement liName = driver().findElement(By.xpath(part1 + i + name));
			String itemName = liName.getText().trim();
			if (itemName.equalsIgnoreCase(categoryName)) {
				System.out.println("Category Name =" + itemName);
				scrollDown("xpath", part1 + i + name);
				clickOnElement("xpath", part1 + i + name);
				waitOnSpinner();
				isCategoryClicked = true;
				break;
			}
		}
		
	}
	
	public boolean verifyResourcePresentUserSide(String resourceName) throws  Exception {
		logInfo("inside verifyResourcePresentUserSide() method to verify the resource.");
		boolean isResourcePresent = false;
		String expTit = prop.getLocatorForEnvironment(appUrl,"resourceLayout");
		if (expTit.equals("list")) {
			System.out.println("Current view is Grid View");
			waitOnElement("xpath", resourcesLisUser);
			WebElement row = driver().findElement(By.xpath(resourcesLisUser));
			List<WebElement> rows = row.findElements(By.cssSelector(categoriestotListView));
			int rowsCnt = rows.size();
			String part1 = "//*[@id='main-content']/div/div/div[2]/div[3]/div[";
			String name = "]/div/div[2]/div/a";
			for (int i = 2; i <= rowsCnt; i++) {
				String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
				System.out.println(res);
				if (res.contains(resourceName)) {
					isResourcePresent = true;
					break;
				}
			}
			if (!isResourcePresent) {
				logInfo(resourceName + " Unable to find the The resource");
				Assert.assertTrue(isResourcePresent, resourceName + " Unable to find the resource");
			}
		} else {
			waitOnElement("cssSelector", resourcesUserTab);
			WebElement row = driver().findElement(By.cssSelector(resourcesUserTab));
			List<WebElement> rows = row.findElements(By.cssSelector(resourcesListGridView));
			int rowsCnt = rows.size();
			String part1 = "div.panel-body>div.clearfix>div>div:nth-child(";
			String name = ")>div>div.main-container-info>span";
			for (int i = 1; i <= rowsCnt; i++) {
				String res = driver().findElement(By.cssSelector(part1 + i + name)).getText().trim();
				System.out.println(res);
				if (res.contains(resourceName)) {
					isResourcePresent = true;
					break;
				}
				
			}
			if (!isResourcePresent) {
				logInfo(resourceName + " Unable to find the The resource to click.");
				Assert.assertTrue(isResourcePresent, resourceName + " Unable to find the resource to click");
			}
			
		}
		return isResourcePresent;
	}

	


	public void selectAResourceUserSide(String resourceName) throws  Exception {
		logInfo("inside selectResourcePresentUserSide() method to select the resource.");
		String expTit = prop.getLocatorForEnvironment(appUrl, "resourceLayout");
		if (expTit.equals("list")) {
			System.out.println("Current view is Grid View");
			boolean isresourceClicked = false;
			waitOnElement("xpath", resourcesTabAdmin);
			WebElement row = driver().findElement(By.xpath(resourcesLisUser));
			List<WebElement> rows = row.findElements(By.cssSelector(categoriestotListView));
			int rowsCnt = rows.size();
			String part1 = "//*[@id='main-content']/div/div/div[2]/div[3]/div[";
			String name = "]/div/div[2]/div/a";
			for (int i = 2; i <= rowsCnt; i++) {
				String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
				System.out.println(res);
				if (res.equalsIgnoreCase(resourceName)) {
					clickOnElement("xpath", part1 + i + name);
					isresourceClicked = true;
					break;
				}
			}
		
		} else {
			System.out.println("Current view is List View");
			boolean isresourceClicked = false;
			waitOnElement("cssSelector", resourcesUserTab);
			WebElement row = driver().findElement(By.cssSelector(resourcesUserTab));
			List<WebElement> rows = row.findElements(By.cssSelector(resourcesListGridView));
			int rowsCnt = rows.size();
			System.out.println("resources to view are" + rowsCnt);
			String part1 = "div.panel-body>div.clearfix>div>div:nth-child(";
			String name = ")>div>div.main-container-info>span";
			String part2 = ")>div>div.thumb-container";
			String viewFile = ">div.view-file>div>a";
			for (int i = 1; i <= rowsCnt; i++) {
				String res = driver().findElement(By.cssSelector(part1 + i + name)).getText().trim();
				System.out.println(res);
				if (res.equalsIgnoreCase(resourceName)) {
					waitOnElement("cssSelector", part1 + i + part2);
					clickOnElement("cssSelector", part1 + i + part2);
					clickOnElement("cssSelector", part1 + i + part2 + viewFile);
					waitOnSpinner();
					isresourceClicked = true;
					break;
				}

			}

		}
	}

	


	public boolean verifyAResourceAssetUserSide(String assetName) throws  Exception {
		logInfo("inside verifyAResourceAssetUserSide() method to select the resource.");
		boolean isAssetFound = false;
		waitOnElement("cssSelector", assetsContainer);
		WebElement row = driver().findElement(By.cssSelector(assetsContainer));
		List<WebElement> rows = row.findElements(By.cssSelector(assetsTot));
		int rowsCnt = rows.size();
		System.out.println("assets to view are" + rowsCnt);
		String part1 = "//*[@id='main-content']/div/div[2]/div[3]/div[";
		String name = "]/div/div[3]/p[1]/a";
		for (int i = 2; i <= rowsCnt + 1; i++) {
			String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(assetName)) {
				isAssetFound = true;
				break;
			}
		}
		
		if (!isAssetFound) {
			logInfo(assetName + " Unable to find the The asset");
			Assert.assertTrue(isAssetFound, assetName + " Unable to find the asset");
		}
			return isAssetFound;
	}

	public boolean  selectAResourceAssetUserSide(String assetName) throws  Exception {
		logInfo("inside selectAResourceAssetUserSide() method to select the resource.");
		boolean isAssetFound = false;
		waitOnElement("cssSelector", resourcesUserTab);
		WebElement row = driver().findElement(By.cssSelector(assetsContainer));
		List<WebElement> rows = row.findElements(By.cssSelector(assetsTot));
		int rowsCnt = rows.size();
		System.out.println("assets to view are" + rowsCnt);
		String part1 = "//*[@id='main-content']/div/div[2]/div[3]/div[";
		String name = "]/div/div[3]/p[1]/a";
		for (int i = 2; i <= rowsCnt + 1; i++) {
			String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(assetName)) {
				clickOnElement("xpath", part1 + i + name);
				isAssetFound = true;
				break;
			}
		}
		if (isAssetFound == false) {
			logInfo(assetName + " Unable to find the The asset to click.");
			Assert.assertTrue(isAssetFound, assetName + " Unable to find the asset to click");
		}
		return isAssetFound;
	}
	
	public boolean verifyAResourceAssetAdminSide(String assetName) throws  Exception {
		logInfo("inside verifyAResourceAssetAdminSide() method to verify the resource.");
		boolean isAssetFound = false;
		waitOnElement("cssSelector", resourcesUserTab);
		waitOnElement("cssSelector", assetsContainer);
		WebElement row = driver().findElement(By.cssSelector(assetsContainer));
		List<WebElement> rows = row.findElements(By.cssSelector(assetsTot));
		int rowsCnt = rows.size();
		System.out.println("assets to view are" + rowsCnt);
		for (int i = 2; i <= rowsCnt + 1; i++) {
			String part1 = "//*[@id='main-content']/div/div[2]/div/div[2]/div[3]/div[";
			String name = "]/div/div[2]/p[1]/a";
			String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(assetName)) {
				isAssetFound = true;
				break;

			}
		}
		return isAssetFound;
	
	}


	public void selectAResourceAssetAdminSide(String assetName) throws  Exception {
		logInfo("inside selectAResourceAssetAdminSide() method to select the resource.");
		boolean isAssetFound = false;
		waitOnElement("cssSelector", assetsContainer);
		WebElement row = driver().findElement(By.cssSelector(assetsContainer));
		List<WebElement> rows = row.findElements(By.cssSelector(assetsTot));
		int rowsCnt = rows.size();
		System.out.println("assets to view are" + rowsCnt);
		for (int i = 2; i <= rowsCnt + 1; i++) {
			String part1 = "//*[@id='main-content']/div/div[2]/div/div[2]/div[3]/div[";
			String name = "]/div/div[2]/p[1]/a";
			String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(assetName)) {
				clickOnElement("xpath", part1 + i + name);
				waitOnSpinner();
				isAssetFound = true;
				break;

			}
		}
	
	}

	public void previewAssetFromOptionsUserSide(String assetName) throws  Exception {
		logInfo("inside previewAssetFromOptionsUserSide() method to Preview the resource.");
		boolean isAssetFound = false;
		waitOnElement("cssSelector", resourcesUserTab);
		WebElement row = driver().findElement(By.cssSelector(assetsContainer));
		List<WebElement> rows = row.findElements(By.cssSelector(assetsTot));
		int rowsCnt = rows.size();
		System.out.println("assets to view are" + rowsCnt);
		String part1 = "//*[@id='main-content']/div/div[2]/div[3]/div[";
		String name = "]/div/div[3]/p[1]/a";
		String moreOptions = "]/div/div[6]/div/div/button";
		String preview = "]/div/div[6]/div/div/ul/li[1]/a";
		for (int i = 2; i <= rowsCnt + 1; i++) {
			String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(assetName)) {
				clickOnElement("xpath", part1 + i + moreOptions);
				clickOnElement("xpath", part1 + i + preview);
				waitOnSpinner();
				isAssetFound = true;
				break;
			}
		}
		if (isAssetFound == false) {
			logInfo(assetName + " Unable to find the The asset to click.");
			Assert.assertTrue(isAssetFound, assetName + " Unable to find the asset to click");
		}
	}

	

	public void editResourceTitle(String resName) throws Exception{
		logInfo("inside editResourceTitle() method.");
		inputTextClear("cssSelector",inputNewResourceTitle);
		inputText("cssSelector",inputNewResourceTitle,resName);
		clickOnLink("linkText","Next");
		clickOnButton("cssSelector",btnCreateNewResource);
	}
	

	public void editResourceTitle_User(String resName) throws Exception{
		logInfo("inside editResourceTitle() method.");
		inputTextClear("cssSelector",inputNewResByUserTitle);
		inputText("cssSelector",inputNewResByUserTitle,resName);
		clickOnButton("cssSelector",userRLCreate);
	}
	
public boolean downloadAssetFromOptions(String assetName) throws  Exception {
		logInfo("inside downloadAssetFromOptions() method to download the resource.");
		boolean isDownloadLinkFound = false;
		waitOnElement("cssSelector", resourcesUserTab);
		WebElement row = driver().findElement(By.cssSelector(assetsContainer));
		List<WebElement> rows = row.findElements(By.cssSelector(assetsTot));
		int rowsCnt = rows.size();
		System.out.println("assets to view are" + rowsCnt);
		String part1 = "//*[@id='main-content']/div/div[2]/div[3]/div[";
		String name = "]/div/div[3]/p[1]/a";
		String moreOptions = "]/div/div[6]/div/div/button";
		String download = "]/div/div[6]/div/div/ul/li[2]/a";
		for (int i = 2; i <= rowsCnt + 1; i++) {
			String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(assetName)) {
				clickOnElement("xpath", part1 + i + moreOptions);
				clickOnElement("xpath", part1 + i + download);
				break;
			}
		}

		if (verifyFileExistsOnDisk(filepath + "icentris_pdf.pdf")) {
			isDownloadLinkFound = true;
			logInfo(filepath + "icentris_pdf.pdf" + " file exists.");
			deleteFileFromLocal("icentris_pdf.pdf");
		} else {
			Assert.assertTrue(isDownloadLinkFound, "couldn't download the file");
			logInfo(filepath + "icentris_pdf.pdf" + " file doesn't exists.");
		}
		return isDownloadLinkFound;

	}

	public void addNewResourceWithMultipleAssets(String resName, String pCategory, boolean resPublish,
			boolean isResNewest, String status,int futueDate) throws Exception {
		logInfo("inside addNewResource() method.");
		System.out.println("inside addNewResource() method.");
		String futDate = getDate(futueDate, "MM/dd/yyyy");
		waitOnElement("linkText", "Add Resource");
		clickOnLink("linkText", "Add Resource");
		WebElement nam = driver().findElement(By.cssSelector(inputNewResourceTitle));
		nam.clear();
		nam.sendKeys(resName);
		uploadFile("Image", inputBrowseThumbnail);
		inputTextClear("xpath", inputResourceTags);
		inputText("xpath", inputResourceTags, resourceTagName);
		waitOnSpinner();
		selectAssets("PDF", resourceAssetPDF);
		selectAssets("Video", resourceAssetVideo);
		selectAssets("Text", resourceAssetText);
		selectAssets("Document", resourceAssetDocument);
		selectAssets("Spreadsheet", resourceAssetSSheet);
		selectAssets("ZIP", resourceAssetZip);
		selectAssets("Presentation", resourceAssetPresentation);
		selectAssets("Image", resourceAssetImage);
		selectAssets("Content Block", resourceAssetCB);
		clickOnLink("linkText", "Next");
		selectCategoryWhileCreatingResource(pCategory);
		selectSubscriptionPlans();
		selectRankDefs(futDate);
		selectFromDropdown("cssSelector", drpdnNewResourceStatus, "byVisibleText", status);
		if (resPublish == true) {
			waitOnElement("cssSelector", publishImmed);
			scrollDown("cssSelector", publishImmed);
			WebElement epub = driver().findElement(By.cssSelector(publishImmed));
			epub.click();
			inputTextClear("cssSelector", inputExpiryDate);
			inputText("cssSelector", inputExpiryDate, futDate);
			clickOnElement("cssSelector","div.pyr_core_resource_expiry_date>div.datetime_picker>span>button");
			clickOnElement("cssSelector","div.pyr_core_resource_expiry_date>div.datetime_picker>span>button");
		}

		if (isResNewest == true) {
			WebElement eresnew = driver().findElement(By.xpath(chkNewResourceDisplayAsNewest));
			boolean isresNewchked = eresnew.isSelected();
			selectRadioOrCheckbox("xpath", chkNewResourceDisplayAsNewest);
			inputTextClear("xpath", inputDisplayAsNewest);
			inputText("xpath", inputDisplayAsNewest, futDate);
			

		}
		selectMarketlangs(languageList);
		waitOnElement("cssSelector", btnCreateNewResource);
		scrollDown("cssSelector", btnCreateNewResource);
		clickOnButton("cssSelector", btnCreateNewResource);
		waitOnSpinner();
		//confirmationMessage("Resource is created");


	}

	public void likeAnAssetWorkSpace() throws Exception {
		logInfo("inside likeAnAssetWorkSpace() Method");
		waitOnElement("cssSelector", likeStats);
		String likeT = driver().findElement(By.cssSelector(likeStats)).getText().trim();
		if (likeT.equalsIgnoreCase("Like")) {
			clickOnElement("cssSelector", likeStats);
			waitOnSpinner();
			waitOnElement("cssSelector", likeStats);
			String likedT = driver().findElement(By.cssSelector(likeStats)).getText().trim();
			Assert.assertEquals(likedT, "Liked");
		}

	}

	public void unlikeAnAssetWorkSpace() throws Exception {
		logInfo("inside unlikeAnAssetWorkSpace() Method");
		waitOnElement("cssSelector", likeStats);
		String likeT = driver().findElement(By.cssSelector(likeStats)).getText().trim();
		if (likeT.equalsIgnoreCase("Liked")) {
			clickOnElement("cssSelector", likeStats);
			waitOnSpinner();
			waitOnElement("cssSelector", likeStats);
			String likedT = driver().findElement(By.cssSelector(likeStats)).getText().trim();
			Assert.assertEquals(likedT, "Like");
		}

	}

	public void saveAsFavoriteWorkSpace() throws Exception {
		logInfo("inside saveAsFavoriteWorkSpace() Method");
		waitOnElement("cssSelector", saveAsFavStats);
		String favT = driver().findElement(By.cssSelector(saveAsFavStats)).getText().trim();
		if (favT.equalsIgnoreCase("Save As Favorite")) {
			clickOnElement("cssSelector", saveAsFavStats);
			waitOnSpinner();
			waitOnElement("cssSelector", saveAsFavStats);
			String favTd = driver().findElement(By.cssSelector(saveAsFavStats)).getText().trim();
			Assert.assertEquals(favTd, "Remove As Favorite");
		}

	}

	public void removeAsFavoriteWorkSpace() throws Exception {
		logInfo("inside removeAsFavoriteWorkSpace() Method");
		waitOnElement("cssSelector", saveAsFavStats);
		String favHeader = driver().findElement(By.cssSelector(saveAsFavStats)).getText().trim();
		if (favHeader.equalsIgnoreCase("Remove As Favorite")) {
			clickOnElement("cssSelector", saveAsFavStats);
			waitOnSpinner();
			waitOnElement("cssSelector", saveAsFavStats);
			String likedT = driver().findElement(By.cssSelector(saveAsFavStats)).getText().trim();
			Assert.assertEquals(likedT, "Save As Favorite");
		}

	}

	public boolean verifyAssetFromFavoritesSection(String assetName) throws Exception {
		logInfo("inside verifyAssetInFavoritesSection() Method");
		System.out.println("inside verifyAssetInFavoritesSection() Method");
		boolean isfavFound = false;
		waitOnElement("cssSelector", myFavoritesHead);
		scrollDown("cssSelector", myFavoritesHead);
		String myfav = driver().findElement(By.cssSelector(myFavoritesHead)).getText().trim();
		System.out.println(myfav + "from landing page");
		if (myfav.equalsIgnoreCase("My Favorites")) {
			WebElement favs = driver().findElement(By.cssSelector(myFavoritesSec));
			List<WebElement> allFavs = favs.findElements(By.tagName("a"));
			System.out.println(allFavs.size());
			for (int i = 2; i <= allFavs.size() + 1; i++) {
				String part1 = ".col-md-4.col-sm-4.col-xs-12:nth-child(3)>div:nth-child(";
				String part2 = ")>a";
				String actualTxt = driver().findElement(By.cssSelector(part1 + i + part2)).getText().trim();
				System.out.println(actualTxt);
				if (assetName.equalsIgnoreCase(actualTxt)) {
					isfavFound = true;
					break;
				}
			}
			if (isfavFound == false) {
				logInfo(assetName + " Unable to find the The asset to click.");
				Assert.assertTrue(isfavFound, assetName + " Unable to find the asset to click");
			}
		}
		return isfavFound;
	}

	public void selectAssetFromFavoritesSection(String assetName) throws Exception {
		logInfo("inside selectAssetInFavoritesSection() Method");
		boolean isfavFound = false;
		String myfav = driver().findElement(By.cssSelector(myFavoritesHead)).getText().trim();
		if (myfav.equalsIgnoreCase("My Favorites")) {
			WebElement favs = driver().findElement(By.cssSelector(myFavoritesSec));
			List<WebElement> allFavs = favs.findElements(By.tagName("a"));
			System.out.println(allFavs.size());
			for (int i = 2; i <= allFavs.size(); i++) {
				String part1 = ".col-md-4.col-sm-4.col-xs-12:nth-child(3)>div:nth-child(";
				String part2 = ")>a";
				String actualTxt = driver().findElement(By.cssSelector(part1 + i + part2)).getText().trim();
				if (assetName.equalsIgnoreCase(actualTxt)) {
					isfavFound = true;
					waitOnElement("cssSelector", part1 + i + part2);
					clickOnElement("cssSelector", part1 + i + part2);
					break;
				}
			}
			if (isfavFound == false) {
				logInfo(assetName + " Unable to find the The asset to click.");
				Assert.assertTrue(isfavFound, assetName + " Unable to find the asset to click");
			}
		}
	}

	public boolean verifyAndSelectAssetFromNewstFilesSection(String assetName) throws Exception {
		logInfo("inside verifyAndSelectAssetFromNewstFilesSection() Method");
		boolean isfavFound = false;
		waitOnElement("cssSelector", newestHead);
		scrollDown("cssSelector", newestHead);
		String newR = driver().findElement(By.cssSelector(newestHead)).getText().trim();
		System.out.println(newR + "from landing page");
		if (newR.equalsIgnoreCase("Newest Files")) {
			WebElement favs = driver().findElement(By.cssSelector(newestSec));
			List<WebElement> allFavs = favs.findElements(By.tagName("a"));
			System.out.println(allFavs.size());
			for (int i = 2; i <= allFavs.size() + 1; i++) {
				String part1 = ".col-md-4.col-sm-4.col-xs-12:nth-child(2)>div:nth-child(";
				String part2 = ")>a";
				String actualTxt = driver().findElement(By.cssSelector(part1 + i + part2)).getText().trim();
				System.out.println(actualTxt);
				if (assetName.equalsIgnoreCase(actualTxt)) {
					isfavFound = true;
					clickOnElement("cssSelector", part1 + i + part2);
					break;
				}
			}
			if (isfavFound == false) {
				logInfo(assetName + " Unable to find the The asset to click.");
				Assert.assertTrue(isfavFound, assetName + " Unable to find the asset to click");
			}
		}
		return isfavFound;
	}

	public void closeWorkSpace() throws Exception {
		logInfo("inside closeWorkSpace() Method");

		clickOnElement("cssSelector", workspaceClose);
	}

	public boolean downloadassetFromWs() throws  Exception {
		logInfo("Inside downloadassetFromWs() Method");
		boolean isDownloadLinkFound = false;
		verifyElementPresent("cssSelector", downloadIc);
		waitOnElement("cssSelector", downloadIc);
		clickOnElement("cssSelector", downloadIc);
		if (verifyFileExistsOnDisk(filepath + "icentris_pdf.pdf")) {
			isDownloadLinkFound = true;
			logInfo(filepath + "icentris_pdf.pdf" + " file exists.");
			deleteFileFromLocal("icentris_pdf.pdf");
		} else {
			Assert.assertTrue(isDownloadLinkFound, "couldn't download the file");
			logInfo(filepath + "icentris_pdf.pdf" + " file doesn't exists.");
		}
		return isDownloadLinkFound;

	}

	public boolean shareAssetToCommunityFromWs() throws  Exception {
		logInfo("Inside shareAssetToCommunityFromWs() method");
		boolean isAssetShared = false;
		verifyElementPresent("cssSelector", postToCommIc);
		waitOnElement("cssSelector", postToCommIc);
		clickOnElement("cssSelector", postToCommIc);
		waitOnElement("xpath", txtareaShareComments);
		boolean isEleFound = verifyElementPresent("xpath", txtareaShareComments);
		if (isEleFound) {
			inputTextClear("xpath", txtareaShareComments);
			inputText("xpath", txtareaShareComments, commentsText);
			clickOnButton("cssSelector", comtsShare);
			confirmationMessage("This resource has been shared to the community.");
			isAssetShared = true;
		}
		return isAssetShared;
	}

	public boolean shareToMyWebsite(String assetTitle) throws Exception {
		logInfo("Verify MyWebsite section and select it, Again verify 'Share To My Website' modal box.");
		System.out.println("Inside shareToMyWebsite() method.");
		waitOnElement("cssSelector", sharePwpIc);
		clickOnElement("cssSelector", sharePwpIc);
		waitOnElement("xpath", btnViewWebSite);
		clickOnElement("xpath", btnViewWebSite);

		boolean isAssetFound = handleMyWebsite(assetTitle);
		navigate2UserRL();
		return isAssetFound;
	}

	public boolean handleMyWebsite(String resourceAsset) throws Exception {
		logInfo("Get focus on second window & get URl and close it.");
		System.out.println("Inside handleMyWebsite() method.");
		boolean isAssetFound = false;
		String wndBeforeWindow = driver().getWindowHandle();	
		for(String w : driver().getWindowHandles()){
			if(!w.equalsIgnoreCase(wndBeforeWindow)){
				driver().switchTo().window(w);
				System.out.println(resourceAsset + "is the expected name");
				waitOnElement("xpath", txtAssetProduct);
				String resAsset = driver().findElement(By.xpath(txtAssetProduct)).getText().trim();
				System.out.println(resAsset + "is the shared asset");
				if (resAsset.equalsIgnoreCase(resourceAsset)) {
					isAssetFound = true;
					System.out.println(isAssetFound);
				}
				break;
				  
			}
			 driver().switchTo().window(wndBeforeWindow); 

		}
		return isAssetFound;
	}

	public boolean shareResourceByEmail(String resource, String mailID) throws  Exception {
		logInfo("Inside shareResourceByEmail() Method");
		System.out.println("Inside shareResourceByEmail() method.");
		boolean isResourceshare = false;
		waitOnElement("cssSelector", shareEmailIc);
		clickOnElement("cssSelector", shareEmailIc);
		composeNSend(mailID);
		return isResourceshare;

	}

	public void composeNSend(String mailId) throws Exception {
		logInfo("Inside composeNSend() Method");
		
		waitOnElement("cssSelector", recipientsTo);
		WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); // inputVibeComposeTo
		inputText("cssSelector", recipientsTo, mailId);
		//composeTo.click();
		composeTo.sendKeys(Keys.TAB);
		composeTo.sendKeys(Keys.TAB);
		Thread.sleep(5000);
		waitOnElement("cssSelector", subject_Mail);
		inputText("cssSelector", subject_Mail, composeEmailSubject_text);
		Thread.sleep(3000);
		waitOnElement("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]");
		Thread.sleep(3000);
		scrollDown("linkText", "Send");
		clickOnLink("linkText", "Send");
		Thread.sleep(10000);
		waitOnElement("xpath", closeWorkspace);
		clickOnElement("xpath", closeWorkspace);
	}

	public void selectallAssetsAndSendBulkEMail(String emailID) throws  Exception {
		logInfo("Inside selectallAssetsAndSendBulkEMail() Method");
		waitOnElement("cssSelector", allAssetsCheck);
		clickOnElement("cssSelector", allAssetsCheck);
		waitOnElement("cssSelector", assetsOpt);
		String selected = driver().findElement(By.cssSelector(assetsSelectedCount)).getText().trim();
		int selectCo = Integer.parseInt(selected);
		System.out.println("Selected checkboxes " + selected);
		if (selectCo >= 1) {

			waitOnElement("cssSelector", sendBulkEmail);
			clickOnElement("cssSelector", sendBulkEmail);
			waitOnSpinner();
			composeNSend(emailID);
		}

	}

	public void selectallAssetsAndDownload() throws  Exception {
		logInfo("Inside selectallAssetsAndDownload() Method");
		
		waitOnElement("cssSelector", allAssetsCheck);
		clickOnElement("cssSelector", allAssetsCheck);
		waitOnElement("cssSelector", assetsOpt);
		String selected = driver().findElement(By.cssSelector(assetsSelectedCount)).getText().trim();
		int selectCo = Integer.parseInt(selected);
		System.out.println("Selected checkboxes " + selected);
		if (selectCo >= 1) {

			waitOnElement("cssSelector", bulkDownload);
			clickOnElement("cssSelector", bulkDownload);
			waitOnSpinner();

		}
		
	}
	
	public boolean closeSelectionWindow() throws  Exception {
		logInfo("Inside closeSelectionWindow() Method");
		System.out.println("Inside shareResourceByEmail() method.");
		boolean isResourceshare = false;
		waitOnElement("cssSelector", closeSelectWind);
		clickOnElement("cssSelector", closeSelectWind);
		return isResourceshare;

	}
	
	
	public boolean verifyDownloadedFile(String assetType) throws InterruptedException {
		logInfo("Inside verifyDownloadedFile() Method");
		boolean isDownloadLinkFound = false;
		switch (assetType) {
		case "Image":
		if (verifyFileExistsOnDisk(filepath + "icentris_image3.jpeg")) {
			isDownloadLinkFound = true;
			logInfo(filepath + "icentris_image3.jpeg" + " file exists.");
			deleteFileFromLocal("icentris_image3.jpeg");
		} else {
			Assert.assertTrue(isDownloadLinkFound, "couldn't download the file");
			logInfo(filepath + "icentris_image3.jpeg" + " file doesn't exists.");
		}
		break;
		case "PDF":
			if (verifyFileExistsOnDisk(filepath + "icentris_pdf.pdf")) {
				isDownloadLinkFound = true;
				logInfo(filepath + "icentris_pdf.pdf" + " file exists.");
				deleteFileFromLocal("icentris_pdf.pdf");
			} else {
				Assert.assertTrue(isDownloadLinkFound, "couldn't download the file");
				logInfo(filepath + "icentris_pdf.pdf" + " file doesn't exists.");
			}
			break;
			
		case "Text":
			if (verifyFileExistsOnDisk(filepath + "icentris_text.txt")) {
				isDownloadLinkFound = true;
				logInfo(filepath + "icentris_text.txt" + " file exists.");
				deleteFileFromLocal("icentris_text.txt");
			} else {
				Assert.assertTrue(isDownloadLinkFound, "couldn't download the file");
				logInfo(filepath + "icentris_text.txt" + " file doesn't exists.");
			}
			break;
		case "Document":
			if (verifyFileExistsOnDisk(filepath + "icentris_doc.doc")) {
				isDownloadLinkFound = true;
				logInfo(filepath + "icentris_doc.doc" + " file exists.");
				deleteFileFromLocal("icentris_doc.doc");
			} else {
				Assert.assertTrue(isDownloadLinkFound, "couldn't download the file");
				logInfo(filepath + "icentris_doc.doc" + " file doesn't exists.");
			}
			break;
		case "Spreadsheet":
			if (verifyFileExistsOnDisk(filepath + "icentris_excel.xlsx")) {
				isDownloadLinkFound = true;
				logInfo(filepath + "icentris_excel.xlsx" + " file exists.");
				deleteFileFromLocal("icentris_excel.xlsx");
			} else {
				Assert.assertTrue(isDownloadLinkFound, "couldn't download the file");
				logInfo(filepath + "icentris_excel.xlsx" + " file doesn't exists.");
			}
			break;
		
		case "Presentation":
			if (verifyFileExistsOnDisk(filepath + "icentris_ppt.pptx")) {
				isDownloadLinkFound = true;
				logInfo(filepath + "icentris_ppt.pptx" + " file exists.");
				deleteFileFromLocal("icentris_ppt.pptx");
			} else {
				Assert.assertTrue(isDownloadLinkFound, "couldn't download the file");
				logInfo(filepath + "icentris_ppt.pptx" + " file doesn't exists.");
			}
			break;
		case "Zip":
			if (verifyFileExistsOnDisk(filepath + "icentris_zip.zip")) {
				isDownloadLinkFound = true;
				logInfo(filepath + "icentris_zip.zip" + " file exists.");
				deleteFileFromLocal("icentris_zip.zip");
			} else {
				Assert.assertTrue(isDownloadLinkFound, "couldn't download the file");
				logInfo(filepath + "icentris_zip.zip" + " file doesn't exists.");
			}
			break;
		}
		return isDownloadLinkFound;

	}

	public void deleteResourceAdmin(String resource) throws  Exception {
		logInfo("inside deleteResourceAdmin() method.");
		String expTit = driver().findElement(By.cssSelector("div.pull-right>a")).getAttribute("title");
		System.out.println(expTit);
		if (expTit.equals("Grid")) {
			System.out.println("into Grid");

			WebElement tblRes = driver().findElement(By.xpath("//*[@id='resource-table']/tbody"));
			List allRows = tblRes.findElements(By.tagName("tr"));
			int all_rows = allRows.size();

			String before_name = "//*[@id='resource-table']/tbody/tr[";
			String after_name = "]/td[2]/a";

			String before_btn = "//*[@id='resource-table']/tbody/tr[";
			String after_btn = "]/td[7]/div/div/div/button";

			String before_delete = "//*[@id='resource-table']/tbody/tr[";
			String after_delete = "]/td[7]/div/div/div/ul/li[3]/a";

			for (int i = 1; i <= all_rows; i++) {
				WebElement n = driver().findElement(By.xpath(before_name + i + after_name));
				String name = n.getText().trim();
				if (name.equalsIgnoreCase(resource)) {
					WebElement btn = driver().findElement(By.xpath(before_btn + i + after_btn));
					btn.click();
					waitOnElement("xpath", before_delete + i + after_delete);
					WebElement delete = driver().findElement(By.xpath(before_delete + i + after_delete));
					delete.click();
					confirmToOk();
					break;
				}
				i = i + 2;
			}

		} else {
			System.out.println("into LIst");
			waitOnElement("xpath", "//*[@id='index_page']/div/div");
			List<WebElement> rows = driver().findElements(By.xpath("//*[@id='index_page']/div/div"));
			int rowsCnt = rows.size();

			String before_res_row = "//*[@id='index_page']/div[";
			String res_row = "]/div/div[";
			String more_options = "]/div/div[1]/div";
			String after_res_row = "]/div[1]/div[4]/div[1]";
			String delete_path = "]/div/div[1]/div/ul/li[3]/a";

			for (int i = 1; i <= rowsCnt; i++) {
				waitOnElement("xpath", "//*[@id='index_page']/div[" + i + "]/div/div");
				List<WebElement> cols = driver().findElements(By.xpath("//*[@id='index_page']/div[" + i + "]/div/div"));
				for (int j = 1; j <= cols.size(); j++) {
					waitOnElement("xpath", before_res_row + i + res_row + j + after_res_row);
					WebElement res = driver().findElement(By.xpath(before_res_row + i + res_row + j + after_res_row));
					if (res.getText().equalsIgnoreCase(resource)) {
						waitOnElement("xpath", before_res_row + i + res_row + j + after_res_row);
						driver().findElement(By.xpath(before_res_row + i + res_row + j + more_options)).click();
						String delete = before_res_row + i + res_row + j + delete_path;
						waitOnElement("xpath", delete);
						WebElement deleteFile = driver().findElement(By.xpath(delete));
						deleteFile.click();
						confirmOK();
						// confirmToDelete();
						break;
					}
				}
			}
		}
	}

	
	
	
	
	public boolean deleteMultipleResources() throws Exception {
		logInfo("inside deleteMultipleResources() method");
		boolean isMultipleSel = false;
		waitOnElement("cssSelector", resourcesTabAdmin);
		WebElement row = driver().findElement(By.cssSelector(resourcesTabAdmin));
		List<WebElement> rows = row.findElements(By.cssSelector(multResChkBox));
		if (rows.size()>0){
		for (WebElement rowD : rows) {
			rowD.click();
			waitOnSpinner();
		}
		clickOnElement("cssSelector", deleteMultipleBtn);
		waitOnElement("cssSelector", btnDeleteResourceYes);
		clickOnElement("cssSelector", btnDeleteResourceYes);
		confirmationMessage("Resource was successfully deleted.");
		isMultipleSel = true;
		}
		return isMultipleSel;
	}

	public void deleteResourcePresentAdmin(String resourceName) throws  Exception {
		logInfo("inside deleteResourcePresentAdmin() method to select the resource.");
		System.out.println("inside deleteResourcePresentAdmin() method to select the resource.");
		String expTit = prop.getLocatorForEnvironment(appUrl,"resourceLayout");
		if (expTit.equals("list")) {
			System.out.println("Current view is Grid View");
			waitOnElement("xpath", resourcesTabAdmin);
			WebElement row = driver().findElement(By.xpath(resourcesTabAdmin));
			List<WebElement> rows = row.findElements(By.cssSelector(resourcesListGridView));
			int rowsCnt = rows.size();
			System.out.println("resources to view are" + rowsCnt);
			String part1 = "div.thumb-item:nth-child(";
			String name = ")>div>div.main-container-info>span:nth-child(1)";
			String part2 = ")>div>div.thumb-container>div";
			String moreOptions = ")>div>div.more-options>div>button.btn.dropdown-toggle > i";
			String delete = ")>div>div.more-options>div>ul>li:nth-child(3)>a";
			for (int i = 1; i <= rowsCnt; i++) {
				String res = driver().findElement(By.cssSelector(part1 + i + name)).getText().trim();

				if (res.equalsIgnoreCase(resourceName)) {
					System.out.println(res);
					WebElement moreOption = driver().findElement(By.cssSelector(part1 + i + moreOptions));
					moreOption.click();
					clickOnElement("cssSelector", part1 + i + delete);
					confirmToOk();
					break;
				}
			}
		} else {
			System.out.println("Current view is List View");
			waitOnElement("xpath", resourcesTabAdmin);
			WebElement row = driver().findElement(By.xpath(resourcesTabAdmin));
			List<WebElement> rows = row.findElements(By.cssSelector(categoriestotListView));
			int rowsCnt = rows.size();
			String part1 = "//*[@id='main-content']/div/div/div[2]/div[1]/div[2]/div[3]/div[1]/div[";
			String moreOptions = "]/div/div[1]/div/button";
			String name = "]/div[3]/div[1]/div[1]/div/div[4]/span[1]";
			String delete = "/div/ul/li[3]/a";
			for (int i = 2; i <= rowsCnt; i++) {
				String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
				System.out.println(res);
				if (res.contains(resourceName)) {
					clickOnElement("xpath", part1 + i + moreOptions);
					scrollDown("xpath", part1 + i + delete);
					clickOnElement("xpath", part1 + i + delete);
					confirmOK();
					break;
				}
			}
		}
	}

	public boolean verifyAssetsAddedWhileCreatingAResource(String assetName) {
		logInfo("inside verifyAssetsAddedWhileCreatingAResource() method to select the resource.");
		boolean isAssetAdded = false;
		WebElement row = driver().findElement(By.cssSelector("#resources_assets_list>tbody"));
		List<WebElement> rows = row.findElements(By.cssSelector("#resources_assets_list>tbody>tr"));
		int rowsCnt = rows.size();
		String part1 = "#resources_assets_list>tbody>tr:nth-child(";
		String part2 = ")>td:nth-child(3)>label";

		for (int i = 2; i <= rowsCnt; i++) {
			String res = driver().findElement(By.cssSelector(part1 +i+ part2)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(assetName)) {
				isAssetAdded = true;
			}
			break;
		}
		return isAssetAdded;
	}
	
	public void deleteAssetFromEditResource(String assetName) throws Exception {
		logInfo("inside deleteAssetFromEditResource() method to delete an asset");
		boolean isAssetAdded = false;
		WebElement row = driver().findElement(By.cssSelector("#resources_assets_list>tbody"));
		List<WebElement> rows = row.findElements(By.cssSelector("#resources_assets_list>tbody>tr"));
		int rowsCnt = rows.size();
		String part1 = "#resources_assets_list>tbody>tr:nth-child(";
		String part2 = ")>td:nth-child(3)>label";
		String moreOptions=")>td:nth-child(4)>div>button";
		String delete=")>td:nth-child(4)>div>ul>li:nth-child(2)>a";

		for (int i = 2; i <= rowsCnt; i++) {
			String res = driver().findElement(By.cssSelector(part1 + i+ part2)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(assetName)) {
				
				WebElement delOp = driver().findElement(By.cssSelector(part1 + i + moreOptions));
				delOp.click();
				WebElement delOp1 = driver().findElement(By.cssSelector(part1 + i + delete));
				delOp1.click();
				
				confirmAlert();
			}
			break;
		}
	}

	public void deleteProductCategory(String pcategory) throws Exception {
		logInfo("inside deleteProductCategory() method.");
		waitOnElement("xpath", productCategoryList);

		boolean isCategoryFound = false;

		WebElement tblResource = driver().findElement(By.xpath(delResTab));
		List parentDivs = tblResource.findElements(By.className("col-md-10"));
		int parent_divs = parentDivs.size();
		System.out.println("Total divs = " + parent_divs);

		String before_pname = "//*[@id='index_page']/div[2]/div[";
		String after_pname = "]/div[3]";

		String before_btn = "//*[@id='index_page']/div[2]/div[";
		String after_btn = "]/div[5]/div/button";

		String before_delete = "//*[@id='index_page']/div[2]/div[";
		String after_delete = "]/div[5]/div/ul/li[2]/a";

		for (int i = parent_divs; i >= 2; i--) {
			WebElement e = driver().findElement(By.xpath(before_pname + i + after_pname));
			String name = e.getText().trim();
			System.out.println("pname =" + name);
			if (pcategory.equalsIgnoreCase(name)) {
				WebElement btn = driver().findElement(By.xpath(before_btn + i + after_btn));
				btn.click();
				waitOnElement("xpath", before_delete + i + after_delete);
				WebElement delete = driver().findElement(By.xpath(before_delete + i + after_delete));
				delete.click();
				confirmToOk();
				waitOnSpinner();
			//	confirmationMessage("Resource Category deleted successfully.");
				break;
			}
		}
	}

	public void deleteAResourceAssetAdminSide(String assetName) throws  Exception {
		logInfo("inside deleteAResourceAssetAdminSide() method to select the resource.");
		boolean isAssetFound = false;
		waitOnElement("cssSelector", resourcesUserTab);
		waitOnElement("cssSelector", assetsContainer);
		WebElement row = driver().findElement(By.cssSelector(assetsContainer));
		List<WebElement> rows = row.findElements(By.cssSelector(assetsTot));
		int rowsCnt = rows.size();
		System.out.println("assets to view are" + rowsCnt);
		for (int i = 2; i <= rowsCnt + 1; i++) {
			String part1 = "//*[@id='main-content']/div/div[2]/div/div[2]/div[3]/div[";
			String name = "]/div/div[2]/p[1]/a";
			String moreOptions="/div/div[5]/div/div/button";
			String delete= "/div/div[5]/div/div/ul/li[3]/a";
			String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(assetName)) {
				clickOnElement("xpath", part1 + i + moreOptions);
				scrollDown("xpath", part1 + i + delete);
				clickOnElement("xpath", part1 + i + delete);
				confirmOK();
				isAssetFound = true;
				break;

			}
		}
	
	}
	
	
	/*public boolean searchResource(String searchTex, String resAsstName) throws Exception {
		logInfo("Inside searchResource() method.");
		boolean isResourceFound = false;
			waitOnElement("xpath", searchResourceTxt);
			inputTextClear("xpath", searchResourceTxt);
			inputText("xpath", searchResourceTxt, searchTex);
			WebElement autoCom=driver().findElement(By.cssSelector(autoComSrch));
			List<WebElement> searchRes = autoCom.findElements(By.tagName("li"));
			int searchCount = searchRes.size();
			System.out.println(searchCount + "are the results to verify");
			String before = "ul.ui-autocomplete.ui-front.ui-menu.ui-widget.ui-widget-content>li:nth-child(";
			String after = ")>a";
			for (int i = 3; i <= searchCount; i++) {
				waitOnElement("cssSelector", before + i + after);
				WebElement res = driver().findElement(By.cssSelector(before + i + after));
				if (res.getText().trim().equalsIgnoreCase(resAsstName)) {
					isResourceFound = true;
					break;
				}
							
			}
			if(!isResourceFound){
				logInfo(resAsstName + " Unable to find the resource.");
				Assert.assertTrue(isResourceFound, resAsstName + " Unable to find the resource");
			}	
		
		return isResourceFound;
		
	}*/

	public void nextResource() throws  Exception {
		logInfo("Inside nextResource() Method.");
		boolean isLinkFound= verifyElementPresent("cssSelector", ".next-pagination");
		if(isLinkFound) {
		waitOnElement("cssSelector",".next-pagination");
		clickOnElement("cssSelector",".next-pagination");
		waitOnSpinner();
		}
		if(!isLinkFound) {
			logInfo(" Unable to find the resource.");
			Assert.assertTrue(isLinkFound,  " Unable to find the link to click");
		}
	}
	
	public void previousResource() throws  Exception {
		logInfo("Inside nextResource() Method.");
		boolean isLinkFound= verifyElementPresent("cssSelector", ".next-pagination");
		if(isLinkFound) {
		waitOnElement("cssSelector",".previous-pagination");
		clickOnElement("cssSelector",".previous-pagination");
		waitOnSpinner();
		
	}
		if(!isLinkFound) {
			logInfo(" Unable to find the resource.");
			Assert.assertTrue(isLinkFound,  " Unable to find the link to click");
		}
	}
	
	public void cloneResource(String resName) throws Exception{
		logInfo("inside cloneResource() method.");
		inputTextClear("cssSelector",inputNewResourceTitle);
		inputText("cssSelector",inputNewResourceTitle,resName);
		clickOnLink("linkText","Next");
		clickOnButton("cssSelector",btnCreateNewResource);
		waitOnSpinner();
		
	}
	
	public void cloneResourceUser(String resName) throws Exception{
		logInfo("inside cloneResource() method.");
		inputTextClear("cssSelector",inputNewResByUserTitle);
		inputText("cssSelector",inputNewResByUserTitle,resName);
		waitOnElement("cssSelector", publishImmed);
		scrollDown("cssSelector", publishImmed);
		WebElement epub = driver().findElement(By.cssSelector(publishImmed));
		epub.click();
		clickOnButton("cssSelector",userRLCreate);
		waitOnSpinner();
		
	}
	
	public void performActionsFromResourceDropDownAdmin(String optionName, String resName) throws  Exception {
		logInfo("inside performActionsFromResourceDropDownAdmin() method to Preview the resource.");

		String expTit = prop.getLocatorForEnvironment(appUrl, "resourceLayout");
		if (expTit.equals("list")) {
			//performActionsFromResourceDropDownAdminListView(optionName, resName);
			boolean isresourceFound = false;
			boolean isDownloadLinkFound=false;
			waitOnElement("cssSelector", resourcesAdminTab);
			WebElement row = driver().findElement(By.cssSelector(resourcesTabAdmin));
			List<WebElement> rows = row.findElements(By.cssSelector(categoriestotListView));
			int rowsCnt = rows.size();
			for (int i = 2; i <= rowsCnt+1; i++) {
				String res = driver().findElement(By.cssSelector(resPagepart1 + i + resPagename)).getText().trim();
				System.out.println(res);
				if (res.equalsIgnoreCase(resName)) {
					System.out.println(res);
					switch (optionName) {
					case "Preview":
						System.out.println("case preview");
						WebElement sa1 = driver().findElement(By.cssSelector(resPagepart1 + i + resmoreOptions));
						sa1.click();
						WebElement sa2 = driver().findElement(By.cssSelector(resPagepart1 + i + respreview));
						sa2.click();
						isresourceFound = true;
						break;
					case "Edit":
						WebElement editOp = driver().findElement(By.cssSelector(resPagepart1 + i + resmoreOptions));
						editOp.click();
						WebElement editOp1 = driver().findElement(By.cssSelector(resPagepart1 + i + resedit));
						editOp1.click();
						isresourceFound = true;
						break;
						
					case "Delete":
						System.out.println("Inside Delete case");
						WebElement deleteOp = driver().findElement(By.cssSelector(resPagepart1 + i + resmoreOptions));
						deleteOp.click();
						WebElement deleteOp1 = driver().findElement(By.cssSelector(resPagepart1 + i + resdelete));
						deleteOp1.click();
						confirmOK();
						isresourceFound = true;
						break;
						
					case "Clone":
						WebElement cloneOp = driver().findElement(By.cssSelector(resPagepart1 + i + resmoreOptions));
						cloneOp.click();
						WebElement cloneOp1 = driver().findElement(By.cssSelector(resPagepart1 + i + resclone));
						cloneOp1.click();
						isresourceFound = true;
						break;
					case "Download":
						WebElement downloadOp = driver().findElement(By.cssSelector(resPagepart1 + i + resmoreOptions));
						downloadOp.click();
						WebElement downloadOp1 = driver().findElement(By.cssSelector(resPagepart1 + i + resdownload));
						downloadOp1.click();
						isresourceFound = true;
						if (verifyFileExistsOnDisk(filepath + "icentris_pdf.pdf")) {
							isDownloadLinkFound = true;
							logInfo(filepath + "icentris_pdf.pdf" + " file exists.");
							deleteFileFromLocal("icentris_pdf.pdf");
						} else {
							Assert.assertTrue(isDownloadLinkFound, "couldn't download the file");
							logInfo(filepath + "icentris_pdf.pdf" + " file doesn't exists.");
						}
						break;
				
						
					default:
						System.out.println("Invalid file type.");
						break;
					}

					break;
				}
			}
			if (isresourceFound == false) {
				logInfo(resName + " Unable to find the The resource to preview.");
				Assert.assertTrue(isresourceFound, resName + " Unable to find the resource to preview");
		
			}
		}
		else {
			boolean isresourceFound = false;
			boolean isDownloadLinkFound=false;
			waitOnElement("cssSelector", resourcesAdminTab);
			WebElement row = driver().findElement(By.cssSelector(resourcesAdminTab));
			List<WebElement> rows = row.findElements(By.cssSelector(resourcesListGridView));
			int rowsCnt = rows.size();
			System.out.println("Resources to view are" + rowsCnt);
			String part1 = "div.thumb-item:nth-child(";
			String name = ")>div>div:nth-child(4)>span:nth-child(1)";
			String moreOptions = ")>div>div:nth-child(1)>div>button";
			String preview =")>div>div:nth-child(1)>div>ul>li:nth-child(1)>a";
			String download = ")>div>div:nth-child(1)>div>ul>li:nth-child(4)>a";
			String edit = ")>div>div:nth-child(1)>div>ul>li:nth-child(2)>a";
			String clone = ")>div>div:nth-child(1)>div>ul>li:nth-child(3)>a";
			String delete = ")>div>div:nth-child(1)>div>ul>li:nth-child(5)>a";
		
			for (int i = 1; i <= rowsCnt; i++) {
				String res = driver().findElement(By.cssSelector(part1 + i + name)).getText().trim();
				System.out.println(res);
				if (res.equalsIgnoreCase(resName)) {
					System.out.println(res);
					switch (optionName) {
					case "Preview":
						WebElement sa1 = driver().findElement(By.cssSelector(part1 + i + moreOptions));
						sa1.click();
						WebElement sa2 = driver().findElement(By.cssSelector(part1 + i + preview));
						sa2.click();
						isresourceFound = true;
						break;
					case "Edit":
						WebElement editOp = driver().findElement(By.cssSelector(part1 + i + moreOptions));
						editOp.click();
						WebElement editOp1 = driver().findElement(By.cssSelector(part1 + i + edit));
						editOp1.click();
						isresourceFound = true;
						break;
						
					case "Delete":
						System.out.println("Inside Delete case");
						isresourceFound = true;
						WebElement deleteOp = driver().findElement(By.cssSelector(part1 + i + moreOptions));
						deleteOp.click();
						WebElement deleteOp1 = driver().findElement(By.cssSelector(part1 + i + delete));
						deleteOp1.click();
						confirmToOk();
						
						break;
						
					case "Clone":
						WebElement cloneOp = driver().findElement(By.cssSelector(part1 + i + moreOptions));
						cloneOp.click();
						WebElement cloneOp1 = driver().findElement(By.cssSelector(part1 + i + clone));
						cloneOp1.click();
						isresourceFound = true;
						break;
					case "Download":
						WebElement downloadOp = driver().findElement(By.cssSelector(part1 + i + moreOptions));
						downloadOp.click();
						WebElement downloadOp1 = driver().findElement(By.cssSelector(part1 + i + download));
						downloadOp1.click();
						isresourceFound = true;
						if (verifyFileExistsOnDisk(filepath + "icentris_pdf.pdf")) {
							isDownloadLinkFound = true;
							logInfo(filepath + "icentris_pdf.pdf" + " file exists.");
							deleteFileFromLocal("icentris_pdf.pdf");
						} else {
							Assert.assertTrue(isDownloadLinkFound, "couldn't download the file/file doens't exist");
							logInfo(filepath + "icentris_pdf.pdf" + " couldn't download the file/file doens't exist");
						}
						break;
				
						
					default:
						System.out.println("Invalid file type.");
						break;
					}

					break;
				}
			}
			if (isresourceFound == false) {
				logInfo(resName + " Unable to find the The resource to preview.");
				Assert.assertTrue(isresourceFound, resName + " Unable to find the resource to preview");
		
			}
			//performActionsFromResourceDropDownAdminGridView(optionName, resName);
		}
	}
	
	
	public void performActionsFromResourceDropDownAdminListView(String optionName,String resName) throws  Exception {
		
	}
	
	
	
	public void performActionsFromResourceDropDownAdminGridView(String optionName,String resName) throws  Exception {
		logInfo("isndie performActionsFromResourceDropDownAdminGridView Method");
		
	
	}
	
	
	
	
	
	public void verifyEnableResourceOptions() throws Exception{
		logInfo("inside verifyEnableResourceOptions() method");
		clickOnLink("xpath", SettingIcRl);
		enableOrDisableResourceOptions("Share Resources To Social Networks","On");
		enableOrDisableResourceOptions("Share Resources To Community","On");
		enableOrDisableResourceOptions("Share Resources To Pwp","On");
		enableOrDisableResourceOptions("Resource Library Send Email As Link Only","Off");
		enableOrDisableResourceOptions("Resource Library Share Through Email","On");
		enableOrDisableResourceOptions("Resource Library Layout","Grid");
		enableOrDisableResourceOptions("Open Asset Workspace From Resource Overlay","Off");
		clickOnButton("xpath", btnUpdateResourceOptions);
		waitOnElement("xpath",reIndexBtn);
		clickOnButton("xpath",reIndexBtn);
	}
	
	
	public void goToResourceSettings() throws Exception{
		logInfo("inside goToResourceSettings() method");
			driver().navigate().to(appUrl + "pyr_core/pyr_admin/resource_library_settings");
		
	}
	
	public void disableResourceOptions() throws Exception{
		logInfo("inside disableResourceOptions() method");
		goToResourceSettings();
		enableOrDisableResourceOptions("Share Resources To Social Networks","off");
		/*enableOrDisableResourceOptions("Share Resources To Community","Off");
		enableOrDisableResourceOptions("Share Resources To Pwp","Off");
	//	enableOrDisableResourceOptions("Resource Library Send Email As Link Only","Off");
		enableOrDisableResourceOptions("Resource Library Share Through Email","Off");
		enableOrDisableResourceOptions("Resource Library Layout","list");
		enableOrDisableResourceOptions("Open Asset Workspace From Resource Overlay","On");
		clickOnButton("xpath", btnUpdateResourceOptions);
		waitOnElement("xpath",reIndexBtn);
		clickOnButton("xpath",reIndexBtn);*/
	}
	
	public void enableOrDisableResourceOptions(String resourceOption, String enableOption) throws Exception{
		logInfo("inside verifyResourceOptions() method");
		System.out.println("inside verifyResourceOptions() method");
		switch(resourceOption){
		case "Number Of Resources Per Page" :
			selectResourceOptionsSettingsToEnterData(resourceOption,enableOption);
			break;

		case "Share Resources To Social Networks":
			selectResourceOptionsSettings(resourceOption,enableOption);
			break;
		case "Share Resources To Community":
			selectResourceOptionsSettings(resourceOption,enableOption);
			break;
		case "Share Resources To Pwp" :
			selectResourceOptionsSettings(resourceOption,enableOption);
			break;
		case "Resource Library Send Email As Link Only" :
			selectResourceOptionsSettings(resourceOption,enableOption);
			break;
		case "Resource Library Share Through Email" :
			selectResourceOptionsSettings(resourceOption,enableOption);
			break;
		case "Resource Library Layout" :
			selectResourceOptionsSettings(resourceOption,enableOption);
			break;
		case "Open Asset Workspace From Resource Overlay" :
			selectResourceOptionsSettings(resourceOption,enableOption);
			break;
		case "Ability To Add Resources By Users":
			selectResourceOptionsSettings(resourceOption,enableOption);
			break;
		case "Ability To View Past By Resources":
			selectResourceOptionsSettingsToEnterData(resourceOption,enableOption);
			break;
		case "Resource Max Icon Size Limit":
			selectResourceOptionsSettingsToEnterData(resourceOption,enableOption);
		break;
		}
	}
	
	
	
	
	
	public void selectResourceOptionsSettings(String resourceOption,String enable) throws Exception{
		logInfo("inside selectResourceOptionsSettings() method");
		List<WebElement> el = driver().findElements(By.xpath(lstResourceOptions));
		String before_name = "//*[@id='main-content']/table/tbody/tr[";
		String after_name = "]/td[1]/a";
		for(int i=1;i<=el.size();i++){
			WebElement ele = driver().findElement(By.xpath(before_name+i+after_name));
			String optionText = ele.getText().trim();
			System.out.println(optionText);
			if(optionText.equalsIgnoreCase(resourceOption)){
					String option = before_name+i+after_name;
					clickOnButton("xpath", option);
					waitOnElement("cssSelector",dropDwValueSett);
					selectFromDropdown("cssSelector",dropDwValueSett,"byVisibleText",enable);
					clickOnElement("cssSelector",updateAppSettting); 
					nav2AdminRL();
					break;
			}
		}
		
	}
	
	public void selectResourceOptionsSettingsToEnterData(String resourceOption,String enableOption) throws Exception{
		logInfo("inside selectResourceOptionsSettingsToEnterData() method");
		List<WebElement> el = driver().findElements(By.xpath(lstResourceOptions));
		String before_name = "//*[@id='main-content']/table/tbody/tr[";
		String after_name = "]/td[1]/a";
		for(int i=1;i<=el.size();i++){
			WebElement ele = driver().findElement(By.xpath(before_name+i+after_name));
			String optionText = ele.getText().trim();
			System.out.println(optionText);
			if(optionText.equalsIgnoreCase(resourceOption)){
					String option = before_name+i+after_name;
					clickOnButton("xpath", option);
					waitOnElement("cssSelector",dropDwValueSett);
					inputTextClear("cssSelector", dropDwValueSett);
					inputText("cssSelector", dropDwValueSett, enableOption);
					clickOnElement("cssSelector",updateAppSettting); 
					nav2AdminRL();
					break;
			}
		}
		
	}
	
	
	
	
	
	public void enableNewestResources() throws  Exception{
		logInfo("inside enableNewestResources() method");
		waitOnElement("xpath","//*[@type='text']");
		inputTextClear("xpath","//*[@type='text']");
	}
	
	
	public void selectStatus(String statusName) throws  Exception {
		logInfo("inside selectStatus() method");
		boolean isStatusFound=false;
		waitOnElement("cssSelector",resStatusDrpDn);
		clickOnButton("cssSelector",resStatusDrpDn);
		WebElement statusCon = driver().findElement(By.cssSelector(statusContainerul));
		List<WebElement> statusCons = statusCon.findElements(By.cssSelector(statusContainerLi));
		int totalDds=statusCons.size();
		System.out.println(totalDds);
		for(int i=3;i<=totalDds;i++) {
			String expectedTxt = driver().findElement(By.cssSelector(statusPart1+i+statusPart2)).getText().trim();
			if(expectedTxt.equalsIgnoreCase(statusName)) {
				clickOnElement("cssSelector", statusPart1+i+statusPart2);
				waitOnSpinner();
				isStatusFound=true;
				break;
			}
			
		}
		if (isStatusFound == false) {
			logInfo(statusName + " Unable to find the The status to click.");
			Assert.assertTrue(isStatusFound, statusName + " Unable to find the status to click");
		}
		
	}
	
	
	public void selectMarkets(String marketName) throws  Exception {
		logInfo("inside selectMarkets() method");
		boolean isStatusFound=false;
		waitOnElement("cssSelector",resMarketDrpDn);
		clickOnButton("cssSelector",resMarketDrpDn);
		waitOnElement("cssSelector",marketContainerul);
		WebElement statusCon = driver().findElement(By.cssSelector(marketContainerul));
		List<WebElement> statusCons = statusCon.findElements(By.cssSelector(statusContainerLi));
		int totalDds=statusCons.size();
		System.out.println(totalDds);
		for(int i=4;i<=totalDds;i++) {
			String expectedTxt = driver().findElement(By.cssSelector(marketPart1+i+statusPart2)).getText().trim();
			System.out.println(expectedTxt);
			if(expectedTxt.contains(marketName)) {
				System.out.println("isnide");
				clickOnElement("cssSelector", marketPart1+i+statusPart2);
				waitOnSpinner();
				isStatusFound=true;
				break;
			}
			
		}
	/*	if (isStatusFound == false) {
			logInfo(statusName + " Unable to find the The status to click.");
			Assert.assertTrue(isStatusFound, statusName + " Unable to find the status to click");
		}*/
		
	}
	
	
	public boolean verifyResourceNotPresentUserSide(String resourceName) throws  Exception {
		logInfo("inside verifyResourceNotPresentUserSide() method to verify the resource.");
		boolean isResourcePresent = true;
		waitOnSpinner();
		waitOnElement("cssSelector",viewIc);
		String expTit = driver().findElement(By.cssSelector(viewIc)).getAttribute("title");
		System.out.println(expTit);
		if (expTit.equals("list")) {
			System.out.println("Current view is Grid View");
			waitOnElement("cssSelector", resourcesUserTab);
			WebElement row = driver().findElement(By.cssSelector(resourcesUserTab));
			List<WebElement> rows = row.findElements(By.cssSelector(resourcesListGridView));
			int rowsCnt = rows.size();
			String part1 = "div.panel-body>div.clearfix>div>div:nth-child(";
			String name = ")>div>div.main-container-info>span";
			for (int i = 1; i <= rowsCnt; i++) {
				String res = driver().findElement(By.cssSelector(part1 + i + name)).getText().trim();
				System.out.println(res);
				if (res.contains(resourceName)) {
					isResourcePresent = false;
					break;
				}
				
			}
			if (isResourcePresent) {
				logInfo(resourceName + " Pending resource is showing to the user");
				Assert.assertTrue(isResourcePresent, resourceName + " Pending resource is showing to the user");
			}
		} else {
			System.out.println("Current view is List View");
			waitOnElement("xpath", resourcesLisUser);
			WebElement row = driver().findElement(By.xpath(resourcesLisUser));
			List<WebElement> rows = row.findElements(By.cssSelector(categoriestotListView));
			int rowsCnt = rows.size();
			String part1 = "//*[@id='main-content']/div/div/div[2]/div[3]/div[";
			String name = "]/div/div[2]/div/a";
			for (int i = 2; i <= rowsCnt; i++) {
				String res = driver().findElement(By.xpath(part1 + i + name)).getText().trim();
				System.out.println(res);
				if (res.equalsIgnoreCase(resourceName)) {
					isResourcePresent = false;
					break;
				}
			}
			if (isResourcePresent) {
				logInfo(resourceName + "Pending resource is showing to the user");
				Assert.assertTrue(isResourcePresent, resourceName + " Pending resource is showing to the user");
			}
		}
		return isResourcePresent;
	}

	
	public void  searchResourceAdmin(String searchTex) throws Exception {
		logInfo("Inside searchResource() method.");
			boolean isCatFound =false;
			waitOnElement("cssSelector", searchResourceTxt);
			inputTextClear("cssSelector", searchResourceTxt);
			inputText("cssSelector", searchResourceTxt, searchTex);
			waitOnElement("cssSelector","#ui-id-1");
		//	waitOnElement("linkText",searchTex);
			clickOnElement("linkText",searchTex);
			waitOnSpinner();
			
		
	}
	public void searchCategory(String searchTex) throws Exception {
		logInfo("Inside searchCategory() method.");
			boolean isCatFound =false;
			waitOnElement("cssSelector", searchResourceCategoryTxt);
			inputTextClear("cssSelector", searchResourceCategoryTxt);
			inputText("cssSelector", searchResourceCategoryTxt, searchTex);
			waitOnElement("cssSelector","#ui-id-1");
		//	Thread.sleep(3000);
			waitOnElement("linkText",searchTex);
			clickOnElement("linkText",searchTex);
			clickOnElement("linkText",searchTex);
			waitOnSpinner();
			
		
	}

	
	public boolean searchResourceUserSide(String searchTex,String categoryName) throws Exception {
		logInfo("Inside searchResourceUserSide() method.");
			boolean isCatFound =false;
			waitOnElement("cssSelector", searchResourceTxt);
			inputTextClear("cssSelector", searchResourceTxt);
			inputText("cssSelector", searchResourceTxt, searchTex);
			waitOnElement("cssSelector","#ui-id-1");
			boolean isEleFound = verifyElementPresent("cssSelector","#ui-id-1");
			if(isEleFound) {
		//	waitOnElement("linkText",searchTex);
			clickOnElement("linkText",searchTex);
			waitOnSpinner();
			String catName = driver().findElement(By.cssSelector(".panel-title")).getText();
			System.out.println(catName);
			if(catName.contains(searchTex)) {
				isCatFound=true;
				Assert.assertEquals("Resources "+categoryName+" "+searchTex, catName);
				
			}
			
			}
			
			else {
				
				logInfo("search resource is not working");
				isCatFound = false;
			}
			return isCatFound;
			
		
	}
	
	public boolean searchAsset(String searchTex) throws Exception {
		logInfo("Inside searchAsset() method.");
			boolean isHeaderCorrect =false;
			waitOnElement("cssSelector", searchResourceTxt);
			inputTextClear("cssSelector", searchResourceTxt);
			inputText("cssSelector", searchResourceTxt, searchTex);
			waitOnElement("cssSelector","#ui-id-1");
		//	waitOnElement("linkText",searchTex);
			clickOnElement("linkText",searchTex);
			waitOnSpinner();
			WebElement header = driver().findElement(By.cssSelector(assetTitleHea));
			String headerName = header.getText().trim();
			System.out.println("name is " + headerName);
			if (headerName.equalsIgnoreCase(searchTex)) {
				logInfo(searchTex + " matches the title in resource preview page.");
				isHeaderCorrect = true;
				
			}
			return isHeaderCorrect;
		
		
	}

	public String getCategoryId(String CatName) throws  Exception {
		String expectedId = null;
		navigate2ManageCategories();
		waitOnElement("cssSelector", ".ic-icon-regular.ic-icon-add");
		List<WebElement> allPlus = driver().findElements(By.cssSelector(".ic-icon-regular.ic-icon-add"));
		for (WebElement x : allPlus) {
			scrollDown("cssSelector", ".ic-icon-regular.ic-icon-add");
			x.click();
		}
		System.out.println("No of plus in the product table = " + allPlus.size());
		List<WebElement> allRows = driver().findElements(By.cssSelector(".col-md-3"));
		System.out.println("No of rows in the product table = " + allRows.size());
		for(int i=1;i<= allRows.size();i++) {
			String part1 = "//*[@id='index_page']/div[2]/div[";
			String part2="]/div[3]";
			String part3= "]/div[6]";
			String prodName = driver().findElement(By.xpath(part1+i+part2)).getText().trim();
			if (prodName.equalsIgnoreCase(CatName)) {
				System.out.println("product found " + prodName);
				expectedId = driver().findElement(By.xpath(part1+i+part3)).getAttribute("data-id");
				System.out.println(expectedId);
				break;
		}
		
		}
		return expectedId;
	
	}
	

	public String getResourceId() throws  Exception {
		waitOnElement("cssSelector", "div.panel-body>div:nth-child(3)>div");
		WebElement resI = driver().findElement(By.cssSelector("div.panel-body>div:nth-child(3)>div"));	
		String expectedResId = resI.getAttribute("data-resource-id");
		System.out.println(expectedResId);
		return expectedResId;
	
	}
	
	
	
	
	
	public String getDefaultView(String Option) throws  Exception {
		logInfo("Inside getDefaultView Method");
		String expectedView= null;
	
		waitOnElement("xpath",lstResourceOptions);
		List<WebElement> el = driver().findElements(By.xpath(lstResourceOptions));
		String before_name = "//*[@id='main-content']/table/tbody/tr[";
		String after_name = "]/td[1]/a";
		String value_tx = "]/td[2]";
		for(int i=1;i<=el.size();i++){
			WebElement ele = driver().findElement(By.xpath(before_name+i+after_name));
			String optionText = ele.getText().trim();
			if(optionText.equalsIgnoreCase(Option)){
				expectedView = driver().findElement(By.xpath(before_name+i+value_tx)).getText().trim();
					break;
			}
		}
	
		return expectedView;
	
	}

	public int  getResourceCount() throws  Exception {
		logInfo("inside getResourceCount() method");
		waitOnElement("cssSelector", rowsCount);
		List<WebElement> rows = driver().findElements(By.cssSelector(rowsCount));
		int rowsCnt = rows.size();
		System.out.println("number of categories are" + rowsCnt);

			return rowsCnt;
	}

	public String modifySettingVal(String settingVal) {
		logInfo("Inside modifySettingVal Method");
		String[] ys1 = settingVal.split(" ");
		String onlyValue= ys1[0];
		System.out.println(onlyValue);
		
		return onlyValue;
	}
	
	public void addNewResourceUserSide(String resName, boolean resPublish, int futueDate) throws Exception {
		logInfo("inside addNewResource() method.");
		System.out.println("inside addNewResource() method.");
		String futDate = getDate(futueDate, "MM/dd/yyyy");
		waitOnElement("cssSelector", addResourceUser);
		clickOnLink("cssSelector", addResourceUser);
		WebElement nam = driver().findElement(By.cssSelector(inputNewResByUserTitle));
		nam.clear();
		nam.sendKeys(resName);
		uploadFile("Image", inputBrowseThumbnailUser);
		inputTextClear("xpath", inputResourceUserTags);
		inputText("xpath", inputResourceUserTags, resourceTagName);
		waitOnSpinner();
		selectAssetsUser("PDF", resourceUserAssetPDF);
		if (resPublish == true) {
			waitOnElement("cssSelector", publishImmed);
			scrollDown("cssSelector", publishImmed);
			WebElement epub = driver().findElement(By.cssSelector(publishImmed));
			epub.click();
			inputTextClear("cssSelector", inputExpiryDate);
			inputText("cssSelector", inputExpiryDate, expiryDate_text);
		}

		waitOnElement("cssSelector", userRLCreate);
		clickOnButton("cssSelector", userRLCreate);
		waitOnSpinner();
		Thread.sleep(10000);
	//	confirmationMessage("My Resource is created");


	}
	public void addAssetUserSide(String assetName, String assetType) throws Exception {
		logInfo("inside addAssetUserSide() method.");
		waitOnElement("partialLinkText", "Upload Assets");
		clickOnLink("partialLinkText", "Upload Assets");
		waitOnSpinner();
		List<WebElement> boxL = driver().findElements(By.xpath(assetAdd));
		int liM = boxL.size();
		System.out.println(liM);
		for (int i = liM; i >= 1; i--) {
			String part1 = "//*[starts-with(@id,'modal-')][";
			String part2 = "]";
			String part3 = "/div/div[1]/div[1]/h1";
			String header = driver().findElement(By.xpath(part1 + i + part2 + part3)).getText().trim();
			System.out.println(header);
			if (header.equalsIgnoreCase("Add Asset")) {
				String assetWinId = driver().findElement(By.xpath(part1 + i + part2)).getAttribute("aria-labelledby");
				System.out.println(assetWinId);
				String[] part = assetWinId.split("-");
				System.out.println(part[3]);
				String assetTitle1 = "//*[@id='pyr_crm_my_resource_resource_assets_attributes_";
				String assetTitle2 = "_title']";
				String assetDesc = "_file_description']";
				String assetfileType = "_file_type']";
				String assetfilePath = "_file_path";
				String assetEmbedCode = "_embed_code']";
				String assetFp1 = "#pyr_crm_my_resource_resource_assets_attributes_";
				String inputAssetPath = assetFp1 + part[3] + assetfilePath;
				String up1 = "//*[@id='div_embed_code_";
				String up2 = "']/a";
				String btn1 = "//*[@id='modal-";
				String btn2 = "']/div/div[1]/div[3]/a[1]";
				waitOnElement("xpath", assetTitle1 + part[3] + assetTitle2);
				inputTextClear("xpath", assetTitle1 + part[3] + assetTitle2);
				inputText("xpath", assetTitle1 + part[3] + assetTitle2, assetName);
				selectFromDropdown("xpath", assetTitle1 + part[3] + assetfileType, "byVisibleText", assetType);
				inputText("xpath", assetTitle1 + part[3] + assetDesc, assetType + " asset description.");
				switch (assetType) {
				case "PDF":
					uploadFile("PDF", inputAssetPath);
					break;
				case "Text":
					uploadFile("Text", inputAssetPath);
					break;
				case "Document":
					uploadFile("Document", inputAssetPath);
					break;
				case "Spreadsheet":
					uploadFile("Excel", inputAssetPath);
					break;
				case "ZIP":
					uploadFile("Zip", inputAssetPath);

					break;
				case "Presentation":
					uploadFile("PPT", inputAssetPath);

					break;
				case "Image":
					uploadFile("Image", inputAssetPath);
					/*
					 * waitOnElement("cssSelector", siteTemp); selectFromDropdown("cssSelector",
					 * siteTemp, "byVisibleText", "Saba"); clickOnElement("cssSelector",
					 * allowDownload); clickOnElement("cssSelector", allowEmail);
					 * clickOnElement("cssSelector", allowShare); clickOnElement("cssSelector",
					 * allowDownload); clickOnElement("cssSelector", allowEmail);
					 * clickOnElement("cssSelector", allowShare)
					 */;
					break;
				case "Video":
					inputTextClear("xpath", assetTitle1 + part[3] + assetEmbedCode);
					inputText("xpath", assetTitle1 + part[3] + assetEmbedCode, embedVid);
					break;
				case "Content Block":
					composeText("xpath", "//iframe[contains(@title,'Rich Text Editor')]", "This is a new asset");
					break;
				default:
					System.out.println("Invalid file type.");
					break;
				}

				clickOnButton("xpath", btn1 + part[3] + btn2);
				waitOnElement("partialLinkText", "Upload Assets");
			
				break;
			}

		}

	}

	public void selectAssetsUser(String assetType, String assetName) throws Exception {
		logInfo("inside selectAssetsUser() method.");
		switch (assetType) {
		case "PDF":
			addAssetUserSide(assetName, "PDF");
			break;
		case "Text":
			addAssetUserSide(assetName, "Text");
			break;
		case "Document":
			addAssetUserSide(assetName, "Document");
			break;
		case "Spreadsheet":
			addAssetUserSide(assetName, "Spreadsheet");
			break;
		case "ZIP":
			addAssetUserSide(assetName, "ZIP");
			break;
		case "Presentation":
			addAssetUserSide(assetName, "Presentation");
			break;
		case "Image":
			addAssetUserSide(assetName, "Image");
			break;
		case "Video":
			addAssetUserSide(assetName, "Video");
			break;
		case "Content Block":
			addAssetUserSide(assetName, "Content Block");
			break;
		default:
			addAssetUserSide(assetName, "PDF");
			break;
		}
	}

	
	public void performActionsFromResourceDropDownUser(String optionName, String resName) throws  Exception {
		logInfo("inside performActionsFromResourceDropDownAdmin() method to Preview the resource.");

		String expTit = prop.getLocatorForEnvironment(appUrl, "resourceLayout");
		if (expTit.equals("list")) {
			performActionsFromResourceDropDownUserListView(optionName, resName);
		}
		else {
			performActionsFromResourceDropDownUserGridView(optionName, resName);
		}
	}
	
	public void performActionsFromResourceDropDownUserListView(String optionName,String resName) throws  Exception {
		logInfo("isndie performActionsFromResourceDropDownUserGridView Method");
		boolean isresourceFound = false;
		boolean isDownloadLinkFound=false;
		waitOnElement("cssSelector", resourcesAdminTab);
		WebElement row = driver().findElement(By.cssSelector(resourcesTabAdmin));
		List<WebElement> rows = row.findElements(By.cssSelector(categoriestotListView));
		int rowsCnt = rows.size();
		for (int i = 2; i <= rowsCnt+1; i++) {
			String res = driver().findElement(By.cssSelector(resPagepart1 + i + resPagename)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(resName)) {
				System.out.println(res);
				switch (optionName) {
		
				case "Edit":
					WebElement editOp = driver().findElement(By.cssSelector(resPagepart1 + i + resmoreOptions));
					editOp.click();
					WebElement editOp1 = driver().findElement(By.cssSelector(resPagepart1 + i + resedit));
					editOp1.click();
					isresourceFound = true;
					break;
					
				case "Delete":
					System.out.println("Inside Delete case");
					WebElement deleteOp = driver().findElement(By.cssSelector(resPagepart1 + i + resmoreOptions));
					deleteOp.click();
					WebElement deleteOp1 = driver().findElement(By.cssSelector(resPagepart1 + i + resdelete));
					deleteOp1.click();
					confirmOK();
					isresourceFound = true;
					break;
					
				case "Clone":
					WebElement cloneOp = driver().findElement(By.cssSelector(resPagepart1 + i + resmoreOptions));
					cloneOp.click();
					WebElement cloneOp1 = driver().findElement(By.cssSelector(resPagepart1 + i + resclone));
					cloneOp1.click();
					isresourceFound = true;
					break;
					
				default:
					System.out.println("Invalid file type.");
					break;
				}

				break;
			}
		}
	
	}
	
	
	
	public void performActionsFromResourceDropDownUserGridView(String optionName,String resName) throws  Exception {
		logInfo("isndie performActionsFromResourceDropDownUserGridView Method");
		boolean isresourceFound = false;
		boolean isDownloadLinkFound=false;
		waitOnElement("cssSelector", resourcesAdminTab);
		WebElement row = driver().findElement(By.cssSelector(resourcesAdminTab));
		List<WebElement> rows = row.findElements(By.cssSelector(resourcesListGridView));
		int rowsCnt = rows.size();
		System.out.println("Resources to view are" + rowsCnt);
		String part1 = "div.thumb-item:nth-child(";
		String name = ")>div>div.main-container-info>span";
		String moreOptions = ")>div>div.more-options>div>button";
		String edit = ")>div>div.more-options>div>ul>li:nth-child(1)>a";
		String clone = ")>div>div.more-options>div>ul>li:nth-child(2)>a";
		String delete = ")>div>div.more-options>div>ul>li:nth-child(3)>a";
	
		for (int i = 1; i <= rowsCnt; i++) {
			String res = driver().findElement(By.cssSelector(part1 + i + name)).getText().trim();
			System.out.println(res);
			if (res.equalsIgnoreCase(resName)) {
				System.out.println(res);
				switch (optionName) {
				
				case "Edit":
					WebElement editOp = driver().findElement(By.cssSelector(part1 + i + moreOptions));
					editOp.click();
					WebElement editOp1 = driver().findElement(By.cssSelector(part1 + i + edit));
					editOp1.click();
					isresourceFound = true;
					break;
					
				case "Delete":
					System.out.println("Inside Delete case");
					WebElement deleteOp = driver().findElement(By.cssSelector(part1 + i + moreOptions));
					deleteOp.click();
					WebElement deleteOp1 = driver().findElement(By.cssSelector(part1 + i + delete));
					deleteOp1.click();
					confirmToOk();
					isresourceFound = true;
					break;
					
				case "Clone":
					WebElement cloneOp = driver().findElement(By.cssSelector(part1 + i + moreOptions));
					cloneOp.click();
					WebElement cloneOp1 = driver().findElement(By.cssSelector(part1 + i + clone));
					cloneOp1.click();
					isresourceFound = true;
					break;
			
				default:
					System.out.println("Invalid file type.");
					break;
				}

				break;
			}
		}
		if (isresourceFound == false) {
			logInfo(resName + " Unable to find the The resource to preview.");
			Assert.assertTrue(isresourceFound, resName + " Unable to find the resource to preview");
	
		}
	
	}
	
	public void selectFacebookIconFromWs_RL() throws Exception {
		logInfo("Entered into selectFacebookIconFromWs_RL() method");
		waitOnElement("cssSelector", facebookIconWsRl);
		clickOnElement("cssSelector", facebookIconWsRl);
	}

	public void selectTwitterIconFromWs_Rl() throws  Exception {
		logInfo("Entered into selectTwitterIconFromWs_Rl() method.");
		waitOnElement("cssSelector", twitterIconWsRl);
		clickOnElement("cssSelector", twitterIconWsRl);

	}
}
