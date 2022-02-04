package com.ayub.mytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayub.mytest.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val results = intent.getStringExtra("accessToken")

        binding.jsonResultsTextview.text = results
    }
}