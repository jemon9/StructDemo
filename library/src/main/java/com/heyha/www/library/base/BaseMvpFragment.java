package com.heyha.www.library.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heyha.www.library.utils.LoadingDialog;
import com.heyha.www.library.utils.ToastUitl;

import butterknife.ButterKnife;

/**
 * Created by Heyha on 2017/1/19.
 */

public abstract class BaseMvpFragment<V extends BaseView,T extends BasePresenter<V>> extends Fragment implements BaseView {
    protected T presenter;
    private Activity mActivity;
    private View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mActivity = getActivity();
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

    public void startActivity(Class<?> cla){
        startActivity(cla,null);
    }

    public void startActivity(Class<?> cla,Bundle bundle){
        Intent intent = new Intent(mActivity,cla);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cla,int resultCode){
        startActivityForResult(cla,null,resultCode);
    }

    public void startActivityForResult(Class<?> cla,Bundle bundle,int resultCode){
        Intent intent = new Intent(mActivity,cla);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivityForResult(intent,resultCode);
    }

    @Override
    public void showLoading() {
        LoadingDialog.showLoadingDialog(mActivity);
    }

    @Override
    public void hideLoading() {
        LoadingDialog.dismissLoadingDidalog();
    }

    @Override
    public void showError(String msg) {
        ToastUitl.showShort(msg);
    }

    protected abstract void initPresenter();

    protected abstract int getLayoutResource();
}
