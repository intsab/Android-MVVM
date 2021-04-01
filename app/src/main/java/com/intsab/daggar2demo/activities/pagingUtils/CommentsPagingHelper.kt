package com.intsab.daggar2demo.activities.pagingUtils

import androidx.paging.PageKeyedDataSource
import com.intsab.daggar2demo.activities.MainActivityViewModel
import com.intsab.daggar2demo.data.models.CommentsModel
import com.intsab.daggar2demo.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentsPagingHelper @Inject constructor(
    private val viewModel: MainActivityViewModel
) : PageKeyedDataSource<Int, CommentsModel>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CommentsModel>
    ) {
        val comments = GlobalScope.async(Dispatchers.IO) {
            viewModel.getComments()
        }
        var list: List<CommentsModel> = arrayListOf()
        GlobalScope.launch {
            list = comments.await()
            viewModel.pageNumber = viewModel.pageNumber + 1
            callback.onResult(list, null, viewModel.pageNumber)
        }


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CommentsModel>) {
        val comments = GlobalScope.async(Dispatchers.IO) {
            viewModel.getComments()
        }
        var list: List<CommentsModel> = arrayListOf()
        GlobalScope.launch {
            list = comments.await()
            viewModel.pageNumber = viewModel.pageNumber + 1
            callback.onResult(list, viewModel.pageNumber)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CommentsModel>) {
    }
}