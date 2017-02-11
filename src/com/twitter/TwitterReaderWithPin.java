package com.twitter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterReaderWithPin {
    private final static String CONSUMER_KEY = "PowlRtV7YqgpJNCEnr7l5FBaD";
    private final static String CONSUMER_KEY_SECRET = "0mPJj0QWnMo83sSVQDRGezAXyszmgzZrPUTpf2wWpimy5yrSVI";

    public void start() throws TwitterException, IOException {

 Twitter twitter = new TwitterFactory().getInstance();
 twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
 RequestToken requestToken = twitter.getOAuthRequestToken();
 System.out.println("Authorization URL: \n"
  + requestToken.getAuthorizationURL());

 AccessToken accessToken = null;

 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 while (null == accessToken) {
     try {
  System.out.print("Input PIN here: ");
  String pin = br.readLine();

  accessToken = twitter.getOAuthAccessToken(requestToken, pin);

     } catch (TwitterException te) {

  System.out.println("Failed to get access token, caused by: "
   + te.getMessage());

  System.out.println("Retry input PIN");

     }
 }

 System.out.println("Access Token: " + accessToken.getToken());
 System.out.println("Access Token Secret: "
  + accessToken.getTokenSecret());
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

    public static void main(String[] args) throws Exception {
 new TwitterReaderWithPin().start();// run the Twitter client
    }
}