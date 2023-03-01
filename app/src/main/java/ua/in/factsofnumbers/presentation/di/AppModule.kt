package ua.`in`.factsofnumbers.presentation.di

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideContext(): Context {
        return context.applicationContext
    }

    @Singleton
    @Provides
    fun provideResources(context: Context): Resources {
        return context.resources
    }
}