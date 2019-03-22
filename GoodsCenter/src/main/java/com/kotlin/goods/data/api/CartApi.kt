package com.kotlin.goods.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.goods.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    购物车 接口
 */
interface CartApi {
    /*
        获取购物车列表
     */
    @POST("Kotlin_war/cart/getList")
    fun getCartList(): Observable<BaseResp<MutableList<CartGoods>?>>

    /*
        添加商品到购物车
     */
    @POST("Kotlin_war/cart/add")
    fun addCart(@Body req: AddCartReq): Observable<BaseResp<Int>>

    /*
        删除购物车商品
     */
    @POST("Kotlin_war/cart/delete")
    fun deleteCartList(@Body req: DeleteCartReq): Observable<BaseResp<String>>

    /*
        提交购物车商品
     */
    @POST("Kotlin_war/cart/submit")
    fun submitCart(@Body req: SubmitCartReq): Observable<BaseResp<Int>>
}
