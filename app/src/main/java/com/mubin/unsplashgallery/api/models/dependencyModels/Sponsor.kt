package com.mubin.unsplashgallery.api.models.dependencyModels


import com.google.gson.annotations.SerializedName

data class Sponsor(
    @SerializedName("accepted_tos")
    var acceptedTos: Boolean? = false,
    @SerializedName("bio")
    var bio: String? = "",
    @SerializedName("first_name")
    var firstName: String? = "",
    @SerializedName("for_hire")
    var forHire: Boolean? = false,
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("instagram_username")
    var instagramUsername: String? = "",
    @SerializedName("last_name")
    var lastName: String? = "",
    @SerializedName("links")
    var links: LinksX? = LinksX(),
    @SerializedName("location")
    var location: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("portfolio_url")
    var portfolioUrl: String? = "",
    @SerializedName("profile_image")
    var profileImage: ProfileImage? = ProfileImage(),
    @SerializedName("social")
    var social: Social? = Social(),
    @SerializedName("total_collections")
    var totalCollections: Int? = 0,
    @SerializedName("total_likes")
    var totalLikes: Int? = 0,
    @SerializedName("total_photos")
    var totalPhotos: Int? = 0,
    @SerializedName("twitter_username")
    var twitterUsername: String? = "",
    @SerializedName("updated_at")
    var updatedAt: String? = "",
    @SerializedName("username")
    var username: String? = ""
)