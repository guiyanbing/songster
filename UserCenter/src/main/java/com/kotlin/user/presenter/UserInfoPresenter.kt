package com.kotlin.user.presenter

import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.presenter.view.UserInfoView
import com.kotlin.user.service.UploadService
import com.kotlin.user.service.UserService
import javax.inject.Inject

/**
 * @author  桂雁彬
 * @date 2019/3/18.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var uploadService: UploadService

    /*
        获取七牛云上传凭证
     */
    fun getUploadToken(){
        if (!checkNetWork())
            return

        mView.showLoading()
        uploadService.getUploadToken().excute(object : BaseSubscriber<String>(mView){
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        },lifecycleProvider)
    }

    /*
        编辑用户资料
     */
    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String){
        if (!checkNetWork())
            return

        mView.showLoading()
        userService.editUser(userIcon,userName,userGender,userSign).excute(object : BaseSubscriber<UserInfo>(mView){
            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        },lifecycleProvider)
    }

}