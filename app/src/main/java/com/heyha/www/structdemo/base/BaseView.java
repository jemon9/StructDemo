package com.heyha.www.structdemo.base;

/**
 * Created by Heyha on 2017/1/19.
 */

public interface BaseView {
    void showLoading();
    void hideLoading();
    void showError(String msg);
}
