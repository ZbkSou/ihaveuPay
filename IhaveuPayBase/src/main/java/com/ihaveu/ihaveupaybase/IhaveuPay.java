package com.ihaveu.ihaveupaybase;

import android.app.Activity;


import com.ihaveu.ihaveupaybase.callback.OnPayResultListener;
import com.ihaveu.ihaveupaybase.enums.PayWay;
import com.ihaveu.ihaveupaybase.pay.paystrategy.ALiPayStrategy;
import com.ihaveu.ihaveupaybase.pay.paystrategy.PayContext;
import com.ihaveu.ihaveupaybase.pay.paystrategy.WeChatPayStrategy;

/**
 * User: bkzhou
 * Date: 2018/9/20
 * Description:
 */
public class IhaveuPay {

    private static IhaveuPay sInstance;
    private OnPayResultListener mOnPayResultListener;

    private static String  WxId;
    //支付信息
    private PayParams mPayParams;


    // 通用结果码 用于返回通用结果
    public static final int COMMON_PAY_OK = 0;
    public static final int COMMON_PAY_ERR = -1;
    public static final int COMMON_USER_CACELED_ERR = -2;
    public static final int COMMON_REQUEST_PAYINFO_ERR = 3;

    private IhaveuPay() {
    }

    public static IhaveuPay newInstance() {
        if (sInstance == null) {
            sInstance = new IhaveuPay();
            return sInstance;
        }
        return sInstance;
    }

    /**
     * 传入微信 id
     * @param wxId
     */
    public void init(String wxId) {
        WxId = wxId;
    }

    public String getWeChatAppID(){
        if(WxId==null){
            throw new NullPointerException("请设置微信 Id");
        }
        return WxId;
    }



    /**
     * 支付
     *
     * @param payParams           支付方式
     * @param onPayResultListener 结果回调
     */
    public void toPay(PayParams payParams, OnPayResultListener onPayResultListener) {
        this.mPayParams = payParams;
        if (mPayParams.getPayWay() == null) {
            throw new NullPointerException("请设置支付方式");
        }
        mOnPayResultListener = onPayResultListener;
        PayContext pc = getPc(payParams);

        pc.pay();

    }

    /**
     * 获取支付实现方法
     *
     * @param payParams
     * @return
     */
    private PayContext getPc(PayParams payParams) {
        PayContext pc = null;
        PayCallBack callBack = new PayCallBack() {
            @Override
            public void onPayCallBack(int code) {
                sendPayResult(code);
            }
        };

        switch (payParams.getPayWay()) {
            case WechatPay:
                pc = new PayContext(new WeChatPayStrategy(payParams.getActivity(), payParams.getPayInfo(), callBack));
                break;
            case ALiPay:
                pc = new PayContext(new ALiPayStrategy(payParams.getActivity(), payParams.getPayInfo(), callBack));
                break;
            default:
                break;
        }
        return pc;
    }

    /**
     * 回调支付结果到请求界面
     *
     * @param code
     */
    private void sendPayResult(int code) {
        if (mPayParams == null) return;

        switch (code) {
            case COMMON_PAY_OK:
                mOnPayResultListener.onPaySuccess(mPayParams.getPayWay());
                break;

            case COMMON_USER_CACELED_ERR:
                mOnPayResultListener.onPayCancel(mPayParams.getPayWay());
                break;

            default:
                mOnPayResultListener.onPayFailure(mPayParams.getPayWay(), code);
                break;
        }
        releaseMomery();
    }

    /**
     * 清空信息
     */
    private void releaseMomery() {
        Activity activity = mPayParams.getActivity();
        activity = null;
        mPayParams = null;
        sInstance = null;

    }

    public interface PayCallBack {
        void onPayCallBack(int code);
    }


}
