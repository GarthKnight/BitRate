package com.bitrate.api;


import com.bitrate.mvp.models.Candle;
import com.bitrate.mvp.models.Coin;
import com.bitrate.mvp.models.Pair;
import com.bitrate.mvp.models.Ticker;

import java.util.ArrayList;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by seishu on 05.05.18.
 */

public interface HitBTCApi {

    @GET("api/2/public/currency")
    Observable<ArrayList<Coin>> getCoins();

    @GET("api/2/public/currency/{currency}")
    Observable<Coin> getCoinById(@Path("currency") String id);

    @GET("/api/2/public/symbol")
    Observable<ArrayList<Pair>> getPairs();

    @GET("/api/2/public/symbol/{symbol}")
    Observable<Pair> getPairById(@Path("symbol") String pairId);

    @GET("/api/2/public/ticker")
    Observable<ArrayList<Ticker>> getTickers();

    @GET("/api/2/public/ticker/{symbol}")
    Observable<Ticker> getTickerById(@Path("symbol") String tickerId);

    @GET("api/2/public/candles/{pair}")
    Observable<ArrayList<Candle>> getHistoryForPair(@Path("pair") String pairId, @Query("period") String period);
}
