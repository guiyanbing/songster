package com.kotlin.pay.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.kotlin.pay.data.repository.PayRepository
import com.kotlin.pay.service.PayService
import rx.Observable
import javax.inject.Inject

/*
    支付 业务实现类
 */
class PayServiceImpl @Inject constructor(): PayService{

    @Inject
    lateinit var repository: PayRepository

    /*
        获取支付签名
     */
    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<String> {
        return repository.getPaySign(orderId,totalPrice).convert()
    }

    /*
        支付订单，同步订单状态
     */
    override fun payOrder(orderId: Int): Observable<Boolean> {
        return repository.payOrder(orderId).convertBoolean()
    }
}
