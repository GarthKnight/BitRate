package com.bitrate.mvp.models;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by seishu on 05.05.18.
 */

public class Candle extends RealmObject {

    @SerializedName("timestamp")
    private String date;
    private double open;
    private double close;
    private double min;
    private double max;
    private String pairId;
    @PrimaryKey
    private String key = UUID.randomUUID().toString();
    private long timeMillisValue;

    public Candle() {

    }

    public long getTimeMillisValue() {
        return timeMillisValue;
    }

    public void setTimeMillisValue(long timeMillisValue) {
        this.timeMillisValue = timeMillisValue;
    }

    public String getDate() {
        return date;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }


    public String getPairId() {
        return pairId;
    }

    public void setPairId(String pairId) {
        this.pairId = pairId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
