package com.kotlin.order.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kotlin.base.ext.loadUrl
import com.kotlin.base.ext.onClick
import com.kotlin.base.ext.setVisible
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.R
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import com.kotlin.order.data.protocol.Order
import kotlinx.android.synthetic.main.layout_order_item.view.*
import org.jetbrains.anko.dip

/*
    订单列表数据适配
 */
class OrderAdapter(context: Context) : BaseRecyclerViewAdapter<Order, OrderAdapter.ViewHolder>(context) {

    var listener: OnOptClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_order_item,
                        parent,
                        false)
        return ViewHolder(view)
    }

    /*
        绑定数据
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        var mTotalCount = 0
        if (model.orderGoodsList.size == 1){//单个商品
            holder.itemView.mSingleGoodsView.setVisible(true)
            holder.itemView.mMultiGoodsView.setVisible(false)//单个商品隐藏多个商品视图
            val orderGoods = model.orderGoodsList[0]
            holder.itemView.mGoodsIconIv.loadUrl(orderGoods.goodsIcon)//商品图标
            holder.itemView.mGoodsDescTv.text = orderGoods.goodsDesc//商品描述
            holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(orderGoods.goodsPrice)//商品价格
            holder.itemView.mGoodsCountTv.text = "x${orderGoods.goodsCount}"//商品数量
            mTotalCount = orderGoods.goodsCount

        }else{//多个商品视图展示
            holder.itemView.mSingleGoodsView.setVisible(false)//多个商品隐藏单个商品视图
            holder.itemView.mMultiGoodsView.setVisible(true)
            holder.itemView.mMultiGoodsView.removeAllViews()
            for (orderGoods in model.orderGoodsList){//动态添加商品视图
                val imageView = ImageView(mContext)
                val lp = ViewGroup.MarginLayoutParams(mContext.dip(60.0f),mContext.dip(60.0f))
                lp.rightMargin = mContext.dip(15f)
                imageView.layoutParams = lp
                imageView.loadUrl(orderGoods.goodsIcon)

                holder.itemView.mMultiGoodsView.addView(imageView)

                mTotalCount += orderGoods.goodsCount
            }
        }

        holder.itemView.mOrderInfoTv.text = "合计${mTotalCount}件商品，总价${YuanFenConverter.changeF2YWithUnit(model.totalPrice)}"


        when(model.orderStatus){//根据订单状态设置颜色、文案及对应操作按钮
            OrderStatus.ORDER_WAIT_PAY -> {
                holder.itemView.mOrderStatusNameTv.text = "待支付"
                holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_red))
                setOptVisible(false,true,true,holder)
            }
            OrderStatus.ORDER_WAIT_CONFIRM -> {
                holder.itemView.mOrderStatusNameTv.text = "待收货"
                holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_blue))
                setOptVisible(true,false,true,holder)
            }

            OrderStatus.ORDER_COMPLETED -> {
                holder.itemView.mOrderStatusNameTv.text = "已完成"
                holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_yellow))
                setOptVisible(false,false,false,holder)
            }

            OrderStatus.ORDER_CANCELED -> {
                holder.itemView.mOrderStatusNameTv.text = "已取消"
                holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_gray))
                setOptVisible(false,false,false,holder)
            }
        }

        //设置确认收货点击事件
        holder.itemView.mConfirmBtn.onClick {
            listener?.let {
                it.onOptClick(OrderConstant.OPT_ORDER_CONFIRM,model)
            }
        }

        //设置支付订单点击事件
        holder.itemView.mPayBtn.onClick {
            listener?.let {
                it.onOptClick(OrderConstant.OPT_ORDER_PAY,model)
            }
        }

        //设置取消订单点击事件
        holder.itemView.mCancelBtn.onClick {
            listener?.let {
                it.onOptClick(OrderConstant.OPT_ORDER_CANCEL,model)
            }
        }



    }

    /*
        设置操作按钮显示或隐藏
     */
    private fun setOptVisible(confirmVisible: Boolean, waitPayVisible: Boolean, cancelVisible: Boolean,holder: ViewHolder) {
        holder.itemView.mConfirmBtn.setVisible(confirmVisible)
        holder.itemView.mPayBtn.setVisible(waitPayVisible)
        holder.itemView.mCancelBtn.setVisible(cancelVisible)

        if (confirmVisible or waitPayVisible or cancelVisible){
            holder.itemView.mBottomView.setVisible(true)
        }else{
            holder.itemView.mBottomView.setVisible(false)
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnOptClickListener {
        fun onOptClick(optType:Int,order:Order)
    }

}
