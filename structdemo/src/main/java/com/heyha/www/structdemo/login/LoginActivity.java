package com.heyha.www.structdemo.login;

import android.util.Log;
import android.widget.EditText;

import com.heyha.www.library.base.BaseMvpActivity;
import com.heyha.www.structdemo.R;
import com.heyha.www.structdemo.login.registet.RegisterActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity implements LoginView {
    @Bind(R.id.username)
    public EditText userName;
    @Bind(R.id.password)
    public EditText passWord;

    @OnClick(R.id.login_btn)
    public void doLogin() {
        // TODO: 2017/1/20 login
    }

    @OnClick(R.id.login_reg)
    public void doRegist() {
        // TODO: 2017/1/20 register
        startActivity(RegisterActivity.class);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Log.i("TAG", "login activity");
    }

    @Override
    protected void initPresenter() {
        presenter = new LoginPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void success() {

    }

    @Override
    public void failure() {

    }
}
