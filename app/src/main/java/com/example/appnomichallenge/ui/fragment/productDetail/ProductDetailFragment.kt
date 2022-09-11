package com.example.appnomichallenge.ui.fragment.productDetail

import android.os.Bundle
import android.view.View
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Categories
import com.example.appnomichallenge.data.model.Products
import com.example.appnomichallenge.databinding.FragmentProductsDetailBinding
import com.example.appnomichallenge.ui.base.BaseFragment
import com.example.appnomichallenge.ui.fragment.products.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailFragment:
    BaseFragment<FragmentProductsDetailBinding, ProductDetailViewModel>(){

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun setViewModelClass() =
        ProductDetailViewModel::class.java

    override fun setViewBinding(): FragmentProductsDetailBinding =
        FragmentProductsDetailBinding.inflate(layoutInflater)
}