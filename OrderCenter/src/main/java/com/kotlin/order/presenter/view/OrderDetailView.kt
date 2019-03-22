package com.kotlin.order.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.order.data.protocol.Order

/*
    订单详情页 视图回调
 */
interface OrderDetailView : BaseView {

    fun onGetOrderByIdResult(result: Order)
}
