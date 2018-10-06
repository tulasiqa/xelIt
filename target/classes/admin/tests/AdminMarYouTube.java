package vibe.admin.tests;

import org.testng.annotations.Test;
import common.Priority;
import vibe.marketing.YouTube.tests.YoutubeMethods;

@Priority(9)
public class AdminMarYouTube extends YoutubeMethods{

	@Test (priority =901)
	public void youTubeVideoUpload() throws Exception{
		 nav2YouTube();
		 addNewVideo(youtubeTitle, youtubeDesc);
		 confirmationMessage("Video was successfully uploaded");
		 nav2YouTube();
		 verifyYouTube(youtubeTitle, youtubeDesc);
		}

	@Test (priority =902)
	public void  youTubevideoUpdateDetails() throws Exception{
		nav2YouTube();
		verifyYouTube(youtubeTitle, youtubeDesc);
		updateyoutube(youtubeTitle, youtubeTitleUpdated);		 
	}	

	

}
