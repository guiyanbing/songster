package com.kotlin.user.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.user.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    用户相关 接口
 */
interface UserApi{

    /*
        用户注册
     */
    @POST("Kotlin_war/userCenter/register")
    fun register(@Body req:RegisterReq):Observable<BaseResp<String>>

    /*
     用户登录
  */
    @POST("Kotlin_war/userCenter/login")
    fun login(@Body req:LoginReq):Observable<BaseResp<UserInfo>>


    /*
     忘记密码
  */
    @POST("Kotlin_war/userCenter/forgetPwd")
    fun forgetPwd(@Body req:ForgetPwdReq):Observable<BaseResp<String>>


    /*
      重置密码
   */
    @POST("Kotlin_war/userCenter/resetPwd")
    fun resetPwd(@Body req:ResetPwdReq):Observable<BaseResp<String>>
    /*
           编辑用户资料
        */
    @POST("Kotlin_war/userCenter/editUser")
    fun editUser(@Body req:EditUserReq):Observable<BaseResp<UserInfo>>
}
