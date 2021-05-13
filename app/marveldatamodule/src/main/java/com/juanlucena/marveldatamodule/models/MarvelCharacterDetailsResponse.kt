package com.juanlucena.marveldatamodule.models

import com.google.gson.annotations.SerializedName

data class MarvelCharacterDetailsResponse (

        @SerializedName("code") val code : Int,
        @SerializedName("status") val status : String,
        @SerializedName("copyright") val copyright : String,
        @SerializedName("attributionText") val attributionText : String,
        @SerializedName("attributionHTML") val attributionHTML : String,
        @SerializedName("etag") val etag : String,
        @SerializedName("data") val data : DetailsData
)

data class DetailsData (

        @SerializedName("offset") val offset : Int,
        @SerializedName("limit") val limit : Int,
        @SerializedName("total") val total : Int,
        @SerializedName("count") val count : Int,
        @SerializedName("results") val results : List<DetailsResults>
)

data class DetailsResults (

        @SerializedName("id") val id : Int,
        @SerializedName("name") val name : String,
        @SerializedName("description") val description : String,
        @SerializedName("modified") val modified : String,
        @SerializedName("thumbnail") val thumbnail : Thumbnail,
        @SerializedName("resourceURI") val resourceURI : String,
        @SerializedName("comics") val comics : Comics,
        @SerializedName("series") val series : Series,
        @SerializedName("stories") val stories : Stories,
        @SerializedName("events") val events : Events,
        @SerializedName("urls") val urls : List<Urls>
)