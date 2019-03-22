package com.kotlin.provider.router

/**
 * @author  桂雁彬
 * @date 2019/3/22.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：
 */

object RouterPath{
    //用户模块
    class UserCenter{
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    //订单模块
    class OrderCenter{
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }

    //支付模块
    class PaySDK{
        companion object {
            const val PATH_PAY = "/paySDK/pay"
        }
    }

    //消息模块
    class MessageCenter{
        companion object {
            const val PATH_MESSAGE_PUSH = "/messageCenter/push"
            const val PATH_MESSAGE_ORDER = "/messageCenter/order"
        }
    }
}