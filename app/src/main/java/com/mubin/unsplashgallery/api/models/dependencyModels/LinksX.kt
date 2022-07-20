package com.mubin.unsplashgallery.api.models.dependencyModels


import com.google.gson.annotations.SerializedName

data class LinksX(
    @SerializedName("followers")
    var followers: String? = "",
    @SerializedName("following")
    var following: String? = "",
    @SerializedName("html")
    var html: String? = "",
    @SerializedName("likes")
    var likes: String? = "",
    @SerializedName("photos")
    var photos: String? = "",
    @SerializedName("portfolio")
    var portfolio: String? = "",
    @SerializedName("self")
    var self: String? = ""
)