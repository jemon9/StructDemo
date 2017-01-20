package com.heyha.www.library.rxtro.http;


import com.heyha.www.library.rxtro.bean.BaseApi;
import com.heyha.www.library.rxtro.subscriber.ProgressSubscriber;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Heyha on 2016/12/20.
 */

public class HttpManager {
    private static volatile HttpManager INSTANCE;

    private HttpManager() {
    }

    public static HttpManager getINSTANCE(){
        if (INSTANCE == null){
            synchronized (HttpManager.class){
                if (INSTANCE == null){
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }

    public void doHttpRequest(BaseApi baseApi){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(baseApi.getConnectionTime(), TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseApi.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        HttpService service = retrofit.create(HttpService.class);

        ProgressSubscriber progressSubscriber = new ProgressSubscriber(baseApi);
        Observable observable = baseApi.getObservable(service)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseApi);  //对返回的统一格式的数据处理，得到与业务相关的数据
        observable.subscribe(progressSubscriber);
    }
}
