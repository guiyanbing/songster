package com.kotlin.order.data.protocol

import android.os.Parcelable
import java.io.Serializable

/*
   收货地址
 */
data class ShipAddress(
        val id: Int,
        var shipUserName: String,
        var shipUserMobile: String,
        var shipAddress: String,
        var shipIsDefault: Int
) : Serializable
