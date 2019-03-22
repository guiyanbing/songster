package com.kotlin.order.event

import com.kotlin.order.data.protocol.ShipAddress

/*
    选择收货人信息事件
 */
class SelectAddressEvent(val address:ShipAddress)
