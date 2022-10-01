package com.example.healthify

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthify.databinding.CommentcolumnBinding

class CommentrvAdapter (private val context: Context, private val commentlist : ArrayList<Comment>,itemListener : onFoodItemClickListener) : RecyclerView.Adapter<CommentrvAdapter.ViewHolder>(){

    private var mListener: onFoodItemClickListener = itemListener

    interface onFoodItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onFoodItemClickListener){

        mListener = listener
    }

    inner class ViewHolder(val binding: CommentcolumnBinding, listener: onFoodItemClickListener) : RecyclerView.ViewHolder(binding.root){

        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listener = mListener
        val binding = CommentcolumnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(commentlist[position]){
                binding.commentTitle.text = this.commentTitle
                binding.commentDescription.text = this.commentDesc
                binding.commentAuthor.text = this.username
            }
        }
    }

    override fun getItemCount(): Int {
        return commentlist.size
    }

}