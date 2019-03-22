package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * @author  桂雁彬
 * @date 2019/3/15.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView,View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mRegisterBtn.enable(mMobileEt,{isBtnEnable()})
        mRegisterBtn.enable(mVerifyCodeEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdConfirmEt,{isBtnEnable()})
        mVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    /*
        注册回调
     */
    override fun onRegisterResult(result: String) {
        toast(result)
        finish()
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

            R.id.mRegisterBtn -> {
                mPresenter.register(mMobileEt.text.toString(),mPwdEt.text.toString(),mVerifyCodeEt.text.toString())
            }
        }
    }

    /*
        判断按钮是否可用
     */
    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()&&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}