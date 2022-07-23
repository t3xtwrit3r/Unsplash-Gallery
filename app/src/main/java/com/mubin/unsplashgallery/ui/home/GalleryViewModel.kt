package com.mubin.unsplashgallery.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.mubin.unsplashgallery.api.models.responseModel.UnsplashPhotoItem
import com.mubin.unsplashgallery.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel
@Inject
constructor(private val repository: AppRepository) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _photoList = MutableLiveData<List<UnsplashPhotoItem>>()
    val photoList: LiveData<List<UnsplashPhotoItem>> = _photoList

    init {
        getPhotoList(1)
    }

    fun getPhotoList(page: Int) {
        _dataLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPhotos(page)
            withContext(Dispatchers.Main) {
                _dataLoading.value = false
                when (response) {
                    is NetworkResponse.Success -> {
                        _photoList.value = response.body
                    }
                    is NetworkResponse.ServerError -> {
                        _errorMessage.value = "Sorry! Server Error occurred, Please try again later!"
                    }
                    is NetworkResponse.NetworkError -> {
                        _errorMessage.value = "Sorry! Internet connection error occurred."
                    }
                    is NetworkResponse.UnknownError -> {
                        _errorMessage.value = "Unknown error occurred, Please try again."
                    }
                }
            }
        }
    }

}