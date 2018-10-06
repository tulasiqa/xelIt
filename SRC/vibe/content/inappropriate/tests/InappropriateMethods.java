package vibe.content.inappropriate.tests;

import common.TestBase;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.mycommunity.tests.CommunityMethods;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;

public class InappropriateMethods extends TestBase{
	
	CommunityMethods comm = new CommunityMethods();
	BusinessContactsMethods bc = new BusinessContactsMethods();
	
	public void navigate2InappropriateWords() throws Exception{	
		logInfo("inside navigate2InappropriateWords() method");
		System.out.println("inside navigate2InappropriateWords() method");
		driver().navigate().to(appUrl+ "/pyr_core/badwords");	
		waitOnSpinner();
	}
	
	public void navigate2ReservedWords() throws Exception{	
		logInfo("inside navigate2ReservedWords() method");
		System.out.println("inside navigate2ReservedWords() method");
		driver().navigate().to(appUrl+ "/pyr_core/badwords");	
		waitOnSpinner();
	}
		
	public boolean verifyInappropriateWordsList() throws Exception{	
		logInfo("inside verifyInappropriateWordsList() method");
		System.out.println("inside verifyInappropriateWordsList() method");
		boolean isVerified = false;
		waitOnElement("xpath",txtInappropriateWords);
		WebElement el = driver().findElement(By.xpath(txtInappropriateWords));
		
		if(el.getText().trim().equalsIgnoreCase("Inappropriate Words List")){
			List<WebElement> lstInappropriateWords = driver().findElements(By.xpath(lstInappropWords));
			if(lstInappropriateWords.size()>0){
				isVerified = true;
			}
		}
		
		return isVerified;
	}
	
	public boolean verifyReservedWordsList() throws Exception{	
		logInfo("inside verifyReservedWordsList() method");
		System.out.println("inside verifyReservedWordsList() method");
		boolean isVerified = false;
		waitOnElement("xpath",txtReservedWords);
		WebElement el = driver().findElement(By.xpath(txtReservedWords));
		
		if(el.getText().trim().equalsIgnoreCase("Reserved Words For Site")){
			isVerified = true;
		}
		
		return isVerified;
	}
	
	public void addInappropriateWord(String inAppropWord) throws Exception{	
		logInfo("inside addInappropriateWord() method");
		System.out.println("inside addInappropriateWord() method");
		waitOnElement("xpath",inputInappropWord);
		inputText("xpath",inputInappropWord,inAppropWord);
		waitOnElement("xpath",btnAddInappropWord);
		clickOnElement("xpath",btnAddInappropWord);
		confirmationMessage("Word created successfully.");
	}
	
	public void validateInappropWord() throws Exception{	
		logInfo("inside validateInappropWord() method");
		System.out.println("inside validateInappropWord() method");
		waitOnElement("xpath",inputInappropWord);
		inputTextClear("xpath",inputInappropWord);
		clickOnElement("xpath",btnAddInappropWord);
		confirmationMessage("Search box cannot be blank!");
	}
	
	public void validateReservedWord() throws Exception{	
		logInfo("inside validateReservedWord() method");
		System.out.println("inside validateReservedWord() method");
		waitOnElement("xpath",inputReserveWord);
		inputTextClear("xpath",inputReserveWord);
		clickOnElement("xpath",btnAddReserveWord);
		confirmationMessage("Search box cannot be blank!");
	}
	
	public boolean verifyInappropriateWord(String InAppropWord) throws Exception{	
		logInfo("inside verifyInappropriateWord() method");
		System.out.println("inside verifyInappropriateWord() method");
		boolean isVerified = false;
		waitOnElement("xpath",lstInappropWords);
		List<WebElement> lstInappropriateWords = driver().findElements(By.xpath(lstInappropWords));
		String before = "//*[@id='badwords_ddl']/option[";
		String after = "]";
		if(lstInappropriateWords.size()>0){
			for(int i=1;i<=lstInappropriateWords.size();i++){
				WebElement el = driver().findElement(By.xpath(before+i+after));
				if(el.getText().equals(InAppropWord)){
					isVerified = true;
					el.click();
					break;
				}
			}
		}
		return isVerified;
	}
	
	public void addReservedWord(String reserveWord) throws Exception{	
		logInfo("inside addReservedWord() method");
		System.out.println("inside addReservedWord() method");
		waitOnElement("xpath",inputReserveWord);
		inputText("xpath",inputReserveWord,reserveWord);
		waitOnElement("xpath",btnAddReserveWord);
		clickOnElement("xpath",btnAddReserveWord);
		confirmationMessage("Word created successfully.");
	}
	
	public void validateReserveWord() throws Exception{	
		logInfo("inside validateReserveWord() method");
		System.out.println("inside validateReserveWord() method");
		waitOnElement("xpath",inputReserveWord);
		inputTextClear("xpath",inputReserveWord);
		clickOnElement("xpath",btnAddReserveWord);
		confirmationMessage("Search box cannot be blank!");
	}
	
	public boolean verifyReservedWord(String reserveWord) throws Exception{	
		logInfo("inside verifyReservedWord() method");
		System.out.println("inside verifyReservedWord() method");
		boolean isVerified = false;
		waitOnElement("xpath",lstResWords);
		List<WebElement> lstInappropriateWords = driver().findElements(By.xpath(lstResWords));
		String before = "//*[@id='reserved_list_ddl']/option[";
		String after = "]";
		if(lstInappropriateWords.size()>0){
			for(int i=1;i<=lstInappropriateWords.size();i++){
				WebElement el = driver().findElement(By.xpath(before+i+after));
				if(el.getText().equals(reserveWord)){
					isVerified = true;
					el.click();
					break;
				}
			}
		}
		return isVerified;
	}
	
	public boolean searchByWord(String inAppropWord) throws Exception{	
		logInfo("inside searchByWord() method");
		System.out.println("inside searchByWord() method");
		boolean isWordFound = false;
		
		waitOnElement("xpath",inputInappropWord);
		inputText("xpath",inputInappropWord,inAppropWord);
		waitOnElement("xpath",btnSearchWord);
		clickOnElement("xpath",btnSearchWord);
		
		waitOnElement("xpath",lstSearch);
		List<WebElement> lstWords = driver().findElements(By.xpath(lstSearch));
		String before = "//*[@id='report']/table/tbody/tr[";
		String after = "]/td[4]";
		
		if(lstWords.size()>0){
			for(int i=1;i<=lstWords.size();i++){
				WebElement el = driver().findElement(By.xpath(before+i+after));
				if(el.getText().equalsIgnoreCase(inAppropWord)){
					isWordFound = true;
					break;
				}
			}
		}
		return isWordFound;
	}
	
	public boolean searchAllWords(String inAppropWord) throws Exception{	
		logInfo("inside searchAllWords() method");
		System.out.println("inside searchAllWords() method");
		boolean isWordFound = false;
		waitOnElement("xpath",btnSearchAllWords);
		clickOnElement("xpath",btnSearchAllWords);
		
		waitOnElement("xpath",lstSearch);
		List<WebElement> lstWords = driver().findElements(By.xpath(lstSearch));
		String before = "//*[@id='report']/table/tbody/tr[";
		String after = "]/td[4]";
		
		if(lstWords.size()>0){
			for(int i=1;i<=lstWords.size();i++){
				WebElement el = driver().findElement(By.xpath(before+i+after));
				if(el.getText().trim().equalsIgnoreCase(inAppropWord)){
					isWordFound = true;
					break;
				}
			}
		}
		return isWordFound;
	}
	
	public boolean filterWordsByLang(String inAppropWord,String language) throws Exception{
		logInfo("inside filterWordsByLang() method");
		System.out.println("inside filterWordsByLang() method");
		boolean isWordFound = false;
		waitOnElement("xpath",ddlLanguageFilter);
		selectFromDropdown("xpath", ddlLanguageFilter,"byVisibleText" , language);
		waitOnElement("xpath",btnLanguageFilter);
		clickOnElement("xpath",btnLanguageFilter);
		isWordFound = verifyInappropriateWord(inAppropWord);
		return isWordFound;
	}
	public boolean viewMaskedContent(String inappropContent) throws Exception{
		logInfo("inside viewMaskedContent() method");
		System.out.println("inside viewMaskedContent() method");
		boolean isMasked = false;
		try{
			waitOnElement("xpath",btnSearchAllWords);
			clickOnElement("xpath",btnSearchAllWords);
			waitOnSpinner();
			waitOnElement("xpath",lstSearch);
			List<WebElement> lstWords = driver().findElements(By.xpath(lstSearch));
			String before = "//*[@id='report']/table/tbody/tr[";
			String after = "]/td[4]";
			String content = "]/td[2]/a";
			
			if(lstWords.size()>0){
				for(int i=1;i<=lstWords.size();i++){
					WebElement el = driver().findElement(By.xpath(before+i+after));
					if(el.getText().equalsIgnoreCase(inappropContent)){
						WebElement lnkContent = driver().findElement(By.xpath(before+i+content));
						lnkContent.click();
						waitOnElement("xpath",lblTrainingInappropComment);
						WebElement trainingComment = driver().findElement(By.xpath(lblTrainingInappropComment));
						if(trainingComment.getText().contains("***")){
							isMasked = true;
						}
						break;
					}
				}
			}
			userLogout();
		}
		catch(Exception ex){
			userLogout();
		}
		return isMasked;
		
	}
	
	public void updateMaskedContent(String inappropContent,String updatedContent) throws Exception{
		try{
			logInfo("inside updateMaskedContent() method");
			System.out.println("inside updateMaskedContent() method");
			waitOnElement("xpath",btnSearchAllWords);
			clickOnElement("xpath",btnSearchAllWords);
			
			waitOnElement("xpath",lstSearch);
			List<WebElement> lstWords = driver().findElements(By.xpath(lstSearch));
			String before = "//*[@id='report']/table/tbody/tr[";
			String after = "]/td[4]";
			String content = "]/td[2]/a";
			
			if(lstWords.size()>0){
				for(int i=1;i<=lstWords.size();i++){
					WebElement el = driver().findElement(By.xpath(before+i+after));
					if(el.getText().equalsIgnoreCase(inappropContent)){
						WebElement lnkContent = driver().findElement(By.xpath(before+i+content));
						lnkContent.click();
						waitOnElement("xpath",lblTrainingInappropComment);
						WebElement trainingComment = driver().findElement(By.xpath(lblTrainingInappropComment));
						if(trainingComment.getText().contains("***")){
							clickOnElement("xpath",lnkCommentEdit);
							waitOnElement("xpath",inputInappropriateComment);
							inputTextClear("xpath",inputInappropriateComment);
							inputText("xpath",inputInappropriateComment,updatedContent);
							waitOnElement("xpath",btnUpdateInappropContent);
							clickOnElement("xpath",btnUpdateInappropContent);
							confirmationMessage("Comment Updated successfully.");
						}
						break;
					}
				}
			}
			userLogout();
		}
		catch(Exception ex){
			userLogout();
		}
		
	}
	
	public void validateSearch() throws Exception{	
		logInfo("inside searchByWord() method");
		System.out.println("inside searchByWord() method");
		waitOnElement("xpath",btnSearchWord);
		clickOnElement("xpath",btnSearchWord);
		confirmationMessage("You must enter a word to search for.");
		
	}
	public void deleteInAppropriateWord(String inAppropWord) throws Exception{
		logInfo("inside deleteInAppropriateWord() method");
		System.out.println("inside deleteInAppropriateWord() method");
		verifyInappropriateWord(inAppropWord);
		waitOnElement("xpath",btnDeleteInappropriateWord);
		clickOnElement("xpath",btnDeleteInappropriateWord);
		confirmationMessage("Word deleted successfully");
	}
	
	public void deleteReservedWord(String reservedWord) throws Exception{
		logInfo("inside deleteReservedWord() method");
		System.out.println("inside deleteReservedWord() method");
		verifyReservedWord(reservedWord);
		waitOnElement("xpath",btnDeleteReservedWord);
		clickOnElement("xpath",btnDeleteReservedWord);
		confirmationMessage("Word deleted successfully");
	}
	
	public void setEmailConfiguration(String subject,String body) throws Exception{
		logInfo("inside setEmailConfiguration() method");
		System.out.println("inside setEmailConfiguration() method");
		waitOnElement("xpath",lnkEmailConfig);
		clickOnElement("xpath",lnkEmailConfig);
		waitOnElement("xpath",inputEmailSubject);
		inputTextClear("xpath",inputEmailSubject);
		inputText("xpath",inputEmailSubject,subject);
		inputTextClear("xpath",inputEmailBody);
		inputText("xpath",inputEmailBody,body);
		waitOnElement("xpath",btnSubmitEmailConfig);
		clickOnElement("xpath",btnSubmitEmailConfig);
		confirmationMessage("Email configuration updated successfully.");
	}
	
	public void exportInappropriateWords() throws Exception{
		logInfo("inside exportInappropriateWords() method");
		System.out.println("inside exportInappropriateWords() method");
		boolean isFileExists = false;
		waitOnElement("xpath",lnkExportInappropriateWords);
		clickOnElement("xpath",lnkExportInappropriateWords);
		String filepath = projectPath+"\\downloads\\";
		if(bc.verifyFileExistsOnDisk(filepath+"export-"+txtExportDate+".xls")){
			logInfo(filepath+"export.xls" + " file exists.");
			System.out.println(filepath+"export.xls" + " file exists.");
		}
		else{
			logInfo(filepath+"export.xls" + " file does not exists.");
			System.out.println(filepath+"export.xls" + " file exists.");
		}
	}
	
	public void importInappropriateWords() throws Exception{
		logInfo("inside importInappropriateWords() method");
		System.out.println("inside importInappropriateWords() method");
		waitOnElement("xpath",lnkImportInappropriateWords);
		clickOnElement("xpath",lnkImportInappropriateWords);
		Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\importCSV.exe");
		confirmationMessage("File was successfully imported.");
	}
	
	public boolean sendMail(String inappropContent,String subject,String body) throws Exception{
		logInfo("inside sendMail() method");
		System.out.println("inside sendMail() method");
		boolean isConfiguredMail = false;
		try{
			waitOnElement("xpath",btnSearchAllWords);
			clickOnElement("xpath",btnSearchAllWords);
			
			waitOnElement("xpath",lstSearch);
			List<WebElement> lstWords = driver().findElements(By.xpath(lstSearch));
			String before = "//*[@id='report']/table/tbody/tr[";
			String after = "]/td[4]";
			String content = "]/td[3]/a";
			
			if(lstWords.size()>0){
				for(int i=1;i<=lstWords.size();i++){
					WebElement el = driver().findElement(By.xpath(before+i+after));
					if(el.getText().equalsIgnoreCase(inappropContent)){
						WebElement lnkContent = driver().findElement(By.xpath(before+i+content));
						lnkContent.click();
						waitOnElement("xpath",inputEmailSubject);
						WebElement mailSubject = driver().findElement(By.xpath(inputEmailSubject));
						WebElement mailBody = driver().findElement(By.xpath(inputEmailBody));
						if(mailSubject.getText().equalsIgnoreCase(subject)&&mailBody.getText().equalsIgnoreCase(body)){
							isConfiguredMail = true;
							clickOnElement("xpath",btnSendConfigureEmail);
							confirmationMessage("Email sent to the user.");
						}
						break;
					}
				}
			}
			userLogout();
		}
		catch(Exception ex){
			userLogout();
		}
		return isConfiguredMail;
		
	}
	
}
