package com.bitrate.mvp.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by seishu on 05.05.18.
 */

public class Pair extends RealmObject implements Parcelable {

    @PrimaryKey
    private String id;
    private String baseCurrency;
    private String quoteCurrency;
    private String feeCurrency;

    public Pair() {

    }

    public String getId() {
        return id;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(baseCurrency);
        dest.writeString(quoteCurrency);
        dest.writeString(feeCurrency);
    }

    public static final Parcelable.Creator<Pair> CREATOR
            = new Parcelable.Creator<Pair>() {
        public Pair createFromParcel(Parcel in) {
            return new Pair(in);
        }

        public Pair[] newArray(int size) {
            return new Pair[size];
        }
    };

    private Pair(Parcel in) {
        id = in.readString();
        baseCurrency = in.readString();
        quoteCurrency = in.readString();
        feeCurrency = in.readString();
    }
}
