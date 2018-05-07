package com.bitrate.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface BaseView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void onLoadingStart();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void onLoadingEnd();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onError(Throwable error);

}
