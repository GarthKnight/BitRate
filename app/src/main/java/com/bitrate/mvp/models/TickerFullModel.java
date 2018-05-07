package com.bitrate.mvp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by seishu on 05.05.18.
 */

public class TickerFullModel extends RealmObject {

    private Ticker ticker;
    private Coin baseCurrency;
    private Coin quoteCurrency;
    @PrimaryKey
    private String id = "";

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        id = ticker.getName();
        this.ticker = ticker;
    }

    public Coin getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Coin baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Coin getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(Coin quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
