package com.ihaveu.ihaveupaybase.pay.paystrategy;

import android.app.Activity;

import com.ihaveu.ihaveupaybase.IhaveuPay;

import java.util.HashMap;

/**
 * User: bkzhou
 * Date: 2018/9/21
 * Description:
 */
public abstract class BasePayStrategy implements PayStrategyInterf{
    protected  HashMap<String,Object> mPayInfo;
    protected IhaveuPay.PayCallBack callback;
    protected Activity activity;


    public BasePayStrategy(Activity activity, HashMap<String,Object> prePayInfo, IhaveuPay.PayCallBack resultListener) {
        mPayInfo = prePayInfo;
        callback = resultListener;
        this.activity = activity;
    }

    public abstract void doPay();
}
