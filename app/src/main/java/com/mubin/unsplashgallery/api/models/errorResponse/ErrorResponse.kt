package com.mubin.unsplashgallery.api.models.errorResponse


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errors")
    var errors: List<String>? = listOf()
)