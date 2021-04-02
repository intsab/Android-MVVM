package com.intsab.mvvm.data.repository

import com.intsab.mvvm.data.datasource.RemoteDataSource
import com.intsab.mvvm.data.models.CommentsModel
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    fun getComments(pageNumber: Int): List<CommentsModel> {
        val url = "posts/$pageNumber/comments"
        val call = remoteDataSource.getComments(url)
        val result = call.execute().body()
        return if (result.isNullOrEmpty()) {
            arrayListOf()
        } else {
            result
        }
    }
}