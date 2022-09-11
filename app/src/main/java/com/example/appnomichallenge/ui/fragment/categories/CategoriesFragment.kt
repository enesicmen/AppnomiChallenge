package com.example.appnomichallenge.ui.fragment.categories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Categories
import com.example.appnomichallenge.databinding.FragmentCategoriesBinding
import com.example.appnomichallenge.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment:
    BaseFragment<FragmentCategoriesBinding,CategoriesViewModel>(){

    lateinit var mCategoriesAdapter: CategoriesAdapter

    private var mCategoriesList: MutableList<Categories> = arrayListOf()

    private var filteredCategoriesList: MutableList<Categories> = arrayListOf()

    override fun initView(savedInstanceState: Bundle?) {
        setCategoriesAdapter()
        getViewModel()?.categoriesList?.observe(this){
            when (it) {
                is Resource.Loading -> {
                    getViewBinding()?.progressBar?.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                    setCategoriesList(it.data!!)
                    filterActiveCategories(true)
                }
                is Resource.Error -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                }
            }
        }
    }

    override fun setViewModelClass() =
        CategoriesViewModel::class.java

    override fun setViewBinding(): FragmentCategoriesBinding =
        FragmentCategoriesBinding.inflate(layoutInflater)

    override fun initLogic() {
        super.initLogic()
        getViewModel()!!.getCategories()
    }

    private fun setCategoriesAdapter() {
        mCategoriesAdapter = CategoriesAdapter(requireActivity(), filteredCategoriesList)
        mCategoriesAdapter.setCallBack(object : CategoriesAdapter.CallBack {
            override fun onClickItem(position: Int, categoryId: String) {
                val actionDetail = CategoriesFragmentDirections.actionCategoriesFragmentToProductsFragment(categoryId = categoryId)
                findNavController().navigate(actionDetail)
            }
        })
        getViewBinding()?.rvCategories?.adapter = mCategoriesAdapter
    }

    private fun filterActiveCategories(isActiveCategories: Boolean){
        val categoriesList = when {
            isActiveCategories -> {
                mCategoriesList.filter { it.isActive }
            }
            else -> {
                mCategoriesList.filter { !it.isActive }
            }
        }
        filteredCategoriesList.clear()
        filteredCategoriesList.addAll(categoriesList)
        mCategoriesAdapter.notifyDataSetChanged()

    }

    private fun setCategoriesList(categories: List<Categories>) {
        mCategoriesList.clear()
        mCategoriesList.addAll(categories)
        mCategoriesAdapter.notifyDataSetChanged()
    }
}