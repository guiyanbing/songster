package com.kotlin.goods.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.data.protocol.Goods

/*
    商品列表 视图回调
 */
interface GoodsListView : BaseView {

    //获取商品列表
    fun onGetGoodsListResult(result: MutableList<Goods>?)
}
