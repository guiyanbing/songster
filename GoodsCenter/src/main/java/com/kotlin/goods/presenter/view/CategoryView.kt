package com.kotlin.goods.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.data.protocol.Category

/*
    商品分类 视图回调
 */
interface CategoryView : BaseView {

    //获取商品分类列表
    fun onGetCategoryResult(result: MutableList<Category>?)
}
