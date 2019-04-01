package com.ihaveu.ihaveupaybase.callback;

import com.ihaveu.ihaveupaybase.enums.PayWay;

/**
 * User: bkzhou
 * Date: 2018/9/21
 * Description:
 */
public interface OnPayResultListener {
    void onPaySuccess(PayWay payWay);

    void onPayCancel(PayWay payWay);

    void onPayFailure(PayWay payWay, int errCode);
}