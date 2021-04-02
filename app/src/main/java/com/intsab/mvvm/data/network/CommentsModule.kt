package com.intsab.mvvm.data.network

import com.intsab.mvvm.data.models.CommentsModel
import dagger.Module
import dagger.Provides
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Url

/**
 *
 * Was Made for testing purpose to provide dependency of the interface NOT IN USE
 **/

@Module
class CommentsModule {
    @Provides
    fun provideServices():CommentsService{
        return object :CommentsService{
            override fun getComments(@Url url: String): Call<List<CommentsModel>> {
               return object :Call<List<CommentsModel>>{
                   override fun enqueue(callback: Callback<List<CommentsModel>>) {
                       TODO("Not yet implemented")
                   }

                   override fun isExecuted(): Boolean {
                       TODO("Not yet implemented")
                   }

                   override fun timeout(): Timeout {
                       TODO("Not yet implemented")
                   }

                   override fun clone(): Call<List<CommentsModel>> {
                       TODO("Not yet implemented")
                   }

                   override fun isCanceled(): Boolean {
                       TODO("Not yet implemented")
                   }

                   override fun cancel() {
                       TODO("Not yet implemented")
                   }

                   override fun execute(): Response<List<CommentsModel>> {
                       TODO("Not yet implemented")
                   }

                   override fun request(): Request {
                       TODO("Not yet implemented")
                   }

               }
            }
        }
    }
}