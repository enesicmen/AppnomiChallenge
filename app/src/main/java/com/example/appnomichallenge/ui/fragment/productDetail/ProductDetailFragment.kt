package com.example.appnomichallenge.ui.fragment.productDetail

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.ProductDetail
import com.example.appnomichallenge.data.model.Products
import com.example.appnomichallenge.databinding.FragmentProductsDetailBinding
import com.example.appnomichallenge.ui.base.BaseFragment
import com.example.appnomichallenge.ui.fragment.products.ProductsAdapter
import com.example.appnomichallenge.ui.fragment.products.ProductsFragmentDirections
import com.example.appnomichallenge.ui.fragment.products.ProductsImageAdapter
import com.example.appnomichallenge.util.Util
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailFragment:
    BaseFragment<FragmentProductsDetailBinding, ProductDetailViewModel>(){

    private var mProductId: String? = null

    private var mProductDetail: ProductDetail? = null

    lateinit var mProductImageAdapter: ProductDetailImageAdapter

    override fun initView(savedInstanceState: Bundle?) {

        setFontSize()
        getViewModel()?.productDetailList?.observe(this) {
            when(it) {
                is Resource.Loading -> {
                    getViewBinding()?.progressBar?.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                    mProductDetail = it.data
                    setProductDetailImageAdapter()
                    setData()

                }
                is Resource.Error -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                }
            }
        }
    }

    override fun setViewModelClass() =
        ProductDetailViewModel::class.java

    override fun setViewBinding(): FragmentProductsDetailBinding =
        FragmentProductsDetailBinding.inflate(layoutInflater)

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

    private fun setProductDetailImageAdapter() {
        mProductImageAdapter = ProductDetailImageAdapter(requireActivity(),mProductDetail?.productsImage?: emptyList())
        getViewBinding()?.rvImages?.adapter = mProductImageAdapter
        mProductImageAdapter.notifyDataSetChanged()
    }

    private fun setData(){
        getViewBinding()?.apply {
            tvTitle.text = mProductDetail?.title
            tvPriceValue.text = mProductDetail?.price.toString()
            tvCreateDate.text = Util.getDateFormat(mProductDetail?.createDate!!)
            tvPriceCurrency.text = mProductDetail?.currency
            tvDescription.text = Html.fromHtml(Html.fromHtml(mProductDetail?.description).toString())


            if(mProductDetail?.campaignPrice != null){
                tvCampaignPriceValue.text = mProductDetail?.campaignPrice.toString()
                tvCampaignPriceCurrency.text = mProductDetail?.currency
            }else {
                tvCampaignPriceValue.visibility = View.GONE
                tvCampaignPriceCurrency.visibility = View.GONE
                tvCampaignPrice.visibility = View.GONE
            }
        }
    }

    private fun setFontSize() {

    }
}