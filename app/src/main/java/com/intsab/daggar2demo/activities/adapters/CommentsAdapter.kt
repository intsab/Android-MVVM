package com.intsab.daggar2demo.activities.adapters

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intsab.daggar2demo.data.models.CommentsModel
import com.intsab.daggar2demo.databinding.CommentItemLayoutBinding


class CommentsAdapter :
    PagedListAdapter<CommentsModel, CommentsAdapter.CommentsViewHolder>(CommentsModel.CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: CommentItemLayoutBinding =
            CommentItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CommentsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
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
