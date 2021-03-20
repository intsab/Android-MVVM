package com.intsab.daggar2demo.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intsab.daggar2demo.R
import com.intsab.daggar2demo.data.models.CommentsModel

class CommentsAdapter(val list: ArrayList<CommentsModel>) :
    RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_item_layout, parent, false)
        return CommentsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val commentItem = list[position]
        holder.tvName.text = commentItem.name
        holder.tvEmail.text = commentItem.email
        holder.tvComment.text = commentItem.body
    }

    inner class CommentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById(R.id.name)
        var tvEmail: TextView = view.findViewById(R.id.email)
        var tvComment: TextView = view.findViewById(R.id.comment)
    }
}