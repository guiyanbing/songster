package com.kotlin.user.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.jph.takephoto.model.TResult
import com.kotlin.base.common.BaseApplication
import com.kotlin.base.common.BaseConstant
import com.kotlin.base.ext.onClick
import com.kotlin.base.injection.module.ActivityModule
import com.kotlin.base.injection.module.LifecycleProviderModule
import com.kotlin.base.ui.activity.BaseTakePhotoActivity
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.R
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.UserInfoPresenter
import com.kotlin.user.presenter.view.UserInfoView
import com.kotlin.user.utils.UserPrefsUtils
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import org.json.JSONObject

/*
    用户信息
 */
class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView {


    private val mUploadManager:UploadManager by lazy { UploadManager() }

    private var mLocalFileUrl:String? = null
    private var mRemoteFileUrl:String? = null

    private var mUserIcon:String? = null
    private var mUserName:String? = null
    private var mUserMobile:String? = null
    private var mUserGender:String? = null
    private var mUserSign:String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()
        initData()
    }


    /*
        初始化视图
     */
    private fun initView() {
        mUserIconView.onClick {

            handlePermisson()

        }

        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(mRemoteFileUrl!!,
                    mUserNameEt.text?.toString()?:"",
                    if(mGenderMaleRb.isChecked) "0" else "1",
                    mUserSignEt.text?.toString()?:""
            )
        }
    }

    /*
        初始化数据
     */
    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)

        mRemoteFileUrl = mUserIcon
        if (mUserIcon != ""){
            GlideUtils.loadUrlImage(this,mUserIcon!!,mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile

        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        }
        else {
            mGenderFemaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)

    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this


    }

    /*
        获取图片成功回调
     */
    override fun takeSuccess(result: TResult?) {

        mLocalFileUrl = result?.image?.compressPath
        mPresenter.getUploadToken()
    }


    /*
        获取上传凭证回调
     */
    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFileUrl,null,result,object:UpCompletionHandler{
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")
                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!,mUserIconIv)
            }

        },null)
    }

    /*
        编辑用户资料回调
     */
    override fun onEditUserResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }




    /**
     * 动态申请权限
     */
    fun handlePermisson(){

        // 需要动态申请的权限
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE

        //查看是否已有权限
        val checkSelfPermission = ActivityCompat.checkSelfPermission(this,permission)

        if (checkSelfPermission  == PackageManager.PERMISSION_GRANTED) {

            showAlertView()


        }else{

            //没有拿到权限  是否需要在第二次请求权限的情况下
            // 先自定义弹框说明 同意后在请求系统权限(就是是否需要自定义DialogActivity)
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){

                alert("安卓就是流氓的获取了你的私人信息","温馨提示"){

                    yesButton {
                        // 点击同意 请求真的权限
                        myRequestPermission()
                    }

                    noButton {
                        //可以回退到上一个界面 也可以不做任何处理
                    }
                }.show()

            }else{
                myRequestPermission()
            }
        }
    }

    private fun myRequestPermission() {
        //可以添加多个权限申请
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        requestPermissions(permissions,1)
    }
}
