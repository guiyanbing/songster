package com.kotlin.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.R
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.injection.component.DaggerOrderComponent
import com.kotlin.order.injection.module.OrderModule
import com.kotlin.order.presenter.OrderDetailPresenter
import com.kotlin.order.presenter.view.OrderDetailView
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_detail.*

/*
    订单详情
 */
@Route(path = RouterPath.MessageCenter.PATH_MESSAGE_ORDER)
class OrderDetailActivity : BaseMvpActivity<OrderDetailPresenter>(), OrderDetailView {
    private lateinit var mAdapter: OrderGoodsAdapter

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        initView()
        loadData()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    /*
        加载数据
     */
    private fun loadData() {
        mPresenter.getOrderById(intent.getIntExtra(ProviderConstant.KEY_ORDER_ID,-1))
    }

    /*
        获取订单回调
     */
    override fun onGetOrderByIdResult(result: Order) {
        mShipNameTv.setContentText(result.shipAddress!!.shipUserName)
        mShipMobileTv.setContentText(result.shipAddress!!.shipUserMobile)
        mShipAddressTv.setContentText(result.shipAddress!!.shipAddress)
        mTotalPriceTv.setContentText(YuanFenConverter.changeF2YWithUnit(result.totalPrice))

        mAdapter.setData(result.orderGoodsList)
    }

}
