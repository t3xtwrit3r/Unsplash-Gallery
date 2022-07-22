package com.mubin.unsplashgallery.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubin.unsplashgallery.api.models.responseModel.UnsplashPhotoItem
import com.mubin.unsplashgallery.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel
@Inject
constructor(private val repository: AppRepository) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _photoList = MutableLiveData<List<UnsplashPhotoItem>>()
    val photoList: LiveData<List<UnsplashPhotoItem>> = _photoList

    init {
        getPhotoList(1)
    }

    fun getPhotoList(page: Int) {
        _dataLoading.value = true
        viewModelScope.launch {

            val response = repository.getPhotos(page)
            _photoList.value = response
            _dataLoading.value = false

        }
    }

}