package com.sky.dongqiudi.dongqiudi.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpActivity<M> extends BaseActivity implements ICommonView{

    protected CommonPresenter mPresenter;
    protected M mModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mPresenter = getPresenter();
        mModel = getModel();
        if (mPresenter!=null)mPresenter.attach(this,(ICommonModel)mModel);
        initView();
        initData();

    }

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    protected abstract M getModel();

    protected abstract void initData();

    protected abstract void initView();

    public CommonPresenter getPresenter() {
        return new CommonPresenter();
    }
    public void netErrorToast(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
