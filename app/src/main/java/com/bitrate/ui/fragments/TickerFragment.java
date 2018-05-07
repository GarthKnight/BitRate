package com.bitrate.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bitrate.R;
import com.bitrate.mvp.models.Pair;
import com.bitrate.mvp.models.TickerFullModel;
import com.bitrate.mvp.presenters.TickerPresenter;
import com.bitrate.mvp.view.TickerView;
import com.bitrate.ui.activities.StartActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by seishu on 05.05.2018.
 */

public class TickerFragment extends BaseFragment implements TickerView {

    private static final String PAIR = "pair";
    @InjectPresenter
    TickerPresenter presenter;


    @BindView(R.id.tvTickerNameValue)
    TextView tvTickerNameValue;
    @BindView(R.id.tvAskValue)
    TextView tvAskValue;
    @BindView(R.id.tvBidValue)
    TextView tvBidValue;
    @BindView(R.id.tvLastValue)
    TextView tvLastValue;
    @BindView(R.id.tvOpenValue)
    TextView tvOpenValue;
    @BindView(R.id.tvLowValue)
    TextView tvLowValue;
    @BindView(R.id.tvHighValue)
    TextView tvHighValue;

    private Pair pair;

    public static TickerFragment create(Pair pair) {
        Bundle args = new Bundle();
        args.putParcelable(PAIR, pair);
        TickerFragment fragment = new TickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ticker, container, false);
        bindUI(v);
        return v;
    }

    @Override
    public void init() {
        pair = getArguments().getParcelable(PAIR);
        ((StartActivity) getActivity()).setTvTitle(pair.getId());
        presenter.showTickerData(pair);
    }

    @OnClick(R.id.btnHistory)
    public void onHistoryClick(View v) {
        showFragment(HistoryFragment.create(pair.getId()), true);
    }

    @Override
    public void onTickerLoaded(TickerFullModel tickerFullModel) {

        String tickerName = tickerFullModel.getBaseCurrency().getFullName() +
                " - " + tickerFullModel.getQuoteCurrency().getFullName();
        tvTickerNameValue.setText(tickerName);
        tvAskValue.setText(formatPrice(tickerFullModel.getTicker().getBestAskPrice()));
        tvBidValue.setText(formatPrice(tickerFullModel.getTicker().getBestBidPrice()));
        tvLastValue.setText(formatPrice(tickerFullModel.getTicker().getPrice()));
        tvOpenValue.setText(formatPrice(tickerFullModel.getTicker().getLastPrice24Ago()));
        tvLowValue.setText(formatPrice(tickerFullModel.getTicker().getLowPrice24Ago()));
        tvHighValue.setText(formatPrice(tickerFullModel.getTicker().getHighPrice24Ago()));
    }

    @Override
    public void onLoadingStart() {
        ((StartActivity) getActivity()).onLoadingStart();
    }

    @Override
    public void onLoadingEnd() {
        ((StartActivity) getActivity()).onLoadingEnd();
    }

    @Override
    public void onError(Throwable error) {
        ((StartActivity) getActivity()).onError(error);
    }
}
