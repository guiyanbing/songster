package com.kotlin.order.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.order.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


/*
    订单 接口
 */
interface OrderApi {

    /*
        取消订单
     */
    @POST("Kotlin_war/order/cancel")
    fun cancelOrder(@Body req: CancelOrderReq): Observable<BaseResp<String>>

    /*
        确认订单
     */
    @POST("Kotlin_war/order/confirm")
    fun confirmOrder(@Body req: ConfirmOrderReq): Observable<BaseResp<String>>

    /*
        根据ID获取订单
     */
    @POST("Kotlin_war/order/getOrderById")
    fun getOrderById(@Body req: GetOrderByIdReq): Observable<BaseResp<Order>>

    /*
        根据订单状态查询查询订单列表
     */
    @POST("Kotlin_war/order/getOrderList")
    fun getOrderList(@Body req: GetOrderListReq): Observable<BaseResp<MutableList<Order>?>>

    /*
        提交订单
     */
    @POST("Kotlin_war/order/submitOrder")
    fun submitOrder(@Body req: SubmitOrderReq): Observable<BaseResp<String>>

}
