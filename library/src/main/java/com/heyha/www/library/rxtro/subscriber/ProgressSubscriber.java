package com.heyha.www.library.rxtro.subscriber;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.heyha.www.library.rxtro.bean.BaseApi;
import com.heyha.www.library.rxtro.progress.ProgressCancelListener;
import com.heyha.www.library.rxtro.progress.ProgressDialogHandler;

import java.lang.ref.SoftReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * rxjava体现，订阅者的角色
 * Created by Heyha on 2016/12/20.
 */

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {
    private static final String TAG = ProgressSubscriber.class.getName();
    private SoftReference<SubscriberOnNextListener> mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Context context;
    /*判断是否弹出加载框*/
    private boolean isShowProgress;
    /*请求数据*/
    private BaseApi baseApi;

  /*  public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        this.mProgressDialogHandler = new ProgressDialogHandler(context,true,this);
    }*/

    public ProgressSubscriber(BaseApi baseApi) {
        this.baseApi = baseApi;
        this.context = baseApi.getContext();
        setShowProgress(baseApi.isShowProgress());
        this.mSubscriberOnNextListener =baseApi.getListener();
        this.mProgressDialogHandler = new ProgressDialogHandler(context,baseApi.isCancelable(),this);
    }

    private void showProgressDialog(){
        if (!isShowProgress() || context == null){
            return;
        }
        if (mProgressDialogHandler != null){
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (!isShowProgress()){
            return;
        }
        if (mProgressDialogHandler != null){
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    public boolean isShowProgress() {
        return isShowProgress;
    }

    public void setShowProgress(boolean showProgress) {
        isShowProgress = showProgress;
    }

    @Override
    public void onCompleted() {
        Log.i(TAG,"onCompleted()");
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        if (context == null){
            return;
        }
        if (e instanceof SocketTimeoutException){
            Toast.makeText(context,"网络异常",Toast.LENGTH_LONG).show();
        }else if (e instanceof ConnectException){
            Toast.makeText(context,"网络异常",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,"error:" + e.getMessage(),Toast.LENGTH_LONG).show();
        }
        if (mSubscriberOnNextListener.get() != null){
            mSubscriberOnNextListener.get().onError(e);
        }
        Log.i(TAG,"onError()");
    }

    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener.get() != null){
            mSubscriberOnNextListener.get().onNext(t);
        }
    }

    /**
     * rxjava　start时打开加载框
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     *来自ProgressCancelListener，当progressDialog取消的时候，取消去observable的订阅，同时取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()){
            this.unsubscribe();
        }
    }
}
