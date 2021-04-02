package com.intsab.mvvm.data.datasource

import com.intsab.mvvm.data.models.CommentsModel
import com.intsab.mvvm.data.network.CommentsService
import com.intsab.mvvm.data.network.NetworkServiceProvider
import retrofit2.Call
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val retrofitServiceProvider: NetworkServiceProvider) :
    CommentsService {
    override fun getComments(url:String): Call<List<CommentsModel>> {
        return retrofitServiceProvider.provideCommentsRetrofitService().getComments(url)
    }
}