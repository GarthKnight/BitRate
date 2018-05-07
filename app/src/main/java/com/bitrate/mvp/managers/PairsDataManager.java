package com.bitrate.mvp.managers;

import com.bitrate.mvp.models.Pair;

import java.util.ArrayList;


/**
 * Created by seishu on 05.05.18.
 */

public class PairsDataManager {

    private ArrayList<Pair> pairs = new ArrayList<>();

    public boolean isEmpty() {
        return pairs == null || pairs.isEmpty();
    }

    public void setPairs(ArrayList<Pair> pairs) {
        this.pairs = pairs;
    }

    public ArrayList<Pair> getPairs() {
        return pairs;
    }
}
