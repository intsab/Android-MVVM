package com.intsab.daggar2demo.data.repository

import com.intsab.daggar2demo.data.datasource.RemoteDataSource
import com.intsab.daggar2demo.data.models.CommentsModel
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    fun getComments(): List<CommentsModel> {
        val call = remoteDataSource.getComments()
        val result = call.execute().body()
        return if (result.isNullOrEmpty()) {
            arrayListOf()
        } else {
            result
        }
    }
}