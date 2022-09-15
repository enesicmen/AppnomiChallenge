package com.example.appnomichallenge.ui.fragment.productDetail

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnomichallenge.R
import com.example.appnomichallenge.data.model.ProductDetailImage
import com.example.appnomichallenge.databinding.RowProductDetailImageBinding
import com.example.appnomichallenge.ui.ext.load

class ProductDetailImageAdapter (
    activity: Activity,
    productImage:List<ProductDetailImage>
): RecyclerView.Adapter<RecyclerView.ViewHolder> (){

    private val RES_ITEM_ROW: Int = R.layout.row_product_detail_image
    private var mInflater: LayoutInflater? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mActivity: Activity? = null
    private var mProductImageList: List<ProductDetailImage>

    init {
        mActivity = activity
        mInflater = LayoutInflater.from(mActivity)
        mLayoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        mProductImageList = productImage
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.layoutManager = mLayoutManager
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val item: View = mInflater?.inflate(RES_ITEM_ROW, parent, false)!!
        return ProductsImageViewHolder(item)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mProductImage: ProductDetailImage =
            mProductImageList[position]

        (holder as ProductDetailImageAdapter.ProductsImageViewHolder).onBind(
            position,
            mProductImage
        )
    }

    override fun getItemCount(): Int {
        return mProductImageList.size
    }

    inner class ProductsImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowProductDetailImageBinding.bind(itemView)

        init {
            setFontSize()
        }

        fun onBind(position: Int, productImage: ProductDetailImage) {
            binding.ivImage.load(productImage.imageUrl)
        }
        private fun setFontSize(){
        }
    }

}