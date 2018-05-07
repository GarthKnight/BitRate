package com.bitrate.mvp.view;


import com.bitrate.mvp.models.TickerFullModel;

/**
 * Created by seishu on 05.05.18.
 */

public interface TickerView extends BaseView {

    void onTickerLoaded(TickerFullModel tickerFullModel);
}
