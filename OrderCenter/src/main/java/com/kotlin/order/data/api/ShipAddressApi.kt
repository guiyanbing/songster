package com.kotlin.order.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.order.data.protocol.AddShipAddressReq
import com.kotlin.order.data.protocol.DeleteShipAddressReq
import com.kotlin.order.data.protocol.EditShipAddressReq
import com.kotlin.order.data.protocol.ShipAddress
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


/*
    地址管理 接口
 */
interface ShipAddressApi {

    /*
        添加收货地址
     */
    @POST("Kotlin_war/shipAddress/add")
    fun addShipAddress(@Body req: AddShipAddressReq): Observable<BaseResp<String>>

    /*
        删除收货地址
     */
    @POST("Kotlin_war/shipAddress/delete")
    fun deleteShipAddress(@Body req: DeleteShipAddressReq): Observable<BaseResp<String>>

    /*
        修改收货地址
     */
    @POST("Kotlin_war/shipAddress/modify")
    fun editShipAddress(@Body req: EditShipAddressReq): Observable<BaseResp<String>>

    /*
        查询收货地址列表
     */
    @POST("Kotlin_war/shipAddress/getList")
    fun getShipAddressList(): Observable<BaseResp<MutableList<ShipAddress>?>>

}
