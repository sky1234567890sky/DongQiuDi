package com.sky.dongqiudi.dongqiudi.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.sky.dongqiudi.dongqiudi.utils.NetworkUtils;

public class NetStatusBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int state = NetworkUtils.getNetWorkState(context);
            if (mNetStatusListener != null) mNetStatusListener.onNetChanged(state);
        }
    }

    private NetStatusListener mNetStatusListener;

    public void setNetStatusListener(NetStatusListener pNetStatusListener) {
        mNetStatusListener = pNetStatusListener;
    }

    public interface NetStatusListener{
        void onNetChanged(int state);
    }
}
