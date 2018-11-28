package zagg.login.tests;

import common.TestBase;

public class LoginMethods extends TestBase{
	
	public void logIn(String userName, String passwd) throws Exception {
            logInfo("Enter login credentials");
            driver().navigate().to(appUrl);
            
            
	}
	
	

}
