package com.intsab.mvvm.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.intsab.mvvm.activities.pagingUtils.CommentsDataSourceFactory
import com.intsab.mvvm.activities.pagingUtils.CommentsPagingHelper
import com.intsab.mvvm.data.models.CommentsModel
import com.intsab.mvvm.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    var pageNumber = 1

    var dataSourceFactory: CommentsDataSourceFactory = CommentsDataSourceFactory(this)
    var dataSourceMutableLiveData: MutableLiveData<CommentsPagingHelper>
    var executor: Executor? = null
    private var pagedListLiveData: LiveData<PagedList<CommentsModel>>

    init {
        dataSourceMutableLiveData = dataSourceFactory.getMutableLive()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .setPrefetchDistance(4)
            .build()
        executor = Executors.newFixedThreadPool(5)
        pagedListLiveData = LivePagedListBuilder<Int, CommentsModel>(dataSourceFactory, config)
            .setFetchExecutor(executor!!).build()
    }

    fun getPagedCommentsList(): LiveData<PagedList<CommentsModel>>? {
        return pagedListLiveData
    }

    //No Need after Paging library as this logic moved in paging Helper Class
    suspend fun getComments(): List<CommentsModel> {
        val comments = GlobalScope.async(Dispatchers.IO) { repo.getComments(pageNumber) }
        return comments.await()
    }
}
