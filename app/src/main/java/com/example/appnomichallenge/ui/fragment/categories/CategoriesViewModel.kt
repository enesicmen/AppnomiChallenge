package com.example.appnomichallenge.ui.fragment.categories

import androidx.lifecycle.ViewModel
import com.example.appnomichallenge.data.NetworkCallback
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.CategoriesModel
import com.example.appnomichallenge.data.repository.CategoriesRepository
import com.example.appnomichallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : ViewModel(){

    var categoriesList: SingleLiveEvent<Resource<List<CategoriesModel>>> = SingleLiveEvent()

    fun getCategories() {
        categoriesList.value = Resource.Loading()
        categoriesRepository.getCategories(object : NetworkCallback<List<CategoriesModel>>{
            override fun onSuccess(data: List<CategoriesModel>) {
                categoriesList.value = Resource.Success(data)
            }

            override fun onError(message: String) {
                categoriesList.value = Resource.Error(message)
            }

        })
    }
}