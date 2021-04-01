package com.intsab.daggar2demo.data.models

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName


data class CommentsModel(
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String
) {
    companion object{
        val CALLBACK: DiffUtil.ItemCallback<CommentsModel> =
            object : DiffUtil.ItemCallback<CommentsModel>() {
                override fun areItemsTheSame(obj: CommentsModel, t1: CommentsModel): Boolean {
                    return obj.name === t1.name
                }

                override fun areContentsTheSame(obj: CommentsModel, t1: CommentsModel): Boolean {
                    return true
                }
            }
    }

}