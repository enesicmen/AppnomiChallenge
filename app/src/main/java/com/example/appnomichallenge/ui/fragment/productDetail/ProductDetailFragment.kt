package com.example.appnomichallenge.ui.fragment.productDetail

import android.os.Bundle
import com.example.appnomichallenge.databinding.FragmentProductsDetailBinding
import com.example.appnomichallenge.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailFragment:
    BaseFragment<FragmentProductsDetailBinding, ProductDetailViewModel>(){
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun setViewModelClass() =
        ProductDetailViewModel::class.java

    override fun setViewBinding(): FragmentProductsDetailBinding {
        TODO("Not yet implemented")
    }
}