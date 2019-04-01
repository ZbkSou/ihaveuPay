package com.ihaveu.ihaveupay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.ihaveu.ihaveupaybase.IhaveuPay;
import com.ihaveu.ihaveupaybase.callback.OnPayResultListener;
import com.ihaveu.ihaveupaybase.enums.PayWay;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static  final  String TAG= "MainActivity";

    @BindView(R.id.but_pay)
    Button butPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.but_pay)
    public void onViewClicked() {
//      IhaveuPay.newInstance().toPay(PayWay.ALiPay,
//          "app_id=2017091308710515&biz_content={\"subject\":\"\\u7eaf\\u68c9\\u889c\\u51711\\u4ef6\",\"out_trade_no\":\"20180926153237GEUI2H\",\"total_amount\":445,\"product_code\":\"QUICK_MSECURITY_PAY\"}&charset=UTF-8&method=alipay.trade.app.pay&notify_url=http://test-api.ihaveu.com/api/pay/alipays/notify&sign_type=RSA2&timestamp=2018-09-26 15:32:38&version=1.0&sign=lGowLRCqw2E5wXfXnf%2B00MM%2FcDt8BKOEQu4qufKq7z6Boj1LPjaZ3%2B9SCNDM4Mh5OT%2B9k4aaFqLrZI0eKUxizRQ3RPQ%2BcDte%2FoJlwR72xqR7E6Ag4EiSubX486iMW%2Fr0I314u0sDZs0DncoNHG2Bjj%2FfDEvxoyjNi8eigj2yEHuUEelw%2F7UdaFihYHFgRjg5B5EKewh%2BRkQsaP01zpEXnc%2F6Ncffy0AhvBnORMWkp68Za8hq%2FIfDuPl8bxUWJQ%2FNeGj4SC7L8F9A%2FanjmmgkPaj%2BEafrzRsOzY9J51ahJUi54aVoOOj3%2Bc%2FS3sVxnyUnrP68bd%2Bnyz1JFQRirSqE5w%3D%3D",
//          this,new OnPayResultListener() {
//              @Override
//              public void onPaySuccess(PayWay payWay) {
//                  Log.d(TAG,"成功");
//              }
//
//              @Override
//              public void onPayCancel(PayWay payWay) {
//                  Log.d(TAG,"取消");
//              }
//
//              @Override
//              public void onPayFailure(PayWay payWay, int errCode) {
//                  Log.d(TAG,"失败");
//              }
//          });
    }
}
