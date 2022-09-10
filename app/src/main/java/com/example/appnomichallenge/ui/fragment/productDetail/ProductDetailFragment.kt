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

    private var mCategories: Categories? = null

    lateinit var mProductsAdapter: ProductsAdapter

    private var mProductsList: MutableList<Products> = arrayListOf()

    override fun initView(savedInstanceState: Bundle?) {
        setProductsAdapter()
        getViewModel()?.productsList?.observe(this) {
            when(it) {
                is Resource.Loading -> {
                    getViewBinding()?.progressBar?.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                    setProductsList(it.data!!)
                    //mProductsList.sortedBy { it.price }

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
            mCategories = safeArgs.categories
        }
    }

    override fun initLogic() {
        super.initLogic()
        getViewModel()!!.getProducts(mCategories?.categoryId!!)
    }

    private fun setProductsAdapter() {
        mProductsAdapter = ProductsAdapter(requireActivity(),mProductsList)
        mProductsAdapter.setCallBack(object : ProductsAdapter.CallBack{
            override fun onClickItem(position: Int, products: Products) {

            }

        })
        getViewBinding()?.rvProducts?.adapter = mProductsAdapter
    }

    private fun setProductsList(products: List<Products>) {
        mProductsList.clear()
        mProductsList.addAll(products)
        mProductsAdapter.notifyDataSetChanged()
    }
}