package com.kotlin.goods.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.data.protocol.GetCategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    商品分类接口
 */
interface CategoryApi {
    /*
        获取商品分类列表
     */
    @POST("Kotlin_war/category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}
