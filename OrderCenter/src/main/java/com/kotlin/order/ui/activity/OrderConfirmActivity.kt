package com.kotlin.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.ext.onClick
import com.kotlin.base.ext.setVisible
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.R
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.event.SelectAddressEvent
import com.kotlin.order.injection.component.DaggerOrderComponent
import com.kotlin.order.injection.module.OrderModule
import com.kotlin.order.presenter.OrderConfirmPresenter
import com.kotlin.order.presenter.view.OrderConfirmView
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/*
    订单确认页
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity:BaseMvpActivity<OrderConfirmPresenter>(),OrderConfirmView {
    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId:Int = 0

    private lateinit var mAdapter:OrderGoodsAdapter
    private var mCurrentOrder:Order? = null

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        mOrderId=intent.getIntExtra(ProviderConstant.KEY_ORDER_ID,0)

        initView()
        initObserve()
        loadData()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }
        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }

        mSubmitOrderBtn.onClick {
            mCurrentOrder?.let {
                mPresenter.submitOrder(it)
            }
        }

        //订单中商品列表
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    /*
        初始化选择收货人事件监听
     */
    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
                .subscribe{
                    t: SelectAddressEvent ->
                    run {
                        mCurrentOrder?.let {
                            it.shipAddress = t.address
                        }
                        updateAddressView()
                    }
                }
                .registerInBus(this)

    }

    /*
        加载订单数据
     */
    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    /*
        获取订单回调
     */
    override fun onGetOrderByIdResult(result: Order) {
        mCurrentOrder = result
        mAdapter.setData(result.orderGoodsList)
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"

        updateAddressView()
    }

    /*
        取消事件监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /*
        根据是否有收货人信息，更新视图
     */
    private fun updateAddressView() {
        mCurrentOrder?.let {
            if (it.shipAddress == null){
                mSelectShipTv.setVisible(true)
                mShipView.setVisible(false)
            }else{
                mSelectShipTv.setVisible(false)
                mShipView.setVisible(true)

                mShipNameTv.text = it.shipAddress!!.shipUserName + "  "+
                        it.shipAddress!!.shipUserMobile
                mShipAddressTv.text = it.shipAddress!!.shipAddress
            }
        }
    }

    /*
        提交订单回调
     */
    override fun onSubmitOrderResult(result: Boolean) {
        toast("订单提交成功")
        ARouter.getInstance().build(RouterPath.PaySDK.PATH_PAY)
                .withInt(ProviderConstant.KEY_ORDER_ID,mCurrentOrder!!.id)
                .withLong(ProviderConstant.KEY_ORDER_PRICE,mCurrentOrder!!.totalPrice)
                .navigation()
        finish()
    }

}
