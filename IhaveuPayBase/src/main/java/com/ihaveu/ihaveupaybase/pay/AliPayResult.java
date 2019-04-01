package com.ihaveu.ihaveupaybase.pay;

import android.text.TextUtils;

import java.util.Map;

/**
 * User: bkzhou
 * Date: 2018/9/21
 * Description:
 */
public class AliPayResult {
    public static final String PAY_OK_STATUS = "9000";// 支付成功
    public static final String PAY_WAIT_CONFIRM_STATUS = "8000";// 交易待确认
    public static final String PAY_FAILED_STATUS = "4000";// 交易失败
    public static final String PAY_CANCLE_STATUS = "6001";// 交易取消
    public static final String PAY_NET_ERR_STATUS = "6002";// 网络出错
    public static final String PAY_UNKNOWN_ERR_STATUS = "6004";// 结果未知
    private String resultStatus;
    private String result;
    private String memo;

    public AliPayResult(Map<String, String> rawResult) {
        if (rawResult == null) {
            return;
        }
        for (String key : rawResult.keySet()) {
            if (TextUtils.equals(key, "resultStatus")) {
                resultStatus = rawResult.get(key);
            } else if (TextUtils.equals(key, "result")) {
                result = rawResult.get(key);
            } else if (TextUtils.equals(key, "memo")) {
                memo = rawResult.get(key);
            }
        }
    }

    @Override
    public String toString() {
        return "resultStatus={" + resultStatus + "};memo={" + memo + "};result={" + result + "}";
    }

    /**
     * @return the resultStatus
     */
    public String getResultStatus() {
        return resultStatus;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }
}

