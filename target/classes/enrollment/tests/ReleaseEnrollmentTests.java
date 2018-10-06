package vibe.enrollment.tests;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import common.EnvProperties;
import common.Priority;
import vibe.users.tests.UsersMethods;

import org.testng.Assert;


//TESTS WRITTEN FOR Stage2 ENVIRONMENT (FOR MONAT)

@Priority(102)
public class ReleaseEnrollmentTests extends ReleaseEnrollmentMethods {
	Logger logger = Logger.getLogger(ReleaseEnrollmentTests.class);
	EnvProperties prop = new EnvProperties();
	UsersMethods um = new UsersMethods();
	
	 String vipMarket = "Market Partner - US";
	 String retailMarket = "Market Partner - US";
	 String EnrollMPMarket = "Market Partner - US";   // "Market Partner - US";		 
	 String EnrollVIPMarket = "VIP Enrollment - US";	
	 String EnrollUSRetialMarket = "Retail Enrollment - US";	
	 String strFlexshipDate =  getDateByMonth(2, "MM/dd/yyyy");
	 
	 
	 // Enroll Market Partner
	 
	 @Test(priority =10202)
	 public void EnrollMPUSMarket() throws Exception{
		 logInfo("inside EnrollMPUSMarket Test");
		
		 String essn = prop.getLocatorForEnvironment(appUrl,"MPUSESSN01")+num;
		 String email = prop.getLocatorForEnvironment(appUrl,"EnrollMPUSEmail01")+num+"@icentris.com";
		 go2HomePage();
	
		 get2EnrollPage(prop.getLocatorForEnvironment(appUrl, "EnrollPageLayout"),"MP","Market Partner - US New");
		
		 selectMPProductPackSelection(prop.getLocatorForEnvironment(appUrl, "EnrollMPChkProducts2Cart"),prop.getLocatorForEnvironment(appUrl, "EnrollMPChkProducts2Flex"));
		
		 // validateMPPersonalInfoWithInvalidData("US");   // uncomment later
		 updateMPPersonalInfo("US", essn, email);
		 addMarketPartnerBillingInformation();
		 submitAndReviewMarketingPartner(essn, email);
		 boolean isEnrollSuccessful = verifyEnrollmentSuccessfulAtUser();
		 if(isEnrollSuccessful==true){
			 logInfo("Enrollment is successful");
			 readConsultantID2File("MP","US");
			 Thread.sleep(5000);
		 } else {
			 logInfo("Enrollment is not successful");
			 Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
		 }   
	 }
	 
	 
	 
	 @Test(priority =10203)
	 public void EnrollMPUSAddationalProducts() throws Exception{
		 logInfo("inside EnrollMPUSAddationalProducts Test");
		 String essn = prop.getLocatorForEnvironment(appUrl,"MPUSAPESSN01")+num;
		 String email = prop.getLocatorForEnvironment(appUrl,"EnrollMPUSAPEmail01 ")+num+"@icentris.com";
		 
		 go2HomePage();
		 get2EnrollPage(prop.getLocatorForEnvironment(appUrl, "EnrollPageLayout"),null,"Market Partner - US New");
			
		 selectMPProductPackSelection(prop.getLocatorForEnvironment(appUrl, "EnrollMPChkProducts2Cart2"),prop.getLocatorForEnvironment(appUrl, "EnrollMPChkProducts2Flex2"));
		 /*String isAddProd2ToCartSelected = prop.getLocatorForEnvironment(appUrl, "EnrollMPChkProducts2Cart2");
		 if(isAddProd2ToCartSelected.equalsIgnoreCase("yes")) {
			 addVolumeSystem4Add2Cart();
			 validateVolumeSystem4Add2Cart();   // uncomment later
		 }*/
		 String isAddProd2ToFlexSelected = prop.getLocatorForEnvironment(appUrl, "EnrollMPChkProducts2Flex2");
		 if(isAddProd2ToFlexSelected.equalsIgnoreCase("yes")) {
			 addVolumeSystem4Add2Cart();
			 validateUSMPVolumeSystem4Flex();   // uncomment later
		 }
		 
		 clickOnElement("linkText", "Continue");
			Thread.sleep(10000);
		
		 //validateMPPersonalInfoWithInvalidData();
		 updateMPPersonalInfo("US", essn, email);
		 addMarketPartnerBillingInformation();
		 //submitAndReviewMarketingPartner();
		 // submitAndReviewMPWithAddationalProducts();
		 
		  submitAndReview4AddationalProdsMarketingPartner(essn, email); 
		 boolean isEnrollSuccessful = verifyEnrollmentSuccessfulAtUser();
		 if(isEnrollSuccessful==true){
			 logInfo("Enrollment is successful");
		 } else {
			 logInfo("Enrollment is not successful");
			 Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
		 }  
	 }
	 
		 
	// Enroll VIP User
	 
	 @Test(priority =10204)
	 public void EnrollVIPUSMarket01() throws Exception{
		 logInfo("inside EnrollVIPUSMarket01 Test");
		 
		 String email = prop.getLocatorForEnvironment(appUrl,"EnrollVIPUSEmail01")+num+"@icentris.com";
		
		 go2HomePage();
		 get2EnrollPage(prop.getLocatorForEnvironment(appUrl, "EnrollPageLayout"),"Enroll VIP","VIP Enrollment - US");
			
		 validateVIPProductsPageWithEmptyProducts();
		
		 addVolumeSystem4Add2Cart();
		 validateVIPVolumeSystem2Cart4USMarket();
		 
		 addVolumeSystem4Add2Cart();
		 validateVIPFlexVolumeSystem4USMarket();
		 
		 validateVIPPersonalInfoWithInvalidData();
		 submitVIPPersonalInfo("US", email);  // New
		 addNewVIPBillingInformation();
		 reviewAndSubmitVIP("US", email);
		 boolean isEnrollSuccessful = verifyEnrollmentSuccessfulAtUser();
		 if(isEnrollSuccessful==true){
			 logInfo("Enrollment is successful");
			 readConsultantID2File("VIP","US");
		 } else {
			 logInfo("Enrollment is not successful");
			 Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
		 } 
	 }
	 
	 
	 @Test(priority =10205) // dependsOnMethods{"EnrollVIPUser"}
	 public void UpgradeVIP1Customer2MP() throws Exception{
		 logInfo("inside UpgradeVIP1Customer2MP Test");
		 
		 String essn = prop.getLocatorForEnvironment(appUrl,"VIPUSESSN01")+num;
		 
		 userLogout();   // uncomment later
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"NewConUSVIP"));
		 selectToolsMenu("Upgrade to Market Partner");
		 selectHighestPackInStarterKit();
		 updateVIPPersonalInformation(essn);
		 updateRetailBillingAndShipping();
		 updateRetailReviewAndSubmit();
		 
		 boolean isMenuFound = validateLinkPresentInToolsMenu("Enroll MP");
		 userLogout();
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"newsCon1"));   // ++ added login here
		 Assert.assertTrue(isMenuFound, "Enroll MP link not found in Tools menu.");
	 }
	 
	// Enroll Retial User
	 
	 @Test(priority =10207)
	 public void EnrollRetialUSMarket01() throws Exception{
		 logInfo("inside EnrollRetialUSMarket01 Test");
		 String email = prop.getLocatorForEnvironment(appUrl,"EnrollRETUSEmail01")+num+"@icentris.com";
		 go2HomePage();
		
		 get2EnrollPage(prop.getLocatorForEnvironment(appUrl, "EnrollPageLayout"),null,EnrollUSRetialMarket);
		 updateRetailPersonalInformation("US", email);
		 addRetialAddationalProducts2Cart();
		 addRetailBillingInformation();
		 reviewAndSubmitRetailWithAddationalProducts("US", email);
		 
		boolean isEnrollSuccessful = verifyEnrollmentSuccessfulAtUser();
		 if(isEnrollSuccessful==true){
			 logInfo("Enrollment is successful");
			 readConsultantID2File("Retail","US");
		 } else {
			 logInfo("Enrollment is not successful");
			  Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
		 } 
	 }
	 
	 
		 
	 // Upgrade US Retail User 2 VIP,   FAILS as it does not show upgrade link to MP after upgrade
	 
	 @Test(priority =10208) // dependsOnMethods{"EnrollRetialUser"}
	 public void UpgradeRetailCustomer2VIP() throws Exception{
		 logInfo("inside UpgradeRetailCustomer2VIP Test");
		 
		 userLogout();   // uncomment later
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"NewConUSRetail"));
		 selectUpgrade2VIP();
		// validateLinkPresentInToolsMenu("Upgrade to VIP");  // Tools menu does not have menu dropdown. This method does not work.
		
		 selectToolsMenu("Upgrade to VIP");
		 updateRetialPersonalInformation4VIP();
		 selectProductByNameOrSKUbyRetailUser();
		 selectNextPage();
		 updateRetailReviewAndSubmit();
		 boolean isUpgrade2MPLinkFound = validateLinkPresentInToolsMenu("Upgrade to Market Partner");
		 userLogout(); 
		 if(isUpgrade2MPLinkFound) {
			 logInfo("Upgrade to Market Partner link found after upgrade to VIP" ); 
		 } else {
			 logInfo("Upgrade to Market Partner link found not after upgrade to VIP" ); 
			 Assert.assertTrue(isUpgrade2MPLinkFound, "Upgrade to Market Partner link found not after upgrade to VIP");
		 }
		  
		
	 }
	 
	
	 
	 @Test(priority =10209)  // dependsOnMethods{"UpgradeRetailCustomer2VIP"}
	 public void UpgradeVIPCustomer2MP() throws Exception{
		 logInfo("inside UpgradeVIPCustomer2MP Test");
		 
		 String essn = prop.getLocatorForEnvironment(appUrl,"VIPUSESSN02")+num; 
		
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"NewConUSRetail"));
		
		// selectUpgradeVIP2MemberPartner();   // remove later old code
		 selectToolsMenu("Upgrade to Market Partner");
		 selectHighestPackInStarterKit();
		 updateVIPPersonalInformation(essn);
		 updateRetailBillingAndShipping();
		 updateRetailReviewAndSubmit();
		 driver().navigate().refresh();
		 boolean isMenuFound = validateLinkPresentInToolsMenu("Enroll MP");
		 userLogout();
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"newsCon1"));  // ++ added login
		 Assert.assertTrue(isMenuFound, "Enroll MP not found in Tools menu after upgrade to MP");
	}
	 
	 
	 
	 @Test(priority =10210)
	 public void EnrollRetialUSMarket02() throws Exception{
		 logInfo("inside EnrollRetialUSMarket02 Test");
		 String email = prop.getLocatorForEnvironment(appUrl,"EnrollRETUSEmail02")+num+"@icentris.com";
		 
		 	go2HomePage();
			get2EnrollPage(prop.getLocatorForEnvironment(appUrl, "EnrollPageLayout"),null,"VIP Enrollment - US");
		
			updateRetailPersonalInformation("US", email);
			 addRetialAddationalProducts2Cart();
			 addRetailBillingInformation();
			 reviewAndSubmitRetailWithAddationalProducts("US", email);
			 
			boolean isEnrollSuccessful = verifyEnrollmentSuccessfulAtUser();
			 if(isEnrollSuccessful==true){
				 logInfo("Enrollment is successful");
				 readConsultantID2File("Retail","US");
			 } else {
				 logInfo("Enrollment is not successful");
				  Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
			 } 
	 }
	 
	 
	 
	 @Test(priority =10212)   
	 public void UpgradeRetailCustomer2MP() throws Exception{
		 logInfo("inside UpgradeRetailCustomer2MP Test");
	
		// String essn = prop.getLocatorForEnvironment(appUrl,"EnrollMPUSAddProdEssn")+num;
		 userLogout();  
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"NewConUSRetail"));
		
		 selectUpgrade2MemberPartner();
		 selectHighestPackInStarterKit();
		 //selectStarterKit(prop.getLocatorForEnvironment(appUrl, "retialStarterKitSelection"));
		 upgradePersonalInfoRetail2MP("US");
		 updateUSBillingRetail2MPUpgrade();
		 updateRetailReviewAndSubmit();
		 boolean isMenuFound = validateLinkPresentInToolsMenu("Enroll MP");
		 userLogout();
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"newsCon1"));  // ++ added login
		 Assert.assertTrue(isMenuFound, "Enroll MP link match not found.");  // uncomment later
	 }
	 
	
	 
	// ########################## CANADA MARKAT ###############################################
	 
	 @Test(priority =10215)
	 public void EnrollMPCAMarket() throws Exception{
		 logInfo("inside EnrollMPCAMarket Test");
		 String essn = prop.getLocatorForEnvironment(appUrl,"MPCAESSN01")+num;
		 String email = prop.getLocatorForEnvironment(appUrl,"EnrollMPCAEmail01")+num+"@icentris.com";
				 
		 go2HomePage();
		 
		 get2EnrollPage(prop.getLocatorForEnvironment(appUrl, "EnrollPageLayout"),"Enroll MP","Market Partner - CA");
		 selectMPProductPackSelectionCAMarket(); 
		 validateMPPersonalInfoWithInvalidData("CA");
	     updateMPPersonalInfo("CA", essn, email);
	 	 addCanadaMarketPartnerBillingInformation();
	 	 submitAndReviewMP4CAMarket(essn, email);
		 boolean isEnrollSuccessful = verifyEnrollmentSuccessfulAtUser();
		 if(isEnrollSuccessful==true){
			 logInfo("Enrollment is successful");
			 readConsultantID2File("MP","Canada");
		 } else {
			 logInfo("Enrollment is not successful");
			 Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
		 }   
	 }
	 
	 	
	 
	 @Test(priority =10216)
	 public void EnrollMPCAAddationalProducts() throws Exception{
		 logInfo("inside EnrollMPCAAddationalProducts Test");
		 String essn = prop.getLocatorForEnvironment(appUrl,"MPCAAPESSN01")+num;
		 String email = prop.getLocatorForEnvironment(appUrl,"EnrollMPCAAPEmail01")+num+"@icentris.com";
			
		 go2HomePage();
		 get2EnrollPage(prop.getLocatorForEnvironment(appUrl, "EnrollPageLayout"),"MP","Market Partner - CA");
			
		 selectMPProductPackSelectionCAMarket(); 
		// validateMPPersonalInfoWithInvalidData();
		 updateMPPersonalInfo("CA", essn, email); 
	 	 addCanadaMPAddationalProds4BillingInfo();
	 	 addAddationalProds4MPCAMarket();
	 	 submitAndReviewAddationalProdsMP4CAMarket(essn, email);
		 boolean isEnrollSuccessful = verifyEnrollmentSuccessfulAtUser();
		 if(isEnrollSuccessful==true){
			 logInfo("Enrollment is successful");
		 } else {
			 logInfo("Enrollment is not successful");
			 showAlertMessages();
			 Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
		 }   
	 }
	 
	 
	 @Test(priority =10217)
	 public void EnrollVIPCAMarket01() throws Exception{
		 logInfo("inside EnrollVIPCAMarket01 Test");
		 
		 String email = prop.getLocatorForEnvironment(appUrl,"EnrollVIPCAEmail01")+num+"@icentris.com";
		 go2HomePage();
		 get2EnrollPage(prop.getLocatorForEnvironment(appUrl, "EnrollPageLayout"),"VIP","VIP Enrollment - CA");
			
		 validateVIPPersonalInfoWithInvalidData();
		 submitVIPPersonalInfo("CA", email);  // New
		// validateVIPProductsPageWithEmptyProducts();  // msg changed
		 setFlexshipDate("05/06/2018");   // not accepting the current date
		 addVolumeSystem4Add2Cart();
	  	 validateVIPVolumeSystem2Cart4CanadaMarket();
		 addVolumeSystem4Add2Flex();
		 validateVIPVolumeSystem4Flex4CanadaMarket();
		 addNewVIPBillingInfo4CanadaMarket();
		 reviewAndSubmitVIP("CA", email);   
		 boolean isEnrollSuccessful = verifyEnrollmentSuccessfulAtUser();
		 if(isEnrollSuccessful==true){
			 logInfo("Enrollment is successful");
			 readConsultantID2File("VIP","Canada");
		 } else {
			 logInfo("Enrollment is not successful");
			 showAlertMessages();
			 Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
		 } 
	 }
	 
	 
	 @Test(priority =10218) // dependsOnMethods{"EnrollRetialUser"}
	 public void UpgradeVIP2MPCanadaMarket() throws Exception{
		 logInfo("inside UpgradeVIP2MPCanadaMarket Test");
		 String essn = prop.getLocatorForEnvironment(appUrl,"MPCAESSN02")+num;
		 
		 userLogout();   
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"NewConCAVIP"));
		 //selectUpgradeVIP2MemberPartner();  // remove later
		 selectToolsMenu("Upgrade to Market Partner");
		 selectHighestPackInStarterKit();
		 updateVIPPersonalInformation(essn);
		 updateRetailBillingAndShipping();
		 updateRetailReviewAndSubmit();
		 driver().navigate().refresh();
		 boolean isMenuFound = validateLinkPresentInToolsMenu("Enroll MP");
		 userLogout();
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"newsCon1"));  // ++ added login
		  Assert.assertTrue(isMenuFound, "Enroll MP not found in Tools menu after upgrade to MP");
		 
	 }
	 
	 
	 
	 @Test(priority =10219)
	 public void EnrollRetialCAMarket01() throws Exception{
		 logInfo("inside EnrollRetialCAMarket01 Test");
		 
		 String email = prop.getLocatorForEnvironment(appUrl,"EnrollRETCAEmail01")+num+"@icentris.com";
		 go2HomePage();
		 get2EnrollPage(prop.getLocatorForEnvironment(appUrl, "EnrollPageLayout"),"Retail","Retail Enrollment - CA");
			
		 updateRetailPersonalInformation("CA", email);
		 addRetialAddationalProducts2Cart4CanadaMarket();
		 addCanadaRetailBillingInfo();
		 reviewAndSubmitRetailWithAddationalProducts("CA", email);
		boolean isEnrollSuccessful = verifyEnrollmentSuccessfulAtUser();
		 if(isEnrollSuccessful==true){
			 logInfo("Enrollment is successful");
			 readConsultantID2File("Retail","Canada");
		 } else {
			 logInfo("Enrollment is not successful");
			 Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
		 } 
	 }
	
	 
	 @Test(priority =10212)   // under development, clarify issues
	 public void UpgradeCARetailCustomer2MP() throws Exception{
		 logInfo("inside UpgradeCARetailCustomer2MP Test");
	
		// String essn = prop.getLocatorForEnvironment(appUrl,"EnrollMPUSAddProdEssn")+num;
		 userLogout();   // uncomment later
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"NewConCARetail"));
		
		 selectUpgrade2MemberPartner();
		 selectHighestPackInStarterKit();
		 //selectStarterKit(prop.getLocatorForEnvironment(appUrl, "retialStarterKitSelection"));
		 upgradePersonalInfoRetail2MP("CA");
		 updateUSBillingRetail2MPUpgrade();
		 updateRetailReviewAndSubmit();
		 boolean isMenuFound = validateLinkPresentInToolsMenu("Enroll MP");
		 userLogout();
		 Assert.assertTrue(isMenuFound, "Enroll MP link match not found.");  // uncomment later
	 }
	 
	 
 // Upgrade Canada Retail User
	 
	 @Test(priority=10220) // dependsOnMethods{"EnrollRetialUser"}
	 public void UpgradeCARetailCustomer2VIP() throws Exception{
		 logInfo("inside UpgradeCARetailCustomer2VIP Test");
		 
		 userLogout();   // uncomment later
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"NewConCARetail"));
		 selectUpgrade2VIP();
		 updateRetialPersonalInformation4VIP();
		 selectProductByNameOrSKUbyRetailUser();
		 selectNextPage();
		 updateRetailReviewAndSubmit();
		 userLogout();  
		 um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"NewConCARetail"));
		 validateUpgrade2MPLinkPresent();
	 }
	 
	
	 
	 @Test(priority =10221)  // dependsOnMethods{"UpgradeRetailCustomer2VIP"}
	 public void UpgradeCAVIPCustomer2MP() throws Exception{
		 logInfo("inside UpgradeCAVIPCustomer2MP Test");
		 String essn = prop.getLocatorForEnvironment(appUrl,"EnrollMPUSAddProdEssn")+num;
		 // um.loginWithConsultantID(prop.getLocatorForEnvironment(appUrl,"NewConUSRetail"));  // remove later
		
		 //selectUpgradeVIP2MemberPartner();
		 selectToolsMenu("Upgrade to Market Partner");
		 selectHighestPackInStarterKit();
		 updateVIPPersonalInformation(essn);
		 updateRetailBillingAndShipping();
		 updateRetailReviewAndSubmit();
		 //validateMPLinkPresent();
		 boolean isMenuFound = validateLinkPresentInToolsMenu("Enroll MP");
		 userLogout();
		 Assert.assertEquals(true, isMenuFound);
	}
	 
	 
	 
	 // ################################# UK Market ############################################
	 
	 @Test(priority =10230)
	 public void EnrollMPUKMarket() throws Exception{
		 logInfo("inside EnrollMPUKMarket Test");
		 
		 String essn = prop.getLocatorForEnvironment(appUrl,"MPUKESSN01")+num;
		 String email = prop.getLocatorForEnvironment(appUrl,"EnrollMPUKEmail01")+num+"@icentris.com";
		
		 go2HomePage();
		 get2EnrollPage(prop.getLocatorForEnvironment(appUrl, "EnrollPageLayout"),"MP","Market Partner - UK");
			
		 selectMPProductPackSelectionUKMarket(); 
		 
	     //validateMPPersonalInfoWithInvalidData();
		 updateMPPersonalInfo("UK", essn , email);
	 	 addUKMarketPartnerBillingInformation();
	 	 submitAndReviewMP4UKMarket(essn, email);
		 boolean isEnrollSuccessful = verifyEnrollmentSuccessfulAtUser();
		 if(isEnrollSuccessful==true){
			 logInfo("Enrollment is successful");
			 readConsultantID2File("MP","UK");
		 } else {
			 logInfo("Enrollment is not successful");
			 Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
		 }   
	 }
	 
	 	
	 
	 @Test(priority =10240)
	 public void CorporateEnrollment2MP() throws Exception{
		 logInfo("inside CorporateEnrollment2MP Test");
		 String uname = "Allison Smith";
		 go2CorpEnrollmentPage();
		 joinNowMP();
		 boolean isUserFound = searchMPLookUp(uname,"");
		 if(isUserFound==true) {
			 selectMPLookUp(uname,"");
		 } else {
			 Assert.assertTrue(isUserFound, uname + " user match not found in MP Lookup");
		 }
	 }
	
	
}