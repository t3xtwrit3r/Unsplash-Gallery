package com.mubin.unsplashgallery.api.apiServices

import com.haroldadmin.cnradapter.NetworkResponse
import com.mubin.unsplashgallery.api.models.responseModel.UnsplashPhotoItem
import com.mubin.unsplashgallery.api.models.errorResponse.ErrorResponse
import com.mubin.unsplashgallery.helper.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceUnsplash {

    @GET(Constants.END_POINT_GET_PHOTOS)
    suspend fun getPhotos(@Query("page") page: Int, @Query("per_page") perPage: Int, @Query("order_by") orderBy: String, @Query("client_id") client_id: String): NetworkResponse<List<UnsplashPhotoItem>, ErrorResponse>

}