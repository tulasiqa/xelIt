package common;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import common.TestBase;


public class SocialNetWorksMethods extends TestBase {

	String By;

	public void selectFacebookIcon() throws Exception {
		logInfo("Entered into selectFacebookIcon() method");
		waitOnElement("cssSelector", facebookIcon);
		clickOnElement("cssSelector", facebookIcon);
	}

	public void selectTwitterIcon() throws  Exception {
		logInfo("Entered into selectTwitterIcon() method.");
		waitOnElement("cssSelector", twitterIcon);
		waitOnElement("cssSelector", twitterIcon);
		clickOnElement("cssSelector", twitterIcon);

	}
	
	public void selectShareEmailIcon() throws Exception {
		logInfo("Entered into selectShareEmailIcon() method");
		waitOnElement("cssSelector", shareByMail);
		clickOnElement("cssSelector", shareByMail);
	}
	
	public void selectShareEmailIconInPage() throws Exception {
		logInfo("Entered into selectShareEmailIconInPage() method");
		waitOnElement("cssSelector", shareByMailWS);
		clickOnElement("cssSelector", shareByMailWS);
	}


	public void selectLinkedInIcon() throws Exception {
		logInfo("Entered into selectLinkedInIcon() method");
		waitOnElement("cssSelector", twitterIcon);
		waitOnElement("cssSelector", linkedIn);
		Thread.sleep(3000);
		clickOnElement("cssSelector", linkedIn);
		Thread.sleep(4000);		
		}
	
	public void selectPwpIcon() throws Exception {
		logInfo("Entered into selectPwpIcon() method");
		waitOnElement("cssSelector", webIconInEcard);
		clickOnElement("cssSelector", webIconInEcard);
	}

	public void go2FacebookPage() {
		logInfo("inside go2FacebookPage() method");
		driver().navigate().to("https://www.facebook.com/");
	}
	
	public void go2TwitterPage() {
		logInfo("inside go2TwitterPage() method");
		driver().navigate().to("https://twitter.com/login");
	}

	public void login2FaceBook(String userName, String passWord) throws Exception {
		logInfo("inside login2FaceBook() Method");
		waitOnElement("cssSelector", fbEmail);
		inputTextClear("cssSelector", fbEmail);
		inputText("cssSelector", fbEmail, fBuserName_Text);
		inputTextClear("cssSelector", fbPassword);
		inputText("cssSelector", fbPassword, fBPwd_text);
		clickOnElement("xpath", fbLogin);
	}

	public void login2FBVerifyPostedDetails(String email, String passwd) throws Exception {
		logInfo("Login into FaceBook");
		Thread.sleep(3000);
		driver().manage().deleteAllCookies();
		Thread.sleep(5000);
		driver().navigate().to("https://www.facebook.com/");
		waitOnElement("cssSelector", fbEmail);
		inputText("cssSelector", fbEmail, email);
		waitOnElement("cssSelector", fbPwd);
		inputText("cssSelector", fbPwd, passwd);
		Thread.sleep(3000);
		submitElement("cssSelector", fbPwd);
		Thread.sleep(3000);
		waitOnElement("cssSelector", spinner);
		waitOnElement("xpath", fbHome);
		clickOnElement("xpath", fbHome);

	}

	public boolean getPostsFromFB(String photoPost) throws Exception {
		logInfo("Verify the Vibe shared post in FaceBook which will retrive from second part");
		boolean isSharedPostPresent = false;
		waitOnElement("xpath","//*[contains(@id,'substream_')]");
		List<WebElement> post = driver().findElements(org.openqa.selenium.By.xpath("//*[contains(@id,'substream_')]"));
		System.out.println("FB size is " + post.size());
		for (WebElement pp : post) {
			if (pp.getText().contains(photoPost)) {
				System.out.println("after matching  is " + pp.getText());
				isSharedPostPresent = true;
				logoutFB();
				break;
			}
		}
		if (isSharedPostPresent == false) {
			System.err.println(photoPost + " is not avaiable in FB");
			logoutFB();
			Assert.assertTrue(isSharedPostPresent, photoPost + " is not avaiable in FB");

		}
		return isSharedPostPresent;

	}
	
	public boolean getPostsOfFBFromTopPart(String photoPost) throws Exception {
		logInfo("Entered into getPostsFromFBOfFirstPart() method");
		boolean isSharedPostPresent = false;
		waitOnElement("cssSelector", fbPost);
		List<WebElement> post = driver().findElements(org.openqa.selenium.By.cssSelector(fbPost));
		System.out.println("FB size is " + post.size());
		for (WebElement pp : post) {
			System.out.println("titles "+ pp.getText());
			if (pp.getText().equalsIgnoreCase(photoPost)) {
				System.out.println("after matching  is " + pp.getText());
				isSharedPostPresent = true;
				break;
			}
		}
		/*if (isSharedPostPresent == false) {
			System.err.println(photoPost + " is not avaiable in FB");
			Assert.assertTrue(isSharedPostPresent, photoPost + " is not avaiable in FB");
		}*/	
		
		return isSharedPostPresent;
	}
	
	
	public void logoutFB() throws Exception, Exception {
		logInfo("Entered into logoutFB() method");
		waitOnElement("cssSelector", fbNavgiation);
		clickOnElement("cssSelector", fbNavgiation);
		Thread.sleep(3000);
		waitOnElement("xpath", fbSettings);
		waitOnElement("xpath", fbLogout);
		clickOnElement("xpath", fbLogout);
	}

	public void getPostsOfFBFrom2ndPart(String photoPost) throws Exception {
		logInfo("Entered into getPostsOfFBFrom2ndPart() method");
		boolean isSharedPostPresent = false;
		waitOnElement("cssSelector", fbPost2ndPart);
		List<WebElement> post = driver().findElements(org.openqa.selenium.By.cssSelector(fbPost2ndPart));
		System.out.println("FB size is " + post.size());
		for (WebElement pp : post) {
			System.out.println(pp.getText());
			if (pp.getText().contains(photoPost)) {
				System.out.println("after matching in FB   is " + pp.getText());
				isSharedPostPresent = true;
				break;
			}
		}
		if (isSharedPostPresent == false) {			
			Assert.assertTrue(isSharedPostPresent, photoPost + " is not avaiable in FB");

		}
		waitOnElement("cssSelector", fbNavgiation);
		clickOnElement("cssSelector", fbNavgiation);
		Thread.sleep(3000);
		waitOnElement("xpath", fbSettings);
		waitOnElement("xpath", fbLogout);
		clickOnElement("xpath", fbLogout);

	}
	
	
	public boolean verifyPostsInTwitter(String twitterPost) throws Exception {
		boolean isSharedPostPresent = false;		
			logInfo("Verify the Vibe shared post in Twitter account");
			waitOnElement("cssSelector", twtPosts);
			Thread.sleep(5000);
			List<WebElement> post = driver().findElements(org.openqa.selenium.By.cssSelector(twtPosts));
			System.out.println("size is " + post.size());
			for (WebElement pp : post) {
				System.out.println("WWWWW " + pp.getText().trim());
				if (pp.getText().contains(twitterPost)) {
					System.out.println("after matching  is " + pp.getText());
					isSharedPostPresent = true;
					logOutTwitter();
					break;
					}
				}	
			
		return isSharedPostPresent;
		
	}
	
	public void logOutTwitter() throws Exception {
		logInfo("Entered into logOutTwitter() method");
		clickOnElement("cssSelector", twtlog);
		waitOnElement("cssSelector", twtLogout);	
		clickOnElement("cssSelector", twtLogout);	
	}	

	 public void shareInFaceBook(String postMessage) throws Exception{
			logInfo("Enter login credentials in FaceBook to share the posts.");
			String wndBeforeWindow = driver().getWindowHandle();	
			for(String w : driver().getWindowHandles()){
				if(!w.equalsIgnoreCase(wndBeforeWindow)){
					driver().switchTo().window(w);
					Thread.sleep(4000);
					boolean isLoginFound = verifyElementPresent("cssSelector",fbEmail);
					if(isLoginFound) {
					waitOnElement("cssSelector", fbEmail);
					Thread.sleep(4000);				
					inputTextClear("cssSelector", fbEmail);
					inputText("cssSelector", fbEmail, fBuserName_Text);
					waitOnElement("cssSelector", fbPassword);
					inputTextClear("cssSelector", fbPassword);
					inputText("cssSelector", fbPassword, fBPwd_text);
					waitOnElement("cssSelector", fbPassword);
					submitElement("cssSelector", fbPassword);
					Thread.sleep(10000);
					waitOnElement("cssSelector", fbSaySmthg);
					waitOnElement("cssSelector", fbSaySmthg);					
					WebElement nam = driver().findElement(org.openqa.selenium.By.cssSelector(fbSaySmthg));            
			        nam.clear();			        
			        nam.sendKeys(postMessage);
			        nam.clear();			        
			        nam.sendKeys(postMessage);
			        waitOnElement("xpath", postFb);
			        clickOnElement("xpath",postFb);	
					driver().switchTo().window(wndBeforeWindow);
					Thread.sleep(5000);
					}
					
					else {

						driver().close();
						driver().switchTo().window(wndBeforeWindow);
						Assert.assertEquals("Unable to see login to facebook as the window not integrated or showing error","facebook");

					}
					break;	
						
				}
			}		

		} 

	
	public void shareInLinkedIn() throws Exception {
		logInfo("Entered into shareInLinkedIn method.");
		String wndBeforeWindow = driver().getWindowHandle();
		for (String w : driver().getWindowHandles()) {
			if (!w.equalsIgnoreCase(wndBeforeWindow)) {
				driver().switchTo().window(w);
				waitOnElement("cssSelector", singInLinked);
				scrollDown("cssSelector", singInLinked);				
				waitOnElement("cssSelector", singInLinked);
				System.out.println("cliked singin");
				clickOnLink("cssSelector", singInLinked);				
				clickOnLink("cssSelector", singInLinked);
				waitOnElement("cssSelector", linkedInEmail);
				inputText("cssSelector", linkedInEmail, fBEmail_text);
				inputText("cssSelector", linkedInPwd, fBPwd_text);
				clickOnButton("cssSelector", linkedInLogin);
				Thread.sleep(10000);
				waitOnElement("cssSelector",spinner);
				waitOnElement("cssSelector", sharChk);
				waitOnElement("cssSelector", sharChk);
				WebElement shareChk = driver().findElement(org.openqa.selenium.By.cssSelector(sharChk));
				String shareChkPost = shareChk.getText().trim();				
				if (shareChkPost.equalsIgnoreCase(sharChkText)){
					waitOnElement("cssSelector", linkedShareaUpdate);
					waitOnElement("cssSelector", linkedShare);
					inputText("cssSelector", linkedShareaUpdate, "Hey Its a Good Product");
					clickOnButton("cssSelector", linkedShare);	
					Thread.sleep(2000);					
					WebElement info = driver().findElement(org.openqa.selenium.By.cssSelector(linkedInfo));
					String infomation = info.getText().trim();							
					if (infomation.equalsIgnoreCase(linkedInfoText)){
						Thread.sleep(2000);
						waitOnElement("linkText", "Close this window");					
						clickOnLink("linkText", "Close this window");						
						break;					
					}else{
						Assert.assertEquals(infomation, linkedInfoText);
						}					
				}else{
				Assert.assertEquals(shareChkPost, sharChkText);
				}							
				Thread.sleep(2000);	
				driver().switchTo().window(wndBeforeWindow);
				Thread.sleep(4000);	
				break;
			} else {
				System.out.println("ot focused");
			}break;
		} 

	}



	public void shareInTwitter() throws Exception{		
		   logInfo("Entered into shareInTwitter() method.");
				String wndBeforeWindow = driver().getWindowHandle();	
				for(String w : driver().getWindowHandles()){
					if(!w.equalsIgnoreCase(wndBeforeWindow)){
						driver().switchTo().window(w);
						Thread.sleep(5000);
						boolean isLoginFound = verifyElementPresent("cssSelector",twitLogin);
						if(isLoginFound) {
						waitOnElement("cssSelector", twitLogin);
						inputTextClear("cssSelector", twitLogin);
						inputText("cssSelector", twitLogin, fBEmail_text);
						inputTextClear("cssSelector", twitPswD);
						inputText("cssSelector", twitPswD, fBPwd_text);	
						submitElement("cssSelector", twitPswD);
						Thread.sleep(5000);
						waitOnElement("cssSelector", twtTweet);						
						clickOnElement("cssSelector", twtTweet);	
						Thread.sleep(3000);
						driver().switchTo().window(wndBeforeWindow);	
						}
						else {
							driver().close();
							driver().switchTo().window(wndBeforeWindow);
							Assert.assertEquals("Unable to see login to Twitter as the window not integrated or showing error","Twitter");
						}
						break;
					}
				}  
			} 

	public void selectGooglePlusIcon() throws  InterruptedException {
		logInfo("Select GooglePlus Icon for sharing  to share the posts.");
		Thread.sleep(4000);
		clickOnElement("cssSelector", googlePlus);
	}

	

	public void login2LinkedIn() throws Exception {

		logInfo("Login into LinkedIn account ");
		driver().manage().deleteAllCookies();
		Thread.sleep(5000);
		driver().navigate().to("https://www.linkedin.com/");
		waitOnElement("cssSelector", linkedEmail);
		inputText("cssSelector", linkedEmail, fBEmail_text);
		Thread.sleep(5000);
		inputText("cssSelector", linkedPwd, fBPwd_text);
		Thread.sleep(3000);
		submitElement("cssSelector", linkedPwd);
	}

	// LinkedIn- Select your updates and verify the posted photos from community
	// wall. And then logout
	public void verifyPostInLinkedAccount(String postTitle) throws Exception {
		logInfo("Entered into verifyPostInLinkedAccount(String postTitle) method");
		WebElement prof = driver().findElement(org.openqa.selenium.By.cssSelector(lipro));
		Actions action = new Actions(driver());
		action.moveToElement(prof).build().perform();
		clickOnLink("linkText", "Your Updates");
		System.out.println(driver().getTitle());
		boolean isPostPresent = false;
		/* if (driver().getTitle().equals(liTitle)){ */
		List<WebElement> li = driver().findElements(org.openqa.selenium.By.cssSelector(liList));
		System.out.println("get size " + li.size());
		for (WebElement ls : li) {
			if (ls.getText().contains(postTitle)) {
				isPostPresent = true;
				break;
			}

		}
		if (isPostPresent == false) {

			System.err.println(postTitle + " is not present in LinkedIn");
			Assert.assertTrue(isPostPresent, postTitle + " is not present in LinkedIn");

		}
		Thread.sleep(5000);
		WebElement acc = driver().findElement(org.openqa.selenium.By.cssSelector(liAcc));
		action.moveToElement(acc).build().perform();
		clickOnLink("linkText", "Sign Out");
		driver().manage().deleteAllCookies();
		logIn(prop.getLocatorForEnvironment(appUrl, "adminUser_text"),
				prop.getLocatorForEnvironment(appUrl, "adminPwd_text"));

		/*
		 * }else{ driver().manage().deleteAllCookies();
		 * Assert.assertEquals(driver().getTitle(), liTitle);
		 * System.err.println("Not able to opened 'Your Updates' screen."); }
		 */
	}
	

	public void login2Twitter() throws Exception {
		logInfo("Entered into login2Twitter() method ");
		driver().manage().deleteAllCookies();
		Thread.sleep(5000);
		driver().navigate().to("https://twitter.com/login");
		Thread.sleep(6000);
		String title = "Log in to Twitter";
		String title2 = "Login on Twitter";
		System.out.println(driver().getTitle());
		if (driver().getTitle().contains(title)) {	
			System.out.println("Entered here 1");
			Thread.sleep(2000);
			inputText("cssSelector", twtUser, fBEmail_text);
			Thread.sleep(2000);
			WebElement remmber = driver().findElement(org.openqa.selenium.By.cssSelector(remember));
			if (remmber.isSelected()) {
				remmber.click();
				}
			waitOnElement("cssSelector", twtPwd);
			inputText("cssSelector", twtPwd, fBPwd_text);
			Thread.sleep(3000);
			submitElement("cssSelector", twtPwd);

		} else if(driver().getTitle().contains(title2)) {
			Thread.sleep(2000);
			System.out.println("Entered here 2");
			inputTextClear("cssSelector", twtUser2);
			inputTextClear("cssSelector", twtPwd2);
			inputText("cssSelector", twtUser2, fBEmail_text);
			Thread.sleep(2000);
			inputText("cssSelector", twtPwd2, fBPwd_text);
			submitElement("cssSelector", twtTweet2);
			
		}
		else {
			System.out.println("Failed!! Something happened.");

		}

	}

	
	public void closeSharePopUp() throws Exception {
		logInfo("Entered into closeSharePopUp() method");
		waitOnElement("cssSelector", closeSharePopUp);
		clickOnElement("cssSelector", closeSharePopUp);
		

	}
	
	

	public void selectPWPInIcon() throws  Exception {
		logInfo("Select LinkedIn Icon for sharing");
		Thread.sleep(2000);
		clickOnElement("cssSelector", pwp);
	}
	
	 public void shareEventByEmail(  String mailID) throws Exception{
	    logInfo("Inside shareEventByEmail Method");	
	    waitOnElement("cssSelector",recipientsTo);
	  	WebElement composeTo = driver().findElement(org.openqa.selenium.By.cssSelector(recipientsTo)); // inputVibeComposeTo
		inputText("cssSelector", recipientsTo, mailID);
		composeTo.click();
		composeText("xpath", "//iframe[contains(@title,'Rich Text Editor, email_textarea')]", inboxMsgBodyText);
		Thread.sleep(10000);
		scrollDown("linkText", "Send");
		clickOnLink("linkText", "Send");
		System.out.println("sent mail to gmail with subject ");
		confirmationMessage("Message sent to: "+mailID);
				
	}	
}
