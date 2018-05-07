package com.bitrate.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bitrate.R;
import com.bitrate.mvp.models.Candle;
import com.bitrate.mvp.presenters.HistoryPresenter;
import com.bitrate.mvp.view.HistoryView;
import com.bitrate.ui.activities.StartActivity;
import com.bitrate.ui.adapters.CandlesAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by seishu on 05.05.2018.
 */

public class HistoryFragment extends BaseFragment implements HistoryView {

    private static final String PAIR_ID = "PairId";

    private String pairId;

    @InjectPresenter
    HistoryPresenter presenter;

    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.progressBar)
    View progressBar;

    public static HistoryFragment create(String pairId) {
        Bundle args = new Bundle();
        args.putString(PAIR_ID, pairId);
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pairId = getArguments().getString(PAIR_ID);
    }

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.framgent_histiory, container, false);
        bindUI(v);
        return v;
    }

    @Override
    public void init() {
        String title = pairId + " History";
        ((StartActivity) getActivity()).setTvTitle(title);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(new CandlesAdapter(new ArrayList<Candle>()));
        presenter.showData(pairId);
    }

    @Override
    public void onLoadingStart() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadingEnd() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(Throwable error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHistoryLoaded(ArrayList<Candle> candles) {
        ((CandlesAdapter) rvList.getAdapter()).addItems(candles);
    }
}
