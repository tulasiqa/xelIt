package vibe.admin.tests;

import vibe.content.inappropriate.tests.InappropriateMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.EnvProperties;
import common.Priority;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.training.tests.TrainingMethods;

@Priority(16)
public class AdminInappropriateWords extends InappropriateMethods {
	
	TrainingMethods tm = new TrainingMethods();
	EnvProperties prop = new EnvProperties();
	
	@Test(priority=1601)
	public void ViewInappropriateWords() throws Exception{
		logInfo("inside ViewInappropriateWords() method");
		System.out.println("inside ViewInappropriateWords() method");
		navigate2InappropriateWords();
		boolean isVerified = verifyInappropriateWordsList();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to verify Inappropriate words list");
		}
		
	}
	
	@Test(priority=1602)
	public void AddInappropriateWord() throws Exception{
		logInfo("inside AddInappropriateWord() method");
		System.out.println("inside AddInappropriateWord() method");
		navigate2InappropriateWords();
		addInappropriateWord(txtInappropWord);
		boolean isWordFound = verifyInappropriateWord(txtInappropWord);
		if(!isWordFound){
			Assert.assertTrue(isWordFound, "Unable to verify Inappropriate word in the list");
		}
	}
	
	@Test(priority=1603)
	public void FilterWordsByLanguage() throws Exception{
		logInfo("inside FilterWordsByLanguage() method");
		System.out.println("inside FilterWordsByLanguage() method");
		navigate2InappropriateWords();
		boolean isWordFound = filterWordsByLang(txtInappropWord,"English");
		if(!isWordFound){
			Assert.assertTrue(isWordFound, "Unable to filter the word "+txtInappropWord +" by language English");
		}
	}
	
	@Test(priority=1604)
	public void ValidateInappropriateWord() throws Exception{
		logInfo("inside ValidateInappropriateWord() method");
		System.out.println("inside ValidateInappropriateWord() method");
		navigate2InappropriateWords();
		validateInappropWord();
	}
	
	
	@Test(priority=1605)
	public void ViewReservedWords() throws Exception{
		logInfo("inside ViewReservedWords() method");
		System.out.println("inside ViewReservedWords() method");
		navigate2ReservedWords();
		boolean isVerified = verifyReservedWordsList();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to verify Reserved words list");
		}
		
	}
	
	@Test(priority=1606)
	public void AddReservedWord() throws Exception{
		logInfo("inside AddReservedWord() method");
		System.out.println("inside AddReservedWord() method");
		navigate2ReservedWords();
		addReservedWord(txtReserveWord);
		boolean isWordFound = verifyReservedWord(txtReserveWord);
		if(!isWordFound){
			Assert.assertTrue(isWordFound, "Unable to verify Reserved word in the list");
		}
	}
	
	@Test(priority=1607)
	public void ValidateReservedWord() throws Exception{
		logInfo("inside ValidateReservedWord() method");
		System.out.println("inside ValidateReservedWord() method");
		navigate2ReservedWords();
		validateReservedWord();
	}
	
	@Test(priority=1608)
	public void VerifyInappropriateWordInTraining() throws Exception{
		try{
			logInfo("inside VerifyInappropriateWordInTraining() method");
			System.out.println("inside VerifyInappropriateWordInTraining() method");
			tm.go2AdminTrainingPage();
			tm.addTrainingCategory(categoryName4);
			boolean isCategoryFound = tm.verifyCategoryPresent(categoryName4);
			if(isCategoryFound){
				tm.go2AddSeriesPage(categoryName4);
				logInfo("Adding series without dependency.");
				tm.addSeriesToTrainingCategory(seriesName5,seriesDesc5);
				boolean isSeriesFound = tm.verifySeriesPresent(seriesName5);
				if(isSeriesFound){
					logInfo("Adding training without dependency.");
					tm.addTraining2Series(seriesName5,trainingTitle5,trainingDesc5,fileType,uploadPdfPath1);
					boolean isTrainingFound = tm.verifyTrainingPresent(trainingTitle5);
					if(!isTrainingFound){
						Assert.assertTrue(isTrainingFound, trainingTitle5 + " training could not be added.");
					}
					else{
						loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
						prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
						
						tm.go2TrainingUsers();
						logInfo("verify category present");
						boolean isCategoryPresent = tm.verifyCategoryPresentAtUser(categoryName4);
						if(isCategoryPresent){
							tm.selectCategory(categoryName4);
							logInfo("verify series present");
							boolean isSeriesPresent = tm.verifySeriesAtUser(seriesName5);
							if(isSeriesPresent){
								logInfo("verify training present");
								boolean isTrainingPresent = tm.verifyTrainingAtUser(trainingTitle5);
								if(isTrainingPresent){
									logInfo("checking the dependency factor of training series.");
									tm.viewTraining(trainingTitle5);
									tm.addComments2Training(trainingTitle5,txtTrainingInappropComment);
									boolean isCommentFound = tm.verifyTrainingComments("***");
									if(!isCommentFound){
										Assert.assertTrue(isCommentFound, "Unable to verify the masked comments " + trainingTitle5);
									}
								}
								else{
									Assert.assertTrue(isTrainingPresent, trainingTitle5 + " training could not be found.");
								}
							}
							else{
								Assert.assertTrue(isSeriesFound, seriesName5 + " series could not be added.");
							}
								
						}
						else{
							Assert.assertTrue(isCategoryFound, categoryName4 + " category could not be added.");
						}
					}
				}
			}
			userLogout();
		}
		catch(Exception ex){
			userLogout();
		}
		
	}
	
	@Test(priority=1609)
	public void SearchByWord() throws Exception{
		logInfo("inside SearchByWord() method");
		System.out.println("inside SearchByWord() method");
		navigate2InappropriateWords();
		boolean isWordFound = searchByWord(txtTrainingInappropComment);
		if(!isWordFound){
			Assert.assertTrue(isWordFound, "Unable to search the word "+txtTrainingInappropComment +" in the search list");
		}
	}
	
	@Test(priority=1610)
	public void ValidateSearchByWord() throws Exception{
		logInfo("inside ValidateSearchByWord() method");
		System.out.println("inside ValidateSearchByWord() method");
		navigate2InappropriateWords();
		validateSearch();
	}
	
	@Test(priority=1611)
	public void SearchAllWords() throws Exception{
		logInfo("inside SearchAllWords() method");
		System.out.println("inside SearchAllWords() method");
		navigate2InappropriateWords();
		boolean isWordFound = searchAllWords(txtTrainingInappropComment);
		if(!isWordFound){
			Assert.assertTrue(isWordFound, "Unable to search the word "+txtTrainingInappropComment +" in the search list");
		}
	}
	
	@Test(priority=1612)
	public void ViewMaskedContent() throws Exception{
		logInfo("inside ViewMaskedContent() method");
		System.out.println("inside ViewMaskedContent() method");
		navigate2InappropriateWords();
		boolean isMaskedContent = viewMaskedContent(txtTrainingInappropComment);
		if(!isMaskedContent){
			Assert.assertTrue(isMaskedContent, "Unable to view the masked content "+txtTrainingInappropComment);
		}
	}
	
	@Test(priority=1613)
	public void UpdateMaskedContent() throws Exception{
		logInfo("inside UpdateMaskedContent() method");
		System.out.println("inside UpdateMaskedContent() method");
		navigate2InappropriateWords();
		updateMaskedContent(txtTrainingInappropComment,txtTrainingComment);
	}
	
	@Test(priority=1614)
	public void ConfigureEmail() throws Exception{
		logInfo("inside ConfigureEmail() method");
		System.out.println("inside ConfigureEmail() method");
		navigate2InappropriateWords();
		setEmailConfiguration(txtEmailSubject,txtEmailBody);
	}
	
	@Test(priority=1615)
	public void SendConfiguredMail() throws Exception{
		logInfo("inside SendConfiguredMail() method");
		System.out.println("inside SendConfiguredMail() method");
		navigate2InappropriateWords();
		boolean isConfiguredMail = sendMail(txtTrainingInappropComment,txtEmailSubject,txtEmailBody);
		if(!isConfiguredMail){
			Assert.assertTrue(isConfiguredMail, "Unable to verify the configured subject & body in the email for "+txtTrainingInappropComment);
		}
	}
	
	@Test(priority=1616)
	public void ExportInappropriateWordsList() throws Exception{
		logInfo("inside ExportInappropriateWordsList() method");
		System.out.println("inside ExportInappropriateWordsList() method");
		navigate2InappropriateWords();
		exportInappropriateWords();
	}
	
	@Test(priority=1617)
	public void ImportInappropriateWords() throws Exception{
		logInfo("inside ImportInappropriateWords() method");
		System.out.println("inside ImportInappropriateWords() method");
		navigate2InappropriateWords();
		importInappropriateWords();
		boolean isWordFound = verifyInappropriateWord(txtImportInappropWord);
		if(!isWordFound){
			Assert.assertTrue(isWordFound, "Unable to verify the newely imported Inappropriate word in the list "+txtImportInappropWord);
		}
	}

	
}
