package com.mubin.unsplashgallery.api.models.dependencyModels


import com.google.gson.annotations.SerializedName

data class Social(
    @SerializedName("instagram_username")
    var instagramUsername: String? = "",
    @SerializedName("paypal_email")
    var paypalEmail: String? = "",
    @SerializedName("portfolio_url")
    var portfolioUrl: String? = "",
    @SerializedName("twitter_username")
    var twitterUsername: String? = ""
)