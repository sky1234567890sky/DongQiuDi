package com.sky.dongqiudi.dongqiudi.base;

public interface ICommonView<T> {
    void onError(int whichApi,Throwable e);
    void onResponse(int whichApi , T...ts);
}
