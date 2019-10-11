package com.sky.dongqiudi.dongqiudi.base;

import android.app.Application;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sky.dongqiudi.dongqiudi.R;
import com.sky.dongqiudi.dongqiudi.broadcast.NetStatusBroadCast;
import com.sky.dongqiudi.dongqiudi.utils.LoadingDialogWithContent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.scwang.smartrefresh.layout.util.DensityUtil.px2dp;
import static com.sky.dongqiudi.dongqiudi.utils.NetworkUtils.NETWORK_MOBILE;
import static com.sky.dongqiudi.dongqiudi.utils.NetworkUtils.NETWORK_NONE;
import static com.sky.dongqiudi.dongqiudi.utils.NetworkUtils.NETWORK_WIFI;

public class BaseActivity extends AppCompatActivity implements NetStatusBroadCast.NetStatusListener {

    private BaseApp mApplication;
    private LinearLayoutManager mManager;
    private NetStatusBroadCast mNetStatusBroadCast;
    private LoadingDialogWithContent mDialog;
    private Unbinder mBind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = ButterKnife.bind(this);
        mApplication = (BaseApp) getApplication();
        mDialog = new LoadingDialogWithContent(this, getString(R.string.loading));
//        setContentView(getLayoutId());
        initView();
        initData();
    }
    protected void initData() {

    }

    protected void initView() {

    }

    public void showLoadingDialog(){
        if (!mDialog.isShowing())mDialog.show();
    }public void hideLoadingDialog(){
        if (mDialog.isShowing())mDialog.hide();
    }



    public void initRecycleView(RecyclerView recyclerView, RefreshLayout refreshLayout) {
        mManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mManager);
        if (refreshLayout != null) {
            refreshLayout.setHeaderHeight(px2dp(120));
            refreshLayout.setFooterHeight(px2dp(100));
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    refresh();
                }
            });
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    loadMore();
                }
            });
        }
    }

    public void registerNetWorkStatus() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetStatusBroadCast = new NetStatusBroadCast();
        mNetStatusBroadCast.setNetStatusListener(this);
        registerReceiver(mNetStatusBroadCast, filter);
    }

    @Override
    public void onNetChanged(int state) {
        if (state == NETWORK_MOBILE || state == NETWORK_WIFI) onNetConnected();
        else if (state == NETWORK_NONE) onNetDisConnected();
    }

    private void onNetDisConnected() {

    }

    private void onNetConnected() {

    }

    protected int getLoadType(Object[] t) {
        return (int) ((Object[]) t[1])[0];
    }

    public void showToast(Object content) {
        Toast.makeText(getApplicationContext(), content.toString(), Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    private void loadMore() {

    }

    private void refresh() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        if (mDialog!=null)
            if (mDialog.isShowing())mDialog.cancel();
    }
}
