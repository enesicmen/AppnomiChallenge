package com.example.appnomichallenge.ui.fragment.categories

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnomichallenge.R
import com.example.appnomichallenge.data.model.Categories
import com.example.appnomichallenge.databinding.RowCategoriesBinding
import com.example.appnomichallenge.ui.base.helper.UIFontSize
import com.example.appnomichallenge.ui.ext.load
import com.example.appnomichallenge.util.Util

class CategoriesAdapter(
    activity: Activity,
    categories: List<Categories>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val RES_ITEM_ROW: Int = R.layout.row_categories
    private var mInflater: LayoutInflater? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mActivity: Activity? = null
    private var mCallback: CallBack? = null
    private var mCategoriesList: List<Categories>

    init {
        mActivity = activity
        mInflater = LayoutInflater.from(mActivity)
        mLayoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        mCategoriesList = categories
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
        val mCategories: Categories =
            mCategoriesList[position]

        (holder as CategoriesViewHolder).onBind(
            position,
            mCategories
        )
    }

    override fun getItemCount(): Int {
        return mCategoriesList.size
    }

    fun setCallBack(callBack: CallBack?) {
        mCallback = callBack
    }

    inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowCategoriesBinding.bind(itemView)

        init {
            setFontSize()
        }

        fun onBind(position: Int, categories: Categories) {

            binding.tvName.text = categories.name
            binding.ivCategory.load(categories.icon)
            binding.tvCreateDate.text = Util.getDateFormat(categories.createDate!!)

            binding.root.setOnClickListener(
                View.OnClickListener {
                    mCallback?.onClickItem(position, categories)
                }
            )
        }

        private fun setFontSize(){
            binding.tvName.textSize = UIFontSize.FONT_SIZE_18
            binding.tvCreateDate.textSize = UIFontSize.FONT_SIZE_13
        }
    }

    interface CallBack {
        fun onClickItem(position: Int, categories: Categories)
    }
}