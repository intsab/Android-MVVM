package com.intsab.mvvm.activities.pagingUtils

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.intsab.mvvm.activities.MainActivityViewModel
import com.intsab.mvvm.data.models.CommentsModel
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