package com.kotlin.goods.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.data.protocol.Category

/*
    购物车页面 视图回调
 */
interface CartListView : BaseView {

    //获取购物车列表
    fun onGetCartListResult(result: MutableList<CartGoods>?)
    //删除购物车
    fun onDeleteCartListResult(result: Boolean)
    //提交购物车
    fun onSubmitCartListResult(result: Int)
}
