package ua.`in`.factsofnumbers.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.`in`.factsofnumbers.domain.model.NumbersFact
import ua.`in`.factsofnumbers.databinding.FragmentFactDescriptionBinding

class FactDescriptionFragment : Fragment() {
    private var _binding: FragmentFactDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFactDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numbersFact = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2){
            arguments?.getParcelable(NUMBERS_FACT, NumbersFact::class.java) ?: throw throw Exception("Miss numbersFact object")
        }else{
            arguments?.getParcelable(NUMBERS_FACT) ?: throw throw Exception("Miss numbersFact object")
        }

        binding.numberTv.text = numbersFact.number.toString()
        binding.descriptionTv.text = numbersFact.description
    }

    companion object{
        const val NUMBERS_FACT = "NUMBERS_FACT"
    }
}