package ua.`in`.factsofnumbers.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.`in`.factsofnumbers.domain.model.NumbersFact

@Database(entities = [NumbersFact::class], version = 1, exportSchema = false)
abstract class NumbersFactDatabase: RoomDatabase() {
    abstract fun numbersFactDao():NumbersFactDao
}