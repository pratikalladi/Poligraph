package com.github.pratikalladi;

import twitter4j.Status;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private final int sentimentValue;
    private final Status status;
    private String sentimentString;

    public Result(Status status, int sentimentValue) {
        this.status = status;
        this.sentimentValue = sentimentValue;
        Map<Integer, String> sentimentMap = new HashMap<Integer, String>() {{
            put(0, "Very Negative");
            put(1, "Negative");
            put(2, "Neutral");
            put(3, "Positive");
            put(4, "Very Positive");
        }};
        this.sentimentString = sentimentMap.get(sentimentValue);
    }

    public int getSentimentValue() {
        return sentimentValue;
    }

    public Status getStatus() {
        return status;
    }

    public String getSentimentString() {
        return sentimentString;
    }
}
