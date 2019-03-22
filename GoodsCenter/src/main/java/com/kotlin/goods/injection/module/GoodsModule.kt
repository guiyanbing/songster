package com.kotlin.goods.injection.module

import com.kotlin.goods.service.GoodsService
import com.kotlin.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/*
    商品Module
 */
@Module
class GoodsModule {

    @Provides
    fun provideGoodservice(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }

}
