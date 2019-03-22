package com.kotlin.order.injection.module

import com.kotlin.order.service.OrderService
import com.kotlin.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/*
    订单Module
 */
@Module
class OrderModule {

    @Provides
    fun provideOrderservice(orderService: OrderServiceImpl): OrderService {
        return orderService
    }

}
