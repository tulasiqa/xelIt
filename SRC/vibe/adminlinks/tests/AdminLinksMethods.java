package vibe.adminlinks.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import common.TestBase;


public class AdminLinksMethods extends TestBase {

	public void go2Adminpage() throws Exception{
		verifyLinkPresent("Go To Admin");
		clickOnLink("linkText", "Go To Admin");
		waitOnSpinner();
	}
	
	public void verifyHeader(String HeaderName) throws Exception{
		logInfo("inside verifyHeader() method");
		System.out.println("inside verifyHeader() method");
		boolean isHeaderFound = false;
		verifyLinkPresent(HeaderName);
		WebElement lnk = driver().findElement(By.linkText(HeaderName));
		if(lnk.isDisplayed()){

		WebElement adminSidePane = driver().findElement(By.cssSelector(adminNavigationbar));
		List<WebElement> allHeadings = adminSidePane.findElements(By.className("accordion-heading"));
		int count = allHeadings.size();
		for(int i=1;i<=count-1;i++){
			String headingName = allHeadings.get(i).getText().trim();
			if(headingName.equalsIgnoreCase(HeaderName)){
				clickOnLink("linkText",headingName);
				waitOnSpinner();
				break;
			}
		}
				
	  }	else {
		  Assert.assertTrue(isHeaderFound, "Unable to verify the link " + HeaderName + " in the admin pane.");
	  }
		
	}
	
	public void verifySubHeaders(String headerName) throws IOException{
		logInfo("inside verifySubHeaders() method");
		System.out.println("inside verifySubHeaders() method");
		boolean isHeaderFound = false;
		List<WebElement> headers = driver().findElements(By.xpath("//*[@class='accordion-group']"));
		String before = "//*[@class='accordion-group'][";
		String after = "]/div[1]/a/span";
		
		String before_subheader = "//div[@class='accordion-group']/div[@class='accordion-body collapse in']/div[@class='accordion-inner']/ul/li[";
		String after_subheader = "]/a/span[@class='text']";
	
		String beforeLink = "//div[@class='accordion-group']/div[@class='accordion-body collapse in']/div[@class='accordion-inner']/ul/li[";
		String afterLink = "]/a";

		if(headers.size()>0){
			for(int i=1;i<=headers.size();i++){
				WebElement header = driver().findElement(By.xpath(before+i+after));
				if(header.getText().trim().equalsIgnoreCase(headerName)){
					isHeaderFound=true;
					List<WebElement> subHeaders = driver().findElements(By.xpath("//*[@class='accordion-group']["+i+"]/div[2]/div/ul/li"));
					if(subHeaders.size()>0){
						for(int j=1;j<=subHeaders.size();j++){
							WebElement e = driver().findElement(By.xpath(before_subheader+j+after_subheader));
							String linkName = e.getText().trim();
							System.out.println("Link Name =" +linkName);
								WebElement z = driver().findElement(By.xpath(beforeLink+j+afterLink));
								String href = z.getAttribute("href");
								URL url = new URL(href);
								HttpURLConnection connection = (HttpURLConnection) url.openConnection();
								String response = "";
								
								try{
									connection.connect();
									response = connection.getResponseMessage().trim();
									connection.disconnect();
									System.out.println("Response for " +linkName +" =" +response);
									logInfo("Response for " +linkName + " = " +response);
									if(!response.equalsIgnoreCase("OK")){
										logInfo("Link " + headerName + "-->" + linkName + " has Invalid Response \'" + response + "\'");
										Assert.assertTrue(!response.equalsIgnoreCase("OK"), "Link " + headerName + "-->" + linkName + " has Invalid Response \'" + response + "\'");
									}
									
								}
								catch(Exception ex){
									System.out.println(ex.getMessage());
								}
						}
					}
					break;
				}
				
			}
		}
				
	
	}
	
	
	}
