package com.ayub.mytest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginModel (
    /*@SerializedName("message")
    @Expose*/
    var message: String = "",

    /*@SerializedName("data")
    @Expose*/
    var data: DataModel = DataModel()
)
class DataModel (
    //@SerializedName("accessToken")
    var accessToken: String? = "",
    //@SerializedName("expiresOn")
    var expiresOn: String? = ""
)
/*
class DataModel {
    @SerializedName("accessToken")
    var accessToken: String? = null
    @SerializedName("expiresOn")
    var expiresOn: String? = null
}*/
