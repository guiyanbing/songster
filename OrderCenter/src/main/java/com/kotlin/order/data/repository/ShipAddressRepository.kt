package com.kotlin.order.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.order.data.api.ShipAddressApi
import com.kotlin.order.data.protocol.AddShipAddressReq
import com.kotlin.order.data.protocol.DeleteShipAddressReq
import com.kotlin.order.data.protocol.EditShipAddressReq
import com.kotlin.order.data.protocol.ShipAddress
import rx.Observable
import javax.inject.Inject


/*
   收货地址数据层
 */
class ShipAddressRepository @Inject constructor() {

    /*
        添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).addShipAddress(AddShipAddressReq(shipUserName,shipUserMobile,shipAddress))
    }

    /*
        删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).deleteShipAddress(DeleteShipAddressReq(id))
    }

    /*
        修改收货地址
     */
    fun editShipAddress(address:ShipAddress): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).editShipAddress(EditShipAddressReq(address.id,address.shipUserName,address.shipUserMobile,address.shipAddress,address.shipIsDefault))
    }

    /*
        获取收货地址列表
     */
    fun getShipAddressList(): Observable<BaseResp<MutableList<ShipAddress>?>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).getShipAddressList()
    }

}
