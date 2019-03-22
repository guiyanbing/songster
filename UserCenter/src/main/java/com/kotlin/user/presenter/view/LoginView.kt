package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.user.data.protocol.UserInfo

/**
 * @author  桂雁彬
 * @date 2019/3/18.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：
 */
interface LoginView :BaseView {
    fun onLoginResult(result: UserInfo)
}