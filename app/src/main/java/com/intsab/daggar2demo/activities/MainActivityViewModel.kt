package com.intsab.daggar2demo.activities

import androidx.lifecycle.ViewModel
import com.intsab.daggar2demo.data.models.CommentsModel
import com.intsab.daggar2demo.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    suspend fun getComments(): List<CommentsModel> {
        val comments = GlobalScope.async(Dispatchers.IO) { repo.getComments() }
        return comments.await()
    }
}
