package com.ayub.mytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var email: TextInputEditText
    lateinit var password: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
    }

    fun login(view: View){
        if(validateForm()){
            val stringEmail = email.text.toString().replace("\\s+".toRegex(), "")
            val stringPassword = password.text.toString().replace("\\s+".toRegex(), "")


            APIs.login(stringEmail, stringPassword){ result, msg ->
                runOnUiThread{
                    if (result){
                        Log.d("accessToken :", msg!!)
                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                        intent.putExtra("accessToken", msg)
                        this@MainActivity.startActivity(intent)
                    }
                }
            }

        }
    }

    private fun validateForm(): Boolean {
        var formValid = true

        val stringEmail = email.text.toString().replace("\\s+".toRegex(), "")
        if (TextUtils.isEmpty(stringEmail)) {
            email.error = "Email required."
            formValid = false
        } else if(TextUtils.indexOf(stringEmail, '@') < 0 || TextUtils.indexOf(stringEmail, '.') < 0){
            email.error = "Enter a valid email address."
            formValid = false
        } else {
            email.error = null
        }

        val stringPassword = password.text.toString().replace("\\s+".toRegex(), "")
        if (TextUtils.isEmpty(stringPassword)){
            password.error = "Password required."
            formValid = false
        } else{
            password.error = null
        }

        return formValid
    }
}