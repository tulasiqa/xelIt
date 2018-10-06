package vibe.billing.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import common.EnvProperties;
import vibe.users.tests.UsersMethods;

@Priority(7)
public class BillingTests1 extends BillingMethods1 {
	
	UsersMethods um = new UsersMethods();
	  EnvProperties prop  = new EnvProperties();
	
	  @Test(priority=700)
		public void EnableBillingUsers() throws  Exception{
			String billingDateMonth = getDateByMonth(1, "MM/dd/yyyy");
			String billingDateYear = getDateByYear(1, "MM/dd/yyyy");
			logInfo("Inside enableBillingUsers() test..");
			System.out.println("Inside enableBillingUsers() test..");
			
			//go to admin and set the users as active and update subscriptions
			go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN1"),prop.getLocatorForEnvironment(appUrl,"billingLN1"),prop.getLocatorForEnvironment(appUrl,"billingCon1"),
						prop.getLocatorForEnvironment(appUrl, "billingUsername1"));
			updateSubscriptionDetails(txtSubPlanLiteMonthly,billingDateMonth);
			
			go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN2"),prop.getLocatorForEnvironment(appUrl,"billingLN2"),prop.getLocatorForEnvironment(appUrl,"billingCon2"),
					prop.getLocatorForEnvironment(appUrl, "billingUsername2"));
			updateSubscriptionDetails(txtSubPlanLiteYearly,billingDateYear);
			
			go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN3"),prop.getLocatorForEnvironment(appUrl,"billingLN3"),prop.getLocatorForEnvironment(appUrl,"billingCon3"),
					prop.getLocatorForEnvironment(appUrl, "billingUsername3"));
			updateSubscriptionDetails(txtSubPlanProMonthly,billingDateMonth);
			
			go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN4"),prop.getLocatorForEnvironment(appUrl,"billingLN4"),prop.getLocatorForEnvironment(appUrl,"billingCon4"),
					prop.getLocatorForEnvironment(appUrl, "billingUsername4"));
			updateSubscriptionDetails(txtSubPlanProYearly,billingDateYear);
			
			//The below users refer to after upgrade/downgrade scenarios
			
			go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN11"),prop.getLocatorForEnvironment(appUrl,"billingLN11"),prop.getLocatorForEnvironment(appUrl,"billingCon11"),
					prop.getLocatorForEnvironment(appUrl, "billingUsername11"));
		    updateSubscriptionDetails(txtSubPlanLiteMonthly,billingDateMonth);
			
			
			
		/*	loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN1"),
					prop.getLocatorForEnvironment(appUrl, "billingLN1"), prop.getLocatorForEnvironment(appUrl, "billingCon1"));
			renewSubscriptions(txtSubPlanLiteMonthly);
			userLogout();
			
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN2"),
					prop.getLocatorForEnvironment(appUrl, "billingLN2"), prop.getLocatorForEnvironment(appUrl, "billingCon2"));
			renewSubscriptions(txtSubPlanLiteYearly);
			userLogout();*/
		
		}
	  
	//TC3712
	//@Test(priority=701)
	public void EnrollLiteMonthlyUser() throws  Exception{
		logInfo("Inside enrollLiteMonthlyUser() test..");
		System.out.println("Inside enrollLiteMonthlyUser() test..");
		navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail1,txtEnrollUserName1,txtSubPlanLiteMonthly);
		logOut();
		logIn(txtEnrollUserName1,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanLiteMonthly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanLiteMonthly);
		}

		boolean isCCInfoVerified = verifyCCDetails("Master",txtEnrollUserName1);
		if(isCCInfoVerified==false){
			Assert.assertTrue(isCCInfoVerified,"Unable to verify the credit card details for the subscription plan : "+txtSubPlanLiteMonthly);
		}
		logOut();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
	}
	

	//TC3713
	//@Test(priority=702)
	public void EnrollLiteYearlyUser() throws  Exception{
		logInfo("Inside enrollLiteYearlyUser() test..");
		System.out.println("Inside enrollLiteYearlyUser() test..");
		navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail2,txtEnrollUserName2,txtSubPlanLiteYearly);
		logOut();
		logIn(txtEnrollUserName2,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanLiteYearly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanLiteYearly);
		}
		
		boolean isCCInfoVerified = verifyCCDetails("Visa",txtEnrollUserName2);
		if(isCCInfoVerified==false){
			Assert.assertTrue(isCCInfoVerified,"Unable to verify the credit card details for the subscription plan : "+txtSubPlanLiteYearly);
		}
		logOut();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
	}
	
	//TC3714
	//@Test(priority=703)
	public void EnrollProMonthlyUser() throws  Exception{
		logInfo("Inside enrollProMonthlyUser() test..");
		System.out.println("Inside enrollProMonthlyUser() test..");
		navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail3,txtEnrollUserName3,txtSubPlanProMonthly);
		logOut();
		logIn(txtEnrollUserName3,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanProMonthly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanProMonthly);
		}
		
		boolean isCCInfoVerified = verifyCCDetails("Discover",txtEnrollUserName3);
		if(isCCInfoVerified==false){
			Assert.assertTrue(isCCInfoVerified,"Unable to verify the credit card details for the subscription plan : "+txtSubPlanProMonthly);
		}
		logOut();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
	}
	
	//TC3715
	//@Test(priority=704)
	public void EnrollProYearlyUser() throws  Exception{
		logInfo("Inside enrollProYearlyUser() test..");
		System.out.println("Inside enrollProYearlyUser() test..");
		navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail4,txtEnrollUserName4,txtSubPlanProYearly);
		logOut();
		logIn(txtEnrollUserName4,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanProYearly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanProYearly);
		}
		
		boolean isCCInfoVerified = verifyCCDetails("AmericanExpress",txtEnrollUserName4);
		if(isCCInfoVerified==false){
			Assert.assertTrue(isCCInfoVerified,"Unable to verify the credit card details for the subscription plan : "+txtSubPlanProYearly);
		}

	}
	
	//TC3696
	@Test(priority=705)
	public void VerifySubscriptionsPlansForLiteMonthly() throws  Exception{
		try{
			logInfo("Inside verifySubscriptionsPlans() test..");
			System.out.println("Inside verifySubscriptionsPlans() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN1"),
					prop.getLocatorForEnvironment(appUrl, "billingLN1"), prop.getLocatorForEnvironment(appUrl, "billingCon1"));
			navigate2Subscription();
			boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanLiteMonthly);
			if(isSubscriptionPlanVerified==false){
				Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanLiteMonthly);
			}
			logOut();
		}
		catch(Exception ex){
			logOut();
		}
		
	}
	
   //TC3697
	@Test(priority=706)
	public void VerifySubscriptionsPlansForLiteYearly() throws  Exception{
		try{
			logInfo("Inside SubscriptionsPlansForLiteYearly() test..");
			System.out.println("Inside SubscriptionsPlansForLiteYearly() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN2"),
					prop.getLocatorForEnvironment(appUrl, "billingLN2"), prop.getLocatorForEnvironment(appUrl, "billingCon2"));
			navigate2Subscription();
			boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanLiteYearly);
			if(isSubscriptionPlanVerified==false){
				Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanLiteYearly);
			}
			logOut();
		}
		catch(Exception ex){
			logOut();
		}
		
	}
	
	//TC3698
	@Test(priority=707)
	public void VerifySubscriptionsPlansForProMonthly() throws  Exception{
		try{
			logInfo("Inside SubscriptionsPlansForProMonthly() test..");
			System.out.println("Inside SubscriptionsPlansForProMonthly() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN3"),
					prop.getLocatorForEnvironment(appUrl, "billingLN3"), prop.getLocatorForEnvironment(appUrl, "billingCon3"));
			navigate2Subscription();
			boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanProMonthly);
			if(isSubscriptionPlanVerified==false){
				Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanProMonthly);
			}
			logOut();
		}
		catch(Exception ex){
		    logOut();
	    }

	}
	
	//TC3699
	@Test(priority=708)
	public void VerifySubscriptionsPlansForProYearly() throws  Exception{
		try{
			logInfo("Inside SubscriptionsPlansForProYearly() test..");
			System.out.println("Inside SubscriptionsPlansForProYearly() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN4"),
					prop.getLocatorForEnvironment(appUrl, "billingLN4"), prop.getLocatorForEnvironment(appUrl, "billingCon4"));
			navigate2Subscription();
			boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanProYearly);
			if(isSubscriptionPlanVerified==false){
				Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanProYearly);
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }

	}
	
	//TC3700 - TC3702, TC3709-TC3711
	@Test(priority=709)
	public void ValidateCreditCardInfo() throws  Exception{
		logInfo("Inside validateCreditCardInfo() test..");
		System.out.println("Inside validateCreditCardInfo() test..");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN1"),
				prop.getLocatorForEnvironment(appUrl, "billingLN1"), prop.getLocatorForEnvironment(appUrl, "billingCon1"));
		navigate2Subscription();
		boolean isCCValidated = verifyCreditCardInformation();
		if(isCCValidated==false){
			Assert.assertTrue(isCCValidated,"Unable to validate the credit card name.");
		}

	}
	
	//TC3703
	@Test(priority=710)
	public void ValidateCvvOfMasterCard() throws  Exception{
		try{
			logInfo("Inside validateCvvOfMasterCard() test..");
			System.out.println("Inside validateCvvOfMasterCard() test..");
			navigate2Subscription();
			boolean isValidatedCVV = verifyCVVByCardType("MasterCard");
			if(isValidatedCVV==false){
				Assert.assertTrue(isValidatedCVV,"Unable to validate the cvv of Master card.");
			}
			logOut();
		}
		catch(Exception ex){
			logOut();
		}
	
	}
	
	//TC3704
	@Test(priority=711)
	public void ValidateCvvOfVisaCard() throws  Exception{
		try{
			logInfo("Inside validateCvvOfVisaCard() test..");
			System.out.println("Inside validateCvvOfVisaCard() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN2"),
					prop.getLocatorForEnvironment(appUrl, "billingLN2"), prop.getLocatorForEnvironment(appUrl, "billingCon2"));
			navigate2Subscription();
			boolean isCCNameValidated = verifyCVVByCardType("Visa");
			if(isCCNameValidated==false){
				Assert.assertTrue(isCCNameValidated,"Unable to validate the cvv of Visa card.");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
	}
	
	//TC3705
	@Test(priority=712)
	public void ValidateCvvOfDiscoverCard() throws  Exception{
		try{
			logInfo("Inside validateCvvOfDiscoverCard() test..");
			System.out.println("Inside validateCvvOfDiscoverCard() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN3"),
					prop.getLocatorForEnvironment(appUrl, "billingLN3"), prop.getLocatorForEnvironment(appUrl, "billingCon3"));
			navigate2Subscription();
			boolean isValidatedCVV = verifyCVVByCardType("Discover");
			if(isValidatedCVV==false){
				Assert.assertTrue(isValidatedCVV,"Unable to validate the cvv of Discover card.");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
	}
		
	//TC3706
	@Test(priority=713)
	public void ValidateCvvOfAmericanExpressCard() throws  Exception{
		logInfo("Inside validateCvvOfAmericanExpressCard() test..");
		System.out.println("Inside validateCvvOfAmericanExpressCard() test..");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN4"),
				prop.getLocatorForEnvironment(appUrl, "billingLN4"), prop.getLocatorForEnvironment(appUrl, "billingCon4"));
		navigate2Subscription();
		boolean isCCNameValidated = verifyCVVByCardType("AmericanExpress");
		if(isCCNameValidated==false){
			Assert.assertTrue(isCCNameValidated,"Unable to validate the cvv of American Express card.");
		}
	}
	
	//TC3707 & TC3708
	@Test(priority=714)
	public void ValidateInvalidCvvAndExpiryDate() throws  Exception{
		try{
			logInfo("Inside validateInvalidCvvAndExpiryDate() test..");
			System.out.println("Inside validateInvalidCvvAndExpiryDate() test..");
			navigate2Subscription();
			boolean isCvvValidated = verifyCVVAndExpiryDate();
			if(isCvvValidated==false){
				Assert.assertTrue(isCvvValidated,"Unable to validate the cvv & expiry date with invalid inputs .");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
	}
	
	// ***************************Upgrade scenarios **************************************//
	
	//TC3716
	@Test(priority=715)
	public void UpgradeFromLiteMonthly2LiteYearly() throws Exception{
	   try{
		    String billingDate = getDateByMonth(1, "MM/dd/yyyy");
			logInfo("Inside upgradeFromLiteMonthly2LiteYearly() test..");
			System.out.println("Inside upgradeFromLiteMonthly2LiteYearly() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN1"),
					prop.getLocatorForEnvironment(appUrl, "billingLN1"), prop.getLocatorForEnvironment(appUrl, "billingCon1"));
			navigate2Subscription();
			boolean isSubscriptionUpgraded = upgradeSubscriptionBetSameSecurityGroups(txtSubPlanLiteMonthly,txtSubPlanLiteYearly,billingDate);
			if(isSubscriptionUpgraded==false){
				Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from lite monthly to lite yearly.");
			}
			logOut();
	   }
	   catch(Exception ex){
	        logOut();
	   }
		
	}
	
	//TC3717
	@Test(priority=716)
	public void VerifySubFromLiteMonthly2LiteYearlyAdmin() throws Exception{
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteMonthly2LiteYearlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteMonthly2LiteYearlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN1"),prop.getLocatorForEnvironment(appUrl,"billingLN1"),prop.getLocatorForEnvironment(appUrl,"billingCon1"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername1"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanLiteMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from lite monthly to lite yearly on the admin side.");
		}
	}
	
	//TC3718
	@Test(priority=717)
	public void UpgradeFromLiteMonthly2ProMonthly() throws Exception{
		try{
			String billingDate = getDateByMonth(1, "MM/dd/yyyy");
			logInfo("Inside upgradeFromLiteMonthly2ProMonthly() test..");
			System.out.println("Inside upgradeFromLiteMonthly2ProMonthly() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN1"),
					prop.getLocatorForEnvironment(appUrl, "billingLN1"), prop.getLocatorForEnvironment(appUrl, "billingCon1"));
			navigate2Subscription();
			boolean isSubscriptionUpgraded = upgradeSubscriptionBetDiffSecurityGroups(txtSubPlanLiteMonthly,txtSubPlanProMonthly,billingDate);
			if(isSubscriptionUpgraded==false){
				Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from lite monthly to pro monthly.");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
	}
	
	//TC3719
	@Test(priority=718)
	public void VerifySubFromLiteMonthly2ProMonthlyAdmin() throws Exception{
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteMonthly2ProMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteMonthly2ProMonthlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN1"),prop.getLocatorForEnvironment(appUrl,"billingLN1"),prop.getLocatorForEnvironment(appUrl,"billingCon1"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername1"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from lite monthly to pro monthly on the admin side.");
		}
	}
	
	//TC3720 2nd lite monthly user
	@Test(priority=719)
	public void UpgradeFromLiteMonthly2ProYearly() throws Exception{
		try{
			String billingDate = getDateByYear(1, "MM/dd/yyyy");
			logInfo("Inside upgradeFromLiteMonthly2ProYearly() test..");
			System.out.println("Inside upgradeFromLiteMonthly2ProYearly() test..");
			/*navigate2Enrollment();
			enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail11,txtEnrollUserName11,txtSubPlanLiteMonthly);*/
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN11"),
					prop.getLocatorForEnvironment(appUrl, "billingLN11"), prop.getLocatorForEnvironment(appUrl, "billingCon11"));
			navigate2Subscription();
			boolean isSubscriptionUpgraded = upgradeSubscriptionBetDiffSecurityGroups(txtSubPlanLiteMonthly,txtSubPlanProYearly,billingDate);
			if(isSubscriptionUpgraded==false){
				Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from lite monthly to pro yearly.");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
	}
	
	//TC3721
	@Test(priority=720)
	public void VerifySubFromLiteMonthly2ProYearlyAdmin() throws Exception{
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteMonthly2ProYearlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteMonthly2ProYearlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN11"),prop.getLocatorForEnvironment(appUrl,"billingLN11"),prop.getLocatorForEnvironment(appUrl,"billingCon11"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername11"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from lite monthly to pro yearly on the admin side.");
		}
	}
	
	//TC3722
	@Test(priority=721)
	public void UpgradeFromLiteYearly2ProMonthly() throws Exception{
		try{
			String billingDate = getDateByMonth(1, "MM/dd/yyyy");
			logInfo("Inside upgradeFromLiteYearly2ProMonthly() test..");
			System.out.println("Inside upgradeFromLiteYearly2ProMonthly() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN2"),
					prop.getLocatorForEnvironment(appUrl, "billingLN2"), prop.getLocatorForEnvironment(appUrl, "billingCon2"));
			navigate2Subscription();
			boolean isSubscriptionUpgraded = upgradeSubscriptionBetDiffSecurityGroups(txtSubPlanLiteYearly,txtSubPlanProMonthly,billingDate);
			if(isSubscriptionUpgraded==false){
				Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from lite yearly to pro monthly.");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
		
	}
	
	//TC3723
	@Test(priority=722)
	public void VerifySubFromLiteYearly2ProMonthlyAdmin() throws Exception{
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteYearly2ProMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteYearly2ProMonthlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN2"),prop.getLocatorForEnvironment(appUrl,"billingLN2"),prop.getLocatorForEnvironment(appUrl,"billingCon2"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername2"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from lite yearly to pro monthly on the admin side.");
		}
	}
	
	//TC3724
	@Test(priority=723)
	public void UpgradeFromLiteYearly2ProYearly() throws Exception{
		try{
			String billingDate = getDateByYear(1, "MM/dd/yyyy");
			logInfo("Inside upgradeFromLiteYearly2ProYearly() test..");
			System.out.println("Inside upgradeFromLiteYearly2ProYearly() test..");
			
			/*navigate2Enrollment();
			enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail21,txtEnrollUserName21,txtSubPlanLiteYearly);*/
			
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN22"),
					prop.getLocatorForEnvironment(appUrl, "billingLN22"), prop.getLocatorForEnvironment(appUrl, "billingCon22"));
			navigate2Subscription();
			boolean isSubscriptionUpgraded = upgradeSubscriptionBetDiffSecurityGroups(txtSubPlanLiteYearly,txtSubPlanProYearly,billingDate);
			if(isSubscriptionUpgraded==false){
				Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from lite yearly to pro yearly.");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
		
	}
	
	//TC3725
	@Test(priority=724)
	public void VerifySubFromLiteYearly2ProYearlyAdmin() throws Exception{
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteYearly2ProYearlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteYearly2ProYearlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN22"),prop.getLocatorForEnvironment(appUrl,"billingLN22"),prop.getLocatorForEnvironment(appUrl,"billingCon22"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername22"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from lite yearly to pro yearly on the admin side.");
		}
	}
	
	//TC3726
	@Test(priority=725)
	public void UpgradeFromProMonthly2ProYearly() throws Exception{
		try{
			String billingDate =  getDateByMonth(1, "MM/dd/yyyy");
			logInfo("Inside upgradeFromProMonthly2ProYearly() test..");
			System.out.println("Inside upgradeFromProMonthly2ProYearly() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN3"),
					prop.getLocatorForEnvironment(appUrl, "billingLN3"), prop.getLocatorForEnvironment(appUrl, "billingCon3"));
			navigate2Subscription();
			boolean isSubscriptionUpgraded = upgradeSubscriptionBetSameSecurityGroups(txtSubPlanProMonthly,txtSubPlanProYearly,billingDate);
			if(isSubscriptionUpgraded==false){
				Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from pro monthly to pro yearly.");
			}
			logOut();
		   }
		   catch(Exception ex){
			   logOut();
		   }
	}
	
	//TC3727
	@Test(priority=726)
	public void VerifySubFromProMonthly2ProYearlyAdmin() throws Exception{
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProMonthly2ProYearlyAdmin() test..");
		System.out.println("Inside verifySubFromProMonthly2ProYearlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN3"),prop.getLocatorForEnvironment(appUrl,"billingLN3"),prop.getLocatorForEnvironment(appUrl,"billingCon3"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername3"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from pro monthly to pro yearly on the admin side.");
		}
	}
	
	//********************************** Downgrade scenarios***********************************************
	
	
	//TC3728
	@Test(priority=727)
	public void DowngradeFromProYearly2ProMonthly() throws Exception{
		try{
			String billingDate = getDateByYear(1, "MM/dd/yyyy");
			logInfo("Inside downgradeFromProYearly2ProMonthly() test..");
			System.out.println("Inside downgradeFromProYearly2ProMonthly() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN4"),
					prop.getLocatorForEnvironment(appUrl, "billingLN4"), prop.getLocatorForEnvironment(appUrl, "billingCon4"));
			navigate2Subscription();
			boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanProYearly,txtSubPlanProMonthly,billingDate);
			if(isSubscriptionDowngraded==false){
				Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from pro yearly to pro monthly.");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
	}
	
	//TC3729
	@Test(priority=728)
	public void VerifySubFromProYearly2ProMonthlyAdmin() throws Exception{
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProYearly2ProMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromProYearly2ProMonthlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN4"),prop.getLocatorForEnvironment(appUrl,"billingLN4"),prop.getLocatorForEnvironment(appUrl,"billingCon4"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername4"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from pro yearly to pro monthly on the admin side.");
		}
	}
	
	//TC3730
	@Test(priority=729)
	public void DowngradeFromProYearly2LiteYearly() throws Exception{
		try{
			String billingDate = getDateByYear(1, "MM/dd/yyyy");
			logInfo("Inside downgradeFromProYearly2LiteYearly() test..");
			System.out.println("Inside downgradeFromProYearly2LiteYearly() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN4"),
					prop.getLocatorForEnvironment(appUrl, "billingLN4"), prop.getLocatorForEnvironment(appUrl, "billingCon4"));
			navigate2Subscription();
			boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanProYearly,txtSubPlanLiteYearly,billingDate);
			if(isSubscriptionDowngraded==false){
				Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from pro yearly to lite yearly.");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
	}
	
	//TC3731
	@Test(priority=730)
	public void VerifySubFromProYearly2LiteYearlyAdmin() throws Exception{
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProYearly2LiteYearlyAdmin() test..");
		System.out.println("Inside verifySubFromProYearly2LiteYearlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN4"),prop.getLocatorForEnvironment(appUrl,"billingLN4"),prop.getLocatorForEnvironment(appUrl,"billingCon4"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername4"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from pro yearly to lite yearly on the admin side.");
		}
	}
	
	//TC3732
	@Test(priority=731)
	public void DowngradeFromProYearly2LiteMonthly() throws Exception{
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside downgradeFromProYearly2LiteMonthly() test..");
		System.out.println("Inside downgradeFromProYearly2LiteMonthly() test..");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN4"),
				prop.getLocatorForEnvironment(appUrl, "billingLN4"), prop.getLocatorForEnvironment(appUrl, "billingCon4"));
		navigate2Subscription();
		boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanProYearly,txtSubPlanLiteMonthly,billingDate);
		if(isSubscriptionDowngraded==false){
			Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from pro yearly to lite monthly.");
		}
	}
	
	//TC3733
	@Test(priority=732)
	public void VerifySubFromProYearly2LiteMonthlyAdmin() throws Exception{
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProYearly2LiteMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromProYearly2LiteMonthlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN4"),prop.getLocatorForEnvironment(appUrl,"billingLN4"),prop.getLocatorForEnvironment(appUrl,"billingCon4"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername4"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from pro yearly to lite monthly on the admin side.");
		}
	}
	
	//TC3734
	@Test(priority=733)
	public void DowngradeFromProMonthly2LiteYearly() throws Exception{
		try{
			String billingDate = getDateByMonth(1, "MM/dd/yyyy");
			logInfo("Inside downgradeFromProMonthly2LiteYearly() test..");
			System.out.println("Inside downgradeFromProMonthly2LiteYearly() test..");
			
			/*navigate2Enrollment();
			enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail31,txtEnrollUserName31,txtSubPlanProMonthly);*/
			
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN3"),
					prop.getLocatorForEnvironment(appUrl, "billingLN3"), prop.getLocatorForEnvironment(appUrl, "billingCon3"));
			navigate2Subscription();
			boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanProMonthly,txtSubPlanLiteYearly,billingDate);
			if(isSubscriptionDowngraded==false){
				Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from pro monthly to lite yearly.");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
	}
	
	//TC3735
	@Test(priority=734)
	public void VerifySubFromProMonthly2LiteYearlyAdmin() throws Exception{
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProMonthly2LiteYearlyAdmin() test..");
		System.out.println("Inside verifySubFromProMonthly2LiteYearlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN3"),prop.getLocatorForEnvironment(appUrl,"billingLN3"),prop.getLocatorForEnvironment(appUrl,"billingCon3"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername3"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from pro monthly to lite yearly on the admin side.");
		}
	}
	
	//TC3736
	@Test(priority=735)
	public void DowngradeFromProMonthly2LiteMonthly() throws Exception{
		try{
			String billingDate = getDateByMonth(1, "MM/dd/yyyy");
			logInfo("Inside downgradeFromProMonthly2LiteMonthly() test..");
			System.out.println("Inside downgradeFromProMonthly2LiteMonthly() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN33"),
					prop.getLocatorForEnvironment(appUrl, "billingLN33"), prop.getLocatorForEnvironment(appUrl, "billingCon33"));
			navigate2Subscription();
			boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanProMonthly,txtSubPlanLiteMonthly,billingDate);
			if(isSubscriptionDowngraded==false){
				Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from pro monthly to lite monthly.");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
	}
	
	//TC3737
	@Test(priority=736)
	public void VerifySubFromProMonthly2LiteMonthlyAdmin() throws Exception{
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProMonthly2LiteMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromProMonthly2LiteMonthlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN33"),prop.getLocatorForEnvironment(appUrl,"billingLN33"),prop.getLocatorForEnvironment(appUrl,"billingCon33"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername33"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from pro monthly to lite monthly on the admin side.");
		}
	}
	
	//TC37107
	@Test(priority=737)
	public void DowngradeFromLiteYearly2LiteMonthly() throws Exception{
		try{
			String billingDate = getDateByYear(1, "MM/dd/yyyy");
			logInfo("Inside downgradeFromLiteYearly2LiteMonthly() test..");
			System.out.println("Inside downgradeFromLiteYearly2LiteMonthly() test..");
			
			/*navigate2Enrollment();
			enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail22,txtEnrollUserName22,txtSubPlanLiteYearly);*/
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN222"),
					prop.getLocatorForEnvironment(appUrl, "billingLN222"), prop.getLocatorForEnvironment(appUrl, "billingCon222"));
			navigate2Subscription();
			boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanLiteYearly,txtSubPlanLiteMonthly,billingDate);
			if(isSubscriptionDowngraded==false){
				Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from lite yearly to lite monthly.");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
	}
	
	//TC3739
	@Test(priority=738)
	public void VerifySubFromLiteYearly2LiteMonthlyAdmin() throws Exception{
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteYearly2LiteMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteYearly2LiteMonthlyAdmin() test..");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN222"),prop.getLocatorForEnvironment(appUrl,"billingLN222"),prop.getLocatorForEnvironment(appUrl,"billingCon222"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername222"));
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanLiteYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from lite yearly to lite monthly on the admin side.");
		}
	}
	
	//billingUsername5 - Pro-Monthly user
	@Test(priority=739)
	public void VerifyActivateUsers() throws  Exception{
		try{
			logInfo("inside verifyActivateUsers Test");
			System.out.println("Inside verifyActivateUsers() test..");
			/*navigate2Enrollment();
			enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail5,txtEnrollUserName5,txtSubPlanLiteYearly); */
			
			go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN5"),prop.getLocatorForEnvironment(appUrl,"billingLN5"),prop.getLocatorForEnvironment(appUrl,"billingCon5"),
					prop.getLocatorForEnvironment(appUrl, "billingUsername5"));
			setUserActiveOrInactive(prop.getLocatorForEnvironment(appUrl, "billingUsername5"),true);
			changeSubscriptionPlanInAdmin(txtSubPlanProYearly);
			
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN5"),
					prop.getLocatorForEnvironment(appUrl, "billingLN5"), prop.getLocatorForEnvironment(appUrl, "billingCon5"));
			navigate2Subscription();
			boolean isSubscriptionFound = verifySubscriptionPlanHeading(txtSubPlanProYearly,"Current Subscription");
			if(isSubscriptionFound==false){
				System.out.println("isSubscriptionFound =" +isSubscriptionFound);
				Assert.assertTrue(isSubscriptionFound, " subscription did not match at the user.");
			}
			
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
		
	}
	
	@Test(priority=740)
	public void VerifyInActivateUsers() throws  Exception{
		logInfo("inside verifyInActivateUsers Test");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN6"),prop.getLocatorForEnvironment(appUrl,"billingLN6"),prop.getLocatorForEnvironment(appUrl,"billingCon6"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername6"));
		setUserActiveOrInactive(prop.getLocatorForEnvironment(appUrl, "billingUsername6"),false);
		um.searchUserFromAdvancedSearch(prop.getLocatorForEnvironment(appUrl,"billingFN6"),prop.getLocatorForEnvironment(appUrl,"billingLN6"),prop.getLocatorForEnvironment(appUrl,"billingCon6"));
		boolean isAlertFound = verifyLoginAlert();
		if(!isAlertFound){
			Assert.assertTrue(isAlertFound, " Able to login as user even the user "+prop.getLocatorForEnvironment(appUrl, "billingUsername6")+" is Inactive");
		}

	}
		
	@Test(priority=741)
	public void VerifyIncorrectNextBillingDate() throws  Exception{
		logInfo("inside verifyIncorrectNextBillingDate Test");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN5"),prop.getLocatorForEnvironment(appUrl,"billingLN5"),prop.getLocatorForEnvironment(appUrl,"billingCon5"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername5"));
		String pastdt = TestBase.getDate(-2, "MM/dd/yyyy");
		System.out.println("pastdt =" +pastdt);
		changeSubscriptionNextBillingDateInAdmin(pastdt);
		waitOnElement("xpath","//span[@class='help-block']");
		WebElement eMsg = driver().findElement(By.xpath("//span[@class='help-block']"));	// error message
		String errMsg = eMsg.getText().trim();
		Assert.assertNotEquals(errMsg.equalsIgnoreCase("should not be in the past"), "Next billing date cannot be past date.");
	}
	
	
	@Test(priority=742)
	public void VerifyValidNextBillingDate() throws  Exception{
		logInfo("inside verifyValidNextBillingDate Test");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN5"),prop.getLocatorForEnvironment(appUrl,"billingLN5"),prop.getLocatorForEnvironment(appUrl,"billingCon5"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername5"));
		String before_change= driver().findElement(By.cssSelector("#pyr_core_subscription_next_billing_date")).getAttribute("value");
		String futuredt = TestBase.getDate(4, "MM/dd/yyyy");
		changeSubscriptionNextBillingDateInAdmin(futuredt);
		confirmationMessage("Subscription was successfully updated.");
		String after_change= driver().findElement(By.cssSelector("#pyr_core_subscription_next_billing_date")).getAttribute("value");
		String exp_value = "Next billing date changed from " + before_change + " 00:00:00 CDT" + " to " + after_change + " 00:00:00 CDT";
		System.out.println("exp_value =" +exp_value);
		Boolean isNotesFound = verifySubscriptionNotesPresent(exp_value);
		System.out.println("isNotesFound =" +isNotesFound);
		if(isNotesFound==false){
			Assert.assertTrue(isNotesFound, " Next billing date not found in the subscription notes." );
		}
	}
	
	@Test(priority=743)
	public void SetBillingFreecycle() throws  Exception{
		logInfo("inside setBillingFreecycle Test");
		String freecycleNotes = "CUSTOMER REQUEST";
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN5"),prop.getLocatorForEnvironment(appUrl,"billingLN5"),prop.getLocatorForEnvironment(appUrl,"billingCon5"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername5"));
		setFreeCycleBilling(freecycleNotes);
		boolean isNotesMatchFound = verifySubscriptionNotesPresent("FREE CYCLE - "+freecycleNotes);
		Assert.assertTrue(isNotesMatchFound, "FREE CYCLE - "+freecycleNotes + " match not found.");
	}
	

	@Test(priority=744)
	public void RefundBillingAmount() throws  Exception{
		logInfo("inside refundBillingAmount Test");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN5"),prop.getLocatorForEnvironment(appUrl,"billingLN5"),prop.getLocatorForEnvironment(appUrl,"billingCon5"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername5"));
		boolean isMatchFound = verifyRefundStatus("4444");
		if(isMatchFound==true){
			boolean isNotesMatchFound = verifySubscriptionNotesPresent("Unhappy with the service");
			Assert.assertTrue(isNotesMatchFound, "Unhappy with the service" + " match not found." );
		}
	}
	
	
	@Test(priority=745)
	public void SetSubscriptionPlanInAdmin() throws  Exception{
		logInfo("inside setSubscriptionPlanInAdmin Test");
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN5"),prop.getLocatorForEnvironment(appUrl,"billingLN5"),prop.getLocatorForEnvironment(appUrl,"billingCon5"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername5"));
		String drpSubscriptionPlanName = "//*[@id='pyr_core_subscription_subscription_plan_id']";
		String before_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("before_change =" +before_change);
		changeSubscriptionPlanInAdmin(txtSubPlanLiteYearly);
		String after_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("after_change =" +after_change);
		boolean isNotesMatchFound = verifySubscriptionNotesPresent("Subscription plan changed from "+before_change+" to "+after_change);
		Assert.assertTrue(isNotesMatchFound, "Subscription plan changed from "+before_change+" to "+after_change + " match not found.");
	}
	
	@Test(priority=746)
	public void UncheckAllowDowngradeOption() throws  Exception{
		logInfo("inside uncheckAllowDowngradeOption Test");
		go2SetupSubscriptionPage();
		setAllowDowngradeOption(false);
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN5"),prop.getLocatorForEnvironment(appUrl,"billingLN5"),prop.getLocatorForEnvironment(appUrl,"billingCon5"),
				prop.getLocatorForEnvironment(appUrl, "billingUsername5"));
		setUserActiveOrInactive(prop.getLocatorForEnvironment(appUrl, "billingUsername5"),true);
		changeSubscriptionPlanInAdmin(txtSubPlanProYearly);
		
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN5"),
				prop.getLocatorForEnvironment(appUrl, "billingLN5"), prop.getLocatorForEnvironment(appUrl, "billingCon5"));
		
		navigate2Subscription();	
		boolean isSubscriptionFound = verifySubscriptionPlanHeading(txtSubPlanProYearly,"Current Subscription");
		if(isSubscriptionFound==true){
			String[] blacklistPlans = {txtSubPlanLiteYearly,txtSubPlanLiteMonthly};
			boolean isMismatchFound = verifyAvailableSubscriptionPlansAtUser(blacklistPlans);
			if(isMismatchFound==true){
				logInfo("Invlaid subscriptions found in Available subscriptions page.");
				Assert.assertFalse(isMismatchFound, "Invlaid subscriptions found in Available subscriptions page.");
			}
		}
	}

	@Test(priority=747)
	public void UpdateCCInfoAtUser() throws  Exception{
		try{
			logInfo("inside updateCCInfoAtUser Test");
			navigate2Subscription();	
			editBillingInfo();
			confirmationMessage("credit card successfully updated.");
			logOut();
		}
		catch(Exception ex){
			logOut();
		}
	}

	@Test(priority=748)
	public void AddDiscPromotion2SubscriptionPlan() throws  Exception{
		try{
			logInfo("inside addPromotion2SubscriptionPlan Test");
			go2SetupSubscriptionPage();
			updateSetupSubscriptionPlan(txtSubPlanProYearly,"Yes", "Yes", "No", "USD", "pro", "Yes", "200" , "Yearly", "20" , "US" );
			go2SetupSubscriptionPage();
			updatePromotionDetails(txtSubPlanProYearly, "25.0", "10", "", "06/16/2016 09:03 AM", "06/16/2020 09:03 AM", "pro", true, true, true, false );
			confirmationMessage("Subscription plan was successfully updated");
			go2SetupSubscriptionPage();
			verifyPromotionsList(txtSubPlanProYearly, true, "pro", "25.0", "10");
			go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN5"),prop.getLocatorForEnvironment(appUrl,"billingLN5"),prop.getLocatorForEnvironment(appUrl,"billingCon5"),
					prop.getLocatorForEnvironment(appUrl, "billingUsername5"));

			setUserActiveOrInactive(prop.getLocatorForEnvironment(appUrl, "billingUsername5"),true);
			changeSubscriptionPlanInAdmin(txtSubPlanProYearly);
			
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN5"),
					prop.getLocatorForEnvironment(appUrl, "billingLN5"), prop.getLocatorForEnvironment(appUrl, "billingCon5"));
			
			navigate2Subscription();	
			boolean isSubscriptionMatchFound = verifySubscriptionPlanHeading(txtSubPlanProYearly,"Current Subscription");
			if(isSubscriptionMatchFound==false){
				Assert.assertTrue(isSubscriptionMatchFound, " subscription title did not match at user");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
	}
	
	
	@Test(priority=749)
	public void AddFreePromotion2SubscriptionPlan() throws  Exception{
		try{
			logInfo("inside addPromotion2SubscriptionPlan Test");
			go2SetupSubscriptionPage();
			updateSetupSubscriptionPlan(txtSubPlanProYearly,"Yes", "Yes", "No", "USD", "pro", "Yes", "200" , "Yearly", "20" , "US" );
			go2SetupSubscriptionPage();
			updatePromotionDetails(txtSubPlanProYearly, "0.0", "10", "", "06/16/2016 09:03 AM", "06/16/2020 09:03 AM", "pro", true, true, true, false );
			confirmationMessage("Subscription plan was successfully updated");
			go2SetupSubscriptionPage();
			verifyPromotionsList(txtSubPlanProYearly, true, "pro", "0.0", "10");
			go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN5"),prop.getLocatorForEnvironment(appUrl,"billingLN5"),prop.getLocatorForEnvironment(appUrl,"billingCon5"),
					prop.getLocatorForEnvironment(appUrl, "billingUsername5"));
			setUserActiveOrInactive(prop.getLocatorForEnvironment(appUrl, "billingUsername5"),true);
			changeSubscriptionPlanInAdmin(txtSubPlanProYearly);
			
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl, "billingFN5"),
					prop.getLocatorForEnvironment(appUrl, "billingLN5"), prop.getLocatorForEnvironment(appUrl, "billingCon5"));
			
			navigate2Subscription();	
			boolean isSubscriptionMatchFound = verifySubscriptionPlanHeading(txtSubPlanProYearly,"Current Subscription");
			if(isSubscriptionMatchFound==false){
				Assert.assertTrue(isSubscriptionMatchFound, " subscription title did not match at user");
			}
			logOut();
		   }
		   catch(Exception ex){
		        logOut();
		   }
		
	}
	
	@Test(priority=750)
	public void SetRerunBillingTest() throws  Exception{
		try{
			logInfo("inside setRerunBilling Test");
			go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"billingFN33"),prop.getLocatorForEnvironment(appUrl,"billingLN33"),prop.getLocatorForEnvironment(appUrl,"billingCon33"),
					prop.getLocatorForEnvironment(appUrl, "billingUsername33"));
			setRerunBilling();
			boolean isNotesMatchFound = verifySubscriptionNotesPresent("Next billing date changed from ");
			Assert.assertTrue(isNotesMatchFound, "Next billing date changed from " + " match not found." );
			logOut();
		}
		catch(Exception ex){
			logOut();
		}
		
	}

		
}
