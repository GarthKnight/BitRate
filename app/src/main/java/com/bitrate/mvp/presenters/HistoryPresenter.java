package com.bitrate.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bitrate.api.Api;
import com.bitrate.mvp.managers.CandlesManager;
import com.bitrate.mvp.models.Candle;
import com.bitrate.mvp.view.HistoryView;
import com.bitrate.realm.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.bitrate.ui.fragments.BaseFragment.getStartOfCurrentDayTimestamp;


@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {

    private static final String FOR_DAYS = "D1";
    CandlesManager candlesManager = new CandlesManager();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");

    public void showData(String pairId) {
        if (!candlesManager.isEmpty()) {
            getViewState().onHistoryLoaded(candlesManager.getCandles());
        } else {
            ArrayList<Candle> candlesFromDB = DBHelper.getCandles(pairId);
            if (candlesFromDB != null && !candlesFromDB.isEmpty() && isCurrentDay(candlesFromDB.get(0).getTimeMillisValue())) {
                candlesManager.setCandles(candlesFromDB);
                getViewState().onHistoryLoaded(candlesManager.getCandles());
            } else {
                loadFromServer(pairId);
            }
        }
    }

    private boolean isCurrentDay(long timeMillis) {
        return timeMillis >= getStartOfCurrentDayTimestamp();
    }

    private void loadFromServer(final String pairId) {
        Api.get().getHistoryForPair(pairId, FOR_DAYS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ArrayList<Candle>, ArrayList<Candle>>() {
                    @Override
                    public ArrayList<Candle> apply(ArrayList<Candle> candles) throws Exception {
                        Collections.reverse(candles);
                        for (Candle candle : candles) {
                            Date d = sdf.parse(candle.getDate());
                            String formattedTime = output.format(d);
                            candle.setDate(formattedTime);
                            candle.setTimeMillisValue(d.getTime());
                            candle.setPairId(pairId);
                        }
                        return candles;
                    }
                })
                .subscribe(new Observer<ArrayList<Candle>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getViewState().onLoadingStart();
                    }

                    @Override
                    public void onNext(ArrayList<Candle> candles) {
                        DBHelper.saveCandles(candles);
                        candlesManager.setCandles(candles);
                        getViewState().onHistoryLoaded(candles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        getViewState().onLoadingEnd();
                    }
                });
    }

}
