package ua.`in`.factsofnumbers.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import ua.`in`.factsofnumbers.domain.model.NumbersFact
import ua.`in`.factsofnumbers.databinding.ListItemNumbersFactBinding

class NumbersFactHolder(private val itemViewBinding: ListItemNumbersFactBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

    fun bind(numbersFact: NumbersFact){
        itemViewBinding.numberTv.text = numbersFact.number.toString()
        itemViewBinding.descriptionTv.text = numbersFact.description

        itemViewBinding.cardView.post {
            val currentCardViewWidth = itemViewBinding.cardView.width
            val numberWidth = itemViewBinding.numberTv.width
            itemViewBinding.descriptionTv.maxWidth = currentCardViewWidth - numberWidth - 64
        }
    }
}