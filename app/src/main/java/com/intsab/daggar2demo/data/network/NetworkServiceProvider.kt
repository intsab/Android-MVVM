package com.intsab.daggar2demo.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkServiceProvider @Inject constructor(val service:CommentsService) {

    fun provideCommentsRetrofitService(): CommentsService {
        val client = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build()
        val retro = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retro.create(CommentsService::class.java)
    }
}