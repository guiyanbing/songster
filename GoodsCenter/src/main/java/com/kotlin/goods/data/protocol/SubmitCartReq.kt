package com.kotlin.goods.data.protocol

/*
    提交购物车
 */
data class SubmitCartReq(val goodsList: List<CartGoods>,val totalPrice: Long)
