package com.intsab.mvvm.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 *
 * Was Using to test some scenarios now its NOT IN USE
 **/


class NetworkServiceProvider @Inject constructor() {

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