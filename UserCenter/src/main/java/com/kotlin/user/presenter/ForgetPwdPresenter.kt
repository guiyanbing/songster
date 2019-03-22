package com.kotlin.user.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.ForgetPwdView
import com.kotlin.user.service.UserService
import javax.inject.Inject

/**
 * @author  桂雁彬
 * @date 2019/3/18.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：忘记密码Presenter
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService: UserService


    fun forgetPwd(mobile: String, verifyCode: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        userService.forgetPwd(mobile, verifyCode).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                if (t)
                    mView.onForgetPwdResult("验证成功")
            }
        }, lifecycleProvider)

    }

}