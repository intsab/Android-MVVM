package com.intsab.mvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.intsab.mvvm.MyApplication
import com.intsab.mvvm.R
import com.intsab.mvvm.activities.MainActivityViewModel
import com.intsab.mvvm.activities.adapters.CommentsAdapter
import com.intsab.mvvm.data.models.CommentsModel
import com.intsab.mvvm.workers.ShowPushNotificationTask
import kotlinx.android.synthetic.main.fragment_main_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivityFragment : Fragment() {


    @Inject
    lateinit var viewModel: MainActivityViewModel
    private val adapter: CommentsAdapter = CommentsAdapter() {
        requireActivity().runOnUiThread {
            setNotificationAfterEvery3Seconds(it.name, it.body)
            Toast.makeText(requireActivity().applicationContext, "Clicked", Toast.LENGTH_SHORT)
                .show()
            val bundle = bundleOf("name" to it.name, "email" to it.email, "details" to it.body)

            requireView().findNavController()
                .navigate(R.id.action_mainActivityFragment_to_detailsFragment, bundle)

        }
    }
    var myCount: Int = 0
    var firstItem: CommentsModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().applicationContext as MyApplication).appComponent.inject(this)
        loader.animate()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@MainActivityFragment.adapter
        }

        loadData()

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {

                if (!(positionStart == 0 && itemCount == 0)) {
                    loader.visibility = View.GONE
                    myCount = itemCount
                    isDataAvailable()
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_activity, container, false)
    }

    private fun loadData() {
        loader.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.getPagedCommentsList()
                ?.observe(viewLifecycleOwner, Observer { pagedList ->
                    requireActivity().runOnUiThread {
                        adapter.submitList(pagedList)
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
            firstItem = adapter?.currentList?.get(0)
            tvNoData.visibility = View.GONE
        }
    }

    private fun setNotificationAfterEvery3Seconds(title: String, desc: String) {
        val data = Data.Builder()
        data.putString("title", title)
        data.putString("desc", desc)

        val periodicWorkRequest = PeriodicWorkRequest.Builder(
            ShowPushNotificationTask::class.java,
            10,
            TimeUnit.MILLISECONDS,
            2,
            TimeUnit.MILLISECONDS
        )
            .setInitialDelay(2, TimeUnit.MILLISECONDS)
            .setInputData(data.build())
            .build()
        WorkManager.getInstance().enqueue(periodicWorkRequest);
    }


}