package org.pratik;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;

public class Poligraph {

    Twitter twitter;

    public Poligraph(String cKey, String cKeySecret, String aToken, String aTokenSecret)
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(cKey)
                .setOAuthConsumerSecret(cKeySecret)
                .setOAuthAccessToken(aToken)
                .setOAuthAccessTokenSecret(aTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }


    // searchWord can be user id, place etc
    // date should be in the format yyyy-MM-dd
    public ArrayList<Status> getTweetsInRange(String startDate, String endDate, String searchWord, int count) throws TwitterException {
        Query query = new Query(searchWord);
        query.setCount(count);
        query.setSince(startDate);
        query.setUntil(endDate);
        return twitterQuery(query);
    }

    public ArrayList<Status> twitterQuery(Query query) throws TwitterException {
        QueryResult result = twitter.search(query);
        return new ArrayList<>(result.getTweets());
    }

    public static void main(String[] args) throws TwitterException {

        String cKey = "FZR30B3lQbMVoNEZBspeoJKPH";
        String cKeySecret = "So12YRUjpr9qM9wfR921toN1yAmdvlJ2LkWIVt0bNuJF4a89Q6";
        String aToken = "1325292306413576194-URa206WZicJkTcCN92DaB2LDx78Pjr";
        String aTokenSecret = "1MLcfJUZxumquQtIDpbPsDWU8jdPr6OFbMurFcN9Mldzk";


        Query query = new Query("#iPhone");
        query.setCount(100);
        Poligraph p = new Poligraph(cKey, cKeySecret, aToken, aTokenSecret);

        ArrayList<Status> result = p.getTweetsInRange("2020-11-01", "2020-11-07", "#Biden", 100);



        for (Status status : result)
            System.out.println("Status@\t" + status.getUser().getScreenName() + "\t:\t" + status.getText());

    }
}