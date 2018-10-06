package vibe.reports.tests;



import org.testng.annotations.Test;
import org.openqa.selenium.By;
import common.Priority;
import common.EnvProperties;
import vibe.ecards.tests.EcardMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.profile.account.ProfileAccountMethods;
import vibe.tasks.tests.TaskMethods;
import vibe.users.tests.UsersMethods;


@Priority(134)
public class AVNReportsTests extends AVNReportsMethods{
	
	TaskMethods tk = new TaskMethods();
	NewsMethods nm = new NewsMethods();
	MyProfileMethods profile = new MyProfileMethods();
	UsersMethods um = new UsersMethods();
	EnvProperties prop  = new EnvProperties();
	EcardMethods em = new EcardMethods();
	ProfileAccountMethods accounts = new ProfileAccountMethods();
	

	// please check Campaign From data before execution
/*	String leaderFrom = "Non-Ldsp";
	String leaderTo = "Platinum Executive Leader"; 
	String campFrom = "C 13 (T/S 15) 2017";
	String campTo = "C 14 (T/S 16) 2017";
	
	String presidentClubFrom = "President's Club";
	String presidentClubTo = "Inner Circle";	
	
	
	String titleCampaign2 = "Campaign";	
	String titleDiscontinueCamp = "Number of Campaigns Until Discontinue Between";
	String discontFrom= "0";
	String discontTo= "9";*/
	
	 
	
	
	String parentEcard = "Blank Letterhead";
    String ecardtempName = "ecard 11"; 
	String ReportName= "3-Day Follow Up"; 
	 
	String pwd = "avonrocks1";	
	String columnName1 = "Acct. Num";
	String columnName2 = "Account Number";
	String columnName3 = "Rep ID";
	String columnName5 = "Acct. Num.";
	String columnName6= "Acct. No.";
	
	
	int generationTo = 3;
	String titleCampaign = "Campaign From";
	String titleLeaderBetween = "Current Leadership Title Between";	
	String titlePresidentClub = "PC Level From";
	String birthday = "Birth Date";
	
	
	//Verifies all reports with their table and title names.
	
	/*	@Test(priority=13401)
		public void validateAllTablesWithTitles() throws Exception{
			 logInfo("Entered into validateAllTablesWithTitles() test");		
			 navigateReports();	
			 validateTableTitles();		
		}
				
		//Reports- Select DownLine Report, Send bulk emails to all Downline usres. 
		@Test(priority=13402)
		public void sendBulkEmailToDownlineUsers() throws Exception{
			 logInfo("Entered into sendBulkEmailToDownlineUsers() test");			
			navigateReports();
			reportstable(ReportName) ;
			contactList("Send Bulk Email");
			sendBulkMail(subjectBulk);
		    confirmationMessage("Email sent");		 
		}


		@Test(priority=13403)
		public void createEventFromReports() throws Exception{
			 logInfo("Entered into createEventFromReports() test");			
			 navigateReports();		
			 reportstable(ReportName) ;			 
			 contactList("Create Event");
			 createEvent(EventText, 10);	
			
			
		}
		
		@Test(priority=13405)
	     public void sendEcardFromReports() throws Exception{
			logInfo("Entered into sendEcardFromReports() test");
		
		profile.navigateMyProfileAccount("Notifications");
		String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
		System.out.println(selfmailId);
		navigateReports();			
		reportstable(ReportName) ;
		contactList("Send Ecard");
		em.selectcatNdEcard(parentEcard, ecardtempName);
		em.enterDetails(ecardCatSubText);
		em.sendManualMail(selfmailId, ecardCatSubText2);	
			
		}
		*/
			
		
	
	@Test(priority=13406)
    public void validateReportsSetting() throws Exception{
		logInfo("Entered into validateReportsSetting() test");
		profile.navigateMyProfileAccount("Report Settings");
		accounts.reportSetting("Constraints Page");                      //Report Output
		navigateReports();
		reportstable(ReportName);
		validateConstriantsTitle();
		profile.navigateMyProfileAccount("Report Settings");
		accounts.reportSetting("Report Output");                      //Report Output		
		navigateReports();
		reportstable(ReportName);
		constriantTitleNotTobePresent();
		System.out.println("failed");	
	
		}
		
		
		@Test(priority=13407)
	     public void verifyAnniversariesReport() throws Exception{
			logInfo("Entered into verifyAnniversariesReport() test");		
			navToProdTable();			
			validateReports("Avon Anniversaries",titleLeaderBetween,columnName6);	
		
		}
		
		@Test(priority=13407)
	     public void verifyBirthdaysReport() throws Exception{
			logInfo("Entered into verifyBirthdaysReport() test");
			navToProdTable();			
			validateReports("Birthdays",titleLeaderBetween,columnName1);		
		}
		
		
		@Test(priority=13408)
		public void verifyFollowUpReport() throws Exception{
			logInfo("inside verifyFollowUpReport() Test");
			navToProdTable();			
			validateReports("3-Day Follow Up",titleLeaderBetween,columnName1);	
			
		}
		
		@Test(priority=13409)
	     public void verifyFlexibleOrderingReport() throws Exception{
			logInfo("Entered into verifyBirthdaysReport() test");
			navToProdTable();			
			validateReports("Flexible Ordering",titleLeaderBetween,columnName1);		
		}
		
		@Test(priority=13410)
	     public void verifyHoldOrdersReport() throws Exception{
			logInfo("Entered into verifyHoldOrdersReport() test");
			navToProdTable();			
			validateReports("Hold Orders",titleLeaderBetween,columnName1);		
		}
		
		@Test(priority=13411)
	     public void verifyLeadershipPerformanceReport() throws Exception{
			logInfo("Entered into verifyLeadershipPerformanceReport() test");
			navToProdTable();			
			validateReports("Leadership Performance",titleCampaign,columnName1);		
		}
		
		@Test(priority=13412)
	     public void verifyNewPromotersReport() throws Exception{
			logInfo("Entered into verifyNewPromotersReport() test");
			navToProdTable();			
			validateReports("New Promoters",titleCampaign,columnName1);		
		}
		
		
		@Test(priority=13413)
	     public void verifyInactivityReport() throws Exception{
			logInfo("Entered into verifyInactivityReport() test");
			navToProdTable();			
			validateReports("No Orders/Inactivity",titleCampaign,columnName1);		
		}
		
		@Test(priority=13414)
	     public void verifyOrdersReport() throws Exception{
			logInfo("Entered into verifyOrdersReport() test");
			navToProdTable();			
			validateReports("Orders",titleCampaign,columnName3);		
		}
		
		@Test(priority=13415)
	     public void verifyPastDueReport() throws Exception{
			logInfo("Entered into verifyPastDueReport() test");
			navToProdTable();			
			validateReports("Past Due",titleLeaderBetween,columnName5);		
		}
		
		//@Test(priority=13416)
	     public void verifyPendingAppointmentsReport() throws Exception{
			logInfo("Entered into verifyPendingAppointmentsReport() test");
			navToProdTable();			
			validateReports("Pending Appointments",titleCampaign,columnName5);		
		}
		
		
		@Test(priority=13417)
	     public void verifyPresidentsClubReport() throws Exception{
			logInfo("Entered into verifyPresidentsClubReport() test");
			navToProdTable();			
			validateReports("President's Club",titlePresidentClub,columnName5);		
		}
		
		@Test(priority=13418)
	     public void verifyRecognitionReport() throws Exception{
			logInfo("Entered into verifyRecognitionReport() test");
			navToProdTable();			
			validateReports("Recognition",titleCampaign,columnName2);		
		}
		
		@Test(priority=13419)
	     public void verifyRemovalsReport() throws Exception{
			logInfo("Entered into verifyRemovalsReport() test");
			navToProdTable();			
			validateReports("Removals",titleCampaign,columnName1);		
		}
		
		@Test(priority=13420)
	     public void verifyRepresentativeSearchReport() throws Exception{
			logInfo("Entered into verifyRepresentativeSearchReport() test");
			navToProdTable();			
			validateReports("Representative Search",titleCampaign,columnName1);		
		}
		
		@Test(priority=13421)
	     public void verifySalesReport() throws Exception{
			logInfo("Entered into verifySalesReport() test");
			navToProdTable();			
			validateReports("Sales",titleCampaign,columnName1);		
		}
		
		@Test(priority=13422)
	     public void verifyeStoreSalesReport() throws Exception{
			logInfo("Entered into verifyeStoreSalesReport() test");
			navToProdTable();			
			validateReports("eStore Sales (Direct Delivery)",titleCampaign,columnName1);		
		}
		
		@Test(priority=13423)
	     public void verifyAdditionsReport() throws Exception{
			logInfo("Entered into verifyAdditionsReport() test");
			navToProdTable();			
			validateReports("Additions/New Appointments",titleCampaign,columnName1);	
		
		}
			
			
	

	
	
	

}
