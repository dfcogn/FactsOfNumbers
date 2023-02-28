package ua.`in`.factsofnumbers.presentation.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ua.`in`.factsofnumbers.data.db.NumbersFactDao
import ua.`in`.factsofnumbers.data.db.NumbersFactDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): NumbersFactDatabase{
        return Room.databaseBuilder(context, NumbersFactDatabase::class.java, "numbers_fact_db").build()
    }

    @Singleton
    @Provides
    fun provideNumbersFactDao(numbersFactDatabase: NumbersFactDatabase): NumbersFactDao{
        return numbersFactDatabase.numbersFactDao()
    }
}