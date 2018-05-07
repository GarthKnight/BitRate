package com.bitrate.mvp.managers;

import com.bitrate.mvp.models.Candle;

import java.util.ArrayList;




public class CandlesManager {

    private ArrayList<Candle> candles = new ArrayList<>();

    public boolean isEmpty() {
        return candles == null || candles.isEmpty();
    }

    public void setCandles(ArrayList<Candle> candles) {
        this.candles = candles;
    }

    public ArrayList<Candle> getCandles() {
        return candles;
    }
}
