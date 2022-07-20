package com.mubin.unsplashgallery.api.models.dependencyModels


import com.google.gson.annotations.SerializedName

data class BusinessWork(
    @SerializedName("status")
    var status: String? = ""
)