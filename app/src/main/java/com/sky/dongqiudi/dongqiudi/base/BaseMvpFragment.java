package com.sky.dongqiudi.dongqiudi.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpFragment<M> extends BaseFragment implements ICommonView{

    private Unbinder mBind;
    protected M mModel;
    protected CommonPresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(getLayoutId(), null);
        mBind = ButterKnife.bind(this, inflate);
        initView();
        mPresenter = getPresenter();
        mModel =  getModel();
        if (mPresenter!=null && mModel!=null)mPresenter.attach(this,(ICommonModel)mModel);
        initData();
        return inflate;
    }

    protected abstract void initData();

    protected abstract CommonPresenter getPresenter();

    protected abstract M getModel();

    public CommonPresenter getmPresenter() {
        return new CommonPresenter();
    }
    protected abstract void initView();
    protected abstract int getLayoutId();
    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        mPresenter.detach();
    }

    public int getLoadType(Object[] t){
        return  t != null && t.length>1 ? (int) t[1] : 0;
    }
}


