package com.example.appnomichallenge.ui.fragment.products

import android.os.Bundle
import android.view.View
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Products
import com.example.appnomichallenge.databinding.FragmentProductsBinding
import com.example.appnomichallenge.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment:
    BaseFragment<FragmentProductsBinding, ProductsViewModel>(){

    private var mCategoryId: String? = null

    lateinit var mProductsAdapter: ProductsAdapter

    private var mProductsList: MutableList<Products> = arrayListOf()

    private var mSortedProductsList: MutableList<Products> = arrayListOf()

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
                }
                is Resource.Error -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                }
            }
        }
    }

    override fun setViewModelClass() =
        ProductsViewModel::class.java

    override fun setViewBinding(): FragmentProductsBinding =
        FragmentProductsBinding.inflate(layoutInflater)

    override fun readDataFromArguments() {
        super.readDataFromArguments()
        arguments?.let {
            val safeArgs = ProductsFragmentArgs.fromBundle(it)
            mCategoryId = safeArgs.categoryId
        }
    }

    override fun initLogic() {
        super.initLogic()
        getViewModel()!!.getProducts(mCategoryId!!)
    }

    private fun setProductsAdapter() {
        mProductsAdapter = ProductsAdapter(requireActivity(),mSortedProductsList)
        mProductsAdapter.setCallBack(object : ProductsAdapter.CallBack{
            override fun onClickItem(position: Int, products: Products) {}
        })
        getViewBinding()?.rvProducts?.adapter = mProductsAdapter
    }

    private fun sortProductList() {
        val sortedProductsList = mProductsList.sortedByDescending { it.price }
        mSortedProductsList.clear()
        mSortedProductsList.addAll(sortedProductsList)
        mProductsAdapter.notifyDataSetChanged()
    }

    private fun setProductsList(products: List<Products>) {
        mProductsList.clear()
        mProductsList.addAll(products)
        sortProductList()
        mProductsAdapter.notifyDataSetChanged()
    }
}