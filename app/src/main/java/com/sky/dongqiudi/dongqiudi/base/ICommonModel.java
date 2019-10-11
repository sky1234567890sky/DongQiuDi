package com.sky.dongqiudi.dongqiudi.base;

public interface ICommonModel<T>{
    void getData(ICommonView view, T...ts);
}
