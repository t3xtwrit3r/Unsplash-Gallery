package com.mubin.unsplashgallery.repository

import com.mubin.unsplashgallery.api.apiServices.ApiServiceUnsplash
import javax.inject.Inject

class AppRepository
@Inject
constructor(private val apiServiceUnsplash: ApiServiceUnsplash) {

    suspend fun getPhotos(page: Int, clientId: String) = apiServiceUnsplash.getPhotos(page, clientId)

}