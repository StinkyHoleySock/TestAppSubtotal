package com.example.testappsubtotal.model

import com.google.gson.annotations.SerializedName

data class Books(

    @SerializedName("items") var items: ArrayList<Items> = arrayListOf()

)