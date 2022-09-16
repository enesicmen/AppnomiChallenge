package com.example.appnomichallenge.ui.fragment.categories

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnomichallenge.R
import com.example.appnomichallenge.data.model.Category
import com.example.appnomichallenge.databinding.RowCategoriesBinding
import com.example.appnomichallenge.ui.ext.load
import com.example.appnomichallenge.util.DateUtils

class CategoriesAdapter(
    activity: Activity,
    categories: List<Category>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val RES_ITEM_ROW: Int = R.layout.row_categories
    private var mInflater: LayoutInflater? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mActivity: Activity? = null
    private var mCallback: CallBack? = null
    private var mCategoryList: List<Category>

    init {
        mActivity = activity
        mInflater = LayoutInflater.from(mActivity)
        mLayoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        mCategoryList = categories
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.layoutManager = mLayoutManager
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val item: View = mInflater?.inflate(RES_ITEM_ROW, parent, false)!!
        return CategoriesViewHolder(item)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mCategory: Category =
            mCategoryList[position]

        (holder as CategoriesViewHolder).onBind(
            position,
            mCategory
        )
    }

    override fun getItemCount(): Int {
        return mCategoryList.size
    }

    fun setCallBack(callBack: CallBack?) {
        mCallback = callBack
    }

    inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowCategoriesBinding.bind(itemView)

        fun onBind(position: Int, category: Category) {

            binding.tvName.text = category.name
            binding.ivCategory.load(category.icon)
            binding.tvCreateDate.text = DateUtils.getDateFormat(category.createDate!!)

            binding.root.setOnClickListener(
                View.OnClickListener {
                    mCallback?.onClickItem(position, category.categoryId!!)
                }
            )
        }
    }

    interface CallBack {
        fun onClickItem(position: Int, categoryId: String)
    }
}