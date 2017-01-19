package com.heyha.www.structdemo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.heyha.www.structdemo.utils.LoadingDialog;
import com.heyha.www.structdemo.utils.ToastUitl;

import butterknife.ButterKnife;

/**
 * Created by Heyha on 2017/1/19.
 */

public abstract class BaseMvpActivity<V extends BaseView,T extends BasePresenter<V>> extends AppCompatActivity {
    protected T presenter;
    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        setContentView(getLayout());
        ButterKnife.bind(this);
        mContext = this;
        initPresenter();
    }

    /**
     * 可以设置主题、标题等内容
     */
    private void doBeforeSetcontentView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null){
            presenter.attach((V) this);
        }
    }

    @Override
    protected void onDestroy() {
        if (presenter != null){
            presenter.dettach();
        }
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    public void startActivity(Class<?> cla){
        startActivity(cla,null);

    }
    /**
     * 带有bundle的startActivity
     * @param cla
     * @param bundle
     */
    public void startActivity(Class<?> cla,Bundle bundle){
        Intent intent = new Intent(this,cla);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        this.startActivity(intent);
    }
    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cla,int requestCode){
        startActivityForResult(cla,null,requestCode);
    }
    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cla, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this,cla);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        this.startActivityForResult(intent,requestCode);
    }



    /**
     * 实例化presenter
     * @return
     */
    protected abstract void initPresenter();
    protected abstract int getLayout();
}
