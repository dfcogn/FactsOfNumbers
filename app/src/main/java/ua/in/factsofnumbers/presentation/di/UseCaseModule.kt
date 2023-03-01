package ua.`in`.factsofnumbers.presentation.di

import dagger.Module
import dagger.Provides
import ua.`in`.factsofnumbers.domain.repository.NumberRepository
import ua.`in`.factsofnumbers.domain.usecase.GetAllNumbersFactPagingFromDbUseCase
import ua.`in`.factsofnumbers.domain.usecase.GetFactByNumberUseCase
import ua.`in`.factsofnumbers.domain.usecase.GetFactByRandomNumberUseCase
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetAllNumbersFactPagingFromDbUseCase(numberRepository: NumberRepository): GetAllNumbersFactPagingFromDbUseCase {
        return GetAllNumbersFactPagingFromDbUseCase(numberRepository)
    }

    @Singleton
    @Provides
    fun provideGetFactByNumberUseCase(numberRepository: NumberRepository): GetFactByNumberUseCase {
        return GetFactByNumberUseCase(numberRepository)
    }

    @Singleton
    @Provides
    fun provideGetFactByRandomNumberUseCase(numberRepository: NumberRepository): GetFactByRandomNumberUseCase {
        return GetFactByRandomNumberUseCase(numberRepository)
    }
}