package ua.`in`.factsofnumbers.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import ua.`in`.factsofnumbers.domain.model.NumbersFact

class NumbersFactItemDiffCallBack: DiffUtil.ItemCallback<NumbersFact>() {
    override fun areItemsTheSame(oldItem: NumbersFact, newItem: NumbersFact): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: NumbersFact, newItem: NumbersFact): Boolean {
        return newItem == oldItem
    }
}