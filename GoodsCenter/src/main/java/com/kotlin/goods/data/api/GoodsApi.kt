package com.kotlin.goods.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.goods.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    商品接口
 */
interface GoodsApi {
    /*
        获取商品列表
     */
    @POST("Kotlin_war/goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品列表
     */
    @POST("Kotlin_war/goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品详情
     */
    @POST("Kotlin_war/goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResp<Goods>>
}
