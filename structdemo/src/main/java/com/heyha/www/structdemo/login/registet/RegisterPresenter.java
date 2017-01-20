package com.heyha.www.structdemo.login.registet;

import android.text.TextUtils;
import android.widget.EditText;

import com.heyha.www.library.base.BasePresenter;
import com.heyha.www.structdemo.bean.RegisterBean;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Heyha on 2017/1/20.
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {
    public RegisterPresenter() {
    }

    public boolean checkLegal(EditText str){
        if (TextUtils.isEmpty(str.getText())){
            mView.showTint(str.getHint().toString() + "不能为空");
            return false;
        }
        return true;
    }
    public boolean isSame(EditText pswd1,EditText pswd2){
        if (!TextUtils.isEmpty(pswd1.getText()) && !TextUtils.isEmpty(pswd2.getText()) && pswd1.getText().equals(pswd2.getText())){
            return true;
        }
        return false;
    }
    public void doRegister(RegisterBean bean){
        bean.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null){
                    mView.regSuccess();
                }else {
                    mView.regFailure(e.toString());
                }
            }
        });
    }
}
