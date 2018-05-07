package com.bitrate.mvp.managers;


import com.bitrate.mvp.models.TickerFullModel;

/**
 * Created by seishu on 05.05.18.
 */

public class TickerManager {


    private TickerFullModel tickerFullModel = new TickerFullModel();

    public boolean isEmpty() {
        return tickerFullModel != null || tickerFullModel.getTicker() == null
                || tickerFullModel.getBaseCurrency() == null
                || tickerFullModel.getQuoteCurrency() == null;
    }

    public void setTickerFullModel(TickerFullModel tickerFullModel) {
        this.tickerFullModel = tickerFullModel;
    }

    public TickerFullModel getTickerFullModel() {
        return tickerFullModel;
    }
}
