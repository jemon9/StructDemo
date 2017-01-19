package com.heyha.www.structdemo.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

/**
 * Created by Heyha on 2017/1/19.
 */

public class LoadingDialog {
    private static ProgressDialog mLoadingDialog;

    /**
     * 显示可以取消的加载框
     * @param activity
     */
    public static void showLoadingDialog(Activity activity){
        if (mLoadingDialog == null){
            mLoadingDialog = new ProgressDialog(activity);
        }
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.cancel();
            }
        });
        if (!mLoadingDialog.isShowing()){
            mLoadingDialog.show();
        }
    }

    public static void dismissLoadingDidalog(){
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }
}
