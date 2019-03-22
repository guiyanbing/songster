package com.kotlin.base.injection.module

import android.app.Activity
import android.content.Context
import com.kotlin.base.common.BaseApplication
import com.kotlin.base.injection.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/*
    Activity级别Module
 */
@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun provideActivity(): Activity {
        return this.activity
    }
}
