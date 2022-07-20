package com.mubin.unsplashgallery.api.models.responseModel


import com.google.gson.annotations.SerializedName
import com.mubin.unsplashgallery.api.models.dependencyModels.*

data class UnsplashPhotoItem(
    @SerializedName("alt_description")
    var altDescription: String? = "",
    @SerializedName("blur_hash")
    var blurHash: String? = "",
    @SerializedName("categories")
    var categories: List<Any>? = listOf(),
    @SerializedName("color")
    var color: String? = "",
    @SerializedName("created_at")
    var createdAt: String? = "",
    @SerializedName("current_user_collections")
    var currentUserCollections: List<Any>? = listOf(),
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("height")
    var height: Int? = 0,
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("liked_by_user")
    var likedByUser: Boolean? = false,
    @SerializedName("likes")
    var likes: Int? = 0,
    @SerializedName("links")
    var links: Links? = Links(),
    @SerializedName("promoted_at")
    var promotedAt: String? = "",
    @SerializedName("sponsorship")
    var sponsorship: Sponsorship? = Sponsorship(),
    @SerializedName("topic_submissions")
    var topicSubmissions: TopicSubmissions? = TopicSubmissions(),
    @SerializedName("updated_at")
    var updatedAt: String? = "",
    @SerializedName("urls")
    var urls: Urls? = Urls(),
    @SerializedName("user")
    var user: User? = User(),
    @SerializedName("width")
    var width: Int? = 0
)