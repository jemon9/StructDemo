package com.heyha.www.library.rxtro.subscriber;

/**暴露给用户的接口，发起网络请求时必须要实现该接口，该接口对请求结果进行处理
 * 成功或者失败或者完成
 * Created by Heyha on 2016/12/20.
 */

public abstract class SubscriberOnNextListener<T> {
    public abstract void onNext(T t);

    /**
     * 失败接口，默认用户不需要调用
     * @param e
     */
    public void onError(Throwable e){

    }

    /**
     * 取消接口，默认用户不需调用
     */
    public void onCancel(){

    }
}
