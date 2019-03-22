package com.kotlin.order.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.data.repository.OrderRepository
import com.kotlin.order.service.OrderService
import rx.Observable
import javax.inject.Inject

/*
    订单业务实现类
 */
class OrderServiceImpl @Inject constructor(): OrderService{

    @Inject
    lateinit var repository: OrderRepository

    /*
        根据ID查询订单
     */
    override fun getOrderById(orderId: Int): Observable<Order> {
        return repository.getOrderById(orderId).convert()
    }

    /*
        订单确认，提交订单
     */
    override fun submitOrder(order: Order): Observable<Boolean> {
        return repository.submitOrder(order).convertBoolean()
    }

    /*
        根据订单状态获取订单列表
     */
    override fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?> {
        return repository.getOrderList(orderStatus).convert()

    }

    /*
        取消订单
     */
    override fun cancelOrder(orderId: Int): Observable<Boolean> {
        return repository.cancelOrder(orderId).convertBoolean()
    }

    /*
        订单确认收货
     */
    override fun confirmOrder(orderId: Int): Observable<Boolean> {
        return repository.confirmOrder(orderId).convertBoolean()
    }
}
