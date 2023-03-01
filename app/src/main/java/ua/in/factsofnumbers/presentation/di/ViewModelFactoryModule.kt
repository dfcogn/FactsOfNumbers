package ua.`in`.factsofnumbers.presentation.di

import dagger.Module
import dagger.Provides
import ua.`in`.factsofnumbers.domain.repository.NumberRepository
import ua.`in`.factsofnumbers.domain.usecase.GetAllNumbersFactPagingFromDbUseCase
import ua.`in`.factsofnumbers.domain.usecase.GetFactByNumberUseCase
import ua.`in`.factsofnumbers.domain.usecase.GetFactByRandomNumberUseCase
import ua.`in`.factsofnumbers.presentation.HomeViewModelFactory
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Singleton
    @Provides
    fun provideHomeViewModelFactory(
        getAllNumbersFactPagingFromDbUseCase: GetAllNumbersFactPagingFromDbUseCase,
        getFactByRandomNumberUseCase: GetFactByRandomNumberUseCase,
        getFactByNumberUseCase: GetFactByNumberUseCase
    ): HomeViewModelFactory{
        return HomeViewModelFactory(
            getAllNumbersFactPagingFromDbUseCase,
            getFactByRandomNumberUseCase,
            getFactByNumberUseCase
        )
    }
}