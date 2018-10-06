package vibe.marketing.YouTube.tests;

import org.testng.annotations.Test;

import common.Priority;

@Priority(109)
public class YouTubeTest extends YoutubeMethods{

	@Test (priority =10901)
	public void youTube_uploadVideoInYouTube() throws Exception{
		 nav2YouTube();
		 addNewVideo(youtubeTitle, youtubeDesc);
		 confirmationMessage("Video was successfully uploaded");
		 nav2YouTube();
		 verifyYouTube(youtubeTitle, youtubeDesc);
		}

	@Test (priority =10902)
	public void  youTube_updateTheVideoDetails() throws Exception{
		nav2YouTube();
		verifyYouTube(youtubeTitle, youtubeDesc);
		updateyoutube(youtubeTitle, youtubeTitleUpdated);		 
	}	

	@Test (priority =10904)
	public void  youTube_deleteVideo() throws Exception{
		nav2YouTube();		
		deleteYouTubeVedio(youtubeTitle);
		verifyNotToPresentUTube(youtubeTitle);
		
		 
	}
	
	@Test (priority =10905)
	public void  youTube_deleteAllVideos() throws Exception{
		nav2YouTube();
		deleteAllYouTubeVedios();
		
		 
	}
	

}
