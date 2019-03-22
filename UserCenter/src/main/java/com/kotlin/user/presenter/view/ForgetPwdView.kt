package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView

/**
 * @author  桂雁彬
 * @date 2019/3/18.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：
 */
interface ForgetPwdView : BaseView {

    /*
        忘记密码回调
     */
    fun onForgetPwdResult(result:String)
}
