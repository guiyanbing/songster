package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView

/*
    用户注册 视图回调
 */
interface RegisterView:BaseView{

    fun onRegisterResult(result:String)
}
