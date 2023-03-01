package ua.`in`.factsofnumbers.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import ua.`in`.factsofnumbers.R
import ua.`in`.factsofnumbers.domain.model.NumbersFact
import ua.`in`.factsofnumbers.databinding.ListItemNumbersFactBinding
import ua.`in`.factsofnumbers.presentation.FactDescriptionFragment
import ua.`in`.factsofnumbers.presentation.HomeFragment

class NumbersFactAdapter(
    private val fragment: HomeFragment
): PagingDataAdapter<NumbersFact, NumbersFactHolder>(NumbersFactItemDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersFactHolder {
        val binding = ListItemNumbersFactBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NumbersFactHolder(binding)
    }

    override fun onBindViewHolder(holder: NumbersFactHolder, position: Int) {
        val numbersFact = getItem(position)
        numbersFact?.let {
            holder.bind(numbersFact)
            holder.itemView.setOnClickListener{
                val args = Bundle().apply {
                    putParcelable(FactDescriptionFragment.NUMBERS_FACT, numbersFact)
                }
                fragment.findNavController().navigate(R.id.action_homeFragment_to_factFragment, args)
            }
        }
    }
}