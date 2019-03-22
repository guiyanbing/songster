package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.ForgetPwdPresenter
import com.kotlin.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * @author  桂雁彬
 * @date 2019/3/18.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()

    }

    /*
        初始化视图
     */
    private fun initView() {

        mNextBtn.enable(mMobileEt,{isBtnEnable()})
        mNextBtn.enable(mVerifyCodeEt,{isBtnEnable()})

        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    /*
        点击事件
     */
    override fun onClick(view: View) {
        when(view.id){
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证成功")
            }

            R.id.mNextBtn -> {
                mPresenter.forgetPwd(mMobileEt.text.toString(),mVerifyCodeEt.text.toString())
            }
        }
    }

    /*
        判断按钮是否可用
     */
    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }

    /*
        忘记密码回调
     */
    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>("mobile"  to mMobileEt.text.toString())
    }
}