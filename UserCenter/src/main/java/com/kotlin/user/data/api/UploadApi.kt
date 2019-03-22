package com.kotlin.user.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.user.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    上传相关 接口
 */
interface UploadApi {

    /*
        获取七牛云上传凭证
     */
    @POST("Kotlin_war/common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>
}
