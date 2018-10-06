package vibe.teardown.tests;

import org.testng.annotations.Test;

import common.Priority;

import vibe.marketing.YouTube.tests.YoutubeMethods;

@Priority(909)
public class AdminTearDownMarkYouTube extends YoutubeMethods{		

	@Test (priority =90901)
	public void  deleteYoutube() throws Exception{
		nav2YouTube();		
		deleteYouTubeVedio(youtubeTitleUpdated);
		verifyNotToPresentUTube(youtubeTitleUpdated);
	}	
	
	//@Test (priority =90903)
	public void  deleteAllYouTubes() throws Exception{
		nav2YouTube();
		deleteAllYouTubeVedios();		
		 
	}
	

}
