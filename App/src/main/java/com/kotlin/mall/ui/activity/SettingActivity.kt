package com.kotlin.mall.ui.activity

import android.os.Bundle
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.mall.R
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.toast

/**
 * @author  桂雁彬
 * @date 2019/3/20.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：设置界面
 */
class SettingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mUserProtocolTv.onClick {
            toast("用户协议")
        }
        mFeedBackTv.onClick {
            toast("反馈意见")
        }
        mAboutTv.onClick {
            toast("关于")
        }

        //退出登录，清空本地用户数据
        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}