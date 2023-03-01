package ua.`in`.factsofnumbers.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ua.`in`.factsofnumbers.R
import ua.`in`.factsofnumbers.databinding.FragmentHomeBinding
import ua.`in`.factsofnumbers.presentation.adapter.NumbersFactAdapter
import ua.`in`.factsofnumbers.presentation.di.App
import javax.inject.Inject

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerAdapter: NumbersFactAdapter
    private var afterAddedNewNumbersFact = false

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

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                homeViewModel.factFlow.collectLatest{
                    recyclerAdapter.submitData(it)
                }
            }
        }

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
                afterAddedNewNumbersFact = true
                recyclerAdapter.refresh()

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

        recyclerAdapter.addLoadStateListener { state: CombinedLoadStates ->
            lifecycleScope.launch {
                val refresh = state.source.refresh
                if (state.refresh is LoadState.NotLoading && afterAddedNewNumbersFact){
                    binding.recyclerView.scrollToPosition(0)
                    afterAddedNewNumbersFact = false
                }
            }
        }
    }
}