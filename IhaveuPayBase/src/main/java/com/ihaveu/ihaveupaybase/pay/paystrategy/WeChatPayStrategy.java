package com.ihaveu.ihaveupaybase.pay.paystrategy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.ihaveu.ihaveupaybase.IhaveuPay;
import com.ihaveu.ihaveupaybase.enums.PayInfoKey;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.ihaveu.ihaveupaybase.IhaveuPay.COMMON_REQUEST_PAYINFO_ERR;

/**
 * User: bkzhou
 * Date: 2018/9/21
 * Description:执行微信的支付方式还有处理微信的回调
 */
public class WeChatPayStrategy extends BasePayStrategy {
    public static final String WECHAT_PAY_RESULT_ACTION = "com.tencent.mm.opensdk.WECHAT_PAY_RESULT_ACTION";
    public static final String WECHAT_PAY_RESULT_EXTRA = "com.tencent.mm.opensdk.WECHAT_PAY_RESULT_EXTRA";
    private LocalBroadcastManager mBroadcastManager;
    private Context mContext;

    public static final int WECHAT_NOT_INSTALLED_ERR = -7;
    public WeChatPayStrategy(Activity activity, HashMap<String,Object> prePayInfo, IhaveuPay.PayCallBack resultListener) {
        super(activity, prePayInfo, resultListener);
        mContext = activity;
    }

    @Override
    public void doPay() {
        if(mPayInfo.get(PayInfoKey.WxData)==null){
            super.callback.onPayCallBack(WECHAT_NOT_INSTALLED_ERR);
            return;
        }
        PayReq req = genPayReq((String)(mPayInfo.get(PayInfoKey.WxData)));
        IWXAPI wxapi = WXAPIFactory.createWXAPI(mContext.getApplicationContext(), IhaveuPay.newInstance().getWeChatAppID(), true);
        if (!wxapi.isWXAppInstalled()) {
            super.callback.onPayCallBack(WECHAT_NOT_INSTALLED_ERR);
            return;
        }
        wxapi.registerApp(req.appId);
        registPayResultBroadcast();

        // 发送支付请求：跳转到微信客户端
        wxapi.sendReq(req);
    }

    /**
     * 转换成微信支付实例类
     * @param payParam
     * @return
     */
    private PayReq genPayReq(String payParam) {

        JSONObject param;
        try {
            param = new JSONObject(payParam);
        } catch (JSONException e) {
            e.printStackTrace();

            return null;
        }
        if (TextUtils.isEmpty(param.optString("appid")) || TextUtils.isEmpty(param.optString("partnerid"))
            || TextUtils.isEmpty(param.optString("prepayid")) || TextUtils.isEmpty(param.optString("package")) ||
            TextUtils.isEmpty(param.optString("noncestr")) || TextUtils.isEmpty(param.optString("timestamp")) ||
            TextUtils.isEmpty(param.optString("sign"))) {
            if (callback != null) {
                callback.onPayCallBack(COMMON_REQUEST_PAYINFO_ERR);
            }
            return null;
        }
        PayReq req = new PayReq();
        req.appId = param.optString("appid");
        req.partnerId = param.optString("partnerid");
        req.prepayId = param.optString("prepayid");
        req.packageValue = param.optString("package");
        req.nonceStr = param.optString("noncestr");
        req.timeStamp = param.optString("timestamp");
        req.sign = param.optString("sign");
        return req;
    }



    private void registPayResultBroadcast() {
        mBroadcastManager = LocalBroadcastManager.getInstance(mContext.getApplicationContext());
        IntentFilter filter = new IntentFilter(WECHAT_PAY_RESULT_ACTION);
        mBroadcastManager.registerReceiver(mReceiver, filter);
    }

    private void unRegistPayResultBroadcast() {
        if (mBroadcastManager != null && mReceiver != null) {
            mBroadcastManager.unregisterReceiver(mReceiver);
            mBroadcastManager = null;
            mReceiver = null;
        }
    }

    /**
     * 支付结果回调
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int result = intent.getIntExtra(WECHAT_PAY_RESULT_EXTRA, -100);
            callback.onPayCallBack(result);

            unRegistPayResultBroadcast();
        }
    };
}
