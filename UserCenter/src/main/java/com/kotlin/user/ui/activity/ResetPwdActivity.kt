package com.kotlin.user.ui.activity

import android.os.Bundle
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.ResetPwdPresenter
import com.kotlin.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.*

/**
 * @author  桂雁彬
 * @date 2019/3/18.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：
 */
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()

    }

    /*
        初始化视图
     */
    private fun initView() {

        mConfirmBtn.enable(mPwdEt,{isBtnEnable()})
        mConfirmBtn.enable(mPwdConfirmEt,{isBtnEnable()})

        mConfirmBtn.onClick {
            if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()){
                toast("密码不一致")
                return@onClick
            }
            mPresenter.resetPwd(intent.getStringExtra("mobile"),mPwdEt.text.toString())
        }
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    /*
        判断按钮是否可用
     */
    private fun isBtnEnable():Boolean{
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

    /*
        重置密码回调
     */
    override fun onResetPwdResult(result: String) {
        toast(result)
       // startActivity(intentFor<LoginActivity>().singleTop().clearTop())

        startActivity<UserInfoActivity>()

    }
}