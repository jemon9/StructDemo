package com.heyha.www.structdemo.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Heyha on 2017/1/19.
 */

public abstract class BaseMvpFragment<V extends BaseView,T extends BasePresenter<V>> extends Fragment {
    protected T presenter;
    private Activity mActivity;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        if (rootView == null){
            rootView = inflater.inflate(getLayoutResource(),null,false);
        }
        ButterKnife.bind(this,rootView);
        initPresenter();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null){
            presenter.attach((V) this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter != null){
            presenter.dettach();
        }
    }

    protected abstract void initPresenter();

    protected abstract int getLayoutResource();
}
