package com.example.appnomichallenge.ui.fragment.products

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnomichallenge.R
import com.example.appnomichallenge.data.model.Products
import com.example.appnomichallenge.data.model.ProductsImage
import com.example.appnomichallenge.databinding.RowProductsBinding
import com.example.appnomichallenge.ui.base.helper.UIFontSize
import com.example.appnomichallenge.util.Util

class ProductsAdapter(
    activity: Activity,
    products: List<Products>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val RES_ITEM_ROW: Int = R.layout.row_products
    private var mInflater: LayoutInflater? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mActivity: Activity? = null
    private var mCallback: CallBack? = null
    private var mProductsList: List<Products>

    init {
        mActivity = activity
        mInflater = LayoutInflater.from(mActivity)
        mLayoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        mProductsList = products
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
        val mProducts: Products =
            mProductsList[position]

        (holder as ProductsAdapter.ProductsViewHolder).onBind(
            position,
            mProducts
        )
    }

    override fun getItemCount(): Int {
        return mProductsList.size
    }

    fun setCallBack(callBack: CallBack?) {
        mCallback = callBack
    }

    inner class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowProductsBinding.bind(itemView)
        lateinit var mProductsImageAdapter: ProductsImageAdapter

        init {
            setFontSize()
        }

        fun onBind(position: Int, products: Products) {

            binding.tvTitle.text = products.title
            binding.tvPriceValue.text = products.price.toString()
            binding.tvPriceCurrency.text = products.currency
            binding.tvCreateDate.text = Util.getDateFormat(products.createDate!!)

            if(products.campaignPrice != null) {

                binding.tvCampaignPriceValue.text = products.campaignPrice.toString()
                binding.tvCampaignPriceCurrency.text = products.currency
            }else {
                binding.tvCampaignPriceValue.visibility = View.GONE
                binding.tvCampaignPriceCurrency.visibility = View.GONE
                binding.tvCampaignPrice.visibility = View.GONE
            }

            val productsImage = products.productsImage
            setProductsImageAdapter(productsImage)

            binding.root.setOnClickListener(
                View.OnClickListener {
                    mCallback?.onClickItem(position, products,products.id!!)
                }
            )
        }

        private fun setProductsImageAdapter(images: List<ProductsImage>) {
            mProductsImageAdapter = ProductsImageAdapter(mActivity!!,images ?: emptyList())
            binding.rvImages.adapter = mProductsImageAdapter
        }

        private fun setFontSize(){
            binding.tvTitle.textSize = UIFontSize.FONT_SIZE_15
            binding.tvPrice.textSize = UIFontSize.FONT_SIZE_13
            binding.tvPriceValue.textSize = UIFontSize.FONT_SIZE_13
            binding.tvPriceCurrency.textSize = UIFontSize.FONT_SIZE_13
            binding.tvCampaignPrice.textSize = UIFontSize.FONT_SIZE_13
            binding.tvCampaignPriceValue.textSize = UIFontSize.FONT_SIZE_13
            binding.tvCampaignPriceCurrency.textSize = UIFontSize.FONT_SIZE_13
            binding.tvCreateDate.textSize = UIFontSize.FONT_SIZE_13
        }
    }

    interface CallBack {
        fun onClickItem(position: Int, products: Products,productId: String)
    }
}