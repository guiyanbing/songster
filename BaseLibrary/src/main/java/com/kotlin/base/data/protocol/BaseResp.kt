package com.kotlin.base.data.protocol

/**
 * @author  桂雁彬
 * @date 2019/3/15.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：
 *  能用响应对象
    @status:响应状态码
    @message:响应文字消息
    @data:具体响应业务对象
 */
data class BaseResp<out T>(val status:Int, val message:String, val data:T)