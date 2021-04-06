package com.intsab.mvvm.activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intsab.mvvm.data.models.CommentsModel
import com.intsab.mvvm.databinding.CommentItemLayoutBinding


class CommentsAdapter(val listener: (item: CommentsModel) -> Unit) : PagedListAdapter<CommentsModel, CommentsAdapter.CommentsViewHolder>(CommentsModel.CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: CommentItemLayoutBinding =
            CommentItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CommentsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.itemView.setOnClickListener {
            listener.invoke(getItem(position)!!)
        }
    }

    class CommentsViewHolder(private val binding: CommentItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: CommentsModel) {
            binding.apply {
                comment = obj
                executePendingBindings()
            }
        }
    }
}
