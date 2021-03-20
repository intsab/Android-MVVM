package com.intsab.daggar2demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.intsab.daggar2demo.data.models.CommentsModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val commentsList: ArrayList<CommentsModel> = arrayListOf()

    //    private val viewModel: MainActivityViewModel by viewModels()
    private val adapter: CommentsAdapter = CommentsAdapter(commentsList)

    @Inject
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApplication).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        GlobalScope.launch(Dispatchers.Main) {
            val items = viewModel.getComments()
            commentsList.clear()
            commentsList.addAll(items)
            adapter.notifyDataSetChanged()
        }

    }
}