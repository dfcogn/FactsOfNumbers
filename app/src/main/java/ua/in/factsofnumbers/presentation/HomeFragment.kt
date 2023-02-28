package ua.`in`.factsofnumbers.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ua.`in`.factsofnumbers.R
import ua.`in`.factsofnumbers.databinding.FragmentHomeBinding
import ua.`in`.factsofnumbers.presentation.adapter.NumbersFactAdapter
import ua.`in`.factsofnumbers.presentation.di.App
import javax.inject.Inject

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerAdapter: NumbersFactAdapter

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels{
        homeViewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ((activity as MainActivity).applicationContext as App).getAppComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.getFactButton.setOnClickListener {
            val number = binding.editTextNumber.text.toString()
            homeViewModel.downloadFactByNumber(number)
        }

        binding.getFactRandomButton.setOnClickListener {
            homeViewModel.downloadFactByRandomNumber()
        }

        homeViewModel.isProgress.observe(viewLifecycleOwner){
            binding.loadStateView.progressBar.isVisible = it
        }

        homeViewModel.responseNewFactOk.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { numbersFact ->
                binding.editTextNumber.setText("")

                val args = Bundle().apply {
                    putParcelable(FactDescriptionFragment.NUMBERS_FACT, numbersFact)
                }

                findNavController().navigate(R.id.action_homeFragment_to_factFragment, args)
            }
        }

        homeViewModel.responseNewFactError.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { error ->
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }

        homeViewModel.isEmptyNumber.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, resources.getString(R.string.enter_your_number), Toast.LENGTH_LONG).show()
            }
        }

        homeViewModel.getAllNumbersFactFromDb().observe(viewLifecycleOwner){
            recyclerAdapter.submitList(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupRecyclerView(){
        recyclerAdapter = NumbersFactAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = recyclerAdapter
    }
}