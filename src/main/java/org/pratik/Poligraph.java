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

    
}