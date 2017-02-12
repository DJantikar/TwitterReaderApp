package com.twitter;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetReader {

	public static void main(String[] args) {
		//Hello
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("4MWHR9sMyLQZe4dNufYF2l2wX")
		  .setOAuthConsumerSecret("S7999qdmgtX7vsMu3gzHMiJRoflZSp7hdGR55sfHMrZS9D3IoE")
		  .setOAuthAccessToken("3314374297-hmrTTvdMR6QVGkDNDd288FpF2JrRHVBKQJqJqMc")
		  .setOAuthAccessTokenSecret("vkGNtIC1lxoPwDAC16xWG1pjqUUdlwSu93v9KiyUJJpGe");
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter twitter = tf.getInstance();
		try {
			List<Status> status = twitter.getHomeTimeline().subList(0, 9);
			for(Status s : status){
				System.out.println(s.getUser().getName() + " -- "+s.getUser().getScreenName()
						+ s.getUser().getMiniProfileImageURL() + " -- "+s.getText()+" -- "+ s.getRetweetCount());
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
