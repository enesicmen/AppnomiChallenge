package com.example.appnomichallenge.ui.fragment.categories

import android.os.Bundle
import android.view.View
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.CategoriesModel
import com.example.appnomichallenge.databinding.FragmentCategoriesBinding
import com.example.appnomichallenge.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment:
    BaseFragment<FragmentCategoriesBinding,CategoriesViewModel>(){

    lateinit var mCategoriesAdapter: CategoriesAdapter

    private var mCategoriesList: MutableList<CategoriesModel> = arrayListOf()

    override fun setViewModelClass() =
        CategoriesViewModel::class.java

    override fun setViewBinding(): FragmentCategoriesBinding =
        FragmentCategoriesBinding.inflate(layoutInflater)

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
                }
                is Resource.Error -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                }
            }
        }
    }

    override fun initLogic() {
        super.initLogic()
        getViewModel()!!.getCategories()
    }

    private fun setCategoriesAdapter() {
        mCategoriesAdapter = CategoriesAdapter(requireActivity(), mCategoriesList)
        mCategoriesAdapter.setCallBack(object : CategoriesAdapter.CallBack {
            override fun onClickItem(position: Int, categoriesModel: CategoriesModel) {
                //val actionDetail = CategoriesFragmentDirections.actionCategoriesFragmentToProductDetailFragment(movie = categoriesModel)
                //findNavController().navigate(actionDetail)
            }
        })
        getViewBinding()?.rvCategories?.adapter = mCategoriesAdapter
    }

    private fun setCategoriesList(categories: List<CategoriesModel>) {
        mCategoriesList.clear()
        mCategoriesList.addAll(categories)
        mCategoriesAdapter.notifyDataSetChanged()
    }
}