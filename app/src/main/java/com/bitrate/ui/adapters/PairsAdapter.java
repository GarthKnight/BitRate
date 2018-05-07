package com.bitrate.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitrate.R;
import com.bitrate.mvp.models.Pair;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seishu on 05.05.2018.
 */

public class PairsAdapter extends RecyclerView.Adapter<PairsAdapter.ParesVH> {

    private ArrayList<Pair> pairs;

    public PairsAdapter(ArrayList<Pair> pairs) {
        this.pairs = pairs;
    }

    @Override
    public ParesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pair, parent, false);
        return new ParesVH(v);
    }

    @Override
    public void onBindViewHolder(ParesVH holder, final int position) {
        String value = pairs.get(position).getId();
        holder.tvPareName.setText(value);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPairClick(pairs.get(position));
            }
        });
    }

    public void onPairClick(Pair pair) {
    }

    public void addItems(ArrayList<Pair> newPairs) {
        pairs.clear();
        pairs.addAll(newPairs);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return pairs.size();
    }

    public class ParesVH extends RecyclerView.ViewHolder {
        @BindView(R.id.tvPareName)
        TextView tvPareName;

        public ParesVH(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
