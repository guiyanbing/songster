package com.kotlin.user.injection.module

import com.kotlin.user.service.UploadService
import com.kotlin.user.service.UserService
import com.kotlin.user.service.impl.UploadServiceImpl
import com.kotlin.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/*
    上传Module
 */
@Module
class UploadModule {

    @Provides
    fun provideUploadService(uploadService: UploadServiceImpl): UploadService {
        return uploadService
    }

}
