package com.bitrate.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bitrate.api.Api;
import com.bitrate.mvp.managers.PairsDataManager;
import com.bitrate.mvp.models.Pair;
import com.bitrate.mvp.view.PairsListView;
import com.bitrate.realm.DBHelper;

import java.util.ArrayList;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by seishu on 05.05.18.
 */
@InjectViewState
public class PairsListPresenter extends MvpPresenter<PairsListView> {

    PairsDataManager dataManager = new PairsDataManager();

    public void showData() {
        if (!dataManager.isEmpty()) {
            getViewState().onPairsLoaded(dataManager.getPairs());
        } else {
            ArrayList<Pair> pairsFromDb = DBHelper.getPairs();
            if (pairsFromDb != null && !pairsFromDb.isEmpty()) {
                dataManager.setPairs(pairsFromDb);
                getViewState().onPairsLoaded(dataManager.getPairs());
            } else {
                loadFromServer();
            }
        }
    }

    private void loadFromServer() {

        Api.get().getPairs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Pair>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getViewState().onLoadingStart();
                    }

                    @Override
                    public void onNext(ArrayList<Pair> pairs) {
                        dataManager.setPairs(pairs);
                        DBHelper.savePairs(pairs);
                        getViewState().onPairsLoaded(pairs);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getViewState().onLoadingEnd();
                    }
                });

    }
}
