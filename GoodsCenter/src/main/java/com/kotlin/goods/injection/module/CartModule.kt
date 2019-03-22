package com.kotlin.goods.injection.module

import com.kotlin.goods.service.CartService
import com.kotlin.goods.service.GoodsService
import com.kotlin.goods.service.impl.CartServiceImpl
import com.kotlin.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/*
    购物车Module
 */
@Module
class CartModule {

    @Provides
    fun provideCartservice(cartService: CartServiceImpl): CartService {
        return cartService
    }

}
