package com.example.appnomichallenge.ui.fragment.categories

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnomichallenge.R
import com.example.appnomichallenge.data.model.CategoriesModel
import com.example.appnomichallenge.databinding.RowCategoriesBinding
import com.example.appnomichallenge.ui.base.helper.UIFontSize
import com.example.appnomichallenge.ui.ext.load
import com.example.appnomichallenge.util.Util
import com.squareup.picasso.Picasso

class CategoriesAdapter(
    activity: Activity,
    categories: List<CategoriesModel>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val RES_ITEM_ROW: Int = R.layout.row_categories
    private var mInflater: LayoutInflater? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mActivity: Activity? = null
    private var mCallback: CallBack? = null
    private var mCategoriesList: List<CategoriesModel>

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
        val mCategories: CategoriesModel =
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

        fun onBind(position: Int, categoriesModel: CategoriesModel) {

            binding.tvName.text = categoriesModel.name
            binding.ivCategory.load(categoriesModel.icon)
            binding.tvCreateDate.text = Util.getDateFormat(categoriesModel.createDate!!)
            binding.tvTotalProductsValue.text = categoriesModel.totalProducts.toString()

        }

        private fun setFontSize(){
            binding.tvName.textSize = UIFontSize.FONT_SIZE_18
            binding.tvTotalProducts.textSize = UIFontSize.FONT_SIZE_13
            binding.tvTotalProductsValue.textSize = UIFontSize.FONT_SIZE_13
            binding.tvCreateDate.textSize = UIFontSize.FONT_SIZE_13
        }
    }

    interface CallBack {
        fun onClickItem(position: Int, categoriesModel: CategoriesModel)
    }
}