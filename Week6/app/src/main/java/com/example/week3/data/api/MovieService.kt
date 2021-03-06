package com.example.week3.data.api

import com.example.week3.util.BaseURL
import com.example.week3.view.mainview.MainActivity
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieService {
    private var api: MovieApi
    init {
        api = createInstance()
    }

    companion object{
        private var mInstance : MovieService? = null
        fun getInstance() = mInstance ?: synchronized(this){
            mInstance ?: MovieService().also { mInstance = it }
        }
    }

    private fun createInstance() : MovieApi{
        val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(MovieApi::class.java)
    }

    fun getApi() : MovieApi = api
}