package ua.`in`.factsofnumbers.presentation.di

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import ua.`in`.factsofnumbers.data.repository.NumberRepositoryImpl
import ua.`in`.factsofnumbers.data.repository.datasource.LocalDataSource
import ua.`in`.factsofnumbers.data.repository.datasource.RemoteDataSource
import ua.`in`.factsofnumbers.domain.repository.NumberRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNumberRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, resources: Resources): NumberRepository {
        return NumberRepositoryImpl(remoteDataSource, localDataSource, resources)
    }
}