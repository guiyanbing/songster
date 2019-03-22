package com.kotlin.pay.injection.component

import com.kotlin.base.injection.PerComponentScope
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.pay.injection.module.PayModule
import com.kotlin.pay.ui.activity.CashRegisterActivity
import dagger.Component

/*
    支付Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(PayModule::class))
interface PayComponent {
    fun inject(activity: CashRegisterActivity)
}
