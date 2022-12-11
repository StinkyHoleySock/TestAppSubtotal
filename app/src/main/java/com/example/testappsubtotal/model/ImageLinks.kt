package com.example.testappsubtotal.model

import com.google.gson.annotations.SerializedName

data class ImageLinks(
    @SerializedName("smallThumbnail") var smallThumbnail: String?,
    @SerializedName("thumbnail") var thumbnail: String?
)
