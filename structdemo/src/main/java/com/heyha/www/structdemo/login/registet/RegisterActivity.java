package com.heyha.www.structdemo.login.registet;

import android.util.Log;
import android.widget.EditText;

import com.heyha.www.library.base.BaseMvpActivity;
import com.heyha.www.library.utils.ToastUitl;
import com.heyha.www.structdemo.R;
import com.heyha.www.structdemo.bean.RegisterBean;
import com.heyha.www.structdemo.login.LoginActivity;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;

public class RegisterActivity extends BaseMvpActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @Bind(R.id.reg_name)
    public EditText regName;
    @Bind(R.id.reg_email)
    public EditText regEmail;
    @Bind(R.id.reg_pswd)
    public EditText regPswd;
    @Bind(R.id.reg_pswd2)
    public EditText regPswd2;

    @OnClick(R.id.register_btn)
    public void doRegister() {
        if (!presenter.checkLegal(regName)) {
            return;
        }
        if (!presenter.checkLegal(regEmail)) {
            return;
        }
        if (!presenter.checkLegal(regPswd)) {
            return;
        }
        if (!presenter.checkLegal(regPswd2)) {
            return;
        }
        if (!presenter.isSame(regPswd, regPswd2)) {
            return;
        }
        Log.i("TAG","123");
        RegisterBean registerBean = new RegisterBean();
        registerBean.setUserName(regName.getText().toString());
        registerBean.setUserEmail(regEmail.getText().toString());
        registerBean.setUserPswd(regPswd.getText().toString());
        presenter.doRegister(registerBean);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Bmob.initialize(this,"9526c389a785382731c501060551886f");
    }

    @Override
    protected void initPresenter() {
        presenter = new RegisterPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void showTint(String comName) {
        showError(comName);
    }

    @Override
    public void regSuccess() {
        startActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void regFailure(String error) {
        ToastUitl.showShort(error);
    }
}
