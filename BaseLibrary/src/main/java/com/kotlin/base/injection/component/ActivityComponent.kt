package com.kotlin.base.injection.component

import android.app.Activity
import android.content.Context
import com.kotlin.base.injection.ActivityScope
import com.kotlin.base.injection.module.ActivityModule
import com.kotlin.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * @author  桂雁彬
 * @date 2019/3/15.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class, LifecycleProviderModule::class))
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}
