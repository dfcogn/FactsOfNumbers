package ua.`in`.factsofnumbers.presentation.di

import dagger.Module
import dagger.Provides
import ua.`in`.factsofnumbers.domain.repository.NumberRepository
import ua.`in`.factsofnumbers.presentation.HomeViewModelFactory
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Singleton
    @Provides
    fun provideHomeViewModelFactory(numberRepository: NumberRepository): HomeViewModelFactory{
        return HomeViewModelFactory(numberRepository)
    }
}