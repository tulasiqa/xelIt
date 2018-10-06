package vibe.training.tests;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.By;
import common.TestBase;
import common.EnvProperties;


public class TrainingMethods extends TestBase {
	EnvProperties prop = new EnvProperties();
	
	public void go2AdminTrainingPage(){
		logInfo("Inside go2AdminTrainingPage() method");
		driver().navigate().to(appUrl+"pyr_core/training_categories");
	}
	
	public void addTrainingCategory(String caterogyName) throws Exception{
		logInfo("Inside addTrainingCategory() method");
		System.out.println("Inside addTrainingCategory() method");
		clickOnLink("linkText", btnAddTrainingCategory);
		waitOnElement("id",inputCategoryName);
		inputText("id", inputCategoryName, caterogyName);
		waitOnElement("xpath",rankDef);
		clickOnElement("xpath", rankDef);
		Thread.sleep(5000);
		waitOnElement("xpath",chkRankAll);
		WebElement rank=driver().findElement(By.xpath(chkRankAll));
		if(rank.isSelected()){
			rank.click();
		}
		selectRankDefs();

		clickOnElement("xpath", marketLang);
		WebElement market=driver().findElement(By.xpath(chkMarketsAll));
		if(market.isSelected()){
			market.click();
		}
		selectMarketLanguages(languageList);
		clickOnElement("xpath", subscriptionPlan);
		WebElement subscription=driver().findElement(By.xpath(chkSubscriptionAll));
		if(subscription.isSelected()){
			subscription.click();
		}
		selectSubscriptionPlan();
		clickOnElement("xpath",btnCreateTrainingCategory);
		logInfo("Training Category has been added successfully.");
	}
	
	
	

	public void selectRankDefs() throws Exception{
		System.out.println("inside selectRankDefs() method");
		logInfo("inside selectRanDefs() method..");
		List<WebElement> allRanks = driver().findElements(By.xpath(trainingCategoryrankDefCheckboxes));
		int cntRanks = allRanks.size();
		System.out.println("Total checks = " +cntRanks );
		String before_rankName = "//*[@id='rank_definition_checkboxes']/div/span[";
		String after_rankName = "]/label";
		String rank_input = "]/label/input";
		int rankList = rankdeflist.length;
		System.out.println("list size =" +rankList);
		for(int i=1;i<=cntRanks;i++){
			for(int j=0;j<=rankList-1;j++){
			WebElement erank = driver().findElement(By.xpath(before_rankName+i+after_rankName));
			String rankName = erank.getText().trim();
			if(rankName.equalsIgnoreCase(rankdeflist[j])){
				WebElement rankSelect = driver().findElement(By.xpath(before_rankName+i+rank_input));
				rankSelect.click();
				System.out.println("Rank Name = " +rankName);
				break;
				
			}
	
			}
	}
	
}
	
	
	
	
	
	public void selectMarketLanguages(String[] marketLanguages) throws Exception{
		System.out.println("inside selectMarketLanguages() method");
		logInfo("inside selectMarketLanguages() method..");
		List<WebElement> allMarkets = driver().findElements(By.xpath(trainingmarketLangCheckboxes));
		int cntMarkets = allMarkets.size();
		System.out.println("Total checks = " +cntMarkets );
		String before_rankName = "//*[@id='market_language_checkboxes']/div/span[";
		String after_rankName = "]/label";
		String rank_input = "]/label/input";
		int marketList = marketLanguages.length;
		System.out.println("list size =" +marketList);
		for(int i=1;i<=cntMarkets;i++){
			for(int j=0;j<=marketList-1;j++){
			WebElement erank = driver().findElement(By.xpath(before_rankName+i+after_rankName));
			String rankName = erank.getText().trim();
			if(rankName.equalsIgnoreCase(marketLanguages[j])){
				WebElement rankSelect = driver().findElement(By.xpath(before_rankName+i+rank_input));
				rankSelect.click();
				System.out.println("Market Name = " +rankName);
				break;
				
			}
	
			}
	}
	
}
	

	public void selectSubscriptionPlan() throws Exception{
		System.out.println("inside selectSubscriptionPlan() method");
		logInfo("inside selectSubscriptionPlan() method..");
		List<WebElement> allMarkets = driver().findElements(By.xpath(trainingSubscriptionPlanCheckboxes));
		int cntMarkets = allMarkets.size();
		System.out.println("Total checks = " +cntMarkets );
		String before_rankName = "//*[@id='subscription_plan_checkboxes']/div/span[";
		String after_rankName = "]/label";
		String rank_input = "]/label/input";
		int subscriptionPlanList = subplanslist.length;
		System.out.println("list size =" +subscriptionPlanList);
		for(int i=1;i<=cntMarkets;i++){
			for(int j=0;j<=subscriptionPlanList-1;j++){
			WebElement erank = driver().findElement(By.xpath(before_rankName+i+after_rankName));
			String rankName = erank.getText().trim();
			if(rankName.equalsIgnoreCase(subplanslist[j])){
				WebElement rankSelect = driver().findElement(By.xpath(before_rankName+i+rank_input));
				rankSelect.click();
				System.out.println("Market Name = " +rankName);
				break;
				
			}
	
			}
	}
	
}
	
	
	public boolean verifyCategoryPresent(String categoryName) throws  Exception{
        logInfo("inside verifyCategoryPresent() method.");
        go2AdminTrainingPage();
        waitOnElement("id",tableTrainingCategories);
        WebElement tblCategories = driver().findElement(By.id(tableTrainingCategories));
        List<WebElement> categories = tblCategories.findElements(By.tagName("a"));
        boolean isCategoryFound = false;
        for(WebElement cat : categories){
			if(cat.getText().equalsIgnoreCase(categoryName)){
				System.out.println("inside ");
				isCategoryFound = true;
				logInfo(categoryName + " category found in training categories page.");
				break;
			}
		}
        
        if(isCategoryFound == false){
            logInfo(categoryName + " category not found in training categories page.");
            Assert.assertTrue(isCategoryFound, categoryName + " category not found in training categories page.");
        }
     return isCategoryFound;
    }
	
	public void go2AddSeriesPage(String categoryName){
		try{
		logInfo("Inside go2AddSeriesPage() method");
		go2AdminTrainingPage();
		waitOnSpinner();
		waitOnElement("id",tableTrainingCategories);
		WebElement element = driver().findElement(By.id(tableTrainingCategories));
		List<WebElement> categories = element.findElements(By.tagName("a"));
		
		for(WebElement cat : categories){
			if(cat.getText().equalsIgnoreCase(categoryName)){
				cat.click();
				waitOnSpinner();
				break;
			}
		}
		logInfo("Entered into Training series page successfully.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addSeriesToTrainingCategory(String seriesName,String description) throws Exception{
		try{
			logInfo("Inside addTrainingSeries() method without dependency.");
			waitOnElement("xpath",btnAddSeries);
			clickOnElement("xpath", btnAddSeries);
			inputText("xpath",trainingSeriesName, seriesName );
			inputText("xpath", trainingSeriesDesc, description);
			selectRadioOrCheckbox("cssSelector", chkPublishArticle);
			clickOnButton("cssSelector", btncreateTraining);
			logInfo("Added Training Series without dependency successfully.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addSeriesToTrainingCategory(String seriesName,String description,String dependency[]) throws Exception{
		try{
			logInfo("Inside addSeriesToTrainingCategory() method with dependency being selected.");
			driver().findElement(By.xpath(btnAddSeries)).click();
			inputText("xpath",trainingSeriesName, seriesName );
			inputText("xpath", trainingSeriesDesc, description);
			selectRadioOrCheckbox("cssSelector", chkPublishArticle);
			Select dependencies = new Select(driver().findElement(By.xpath(trainingSeriesDependency)));
			List<WebElement> options = dependencies.getOptions();
			int oSize = options.size();
			for(int i=0; i < oSize; i++){
				String optionVal=dependencies.getOptions().get(i).getText();
				for(int j=0; j < dependency.length; j++){
					if(optionVal.equalsIgnoreCase(dependency[j].toString())){
						dependencies.selectByVisibleText(dependency[j].toString());
					}
				}
			}
			clickOnButton("cssSelector", createTrainingSeries);
			logInfo("Added Training Series successfully.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean verifySeriesPresent(String seriesName) throws  Exception{
        logInfo("inside verifySeriesPresent() method.");
        waitOnElement("id",tableSeries);
        WebElement tblSeries = driver().findElement(By.id(tableSeries));
        List<WebElement> series = tblSeries.findElements(By.tagName("a"));
        boolean isSeriesFound = false;
               
        for(WebElement se : series){
			if(se.getText().equalsIgnoreCase(seriesName)){
				isSeriesFound = true;
				logInfo(seriesName + " series found in training series page.");
				break;
			}
		}
        
        if(isSeriesFound == false){
            logInfo(seriesName + " series not found in training series page.");
            Assert.assertTrue(isSeriesFound, seriesName + " series not found in training series page.");
        }
     return isSeriesFound;
    }
	
	public void addTraining2Series(String seriesName,String title,String desc,String type,String path){
		try{
			logInfo("Inside addTraining2Series() method");
			WebElement element = driver().findElement(By.id(tableSeries));
			List<WebElement> trainingSeries = element.findElements(By.tagName("a"));
			
			for(WebElement ts : trainingSeries){
				if(ts.getText().equalsIgnoreCase(seriesName)){
					ts.click();
					break;
				}
			}
			clickOnElement("xpath", btnAddTraining);
			inputText("xpath",inputAddVideoTitile, title );
			inputText("xpath",inputAddVideoDesc, desc );
			selectFromDropdown("xpath", selectFileType, "byVisibleText", "PDF"); 
			uploadFile("PDF",selectFilePath);
			clickOnElement("xpath",prop.getLocatorForEnvironment(appUrl,"btncreateTraining"));
			clickOnElement("cssSelector",".breadcrumb>li:nth-child(2)");
			logInfo("Added training to the series successfully.");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addTraining2Series(String seriesName,String title,String desc,String type,String path,String[] dependency) throws Exception{
		try{
			logInfo("Inside addPdf2Series() method");
			WebElement element = driver().findElement(By.id(tableSeries));
			List<WebElement> trainingSeries = element.findElements(By.tagName("a"));
			
			for(WebElement ts : trainingSeries){
				if(ts.getText().equalsIgnoreCase(seriesName)){
					ts.click();
					break;
				}
			}
			clickOnElement("xpath", btnAddTraining);
			inputText("xpath",inputAddVideoTitile, title );
			inputText("xpath",inputAddVideoDesc, desc );
			selectFromDropdown("xpath", selectFileType, "byVisibleText", "PDF");
			uploadFile("PDF",selectFilePath);
			waitOnElement("xpath",trainingDependency);
			Select dependencies = new Select(driver().findElement(By.xpath(trainingDependency)));
			List<WebElement> options = dependencies.getOptions();
			int oSize = options.size();
			
			for(int i=0; i < oSize; i++){
				String optionVal=dependencies.getOptions().get(i).getText();
				for(int j=0; j < dependency.length; j++){
					if(optionVal.equalsIgnoreCase(dependency[j].toString())){
						dependencies.selectByVisibleText(dependency[j].toString());
					}
				}
			}
			clickOnElement("cssSelector",btncreateTraining);
			clickOnElement("cssSelector",".breadcrumb>li:nth-child(2)");
			logInfo("Added training to the series with dependency successfully.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean verifyTrainingPresent(String trainingTitle) throws  Exception{
        logInfo("inside verifyTrainingPresent() method.");
        System.out.println("inside verifyTrainingPresent() method.");
        waitOnElement("id",tableTrainings);
        WebElement tblTrainings = driver().findElement(By.id(tableTrainings));
        List<WebElement> trainings = tblTrainings.findElements(By.tagName("a"));
        boolean isTrainingsFound = false;
               
        for(WebElement tr : trainings){
			if(tr.getText().equalsIgnoreCase(trainingTitle)){
				isTrainingsFound = true;
				logInfo(trainingTitle + " training found in the training page.");
				break;
			}
		}
        
        if(isTrainingsFound == false){
            logInfo(trainingTitle + " training not found in training series page.");
            Assert.assertTrue(isTrainingsFound, trainingTitle + " training not found in the training page.");
        }
     return isTrainingsFound;
    }
	
	public void updateTraining(String trainingTitle, String updatedTrainingTitle) throws  Exception{
        logInfo("inside updateTraining() method.");
        System.out.println("inside updateTraining() method.");
        waitOnElement("id",tableTrainings);
        List<WebElement> lstTraining = driver().findElements(By.xpath("//*[contains(@id,'training_video_id')]/div/div[1]/span/a"));
        String before = "//*[contains(@id,'training_video_id')][";
        String after = "]/div/div[1]/span/a";
        if(lstTraining.size()>0){
        	for(int i=1;i<=lstTraining.size();i++){
        		WebElement el = driver().findElement(By.xpath(before+i+after));
        		if(el.getText().equalsIgnoreCase(trainingTitle)){
        			String before_edit = "//*[contains(@id,'training_video_id')][";
        			String after_edit = "]/div/div[2]/a[1]/i";
        			WebElement editTraining = driver().findElement(By.xpath(before_edit+i+after_edit));
        			editTraining.click();
        			waitOnElement("xpath",inputAddVideoTitile);
        			inputTextClear("xpath", inputAddVideoTitile);
        			inputText("xpath", inputAddVideoTitile, updatedTrainingTitle);
        			waitOnElement("xpath",prop.getLocatorForEnvironment(appUrl,"btnUpdateTraining"));
        			clickOnElement("xpath",prop.getLocatorForEnvironment(appUrl,"btnUpdateTraining"));
        			confirmationMessage("Training has been updated successfully.");
        		}
        	}
        }
    }
	
	public void back2Series() throws Exception{
		 logInfo("inside back2Series() method");
		 System.out.println("inside back2Series() method");
		 waitOnElement("xpath", back2ListOfSeries);
		 clickOnElement("xpath", back2ListOfSeries);
	}
	
	
	public void go2TrainingUsers() throws IOException, Exception{
		logInfo("Inside go2TrainingUsers() method.");
		System.out.println("Inside go2TrainingUsers() method.");
		driver().navigate().to(appUrl+"pyr_core/training_users");
	}
	
	public boolean verifyCategoryPresentAtUser(String categoryName) throws Exception{
		logInfo("Inside verifyCategoryPresentAtUser() method.");
		System.out.println("Inside verifyCategoryPresentAtUser() method.");
		waitOnElement("xpath",categoryPanel);
		WebElement element = driver().findElement(By.xpath(categoryPanel));
		List<WebElement> trainingCategories = element.findElements(By.tagName("a"));
		boolean isCategoryPresent = false;
		for(WebElement tc : trainingCategories){
			if(tc.getText().equalsIgnoreCase(categoryName)){
				isCategoryPresent = true;
				break;
			}
		}
		return isCategoryPresent;
	}
	
	public void selectCategory(String categoryName) throws  Exception {
		logInfo("Inside selectCategory() method.");
		System.out.println("Inside selectCategory() method.");
		waitOnElement("xpath",categoryPanel);
		WebElement element = driver().findElement(By.xpath(categoryPanel));
		List<WebElement> trainingCategories = element.findElements(By.tagName("a"));
		
		for(WebElement tc : trainingCategories){
			if(tc.getText().equalsIgnoreCase(categoryName)){
				tc.click();
				break;
			}
		}
	}

	public boolean verifySeriesAtUser(String seriesName) throws Exception{
		logInfo("Inside verifySeriesAtUser() method.");
		System.out.println("Inside verifySeriesAtUser() method.");
		boolean isSeriesPresent = false;
		waitOnSpinner();
		waitOnElement("xpath",listSeries);
		List<WebElement> seriesList = driver().findElements(By.xpath(listSeries));
		String before = "//*[@id='main-content']/div/div[3]/div[2]/div[";
		String after = "]/div[2]/a";
		
		for(int i=1; i <= seriesList.size(); i++){
			waitOnElement("xpath",before+i+after);
			WebElement eSeriesName = driver().findElement(By.xpath(before+i+after));
			String series = eSeriesName.getText().trim();
			System.out.println(series);
			if(series.equalsIgnoreCase(seriesName)){
				isSeriesPresent = true;
				break;
			}
			
		}
		return isSeriesPresent;
	}
	
	
	
	public boolean verifyTrainingAtUser(String trainingName) throws Exception{
		logInfo("inside verifyTrainingAtUser Method");
		waitOnElement("xpath",listSeries);
		List<WebElement> trainingsList = driver().findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[2]/div/div[2]/div[";
		String after = "]/a";
		boolean isTrainingPresent = false;
		for(int i=1; i <= trainingsList.size(); i++){
			System.out.println("inside for series loop");
			waitOnElement("xpath",before+i+after);
			WebElement eTrainingName = driver().findElement(By.xpath(before+i+after));
			String training = eTrainingName.getText().trim();
			if(training.equalsIgnoreCase(trainingName)){
				System.out.println("inside series if cond");
				isTrainingPresent = true;
				break;
			}
			else{
				isTrainingPresent = false;
			}
		}
		
		return isTrainingPresent;
	}
	
	public void checkTrainingDependencyError(String seriesName, String trainingName, String dependencySeries) throws Exception{
		System.out.println("entered checkTrainingDependency");
		WebElement tblSeries = driver().findElement(By.xpath(seriesPanel));
		List<WebElement> seriesList = tblSeries.findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[2]/div[";
		String after = "]/div[2]/a";
		String before_training = "//*[@id='main-content']/div/div[3]/div[2]/div[";
		String after_training = "]/div[2]/div[2]/a";
		for(int i=1; i <= seriesList.size(); i++){
			WebElement eSeriesName = driver().findElement(By.xpath(before+i+after));
			String series = eSeriesName.getText().trim();
			if(series.equalsIgnoreCase(seriesName)){
				WebElement training = driver().findElement(By.xpath(before_training+i+after_training));
				if(training.getText().trim().equalsIgnoreCase(trainingName)){
					training.click();
					confirmationMessage("Please Complete the dependent series: " + dependencySeries);
				}
				break;
			}
		}
	}
	
	public void completeTraining(String trainingName) throws Exception{
		logInfo("Inside completeTraining() method.");
		waitOnElement("xpath", seriesPanel);
		WebElement tblTrainings = driver().findElement(By.xpath(seriesPanel));
		List<WebElement> trainingsList = tblTrainings.findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[2]/div[";
		String after = "]/div[2]/div/a";
		for(int i=1; i <= trainingsList.size(); i++){
			WebElement eTrainingName = driver().findElement(By.xpath(before+i+after));
			String training = eTrainingName.getText().trim();
			if(training.equalsIgnoreCase(trainingName.trim())){
				eTrainingName.click();
				break;
			}
		}
		waitOnElement("cssSelector",prop.getLocatorForEnvironment(appUrl,"closeTrainingWindow") );
		clickOnElement("cssSelector",prop.getLocatorForEnvironment(appUrl,"closeTrainingWindow") );
		WebElement verifyTrainingChecked = driver().findElement(By.cssSelector("input[checked='checked']"));
		boolean isChecked = verifyTrainingChecked.isSelected();
		if(isChecked){
			Assert.assertTrue(isChecked, "Able to complete the training");
		}
		else{
			Assert.assertTrue(isChecked, "unable to complete the training");
		}
	}
	
	public void completeAllTrainings() throws Exception{
		logInfo("Inside completeAllTrainings() method.");
		waitOnElement("xpath",seriesPanel);
		WebElement tblSeries = driver().findElement(By.xpath(seriesPanel));
		waitOnElement("xpath",listSeries);
		List<WebElement> lstSeries = tblSeries.findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[2]/div[";
		String after = "]/div[2]/div/a";
		for(int i=1; i <= lstSeries.size(); i++){
			//Thread.sleep(5000);
			waitOnElement("xpath",before+i+after);
			List<WebElement> trainings = driver().findElements(By.xpath(before+i+after));
			String before1 = "//*[@id='main-content']/div/div[3]/div[2]/div[";
			String middle = "]/div[2]/div[";
			String after1 = "]/a";
			for(int j=1; j <= trainings.size(); j++){
				//Thread.sleep(10000);
				waitOnElement("xpath",before1+i+middle+j+after1);
				WebElement tr = driver().findElement(By.xpath(before1+i+middle+j+after1));
				System.out.println(tr.getText());
				tr.click();
				waitOnSpinner();
				waitOnElement("cssSelector",prop.getLocatorForEnvironment(appUrl,"closeTrainingWindow") );
				clickOnElement("cssSelector",prop.getLocatorForEnvironment(appUrl,"closeTrainingWindow") );
				waitOnSpinner();
			}
		}
		logInfo("Successfully completed multiple training series.");
		
	}
	
	public boolean verifyAllTrainingsCompleted() throws  Exception{
		logInfo("Inside verifyAllTrainingsCompleted() method.");
		waitOnElement("xpath",seriesPanel);
		WebElement tblSeries = driver().findElement(By.xpath(seriesPanel));
		waitOnElement("xpath",listSeries);
		List<WebElement> list = tblSeries.findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[2]/div[";
		String after = "]/div[2]/div/a";
		boolean isVerifiedAllTrainings = false;
		for(int i=1; i <= tblSeries.findElements((By.xpath(listSeries))).size(); i++){
			waitOnElement("xpath",before+i+after);
			List<WebElement> trainings = driver().findElements(By.xpath(before+i+after));
			String before1 = "//*[@id='main-content']/div/div[3]/div[2]/div[";
			String middle = "]/div[2]/div[";
			String after1 = "]/input";
			for(int j=1; j <= trainings.size(); j++){
				waitOnElement("xpath",before1+i+middle+j+after1);
				WebElement tr = driver().findElement(By.xpath(before1+i+middle+j+after1));
				if(tr.isSelected()){
					isVerifiedAllTrainings = true;
				}
				else{
					isVerifiedAllTrainings = false;
				}
			}
		}
		logInfo("Successfully verified the completion of all training series of a category.");
		System.out.println(isVerifiedAllTrainings);
		return isVerifiedAllTrainings;
	}
	
	public void deleteCategory(String trainingCategory) throws  Exception{
		logInfo("Inside deleteCategory() method.");
		System.out.println("Inside deleteCategory() method.");
		boolean isCategoryFound = true;
		waitOnElement("xpath","//*[@id='training_categories_panel_list']/div");
		List<WebElement> categories = driver().findElements(By.xpath("//*[@id='training_categories_panel_list']/div"));
		
		String before = "//*[@id='training_categories_panel_list']/div[";
		String after = "]/div/div[1]/span/a";
		
		String beforeDelete = "//*[@id='training_categories_panel_list']/div[";
		String afterDelete = "]/div/div[2]/a[3]";
		
		for(int i=1;i<=categories.size();i++){
			waitOnElement("xpath",before+i+after);
			WebElement category = driver().findElement(By.xpath(before+i+after));
			if(category.getText().trim().equalsIgnoreCase(trainingCategory)){
				isCategoryFound = true;
				System.out.println("Trianing category found "+trainingCategory);
				WebElement el = driver().findElement(By.xpath(beforeDelete+i+afterDelete));
				el.click();
				confirmOK();
				confirmationMessage("Training Category has been deleted successfully.");
				break;
			}
		}
	
	}

	/*Tupperware scripts......*/
	
	public void vtup_addTraining2Series(String seriesName,String title,String desc,String type,String path){
		try{
			logInfo("Inside addTraining2Series() method");
			WebElement element = driver().findElement(By.id(tableSeries));
			List<WebElement> trainingSeries = element.findElements(By.tagName("a"));
			
			for(WebElement ts : trainingSeries){
				if(ts.getText().equalsIgnoreCase(seriesName)){
					ts.click();
					break;
				}
			}
			clickOnElement("xpath", btnAddTraining);
			inputText("xpath",inputAddVideoTitile, title );
			inputText("xpath",inputAddVideoDesc, desc );
			
			
			selectFromDropdown("xpath", selectFileType, "byVisibleText", "PDF"); //using dropdown method from testbase ganga
		//	Select pdf = new Select(driver().findElement(By.id(selectFileType)));
		//	pdf.selectByVisibleText(type);
			
			
			
			uploadFile("PDF",selectFilePath);
			visibilityAccess_MarketSegments();
			visibilityAccess_RegionNumbers();
			visibilityAccess_EliteStatuses();
			visibilityAccess_LenthOfTime();
			/*driver().findElement(By.cssSelector(selectFilePath)).click();
			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_PDF.exe");
			Thread.sleep(10000);*/
			System.out.println(driver().findElement(By.xpath(prop.getLocatorForEnvironment(appUrl,"btnCreateTrainingVideo"))).getText());
			clickOnElement("xpath", prop.getLocatorForEnvironment(appUrl,"btnCreateTrainingVideo"));
			clickOnElement("xpath", backToSeries);
			logInfo("Added training to the series successfully.");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public void visibilityAccess_MarketSegments() throws InterruptedException{
		logInfo("inside visibilityAccess_MarketSegments method");
		clickOnElement("xpath",visibilitySettingsIcon);
		WebElement all = driver().findElement(By.cssSelector("#MarketSegmentsAll"));
		if(all.isSelected()){
			all.click();
			Thread.sleep(2000);
			}	

		 List<WebElement> marketSegments = driver().findElements(By.xpath(marketsegmentsCheckboxes));
		int  totalCheckbox = marketSegments.size();
		System.out.println(totalCheckbox +"is the number of available checkboxes in marketSegments section");
		int marketSegmentsList = marketsegments.length;
		System.out.println("list size =" +marketSegmentsList);
		for(int i=1;i<=totalCheckbox;i++){
			for(int j=0;j<=marketSegmentsList-1;j++){
		String xpath1 = "//*[@id='visibilty_settings']/div[1]/div[2]/div/span[";
		String xpath2 = "]/label";
		//String xpath3  = "/label";
		WebElement clickChekcbox = driver().findElement(By.xpath(xpath1+i+xpath2));
		String marketSegmentLabels = clickChekcbox.getText().trim();
		System.out.println("Label Name = " +marketSegmentLabels);
		if(marketSegmentLabels.equalsIgnoreCase(marketsegments[j] )){ 

		WebElement clickChekcboxes = driver().findElement(By.xpath(xpath1+i+xpath2));
		clickChekcboxes.click();
		logInfo("Selected all checkboxes");
		System.out.print("selected all checkboxes");
				}
			}	
		}
	
	}

	public void visibilityAccess_RegionNumbers() throws InterruptedException{
		logInfo("inside visibilityAccess_RegionNumbers method");
		WebElement all = driver().findElement(By.cssSelector("#RegionNumbersAll"));
		if(all.isSelected()){
			all.click();
			Thread.sleep(2000);
		}	
		List<WebElement> regionNumbers = driver().findElements(By.xpath(regionNumberCheckboxes));
		int  totalCheckbox = regionNumbers.size();
		System.out.println(totalCheckbox +"is the number of available checkboxes in marketSegments section");
		int regionNumbersList = regionNumbersli.length;
		for(int i=1;i<=totalCheckbox;i++){
			for(int j=0;j<=regionNumbersList-1;j++){
				String xpath1 = "//*[@id='visibilty_settings']/div[2]/div[2]/div/span[";
				String xpath2 = "]/label";
				WebElement clickChekcbox = driver().findElement(By.xpath(xpath1+i+xpath2));
				String regionNumbersLabels = clickChekcbox.getText().trim();
				System.out.println("Label Name = " +regionNumbersLabels);
				if(regionNumbersLabels.equalsIgnoreCase(regionNumbersli[j] )){ 

						WebElement clickChekcboxes = driver().findElement(By.xpath(xpath1+i+xpath2));
						clickChekcboxes.click();
						logInfo("Selected all checkboxes");
						System.out.print("selected all checkboxes");
					}
			}	
		}

	}

	public void visibilityAccess_EliteStatuses() throws InterruptedException{
		logInfo("inside visibilityAccess_EliteStatuses method");
		WebElement all = driver().findElement(By.cssSelector("#EliteStatusesAll"));
		if(all.isSelected()){
			all.click();
			Thread.sleep(2000);
		}	
		List<WebElement> regionNumbers = driver().findElements(By.xpath(eliteStatusesCheckboxes));
		int  totalCheckbox = regionNumbers.size();
		System.out.println(totalCheckbox +"is the number of available checkboxes in marketSegments section");
		int eliteList = eliteStatuses.length;
		for(int i=1;i<=totalCheckbox;i++){
			for(int j=0;j<=eliteList-1;j++){
				String xpath1 = "//*[@id='visibilty_settings']/div[3]/div[2]/div/span[";
				String xpath2 = "]/label";
				WebElement clickChekcbox = driver().findElement(By.xpath(xpath1+i+xpath2));
				String elitestatusLabels = clickChekcbox.getText().trim();
				System.out.println("Label Name = " +elitestatusLabels);
				if(elitestatusLabels.equalsIgnoreCase(eliteStatuses[j] )){ 

						WebElement clickChekcboxes = driver().findElement(By.xpath(xpath1+i+xpath2));
						clickChekcboxes.click();
						logInfo("Selected all checkboxes");
						System.out.print("selected all checkboxes");
					}
			}	
		}

	}

	public void visibilityAccess_LenthOfTime() throws InterruptedException{
		logInfo("inside visibilityAccess_LenthOfTime method");
		WebElement all = driver().findElement(By.cssSelector("#LengthofTimeAll"));
		if(all.isSelected()){
			all.click();
			Thread.sleep(2000);
		}	
		List<WebElement> lengthof = driver().findElements(By.xpath(lengthOfTimeCheckboxes));
		int  totalCheckbox = lengthof.size();
		System.out.println(totalCheckbox +"is the number of available checkboxes in marketSegments section");
		int lengthofTimeList = lengthOfTime.length;
		for(int i=1;i<=totalCheckbox;i++){
			for(int j=0;j<=lengthofTimeList-1;j++){
				String xpath1 = "//*[@id='visibilty_settings']/div[3]/div[2]/div/span[";
				String xpath2 = "]/label";
				WebElement clickChekcbox = driver().findElement(By.xpath(xpath1+i+xpath2));
				String lengthOftimeLabel = clickChekcbox.getText().trim();
				System.out.println("Label Name = " +lengthOftimeLabel);
				if(lengthOftimeLabel.equalsIgnoreCase(lengthOfTime[j] )){ 

						WebElement clickChekcboxes = driver().findElement(By.xpath(xpath1+i+xpath2));
						clickChekcboxes.click();
						logInfo("Selected all checkboxes");
						System.out.print("selected all checkboxes");
					}
			}	
		}

	}
	
	public void addComments2Training(String trainingName,String trainingComment) throws Exception{
		logInfo("Inside addComments2Training() method.");
		System.out.println("Inside addComments2Training() method.");
		waitOnElement("xpath", seriesPanel);
		WebElement tblTrainings = driver().findElement(By.xpath(seriesPanel));
		List<WebElement> trainingsList = tblTrainings.findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[2]/div[";
		String after = "]/div[2]/div/a";
		for(int i=1; i <= trainingsList.size(); i++){
			WebElement eTrainingName = driver().findElement(By.xpath(before+i+after));
			String training = eTrainingName.getText().trim();
			if(training.equalsIgnoreCase(trainingName.trim())){
				eTrainingName.click();
				break;
			}
		}
		waitOnElement("xpath",inputTrainingComments );
		inputText("xpath",inputTrainingComments,trainingComment);
		waitOnSpinner();
		waitOnElement("xpath",btnTrainingComment);
		clickOnElement("xpath",btnTrainingComment);
	}	
	
	public boolean verifyTrainingComments(String comment) throws  Exception{
		logInfo("Inside verifyTrainingComments() method.");
		System.out.println("Inside verifyTrainingComments() method.");
		boolean isCommentFound = false;
		waitOnElement("cssSelector",lstTranComments);
		List<WebElement> comments = driver().findElements(By.cssSelector(lstTranComments));
		String before = ".status_messages>.media:nth-child(";
		String after = ")>div>.activity_content";
		if(comments.size()>0){
			for(int i=1;i<=comments.size();i++){
				waitOnElement("cssSelector",before+i+after);
				WebElement el = driver().findElement(By.cssSelector(before+i+after));
				if(el.getText().equalsIgnoreCase(comment)){
					isCommentFound = true;
					break;
				}
			}
		}
		return isCommentFound;
	}
	
	public boolean viewTraining(String trainingName) throws Exception{
		logInfo("Inside viewTraining() method.");
		System.out.println("Inside viewTraining() method.");
		boolean isTrainingFound = false;
		waitOnElement("xpath", seriesPanel);
		WebElement tblTrainings = driver().findElement(By.xpath(seriesPanel));
		List<WebElement> trainingsList = tblTrainings.findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[2]/div[";
		String after = "]/div[2]/div/a";
		for(int i=1; i <= trainingsList.size(); i++){
			WebElement eTrainingName = driver().findElement(By.xpath(before+i+after));
			String training = eTrainingName.getText().trim();
			if(training.equalsIgnoreCase(trainingName.trim())){
				isTrainingFound = true;
				eTrainingName.click();
				break;
			}
		}
		return isTrainingFound;
	}
	
	public boolean likeTrainingComment(String comment) throws  Exception{
		logInfo("Inside likeTrainingComments() method.");
		System.out.println("Inside likeTrainingComments() method.");
		boolean isLiked = false;
		waitOnElement("cssSelector",lstTranComments);
		List<WebElement> comments = driver().findElements(By.cssSelector(lstTranComments));
		String before = ".status_messages>.media:nth-child(";
		String after = ")>div>.activity_content";
		String before_like = "//*[@id='page']/div[7]/div/div/div[3]/div[2]/section[2]/div[2]/div[";
		String after_like = "]/div/div[2]/span[1]/a"; 
		String after_liked = "]/div/div[2]/span[1]/a/a"; 
		
		if(comments.size()>0){
			for(int i=1;i<=comments.size();i++){
				WebElement el = driver().findElement(By.cssSelector(before+i+after));
				if(el.getText().equalsIgnoreCase(comment)){
					WebElement likeComment = driver().findElement(By.xpath(before_like+i+after_like));
					String beforeLike = likeComment.getText().trim();
					likeComment.click();
					WebElement likedComment = driver().findElement(By.xpath(before_like+i+after_liked));
					String afterLike = likedComment.getText().trim();
					if(!beforeLike.equalsIgnoreCase(afterLike)){
						isLiked = true;
					}
					waitOnElement("cssSelector",prop.getLocatorForEnvironment(appUrl,"closeTrainingWindow") );
					clickOnElement("cssSelector",prop.getLocatorForEnvironment(appUrl,"closeTrainingWindow") );
					break;
				}
			}
		}
		return isLiked;
	}
	
	public void deleteTrainingComment(String comment) throws  Exception{
		logInfo("Inside deleteTrainingComment() method.");
		System.out.println("Inside deleteTrainingComment() method.");
		boolean isCommentFound = false;
		waitOnElement("cssSelector",lstTranComments);
		List<WebElement> comments = driver().findElements(By.cssSelector(lstTranComments));
		String before = ".status_messages>.media:nth-child(";
		String after = ")>div>.activity_content";
		String before_delete = "//*[@id='page']/div[3]/div/div/div[3]/div[2]/section[2]/div[2]/div[";
		String after_delete = "]/div/div[2]/span[3]/a"; 
				
		if(comments.size()>0){
			for(int i=1;i<=comments.size();i++){
				WebElement el = driver().findElement(By.cssSelector(before+i+after));
				if(el.getText().equalsIgnoreCase(comment)){
					WebElement deleteComment = driver().findElement(By.xpath(before_delete+i+after_delete));
					deleteComment.click();
					isCommentFound = verifyTrainingComments(comment);
					if(isCommentFound){
						Assert.assertFalse(isCommentFound, "Able to find the comment after deletion "+comment);
					}
					waitOnElement("cssSelector",prop.getLocatorForEnvironment(appUrl,"closeTrainingWindow") );
					clickOnElement("cssSelector",prop.getLocatorForEnvironment(appUrl,"closeTrainingWindow") );
					break;
				}
			}
		}
	}
	
	public void updateTrainingCategory(String categoryName,String updatedCategoryName) throws  Exception{
        logInfo("inside updateTrainingCategory() method.");
        System.out.println("inside updateTrainingCategory() method.");
        List<WebElement> trainingCatgories = driver().findElements(By.xpath(lstTrainingCategories));
        String before = "//*[contains(@id,'training_category_id')][";
        String after = "]/div/div[1]/span/a";
        String before_edit = "]/div/div[2]/a[1]";
        
        if(trainingCatgories.size()>0){
        	for(int i=1;i<=trainingCatgories.size();i++){
        		WebElement category = driver().findElement(By.xpath(before+i+after));
        		if(category.getText().equalsIgnoreCase(categoryName)){
        			WebElement editCategory = driver().findElement(By.xpath(before+i+before_edit));
        			editCategory.click();
        			waitOnElement("id",inputCategoryName);
        			inputTextClear("id",inputCategoryName);
        			inputText("id", inputCategoryName, updatedCategoryName);
        			clickOnElement("cssSelector",btnUpdateTrainingCat);
        			confirmationMessage("Training Category has been updated successfully.");
        			break;
        		}
        	}
        }
    }
	
	public void updateTrainingSeries(String seriesName,String updatedSeriesName) throws  Exception{
        logInfo("inside updateTrainingSeries() method.");
        System.out.println("inside updateTrainingSeries() method.");
        List<WebElement> seriesList = driver().findElements(By.xpath(lstTrainingSeries));
        if(seriesList.size()>0){
        	String before = "//*[contains(@id,'training_series_id')][";
        	String after = "]/div/h4/div/div[2]/span/a";
        	for(int i=1;i<=seriesList.size();i++){
        		WebElement el = driver().findElement(By.xpath(before+i+after));
        		if(el.getText().trim().equalsIgnoreCase(seriesName)){
        			String before_edit = ".//*[contains(@id,'training_series_id')][";
                	String after_edit = "]/div/h4/div/div[3]/a[1]/i";
                	WebElement editSeries = driver().findElement(By.xpath(before_edit+i+after_edit));
                	editSeries.click();
                	waitOnElement("xpath",trainingSeriesName);
                	inputTextClear("xpath",trainingSeriesName);
                	inputText("xpath",trainingSeriesName,updatedSeriesName);
                	waitOnElement("xpath",btnUpdateTrainingSeries);
                	clickOnElement("xpath",btnUpdateTrainingSeries);
                	confirmationMessage("Training series has been updated successfully.");
        			break;
        		}
        	}
        	
        }
    }
	
	public boolean viewTrainingCategory(String categoryName) throws  Exception{
		boolean isCategoryFound = false;
        logInfo("inside viewTrainingCategory() method.");
        System.out.println("inside viewTrainingCategory() method.");
        List<WebElement> trainingCatgories = driver().findElements(By.xpath(lstTrainingCategories));
        String before = "//*[contains(@id,'training_category_id')][";
        String after = "]/div/div[1]/span/a";
        String before_view = "]/div/div[2]/a[2]";
        
        if(trainingCatgories.size()>0){
        	for(int i=1;i<=trainingCatgories.size();i++){
        		WebElement category = driver().findElement(By.xpath(before+i+after));
        		if(category.getText().equalsIgnoreCase(categoryName)){
        			WebElement viewCategory = driver().findElement(By.xpath(before+i+before_view));
        			viewCategory.click();
        			waitOnElement("xpath",lblViewTrainingCategory);
        			WebElement el = driver().findElement(By.xpath(lblViewTrainingCategory));
        			if(el.getText().trim().equalsIgnoreCase(categoryName)){
        				isCategoryFound=true;
        			}
        			break;
        		}
        	}
        }
        return isCategoryFound;
    }
	
	public boolean viewTrainingSeries(String seriesName) throws  Exception{
		boolean isSeriesViewed = false;
        logInfo("inside viewTrainingSeries() method.");
        System.out.println("inside viewTrainingSeries() method.");
        List<WebElement> seriesList = driver().findElements(By.xpath(lstTrainingSeries));
        if(seriesList.size()>0){
        	String before = "//*[contains(@id,'training_series_id')][";
        	String after = "]/div/h4/div/div[2]/span/a";
        	for(int i=1;i<=seriesList.size();i++){
        		WebElement el = driver().findElement(By.xpath(before+i+after));
        		if(el.getText().trim().equalsIgnoreCase(seriesName)){
        			String before_view = "//*[contains(@id,'training_series_id')][";
                	String after_view = "]/div/h4/div/div[3]/a[2]/i";
                	WebElement viewSeries = driver().findElement(By.xpath(before_view+i+after_view));
                	viewSeries.click();
                	waitOnElement("xpath","//*[@id='main-content']");
                	WebElement series = driver().findElement(By.xpath("//*[@id='main-content']"));
                	if(series.getText().contains(seriesName)){
                		isSeriesViewed = true;
                	}
        			break;
        		}
        	}
        	
        }
        return isSeriesViewed;
    }
	
	public boolean reorderTrainingCategory(String categoryName) throws  Exception{
		boolean isCategoryFound = false;
        logInfo("inside reorderTrainingCategory() method.");
        System.out.println("inside reorderTrainingCategory() method.");
        List<WebElement> trainingCatgories = driver().findElements(By.xpath(lstTrainingCategories));
        String before = "//*[contains(@id,'training_category_id')][";
        String after = "]/div/div[1]/span/a";
        String before_reorder = "]/div/div[2]/a[3]";
        
        if(trainingCatgories.size()>0){
        	for(int i=1;i<=trainingCatgories.size();i++){
        		WebElement category = driver().findElement(By.xpath(before+i+after));
        		if(category.getText().equalsIgnoreCase(categoryName)){
        			WebElement viewCategory = driver().findElement(By.xpath(before+i+before_reorder));
        			viewCategory.click();
        			//Need to work on this after drag and drop works
        			break;
        		}
        	}
        }
        return isCategoryFound;
    }
	
	public void deleteTrainingSeries(String seriesName) throws  Exception{
        logInfo("inside deleteTrainingSeries() method.");
        System.out.println("inside deleteTrainingSeries() method.");
        List<WebElement> seriesList = driver().findElements(By.xpath(lstTrainingSeries));
        if(seriesList.size()>0){
        	String before = "//*[contains(@id,'training_series_id')][";
        	String after = "]/div/h4/div/div[2]/span/a";
        	for(int i=1;i<=seriesList.size();i++){
        		WebElement el = driver().findElement(By.xpath(before+i+after));
        		if(el.getText().trim().equalsIgnoreCase(seriesName)){
        			String before_delete = "//*[contains(@id,'training_series_id')][";
                	String after_delete = "]/div/h4/div/div[3]/a[3]/i";
                	WebElement viewSeries = driver().findElement(By.xpath(before_delete+i+after_delete));
                	viewSeries.click();
                	confirmationMessage("Training Series has been successfully deleted.");
        			break;
        		}
        	}
        	
        }
    }
	
	public void setTrainingCommentSettings() throws  Exception{
		logInfo("inside setTrainingCommentSettings() method.");
        System.out.println("inside setTrainingCommentSettings() method.");
        waitOnElement("xpath",btnTrainingSettings);
        clickOnElement("xpath",btnTrainingSettings);
        waitOnElement("xpath",ddlTrainingComments);
        selectFromDropdown("xpath", ddlTrainingComments, "byVisibleText", "On");
        clickOnElement("xpath",btnUpdateTrainingSettings);
        confirmationMessage("Training Settings updated successfully.");
    }
	
	public boolean clickTrainingSeries(String seriesName) throws  Exception{
        logInfo("inside clickTrainingSeries() method.");
        System.out.println("inside clickTrainingSeries() method.");
        waitOnElement("id",tableSeries);
        WebElement tblSeries = driver().findElement(By.id(tableSeries));
        List<WebElement> series = tblSeries.findElements(By.tagName("a"));
        boolean isSeriesFound = false;
               
        for(WebElement se : series){
			if(se.getText().equalsIgnoreCase(seriesName)){
				isSeriesFound = true;
				se.click();
				waitOnSpinner();
				logInfo(seriesName + " series found in training series page.");
				break;
			}
		}
        
        if(isSeriesFound == false){
            logInfo(seriesName + " series not found in training series page.");
            Assert.assertTrue(isSeriesFound, seriesName + " series not found in training series page.");
        }
     return isSeriesFound;
    }
	
	public boolean viewTrainingVideo(String trainingTitle) throws  Exception{
        logInfo("inside viewTrainingVideo() method.");
        System.out.println("inside viewTrainingVideo() method.");
        boolean isTrainingFound = false;
        waitOnElement("id",tableTrainings);
        List<WebElement> lstTraining = driver().findElements(By.xpath("//*[contains(@id,'training_video_id')]/div/div[1]/span/a"));
        String before = "//*[contains(@id,'training_video_id')][";
        String after = "]/div/div[1]/span/a";
        if(lstTraining.size()>0){
        	for(int i=1;i<=lstTraining.size();i++){
        		WebElement el = driver().findElement(By.xpath(before+i+after));
           		if(el.getText().equalsIgnoreCase(trainingTitle)){
        			String before_view = "//*[contains(@id,'training_video_id')][";
        			String after_view = "]/div/div[2]/a[2]/i";
        			WebElement viewTraining = driver().findElement(By.xpath(before_view+i+after_view));
        			viewTraining.click();
        			waitOnElement("xpath","//*[@id='main-content']");
        			WebElement el1 = driver().findElement(By.xpath("//*[@id='main-content']"));
        			if(el1.getText().trim().contains(trainingTitle)){
        				isTrainingFound = true;
        				break;
        			}
        			
        		}
        	}
        }
        return isTrainingFound;
    }
	
	public void deleteTrainingVideo(String trainingTitle) throws  Exception{
        logInfo("inside deleteTrainingVideo() method.");
        System.out.println("inside deleteTrainingVideo() method.");
        boolean isTrainingFound = false;
        waitOnElement("id",tableTrainings);
        List<WebElement> lstTraining = driver().findElements(By.xpath("//*[contains(@id,'training_video_id')]/div/div[1]/span/a"));
        String before = "//*[contains(@id,'training_video_id')][";
        String after = "]/div/div[1]/span/a";
        if(lstTraining.size()>0){
        	for(int i=1;i<=lstTraining.size();i++){
        		WebElement el = driver().findElement(By.xpath(before+i+after));
        		if(el.getText().equalsIgnoreCase(trainingTitle)){
        			String before_view = "//*[contains(@id,'training_video_id')][";
        			String after_view = "]/div/div[2]/a[3]/i";
        			WebElement viewTraining = driver().findElement(By.xpath(before_view+i+after_view));
        			viewTraining.click();
        			confirmOK();
        			
        		}
        	}
        }

    }
	
				
}


