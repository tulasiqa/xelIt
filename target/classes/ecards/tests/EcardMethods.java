package vibe.ecards.tests;


import java.util.List;
import org.testng.Assert;
import common.EnvProperties;
import common.SocialNetWorksMethods;
import common.TestBase;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.shopping.tests.ShoppingMethods;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

    public class EcardMethods extends TestBase {       	
    	
    	
    	ShoppingMethods sm = new ShoppingMethods();
    	MyProfileMethods profile = new MyProfileMethods();
    	SocialNetWorksMethods snw = new SocialNetWorksMethods();
    	EnvProperties prop = new EnvProperties();

    	
    	public void nav2AdminEcard() throws Exception{
    		logInfo("Entered into nav2AdminEcard() method");
    		driver().navigate().to(appUrl + "admin/crm/ecard_templates"); 
    		 		
    		
    		
    	}	 

	
	public void nav2Ecard() throws Exception{
		logInfo("Entered into nav2Ecard() method");
		driver().navigate().to(appUrl + "crm/ecard_templates"); 
		 		
	}
	
	public void nav2EcardTemp() throws Exception{
		logInfo("Entered into nav2EcardTemp() method");
		driver().navigate().to(appUrl + "admin/crm/ecard_templates/new?size=640X480"); 
		 		
	}
	
	public void nav2EcardCategories() throws Exception{
		logInfo("Entered into nav2EcardCategories() method");
		nav2AdminEcard();
		listOfPanelTitles("Manage Categories");
	
	}
	
	
	public void filterCategory(String eCategoryName) throws Exception{
		logInfo("Entered into filterCategory() method ");		
		clickOnElement("cssSelector", catDrops);
		 
		boolean isCatPresent = false;
		List <WebElement> cat = driver().findElements(By.cssSelector(catDropsList));
		for (int i=3; i<= cat.size()+3; i++){
			WebElement catName = driver().findElement(By.cssSelector(catDropsListBfr+i+catDropsListAft));
			String category = catName.getText().trim();			
			if (category.equalsIgnoreCase(eCategoryName)){
				isCatPresent =true;
				catName.click();
				break;		
			}
			
		}if (isCatPresent==false){
			Assert.assertTrue(isCatPresent, eCategoryName + " - category is not present in dropdown.");
		}		
	}
	
	public void filterMarket(String eMarket) throws Exception{
		logInfo("Entered into filterMarket() method ");		
		clickOnElement("cssSelector", markDrops);
		 
		boolean isMarketPresent = false;
		List <WebElement> cat = driver().findElements(By.cssSelector(markDropsList));
		System.out.println(cat.size());
		for (int i=3; i<= cat.size()+3; i++){
			WebElement marName = driver().findElement(By.cssSelector(markDropsListBfr+i+catDropsListAft));
			String market = marName.getText().trim();
			System.out.println(market);
			if (market.equalsIgnoreCase(eMarket)){
				isMarketPresent =true;
				marName.click();
				break;		
			}
			
		}if (isMarketPresent==false){
			Assert.assertTrue(isMarketPresent, eMarket + " - Market is not present in dropdown.");
		}		
	}
	
	public void filterSubscription(String eSubscrption) throws Exception{
		logInfo("Entered into filterSubscription() method ");		
		clickOnElement("cssSelector", subDrops);		
		boolean isSubPresent = false;
		waitOnElement("cssSelector", subDropsList);
		List <WebElement> cat = driver().findElements(By.cssSelector(subDropsList));		
		for (int i=3; i<= cat.size()+3; i++){
			WebElement subName = driver().findElement(By.cssSelector(subDropsListBfr+i+catDropsListAft));
			String subscp = subName.getText().trim();
			System.out.println(subscp);
			if (subscp.equalsIgnoreCase(eSubscrption)){
				isSubPresent =true;
				subName.click();
				
				break;	
			}
			
		}if (isSubPresent==false){
			Assert.assertTrue(isSubPresent, eSubscrption + " - Subscription is not present in dropdown.");
		}
	 }
	
	public void filterRanks(String eRanker) throws Exception{
		logInfo("Entered into filterRanks() method ");		
		clickOnElement("cssSelector", rankDrops);
		waitOnElement("cssSelector", rankDropsList);
		boolean isRankPresent = false;
		List <WebElement> ra = driver().findElements(By.cssSelector(rankDropsList));		
		for (int i=3; i<= ra.size()+3; i++){
			WebElement rankName = driver().findElement(By.cssSelector(rankDropsListBfr+i+catDropsListAft));
			String ranker = rankName.getText().trim();			
			if (ranker.equalsIgnoreCase(eRanker)){
				isRankPresent =true;
				rankName.click();
				break;		
			}
			
		}if (isRankPresent==false){
			Assert.assertTrue(isRankPresent, eRanker + " - Ranker is not present in dropdown.");
		}
	}
	
	public void filterStatus(String eStatus) throws Exception{
		logInfo("Entered into filterRanks() method ");		
		clickOnElement("cssSelector", statusDrops);
		waitOnElement("cssSelector", statusDropsList);
		boolean isStatusPresent = false;
		List <WebElement> ra = driver().findElements(By.cssSelector(statusDropsList));		
		for (int i=3; i<= ra.size()+3; i++){
			WebElement rankName = driver().findElement(By.cssSelector(statusDropsListBfr+i+catDropsListAft));
			String ranker = rankName.getText().trim();			
			if (ranker.equalsIgnoreCase(eStatus)){
				isStatusPresent =true;
				rankName.click();
				break;		
			}			
		}if (isStatusPresent==false){
			Assert.assertTrue(isStatusPresent, eStatus + " - status is not present in dropdown.");
		}
	}
	
	public void selectCardSize(int size_1_to_4) throws Exception{
		logInfo("Entered into selectCardSize(int size_1_to_4) method ");
		waitOnElement("cssSelector", sizeInfo);;
		WebElement testMsg = driver().findElement(By.cssSelector(sizeInfo));
		Assert.assertEquals(testMsg.getText().trim(), sizeInfoText);	
		List <WebElement> card = driver().findElements(By.cssSelector(cardSizes));		
		if ((size_1_to_4<=0)||(size_1_to_4>4)){
		WebElement cardSize = driver().findElement(By.cssSelector(cardSizesBfr+size_1_to_4+cardSizesAfr));
		Actions act = new Actions(driver());
		act.doubleClick(cardSize).build().perform();	
		cardSize.click();
		}		
	}
	
	//Selects panel tabs of List Ecard Templates like NewEcard, Setting , Managecat, Background etc.
	public void listOfPanelTitles(String listPanel){
		logInfo("Entered into listOfPanelTitles() method ");
		boolean isListPresent = false;
		List <WebElement> li = driver().findElements(By.cssSelector(mangCatLis));		
		for (WebElement lis :li){		
			if (lis.getText().equalsIgnoreCase(listPanel)){
				isListPresent=true;
				lis.click();
				break;			
			}
			
		}if (isListPresent==false){
			Assert.assertTrue(isListPresent, listPanel + " panel is not found under Listing Ecard Templates");
		}
		
		
	}
	
	public void addEcardCategory(String catName, String parentCat) throws Exception{
		logInfo("Entered into addEcardCategory() method ");		
		listOfPanelTitles("Manage Categories");		
		waitOnElement("cssSelector", eAddNewCat);
		clickOnButton("cssSelector", eAddNewCat);
		
		waitOnElement("cssSelector",eCatName);	
		waitOnElement("cssSelector",eCatName);
		waitOnElement("cssSelector",eCatName);
		inputText("cssSelector",eCatName,catName);		
		selectFromDropdown("cssSelector",eCatParSelect,"byVisibleText",parentCat);
		waitOnElement("cssSelector",eCardCreate);	
		clickOnButton("cssSelector",eCardCreate);		
		waitOnSpinner();
		
		confirmationMessage("Ecards Category created successfully.");
	}
	
	
	public boolean validateCategories(String category, String subCategory) throws Exception{
		logInfo("Entered into validateCategories() method ");		
		nav2EcardCategories();		
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));
		System.out.println("size"+ cat.size());	
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr));
			String pCatgName  = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)){
				isParentPresent = true;
				WebElement plusClick = driver().findElement(By.cssSelector(catListBfr+i+catListPlus));
				plusClick.click();
					
				List <WebElement> subCat = driver().findElements(By.cssSelector(catListBfr+i+subCatList));				
				boolean isSubPresent = false;
				for (int j =1; j<=subCat.size(); j++){
					WebElement subCatNm = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+subCatListAfr));
					String subCatgName  = subCatNm.getText().trim(); 					
					if (subCatgName.equalsIgnoreCase(subCategory)){
						isSubPresent = true;						
						break;					
					}					
				}if(isSubPresent==false){
					Assert.assertTrue(isSubPresent, subCategory + " Subcategory is not present under "+category);
				}				
				break;
			}			
		}if(isParentPresent==false){
			Assert.assertTrue(isParentPresent, category + " Parent Category is not present.");
		}
		return isParentPresent;			
	}
	
	
	public boolean updateChildCategories(String category, String subCategory, String updateCategory) throws Exception{
		logInfo("Entered into updateChildCategories() method ");		
		nav2EcardCategories();		
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));	
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr));
			String pCatgName  = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)){
				isParentPresent = true;
				WebElement plusClick = driver().findElement(By.cssSelector(catListBfr+i+catListPlus));
				plusClick.click();
				 				
				List <WebElement> subCat = driver().findElements(By.cssSelector(catListBfr+i+subCatList));				
				boolean isSubPresent = false;
				for (int j =1; j<=subCat.size(); j++){
					WebElement subCatNm = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+subCatListAfr));
					String subCatgName  = subCatNm.getText().trim(); 					
					if (subCatgName.equalsIgnoreCase(subCategory)){
						isSubPresent = true;							
						WebElement actionClick = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+catActionAfr));
						actionClick.click();
						 
						WebElement edit = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+catActionAfr+catActionEdit));
						edit.click();
						 
						inputTextClear("cssSelector",eCatName);
						inputText("cssSelector",eCatName,updateCategory);
						 
						clickOnButton("cssSelector", updateCat);
						confirmationMessage("Ecard Category updated successfully.");	
						 
						break;					
					}					
				}if(isSubPresent==false){
					Assert.assertTrue(isSubPresent, subCategory + " Subcategory is not present under "+category);
				}				
				break;
			}			
		}if(isParentPresent==false){
			Assert.assertTrue(isParentPresent, category + " Parent Category is not present.");
		}
		return isParentPresent;			
	}
	
	
	public void deleteChildCategories(String category, String subCategory) throws Exception{
		logInfo("Entered into deleteChildCategories() method ");		
		nav2EcardCategories();		
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));		
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr));
			String pCatgName  = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)){				
				isParentPresent = true;
				WebElement plusClick = driver().findElement(By.cssSelector(catListBfr+i+catListPlus));
				plusClick.click();
				 
				List <WebElement> subCat = driver().findElements(By.cssSelector(catListBfr+i+subCatList));				
				boolean isSubPresent = false;
				for (int j =1; j<=subCat.size(); j++){
					WebElement subCatNm = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+subCatListAfr));
					String subCatgName  = subCatNm.getText().trim(); 					
					if (subCatgName.equalsIgnoreCase(subCategory)){
						isSubPresent = true;								
						WebElement actionClick = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+catActionAfr));
						actionClick.click();
						waitOnElement("cssSelector",catListBfr+i+subCatListBfr+j+catActionAfr+catActionDelete);
						WebElement delete = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+catActionAfr+catActionDelete));
						delete.click();
						 						
						confirmOK();
						confirmationMessage("Ecard Category deleted successfully.");							
						break;					
					}					
				}if(isSubPresent==false){
					Assert.assertTrue(isSubPresent, subCategory + " Subcategory is not present under "+category);
				}				
				break;
			}			
		}if(isParentPresent==false){
			Assert.assertTrue(isParentPresent, category + " Parent Category is not present.");
		}
				
	}
	
	public boolean NotPresentChildCategories(String category, String subCategory) throws Exception{
		logInfo("Entered into updateChildCategories() method ");		
		nav2EcardCategories();		
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));		
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr));
			String pCatgName  = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)){
				isParentPresent = true;
				WebElement plusClick = driver().findElement(By.cssSelector(catListBfr+i+catListPlus));
				plusClick.click();
				 				
				List <WebElement> subCat = driver().findElements(By.cssSelector(catListBfr+i+subCatList));				
				boolean isSubPresent = true;
				for (int j =1; j<=subCat.size(); j++){
					WebElement subCatNm = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+subCatListAfr));
					String subCatgName  = subCatNm.getText().trim(); 				
					if (subCatgName.equalsIgnoreCase(subCategory)){
						isSubPresent = false;
						Assert.assertTrue(isSubPresent, subCategory + " Subcategory is still present under "+category);
						 
						break;					
					}					
				}if(isSubPresent==true){
					System.out.println("Sucess!! Not present Subcategory under "+category);
					
				}				
				break;
			}			
		}if(isParentPresent==false){
			Assert.assertTrue(isParentPresent, category + " Parent Category is not present.");
		}
		return isParentPresent;			
	}
	
	
	public void deleteParentCategories(String category) throws Exception{
		logInfo("Entered into deleteParentCategories() method ");		
		nav2EcardCategories();		
		waitOnElement("cssSelector",catList);
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			String catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr)).getText().trim();
			System.out.println("catNm "+catNm);
			if (catNm.equalsIgnoreCase(category)){
				isParentPresent = true;		
				        scrollDown("cssSelector", catListBfr+i+catListAfr);
						WebElement actionClick = driver().findElement(By.cssSelector(catListBfr+i+catActionAfr));
						actionClick.click();						
						WebElement delete = driver().findElement(By.cssSelector(catListBfr+i+catActionAfr+catActionDelete));
						delete.click();						
					    confirmOK();
						confirmationMessage("Ecard Category deleted successfully.");	
						 
						break;						
			}			
		}if(isParentPresent==false){
			Assert.assertTrue(isParentPresent, category + " Parent Category is not present.");
		}
				
	}
	
	
	public void notPresentParentCategories(String category) throws Exception{
		logInfo("Entered into notPresentParentCategories() method ");		
		nav2EcardCategories();	
		waitOnElement("cssSelector",catList);
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));		
		boolean isParentPresent = true;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr));
			String pCatgName  = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)){		
						isParentPresent = false;
						Assert.assertTrue(isParentPresent, category + " Subcategory is still present under "+category);
						 
						break;					
					}					
				}if(isParentPresent==true){
					System.out.println("Sucess!! Not present in category  ");
					
				}				
						
	}    
	  
	public void createECard(String ecardName,String category, String childCategory ) throws Exception{
        logInfo("Entered into createECard() method ");        
         int FutueDate = 0;
         String futDate = getDate(FutueDate, "MM/dd/yyyy");
         String status = "Active";
         clickOnButton("cssSelector", newCard);
         nav2EcardTemp();         
         waitOnElement("cssSelector", eName);    
         WebElement nam = driver().findElement(By.cssSelector(eName));            
         nam.clear();
         WebElement desc = driver().findElement(By.cssSelector(eDescrp));
         desc.clear();
         desc.sendKeys(ecardName);
         System.out.println(nam.getAttribute("placeholder"));
         nam.sendKeys(ecardName);        
         clickOnElement("cssSelector", pubNow);        
         waitOnElement("cssSelector", browseBtn);        
         uploadFile("Image", browseBtn);
         selectCatInThemeSetting(category,childCategory);           
         scrollDown("cssSelector", ePublish);    
         waitOnElement("cssSelector", ePublish);        
         clickOnElement("cssSelector", ePublish);
         confirmationMessage("Ecard template was successfully created.");      
     }    
	
	
	public void validateTitle(String expTitle) throws Exception {
		logInfo("Entered into validateTitle() method ");
		waitOnSpinner();
		 
		waitOnElement("cssSelector", widTitles);
		WebElement titl= driver().findElement(By.cssSelector(widTitles));
		String title = titl.getText().trim();
		System.out.println("title "+ title);
		Assert.assertEquals(title, expTitle);
		
	}
    
    public void selectCatLinkOptions(String catOptions){
    	logInfo("Entered into selectCatLinkOptions() method ");		
    	List <WebElement> op = driver().findElements(By.cssSelector(eCatLinkOptins));    	
    	boolean isOptPresnce = false;
    	for (WebElement opt :op){    		
    		if(opt.getText().trim().equalsIgnoreCase(catOptions)){
    			isOptPresnce=true;
    			opt.click();
    			break;
    		}
    		
    	}if (isOptPresnce==false){
    		Assert.assertTrue(isOptPresnce, catOptions +" link is not present in Theme Settings");
    	}
    	
    }
    
     public void selectMarkets(String[] marketLanguages) {
    	 logInfo("Entered into selectMarkets() method ");
    	 selectCatLinkOptions("Markets");    	 
    	 WebElement e = driver().findElement(By.cssSelector(subscriptionchkMarketAll));
 			if (e.isSelected()) {
 			System.out.println("all checkbox is toggled on");
 			e.click();
 			}
 			if(!e.isSelected()) {
 				System.out.println("not checkbox is toggled on");
 	 			e.click();
 	 			e.click();
 	 			}
 			
 			int langgs = marketLanguages.length;
 			boolean isLanFound =true;
 			List <WebElement> lang = driver().findElements(By.cssSelector(compLangDD));			
 			for (WebElement langs : lang){	
 				for (int i=0; i<=langgs-1; i++ )
 				if (langs.getText().equalsIgnoreCase(marketLanguages[i])){
 					if(!langs.isSelected()){
 					isLanFound =true;	
 					langs.click();
 					break;
 				}}
 					
 				}if (isLanFound==false){
 					Assert.assertTrue(isLanFound, marketLanguages+" - language is not found");
 				}   	 
    	 
     }
     
     public void selectSubscriptionPlan(String[] SubcriptionPlan) throws Exception{
 		logInfo("Entered into selectSubscriptionPlan() method"); 				
 		    selectCatLinkOptions("Subscription Plan");  
 			WebElement subsAll = driver().findElement(By.cssSelector(subAll));
 			if (subsAll.isSelected()){
 				subsAll.click();
 				waitOnElement("cssSelector", subAll); 				
 				}
 			if (!subsAll.isSelected()){
 				subsAll.click();
 				waitOnElement("cssSelector", subAll); 	
 				subsAll.click();
 				}
 			boolean isSubFound = false;			
 			int subcription = SubcriptionPlan.length;			
 			List <WebElement> sub = driver().findElements(By.cssSelector(compSubPlanDD));			
 			for (WebElement subPlan : sub){	
 				for(int j=0; j<=subcription-1;j++){				
 				if (subPlan.getText().equalsIgnoreCase(SubcriptionPlan[j])){
 					isSubFound= true;
 					if(!(subPlan.isSelected())){
 					subPlan.click();
 					break;
 						}
 				    }	
 				}
 			}if (isSubFound==false){
 				Assert.assertTrue(isSubFound, SubcriptionPlan+" - subscription is not found");
 			}
 			
 						
 	}
     
     
     
    
    
    
    public void selectCatInThemeSetting(String category, String childCategory) throws Exception{
    	logInfo("Entered into selectCatInThemeSetting() method ");    	
    	selectCatLinkOptions("Categories");
    	selectParentCateInThemeSettings(category);
    	//selectChildCateInThemeSettings(category, childCategory);
    }
    
    
    public void selectParentCateInThemeSettings(String category) throws Exception{
		logInfo("Entered into selectParentCateInThemeSettings() method ");		
		waitOnElement("cssSelector", eCatChk);
		List <WebElement> cat = driver().findElements(By.cssSelector(eCatChk));		
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(eCatChkBfr+i+eCatChkAft));
			String pCatgName  = catNm.getText().trim();		
			scrollDown("cssSelector", eCatChkBfr+i+eCatChkAft);
			if (pCatgName.contains(category)){		
						isParentPresent = true;
						System.out.println("mathced parent is  "+pCatgName);
						WebElement catNmChk = driver().findElement(By.cssSelector(eCatChkBfr+i+eCatChkBoxAft));
						if (!catNmChk.isSelected()){
							catNmChk.click();						
						}						
						break;					
					}					
				}if(isParentPresent==false){
					Assert.assertTrue(isParentPresent, category + " category is not present under theme settings");										
				}				
						
	}
    
    public void selectChildCateInThemeSettings(String category, String childCategory) throws Exception{
		logInfo("Entered into selectParentCateInThemeSettings() method ");				
		List <WebElement> cat = driver().findElements(By.cssSelector(eCatChk));		
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(eCatChkBfr+i+eCatChkAft));
			String pCatgName  = catNm.getText().trim();
			System.out.println("pCatgName "+pCatgName);
			if (pCatgName.contains(category)){		
						isParentPresent = true;
						List <WebElement> childCat = driver().findElements(By.cssSelector(eCatChkBfr+i+eChildCatChkBoxAft));						
						boolean isChild = false;
						for (WebElement cc :childCat ){							
							String cCatgName  = cc.getText().trim();
							if (cCatgName.equalsIgnoreCase(childCategory)){	
								isChild = true;
								WebElement childCatBox = driver().findElement(By.xpath(eChildCatChkBoxAftx+i+eChildCatChkBoxAft2x));
								if (!childCatBox.isSelected()){
									childCatBox.click();
									 
								}						
								break;	
							}
						}if(isChild==false){
							Assert.assertTrue(isChild, childCategory + " category is not present under "+category);										
						}						
					break;											
					}					
				}if(isParentPresent==false){
					Assert.assertTrue(isParentPresent, category + " category is not present under theme settings");										
				}				
						
	}
	
  
	//users Side Test methods
	public void verifyCategoryName(String categoryName){
		logInfo("Entered into verifyCategoryName(String categoryName) method");
		
		boolean isCatPresent= false;
		List <WebElement> lib = driver().findElements(By.cssSelector(eCardParentCat));		
		for (int i = 1; i<=lib.size(); i++ ){			
			WebElement catName = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatAft));			
			if (catName.getText().equalsIgnoreCase(categoryName)){
				isCatPresent =true;	
				break;				
			}			
		}if (isCatPresent==false){
			Assert.assertTrue(isCatPresent, categoryName +" is not present in Library list.");
		}		
	}
	
	public void selectcategory(String categoryName) throws Exception{		
  	logInfo("Entered into selectcategory(String categoryName) method");
  	boolean isCatPresent= false;
	 
	waitOnElement("cssSelector",eCardParentCat);
	List <WebElement> lib = driver().findElements(By.cssSelector(eCardParentCat));	
	for (int i = 1; i<=lib.size(); i++ ){			
		WebElement catName = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatAft));			
		if (catName.getText().equalsIgnoreCase(categoryName)){					
			isCatPresent =true;
			WebElement catNameSelect = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatPlusIcon));
			String cathref = catNameSelect.getAttribute("href");			
			String expected ="crm/ecard_templates#";		
			if(cathref.contains(expected)){
				waitOnElement("cssSelector", eCardParentCatBfr+i+eCardParentCatPlusIcon2);
				WebElement catNameSelect2 = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatPlusIcon2));
				catNameSelect2.click();
				
			}else{
				waitOnElement("cssSelector", eCardParentCatBfr+i+eCardParentCatPlusIcon);
				catNameSelect.click();							
			}
			waitOnElement("cssSelector", spinner);
			 	
	    	waitOnElement("cssSelector", eCardrows);
			break;			
			}			
		}if (isCatPresent==false){
			Assert.assertTrue(isCatPresent, categoryName +" is not present in Library list.");
		}
    }
	
	public void selectHistory(String categoryName) throws Exception{		
	  	  logInfo("Entered into selectHistory(String categoryName) method");
			boolean isCatPresent= false;
			List <WebElement> lib = driver().findElements(By.cssSelector(eCardParentCatDir));			
			for (int i = 1; i<=lib.size(); i++ ){	
				waitOnElement("cssSelector", eCardParentCatDirBfr+i+eCardParentCatDirAft);
				WebElement catName = driver().findElement(By.cssSelector(eCardParentCatDirBfr+i+eCardParentCatDirAft));					
				if (catName.getText().equalsIgnoreCase(categoryName)){
					isCatPresent =true;
					scrollDown("cssSelector", eCardParentCatDirBfr+i+eCardParentCatDirAft);
					catName.click();														
					break;				
				}			
			}if (isCatPresent==false){
				Assert.assertTrue(isCatPresent, categoryName +" is not present in Library list.");
			}
	    }
	
	
	public void selectCategory(String categoryName) throws Exception{		
	  	  logInfo("Entered into selectCategory(String categoryName, String cardName) method");
			boolean isCatPresent= false;
			waitOnSpinner();
			waitOnElement("cssSelector",eCardParentCat);
			 
			List <WebElement> lib = driver().findElements(By.cssSelector(eCardParentCat));
			for (int i = 1; i<=lib.size(); i++ ){		
				waitOnElement("cssSelector", eCardParentCatBfr+i+eCardParentCatAft);
				WebElement catName = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatAft));			
				if (catName.getText().equalsIgnoreCase(categoryName)){					
					isCatPresent =true;
					scrollDown("cssSelector", eCardParentCatBfr+i+eCardParentCatAft);
					WebElement catNameSelect = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatPlusIcon));					
					String cathref = catNameSelect.getAttribute("href");			
					String expected ="crm/ecard_templates#";			
					if(cathref.contains(expected)){
						waitOnElement("cssSelector", eCardParentCatBfr+i+eCardParentCatPlusIcon2);
						WebElement catNameSelect2 = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatPlusIcon2));
						catNameSelect2.click();					
						 					
					}else{
						waitOnElement("cssSelector", eCardParentCatBfr+i+eCardParentCatPlusIcon);
						catNameSelect.click();
						
					     }
					waitOnElement("cssSelector", spinner);
					 				
			    	waitOnElement("cssSelector", eCardrows);					
					break;				
				}			
			}if (isCatPresent==false){
				Assert.assertTrue(isCatPresent, categoryName +" is not present in Library list.");
			}
	    }  
	
	public boolean isCategoryPresent(String categoryName) throws Exception{		
	  	  logInfo("Entered into selectCategory(String categoryName, String cardName) method");
			boolean isCatPresent= false;
			waitOnSpinner();
			waitOnElement("cssSelector",eCardParentCat);			
			List <WebElement> lib = driver().findElements(By.cssSelector(eCardParentCat));			
			for (int i = 1; i<=lib.size(); i++ ){	
				scrollDown("cssSelector", eCardParentCatBfr+i+eCardParentCatAft);
				WebElement catName = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatAft));			
				if (catName.getText().equalsIgnoreCase(categoryName)){
					isCatPresent =true;
					break;
				}		
			}
			return isCatPresent;
	    }  
	
	public void selectCatNdEcard(String categoryName, String cardName) throws Exception{		
  	  logInfo("Entered into selectcatNdEcard(String categoryName, String cardName) method");
		boolean isCatPresent= false;
		 
		waitOnElement("cssSelector",eCardParentCat);
		List <WebElement> lib = driver().findElements(By.cssSelector(eCardParentCat));		
		for (int i = 1; i<=lib.size(); i++ ){	
			waitOnElement("cssSelector", eCardParentCatBfr+i+eCardParentCatAft);
			WebElement catName = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatAft));			
			if (catName.getText().equalsIgnoreCase(categoryName)){					
				isCatPresent =true;				
				WebElement catNameSelect = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatPlusIcon));
				String cathref = catNameSelect.getAttribute("href");			
				String expected ="crm/ecard_templates";			
				if(cathref.contains(expected)){
					waitOnElement("cssSelector", eCardParentCatBfr+i+eCardParentCatPlusIcon);
					catNameSelect.click();
					catNameSelect.click();	
				}else{					
					waitOnElement("cssSelector", eCardParentCatBfr+i+eCardParentCatPlusIcon2);
					WebElement catNameSelect2 = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatPlusIcon2));
					catNameSelect2.click();					
					 		
							}
				waitOnElement("cssSelector", spinner);
				 				
		    	waitOnElement("cssSelector", eCardrows);				
				editEcard(cardName);				
				break;				
			}			
		}if (isCatPresent==false){
			Assert.assertTrue(isCatPresent, categoryName +" is not present in Library list.");
		}
    }  
	
	
	
	
	 public void selectFBIconInEcard() throws Exception{
 		logInfo("Entered into selectFBIconInEcard() method");
 		waitOnElement("cssSelector", fbIconInEcard);
 		waitOnElement("cssSelector", tweetIconInEcard);  		
 		clickOnElement("cssSelector", fbIconInEcard);		
 		
 	} 
	 
	 public void selectTweetIconInEcard() throws Exception{
	 		logInfo("Entered into selectTweetIconInEcard() method");
	 		waitOnElement("cssSelector", fbIconInEcard);
	 		waitOnElement("cssSelector", tweetIconInEcard); 
	 		 
	 		clickOnElement("cssSelector", tweetIconInEcard);		
	 		
	 	} 
	 
	 public void closeCardPopUp() throws Exception {
			logInfo("Entered into closeCardPopUp() method");
			waitOnElement("cssSelector", closeCard);
			waitOnElement("cssSelector", closeCard);
			clickOnElement("cssSelector", closeCard);
			

		}
      
      public boolean verifyEcardTemplate(String cardName) throws Exception{
    	  logInfo("Entered into verifyEcard(String cardName) method");
    	  boolean isTemptPresent= false;    	  
    	  waitOnElement("cssSelector", eCardrows);
    	  List <WebElement> rows = driver().findElements(By.cssSelector(eCardrows));     	 
    	  for (int j = 1; j<=rows.size(); j++) {    		  
    		  List <WebElement> cardTemp =  driver().findElements(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTemp));        	  
        	  for (int i=1; i<=cardTemp.size(); i++){  
        		  waitOnElement("cssSelector", eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempAfr);
        		  WebElement name = driver().findElement(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempAfr));        		  
        		  System.out.println("templates aee "+name.getText());
        		  if(name.getText().equalsIgnoreCase(cardName)){
        			  isTemptPresent=true;             			 
        			  break;    			  
        		  }  
    	  } 
      } 
	return isTemptPresent;
      }
	
      
      public void editEcard(String cardName) throws Exception{
    	  logInfo("Entered into editEcard(String cardName) method");
    	  waitOnElement("cssSelector", spinner);
    	  waitOnElement("cssSelector", eCardrows);
    	  boolean isTemptPresent= false;    	  
    	  List <WebElement> rows = driver().findElements(By.cssSelector(eCardrows));    	 
    	  for (int j = 1; j<=rows.size(); j++) {    		  
    		  List <WebElement> cardTemp =  driver().findElements(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTemp));        	  
        	  for (int i=1; i<=cardTemp.size(); i++){   
        		  waitOnElement("cssSelector", eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempAfr);
        		  WebElement name = driver().findElement(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempAfr));        		  
        		  if(name.getText().equalsIgnoreCase(cardName)){        		
        			  isTemptPresent=true;
        			  name.click();        			
        			  waitOnSpinner();
        			  break;    			  
        		  }  
    	  } 
      }  if (isTemptPresent ==false){
		  Assert.assertTrue(isTemptPresent, cardName +" is not available in Template");   	  
      			}
      }
      
     public void enterDetails(String subject) throws Exception{
    	 logInfo("Entered into enterDetails(String subject) method");
    	 waitOnElement ("cssSelector", ecardSub);    	
    	 waitOnElement ("cssSelector", ecardSub);    	                     
    	 inputTextClear("cssSelector", ecardSub);
    	 inputTextClear("cssSelector", ecardSub);
    	 inputText("cssSelector", ecardSub, subject); 
    	 inputTextClear("cssSelector", ecardSub);
    	 inputText("cssSelector", ecardSub, subject);               
     }     
     
     public void saveCardAs(String name) throws Exception{
    	 logInfo("Entered into saveCardAs(String name) method"); 
    	 String title = "Save Template";    	
    	 waitOnElement("cssSelector", ecardSave);
    	 clickOnElement("cssSelector", ecardSave);
    	 waitOnElement("cssSelector", spinner);
    	  
    	 waitOnElement("cssSelector", foot);     	 
    	 WebElement tit = driver().findElement(By.cssSelector(saveTemptitle));    	 
    	 if(tit.getText().trim().equalsIgnoreCase(title)){    		  
        	 waitOnElement("cssSelector", ecardSaveAs);	
        	 waitOnElement("cssSelector", temptitleInput);
        	 inputTextClear("cssSelector", temptitleInput);  
        	 inputText("cssSelector", temptitleInput, name);    	 
        	 waitOnElement("cssSelector", ecardSaveAs);		
    		 clickOnButton("cssSelector", ecardSaveAs);       		 
        	 confirmationMessage("Ecard Template was successfully saved.");    		 
    		 
    	 }else{
    		 Assert.assertEquals(tit.getText().trim(), title);
    	 }
    	 
    	
    	 
     }
     
     public void verifyEcardInSavedEcards(String ecardName) throws Exception{
    	 logInfo("Entered into verifyEcardInSavedEcards(String ecardName) method");
      	  boolean isTemptPresent= false;
      	  clickOnLink("linkText", "Saved Ecards");
      	   
      	  List <WebElement> rows = driver().findElements(By.cssSelector(savedTitles)); 
      	  for (WebElement title: rows){      		  
      		  if (title.getText().equalsIgnoreCase(ecardName)){
      			isTemptPresent =true;      					
      			break;
      			  
      		  }
      		  
      	  } if (isTemptPresent ==false){
   		  Assert.assertTrue(isTemptPresent, ecardName +" is not available in Template");   	  
   		}
      	  
        }
     
     
     public void verifyNdEditEcard(String ecardName) throws Exception{
    	  logInfo("Entered into verifyNdEditEcard(String ecardName) method");
	   	  boolean isTemptPresent= false;
	   	   
	   	  List <WebElement> rows = driver().findElements(By.cssSelector(savedTitles)); 	   	 
	   	  for (WebElement title: rows){
	   		  System.out.println(title.getText());
	   		  if (title.getText().equalsIgnoreCase(ecardName)){
	   			isTemptPresent =true;
	   			title.click();   			
	   			break;
	   			  
	   		  }
	   		  
	   	  } if (isTemptPresent ==false){
			  Assert.assertTrue(isTemptPresent, ecardName +" is not available in Template");   	  
			}
   	  
     }
     
     
     public void deleteSavedEcard(String ecardName) throws Exception{
    	 logInfo("Entered into deleteSavedEcard(String ecardName) method");
      	  boolean isTemptPresent= false;
      	   
      	  List <WebElement> rows = driver().findElements(By.cssSelector(savedTitles));       	  
      	  for (int i=1; i<=rows.size(); i++){
      		  WebElement  title = driver().findElement(By.cssSelector(savedECardTempBfr+i+eCardTempAfr));       		
      		  if (title.getText().equalsIgnoreCase(ecardName)){
      			isTemptPresent =true;
      			Actions act = new Actions(driver());      			
      			WebElement toggle =  driver().findElement(By.cssSelector(savedECardTempBfr+i+eCardToggleDropDown));
      			act.moveToElement(toggle).build().perform();      			
      			toggle.click();
      			break;
      		  }     		  
      	  } if (isTemptPresent ==false){
   		  Assert.assertTrue(isTemptPresent, ecardName +" is not available in Template");   	  
   		}   }     
    	
    	public void sendMail(String mailId, String subject) throws Exception{
        	logInfo("Entered into sendManualMail() method");
        	   	waitOnElement("cssSelector", spinner);
        	    waitOnElement("cssSelector", mailIcon);         	            	    
       	    	clickOnElement("cssSelector", mailIcon);    
       	    	waitOnElement("cssSelector", spinner);
       	    	 
       	    	waitOnElement("xpath", headerEcard);
       	    	WebElement head = driver().findElement(By.xpath(headerEcard));
				String title = head.getText().trim();				
				Assert.assertEquals(title, "Compose Email");       	    	
            	waitOnElement("cssSelector", inputEmailEcard);
            	  
            	waitOnElement("cssSelector", inputEmailEcard);            	
            	inputText("cssSelector", inputEmailEcard, mailId);  
            	WebElement maild = driver().findElement(By.cssSelector(inputEmailEcard));
               	maild.sendKeys(Keys.TAB);
               	maild.sendKeys(Keys.TAB);
            	inputTextClear("cssSelector", inputComposeEmailSubject);
    			inputText("cssSelector", inputComposeEmailSubject,subject  );    			
    			   // kept to handle compose body 		
    			waitOnElement("cssSelector", ecardMailSendBtn);     			
               	clickOnElement("cssSelector", ecardMailSendBtn);            	    	    	 
       	    	
       	     } 	
    	
    	//  Select Next in "Enter Content" section and compose a mail an then catches toaster message  	
    	public void sendManualMailInIDLife(String mailId, String subject) throws Exception{
        	logInfo("Entered into sendMail(String name) method");           	 
           	clickOnButton("cssSelector", ecardNext);            	          
           	waitOnElement("cssSelector", inputManualEmail);
           	inputText("cssSelector", inputEmailEcard, mailId);                    	
           	inputTextClear("cssSelector", inputComposeEmailSubject);
			inputText("cssSelector", inputComposeEmailSubject,subject  );
			   // kept to handle compose body 
			waitOnElement("cssSelector", inputComposeEmailSubject);
			composeText("xpath",composeBody,composeBodyText);  
			getText("cssSelector", ecardMailSendBtn, "test "); 
			waitOnElement("cssSelector", ecardMailSendBtn);         	
           	clickOnButton("cssSelector", ecardMailSendBtn);
           	confirmationMessage("Message sent to: "+mailId);             	
            }
    	
    	
    	public void sendManualMailToManyRecepients(String mailId, int noOfMails) throws Exception{
        	logInfo("Entered into sendMail(String name) method");
        	waitOnElement("cssSelector", spinner);
    	    waitOnElement("cssSelector", mailIcon); 
    	             	    
   	    	clickOnButton("cssSelector", mailIcon);    
   	    	waitOnElement("cssSelector", spinner);
        	waitOnElement("cssSelector", inputEmailEcard);
        	  
        	waitOnElement("cssSelector", inputEmailEcard);
        	inputText("cssSelector", inputEmailEcard, mailId);  
        	WebElement maild = driver().findElement(By.cssSelector(inputEmailEcard));
           	maild.sendKeys(Keys.TAB); 
           	maild.sendKeys(Keys.TAB); 
           	
           	for (int i= 3; i<=noOfMails+2; i++){
           		String mailId2 = "vibers"+TestBase.generateRandomString()+i+"@icentris.com";           		
           		 
           		inputText("cssSelector", inputEmailEcard, mailId2);  
            	WebElement maild2 = driver().findElement(By.cssSelector(inputEmailEcard));
               	maild2.sendKeys(Keys.TAB); 
               	maild2.sendKeys(Keys.TAB); 
           		}           	
           	
			waitOnElement("cssSelector", ecardMailSendBtn); 			
           	clickOnElement("cssSelector", ecardMailSendBtn);  
           	waitOnElement("cssSelector", ecardSave);
           	WebElement confMsg = driver().findElement(By.cssSelector(ecardSave));
           	Assert.assertEquals(confMsg.getText(), "Save");     	
           	
            }
    	
    	
    	public void validationMails(String mailId) throws Exception{
        	logInfo("Entered into validationMails(String mailId) method");
        	waitOnSpinner();
    	    waitOnElement("cssSelector", mailIcon);     	    
   	    	clickOnButton("cssSelector", mailIcon);   	    	
   	    	waitOnElement("cssSelector", inputEmailEcard);        	
        	waitOnElement("cssSelector", inputEmailEcard);
        	inputTextClear("cssSelector", subject_Mail);
        	inputTextClear("cssSelector", subject_Mail);
        	inputText("cssSelector", subject_Mail, ecardCatSubText); 
			waitOnElement("cssSelector", ecardMailSendBtn);
           	clickOnButton("cssSelector", ecardMailSendBtn);           	       
           	confirmationMessage(prop.getLocatorForEnvironment(appUrl,"eacrdToasterMsg")); 
           	waitOnElement("cssSelector", inputEmailEcard);
        	inputText("cssSelector", inputEmailEcard, mailId);  
        	WebElement maild = driver().findElement(By.cssSelector(inputEmailEcard));
           	maild.sendKeys(Keys.TAB);
           	maild.sendKeys(Keys.TAB);
           	waitOnElement("cssSelector", inputEmailEcard);	
			waitOnElement("cssSelector", ecardMailSendBtn);         	
           	clickOnButton("cssSelector", ecardMailSendBtn);
                 	
            }   	
    	
    	public void gridNdLsitView() throws Exception{
        	logInfo("Entered into gridNdLsitView() method");
        	waitOnElement("cssSelector", ecardGridView);
        	clickOnElement ("cssSelector",ecardGridView);
        	 
        	WebElement tit = driver().findElement(By.cssSelector(ecardFirstTitle));
        	String expTitle = tit.getText();
        	System.out.println("expecte title is "+expTitle);
        	 
        	WebElement listView = (new WebDriverWait(driver(), 10))
        			  .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ecardListView)));
        	listView.click();
        	 
        	waitOnElement("cssSelector", ecardListTitle);
        	WebElement listTit = driver().findElement(By.cssSelector(ecardListTitle));
        	String ActTitle = listTit.getText();        	        	
        	Assert.assertEquals(ActTitle, expTitle);
        	clickOnElement ("cssSelector",ecardGridView);        	
        	waitOnElement("cssSelector", ecardGridView);
        	clickOnElement ("cssSelector",ecardListView);   
        	           	
            }
    	
    	public void gridNdListViewInSavedCards(String category ,String savedCardName) throws Exception{
        	logInfo("Entered into gridNdListViewInSavedCards(String name) method"); 
        	selectcategory(category);        	
        	waitOnElement("cssSelector", ecardGridView);    		
        	clickOnElement ("cssSelector",ecardGridView);
        	WebElement tit = driver().findElement(By.cssSelector(ecardFirstTitle));
        	String actTitleInGrid = tit.getText();        	
        	Assert.assertEquals(actTitleInGrid, savedCardName);  
        	waitOnElement("cssSelector", ecardGridView);    		
        	clickOnElement ("cssSelector",ecardListView);
        	clickOnElement ("cssSelector",ecardListView);
        	           	
        	waitOnElement("cssSelector", ecardListTitle);  
        	WebElement listTit = driver().findElement(By.cssSelector(ecardListTitle));
        	String ActTitleInList = listTit.getText();        	
        	Assert.assertEquals(ActTitleInList, savedCardName);
        	clickOnElement ("cssSelector",ecardGridView);
           	
            }
    	
    	public void editEcardInListView(String ecardName) throws Exception{
    		boolean ispresent= false;    		
    		clickOnLink("linkText", savedCard);    		
    		
    		clickOnElement ("cssSelector",ecardListView);
    		     		
    		List <WebElement> crd = driver().findElements(By.cssSelector(listcards));
    		System.out.println("size is "+crd.size());
    		for (int i=1; i<=crd.size(); i++){
    			WebElement name = driver().findElement(By.cssSelector(cardNameBfr+i+cardNameAfr));    			
    			if (name.getText().equalsIgnoreCase(ecardName)){
    				ispresent = true;
    				 
    				waitOnElement("cssSelector", cardNameBfr+i+cardOptionsAfr);
    				WebElement more = driver().findElement(By.cssSelector(cardNameBfr+i+cardOptionsAfr));
    				more.click();
    				waitOnElement("cssSelector", cardNameBfr+i+cardOptionEdit);
    				WebElement edit = driver().findElement(By.cssSelector(cardNameBfr+i+cardOptionEdit));
    				edit.click();
    				    				
    				break;   				
    			}    			
    		}if (ispresent==false){
    			Assert.assertTrue(ispresent, ecardName + " - is not found in saved Card list" );
    		}
    		
    	}    	
    	
    	
    	public void deleteEcardInListView(String ecardName) throws Exception{
    		boolean ispresent= false;     	
    		waitOnElement("cssSelector",ecardListView);
    		scrollDown("cssSelector",ecardListView);
    		clickOnElement ("cssSelector",ecardListView);
    		clickOnElement ("cssSelector",ecardListView);
    		waitOnElement("cssSelector",listcards);
    		List <WebElement> crd = driver().findElements(By.cssSelector(listcards));    		
    		for (int i=1; i<=crd.size(); i++){
    			String name = driver().findElement(By.cssSelector(cardNameBfr+i+cardNameAfr)).getText().trim();    			
    			if (name.equalsIgnoreCase(ecardName)){
    				ispresent = true;    				
    				WebElement more = driver().findElement(By.cssSelector(cardNameBfr+i+cardOptionsAfr));
    				more.click();
    				waitOnElement("cssSelector", cardNameBfr+i+cardOptionDelete);
    				WebElement delete = driver().findElement(By.cssSelector(cardNameBfr+i+cardOptionDelete));
    				delete.click();
    				confirmOK();
    				confirmationMessage(ecardName+" was deleted successfully");    
    				waitOnSpinner();
    				break;	
    			}    			
    		}if (ispresent==false){
    			Assert.assertTrue(ispresent, ecardName + " - is not found in saved Card list" );
    		}
    		
    	}
    	
    	public void notToPresentSavedEcards(String ecardName) throws Exception{
       	 logInfo("Entered into notToPresentSavedEcards(String ecardName) method");
         	  boolean isTemptPresent= true;
         	  waitOnElement("linkText",savedCard);
         	  clickOnLink("linkText",savedCard);
         	  waitOnElement("cssSelector", savedTitles);
         	  List <WebElement> rows = driver().findElements(By.cssSelector(savedTitles));          	
         	  for (WebElement title: rows){
         		  System.out.println(title.getText());
         		  if (title.getText().equalsIgnoreCase(ecardName)){
         			 Assert.assertTrue(isTemptPresent, ecardName +" is still present in List");   	 
         			isTemptPresent =false;      					
         			break;         			  
         		  }         		  
         	  } if (isTemptPresent ==true){
         		  System.out.println("success!! not present.");      		   
         	  			}
           }    	
    	
    	public void validateSubject(String subject) throws Exception{
       	 logInfo("Entered into enterDetails(String subject) method");   
       	  enterDetails(subject);
       	  waitOnElement("cssSelector", readbleSubject);
          clickOnElement("cssSelector", readbleSubject);
       	  waitOnElement("cssSelector", readbleSubject);
       	  WebElement actSub = driver().findElement(By.cssSelector(readbleSubject));       
       	  Assert.assertEquals(actSub.getText(), subject);    	 	 
       	 
        }
    	
    	public void validateSubjectLength() throws Exception{
          	 logInfo("Entered into enterDetails(String subject) method");
          	 String subject = "123456"+TestBase.generateRandomNumberInRange(1000, 9999);
          	  
          	 waitOnElement ("cssSelector", ecardSub);
          	 inputTextClear("cssSelector", ecardSub);
          	// Test -1 Enters 250 characters and retrieves same size 
          	 for (int i=1; i<=25; i++){
          		 
          	 inputText("cssSelector", ecardSub, subject);
          	 }
          	   	
          	 waitOnElement ("cssSelector", readbleSubject);
             clickOnElement("cssSelector", readbleSubject);
          	         	 
          	 WebElement actSub = driver().findElement(By.cssSelector(ecardSub));          	 
          	 String subSize = actSub.getAttribute("value");
      	     int size =  subSize.length();
      	     if (size==250){
      	    	 System.out.println("Success!! Subject field is accepting max 250 characters");
      	     }else {
      	    	 Assert.assertEquals(size, 250);
      	    	 
      	     }   // Test-1 - Ends
      	     
      	     // Test -2 Enters more than 250 characters and retrieves 250 characters  size only. 
      	        
      	     waitOnElement ("cssSelector", ecardSub);
        	 inputTextClear("cssSelector", ecardSub);        	
        	 for (int i=1; i<=26; i++){
        	 waitOnElement("cssSelector", ecardSub);
        	 inputText("cssSelector", ecardSub, subject);
        	           }        	 
        	 waitOnElement ("cssSelector", readbleSubject);
        	 clickOnElement("cssSelector", readbleSubject);
        	 waitOnElement("cssSelector", ecardSub);
        	 WebElement actSub2 = driver().findElement(By.cssSelector(ecardSub));        	
        	 String subSize2 = actSub2.getAttribute("value");
    	     int size2 =  subSize2.length();
    	     if (size2==250){
    	    	 System.out.println("Success!! Subject field is accepting max 250 characters");
    	     }else {
    	    	 Assert.assertEquals(size2, 250);
    	    	 
    	     }      	     
    	        
        	 inputTextClear("cssSelector", ecardSub);   	
    	}
    	
    	public void gridNdLsitViewEcardSizes() throws Exception{
        	logInfo("Entered into gridNdLsitViewEcardSizes() method");
        	waitOnElement ("cssSelector", ecardGridView);
        	clickOnElement ("cssSelector",ecardGridView);
        	List<WebElement> gsize = driver().findElements(By.cssSelector(ecardFirstTitle));
        	int gridSize = gsize.size();        	
        	 
        	waitOnElement ("cssSelector", ecardListView);
        	clickOnElement ("cssSelector", ecardListView);
        	clickOnElement ("cssSelector",ecardListView);
        	  
        	waitOnElement ("cssSelector", thumb);
        	WebElement tbl = driver().findElement(By.cssSelector(thumb));
        	String table = tbl.getText().trim();
        	if (table.equals(thumbText)){
        		waitOnElement ("cssSelector", ecardListTitle);
            	List<WebElement> lSize = driver().findElements(By.cssSelector(ecardListTitle));
            	int listSize = lSize.size();            	
            	//Assert.assertEquals(gridSize, listSize);
            	//clickOnElement ("cssSelector",ecardGridView);
        		
        	}else{
        		Assert.assertEquals(table, thumbText);
        		}   	
            }	
    	
    	public void validateMail(String mailId) throws Exception{
        	logInfo("Entered into validateMail(String mailId, int noOfMail) method");        	
        	String mailId1 = "vibers"+TestBase.generateRandomString()+"@gmail.com";
        	String invalidmail = "vibers"+TestBase.generateRandomString();
       		            	 
           	clickOnButton("cssSelector", ecardNext); 
           	 
           	clickOnLink("linkText", "Add Manually");  
           	 
           	inputText("cssSelector", inputManualEmail, invalidmail); 
           	clickOnButton("cssSelector", addGrpAdd);
           	  
           	inputTextClear("cssSelector", inputManualEmail);           	
           	 
           	WebElement header = driver().findElement(By.cssSelector(headerMail));          
           	Assert.assertEquals(header.getText(), headerMailText);           	
           	inputTextClear("cssSelector", inputManualEmail);     
           	inputText("cssSelector", inputManualEmail, mailId);   
           	clickOnElement("cssSelector", moremailsLink);   
           	inputText("cssSelector", mailBfr+3+mailAfr, mailId1); 
           	 
           	clickOnLink("linkText", "Remove");
         	clickOnButton("cssSelector", addGrpAdd);
           	  
           	WebElement mailIds = driver().findElement(By.cssSelector(mailText));
           	String givenMailId = mailIds.getText();
           	System.out.println("givenMailId "+ givenMailId);
           	Assert.assertEquals(givenMailId, mailId+"x");
           	 
        	clickOnButton("cssSelector", ecardMailSend);
        	clickOnLink("linkText", "Start");         		
           		          		
           	}  
    	
    	public void assertPageObjects(String categoryName, String cardName) throws Exception{
    		logInfo("Entered into assertPageObjects() method");    	
    		WebElement library = driver().findElement(By.cssSelector(libTitle));
    		Assert.assertEquals(library.getText(), libTitleText);
    		WebElement seacrh = driver().findElement(By.cssSelector(ecardSearch));    	
    		Assert.assertEquals(seacrh.getAttribute("placeholder"), ecrdSearchPlaceHolder);
    		/*selectcatNdEcard(categoryName, cardName);
    		 
    		clickOnElement("cssSelector", subHint);
    		Actions act = new Actions(driver);
    		WebElement hints = driver().findElement(By.cssSelector(subHint));
    		act.moveToElement(hints).build().perform();
    		 
    		WebElement hintMessage = driver().findElement(By.cssSelector(subHint));
    		System.out.println("Hint mssgae is "+ hintMessage.getText());
    		Assert.assertEquals(hintMessage.getText(), hintMsg);*/
    		
    		
    		
    	}
    	
    	
    	public void searchEcard(String ecardName) throws Exception{
    		logInfo("Entered into searchEcard(String ecardName) method");    		
    		WebElement library = driver().findElement(By.cssSelector(libTitle));    		
    		Assert.assertEquals(library.getText(), libTitleText);
    		WebElement seacrh = driver().findElement(By.cssSelector(ecardSearch));
    		Assert.assertEquals(seacrh.getAttribute("placeholder"), ecrdSearchPlaceHolder);
    		inputTextClear("cssSelector", ecardSearch);
    		inputText("cssSelector", ecardSearch, ecardName);
    		 
    		submitElement("cssSelector", ecardSearch);
    		 
    	}
    	  
    	
    	public void shareIncommunity(String comment) throws Exception{ 
    		logInfo("Entered into shareIncommunity() method");    	
    		clickOnLink("linkText", "Share Ecard");
    		 
    		WebElement com = driver().findElement(By.cssSelector(RLComm));
    		Assert.assertEquals(com.getText(),RLCommText );
    		clickOnButton("cssSelector",commShare);		
    		 
    		verifyElementPresent("cssSelector",ecardComm);
    		inputText("cssSelector",ecardComm, comment);		
    		clickOnButton("cssSelector",commShareBtn);
    		confirmationMessage("This ecard has been shared to the community.");	
    		  		
    	}
    	
    	
    	public void shareInWebsite() throws Exception{
    		logInfo("Entered into shareInWebsite() method");  
    		waitOnSpinner();
    		waitOnElement("cssSelector", shareWebEcardTilte);    		
    		WebElement com = driver().findElement(By.cssSelector(shareWebEcardTilte));
    		Assert.assertEquals(com.getText(),webEcardTile );
    		clickOnButton("cssSelector",viewWebBtn);   		
    		
    		/*WebElement title = driver().findElement(By.cssSelector(webShareTitle));
    		WebElement urlPath = driver().findElement(By.cssSelector(shareLinktext));
    		Assert.assertEquals(title.getText(), webShareText);
    		String expectedurl = urlPath.getText();
    		System.out.println("expected url is "+expectedurl);
    		clickOnButton("cssSelector", viewWebsite);
    		//rl2.handleMyWebsite();
    		snw.closeSharePopUp();*/
    		
    	
    	}
    	
    	
    	public void avonCreateECard(String ecardName,String category, String childCategory ) throws Exception{
 		   logInfo("Entered into avonCreateECard() method ");		
 			clickOnButton("cssSelector", newCard); 			
 			nav2EcardTemp();
 			 
 			WebElement nam = driver().findElement(By.cssSelector(eName));
 			WebElement desc = driver().findElement(By.cssSelector(eDescrp));
 			nam.clear();
 			desc.clear();
 			nam.sendKeys(ecardName);
 			desc.sendKeys(ecardName);
 			avonSelectCatInThemeSetting(category,childCategory);
 			/*waitOnElement("cssSelector", browseBtn);			
 			clickOnElement("cssSelector", browseBtn);
 			 
 			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\coffeImage.exe"); 			
 			 
 			
 			clickOnElement("cssSelector", ePublish);
 			confirmationMessage("Ecard template was successfully created.");*/
 			
 			
 		}	
    	
    	
    	public void avonSelectCatInThemeSetting(String category, String childCategory) throws Exception{
        	logInfo("Entered into selectCatInThemeSetting() method ");
        	
        	selectCatLinkOptions("Categories");
        	avonSelectParentCateInThemeSettings(category);
        	//selectChildCateInThemeSettings(category, childCategory);
        }
    	
    	 public void avonSelectParentCateInThemeSettings(String category) throws Exception{
    			logInfo("Entered into avonSelectParentCateInThemeSettings() method ");    			
    			waitOnElement("cssSelector", AVeCatChk);
    			List <WebElement> cat = driver().findElements(By.cssSelector(AVeCatChk));    		
    			boolean isParentPresent = false;
    			for (int i=1; i<=cat.size(); i++){			
    				WebElement catNm = driver().findElement(By.cssSelector(AVeCatChkBfr+i+AVeCatChkAft));
    				String pCatgName  = catNm.getText().trim();    				
    				if (pCatgName.equalsIgnoreCase(category)){		
    							isParentPresent = true;
    							WebElement catNmChk = driver().findElement(By.cssSelector(AVeCatChkBfr+i+AVeCatChkBoxAft));
    							if (!catNmChk.isSelected()){
    								catNmChk.click();
    								 
    							}						
    							break;					
    						}					
    					}if(isParentPresent==false){
    						Assert.assertTrue(isParentPresent, category + " category is not present under theme settings");										
    					}				
    							
    		}
    	 
    	 public void selectHistoryList(String pastDays) throws Exception{		
    	  	  logInfo("Entered into selectHistoryList(String categoryName, String cardName) method");
    			boolean isCatPresent= false;
    			List <WebElement> lib = driver().findElements(By.cssSelector(hisList));    			
    			for (int i = 1; i<=lib.size(); i++ ){			
    				WebElement catName = driver().findElement(By.cssSelector(hisListBfr+i+hisListAfr));			
    				if (catName.getText().equalsIgnoreCase(pastDays)){
    					isCatPresent =true;    					
    					catName.click(); 
    					waitOnElement("cssSelector", ".panel-title");
    					break;				
    				}			
    			}if (isCatPresent==false){
    				Assert.assertTrue(isCatPresent, pastDays +" is not present in History list.");
    			}
    	    }
    	  
    	 
    	 public void verfiyHistoryCard(String cardName){
       	  logInfo("Entered into verfiyHistoryCard(String cardName) method");
       	  boolean isTemptPresent= false;    	  
       	  List <WebElement> rows = driver().findElements(By.cssSelector(hisCardrows));    	 
       	  for (int j = 1; j<=rows.size(); j++) {    		  
       		  List <WebElement> cardTemp =  driver().findElements(By.cssSelector(hisCardrowsBfr+j+hisCardrowsAft+eCardTemp));        	  
           	  for (int i=1; i<=cardTemp.size(); i++){    		  
           		  WebElement name = driver().findElement(By.cssSelector(hisCardrowsBfr+j+hisCardrowsAft+eCardTempBfr+i+eCardTempAfr));        		  
           		  if(name.getText().equalsIgnoreCase(cardName)){
           			  isTemptPresent=true;           			 
           			  break;    			  
           		  }  
       	  } 
         }  if (isTemptPresent ==false){
   		  Assert.assertTrue(isTemptPresent, cardName +" is not available in Template");   	  
         			}
         }	
    	 
    	 
    	 public void selectMoreOptionsOfEcardTemplate(String cardName, String moreOptionType) throws Exception{
       	  logInfo("Entered into selectMoreOptionsOfEcardTemplate(String cardName) method");
       	  boolean isTemptPresent= false;       	  
       	  List <WebElement> rows = driver().findElements(By.cssSelector(eCardrows));        	 
       	  for (int j = 1; j<=rows.size(); j++) {    		  
       		  List <WebElement> cardTemp =  driver().findElements(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTemp));
       		  for (int i=1; i<=cardTemp.size(); i++){ 
           		  waitOnElement("cssSelector", eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempAfr);
           		  WebElement name = driver().findElement(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempAfr));
           		  if(name.getText().equalsIgnoreCase(cardName)){
           			  isTemptPresent=true; 
           			  waitOnElement("cssSelector", eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempMoreOpt);
           			  WebElement moreOption =  driver().findElement(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempMoreOpt));
           			  moreOption.click();           			  
           		      boolean ismoreOptionPresent = false;
           		      waitOnElement("cssSelector", eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempMoreOptList);
           			  List <WebElement> moreOps = driver().findElements(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempMoreOptList));
           			  for (int k=1; k<=moreOps.size();k++){           				 
           				/*waitOnElement("cssSelector", eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempMoreOptListBfr+k+eCardTempMoreOptListAft);  */
           				WebElement optionName = driver().findElement(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempMoreOptListBfr+k+eCardTempMoreOptListAft));
           				String 	options = optionName.getText().trim();            				
           				if (options.equalsIgnoreCase(moreOptionType)){
           				ismoreOptionPresent = true; 
           					
           					switch(options){
           					case "Preview":
           						optionName.click();           						
           						break; 		
           						
           					case "Edit":
           						 optionName.click();  
           						  
           						/* waitOnElement("cssSelector", ePublish); 
	           					 String publish = driver().findElement(By.cssSelector(ePublish)).getText().trim();	
	           					        
	           					 Assert.assertEquals(publish, "Publish");*/               					
           						break;
           						
           					case "Delete": 
           						System.out.println("not o entered");
           						optionName.click();  
               					 
               					waitOnElement("cssSelector", deleteToOk);
               					waitOnElement("cssSelector", deleteToOk);
               					waitOnElement("cssSelector", deleteToOk);            					
               					WebElement ok = driver().findElement(By.cssSelector(deleteToOk));           					
               					ok.click(); 
           						break;           					
           					} break;		  
           				}  
           			  }if (ismoreOptionPresent==false){
           				  Assert.assertTrue(ismoreOptionPresent, moreOptionType + " option is not present under toogle moreoptions");
           			  }
           			  break;    			  
           		  }  
       	  } 
         }  if (isTemptPresent ==false){
   		  Assert.assertTrue(isTemptPresent, cardName +" is not available in Templatelist");   	  
         			}
         }
    	 
    	 
    	
    	 
    	 public void templateNotToPresent(String cardName) throws Exception{
       	  logInfo("Entered into templateNotToPresent(String cardName) method");
       	  boolean isTemptPresent= true;
       	  waitOnElement("cssSelector", eCardrows);       	
       	  List <WebElement> rows = driver().findElements(By.cssSelector(eCardrows));        	 
       	  for (int j = 1; j<=rows.size(); j++) {
       		  waitOnElement("cssSelector", eCardrowsBfr+j+eCardrowsAft+eCardTemp);
       		  List <WebElement> cardTemp =  driver().findElements(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTemp));        	  
           	  for (int i=1; i<=cardTemp.size(); i++){    		  
           		  WebElement name = driver().findElement(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempAfr));        		  
           		  if(name.getText().equalsIgnoreCase(cardName)){
           			  isTemptPresent=false;     
           			  Assert.assertTrue(isTemptPresent, cardName +" is still present in Templatelist");           			  
           			  break;    			  
           		  }  
           	  } 
         }  if (isTemptPresent ==true){
        	 System.out.println(cardName+ " is not present.");
   		    	  
         			}
         }
    	 
    	 public void handleWebsiteWindow() throws Exception {  		 
    	  logInfo("Entered into handleWebsiteWindow() method");
    	 String wndBeforeWindow = driver().getWindowHandle();	    	 
 			for(String w : driver().getWindowHandles()){ 							
 				if(!w.equalsIgnoreCase(wndBeforeWindow)){
 					driver().switchTo().window(w);	  					
 					  	 					
 					waitOnElement("xpath", close2NdWind);
 					clickOnElement("xpath",close2NdWind); 					
 					driver().close();					
 					driver().switchTo().window(wndBeforeWindow);  
 					waitOnElement("xpath", close2NdWind2);
 					clickOnElement("xpath",close2NdWind2); 								
 				}else { 					
 					waitOnElement("xpath", close2NdWind);
 					clickOnElement("xpath",close2NdWind); 	
 					driver().switchTo().window(wndBeforeWindow);  
 					waitOnElement("xpath", close2NdWind2);
 					clickOnElement("xpath",close2NdWind2); 	
 				}
 			}
    		 
    	 }
    	 
    	 public void optionsSettingsNotToPresent(String option) throws Exception {
    		 logInfo("Entered into selectOptions(String option) method");
    		 waitOnElement("cssSelector", ecardOpt);
    		 clickOnElement("cssSelector", ecardOpt);
    		 boolean isFound =true;
    		 waitOnElement("cssSelector", ecardOptList);
    		 List<WebElement> opt = driver().findElements(By.cssSelector(ecardOptList));
    		 for(WebElement opts:opt) {
    			 String actOption= opts.getText().trim();    			 
    			 if(actOption.equalsIgnoreCase(option)) {
    				 isFound=false;    						 
    				 waitOnElement("cssSelector", ecardOpt);
    				 Assert.assertTrue(isFound, optSettingsPost + "is still present");
    				 break;
    			 }
    		 }if(isFound==true){
    			 System.out.println("Is not present");
    		 }					 
    	 }
    	 
    	 
    	 public void selectOptions(String option) throws Exception {
    		 logInfo("Entered into selectOptions(String option) method");
    		 waitOnElement("cssSelector", ecardOpt);
    		 clickOnElement("cssSelector", ecardOpt);
    		 boolean isFound =false;
    		 waitOnElement("cssSelector", ecardOptList);
    		 List<WebElement> opt = driver().findElements(By.cssSelector(ecardOptList));
    		 for(WebElement opts:opt) {
    			 String actOption= opts.getText().trim();    			 
    			 if(actOption.equalsIgnoreCase(option)) {
    				 isFound=true;
    				 opts.click();				 
    				 waitOnElement("cssSelector", ecardOpt);
    				 break;
    			 }
    		 }if(isFound==false) {
    			 Assert.assertTrue(isFound, option+ " - option is not found");
    		 }    		 
    	 }
    	 
    	 public void sharetoCommunity(String text) throws Exception {
    		 logInfo("Entered into sharetoCommunity() method");
    		 waitOnSpinner();
    		 waitOnElement("cssSelector", shareComment);
    		 inputText("cssSelector", shareComment, text);
    		 clickOnElement("cssSelector", shareComBtn);
    		 confirmationMessage("This ecard has been shared to the community.");
    	 
    	 }
    	 
    	 public void advanceSettingOfPWP(String pwpUrl) throws Exception {
    		 logInfo("Entered into advanceSettingOfPWP() method");
    		 waitOnSpinner();
    		 waitOnElement("cssSelector", selectPWP);    		
    		 selectFromDropdown("cssSelector", selectPWP, "byVisibleText", pwpUrl); 
    		 
    	 }
    	 
    	 public void selectPublish() throws Exception {
    		 logInfo("Entered into selectPublish() method");
    		 scrollDown("cssSelector", eRePublish);    
             waitOnElement("cssSelector", eRePublish);            
             clickOnElement("cssSelector", eRePublish);
             confirmationMessage("Ecard template was successfully updated.");
    		 
    	 }
    	 
    	 public void selectAsDraft() throws Exception {
    		 logInfo("Entered into selectAsDraft() method");
    		 scrollDown("cssSelector", eRePublish); 
             waitOnElement("cssSelector", eDarft);
             clickOnElement("cssSelector", eDarft);
             confirmationMessage("Ecard template was successfully updated.");
             }
    	 
    	 
    	 public void enableOrDisableLibrarySettings(String expectedSettingsLink, String status) throws Exception {
    		 logInfo("Entered into enableOrDisableLibrarySettings() method");
    		 waitOnElement("xpath", setList);
    		 boolean isPresent =false;
    		 List<WebElement> li = driver().findElements(By.xpath(setList));    		 
    		 for(WebElement listSetting: li) {
    			 String settingText = listSetting.getText().trim();    			 
    			 if(settingText.equalsIgnoreCase(expectedSettingsLink)){
    				 isPresent =true;
    				 listSetting.click();    
    				 waitOnSpinner();
    				 selectFromDropdown("cssSelector", setValue, "byVisibleText", status);
    				 clickOnElement("cssSelector", setUpdateBtn);
    				 confirmationMessage("Ecard Library Setting Updated Successfully.");
    				 break;    				 
    			 }    			 
    		 }if(isPresent==false) {
    			 Assert.assertTrue(isPresent, expectedSettingsLink+ " is not present.");
    		 }   		 
    	 }
    	 
    	 public void verifyLibraryFilterSettings() throws Exception {
    		 logInfo("Entered into verifyLibraryFilterSettings() method");
    		 waitOnElement("cssSelector", ecardFilters);
    		 List <WebElement> filt = driver().findElements(By.cssSelector(ecardFilters));
    		 int actFilters = filt.size();
    		 if(actFilters==2){
    			 Assert.assertEquals(actFilters, 2);    			 
    		 }else {
    			 Assert.assertEquals(actFilters, 2);
    		 }    		 
    		 
    	 }
    	 
    	 
    	 public void socialNetworkIconValidation() throws Exception{
    		 logInfo("Entered into socialNetworkIconValidation() method");
        	 waitOnElement("cssSelector", socialNetworkInEcard);
    		 List <WebElement> filt = driver().findElements(By.cssSelector(socialNetworkInEcard));
    		 int icons = filt.size();    		
    		 if(icons==1){
    			 Assert.assertEquals(icons, 1);    			 
    		 }else {
    			 Assert.assertEquals(icons, 1);
    		 }  
    		 
    		 
    	 }
    	 
    	
    	 
    	 
       	 
    	 
 
	
}
	
