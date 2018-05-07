package com.bitrate.ui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.MvpFragment;
import com.bitrate.ui.activities.BaseActivity;

import java.math.BigDecimal;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by seishu on 06.03.2017.
 */

public class BaseFragment extends MvpFragment {

    private Unbinder unbinder;

    public void bindUI(View v) {
        unbinder = ButterKnife.bind(this, v);
        init();
    }

    public void init() {

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }


    public void showFragment(Fragment fragment, boolean addToBack) {
        ((BaseActivity) getActivity()).showFragment(fragment, addToBack);
    }

    public void log(String log) {
        Log.d(this.getClass().getName(), log);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public static String formatPrice(double value) {
        return new BigDecimal(Double.toString(value)).toPlainString();
    }

    public static long getStartOfCurrentDayTimestamp() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}
