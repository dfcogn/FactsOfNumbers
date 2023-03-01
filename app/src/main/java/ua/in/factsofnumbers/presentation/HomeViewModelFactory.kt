package ua.`in`.factsofnumbers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.`in`.factsofnumbers.domain.repository.NumberRepository
import ua.`in`.factsofnumbers.domain.usecase.GetAllNumbersFactPagingFromDbUseCase
import ua.`in`.factsofnumbers.domain.usecase.GetFactByNumberUseCase
import ua.`in`.factsofnumbers.domain.usecase.GetFactByRandomNumberUseCase

class HomeViewModelFactory(
    private val getAllNumbersFactPagingFromDbUseCase: GetAllNumbersFactPagingFromDbUseCase,
    private val getFactByRandomNumberUseCase: GetFactByRandomNumberUseCase,
    private val getFactByNumberUseCase: GetFactByNumberUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            getAllNumbersFactPagingFromDbUseCase,
            getFactByRandomNumberUseCase,
            getFactByNumberUseCase
        ) as T
    }
}