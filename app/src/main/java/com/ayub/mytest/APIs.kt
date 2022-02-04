package com.ayub.mytest

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Retrofit
import java.io.IOException


class APIs {

    companion object {
        val LOGIN_URL = "https://dev.buenafrutasolutions.com"

        fun login(email:String, password:String, completion:(Boolean, String?) -> Unit){
            val retrofit = Retrofit.Builder().baseUrl(LOGIN_URL).build()

            val service = retrofit.create(APIService::class.java)

            val jsonObject = JSONObject()
            jsonObject.put("email", email)
            jsonObject.put("password", password)

            val jsonObjectString = jsonObject.toString()
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

            CoroutineScope(Dispatchers.IO).launch {
                val response = service.login(requestBody)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        /*val gson = GsonBuilder().setPrettyPrinting().create()
                        val prettyJson = gson.toJson(
                            JsonParser.parseString(
                                response.body()?.string()
                            )
                        )
                        val responseJson = JSONObject(prettyJson)*/
                        val responseJson = JSONObject(response.body()?.string())
                        val dataJson = responseJson.getJSONObject("data")
                        val token = dataJson.getString("accessToken")
                        completion(true, token)
                    } else {

                        Log.e("RETROFIT_ERROR", response.code().toString())
                        completion(false, null)
                    }
                }
            }
        }
    }
}