package ua.`in`.factsofnumbers.presentation.di

import dagger.Module
import dagger.Provides
import ua.`in`.factsofnumbers.data.db.NumbersFactDao
import ua.`in`.factsofnumbers.data.repository.datasource.LocalDataSource
import ua.`in`.factsofnumbers.data.repository.datasource.RoomDataSource
import javax.inject.Singleton

@Module
class LocalDataSourceModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(numbersFactDao: NumbersFactDao): LocalDataSource{
        return RoomDataSource(numbersFactDao)
    }
}