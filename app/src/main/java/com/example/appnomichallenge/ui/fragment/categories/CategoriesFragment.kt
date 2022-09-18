package com.example.appnomichallenge.ui.fragment.categories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Category
import com.example.appnomichallenge.databinding.FragmentCategoriesBinding
import com.example.appnomichallenge.ui.base.BaseFragment
import com.example.appnomichallenge.ui.ext.setVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment:
    BaseFragment<FragmentCategoriesBinding,CategoriesViewModel>(){

    lateinit var mCategoriesAdapter: CategoriesAdapter

    private var mCategoryList: MutableList<Category> = mutableListOf()

    override fun setViewModelClass() = CategoriesViewModel::class.java

    override fun setViewBinding(): FragmentCategoriesBinding =
        FragmentCategoriesBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {
        initCategoryAdapter()
        getViewModel()?.categoryList?.observe(this){
            when (it) {
                is Resource.Loading -> getViewBinding()?.progressBar?.setVisibility(isVisible = true)

                is Resource.Success -> {
                    getViewBinding()?.progressBar?.setVisibility(isVisible = false)
                    setCategoriesList(it.data!!)
                }
                is Resource.Error -> getViewBinding()?.progressBar?.setVisibility(isVisible = false)
            }
        }
    }

    override fun initLogic() {
        super.initLogic()
        getViewModel()!!.getCategories()
    }

    private fun initCategoryAdapter() {
        mCategoriesAdapter = CategoriesAdapter(
            categoryList = mCategoryList,
            onClicked = {
                val actionDetail = CategoriesFragmentDirections.actionCategoriesFragmentToProductsFragment(categoryId = mCategoryList[it].categoryId ?: "")
                findNavController().navigate(actionDetail)
            }
        )
        getViewBinding()?.rvCategories?.adapter = mCategoriesAdapter
    }

    private fun setCategoriesList(categories: List<Category>) {
        mCategoryList.clear()
        mCategoryList.addAll(categories.filter { it.isActive })
        mCategoriesAdapter.notifyDataSetChanged()
    }
}