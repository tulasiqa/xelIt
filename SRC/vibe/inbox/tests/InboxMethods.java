package vibe.inbox.tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.TestBase;

import vibe.myprofile.tests.MyProfileMethods;

public class InboxMethods extends TestBase {
	MyProfileMethods profile = new MyProfileMethods();

	// Login to gmail account with the specified user and password.

	public void loginGmail(String uname, String passwd) throws Exception {
		logInfo("inside loginGmail() method...");

		driver().navigate().to("https://www.google.co.in/");
		driver().navigate().refresh();
		
		clickOnLink("linkText", "Sign in");
		Thread.sleep(5000);
		waitOnElement("cssSelector", inputGmail);
		inputTextClear("cssSelector", inputGmail);
		inputText("cssSelector", inputGmail, uname);
		waitOnElement("cssSelector", btnGmailNext);
		clickOnButton("cssSelector", btnGmailNext);
		Thread.sleep(3000);

		waitOnElement("xpath", inputGmailPasswd);
		inputText("xpath", inputGmailPasswd, passwd);
		clickOnButton("cssSelector", btnpassNext);
		Thread.sleep(3000);
		driver().navigate().to("https://mail.google.com/mail/u/0/#inbox");
		WebDriverWait wait = new WebDriverWait(driver(), 120);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(drpdnGmailUserProfile)));
		Thread.sleep(5000);
	}

	// Verify if emails is received to gmail from external sources.

	public boolean verifyInboxGmail(String subject) {
		logInfo("inside verifyInboxGmails() method ...");
		WebElement gmailBody = driver().findElement(By.xpath(tblGmailBody));
		List allRows = gmailBody.findElements(By.tagName("tr"));
		int count = allRows.size();

		String before = "//table/tbody/tr[";
		String after = "]/td[6]/div/div/div/span[1]";

		String beforeChk = "//table/tbody/tr[";
		String afterChk = "]/td[2]/div[@role='checkbox']";

		boolean isMatchFound = false;
		for (int i = 1; i <= count; i++) {
			WebElement x = driver().findElement(By.xpath(before + i + after));
			String gmail_summary = x.getText().trim();
			System.out.println("Gmail subjects = " + gmail_summary);
			if (gmail_summary.equalsIgnoreCase(subject.trim())) {
				isMatchFound = true;
				logInfo(subject + " email match found @row " + i);
				break;
			}
		}

		if (isMatchFound == false) {
			logInfo(subject + " no email match found in Gmail.");
			// Assert.assertTrue(isMatchFound, subject + " no email match found");
		}
		return isMatchFound;
	}

	// Compose and send an email from gmail.

	public void composeGmail(String to_recipients, String subject, String body) throws Exception {
		logInfo("inside composeGmail() method.");

		waitOnElement("cssSelector", btnComposeGmail);
		clickOnButton("cssSelector", btnComposeGmail);
		Thread.sleep(5000);
		
		//waitOnElement("xpath",eReceipients);
		clickOnElement("xpath",eReceipients);
		driver().findElement(By.xpath(inputComposeTo)).sendKeys(to_recipients);
		driver().findElement(By.xpath(inputComposeTo)).sendKeys(Keys.TAB);
		
		/*WebElement eReceipient = driver().findElement(By.xpath(eReceipients));
		if (eReceipient.isDisplayed()) {
			eReceipient.click();
			Thread.sleep(5000);
			logInfo("clicked gmail's Receipient element ...");
		}
		
			inputText("xpath", inputComposeTo, to_recipients);
			
			Robot rb = new Robot();
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);*/
		
		WebElement eSubject = driver().findElement(By.xpath(eSubjects));
		if (eSubject.isDisplayed()) {
			eSubject.click();
			Thread.sleep(3000);
		}
		
		inputText("xpath", inputComposeSubject, subject);
		Thread.sleep(3000);
	
		WebElement eDesc = driver().findElement(By.xpath(inputComposeDesc));
		eDesc.click();
		inputText("xpath", inputComposeDesc, body);
		Thread.sleep(2000);
		clickOnButton("xpath", btnComposeSend);
		Thread.sleep(5000);

	}

	public boolean openMailInGmail(String subject) throws  Exception {
		logInfo("inside openMailInGmail() method ...");
		WebElement gmailBody = driver().findElement(By.xpath(tblGmailBody));
		List allRows = gmailBody.findElements(By.tagName("tr"));
		int count = allRows.size();

		String before = "//table/tbody/tr[";
		String after = "]/td[6]/div/div/div/span[1]";

		String beforeChk = "//table/tbody/tr[";
		String afterChk = "]/td[2]/div[@role='checkbox']";

		boolean isMatchFound = false;
		for (int i = 1; i <= count; i++) {
			WebElement x = driver().findElement(By.xpath(before + i + after));
			String gmail_summary = x.getText().trim();
			System.out.println("Gmail subjects = " + gmail_summary);
			if (gmail_summary.equalsIgnoreCase(subject.trim())) {
				isMatchFound = true;
				x.click();
				logInfo(subject + " email match found @row " + i);
				Assert.assertTrue(isMatchFound, subject + " email match found @row " + i);
				break;
			}

		}

		if (isMatchFound == false) {
			logInfo(subject + " no email match found in Gmail.");
			Assert.assertTrue(isMatchFound, subject + " no email match found");
		}
		return isMatchFound;
	}

	// Sign out from Gmail account for new workspace or UI.

	public void signoutGmail() throws Exception {
		logInfo("inside signoutGmail() method ...");
		WebDriverWait wait = new WebDriverWait(driver(), 180);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(drpdnGmailUserProfile)));

		WebElement profile = driver().findElement(By.xpath(drpdnGmailUserProfile));
		if (profile.isDisplayed()) {
			logInfo(profile.getText().trim() + " found..");
			profile.click();
			clickOnLink("linkText", "Sign out");
			Thread.sleep(10000);
			
			/*waitOnElement("xpath", "//div[@role='presentation']/div[1]/div/div/div[2]/div");
			clickOnElement("xpath", "//div[@role='presentation']/div[1]/div/div/div[2]/div");
			Thread.sleep(5000);*/
			
			waitOnElement("xpath", "//*[@class='cbSDSe']/div[2]/div");
			clickOnElement("xpath", "//*[@class='cbSDSe']/div[2]/div");
			Thread.sleep(5000);
			
			waitOnElement("xpath", "//form[@role='presentation']");
			WebElement eform = driver().findElement(By.xpath("//*[@id='initialView']/div[2]"));
			System.out.println("eform.isDisplayed() = ======== " + eform.isDisplayed());

			if (eform.isDisplayed()) {

				clickOnElement("xpath", "//form[@role='presentation']/div[2]/div[1]/div[1]/div[1]/ul[2]/li[1]/button[1]");
				WebElement eAccounts = driver().findElement(By.xpath("//form[@role='presentation']/div[2]/div[1]/div[1]/div[1]/ul[1]"));
				List allAccount = eAccounts.findElements(By.tagName("li"));
				int total_accounts = allAccount.size();
				System.out.println("Total Accounts = ======== " + total_accounts);
				if (total_accounts >= 1) {
					String before_accName = "//form[@role='presentation']/div[2]/div[1]/div[1]/div[1]/ul[";
					String after_accName = "]/li[1]/div/div[2]/p[1]";
					String before_remove = "//form[@role='presentation']/div[2]/div[1]/div[1]/div[1]/ul[";
					String after_remove = "]/li[1]/div/div[2]/div";

					for (int i = 1; i <= total_accounts - 1; i++) {

						WebElement eRemove = driver().findElement(By.xpath(before_remove + i + after_remove));
						eRemove.click();
						Thread.sleep(2000);
						WebElement handleDialog = driver()
								.findElement(By.xpath("//*[@id='yDmH0d']/div[5]/div[2]/div[1]"));
						if (handleDialog.isDisplayed()) {
							handleDialog.click();
						}
						Thread.sleep(5000);
						logInfo("clicked Remove link. ---");
					}
				}
			}

			clickOnElement("xpath", "//form[@role='presentation']/div[2]/div[1]/div[1]/div[1]/ul[2]/li[1]");
			Thread.sleep(3000);
		}

	}

// Navigate to vibe communications -> inbox page.

	public void go2Inbox() throws Exception {
		logInfo("inside go2Inbox() method..");
		driver().navigate().to(appUrl + "crm/messages");
		//driver().navigate().refresh();   // remove this later
	}
	
	// Verify if the specified email is present in the inbox panel and select
	// the matching mail.

	public void go2EmailTemplates(){
		logInfo("inside go2EmailTemplates() method..");
		driver().navigate().to(appUrl + "pyr_core/account#email_templates");
	}
	
	
	public boolean verifyVibeInboxMail(String subject) {
		logInfo("inside verifyVibeInboxMail() method..");
		boolean isEmailVerified = false;
		WebElement e = driver().findElement(By.xpath(tblInboxBody));
		List allRows = e.findElements(By.tagName("tr"));
		int count = allRows.size();

		String before = "//*[@id='bulk-form']/table/tbody/tr[";
		String after = "]/td[5]/span[1]";

		String beforeChk = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterChk = "]/td[1]/input";

		int matchCnt = 0;

		for (int i = 1; i <= count - 1; i++) {
			WebElement x = driver().findElement(By.xpath(before + i + after));
			String act_subject = x.getText().trim();
			if (act_subject.equalsIgnoreCase(subject)) {
				isEmailVerified = true;
				WebElement chk = driver().findElement(By.xpath(beforeChk + i + afterChk));
				chk.click();
				x.click();
				Reporter.log(subject + " match found.");
				logInfo(subject + " match found.");
				Assert.assertTrue(act_subject.equalsIgnoreCase(subject),
						"subject \'" + subject + "\' email match found in inbox.");
				matchCnt++;
				break;
			}
		}

		if (matchCnt == 0) {
			logInfo(subject + " match not found.");
			Reporter.log(subject + " match not found.");
			Assert.assertTrue(false, "subject \'" + subject + "\' email match could not be found in inbox.");
		}
		return isEmailVerified;
	}

	// Verify the count of unread emails displayed in QuickLinks pane in the
	// home page.

	public int verifyInboxQuickLinks() throws Exception {
		logInfo("inside verifyInboxQuickLinks() method..");
		driver().navigate().to(appUrl);
		waitOnElement("linkText", "Home");
		// verifyLinkPresent("Home");
		clickOnLink("linkText", "Home");
		WebElement inbox = driver().findElement(By.xpath(divInboxCount));
		String inboxCount = inbox.getText().trim();
		int count = Integer.parseInt(inboxCount);
		logInfo("count displayed in Quick Links = " + count);
		return count;
	}

	// Set emails per page in inbox.

	public void setMailsPerPage(String MailsPerPage) throws Exception {
		logInfo("inside setMailsPerPage() method..");

		// waitOnElement("cssSelector",btnToggleMailsperPage);
		WebElement ebtnToggleMailsperPage = driver().findElement(By.cssSelector(btnToggleMailsperPage));
		ebtnToggleMailsperPage.click();
		waitOnElement("linkText", MailsPerPage);
		clickOnLink("linkText", MailsPerPage);
		Thread.sleep(3000);
	}

	// Retrieve the count of all emails by filter type -
	// Read/Unread/All/Starred/Unstarred/Important/Unimportant.

	public int selectVibeMails(String filterName) throws Exception {
		logInfo("inside selectVibeMails() method..");

		// setMailsPerPage("100 emails per page");
		go2Inbox();

		verifyElementPresent("xpath", btnFilterInboxEmail);
		clickOnButton("xpath", btnFilterInboxEmail);

		// clickOnLink("linkText",filterName);
		switch (filterName) {
		case "All":
			clickOnElement("xpath", "//*[@id='bulk-controls-container']/div[1]/ul/li[1]/a");
			Thread.sleep(5000);
		case "None":
			clickOnElement("xpath", "//*[@id='bulk-controls-container']/div[1]/ul/li[2]/a");
			Thread.sleep(5000);
		case "Read":
			clickOnElement("xpath", "//*[@id='bulk-controls-container']/div[1]/ul/li[3]/a");
			Thread.sleep(5000);
		case "Unread":
			clickOnElement("xpath", "//*[@id='bulk-controls-container']/div[1]/ul/li[4]/a");
			Thread.sleep(5000);
		case "Starred":
			clickOnElement("xpath", "//*[@id='bulk-controls-container']/div[1]/ul/li[5]/a");
			Thread.sleep(5000);
		case "Unstarred":
			clickOnElement("xpath", "//*[@id='bulk-controls-container']/div[1]/ul/li[6]/a");
			Thread.sleep(5000);
		case "Important":
			clickOnElement("xpath", "//*[@id='bulk-controls-container']/div[1]/ul/li[7]/a");
		case "Unimportant":
			clickOnElement("xpath", "//*[@id='bulk-controls-container']/div[1]/ul/li[8]/a");
			Thread.sleep(5000);
		}

		WebElement e = driver().findElement(By.xpath(tblInboxBody));
		List allRows = e.findElements(By.tagName("tr"));
		int count = allRows.size() - 1;
		System.out.println("Total mails =" + count);

		String beforechk = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterchk = "]/td[1]/input";
		int chkCount = 0;
		for (int i = 1; i <= count - 1; i++) {
			WebElement chkBox = driver().findElement(By.xpath(beforechk + i + afterchk));
			if (chkBox.isSelected()) {
				chkCount++;
			}
		}

		logInfo("Total selected emails in Inbox = " + chkCount);
		return chkCount;
	}

	public boolean selectVibeMailsWithSubject(String subject) throws Exception {
		logInfo("inside selectVibeMailsWithSubject() method..");

		// setMailsPerPage("100 emails per page");
		//go2Inbox();
		//setMailsPerPage("100 emails per page");
		//driver().navigate().refresh();    // remove the statement later
		
		waitOnElement("xpath", tblInboxBody);
		WebElement e = driver().findElement(By.xpath(tblInboxBody));
		List allRows = e.findElements(By.tagName("tr"));
		int count = allRows.size();

		String before = "//*[@id='bulk-form']/table/tbody/tr[";
		String after = "]/td[5]/span[1]";

		String beforeChk = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterChk = "]/td[1]/input";
		boolean isMatchFound = false;

		for (int i = 1; i <= count - 1; i++) {
			WebElement x = driver().findElement(By.xpath(before + i + after));
			String act_subject = x.getText().trim();
			if (act_subject.equalsIgnoreCase(subject)) {
				isMatchFound = true;
				WebElement chk = driver().findElement(By.xpath(beforeChk + i + afterChk));
				chk.click();
				logInfo("email with subject \'" + subject + "\' match found @" + i);
				// Assert.assertTrue(act_subject.equalsIgnoreCase(subject),
				// "email with subject \'" + subject + "\' match found @" + i);
			}
		}

		if (isMatchFound == false) {
			logInfo("No emails found with the specified subject : " + subject);
			// Assert.assertTrue(isMatchFound, "No emails found with the
			// specified subject : " + subject); // can comment later
		}
		return isMatchFound;
	}

	public void markEmailStarred(String subject) throws Exception {
		logInfo("inside markEmailStarred() method..");
		go2Inbox();

		setMailsPerPage("100 emails per page");
		Thread.sleep(5000);

		WebElement e = driver().findElement(By.xpath(tblInboxBody));
		List allRows = e.findElements(By.tagName("tr"));
		int count = allRows.size();
		boolean isEmailFound = false;

		String beforeSubj = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterSubj = "]/td[5]/span[1]";

		String beforeStarred = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterStarrted = "]/td[3]/a/i";

		logInfo("Number of emails = " + count);

		for (int i = 1; i <= count - 1; i++) {
			WebElement x = driver().findElement(By.xpath(beforeSubj + i + afterSubj));
			String act_subject = x.getText().trim();
			System.out.println("Actual Subject = " + act_subject);
			if (act_subject.equalsIgnoreCase(subject.trim())) {
				logInfo(act_subject + " email match found.");
				isEmailFound = true;
				// implicityWaits(10);
				WebElement eleStarred = driver().findElement(By.xpath(beforeStarred + i + afterStarrted));
				eleStarred.click();
				break;
			}
		}

		if (isEmailFound == false) {
			logInfo("\'" + subject + "\'  email match not found.");
			Assert.assertTrue(false, "\'" + subject + "\'  email match not found.");
		}
	}

	public void verifyEmailStarred(String subject) throws InterruptedException, Exception {
		logInfo("inside verifyEmailStarred() method..");

		go2Inbox();
		clickOnLink("xpath", lnkStarred);
		Thread.sleep(5000);

		WebElement e = driver().findElement(By.xpath(tblInboxBody));
		List allRows = e.findElements(By.tagName("tr"));
		int count = allRows.size() - 1;
		boolean isEmailStarred = false;
		String beforeSubj = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterSubj = "]/td[5]/span[1]";

		logInfo("Number of emails = " + count);

		for (int i = 1; i <= count - 1; i++) {
			WebElement x = driver().findElement(By.xpath(beforeSubj + i + afterSubj));
			String act_subject = x.getText().trim();
			if (act_subject.equalsIgnoreCase(subject.trim())) {
				logInfo(subject + " email match found and marked as starred.");
				isEmailStarred = true;
				break;
			}
		}

		if (isEmailStarred == false) {
			logInfo(subject + " no emails marked as starred.");
			Assert.assertTrue(isEmailStarred, subject + " email could not be marked as starred.");
		}
	}

	public void markEmailsImportant(String subject) throws Exception {
		logInfo("inside markEmailsImportant() method..");
		go2Inbox();
		// setMailsPerPage("100 emails per page");

		Thread.sleep(5000);

		WebElement e = driver().findElement(By.xpath(tblInboxBody));
		List allRows = e.findElements(By.tagName("tr"));
		int count = allRows.size();
		boolean isEmailFound = false;
		String beforeSubj = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterSubj = "]/td[5]/span[1]";

		String beforeImp = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterImp = "]/td[4]/a/i";

		logInfo("Number of emails = " + count);

		for (int i = 1; i <= count - 1; i++) {
			WebElement x = driver().findElement(By.xpath(beforeSubj + i + afterSubj));
			String act_subject = x.getText().trim();
			if (act_subject.equalsIgnoreCase(subject.trim())) {
				logInfo(subject + " : email match found.");
				isEmailFound = true;
				// implicityWaits(10);
				WebElement eleStarred = driver().findElement(By.xpath(beforeImp + i + afterImp));
				eleStarred.click();
				break;
			}
		}

		if (isEmailFound == false) {
			logInfo(subject + " email match not found.");
			Assert.assertTrue(isEmailFound, subject + " match not found.");
		}

	}

	public void verifyImpEmails(String subject) throws InterruptedException, Exception {
		logInfo("inside verifyImpEmails() method..");

		clickOnLink("xpath", lnkImportant);
		Thread.sleep(5000);

		WebElement e = driver().findElement(By.xpath(tblInboxBody));
		List allRows = e.findElements(By.tagName("tr"));
		int count = allRows.size();
		boolean isEmailMarked = false;
		String beforeSubj = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterSubj = "]/td[5]/span[1]";

		logInfo("Number of emails = " + count);

		for (int i = 1; i <= count - 1; i++) {
			WebElement x = driver().findElement(By.xpath(beforeSubj + i + afterSubj));
			String act_subject = x.getText().trim();
			if (act_subject.equalsIgnoreCase(subject.trim())) {
				logInfo(subject + " email match found and marked as important.");
				isEmailMarked = true;
				break;
			}
		}

		if (isEmailMarked == false) {
			logInfo(subject + " no emails marked as found important.");
			Assert.assertTrue(isEmailMarked, subject + " no emails marked as found important.");
		}
	}

	public void createLabel(String labelName) throws Exception {
		logInfo("inside createLabel() method.");
		
		scrollDown("linkText","Create New");
		clickOnElement("linkText","Create New");
		Thread.sleep(3000);
		waitOnElement("cssSelector", inputLabelName);
		clickOnElement("cssSelector", inputLabelName);
		inputText("cssSelector", inputLabelName, labelName_text);
		clickOnButton("cssSelector", btnLabelSave);
		Thread.sleep(3000);
		logInfo(labelName + " creating label");
		//confirmationMessage("Label created successfully.");
	}

	
	// Apply label for emails

	public void applyLabel(String labelName) throws Exception {
		logInfo("inside applyLabel() method..");
		
		waitOnElement("xpath", btnApplyLabel);
		clickOnButton("xpath", btnApplyLabel);

		WebElement applyLabelPanels = driver().findElement(By.xpath(labelControl));
		List<WebElement> li_menus = applyLabelPanels.findElements(By.tagName("li"));
		int li_count = li_menus.size();

		String before = "//div[@class='btn-group apply-label-group open']/ul/li[";
		String after = "]/a";

		for (int i = li_count-2; i >0; i--) {
			WebElement li = driver().findElement(By.xpath(before + i + after));
			String liName = li.getText().trim();
			if (liName.equalsIgnoreCase(labelName)) {
				li.click();
				logInfo("clicked on menu label " + liName);
				break;
			}
		}
		
	}

	public boolean verifyLabeledEmail(String subject) throws Exception {
		logInfo("inside verifyLabeledEmail() method..");
		boolean isLabelFound = false;
		
		waitOnElement("xpath",tblInboxBody);
		WebElement gmailBody = driver().findElement(By.xpath(tblInboxBody));
		List allRows = gmailBody.findElements(By.tagName("tr"));
		int count = allRows.size();

		String beforeSubj = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterSubj = "]/td[5]/span[1]";

		String beforeChk = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterChk = "]/td[1]/input";

		String beforelbl = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterlbl = "]/td[5]/div[1]/span/a[1]"; // "]/td[5]/span[1]/a[1]";

		int matchCnt = 0;
		for (int i = 1; i <= count - 1; i++) {
			WebElement x = driver().findElement(By.xpath(beforeSubj + i + afterSubj));
			String act_subject = x.getText().trim();
			if (act_subject.equalsIgnoreCase(subject)) {
				logInfo(subject + " match found.");
				matchCnt++;
				WebElement input = driver().findElement(By.xpath(beforeChk + i + afterChk));
				input.click();
				WebElement elabel = driver().findElement(By.xpath(beforelbl + i + afterlbl));
				String lblName = elabel.getText().trim();
				if (lblName.equalsIgnoreCase(labelName_text)) {
					logInfo("Email with " + subject + " has the label " + lblName);
					isLabelFound = true;
					// Assert.assertTrue(lblName.equalsIgnoreCase(labelName_text),
					// "Email with " + subject + " has the label " + lblName );
				}
			}
		}

		if (matchCnt == 0) {
			logInfo(subject + " match not found.");
			Assert.assertTrue(false, "No emails found with " + subject + " and has the label.");
		}
		return isLabelFound;
	}
	
	
	public void updateEmailLabel(String labelName) throws Exception {
		logInfo("inside updateEmailLabel() method.");
		
		//go2Inbox();
	
		waitOnElement("xpath",lableSidePane);
		WebElement lablePanel = driver().findElement(By.xpath(lableSidePane));
		List li_items = lablePanel.findElements(By.xpath("li"));
		int li_count = li_items.size();

		String before = "//*[@id='email-navigation']/ul[3]/li[";
		String after = "]/a";  // /span

		String beforeEdit = "//*[@id='email-navigation']/ul[3]/li[";
		String afterEdit = "]/span/a[1]/span";  
		
		System.out.println("li_count =" +li_count);
		for (int i=li_count-1; i>=1; i--) {
			System.out.println("i =" +i);
			WebElement link = driver().findElement(By.xpath(before + i + after));
			String linkName = link.getText().trim();
			System.out.println("linkName = "+linkName);
			if (linkName.contains(labelName.trim())) {
				logInfo(linkName + " : label match found for deletion.");
				scrollDown("xpath",before + i +"]");  // 
				hoverOnElement("xpath",before + i +"]" );  
				hoverOnElement("xpath",beforeEdit + i + afterEdit);
				clickOnElement("xpath",beforeEdit + i + afterEdit);
				Thread.sleep(3000);
				waitOnElement("cssSelector", inputLabelName);
				clickOnElement("cssSelector", inputLabelName);
				inputTextClear("cssSelector", inputLabelName);
				inputText("cssSelector", inputLabelName, labelName+"_Updated");
				clickOnButton("cssSelector", btnLabelSave);
				Thread.sleep(5000);
				break;
			}
		}
	}

	
	public boolean verifyEmailLabelIsPresent(String labelName) throws  Exception {
		logInfo("inside verifyEmailLabelIsPresent() method.");
		boolean isLabelFound = false;
			
		waitOnElement("xpath",lableSidePane);
		WebElement lablePanel = driver().findElement(By.xpath(lableSidePane));
		List li_items = lablePanel.findElements(By.xpath("li"));
		int li_count = li_items.size();

			String before = "//*[@id='email-navigation']/ul[3]/li[";
			String after = "]/a";  // /span

			System.out.println("li_count =" +li_count);
			for (int i=li_count-1; i>=1; i--) {
				System.out.println("i =" +i);
				WebElement link = driver().findElement(By.xpath(before + i + after));
				String linkName = link.getText().trim();
				System.out.println("linkName = "+linkName);
				if (linkName.contains(labelName.trim())) {
					logInfo(linkName + " : label match found.");
					isLabelFound = true;					
					break;
				}
			}

		return isLabelFound;
	}
	
	public void deleteLabel(String labelName) throws Exception {
		logInfo("inside deleteLabel() method...");
		
		go2Inbox();
		Thread.sleep(5000);
		
		waitOnElement("xpath",lableSidePane);
		WebElement lablePanel = driver().findElement(By.xpath(lableSidePane));
		List li_items = lablePanel.findElements(By.xpath("li"));
		int li_count = li_items.size();

		String before = "//*[@id='email-navigation']/ul[3]/li[";
		String after = "]/a";  // /span

		String beforeClose = "//*[@id='email-navigation']/ul[3]/li[";
		String afterClose = "]/span/a[2]/span";  //]/span/a[2]/span/i"; 
		
		System.out.println("li_count =" +li_count);
		for (int i=li_count-1; i>=1; i--) {
			System.out.println("i =" +i);
			WebElement link = driver().findElement(By.xpath(before + i + after));
			String linkName = link.getText().trim();
			System.out.println("linkName = "+linkName);
			if (linkName.contains(labelName.trim())) {
				logInfo(linkName + " : label match found for deletion.");
				scrollDown("xpath",before + i +"]");  // 
				hoverOnElement("xpath",before + i +"]" );  
				hoverOnElement("xpath",beforeClose + i + afterClose);
				clickOnElement("xpath",beforeClose + i + afterClose);
				waitOnElement("cssSelector",deleteToOk);
				confirmOK();
				Thread.sleep(5000);
				break;
			}
		}
	}


	
	
	// Compose an email and send to another (internal/external) users.

		public void composeVibeEmail(String recipients, String subject) throws Exception {
			logInfo("inside composeVibeEmail() method...");
			
			go2Inbox();		
			waitOnElement("linkText", "New Email");
			clickOnLink("linkText", "New Email");
			Thread.sleep(5000);
		
			driver().findElement(By.cssSelector(recipientsTo)).sendKeys(recipients);
			waitOnElement("cssSelector", subject_Mail);
			inputText("cssSelector", subject_Mail, subject);
			Thread.sleep(3000);
			waitOnElement("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]");
			composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]", inboxMsgBodyText);
			//driver().findElement(By.xpath("//iframe[contains(@title,'Rich Text Editor, email_textarea')]")).sendKeys(inboxMsgBodyText);
			Thread.sleep(3000);
			scrollDown("linkText", "Send");

			clickOnLink("linkText", "Send");
			System.out.println("sent mail to gmail with subject " + subject);
			Thread.sleep(10000);
		}
		
		

	public void minimizeQuickChatWindow() throws InterruptedException {
		logInfo("inside minimizeQuickChatWindow() method.");

		boolean isQuickWindowPresent = verifyElementPresent("xpath", "//*[@id='chat-sidebar']/div[1]/div[1]");
		if (isQuickWindowPresent) {
			clickOnElement("xpath", "//*[@id='chat-sidebar']/div[1]/div[1]");
		}
	}

	// Compose an email with attachments 

	public void composeVibeEmailwithAttachment(String recipients, String subject) throws Exception {
		logInfo("inside composeVibeEmailwithAttachment() method...");
		
		go2Inbox();
		
		clickOnLink("linkText", "New Email");
		Thread.sleep(5000);
		waitOnElement("cssSelector",recipientsTo);
		WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); 
		inputText("cssSelector", recipientsTo, recipients);
		composeTo.click();
		Thread.sleep(3000);
		inputText("cssSelector", subject_Mail, subject);
		waitOnElement("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]");
		composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]", inboxMsgBodyText);
		
		//scrollDown("cssSelector","#integrated-message-attach-file-field-label");
		//clickOnElement("cssSelector","#integrated-message-attach-file-field-label");
		Thread.sleep(5000);
		
		//inputText("cssSelector","input#integrated-message-attach-file-field","C:\\vibe\\testdata\\files\\icentris_text.txt");
		//inputText("cssSelector","integrated-message-attach-file-field-inline","C:\\vibe\\testdata\\files\\icentris_text.txt");
		
		composeText("cssSelector","input#integrated-message-attach-file-field","C:\\vibe\\testdata\\files\\icentris_text.txt");
		
		//JavascriptExecutor jse = (JavascriptExecutor) driver;
		//jse.executeScript("document.getElementByCssSelector('input#integrated-message-attach-file-field').value = 'C:\\vibe\\testdata\\files\\icentris_text.txt';");
		
		// uploadFile("Image","#message-attach-file-field");
		//uploadFile("Document","#message-attach-file-field");
		Thread.sleep(3000);
		
		//clickOnElement("cssSelector","div[class='modal fade upload_file_modal in'] > .modal-dialog  > .modal-content > div.modal-footer> button:nth-child(1)");
		//Thread.sleep(10000);
		
		scrollDown("linkText", "Send");
		clickOnLink("linkText", "Send");
		
	}


	
	
	public void composeVibeEmailwithRescAttachment(String recipients, String subject, String categoryName,
		String resourceName) throws Exception {		 // ,String assetName
		logInfo("inside composeVibeEmailwithRescAttachment() method...");
		go2Inbox();
		
		clickOnLink("linkText", "New Email");
		Thread.sleep(5000);
		waitOnElement("cssSelector",recipientsTo);
		
		WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); 
		inputText("cssSelector", recipientsTo, recipients);
		composeTo.click();
		
		inputText("cssSelector", subject_Mail, subject);
		waitOnElement("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]");
		composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]", inboxMsgBodyText);
		
		scrollDown("linkText", "Attach Resource File");
		clickOnLink("linkText", "Attach Resource File");
		Thread.sleep(5000);
		
		waitOnElement("cssSelector","#resource_query");
		inputTextClear("cssSelector","#resource_query");
		inputText("cssSelector","#resource_query",resourceName+" ");
		Thread.sleep(5000);
		WebElement resPanel = driver().findElement(By.cssSelector("div.resource-checklist"));
		List allRows = resPanel.findElements(By.className("row"));
		System.out.println("Total Rows = "+allRows.size());
		
		String before_prodCategory = "//div[@class='resource-checklist']/div[";
		String after_prodCategory ="]/div/div[1]";
		String after_prodIcon = "]/div/div[1]/a";
		
		if(allRows.size()>0) {
			for(int i=1;i<=allRows.size();i++) {
				WebElement pc = driver().findElement(By.xpath(before_prodCategory+i+after_prodCategory));
				String act_prodcat = pc.getText().trim();
				if(act_prodcat.equalsIgnoreCase(categoryName)) {
					WebElement pi = driver().findElement(By.xpath(before_prodCategory+i+after_prodIcon));
					pi.click();
					Thread.sleep(3000);
					String after_res = "]/div/div[2]/div/div/div[2]/div/div[1]";
					String after_resInput = "]/div/div[2]/div/div/div[2]/input";
					WebElement res = driver().findElement(By.xpath(before_prodCategory+i+after_res));
					String act_res = res.getText().trim();
					if(act_res.equalsIgnoreCase(resourceName)) {
						WebElement resIcon = driver().findElement(By.xpath(before_prodCategory+i+after_resInput));
						resIcon.click();
						break;
					}
				}
				
			}
		}
		
		clickOnButton("xpath", btnRescSave);
		Thread.sleep(5000);
		clickOnLink("linkText", "Send");
		Thread.sleep(10000);
	}

	// Verify if email with the specified subject created is shown in the inbox.

	public void verifyVibeInboxMails(String subject) throws Exception {
		logInfo("inside verifyVibeInboxMails() method...");
		go2Inbox();

		WebElement inboxPane = driver().findElement(By.xpath(inboxPanel));
		List allRows = inboxPane.findElements(By.tagName("tr"));
		int rows_count = allRows.size();
		Boolean isEmailFound = false;

		for (int i = 1; i <= rows_count - 1; i++) {
			String beforeSummary = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterSummary = "]/td[5]/span[1]";
			WebElement x = driver().findElement(By.xpath(beforeSummary + i + afterSummary));
			String summary = x.getText().trim();

			if (summary.equalsIgnoreCase(subject.trim())) {
				isEmailFound = true;
				logInfo(subject + " email match found in inbox.");

				Assert.assertTrue(isEmailFound == true, subject + " email match found in inbox.");
				break;
			}
		}

		if (isEmailFound == false) {
			logInfo(subject + " email match could not be found in inbox.");
			Assert.assertTrue(isEmailFound == true, subject + " email match could not be found in inbox.");
		}
	}

	// Delete vibe inbox mails based on the filter type -
	// Read/Unread/All/Starred/Unstarred/Important/Unimportant.

	public void deleteFilteredVibeMails() throws Exception {
		logInfo("inside deleteFilteredVibeMails() method..");

		WebElement gmailBody = driver().findElement(By.xpath(tblInboxBody));
		List allRows = gmailBody.findElements(By.tagName("tr"));
		int count = allRows.size();

		String beforechk = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterchk = "]/td[1]/input";

		int chkCount = 0;
		for (int i = 1; i <= count - 1; i++) {
			WebElement chkBox = driver().findElement(By.xpath(beforechk + i + afterchk));
			if (chkBox.isSelected()) {
				chkCount++;
			}
		}

		logInfo("no of emails selected to delete = " + chkCount);
		if (chkCount > 0) {
			//verifyElementPresent("xpath", btnDeleteInboxMail);
			clickOnButton("xpath", btnDeleteInboxMail);
			Thread.sleep(10000);
		}
	}

	// Verify email deleted is displayed in the trashed folder.

	public void verifyTrashedEmails(String subject) throws Exception {
		logInfo("inside verifyTrashedEmails() method.");
		go2Inbox();
		clickOnLink("linkText", "Trash");
		Thread.sleep(5000);
		WebElement inboxPane = driver().findElement(By.xpath(inboxPanel));
		List allRows = inboxPane.findElements(By.tagName("tr"));
		int rows_count = allRows.size();
		Boolean isEmailFound = false;
		for (int i = 1; i <= rows_count - 1; i++) {
			String beforeSummary = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterSummary = "]/td[5]/span[1]";
			WebElement x = driver().findElement(By.xpath(beforeSummary + i + afterSummary));
			String summary = x.getText().trim();
			if (summary.equalsIgnoreCase(subject.trim())) {
				isEmailFound = true;
				break;
			}
		}

		if (isEmailFound == true) {
			logInfo("subject \'" + subject + "\' email deleted is found in trash folder.");
			Assert.assertTrue(isEmailFound == true,
					"subject \'" + subject + "\' email deleted is found in trash folder.");
		} else {
			logInfo("subject \'" + subject + "\' email not found in the trash folder.");
			Assert.assertTrue(isEmailFound == true, "subject \'" + subject + "\' email not found in the trash folder.");
		}
	}

	// Set Notifications for the user account

	public void setNotifications2User(String userName) throws Exception {
		logInfo("inside setNotifications2User() method.");
		driver().navigate().to(appUrl + "pyr_core/account");

		clickOnLink("linkText", "Notifications");
		WebElement notificationTbl = driver().findElement(By.xpath(paneNotifications));
		List allRows = notificationTbl.findElements(By.tagName("tr"));
		int all_rows = allRows.size();

		String before_name = "//*[@id='messaging']/div[1]/table/tbody/tr[";
		String after_name = "]/td[1]";

		String before_row = "//*[@id='messaging']/div[1]/table/tbody/tr[";
		String after_row = "]/td[2]/input";

		if (all_rows > 0) {
			for (int i = 1; i <= all_rows; i++) {
				WebElement e = driver().findElement(By.xpath(before_name + i + after_name));
				String email = e.getText().trim();
				if (email.equalsIgnoreCase(userName)) {
					WebElement radio = driver().findElement(By.xpath(before_row + i + after_row));
					radio.click();
					break;
				}
			}
		}
	}

	public boolean selectMailsWithSubject(String subject) throws Exception {
		logInfo("inside selectVibeMailsWithSubject() method..");
		
		// go2Inbox();
		waitOnElement("xpath", tblInboxBody);
		WebElement e = driver().findElement(By.xpath(tblInboxBody));
		List allRows = e.findElements(By.tagName("tr"));
		int count = allRows.size();

		String before = "//*[@id='bulk-form']/table/tbody/tr[";
		String after = "]/td[5]/span[1]";

		String beforeChk = "//*[@id='bulk-form']/table/tbody/tr[";
		String afterChk = "]/td[1]/input";
		boolean isMatchFound = false;

		for (int i = 1; i <= count - 1; i++) {
			WebElement x = driver().findElement(By.xpath(before + i + after));
			String act_subject = x.getText().trim();
			// if (act_subject.matches(subject)) {
			// if (subject.contains(act_subject)) { // use this later
			if (subject.equalsIgnoreCase(act_subject)) {
				isMatchFound = true;
				WebElement chk = driver().findElement(By.xpath(beforeChk + i + afterChk));
				chk.click();
				logInfo("email with subject \'" + subject + "\' match found @" + i);
				// Assert.assertTrue(act_subject.equalsIgnoreCase(subject),
				// "email with subject \'" + subject + "\' match found @" + i);
			}
		}

		if (isMatchFound == false) {
			logInfo("No emails found with the specified subject : " + subject);
			// Assert.assertTrue(isMatchFound, "No emails found with the
			// specified subject : " + subject);
		}
		return isMatchFound;
	}

	// count emails in inbox page

	public int countEmailsInInboxPage(String setMailsPerPage, String filter) throws Exception {
		logInfo("inside countEmailsInInboxPage() method.");

		setMailsPerPage(setMailsPerPage);
		go2Inbox();

		waitOnElement("xpath", btnFilterInboxEmail);
		clickOnButton("xpath", btnFilterInboxEmail);
		clickOnLink("linkText", filter);

		WebElement e = driver().findElement(By.xpath(tblInboxBody));
		List allRows = e.findElements(By.tagName("tr"));
		int count = allRows.size() - 1;

		logInfo("Total emails in Inbox = " + count);
		return count;
	}

	public boolean verifyEmailsCheckedForFilter(String subject, String filter) throws Exception {
		logInfo("inside verifyEmailsCheckedForFilter() method.");

		// go2Inbox();
		// setMailsPerPage("100 emails per page");

		waitOnElement("xpath", btnFilterInboxEmail);
		clickOnButton("xpath", btnFilterInboxEmail);
		clickOnLink("linkText", filter);

		waitOnElement("xpath", tblInboxBody);
		WebElement e = driver().findElement(By.xpath(tblInboxBody));
		List allRows = e.findElements(By.tagName("tr"));

		String before_chk = "//*[@id='bulk-form']/table/tbody/tr[";
		String after_chk = "]/td[1]/input";

		String before = "//*[@id='bulk-form']/table/tbody/tr[";
		String after = "]/td[5]/span[1]";
		boolean isEmailMatched = false;

		for (int i = 1; i <= allRows.size() - 1; i++) {
			WebElement chk = driver().findElement(By.xpath(before_chk + i + after_chk));
			boolean isChecked = chk.isSelected();

			WebElement subj = driver().findElement(By.xpath(before + i + after));
			String actSubj = subj.getText().trim();

			if (actSubj.equalsIgnoreCase(subject) && isChecked == true) {
				logInfo(subject + " email match is found checked.");
				isEmailMatched = true;
				break;
			}
			Thread.sleep(1000);
		}

		return isEmailMatched;
	}

	public boolean viewEmailsWithSubject(String subject) throws Exception {
		logInfo("inside viewEmailsWithSubject() method..");

		// setMailsPerPage("100 Emails Per Page");
		// go2Inbox();
		
		waitOnElement("xpath", tblInboxBody);
		WebElement e = driver().findElement(By.xpath(tblInboxBody));
		List allRows = e.findElements(By.tagName("tr"));
		int count = allRows.size();

		String before = "//*[@id='bulk-form']/table/tbody/tr[";
		String after = "]/td[5]/span[1]";

		boolean isMatchFound = false;
		for (int i = 1; i <= count - 1; i++) {
			WebElement x = driver().findElement(By.xpath(before + i + after));
			String act_subject = x.getText().trim();
			// if (act_subject.matches(subject)) {
			if (subject.contains(act_subject)) {
				isMatchFound = true;
				x.click();
				logInfo("email with subject \'" + subject + "\' match found @" + i);
				Thread.sleep(5000);
				break;
				// Assert.assertTrue(act_subject.equalsIgnoreCase(subject),
				// "email with subject \'" + subject + "\' match found @" + i);
			}
		}

		if (isMatchFound == false) {
			logInfo("No emails found with the specified subject : " + subject);
			// Assert.assertTrue(isMatchFound, "No emails found with the
			// specified subject : " + subject);
		}
		return isMatchFound;
	}

	public int getInboxCntInQuickLinksWidget() {
		logInfo("inside getInboxCntInQuickLinksWidget() method");

		WebElement e = driver().findElement(By.xpath("//*[@id='user-control-panel']/div[2]/div/a[4]/div[1]"));
		String inboxCnt = e.getText().trim();
		int count = Integer.parseInt(inboxCnt);

		return count;
	}

	public void searchEmail(String emailSubject) throws Exception {
		logInfo("inside searchEmail() method");

		inputTextClear("xpath", inputSearch);
		inputText("xpath", inputSearch, emailSubject);
		clickOnElement("xpath", "//*[@id='btn-search']");
		Thread.sleep(5000);

	}

	public void selectInboxFolder(String folderName) throws  Exception {
		logInfo("inside selectInboxFolder() method");

		switch (folderName) {
		case "Inbox":
			driver().navigate().to(appUrl + "crm/messages");
			driver().navigate().refresh();
			logInfo("selected Inbox folder");
			Thread.sleep(3000);
			break;
		case "Starred":
			driver().navigate().to(appUrl + "crm/messages/starred");
			driver().navigate().refresh();
			logInfo("selected Starred folder");
			Thread.sleep(3000);
			break;
		case "Important":
			driver().navigate().to(appUrl + "crm/messages/important");
			driver().navigate().refresh();
			logInfo("selected Important folder");
			Thread.sleep(3000);
			break;
		case "Sent":
			driver().navigate().to(appUrl + "crm/messages/sent");
			driver().navigate().refresh();
			logInfo("selected Sent folder");
			Thread.sleep(3000);
			break;
		case "Attachments":
			driver().navigate().to(appUrl + "crm/messages/drive");
			driver().navigate().refresh();
			logInfo("selected Attachments folder");
			Thread.sleep(3000);
			break;
		case "Trash":
			driver().navigate().to(appUrl + "crm/messages/trash");
			driver().navigate().refresh();
			logInfo("selected Trash folder");
			Thread.sleep(3000);
			break;
		case "Spam":
			driver().navigate().to(appUrl + "crm/messages/spam");
			driver().navigate().refresh();
			logInfo("selected Spam folder");
			Thread.sleep(3000);
			break;
		}
	}

	public void performMoreActionsForSelectedEmails(String actionName) throws Exception {
		logInfo("inside performMoreActionsForSelectedEmails() method.");
		// go2Inbox();

		waitOnElement("xpath", btnMoreFilter);
		clickOnButton("xpath", btnMoreFilter);
		clickOnLink("linkText", actionName);
		Thread.sleep(5000);
	}

	public void go2Users() {
		logInfo("inside go2Users() method.");
		driver().navigate().to(appUrl + "pyr_core/users");
	}

	public void loginAsUser(String user) throws Exception {
		logInfo("inside loginAsUser() method.");
		go2Users();
		searchUser(user);
		boolean isUserFound = verifyUserPresent(user);
		if (isUserFound) {
			clickOnLink("linkText", "Login As User");
			logInfo("clicked on Login As User button");
		}
	}

	public void searchUser(String uName) throws  Exception {
		logInfo("inside searchUser() method.");
		waitOnElement("xpath", searchUser);
		inputTextClear("xpath", searchUser);
		inputText("xpath", searchUser, uName + " ");
		clickOnButton("cssSelector", btnUserSearch);
		Thread.sleep(5000);
	}

	public boolean verifyUserPresent(String uName) throws  Exception {
		logInfo("inside verifyUserPresent() method.");

		Thread.sleep(5000);
		waitOnElement("xpath", tblAdminUsers);
		WebElement tblUsers = driver().findElement(By.xpath(tblAdminUsers));
		List allUsers = tblUsers.findElements(By.tagName("tr"));
		String before_name = "//*[@id='users']/table/tbody/tr[";
		String after_name = "]/td[3]/a";
		boolean isUserFound = false;
		if (allUsers.size() > 0) {
			for (int i = 1; i <= allUsers.size(); i++) {
				WebElement tblmsg = driver().findElement(By.xpath("//*[@id='users']/table/tbody/tr"));
				List msgrow = tblmsg.findElements(By.tagName("td"));
				if (msgrow.size() > 1) {
					WebElement x = driver().findElement(By.xpath(before_name + i + after_name));
					String name = x.getText().trim();
					verifyElementPresent("xpath", before_name + i + after_name);
					if (name.equalsIgnoreCase(uName)) {
						isUserFound = true;
						logInfo(uName + " user found in user search page.");
						break;
					}
				}
			}
		}

		if (isUserFound == false) {
			logInfo(uName + " user not found in user search page.");
			Assert.assertTrue(isUserFound, uName + " user not found in user search page.");
		}
		return isUserFound;
	}

	public void go2Homepage() {
		logInfo("inside loginAsUser() method.");
		driver().navigate().to(appUrl);
	}

	public void submitReply(String subject) throws  Exception {
		logInfo("inside submitReply() method.");

		waitOnElement("linkText", "Reply");
		clickOnElement("linkText", "Reply");
		
		waitOnElement("cssSelector", subject_Mail);
		inputTextClear("cssSelector", subject_Mail);
		inputText("cssSelector", subject_Mail, subject);

		waitOnElement("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]");
		composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]", subject + " replied");
		scrollDown("linkText", "Send");
	
		clickOnLink("linkText", "Send");
		Thread.sleep(5000);
	}
	
	public void markEmailAsSpam() throws  Exception {
		logInfo("inside markEmailAsSpam() method.");
		
		waitOnElement("linkText", "Mark Spam");
		clickOnElement("linkText", "Mark Spam");
		Thread.sleep(4000);
	}

	public void forwardEmail(String subject, String forwardRecepient) throws  Exception {
		logInfo("inside forwardEmail() method.");

		waitOnElement("linkText", "Forward");
		clickOnElement("linkText", "Forward");
		
		WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); // inputVibeComposeTo
		composeTo.click();

		clickOnElement("cssSelector", recipientsTo);
		Thread.sleep(5000);
		inputText("cssSelector", recipientsTo, forwardRecepient);
		Thread.sleep(5000);

		waitOnElement("cssSelector", subject_Mail);
		inputTextClear("cssSelector", subject_Mail);
		inputText("cssSelector", subject_Mail, subject);
		
		waitOnElement("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]");
		composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]", subject + " forwarded");
		scrollDown("linkText", "Send");
		
		clickOnLink("linkText", "Send");
		Thread.sleep(5000);
	}

	// Compose an email with attachment

	public void composeEmailWithAttachment(String recipients, String subject) throws Exception {
		logInfo("inside composeEmailWithAttachment() method.");
		go2Inbox();

		clickOnLink("linkText", "New Email");

		WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); // inputVibeComposeTo
		composeTo.click();

		System.out.println("recipients = " + recipients);

		clickOnElement("cssSelector", recipientsTo);
		Thread.sleep(5000);
		inputText("cssSelector", recipientsTo, recipients);
		Thread.sleep(5000);
		// clickOnElement("xpath","//*[@class='mp_item mp_selectable
		// mp_highlighted']");

		inputText("cssSelector", subject_Mail, subject);

		Robot rb = new Robot();
		rb.delay(2000);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.delay(2000);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_I);
		rb.keyRelease(KeyEvent.VK_I);
		rb.keyPress(KeyEvent.VK_B);
		rb.keyRelease(KeyEvent.VK_B);
		rb.keyPress(KeyEvent.VK_E);
		rb.keyRelease(KeyEvent.VK_E);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);

		addAttachment("Image");

		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(10000);
	}

	public void addAttachment(String fileType) throws  Exception {
		logInfo("inside addAttachment() method");

		clickOnElement("linkText", "Attach A File");

		/*
		Thread.sleep(10000);
		waitOnElement("cssSelector", "form.simple_form.new_pyr_crm_message > input#message-attach-file-field");
		
		 * clickOnElement("cssSelector",
		 * "form.simple_form.new_pyr_crm_message > input#message-attach-file-field"
		 * );
		 * 
		 * uploadFile(fileType);
		 */
		
		//uploadFile(fileType, "form.simple_form.new_pyr_crm_message > input#message-attach-file-field");

		clickOnElement("cssSelector", "div.modal-content > div.modal-footer > button.btn.btn-default:nth-child(1)");
		Thread.sleep(30000);
	}

	public void composeEmailWithNoData() throws Exception {
		logInfo("inside composeEmailWithAttachment() method.");
		go2Inbox();

		clickOnLink("linkText", "New Email");

		verifyLinkPresent("Send");
		clickOnLink("linkText", "Send");

		//Thread.sleep(3000);
		confirmationMessage("Please add a recipient to your message.");
		
		/*waitOnElement("cssSelector", "div.modal-content > div.modal-body > div.bootbox-body");
		String msg = getTextPresentOnElement("cssSelector", "div.modal-content > div.modal-body > div.bootbox-body");
		return msg;*/
		
		
	}

	public void unsubscribeEmail(String recepient2Addr, String optStatus, String recepientFromAddr)
			throws  Exception {
		logInfo("inside unsubscribeEmail() method.");
		
		String parentwindowTitle= driver().getTitle();
		String parentwindowHandle = driver().getWindowHandle();
		System.out.println("parent title =" +parentwindowTitle);
		System.out.println("parent handle =" +parentwindowHandle);
		
		waitOnElement("linkText", "Unsubscribe");
		clickOnLink("linkText", "Unsubscribe");
		Thread.sleep(5000);
		
		Set <String> allWindowHandles = driver().getWindowHandles();
		System.out.println("Total Handles =" +allWindowHandles.size());
		
		if (allWindowHandles.size() >=2) {
		for (String currentWindowHandle : allWindowHandles) {
			System.out.println("currentWindowHandle ="+currentWindowHandle);
			
			if (!currentWindowHandle.equals(parentwindowHandle)) {
				System.out.println("inside child handle " + currentWindowHandle);
				
					driver().switchTo().window(currentWindowHandle);
					Thread.sleep(5000);
					
					//inputTextClear("cssSelector", inputToMail);
					//inputText("cssSelector", inputToMail, recepient2Addr);

					switch (optStatus) {
						case "Optin":
							selectFromDropdown("cssSelector", drpdnOptStatus, "byVisibleText", "opt in to mailings");
							break;
						case "Optout":
							selectFromDropdown("cssSelector", drpdnOptStatus, "byVisibleText", "opt out of mailings");
							break;
					}

					//inputTextClear("cssSelector", inputFromMail);
					//inputText("cssSelector", inputFromMail, recepientFromAddr);
	
					clickOnElement("cssSelector","input[value='submit']");
					Thread.sleep(3000);
					driver().close();
					driver().switchTo().window(parentwindowHandle);
					Thread.sleep(3000);
					break;
				}
		  }
		}
	}
	
	
	
	public boolean verifyAttachmentIsPresent(String fromRecepient) throws  Exception {
		logInfo("inside verifyAttachmentIsPresent() method.");

		String before_addr = "//*[@id='email-panel']/div[3]/div[";
		String after_addr = "]/div/div[1]/span";

		boolean isAttachPresent = false;
		waitOnElement("xpath", "//*[@id='email-panel']/div[3]");
		WebElement attachPanel = driver().findElement(By.xpath("//*[@id='email-panel']/div[3]"));
		List allMails = attachPanel
				.findElements(By.cssSelector("div.col-md-12 > div.media.actionable.attachment-container"));
		int all_mails = allMails.size();
		System.out.println("Total mails =" + all_mails);
		if (all_mails > 0) {
			for (int i = 1; i <= all_mails; i++) {
				WebElement x = driver().findElement(By.xpath(before_addr + i + after_addr));
				String addr = x.getText().trim();
				if (addr.contains(fromRecepient)) {
					System.out.println(addr);
					isAttachPresent = true;
					logInfo(fromRecepient + " is present in Attachment folder.");
					break;
				}
			}
		}

		if (isAttachPresent == false) {
			logInfo(fromRecepient + " is not present in Attachment folder.");
		}

		return isAttachPresent;
	}

	public boolean verifyFileExistsOnDisk(String filepath) {
		logInfo("inside verifyFileExistsOnDisk() method");
		File file = new File(filepath);
		return file.exists();
	}

	public boolean downloadAttachment(String fromRecepient) throws  Exception {
		logInfo("inside downloadAttachment() method.");
		System.out.println("fromRecepient = " + fromRecepient);

		String before_addr = "//*[@id='email-panel']/div[3]/div[";
		String after_addr = "]/div/div[1]/span";

		String before_download = "//*[@id='email-panel']/div[3]/div[";
		String after_download = "]/div/div[3]/div[4]/a[2]";

		boolean isAttachPresent = false;
		boolean isFileExists = false;
		String filepath = null;

		waitOnElement("xpath", "//*[@id='email-panel']/div[3]");
		WebElement attachPanel = driver().findElement(By.xpath("//*[@id='email-panel']/div[3]"));
		List allMails = attachPanel
				.findElements(By.cssSelector("div.col-md-12 > div.media.actionable.attachment-container"));
		int all_mails = allMails.size();
		System.out.println("Total mails =" + all_mails);
		fromRecepient = "To: " + fromRecepient;

		if (all_mails > 0) {
			for (int i = 1; i <= all_mails; i++) {
				System.out.println("Row no = " + i);
				WebElement x = driver().findElement(By.xpath(before_addr + i + after_addr));
				String addr = x.getText().trim();

				System.out.println("addr = " + addr);
				System.out.println("fromRecepient = " + fromRecepient);
				if (addr.equalsIgnoreCase(fromRecepient)) {
					isAttachPresent = true;
					WebElement download = driver().findElement(By.xpath(before_download + i + after_download));
					download.click();

					logInfo(fromRecepient + " is present in Attachment folder.");

					Thread.sleep(5000);
					Robot rb = new Robot();
					rb.keyPress(KeyEvent.VK_TAB);
					rb.keyRelease(KeyEvent.VK_TAB);
					rb.keyPress(KeyEvent.VK_TAB);
					rb.keyRelease(KeyEvent.VK_TAB);
					rb.keyPress(KeyEvent.VK_TAB);
					rb.keyRelease(KeyEvent.VK_TAB);
					rb.keyPress(KeyEvent.VK_ENTER);
					rb.keyRelease(KeyEvent.VK_ENTER);

					filepath = "c:\\vibe\\downloads\\";
					if (verifyFileExistsOnDisk(filepath + "icentris_image.jpg")) {
						isFileExists = true;
						logInfo(filepath + "export.xls" + " file exists.");
					}
					break;
				}
			}
		}

		/*
		 * if(isAttachPresent==false){ logInfo(fromRecepient +
		 * " is not present in Attachment folder."); }
		 */

		if (isFileExists == false) {
			logInfo(filepath + "icentris_image.jpg" + " file does not exists.");
		}
		return isFileExists;
	}


	// Email Signature methods
	
		public void go2EmailSignaturesPage() throws InterruptedException{
			logInfo("inside navigate2EmailSignaturesPage() page");
			driver().navigate().to(appUrl + "pyr_core/account#email_signatures");
			Thread.sleep(5000);
		}
		
		public void addSignature(String signatureName) throws Exception {
			logInfo("inside addSignature method ");
			
			waitOnElement("cssSelector", ".add-signature-link");
			clickOnElement("cssSelector", ".add-signature-link");
			Thread.sleep(5000);
			waitOnElement("cssSelector", "#pyr_crm_email_signature_name");
			inputTextClear("cssSelector", "#pyr_crm_email_signature_name");
			inputText("cssSelector", "#pyr_crm_email_signature_name", signatureName);
			composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, signature-content')]", "Thanks Icentris");
			clickOnElement("cssSelector", ".btn.btn-default.btn-primary.create-signature-submit");
			confirmationMessage("Signature successfully created");
			Thread.sleep(5000);
		}

		
		
		public boolean verifyEmailSignatureIsPresent(String signName) throws  Exception {
			logInfo("inside verifyEmailSignatureIsPresent Method");
		//	go2EmailSignaturesPage();
			boolean isSignPresent = false;
			WebElement x = driver().findElement(By.xpath("//*[@id='email_signatures_tab']"));
			List<WebElement> row = x.findElements(By.cssSelector("div.row"));
			System.out.println("Total Rows = " + row.size());
			String before_row = "//*[@id='email_signatures_tab']/div[";
			String after_row = "]";
			if(row.size() > 0){
				for(int i=2; i<=row.size(); i++ ){
					System.out.println("Row Num = " + i);
					WebElement e = driver().findElement(By.xpath(before_row+i+after_row));
					List<WebElement> allCols = e.findElements(By.cssSelector("div.col-md-6.signature-wrapper"));
					System.out.println("Total Cols = " + allCols.size());
					String before_col = "//*[@id='email_signatures_tab']/div[";
					String mid_col = "]/div[";
					String after_col = "]/div/span/h5/strong";
					if(allCols.size()>=1) {
						for(int j=1; j<=allCols.size();j++) {
							WebElement y = driver().findElement(By.xpath(before_col+i+mid_col+j+after_col));
							String actSignature = y.getText().trim();
							System.out.println("------------>" + actSignature);
							if(actSignature.equalsIgnoreCase(signName)){
								logInfo(signName + " signature match found.");
								isSignPresent = true;
								break;
							}
						}
					}
				}
			}
			
			if(isSignPresent==false){
				logInfo(signName + " signature not found.");
				// Assert.assertTrue(isSignPresent, signName + " signature not found.");
			}
			
			return isSignPresent;
		}
		
		
		public void selectSignatureAction(String signName, String signatureAction) throws  Exception{
			System.out.println("inside selectSignatureAction method()"); //logInfo
			
			WebElement x = driver().findElement(By.xpath("//*[@id='email_signatures_tab']"));
			List<WebElement> row = x.findElements(By.cssSelector("div.row"));
			System.out.println("Total Rows = " + row.size());
			String before_row = "//*[@id='email_signatures_tab']/div[";
			String after_row = "]";
			if(row.size() > 0){
				for(int i=2; i<=row.size(); i++ ){
					System.out.println("Row Num = " + i);
					WebElement e = driver().findElement(By.xpath(before_row+i+after_row));
					List<WebElement> allCols = e.findElements(By.cssSelector("div.col-md-6.signature-wrapper"));
					System.out.println("Total Cols = " + allCols.size());
					String before_col = "//*[@id='email_signatures_tab']/div[";
					String mid_col = "]/div[";
					String after_col = "]/div/span/h5/strong";
					String after_dropDown = "]/div/div[1]/button";
					String after_markasDefault = "]/div/div[1]/ul/li[1]/a";  
					String after_editSignature = "]/div/div[1]/ul/li[2]/a";
					String after_deleteSignature = "]/div/div[1]/ul/li[3]/a";
					if(allCols.size()>=1) {
						for(int j=1; j<=allCols.size();j++) {
							WebElement y = driver().findElement(By.xpath(before_col+i+mid_col+j+after_col));
							String actSignature = y.getText().trim();
							System.out.println("------------>" + actSignature);
							if(actSignature.equalsIgnoreCase(signName)){
								WebElement drpdown = driver().findElement(By.xpath(before_col+i+mid_col+j+after_dropDown));
								drpdown.click();
							
								switch(signatureAction){
									case "Set as Default":
										logInfo("signature marked as default");
										WebElement setDefault = driver().findElement(By.xpath(before_col+i+mid_col+j+after_markasDefault));
										setDefault.click();
										confirmationMessage("Signature selected as default");
										break;
									case "Edit":
										logInfo("signature being edited");
										WebElement edit = driver().findElement(By.xpath(before_col+i+mid_col+j+after_editSignature));
										edit.click();
										confirmationMessage("Signature successfully updated");
										Thread.sleep(5000);
										break;
									case "Delete":
										logInfo("signature being deleted");
										WebElement delete = driver().findElement(By.xpath(before_col+i+mid_col+j+after_deleteSignature));
										delete.click();
										Thread.sleep(3000);
										confirmationMessage("Signature successfully deleted");
										break;
									}
									
								 }
							}
					}
				}
			}
			
		}

		public boolean verifySignatureIsMarkedDefault(String signName) throws Exception {
			logInfo("inside verifySignatureIsMarkedDefault method ");
			System.out.println("inside verifySignatureIsMarkedDefault method ");
			boolean isSignDefault = false;
			WebElement x = driver().findElement(By.xpath("//*[@id='email_signatures_tab']"));
			List<WebElement> row = x.findElements(By.cssSelector("div.row"));
			System.out.println("Total Rows = " + row.size());
			String before_row = "//*[@id='email_signatures_tab']/div[";
			String after_row = "]";
			if(row.size() > 0){
				for(int i=2; i<=row.size(); i++ ){
					System.out.println("Row Num = " + i);
					WebElement e = driver().findElement(By.xpath(before_row+i+after_row));
					List<WebElement> allCols = e.findElements(By.cssSelector("div.col-md-6.signature-wrapper"));
					System.out.println("Total Cols = " + allCols.size());
					String before_col = "//*[@id='email_signatures_tab']/div[";
					String mid_col = "]/div[";
					String after_col = "]/div/span/h5/strong";
					String after_markedAsDefault = "]/div/span/h5/span";
					if(allCols.size()>=1) {
						for(int j=1; j<=allCols.size();j++) {
							WebElement y = driver().findElement(By.xpath(before_col+i+mid_col+j+after_col));
							String actSignature = y.getText().trim();
							System.out.println("------------>" + actSignature);
							if(actSignature.equalsIgnoreCase(signName)){
								WebElement marked = driver().findElement(By.xpath(before_col+i+mid_col+j+after_markedAsDefault));
								if (marked.isDisplayed()) {
									isSignDefault = true;
									logInfo(signatureName + "Signature  found in email signatures list and is marked default.");
									break;
								}
							}
						}
					}
				}
			}	
			
			if(isSignDefault==false){
				logInfo(signatureName + "Signature could not be marked as default.");
				Assert.assertTrue(isSignDefault, signatureName + "Signature could not be marked as default.");
			}
			
			return isSignDefault;
		}



		public void editAnEmailSignature(String signName, String signUpdateName) throws  Exception {
			logInfo("inside editAnEmailTemplate() Method");
			
			waitOnElement("cssSelector", "#pyr_crm_email_signature_name");
			inputTextClear("cssSelector", "#pyr_crm_email_signature_name");
			inputText("cssSelector", "#pyr_crm_email_signature_name", signUpdateName);
			clickOnElement("cssSelector", ".btn.btn-default.btn-primary.update-signature-submit");
			confirmationMessage("Signature successfully updated");
			Thread.sleep(3000);
		}



		public void deleteSingature(String signName) throws Exception {
			logInfo("inside deleteSingature Method ");
		
			waitOnElement("xpath","//*[@id='email_signatures_tab']");
			WebElement eSignaturePanel = driver().findElement(By.xpath("//*[@id='email_signatures_tab']/div[2]"));
			List<WebElement> totalSigns = eSignaturePanel.findElements(By.cssSelector(".col-md-6.signature-wrapper"));
			int signsSize = totalSigns.size();
			System.out.println(signsSize);
			
			if(signsSize>0){
				for (int i = 1; i <=signsSize+1; i++) {
					String beforePath = "//*[@id='email_signatures_tab']/div[2]/div[";
					String afterPath = "]/div/span/h5/strong";
					String dropDown = "]/div/div[1]/button";  // ]/div/div[1]
					String deleteSignature = "]/div/div[1]/ul/li[3]/a";
				
					WebElement y = driver().findElement(By.xpath(beforePath + i + afterPath));
					String title = y.getText().trim();
					System.out.println("title is " + title);
					if (title.equalsIgnoreCase(signName)) {
						logInfo(signName + "Signature  found in email signatures list");
						driver().findElement(By.xpath(beforePath + i + dropDown)).click();
						WebElement option = driver().findElement(By.xpath(beforePath + i + deleteSignature));
						String optionName = option.getText().trim();
						System.out.println("option is " + optionName);
						option.click();
						confirmationMessage("Signature successfully deleted");
						break;
					   }
				}
			}
		}

			
		
		public void composeVibeEmailWithSingature(String recipients,String subject, String signatureName) throws Exception {
			logInfo("inside composeVibeEmailWithSingature() method...");
			
			waitOnElement("linkText", "New Email");
			clickOnLink("linkText", "New Email");
			
			Thread.sleep(5000);
			waitOnElement("cssSelector",recipientsTo);
			WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); // inputVibeComposeTo
			inputText("cssSelector", recipientsTo, recipients);
			composeTo.click();
			Thread.sleep(5000);
			
			inputText("cssSelector", subject_Mail, subject);
			Thread.sleep(3000);
			waitOnElement("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]");
			composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]", "Mail with signature");
			Thread.sleep(3000);
			selectSignature(signatureName);
			scrollDown("linkText", "Send");
			clickOnLink("linkText", "Send");
			System.out.println("sent mail to gmail with subject " + subject);
			Thread.sleep(5000);
		}

		
		
		public void selectSignature(String signatureName) throws  Exception{
			logInfo("inside selectSignature() method");
			boolean isSignatureFound = false;
			
			waitOnElement("xpath","//*[@class='signature-dropdown dropdown']");
			clickOnElement("xpath","//*[@class='signature-dropdown dropdown']");
			
			WebElement signaturePanel = driver().findElement(By.xpath("//div[@class='signature-dropdown dropdown open']/ul"));
			List <WebElement> li = signaturePanel.findElements(By.tagName("li"));
			System.out.println("Total Li's = " +li.size());
			
			String before_sig = "//div[@class='signature-dropdown dropdown open']/ul/li[";
			String after_sig = "]/a";
			
			for(int i=li.size()-2; i>0;i--){
				WebElement eSign = driver().findElement(By.xpath(before_sig+i+after_sig));
				String actSignature = eSign.getText().trim();
				System.out.println("actSignature = " +actSignature);
				if(actSignature.equalsIgnoreCase(signatureName)){
					isSignatureFound = true;
					System.out.println(signatureName + " signature match found");
					eSign.click();
					Thread.sleep(5000);
					break;
				}
			}
			
			if(isSignatureFound==false){
				logInfo(signatureName + " signature not found in the compose filter");
				Assert.assertTrue(isSignatureFound, signatureName + " signature not found in the compose filter");
			}
		}
		
		
		
		public boolean selectMailsWithSignature(String signatureName) throws Exception {
			logInfo("inside selectMailsWithSignature() method..");
		
			waitOnElement("xpath", tblInboxBody);
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size();

			String before = "//*[@id='bulk-form']/table/tbody/tr[";
			String after = "]/td[5]/span[2]";

			String beforeChk = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterChk = "]/td[1]/input";
			boolean isMatchFound = false;

			for (int i = 1; i <= count - 1; i++) {
				WebElement x = driver().findElement(By.xpath(before + i + after));
				String act_sig = x.getText().trim();
			
				if (signatureName.equalsIgnoreCase(act_sig)) {
					isMatchFound = true;
					WebElement chk = driver().findElement(By.xpath(beforeChk + i + afterChk));
					chk.click();
					logInfo("email with subject \'" + signatureName + "\' match found @" + i);
					
				}
			}

			if (isMatchFound == false) {
				logInfo("No emails found with the specified subject : " + signatureName);
			
			}
			return isMatchFound;
		}
		
		
		
		
		// Email Template methods
		
			public void go2EmailTemplatespage() throws InterruptedException{
				logInfo("inside go2EmailTemplatespage method");
				driver().navigate().to(appUrl + "pyr_core/account#email_templates");
				
			}
			
			public void addEmailTemplate(String templName, String subject, String content) throws Exception {
				logInfo("inside addEmailTemplate method");
				
				waitOnElement("xpath","//*[@id='email_templates_tab']/div[1]/a");
				clickOnElement("xpath","//*[@id='email_templates_tab']/div[1]/a");
				Thread.sleep(3000);
				waitOnElement("cssSelector", tempName);
				inputTextClear("cssSelector", tempName);
				inputText("cssSelector", tempName, templName);
				inputTextClear("cssSelector", tempSubject);
				inputText("cssSelector", tempSubject, subject);
				composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, template-content')]", content);
				clickOnElement("cssSelector", createTempBtn);
				//confirmationMessage("Template successfully created");
				Thread.sleep(5000);
			}
			
			
			public boolean verifyEmailTemplateIsPresent(String templateName) throws Exception {
				logInfo("inside verifytemplatePresentInTemplatesPage method");
				boolean isTemplatePresent = false;
				WebElement x = driver().findElement(By.xpath("//*[@id='email_templates_tab']"));
				List<WebElement> row = x.findElements(By.cssSelector("div.row"));
				System.out.println("Total Rows = " + row.size());
				String before_row = "//*[@id='email_templates_tab']/div[";
				String after_row = "]";
				if(row.size() > 0){
					for(int i=2; i<=row.size(); i++ ){
						System.out.println("Row Num = " + i);
						WebElement e = driver().findElement(By.xpath(before_row+i+after_row));
						List<WebElement> allCols = e.findElements(By.cssSelector("div.col-md-6.template-wrapper"));
						System.out.println("Total Cols = " + allCols.size());
						String before_col = "//*[@id='email_templates_tab']/div[";
						String mid_col = "]/div[";
						String after_col = "]/div/span/h5/strong";
						if(allCols.size()>=1) {
							for(int j=1; j<=allCols.size();j++) {
								WebElement y = driver().findElement(By.xpath(before_col+i+mid_col+j+after_col));
								String actTemplate = y.getText().trim();
								System.out.println("------------>" + actTemplate);
								if(actTemplate.equalsIgnoreCase(templateName)){
									logInfo(templateName + " email template match found.");
									isTemplatePresent = true;
									break;
								}
							}
						}
					}
				}
				
				if(isTemplatePresent==false){
					logInfo(templateName + " email template not found.");
					// Assert.assertTrue(isTemplatePresent, templateName + " email template not found.");
				}
				
				return isTemplatePresent;
			}
			
			/*public void selectTemplateAction(String templateName, String templateAction) throws  Exception{
				System.out.println("inside selectTemplateAction method()"); 
				
				driver().navigate().refresh();
				waitOnElement("xpath","//*[@id='email_templates_tab']");
				WebElement eTemplatePanel = driver().findElement(By.xpath("//*[@id='email_templates_tab']"));
				List<WebElement> totalTemplates = eTemplatePanel.findElements(By.cssSelector(".col-md-6.template-wrapper"));
				int templateSize = totalTemplates.size();
				System.out.println(templateSize);
				
				if(templateSize>0){
					for (int i = 2; i <=templateSize+1; i++) {
					String beforePath = "//*[@id='email_templates_tab']/div[";
					String afterPath = "]/div/span/h5/strong";
					String dropDown = "]/div/div[1]";
					String editTemp = "]/div/div[1]/ul/li[1]/a";
					String deleteTemp = "]/div/div[1]/ul/li[2]/a";
					WebElement x = driver().findElement(By.xpath(beforePath + i + afterPath));
					String actTemplate = x.getText().trim();
					System.out.println("title is " + actTemplate);
					if (actTemplate.equalsIgnoreCase(templateName)) {
						logInfo(templateName + " template  found in email templates list");
						WebElement drpdown = eTemplatePanel.findElement(By.xpath(beforePath + i + dropDown));
						drpdown.click();
						
						switch(templateAction){
						case "Edit":
							logInfo("template being edited");
							WebElement edit = eTemplatePanel.findElement(By.xpath(beforePath+i+editTemp));
							edit.click();
							Thread.sleep(4000);
							break;
						case "Delete":
							logInfo("clicked Delete link");
							WebElement delete = eTemplatePanel.findElement(By.xpath(beforePath+i+deleteTemp));
							delete.click();
							Thread.sleep(4000);
							confirmationMessage("Template successfully deleted");
							break;
						}
						
					 }
				  }
				}
				
			}*/
			
		
			public void selectTemplateAction(String templateName, String templateAction) throws  Exception{
				System.out.println("inside selectTemplateAction method()"); 
				
				WebElement x = driver().findElement(By.xpath("//*[@id='email_templates_tab']"));
				List<WebElement> row = x.findElements(By.cssSelector("div.row"));
				System.out.println("Total Rows = " + row.size());
				String before_row = "//*[@id='email_templates_tab']/div[";
				String after_row = "]";
				if(row.size() > 0){
					for(int i=2; i<=row.size(); i++ ){
						System.out.println("Row Num = " + i);
						WebElement e = driver().findElement(By.xpath(before_row+i+after_row));
						List<WebElement> allCols = e.findElements(By.cssSelector("div.col-md-6.template-wrapper"));
						System.out.println("Total Cols = " + allCols.size());
						String before_col = "//*[@id='email_templates_tab']/div[";
						String mid_col = "]/div[";
						String after_col = "]/div/span/h5/strong";
						String after_dropDown = "]/div/div[1]/button";
						String after_editTemplate = "]/div/div[1]/ul/li[1]/a";
						String after_deleteTemplate = "]/div/div[1]/ul/li[2]/a";
						if(allCols.size()>=1) {
							for(int j=1; j<=allCols.size();j++) {
								WebElement y = driver().findElement(By.xpath(before_col+i+mid_col+j+after_col));
								String actTemplate = y.getText().trim();
								System.out.println("------------>" + actTemplate);
								if(actTemplate.equalsIgnoreCase(templateName)){
									WebElement drpdown = driver().findElement(By.xpath(before_col+i+mid_col+j+after_dropDown));
									drpdown.click();
				
						switch(templateAction){
						case "Edit":
							logInfo("template being edited");
							WebElement edit = driver().findElement(By.xpath(before_col+i+mid_col+j+after_editTemplate));
							edit.click();
							Thread.sleep(4000);
							break;
						case "Delete":
							logInfo("clicked Delete link");
							WebElement delete = driver().findElement(By.xpath(before_col+i+mid_col+j+after_deleteTemplate));
							delete.click();
							Thread.sleep(4000);
							confirmationMessage("Template successfully deleted");
							break;
						}
						
					}
				}
			}
		}
	}
			
}



			
		public void editEmailTemplate(String templateName, String tempUpdatedName) throws  Exception {
				logInfo("inside editEmailTemplate() Method");
				
				waitOnElement("cssSelector", "#pyr_crm_email_template_name");
				inputTextClear("cssSelector", "#pyr_crm_email_template_name");
				inputText("cssSelector", "#pyr_crm_email_template_name", tempUpdatedName);
				inputTextClear("cssSelector", "#pyr_crm_email_template_subject");
				inputText("cssSelector", "#pyr_crm_email_template_subject", templateName+" subject");
				composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, template-content')]", tempUpdatedName+" content");
				
				clickOnElement("cssSelector", "button.btn.btn-default.btn-primary.update-template-submit");
				confirmationMessage("Template updated successfully");
				Thread.sleep(5000);
			}
			
		
		public void composeVibeEmailWithTemplate(String recipients, String subject, String templateName) throws Exception {
			logInfo("inside composeVibeEmailWithTemplate() method.");
			
			waitOnElement("linkText", "New Email");
			clickOnLink("linkText", "New Email");
			
			Thread.sleep(5000);
			waitOnElement("cssSelector",recipientsTo);
			WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); // inputVibeComposeTo
			inputText("cssSelector", recipientsTo, recipients);
			composeTo.click();
			Thread.sleep(5000);
			
			inputText("cssSelector", subject_Mail, subject);
			waitOnElement("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]");
			Thread.sleep(5000);
			composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]", "Mail with Template");
			
			selectEmailTemplate(templateName);
			scrollDown("linkText", "Send");

			clickOnLink("linkText", "Send");
			Thread.sleep(5000);
		}

		
		public void selectEmailTemplate(String templateName) throws  Exception{
			logInfo("inside selectEmailTemplate() method");
			boolean isTemplateFound = false;
			
			waitOnElement("xpath","//*[@class='template-dropdown dropdown']");
			clickOnElement("xpath","//*[@class='template-dropdown dropdown']");
			
			WebElement templatePanel = driver().findElement(By.xpath("//*[@class='template-dropdown dropdown open']/ul"));
			List <WebElement> li = templatePanel.findElements(By.tagName("li"));
			System.out.println("Total Li's = " +li.size());
			
			String before_temp = "//div[@class='template-dropdown dropdown open']/ul/li[";
			String after_temp = "]/a";
			
			for(int i=li.size()-2; i>0;i--){
				WebElement eTemplate = driver().findElement(By.xpath(before_temp+i+after_temp));
				String actTemplate = eTemplate.getText().trim();
				System.out.println("actTemplate = " +actTemplate);
				if(actTemplate.equalsIgnoreCase(templateName)){
					isTemplateFound = true;
					System.out.println(templateName + " template match found");
					eTemplate.click();
					Thread.sleep(5000);
					break;
				}
			}
			
			if(isTemplateFound==false){
				logInfo(templateName + " template not found in the compose filter");
				Assert.assertTrue(isTemplateFound, templateName + " template not found in the compose filter");
			}
		}
		
		
		public void compose2CreateSaveAsTemplate(String recipients, String subject, String templateName) throws Exception {
			logInfo("inside compose2CreateSaveAsTemplate() method.");
			
			waitOnElement("linkText", "New Email");
			clickOnLink("linkText", "New Email");
			
			Thread.sleep(5000);
			waitOnElement("cssSelector",recipientsTo);
			WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); // inputVibeComposeTo
			inputText("cssSelector", recipientsTo, recipients);
			composeTo.click();
			Thread.sleep(5000);
			
			inputText("cssSelector", subject_Mail, subject);
			Thread.sleep(3000);
			waitOnElement("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]");
			composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]", "Mail with New Template");
			Thread.sleep(3000);
			
			// Save as Template dialog
			saveAsTemplate(templateName,templateName+" subject");		// create a new template
			
			// Verify selecting the template in compose email
			
			clickOnElement("cssSelector","span.template-dropdown-anchor > div > a");
			WebElement tempPanel = driver().findElement(By.cssSelector("span.template-dropdown-anchor > div > ul"));
		    List allTemplates = tempPanel.findElements(By.tagName("li"));
		    String before_li = "span.template-dropdown-anchor > div > ul > li:nth-child(";
		    String after_li = ") > a";
		    
			if(allTemplates.size()>1) {
				for(int i=1;i<=allTemplates.size()-2;i++) {
					WebElement e = driver().findElement(By.cssSelector(before_li+i+after_li));
					String act_template = e.getText().trim();
					if(act_template.equalsIgnoreCase(templateName)) {
						e.click();
						Thread.sleep(3000);
						break;
					}
				}
			}
			
			//stayOnPage();
			
			scrollDown("linkText", "Send");
			clickOnLink("linkText", "Send");
			
			leavePage();
			
			Thread.sleep(5000);
		}

		
		public void saveAsTemplate(String newTemplateName, String newTemplateSubject) throws  Exception{
			logInfo("inside saveAsTemplate() method.");
			
			waitOnElement("linkText","Save as Template");
			clickOnElement("linkText","Save as Template");
			
			waitOnElement("cssSelector", "#pyr_crm_email_template_name");
			inputTextClear("cssSelector", "#pyr_crm_email_template_name");
			inputText("cssSelector", "#pyr_crm_email_template_name", newTemplateName);
			inputTextClear("cssSelector", "#pyr_crm_email_template_subject");
			inputText("cssSelector", "#pyr_crm_email_template_subject", newTemplateSubject);
			composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, template-content')]", newTemplateName+" content");
			
			scrollDown("xpath", "//button[contains(text(),'Save')]");
			clickOnElement("xpath", "//button[contains(text(),'Save')]");
			//confirmationMessage("Template updated successfully");
			Thread.sleep(5000);
		}
	
}


	

