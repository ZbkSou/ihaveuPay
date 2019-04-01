package com.ihaveu.ihaveupaybase;

import android.app.Activity;

import com.ihaveu.ihaveupaybase.enums.PayWay;

import java.util.HashMap;

/**
 * User: bkzhou
 * Date: 2019/3/15
 * Description:
 */
public class PayParams {
    private Activity mActivity;
    private PayWay mPayWay;
    private HashMap<String,Object> payInfo;
    public HashMap<String, Object> getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(HashMap<String, Object> payInfo) {
        this.payInfo = payInfo;
    }



    public Activity getActivity() {
        return mActivity;
    }

    private void setActivity(Activity activity) {
        mActivity = activity;
    }


    public PayWay getPayWay() {
        return mPayWay;
    }

    private void setPayWay(PayWay mPayWay) {
        this.mPayWay = mPayWay;
    }




    public static class Builder {
        Activity mActivity;
        PayWay payWay;
        HashMap<String,Object> payInfo;


        public Builder(Activity activity) {
            mActivity = activity;
        }



        public PayParams.Builder payWay(PayWay way) {
            payWay = way;
            return this;
        }

        public PayParams.Builder payInfo(HashMap<String,Object> payInfo) {
            this.payInfo = payInfo;
            return this;
        }

        public PayParams build() {
            PayParams params = new PayParams();
            params.setActivity(mActivity);
            params.setPayWay(payWay);
            params.setPayInfo(payInfo);

            return params;
        }

    }
}
