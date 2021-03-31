package com.intsab.daggar2demo.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.intsab.daggar2demo.MyApplication
import com.intsab.daggar2demo.R
import com.intsab.daggar2demo.activities.adapters.CommentsAdapter
import com.intsab.daggar2demo.data.models.CommentsModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val commentsList: ArrayList<CommentsModel> = arrayListOf()

    private val adapter: CommentsAdapter = CommentsAdapter(this, commentsList)

    @Inject
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApplication).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        loader.animate()
        add_more.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                viewModel.pageNumber = viewModel.pageNumber + 1
                loader.visibility = View.VISIBLE
                GlobalScope.launch(Dispatchers.Main) {
                    viewModel.getComments()
                }
            }
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        loadData()
    }

    private fun loadData() {
        loader.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.getComments().dataList.observe(this@MainActivity, Observer {
                runOnUiThread {
                    commentsList.addAll(it)
                    adapter.notifyDataSetChanged()
                    loader.visibility = View.GONE
                }
            })
        }
    }
}