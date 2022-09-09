package com.example.appnomichallenge.ui.fragment.products

import android.os.Bundle
import com.example.appnomichallenge.databinding.FragmentProductsBinding
import com.example.appnomichallenge.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment:
    BaseFragment<FragmentProductsBinding, ProductsViewModel>(){
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun setViewModelClass() =
        ProductsViewModel::class.java

    override fun setViewBinding(): FragmentProductsBinding {
        TODO("Not yet implemented")
    }
}