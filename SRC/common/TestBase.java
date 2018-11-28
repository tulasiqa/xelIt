package common;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

public class TestBase extends DriverInitializer{
protected static final Log logger = LogFactory.getLog(TestBase.class);

	WebElement element, toElement;
	WebDriverWait wait;
	String By;
	ExtentTest test;
	JavascriptExecutor js;		
	
	protected int num = TestBase.generateRandomNumberInRange(1000, 9999);
	
	// This method is used to get the system date and time	
		public static String getSystemDate(){
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date  = new Date();
			return dateFormat.format(date);
		}
	
		
	// This method is used to Maximize the browser window.	
	public void maximizeBrowser() throws Exception{
		try{	
			driver().manage().window().maximize();
		}
		catch(Exception e){
			logInfo("Failed!! unable to maximize the browser window");		
		}
	}
	
	// This method is used to configure the log4j.xml file and can send the logging information to that file.
			
	public void logInfo(Object message) {
		try{	
			Logger Log = Logger.getLogger(Logger.class.getName());
			DOMConfigurator.configure("log4j.xml");
		    Log.info(message);
		}
		catch(Exception e){
			logInfo("Failed!! unable to log the information.");		
		}
		
 	} 		

	// This method is used to perform click operation on a specific element.
			
	public void clickOnElement(String Bytype, String locator) {	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			case "linkText":
				element = driver().findElement(ByLinkText.linkText(locator));
				break;
			default :
				System.out.println("Invalid type passed to clickOnElement."+locator);
				break;
			}
			if(element.isDisplayed() && element.isEnabled()){
				element.click();
			}
			   
		}
		catch(Exception e){
			logInfo("Failed!! Unable to click on an element."+locator);		
		}
		
	}
	
	// This method is used to submit an element.
	
	public void submitElement(String Bytype, String locator) {	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			case "linkText":
				element = driver().findElement(ByLinkText.linkText(locator));
				break;
			default :
				System.out.println("Invalid type passed to submitElement."+locator);
				break;
			}
			if(element.isDisplayed() && element.isEnabled()){
				element.submit();
			}
			   
		}
		catch(Exception e){
			logInfo("Failed!! Unable to submit the element."+locator);		
		}
		
	}
			
	// This method is used to perform click operation on a specified link.
		
	public void clickOnLink(String Bytype, String locator) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "linkText":
				element = driver().findElement(ByLinkText.linkText(locator));
				break;
			case "partialLinkText":
				element = driver().findElement(ByPartialLinkText.partialLinkText(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;	
			default :
				System.out.println("Invalid type passed to clickOnLink "+locator);
				break;
			}
			element.click();
		} 
		catch(Exception e) {
			logInfo("Failed!! unable to click on a Link."+locator);			
		}
	}


	// This method is used to verify the specified text and it returns a boolean value.
	public boolean verifyTextPresent(String someText){
	boolean isTextPresent = false;
	try{
		isTextPresent = driver().getPageSource().contains(someText);
		System.out.println("isTextPresent = " +isTextPresent);
		}
		catch(Exception e){
			logger.error("Failed!! Unable to locate the text."+someText); 
		}
		return isTextPresent;
	}

		
	// This method is used to verify the title of the page and comparing the Actual Text with the Expected Text.
	
	public void verifyTitle(String actual,String expected) throws Exception{
		try{
			actual = driver().getTitle();
			
			if (actual.equalsIgnoreCase(expected)){
				System.out.println("correct page title : " + "\"" + actual + "\"");
			}
			else{
				System.out.println("incorrect page title : " + "\"" + actual + "\"" + ". \n\t Title should be " + "\"" + expected + "\"" );
			}
		}
		catch(Exception e){
			logInfo("Failed!! miss matches with "+actual + "and "+ expected );
		}
	}

	// This method is used to verify if the specified element is present on the DOM page.
	
	public boolean verifyElementPresent(String Bytype,String locator) throws InterruptedException{
		boolean isElementPresent = false;
		try{
			switch(Bytype){
			case "xpath":
				element = (new WebDriverWait(driver(), 20)).until(ExpectedConditions.visibilityOfElementLocated(ByXPath.xpath(locator)));
				break;
			case "id":
				element = (new WebDriverWait(driver(), 20)).until(ExpectedConditions.visibilityOfElementLocated(ById.id(locator)));
				break;
			case "name":
				element = (new WebDriverWait(driver(), 20)).until(ExpectedConditions.visibilityOfElementLocated(ByName.name(locator)));
				break;
			case "className":
				element = (new WebDriverWait(driver(), 20)).until(ExpectedConditions.visibilityOfElementLocated(ByClassName.className(locator)));
				break;
			case "cssSelector":
				element = (new WebDriverWait(driver(), 20)).until(ExpectedConditions.visibilityOfElementLocated(ByCssSelector.cssSelector(locator)));
				break;
			case "linkText":
				element = (new WebDriverWait(driver(), 20)).until(ExpectedConditions.visibilityOfElementLocated(ByLinkText.linkText(locator)));
				break;
			default :
				System.out.println("Invalid type passed to verifyElementPresent."+locator);
				break;
			}
			
			if(element.isDisplayed())
				isElementPresent = true;

		}catch(Exception e){
	    	e.printStackTrace();
	    	System.out.println("specified element is not present"+locator);
	    }
		return isElementPresent;
	}
	
	// This method is used to verify if the specified link text is present on the page.
	
	public boolean verifyLinkPresent(String expValue) throws Exception{	
		 boolean flag = false;
		try{
		   	List<WebElement> links = driver().findElements(ByTagName.tagName("a"));
	    	for(WebElement x : links){
	    		String actValue = x.getText();
	    		if(actValue.contains(expValue)){
	    			flag = true;
	    			break;
	    		} 
	    	}
	    	 if(flag = false){
	    		 logInfo("The link "+ expValue + " : could not be found.");
	    	 }
	    	
		}catch(Exception e) {
			logInfo("Failed!! Unable to find a link " + expValue);			
		}
		 return flag;
	}
		
   	
	// This method is used to perform click operation on the specified button.
	
	public void clickOnButton(String Bytype, String locator) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to clickOnButton."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				element.click();
			}
		}
		catch(Exception e){
			logInfo("Failed!! Unable to click on a button."+locator);		
		}
		
	}

	// This method is used to double click on a specified element.
	
	public void doubleClick(String Bytype, String locator ) throws Exception{
		try{
			Actions action = new Actions(driver());
			
			switch(Bytype){
			case "xpath":
				action.moveToElement(driver().findElement(ByXPath.xpath(locator))).doubleClick().build().perform();
				break;
			case "id":
				action.moveToElement(driver().findElement(ById.id(locator))).doubleClick().build().perform();
				break;
			case "name":
				action.moveToElement(driver().findElement(ByName.name(locator))).doubleClick().build().perform();
				break;
			case "className":
				action.moveToElement(driver().findElement(ByClassName.className(locator))).doubleClick().build().perform();
				break;
			case "cssSelector":
				action.moveToElement(driver().findElement(ByCssSelector.cssSelector(locator))).doubleClick().build().perform();
				break;
			case "linkText":
				action.moveToElement(driver().findElement(ByLinkText.linkText(locator))).doubleClick().build().perform();
				break;
			case "tagName":
				action.moveToElement(driver().findElement(ByTagName.tagName(locator))).doubleClick().build().perform();
				break;
			case "partialLinkText":
				action.moveToElement(driver().findElement(ByPartialLinkText.partialLinkText(locator))).doubleClick().build().perform();
				break;
			default :
				System.out.println("Invalid type passed to clickOnButton."+locator);
				break;
			}
			
		}
		catch(Exception e){
			logInfo("Failed!! To double click on " + locator);		
		}
			
	}
	
	// This method is used to click on the text area.
	
	public void clickOnTextArea(String Bytype, String locator, String EnterTextArea) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			case "tagName":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to clickOnTextArea."+locator);
				break;
			}
			if(element.isDisplayed()){
				element.click();
				element.clear();
				element.sendKeys(EnterTextArea);
			}
			
		}
		catch(Exception e){
			logInfo("Failed!! Unable to click and enter text in the text area element."+locator);		
		}
		
	}
	
	// This method is used to enter the values into a text box
	
	public void inputText(String Bytype, String locator, String value) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to inputText."+locator);
				break;
			}
			
			boolean ment = driver().findElement(ByCssSelector.cssSelector(locator)).isSelected();
			boolean status = element.isDisplayed();
			String str= String.valueOf(status);
			
			if(element.isDisplayed() && element.isEnabled()){
				element.sendKeys(value);
			}
		}
		catch(Exception e){
			logInfo("Failed!! Unable to enter a text into a textbox."+locator);		
		}
	}
		
	// This method is used to clear text box values.
	
	public void inputTextClear(String Bytype, String locator) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to inputTextClear."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				element.clear();
			}
		}
		catch(Exception e){
			logInfo("Failed!! Unable to clear the text from a textbox."+locator);		
		}
	}

	// This method is used to select options from a drop down list. 
	
	public void selectFromDropdown(String Bytype, String locator, String selectType, String value) throws Exception{
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "tagName":
				element = driver().findElement(ByTagName.tagName(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to selectFromDropdown."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				Select select = new Select(element);
				if(selectType == "byVisibleText"){
					select.selectByVisibleText(value);
				} else if (selectType == "byValue"){
					select.selectByValue(value);
				} else if(selectType == "byIndex"){
					Integer ivalue = Integer.valueOf(value);
					select.selectByIndex(ivalue);
					
				}
			  }
		}
		catch(Exception e){
			logInfo("Failed!! Required option is not available or not select drop down list"+locator);		
		}
	}
	

	public void selectFromDropdown(String Bytype, String locator, String selectType, int value) throws Exception{
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "tagName":
				element = driver().findElement(ByTagName.tagName(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to selectFromDropdown."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				Select select = new Select(element);
				if(selectType == "byIndex"){
					select.selectByIndex(value);
			  }
		   }
		}
		catch(Exception e){
			logInfo("Failed!! Required option is not available or not select drop down list"+locator);		
		}
	}
	
	
	//This method is used to get the current selected item from drop down
	
	public String getCurrentSelectionFromDropdown(String Bytype, String locator) throws Exception{
		String value = null;
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "tagName":
				element = driver().findElement(ByTagName.tagName(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to selectFromDropdown."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				Select select = new Select(element);
				value = select.getFirstSelectedOption().getText().trim();
			}
		}
		catch(Exception e){
		logInfo("Failed!! Required option is not available or not select drop down list"+locator);
		}
		return value;
	}
			
	// This method is used to select either a radio buttons or check boxes.
	
	public void deselectRadioOrCheckbox(String Bytype, String locator) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to selectRadioOrCheckbox."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				if(element.isSelected()){
				element.click();
		}}}
		catch(Exception e){
			logInfo("Failed!! Unable to select the specified option" + locator);		
		}
	}	
	
	// This method is used to select either a radio buttons or check boxes  and wont click if it is already selected.
	
		public void selectRadioOrCheckbox(String Bytype, String locator) throws Exception{	
			try{
				switch(Bytype){
				case "xpath":
					element = driver().findElement(ByXPath.xpath(locator));
					break;
				case "id":
					element = driver().findElement(ById.id(locator));
					break;
				case "name":
					element = driver().findElement(ByName.name(locator));
					break;
				case "className":
					element = driver().findElement(ByClassName.className(locator));
					break;
				case "cssSelector":
					element = driver().findElement(ByCssSelector.cssSelector(locator));
					break;
				default :
					System.out.println("Invalid type passed to selectRadioOrCheckbox."+locator);
					break;
				}
				
				if(element.isDisplayed() && element.isEnabled()){
					if(!element.isSelected()){
					element.click();
			}}}
			catch(Exception e){
				logInfo("Failed!! Unable to select the specified option" + locator);		
			}
		}
		
	// This method is used to generate a random number.	
	public static int random(){
        Random r = new Random();
        int random_num = r.nextInt(1000);
        return random_num;
	}	
	
    // This method is used to generate random number in s specified range.    
    public static int generateRandomNumberInRange(int min,int max){
		logger.info("Generating random number in range - "+min+","+max);
		Random r = new Random();
		int num=r.nextInt(max-min) + min;
		logger.info("Generating random number = "+num);
		return num;	
		}

		

    	// This method is used to Wait for a element to present.	
			public void waitOnElement(String Bytype,String locator) throws Exception{
				try{
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver())
						 .withTimeout(120, TimeUnit.SECONDS)
			    		 .pollingEvery(2, TimeUnit.SECONDS);			   			   		 
					switch(Bytype){
					case "xpath":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByXPath.xpath(locator)));
						break;
					case "id":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ById.id(locator)));
						break;
					case "className":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByClassName.className(locator)));
						break;
					case "cssSelector":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByCssSelector.cssSelector(locator)));
						break;
					case "linkText":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByLinkText.linkText(locator)));
						break;
					case "partialLinkText":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByPartialLinkText.partialLinkText(locator)));
						break;
					default :
						System.out.println("Invalid type passed to waitOnElement.");
						break;
					}					
					if(element.isDisplayed()){
		            	logInfo(element.getText() + " element is present");		            	
		            }
				}
				catch(Exception e){
					logInfo("Failed!! Unable to wait on this element " + locator);		
				}				
			}	
				
	// This method is used to wait until page being loaded.
	
	public void waitForPageToLoad()
	  {
	     (new WebDriverWait(driver(), DEFAULT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
	      public Boolean apply(WebDriver d) {
	        return (((org.openqa.selenium.JavascriptExecutor) driver()).executeScript("return document.readyState").equals("complete"));
	      }
	    });
	 }
	
	
			// Validate text in the webpage.		
		public boolean validateTextPresentInPage(String Bytype, String locator, String expText){
			String value = null;
			boolean isTextFound = false;			
			try{
				switch(Bytype){
				case "xpath":
					element = driver().findElement(ByXPath.xpath(locator));
					break;
				case "id":
					element = driver().findElement(ById.id(locator));
					break;
				case "name":
					element = driver().findElement(ByName.name(locator));
					break;
				case "tagName":
					element = driver().findElement(ByTagName.tagName(locator));
					break;
				case "className":
					element = driver().findElement(ByClassName.className(locator));
					break;
				case "cssSelector":
					element = driver().findElement(ByCssSelector.cssSelector(locator));
					break;
				default :
					System.out.println("Invalid type passed to selectFromDropdown."+locator);
					break;
				}				
				if(element.isDisplayed() && element.isEnabled()){
					value = element.getText().trim(); 
					if(value.contains(expText)){
						logInfo(value + " match foud in the page.");
					}
				}
				
			} catch(Exception e){
				logInfo(value + " could not be in the page.");
				logger.error("Failed!! Required option is not available "+locator);		
			}
			return isTextFound;
			
		}
	
		
		// Get text on the element
		
		public String getTextPresentOnElement(String Bytype, String locator) throws Exception{	
			String value = null;
			
			try{
				switch(Bytype){
				case "xpath":
					element = driver().findElement(ByXPath.xpath(locator));
					break;
				case "id":
					element = driver().findElement(ById.id(locator));
					break;
				case "name":
					element = driver().findElement(ByName.name(locator));
					break;
				case "className":
					element = driver().findElement(ByClassName.className(locator));
					break;
				case "cssSelector":
					element = driver().findElement(ByCssSelector.cssSelector(locator));
					break;
				default :
					System.out.println("Invalid type passed to getText."+locator);
					break;
				}
				
				if(element.isDisplayed() && element.isEnabled()){
					value = element.getText().trim();
					System.out.println("value =" +value);
				}
			}
			catch(Exception e){
				logger.error("Failed!! Unable to get the text of an element " + locator);		
			}
			
			return value;
		}
		
		
		 
		 public void scrollDown(String Bytype, String locator) throws Exception{
			 try{
				 js = (JavascriptExecutor)driver();        
				 
				switch(Bytype){
					case "xpath":
						element = driver().findElement(ByXPath.xpath(locator));
						break;
					case "id":
						element = driver().findElement(ById.id(locator));
						break;
					case "name":
						element = driver().findElement(ByName.name(locator));
						break;
					case "className":
						element = driver().findElement(ByClassName.className(locator));
						break;
					case "cssSelector":
						element = driver().findElement(ByCssSelector.cssSelector(locator));
						break;
					case "linkText":
						element = driver().findElement(ByLinkText.linkText(locator));
						break;	
					case "partialLinkText":
						element = driver().findElement(ByPartialLinkText.partialLinkText(locator));
						break;	
					default :
						System.out.println("Invalid type passed to clickOnButton."+locator);
						break;
					}
					
					if(element.isDisplayed() && element.isEnabled()){
						 js.executeScript("arguments[0].scrollIntoView(true);", element);
						 int  size = (int) js.executeScript(" return window.innerHeight;",element);
					}
				}
				catch(Exception e){
					logger.error("Failed!! Unable to find element "+locator);		
				}
		 }
		 
		 
		 // Replacement of auto-it
		 public void uploadFile(String fileType,String cssLocator) throws Exception{
			 logInfo("inside uploadFile() method.");
			 
			 switch(fileType){
			 	
				case "Image":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath + "icentris_image3.jpeg");
					break;
				case "Document":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath+"icentris_doc.doc");
					break;
				case "PDF":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath+"icentris_pdf.pdf");
					break;
				case "PPT":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath+"icentris_ppt.pptx");
					break;
				case "Text":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath+"icentris_text.txt");
					break;
				case "Video":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath+"icentris_video.mp4");
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(Keys.F8);
					break;
				case "Zip":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath+"icentris_zip.zip");
					break;
				case "Audio":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath+"icentris_audio.mp3");
					break;
				case "Excel":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath+"icentris_excel.xlsx");
					break;
				case "GIF":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath+"icentris_gif.gif");
					break;
				case "MoreThan4MBFile":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath+"MoreThan4MbImg.jpeg");
					break;
				case "MoreThan4MBPDF":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys(uploadPath+"5mb_file.pdf");
					break;
				default :
					System.out.println("Invalid File Type mentiods.");
					break;
			 }
		 }
		 
		 	
		// Replacement of RObot
		public void composeText(String Bytype, String locator, String text) {
			logInfo("inside composeText() method.");
			
			try{
				js = (JavascriptExecutor)driver(); 				 
				switch(Bytype){
					case "xpath":
						element = driver().findElement(ByXPath.xpath(locator));
						break;
					case "id":
						element = driver().findElement(ById.id(locator));
						break;
					case "name":
						element = driver().findElement(ByName.name(locator));
						break;
					case "className":
						element = driver().findElement(ByClassName.className(locator));
						break;
					case "cssSelector":
						element = driver().findElement(ByCssSelector.cssSelector(locator));
						break;
					default :
						System.out.println("Invalid type passed to composeText method "+locator);
						break;
					}
					
					WebElement frame = element;
					driver().switchTo().frame(frame);
					WebElement body = driver().findElement(ByTagName.tagName("body"));
					body.clear();
					body.sendKeys(text);
					driver().switchTo().defaultContent();
				
				}
				catch(Exception e){
					logger.error("Failed!! Unable to click on a button."+locator);		
				}

		}
		 
	
		
		public void confirmBootboxYes() throws Exception {
			logInfo("inside confirmBootboxYes() method.");
			waitOnElement("xpath","//button[text()='Yes']");
			driver().findElement(ByXPath.xpath("//button[text()='Yes']")).click();
		}
		
		public void confirmBootboxNo() throws Exception, Exception {
			logInfo("inside confirmBootboxNo() method.");
			waitOnElement("xpath","//button[text()='No']");
			driver().findElement(ByXPath.xpath("//button[text()='No']")).click();
		}
		
		// This method is being used to accept javascript alerts / confirmations
		
	public void confirmAlert(){
			logInfo("inside confirmJsAlertOrConfirmation() method.");
			Alert alert = driver().switchTo().alert();
			alert.accept();
		}
		
		// This method is being used to dismiss javascript alerts / confirmations
		
		public void dismissAlert() throws Exception{
			logInfo("inside cancelJsAlertOrConfirmation() method.");
			Alert alert = driver().switchTo().alert();
			alert.dismiss();
			SoftAssert softAssert = new SoftAssert ();
			//softAssert.assertNotEquals(actual, expected);

			
			
		}
		
			    
	  
	  	 public void hoverOnElement(String Bytype, String locator) throws Exception{
			 try{
				 
				 Actions builder = new Actions(driver());
				
				switch(Bytype){
					case "xpath":
						element = driver().findElement(ByXPath.xpath(locator));
						builder.moveToElement(element).build().perform();
						builder.click();
						break;
					case "id":
						element = driver().findElement(ById.id(locator));
						builder.moveToElement(element).build().perform();
						builder.click();
						break;
					case "name":
						element = driver().findElement(ByName.name(locator));
						builder.moveToElement(element).build().perform();
						builder.click();
						break;
					case "className":
						element = driver().findElement(ByClassName.className(locator));
						builder.moveToElement(element).build().perform();
						builder.click();
						break;
					case "cssSelector":
						element = driver().findElement(ByCssSelector.cssSelector(locator));
						builder.moveToElement(element).build().perform();
						builder.click();
						break;
					case "linkText":
						element = driver().findElement(ByLinkText.linkText(locator));
						builder.moveToElement(element).build().perform();
						builder.click();
					case "partialLinkText":
						element = driver().findElement(ByPartialLinkText.partialLinkText(locator));
						builder.moveToElement(element).build().perform();
						builder.click();	
					default :
						System.out.println("Invalid type passed to clickOnButton."+locator);
						break;
					}
					
			 	}
				catch(Exception e){
					logger.error("Failed!! Unable to click on a button."+locator);		
				}
		 }

	 
	  	public boolean validateTextOnElement(String Bytype, String locator, String expectedText) {
			logInfo("inside validateTextOnElement() method.");
			boolean isMatchFound = false;
			
			try{
				switch(Bytype){
				case "xpath":
					element = driver().findElement(ByXPath.xpath(locator));
					break;
				case "id":
					element = driver().findElement(ById.id(locator));
					break;
				case "name":
					element = driver().findElement(ByName.name(locator));
					break;
				case "className":
					element = driver().findElement(ByClassName.className(locator));
					break;
				case "cssSelector":
					element = driver().findElement(ByCssSelector.cssSelector(locator));
					break;
				case "linkText":
					element = driver().findElement(ByLinkText.linkText(locator));
					break;
				default :
					System.out.println("Invalid type passed to clickOnElement."+locator);
					break;
				}
				
				String actualText = element.getText().trim();
				System.out.println("actualText =" +actualText);
				
				if(actualText.equalsIgnoreCase(expectedText)){
				//	System.out.println(expectedText + " match found in the element");
					logInfo(expectedText + " match found in the element");
					isMatchFound=true;
					
				} else {
					System.out.println(expectedText + " match not found in the element");
					logInfo(expectedText + " match not found in the element");
					Assert.assertTrue(isMatchFound, expectedText + " match not found in the element");
				}
				   
			}
			catch(Exception e){
				logInfo("Failed!! Unable to find the element."+locator);		
			}
			
			return isMatchFound;
		}

	 public void hoverOnElementAndClick(String Bytype, String locator) throws Exception{
		 try{
			 
			 Actions builder = new Actions(driver());
			
			switch(Bytype){
				case "xpath":
					element = driver().findElement(ByXPath.xpath(locator));
					builder.moveToElement(element).build().perform();
					element.click();
					break;
				case "id":
					element = driver().findElement(ById.id(locator));
					builder.moveToElement(element).build().perform();
					element.click();
					break;
				case "name":
					element = driver().findElement(ByName.name(locator));
					builder.moveToElement(element).build().perform();
					element.click();
					break;
				case "className":
					element = driver().findElement(ByClassName.className(locator));
					builder.moveToElement(element).build().perform();
					element.click();
					break;
				case "cssSelector":
					element = driver().findElement(ByCssSelector.cssSelector(locator));
					builder.moveToElement(element).build().perform();
					element.click();
					break;
				case "linkText":
					element = driver().findElement(ByLinkText.linkText(locator));
					builder.moveToElement(element).build().perform();
					element.click();
				case "partialLinkText":
					element = driver().findElement(ByPartialLinkText.partialLinkText(locator));
					builder.moveToElement(element).build().perform();
					element.click();	
				default :
					System.out.println("Invalid type passed to clickOnButton."+locator);
					break;
				}
				
		 	}
			catch(Exception e){
				logger.error("Failed!! Unable to click on a button."+locator);		
			}
	 }

	 	 
	 public void dragAndDropAction(WebElement from, WebElement to) throws Exception{		
			logInfo("Entered into dragAndDropAction() method");		
			Actions builder = new Actions(driver());
			Action drag = builder.clickAndHold(from).
					moveToElement(to).
					release(to).
					build();
			drag.perform();			
		}
	 
	 

}