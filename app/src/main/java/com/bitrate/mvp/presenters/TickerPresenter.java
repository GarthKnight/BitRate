package com.bitrate.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bitrate.api.Api;
import com.bitrate.mvp.managers.TickerManager;
import com.bitrate.mvp.models.Coin;
import com.bitrate.mvp.models.Pair;
import com.bitrate.mvp.models.Ticker;
import com.bitrate.mvp.models.TickerFullModel;
import com.bitrate.mvp.view.TickerView;
import com.bitrate.realm.DBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.bitrate.ui.fragments.BaseFragment.getStartOfCurrentDayTimestamp;


/**
 * Created by seishu on 05.05.18.
 */
@InjectViewState
public class TickerPresenter extends MvpPresenter<TickerView> {

    TickerManager tickerManager = new TickerManager();

    public void showTickerData(Pair pair) {
        if (!tickerManager.isEmpty()) {
            getViewState().onTickerLoaded(tickerManager.getTickerFullModel());
        } else {
            TickerFullModel model = DBHelper.getTickerById(pair.getId());
            if (model != null && isCurrentDay(model.getTicker().getDate())) {
                tickerManager.setTickerFullModel(model);
                getViewState().onTickerLoaded(model);
            } else {
                loadTickerData(pair);
            }
        }
    }

    private boolean isCurrentDay(String date) {
        boolean result = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date tickerDate = sdf.parse(date);
            result = tickerDate.getTime() >= getStartOfCurrentDayTimestamp();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    private void loadTickerData(final Pair pair) {
        Api.get().getTickerById(pair.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Ticker, ObservableSource<Coin>>() {
                    @Override
                    public ObservableSource<Coin> apply(Ticker ticker) throws Exception {
                        tickerManager.getTickerFullModel().setTicker(ticker);
                        return Api.get()
                                .getCoinById(pair.getBaseCurrency())
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })

                .flatMap(new Function<Coin, ObservableSource<Coin>>() {
                    @Override
                    public ObservableSource<Coin> apply(Coin coin) throws Exception {
                        tickerManager.getTickerFullModel().setBaseCurrency(coin);
                        return Api.get()
                                .getCoinById(pair.getQuoteCurrency())
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })

                .subscribe(new Observer<Coin>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getViewState().onLoadingStart();
                    }

                    @Override
                    public void onNext(Coin coin) {
                        tickerManager.getTickerFullModel().setQuoteCurrency(coin);
                        DBHelper.saveTicker(tickerManager.getTickerFullModel());
                        getViewState().onTickerLoaded(tickerManager.getTickerFullModel());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e);
                    }

                    @Override
                    public void onComplete() {
                        getViewState().onLoadingEnd();
                    }
                });
    }
}
