package com.example.appnomichallenge.ui.fragment.products

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Product
import com.example.appnomichallenge.databinding.FragmentProductsBinding
import com.example.appnomichallenge.ui.base.BaseFragment
import com.example.appnomichallenge.ui.ext.setVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment:
    BaseFragment<FragmentProductsBinding, ProductsViewModel>(){

    private var mCategoryId: String? = null

    lateinit var mProductsAdapter: ProductsAdapter

    private var mProductList: MutableList<Product> = mutableListOf()

    override fun setViewModelClass() = ProductsViewModel::class.java

    override fun setViewBinding(): FragmentProductsBinding =
        FragmentProductsBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {
        initProductsAdapter()
        getViewModel()?.productList?.observe(this) {
            when(it) {
                is Resource.Loading ->getViewBinding()?.progressBar?.setVisibility(isVisible = true)

                is Resource.Success -> {
                    getViewBinding()?.progressBar?.setVisibility(isVisible = false)
                    setProductsList(it.data!!)
                }
                is Resource.Error -> getViewBinding()?.progressBar?.setVisibility(isVisible = false)
            }
        }

    }

    override fun readDataFromArguments() {
        super.readDataFromArguments()
        arguments?.let {
            val safeArgs = ProductsFragmentArgs.fromBundle(it)
            mCategoryId = safeArgs.categoryId
        }
    }

    override fun initLogic() {
        super.initLogic()
        getViewModel()!!.getProducts(mCategoryId!!,"Price")
    }

    private fun initProductsAdapter() {

        mProductsAdapter = ProductsAdapter(
            productList = mProductList,
            onClicked = {
                val actionDetail = ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(productId = mProductList[it].id ?: "")
                findNavController().navigate(actionDetail)
            }
        )
        getViewBinding()?.rvProducts?.adapter = mProductsAdapter
    }

    private fun setProductsList(products: List<Product>) {
        mProductList.clear()
        mProductList.addAll(products)
        mProductsAdapter.notifyDataSetChanged()
    }
}