package com.kotlin.goods.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.presenter.view.CategoryView
import com.kotlin.goods.service.CategoryService
import javax.inject.Inject

/*
    商品分类 Presenter
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService


    /*
        获取商品分类列表
     */
    fun getCategory(parentId:Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        categoryService.getCategory(parentId).excute(object : BaseSubscriber<MutableList<Category>?>(mView) {
            override fun onNext(t: MutableList<Category>?) {
                    mView.onGetCategoryResult(t)
            }
        }, lifecycleProvider)

    }

}
