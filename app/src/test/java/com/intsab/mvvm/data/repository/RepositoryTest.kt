package com.intsab.mvvm.data.repository

import com.intsab.mvvm.data.datasource.RemoteDataSource
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest : TestCase() {

    @Mock
     var charactersDataSource: RemoteDataSource?= null
    private val pageNumber=1
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetComments() {
      val value=  charactersDataSource?.getComments("posts/$pageNumber/comments")?.execute()
        assert(value!=null)
    }
}