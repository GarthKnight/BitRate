package com.bitrate.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bitrate.R;
import com.bitrate.mvp.models.Pair;
import com.bitrate.mvp.presenters.PairsListPresenter;
import com.bitrate.mvp.view.PairsListView;
import com.bitrate.ui.adapters.PairsAdapter;
import com.bitrate.ui.fragments.TickerFragment;

import java.util.ArrayList;


import butterknife.BindView;

public class StartActivity extends BaseActivity implements PairsListView {
    @InjectPresenter
    PairsListPresenter presenter;

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rvList) RecyclerView rvList;
    @BindView(R.id.progressBar)
    View progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        bindUI(this);
        initRV();
        presenter.showData();
    }


    protected void onResume() {
        super.onResume();
        setTvTitle(getString(R.string.pairs));
    }

    public void setTvTitle(String title) {
        tvTitle.setText(title);
    }

    private void initRV() {
        rvList.setLayoutManager(new LinearLayoutManager(this));
        PairsAdapter pairsAdapter = new PairsAdapter(new ArrayList<Pair>()) {
            @Override
            public void onPairClick(Pair pair) {
                showFragment(TickerFragment.create(pair), true);
            }
        };
        rvList.setAdapter(pairsAdapter);
    }

    @Override
    public void onPairsLoaded(ArrayList<Pair> pairs) {
        ((PairsAdapter) rvList.getAdapter()).addItems(pairs);
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
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}