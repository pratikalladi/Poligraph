package org.pratik;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import java.util.ArrayList;
import java.time.ZonedDateTime;

public class Poligraph {
    Twitter twitter = TwitterFactory.getSingleton();


    public ArrayList<String> getTweetsInRange(ZonedDateTime start, ZonedDateTime end)
    {
      twitter.search()
    }

    public static void main(String[] args) throws TwitterException
     {


         ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true
                 .setOAuthConsumerKey("consumer key")
                 .setOAuthConsumerSecret("consumer secret")
                 .setOAuthAccessToken("access token")
                 .setOAuthAccessTokenSecret("access token secret")
         TwitterFactory tf = new TwitterFactory(cb.build());
         Twitter twitter = tf.getInstance();
     Query query = new Query("#google");

         QueryResult result = twitter.search(query);

         for (Status status: result.getTweets())
           System.out.println("Status@\t" + status.getUser().getScreenName() + "\t:\t" + status.getText());

        }
}