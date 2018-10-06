package common;

import org.testng.annotations.Test;

@Priority(101)
public class UserLogin extends TestBase{ 
	
	 @Test(priority=10100)
	
	 public void loginAsUser() throws  Exception{
	logInfo("inside loginAsUser() Test.");
	loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
			prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
	}
	 
	 

	
}
