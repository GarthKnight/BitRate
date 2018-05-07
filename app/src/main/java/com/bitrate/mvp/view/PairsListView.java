package com.bitrate.mvp.view;

import com.bitrate.mvp.models.Pair;

import java.util.ArrayList;


/**
 * Created by seishu on 05.05.18.
 */

public interface PairsListView extends BaseView {

    void onPairsLoaded(ArrayList<Pair> pairs);
}
