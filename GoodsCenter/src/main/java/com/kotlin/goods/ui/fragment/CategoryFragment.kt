package com.kotlin.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.setVisible
import com.kotlin.base.ext.startLoading
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.goods.R
import com.kotlin.goods.common.GoodsConstant

import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.injection.component.DaggerCategoryComponent
import com.kotlin.goods.injection.module.CategoryModule
import com.kotlin.goods.presenter.CategoryPresenter
import com.kotlin.goods.presenter.view.CategoryView
import com.kotlin.goods.ui.activity.GoodsActivity
import com.kotlin.goods.ui.adapter.SecondCategoryAdapter
import com.kotlin.goods.ui.adapter.TopCategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * @author  桂雁彬
 * @date 2019/3/19.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：
 */
class CategoryFragment :BaseMvpFragment<CategoryPresenter>(), CategoryView {

    //一级分类Adapter
    lateinit var topAdapter: TopCategoryAdapter
    //二级分类Adapter
    lateinit var secondAdapter: SecondCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
         return inflater.inflate(R.layout.fragment_category, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }


    /*
           初始化视图
        */
    private fun initView() {

        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(this!!.context!!)
        mTopCategoryRv.adapter = topAdapter
        //单项点击事件
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()

                loadData(item.id)
            }
        })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(this!!.context!!)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                startActivity<GoodsActivity>(GoodsConstant.KEY_CATEGORY_ID  to item.id)
            }
        })

    }


    /*
           加载数据
        */
    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }

        mPresenter.getCategory(parentId)

    }

    /*
      获取商品分类 回调
   */
    override fun onGetCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            } else {
                secondAdapter.setData(result)
                mTopCategoryIv.setVisible(true)
                mCategoryTitleTv.setVisible(true)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            //没有数据
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(mActivityComponent).categoryModule(CategoryModule()).build().inject(this)
        mPresenter.mView = this
    }


}