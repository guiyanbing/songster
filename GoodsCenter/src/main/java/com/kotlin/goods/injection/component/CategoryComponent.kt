package com.kotlin.goods.injection.component

import com.kotlin.base.injection.PerComponentScope
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.goods.injection.module.CategoryModule
import com.kotlin.goods.ui.fragment.CategoryFragment
import dagger.Component

/*
    商品分类Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}
