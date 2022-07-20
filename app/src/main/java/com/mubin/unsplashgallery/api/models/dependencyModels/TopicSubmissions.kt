package com.mubin.unsplashgallery.api.models.dependencyModels


import com.google.gson.annotations.SerializedName

data class TopicSubmissions(
    @SerializedName("business-work")
    var businessWork: BusinessWork? = BusinessWork()
)