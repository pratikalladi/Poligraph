package org.pratik;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Date;

public class Poligraph {
    Twitter twitter = TwitterFactory.getSingleton();


    // searchWord can be user id, place etc
    // date should be in the format yyyy-MM-dd
    public ArrayList<Status> getTweetsInRange(String startDate, String endDate, String searchWord) throws TwitterException {
        Query query = new Query(searchWord);
        query.setSince(startDate);
        query.setUntil(endDate);
        return twitterQuery(query);
    }

    public ArrayList<Status> twitterQuery(Query query) throws TwitterException {
        QueryResult result = twitter.search(query);
        return new ArrayList<>(result.getTweets());
    }

    public ArrayList<Status> getUserTweets(String user) throws TwitterException {
        ResponseList<Status> result = twitter.getUserTimeline(user);
        return new ArrayList<>(result);
    }

    public ArrayList<Status> getUserTweetsInRange(String user, Date startDate, Date endDate) throws TwitterException {
        ArrayList<Status> statuses = new ArrayList<>();
        Date createdAt;
        for (Status status : getUserTweets(user)) {
            createdAt = status.getCreatedAt();
            if (createdAt.after(startDate) && createdAt.before(endDate)) {
                statuses.add(status);
            }
        }
        return statuses;
    }

    public ArrayList<Status> getUserTweetsContainsWord(String user, String word) throws TwitterException {
        ArrayList<Status> statuses = new ArrayList<>();
        for (Status status : getUserTweets(user)) {
            if (status.getText().contains(word)) {
                statuses.add(status);
            }
        }
        return statuses;
    }

    public ArrayList<Status> getUserTweetsContainsStringInRange(String user, String word, Date startDate, Date endDate) throws TwitterException {
        ArrayList<Status> statuses = new ArrayList<>();
        for (Status status : getUserTweetsInRange(user,startDate,endDate)) {
            if (status.getText().contains(word)) {
                statuses.add(status);
            }
        }
        return statuses;
    }

    public Result analyzeSentiment(Status status) {
        TwitterSentimentAnalysis sentimentAnalysis = new TwitterSentimentAnalysis();
        int sentimentValue = sentimentAnalysis.analyzeTweet(status.getText());
        return new Result(status, sentimentValue);
    }

    public ArrayList<Result> analyzeSentiment(ArrayList<Status> statuses) {
        ArrayList<Result> results = new ArrayList<>();
        for (Status status : statuses) {
            results.add(analyzeSentiment(status));

        }
        return results;
    }

    public static void main(String[] args) throws TwitterException {


        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("your consumer key")
                .setOAuthConsumerSecret("your consumer secret")
                .setOAuthAccessToken("your access token")
                .setOAuthAccessTokenSecret("your access token secret");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Query query = new Query("#google");

        QueryResult result = twitter.search(query);

        for (Status status : result.getTweets())
            System.out.println("Status@\t" + status.getUser().getScreenName() + "\t:\t" + status.getText());

    }
}