package vibe.widgets.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.EnvProperties;
import common.Priority;
import common.TestBase;


import vibe.tasks.tests.TaskMethods;
import vibe.teardown.tests.AdminTearDownMarkNews;
import vibe.calendar2.tests.CalendarTests;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.marketing.ads.tests.AdsMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.marketing.companyNews.tests.NewsTests;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.myprofile.tests.MyProfileTests;

@Priority(126)
public class WidgetsTests extends WidgetsMethods {
	
	WidgetsMethods wm = new WidgetsMethods();
	TestBase tb = new TestBase();
	NewsTests nt = new NewsTests();
	NewsMethods nm = new NewsMethods();
	AdminTearDownMarkNews tnews = new AdminTearDownMarkNews();
	CommunityMethods cm = new CommunityMethods();
	CalendarTests cal = new CalendarTests();
	MyProfileMethods pm = new MyProfileMethods();
	BusinessContactsMethods bm = new BusinessContactsMethods();
	AdsMethods ads = new AdsMethods();	
	CalendarTests cat = new CalendarTests();
	MyProfileTests pt = new MyProfileTests();
	
	String yes = "Yes";
	
	
	
	// ABOUT Widget
	
	@Test(priority=12601)
	public void verifyAboutWidget() throws Exception{
		logInfo("inside verifyAboutWidget() Test");
				
		// drag and drop the widget on the home page.
		//dragAndDropWidget("About");
		
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("About");
		if(isWidgetAdded2Homepage==true){
			logInfo("About widget added to home page.");
			 verifyWidgetForProblemsRendering("About");
			boolean isMatchFound = verifyAboutWidgetContent();
			if(!isMatchFound){
				Assert.assertTrue(isMatchFound, " Email match not found in the widget");
			}
		
			//// removeWidgetInHomepage("About");
		} else {
			logInfo("About widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "About widget could not be added to home page.");
		}
	}  
	
	
	
	@Test(priority=12602)
	public void disableAboutWidget() throws Exception{
		logInfo("inside disableAboutWidget() Test");
		 
		setWidgetStatus("About",false);
		go2HomePage();
		
		boolean isWidgPresent = verifyWidgetPresent("About");
		if(isWidgPresent==true){
			logInfo("About widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "About widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("About widget not found in the widget page, since the widget is disabled.");
		}
	}
	
	
	// ACTION ITEMS Widget
	
	@Test(priority=12603)
	public void verifyActionItemsWidget() throws Exception{
		logInfo("inside verifyActionItemsWidget() Test");
		// drag and drop the widget on the home page.
		//dragAndDropWidget("Action Items");
		
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Action Items");
		if(isWidgetAdded2Homepage==true){
			logInfo("Action Items widget added to home page.");
			verifyWidgetForProblemsRendering("Action Items");
			// removeWidgetInHomepage("Action Items");
		} else {
			logInfo("Action Items widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Action Items widget could not be added to home page.");
		}
	}  
	
	
	
	@Test(priority=12604)
	public void disableActionItemsWidget() throws Exception{
		logInfo("inside disableActionItemsWidget() Test");
		 
		setWidgetStatus("Action Items",false);
		go2HomePage();
		
		boolean isWidgPresent = verifyWidgetPresent("Action Items");
		if(isWidgPresent==true){
			logInfo("Action Items widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Action Items widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Action Items widget not found in the widget page, since the widget is disabled.");
		}
	}
	
	
	
	// Tabbed Information
	
	
	@Test(priority=12605)
	public void verifyTabbedInformationWidget() throws Exception{
		logInfo("inside verifyTabbedInformationWidget() Test");
	
		 nm.companyNewsCreation(title,excerpt, ranker5,languageList,subscripList, yes,yes,yes,yes);
		 String subslist = EnvProperties.getLocatorForEnvironment(appUrl, "subscripListOfVibe[]");
		
		
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Tabbed Information");
		
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tabbed Information");
		if(isWidgetAdded2Homepage==true){
			logInfo("Tabbed Information widget added to home page.");
			verifyWidgetForProblemsRendering("Tabbed Information");
			
			boolean isMatchFound = verifyNewsPresentInWidget(title);
			if(!isMatchFound){
				Assert.assertTrue(isMatchFound, " News match not found in the Tabbed Information widget");
			}
		
			// removeWidgetInHomepage("Tabbed Information");
		} else {
			logInfo("Tabbed Information widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tabbed Information widget could not be added to home page.");
		}
	}  
	

	
	@Test(priority=12606)
	public void disableTabbedInformationWidget() throws Exception{
		logInfo("inside disableTabbedInformationWidget() Test");
		 
		setWidgetStatus("Tabbed Information",false);
		go2HomePage();
		
		boolean isWidgPresent = verifyWidgetPresent("Tabbed Information");
		if(isWidgPresent==true){
			logInfo("Tabbed Information widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Tabbed Information widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Tabbed Information widget not found in the widget page, since the widget is disabled.");
		}
	}
	
	
	
	// CALENDAR TYPES Widget

		@Test(priority=12607)
		public void verifyCalendarTypesWidget() throws Exception{
			logInfo("inside verifyCalendarTypesWidget() Test");
			
			go2HomePage();
			dragAndDropWidget("Calendar Types");
						
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Calendar Types");
			if(isWidgetAdded2Homepage==true){
				logInfo("Calendar Types widget added to home page.");
				verifyWidgetForProblemsRendering("Calendar Types");
				verifyCalendarTypesInWidget();
				
				// removeWidgetInHomepage("Calendar Types");
			} else {
				logInfo("Calendar Types widget could not be added to home page.");
				Assert.assertTrue(isWidgetAdded2Homepage, "Calendar Types widget could not be added to home page.");
			}
		}  
		
		
		
		@Test(priority=12608)
		public void disableCalendarTypesWidget() throws Exception{
			logInfo("inside disableCalendarTypesWidget() Test");
			 
			setWidgetStatus("Calendar Types",false);
			go2HomePage();
			
			boolean isWidgPresent = verifyWidgetPresent("Calendar Types");
			if(isWidgPresent==true){
				logInfo("Calendar Types widget still present in the widget page when widget is disabled.");
				Assert.assertFalse(isWidgPresent, "Calendar Types widget still present in the widget page when widget is disabled.");
			} else {
				logInfo("Calendar Types widget not found in the widget page, since the widget is disabled.");
			}
		}
		
	
		
		// COMMUNITY STREAM Widget

		@Test(priority=12609)
		public void verifyCommunityStreamWidget() throws Exception{
			logInfo("inside enableCommunityStreamWidget() Test");
					
			go2HomePage();
			dragAndDropWidget("Community Stream");
						
			// verify enabled widgets are present in widgets pane.
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Community Stream");
			if(isWidgetAdded2Homepage==true){
				logInfo("Community Stream widget added to home page.");
				verifyWidgetForProblemsRendering("Community Stream");
				// removeWidgetInHomepage("Community Stream");
			} else {
				logInfo("Community Stream widget could not be added to home page.");
				Assert.assertTrue(isWidgetAdded2Homepage, "Community Stream widget could not be added to home page.");
			}
		}  
				
				
				
		@Test(priority=12610)
		public void disableCommunityStreamWidget() throws Exception{
			logInfo("inside disableCommunityStreamWidget() Test");
			 
			setWidgetStatus("Community Stream",false);
			go2HomePage();
					
			boolean isWidgPresent = verifyWidgetPresent("Community Stream");
			if(isWidgPresent==true){
				logInfo("Community Stream widget still present in the widget page when widget is disabled.");
					Assert.assertFalse(isWidgPresent, "Community Stream widget still present in the widget page when widget is disabled.");
				} else {
					logInfo("Community Stream widget not found in the widget page, since the widget is disabled.");
				}
			}
				
	
		
		// COMPANY NEWS Widget

		@Test(priority=12611)
		public void verifyCompanyNewsWidget() throws Exception{
		logInfo("inside verifyCompanyNewsWidget() Test");
	
		go2HomePage();
		dragAndDropWidget("Company News");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Company News");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Company News");
		}
									
		if(isWidgetAdded2Homepage==true){
			logInfo("Company News widget added to home page.");
			verifyWidgetForProblemsRendering("Company News");
			// removeWidgetInHomepage("Company News");
		} else {
			logInfo("Company News widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Company News widget could not be added to home page.");
		}
	}  
								
								
								
		@Test(priority=12612)
		public void disableCompanyNewsWidget() throws Exception{
		logInfo("inside disableCompanyNewsWidget() Test");
		 
		setWidgetStatus("Company News",false);
		go2HomePage();
									
		boolean isWidgPresent = verifyWidgetPresent("Company News");
		if(isWidgPresent==true){
			logInfo("Company News widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Company News widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Company News widget not found in the widget page, since the widget is disabled.");
		}
	}	
		
	
	// COMPANY SOCIAL NETWORKS Widget

		@Test(priority=12613)
		public void verifyCompanyNetworksWidget() throws Exception{
		logInfo("inside verifyCompanyNetworksWidget() Test");
		
		// drag and drop the widget on the home page.
		
		go2HomePage();
		dragAndDropWidget("Company Social Networks");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Company Social Networks");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Company Social Networks");
		}
									
		if(isWidgetAdded2Homepage==true){
			logInfo("Company Social Networks widget added to home page.");
			verifyWidgetForProblemsRendering("Company Social Networks");
			// removeWidgetInHomepage("Company Social Networks");
		} else {
			logInfo("Company Social Networks widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Company Social Networks widget could not be added to home page.");
		}
	}  
								
								
								
		@Test(priority=12614)
		public void disableCompanyNetworksWidget() throws Exception{
		logInfo("inside disableCompanyNewsWidget() Test");
		 
		setWidgetStatus("Company Social Networks",false);
		go2HomePage();
									
		boolean isWidgPresent = verifyWidgetPresent("Company Social Networks");
		if(isWidgPresent==true){
			logInfo("Company Social Networks widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Company Social Networks widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Company Social Networks widget not found in the widget page, since the widget is disabled.");
		}
	}	


		// CONTACT GROUPS

		@Test(priority=12615)
		public void verifyContactGroupsWidget() throws Exception{
		logInfo("inside verifyContactGroupsWidget() Test");
													
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Contact Groups");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Contact Groups");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Contact Groups");
		}
											
		if(isWidgetAdded2Homepage==true){
			logInfo("Contact Groups widget added to home page.");
			verifyWidgetForProblemsRendering("Contact Groups");
			// removeWidgetInHomepage("Contact Groups");
		} else {
			logInfo("Contact Groups widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Contact Groups widget could not be added to home page.");
		}
	}  
										
										
										
		@Test(priority=12616)
		public void disableContactGroupsWidget() throws Exception{
		logInfo("inside disableContactGroupsWidget() Test");
		 
		setWidgetStatus("Contact Groups",false);
		go2HomePage();
											
		boolean isWidgPresent = verifyWidgetPresent("Contact Groups");
		if(isWidgPresent==true){
			logInfo("Contact Groups widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Contact Groups widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Contact Groups widget not found in the widget page, since the widget is disabled.");
		}
	}	
		
	
		
		// EVENTS Widgets

		@Test(priority=12617)
		public void verifyEventsWidget() throws Exception{
		logInfo("inside verifyEventsWidget() Test");
												
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Events");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Events");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Events");
		}
											
		if(isWidgetAdded2Homepage==true){
			logInfo("Events widget added to home page.");
			verifyWidgetForProblemsRendering("Events");
			// removeWidgetInHomepage("Events");
		} else {
			logInfo("Events widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Events widget could not be added to home page.");
		}
	}  
										
										
										
		@Test(priority=12618)
		public void disableEventsWidget() throws Exception{
		logInfo("inside disableEventsWidget() Test");
		 
		setWidgetStatus("Events",false);
		go2HomePage();
											
		boolean isWidgPresent = verifyWidgetPresent("Events");
		if(isWidgPresent==true){
			logInfo("Events widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Events widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Events widget not found in the widget page, since the widget is disabled.");
		}
	}	
		
		
		// FEATURED BLOGS Widgets

		@Test(priority=12619)
		public void verifyFeaturedBlogsWidget() throws Exception{
		logInfo("inside verifyFeaturedBlogsWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Featured Blogs");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Featured Blogs");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Featured Blogs");
		}
											
		if(isWidgetAdded2Homepage==true){
			logInfo("Featured Blogs widget added to home page.");
			verifyWidgetForProblemsRendering("Featured Blogs");
			// removeWidgetInHomepage("Featured Blogs");
		} else {
			logInfo("Featured Blogs widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Featured Blogs widget could not be added to home page.");
		}
	}  
										
										
										
		@Test(priority=12620)
		public void disableFeaturedBlogsWidget() throws Exception{
		logInfo("inside disableFeaturedBlogsWidget() Test");
		 
		setWidgetStatus("Featured Blogs",false);
		go2HomePage();
											
		boolean isWidgPresent = verifyWidgetPresent("Featured Blogs");
		if(isWidgPresent==true){
			logInfo("Featured Blogs widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Featured Blogs widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Featured Blogs widget not found in the widget page, since the widget is disabled.");
		}
	}	
		
		// FEATURED PHOTOS Widgets

		@Test(priority=12621)
		public void verifyFeaturedPhotosWidget() throws Exception{
		logInfo("inside verifyFeaturedPhotosWidget() Test");
		 
				
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Featured Photos");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Featured Photos");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Featured Photos");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("Featured Photos widget added to home page.");
			verifyWidgetForProblemsRendering("Featured Photos");
			// removeWidgetInHomepage("Featured Photos");
		} else {
			logInfo("Featured Photos widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Featured Photos widget could not be added to home page.");
		}
	}  
												
												
												
		@Test(priority=12622)
		public void disableFeaturedPhotosWidget() throws Exception{
		logInfo("inside disableFeaturedPhotosWidget() Test");
		 
		setWidgetStatus("Featured Photos",false);
		go2HomePage();
													
		boolean isWidgPresent = verifyWidgetPresent("Featured Photos");
		if(isWidgPresent==true){
			logInfo("Featured Photos widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Featured Photos widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Featured Photos widget not found in the widget page, since the widget is disabled.");
		}
	}	
				
	
		
		// FEATURED VIDEOS Widgets

		@Test(priority=12623)
		public void enableFeaturedVideosWidget() throws Exception{
		logInfo("inside enableFeaturedVideosWidget() Test");
		 
		dragAndDropWidget("Featured Videos");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Featured Videos");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Featured Videos");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("Featured Videos widget added to home page.");
			verifyWidgetForProblemsRendering("Featured Videos");
			// removeWidgetInHomepage("Featured Videos");
		} else {
			logInfo("Featured Videos widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Featured Videos widget could not be added to home page.");
		}
	}  
												
												
												
		@Test(priority=12624)
		public void disableFeaturedVideosWidget() throws Exception{
		logInfo("inside disableFeaturedVideosWidget() Test");
		 
		setWidgetStatus("Featured Videos",false);
		go2HomePage();
													
		boolean isWidgPresent = verifyWidgetPresent("Featured Videos");
		if(isWidgPresent==true){
			logInfo("Featured Videos widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Featured Videos widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Featured Videos widget not found in the widget page, since the widget is disabled.");
		}
	}	
					
		
		// FOLLOWING Widgets

		@Test(priority=12625)
		public void enableFollowingWidget() throws Exception{
		logInfo("inside enableFollowingWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Following");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Following");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Following");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("Following widget added to home page.");
			verifyWidgetForProblemsRendering("Following");
			// removeWidgetInHomepage("Following");
		} else {
			logInfo("Following widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Following widget could not be added to home page.");
		}
	}  
												
												
												
		@Test(priority=12626)
		public void disableFollowingWidget() throws Exception{
		logInfo("inside disableFollowingWidget() Test");
		//  
		setWidgetStatus("Following",false);
		go2HomePage();
													
		boolean isWidgPresent = verifyWidgetPresent("Following");
		if(isWidgPresent==true){
			logInfo("Following widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Following widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Following widget not found in the widget page, since the widget is disabled.");
		}
	}	
			
		
		
		// Goals Widgets

		@Test(priority=12627)
		public void enableGoalsWidget() throws Exception{
		logInfo("inside enableGoalsWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Goals");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Goals");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Goals");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("Goals widget added to home page.");
			verifyWidgetForProblemsRendering("Goals");
			// removeWidgetInHomepage("Goals");
		} else {
			logInfo("Goals widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Goals widget could not be added to home page.");
		}
	}  
												
												
												
		@Test(priority=12628)
		public void disableGoalsWidget() throws Exception{
		logInfo("inside disableGoalsWidget() Test");
		 
		setWidgetStatus("Goals",false);
		go2HomePage();
													
		boolean isWidgPresent = verifyWidgetPresent("Goals");
		if(isWidgPresent==true){
			logInfo("Goals widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Goals widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Goals widget not found in the widget page, since the widget is disabled.");
		}
	}	
			
		
		
		// MYBLOG Widgets

		@Test(priority=12629)
		public void enableMyBlogWidget() throws Exception{
		logInfo("inside enableMyBlogWidget() Test");
		
		go2HomePage();
		dragAndDropWidget("My Blog");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("My Blog");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("My Blog");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("My Blog widget added to home page.");
			verifyWidgetForProblemsRendering("My Blog");
			// removeWidgetInHomepage("My Blog");
		} else {
			logInfo("My Blog widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "My Blog widget could not be added to home page.");
		}
	}  
												
												
												
		@Test(priority=12630)
		public void disableMyBlogWidget() throws Exception{
		logInfo("inside disableMyBlogWidget() Test");
		 
		setWidgetStatus("My Blog",false);
		go2HomePage();
													
		boolean isWidgPresent = verifyWidgetPresent("My Blog");
		if(isWidgPresent==true){
			logInfo("My Blog widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "My Blog widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("My Blog widget not found in the widget page, since the widget is disabled.");
		}
	}	
			
		
	// MY PHOTOS Widgets

		@Test(priority=12631)
		public void enableMyPhotosWidget() throws Exception{
		logInfo("inside enableMyPhotosWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("My Photos");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("My Photos");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("My Photos");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("My Photos widget added to home page.");
			verifyWidgetForProblemsRendering("My Photos");
			// removeWidgetInHomepage("My Photos");
		} else {
			logInfo("My Photos widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "My Photos widget could not be added to home page.");
		}
	}  
												
												
												
		@Test(priority=12632)
		public void disableMyPhotosWidget() throws Exception{
		logInfo("inside disableMyPhotosWidget() Test");
		 
		setWidgetStatus("My Photos",false);
		go2HomePage();
													
		boolean isWidgPresent = verifyWidgetPresent("My Photos");
		if(isWidgPresent==true){
			logInfo("My Photos widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "My Photos widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("My Photos widget not found in the widget page, since the widget is disabled.");
		}
	}
		
		
	
		// MY VIDEOS Widgets

		@Test(priority=12633)
		public void enableMyVideosWidget() throws Exception{
		logInfo("inside enableMyVideosWidget() Test");
						
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("My Videos");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("My Videos");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("My Videos");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("My Videos widget added to home page.");
			verifyWidgetForProblemsRendering("My Videos");
			// removeWidgetInHomepage("My Videos");
		} else {
			logInfo("My Videos widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "My Videos widget could not be added to home page.");
		}
	}  
														
														
														
		@Test(priority=12634)
		public void disableMyVideosWidget() throws Exception{
		logInfo("inside disableMyVideosWidget() Test");
		 
		setWidgetStatus("My Videos",false);
		go2HomePage();
															
		boolean isWidgPresent = verifyWidgetPresent("My Videos");
		if(isWidgPresent==true){
			logInfo("My Videos widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "My Videos widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("My Videos widget not found in the widget page, since the widget is disabled.");
		}
	}	
				
	
		
		// MY WEBSITES Widgets

		@Test(priority=12635)
		public void enableMyWebsitesWidget() throws Exception{
		logInfo("inside enableMyWebsitesWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("My Websites");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("My Websites");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("My Websites");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("My Websites widget added to home page.");
			verifyWidgetForProblemsRendering("My Websites");
			// removeWidgetInHomepage("My Websites");
		} else {
			logInfo("My Websites widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "My Websites widget could not be added to home page.");
		}
	}  
														
														
														
		@Test(priority=12636)
		public void disableMyWebsitesWidget() throws Exception{
		logInfo("inside disableMyWebsitesWidget() Test");
		 
		setWidgetStatus("My Websites",false);
		go2HomePage();
															
		boolean isWidgPresent = verifyWidgetPresent("My Websites");
		if(isWidgPresent==true){
			logInfo("My Websites widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "My Websites widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("My Websites widget not found in the widget page, since the widget is disabled.");
		}
	}	
				
		
		
		// NOTIFICATIONS Widgets

		@Test(priority=12637)
		public void enableNotificationsWidget() throws Exception{
		logInfo("inside enableNotificationsWidget() Test");
		
		go2HomePage();
		/*cat.createNewCalendarEvent();*/
																
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Notifications");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Notifications");
		if(isWidgetAdded2Homepage==true){
			logInfo("Notifications widget added to home page.");
			
			String notification_text = "upcoming event(s) in the next 126 days";
			boolean isNotificationFound = verifyNotificationsInWidget(notification_text);  
			if(isNotificationFound==false){
				logInfo(notification_text + " notification not found in Notifications widget.");
				Assert.assertTrue(isNotificationFound, notification_text + " notification not found in Notifications widget.");
			}
		}
	}  
														
														
														
		@Test(priority=12638)
		public void disableNotificationsWidget() throws Exception{
		logInfo("inside disableNotificationsWidget() Test");
		 
		setWidgetStatus("Notifications",false);
		go2HomePage();
															
		boolean isWidgPresent = verifyWidgetPresent("Notifications");
		if(isWidgPresent==true){
			logInfo("Notifications widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Notifications widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Notifications widget not found in the widget page, since the widget is disabled.");
		}
	}	
		
		
		
		// PROGRESS REPORT Widgets

		@Test(priority=12639)
		public void enableProgressReportWidget() throws Exception{
		logInfo("inside enableProgressReportWidget() Test");
		 
		go2HomePage();
		dragAndDropWidget("Progress Report");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Progress Report");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Progress Report");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("Progress Report widget added to home page.");
			verifyWidgetForProblemsRendering("Progress Report");
			// removeWidgetInHomepage("Progress Report");
			
		} else {
			logInfo("Progress Report widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Progress Report widget could not be added to home page.");
		}
	}  
														
														
														
		@Test(priority=12640)
		public void disableProgressReportWidget() throws Exception{
		logInfo("inside disableProgressReportWidget() Test");
		 
		setWidgetStatus("Progress Report",false);
		go2HomePage();
															
		boolean isWidgPresent = verifyWidgetPresent("Progress Report");
		if(isWidgPresent==true){
			logInfo("Progress Report widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Progress Report widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Progress Report widget not found in the widget page, since the widget is disabled.");
		}
	}	
				
		
		
		// QUICKLINKS BUTTON VIEW Widgets

		@Test(priority=12641)
		public void enableQuickLinksButtonViewWidget() throws Exception{
		logInfo("inside enableQuickLinksButtonViewWidget() Test");
		 
				
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Quick Links Button View");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Quick Links Button View");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Quick Links Button View");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("Quick Links Button View widget added to home page.");
			verifyWidgetForProblemsRendering("Quick Links Button View");
			// removeWidgetInHomepage("Quick Links Button View");
		} else {
			logInfo("Quick Links Button View widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Quick Links Button View widget could not be added to home page.");
		}
	}  
														
														
														
		@Test(priority=12642)
		public void disableQuicLinksButtonViewWidget() throws Exception{
		logInfo("inside disableQuickLinksButtonViewWidget() Test");
		 
		setWidgetStatus("Quick Links Button View",false);
		go2HomePage();
															
		boolean isWidgPresent = verifyWidgetPresent("Quick Links Button View");
		if(isWidgPresent==true){
			logInfo("Quick Links Button View widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Quick Links Button View widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Quick Links Button View widget not found in the widget page, since the widget is disabled.");
		}
	}	

		
		
		// QUICKLINKS LIEW VIEW Widgets

		@Test(priority=12643)
		public void enableQuickLinksListViewWidget() throws Exception{
		logInfo("inside enableQuickLinksButtonListWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Quick Links List View");
		
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Quick Links List View");
		if(isWidgetAdded2Homepage==true){
			dragAndDropWidget("Quick Links List View");
			verifyWidgetForProblemsRendering("Quick Links List View");
			// removeWidgetInHomepage("Quick Links List View");
		}	else {
			logInfo("Quick Links List View widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Quick Links List View widget could not be added to home page.");
		}
	}  
														
														
														
		@Test(priority=12644)
		public void disableQuickLinksListViewWidget() throws Exception{
		logInfo("inside disableQuickLinksListViewWidget() Test");
		 
		setWidgetStatus("Quick Links List View",false);
		go2HomePage();
															
		boolean isWidgPresent = verifyWidgetPresent("Quick Links List View");
		if(isWidgPresent==true){
			logInfo("Quick Links List View widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Quick Links List View widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Quick Links List View widget not found in the widget page, since the widget is disabled.");
		}
	}	
		
		
	
		
		// RECENT ACTIVITY Widgets

		@Test(priority=12645)
		public void enableRecentActivityWidget() throws Exception{
		logInfo("inside enableRecentActivityWidget() Test");
		 
			
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Recent Activity");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Recent Activity");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Recent Activity");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("Recent Activity widget added to home page.");
			verifyWidgetForProblemsRendering("Recent Activity");
			// // removeWidgetInHomepage("Recent Activity");
		} else {
			logInfo("Recent Activity widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Recent Activity widget could not be added to home page.");
		}
	}  
														
														
														
		@Test(priority=12646)
		public void disableRecentActivityWidget() throws Exception{
		logInfo("inside disableRecentActivityWidget() Test");
		 
		setWidgetStatus("Recent Activity",false);
		go2HomePage();
															
		boolean isWidgPresent = verifyWidgetPresent("Recent Activity");
		if(isWidgPresent==true){
			logInfo("Recent Activity widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Recent Activity widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Recent Activity widget not found in the widget page, since the widget is disabled.");
		}
	}	
		
		
		// REPORT Widgets

		@Test(priority=12647)
		public void enableReportWidget() throws Exception{
		logInfo("inside enableReportWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Report");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Report");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Report");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("Report widget added to home page.");
			verifyWidgetForProblemsRendering("Report");
			// removeWidgetInHomepage("Report");
		} else {
			logInfo("Report widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Report widget could not be added to home page.");
		}
	}  
														
														
														
		@Test(priority=12648)
		public void disableReportWidget() throws Exception{
		logInfo("inside disableReportWidget() Test");
		 
		setWidgetStatus("Report",false);
		go2HomePage();
															
		boolean isWidgPresent = verifyWidgetPresent("Report");
		if(isWidgPresent==true){
			logInfo("Report widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Report widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Report widget not found in the widget page, since the widget is disabled.");
		}
	}	
		
		
		
		// RESOURCE LIBRARY Widgets

		@Test(priority=12649)
		public void enableResourceLibraryWidget() throws Exception{
		logInfo("inside enableResourceLibraryWidget() Test");
																	
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Resource Library");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Resource Library");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Resource Library");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("Resource Library widget added to home page.");
			verifyWidgetForProblemsRendering("Resource Library");
			// removeWidgetInHomepage("Resource Library");
		} else {
			logInfo("Resource Library widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Resource Library widget could not be added to home page.");
		}
	}  
														
														
														
		@Test(priority=12650)
		public void disableResourceLibraryWidget() throws Exception{
		logInfo("inside disableResourceLibraryWidget() Test");
		 
		setWidgetStatus("Resource Library",false);
		go2HomePage();
															
		boolean isWidgPresent = verifyWidgetPresent("Resource Library");
		if(isWidgPresent==true){
			logInfo("Resource Library widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Resource Library widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Resource Library widget not found in the widget page, since the widget is disabled.");
		}
	}	
		
		
		
		// SEARCH CONTACTS Widgets

		@Test(priority=12651)
		public void enableSearchContactsWidget() throws Exception{
		logInfo("inside enableSearchContactsWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Search Contacts");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Search Contacts");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Search Contacts");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("Search Contacts widget added to home page.");
			verifyWidgetForProblemsRendering("Search Contacts");
			// removeWidgetInHomepage("Search Contacts");
		} else {
			logInfo("Search Contacts widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Search Contacts widget could not be added to home page.");
		}
	}  
														
														
														
		@Test(priority=12652)
		public void disableSearchContactsWidget() throws Exception{
		logInfo("inside disableSearchContactsWidget() Test");
		 
		setWidgetStatus("Search Contacts",false);
		go2HomePage();
															
		boolean isWidgPresent = verifyWidgetPresent("Search Contacts");
		if(isWidgPresent==true){
			logInfo("Search Contacts widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Search Contacts widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Search Contacts widget not found in the widget page, since the widget is disabled.");
		}
	}	
		
		
		
		// SEARCH EVENTS Widgets

		@Test(priority=12653)
		public void enableSearchEventsWidget() throws Exception{
		logInfo("inside enableSearchEventsWidget() Test");
																			
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Search Events");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Search Events");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Search Events");
		}
																	
		if(isWidgetAdded2Homepage==true){
			logInfo("Search Events widget added to home page.");
			verifyWidgetForProblemsRendering("Search Events");
			// removeWidgetInHomepage("Search Events");
		} else {
			logInfo("Search Events widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Search Events widget could not be added to home page.");
		}
	}  
																
																
		@Test(priority=12654)
		public void disableSearchEventsWidget() throws Exception{
		logInfo("inside disableSearchEventsWidget() Test");
		 
		setWidgetStatus("Search Events",false);
		go2HomePage();
																	
		boolean isWidgPresent = verifyWidgetPresent("Search Events");
		if(isWidgPresent==true){
			logInfo("Search Events widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Search Events widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Search Events widget not found in the widget page, since the widget is disabled.");
		}
	}	
			
		
		// ADD TASKS Widgets

		@Test(priority=12655)
		public void enableTasksAddWidget() throws Exception{
		logInfo("inside enableTasksAddWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Tasks Add");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tasks Add");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Tasks Add");
		}
																	
		if(isWidgetAdded2Homepage==true){
			logInfo("Tasks Add widget added to home page.");
			verifyWidgetForProblemsRendering("Tasks Add");
			// removeWidgetInHomepage("Tasks Add");
		} else {
			logInfo("Tasks Add widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tasks Add widget could not be added to home page.");
		}
	}  
																
																
		@Test(priority=12656)
		public void disableTasksAddWidget() throws Exception{
		logInfo("inside disableTasksAddWidget() Test");
		 
		setWidgetStatus("Tasks Add",false);
		go2HomePage();
																	
		boolean isWidgPresent = verifyWidgetPresent("Tasks Add");
		if(isWidgPresent==true){
			logInfo("Tasks Add widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Tasks Add widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Tasks Add widget not found in the widget page, since the widget is disabled.");
		}
	}	
			
		

		// TASKS COMPLETED Widget

	
		@Test(priority=12657)
		public void enableTasksCompletedWidget() throws Exception{
		logInfo("inside enableTasksCompletedWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Tasks Completed");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tasks Completed");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Tasks Completed");
		}
																			
		if(isWidgetAdded2Homepage==true){
			logInfo("Tasks Completed widget added to home page.");
			verifyWidgetForProblemsRendering("Tasks Completed");
			// removeWidgetInHomepage("Tasks Completed");
		} else {
			logInfo("Tasks Completed widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tasks Completed widget could not be added to home page.");
		}
	}  
																		
																		
		@Test(priority=12658)
		public void disableTasksCompletedWidget() throws Exception{
		logInfo("inside disableTasksCompletedWidget() Test");
		 
		setWidgetStatus("Tasks Completed",false);
		go2HomePage();
																			
		boolean isWidgPresent = verifyWidgetPresent("Tasks Completed");
		if(isWidgPresent==true){
			logInfo("Tasks Completed widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Tasks Completed widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Tasks Completed widget not found in the widget page, since the widget is disabled.");
		}
	}	
		
		

		// TASKS COMPLETED INCOMPLETE Widget

	
		@Test(priority=12659)
		public void enableTasksCompletedIncompleteWidget() throws Exception{
		logInfo("inside enableTasksCompletedIncompleteWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Tasks Completed Incomplete");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tasks Completed Incomplete");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Tasks Completed Incomplete");
		}
																			
		if(isWidgetAdded2Homepage==true){
			logInfo("Tasks Completed Incomplete widget added to home page.");
			verifyWidgetForProblemsRendering("Tasks Completed Incomplete");
			// removeWidgetInHomepage("Tasks Completed Incomplete");
		} else {
			logInfo("Tasks Completed Incomplete widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tasks Completed Incomplete widget could not be added to home page.");
		}
	}  
																		
																		
		@Test(priority=12660)
		public void disableTasksCompletedIncompleteWidget() throws Exception{
		logInfo("inside disableTasksCompletedIncompleteWidget() Test");
		 
		setWidgetStatus("Tasks Completed Incomplete",false);
		go2HomePage();
																			
		boolean isWidgPresent = verifyWidgetPresent("Tasks Completed Incomplete");
		if(isWidgPresent==true){
			logInfo("Tasks Completed Incomplete widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Tasks Completed Incomplete widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Tasks Completed Incomplete widget not found in the widget page, since the widget is disabled.");
		}
	}
		
		
		
		// TASKS FUTURE Widget

		
		@Test(priority=12661)
		public void enableTasksFutureWidget() throws Exception{
		logInfo("inside enableTasksFutureWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Tasks Future");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tasks Future");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Tasks Future");
		}
																			
		if(isWidgetAdded2Homepage==true){
			logInfo("Tasks Future widget added to home page.");
			verifyWidgetForProblemsRendering("Tasks Future");
			// removeWidgetInHomepage("Tasks Future");
		} else {
			logInfo("Tasks Future widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tasks Future widget could not be added to home page.");
		}
	}  
																		
																		
		@Test(priority=12662)
		public void disableTasksFutureWidget() throws Exception{
		logInfo("inside disableTasksFutureWidget() Test");
		 
		setWidgetStatus("Tasks Future",false);
		go2HomePage();
																			
		boolean isWidgPresent = verifyWidgetPresent("Tasks Future");
		if(isWidgPresent==true){
			logInfo("Tasks Future widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Tasks Future widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Tasks Future widget not found in the widget page, since the widget is disabled.");
		}
	}
		
		
		
	// TASKS INCOMPLETE Widget

		
		@Test(priority=12663)
		public void enableTasksIncompleteWidget() throws Exception{
		logInfo("inside enableTasksIncompleteWidget() Test");
		
		go2HomePage();
		dragAndDropWidget("Tasks Incomplete");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tasks Incomplete");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Tasks Incomplete");
		}
																			
		if(isWidgetAdded2Homepage==true){
			logInfo("Tasks Incomplete widget added to home page.");
			verifyWidgetForProblemsRendering("Tasks Incomplete");
			// removeWidgetInHomepage("Tasks Incomplete");
		} else {
			logInfo("Tasks Incomplete widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tasks Incomplete widget could not be added to home page.");
		}
	}  
																		
																		
		@Test(priority=12664)
		public void disableTasksIncompleteWidget() throws Exception{
		logInfo("inside disableTasksIncompleteWidget() Test");
		 
		setWidgetStatus("Tasks Incomplete",false);
		go2HomePage();
																			
		boolean isWidgPresent = verifyWidgetPresent("Tasks Incomplete");
		if(isWidgPresent==true){
			logInfo("Tasks Incomplete widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Tasks Incomplete widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Tasks Incomplete widget not found in the widget page, since the widget is disabled.");
		}
	}		

		
		
		// TASKS NO DUE DATE Widget

		
		@Test(priority=12665)
		public void enableTasksNoDueDateWidget() throws Exception{
		logInfo("inside enableTasksNoDueDateWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Tasks No Due Date");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tasks No Due Date");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Tasks No Due Date");
		}
																					
		if(isWidgetAdded2Homepage==true){
			logInfo("Tasks No Due Date widget added to home page.");
			verifyWidgetForProblemsRendering("Tasks No Due Date");
			// removeWidgetInHomepage("Tasks No Due Date");
		} else {
			logInfo("Tasks No Due Date widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tasks No Due Date widget could not be added to home page.");
		}
	}  
																				
																				
		@Test(priority=12666)
		public void disableTasksNoDueDateWidget() throws Exception{
		logInfo("inside disableTasksNoDueDateWidget() Test");
		 
		setWidgetStatus("Tasks No Due Date",false);
		go2HomePage();
																					
		boolean isWidgPresent = verifyWidgetPresent("Tasks No Due Date");
		if(isWidgPresent==true){
			logInfo("Tasks No Due Date widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Tasks No Due Date widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Tasks No Due Date widget not found in the widget page, since the widget is disabled.");
		}
	}		
		
		
		
	// TASKS OVERDUE Widget

		
		@Test(priority=12667)
		public void enableTasksOverdueWidget() throws Exception{
		logInfo("inside enableTasksOverdueWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Tasks Overdue");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tasks Overdue");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Tasks Overdue");
		}
																					
		if(isWidgetAdded2Homepage==true){
			logInfo("Tasks Overdue widget added to home page.");
			verifyWidgetForProblemsRendering("Tasks Overdue");
			// removeWidgetInHomepage("Tasks Overdue");
		} else {
			logInfo("Tasks Overdue widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tasks Overdue widget could not be added to home page.");
		}
	}  
																				
																				
		@Test(priority=12668)
		public void disableTasksOverdueWidget() throws Exception{
		logInfo("inside disableTasksOverdueWidget() Test");
		 
		setWidgetStatus("Tasks Overdue",false);
		go2HomePage();
																					
		boolean isWidgPresent = verifyWidgetPresent("Tasks Overdue");
		if(isWidgPresent==true){
			logInfo("Tasks Overdue widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Tasks Overdue widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Tasks Overdue widget not found in the widget page, since the widget is disabled.");
		}
	}		
		
		

		// TASKS TODAY Widget

		
		@Test(priority=12669)
		public void enableTasksTodayWidget() throws Exception{
		logInfo("inside enableTasksTodayWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Tasks Today");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tasks Today");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Tasks Today");
		}
																							
		if(isWidgetAdded2Homepage==true){
			logInfo("Tasks Today widget added to home page.");
			verifyWidgetForProblemsRendering("Tasks Today");
			// removeWidgetInHomepage("Tasks Today");
		} else {
			logInfo("Tasks Today widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tasks Today widget could not be added to home page.");
		}
	}  
																						
																						
		@Test(priority=12670)
		public void disableTasksTodayWidget() throws Exception{
		logInfo("inside disableTasksTodayWidget() Test");
		 
		setWidgetStatus("Tasks Today",false);
		go2HomePage();
																							
		boolean isWidgPresent = verifyWidgetPresent("Tasks Today");
		if(isWidgPresent==true){
			logInfo("Tasks Today widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Tasks Today widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Tasks Today widget not found in the widget page, since the widget is disabled.");
		}
	}		
		
		
	
		// TICKER Widget

		
		@Test(priority=12671)
		public void enableTickerWidget() throws Exception{
		logInfo("inside enableTickerWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
		dragAndDropWidget("Ticker");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Ticker");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Ticker");
		}
																							
		if(isWidgetAdded2Homepage==true){
			logInfo("Ticker widget added to home page.");
			verifyWidgetForProblemsRendering("Ticker");
			// removeWidgetInHomepage("Ticker");
		} else {
			logInfo("Ticker widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Ticker widget could not be added to home page.");
		}
	}  
																						
																						
		@Test(priority=1261262)
		public void disableTickerWidget() throws Exception{
		logInfo("inside disableTickerWidget() Test");
		 
		setWidgetStatus("Ticker",false);
		go2HomePage();
																							
		boolean isWidgPresent = verifyWidgetPresent("Ticker");
		if(isWidgPresent==true){
			logInfo("Ticker widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Ticker widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Ticker widget not found in the widget page, since the widget is disabled.");
		}
	}		

		

	// TRENDING PHOTOS Widget

		
		@Test(priority=12673)
		public void enableTrendingPhotosWidget() throws Exception{
		logInfo("inside enableTrendingPhotosWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
	    dragAndDropWidget("Trending Photos");
	    boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Trending Photos");
	    while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Trending Photos");
		}
																									
		if(isWidgetAdded2Homepage==true){
			logInfo("Trending Photos widget added to home page.");
			verifyWidgetForProblemsRendering("Trending Photos");
			// removeWidgetInHomepage("Trending Photos");
		} else {
			logInfo("Trending Photos widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Trending Photos widget could not be added to home page.");
		}
	}  
																								
																								
		@Test(priority=12674)
		public void disableTrendingPhotosWidget() throws Exception{
			logInfo("inside disableTrendingPhotosWidget() Test");
		 
		setWidgetStatus("Trending Photos",false);
		go2HomePage();
																										
		boolean isWidgPresent = verifyWidgetPresent("Trending Photos");
		if(isWidgPresent==true){
			logInfo("Trending Photos widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Trending Photos widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Trending Photos widget not found in the widget page, since the widget is disabled.");
		}
	}		


		// TRENDING VIDEOS Widget
		
		@Test(priority=12675)
		public void enableTrendingVideosWidget() throws Exception{
		logInfo("inside enableTrendingVideosWidget() Test");
		 
		// drag and drop the widget on the home page.
		go2HomePage();
	    dragAndDropWidget("Trending Videos");
	    boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Trending Videos");
	    while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Trending Videos");
		}
																									
		if(isWidgetAdded2Homepage==true){
			logInfo("Trending Videos widget added to home page.");
			verifyWidgetForProblemsRendering("Trending Videos");
			// removeWidgetInHomepage("Trending Videos");
		} else {
			logInfo("Trending Videos widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Trending Videos widget could not be added to home page.");
		}
	}  
																								
																								
		@Test(priority=12676)
		public void disableTrendingVideosWidget() throws Exception{
			logInfo("inside disableTrendingVideosWidget() Test");
		 
		setWidgetStatus("Trending Videos",false);
		go2HomePage();
																										
		boolean isWidgPresent = verifyWidgetPresent("Trending Videos");
		if(isWidgPresent==true){
			logInfo("Trending Videos widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Trending Videos widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Trending Videos widget not found in the widget page, since the widget is disabled.");
		}
	}		

		
	// COMPANY OR CAROUSEL ADS
	
		@Test(priority=126177)
		public void VerifyCarouselAdsWidget() throws Exception{
			logInfo("inside VerifyCarouselAdsWidget() Test");
			
			go2HomePage();
			//dragAndDropWidget("Carousel Ads");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Carousel Ads");
			while(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Carousel Ads");
			}
			
			
			if(isWidgetAdded2Homepage==true){
				logInfo("Carousel Ads widget added to home page.");
				verifyWidgetForProblemsRendering("Carousel Ads");
				boolean isAdMatchFound = verifyAdsInWidget("Healthy drinks");		// 
				if(!isAdMatchFound){
					Assert.assertTrue(isAdMatchFound, "Ad not found in Carousel Ads Widgets page.");
				}
			} else {
				logInfo("Carousel Ads widget could not be added to home page.");
				Assert.assertTrue(isWidgetAdded2Homepage, "Carousel Ads widget could not be added to home page.");
			}
			
			// removeWidgetInHomepage("Carousel Ads");
		}
	
		
		@Test(priority=126178)
		public void disableCarouselAdsWidget() throws Exception{
			logInfo("inside disableCarouselAdsWidget() Test");
			 
			setWidgetStatus("Carousel Ads",false);
			go2HomePage();
					
			boolean isWidgPresent = verifyWidgetPresent("Carousel Ads");
			if(isWidgPresent==true){
				logInfo("Carousel Ads widget still present in the widget page when widget is disabled.");
					Assert.assertFalse(isWidgPresent, "Carousel Ads widget still present in the widget page when widget is disabled.");
				} else {
					logInfo("Carousel Ads widget not found in the widget page, since the widget is disabled.");
				}
			}
		
		
		// FEATURED PHOTOS
		
		@Test(priority=126179)
		public void verifyFeaturedPhotoWidget() throws Exception{
			logInfo("inside verifyFeaturedPhotoWidget() Test");
			
			// ENABLE FEATURED PHOTOS IN ADMIN WIDGET
			
			//setWidgetStatus("Featured Photos",true);
			//setWidgetStatus("Recent Activity",true);
			
			go2HomePage();			
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Featured Photos");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Featured Photos widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Featured Photos widget not found in the widget pane.");
			} else {
				dragAndDropWidget("Recent Activity");
				dragAndDropWidget("Featured Photos");
				
				boolean isProbRenderingFound = verifyWidgetForProblemsRendering("Featured Photos");
				System.out.println("isProb = " +isProbRenderingFound);
				
				if(verifyWidgetForProblemsRendering("Featured Photos")){
					Assert.assertFalse(isProbRenderingFound, "problems rendering issues in Featured Photos");
				} else {
					cm.addCommunityPhoto(photoTitle_text,visibility_text_community);		
					boolean isPostFound = cm.verifyPostIsPresent(photoTitle_text); 
					if(isPostFound==true){
						cm.setStatusForPosts(photoTitle_text, "Feature");
						
						} 
					
					go2HomePage();
					cm.verifyPostIsPresent(photoTitle_text); 
					// // removeWidgetInHomepage("Featured Photos");
				}
			}	
		}
		
		
		// FEATURED VIDEOS
		
		@Test(priority=12680)
		public void verifyFeaturedVideosWidget() throws Exception{
			logInfo("inside verifyFeaturedVideosWidget() Test");
			
			// ENABLE FEATURED VIDEOS IN ADMIN WIDGET
			
			// setWidgetStatus("Recent Activity",true);
			// setWidgetStatus("Featured Videos",true);
			
			go2HomePage();
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Featured Videos");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Featured Videos widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Featured Videos widget not found in the widget pane.");
			} else {
				dragAndDropWidget("Recent Activity");
				dragAndDropWidget("Featured Videos");
				boolean isProbRenderingFound = verifyWidgetForProblemsRendering("Featured Videos");
				System.out.println("isProb = " +isProbRenderingFound);
				
				if(verifyWidgetForProblemsRendering("Featured Videos")){
					Assert.assertFalse(isProbRenderingFound, "problems rendering issues in Featured Videos");
				} else {
					
					cm.addCommunityVideo(videoTitle_text,visibility_text_community);		
					boolean isPostFound = cm.verifyPostIsPresent(videoTitle_text);
					if(isPostFound==true){
						cm.setStatusForPosts(videoTitle_text, "Feature");
					}
					
					go2HomePage();
					cm.verifyPostIsPresent(videoTitle_text); 
					// removeWidgetInHomepage("Featured Videos");
				}
			}	
		}
		
		
		
	// ADD CONTACT FROM WIDGET
		
	@Test(priority=12681)
		public void addContactFromWidget() throws Exception{
			logInfo("inside addContactFromWidget() Test");
			
			// drag and drop the widget on the home page.
			go2HomePage();
			dragAndDropWidget("Add Contact");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Add Contact");
			while(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Add Contact");
			}
			
			if(isWidgetAdded2Homepage==true){
				logInfo("Add Contact widget added to home page.");
				verifyWidgetForProblemsRendering("Add Contact");
				closeEditWidgetsPane();
				addContactFromWidget("TEST99","CONTACT99");
				confirmationMessage("Contact is created");
				Thread.sleep(5000);
				
			} else {
				logInfo("Add Contact widget could not be added to home page.");
				Assert.assertTrue(isWidgetAdded2Homepage, "Add Contact widget could not be added to home page.");
			}
			
			// removeWidgetInHomepage("Add Contact");
		}	
		
		

		@Test(priority=12682)
		public void disableAddContactWidget() throws Exception{
			logInfo("inside disableAddContactWidget() Test");
			 
			setWidgetStatus("Add Contact",false);
			go2HomePage();
			
			boolean isWidgPresent = verifyWidgetPresent("Add Contact");
			if(isWidgPresent==true){
				logInfo("Add Contact widget still present in the widget page when widget is disabled.");
				Assert.assertFalse(isWidgPresent, "Add Contact widget still present in the widget page when widget is disabled.");
			} else {
				logInfo("Add Contact widget not found in the widget page, since the widget is disabled.");
			}
		}
		
		
	// SEARCH CONTACT FROM WIDGET
		
		@Test(priority=12683)
		public void searchContactFromWidget() throws Exception{
			logInfo("inside searchContactFromWidget() Test");
			String contacts = "TEST99" +" "+ "CONTACT99";
			
			// drag and drop the widget on the home page.
				go2HomePage();
				dragAndDropWidget("Search Contacts");
				boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Search Contacts");
				while(isWidgetAdded2Homepage==false){
					dragAndDropWidget("Search Contacts");
				}
			
				if(isWidgetAdded2Homepage==true){
					logInfo("Search Contacts widget added to home page.");
					verifyWidgetForProblemsRendering("Search Contacts");
					closeEditWidgetsPane();
					boolean isMatchFound = searchContactFromWidget("TEST99","CONTACT99");
					if(isMatchFound==true){
						// bm.deleteBusinessContact("TEST99","CONTACT99");
						bm.selectMultipleContacts(contacts, "Delete Selected");
					}
					
				} else {
					logInfo("Search Contacts widget could not be added to home page.");
					Assert.assertTrue(isWidgetAdded2Homepage, "Search Contacts widget could not be added to home page.");
				}
				
				// removeWidgetInHomepage("Search Contacts");
			}	
	
		
	
		
	//****************************************** widgets content verification ***********************************************//
		
		@Test(priority=12684)
		public void verifyCompanyNewsWidgetContent() throws Exception{
			logInfo("inside verifyCompanyNewsWidgetContent() Test");			
			 nm.companyNewsCreation(title,excerpt, ranker5,languageList,subscripList, yes,yes,yes,yes);
				
			go2HomePage();
			dragAndDropWidget("Company News");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Company News");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Company News");
			}
			
			if(verifyWidgetForProblemsRendering("Company News")){
				boolean isNewsPresent = verifyNewsPresentOnWidget(title);
				if(isNewsPresent==false){
					Assert.assertTrue(isNewsPresent, "Unable to  find company news on the company news widget.");
				}
				else{
					boolean isNewsPresentViewMore = verifyNewsInViewMore(title);
					if(isNewsPresentViewMore==false){
						Assert.assertTrue(isNewsPresentViewMore, "Unable to  find company news when click on view more.");
					}
				}
			}
		/*	tnews.news_DeleteNews();*/
				
		}  
		
		
		@Test(priority=12685)
		public void verifyAddContactWidgetContent() throws Exception{
			logInfo("inside verifyAddContactWidgetContent() Test");
			
			go2HomePage();			
			dragAndDropWidget("Add Contact");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Add Contact");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Add Contact");
			}
			
			if(!verifyWidgetForProblemsRendering("Add Contact")){
				addContactFromWidget(contactFirstName_text,contactLastName_text);
				boolean isContactAdded= bm.verifyBusinessContact(contactFirstName_text,contactLastName_text);
				if(isContactAdded==false){
					System.out.println("Business contact could not be added from the Add Contact widget.");
					Assert.assertTrue(isContactAdded, "Business contact could not be added from the Add Contact widget.");
				}
				bm.deleteBusinessContact(contactFirstName_text,contactLastName_text);
			}
		}  
		
		
		@Test(priority=12686)
		public void verifyBusinessAlertsWidgetContent() throws Exception{
			logInfo("inside verifyBusinessAlertsWidgetContent() Test");
			
			go2HomePage();
			dragAndDropWidget("Business Alerts");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Business Alerts");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Business Alerts");
			}
			
			if(!verifyWidgetForProblemsRendering("Business Alerts")){
				boolean isTaskMessageFound= verifyTaskInBusinessAlerts(taskMessage);
				if(isTaskMessageFound==false){
					System.out.println("Unable to find the task message in Business Alerts widget.");
					Assert.assertTrue(isTaskMessageFound, "Unable to find the task message in Business Alerts widget.");
				}
			}
		}
		
		
		@Test(priority=12687)
		public void disableBusinessAlertsWidget() throws Exception{
			logInfo("inside disableBusinessAlertsWidget() Test");
			 
			setWidgetStatus("Business Alerts",false);
			go2HomePage();
			
			boolean isWidgPresent = verifyWidgetPresent("Business Alerts");
			if(isWidgPresent==true){
				logInfo("Business Alerts widget still present in the widget page when widget is disabled.");
				Assert.assertFalse(isWidgPresent, "Business Alerts widget still present in the widget page when widget is disabled.");
			} else {
				logInfo("Business Alerts widget not found in the widget page, since the widget is disabled.");
			}
		}
		
		
		@Test(priority=12688)
		public void verifyCRMAlertsWidgetContent() throws Exception{
			logInfo("inside verifyCRMAlertsWidgetContent() Test");
			 
			TaskMethods tm = new TaskMethods();
			// go2EditWidgetsPage();
			//setWidgetStatus("CRM Alerts",true);
			//setWidgetStatus("Tasks Add",true);
			
			go2HomePage();
			dragAndDropWidget("CRM Alerts");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("CRM Alerts");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("CRM Alerts");
			}
			
			dragAndDropWidget("Tasks Add");
			boolean isWidgetAdded2Homepage2 = verifyWidgetsInHomepage("Tasks Add");
			if(isWidgetAdded2Homepage2==false){
				dragAndDropWidget("Tasks Add");
			}
			
			tm.addTask(todaysTask_text,"TodaysTask");
			
			
			if(!verifyWidgetForProblemsRendering("CRM Alerts")){
				boolean isTaskMessageFound= verifyTaskInCRMAlerts(taskMessage);
				if(isTaskMessageFound==false){
					System.out.println("Unable to find the task message in CRM Alerts widget.");
					Assert.assertTrue(isTaskMessageFound, "Unable to find the task message in CRM Alerts widget.");
				}
			}
		}
		
		
		@Test(priority=12689)
		public void disableCRMAlertsWidget() throws Exception{
		logInfo("inside disableCRMAlertsWidget() Test");
		 
		setWidgetStatus("CRM Alerts",false);
		go2HomePage();
											
		boolean isWidgPresent = verifyWidgetPresent("CRM Alerts");
		if(isWidgPresent==true){
			logInfo("CRM Alerts widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "CRM Alerts widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("CRM Alerts widget not found in the widget page, since the widget is disabled.");
		}
	  }	
		
		
		
		@Test(priority=12690)
		public void verifyRecentActivityWidgetContent() throws Exception{
			logInfo("inside verifyRecentActivityWidgetContent() Test");
			
			cm.addBlogPost(addPostTitle_text);
			
			// drag and drop the widget on the home page.
			go2HomePage();
			dragAndDropWidget("Recent Activity");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Recent Activity");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Recent Activity");
			}
			
			if(!verifyWidgetForProblemsRendering("Recent Activity")){
				boolean isPostFound =  cm.verifyPostIsPresent(addPostTitle_text);
				if(isPostFound==false){
					System.out.println("Unable to find the blog post in Recent Activity widget.");
					Assert.assertTrue(isPostFound, "Unable to find the blog post in Recent Activity widget.");
				}				
				cm.deleteCommunityPost(addPostTitle_text);
							
			}
		}
		
		
		// COMMUNITY STREAM WIDGET
		
		@Test(priority=12691)
		public void verifyCommunityStreamWidgetContent() throws Exception{
			logInfo("inside verifyCommunityStreamWidgetContent() Test");
			
			// setWidgetStatus("Recent Activity",true);
			// setWidgetStatus("Community Stream",true);
						
			// verify enabled widgets are present in widgets pane.
			go2HomePage();
			boolean isCSWidgetPresentInWidgetPane = verifyWidgetPresent("Community Stream");
			if(!isCSWidgetPresentInWidgetPane==true){
				System.out.println("Community Stream widget not found in the widget pane.");
				Assert.assertTrue(isCSWidgetPresentInWidgetPane, "Community Stream widget not found in the widget pane.");
			} 
			
			boolean isRAWidgetPresentInWidgetPane = verifyWidgetPresent("Recent Activity");
			if(!isRAWidgetPresentInWidgetPane==true){
				System.out.println("Recent Activity widget not found in the widget pane.");
				Assert.assertTrue(isRAWidgetPresentInWidgetPane, "Recent Activity widget not found in the widget pane.");
			} 
			
			// drag and drop the widget on the home page.
			
			//removeAllWidgetInHomepage();
			
			dragAndDropWidget("Recent Activity");
			
			boolean isRAWidgetAdded2Homepage = verifyWidgetsInHomepage("Recent Activity");
			System.out.println("isRAWidgetAdded2Homepage =" +isRAWidgetAdded2Homepage);
			if(isRAWidgetAdded2Homepage==false){
				dragAndDropWidget("Recent Activity");
			} 
			
			cm.addBlogPost(addPostTitle_text);
			// removeWidgetInHomepage("Recent Activity");
			
			dragAndDropWidget("Community Stream");
			
			boolean isCSWidgetAdded2Homepage = verifyWidgetsInHomepage("Community Stream");
			System.out.println("isCSWidgetAdded2Homepage =" +isCSWidgetAdded2Homepage);
			if(isCSWidgetAdded2Homepage==false){
				dragAndDropWidget("Community Stream");
			} 
			
		    // verifyWidgetForProblemsRendering("Community Stream")){
				
				
			boolean isPostFound =  verifyCommunityStreamWidget(addPostTitle_text);
			if(isPostFound==false){
				logInfo(addPostTitle_text + " post not found in Community Stream widget.");					
					cm.deleteCommunityPost(addPostTitle_text);
					Assert.assertTrue(isPostFound, addPostTitle_text + " post not found in Community Stream widget.");
				}			
			 cm.deleteCommunityPost(addPostTitle_text);
		}
	
		
		@Test(priority=12692)
		public void verifyEventsWidgetContent() throws Exception{
			logInfo("inside verifyEventsWidgetContent() Test");
		
			setWidgetStatus("Events",true);
			
			// Remove all widgets in the home page.
			go2HomePage();
			//removeAllWidgetInHomepage();
			
			
			// verify enabled widgets are present in widgets pane.
			go2HomePage();
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Events");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Events widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Events widget not found in the widget pane.");
			} 
			
						
			// drag and drop the widget on the home page.
					
			dragAndDropWidget("Events");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Events");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Events");
			}
			
			if(!verifyWidgetForProblemsRendering("Events")){
				closeEditWidgetsPane();
				Thread.sleep(5000);
				addEventFromWidget(newEventName_text);
				boolean isEventFound =  verifyEventInEventsWidget(newEventName_text);
				if(isEventFound==false){
					System.out.println("Unable to find the event in Events widget.");
					Assert.assertTrue(isEventFound, "Unable to find the event in Events widget.");
				}
				cal.deleteCalendarEvent("New Event",newEventName_text);
							
			}
		}
		
		
		@Test(priority=12693)
		public void verifyGroupsWidgetContent() throws Exception{
			logInfo("inside verifyGroupsWidgetContent() Test");
			
			// Enable Contact Groups widget
			setWidgetStatus("Contact Groups",true);
			
			go2HomePage();
			bm.addGroup(groupName_text);

			boolean isGroupAdded = bm.verifyGroupIsPresent(groupName_text);  
			if(isGroupAdded==false){
			  logInfo(groupName_text + " group could not be created.");
			  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
			}
			
		
			// verify enabled widgets are present in widgets pane.
			go2HomePage();
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Contact Groups");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Contact Groups widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Contact Groups widget not found in the widget pane.");
			} 
			
			// remove all widgets in home page.
			//removeAllWidgetInHomepage();
			
			
			// drag and drop the widget
			
			dragAndDropWidget("Contact Groups");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Contact Groups");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Contact Groups");
			}
					
			
			boolean isGroupFound = false;
			isGroupFound = verifyContactGroupsInWidget(groupName_text);
			if(isGroupFound==false){
				logInfo(groupName_text + " group not found inside the widget.");
				Assert.assertTrue(isGroupFound, groupName_text + " group not found inside the widget.");
			}
			
			bm.deleteGroup(groupName_text);
			confirmationMessage("Group Deleted.");
			isGroupFound = bm.verifyGroupIsPresent(groupName_text);
			System.out.println("isGroupFound =" +isGroupFound);
			// removeWidgetInHomepage("Contact Groups");
		}
		
		
		// Goal Widget
		
		@Test(priority=12694)
		public void verifyGoalWidgetContent() throws Exception{
			logInfo("inside verifyGoalWidgetContent() Test");
			
			setWidgetStatus("Goals",true);
			addNewGoal(Title_text);
			
			// verify enabled widgets are present in widgets pane.
			
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Goals");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Goals widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Goals widget not found in the widget pane.");
			} 
			
							
			// remove all widgets in home page.
				//removeAllWidgetInHomepage();
			
			// drag and drop the widget on the home page.
			dragAndDropWidget("Goals");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Goals");
			if(isWidgetAdded2Homepage==true){
				logInfo("Goals widget added to home page.");
				boolean isGoalFound = verifyGoalsInWidget(Title_text);  
				if(isGoalFound==false){
					logInfo(Title_text + " goal not found in Goals widget.");
					Assert.assertTrue(isGoalFound, Title_text + " goal not found in Goals widget.");
				}
			}
		
		}	
		
		
		
		// MY PHOTOS WIDGET
		
		
		@Test(priority=12695)
		public void verifyMyPhotoWidgetContent() throws Exception{
			logInfo("inside verifyMyPhotoWidgetContent() Test");
			
			setWidgetStatus("My Photos",true);
			pm.nav2profile();
			cm.addCommunityPhoto(photoTitle_text, visibilitySettings_text);
			// pt.profile_AddPhoto();
			
			// verify enabled widgets are present in widgets pane.
			
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("My Photos");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("My Photos widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "My Photos widget not found in the widget pane.");
			} 
			
							
			// remove all widgets in home page.
				//removeAllWidgetInHomepage();
			
			// drag and drop the widget on the home page.
			dragAndDropWidget("My Photos");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("My Photos");
			if(isWidgetAdded2Homepage==true){
				logInfo("My Photos widget added to home page.");
				
				boolean isMyPhotoFound = verifyMyPhotosInWidget(photoTitle_text);  
				if(isMyPhotoFound==false){
					logInfo(photoTitle_text + " photo not found in My Photos widget.");
					Assert.assertTrue(isMyPhotoFound, photoTitle_text + " photo not found in My Photos widget.");
				}
			}
		
		}	
	
		
		
		
		
		
}
