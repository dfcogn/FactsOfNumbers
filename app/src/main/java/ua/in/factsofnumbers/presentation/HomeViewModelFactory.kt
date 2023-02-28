package ua.`in`.factsofnumbers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.`in`.factsofnumbers.domain.repository.NumberRepository

class HomeViewModelFactory(private val numberRepository: NumberRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            numberRepository
        ) as T
    }
}