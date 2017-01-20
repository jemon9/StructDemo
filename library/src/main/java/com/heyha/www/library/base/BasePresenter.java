package com.heyha.www.library.base;

/**
 * Created by Heyha on 2017/1/19.
 */

public abstract class BasePresenter<V extends BaseView> {
    public V mView;

    public void attach(V mView){
        this.mView = mView;
    }

    public void dettach(){
        mView = null;
    }
}
