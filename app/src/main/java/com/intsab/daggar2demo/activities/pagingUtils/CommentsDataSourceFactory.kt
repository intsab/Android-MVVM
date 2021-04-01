package com.intsab.daggar2demo.activities.pagingUtils

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.intsab.daggar2demo.activities.MainActivityViewModel
import com.intsab.daggar2demo.data.models.CommentsModel
import javax.inject.Inject


class CommentsDataSourceFactory @Inject constructor(private var viewModel: MainActivityViewModel) :
    DataSource.Factory<Int, CommentsModel>() {
    var pagingHelper: CommentsPagingHelper? = null
    var mutableLiveData: MutableLiveData<CommentsPagingHelper> = MutableLiveData()

    override fun create(): DataSource<Int, CommentsModel> {
        pagingHelper = CommentsPagingHelper(viewModel)
        mutableLiveData.postValue(pagingHelper)
        return pagingHelper!!
    }

    fun getMutableLive(): MutableLiveData<CommentsPagingHelper> {
        return mutableLiveData
    }
}