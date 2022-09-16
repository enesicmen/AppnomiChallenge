package com.example.appnomichallenge.ui.fragment.categories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Category
import com.example.appnomichallenge.databinding.FragmentCategoriesBinding
import com.example.appnomichallenge.ui.base.BaseFragment
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
                is Resource.Loading -> getViewBinding()?.progressBar?.visibility = View.VISIBLE

                is Resource.Success -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                    setCategoriesList(it.data!!)
                }
                is Resource.Error -> getViewBinding()?.progressBar?.visibility = View.GONE
            }
        }
    }

    override fun initLogic() {
        super.initLogic()
        getViewModel()!!.getCategories()
    }

    private fun initCategoryAdapter() {
        mCategoriesAdapter = CategoriesAdapter(requireActivity(), mCategoryList)
        mCategoriesAdapter.setCallBack(object : CategoriesAdapter.CallBack {
            override fun onClickItem(position: Int, categoryId: String) {
                val actionDetail = CategoriesFragmentDirections.actionCategoriesFragmentToProductsFragment(categoryId = categoryId)
                findNavController().navigate(actionDetail)
            }
        })
        getViewBinding()?.rvCategories?.adapter = mCategoriesAdapter
    }

    private fun setCategoriesList(categories: List<Category>) {
        mCategoryList.clear()
        mCategoryList.addAll(categories.filter { it.isActive })
        mCategoriesAdapter.notifyDataSetChanged()
    }
}