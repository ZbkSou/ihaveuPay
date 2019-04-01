package com.ihaveu.ihaveupaybase.pay.paystrategy;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.ihaveu.ihaveupaybase.IhaveuPay;
import com.ihaveu.ihaveupaybase.enums.PayInfoKey;
import com.ihaveu.ihaveupaybase.pay.AliPayResult;
import com.ihaveu.ihaveupaybase.utils.ThreadManager;

import java.util.HashMap;
import java.util.Map;

/**
 * User: bkzhou
 * Date: 2018/9/21
 * Description: 不同的支付策略内部处理自己的结果会掉，返回给统一的支付结果需要返回公用的支付结果
 */
public class ALiPayStrategy extends BasePayStrategy{
    private static final int PAY_RESULT_MSG = 0;
    // 支付宝结果码
    public static final int ALI_PAY_WAIT_CONFIRM_ERR = 8000;
    public static final int ALI_PAY_NET_ERR = 6002;
    public static final int ALI_PAY_UNKNOW_ERR = 6004;
    public static final int ALI_PAY_OTHER_ERR = 6005;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what != PAY_RESULT_MSG) {
                return;
            }
            ThreadManager.shutdown();
            AliPayResult result = new AliPayResult((Map<String, String>) msg.obj);
            switch (result.getResultStatus()) {
                case AliPayResult.PAY_OK_STATUS:
                    callback.onPayCallBack(IhaveuPay.COMMON_PAY_OK);
                    break;

                case AliPayResult.PAY_CANCLE_STATUS:
                    callback.onPayCallBack(IhaveuPay.COMMON_USER_CACELED_ERR);
                    break;

                case AliPayResult.PAY_FAILED_STATUS:
                    callback.onPayCallBack(IhaveuPay.COMMON_PAY_ERR);
                    break;

                case AliPayResult.PAY_WAIT_CONFIRM_STATUS:
                    callback.onPayCallBack(ALI_PAY_WAIT_CONFIRM_ERR);
                    break;

                case AliPayResult.PAY_NET_ERR_STATUS:
                    callback.onPayCallBack(ALI_PAY_NET_ERR);
                    break;

                case AliPayResult.PAY_UNKNOWN_ERR_STATUS:
                    callback.onPayCallBack(ALI_PAY_UNKNOW_ERR);
                    break;

                default:
                    callback.onPayCallBack(ALI_PAY_OTHER_ERR);
                    break;
            }
            mHandler.removeCallbacksAndMessages(null);
        }
    };

    public ALiPayStrategy(Activity activity, HashMap<String,Object> prePayInfo, IhaveuPay.PayCallBack resultListener) {
        super(activity, prePayInfo, resultListener);
    }

    @Override
    public void doPay() {
        if(null == mPayInfo||(mPayInfo.get(PayInfoKey.AliUrl))==null){
            callback.onPayCallBack(IhaveuPay.COMMON_REQUEST_PAYINFO_ERR);
        }
        Runnable payRun = new Runnable() {
            @Override
            public void run() {
                PayTask task = new PayTask(activity);
                Map<String, String> result = task.payV2((String)(mPayInfo.get(PayInfoKey.AliUrl)), true);
                Message message = mHandler.obtainMessage();
                message.what = PAY_RESULT_MSG;
                message.obj = result;
                mHandler.sendMessage(message);
            }
        };
        ThreadManager.execute(payRun);
    }
}
