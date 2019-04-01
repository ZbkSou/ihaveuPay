package com.ihaveu.ihaveupaybase.pay.paystrategy;

/**
 * User: bkzhou
 * Date: 2018/9/21
 * Description:不同的支付策略内部处理自己的结果会掉，返回给统一的支付结果需要返回公用的支付结果
 * 执行微信的支付方式还有处理微信的回调
 */
public interface PayStrategyInterf {
    void doPay();
}
