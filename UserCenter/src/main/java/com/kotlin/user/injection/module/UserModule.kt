package com.kotlin.user.injection.module

import com.kotlin.user.service.UserService
import com.kotlin.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

/*
    用户模块Module
 */
@Module
class UserModule {

    @Provides
    fun provideUserService(userService: UserServiceImpl): UserService {
        return userService
    }

}

