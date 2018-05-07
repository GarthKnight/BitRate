package com.bitrate.realm;

import com.bitrate.mvp.models.Candle;
import com.bitrate.mvp.models.Pair;
import com.bitrate.mvp.models.TickerFullModel;

import java.util.ArrayList;


import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class DBHelper {

    private static final String TAG = "Realm DBHelper";


    public static void saveTicker(TickerFullModel ticker) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(ticker);
            realm.commitTransaction();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            realm.close();
        }
    }

    public static TickerFullModel getTickerById(String id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(TickerFullModel.class).equalTo("id", id).findFirst();
    }

    public static ArrayList<Pair> getPairs() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Pair> pairRealmResults =
                realm.where(Pair.class).findAll();
        return mapRealmResultToArray(pairRealmResults);
    }

    public static void savePairs(ArrayList<Pair> pairs) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(mapArrayListToRealm(pairs));
            realm.commitTransaction();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            realm.close();
        }
    }

    public static void saveCandles(ArrayList<Candle> candles) {
        Realm realm = Realm.getDefaultInstance();
        if (candles != null && !candles.isEmpty()) {
            final String pairId = candles.get(0).getPairId();
            try {
                realm.beginTransaction();
                RealmResults<Candle> result = realm.where(Candle.class).equalTo("pairId", pairId).findAll();
                result.deleteAllFromRealm();
                realm.commitTransaction();
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(mapArrayListToRealm(candles));
                realm.commitTransaction();
            } catch (IllegalStateException e) {
                e.printStackTrace();
                realm.close();
            }
        }
    }

    public static ArrayList<Candle> getCandles(String pairId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Candle> pairRealmResults =
                realm.where(Candle.class).findAll();
        return new ArrayList<>(realm.copyFromRealm(pairRealmResults));

    }


    private static <T extends RealmObject> RealmList<T> mapArrayListToRealm(ArrayList<T> objects) {
        RealmList<T> result = new RealmList<>();
        result.addAll(objects);
        return result;
    }

    private static <T extends RealmObject> ArrayList<T> mapRealmResultToArray(RealmResults<T> objects) {
        return new ArrayList<>(objects);
    }
}
