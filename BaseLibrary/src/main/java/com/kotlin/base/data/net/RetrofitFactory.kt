package com.kotlin.base.data.net

import com.kotlin.base.common.BaseConstant
import com.kotlin.base.utils.AppPrefsUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author  桂雁彬
 * @date 2019/3/15.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：Retrofit工厂，单例
 */
class RetrofitFactory private constructor(){

    /*
        单例实现
     */
    companion object {
        val instance:RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val interceptor: Interceptor
    private val retrofit: Retrofit

    //初始化
    init {
        //通用拦截
        interceptor = Interceptor {
            chain -> val request = chain.request()
                .newBuilder()
                .addHeader("Content_Type","application/json")
                .addHeader("charset","UTF-8")
                .addHeader("token", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                .build()

            chain.proceed(request)
        }

        //Retrofit实例化
        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    /*
        OKHttp创建
     */
    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor())
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    /*
        日志拦截器
     */
    private fun initLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /*
        具体服务实例化
     */
    fun <T> create(service:Class<T>):T{
        return retrofit.create(service)
    }
}