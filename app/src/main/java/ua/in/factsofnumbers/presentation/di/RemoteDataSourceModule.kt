package ua.`in`.factsofnumbers.presentation.di

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import ua.`in`.factsofnumbers.data.api.NumberService
import ua.`in`.factsofnumbers.data.repository.datasource.RemoteDataSource
import ua.`in`.factsofnumbers.data.repository.datasource.RetrofitDataSource
import javax.inject.Singleton

@Module
class RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(numberService: NumberService): RemoteDataSource{
        return RetrofitDataSource(numberService)
    }
}