package com.mubin.unsplashgallery.repository

import com.mubin.unsplashgallery.api.apiServices.ApiServiceUnsplash
import com.mubin.unsplashgallery.api.models.responseModel.UnsplashPhotoItem
import com.mubin.unsplashgallery.helper.Constants
import javax.inject.Inject

class AppRepository
@Inject
constructor(private val apiServiceUnsplash: ApiServiceUnsplash) {

    suspend fun getPhotos(page: Int) = apiServiceUnsplash.getPhotos(page, 25, "latest", Constants.CLIENT_ID)

}