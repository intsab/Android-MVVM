package com.intsab.daggar2demo.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intsab.daggar2demo.data.models.CommentsModel
import com.intsab.daggar2demo.databinding.CommentItemLayoutBinding


class CommentsAdapter(private val context: Context, private var list: ArrayList<CommentsModel>) :
    RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemBinding: CommentItemLayoutBinding =
            CommentItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CommentsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(list[position])
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
