package com.ihaveu.ihaveupaybase.pay.paystrategy;

/**
 * User: bkzhou
 * Date: 2018/9/21
 * Description:策略模式依赖注入具体的支付方式
 */
public class PayContext {

    private PayStrategyInterf mStrategy;

    public PayContext(PayStrategyInterf strategy) {
        mStrategy = strategy;
    }

    public void pay() {
        if (mStrategy != null) {
            mStrategy.doPay();
        }
    }
}
