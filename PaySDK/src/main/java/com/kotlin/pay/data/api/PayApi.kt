package com.kotlin.pay.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.pay.data.protocol.GetPaySignReq
import com.kotlin.pay.data.protocol.PayOrderReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


/*
    支付 接口
 */
interface PayApi {

    /*
        获取支付宝支付签名
     */
    @POST("Kotlin_war/pay/getPaySign")
    fun getPaySign(@Body req: GetPaySignReq): Observable<BaseResp<String>>

    /*
        刷新订单状态，已支付
     */
    @POST("Kotlin_war/order/pay")
    fun payOrder(@Body req: PayOrderReq): Observable<BaseResp<String>>

}
