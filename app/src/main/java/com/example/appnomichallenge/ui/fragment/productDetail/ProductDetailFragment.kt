package com.example.appnomichallenge.ui.fragment.productDetail

import android.os.Bundle
import android.view.View
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Product
import com.example.appnomichallenge.databinding.FragmentProductsDetailBinding
import com.example.appnomichallenge.ui.base.BaseFragment
import com.example.appnomichallenge.ui.ext.load
import com.example.appnomichallenge.ui.ext.setHtmlText
import com.example.appnomichallenge.util.DateUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailFragment:
    BaseFragment<FragmentProductsDetailBinding, ProductDetailViewModel>(){

    private var mProductId: String? = null

    private var mProductDetail: Product? = null

    override fun setViewModelClass() = ProductDetailViewModel::class.java

    override fun setViewBinding(): FragmentProductsDetailBinding =
        FragmentProductsDetailBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {
        getViewModel()?.productDetailList?.observe(this) {
            when(it) {
                is Resource.Loading -> getViewBinding()?.progressBar?.visibility = View.VISIBLE

                is Resource.Success -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                    mProductDetail = it.data
                    setData()
                }
                is Resource.Error -> getViewBinding()?.progressBar?.visibility = View.GONE
            }
        }
    }

    override fun readDataFromArguments() {
        super.readDataFromArguments()
        arguments?.let {
            val safeArgs = ProductDetailFragmentArgs.fromBundle(it)
            mProductId = safeArgs.productId
        }
    }

    override fun initLogic() {
        super.initLogic()
        getViewModel()!!.getProductDetail(productId = mProductId!!)
    }

    private fun setData(){
        getViewBinding()?.apply {
            tvTitle.text = mProductDetail?.title
            tvPriceValue.text = mProductDetail?.price.toString()
            tvCreateDate.text = DateUtils.getDateFormat(mProductDetail?.createDate!!)
            tvPriceCurrency.text = mProductDetail?.currency
            ivImage.load(mProductDetail?.featuredImage?.normal)
            tvDescription.setHtmlText(mProductDetail?.description)

            if(mProductDetail?.campaignPrice != null){
                tvCampaignPriceValue.text = mProductDetail?.campaignPrice.toString()
                tvCampaignPriceCurrency.text = mProductDetail?.currency
            }else {
                tvCampaignPriceValue.visibility = View.GONE
                tvCampaignPriceCurrency.visibility = View.GONE
                tvCampaignPrice.visibility = View.GONE
            }

            if(mProductDetail?.stock!! <= 0){
                ivOutOfStock.visibility = View.VISIBLE
                btnBasket.visibility = View.GONE
            }else {
                ivOutOfStock.visibility = View.GONE
                btnBasket.visibility = View.VISIBLE
            }
        }
    }
}