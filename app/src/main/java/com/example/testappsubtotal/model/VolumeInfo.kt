package com.example.testappsubtotal.model

import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    @SerializedName("title") var title: String? = null,
    @SerializedName("subtitle") var subtitle: String? = null,
    @SerializedName("authors") var authors: ArrayList<String> = arrayListOf(),
    @SerializedName("publisher") var publisher: String? = "Publisher details not available",
    @SerializedName("publishedDate") var publishedDate: String? = null,
    @SerializedName("description") var description: String? = "No description found",
    @SerializedName("pageCount") var pageCount: Int? = null,
    @SerializedName("printType") var printType: String? = null,
    @SerializedName("categories") var categories: ArrayList<String> = arrayListOf(),
    @SerializedName("averageRating") var averageRating: Double? = null,
    @SerializedName("ratingsCount") var ratingsCount: Int? = null,
    @SerializedName("maturityRating") var maturityRating: String? = null,
    @SerializedName("allowAnonLogging") var allowAnonLogging: Boolean? = null,
    @SerializedName("contentVersion") var contentVersion: String? = null,
    @SerializedName("imageLinks") var imageLinks: ImageLinks? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("previewLink") var previewLink: String? = null,
    @SerializedName("infoLink") var infoLink: String? = null,
    @SerializedName("canonicalVolumeLink") var canonicalVolumeLink: String? = null
)