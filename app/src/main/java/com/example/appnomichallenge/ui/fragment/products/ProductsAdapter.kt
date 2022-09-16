package com.example.appnomichallenge.ui.fragment.products

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnomichallenge.R
import com.example.appnomichallenge.data.model.Product
import com.example.appnomichallenge.databinding.RowProductsBinding
import com.example.appnomichallenge.ui.ext.load
import com.example.appnomichallenge.util.DateUtils

class ProductsAdapter(
    activity: Activity,
    products: List<Product>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val RES_ITEM_ROW: Int = R.layout.row_products
    private var mInflater: LayoutInflater? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mActivity: Activity? = null
    private var mCallback: CallBack? = null
    private var mProductList: List<Product>

    init {
        mActivity = activity
        mInflater = LayoutInflater.from(mActivity)
        mLayoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        mProductList = products
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.layoutManager = mLayoutManager
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val item: View = mInflater?.inflate(RES_ITEM_ROW, parent, false)!!
        return ProductsViewHolder(item)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mProduct: Product =
            mProductList[position]

        (holder as ProductsAdapter.ProductsViewHolder).onBind(
            position,
            mProduct
        )
    }

    override fun getItemCount(): Int {
        return mProductList.size
    }

    fun setCallBack(callBack: CallBack?) {
        mCallback = callBack
    }

    inner class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowProductsBinding.bind(itemView)

        fun onBind(position: Int, product: Product) {

            binding.tvTitle.text = product.title
            binding.tvPriceValue.text = product.price.toString()
            binding.tvPriceCurrency.text = product.currency
            binding.tvCreateDate.text = DateUtils.getDateFormat(product.createDate!!)
            binding.ivImage.load(product.featuredImage?.thumbnail)

            if(product.campaignPrice != null) {

                binding.tvCampaignPriceValue.text = product.campaignPrice.toString()
                binding.tvCampaignPriceCurrency.text = product.currency
            }else {
                binding.tvCampaignPriceValue.visibility = View.GONE
                binding.tvCampaignPriceCurrency.visibility = View.GONE
                binding.tvCampaignPrice.visibility = View.GONE
            }

            binding.root.setOnClickListener(
                View.OnClickListener {
                    mCallback?.onClickItem(position, product,product.id!!)
                }
            )
        }
    }

    interface CallBack {
        fun onClickItem(position: Int, product: Product, productId: String)
    }
}