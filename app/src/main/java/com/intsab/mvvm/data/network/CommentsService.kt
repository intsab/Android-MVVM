package com.intsab.mvvm.data.network

import com.intsab.mvvm.data.models.CommentsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface CommentsService {
    @GET
    fun getComments(@Url url:String): Call<List<CommentsModel>>
}