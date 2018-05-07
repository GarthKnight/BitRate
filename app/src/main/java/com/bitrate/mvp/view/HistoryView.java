package com.bitrate.mvp.view;


import com.bitrate.mvp.models.Candle;

import java.util.ArrayList;


public interface HistoryView extends BaseView {

    void onHistoryLoaded(ArrayList<Candle> candles);

}
