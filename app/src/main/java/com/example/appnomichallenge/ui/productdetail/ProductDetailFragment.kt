package com.example.appnomichallenge.ui.productdetail

import android.os.Bundle
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Product
import com.example.appnomichallenge.databinding.FragmentProductsDetailBinding
import com.example.appnomichallenge.ui.common.BaseFragment
import com.example.appnomichallenge.ui.common.ext.load
import com.example.appnomichallenge.ui.common.ext.setHtmlText
import com.example.appnomichallenge.ui.common.ext.setVisibility
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
                is Resource.Loading -> getViewBinding()?.progressBar?.setVisibility(isVisible = true)

                is Resource.Success -> {
                    getViewBinding()?.progressBar?.setVisibility(isVisible = false)
                    mProductDetail = it.data
                    setData()
                }
                is Resource.Error -> getViewBinding()?.progressBar?.setVisibility(isVisible = false)
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
            cwProductDetail.setVisibility(isVisible = true)
            tvTitle.text = mProductDetail?.title
            tvPriceValue.text = mProductDetail?.price.toString()
            tvCreateDate.text = DateUtils.getDateFormat(mProductDetail?.createDate!!)
            tvPriceCurrency.text = mProductDetail?.currency
            ivImage.load(mProductDetail?.featuredImage?.normal)
            tvDescription.setHtmlText(mProductDetail?.description)

            if(mProductDetail?.campaignPrice != null){
                tvCampaignPriceValue.text = mProductDetail?.campaignPrice.toString()
                tvCampaignPriceCurrency.text = mProductDetail?.currency
            } else {
                tvCampaignPriceValue.setVisibility(isVisible = false)
                tvCampaignPriceCurrency.setVisibility(isVisible = false)
                tvCampaignPrice.setVisibility(isVisible = false)
            }

            if(mProductDetail?.stock!! <= 0){
                ivOutOfStock.setVisibility(isVisible = true)
                btnBasket.setVisibility(isVisible = false)
            } else {
                ivOutOfStock.setVisibility(isVisible = false)
                btnBasket.setVisibility(isVisible = true)
            }
        }
    }
}