package com.example.appnomichallenge.ui.fragment.products

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnomichallenge.R
import com.example.appnomichallenge.data.model.ProductsImage
import com.example.appnomichallenge.databinding.RowProductsImageBinding
import com.example.appnomichallenge.ui.ext.load

class ProductsImageAdapter(
    activity: Activity,
    images: List<ProductsImage>
): RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    private val RES_ITEM_ROW: Int = R.layout.row_products_image
    private var mInflater: LayoutInflater? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mActivity: Activity? = null
    private var mProductsImageList: List<ProductsImage>

    init {
        mActivity = activity
        mInflater = LayoutInflater.from(mActivity)
        mLayoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false)
        mProductsImageList = images
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
        val mProductsImage: ProductsImage =
            mProductsImageList[position]

        (holder as ProductsImageAdapter.ProductsImageViewHolder).onBind(
            position,
            mProductsImage
        )
    }

    override fun getItemCount(): Int {
        return mProductsImageList.size
    }

    inner class ProductsImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowProductsImageBinding.bind(itemView)

        fun onBind(position: Int, productsImage: ProductsImage) {

            binding.ivImage.load(productsImage.imageUrl)
        }
    }
}