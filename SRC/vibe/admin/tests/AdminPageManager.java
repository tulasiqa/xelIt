package vibe.admin.tests;


import org.testng.annotations.Test;
import common.Priority;
import vibe.content.pageManager.tests.PageManagerMethods;

@Priority(45)
public class AdminPageManager extends PageManagerMethods {
	
	
	
	@Test(priority=4501)
	public void validatePageManager() throws Exception{
		
		nav2PageManager();
		verifyPageManager();		
	}
	
	
	@Test(priority=4502)
	public void validateSiteMap() throws Exception{		
		nav2PageManager();
		assertSiteMapLanguage();
	    verifySiteMap();
	    assertSiteMapLanguage();	
	}
	
	
	@Test(priority=4520)
	public void stopCMS() throws Exception{		
		nav2PageManager();
		selectAdminUserDropDwon("Stop CMS");
			
	}
		

}
