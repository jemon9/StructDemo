package com.heyha.www.structdemo.login.registet;

import com.heyha.www.library.base.BaseView;

/**
 * Created by Heyha on 2017/1/20.
 */

public interface RegisterView extends BaseView {
    void showTint(String str);
    void regSuccess();
    void regFailure(String error);
}
