package com.kotlin.user.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.service.UserService
import javax.inject.Inject

/*
    用户注册Presenter
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService


    fun register(mobile: String, pwd: String, verifyCode: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        userService.register(mobile, pwd, verifyCode).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                if (t){
                    mView.onRegisterResult("注册成功")
                }else{
                    mView.onRegisterResult("注册失败")

                }

            }
        }, lifecycleProvider)

    }

}
