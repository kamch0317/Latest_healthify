package com.example.healthify

import androidx.recyclerview.widget.RecyclerView
import com.example.healthify.databinding.CardCellBinding

class HomeWorkViewHolder (

    private val cardCellBinding: CardCellBinding,
) : RecyclerView.ViewHolder(cardCellBinding.root) {
    fun bindWork(workout: WorkoutData) {
        cardCellBinding.cover.setImageResource(workout.pic)
        cardCellBinding.title.text = workout.title
        cardCellBinding.author.text = workout.cmore

    }
}