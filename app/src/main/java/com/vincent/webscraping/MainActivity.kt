package com.vincent.webscraping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://developer.android.com/")
            .build()

        val api = retrofit.create(API::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val result = api.getPageResponse("courses/jetpack-compose/course")
            if(result.isSuccessful && result.body()!=null){
                Log.d("Jamie Foxx",result.body().toString())
            }
        }
    }
}

interface  API {
    @GET
    suspend fun getPageResponse(@Url url :String): Response<String>
}