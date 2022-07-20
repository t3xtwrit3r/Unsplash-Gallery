package com.mubin.unsplashgallery.api.models.dependencyModels


import com.google.gson.annotations.SerializedName

data class Sponsorship(
    @SerializedName("impression_urls")
    var impressionUrls: List<String>? = listOf(),
    @SerializedName("sponsor")
    var sponsor: Sponsor? = Sponsor(),
    @SerializedName("tagline")
    var tagline: String? = "",
    @SerializedName("tagline_url")
    var taglineUrl: String? = ""
)