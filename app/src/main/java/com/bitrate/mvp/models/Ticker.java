package com.bitrate.mvp.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by seishu on 05.05.18.
 */

public class Ticker extends RealmObject {


    @SerializedName("last")
    private double price;
    private double bid;
    private double ask;
    private double open;
    private double low;
    private double high;
    @SerializedName("timestamp")
    private String date;
    @PrimaryKey
    @SerializedName("symbol")
    private String name;


    public double getBestAskPrice() {
        return ask;
    }

    public double getBestBidPrice() {
        return bid;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLastPrice24Ago() {
        return open;
    }

    public double getLowPrice24Ago() {
        return low;
    }

    public double getHighPrice24Ago() {
        return high;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }


}
