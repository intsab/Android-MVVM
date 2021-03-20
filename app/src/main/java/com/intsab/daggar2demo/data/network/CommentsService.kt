package com.intsab.daggar2demo.data.network

import com.intsab.daggar2demo.data.models.CommentsModel
import retrofit2.Call
import retrofit2.http.GET

interface CommentsService {
    @GET("posts/1/comments")
    fun getComments(): Call<List<CommentsModel>>
}