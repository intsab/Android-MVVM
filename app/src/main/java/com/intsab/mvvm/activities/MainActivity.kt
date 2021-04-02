package com.intsab.mvvm.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.intsab.mvvm.MyApplication
import com.intsab.mvvm.R
import com.intsab.mvvm.activities.adapters.CommentsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val adapter: CommentsAdapter = CommentsAdapter()

    @Inject
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApplication).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        loader.animate()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        loadData()
    }

    private fun loadData() {
        loader.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.Main) {
            viewModel.getPagedCommentsList()?.observe(this@MainActivity, Observer {
                runOnUiThread {
                    adapter.submitList(it)
                    loader.visibility = View.GONE
                }
            })
        }
    }
}