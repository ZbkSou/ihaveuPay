package com.ihaveu.ihaveupaybase.enums;

/**
 * User: bkzhou
 * Date: 2018/9/21
 * Description:
 */
public enum PayWay {
    WechatPay(0),
    ALiPay(1);


    int payway;
    PayWay(int way) {
        payway = way;
    }

    @Override
    public String toString() {
        return String.valueOf(payway);
    }

}