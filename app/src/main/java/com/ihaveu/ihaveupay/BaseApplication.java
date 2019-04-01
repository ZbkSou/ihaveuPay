package com.ihaveu.ihaveupay;

import android.app.Application;

import com.ihaveu.ihaveupaybase.IhaveuPay;
import com.ihaveu.ihaveupaybase.wxgenerator.WXTemplateActivity;
import com.ihaveu.paycompiler.WXActivityGenerator;


/**
 * User: bkzhou
 * Date: 2018/9/26
 * Description:
 */
@WXActivityGenerator(getPackageName = "com.ihaveu.ihaveupay",
    getSupperClass = WXTemplateActivity.class)
public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        IhaveuPay.newInstance().init("wx16e7ad3a006d8066");
    }
}
