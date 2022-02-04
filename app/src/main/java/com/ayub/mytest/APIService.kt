package com.ayub.mytest

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {

    @POST("/api/account/login")
    suspend fun login(@Body requestBody: RequestBody): Response<ResponseBody>
}

