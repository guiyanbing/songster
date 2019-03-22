package com.kotlin.user.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.user.data.respository.UploadRepository
import com.kotlin.user.service.UploadService
import rx.Observable
import javax.inject.Inject

/*
    上传业务实现类
 */
class UploadServiceImpl @Inject constructor(): UploadService {

    @Inject
    lateinit var repository: UploadRepository

    override fun getUploadToken(): Observable<String> {
       return repository.getUploadToken().convert()
    }

}
