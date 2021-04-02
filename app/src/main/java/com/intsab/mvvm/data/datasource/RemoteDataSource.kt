package com.intsab.mvvm.data.datasource

import com.intsab.mvvm.data.models.CommentsModel
import com.intsab.mvvm.data.network.CommentsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource  @Inject constructor(){
    fun getComments(url: String): Call<List<CommentsModel>> {
        return provideCommentsRetrofitService().getComments(url)
    }

    private fun provideCommentsRetrofitService(): CommentsService {
        val client = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build()
        val retro = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retro.create(CommentsService::class.java)
    }
}