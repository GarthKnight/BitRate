package com.bitrate.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitrate.R;
import com.bitrate.mvp.models.Candle;
import com.bitrate.ui.fragments.BaseFragment;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

public class CandlesAdapter extends RecyclerView.Adapter<CandlesAdapter.CandlesVH> {

    private ArrayList<Candle> candles;

    public CandlesAdapter(ArrayList<Candle> candles) {
        this.candles = candles;
    }

    @Override
    public CandlesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candle, parent, false);
        return new CandlesVH(v);
    }

    @Override
    public void onBindViewHolder(CandlesVH holder, final int position) {
        Candle candle = candles.get(position);
        holder.tvDate.setText(candle.getDate());
        holder.tvOpenValue.setText(BaseFragment.formatPrice(candle.getOpen()));
        holder.tvCloseValue.setText(BaseFragment.formatPrice(candle.getClose()));
    }

    public void addItems(ArrayList<Candle> newPairs) {
        candles.clear();
        candles.addAll(newPairs);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return candles.size();
    }

    public class CandlesVH extends RecyclerView.ViewHolder {

        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvOpenValue)
        TextView tvOpenValue;
        @BindView(R.id.tvCloseValue)
        TextView tvCloseValue;

        public CandlesVH(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
