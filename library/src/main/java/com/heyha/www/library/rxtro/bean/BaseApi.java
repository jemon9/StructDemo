package com.heyha.www.library.rxtro.bean;

import android.content.Context;


import com.heyha.www.library.rxtro.http.HttpService;
import com.heyha.www.library.rxtro.subscriber.SubscriberOnNextListener;

import java.lang.ref.SoftReference;

import rx.Observable;
import rx.functions.Func1;

/**
 * 每一个请求方法都需要实现该类
 * Created by Heyha on 2016/12/22.
 */

public abstract class BaseApi<T> implements Func1<HttpResult<T>, T> {

    /*是否显示加载框,默认不加载*/
    private boolean showProgress;
    /*是否能取消加载框,默认可以取消*/
    private boolean isCancelable = true;
    /*默认基地址,根据需要修改，也可以在发起请求时动态设置*/
    private String baseUrl = "https://api.douban.com/v2/movie/";
    /*超时时间，默认6s*/
    private int connectionTime = 6;
    /*回调,在用户发起请求的地方实现该接口，进行数据的回调,必须要实现该回调*/
    private SoftReference<SubscriberOnNextListener> mSubscriberOnNextListener;
    /*上下文，在子类中获得*/
    private Context context;

    public BaseApi(SubscriberOnNextListener listener, Context context) {
        setShowProgress(true);
        setListener(listener);
        this.context = context;
    }

    /**
     * 设置参数,获取访问哪个方法
     *
     * @param method
     * @return
     */
    public abstract Observable getObservable(HttpService method);

    public boolean isCancelable() {
        return isCancelable;
    }

    public void setCancelable(boolean cancelable) {
        isCancelable = cancelable;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SoftReference<SubscriberOnNextListener> getListener() {
        return mSubscriberOnNextListener;
    }

    public void setListener(SubscriberOnNextListener
                                    mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = new SoftReference(mSubscriberOnNextListener);
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    @Override
    public T call(HttpResult<T> httpResult) {
        //结合项目返回的统一数据的格式修改HttpResult并修改此处
        if (httpResult.getCount() == 0) {
            //在此处抛出异常
        }
        return httpResult.getSubjects();
    }
}
