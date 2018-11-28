package zagg.login.tests;

import org.testng.annotations.Test;

public class LoginTests extends LoginMethods{
	
	 @Test(priority=101)
	  public void login() throws Exception{	
		 	logInfo("inside login() method");
		 	System.out.println("project path "+ projectPath);
		    logIn("adminUser_text","adminPwd_text");
		    System.out.println("project path "+ projectPath);
		    System.out.println("project path "+ projectPath);
		 	
	  }

}
