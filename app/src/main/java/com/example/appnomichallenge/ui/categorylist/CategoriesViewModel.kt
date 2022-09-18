package com.example.appnomichallenge.ui.categorylist

import androidx.lifecycle.ViewModel
import com.example.appnomichallenge.data.NetworkCallback
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Category
import com.example.appnomichallenge.data.repository.CategoryRepository
import com.example.appnomichallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel(){

    var categoryList: SingleLiveEvent<Resource<List<Category>>> = SingleLiveEvent()

    fun getCategories() {
        categoryList.value = Resource.Loading()
        categoryRepository.getCategories(object : NetworkCallback<List<Category>>{
            override fun onSuccess(data: List<Category>) {
                categoryList.value = Resource.Success(data)
            }
            override fun onError(message: String) {
                categoryList.value = Resource.Error(message)
            }
        })
    }
}