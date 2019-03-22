package com.kotlin.pay.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.pay.presenter.view.PayView
import com.kotlin.pay.service.PayService
import javax.inject.Inject

/*
    支付Presenter
 */
class PayPresenter @Inject constructor() : BasePresenter<PayView>() {
    @Inject
    lateinit var service: PayService

    /*
        获取支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.getPaySign(orderId,totalPrice).excute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetSignResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        订单支付，同步订单状态
     */
    fun payOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.payOrder(orderId).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onPayOrderResult(t)
            }
        }, lifecycleProvider)

    }



}
