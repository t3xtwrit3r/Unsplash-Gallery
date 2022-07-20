package com.mubin.unsplashgallery.di

import com.google.gson.Gson
import com.mubin.unsplashgallery.api.RetrofitUtils.retrofitInstance
import com.mubin.unsplashgallery.api.apiServices.ApiServiceUnsplash
import com.mubin.unsplashgallery.helper.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("apiUnsplash")
    fun provideBaseUrlUnsplash() = Constants.BASE_URL


    @Provides
    @Singleton
    fun provideRetrofitInstance(@Named("apiUnsplash") BASE_URL: String, gson: Gson, httpClient: OkHttpClient): ApiServiceUnsplash =
        retrofitInstance(baseUrl = BASE_URL, gson, httpClient)
            .create(ApiServiceUnsplash::class.java)

}