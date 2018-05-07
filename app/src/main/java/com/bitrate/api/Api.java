package com.bitrate.api;

import com.bitrate.mvp.models.Candle;
import com.bitrate.mvp.models.Coin;
import com.bitrate.mvp.models.Pair;
import com.bitrate.mvp.models.Ticker;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by seishu on 05.05.18.
 */

public class Api {
    private static final Api ourInstance = new Api();
    private Retrofit retrofit;
    private HitBTCApi projectApi;
    private String url = "https://api.hitbtc.com";

    public static Api get() {
        return ourInstance;
    }

    private Api() {
        initRetrofit();
    }

    private void initRetrofit() {
        Gson gson = new Gson();

        HttpLoggingInterceptor bodyInterceptor = new HttpLoggingInterceptor();
        bodyInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        HttpLoggingInterceptor headerInterceptor = new HttpLoggingInterceptor();
        headerInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);


        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(bodyInterceptor)
                .addInterceptor(headerInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        projectApi = retrofit.create(HitBTCApi.class);
    }

    public Observable<ArrayList<Pair>> getPairs(){
        return projectApi.getPairs();
    }

    public  Observable<Ticker> getTickerById(String pairId){
        return projectApi.getTickerById(pairId);
    }

    public  Observable<Coin> getCoinById(String pairId){
        return projectApi.getCoinById(pairId);
    }

    public Observable<ArrayList<Candle>> getHistoryForPair(String pairId, String period){
        return projectApi.getHistoryForPair(pairId, period);
    }
}
