package com.intsab.mvvm.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
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
    var myCount: Int = 0

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
            viewModel.getPagedCommentsList()?.observe(this@MainActivity, Observer { pagedList ->
                runOnUiThread {
                    adapter.submitList(pagedList)
                    pagedList.addWeakCallback(null, object : PagedList.Callback() {
                        override fun onChanged(position: Int, count: Int) {}
                        override fun onInserted(position: Int, count: Int) {
                            myCount = count
                            isDataAvailable()
                        }

                        override fun onRemoved(position: Int, count: Int) {}
                    })
                    loader.visibility = View.GONE

                }
            })
        }
    }

    //Added for Make Espresso Test lil complex
     fun getMyDataCount(): Int {
        return myCount
    }

    private fun isDataAvailable() {
        if (getMyDataCount() < 1) {
            tvNoData.visibility = View.VISIBLE
        } else {
            tvNoData.visibility = View.GONE
        }
    }
}