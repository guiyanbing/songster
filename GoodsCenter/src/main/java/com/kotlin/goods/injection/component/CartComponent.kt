package com.kotlin.goods.injection.component

import com.kotlin.base.injection.PerComponentScope
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.goods.injection.module.CartModule
import com.kotlin.goods.injection.module.GoodsModule
import com.kotlin.goods.ui.activity.GoodsActivity
import com.kotlin.goods.ui.fragment.CartFragment
import com.kotlin.goods.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/*
    购物车Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CartModule::class))
interface CartComponent {
    fun inject(fragment: CartFragment)
}
