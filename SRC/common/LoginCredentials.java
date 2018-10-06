package common;

import org.testng.annotations.Test;

@Priority(1)
public class LoginCredentials extends TestBase {

	 @Test(priority=101)
	  public void login() throws Exception{	
		 	logInfo("inside login() method");
		 	System.out.println("project path "+ projectPath);
		    logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		 	
	  }

	 
	 
}
